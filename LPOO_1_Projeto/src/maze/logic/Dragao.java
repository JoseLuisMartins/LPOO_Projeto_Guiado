package maze.logic;


public class Dragao extends Elemento{
	private boolean morto;
	private boolean adormecido;	


	public Dragao(int x_pos, int y_pos, char s) {
		super(x_pos, y_pos, s);
		morto=false;
		adormecido=false;
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
		if(adormecido)
			this.set_simbolo('d');
		else
			this.set_simbolo('D');
	}

}
