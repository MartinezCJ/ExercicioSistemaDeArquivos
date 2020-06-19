package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class ArquivosController implements IArquivosController{

	@Override
	public void verificaDirTemp() throws IOException {
		String path = "C:\\TEMP";
		File dir = new File(path);
		boolean check = false;
		if (dir.exists() && dir.isDirectory()) {
			System.out.println("<DIR>\t"+dir.getName());
			System.out.println("Diretório já existe.");
		}else {
			check = dir.mkdir();
			System.out.println("Criado com sucesso? "+check);
			System.out.println("<DIR>\t"+dir.getName());
		}
	}

	@Override
	public boolean verificaRegistro(String arquivo, int codigo) throws IOException {
		String path = "C:\\TEMP";
		path += ("\\"+arquivo+".csv");
		System.out.println(path);
		File file = new File(path);
		if (file.exists() && file.isFile()) {
			System.out.println("Lendo csv");
			String separador = ";";
			FileInputStream  fluxo = new FileInputStream(file);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			while (linha != null) {
				//System.out.println(linha);
				String[] cod = linha.split(separador);
				//System.out.println(cod[0]);
				int i = Integer.parseInt(cod[0]);
				if(i==codigo) {
					buffer.close();
					leitor.close();
					fluxo.close();
					System.out.println("Registro válido");
					return true;
				}
				linha = buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();
			System.out.println("Registro inválido");
			return false;
		}else {
			System.out.println("Arquivo não encontrado");
			return false;
		}
	}

	@Override
	public void imprimeCadastro(String arquivo, int codigo) throws IOException {
		if(verificaRegistro(arquivo, codigo)) {
			String path = "C:\\TEMP";
			path += ("\\"+arquivo+".csv");
			File file = new File(path);
			String separador = ";";
			FileInputStream  fluxo = new FileInputStream(file);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			while (linha != null) {
				//System.out.println(linha);
				String[] cod = linha.split(separador);
				//System.out.println(cod[0]);
				int i = Integer.parseInt(cod[0]);
				if(i==codigo) {
					System.out.println("\nCódigo: "+cod[0]+"\nNome: "+cod[1]+"\nE-mail: "+cod[2]);
				}
				linha = buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();
		}
	}

	@Override
	public void insereCadastro(String arquivo, int codigo, String nome, String email) throws IOException {
		if(!verificaRegistro(arquivo, codigo)) {
			String path = "C:\\TEMP";
			path += ("\\"+arquivo+".csv");
			System.out.println(path);
			File file = new File(path);
			if (file.exists() && file.isFile()) {
				boolean existe = true;
				nome = JOptionPane.showInputDialog(null, "Digite o nome do cadastro", "Entrada de texto", JOptionPane.INFORMATION_MESSAGE);
				email = JOptionPane.showInputDialog(null, "Digite o e-mail do cadastro", "Entrada de texto", JOptionPane.INFORMATION_MESSAGE);
				String conteudo = geraTxt(codigo, nome, email);
				FileWriter fileWriter = new FileWriter(file, existe);
				PrintWriter print = new PrintWriter(fileWriter);
				print.write(conteudo);
				print.flush();
				print.close();
				fileWriter.close();
			}
		}
	}

	private String geraTxt(int codigo, String nome, String email) {
		StringBuffer buffer = new StringBuffer();
		String linha = codigo+";"+nome+";"+email;
		buffer.append(linha + "\r\n");		
		return buffer.toString();
	}

}
