package kronk.lol.wh;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import pantallas.ScreenManager;
import recursos.Render;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class PrincipalWh extends Game {
    private SpriteBatch batch;
    private Texture image;
    private ScreenManager screenMg;

    @Override
    public void create() {
		Render.batch = new SpriteBatch();
		screenMg = new ScreenManager(this);
		screenMg.setPartida();
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
    }
}
