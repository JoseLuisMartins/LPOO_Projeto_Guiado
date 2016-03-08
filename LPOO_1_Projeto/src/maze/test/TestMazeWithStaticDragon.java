package maze.test;

import static org.junit.Assert.*;

import org.junit.Test;

import maze.logic.Direction;
import maze.logic.Dragao;
import maze.logic.Espada;
import maze.logic.Heroi;
import maze.logic.Jogo;
import maze.logic.Labirinto;

public class TestMazeWithStaticDragon {

	char [][] m1 = {{'X', 'X', 'X', 'X', 'X'},
					{'X', ' ', ' ', ' ', 'S'},
					{'X', ' ', 'X', ' ', 'X'},
					{'X', ' ', ' ', ' ', 'X'},
					{'X', 'X', 'X', 'X', 'X'}};

	Heroi h=new Heroi(3, 1, 'H'); 
	Dragao d=new Dragao(3,3,'D');
	Espada e=new Espada(1, 3, 'E'); 

	@Test
	public void testMoveHeroToFreeCell() {
		Jogo j= new Jogo(m1,h,d,e); 
		assertEquals(j.getHeroi().get_simbolo(), j.getLabirinto().getMaze()[j.getHeroi().get_y()][j.getHeroi().get_x()]);
		assertEquals(3,j.getHeroi().get_x());
		assertEquals(1,j.getHeroi().get_y());
		assertEquals(true,j.move(Direction.LEFT));
		assertEquals(2,j.getHeroi().get_x());
		assertEquals(1,j.getHeroi().get_y());
	}
	
	@Test
	public void TestDefaultGameConstructor() {
		Jogo game=new Jogo();
		Dragao d1 = game.getDragoes().get(0);
		assertEquals(game.getHeroi().get_simbolo(), game.getLabirinto().getMaze()[game.getHeroi().get_y()][game.getHeroi().get_x()]);
		assertEquals(d1.get_simbolo(), game.getLabirinto().getMaze()[d1.get_y()][d1.get_x()]);
		assertEquals(game.getEspada().get_simbolo(), game.getLabirinto().getMaze()[game.getEspada().get_y()][game.getEspada().get_x()]);
		System.out.println(game);
	}
	
	@Test
	public void testMoveHeroToWall() {
		Jogo j= new Jogo(m1,h,d,e);
		assertEquals(3,j.getHeroi().get_x());
		assertEquals(1,j.getHeroi().get_y());
		assertEquals(false,j.move(Direction.UP));
		assertEquals(3,j.getHeroi().get_x());
		assertEquals(1,j.getHeroi().get_y());
	}
	
	@Test
	public void testHeroDies() {
		Jogo j= new Jogo(m1,h,d,e);
		j.move(Direction.DOWN);
		assertEquals(true,j.getFimJogo());
	}
	
	@Test
	public void testHeroPickSword() { 
		Heroi h1=new Heroi(1, 2, 'H');
		Jogo j= new Jogo(m1,h1,d,e);
		j.move(Direction.DOWN);
		assertEquals(true,j.getHeroi().getArmado());
		assertEquals('A',j.getHeroi().get_simbolo());
		assertEquals(' ',j.getLabirinto().getMaze()[j.getHeroi().get_y()-1][j.getHeroi().get_x()]);
		assertEquals(j.getHeroi().get_simbolo(),j.getLabirinto().getMaze()[j.getHeroi().get_y()][j.getHeroi().get_x()]);
	}
	
	@Test
	public void testDragonDiesDown() {
		Jogo j= new Jogo(m1,h,d,e);//mata dragao em baixo
		Dragao d1 = j.getDragoes().get(0);
		j.getHeroi().setArmado();
		j.move(Direction.DOWN);
		assertEquals(true,j.getDragoes().size()==0);
	
	}
	@Test
	public void testDragonDiesLeft() {
		Dragao d1=new Dragao(1, 1, 'D');//mata dragao á esquerda
		Heroi  h1=new Heroi(3, 1, 'H');
		Jogo j= new Jogo(m1,h1,d1,e);
		j.getHeroi().setArmado();
		j.move(Direction.LEFT);
		assertEquals(true,j.getDragoes().size()==0);
	
	}
	
	@Test
	public void testDragonDiesUp() {
		Dragao d1=new Dragao(3, 1, 'D');//mata dragao em cima
		Heroi  h1=new Heroi(3, 3, 'H');
		Jogo j= new Jogo(m1,h1,d1,e);
		j.getHeroi().setArmado();
		j.move(Direction.UP);
		assertEquals(true,j.getDragoes().size()==0);
	}
	
	@Test
	public void testDragonDiesRight() {
		Dragao d1=new Dragao(3, 1, 'D');//mata dragao á direita 
		Heroi  h1=new Heroi(1, 1, 'H');
		Jogo j= new Jogo(m1,h1,d1,e); 
		j.getHeroi().setArmado();
		j.move(Direction.RIGHT);
		assertEquals(true,j.getDragoes().size()==0);	
	}
	
	
	
	@Test
	public void testHeroWins() {
		Jogo j= new Jogo(m1,h,d,e);
		j.move(Direction.LEFT);
		j.move(Direction.LEFT);
		j.move(Direction.DOWN);
		j.move(Direction.DOWN);
		j.move(Direction.RIGHT);
		j.move(Direction.RIGHT);
		j.move(Direction.UP);
		j.move(Direction.UP);
		assertEquals(true,j.move(Direction.RIGHT));
		assertEquals(true,j.getSair());
		assertEquals(' ',j.getLabirinto().getMaze()[j.getHeroi().get_y()][j.getHeroi().get_x()-1]);
		assertEquals(j.getHeroi().get_simbolo(),j.getLabirinto().getMaze()[j.getHeroi().get_y()][j.getHeroi().get_x()]);
		
	} 
	
	@Test
	public void testHeroGettingOutWithoutSword() {
		Jogo j= new Jogo(m1,h,d,e);
		assertEquals(false,j.move(Direction.RIGHT));
	}
	 
	
	@Test
	public void testHeroGettingOutWithoutKillingTheDragon() {
		Jogo j= new Jogo(m1,h,d,e);
		
		j.getHeroi().setArmado();
		assertEquals(true, j.getHeroi().getArmado());
		assertEquals(false,j.move(Direction.RIGHT));
	}
	
	@Test
	public void testAddDragon() {
		Jogo j= new Jogo(m1,h,d,e);
		
		assertEquals(false,j.addDragon(0, 0));
		assertEquals(true,j.addDragon(1, 1));
		assertEquals('D',j.getLabirinto().getMaze()[1][1]);
		assertEquals(2,j.getDragoes().size());
		assertEquals(false,j.addDragon(3,1));
	}
	
	
	
	/**/
	
	
	

}
