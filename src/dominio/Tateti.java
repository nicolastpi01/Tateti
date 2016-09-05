package dominio;

import java.util.ArrayList;
import exceptions.ElTableroDebeTenerLasMismasDimensionesYMinimoTamanhoTres;
import exceptions.PosicionOcupadaException;
import exceptions.SalioDelTableroException;

public class Tateti {
	private Tablero tablero;
	
	public Tateti(int alto, int ancho, ArrayList<Pieza> piezas) throws ElTableroDebeTenerLasMismasDimensionesYMinimoTamanhoTres {
		tablero = new Tablero(alto, ancho, piezas);
	}
	
	public void jugar(int alto, int ancho, Pieza p) throws SalioDelTableroException, PosicionOcupadaException {
		tablero.ponerPieza(alto, ancho, p);
	}

}
