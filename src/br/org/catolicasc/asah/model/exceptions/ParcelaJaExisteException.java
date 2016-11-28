package br.org.catolicasc.asah.model.exceptions;

public class ParcelaJaExisteException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ParcelaJaExisteException() {
	}

	public ParcelaJaExisteException(String message) {
		super(message);
	}

	public ParcelaJaExisteException(Throwable cause) {
		super(cause);
	}

	public ParcelaJaExisteException(String message, Throwable cause) {
		super(message, cause);
	}

	public ParcelaJaExisteException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
