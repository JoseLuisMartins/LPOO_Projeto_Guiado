package maze.logic;

/**
 * Represents the dragon
 * 
 * @author José & Marcelo
 */
public class Dragao extends Elemento{
	
	private static final long serialVersionUID = -3930253765833770210L;
	private boolean morto;
	private boolean adormecido;	
	private boolean GuardaEspada;
	private Direction dir;
	
	/**
	 * @return the current direction of the dragon
	 */
	public Direction getDir(){ 
		return dir;
	}
	
	/**
	 * sets the current direction of the dragon
	 * @param d represents a Direction 
	 */
	public void setDir(Direction d){ 
		dir=d;
	}
	
	/**
	 * Creates a dragon
	 * @param x_pos	 represents the coordinate x of the dragon	
	 * @param y_pos	 represents the coordinate y of the dragon
	 * @param s  represents the symbol of dragon
	 */ 
	public Dragao(int x_pos, int y_pos, char s) {
		super(x_pos, y_pos, s);
		morto=false;
		adormecido=false;
		GuardaEspada=false;
		dir=Direction.DOWN;
	} 

	/**
	 * @return true if the dragon is above the sword
	 */
	public boolean getGuardaEspada(){ 
		return GuardaEspada;
	}

	/**
	 *  sets GuardaEspada
	 *  
	 * @param x represents a boolean
	 */
	public void SetGuardaEspada(boolean x){
		GuardaEspada=x;

		if(GuardaEspada)
			this.set_simbolo('F');
		else
			this.set_simbolo('D');
	}

	/**
	 * 
	 * @return true if the dragon is dead else return false
	 */
	public boolean getMorto(){ 
		return morto;
	}

	/**
	 * sets the dragon as dead
	 */
	public void setMorto(){
		morto=true;
	}

	/**
	 * @return true if the dragon is sleeping
	 */
	public boolean getAdormecido(){
		return adormecido;
	}

	/**
	 * sets if the dragon is sleeping or not 
	 * @param val represents a boolean
	 */
	public void setAdormecido(boolean val){
		adormecido=val;
		
		if(adormecido){
			if(GuardaEspada)
				this.set_simbolo('f');
			else
				this.set_simbolo('d');
		}
		else{
			if(GuardaEspada)
				this.set_simbolo('F');
			else
				this.set_simbolo('D');
	
		}
	}
}
