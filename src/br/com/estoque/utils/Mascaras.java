package br.com.estoque.utils;

public class Mascaras {
	
	public String mascaraCPF(String cpf){
		
		String formatada = cpf.substring(0, 3) + "."+
							cpf.substring(3,6) + "."+
							cpf.substring(6,8) + "-"+
							cpf.substring(8, 11);
		
		return formatada;
	}

}
