package maze.logic;

import java.io.Serializable;

/**`
 *  
 * @author Jos� & Marcelo
 *
 */
public class Labirinto implements Serializable{
	private char maze[][] = {{'x','x','x','x','x','x','x','x','x','x'},
							 {'x',' ',' ',' ',' ',' ',' ',' ',' ','x'},
							 {'x',' ','x','x',' ','x',' ','x',' ','x'},
							 {'x',' ','x','x',' ','x',' ','x',' ','x'},
							 {'x',' ','x','x',' ','x',' ','x',' ','x'},
							 {'x',' ',' ',' ',' ',' ',' ','x',' ',' '},
							 {'x',' ','x','x',' ','x',' ','x',' ','x'},
							 {'x',' ','x','x',' ','x',' ','x',' ','x'},
							 {'x',' ','x','x',' ',' ',' ',' ',' ','x'},
							 {'x','x','x','x','x','x','x','x','x','x'}
							 } ;


	/**
	 * creates a labyrinth (default)
	 */
	public Labirinto(){
		maze[5][9]='S';
	}
	
	/**
	 *  creates a labyrinth 
	 * @param m represents the maze
	 */
	public Labirinto(char m[][]){
		maze=m;
	}
	
	/**
	 * @return the maze
	 */
	public char[][] getMaze(){
		return maze;
	}
	
	/**
	 * sets the position with coordinates x and y with a char
	 * @param x represents the coordinate x 
	 * @param y represents the coordinate y
	 * @param value	 -  represents the symbol of an element
	 */
	public void setMaze(int x,int y,char value){
		maze[y][x]=value;
	}
	
	/**
	 * return a String that represents the maze
	 */
	public String toString(){
		String m= "";
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[i].length; j++) {
				m += maze[i][j]; 
				m+= ' '; 
			}
			m+= '\n';
		}
		
		return m; 
	} 
}
