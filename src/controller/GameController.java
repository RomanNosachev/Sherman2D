package controller;

import java.util.Timer;
import java.util.TimerTask;

import org.newdawn.slick.GameContainer;
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
    
    private Timer            explosiveTimer;
        
    public GameController(Level model)
    {
        this.model = model;
        explosiveTimer = new Timer();
    }
    
    public void mainLoop(GameContainer gc, int delta) throws SlickException
    {                        
        if (model.getTankHitPoint() <= 0)
        {
            setGameOver(true);
        }
                
        if (model.isShooting())
        {
			Vector2f oldVector = new Vector2f(model.getShellCenterX(), model.getShellCenterY());

            model.setShellPosition(model.getShellBase().getX() + model.getShotRouteVectorX() * delta / PhysicConstants.CLOCK_PER_SEC,
                    model.getShellBase().getY() - model.getShotRouteVectorY() * delta / PhysicConstants.CLOCK_PER_SEC);

			Vector2f newVector = new Vector2f(model.getShellCenterX(), model.getShellCenterY());
			float rotateAngle = (float) new Vector2f(newVector.getX() - oldVector.getX(), newVector.getY() - oldVector.getY()).getTheta() + 90;

			model.setShellRotation(rotateAngle);

            model.setShotRouteVectorY(model.getShotRouteVectorY() - PhysicConstants.GRAVITY * delta / PhysicConstants.CLOCK_PER_SEC);

            model.addShotPathPoint(model.getShellBase().getCenterX(), model.getShellBase().getCenterY());

            if (model.tankExcludesShell() && !model.isShellLeftTank())
            {
                model.setShellLeftTank(true);
                model.setTankDamaged(false);
            }

            if (model.isShellLeftTank() && model.shellBoundingWithTank())
            {
                if (model.shellCollidesWithTank() && !model.isTankDamaged())
                {
                    model.setTankDamaged(true);
                    model.setTankHitPoint(model.getTankHitPoint() - model.getShellDamage());
                }
            }

            if (model.shellCollidesWithLevel() || !model.levelContainsShell())
            {
                model.setTankDamaged(false);
                model.setShooting(false);
                model.setShellLeftTank(false);
                model.setShellCollides(true);
                
                model.setShellCollisionPoint(model.getShellPathBack());

                explosiveTimer = new Timer();
                explosiveTimer.schedule(new TimerTask() {
                    @Override
                    public void run()
                    {
                        model.setShellCollides(false);
                    }
                }, 180);
                
				model.setShellRotation(muzzleAngle);

				model.setShellPosition(model.getTankCannonSimpleCenterX() - model.getShellStartWidth() / 2, 
				                       model.getTankCannonSimpleCenterY() - model.getShellStartHeight() / 2);
				
				model.shellRotate(shellCorrectionAngle);
            }
        }       
    }

    public void shot()
    {
        if (!model.isShooting())
        {
            muzzleAngle = model.getShellRotateAngle();
            model.shot();
            shellCorrectionAngle = 0;
        }
    }
    
    public void rotateLeft(int delta)
    {
        float rotateAngle = 45F * delta / PhysicConstants.CLOCK_PER_SEC;
        
        model.tankRotate(-rotateAngle);
        model.tankCannonRotate(-rotateAngle, model.getTankSimpleCenterX(), model.getTankSimpleCenterY());
        model.setShotStartAngle(model.getShotStartAngle() + rotateAngle);
        
        if (model.isShooting())
            shellCorrectionAngle -= rotateAngle;
        else
            model.shellRotate(-rotateAngle, model.getTankSimpleCenterX(),
                    model.getTankSimpleCenterY());
    }
    
    public void rotateRight(int delta)
    {
        float rotateAngle = 45F * delta / PhysicConstants.CLOCK_PER_SEC;
        
        model.tankRotate(rotateAngle);
        model.tankCannonRotate(rotateAngle, model.getTankSimpleCenterX(), model.getTankSimpleCenterY());
        model.setShotStartAngle(model.getShotStartAngle() - rotateAngle);
        
        if (model.isShooting())
            shellCorrectionAngle += rotateAngle;
        else
            model.shellRotate(rotateAngle, model.getTankSimpleCenterX(),
                    model.getTankSimpleCenterY());
    }
    
    public void upGun(int delta)
    {
        float rotateAngle = 45F * delta / PhysicConstants.CLOCK_PER_SEC;
        
        if (Float.compare(model.getShotStartAngle() + model.getTankRotateAngle(),
                model.getMaxAimingAngle()) < 0)
        {
            model.setShotStartAngle(model.getShotStartAngle() + rotateAngle);
            
            model.tankCannonRotate(-rotateAngle, model.getTankCannonRotationPointX(), model.getTankCannonRotationPointY());
            
            if (model.isShooting())
                shellCorrectionAngle -= rotateAngle;
            else
                model.shellRotate(-rotateAngle, model.getTankCannonRotationPointX(), model.getTankCannonRotationPointY());                

        } else
        {
            model.setShotStartAngle(model.getMaxAimingAngle() - model.getTankRotateAngle());
        }
    }
    
    public void downGun(int delta)
    {
        float rotateAngle = 45F * delta / PhysicConstants.CLOCK_PER_SEC;
        
        if (Float.compare(model.getShotStartAngle() + model.getTankRotateAngle(),
                model.getMinAimingAngle()) > 0)
        {
            model.setShotStartAngle(model.getShotStartAngle() - rotateAngle);
            
            model.tankCannonRotate(rotateAngle, model.getTankCannonRotationPointX(), model.getTankCannonRotationPointY());
            
            if (model.isShooting())
                shellCorrectionAngle += rotateAngle;
            else
                model.shellRotate(rotateAngle, model.getTankCannonRotationPointX(), model.getTankCannonRotationPointY());                
        } else
        {
            model.setShotStartAngle(model.getMinAimingAngle() - model.getTankRotateAngle());
        }
    }
    
    public void addShotPower(int delta)
    {
        if (Float.compare(model.getShortStartSpeed(), 5000) < 0)
        {
            model.setShotStartSpeed(model.getShortStartSpeed() + 200F * delta / PhysicConstants.CLOCK_PER_SEC);
        } else
        {
            model.setShotStartSpeed(5000);
        }
    }
    
    public void subShotPower(int delta)
    {
        if (Float.compare(model.getShortStartSpeed(), 0) > 0)
        {
            model.setShotStartSpeed(model.getShortStartSpeed() - 200F * delta / PhysicConstants.CLOCK_PER_SEC);
        } else
        {
            model.setShotStartSpeed(0);
        }
    }
    
    public void moveLeft(int delta)
    {
        model.setIsMoving(Move.BACK);
        
        float movement = model.getMovePoint() * delta / PhysicConstants.CLOCK_PER_SEC;
        
        model.setPositionX(model.getTankX() - movement);
        model.setTankCannonX(model.getTankCannonX() - movement);
        model.setTankCannonRotationPointX(model.getTankCannonRotationPointX() - movement);
        
        if (!model.isShooting())
            model.setShellX(model.getShellBase().getX() - movement);
        
        if (model.tankCollidesWithLevel())
        {
            model.setPositionX(model.getTankBase().getX() + movement);
            model.setTankCannonX(model.getTankCannonX() + movement);
            model.setTankCannonRotationPointX(model.getTankCannonRotationPointX() + movement);
            
            if (!model.isShooting())
                model.setShellX(model.getShellBase().getX() + movement);
        }
    }
    
    public void moveRight(int delta)
    {
        model.setIsMoving(Move.FORTH);
        
        float movement = model.getMovePoint() * delta / PhysicConstants.CLOCK_PER_SEC;

        model.setPositionX(model.getTankX() + movement);
        model.setTankCannonX(model.getTankCannonX() + movement);
        model.setTankCannonRotationPointX(model.getTankCannonRotationPointX() + movement);
        
        if (!model.isShooting())
            model.setShellX(model.getShellBase().getX() + movement);
        
        if (model.tankCollidesWithLevel())
        {                
            model.setPositionX(model.getTankBase().getX() - movement);
            model.setTankCannonX(model.getTankCannonX() - movement);
            model.setTankCannonRotationPointX(model.getTankCannonRotationPointX() - movement);

            if (!model.isShooting())
                model.setShellX(model.getShellBase().getX() - movement);
        }
    }
    
    public void stop()
    {
        model.setIsMoving(Move.STOP);
    }
    
    public void moveUp(int delta)
    {
        float movement = model.getMovePoint() * delta / PhysicConstants.CLOCK_PER_SEC;
        
        model.setPositionY(model.getTankY() - movement);
        model.setTankCannonY(model.getTankCannonY() - movement);
        model.setTankCannonRotationPointY(model.getTankCannonRotationPointY() - movement);
                    
        if (!model.isShooting())
            model.setShellY(model.getShellBase().getY() - movement);
        
        if (model.tankCollidesWithLevel())
        {
            model.setPositionY(model.getTankY() + movement);
            model.setTankCannonY(model.getTankCannonY() + movement);
            model.setTankCannonRotationPointY(model.getTankCannonRotationPointY() + movement);
            
            if (!model.isShooting())
                model.setShellY(model.getShellBase().getY() + movement);
        }
    }
    
    public void moveDown(int delta)
    {
        float movement = model.getMovePoint() * delta / PhysicConstants.CLOCK_PER_SEC;
        
        model.setPositionY(model.getTankY() + movement);
        model.setTankCannonY(model.getTankCannonY() + movement);
        model.setTankCannonRotationPointY(model.getTankCannonRotationPointY() + movement);
        
        if (!model.isShooting())
            model.setShellY(model.getShellBase().getY() + movement);
        
        if (model.tankCollidesWithLevel())
        {
            model.setPositionY(model.getTankBase().getY() - movement);
            model.setTankCannonY(model.getTankCannonY() - movement);
            model.setTankCannonRotationPointY(model.getTankCannonRotationPointY() - movement);
            
            if (!model.isShooting())
                model.setShellY(model.getShellBase().getY() - movement);
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
