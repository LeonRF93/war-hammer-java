package pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;

import kronk.lol.wh.PrincipalWh;
import mapas.Mapa;
import recursos.Entradas;
import recursos.Imagen;
import recursos.Render;
import tropas.Momo;
import tropas.Tropa;

public class Partida implements Screen{

	private PrincipalWh principal;
	private ScreenManager screenMg;
	
	// Objetos
	private Tropa momo;
	private Mapa mapa;
	
	// Img
	private Imagen fondo;
	
	// Camara
	private OrthographicCamera camara;
	
	// Input
	private Entradas entradas = new Entradas(this);
	
	public Partida(PrincipalWh principal) {
		this.principal = principal;
		screenMg = new ScreenManager(principal);
	}
	
	@Override
	public void show() {
		
		Gdx.input.setInputProcessor(entradas);
		
		fondo = new Imagen("img/background/fondo.png", Render.ANCHO * 2, Render.ALTO * 2);
		mapa = new Mapa(fondo);
		
		camara = new OrthographicCamera(Render.ANCHO, Render.ALTO);
		camara.position.set(Render.ANCHO / 2, Render.ALTO / 2, 0);
		
		camara.zoom = 1.5f;
		
		momo = new Momo();
		
	}

	@Override
	public void render(float delta) {
		
		camara.update();
		Render.batch.setProjectionMatrix(camara.combined);
		Render.batch.begin();
		
		momo.mover();
		mapa.dibujar();
		momo.dibujar();
		
		Render.batch.end();
		
	}

    public void zoomIn() {
        if (camara.zoom > 0.1f) { // Limitar el zoom mínimo
            camara.zoom -= 0.02f; // Ajusta la velocidad de zoom según necesites
        }
    }

    // Función para alejar la cámara (hacer zoom out)
    public void zoomOut() {
        camara.zoom += 0.02f; // Ajusta la velocidad de zoom según necesites
    }
	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		fondo.dispose();
		momo.dispose();
		
	}

}
