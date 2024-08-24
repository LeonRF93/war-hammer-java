package pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;

import kronk.lol.wh.PrincipalWh;
import recursos.Entradas;
import recursos.Imagen;
import recursos.Render;
import tropas.Tropa;

public class Partida implements Screen{

	private PrincipalWh principal;
	private ScreenManager screenMg;
	
	private Tropa bana;
	private Imagen banaimg;
	
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
		
		fondo = new Imagen("img/background/fondo.png", Render.ANCHO, Render.ALTO);
		
		camara = new OrthographicCamera(Render.ANCHO, Render.ALTO);
		camara.position.set(Render.ANCHO / 2, Render.ALTO / 2, 0);
		
		banaimg = new Imagen("img/pjs/ban.jpg", Render.ANCHO, Render.ALTO);
		bana = new Tropa(banaimg, 3, 3);
		
	}

	@Override
	public void render(float delta) {
		
		camara.update();
		Render.batch.setProjectionMatrix(camara.combined);
		Render.batch.begin();
		
		bana.mover();
		fondo.dibujar();
		bana.dibujar();
		
		Render.batch.end();
		
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
		bana.dispose();
		
	}

}
