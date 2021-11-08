package SchiffeVersenken;

public class InvalideSchiffSetPositionExecption extends Exception {
	public InvalideSchiffSetPositionExecption() {
		super();
	}

	public InvalideSchiffSetPositionExecption(String message) {
		super(message);
	}

	public InvalideSchiffSetPositionExecption(String message, Throwable t) {
		super(message, t);
	}
}
