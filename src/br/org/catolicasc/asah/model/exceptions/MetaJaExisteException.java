package br.org.catolicasc.asah.model.exceptions;

public class MetaJaExisteException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public MetaJaExisteException() {
	}

	public MetaJaExisteException(String message) {
		super(message);
	}

	public MetaJaExisteException(Throwable cause) {
		super(cause);
	}

	public MetaJaExisteException(String message, Throwable cause) {
		super(message, cause);
	}

	public MetaJaExisteException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
