package model;

import java.util.List;

public class Cliente {
	private String nome;
	private List<String> opcoes;
	String displayItems = "";
	
	public Cliente(String nome, List<String> opcoes) {
		this.nome = nome;
		this.opcoes = opcoes;
	}
	
	@Override
    public String toString() {
        for (String i : opcoes) { // errado, está printando items repetidos
        	if (!opcoes.contains(i)) {
        		displayItems += i;     		
        	}
        }

        return "nome: " + nome + "\n" + "opções:\n" + displayItems;
    }
}
