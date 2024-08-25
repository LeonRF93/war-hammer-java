package pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;

import kronk.lol.wh.PrincipalWh;
import mapas.Mapa;
import recursos.Entradas;
import recursos.Globales;
import recursos.Imagen;
import recursos.Render;
import recursos.Texto;
import tropas.Momo;
import tropas.Tropa;

public class Partida implements Screen{

	private PrincipalWh principal;
	private ScreenManager screenMg;
	
	// Objetos
	private Tropa momo;
	private Mapa mapa;
	private Texto texto;
	
	// Img
	private Imagen fondo;
	
	// Camara
	private OrthographicCamera camaraHud;
	public static float zoom = 1f;
	
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
		
		Globales.camara = new OrthographicCamera(Render.ANCHO , Render.ALTO);
		Globales.camara.position.set(Render.ANCHO / 2 , Render.ALTO/ 2 , 0);
		zoomear();
		zoom = Globales.camara.zoom;
		
		camaraHud = new OrthographicCamera(Render.ANCHO, Render.ALTO);
		camaraHud.position.set(Render.ANCHO / 2, Render.ALTO / 2, 0);
		
		momo = new Momo();
		
		texto = new Texto("fonts/Minecraft.ttf", 20, Color.WHITE, false);
		texto.x = 20;
		texto.y = 20;
		
	}

	@Override
	public void render(float delta) {
		
		Render.limpiarPantalla(0, 0, 0, 1);
		
		Globales.camara.update();
		Globales.camara.position.set(momo.getX(), momo.getY(), 0f);
		Render.batch.setProjectionMatrix(Globales.camara.combined);
		Render.batch.begin();
		
		momo.mover(mapa.getAncho(), mapa.getAlto());
		mapa.dibujar();
		momo.dibujar();
		
		System.out.println("Ancho de tropa"+momo.getAncho());
		
		Render.batch.end();
		
		//Hud
//		camaraHud.update();
//		Render.batch.setProjectionMatrix(camaraHud.combined);
//		Render.batch.begin();
//
//		mostraCoordenadas(Entradas.getMouseX(), Entradas.getMouseY());
//		
//		Render.batch.end();
		
	}

	public void mostraCoordenadas(int x, int y) {
		texto.texto = ("X:"+ x+" Y: "+y);
		texto.dibujar();
	}
	
	public void zoomear() {
		Globales.camara.zoom = 1.5f;
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
