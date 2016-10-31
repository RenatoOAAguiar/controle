package br.com.estoque.utils;

public class ExcecaoCampoVazio extends Exception{
	
		private static final long serialVersionUID = 1L;

		@Override
		public String getMessage(){
			return "Campos obrigatórios não foram preenchidos!";
		}

	
}
