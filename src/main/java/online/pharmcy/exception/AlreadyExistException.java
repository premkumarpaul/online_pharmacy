package online.pharmcy.exception;


/**
 * @author PREMKUMAR PAUL
 *
 * USER ALREADY PRESENT EXCEPTION
 */
public class AlreadyExistException extends RuntimeException { 
	private static final long serialVersionUID = 1L; 
	public AlreadyExistException(String message) { 
		super(message); 
	} 

} 
