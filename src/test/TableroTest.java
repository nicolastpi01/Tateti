package test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import dominio.Pieza;
import dominio.Tablero;
import exceptions.ElTableroDebeTenerLasMismasDimensionesYMinimoTamanhoTres;
import exceptions.PosicionOcupadaException;
import exceptions.SalioDelTableroException;

public class TableroTest {
	Tablero tablero;
	Pieza pieza0;

	@Before
	public void setUp() throws ElTableroDebeTenerLasMismasDimensionesYMinimoTamanhoTres {
		tablero = new Tablero(3, 3, new ArrayList<Pieza>());
		pieza0 = new Pieza("0");
	}
	@Test
	public void estOcupadaTest() throws SalioDelTableroException, PosicionOcupadaException {
		tablero.ponerPieza(1, 1, pieza0);
		assertTrue(tablero.estaOcupada(1, 1));
		assertFalse(tablero.estaOcupada(0, 0));
	}
	
	@Test
	public void salioDelTableroTest() {
		assertTrue(tablero.salioDelTablero(4, 3));
		assertTrue(tablero.salioDelTablero(3, 4));
		assertTrue(tablero.salioDelTablero(3, 3));
		assertFalse(tablero.salioDelTablero(2, 2));
	}
	
	@Test
	public void puedeRealizarMovimientoTest() throws SalioDelTableroException, PosicionOcupadaException {
		assertFalse(tablero.puedeRealizarMovimiento(3, 3, pieza0));
		assertFalse(tablero.puedeRealizarMovimiento(4, 3, pieza0));
		assertFalse(tablero.puedeRealizarMovimiento(3, 4, pieza0));
		tablero.ponerPieza(1, 1, pieza0);
		assertFalse(tablero.puedeRealizarMovimiento(1, 1, pieza0));
		assertTrue(tablero.puedeRealizarMovimiento(2, 2, pieza0));
		assertTrue(tablero.puedeRealizarMovimiento(1, 2, pieza0));
	}
	
	@Test
	public void ponerPiezaTest() throws SalioDelTableroException, PosicionOcupadaException {
		tablero.ponerPieza(2, 2, pieza0);
		assertNotEquals(tablero.getEnPos(2, 2), null);
		tablero.ponerPieza(1, 1, pieza0);
		assertNotEquals(tablero.getEnPos(1, 1), null);
	}
	
	@Test
	public void ganoCruzadoTest() throws SalioDelTableroException, PosicionOcupadaException {
		tablero.ponerPieza(0, 0, pieza0);
		tablero.ponerPieza(1, 1, pieza0);
		tablero.ponerPieza(2, 2, pieza0);
		assertTrue(tablero.ganoCruzado(pieza0));
	}
	
	@Test
	public void ganoEnHorizontal() throws SalioDelTableroException, PosicionOcupadaException {
		tablero.ponerPieza(2, 0, pieza0);
		tablero.ponerPieza(2, 1, pieza0);
		tablero.ponerPieza(2, 2, pieza0);
		assertTrue(tablero.ganoEnHorizontal(2, pieza0));
	}
	
	@Test
	public void ganoEnVertical() throws SalioDelTableroException, PosicionOcupadaException {
		tablero.ponerPieza(0, 1, pieza0);
		tablero.ponerPieza(1, 1, pieza0);
		tablero.ponerPieza(2, 1, pieza0);
		assertTrue(tablero.ganoEnVertical(1, pieza0));
	}
	
	@Test
	public void empato() {
		assertTrue(true);
	}

}
