package controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

//import model.Cliente;

public class GerenciarNome extends AbstractAction {
	
	private static final long serialVersionUID = 1L;
//	Cliente nn = new Cliente();
	private String nome;
		
	public GerenciarNome() {
		putValue(NAME, "Printar Nome");
		putValue(SHORT_DESCRIPTION, "Printar nome");
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome= nome;
	}
	
	public GerenciarNome(String nome) {
		this.nome = nome;
		putValue(NAME, "Printar Nome");
		putValue(SHORT_DESCRIPTION, "Printar nome");
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println(nome);
	}
	
}
