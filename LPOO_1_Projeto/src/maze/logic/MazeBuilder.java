package maze.logic;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MazeBuilder implements IMazeBuilder{

	public static class Cell {		
		private int x, y;

		/**
		 * @return the coordinate x of the 	Visited Cells array
		 */
		public int getX() {
			return x;
		}

		/**
		 * 
		 *  @return the coordinate x of the Maze array
		 */
		public int getXMaze() {
			return (x*2)+1;
		}

		/**
		 * @return the coordinate y of the Visited Cells array
		 */
		public int getY() {
			return y;
		}

		/**
		 * 
		 * @return the coordinate y of the Maze array
		 */
		public int getYMaze() {
			return (y*2)+1;
		}

		/**
		 * crates a cell
		 * @param x - represents the coordinate x of the cell
		 * @param y - represents the coordinate y of the cell
		 */
		public Cell(int x, int y) {
			this.x = x;
			this.y = y;
		}

		/**
		 * @param p represents a  cell
		 * @return true if two cells are adjacent
		 */
		public boolean adjacentTo(Cell p) {
			return Math.abs(p.x - this.x) + Math.abs(p.y - this.y) == 1;
		}
	}

	private char maze[][];
	private char visitedCells[][];
	private Cell guideCell;
	private Stack<Cell> pathHistory= new Stack<Cell>();
	private Heroi h;
	private Dragao d;
	private Espada e;

	/**
	 * @return the hero of the maze
	 */
	public Heroi getHeroi(){
		return h;
	}
	
	/**
	 *  @return the dragon of the maze
	 */
	public Dragao getDragao(){
		return d;
	}
	
	/**
	 * @return the sword of the maze
	 */
	public Espada getEspada(){
		return e;
	}

	/**
	 * creates the maze with dimension = size
	 * @param size, represents the dimension of the maze
	 */
	@Override
	public char[][] buildMaze(int size) throws IllegalArgumentException {
		int visitedSize=(size-1)/2;
		maze= new char[size][size];
		visitedCells= new char[visitedSize][visitedSize];

 
		for (int i = 0; i < maze.length; i++) { 
			for (int j = 0; j < maze[i].length; j++) {
				if(i%2==1 && j%2==1)
					maze[i][j]=' '; 
				else
					maze[i][j]='X';
			} 
		}

		for (int i = 0; i < visitedCells.length; i++) {
			for (int j = 0; j < visitedCells[i].length; j++) {
				visitedCells[i][j]='-';
			}
		}

		Random r = new Random(); 
		guideCell=new Cell(r.nextInt(visitedSize),r.nextInt(visitedSize));


		visitedCells[guideCell.getY()][guideCell.getX()]='+';


		//put exit on border
		int s =	r.nextInt(size-2)+1;
		while(s%2 != 1)//fazer ate encontrar numero impar
			s=r.nextInt(size-2)+1;

		switch (r.nextInt(4)) {
		case 0://top
			maze[0][s]= 'S';
			break;
		case 1://bottom
			maze[size-1][s]= 'S';
			break;

		case 2://right
			maze[s][size-1]= 'S';
			break;
		case 3://left
			maze[s][0]= 'S';
			break;
		}

		pathHistory.push(guideCell);

		while(pathHistory.size() !=0){
			ArrayList<Direction> dir = possibleDirections();

			if(dir.size()==0)//dead end voltar atras
				guideCell=pathHistory.pop();
			else{//escolher uma direcao aleatoria 
				Direction d= dir.get(r.nextInt(dir.size()));

				switch (d) {
				case UP:
					guideCell=new Cell(guideCell.getX(), guideCell.getY()-1);
					maze[guideCell.getYMaze()+1][guideCell.getXMaze()]=' ';
					break;
				case DOWN:
					guideCell=new Cell(guideCell.getX(), guideCell.getY()+1);
					maze[guideCell.getYMaze()-1][guideCell.getXMaze()]=' ';
					break; 
				case RIGHT:
					guideCell=new Cell(guideCell.getX()+1, guideCell.getY());
					maze[guideCell.getYMaze()][guideCell.getXMaze()-1]=' ';
					break;
				case LEFT:
					guideCell=new Cell(guideCell.getX()-1, guideCell.getY());
					maze[guideCell.getYMaze()][guideCell.getXMaze()+1]=' ';
					break;
				}
				visitedCells[guideCell.getY()][guideCell.getX()]='+';
				pathHistory.push(guideCell);
			}

		} 
		placeElements();
		return maze;
	}

	/**
	 * @return a set of possible directions
	 */
	public ArrayList<Direction> possibleDirections(){
		ArrayList<Direction> dir = new ArrayList<Direction>();


		if(guideCell.getY()-1 >= 0)//UP
			if(visitedCells[guideCell.getY()-1][guideCell.getX()]=='-')
				dir.add(Direction.UP);

		if(guideCell.getY()+1 < visitedCells.length)//DOWN
			if(visitedCells[guideCell.getY()+1][guideCell.getX()]=='-')
				dir.add(Direction.DOWN);

		if(guideCell.getX()-1 >= 0)//LEFT
			if(visitedCells[guideCell.getY()][guideCell.getX()-1]=='-')
				dir.add(Direction.LEFT);

		if(guideCell.getX()+1 < visitedCells.length)//RIGHT
			if(visitedCells[guideCell.getY()][guideCell.getX()+1]=='-')
				dir.add(Direction.RIGHT);

		return dir;
	}

	/**
	 *  place elements(hero, sword and dragon)
	 */
	public void placeElements(){
		Cell hero = getFreePosition();
		h=new Heroi(hero.getX(), hero.getY(), 'H');
		maze[hero.getY()][hero.getX()]=h.get_simbolo();
		
		Cell sword = getFreePosition();
		e=new Espada(sword.getX(), sword.getY(), 'E');
		maze[sword.getY()][sword.getX()]=e.get_simbolo();
		Cell dragon = getFreePosition();
		
		while(dragon.adjacentTo(hero)) 
			dragon=getFreePosition();
		
		d=new Dragao(dragon.getX(), dragon.getY(), 'D');
		maze[dragon.getY()][dragon.getX()]=d.get_simbolo();
	}
	
	/**
	 * Returns a free Cell
	 * @return a Cell
	 */
	public Cell getFreePosition(){
		Random r = new Random(); 
		int x,y;
		do {
			x=r.nextInt(maze.length-2)+1;
			y=r.nextInt(maze.length-2)+1;	
		} while (maze[y][x] != ' ');
		
		Cell res= new Cell(x,y);
		
		return res;
	}
	



}
