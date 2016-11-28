package br.org.catolicasc.asah.model.exceptions;

public class ParcelaNaoEncontradaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ParcelaNaoEncontradaException() {
	}

	public ParcelaNaoEncontradaException(String message) {
		super(message);
	}

	public ParcelaNaoEncontradaException(Throwable cause) {
		super(cause);
	}

	public ParcelaNaoEncontradaException(String message, Throwable cause) {
		super(message, cause);
	}

	public ParcelaNaoEncontradaException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
