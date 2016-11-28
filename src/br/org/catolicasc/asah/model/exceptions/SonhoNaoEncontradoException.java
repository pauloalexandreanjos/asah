package br.org.catolicasc.asah.model.exceptions;

public class SonhoNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SonhoNaoEncontradoException() {
	}

	public SonhoNaoEncontradoException(String message) {
		super(message);
	}

	public SonhoNaoEncontradoException(Throwable cause) {
		super(cause);
	}

	public SonhoNaoEncontradoException(String message, Throwable cause) {
		super(message, cause);
	}

	public SonhoNaoEncontradoException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}