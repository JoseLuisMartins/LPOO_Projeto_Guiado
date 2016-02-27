package maze.logic;

import java.util.Random;


public class Jogo {
	private Labirinto l;
	private Heroi h;
	private Dragao d;
	private Espada e;
	private boolean fim_jogo;
	private boolean sair;

	public Jogo(){
		h=new Heroi(1, 1, 'H');
		d=new Dragao(1,3,'D');
		e=new Espada(1, 8, 'E');
		l=new Labirinto();
		l.setMaze(h.get_x(), h.get_y(),h.get_simbolo());
		l.setMaze(d.get_x(), d.get_y(), d.get_simbolo());
		l.setMaze(e.get_x(), e.get_y(), e.get_simbolo());
		fim_jogo=false;
		sair=false;
	}


	public Dragao getDragao(){
		return d;
	}

	public boolean getFimJogo(){
		return fim_jogo;
	}

	public boolean getSair(){
		return sair;
	}

	public boolean checkDragon(){
		if(l.getMaze()[h.get_y()-1][h.get_x()] == d.get_simbolo()){//up
			if(h.getArmado()){
				l.setMaze(h.get_x(),h.get_y()-1, ' ');	
				d.setMorto();
			}
			else if(!d.getAdormecido())
				fim_jogo=true;

		}
		else if(l.getMaze()[h.get_y()+1][h.get_x()] == d.get_simbolo()){//down
			if(h.getArmado()){
				l.setMaze(h.get_x(),h.get_y()+1, ' ');
				d.setMorto();
			}
			else if(!d.getAdormecido())
				fim_jogo=true;

		}
		else if(l.getMaze()[h.get_y()][h.get_x()+1] == d.get_simbolo()){//right
			if(h.getArmado()){
				l.setMaze(h.get_x()+1,h.get_y(), ' ');
				d.setMorto();
			}
			else if(!d.getAdormecido())
				fim_jogo=true;

		}
		else if(l.getMaze()[h.get_y()][h.get_x()-1] == d.get_simbolo()){//left
			if(h.getArmado()){
				l.setMaze(h.get_x()-1,h.get_y(), ' ');	
				d.setMorto();
			}
			else if(!d.getAdormecido())
				fim_jogo=true;
		}

		return true;
	} 

	public void toggleAdormecerRandom(){
		Random r = new Random();
		int mudar=r.nextInt(2);

		if(mudar==1)
			if(d.getAdormecido()){//se esta adormecido
				d.setAdormecido(false);//acordar
				l.setMaze(d.get_x(),d.get_y(), d.get_simbolo());
				System.out.println(d.get_simbolo());
			}
			else{//adormecer
				d.setAdormecido(true);
				l.setMaze(d.get_x(),d.get_y(), d.get_simbolo());	
				System.out.println(d.get_simbolo());
			}
	}

	public void moveDragon(){
		Random r = new Random();
		int move=r.nextInt(4);
		switch (move) {
		case 0://up
			if (l.getMaze()[d.get_y()-1][d.get_x()] == ' ') {
				if(d.get_simbolo()=='D')
					l.setMaze(d.get_x(), d.get_y(), ' ');
				else if(d.get_simbolo() =='F'){
					l.setMaze(d.get_x(), d.get_y(), e.get_simbolo());
					d.set_simbolo('D');
				}
				l.setMaze(d.get_x(), d.get_y()-1,d.get_simbolo());
				d.setPos(d.get_x(),d.get_y()-1);
			} else if(l.getMaze()[d.get_y()-1][d.get_x()] == e.get_simbolo()) {
				d.set_simbolo('F');
				l.setMaze(d.get_x(), d.get_y(), ' ');
				l.setMaze(d.get_x(), d.get_y()-1,d.get_simbolo());
				d.setPos(d.get_x(),d.get_y()-1);
			}
			break;
		case 1://down
			if (l.getMaze()[d.get_y()+1][d.get_x()] == ' ') {
				if(d.get_simbolo()=='D')
					l.setMaze(d.get_x(), d.get_y(), ' ');
				else if(d.get_simbolo() =='F'){
					l.setMaze(d.get_x(), d.get_y(), e.get_simbolo());
					d.set_simbolo('D');
				}
				l.setMaze(d.get_x(), d.get_y()+1,d.get_simbolo());
				d.setPos(d.get_x(),d.get_y()+1);
			} else if(l.getMaze()[d.get_y()+1][d.get_x()] == e.get_simbolo()) {
				d.set_simbolo('F');
				l.setMaze(d.get_x(), d.get_y(), ' ');
				l.setMaze(d.get_x(), d.get_y()+1,d.get_simbolo());
				d.setPos(d.get_x(),d.get_y()+1);
			}
			break;
		case 2://right
			if (l.getMaze()[d.get_y()][d.get_x()+1] == ' ') {
				if(d.get_simbolo()=='D')
					l.setMaze(d.get_x(), d.get_y(), ' ');
				else if(d.get_simbolo() =='F'){
					l.setMaze(d.get_x(), d.get_y(), e.get_simbolo());
					d.set_simbolo('D');
				}
				l.setMaze(d.get_x()+1, d.get_y(),d.get_simbolo());
				d.setPos(d.get_x()+1,d.get_y());
			} else if(l.getMaze()[d.get_y()][d.get_x()+1] == e.get_simbolo()) {
				d.set_simbolo('F');
				l.setMaze(d.get_x(), d.get_y(), ' ');
				l.setMaze(d.get_x()+1, d.get_y(),d.get_simbolo());
				d.setPos(d.get_x()+1,d.get_y());
			}
			break;
		case 3://left
			if (l.getMaze()[d.get_y()][d.get_x()-1] == ' ') {
				if(d.get_simbolo()=='D')
					l.setMaze(d.get_x(), d.get_y(), ' ');
				else if(d.get_simbolo() =='F'){
					l.setMaze(d.get_x(), d.get_y(), e.get_simbolo());
					d.set_simbolo('D');
				}
				l.setMaze(d.get_x()-1, d.get_y(),d.get_simbolo());
				d.setPos(d.get_x()-1,d.get_y());
			} else if(l.getMaze()[d.get_y()][d.get_x()-1] == e.get_simbolo()) {
				d.set_simbolo('F');
				l.setMaze(d.get_x(), d.get_y(), ' ');
				l.setMaze(d.get_x()-1, d.get_y(),d.get_simbolo());
				d.setPos(d.get_x()-1,d.get_y());
			}
			break;
		default://manter
			break;
		}


	}

