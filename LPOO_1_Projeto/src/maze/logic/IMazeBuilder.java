package maze.logic;

/**
 * 
 * @author José & Marcelo
 *
 */
public interface IMazeBuilder {
	public char[][] buildMaze(int size) throws IllegalArgumentException;
}
