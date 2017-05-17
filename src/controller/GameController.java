package controller;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import model.PhysicConstants;
import model.dynamicGameObject.stateEnum.Climb;
import model.dynamicGameObject.stateEnum.Direction;
import model.level.Level;

public class GameController 
{
    private Level               model;
    
    private GravityController   gravityController;
    private EnemyController     enemyController;
    
    private boolean             gameOver = false;
        
    public GameController(Level model)
    {
        this.model = model;
        gravityController = new GravityController(model); 
        enemyController = new EnemyController(model);
    }
    
    public void mainLoop(GameContainer gc, int delta) throws SlickException
    {                                       
        gravityController.gravity(delta);
        enemyController.control(delta);
                        
        if (model.getTankHitPoints() <= 0)
        {
            setGameOver(true);
        }
        
        if (model.getTankRotateAngle() > 135 || model.getTankRotateAngle() < -135)
        {
            setGameOver(true);
        }
        
        for (int objectIndex = 0; objectIndex < model.getObjectsCount(); objectIndex++)
        {
            if (model.tankCollidesWithObject(objectIndex))
            {
                model.removeObject(objectIndex);
                objectIndex--;
            }
        }
        
        for (int enemyIndex = 0; enemyIndex < model.getEnemiesCount(); enemyIndex++)
        {
            if (model.getEnemyHitPoints(enemyIndex) <= 0)
            {
                model.removeEnemies(enemyIndex);
                
                continue;
            }
            
            if (model.tankCollidesWithEnemy(enemyIndex))
            {
                model.setTankCollides(true);
                
                if (model.isTankCollides())
                {
                    //model.damageTank(model.getEnemyMeleeDamage(enemyIndex));
                    //model.damageEnemy(enemyIndex, model.getTankMeleeDamage());
                }
                
            }
            else
            {
                model.setTankCollides(false);
            } 
        }
                        
        for (int shellIndex = 0; shellIndex < model.getShellCount() - 1; shellIndex++)
        {            
            if (model.isShellFlying(shellIndex))
            {
                Vector2f oldVector = new Vector2f(model.getShellCenterX(shellIndex), model.getShellCenterY(shellIndex));

                model.setShellPosition(shellIndex, model.getShellBase(shellIndex).getX() + model.getShotDirectionX(shellIndex) * delta / PhysicConstants.CLOCK_PER_SEC,
                        model.getShellBase(shellIndex).getY() - model.getShotDirectionY(shellIndex) * delta / PhysicConstants.CLOCK_PER_SEC);

                Vector2f newVector = new Vector2f(model.getShellCenterX(shellIndex), model.getShellCenterY(shellIndex));
                float rotateAngle = (float) new Vector2f(newVector.getX() - oldVector.getX(), newVector.getY() - oldVector.getY()).getTheta() + 90;
                
                model.setShellRotation(shellIndex, rotateAngle);

                model.addShotPathPoint(shellIndex, model.getShellBase(shellIndex).getCenterX(), model.getShellBase(shellIndex).getCenterY());

                if (model.tankExcludesShell(shellIndex) && !model.isShellLeftTank())
                {
                    model.setShellLeftTank(true);
                    model.setTankDamaged(false);
                }

                if (model.isShellLeftTank() && model.shellBoundingWithTank(shellIndex))
                {
                    if (model.shellCollidesWithTank(shellIndex) && !model.isTankDamaged())
                    {
                        model.setTankDamaged(true);
                        model.damageTank(model.getShellDamage(shellIndex));
                        
                        continue;
                    }
                }

                if (model.shellCollidesWithLevel(shellIndex) || !model.levelContainsShell(shellIndex))
                {
                    model.setTankDamaged(false);
                    model.setShellFlying(shellIndex, false);
                    model.setShellLeftTank(false);
                    model.setShellCollides(shellIndex, true);
                    model.setShellCollisionPoint(shellIndex, model.getShellPathBack(shellIndex));    
                    
                    continue;
                }
                
                for (int objectIndex = 0; objectIndex < model.getObjectsCount(); objectIndex++)
                {                    
                    if (model.shellCollidesWithObject(shellIndex, objectIndex))
                    {
                        model.setShellCollides(shellIndex, true);
                        model.setShellFlying(shellIndex, false);
                        
                        model.setShellCollisionPoint(shellIndex, model.getShellPathBack(shellIndex));
                        model.removeObject(objectIndex);
                    }
                }
                
                for (int enemyIndex = 0; enemyIndex < model.getEnemiesCount(); enemyIndex++)
                {
                    if (model.shellCollidesWithEnemy(shellIndex, enemyIndex))
                    {
                        model.setShellCollides(shellIndex, true);
                        model.setShellFlying(shellIndex, false);
                        model.setShellCollisionPoint(shellIndex, model.getShellPathBack(shellIndex));
                        
                        model.damageEnemy(enemyIndex, model.getShellDamage(shellIndex));
                    }
                }
            }
            else
            {
                model.removeShell(shellIndex);
                shellIndex--;
            }
        }
    }

    public void shot()
    {
        model.shot();
    }

    public void upGun(int delta)
    {        
        model.upTankCannon(45F * delta / PhysicConstants.CLOCK_PER_SEC);
    }
    
    public void downGun(int delta)
    {
        model.upTankCannon(-45F * delta / PhysicConstants.CLOCK_PER_SEC);
    }
    
    public void addShotPower(int delta)
    {
        model.addTankShotPower(200F * delta / PhysicConstants.CLOCK_PER_SEC);
    }
    
    public void subShotPower(int delta)
    {
        model.addTankShotPower(-200F * delta / PhysicConstants.CLOCK_PER_SEC);
    }
    
    public void moveBack(int delta)
    {        
        model.moveTank(-model.getMovePoints() * delta / PhysicConstants.CLOCK_PER_SEC);
    }
    
    public void moveForth(int delta)
    {        
        model.moveTank(model.getMovePoints() * delta / PhysicConstants.CLOCK_PER_SEC);
    }
    
    public void stop()
    {
        model.setTankMoving(Direction.STOP);
        model.setTankClimbing(Climb.STRAIGHT);
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
