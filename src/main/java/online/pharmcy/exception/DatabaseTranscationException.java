package online.pharmcy.exception;


/**
 * @author PREMKUMAR PAUL
  
   
 * IF THE DATA IS NOT FOUND IN THE DATABASE EXCEPTION
 */
public class DatabaseTranscationException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public DatabaseTranscationException(String message) {
		super(message);
	}

}