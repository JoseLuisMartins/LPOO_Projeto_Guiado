package maze.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import maze.logic.Direction;
import maze.logic.Dragao;
import maze.logic.GameMode;
import maze.logic.Jogo;
import maze.logic.MazeBuilder;

import javax.swing.JComboBox;
import javax.print.attribute.TextSyntax;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;

public class Interface {

	private JFrame frmMaze;
	private JTextField mazeDimension;
	private JTextField nDragoes;
	private Jogo j;
	private GameMode mode;
	private JButton baixo;
	private JButton esquerda;
	private JButton direita;
	private JButton cima;
	private JLabel estadoJogo;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface window = new Interface();
					window.frmMaze.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void dragonAction(){

		switch (mode) {
		case StaticDragon://dragao parado
			break; 
		case MovingDragon://Dragao com Movimentacao 
			for (Dragao d : j.getDragoes()) {
				j.moveDragon(d);
			} 
			j.checkDragon();
			break;
		case ToogleSleepAndMoveDragon://Dragao com Movimentacao intercalada com Dormir 
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
	private void checkFimJogo(){

		if(j.getFimJogo())
			estadoJogo.setText("Foi Derrotado Pelo Dragao!");
		else if(j.getSair())
			estadoJogo.setText("Parabens, Conseguiu derrotar ao Dragao!");	
		
		if(j.getFimJogo() || j.getSair()){
			cima.setEnabled(false);
			baixo.setEnabled(false);
			esquerda.setEnabled(false);
			direita.setEnabled(false);
		}

	}

	private void initialize() {
		frmMaze = new JFrame();
		frmMaze.setResizable(false);
		frmMaze.setTitle("Maze");
		frmMaze.setBounds(100, 100, 782, 498);
		frmMaze.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMaze.getContentPane().setLayout(null);

		JLabel lblDimensoDoLabirinto = new JLabel("Dimens\u00E3o do Labirinto");
		lblDimensoDoLabirinto.setBounds(41, 25, 119, 14);
		frmMaze.getContentPane().add(lblDimensoDoLabirinto);

		JLabel lblNumeroDeDrages = new JLabel("Numero de Drag\u00F5es");
		lblNumeroDeDrages.setBounds(41, 50, 105, 14);
		frmMaze.getContentPane().add(lblNumeroDeDrages);

		JLabel lblTipoDeDrages = new JLabel("Tipo de Drag\u00F5es");
		lblTipoDeDrages.setBounds(41, 75, 92, 14);
		frmMaze.getContentPane().add(lblTipoDeDrages);

		mazeDimension = new JTextField();
		mazeDimension.setText("11");
		mazeDimension.setBounds(193, 22, 86, 20);
		frmMaze.getContentPane().add(mazeDimension);
		mazeDimension.setColumns(10);

		nDragoes = new JTextField();
		nDragoes.setText("1");
		nDragoes.setBounds(193, 47, 86, 20);
		frmMaze.getContentPane().add(nDragoes);
		nDragoes.setColumns(10);

		JComboBox tipoDragoes = new JComboBox();
		tipoDragoes.setBounds(193, 72, 265, 20);
		tipoDragoes.addItem("Estáticos");
		tipoDragoes.addItem("Com Movimentação");
		tipoDragoes.addItem("Com Movimentação e a Dormir");
		frmMaze.getContentPane().add(tipoDragoes);


		JButton terminarPrograma = new JButton("Terminar Programa");
		terminarPrograma.setForeground(Color.WHITE);
		terminarPrograma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		terminarPrograma.setBackground(Color.BLUE);
		terminarPrograma.setBounds(546, 96, 172, 43);
		frmMaze.getContentPane().add(terminarPrograma);

		JTextArea labOutput = new JTextArea();
		labOutput.setFont(new Font("Courier New", Font.PLAIN, 13));
		labOutput.setEditable(false);
		labOutput.setBounds(41, 142, 409, 269);
		frmMaze.getContentPane().add(labOutput);

		estadoJogo = new JLabel("Pode gerar novo labirinto!");
		estadoJogo.setBounds(51, 422, 399, 14);
		frmMaze.getContentPane().add(estadoJogo);

		cima = new JButton("Cima");
		cima.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				j.move(Direction.UP);
				dragonAction();
				labOutput.setText(j.toString());
				checkFimJogo();
			}
		});
		cima.setEnabled(false);
		cima.setBounds(566, 207, 100, 29);
		frmMaze.getContentPane().add(cima);

		esquerda = new JButton("Esquerda");
		esquerda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				j.move(Direction.LEFT);
				dragonAction();
				labOutput.setText(j.toString());
				checkFimJogo();
			}
		});
		esquerda.setEnabled(false);
		esquerda.setBounds(505, 241, 100, 29);
		frmMaze.getContentPane().add(esquerda);

		direita = new JButton("Direita");
		direita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				j.move(Direction.RIGHT);
				dragonAction();
				labOutput.setText(j.toString());
				checkFimJogo();
			}
		});
		direita.setEnabled(false);
		direita.setBounds(632, 241, 105, 29);
		frmMaze.getContentPane().add(direita);

		baixo = new JButton("Baixo");
		baixo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				j.move(Direction.DOWN);
				dragonAction();
				labOutput.setText(j.toString());
				checkFimJogo();
			}
		});
		baixo.setEnabled(false);
		baixo.setBounds(566, 275, 100, 29);
		frmMaze.getContentPane().add(baixo);


		JButton gerarLabirinto = new JButton("Gerar novo labirinto");
		gerarLabirinto.setForeground(Color.WHITE);
		gerarLabirinto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MazeBuilder builder = new MazeBuilder();
				int dim=Integer.parseInt(mazeDimension.getText());
				int nD=Integer.parseInt(nDragoes.getText());
				char[][] maze=builder.buildMaze(dim);
				Random r=new Random();

				j = new Jogo(maze,builder.getHeroi() ,builder.getDragao() ,builder.getEspada());
				cima.setEnabled(true);
				baixo.setEnabled(true);
				esquerda.setEnabled(true);
				direita.setEnabled(true);
				if(tipoDragoes.getSelectedItem()=="Estáticos")
					mode=GameMode.StaticDragon;
				else if(tipoDragoes.getSelectedItem()=="Com Movimentação")
					mode=GameMode.MovingDragon;
				else
					mode=GameMode.ToogleSleepAndMoveDragon;

				nD--;
				while(nD>0){
					if(j.addDragon(r.nextInt(dim-2)+1, r.nextInt(dim-2)+1))
						nD--;
				}
				labOutput.setText(j.toString());

			}
		});
		gerarLabirinto.setBackground(Color.BLUE);
		gerarLabirinto.setBounds(546, 21, 172, 43);
		frmMaze.getContentPane().add(gerarLabirinto);
	}
}
