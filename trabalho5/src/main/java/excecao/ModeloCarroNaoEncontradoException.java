package excecao;

public class ModeloCarroNaoEncontradoException extends Exception{
	private final static long serialVersionUID = 1;

    public ModeloCarroNaoEncontradoException() {
	super();
    }

    public ModeloCarroNaoEncontradoException(String msg) {
	super(msg);
    }
}
