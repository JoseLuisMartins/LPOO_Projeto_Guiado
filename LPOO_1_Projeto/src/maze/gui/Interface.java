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
	private JLabel erroNDragoes;
	private JLabel erroDimension;


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
			estadoJogo.setText("Foi derrotado pelo dragão!");
		else if(j.getSair())
			estadoJogo.setText("Parabéns, conseguiu derrotar o(s) dragão!");	
		else if(j.getDragoes().size()>=0)
			estadoJogo.setText("Falta(m) matar " + j.getDragoes().size() + " dragõe(s)");	
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
		frmMaze.setBounds(100, 100, 807, 527);
		frmMaze.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMaze.getContentPane().setLayout(null);

		JLabel lblDimensoDoLabirinto = new JLabel("Dimens\u00E3o do Labirinto");
		lblDimensoDoLabirinto.setBounds(41, 25, 142, 14);
		frmMaze.getContentPane().add(lblDimensoDoLabirinto);

		JLabel lblNumeroDeDrages = new JLabel("Numero de Drag\u00F5es");
		lblNumeroDeDrages.setBounds(41, 50, 142, 14);
		frmMaze.getContentPane().add(lblNumeroDeDrages);

		JLabel lblTipoDeDrages = new JLabel("Tipo de Drag\u00F5es");
		lblTipoDeDrages.setBounds(41, 75, 142, 14);
		frmMaze.getContentPane().add(lblTipoDeDrages);

		erroDimension = new JLabel("");
		erroDimension.setForeground(Color.RED);
		erroDimension.setBounds(294, 22, 218, 20);
		frmMaze.getContentPane().add(erroDimension);


		erroNDragoes = new JLabel("");
		erroNDragoes.setForeground(Color.RED);
		erroNDragoes.setBounds(294, 47, 218, 20);
		frmMaze.getContentPane().add(erroNDragoes);

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
		tipoDragoes.setBounds(193, 72, 231, 20);
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
		labOutput.setForeground(Color.WHITE);
		labOutput.setBackground(Color.LIGHT_GRAY);
		labOutput.setFont(new Font("Courier New", Font.PLAIN, 13));
		labOutput.setEditable(false);
		labOutput.setBounds(32, 112, 392, 316);
		frmMaze.getContentPane().add(labOutput);

		estadoJogo = new JLabel("Pode gerar novo labirinto!");
		estadoJogo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		estadoJogo.setBounds(53, 439, 399, 20);
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
		cima.setBounds(566, 198, 112, 32);
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
		esquerda.setBounds(506, 241, 104, 32);
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
		direita.setBounds(620, 241, 111, 32);
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
		baixo.setBounds(566, 284, 124, 32);
		frmMaze.getContentPane().add(baixo);




		JButton gerarLabirinto = new JButton("Gerar novo labirinto");
		gerarLabirinto.setForeground(Color.WHITE);
		gerarLabirinto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MazeBuilder builder = new MazeBuilder();
				int dim=Integer.parseInt(mazeDimension.getText());
				int nD=Integer.parseInt(nDragoes.getText());
				
				erroNDragoes.setText("");
				if(dim % 2 == 0 || dim > 21 || dim < 5){
					erroDimension.setText("Dimensão inválida!!");
					return;
				}
				erroDimension.setText("");
					
				char[][] maze=builder.buildMaze(dim);
				Random r=new Random();

				j = new Jogo(maze,builder.getHeroi() ,builder.getDragao() ,builder.getEspada());

				if(tipoDragoes.getSelectedItem()=="Estáticos")
					mode=GameMode.StaticDragon;
				else if(tipoDragoes.getSelectedItem()=="Com  Movimentação")
					mode=GameMode.MovingDragon;
				else
					mode=GameMode.ToogleSleepAndMoveDragon;

				int maxDragons = j.NMaxDragons();
				if(nD > maxDragons || nD <= 0){
					erroNDragoes.setText("Numero de dragões invalido!!");
					return;
				}
				erroNDragoes.setText(""); 
				
				j.addNDragons(nD);
				
				cima.setEnabled(true);
				baixo.setEnabled(true);
				esquerda.setEnabled(true);
				direita.setEnabled(true);
				
				labOutput.setText(j.toString());

			}
		});
		gerarLabirinto.setBackground(Color.BLUE);
		gerarLabirinto.setBounds(546, 21, 172, 43);
		frmMaze.getContentPane().add(gerarLabirinto);


	}
}