	public boolean moveRight(){
		if((h.get_x()+1) > l.getMaze()[h.get_y()].length)
			return false;


		switch (l.getMaze()[h.get_y()][h.get_x()+1]) {
		case ' ':
			l.setMaze(h.get_x(), h.get_y(), ' ');
			l.setMaze(h.get_x()+1, h.get_y(),h.get_simbolo());
			h.setPos(h.get_x()+1,h.get_y());
			break;
		case 'E':
			h.setArmado();
			l.setMaze(h.get_x(), h.get_y(), ' ');
			l.setMaze(h.get_x()+1, h.get_y(),h.get_simbolo());
			h.setPos(h.get_x()+1,h.get_y());
			break;
		case 'S':
			if(d.getMorto()){
				l.setMaze(h.get_x(), h.get_y(), ' ');
				l.setMaze(h.get_x()+1, h.get_y(),h.get_simbolo());
				h.setPos(h.get_x()+1,h.get_y());
				sair=true;
			}
			break;
		default:
			return false;
		}


		return true;
	}

	public boolean moveLeft(){
		if((h.get_x()-1) < 0)
			return false;

		switch (l.getMaze()[h.get_y()][h.get_x()-1]) {
		case ' ':
			l.setMaze(h.get_x(), h.get_y(), ' ');
			l.setMaze(h.get_x()-1, h.get_y(),h.get_simbolo());
			h.setPos(h.get_x()-1,h.get_y());
			break;
		case 'E':
			h.setArmado();
			l.setMaze(h.get_x(), h.get_y(), ' ');
			l.setMaze(h.get_x(), h.get_y()-1,h.get_simbolo());
			h.setPos(h.get_x(),h.get_y()-1);
			break;
		case 'S':
			if(d.getMorto()){
				sair=true;
				l.setMaze(h.get_x(), h.get_y(), ' ');
				l.setMaze(h.get_x()+1, h.get_y(),h.get_simbolo());
				h.setPos(h.get_x()+1,h.get_y());
			}
			break;
		default:
			return false;
		}


		return true;
	}

	public boolean moveUp(){
		if((h.get_y()-1) < 0)
			return false;

		switch (l.getMaze()[h.get_y()-1][h.get_x()]) {
		case ' ':
			l.setMaze(h.get_x(), h.get_y(), ' ');
			l.setMaze(h.get_x(), h.get_y()-1,h.get_simbolo());
			h.setPos(h.get_x(),h.get_y()-1);
			break;
		case 'E':
			h.setArmado();
			l.setMaze(h.get_x(), h.get_y(), ' ');
			l.setMaze(h.get_x(), h.get_y()-1,h.get_simbolo());
			h.setPos(h.get_x(),h.get_y()-1);
			break;
		case 'S':
			if(d.getMorto()){
				sair=true;
				l.setMaze(h.get_x(), h.get_y(), ' ');
				l.setMaze(h.get_x(), h.get_y()-1,h.get_simbolo());
				h.setPos(h.get_x(),h.get_y()-1);
			}
			break;
		default:
			return false;
		}


		return true;
	}


	public boolean moveDown(){
		if((h.get_y()+1) > l.getMaze().length)
			return false;

		switch (l.getMaze()[h.get_y()+1][h.get_x()]) {
		case ' ':
			l.setMaze(h.get_x(), h.get_y(), ' ');
			l.setMaze(h.get_x(), h.get_y()+1,h.get_simbolo());
			h.setPos(h.get_x(),h.get_y()+1);
			break;
		case 'E':
			h.setArmado();
			l.setMaze(h.get_x(), h.get_y(), ' ');
			l.setMaze(h.get_x(), h.get_y()+1,h.get_simbolo());
			h.setPos(h.get_x(),h.get_y()+1);
			break;
		case 'S':
			if(d.getMorto()){
				sair=true;
				l.setMaze(h.get_x(), h.get_y(), ' ');
				l.setMaze(h.get_x(), h.get_y()+1,h.get_simbolo());
				h.setPos(h.get_x(),h.get_y()+1);
			}
			break;
		default:
			return false;
		}


		return true;
	}



	public String toString(){
		return l.toString();
	}
}
