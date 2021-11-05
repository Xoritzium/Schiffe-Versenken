package SchiffeVersenken;

public class InvalideEingabeException extends Exception {
	public InvalideEingabeException() {
		super();
	}

	public InvalideEingabeException(String message) {
		super(message);
	}

	public InvalideEingabeException(String message, Throwable t) {
		super(message, t);
	}
}
