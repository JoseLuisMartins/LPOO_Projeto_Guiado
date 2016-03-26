package maze.test;

import static org.junit.Assert.*;

import org.junit.Test;

import maze.logic.Dragao;
import maze.logic.Espada;
import maze.logic.GameMode;
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
		Jogo j= new Jogo(m1,h,d,e,GameMode.MovingDragon);

		
		assertEquals(3, j.getDragoes().get(0).get_x()); 
		assertEquals(3, j.getDragoes().get(0).get_y());
		
		j.moveDragon( j.getDragoes().get(0));
		
		assertEquals(true,(3 == j.getDragoes().get(0).get_x()) ||  
						  (2 == j.getDragoes().get(0).get_x()));
		assertEquals(true,(3 == j.getDragoes().get(0).get_y()) ||  
						  (2 == j.getDragoes().get(0).get_y()));
	} 
	 
	@Test 
	public void testMoveDragontoSword() {
		Espada sword=new Espada(2, 3, 'E');
		Jogo j= new Jogo(m1,h,d,sword,GameMode.MovingDragon);
		Dragao d1=j.getDragoes().get(0);
		
		assertEquals(3,  d1.get_x()); 
		assertEquals(3, d1.get_y());
			 
		while (!( d1.get_x() == 2 && d1.get_y() == 3)) {
			j.moveDragon(d1);
		}
		
		assertEquals('F', d1.get_simbolo());
		
		while ((d1.get_x() == 2 && d1.get_y() == 3)) {
			j.moveDragon(d1);
		}
		 
		assertEquals('D', d1.get_simbolo());
	}  
 
	@Test 
	public void testToggleSleep() {
		Jogo j= new Jogo(m1,h,d,e,GameMode.MovingDragon);
		Dragao d1=j.getDragoes().get(0);
		assertEquals(false, d1.getAdormecido());
		
		
		while(d1.getAdormecido()==false)
		j.toggleAdormecerRandom();		
		
		assertEquals('d', d1.get_simbolo());
		
		while(d1.getAdormecido()==true)
			j.toggleAdormecerRandom();
		
		assertEquals('D', d1.get_simbolo());
	} 
	
	@Test 
	public void testToggleSleepWithSword() {
		Jogo j= new Jogo(m1,h,d,e,GameMode.MovingDragon);
		Dragao d1=j.getDragoes().get(0);
		
		assertEquals(false, d1.getAdormecido());
		
		while (!(d1.get_x() == 1 && d1.get_y() == 3)) {
			j.moveDragon(d1);
		}
		
		while(d1.getAdormecido()==false)
		j.toggleAdormecerRandom();		
		
		assertEquals('f',d1.get_simbolo());
		
		while(d1.getAdormecido()==true)
			j.toggleAdormecerRandom();
		
		assertEquals('F', d1.get_simbolo());
	}
	
	
	
	
	
	
	
	
	
}
/**/