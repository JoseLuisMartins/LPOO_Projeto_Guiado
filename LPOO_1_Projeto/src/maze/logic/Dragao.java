package maze.logic;

/**
 * Represents the dragon
 */
public class Dragao extends Elemento{
	
	private static final long serialVersionUID = -3930253765833770210L;
	private boolean morto;
	private boolean adormecido;	
	private boolean GuardaEspada;
	private Direction dir;
	
	public Direction getDir(){ 
		return dir;
	}
	
	public void setDir(Direction d){ 
		dir=d;
	}
	
/**
 * Creates a dragon
 * @param x_pos		
 * @param y_pos
 * @param s  reprents the symbol of dragon
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
	 * @param x 
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
	 * @return true if the dragon is dead
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
	 * @param val
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
