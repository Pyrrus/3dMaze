package com.example.dmaze;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Random;

import javax.microedition.khronos.opengles.GL10;

public class fireFOUR {
	private FloatBuffer vertexBuffer;  // Buffer for vertex-array
	  
	private float[] vertices = {  // Vertices for the fire four
		   };
	  
	   // Constructor - Setup with random vertex buffer
	   public fireFOUR() {
		   
		   Random rand = new Random();
		   
		   float randomNum1 =  rand.nextFloat() * (1.5f - (-1)) + (-1);
		   float randomNum2 =  rand.nextFloat() * (1.5f - (-1)) + (-1);
		   float randomNum3 =  rand.nextFloat() * (1.5f - (-1)) + (-1);
		   float randomNum4 =  rand.nextFloat() * (1.5f - (-1)) + (-1);
		   float randomNum5 =  rand.nextFloat() * (1.5f - (-1)) + (-1);
		   float randomNum6 =  rand.nextFloat() * (1.5f - (-1)) + (-1);
		   float randomNum7 =  rand.nextFloat() * (1.5f - (-1)) + (-1);
		   float randomNum8 =  rand.nextFloat() * (1.5f - (-1)) + (-1);
		   float randomNum9 =  rand.nextFloat() * (1.5f - (-1)) + (-1);
		   float randomNum10 =  rand.nextFloat() * (1.5f - (-1)) + (-1);
		   float randomNum11 =  rand.nextFloat() * (1.5f - (-1)) + (-1);
		   float randomNum12 =  rand.nextFloat() * (1.5f - (-1)) + (-1);
		   
		   
		   
		    float[] vertices2 = {  // Vertices for the fire four
		    		randomNum1, randomNum2,  randomNum9,  // 0. 
		    		randomNum3, randomNum4,  randomNum10,  // 1. 
		    		randomNum5,  randomNum6,  randomNum11,  // 2. 
		    		randomNum7,  randomNum8,  randomNum12,  // 2. 
				   };
		   vertices = vertices2;
	      ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
	      vbb.order(ByteOrder.nativeOrder()); 
	      vertexBuffer = vbb.asFloatBuffer(); 
	      vertexBuffer.put(vertices);         
	      vertexBuffer.position(0);   
	   }
	  
	   // Render the shape
	   public void draw(GL10 gl) {
	      gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
	      gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
	      gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, vertices.length / 3);
	      gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	   }
}
