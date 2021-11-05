package SchiffeVersenken;

public class InvalideRichtungException extends Exception{
	public InvalideRichtungException() {
		super();
	}

	public InvalideRichtungException(String message) {
		super(message);
	}

	public InvalideRichtungException(String message, Throwable t) {
		super(message, t);
	}
}
