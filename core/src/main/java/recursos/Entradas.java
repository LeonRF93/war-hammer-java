package recursos;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;

import pantallas.Partida;

public class Entradas implements InputProcessor {

	private static int mouseX = 0, mouseY = 0;
	private static int botonMouse = -1;
	private static boolean teclaPausa;

	Screen app;

	public Entradas(Screen app) {
		this.app = app;
	}

	@Override
	public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.ESCAPE || keycode == Input.Keys.SPACE) {
            System.out.println("escape pulsado");
        }
        return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		botonMouse = button;
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		botonMouse = -1;
		return false;
	}

	@Override
	public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		mouseX = screenX;
		mouseY = screenY;
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		// TODO Auto-generated method stub
		return false;
	}

	public static int getMouseX() {
		return mouseX;
	}

	public static int getMouseY() {
		return Render.ALTO - mouseY;
	}

	public static int getBotonMouse() {
		return botonMouse;
	}
	
	public static boolean getTeclaPausa() {
		return teclaPausa;
	}
}
