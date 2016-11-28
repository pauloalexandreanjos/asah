package br.org.catolicasc.asah.model.exceptions;

public class RendaNaoEncontradaException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public RendaNaoEncontradaException() {

	}

	public RendaNaoEncontradaException(String message) {
		super(message);

	}

	public RendaNaoEncontradaException(Throwable cause) {
		super(cause);

	}

	public RendaNaoEncontradaException(String message, Throwable cause) {
		super(message, cause);

	}

	//Construtor JDK 1.7
	public RendaNaoEncontradaException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}
}
