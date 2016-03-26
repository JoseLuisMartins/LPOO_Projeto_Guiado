package maze.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
import java.awt.Dimension;

import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.awt.event.ActionEvent;

public class Interface {

	private JFrame frmMaze;
	private JTextField mazeDimension;
	private JTextField nDragoes;
	private Jogo j=null;
	private JLabel erroNDragoes;
	private JLabel erroDimension;
	//desenho do mapa
	private MapWindow panel=new MapWindow();
	private JFrame f; 
	// ficheiros
	private SaveGame sGame;
	private JButton btnCarregar;
	private JTextField nomeFicheiro;
	private JComboBox<String> ficheiros;
	//criar mapa
	private JButton btnSaida;
	private JButton btnBloco;
	private JButton btnEspada;
	private JButton btnHeroi;
	private JButton btnDragao;
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
		sGame = new SaveGame();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */


	public void updateJcomboBox(){
		ficheiros.removeAllItems();
		for (String file :	sGame.getFiles()) {
			ficheiros.addItem(file);
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



		JComboBox<String> tipoDragoes = new JComboBox<String>();
		tipoDragoes.setBounds(193, 72, 231, 20);
		tipoDragoes.addItem("Estáticos");
		tipoDragoes.addItem("Com Movimentação");
		tipoDragoes.addItem("Com Movimentação e a Dormir");
		frmMaze.getContentPane().add(tipoDragoes);


		JButton terminarPrograma = new JButton("Terminar Programa");
		terminarPrograma.setForeground(Color.WHITE);
		terminarPrograma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sGame.writeNameFiles();
				System.exit(0);
			}
		});
		terminarPrograma.setBackground(Color.BLUE);
		terminarPrograma.setBounds(546, 96, 172, 43);
		frmMaze.getContentPane().add(terminarPrograma);


		f= new JFrame("Maze");
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);		

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

				GameMode mode=GameMode.StaticDragon;

				if(tipoDragoes.getSelectedItem()=="Estáticos")
					mode=GameMode.StaticDragon;
				else if(tipoDragoes.getSelectedItem()=="Com Movimentação")
					mode=GameMode.MovingDragon;
				else if(tipoDragoes.getSelectedItem()=="Com Movimentação e a Dormir") 
					mode=GameMode.ToogleSleepAndMoveDragon;

				j = new Jogo(maze,builder.getHeroi() ,builder.getDragao() ,builder.getEspada(),mode);

				int maxDragons = j.NMaxDragons();
				if(nD > maxDragons || nD <= 0){
					erroNDragoes.setText("Numero de dragões invalido!!");
					return;
				}
				erroNDragoes.setText(""); 
				nD--;
				j.addNDragons(nD); 



				panel.setJogo(j);
				panel.setCreateMap(false);
				panel.update();
				f.getContentPane().setPreferredSize(new Dimension(800,800));
				f.getContentPane().add(panel); 
				f.pack(); 
				f.setVisible(true);
				panel.requestFocus(); // to receive keyboard events /**/

			}
		});

		gerarLabirinto.setBackground(Color.BLUE);
		gerarLabirinto.setBounds(546, 21, 172, 43);
		frmMaze.getContentPane().add(gerarLabirinto);

		ficheiros = new JComboBox<String>();
		ficheiros.setBounds(484, 300, 262, 20);
		updateJcomboBox();
		frmMaze.getContentPane().add(ficheiros);


		nomeFicheiro = new JTextField();
		nomeFicheiro.setBounds(588, 186, 172, 36);
		frmMaze.getContentPane().add(nomeFicheiro);
		nomeFicheiro.setColumns(10);

		JLabel lableErroSaveName = new JLabel("");
		lableErroSaveName.setForeground(Color.RED);
		lableErroSaveName.setBounds(484, 234, 276, 27);
		frmMaze.getContentPane().add(lableErroSaveName);


		JButton btnSave = new JButton("Save");
		btnSave.setForeground(Color.WHITE);
		btnSave.setBackground(Color.BLUE);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(j!=null){

					String n=nomeFicheiro.getText() + ".txt";
					boolean valido=true;

					for (String nome : sGame.getFiles()) {
						if(nome.equals(n))
							valido=false;
					}

					if(valido){
						lableErroSaveName.setText("");
						sGame.save(n,j);
						updateJcomboBox();
					}
					else
						lableErroSaveName.setText("Introduza um nome Valido");
				}
				else
					lableErroSaveName.setText("Não existe nenhum jogo para gravar");
			}
		});
		btnSave.setBounds(484, 186, 94, 37);
		frmMaze.getContentPane().add(btnSave);

		btnCarregar = new JButton("Carregar");
		btnCarregar.setBackground(Color.BLUE);
		btnCarregar.setForeground(Color.WHITE);
		btnCarregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(ficheiros.getItemCount()>0){
					j = sGame.read(ficheiros.getSelectedItem().toString());

					panel.setJogo(j);
					panel.setCreateMap(false);
					panel.update();
					f.getContentPane().setPreferredSize(new Dimension(800,800));
					f.getContentPane().add(panel); 
					f.pack(); 
					f.setVisible(true);
					panel.requestFocus(); // to receive keyboard events /**/
				}
			}
		});
		btnCarregar.setBounds(484, 327, 119, 48); 
		frmMaze.getContentPane().add(btnCarregar);

		JLabel lblFicheiros = new JLabel("Ficheiros:");
		lblFicheiros.setForeground(Color.BLUE);
		lblFicheiros.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFicheiros.setBounds(484, 272, 114, 27);
		frmMaze.getContentPane().add(lblFicheiros);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(ficheiros.getItemCount() > 0){
					String n=ficheiros.getSelectedItem().toString();
					Iterator<String> itr=sGame.getFiles().iterator();

					while(itr.hasNext()){
						String nameItr=itr.next();
						if(nameItr.equals(n)){
							File f= new File(n);
							try {
								f.delete();
							} catch (Exception e) {
								e.printStackTrace();
								return;
							}
							itr.remove();
							break;
						}
					}
					sGame.writeNameFiles();	
					updateJcomboBox();
				}
			}
		});
		btnEliminar.setBackground(Color.BLUE);
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setBounds(620, 327, 126, 48);
		frmMaze.getContentPane().add(btnEliminar); 


		btnHeroi = new JButton("Heroi");
		btnHeroi.setForeground(Color.WHITE);
		btnHeroi.setBackground(Color.LIGHT_GRAY);
		btnHeroi.setEnabled(false);
		btnHeroi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel.setCurrentElement(CreateGameElement.Hero);
				btnHeroi.setBackground(Color.BLUE);
				btnDragao.setBackground(Color.LIGHT_GRAY);
				btnEspada.setBackground(Color.LIGHT_GRAY);
				btnBloco.setBackground(Color.LIGHT_GRAY);
				btnSaida.setBackground(Color.LIGHT_GRAY);
			}
		});
		btnHeroi.setBounds(41, 256, 124, 43);
		frmMaze.getContentPane().add(btnHeroi);

		btnDragao = new JButton("Dragao");
		btnDragao.setForeground(Color.WHITE);
		btnDragao.setBackground(Color.LIGHT_GRAY);
		btnDragao.setEnabled(false);
		btnDragao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDragao.setBackground(Color.BLUE);
				btnHeroi.setBackground(Color.LIGHT_GRAY);
				btnEspada.setBackground(Color.LIGHT_GRAY);
				btnBloco.setBackground(Color.LIGHT_GRAY);
				btnSaida.setBackground(Color.LIGHT_GRAY);
				panel.setCurrentElement(CreateGameElement.Dragon);
			}
		});
		btnDragao.setBounds(227, 256, 114, 43);
		frmMaze.getContentPane().add(btnDragao);

		btnEspada = new JButton("Espada");
		btnEspada.setForeground(Color.WHITE);
		btnEspada.setBackground(Color.LIGHT_GRAY);
		btnEspada.setEnabled(false);
		btnEspada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setCurrentElement(CreateGameElement.Sword);
				btnEspada.setBackground(Color.BLUE);
				btnDragao.setBackground(Color.LIGHT_GRAY);
				btnHeroi.setBackground(Color.LIGHT_GRAY);
				btnBloco.setBackground(Color.LIGHT_GRAY);
				btnSaida.setBackground(Color.LIGHT_GRAY);
			}
		});
		btnEspada.setBounds(41, 310, 124, 40);
		frmMaze.getContentPane().add(btnEspada);

		btnBloco = new JButton("Bloco");
		btnBloco.setForeground(Color.WHITE);
		btnBloco.setBackground(Color.LIGHT_GRAY);

		btnBloco.setEnabled(false);
		btnBloco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setCurrentElement(CreateGameElement.Block);
				btnBloco.setBackground(Color.BLUE);
				btnEspada.setBackground(Color.LIGHT_GRAY);
				btnDragao.setBackground(Color.LIGHT_GRAY);
				btnHeroi.setBackground(Color.LIGHT_GRAY);
				btnSaida.setBackground(Color.LIGHT_GRAY);

			}
		});
		btnBloco.setBounds(227, 310, 114, 40);
		frmMaze.getContentPane().add(btnBloco);

		btnSaida = new JButton("Saida");
		btnSaida.setForeground(Color.WHITE);
		btnSaida.setBackground(Color.LIGHT_GRAY);

		btnSaida.setEnabled(false);
		btnSaida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setCurrentElement(CreateGameElement.Exit);
				btnSaida.setBackground(Color.BLUE);
				btnBloco.setBackground(Color.LIGHT_GRAY);
				btnEspada.setBackground(Color.LIGHT_GRAY);
				btnDragao.setBackground(Color.LIGHT_GRAY);
				btnHeroi.setBackground(Color.LIGHT_GRAY);
			}
		});
		btnSaida.setBounds(142, 361, 108, 42);
		frmMaze.getContentPane().add(btnSaida);

		JLabel lblErrorCreateMap = new JLabel("");
		lblErrorCreateMap.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblErrorCreateMap.setForeground(Color.RED);
		lblErrorCreateMap.setBounds(309, 437, 451, 37);
		frmMaze.getContentPane().add(lblErrorCreateMap);

		JButton btnTerminarCriao = new JButton("Terminar Cria\u00E7\u00E3o");
		btnTerminarCriao.setBackground(Color.BLUE);
		btnTerminarCriao.setForeground(Color.WHITE);
		btnTerminarCriao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				lblErrorCreateMap.setText(""); 

				if(j.getHeroi()==null || j.getEspada()==null || j.getHasExit()==false || j.getDragoes().size()==0){
					lblErrorCreateMap.setText("Precisa de colocar um heroi, uma espada, uma saida e pelo menos um dragao!");
					return;
				}
				for (Dragao d  : j.getDragoes()) {
					int xHero=j.getHeroi().get_x();
					int yHero=j.getHeroi().get_y();
					if(Math.abs(d.get_x() - xHero) + Math.abs(d.get_y() - yHero) == 1){
						lblErrorCreateMap.setText("Não podem existir dragoes adjacentes ao Heroi!");
						return;
					}
				}

				btnDragao.setEnabled(false);
				btnEspada.setEnabled(false);
				btnHeroi.setEnabled(false);
				btnSaida.setEnabled(false);
				btnBloco.setEnabled(false);
				btnTerminarCriao.setEnabled(false);
				panel.setCreateMap(false);
			}
		});
		btnTerminarCriao.setEnabled(false);
		btnTerminarCriao.setBounds(125, 414, 154, 60);
		frmMaze.getContentPane().add(btnTerminarCriao);



		JButton btnCriarMapa = new JButton("Criar Mapa");
		btnCriarMapa.setBackground(Color.BLUE);
		btnCriarMapa.setForeground(Color.WHITE);
		btnCriarMapa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MazeBuilder builder = new MazeBuilder();
				int dim=Integer.parseInt(mazeDimension.getText());

				if(dim % 2 == 0 || dim > 21 || dim < 5){
					erroDimension.setText("Dimensão inválida!!");
					return;
				}
				erroDimension.setText("");

				GameMode mode=GameMode.StaticDragon;

				if(tipoDragoes.getSelectedItem()=="Estáticos")
					mode=GameMode.StaticDragon;
				else if(tipoDragoes.getSelectedItem()=="Com Movimentação")
					mode=GameMode.MovingDragon;
				else if(tipoDragoes.getSelectedItem()=="Com Movimentação e a Dormir") 
					mode=GameMode.ToogleSleepAndMoveDragon;

				char[][] maze= new char[dim][dim];

				//iniciar o maze

				for (int i = 0; i <dim ; i++) {
					for (int j = 0; j < dim; j++) {
						if(i==0 || i == dim-1 || j==0 || j==dim-1 )
							maze[i][j]='X';
						else
							maze[i][j]=' ';
					}
				}

				j=new Jogo();
				j.setDragons(new ArrayList<Dragao>());
				j.setEspada(null);
				j.setHeroi(null);
				j.setLabirinto(maze);
				j.setGameMode(mode);
				j.setHasExit(false);

				panel.setJogo(j);
				panel.setCreateMap(true);
				panel.update();
				f.getContentPane().setPreferredSize(new Dimension(800,800));
				f.getContentPane().add(panel); 
				f.pack(); 
				f.setVisible(true);
				panel.requestFocus(); // to receive keyboard events /**/

				btnDragao.setEnabled(true);
				btnEspada.setEnabled(true);
				btnHeroi.setEnabled(true);
				btnSaida.setEnabled(true);
				btnBloco.setEnabled(true);
				btnBloco.setBackground(Color.BLUE);
				btnEspada.setBackground(Color.LIGHT_GRAY);
				btnDragao.setBackground(Color.LIGHT_GRAY);
				btnHeroi.setBackground(Color.LIGHT_GRAY);
				btnSaida.setBackground(Color.LIGHT_GRAY);
				btnTerminarCriao.setEnabled(true);

			}
		});
		btnCriarMapa.setBounds(132, 154, 164, 68);
		frmMaze.getContentPane().add(btnCriarMapa);




	}
}
