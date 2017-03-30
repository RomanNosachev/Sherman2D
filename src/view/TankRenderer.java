package view;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import model.Move;
import model.Tank;

public class TankRenderer extends DynamicRenderer{
	private Tank renderingObject;

	private Animation 	animation;
	private int 		sheetCount;
	private int 		selectSheet;
		
	public TankRenderer(Tank rObject) 
	{
		renderingObject = rObject;
	}
	
	public void setSprite(Image sprite, int count)
	{
		animation = new Animation(new SpriteSheet(sprite, sprite.getWidth() / count, sprite.getHeight()), 1);
		sheetCount = count;
		selectSheet = count / 2;
	}
	
	@Override
	public void init(GameContainer gc) throws SlickException 
	{
		boundingRadius = renderingObject.getBoundingCircleRadius();
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException 
	{
		drawBase(g, renderingObject.getBase());
		drawBoundingSphere(g, renderingObject);
		drawAnimation(g);
	}
	
	public void drawAnimation(Graphics g)
	{				
		if (renderingObject.isMoving() == Move.BACK)
			if (++selectSheet >= sheetCount)
				selectSheet = 0;
		
		if (renderingObject.isMoving() == Move.FORTH)
			if (--selectSheet <= 0)
				selectSheet = sheetCount - 1;
		
		Image sprite = animation.getImage(selectSheet);
		//sprite.setCenterOfRotation(renderingObject.getCenterX(), renderingObject.getCenterY());
		sprite.setRotation(renderingObject.getRotateAngle());
		
	//	g.drawImage(sprite, renderingObject.getX(), renderingObject.getY());

		g.drawImage(sprite, renderingObject.getCenterX() - sprite.getWidth() / 2,
							renderingObject.getCenterY() - sprite.getHeight() / 2);	
		
		System.out.println(renderingObject.getCenterX() + " " + renderingObject.getCenterY() + "    " + sprite.getWidth() + " " + sprite.getHeight());
	}
}
