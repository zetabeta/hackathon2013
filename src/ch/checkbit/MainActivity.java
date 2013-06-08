package ch.checkbit;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.grow);
		View mainScreen = findViewById(R.id.mainscreen);
		mainScreen.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				ImageView scene = (ImageView) findViewById(R.id.growgraphic);
				TransitionDrawable sceneDrawable = (TransitionDrawable) scene
						.getDrawable();
				sceneDrawable.startTransition(3000);

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
	public void sendMessage(View view) {
		Intent intent = new Intent(this, MoveActivity.class);
		// EditText editText = (EditText) findViewById(R.id.edit_message);
		// String message = editText.getText().toString();
		// intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);
	}

}
