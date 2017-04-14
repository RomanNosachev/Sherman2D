package controller;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import model.PhysicConstants;
import model.level.Level;
import model.tank.Move;

public class GameController {
    private Level            model;
    
    private boolean          gameOver      = false;
    private float            shellCorrectionAngle;
    private float            muzzleAngle;
    
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
			Vector2f oldVector = new Vector2f(model.getShellCenterX(), model.getShellCenterY());

            model.setShellPosition(model.getShellBase().getX() + model.getShotRouteVectorX() * delta / CLOCK_PER_SEC,
                    model.getShellBase().getY() - model.getShotRouteVectorY() * delta / CLOCK_PER_SEC);

			Vector2f newVector = new Vector2f(model.getShellCenterX(), model.getShellCenterY());
			float rotateAngle = (float) new Vector2f(newVector.getX() - oldVector.getX(), newVector.getY() - oldVector.getY()).getTheta() + 90;

			model.setShellRotation(rotateAngle);

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
				model.setShellRotation(muzzleAngle);

                model.setShellPosition(
                        model.getTankStartPositionX() + (model.getTankSimpleCenterX() - model.getTankStartWidth() / 2 - model.getTankStartPositionX())
                                - (model.getTankStartPositionX() - model.getShellStartPositionX()),
                        model.getTankStartPositionY() + (model.getTankSimpleCenterY() - model.getTankStartHeight() / 2 - model.getTankStartPositionY())
                                - (model.getTankStartPositionY() - model.getShellStartPositionY()));

