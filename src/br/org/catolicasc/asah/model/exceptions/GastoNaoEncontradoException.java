package br.org.catolicasc.asah.model.exceptions;

public class GastoNaoEncontradoException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public GastoNaoEncontradoException() {
	}

	public GastoNaoEncontradoException(String message) {
		super(message);
	}

	public GastoNaoEncontradoException(Throwable cause) {
		super(cause);
	}

	public GastoNaoEncontradoException(String message, Throwable cause) {
		super(message, cause);
	}

	public GastoNaoEncontradoException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
