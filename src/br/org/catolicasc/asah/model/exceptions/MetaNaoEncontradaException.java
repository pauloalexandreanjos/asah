package br.org.catolicasc.asah.model.exceptions;

public class MetaNaoEncontradaException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public MetaNaoEncontradaException() {
	}

	public MetaNaoEncontradaException(String message) {
		super(message);
	}

	public MetaNaoEncontradaException(Throwable cause) {
		super(cause);
	}

	public MetaNaoEncontradaException(String message, Throwable cause) {
		super(message, cause);
	}

	public MetaNaoEncontradaException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
