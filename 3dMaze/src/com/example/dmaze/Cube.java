package com.example.dmaze;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;

/*
 * Cube
 * make my walls and make a the treasure. I was planing on making on the texture for
 * the treasure. it work, but the texture will go over the all the objects so I stop
 * that.
 */
class Cube {
	private FloatBuffer vertexBuffer;

	private float[][] colors = { { 1.0f, 0.0f, 0.0f, 1.0f },
			{ 1.0f, 0.0f, 0.0f, 1.0f }, { 1.0f, 0.0f, 0.0f, 1.0f },
			{ 1.0f, 0.0f, 0.0f, 1.0f }, { 1.0f, 0.0f, 0.0f, 1.0f },
			{ 1.0f, 0.0f, 0.0f, 1.0f } };

	// Constructor - Set up the buffers
	public Cube(float size) {
		float[] vertices = { // Vertices for the front face
		-size / 2, -size / 2, size / 2, // 0. left-bottom-front
				size / 2, -size / 2, size / 2, // 1. right-bottom-front
				-size / 2, size / 2, size / 2, // 2. left-top-front
				size / 2, size / 2, size / 2 // 3. right-top-front
		};

		ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
		vbb.order(ByteOrder.nativeOrder());
		vertexBuffer = vbb.asFloatBuffer();
		vertexBuffer.put(vertices);
		vertexBuffer.position(0);

	}

	// Draw the cube
	public void draw(GL10 gl) {
		gl.glFrontFace(GL10.GL_CCW); // Front face in counter-clockwise
										// orientation
		gl.glEnable(GL10.GL_CULL_FACE); // Enable cull face
		gl.glCullFace(GL10.GL_BACK); // Cull the back face (don't display)

		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);

		// Front
		gl.glColor4f(colors[0][0], colors[0][1], colors[0][2], colors[0][3]);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);

		// Right
		gl.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
		gl.glColor4f(colors[1][0], colors[1][1], colors[1][2], colors[1][3]);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);

		// Back 
		gl.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
		gl.glColor4f(colors[2][0], colors[2][1], colors[2][2], colors[2][3]);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);

		// Left
		gl.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
		gl.glColor4f(colors[3][0], colors[3][1], colors[3][2], colors[3][3]);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);

		// Bottom
		gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
		gl.glColor4f(colors[4][0], colors[4][1], colors[4][2], colors[4][3]);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);

		// Top 
		gl.glRotatef(180.0f, 1.0f, 0.0f, 0.0f);
		gl.glColor4f(colors[5][0], colors[5][1], colors[5][2], colors[5][3]);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);

		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisable(GL10.GL_CULL_FACE);
	}

}
