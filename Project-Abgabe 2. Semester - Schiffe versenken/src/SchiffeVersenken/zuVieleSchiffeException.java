package SchiffeVersenken;

public class zuVieleSchiffeException extends Exception{

	public zuVieleSchiffeException() {
		super();
	}

	public zuVieleSchiffeException(String message) {
		super(message);
	}

	public zuVieleSchiffeException(String message, Throwable t) {
		super(message, t);
	}
}
