package maze.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import maze.logic.Dragao;
import maze.logic.Espada;
import maze.logic.Heroi;
import maze.logic.Jogo;

public class TestMazeWithMoveAndSlpeepCapabilitiesDragon {
	
	char [][] m1 = {{'X', 'X', 'X', 'X', 'X'},
					{'X', ' ', ' ', ' ', 'S'},
					{'X', ' ', 'X', ' ', 'X'} ,
					{'X', ' ', ' ', ' ', 'X'},
					{'X', 'X', 'X', 'X', 'X'}};

	Heroi h=new Heroi(2, 1, 'H');
	Dragao d=new Dragao(3,3,'D');
	Espada e=new Espada(1, 3, 'E');  
	
	
	@Test
	public void testRamdomMoveDragon() {
		Jogo j= new Jogo(m1,h,d,e);
		assertEquals(3, j.getDragao().get_x()); 
		assertEquals(3, j.getDragao().get_y());
		
		j.moveDragon();
		
		assertEquals(true,(3 == j.getDragao().get_x()) ||  
						  (2 == j.getDragao().get_x()));
		assertEquals(true,(3 == j.getDragao().get_y()) ||  
						  (2 == j.getDragao().get_y()));
	} 
	 
	@Test 
	public void testMoveDragontoSword() {
		Espada sword=new Espada(2, 3, 'E');
		Jogo j= new Jogo(m1,h,d,sword);
		assertEquals(3, j.getDragao().get_x()); 
		assertEquals(3, j.getDragao().get_y());
			 
		while (!(j.getDragao().get_x() == 2 && j.getDragao().get_y() == 3)) {
			j.moveDragon();
		}
		
		assertEquals('F', j.getDragao().get_simbolo());
		
		while ((j.getDragao().get_x() == 2 && j.getDragao().get_y() == 3)) {
			j.moveDragon();
		}
		 
		assertEquals('D', j.getDragao().get_simbolo());
	}  
 
	@Test 
	public void testToggleSleep() {
		Jogo j= new Jogo(m1,h,d,e);
		assertEquals(false, j.getDragao().getAdormecido());
		
		
		while(j.getDragao().getAdormecido()==false)
		j.toggleAdormecerRandom();		
		
		assertEquals('d', j.getDragao().get_simbolo());
		
		while(j.getDragao().getAdormecido()==true)
			j.toggleAdormecerRandom();
		
		assertEquals('D', j.getDragao().get_simbolo());
	} 
	
	@Test 
	public void testToggleSleepWithSword() {
		Jogo j= new Jogo(m1,h,d,e);
		assertEquals(false, j.getDragao().getAdormecido());
		
		while (!(j.getDragao().get_x() == 1 && j.getDragao().get_y() == 3)) {
			j.moveDragon();
		}
		
		while(j.getDragao().getAdormecido()==false)
		j.toggleAdormecerRandom();		
		
		assertEquals('f', j.getDragao().get_simbolo());
		
		while(j.getDragao().getAdormecido()==true)
			j.toggleAdormecerRandom();
		
		assertEquals('F', j.getDragao().get_simbolo());
	}
	
	
	
	/**/
	
	
	
	
	
}
