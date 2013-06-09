package ch.checkbit.activities;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.drawable.TransitionDrawable;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import ch.checkbit.R;

public class MusicActivity extends Activity {

    private MediaPlayer melody;

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
        sceneDrawable.startTransition(15000);

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

    }

    public void cut(View view) {

    }

    public void water(View view) {

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
        // Inflate the menu; this adds items to the action bar if it is present.
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

    private void playTone() {

        double duration = 1; // seconds
        double freqOfTone = 5000; // hz
        int sampleRate = 8000; // a number

        double dnumSamples = duration * sampleRate;
        dnumSamples = Math.ceil(dnumSamples);
        int numSamples = (int) dnumSamples;
        double sample[] = new double[numSamples];
        byte generatedSnd[] = new byte[2 * numSamples];

        for (int i = 0; i < numSamples; ++i) { // Fill the sample array
            sample[i] = Math.sin(freqOfTone * 2 * Math.PI * i / (sampleRate));
        }

        // convert to 16 bit pcm sound array
        // assumes the sample buffer is normalized.
        // convert to 16 bit pcm sound array
        // assumes the sample buffer is normalised.
        int idx = 0;
        int i = 0;

        int ramp = numSamples / 20; // Amplitude ramp as a percent of sample
                                    // count

        for (i = 0; i < ramp; ++i) { // Ramp amplitude up (to avoid clicks)
            double dVal = sample[i];
            // Ramp up to maximum
            final short val = (short) ((dVal * 32767 * i / ramp));
            // in 16 bit wav PCM, first byte is the low order byte
            generatedSnd[idx++] = (byte) (val & 0x00ff);
            generatedSnd[idx++] = (byte) ((val & 0xff00) >>> 8);
        }

        for (i = 0; i < numSamples - ramp; ++i) { // Max amplitude for most of
                                                  // the samples
            double dVal = sample[i];
            // scale to maximum amplitude
            final short val = (short) ((dVal * 32767));
            // in 16 bit wav PCM, first byte is the low order byte
            generatedSnd[idx++] = (byte) (val & 0x00ff);
            generatedSnd[idx++] = (byte) ((val & 0xff00) >>> 8);
        }

        // for (i = 0; i < numSamples; ++i) { // Ramp amplitude down
        // double dVal = sample[i];
        // // Ramp down to zero
        // final short val = (short) ((dVal * 32767 * (numSamples - i) / ramp));
        // // in 16 bit wav PCM, first byte is the low order byte
        // generatedSnd[idx++] = (byte) (val & 0x00ff);
        // generatedSnd[idx++] = (byte) ((val & 0xff00) >>> 8);
        // }

        AudioTrack audioTrack = null; // Get audio track
        try {
            audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, sampleRate, AudioFormat.CHANNEL_CONFIGURATION_MONO,
                    AudioFormat.ENCODING_PCM_16BIT, (int) numSamples * 2, AudioTrack.MODE_STATIC);
            audioTrack.write(generatedSnd, 0, generatedSnd.length); // Load the
                                                                    // track
            audioTrack.play(); // Play the track
        } catch (Exception e) {

        }

        int x = 0;
        do { // Montior playback to find when done
            if (audioTrack != null)
                x = audioTrack.getPlaybackHeadPosition();
            else
                x = numSamples;
        } while (x < numSamples);

        if (audioTrack != null)
            audioTrack.release();
    }

}
