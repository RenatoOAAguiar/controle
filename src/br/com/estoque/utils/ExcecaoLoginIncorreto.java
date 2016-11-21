package br.com.estoque.utils;

public class ExcecaoLoginIncorreto extends Exception{
	
		private static final long serialVersionUID = 1L;

		@Override
		public String getMessage(){
			return "Usuário ou senha inválidos! \nDigite novamente!";
		}

	
}
