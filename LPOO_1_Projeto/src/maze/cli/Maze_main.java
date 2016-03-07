package maze.cli;
import java.util.Iterator;
import java.util.Scanner;

import maze.logic.Direction;
import maze.logic.Dragao;
import maze.logic.Espada;
import maze.logic.Heroi;
import maze.logic.IMazeBuilder;
import maze.logic.Jogo;
import maze.logic.MazeBuilder;




public class Maze_main {
	public static void main(String[] args){

		Jogo j;
		MazeBuilder builder = new MazeBuilder();
		Scanner s = new Scanner(System.in);

		System.out.println("---------Jogo do Labirinto---------");
		System.out.println("Labirinto Normal (prima numero 1)");
		System.out.println("Labirinto Aleatorio (prima numero 2)");
		String lab = s.nextLine();

		while(!(lab.equals("1") || lab.equals("2"))){
			System.out.println("Insira um modo Valido \n");
			lab= s.nextLine();	
		}


		int tamanho;

		if(lab.equals("2")){
			System.out.println("Indique o tamanho do labirinto (numero impar >= 5): ");
			tamanho = s.nextInt();
			while(tamanho % 2 == 0 || tamanho < 5){
				System.out.println("Indique o tamanho do labirinto (numero impar >= 5):  ");
				tamanho = s.nextInt();	
			}

			j = new Jogo(builder.buildMaze(tamanho),builder.getHeroi() ,builder.getDragao() ,builder.getEspada());
		}
		else{
			j = new Jogo();
		}
		Dragao dra = new Dragao(8,1, 'D');
		j.addDragons(dra);
		System.out.println("Selecione o modo de jogo\n");
		System.out.println("Dragao Parado (prima numero 1)");
		System.out.println("Dragao com Movimentacao (prima numero 2)");
		System.out.println("Dragao com Movimentacao intercalada com Dormir (prima numero 3)");

		String modo=s.nextLine();

		while(!(modo.equals("1") || modo.equals("2") || modo.equals("3"))){
			System.out.println("Insira um modo Valido \n");
			modo= s.nextLine();	
		}
		int m= Integer.parseInt(modo);


		System.out.println(j);

		while(!j.getFimJogo() && !j.getSair()){
			String movimento= s.nextLine();
			while(!(movimento.equals("a") || movimento.equals("w") || movimento.equals("s") || movimento.equals("d") )){
				movimento= s.nextLine();	
			}

			char mov = movimento.charAt(0);

			switch (mov) {
			case 'w'://up
				j.move(Direction.UP);
				break;
			case 's'://down
				j.move(Direction.DOWN);
				break;
			case 'd'://right
				j.move(Direction.RIGHT);
				break;
			case 'a'://left
				j.move(Direction.LEFT); 
				break;
			default:
				break;
			}

			if(j.getDragoes().size()!=0 && j.getFimJogo()==false){//se o dragao nao morreu e o heroi tambem nao, movimentar o dragao ou adormecer dependendo do modo de jogo

				switch (m) {
				case 1://dragao parado

					break;
				case 2://Dragao com Movimentacao 
					for (Dragao d : j.getDragoes()) {
						j.moveDragon(d);
					}
					j.checkDragon();
					break;
				case 3://Dragao com Movimentacao intercalada com Dormir 
					j.toggleAdormecerRandom();
					for (Dragao d : j.getDragoes()) {
						if(d.getAdormecido()==false){//se não estiver adormecido o dragao pode mover-se
							j.moveDragon(d);
						}
					}
					j.checkDragon();
					break;
				default:
					break;
				} 
			}

			System.out.println(j);

		}

		System.out.println(j);
		if (j.getSair()) {
			System.out.println("Parabens, Conseguiu derrotar ao Dragao!\n");
		} else {
			System.out.println("Foi Derrotado Pelo Dragao!\n ");
		}

		s.close();
		/**/
	}
}
