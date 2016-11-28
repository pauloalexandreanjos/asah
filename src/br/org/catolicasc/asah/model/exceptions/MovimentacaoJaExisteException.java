package br.org.catolicasc.asah.model.exceptions;

public class MovimentacaoJaExisteException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public MovimentacaoJaExisteException() {
	}

	public MovimentacaoJaExisteException(String message) {
		super(message);
	}

	public MovimentacaoJaExisteException(Throwable cause) {
		super(cause);
	}

	public MovimentacaoJaExisteException(String message, Throwable cause) {
		super(message, cause);
	}

	public MovimentacaoJaExisteException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
