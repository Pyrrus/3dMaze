package com.example.dmaze;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;

/*
 * lowerPart
 * this will draw the lower part of the creatures
 */
public class lowerPart {
	private FloatBuffer vertexBuffer;

	// set the frame work
	private float[] vertices = { -1.0f / 3, 1.0f / 3, 0.0f, // 0. left
			0.0f, -4.0f / 3, -2.0f, // 1. bottom
			0.0f, -0.0f / 3, 0.0f, // 2. top
			1.0f / 3, 1.0f / 3, 0.0f // 3. right
	};

	public lowerPart() {

		ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
		vbb.order(ByteOrder.nativeOrder());
		vertexBuffer = vbb.asFloatBuffer();
		vertexBuffer.put(vertices);
		vertexBuffer.position(0);
	}

	// draw the object
	public void draw(GL10 gl) {
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, vertices.length / 3);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}
}