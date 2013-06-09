package ch.checkbit.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import ch.checkbit.CyclicTransitionDrawable;
import ch.checkbit.R;

public class MainActivity extends Activity {

    private SharedPreferences sharedPref;
    private long startTime = System.currentTimeMillis();
    private boolean started = false;
    private CyclicTransitionDrawable ctdGrow;
    private CyclicTransitionDrawable ctdThursty;
    private ImageView scene;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_grow);

        View mainScreen = findViewById(R.id.mainscreen);

        mainScreen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                scene = (ImageView) findViewById(R.id.grow1);
                Drawable d2 = getResources().getDrawable(R.drawable.grow_2);
                Drawable d3 = getResources().getDrawable(R.drawable.grow_3);
                Drawable d4 = getResources().getDrawable(R.drawable.grow_4);
                Drawable d5 = getResources().getDrawable(R.drawable.grow_5);
                Drawable d6 = getResources().getDrawable(R.drawable.grow_6);
                Drawable d7 = getResources().getDrawable(R.drawable.grow_7);
                Drawable d8 = getResources().getDrawable(R.drawable.grow_8);
                Drawable d9 = getResources().getDrawable(R.drawable.grow_9);
                Drawable d10 = getResources().getDrawable(R.drawable.grow_10);
                Drawable d11 = getResources().getDrawable(R.drawable.grow_11);
                Drawable d12 = getResources().getDrawable(R.drawable.grow_12);
                Drawable d13 = getResources().getDrawable(R.drawable.grow_13);
                ctdGrow = new CyclicTransitionDrawable(new Drawable[] { d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12,
                        d13 });
                if (!started) {
                    Log.i("mainActivity started ", "" + started);
                    started = true;

                    scene.setImageDrawable(ctdGrow);
                    ctdGrow.startTransition(1000, 1);
                } else {
                    Log.i("mainActivity started ", "" + started);
                    long timePassed = System.currentTimeMillis() - startTime;
                    Log.i("timePassed ", "" + timePassed);
                    if (timePassed > 1000 * 30) {
                        ctdGrow.invalidateSelf();
                        Drawable thursty = getResources().getDrawable(R.drawable.thursty);
                        ctdThursty = new CyclicTransitionDrawable(new Drawable[] { d13, thursty });
                        scene.setImageDrawable(ctdThursty);
                        ctdThursty.startTransition(3000, 1);
                        startTime = System.currentTimeMillis();
                    }
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /** Called when the user clicks the Send button */
    public void play(View view) {
        Intent intent = new Intent(this, MusicActivity.class);
        // EditText editText = (EditText) findViewById(R.id.edit_message);
        // String message = editText.getText().toString();
        // intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void cut(View view) {

    }

    public void water(View view) {
        Log.i("water", "watering...");
        ctdThursty.invalidateSelf();
        Drawable thursty = getResources().getDrawable(R.drawable.thursty);
        Drawable d13 = getResources().getDrawable(R.drawable.grow_13);
        CyclicTransitionDrawable ctd1 = new CyclicTransitionDrawable(new Drawable[] { thursty, d13 });
        scene.setImageDrawable(ctd1);
        ctd1.startTransition(3000, 1);
        // ImageView scene = (ImageView)
        // findViewById(R.id.notthurstyanymoreanime);
        // TransitionDrawable sceneDrawable = (TransitionDrawable)
        // scene.getDrawable();
        // sceneDrawable.startTransition(3000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // sharedPref.edit().remove("STARTED1");
        // sharedPref.edit().clear();
        // sharedPref.edit().commit();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

}
