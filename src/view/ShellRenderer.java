package view;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Line;

import model.Shell;

public class ShellRenderer extends DynamicRenderer {
	private Shell renderingObject;
	
	private Image texture;
	private float prevRotate;
	
	public ShellRenderer(Shell rObject) 
	{
		renderingObject = rObject;
	}
	
	public void setTexture(Image texture)
	{
		this.texture = texture;
		this.texture.rotate(90);
	}
	
	@Override
	public void init(GameContainer gc) throws SlickException 
	{		
		prevRotate = renderingObject.getStartAngle();
		boundingRadius = renderingObject.getBoundingCircleRadius();
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException 
	{
		drawShotParameters(g);
		drawPath(g);
		drawBase(g, renderingObject.getBase());
		drawBoundingSphere(g, renderingObject);
		drawTexture(g);
	}
	
	public void drawTexture(Graphics g)
	{
		g.drawImage(texture, renderingObject.getBase().getX(), renderingObject.getBase().getY());
		texture.rotate(prevRotate - renderingObject.getStartAngle());
		prevRotate = renderingObject.getStartAngle();
	}
	
	public void drawShotParameters(Graphics g)
	{
		g.setColor(Color.white);
		g.drawString("Speed: " + Float.toString(renderingObject.getStartSpeed()), 20, Display.getHeight() - 30);
		g.drawString("Angle: " + Float.toString(renderingObject.getStartAngle()), 190, Display.getHeight() - 30);
	}
	
	public void drawPath(Graphics g)
	{
		g.setColor(Color.red);
		
		for (int i = 0; i < renderingObject.getPathSize() - 1; i++)
		{
			g.draw(new Line(renderingObject.getPathPoint(i).getX(),  
							renderingObject.getPathPoint(i).getY(),
							renderingObject.getPathPoint(i + 1).getX(),
							renderingObject.getPathPoint(i + 1).getY()));
		}
	}
}
