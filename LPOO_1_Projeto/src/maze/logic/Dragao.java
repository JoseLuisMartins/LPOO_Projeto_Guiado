package maze.logic;


public class Dragao extends Elemento{
	private boolean morto;
	private boolean adormecido;	
	private boolean GuardaEspada;


	public Dragao(int x_pos, int y_pos, char s) {
		super(x_pos, y_pos, s);
		morto=false;
		adormecido=false;
		GuardaEspada=false;
	}

	public boolean getGuardaEspada(){ 
		return GuardaEspada;
	}

	public void SetGuardaEspada(boolean x){
		GuardaEspada=x;

		if(GuardaEspada)
			this.set_simbolo('F');
		else if(adormecido)
			this.set_simbolo('d');
		else
			this.set_simbolo('D');
	}

	public boolean getMorto(){ 
		return morto;
	}

	public void setMorto(){
		morto=true;
	}

	public boolean getAdormecido(){
		return adormecido;
	}

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
