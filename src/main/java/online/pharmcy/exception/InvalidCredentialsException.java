package online.pharmcy.exception;


/**
 * @author PREMKUMAR PAUL
 *
 * INVALID USERNAME AND PASSWORD
 */
public class InvalidCredentialsException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public InvalidCredentialsException(String message) {
		super(message);
	}

}
