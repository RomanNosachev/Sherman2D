package view.tankRenderer;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import model.dynamicGameObject.stateEnum.Direction;
import model.tank.Tank;
import view.renderer.Player;

public class TankPlayer implements Player {
    private Tank                playingObject;
    
    private Sound               move;
    private ArrayList<Sound>    hit;
    private boolean             hitPlaying = false;
    private int                 hitPlayingIndex = 0;

    public TankPlayer(Tank object)
    {
        playingObject = object;
    }
    
    public void setHit(ArrayList<Sound> hit)
    {
        this.hit = hit;
    }
    
    public void setMove(Sound move)
    {
        this.move = move;
    }

    @Override
    public void play(GameContainer gc) throws SlickException
    {
        if (playingObject.getMoving() != Direction.STOP)
        {
            if (!move.playing())
                move.play();
        }
        else 
        {            
            move.stop();
        }
                        
        if (!hit.get(hitPlayingIndex).playing())
        {
            hitPlaying = false;
        }
        
        if (playingObject.isDamaged())
        {                        
            if (!hitPlaying)
            {
                hitPlaying = true;
                Random rand = new Random();
                hitPlayingIndex = rand.nextInt(hit.size());

                hit.get(hitPlayingIndex).play();
            }
        }
    }
}
