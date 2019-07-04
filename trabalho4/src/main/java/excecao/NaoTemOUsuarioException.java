package excecao;

public class NaoTemOUsuarioException extends Exception  {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public NaoTemOUsuarioException() {
		super();
    }
    public NaoTemOUsuarioException(String msg) {
		super(msg);
    }
}
