package br.org.catolicasc.asah.model.exceptions;

public class SonhoJaExisteException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SonhoJaExisteException() {
	}

	public SonhoJaExisteException(String message) {
		super(message);
	}

	public SonhoJaExisteException(Throwable cause) {
		super(cause);
	}

	public SonhoJaExisteException(String message, Throwable cause) {
		super(message, cause);
	}

	public SonhoJaExisteException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
