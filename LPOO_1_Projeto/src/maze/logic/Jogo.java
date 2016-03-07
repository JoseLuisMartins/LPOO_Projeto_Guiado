package maze.logic;

import java.util.ArrayList;
import java.util.Random;


public class Jogo {
	private Labirinto l;
	private Heroi h;
	ArrayList<Dragao> arrayDragon = new ArrayList<Dragao>();
	private Espada e;
	private boolean fim_jogo;//derrotado pelo dragao
	private boolean sair;//derrotou o dragao e saiu


	public Jogo(){
		h=new Heroi(1, 1, 'H');
		Dragao d=new Dragao(1,3,'D');
		arrayDragon.add(d);
		e=new Espada(1, 8, 'E');
		l=new Labirinto();
		l.setMaze(h.get_x(), h.get_y(),h.get_simbolo());
		l.setMaze(d.get_x(), d.get_y(), d.get_simbolo());
		l.setMaze(e.get_x(), e.get_y(), e.get_simbolo());
		fim_jogo=false;
		sair=false;
	}


	public Jogo(char m[][],Heroi hero,Dragao dragon, Espada sword){
		h=hero;
		arrayDragon.add(dragon);
		e=sword;
		l=new Labirinto(m);
		l.setMaze(h.get_x(), h.get_y(),h.get_simbolo());
		l.setMaze(dragon.get_x(), dragon.get_y(), dragon.get_simbolo());
		l.setMaze(e.get_x(), e.get_y(), e.get_simbolo());
		fim_jogo=false;
		sair=false;
	}

	public boolean addDragons(Dragao d){
		if(l.getMaze()[d.get_y()][d.get_x()] != ' ')
		
		arrayDragon.add(d);
		l.setMaze(d.get_x(), d.get_y(),d.get_simbolo());
		return true;
	}
	
	public Labirinto getLabirinto(){
		return l;
	}

	public Espada getEspada(){
		return e;
	} 

	public ArrayList<Dragao> getDragoes(){
		return arrayDragon; 
	}

	public Heroi getHeroi(){
		return h;
	}

	public boolean getFimJogo(){
		return fim_jogo;
	}

	public boolean getSair(){
		return sair;
	} 

	public void checkDragon(){
		ArrayList<Dragao> deadDragons=new ArrayList<Dragao>();
		
		for (int i = 0; i < arrayDragon.size(); i++) {
			
			if(l.getMaze()[h.get_y()-1][h.get_x()] == arrayDragon.get(i).get_simbolo()){//up
				if(h.getArmado()){
					l.setMaze(h.get_x(),h.get_y()-1, ' ');	
					deadDragons.add(arrayDragon.get(i));
				}
				else if(!arrayDragon.get(i).getAdormecido())
					fim_jogo=true; 

			}
			else if(l.getMaze()[h.get_y()+1][h.get_x()] == arrayDragon.get(i).get_simbolo()){//down
				if(h.getArmado()){
					l.setMaze(h.get_x(),h.get_y()+1, ' ');
					deadDragons.add(arrayDragon.get(i));
				}
				else if(!arrayDragon.get(i).getAdormecido())
					fim_jogo=true;

			}
			else  if(l.getMaze()[h.get_y()][h.get_x()+1] == arrayDragon.get(i).get_simbolo()){//right
				if(h.getArmado()){
					l.setMaze(h.get_x()+1,h.get_y(), ' ');
					deadDragons.add(arrayDragon.get(i));
				}
				else if(!arrayDragon.get(i).getAdormecido())
					fim_jogo=true;
			}

			else if(l.getMaze()[h.get_y()][h.get_x()-1] == arrayDragon.get(i).get_simbolo()){//left
				if(h.getArmado()){
					l.setMaze(h.get_x()-1,h.get_y(), ' ');	
					deadDragons.add(arrayDragon.get(i));
				}
				else if(!arrayDragon.get(i).getAdormecido())
					fim_jogo=true;
			}
		}
		
		for (int i = 0; i < deadDragons.size(); i++) {
			arrayDragon.remove(deadDragons.get(i));
		}
	} 

	public void toggleAdormecerRandom(){
		Random r = new Random();  
		int mudar;

		for(Dragao d: arrayDragon){
			mudar=r.nextInt(2);
			if(mudar==1)
				if(d.getAdormecido()){//se esta adormecido
					d.setAdormecido(false);//acordar
					l.setMaze(d.get_x(),d.get_y(), d.get_simbolo());
				}
				else{//adormecer
					d.setAdormecido(true);
					l.setMaze(d.get_x(),d.get_y(), d.get_simbolo());	
				}
		}
	}

	public void moveDragonAux(Direction dir, Dragao d) {
		int x=d.get_x();
		int y=d.get_y();

		switch (dir) {
		case UP://up
			y=d.get_y()-1 ;
			break;
		case DOWN://down
			y=d.get_y()+1 ;
			break;
		case RIGHT://right
			x=d.get_x()+1;
			break;
		case LEFT://left
			x=d.get_x()-1; 
			break;  
		}

		if (l.getMaze()[y][x] == ' ') {
			if(d.get_simbolo()=='D')
				l.setMaze(d.get_x(), d.get_y(), ' ');
			else if(d.getGuardaEspada()){
				d.SetGuardaEspada(false);
				l.setMaze(d.get_x(), d.get_y(), e.get_simbolo());
				d.set_simbolo('D');
			} 

			l.setMaze(x,y,d.get_simbolo());
			d.setPos(x,y);
		} else if(l.getMaze()[y][x] == e.get_simbolo()) {
			d.SetGuardaEspada(true);
			l.setMaze(d.get_x(), d.get_y(), ' ');
			l.setMaze(x, y,d.get_simbolo());
			d.setPos(x,y);
		}
	}

	public void moveDragon(Dragao d){
		Random r = new Random(); 
		int move=r.nextInt(4);
			switch (move) {
			case 0://up
				moveDragonAux(Direction.UP , d);
				break;
			case 1://down
				moveDragonAux(Direction.DOWN, d);
				break;
			case 2://right
				moveDragonAux(Direction.RIGHT, d);
				break;
			case 3://left 
				moveDragonAux(Direction.LEFT, d);
				break;
			default://manter
				break;
			}
		
	}

	public boolean move(Direction dir){
		int x=h.get_x();
		int y=h.get_y();

		switch (dir) {
		case UP://up
			y=h.get_y()-1 ;
			break;
		case DOWN://down
			y=h.get_y()+1 ;
			break;
		case RIGHT://right
			x=h.get_x()+1;
			break;
		case LEFT://left
			x=h.get_x()-1; 
			break;  
		}

		if(x > l.getMaze()[y].length)
			return false;


		switch (l.getMaze()[y][x]) {
		case ' ':
			l.setMaze(h.get_x(), h.get_y(), ' ');
			l.setMaze(x, y,h.get_simbolo());
			h.setPos(x,y);
			break;
		case 'E': 
			h.setArmado();
			l.setMaze(h.get_x(), h.get_y(), ' ');
			l.setMaze(x, y,h.get_simbolo());
			h.setPos(x,y);
			break;
		case 'S':
			if(arrayDragon.size() == 0 ){
				l.setMaze(h.get_x(), h.get_y(), ' ');
				l.setMaze(x, y,h.get_simbolo());
				h.setPos(x,y);
				sair=true;
				return true;
			}
			else  
				return false;
		default:
			return false;
		}

		checkDragon();
		return true;	

	}

	public String toString(){
		return l.toString();
	}
}
