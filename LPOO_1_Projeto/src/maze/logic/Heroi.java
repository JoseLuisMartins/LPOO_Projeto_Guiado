package maze.logic;


public class Heroi extends Elemento{
	private boolean armado;

	
	public Heroi(int x_pos, int y_pos, char s) {
		super(x_pos, y_pos, s);
		armado=false;

	}

	public boolean getArmado(){
		return armado;
	}
	
	public void setArmado(){
		armado=true;
		this.set_simbolo('A');
	}
	
}
