package com.example.dmaze;

import android.app.Activity;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import com.example.dmaze.FileOperations;

/*
 * Viewer.
 * this will save and load the file for the maze. 
 * Also, make a maze with the random layout. 
 * Lastly, make view for the opengl to display on the screen.
 */
public class Viewer extends Activity {
	GLSurfaceView glView;
	int[][] m;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent intent = getIntent();

		String size = intent.getStringExtra("size");

		// get the what size of the file from mainActivity
		int mSize = Character.getNumericValue(size.charAt(0));

		if (mSize != 1) {
			// save the file
			String filename = "saveMaze";

			MazeGenerator maze = new MazeGenerator(mSize, mSize);

			m = maze.getMaze();
			FileOperations fop = new FileOperations();

			String data = "" + mSize;
			for (int i = 0; i < mSize; i++) {
				for (int j = 0; j < mSize; j++) {
					data += m[i][j];
				}
			}

			fop.write(filename, data);

		} else {
			// load the file and write file
			String readfilename = "saveMaze";
			FileOperations fop = new FileOperations();
			String text = fop.read(readfilename);

			int counter = 0;
			mSize = Character.getNumericValue(text.charAt(counter));

			m = new int[mSize][mSize];

			counter++;

			for (int i = 0; i < mSize; i++) {
				for (int j = 0; j < mSize; j++) {
					m[i][j] = Character.getNumericValue(text.charAt(counter));
					counter++;
				}
			}

			String data = "" + mSize;
			for (int i = 0; i < mSize; i++) {
				for (int j = 0; j < mSize; j++) {
					data += m[i][j];
				}
			}

			fop.write(readfilename + "Load", data);

		}

		// set the screen full size and remove the title
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		// view of the OpenGL on the screen
		glView = new MainSurfaceView(this, mSize, m);
		setContentView(glView);

	}

	@Override
	protected void onPause() {
		super.onPause();
		glView.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		glView.onResume();
	}
}
