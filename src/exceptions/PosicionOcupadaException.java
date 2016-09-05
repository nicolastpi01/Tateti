package exceptions;

@SuppressWarnings("serial")
public class PosicionOcupadaException extends Exception {
	public PosicionOcupadaException() {
		super("POSICION OCUPADA");
	}
}
