package Conexao;



public class exception extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public exception() {
	}

	public exception(String message) {
		super(message);
	}

	public exception(Throwable throwed) {
		super(throwed);
	}

	public exception(String message, Throwable throwed) {
		super(message, throwed);
	}

}