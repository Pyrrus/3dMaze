//package com.example.dmaze;
//
//import java.util.Random;
//import javax.microedition.khronos.egl.EGLConfig;
//import javax.microedition.khronos.opengles.GL10;
//import android.content.Context;
//import android.opengl.GLU;
//import com.example.dmaze.MainSurfaceView;
//import com.example.dmaze.FileOperations;
//
///*
// * MainRenderer.
// * This is the where the all the display at.
// */
//public class MainRenderer implements MainSurfaceView.Renderer {
//
//	public int maxx;
//	public int maxz;
//	private final int[][] m;
//	private Context context;
//
//	public MainRenderer(Context context, int size, int[][] Maze) {
//		maxx = size;
//		maxz = size;
//		m = Maze;
//		this.context = context;
//
//		// make another save to see it is saving the file right and test
//		// it same from the first one.
//		String filename = "saveMaze";
//		FileOperations fop = new FileOperations();
//
//		String data = "" + size;
//		for (int i = 0; i < size; i++) {
//			for (int j = 0; j < size; j++) {
//				data += m[i][j];
//			}
//		}
//
//		fop.write(filename + "Renderer", data);
//	}
//
//	// help with the size of the maze and angles for the moving the screen
//	public float mAngleX;
//	public float mAngleY;
//	public float size = 10.0f;
//	public float WORLD_SIZE = 200.0f;
//	public float wallSize = WORLD_SIZE / size;
//
//	// color of the light
//	float[] colorBlack = { 0.0f, 0.0f, 0.0f, 1.0f };
//	float[] colorWhite = { 1.0f, 1.0f, 1.0f, 1.0f };
//	float[] colorGray = { 0.6f, 0.6f, 0.6f, 1.0f };
//	float[] colorRed = { 1.0f, 0.25f, 0.0f, .02f };
//	float[] colorBlue = { 0.0f, 0.0f, 1.0f, 1.0f };
//	float[] colorGreen = { 0.0f, 1.0f, 0.0f, 1.0f };
//	float[] colorYellow = { 1.0f, 1.0f, 0.0f, 1.0f };
//	float[] colorLightYellow = { .5f, .5f, 0.0f, 1.0f };
//	float[] colorGold = { 0.8f, 0.498039f, 0.196078f, 1.0f };
//
//	// For controlling maze's z-position, x and y angles and speeds
//	float angleX = 0;
//	float angleY = 0;
//	float speedX = 0;
//	float speedY = 0;
//	float z = wallSize / 2.0f;
//
//	// if I using the keyboard I can set the lighting on or off
//	boolean lightingEnabled = false;
//
//	// add the odd lighting for later time
//	private float[] lightDiffuse = { 0.5f, 0.5f, 0.5f, 0.5f };
//	private float[] lightPosition = { 0.0f, 0.0f, 10.0f, 0.5f };
//
//	// for the exit which is the treasure
//	int minObject = 0;
//	int maxObject = 1; // max of object that need to be made
//
//	// make the size of the well
//	private final Cube mCube = new Cube(wallSize);
//
//	// make the treasure
//	private final Cube box = new Cube(2);
//
//	// make the floor
//	private final floor mFloor = new floor(wallSize, 0.80f, 0.80f, 0.80f, 1.0f);
//
//	// make the water floor
//	private final floor water = new floor(wallSize, 0.439216f, 0.858824f,
//			0.576471f, 1.0f);
//
//	// make acid floor
//	private final floor acid = new floor(wallSize, 0.32f, 0.49f, 0.46f, 1.0f);
//
//	// make the uneven floors
//	private final lowerFloor LFloor = new lowerFloor(wallSize, 0.309804f,
//			0.184314f, 0.184314f, 1.0f);
//
//	// make the uneven floors
//	private final upperFloor UpFloor = new upperFloor(wallSize, 0.85f, 0.53f,
//			0.10f, 1.0f);
//
//	private final threeDCircle lighter = new threeDCircle();
//
//	private final Spear Spear = new Spear();
//
//	private final body body = new body();
//
//	private final cross cross = new cross();
//
//	private final upperPart upperJaw = new upperPart();
//
//	private final lowerPart lowerJaw = new lowerPart();
//
//	@Override
//	public void onDrawFrame(GL10 gl) {
//
//		float copx = wallSize / 2.0f;
//
//		float copy = 0.0f;
//
//		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
//		gl.glLoadIdentity();
//
//		// start point
//		gl.glTranslatef(copx, copy, -z);
//
//		// help moving in 3D
//		gl.glRotatef(angleX, 1.0f, 0.0f, 0.0f);
//		gl.glRotatef(angleY, 0.0f, 1.0f, 0.0f);
//
//		// display the map
//		displayMaze(gl);
//
//		angleX += speedX;
//		angleY += speedY;
//
//	}
//
//	public void displayMaze(GL10 gl) {
//		// set the lighting on
//		gl.glEnable(GL10.GL_LIGHTING);
//
//		// count how many exit in the world
//		int exit = 0;
//
//		// help with the 3d world
//		float offset = 0.0f;
//
//		// count how many fire in the world
//		int counterFire = 0;
//
//		Random rand = new Random();
//		offset = -(WORLD_SIZE - wallSize) / 2.0f;
//		for (int i = 0; i < maxx; i++) {
//			for (int j = 0; j < maxz; j++) {
//				// set all the light to off
//				gl.glDisable(GL10.GL_LIGHT2);
//				gl.glDisable(GL10.GL_LIGHT1);
//				gl.glDisable(GL10.GL_LIGHT0);
//				gl.glDisable(GL10.GL_LIGHT3);
//				gl.glDisable(GL10.GL_LIGHT4);
//				gl.glDisable(GL10.GL_LIGHT5);
//				gl.glDisable(GL10.GL_LIGHT6);
//				gl.glDisable(GL10.GL_LIGHT7);
//				if (m[i][j] == 1) {
//					// wall of the world
//					gl.glEnable(GL10.GL_LIGHT2);
//					gl.glPushMatrix();
//					gl.glTranslatef(offset + i * wallSize, 0.0f, offset + j
//							* wallSize);
//					float spotDirection[] = { offset + i * wallSize, 0.0f,
//							offset + j * wallSize };
//					gl.glLightfv(GL10.GL_LIGHT2, GL10.GL_SPOT_DIRECTION,
//							spotDirection, 0);
//					gl.glLightfv(GL10.GL_LIGHT2, GL10.GL_AMBIENT, colorYellow,
//							0);
//					gl.glLightfv(GL10.GL_LIGHT2, GL10.GL_DIFFUSE, colorYellow,
//							0);
//					gl.glLightfv(GL10.GL_LIGHT2, GL10.GL_SPECULAR, colorYellow,
//							0);
//					gl.glLightf(GL10.GL_LIGHT2, GL10.GL_CONSTANT_ATTENUATION,
//							1.2f);
//					mCube.draw(gl);
//					gl.glPopMatrix();
//
//				} else if (m[i][j] == 2) {
//					// lower and upper floor
//					gl.glEnable(GL10.GL_LIGHT0);
//					float spotDirection[] = { offset + i * wallSize, 0.8f,
//							offset + j * wallSize };
//					gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPOT_DIRECTION,
//							spotDirection, 0);
//					gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, colorGray, 0);
//					gl.glLightf(GL10.GL_LIGHT0, GL10.GL_CONSTANT_ATTENUATION,
//							0.2f);
//					gl.glPushMatrix();
//					gl.glTranslatef(offset + i * wallSize, 0.8f, offset + j
//							* wallSize);
//					LFloor.draw(gl);
//					gl.glPopMatrix();
//
//					float spotDirection2[] = { offset + i * wallSize, 0.0f,
//							offset + j * wallSize };
//					gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPOT_DIRECTION,
//							spotDirection2, 0);
//					gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, colorGray, 0);
//					gl.glLightf(GL10.GL_LIGHT0, GL10.GL_CONSTANT_ATTENUATION,
//							0.2f);
//					gl.glPushMatrix();
//					gl.glTranslatef(offset + i * wallSize, 0.0f, offset + j
//							* wallSize);
//					UpFloor.draw(gl);
//					gl.glPopMatrix();
//				} else if (m[i][j] == 3) {
//					// water floor
//					gl.glEnable(GL10.GL_LIGHT3);
//					float spotDirection[] = { offset + i * wallSize, 0.0f,
//							offset + j * wallSize };
//					gl.glLightfv(GL10.GL_LIGHT3, GL10.GL_SPOT_DIRECTION,
//							spotDirection, 0);
//					gl.glLightfv(GL10.GL_LIGHT3, GL10.GL_AMBIENT,
//							colorLightYellow, 0);
//					gl.glLightfv(GL10.GL_LIGHT3, GL10.GL_DIFFUSE, colorBlue, 0);
//					gl.glLightfv(GL10.GL_LIGHT3, GL10.GL_SPECULAR, colorYellow,
//							0);
//					gl.glPushMatrix();
//					gl.glTranslatef(offset + i * wallSize, 0.0f, offset + j
//							* wallSize);
//					water.draw(gl);
//					gl.glPopMatrix();
//
//				} else if (m[i][j] == 4) {
//					// make the exit in the game
//					int randomNum = rand.nextInt((maxObject - minObject) + 1)
//							+ minObject; // give me random number
//					if (exit != 1) {
//						exit += 1;
//						gl.glEnable(GL10.GL_LIGHT7);
//						float spotDirectio4[] = { offset + i * wallSize, 0.0f,
//								offset + j * wallSize };
//						gl.glLightfv(GL10.GL_LIGHT7, GL10.GL_SPOT_DIRECTION,
//								spotDirectio4, 0);
//						gl.glLightfv(GL10.GL_LIGHT7, GL10.GL_AMBIENT,
//								colorLightYellow, 0);
//						gl.glLightfv(GL10.GL_LIGHT7, GL10.GL_SPECULAR,
//								colorWhite, 0);
//						gl.glLightfv(GL10.GL_LIGHT7, GL10.GL_DIFFUSE,
//								colorGold, 0);
//						gl.glLightf(GL10.GL_LIGHT7,
//								GL10.GL_CONSTANT_ATTENUATION, 0.2f);
//						gl.glPushMatrix();
//						gl.glTranslatef(offset + i * wallSize, 0.0f, offset + j
//								* wallSize);
//						box.draw(gl);
//						gl.glPopMatrix();
//
//					}
//
//					// normal floor
//					gl.glEnable(GL10.GL_LIGHT0);
//					float spotDirection[] = { offset + i * wallSize, 0.0f,
//							offset + j * wallSize };
//					gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPOT_DIRECTION,
//							spotDirection, 0);
//					gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT,
//							colorLightYellow, 0);
//					gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPECULAR, colorWhite,
//							0);
//					gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, colorGray, 0);
//					gl.glLightf(GL10.GL_LIGHT0, GL10.GL_CONSTANT_ATTENUATION,
//							0.2f);
//					gl.glPushMatrix();
//					gl.glTranslatef(offset + i * wallSize, 0.0f, offset + j
//							* wallSize);
//					mFloor.draw(gl);
//					gl.glPopMatrix();
//				} else if (m[i][j] == 5) {
//					// acid floor
//					gl.glEnable(GL10.GL_LIGHT5);
//					float spotDirection[] = { offset + i * wallSize, 0.0f,
//							offset + j * wallSize };
//					gl.glLightfv(GL10.GL_LIGHT5, GL10.GL_SPOT_DIRECTION,
//							spotDirection, 0);
//					gl.glLightfv(GL10.GL_LIGHT5, GL10.GL_AMBIENT,
//							colorLightYellow, 0);
//					gl.glLightfv(GL10.GL_LIGHT5, GL10.GL_SPECULAR, colorWhite,
//							0);
//					gl.glLightfv(GL10.GL_LIGHT5, GL10.GL_DIFFUSE, colorGreen, 0);
//					gl.glLightf(GL10.GL_LIGHT5, GL10.GL_CONSTANT_ATTENUATION,
//							0.2f);
//					gl.glPushMatrix();
//					gl.glTranslatef(offset + i * wallSize, 0.0f, offset + j
//							* wallSize);
//					acid.draw(gl);
//					gl.glPopMatrix();
//
//				} else if (m[i][j] == 6) {
//					// deep holes
//				} else if (m[i][j] == 7) {
//					// fire trap and normal floor
//					if (counterFire != 1) {
//						counterFire += 1;
//						gl.glEnable(GL10.GL_LIGHT1);
//						gl.glPushMatrix();
//						gl.glTranslatef(offset + i * wallSize, 0.0f, offset + j
//								* wallSize);
//						float spotDirection[] = { offset + i * wallSize, 0.0f,
//								offset + j * wallSize };
//						gl.glLightfv(GL10.GL_LIGHT1, GL10.GL_SPOT_DIRECTION,
//								spotDirection, 0);
//						gl.glLightfv(GL10.GL_LIGHT1, GL10.GL_AMBIENT,
//								colorLightYellow, 0);
//						gl.glLightfv(GL10.GL_LIGHT1, GL10.GL_SPECULAR,
//								colorWhite, 0);
//						gl.glLightfv(GL10.GL_LIGHT1, GL10.GL_DIFFUSE, colorRed,
//								0);
//
//						gl.glLightf(GL10.GL_LIGHT1,
//								GL10.GL_CONSTANT_ATTENUATION, 1.2f);
//						lighter.draw(gl);
//						gl.glPopMatrix();
//						gl.glEnable(GL10.GL_LIGHT0);
//						gl.glDisable(GL10.GL_LIGHT1);
//						float spotDirection2[] = { offset + i * wallSize, 0.0f,
//								offset + j * wallSize };
//						gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPOT_DIRECTION,
//								spotDirection2, 0);
//						gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT,
//								colorLightYellow, 0);
//						gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPECULAR,
//								colorWhite, 0);
//						gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE,
//								colorGray, 0);
//						gl.glLightf(GL10.GL_LIGHT0,
//								GL10.GL_CONSTANT_ATTENUATION, 0.2f);
//						gl.glPushMatrix();
//						gl.glTranslatef(offset + i * wallSize, 0.0f, offset + j
//								* wallSize);
//						mFloor.draw(gl);
//						gl.glPopMatrix();
//					}
//					gl.glEnable(GL10.GL_LIGHT0);
//					gl.glDisable(GL10.GL_LIGHT1);
//					float spotDirection2[] = { offset + i * wallSize, 0.0f,
//							offset + j * wallSize };
//					gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPOT_DIRECTION,
//							spotDirection2, 0);
//					gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT,
//							colorLightYellow, 0);
//					gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPECULAR, colorWhite,
//							0);
//					gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, colorGray, 0);
//					gl.glLightf(GL10.GL_LIGHT0, GL10.GL_CONSTANT_ATTENUATION,
//							0.2f);
//					gl.glPushMatrix();
//					gl.glTranslatef(offset + i * wallSize, 0.0f, offset + j
//							* wallSize);
//					mFloor.draw(gl);
//					gl.glPopMatrix();
//				} else if (m[i][j] == 8) {
//					// three Spear trap in a row
//					gl.glEnable(GL10.GL_LIGHT0);
//					float spotDirection[] = { offset + i * wallSize, 0.0f,
//							offset + j * wallSize };
//					gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPOT_DIRECTION,
//							spotDirection, 0);
//					gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT,
//							colorLightYellow, 0);
//					gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPECULAR, colorWhite,
//							0);
//					gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, colorRed, 0);
//					gl.glLightf(GL10.GL_LIGHT0, GL10.GL_CONSTANT_ATTENUATION,
//							0.2f);
//					gl.glPushMatrix();
//					gl.glTranslatef(offset + i * wallSize, -13.0f, offset + j
//							* wallSize);
//					Spear.draw(gl);
//					gl.glPopMatrix();
//
//					float spotDirectio2[] = { offset + i * wallSize + 4, 0.0f,
//							offset + j * wallSize };
//					gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPOT_DIRECTION,
//							spotDirectio2, 0);
//					gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT,
//							colorLightYellow, 0);
//					gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPECULAR, colorWhite,
//							0);
//					gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, colorRed, 0);
//					gl.glLightf(GL10.GL_LIGHT0, GL10.GL_CONSTANT_ATTENUATION,
//							0.2f);
//					gl.glPushMatrix();
//					gl.glTranslatef(offset + i * wallSize + 4, -13.0f, offset
//							+ j * wallSize);
//					Spear.draw(gl);
//					gl.glPopMatrix();
//
//					float spotDirectio4[] = { offset + i * wallSize - 4, 0.0f,
//							offset + j * wallSize };
//					gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPOT_DIRECTION,
//							spotDirectio4, 0);
//					gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT,
//							colorLightYellow, 0);
//					gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPECULAR, colorWhite,
//							0);
//					gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, colorRed, 0);
//					gl.glLightf(GL10.GL_LIGHT0, GL10.GL_CONSTANT_ATTENUATION,
//							0.2f);
//					gl.glPushMatrix();
//					gl.glTranslatef(offset + i * wallSize - 4, -13.0f, offset
//							+ j * wallSize);
//					Spear.draw(gl);
//					gl.glPopMatrix();
//
//					float spotDirection3[] = { offset + i * wallSize, 0.0f,
//							offset + j * wallSize };
//					gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPOT_DIRECTION,
//							spotDirection3, 0);
//					gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT,
//							colorLightYellow, 0);
//					gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPECULAR, colorWhite,
//							0);
//					gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, colorGray, 0);
//					gl.glLightf(GL10.GL_LIGHT0, GL10.GL_CONSTANT_ATTENUATION,
//							0.2f);
//					gl.glPushMatrix();
//					gl.glTranslatef(offset + i * wallSize, 0.0f, offset + j
//							* wallSize);
//					mFloor.draw(gl);
//					gl.glPopMatrix();
//
//				} else if (m[i][j] == 9) {
//					gl.glEnable(GL10.GL_LIGHT1);
//					gl.glPushMatrix();
//					gl.glTranslatef(offset + i * wallSize, 0.0f, offset + j
//							* wallSize);
//					float spotDirection[] = { offset + i * wallSize, 0.0f,
//							offset + j * wallSize };
//					gl.glLightfv(GL10.GL_LIGHT1, GL10.GL_SPOT_DIRECTION,
//							spotDirection, 0);
//					gl.glLightfv(GL10.GL_LIGHT1, GL10.GL_AMBIENT,
//							colorLightYellow, 0);
//					gl.glLightfv(GL10.GL_LIGHT1, GL10.GL_SPECULAR, colorWhite,
//							0);
//					gl.glLightfv(GL10.GL_LIGHT1, GL10.GL_DIFFUSE, colorRed, 0);
//
//					gl.glLightf(GL10.GL_LIGHT1, GL10.GL_CONSTANT_ATTENUATION,
//							1.2f);
//					lowerJaw.draw(gl);
//					gl.glPopMatrix();
//
//					gl.glEnable(GL10.GL_LIGHT1);
//					gl.glPushMatrix();
//					gl.glTranslatef(offset + i * wallSize, 0.0f, offset + j
//							* wallSize);
//					float spotDirection2[] = { offset + i * wallSize, 0.0f,
//							offset + j * wallSize };
//					gl.glLightfv(GL10.GL_LIGHT1, GL10.GL_SPOT_DIRECTION,
//							spotDirection2, 0);
//					gl.glLightfv(GL10.GL_LIGHT1, GL10.GL_AMBIENT,
//							colorLightYellow, 0);
//					gl.glLightfv(GL10.GL_LIGHT1, GL10.GL_SPECULAR, colorWhite,
//							0);
//					gl.glLightfv(GL10.GL_LIGHT1, GL10.GL_DIFFUSE, colorRed, 0);
//
//					gl.glLightf(GL10.GL_LIGHT1, GL10.GL_CONSTANT_ATTENUATION,
//							1.2f);
//					upperJaw.draw(gl);
//					gl.glPopMatrix();
//
//					gl.glEnable(GL10.GL_LIGHT0);
//					gl.glDisable(GL10.GL_LIGHT1);
//					float spotDirection3[] = { offset + i * wallSize, 0.0f,
//							offset + j * wallSize };
//					gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPOT_DIRECTION,
//							spotDirection3, 0);
//					gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT,
//							colorLightYellow, 0);
//					gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPECULAR, colorWhite,
//							0);
//					gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, colorGray, 0);
//					gl.glLightf(GL10.GL_LIGHT0, GL10.GL_CONSTANT_ATTENUATION,
//							0.2f);
//					gl.glPushMatrix();
//					gl.glTranslatef(offset + i * wallSize, 0.0f, offset + j
//							* wallSize);
//					mFloor.draw(gl);
//					gl.glPopMatrix();
//				} else if (m[i][j] == 0) {
//					// make the tome or bad place using cross
//					gl.glEnable(GL10.GL_LIGHT0);
//
//					float spotDirection3[] = { offset + i * wallSize, 0.0f,
//							offset + j * wallSize };
//					gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPOT_DIRECTION,
//							spotDirection3, 0);
//					gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT,
//							colorLightYellow, 0);
//					gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPECULAR, colorWhite,
//							0);
//					gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, colorGray, 0);
//					gl.glLightf(GL10.GL_LIGHT0, GL10.GL_CONSTANT_ATTENUATION,
//							0.2f);
//					gl.glPushMatrix();
//					gl.glTranslatef(offset + i * wallSize, 0.0f, offset + j
//							* wallSize);
//					body.draw(gl);
//					gl.glPopMatrix();
//
//					float spotDirection2[] = { offset + i * wallSize, 0.0f,
//							offset + j * wallSize };
//					gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPOT_DIRECTION,
//							spotDirection2, 0);
//					gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT,
//							colorLightYellow, 0);
//					gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPECULAR, colorWhite,
//							0);
//					gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, colorGray, 0);
//					gl.glLightf(GL10.GL_LIGHT0, GL10.GL_CONSTANT_ATTENUATION,
//							0.2f);
//					gl.glPushMatrix();
//					gl.glTranslatef(offset + i * wallSize, 0.0f, offset + j
//							* wallSize);
//					cross.draw(gl);
//					gl.glPopMatrix();
//
//					float spotDirection[] = { offset + i * wallSize, 0.0f,
//							offset + j * wallSize };
//					gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPOT_DIRECTION,
//							spotDirection, 0);
//					gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT,
//							colorLightYellow, 0);
//					gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPECULAR, colorWhite,
//							0);
//					gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, colorGray, 0);
//					gl.glLightf(GL10.GL_LIGHT0, GL10.GL_CONSTANT_ATTENUATION,
//							0.2f);
//					gl.glPushMatrix();
//					gl.glTranslatef(offset + i * wallSize, 0f, offset + j
//							* wallSize);
//					mFloor.draw(gl);
//					gl.glPopMatrix();
//				}
//			}
//		}
//
//	}
//
//	@Override
//	public void onSurfaceChanged(GL10 gl, int width, int height) {
//		gl.glViewport(0, 0, width, height);
//		gl.glMatrixMode(GL10.GL_PROJECTION);
//		gl.glLoadIdentity();
//		GLU.gluPerspective(gl, 45.0f, (float) width / (float) height, 0.1f,
//				100.0f);
//		gl.glViewport(0, 0, width, height);
//
//		gl.glMatrixMode(GL10.GL_MODELVIEW);
//		gl.glLoadIdentity();
//	}
//
//	@Override
//	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
//		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);
//
//		gl.glClearDepthf(1.0f);
//		gl.glEnable(GL10.GL_DEPTH_TEST);
//		gl.glDepthFunc(GL10.GL_LEQUAL);
//
//		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
//
//	}
//
//}
