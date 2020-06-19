package view;

import java.io.IOException;

import javax.swing.JOptionPane;

import controller.ArquivosController;
import controller.IArquivosController;

public class Principal {

	public static void main(String[] args) {

		IArquivosController arqCont = new ArquivosController();
		String arquivo = "";
		String nome ="";
		String email = "";
		int codigo = -0;
		int op = -0;


		while(op!=-99) {
			op = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite : 1 para verificar a pasta TEMP\n\nDigite : 2 para verificar um registro\n\nDigite : 3 para imprimir um registro\n\nDigite : 4 para inserir um registro\n\nDigite : -99 para sair", "MENU PRINCIPAL", JOptionPane.INFORMATION_MESSAGE));
			try {
				switch(op) {
				case 1:
					arqCont.verificaDirTemp();
					break;
				case 2:
					arquivo = JOptionPane.showInputDialog(null, "Digite o nome do cadastro", "Entrada de texto", JOptionPane.INFORMATION_MESSAGE);
					codigo = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o número do registro", "Entrada de texto", JOptionPane.INFORMATION_MESSAGE));
					System.out.println(arquivo+"&&"+codigo);
					arqCont.verificaRegistro(arquivo, codigo);
					break;
				case 3:
					arquivo = JOptionPane.showInputDialog(null, "Digite o nome do cadastro", "Entrada de texto", JOptionPane.INFORMATION_MESSAGE);
					codigo = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o número do registro", "Entrada de texto", JOptionPane.INFORMATION_MESSAGE));
					arqCont.imprimeCadastro(arquivo, codigo);
					break;
				case 4:
					arquivo = JOptionPane.showInputDialog(null, "Digite o nome do cadastro", "Entrada de texto", JOptionPane.INFORMATION_MESSAGE);
					codigo = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o número do registro", "Entrada de texto", JOptionPane.INFORMATION_MESSAGE));
					arqCont.insereCadastro(arquivo, codigo, nome, email);
					break;
				case -99:
					System.out.println("Saindo!");
					System.exit(0);
					break;
				default:
					System.out.println("Opção inválida! Saindo!");
					System.exit(0);
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
