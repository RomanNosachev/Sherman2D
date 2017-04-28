package controller;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

public class KeyController {
    private GameController controller;
    
    public KeyController(GameController controller)
    {
        this.controller = controller;
    }
    
    public void inputHandle(GameContainer gc, int delta)
    {
        if (gc.getInput().isKeyPressed(Input.KEY_SPACE))
            controller.shot();
            
        if (gc.getInput().isKeyDown(Input.KEY_RIGHT))
            controller.rotateRight(delta);
        
        if (gc.getInput().isKeyDown(Input.KEY_LEFT))
            controller.rotateLeft(delta);

        if (gc.getInput().isKeyDown(Input.KEY_UP))
            controller.upGun(delta);
        
        if (gc.getInput().isKeyDown(Input.KEY_DOWN))
            controller.downGun(delta);
        
        if (gc.getInput().isKeyDown(Input.KEY_ADD))
            controller.addShotPower(delta);
        
        if (gc.getInput().isKeyDown(Input.KEY_SUBTRACT))
            controller.subShotPower(delta);
        
        if (gc.getInput().isKeyDown(Input.KEY_D) != gc.getInput().isKeyDown(Input.KEY_A))
        {
            if (gc.getInput().isKeyDown(Input.KEY_D))
                controller.moveForth(delta);
            else 
                controller.moveBack(delta);
        }
        else 
        {
            controller.stop();
        }
        
        if (gc.getInput().isKeyDown(Input.KEY_S))
            controller.moveDown(delta);
        
        if (gc.getInput().isKeyDown(Input.KEY_W))
            controller.moveUp(delta);
    }
}
