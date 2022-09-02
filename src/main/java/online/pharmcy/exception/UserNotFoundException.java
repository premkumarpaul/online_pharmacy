package online.pharmcy.exception;




/**
 * @author PREMKUMAR PAUL
 *
 * USER NOT FOUND EXCEPTION
 */
public class UserNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public UserNotFoundException(String message) {
		super(message);
	}

}