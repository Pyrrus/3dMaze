package com.example.dmaze;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;

/*
 * Floor
 * make the floor and type of floor trap
 */
public class LeftRightSide {
	private FloatBuffer vertexBuffer; // Buffer for vertex-array

	private float[] vertices = { // Vertices for the square
	-1.0f, -1.0f, 0.0f, // 0. left-bottom
			1.0f, -1.0f, 0.0f, // 1. right-bottom
			-1.0f, 1.0f, 0.0f, // 2. left-top
			1.0f, 1.0f, 0.0f // 3. right-top
	};

	// color for the floor
	private float red;
	private float blue;
	private float green;
	private float opacity;

	// Constructor - Setup the vertex buffer
	public LeftRightSide(float size, float redData, float blueData, float greenData,
			float opacityData) {
		float[] verticesHolder = { // Vertices for the square
				-size / 2  , size / 2 - 5,  -5.0f,  // 0. left-bottom
				size / 2, size / 2 - 5, -5.0f,  // 1. right-bottom
			      -size / 2,  size / 2,   0,  // 2. left-top
			      size / 2,  size / 2,  0f   // 3. right-top 
			       };
		vertices = verticesHolder;
		red = redData;
		green = greenData;
		blue = blueData;
		opacity = opacityData;

		ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
		vbb.order(ByteOrder.nativeOrder()); // Use native byte order
		vertexBuffer = vbb.asFloatBuffer(); // Convert from byte to float
		vertexBuffer.put(vertices); // Copy data into buffer
		vertexBuffer.position(0); // Rewind
	}

	public void draw(GL10 gl) {
		gl.glColor4f(red, green, blue, opacity);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, vertices.length / 3);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}
}
