package com.example.dmaze;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

/*
 * Endgame 
 * add image over top the cube
 */
public class endGame {
   private FloatBuffer vertexBuffer; 
   private FloatBuffer texBuffer;    
  
   private float[] vertices = { // Vertices for a face
      -1.0f, -1.0f, 0.0f,  
       1.0f, -1.0f, 0.0f,  
      -1.0f,  1.0f, 0.0f, 
       1.0f,  1.0f, 0.0f   
   };
  
   float[] texData = {
      0.0f, 1.0f,  
      1.0f, 1.0f,  
      0.0f, 0.0f,  
      1.0f, 0.0f   
   };
   int[] textureIDs = new int[1];   // texture-ID
     
   // Constructor - for Endgame
   public endGame() {
     
      ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
      vbb.order(ByteOrder.nativeOrder()); 
      vertexBuffer = vbb.asFloatBuffer(); 
      vertexBuffer.put(vertices);         
      vertexBuffer.position(0);           
  
      
      ByteBuffer tbb = ByteBuffer.allocateDirect(texData.length * 4);
      tbb.order(ByteOrder.nativeOrder());
      texBuffer = tbb.asFloatBuffer();
      texBuffer.put(texData);
      texBuffer.position(0);
   }
   
   // Draw the shape
   public void draw(GL10 gl) {
      gl.glFrontFace(GL10.GL_CCW);    
      gl.glEnable(GL10.GL_CULL_FACE); 
      gl.glCullFace(GL10.GL_BACK);     
   
      gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
      gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
      gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);  
      gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, texBuffer); 
      
      // front
      gl.glPushMatrix();
      gl.glTranslatef(0.0f, 0.0f, 1.0f);
      gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
      gl.glPopMatrix();
  
      // left
      gl.glPushMatrix();
      gl.glRotatef(270.0f, 0.0f, 1.0f, 0.0f);
      gl.glTranslatef(0.0f, 0.0f, 1.0f);
      gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
      gl.glPopMatrix();
  
      // back
      gl.glPushMatrix();
      gl.glRotatef(180.0f, 0.0f, 1.0f, 0.0f);
      gl.glTranslatef(0.0f, 0.0f, 1.0f);
      gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
      gl.glPopMatrix();
  
      // right
      gl.glPushMatrix();
      gl.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
      gl.glTranslatef(0.0f, 0.0f, 1.0f);
      gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
      gl.glPopMatrix();
  
      // top
      gl.glPushMatrix();
      gl.glRotatef(270.0f, 1.0f, 0.0f, 0.0f);
      gl.glTranslatef(0.0f, 0.0f, 1.0f);
      gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
      gl.glPopMatrix();
  
      // bottom
      gl.glPushMatrix();
      gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
      gl.glTranslatef(0.0f, 0.0f, 1.0f);
      gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
      gl.glPopMatrix();
  
      gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);  // Disable texture
      gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
      gl.glDisable(GL10.GL_CULL_FACE);
   }
  
   // Load an image into GL texture
   public void loadTexture(GL10 gl, Context context) {
      gl.glGenTextures(1, textureIDs, 0); // Generate texture-ID array

      gl.glBindTexture(GL10.GL_TEXTURE_2D, textureIDs[0]);   // Bind to texture ID
      // Set up texture filters
      gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
      gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
  
      // Construct an input stream to texture image "res\drawable\crate2.gif"
      InputStream istream = context.getResources().openRawResource(R.drawable.crate2);
      Bitmap bitmap;
      try {
         // Read and decode input as bitmap from the image
         bitmap = BitmapFactory.decodeStream(istream);
      } finally {
         try {
            istream.close();
         } catch(IOException e) { }
      }
  
      // Build Texture from loaded bitmap to display the image
      GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
      bitmap.recycle();
   }
}