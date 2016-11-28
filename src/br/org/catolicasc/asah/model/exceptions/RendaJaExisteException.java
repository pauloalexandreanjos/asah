package br.org.catolicasc.asah.model.exceptions;

public class RendaJaExisteException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public RendaJaExisteException() {
	}

	public RendaJaExisteException(String message) {
		super(message);
	}

	public RendaJaExisteException(Throwable cause) {
		super(cause);
	}

	public RendaJaExisteException(String message, Throwable cause) {
		super(message, cause);
	}

	public RendaJaExisteException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
