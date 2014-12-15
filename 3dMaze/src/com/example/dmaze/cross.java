package com.example.dmaze;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;

/*
 * cross.
 * this make the made of the cross.
 */
class cross {
	  private FloatBuffer vertexBuffer;  // Buffer for vertex-array
	  
	   private float[] vertices = {  // Vertices for the square
	      -2.0f, 2.5f,  0.0f,  // 0. left-bottom
	       2.0f, 2.5f,  0.0f,  // 1. right-bottom
	      -2.0f,  3.0f,  0.0f,  // 2. left-top
	       2.0f,  3.0f,  0.0f   // 3. right-top
	   };
	  
	   // Constructor - Setup the vertex buffer
	   public cross(float size) {
		    float[] vertices2 = {  // Vertices for the square
				      -2.0f / size, 2.5f / size,  0.0f / size,  // 0. left-bottom
				       2.0f / size, 2.5f / size,  0.0f / size,  // 1. right-bottom
				      -2.0f / size,  3.0f / size,  0.0f / size,  // 2. left-top
				       2.0f / size,  3.0f / size,  0.0f / size   // 3. right-top
				   };
		    vertices = vertices2;
	      // Setup vertex array buffer. Vertices in float. A float has 4 bytes
	      ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
	      vbb.order(ByteOrder.nativeOrder()); // Use native byte order
	      vertexBuffer = vbb.asFloatBuffer(); // Convert from byte to float
	      vertexBuffer.put(vertices);         // Copy data into buffer
	      vertexBuffer.position(0);           // Rewind
	   }
	  
	   // draw object
	   public void draw(GL10 gl) {
	      // Enable vertex-array and define its buffer
	      gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
	      gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
	      // Draw the primitives from the vertex-array directly
	      gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, vertices.length / 3);
	      gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	   }
	   
	   
}