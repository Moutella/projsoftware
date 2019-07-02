package excecao;

public class BairroNaoEncontradoException extends Exception{
	private final static long serialVersionUID = 1;

    public BairroNaoEncontradoException() {
	super();
    }

    public BairroNaoEncontradoException(String msg) {
	super(msg);
    }
}