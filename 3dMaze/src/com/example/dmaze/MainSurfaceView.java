package com.example.dmaze;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.KeyEvent;
import android.view.MotionEvent;

/*
 * Custom GL view by extending GLSurfaceView so as
 * to override event handlers such as onKeyUp(), onTouchEvent()
 */
public class MainSurfaceView extends GLSurfaceView {
	MainRenderer renderer; // Custom GL Renderer

	// For touch event
	private float previousX;
	private float previousY;
	private float previousZ;
	int sizeMaze = 0;

	// Constructor - Allocate and set the renderer
	public MainSurfaceView(Context context, int size, int[][] m) {
		super(context);

		renderer = new MainRenderer(context, size, m);
		this.setRenderer(renderer);
		// Request focus, otherwise key/button won't react
		this.requestFocus();
		this.setFocusableInTouchMode(true);
	}

	public void setSize(int size) {
		sizeMaze = size;
	}

	// Handler for key event. I was try to use this to move in the maze.
	// but it not work. I need to use the real keyboard to work.
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent evt) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_DPAD_LEFT: // Decrease Y-rotational speed
			renderer.speedY -= 0.1f;
			break;
		case KeyEvent.KEYCODE_DPAD_RIGHT: // Increase Y-rotational speed
			renderer.speedY += 0.1f;
			break;
		case KeyEvent.KEYCODE_DPAD_UP: // Decrease X-rotational speed
			renderer.speedX -= 0.1f;
			break;
		case KeyEvent.KEYCODE_DPAD_DOWN: // Increase X-rotational speed
			renderer.speedX += 0.1f;
			break;
		case KeyEvent.KEYCODE_A: // Zoom out (decrease z)
			renderer.z -= 0.2f;
			break;
		case KeyEvent.KEYCODE_Z: // Zoom in (increase z)
			renderer.z += 0.2f;
			break;
		}
		return true; // Event handled
	}

	// Handler for touch event. this work, so far I not find a way to not go
	// through
	// walls.
	@Override
	public boolean onTouchEvent(final MotionEvent evt) {
		float currentX = evt.getX();
		float currentY = evt.getY();
		float currentz = renderer.z;
		// float currentZ = evt.
		float deltaX, deltaY;
		switch (evt.getAction()) {
		case MotionEvent.ACTION_MOVE:
			// Modify rotational angles according to movement
			deltaX = currentX - previousX;
			deltaY = currentY - previousY;

			if (deltaY < 0)
				renderer.angleX -= 1f;
			else
				renderer.angleX += 1f;

			if (deltaX < 0)
				renderer.angleY -= 1f;
			else
				renderer.angleY += 1f;

			break;

		}
		// Save current x, y
		previousX = currentX;
		previousY = currentY;
		previousZ = currentz;
		return true; // Event handled
	}

}