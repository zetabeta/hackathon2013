package ch.checkbit.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import ch.checkbit.CyclicTransitionDrawable;
import ch.checkbit.R;

public class MainActivity extends Activity {

    private SharedPreferences sharedPref;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grow);
        View mainScreen = findViewById(R.id.mainscreen);
        // sharedPref =
        // getApplicationContext().getSharedPreferences("ch.checkbit.MainActivity",
        // Context.MODE_WORLD_READABLE);

        mainScreen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Log.i("test", "" + sharedPref.contains("Blabla"));
                // Log.i("test", "" + sharedPref.contains("STARTED1"));
                // Editor editor =
                // getApplicationContext().getSharedPreferences("ch.checkbit.MainActivity",
                // Context.MODE_WORLD_READABLE).edit();
                // editor.putBoolean("STARTED1", true);
                // editor.commit();
                // Log.i("test",
                // ""
                // +
                // getApplicationContext().getSharedPreferences("ch.checkbit.MainActivity",
                // Context.MODE_PRIVATE).contains("STARTED"));

                ImageView scene = (ImageView) findViewById(R.id.grow1);
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
                CyclicTransitionDrawable ctd = new CyclicTransitionDrawable(new Drawable[] { d2, d3, d4, d5, d6, d7,
                        d8, d9, d10, d11, d12, d13 });
                scene.setImageDrawable(ctd);
                ctd.startTransition(1000, 1);
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

    public void cut() {

    }

    public void water() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // sharedPref.edit().remove("STARTED1");
        // sharedPref.edit().clear();
        // sharedPref.edit().commit();
    }

}
