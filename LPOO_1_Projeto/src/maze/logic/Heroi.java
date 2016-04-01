package maze.logic;

/**
 * Represents a hero
 */
public class Heroi extends Elemento{
	private static final long serialVersionUID = -4497110182031883550L;
	private boolean armado;
	
	/**
	 * Creates a Hero
	 * @param x_pos represents the coordinate x of the position of the Hero
	 * @param y_pos represents the coordinate y of the position of the Hero
	 * @param s represents the symbol of the Hero
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
