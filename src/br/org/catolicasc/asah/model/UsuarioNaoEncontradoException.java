package br.org.catolicasc.asah.model;

public class UsuarioNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UsuarioNaoEncontradoException() {

	}

	public UsuarioNaoEncontradoException(String message) {
		super(message);

	}

	public UsuarioNaoEncontradoException(Throwable cause) {
		super(cause);

	}

	public UsuarioNaoEncontradoException(String message, Throwable cause) {
		super(message, cause);

	}

	//Construtor JDK 1.7
	public UsuarioNaoEncontradoException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

}
