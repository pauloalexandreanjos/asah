package br.org.catolicasc.asah.model.exceptions;

public class MovimentacaoNaoEncontradaException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public MovimentacaoNaoEncontradaException() {
	}

	public MovimentacaoNaoEncontradaException(String message) {
		super(message);
	}

	public MovimentacaoNaoEncontradaException(Throwable cause) {
		super(cause);
	}

	public MovimentacaoNaoEncontradaException(String message, Throwable cause) {
		super(message, cause);
	}

	public MovimentacaoNaoEncontradaException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
