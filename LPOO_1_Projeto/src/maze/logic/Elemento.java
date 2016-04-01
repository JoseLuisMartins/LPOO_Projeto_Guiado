package maze.logic;

import java.io.Serializable;

/**
 * Represents an Element
 * 
 * @author José & Marcelo
 */
public class Elemento implements Serializable {
	private int x;
	private int y;
	private char simbolo;
	private static final long serialVersionUID = 8669284861713879701L;
	
	/**
	 * creates an Element
	 * @param x_pos represents the coordinate x of the position of the Element
	 * @param y_pos represents the coordinate y of the position of the Element
	 * @param s represents the symbol of the Element
	 */
	
	public Elemento(int x_pos,int y_pos, char s){
		x=x_pos;
		y=y_pos;
		simbolo=s;
	}
	
	/**
	 * 	sets the position of the Element
	 * @param x_pos represents the coordinate x
	 * @param y_pos represents the coordinate y
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
	 * @param s new symbol
	 */
	public void set_simbolo(char s){
		simbolo=s;
	}
}
