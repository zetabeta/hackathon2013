package ch.checkbit.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import ch.checkbit.CyclicTransitionDrawable;
import ch.checkbit.R;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grow);
        View mainScreen = findViewById(R.id.mainscreen);
        mainScreen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ImageView scene = (ImageView) findViewById(R.id.growgraphic);
                Drawable d1 = getResources().getDrawable(R.drawable.mainbonsai);
                Drawable d2 = getResources().getDrawable(R.drawable.mainbonsai1);
                Drawable d3 = getResources().getDrawable(R.drawable.mainbonsai2);
                CyclicTransitionDrawable ctd = new CyclicTransitionDrawable(new Drawable[] { d1,
                        d2, d3 });
                scene.setImageDrawable(ctd);
                ctd.startTransition(3000, 3000);
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

}
