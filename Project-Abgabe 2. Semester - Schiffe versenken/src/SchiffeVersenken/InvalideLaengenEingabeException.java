package SchiffeVersenken;

public class InvalideLaengenEingabeException extends Exception{
	public InvalideLaengenEingabeException() {
		super();
	}

	public InvalideLaengenEingabeException(String message) {
		super(message);
	}

	public InvalideLaengenEingabeException(String message, Throwable t) {
		super(message, t);
	}
}
