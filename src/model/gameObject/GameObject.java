package model.gameObject;

import java.io.Serializable;

import org.newdawn.slick.geom.Shape;

public interface GameObject extends Serializable {
    Shape getBase();
    
    boolean collidesWith(Shape s) throws IllegalArgumentException;
    
    boolean collidesWith(GameObject object) throws IllegalArgumentException;
    
    boolean isContains(Shape s) throws IllegalArgumentException;
    
    boolean isContains(GameObject object) throws IllegalArgumentException;
}