                model.shellRotate(-model.getTankRotateAngle() + shellCorrectionAngle);
                model.shellRotate(model.getTankRotateAngle(),
                        model.getTankCenterX(), model.getTankCenterY());
            }
        }
    }
    
    private void keyController(GameContainer gc, int delta)
    {
        if (gc.getInput().isKeyPressed(Input.KEY_SPACE))
        {
            if (!model.isShooting())
            {
            	muzzleAngle = model.getShellRotateAngle();
                model.shot();
                shellCorrectionAngle = 0;
            }
        }
        
        if (gc.getInput().isKeyDown(Input.KEY_RIGHT))
        {
            float rotateAngle = 45F * delta / CLOCK_PER_SEC;
            
            model.tankRotate(rotateAngle);
            model.setShotStartAngle(model.getShotStartAngle() - rotateAngle);
            
            if (model.isShooting())
                shellCorrectionAngle += rotateAngle;
            else
                model.shellRotate(rotateAngle, model.getTankSimpleCenterX(),
                        model.getTankSimpleCenterY());
        }
        
        if (gc.getInput().isKeyDown(Input.KEY_LEFT))
        {
            float rotateAngle = 45F * delta / CLOCK_PER_SEC;
            
            model.tankRotate(-rotateAngle);
            model.setShotStartAngle(model.getShotStartAngle() + rotateAngle);
            
            if (model.isShooting())
                shellCorrectionAngle -= rotateAngle;
            else
                model.shellRotate(-rotateAngle, model.getTankSimpleCenterX(),
                        model.getTankSimpleCenterY());
        }
        
        if (gc.getInput().isKeyDown(Input.KEY_UP))
        {
            float rotateAngle = 45F * delta / CLOCK_PER_SEC;
            
            if (Float.compare(model.getShotStartAngle() + model.getTankRotateAngle(),
                    model.getMinAimingAngle()) > 0)
            {
                model.setShotStartAngle(model.getShotStartAngle() - rotateAngle);
                
                if (model.isShooting())
                    shellCorrectionAngle += rotateAngle;
                else
                    model.shellRotate(rotateAngle);
            } else
            {
                model.setShotStartAngle(model.getMinAimingAngle() - model.getTankRotateAngle());
            }
            
        } else if (gc.getInput().isKeyDown(Input.KEY_DOWN))
        {
            float rotateAngle = 45F * delta / CLOCK_PER_SEC;
            
            if (Float.compare(model.getShotStartAngle() + model.getTankRotateAngle(),
                    model.getMaxAimingAngle()) < 0)
            {
                model.setShotStartAngle(model.getShotStartAngle() + rotateAngle);
                
                if (model.isShooting())
                    shellCorrectionAngle -= rotateAngle;
                else
                    model.shellRotate(-rotateAngle);
            } else
            {
                model.setShotStartAngle(model.getMaxAimingAngle() - model.getTankRotateAngle());
            }
        }
        
        if (gc.getInput().isKeyDown(Input.KEY_ADD))
        {
            if (Float.compare(model.getShortStartSpeed(), 5000) < 0)
            {
                model.setShotStartSpeed(model.getShortStartSpeed() + 200F * delta / CLOCK_PER_SEC);
            } else
            {
                model.setShotStartSpeed(5000);
            }
        }
        
        if (gc.getInput().isKeyDown(Input.KEY_SUBTRACT))
        {
            if (Float.compare(model.getShortStartSpeed(), 0) > 0)
            {
                model.setShotStartSpeed(model.getShortStartSpeed() - 200F * delta / CLOCK_PER_SEC);
            } else
            {
                model.setShotStartSpeed(0);
            }
        }
        
        if (gc.getInput().isKeyDown(Input.KEY_A))
        {
            model.setIsMoving(Move.BACK);
            
            model.setPositionX(model.getTankBase().getX() - model.getMovePoint() * delta / CLOCK_PER_SEC);
            
            if (!model.isShooting())
                model.setShellX(model.getShellBase().getX() - model.getMovePoint() * delta / CLOCK_PER_SEC);
            
            if (model.tankCollidesWithLevel())
            {
                model.setPositionX(model.getTankBase().getX() + model.getMovePoint() * delta / CLOCK_PER_SEC);
                
                if (!model.isShooting())
                    model.setShellX(model.getShellBase().getX() + model.getMovePoint() * delta / CLOCK_PER_SEC);
            }
        }
        
        if (gc.getInput().isKeyDown(Input.KEY_D))
        {
            model.setIsMoving(Move.FORTH);
            
            model.setPositionX(model.getTankBase().getX() + model.getMovePoint() * delta / CLOCK_PER_SEC);
            
            if (!model.isShooting())
                model.setShellX(model.getShellBase().getX() + model.getMovePoint() * delta / CLOCK_PER_SEC);
            
            if (model.tankCollidesWithLevel())
            {                
                model.setPositionX(model.getTankBase().getX() - model.getMovePoint() * delta / CLOCK_PER_SEC);
                
                if (!model.isShooting())
                    model.setShellX(model.getShellBase().getX() - model.getMovePoint() * delta / CLOCK_PER_SEC);
            }
        }
        
        if (gc.getInput().isKeyDown(Input.KEY_S))
        {
            model.setPositionY(model.getTankBase().getY() + model.getMovePoint() * delta / CLOCK_PER_SEC);
            
            if (!model.isShooting())
                model.setShellY(model.getShellBase().getY() + model.getMovePoint() * delta / CLOCK_PER_SEC);
            
            if (model.tankCollidesWithLevel())
            {
                model.setPositionY(model.getTankBase().getY() - model.getMovePoint() * delta / CLOCK_PER_SEC);
                
                if (!model.isShooting())
                    model.setShellY(model.getShellBase().getY() - model.getMovePoint() * delta / CLOCK_PER_SEC);
            }
        }
        
        if (gc.getInput().isKeyDown(Input.KEY_W))
        {
            model.setPositionY(model.getTankBase().getY() - model.getMovePoint() * delta / CLOCK_PER_SEC);
            
            if (!model.isShooting())
                model.setShellY(model.getShellBase().getY() - model.getMovePoint() * delta / CLOCK_PER_SEC);
            
            if (model.tankCollidesWithLevel())
            {
                model.setPositionY(model.getTankBase().getY() + model.getMovePoint() * delta / CLOCK_PER_SEC);
                
                if (!model.isShooting())
                    model.setShellY(model.getShellBase().getY() + model.getMovePoint() * delta / CLOCK_PER_SEC);
            }
        }
    }
    
    public boolean isGameOver()
    {
        return gameOver;
    }
    
    public void setGameOver(boolean gameOver)
    {
        this.gameOver = gameOver;
    }
}
