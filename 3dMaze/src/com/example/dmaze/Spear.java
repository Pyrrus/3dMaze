package com.example.dmaze;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;

/*
 * Spear
 * this make the spear trap which I learn from many type of the book and web
 */
public class Spear {
	private FloatBuffer vertexBuffer; // Buffer for vertex-array
	private FloatBuffer colorBuffer; // Buffer for color-array
	private ByteBuffer indexBuffer; // Buffer for index-array

	private float[] vertices = { // 5 vertices of the pyramid in (x,y,z)
	-1.0f, 4.0f, -1.0f, // 0. left-bottom-back
			1.0f, 4.0f, -1.0f, // 1. right-bottom-back
			1.0f, 4.0f, 1.0f, // 2. right-bottom-front
			-1.0f, 4.0f, 1.0f, // 3. left-bottom-front
			0.0f, 8.0f, 0.0f // 4. top
	};



	private byte[] indices = { // Vertex 4 Triangles
	2, 4, 3, // front face (CCW)
			1, 4, 2, // right face
			0, 4, 1, // back face
			4, 0, 3 // left face
	};
	
	private float[] colors = {  
		      0.0f, 0.0f, 1.0f, 1.0f, 
		   };
	
	// Constructor - setup with spear
	public Spear(float size) {

		float[] vertices2 = { // 5 vertices of the pyramid in (x,y,z)
				-1.0f / size, -1.0f / size, -1.0f / size, // 0. left-bottom-back
						1.0f / size, -1.0f / size, -1.0f / size, // 1. right-bottom-back
						1.0f / size, 1.0f / size, -1.0f / size, // 2. right-bottom-front
						-1.0f / size, -1.0f / size, 1.0f / size, // 3. left-bottom-front
						0.0f, 0, 8.0f / size // 4. top
				};
		 vertices = vertices2;
		ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
		vbb.order(ByteOrder.nativeOrder()); // Use native byte order
		vertexBuffer = vbb.asFloatBuffer(); // Convert from byte to float
		vertexBuffer.put(vertices); // Copy data into buffer
		vertexBuffer.position(0); // Rewind

		
		ByteBuffer cbb = ByteBuffer.allocateDirect(colors.length * 4);
	      cbb.order(ByteOrder.nativeOrder());
	      colorBuffer = cbb.asFloatBuffer();
	      colorBuffer.put(colors);
	      colorBuffer.position(0);
		indexBuffer = ByteBuffer.allocateDirect(indices.length);
		indexBuffer.put(indices);
		indexBuffer.position(0);
	}

	// Draw the it
	public void draw(GL10 gl) {
		// Front face in counter-clockwise orientation
		gl.glFrontFace(GL10.GL_CCW);

		// Enable arrays and define their buffers
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);

		gl.glDrawElements(GL10.GL_TRIANGLES, indices.length,
				GL10.GL_UNSIGNED_BYTE, indexBuffer);

		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
	}
}