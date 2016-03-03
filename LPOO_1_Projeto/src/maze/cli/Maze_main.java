package maze.cli;
import java.util.Scanner;

import maze.logic.Dragao;
import maze.logic.Espada;
import maze.logic.Heroi;
import maze.logic.Jogo;



 
public class Maze_main {
	public static void main(String[] args){
		
		Jogo j= new Jogo();

		Scanner s = new Scanner(System.in);
		
		System.out.println("---------Jogo do Labirinto---------\n");
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
				j.moveUp();
				break;
			case 'a'://left
				j.moveLeft();
				break;
			case 's'://down
				j.moveDown();
				break;
			case 'd'://right
				j.moveRight();
				break;
			default:
				break;
			}

			if(j.getDragao().getMorto()==false){//se o dragao ainda não morreu verificar se esta em celulas adjacentes

				switch (m) {
				case 1://dragao parado

					break;
				case 2://Dragao com Movimentacao
					j.moveDragon();
					break;
				case 3://Dragao com Movimentacao intercalada com Dormir 
					j.toggleAdormecerRandom();

					if(j.getDragao().getAdormecido()==false)//se não estiver adormecido o dragao pode mover-se
						j.moveDragon();
					break;
				default:
					break;
				}
	

				j.checkDragon();	
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
	}
}
