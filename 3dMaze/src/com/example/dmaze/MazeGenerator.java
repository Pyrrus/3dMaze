package com.example.dmaze;
 

/*
 * MazeGenerator
 * the make the layout of the maze and random layout.
 * I learn over the web.
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public final class MazeGenerator {
	private int x;
	private int y;
	private int[][] maze;

	public MazeGenerator(int x, int y) {

			this.x = x;
			this.y = y;
			maze = new int[this.x][this.y];
			
			// Initialize all the maze 
		    for (int i = 0; i < x; i++)
		        for (int j = 0; j < y; j++)
		            maze[i][j] = 1;
		 
		     Random rand = new Random();
		     
		     // help with the layout of the map
		     int r = rand.nextInt(x);
		     while (r % 2 == 0) {
		         r = rand.nextInt(x);
		     }
		     
		     // help with the layout of the map
		     int c = rand.nextInt(y);
		     while (c % 2 == 0) {
		         c = rand.nextInt(y);
		     }
		     // Starting cell
		     maze[r][c] = 0;
		 
		     recursion(r, c);
		     
		     //set up the where the object where to go
		     setup();
	}
	
	//set all the object on the maze
    public void setup() {
        int minObject = 0;
        int maxObject = 9;
		Random rand = new Random();
        for (int i = 0; i < x; i++){
            for (int j = 0; j < y; j++){
               if (maze[i][j] == 0) {
            	   int randomNum = rand.nextInt((maxObject - minObject) + 1) + minObject; // give me random number
            	   if (randomNum != 1) {
            		   maze[i][j] = randomNum;
            	   }
               }
            }
        }
    }
    
    //make the the layout of the map with wall and floor
	public void recursion(int r, int c) {
     // 4 random directions
        Integer[] randDirs = generateRandomDirections();
        for (Integer randDir : randDirs) {
            switch (randDir) {
                case 1: // Up
                    if (r - 2 <= 0)
                        continue;
                    if (maze[r - 2][c] != 0) {
                        maze[r-2][c] = 0;
                        maze[r-1][c] = 0;
                        recursion(r - 2, c);
                    }
                    break;
            case 2: // Right
                if (c + 2 >= y - 1)
                    continue;
                if (maze[r][c + 2] != 0) {
                    maze[r][c + 2] = 0;
                    maze[r][c + 1] = 0;
                    recursion(r, c + 2);
                }
                break;
            case 3: // Down
                if (r + 2 >= x - 1)
                    continue;
                if (maze[r + 2][c] != 0) {
                    maze[r+2][c] = 0;
                    maze[r+1][c] = 0;
                    recursion(r + 2, c);
                }
                break;
            case 4: // Left
                if (c - 2 <= 0)
                    continue;
                if (maze[r][c - 2] != 0) {
                    maze[r][c - 2] = 0;
                    maze[r][c - 1] = 0;
                    recursion(r, c - 2);
                }
                break;
            }
        }
 
 }
    // help with the if is the random map
    public void display() {
        for (int i = 0; i < x; i++){
    		for (int j = 0; j < y; j++){
               System.out.print(maze[i][j]);
            }
    		System.out.print("\n");
        }
    }
    
 //help on the layout   
 public Integer[] generateRandomDirections() {
      ArrayList<Integer> randoms = new ArrayList<Integer>();
      for (int i = 0; i < 4; i++)
           randoms.add(i + 1);
      Collections.shuffle(randoms);
 
     return randoms.toArray(new Integer[4]);
 }
 
 //get the data from the maze where any place
  public int[][] getMaze() {
		 return maze;
	 }


}