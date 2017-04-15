package view.renderer;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public interface Renderer {
    public void init(GameContainer gc) throws SlickException;
    
    public void render(GameContainer gc, Graphics g) throws SlickException;
    
    public void setSprite(Image sprite);
}
