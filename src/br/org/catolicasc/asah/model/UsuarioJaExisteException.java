package br.org.catolicasc.asah.model;

public class UsuarioJaExisteException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UsuarioJaExisteException() {
	}

	public UsuarioJaExisteException(String message) {
		super(message);
	}

	public UsuarioJaExisteException(Throwable cause) {
		super(cause);
	}

	public UsuarioJaExisteException(String message, Throwable cause) {
		super(message, cause);
	}

	public UsuarioJaExisteException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
