package ch.checkbit.activities;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.drawable.TransitionDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import ch.checkbit.R;

/**
 * handles the special music feature
 * 
 * @author zeta
 * 
 */
public class MusicActivity extends Activity {

    private MediaPlayer melody;
    private static final int BLOSSON_TRANSITION_DURATION = 15000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        melody = MediaPlayer.create(getBaseContext(), R.raw.bluemood);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_music);
        melody.start();
        ImageView scene = (ImageView) findViewById(R.id.blossomanime);
        TransitionDrawable sceneDrawable = (TransitionDrawable) scene.getDrawable();
        sceneDrawable.startTransition(BLOSSON_TRANSITION_DURATION);

        View mainScreen = findViewById(R.id.musicscreen);
        mainScreen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (melody.isPlaying()) {
                    melody.pause();
                } else {
                    melody.start();
                }
            }
        });
        setupActionBar();
    }

    public void play(View view) {
        // TODO
    }

    public void cut(View view) {
        // TODO
    }

    public void water(View view) {
        // TODO
    }

    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setupActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.music, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        melody.stop();
    }

}
