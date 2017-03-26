package controller;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import model.Level;
import model.Move;
import model.PhysicConstants;

public class GameController {
	private Level model;
	
	private boolean gameOver = false;
	
	private static final int CLOCK_PER_SEC = 1000;
	
	public GameController(Level model)
	{
		this.model = model;
	}

	public void init(GameContainer gc) throws SlickException 
	{
		
	}

	public void update(GameContainer gc, int delta) throws SlickException 
	{
		model.setIsMoving(Move.STOP);
		
		keyController(gc, delta);
		
		if (model.isShooting())
		{															
			model.setShellPosition(model.getShellBase().getX() + model.getShotRouteVectorX() * delta / CLOCK_PER_SEC,
								   model.getShellBase().getY() - model.getShotRouteVectorY() * delta / CLOCK_PER_SEC);
			
			model.setShotRouteVectorY(model.getShotRouteVectorY() - PhysicConstants.GRAVITY * delta / CLOCK_PER_SEC);
			
			model.addShotPathPoint(model.getShellBase().getCenterX(), model.getShellBase().getCenterY());
			
			if (model.tankExcludesShell() && !model.isShellLeftTank())
			{
				model.setShellLeftTank(true);
			}
			
			if (model.isShellLeftTank() && model.shellBoundingWithTank())
			{				
				if (model.shellCollidesWithTank())
				{
					setGameOver(true);
				}
			}

			if (model.shellCollidesWithLevel() || !model.levelContainsShell())
			{
				model.setIsShooting(false);
				model.setShellLeftTank(false);
					
				model.setShellPosition(model.getTankBase().getCenterX(), model.getTankBase().getY());

				model.setShellPosition(model.getShellBase().getX() - model.getShotRouteVectorX() * delta / CLOCK_PER_SEC,
						   			   model.getShellBase().getY() + model.getShotRouteVectorY() * delta / CLOCK_PER_SEC);
			}
		}
		else
		{
			model.setShellPosition(model.getTankBase().getCenterX(), model.getTankBase().getY());
		}
	}
	
	private void keyController(GameContainer gc, int delta)
	{
		if (gc.getInput().isKeyPressed(Input.KEY_SPACE))
		{
			if (!model.isShooting())
			{
				model.shot();
			}
		}
		
		if (gc.getInput().isKeyDown(Input.KEY_UP))
		{	
			if (Float.compare(model.getShotStartAngle(), model.getMaxAimingAngle()) < 0)
			{
				model.setShotStartAngle(model.getShotStartAngle() + 45F * delta / CLOCK_PER_SEC);
			}
			else
			{
				model.setShotStartAngle(model.getMaxAimingAngle());
			}
		}
		else if (gc.getInput().isKeyDown(Input.KEY_DOWN))
		{
			if (Float.compare(model.getShotStartAngle(), model.getMinAimingAngle()) > 0)
			{
				model.setShotStartAngle(model.getShotStartAngle() - 45F * delta / CLOCK_PER_SEC);
			}
			else
			{
				model.setShotStartAngle(model.getMinAimingAngle());
			}
		}
		
		if (gc.getInput().isKeyDown(Input.KEY_ADD))
		{
			if (Float.compare(model.getShortStartSpeed(), 5000) < 0)
			{
				model.setShotStartSpeed(model.getShortStartSpeed() + 200F * delta / CLOCK_PER_SEC);
			}
			else
			{
				model.setShotStartSpeed(5000);
			}
		}
		
		if (gc.getInput().isKeyDown(Input.KEY_SUBTRACT))
		{
			if (Float.compare(model.getShortStartSpeed(), 0) > 0)
			{
				model.setShotStartSpeed(model.getShortStartSpeed() - 200F * delta / CLOCK_PER_SEC);
			}
			else
			{
				model.setShotStartSpeed(0);
			}
		}

		if (gc.getInput().isKeyDown(Input.KEY_A))
		{
			model.setIsMoving(Move.BACK);
			
			model.setPositionX(model.getTankBase().getX() - model.getMovePoint() * delta / CLOCK_PER_SEC);
			
			if (model.tankCollidesWithLevel())
			{
				model.setPositionX(model.getTankBase().getX() + model.getMovePoint() * delta / CLOCK_PER_SEC);
			}
		}

		if (gc.getInput().isKeyDown(Input.KEY_D))
		{
			model.setIsMoving(Move.FORTH);
		
			model.setPositionX(model.getTankBase().getX() + model.getMovePoint() * delta / CLOCK_PER_SEC);
			
			if (model.tankCollidesWithLevel())
			{
				model.setPositionX(model.getTankBase().getX() - model.getMovePoint() * delta / CLOCK_PER_SEC);
			}
		}
		
		/*
		if (gc.getInput().isKeyDown(Input.KEY_S))
		{
			model.setPositionY(model.getTankBase().getY() + model.getMovePoint() * delta / CLOCK_PER_SEC);
			
			if (model.tankCollidesWithLevel())
			{
				model.setPositionY(model.getTankBase().getY() - model.getMovePoint() * delta / CLOCK_PER_SEC);
			}
		}
		
		if (gc.getInput().isKeyDown(Input.KEY_W))
		{
			model.setPositionY(model.getTankBase().getY() - model.getMovePoint() * delta / CLOCK_PER_SEC);
			
			if (model.tankCollidesWithLevel())
			{
				model.setPositionY(model.getTankBase().getY() + model.getMovePoint() * delta / CLOCK_PER_SEC);
			}
		}
		*/
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
}
