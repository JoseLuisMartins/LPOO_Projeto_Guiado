package maze.logic;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Represents the game
 * 
 * @author Jos� & Marcelo
 */

public class Jogo implements Serializable{
	private Labirinto l;
	private Heroi h;
	ArrayList<Dragao> arrayDragon = new ArrayList<Dragao>();
	private Espada e;
	private boolean fim_jogo;//derrotado pelo dragao
	private boolean sair;//derrotou o dragao e saiu
	private GameMode mode;
	private boolean hasExit;

	/**
	 * Creates a game with a default maze
	 */
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
		mode=GameMode.StaticDragon; 
		hasExit=true;
	}



	/**
	 * Creates a game 
	 * @param m  represents the maze
	 * @param hero represents the hero
	 * @param dragon represents the dragon
	 * @param sword represents the sword
	 * @param mod represents the GameMode
	 */
	public Jogo(char m[][],Heroi hero,Dragao dragon, Espada sword,GameMode mod){
		h=hero;
		arrayDragon.add(dragon);
		e=sword;
		l=new Labirinto(m);
		l.setMaze(h.get_x(), h.get_y(),h.get_simbolo());
		l.setMaze(dragon.get_x(), dragon.get_y(), dragon.get_simbolo());
		l.setMaze(e.get_x(), e.get_y(), e.get_simbolo());
		fim_jogo=false;
		sair=false;
		mode=mod;
		hasExit=true;
	}

	/**
	 * Add nDragoes if possible
	 * @param nDragoes, number of dragons
	 * @return	true if its possible to add nDragoes
	 */
	public boolean addNDragons(int nDragoes){

		Random r=new Random();

		int  mazeLength = l.getMaze().length;

		while(nDragoes > 0){
			if(addDragon(r.nextInt(mazeLength-2)+1, r.nextInt(mazeLength-2)+1))
				nDragoes--;
		}
		return true;
	}

	/**
	 * 
	 * @param x coordinate x of the position of the Dragon
	 * @param y coordinate y of the position of the Dragon
	 * @return true if its possible to add the dragon in that position
	 */
	public boolean addDragon(int x,int y){

		if(x<=0 || y<=0|| x>=l.getMaze().length || y>=l.getMaze().length )
			return false;

		Dragao dragon;

		if(l.getMaze()[y][x] == ' ' && Math.abs(x - h.get_x()) + Math.abs(y - h.get_y()) != 1){//se a celula estiver livre e o heroi nao estiver adjacente adiciona
			dragon=new Dragao(x, y, 'D');
			l.setMaze(dragon.get_x(), dragon.get_y(),dragon.get_simbolo());
			arrayDragon.add(dragon);
			return true;
		}

		return false;
	}

	/**
	 * 
	 * @return GameMode
	 */
	
	public GameMode getGameMode(){
		return mode;
	}

	/**
	 * sets the game mode
	 * @param m represents a game mode
	 */
	
	public void setGameMode(GameMode m){
		mode=m;
	}


	/**
	 * 	
	 * @return true if the maze has exit
	 */
	public boolean getHasExit(){
		return hasExit;
	}

	/**
	 * sets the boolean hasExit whit the parameter val
	 * @param val represents a boolean
	 */
	public void setHasExit(boolean val){
		hasExit=val;
	}

	/**
	 * @return the maze of the game
	 */
	public Labirinto getLabirinto(){
		return l;
	}

	/**
	 * @return the sword of the game
	 */
	public Espada getEspada(){
		return e;
	}  

	/**
	 * @return the set of dragons of the game
	 */
	public ArrayList<Dragao> getDragoes(){
		return arrayDragon; 
	}

	/**
	 * @return the hero of the game
	 */
	public Heroi getHeroi(){
		return h;
	}

	/**
	 * @return true if the hero is dead
	 */
	public boolean getFimJogo(){
		return fim_jogo;
	}

	/**
	 * @return true if the hero kill all dragons
	 */
	public boolean getSair(){
		return sair;
	} 

	/**
	 * sets the hero
	 * @param h represents a hero
	 */
	public void setHeroi(Heroi h){
		this.h= h;
	}

	/**
	 * sets the sword
	 * @param e represents a sword
	 */
	public void setEspada(Espada e){
		this.e = e;
	}

	/**
	 * sets the set of dragons
	 * @param d represents a set of dragons
	 */
	public void setDragons(ArrayList<Dragao> d){
		this.arrayDragon = d;
	}
	
	/**
	 * sets the boolean sair with the parameter val
	 * @param val represents a boolean
	 */
	public void setSair(boolean val){
		sair= val;
	}
	
	/**
	 * sets the parameter fim_jogo with the parameter val
	 * @param val represents a boolean
	 */
	public void setFimJogo(boolean val){
		fim_jogo= val;
	}

	/**
	 *  add a dragon to the set of dragons
	 * @param d represents a dragon
	 */
	public void addDragon(Dragao d){
		arrayDragon.add(d);
	} 

	/**
	 * removes a dragon
	 * @param x represents the coordinate x of the dragon
	 * @param y represents the coordinate y of the dragon
	 */
	public void removeDragon(int x, int y){//usado no creatMap para remover um dragao
		Iterator<Dragao> itr=arrayDragon.iterator();

		while(itr.hasNext()){
			Dragao d=itr.next();
			if(d.get_x()==x && d.get_y() == y){
				itr.remove();
				break;
			}
		}
	}

	/**
	 * 	sets the maze
	 * @param maze, new maze
	 */
	public void setLabirinto(char [][] maze){
		this.l = new Labirinto(maze);
	}

	/**
	 *  moves the dragons, according with the gameMode
	 *  
	 */
	private void dragonAction(){

		switch (mode) {
		case StaticDragon://dragao parado
			break; 
		case MovingDragon://Dragao com Movimentacao 
			for (Dragao d : arrayDragon) {
				moveDragon(d);
			} 
			break;
		case ToogleSleepAndMoveDragon://Dragao com Movimentacao intercalada com Dormir 
			toggleAdormecerRandom();
			for (Dragao d : arrayDragon) {
				if(d.getAdormecido()==false){//se n�o estiver adormecido o dragao pode mover-se
					moveDragon(d);
				}
			}
			break;
		default:
			break;
		} 
		checkDragon();
	}


	/**
	 *  runs through the set of dragons and checks if any of them can kill the hero or be killed
	 */
	public void checkDragon(){
		Iterator<Dragao> itr = arrayDragon.iterator();

		while(itr.hasNext()){
			Dragao d=itr.next();
			if(d.get_x()==h.get_x() && d.get_y()==(h.get_y()-1)){//up
				if(h.getArmado()){
					l.setMaze(h.get_x(),h.get_y()-1, ' ');	
					itr.remove();
				}
				else if(!d.getAdormecido())
					fim_jogo=true; 

			}
			else if(d.get_x()==h.get_x() && d.get_y()==(h.get_y()+1)){//down
				if(h.getArmado()){
					l.setMaze(h.get_x(),h.get_y()+1, ' ');
					itr.remove();
				}
				else if(!d.getAdormecido())
					fim_jogo=true;

			}
			else  if(d.get_x()==(h.get_x()+1) && d.get_y()==h.get_y()){//right
				if(h.getArmado()){
					l.setMaze(h.get_x()+1,h.get_y(), ' ');
					itr.remove();
				}
				else if(!d.getAdormecido())
					fim_jogo=true;
			}

			else if(d.get_x()==(h.get_x()-1) && d.get_y()==h.get_y()){//left
				if(h.getArmado()){
					l.setMaze(h.get_x()-1,h.get_y(), ' ');	
					itr.remove();
				}
				else if(!d.getAdormecido())
					fim_jogo=true;
			} 
		} 
	} 

	/**
	 * runs through the set of dragons and if random number is equal to 1, the dragon changes his state 
	 */
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

	/**
	 * 	Moves the dragon
	 * @param dir represents the direction
	 * @param d represents a Dragon
	 */
	public void moveDragonAux(Direction dir, Dragao d) {
		int x=d.get_x();
		int y=d.get_y();

		d.setDir(dir);

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

	/**
	 * depending on the random variable, the dragon can move up, down, left or right 
	 * @param d represents a Dragon
	 */
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

	/**
	 * Moves the hero according with the Direction
	 * @param dir	represents the direction
	 * @return if it's possible return true else return false
	 */
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
			else { 
				dragonAction();
				return false;
			}
		default:
			dragonAction(); 
			return false;
		}

		dragonAction();
		return true;	

	}

	/** 
	 * 	returns a string that "textually represents" the maze
	 */
	public String toString(){
		return l.toString();
	}

	/**
	 * @return the maximum number of dragons that the labyrinth can have 
	 */
	public int NMaxDragons(){
		return (((l.getMaze().length * l.getMaze().length)/16)*4) - 1; 
	}


} 
