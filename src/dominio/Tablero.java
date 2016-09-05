package dominio;

import java.util.ArrayList;
import java.util.List;

import exceptions.ElTableroDebeTenerLasMismasDimensionesYMinimoTamanhoTres;
import exceptions.PosicionOcupadaException;
import exceptions.SalioDelTableroException;

public class Tablero {
	private Pieza[][] posiciones;
	private int base;
	private int altura;
	private List<Pieza> piezasValidas;
	
	public Tablero(int base, int altura, ArrayList<Pieza> piezasValidas) throws ElTableroDebeTenerLasMismasDimensionesYMinimoTamanhoTres {
		if(base >= 3 && base == altura) {
			this.posiciones = new Pieza[base][altura];
			this.base = base;
			this.altura = altura;
			this.piezasValidas = piezasValidas;
		}
		else throw new ElTableroDebeTenerLasMismasDimensionesYMinimoTamanhoTres();
	}
	
	public void agregarPiezaValida(Pieza p) {
		this.piezasValidas.add(p);
	}
	
	public boolean estaOcupada(int base, int altura) {
		return posiciones[base][altura] != null;
	}
	
	public boolean salioDelTablero(int base, int altura) {
		return base >= this.base || altura >= this.altura;
	}
	
	public boolean puedeRealizarMovimiento(int base, int altura, Pieza p) {
		return !salioDelTablero(base, altura) && !estaOcupada(base, altura);
	}
	 
	public void ponerPieza(int base, int altura, Pieza p) throws SalioDelTableroException, PosicionOcupadaException {
		if(puedeRealizarMovimiento(base, altura, p)) posiciones[base][altura] = p;
		else if(salioDelTablero(base, altura)) throw new SalioDelTableroException();
		else if (estaOcupada(base, altura)) throw new PosicionOcupadaException();
	}
	
	public Pieza getEnPos(int base, int altura) {
		return posiciones[base][altura];
	}
	
	public boolean gano(int base, int altura, Pieza p) {
		return ganoCruzado(p) || ganoEnRecta(base, altura, p);
	}
	
	public boolean ganoCruzado(Pieza p) {
		boolean ret = true;
		for(int i = 0; i < altura; i++) {
			ret = ret && (this.getEnPos(i, i).getTipo() == p.getTipo());
		}
			return ret;
	}
	
	public boolean ganoEnRecta(int base, int altura, Pieza p) {
		return ganoEnVertical(altura, p) || ganoEnHorizontal(base, p);
	}
	
	public boolean ganoEnHorizontal(int base, Pieza p) {
		boolean ret = true;
		for(int i = 0; i < this.base; i++) {
			ret = ret && (this.getEnPos(base, i).getTipo() == p.getTipo());
		}
			return ret;
	}
	
	public boolean ganoEnVertical(int altura, Pieza p) {
		boolean ret = true;
		for(int i = 0; i < this.altura; i++) {
			ret = ret && (this.getEnPos(i, altura).getTipo() == p.getTipo());
		}
			return ret;
	}
	
	public boolean empate() {
		return empateEnRecta() || empateEnCruzado();	
	}
	
	public boolean empateEnRecta() {
		return empateEnVertical() || empateEnHorizontal();
	}
	
	public boolean empateEnVertical() {
		return true;
	}
	
	public boolean empateEnHorizontal() {
		return true;
	}
	
	public boolean empateEnCruzado() {
		boolean ret = false;
		Pieza p;
		p = this.getEnPos(0, 0);
		for(int i = 0; i < altura; i++) {
			if(p != null) ret = ret || (this.getEnPos(i, i).getTipo() != p.getTipo());
			else p = this.getEnPos(i+1, i+1);
		}
			return ret;
	}
	
}