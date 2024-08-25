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
	
}
