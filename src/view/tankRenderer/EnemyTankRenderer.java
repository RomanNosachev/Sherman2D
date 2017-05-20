package view.tankRenderer;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import model.dynamicGameObject.DynamicGameObject;

public class EnemyTankRenderer 
extends TankRenderer
{
    public EnemyTankRenderer(DynamicGameObject object)
    {
        super(object);
    }
    
    public EnemyTankRenderer()
    {
        super();
    }
    
    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException
    {
        super.render(gc, g);
        
        String enemyHP = String.format("%.0f / %.0f", renderingObject.getHitPoints(), renderingObject.getMaxHitPoints());
        g.drawString(enemyHP, renderingObject.getX() + 20, renderingObject.getY() - 20);   
    }
}
