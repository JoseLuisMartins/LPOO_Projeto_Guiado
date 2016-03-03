package maze.logic;

public class Labirinto {
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

	public Labirinto(){
		maze[5][9]='S';
	}
	
	public Labirinto(char m[][]){
		maze=m;
	}
	
	
	public char[][] getMaze(){
		return maze;
	}
	
	public void setMaze(int x,int y,char value){
		maze[y][x]=value;
	}
	
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
