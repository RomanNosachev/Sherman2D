package view;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

import model.DynamicGameObject;

public abstract class DynamicRenderer implements Renderer{
	protected float 	boundingRadius;
	
	public abstract void init(GameContainer gc) throws SlickException;
	public abstract void render(GameContainer gc, Graphics g) throws SlickException;
	
	public void drawBoundingSphere(Graphics g, DynamicGameObject object)
	{
		g.setColor(Color.lightGray);
		g.draw(object.getBoundingCircle());
	}
	
	public void drawBase(Graphics g, Shape base)
	{
		g.setColor(Color.darkGray);
		g.draw(base);
	}
}
