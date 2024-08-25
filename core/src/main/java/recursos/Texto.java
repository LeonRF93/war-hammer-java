package recursos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class Texto {

    BitmapFont fuente;
    FreeTypeFontGenerator generador;
    FreeTypeFontGenerator.FreeTypeFontParameter parametros;
    public int x=0, y=0;
    public String texto = "";

    public Texto(String rutaFuente, int escala, Color color, boolean sombra) {
        generador = new FreeTypeFontGenerator(Gdx.files.internal(rutaFuente));
        parametros = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parametros.size = escala;
        parametros.color = color;
        if (sombra) {
            parametros.shadowColor = Color.BLACK;
            parametros.shadowOffsetX = 5;
            parametros.shadowOffsetY = 5;
        }
        fuente = generador.generateFont(parametros);
    }

    public void dibujar() {
        fuente.draw(Render.batch, texto, x, y);
    }

    public void modificarEscala(int nuevaEscala) {
        parametros.size = nuevaEscala;
        fuente.dispose();  // Liberar la fuente anterior para evitar fugas de memoria
        fuente = generador.generateFont(parametros);
    }

    public void dispose() {
        fuente.dispose();
        generador.dispose();
    }
}
