package controller;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import model.PhysicConstants;
import model.level.Level;
import model.tank.Climb;
import model.tank.Move;

public class GameController 
{
    private Level               model;
    
    private GravityController   gravityController;
    
    private boolean             gameOver = false;
        
    public GameController(Level model)
    {
        this.model = model;
        gravityController = new GravityController(model); 
    }
    
    public void mainLoop(GameContainer gc, int delta) throws SlickException
    {                         
        System.out.println(model.isTankCollides());

        gravityController.gravity(delta);
                
        if (model.getTankHitPoint() <= 0)
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
            if (model.getEnemyHitPoint(enemyIndex) <= 0)
            {
                model.removeEnemies(enemyIndex);
                enemyIndex--;
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
                        model.addTankHitPoint(-model.getShellDamage(shellIndex));
                        
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
                        
                        model.addEnemyHitPoint(enemyIndex, -model.getShellDamage(shellIndex));
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
        if (model.getTankDirection() == Move.FORTH)
            model.tankReverse(true, false);
        
        float movement = model.getMovePoint() * delta / PhysicConstants.CLOCK_PER_SEC;
                        
        for (int i = 0; i < movement; i++)
        {                
            model.moveTankX(-1);
            
            if (model.tankCollidesWithLevel() || model.tankCollidesWithEnemies())
            {
                model.moveTankX(1);
                                        
                model.setClimbing(Climb.UP);
                model.moveTankY(-1);
                        
                if (model.tankCollidesWithLevel() || model.tankCollidesWithEnemies())
                {
                    model.setClimbing(Climb.STRAIGHT);
                    model.moveTankY(1);
                }
            }
            else
            {
                model.setMoving(Move.BACK);
            }
        } 
    }
    
    public void moveForth(int delta)
    {        
        if (model.getTankDirection() == Move.BACK)
            model.tankReverse(true, false);
        
        float movement = model.getMovePoint() * delta / PhysicConstants.CLOCK_PER_SEC;
        
        for (int i = 0; i < movement; i++)
        {                
            model.moveTankX(1);
            
            if (model.tankCollidesWithLevel() || model.tankCollidesWithEnemies())
            {
                model.moveTankX(-1);
                                        
                model.setClimbing(Climb.UP);
                model.moveTankY(-1);
                        
                if (model.tankCollidesWithLevel() || model.tankCollidesWithEnemies())
                {
                    model.setClimbing(Climb.STRAIGHT);
                    model.moveTankY(1);
                }
            }
            else
            {
                model.setMoving(Move.FORTH);
            }
        }
    }
    
    public void stop()
    {
        model.setMoving(Move.STOP);
        model.setClimbing(Climb.STRAIGHT);
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
