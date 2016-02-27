package maze.logic;


public class Elemento {
	private int x;
	private int y;
	private char simbolo;
	
	public Elemento(int x_pos,int y_pos, char s){
		x=x_pos;
		y=y_pos;
		simbolo=s;
	}
	
	public void setPos(int x_pos,int y_pos){
		x=x_pos;
		y=y_pos;
	}
	
	public int get_x(){
		return x;
	}
	
	public int get_y(){
		return y;
	}
	
	public char get_simbolo(){
		return simbolo;
	}
	
	public void set_simbolo(char s){
		simbolo=s;
	}
}
