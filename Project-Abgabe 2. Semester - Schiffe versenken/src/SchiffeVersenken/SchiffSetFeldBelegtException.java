package SchiffeVersenken;

public class SchiffSetFeldBelegtException extends Exception{
	public SchiffSetFeldBelegtException() {
		super();
	}

	public SchiffSetFeldBelegtException(String message) {
		super(message);
	}

	public SchiffSetFeldBelegtException(String message, Throwable t) {
		super(message, t);

	}
}
