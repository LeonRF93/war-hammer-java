package tropas;

import pantallas.Partida;
import recursos.Entradas;
import recursos.Globales;
import recursos.Imagen;
import recursos.Render;

public class Tropa {

    // Img
    private Imagen imagen;
    private float x, y;

    // Sistema de ataque
    private int cantDados;
    private int heridas;

    // Movimiento
    private boolean enMovimineto;
    private int pulgadasMovimiento = 6;
    private boolean unaVezMovimiento;
    private int xMouse, yMouse;

    private float velocidad = 500f; // Velocidad en píxeles por segundo
    private float distanciaMaximaPorMovimiento = 200f; // Distancia máxima por movimiento
    private float distanciaTotal = 50000f; // Distancia total que la tropa puede recorrer

    private float dx = 0;
    private float dy = 0;
    private double distance = 0f;
    private double directionX = 0f;
    private double directionY = 0f;

    public Tropa(Imagen imagen, int heridas, int cantDados) {
        this.imagen = imagen;
        this.imagen.setSize(50, 50);
        this.heridas = heridas;
        this.cantDados = cantDados;
    }

    public void dibujar() {
        imagen.dibujar();
        System.out.println("X: "+x+"Y: "+y);
    }

    public void mover(int mapaAncho, int mapaAlto) {
    	
    	// evitar q la tropa se vaya fuera del mapa
    	if(x < 0 || y < 0 || x > mapaAncho || y > mapaAlto) {
    		enMovimineto = false;
    	}
    	
    	if(x < 0) {
    		x  = 0;
    	}
    	if(y < 0) {
    		y = 0;
    	}
    	
    	if(x > mapaAncho) {
    		x = mapaAncho;
    	}
    	if(y > mapaAlto) {
    		y = mapaAlto;
    	}

    	// Hacer click
        if (Entradas.getBotonMouse() == 0 && (x >= 0 || x >= mapaAncho  && y <= 0 || y <= mapaAlto)) {
        	
        	
            // Verificar si aún hay distancia total disponible para moverse
            if (distanciaTotal <= 0) {
            	System.out.println("Distancia maxima recorrida");
                return; // Si no hay distancia restante, no se puede mover
            }
        	
            if (!unaVezMovimiento) {

                // Ajustar las coordenadas del mouse según la posición de la cámara
            	xMouse = Entradas.getMouseX() + (int) (Globales.camara.position.x - Globales.camara.viewportWidth / 2);
            	yMouse = Entradas.getMouseY() + (int) (Globales.camara.position.y - Globales.camara.viewportHeight / 2);

                // Calcular la dirección del movimiento
                dx = xMouse - x;
                dy = yMouse - y;
                distance = Math.sqrt(dx * dx + dy * dy);

                // Si la distancia al destino es mayor que la distancia máxima permitida
                if (distance > distanciaMaximaPorMovimiento) {
                    // Normalizar el vector de dirección
                    directionX = dx / distance;
                    directionY = dy / distance;

                    // Ajustar la posición objetivo para que esté a la distancia máxima permitida
                    xMouse = (int) (x + directionX * distanciaMaximaPorMovimiento);
                    yMouse = (int) (y + directionY * distanciaMaximaPorMovimiento);
                    distance = distanciaMaximaPorMovimiento;
                }

                // Verificar si la distancia solicitada supera la distancia total restante
                if (distance > distanciaTotal) {
                    // Ajustar la posición objetivo para que esté a la distancia restante permitida
                    directionX = dx / distance;
                    directionY = dy / distance;

                    xMouse = (int) (x + directionX * distanciaTotal);
                    yMouse = (int) (y + directionY * distanciaTotal);
                    distance = distanciaTotal; // La nueva distancia es igual a la distancia restante
                }

                enMovimineto = true;
                unaVezMovimiento = true;
            }
        }

        if (enMovimineto && distanciaTotal > 0) {
        	
        	System.out.println("Distancia restante: "+distanciaTotal);
        	
            // Calcular la dirección del movimiento
            dx = xMouse - x;
            dy = yMouse - y;
            distance = Math.sqrt(dx * dx + dy * dy);

            if (distance > 1) {
                // Normalizar el vector de dirección
                directionX = dx / distance;
                directionY = dy / distance;

                // Calcular el desplazamiento basado en la velocidad y deltaTime
                float deltaTime = Render.getDeltaTime();
                float moveAmount = velocidad * deltaTime;

                // Si la distancia restante es menor que el movimiento que se va a realizar,
                // entonces nos aseguramos de no sobrepasar el objetivo.
                if (moveAmount > distance) {
                    x = xMouse;
                    y = yMouse;
                    enMovimineto = false; // Movimiento terminado
                } else {
                    // Mover en la dirección calculada
                    x += directionX * moveAmount;
                    y += directionY * moveAmount;
                }

                // Restar la distancia recorrida de la distancia total disponible
                distanciaTotal -= moveAmount;

                // Actualizar la posición de la imagen
                imagen.setPosition(x, y);
            } else {
                enMovimineto = false; // Movimiento terminado
            }
        }
        
        if(Entradas.getBotonMouse() == -1 && !enMovimineto) {
        	unaVezMovimiento = false;
        }
        
    }

    public int getAncho() {
    	return imagen.ancho;
    }
    
    public float getX() {
		return x;
	}
    
    public float getY() {
		return y;
	}
    
    public void dispose() {
        imagen.dispose();
    }
}
