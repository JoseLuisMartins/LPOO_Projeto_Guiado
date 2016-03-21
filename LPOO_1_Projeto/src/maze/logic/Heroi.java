package maze.logic;

/**
 * Represents a hero
 */
public class Heroi extends Elemento{
	private boolean armado;
	
	/**
	 * Creates a Hero
	 * @param x_pos
	 * @param y_pos
	 * @param s
	 */
	public Heroi(int x_pos, int y_pos, char s) {
		super(x_pos, y_pos, s);
		armado=false;

	}

	/**
	 * @return true if the hero is armed
	 */
	public boolean getArmado(){
		return armado;
	}
	
	/**
	 * 	sets the hero as armed
	 */
	public void setArmado(){
		armado=true;
		this.set_simbolo('A');
	}
	
}
