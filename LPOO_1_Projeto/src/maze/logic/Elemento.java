package maze.logic;

import java.io.Serializable;

/**
 * Represents an Element
 */
public class Elemento implements Serializable {
	private int x;
	private int y;
	private char simbolo;
	private static final long serialVersionUID = 8669284861713879701L;
	public Elemento(int x_pos,int y_pos, char s){
		x=x_pos;
		y=y_pos;
		simbolo=s;
	}
	
	/**
	 * 	sets the position of the Element
	 * @param x_pos
	 * @param y_pos
	 */
	public void setPos(int x_pos,int y_pos){
		x=x_pos;
		y=y_pos;
	}
	
	/**
	 * @return the coordinate x of the position of the Element
	 */
	public int get_x(){
		return x;
	}
	
	/**
	 * 
	 * @return the coordinate y of the position of the Element
	 */
	public int get_y(){
		return y;
	}
	
	/**
	 * 
	 * @return the symbol of the Element
	 */
	public char get_simbolo(){
		return simbolo;
	}
	
	/**
	 * sets the symbol of the Element
	 * @param s
	 */
	public void set_simbolo(char s){
		simbolo=s;
	}
}
