package br.com.wedson.aj3.exception;

/*
 * Wedson - "The Developers Company"
 * 
 * 
 */
public class WedsonException extends Exception {

	public WedsonException(String mensagem, Exception e) {
		super(mensagem, e);
	}

	public WedsonException(String mensagem) {
		super(mensagem);
	}
}
