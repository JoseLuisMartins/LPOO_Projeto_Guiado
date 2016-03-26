package maze.gui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import maze.logic.Dragao;
import maze.logic.Espada;
import maze.logic.GameMode;
import maze.logic.Heroi;
import maze.logic.Jogo;

public class SaveGame {

	private ArrayList<String> files;
	private File fich = new File("nomeFicheiros.txt");
	
	public SaveGame(){
		files = new ArrayList<String>();
		readNameFiles();
	}
 
	public ArrayList<String> getFiles(){
		return files;
	}
	

	public void removeFile(String nome){
		files.remove(nome);
		
	}
	 
	public void save(String nome, Jogo j)  {
		File f = new File(nome);
		try {
			FileOutputStream fOut = new FileOutputStream(f);
			ObjectOutputStream out = new ObjectOutputStream(fOut);
			out.writeObject(j);
			out.close();
			fOut.close();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		files.add(nome);
		writeNameFiles();
	}
	

	public Jogo read(String nomeFich){
		Jogo j;
		
		try {
			FileInputStream fIn = new FileInputStream( new File(nomeFich));
			ObjectInputStream in = new ObjectInputStream(fIn);
			j=(Jogo)in.readObject();
			in.close();
			fIn.close();
		} catch (Exception exp) {
			exp.printStackTrace();
			return null;
		}
	
	return j;
	}

	public void readNameFiles(){
		ArrayList<String> files = new ArrayList<String>() ;
		File f = new File("nomeFicheiros.txt");
		String conteudo = null;
		if(!f.exists()){
			 files = new ArrayList<String>();
			 return;
		}
			
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			while((conteudo = br.readLine()) != null)
				files.add(conteudo);
			br.close();
		} catch (Exception exp) {
			exp.printStackTrace();
			return;
		}
		this.files = files;

	}
	
	public void writeNameFiles(){
		
		try {
			FileOutputStream ot = new FileOutputStream(fich);
			OutputStreamWriter o = new OutputStreamWriter(ot);
			for (String nome : files) {
				o.write(nome + "\n");
			}
			o.close();
			ot.close();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
	}
}
