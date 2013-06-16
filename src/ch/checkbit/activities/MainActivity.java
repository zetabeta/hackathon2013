package ch.checkbit.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import ch.checkbit.MultipleTransitionDrawable;
import ch.checkbit.R;

public class MainActivity extends Activity {

    private static final int TRANSITION_DURATION_GROW = 1000;
    private static final int TRANSITION_DURATION_WATER = 3000;
    private static final int TRANSITION_DURATION_CUT = 3000;
    private static final int TRANSITION_DURATION_THURSTY = 3000;
    private static final int TRANSITION_DURATION_HAIRY = 3000;
    private static final long TIME_WATER_MILLISECONDS = 1000;
    // private static final long TIME_CUT_MILLISECONDS = 1000;
    private static final int PAUSE_DURATION = 1;
    private Drawable finalState;

    private long startTime = System.currentTimeMillis();
    private boolean started = false;
    private MultipleTransitionDrawable ctdGrow;
    private MultipleTransitionDrawable ctdThursty;
    private MultipleTransitionDrawable ctdHairy;
    private ImageView scene;
    private boolean cut = false;
    private boolean water = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_grow);

        View mainScreen = findViewById(R.id.mainscreen);
        mainScreen.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                scene = (ImageView) findViewById(R.id.grow1);
                finalState = getResources().getDrawable(R.drawable.grow_13);

                if (!started) {
                    started = true;
                    startGrowSequence();
                } else {
                    long timePassed = System.currentTimeMillis() - startTime;
                    if (timePassed > TIME_WATER_MILLISECONDS * 10) {
                        if (timePassed % 2 == 0) {
                            ctdGrow.invalidateSelf();
                            startThurstySequence();
                            startTime = System.currentTimeMillis();
                            water = true;
                        } else {
                            ctdGrow.invalidateSelf();
                            startHairySequence();
                            startTime = System.currentTimeMillis();
                            cut = true;
                        }
                    }
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void play(View view) {
        if (cut == false && water == false) {
            Intent intent = new Intent(this, MusicActivity.class);
            startActivity(intent);
        }
    }

    public void cut(View view) {
        if (cut == true) {
            invalidatePrevious();
            Drawable thursty = getResources().getDrawable(R.drawable.hairy);
            ctdGrow = new MultipleTransitionDrawable(new Drawable[] { thursty, finalState });
            scene.setImageDrawable(ctdGrow);
            ctdGrow.startTransition(TRANSITION_DURATION_CUT, PAUSE_DURATION);
            cut = false;
        }
    }

    public void water(View view) {
        if (water == true) {
            invalidatePrevious();
            Drawable thursty = getResources().getDrawable(R.drawable.thursty);
            ctdGrow = new MultipleTransitionDrawable(new Drawable[] { thursty, finalState });
            scene.setImageDrawable(ctdGrow);
            ctdGrow.startTransition(TRANSITION_DURATION_WATER, PAUSE_DURATION);
            water = false;
        }
    }

    private void invalidatePrevious() {
        try {
            ctdThursty.invalidateSelf();
        } catch (Exception e) {
            Log.i("ERROR:", "problem while invalidating thursy sequence...");
        }

        try {
            ctdHairy.invalidateSelf();
        } catch (Exception e) {
            Log.i("ERROR:", "problem while invalidating hairy sequence...");
        }
    }

    private void startGrowSequence() {
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
        ctdGrow = new MultipleTransitionDrawable(new Drawable[] { d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12,
                finalState });
        scene.setImageDrawable(ctdGrow);
        ctdGrow.startTransition(TRANSITION_DURATION_GROW, PAUSE_DURATION);
    }

    private void startThurstySequence() {
        Drawable thursty = getResources().getDrawable(R.drawable.thursty);
        ctdThursty = new MultipleTransitionDrawable(new Drawable[] { finalState, thursty });
        scene.setImageDrawable(ctdThursty);
        ctdThursty.startTransition(TRANSITION_DURATION_THURSTY, PAUSE_DURATION);
    }

    private void startHairySequence() {
        Drawable hairy = getResources().getDrawable(R.drawable.hairy);
        ctdHairy = new MultipleTransitionDrawable(new Drawable[] { finalState, hairy });
        scene.setImageDrawable(ctdHairy);
        ctdHairy.startTransition(TRANSITION_DURATION_HAIRY, PAUSE_DURATION);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

}
