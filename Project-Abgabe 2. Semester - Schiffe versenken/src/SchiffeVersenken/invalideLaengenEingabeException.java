package SchiffeVersenken;

public class invalideLaengenEingabeException extends Exception{
	public invalideLaengenEingabeException() {
		super();
	}

	public invalideLaengenEingabeException(String message) {
		super(message);
	}

	public invalideLaengenEingabeException(String message, Throwable t) {
		super(message, t);
	}
}
