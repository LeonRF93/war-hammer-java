package mapas;

import recursos.Imagen;

public class Mapa {

	Imagen fondo;
	
	public Mapa(Imagen fondo) {
		this.fondo = fondo;
	}
	
	public void dibujar() {
		this.fondo.dibujar();
	}
	
	public int getAncho() {
		return fondo.ancho;
	}
	
	public int getAlto() {
		return fondo.alto;
	}
	
}
