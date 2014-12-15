package com.example.dmaze;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

/*
 * MainActivity.
 * open screen for the app
 */
public class MainActivity extends ActionBarActivity {
	GLSurfaceView glView;
	int mazeSize = 0;
	Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context = this;

		// set the button for 7x7
		Button button = (Button) findViewById(R.id.button1);

		// set the button for 8x8
		Button button2 = (Button) findViewById(R.id.button2);

		// set the button for 8x8
		Button button3 = (Button) findViewById(R.id.button3);

		// set the button for load file
		Button button4 = (Button) findViewById(R.id.button4);

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				Intent i = new Intent(getApplicationContext(), Viewer.class);
				i.putExtra("size", "7");
				startActivity(i);

			}

		});

		button2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				Intent i = new Intent(getApplicationContext(), Viewer.class);
				i.putExtra("size", "8");
				startActivity(i);
			}

		});

		button3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				Intent i = new Intent(getApplicationContext(), Viewer.class);
				i.putExtra("size", "9");
				startActivity(i);
			}

		});

		button4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				Intent i = new Intent(getApplicationContext(), Viewer.class);
				i.putExtra("size", "1");
				startActivity(i);
			}

		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
