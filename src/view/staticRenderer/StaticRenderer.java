package view.staticRenderer;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;

import view.renderer.Renderer;

public abstract class StaticRenderer implements Renderer {
    public void drawBase(Graphics g, Shape base)
    {
        g.draw(base);
    }
}
