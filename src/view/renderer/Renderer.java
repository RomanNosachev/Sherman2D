package view.renderer;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import model.camera.Camera;

public interface Renderer {    
    public void render(GameContainer gc, Graphics g) throws SlickException;
    
    public void setSprite(Image sprite);
    public void setCamera(Camera camera);
}
