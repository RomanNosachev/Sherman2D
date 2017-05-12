package model.dynamicGameObject;

import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;

import model.gameObject.GameObject;
import model.staticGameObject.StaticGameObject;

@SuppressWarnings("serial")
public abstract class DynamicGameObject 
implements GameObject,
           Cloneable
{
    protected Shape         base;
    protected Shape         simpleBase;
    
    protected float         scale = 1F;
    
    protected Vector2f      startPosition;
    protected float         startWidth;
    protected float         startHeight;
    
    protected float         boundingRadius;
    protected float         rotateAngle = 0;
    
    protected float         hitPoint;
    protected float         maxHitPoint;
    
    protected Direction     moving = Direction.STOP;
    protected Direction     direction = Direction.FORTH;
    protected Climb         climbing = Climb.STRAIGHT;
    
    protected volatile boolean  collides = false;
    
    public Shape getSimpleBase()
    {
        return simpleBase;
    }
    
    public float getStartWidth()
    {
        return startWidth;
    }
    
    public float getStartHeight()
    {
        return startHeight;
    }
    
    public float getRotateAngle()
    {
        return rotateAngle;
    }
    
    public void setRotateAngle(float rotateAngle)
    {
        this.rotateAngle = rotateAngle;
    }
    
    public void setStartPosition(Vector2f pos) throws IllegalArgumentException
    {
        startPosition = pos;
        simpleBase.setLocation(simpleBase.getX() - (base.getX() - pos.x), simpleBase.getY() - (base.getY() - pos.y));
        base.setLocation(pos.x, pos.y);        
    }

    public Vector2f getPosition()
    {
        return base.getLocation();
    }
    
    public Vector2f getStartPosition()
    {
        return startPosition;
    }
    
    public float getStartX()
    {
        return startPosition.x;
    }
    
    public float getStartY()
    {
        return startPosition.y;
    }
    
    public void setPosition(Vector2f pos) throws IllegalArgumentException
    {
        simpleBase.setLocation(simpleBase.getX() - (base.getX() - pos.x), simpleBase.getY() - (base.getY() - pos.y));      
        base.setLocation(pos.x, pos.y);
    }
    
    public void setPosition(float x, float y)
    {
        simpleBase.setLocation(simpleBase.getX() - (base.getX() - x), simpleBase.getY() - (base.getY() - y));
        base.setLocation(x, y);
    }
    
    public float[] getNormal(int index)
    {
        return base.getNormal(index);
    }
    
    public float[] getPoint(int index)
    {
        return base.getPoint(index);
    }
    
    public boolean includes(float x, float y)
    {
        return base.includes(x, y);
    }
    
    public int indexOf(float x, float y)
    {
        return base.indexOf(x, y);
    }
    
    public void setX(float x)
    {        
        simpleBase.setX(simpleBase.getX() - (base.getX() - x));
        base.setX(x);
    }
    
    public void setY(float y)
    {
        simpleBase.setY(simpleBase.getY() - (base.getY() - y));
        base.setY(y);
    }
    
    public void rotate(float angle)
    {
        rotateAngle += angle;
        base = new Polygon(base.transform(Transform.createRotateTransform(angle * (float) Math.PI / 180F,
                simpleBase.getCenterX(), simpleBase.getCenterY())).getPoints());
        simpleBase = new Polygon(simpleBase.transform(Transform.createRotateTransform(angle * (float) Math.PI / 180F,
                simpleBase.getCenterX(), simpleBase.getCenterY())).getPoints());
    }
    
    public void rotate(float angle, float x, float y)
    {
        rotateAngle += angle;
        base = new Polygon(
                base.transform(Transform.createRotateTransform(angle * (float) Math.PI / 180F, x, y)).getPoints());
        simpleBase = new Polygon(
                simpleBase.transform(Transform.createRotateTransform(angle * (float) Math.PI / 180F, x, y)).getPoints());
    }
    
    @Override
    public boolean collidesWith(GameObject object) throws IllegalArgumentException
    {
        return base.intersects(object.getBase());
    }
    
    @Override
    public boolean collidesWith(Shape s) throws IllegalArgumentException
    {
        return base.intersects(s);
    }

    @Override
    public boolean isContains(GameObject object) throws IllegalArgumentException
    {
        return base.contains(object.getBase());
    }
    
    @Override
    public boolean isContains(Shape s) throws IllegalArgumentException
    {
        return base.contains(s);
    }
    
    public boolean boundingWith(Shape s) throws IllegalArgumentException
    {
        return getBoundingCircle().intersects(s) || getBoundingCircle().contains(s);
    }
    
    public boolean boundingWith(DynamicGameObject object) throws IllegalArgumentException
    {
        return getBoundingCircle().intersects(object.getBoundingCircle())
                || getBoundingCircle().contains(object.getBoundingCircle());
    }
    
    public Shape getBase()
    {
        return base;
    }
    
    public Shape getBoundingCircle()
    {
        return new Ellipse(base.getCenterX(), base.getCenterY(), boundingRadius, boundingRadius);
    }
    
    public void setBase(Shape base) throws IllegalArgumentException
    {
        this.base = base;
        boundingRadius = base.getBoundingCircleRadius();
        simpleBase = new Rectangle(base.getX(), base.getY(), base.getWidth(), base.getHeight());
        startWidth = simpleBase.getWidth();
        startHeight = simpleBase.getHeight();
    }
    
    public float getBoundingCircleRadius()
    {
        return base.getBoundingCircleRadius();
    }
    
    public float getX()
    {
        return base.getX();
    }
    
    public float getY()
    {
        return base.getY();
    }
    
    public float getSimpleCenterX()
    {
        return simpleBase.getCenterX();
    }
    
    public float getSimpleCenterY()
    {
        return simpleBase.getCenterY();
    }
    
    public float getCenterX()
    {
        return base.getCenterX();
    }
    
    public float getCenterY()
    {
        return base.getCenterY();
    }
    
    public float getMinX()
    {
        return base.getMinX();
    }
    
    public float getMinY()
    {
        return base.getMinY();
    }
    
    public float getMaxX()
    {
        return base.getMaxX();
    }
    
    public float getMaxY()
    {
        return base.getMaxY();
    }
    
    public float getHeight()
    {
        return base.getHeight();
    }
    
    public float getWidth()
    {
        return base.getWidth();
    }
    
    public float getHitPoint()
    {
        return hitPoint;
    }

    public void setHitPoint(float f)
    {
        this.hitPoint = f;
    }

    public float getMaxHitPoint()
    {
        return maxHitPoint;
    }

    public void setMaxHitPoint(int maxHitPoint)
    {
        this.maxHitPoint = maxHitPoint;
        hitPoint = maxHitPoint;
    }
    
    public StaticGameObject toStaticGameObject()
    {
        return new StaticGameObject(base);
    }
    
    public void setScale(float scale)
    {
        this.scale = scale;
        
        Vector2f pos = base.getLocation();
        base.setLocation(0, 0);
        base = base.transform(Transform.createScaleTransform(scale, scale));
        base.setLocation(pos);        
    }
    
    public float getScale()
    {
        return scale;
    }
    
    public void moveX(float movement)
    {
        base.setX(base.getX() + movement);
        simpleBase.setX(simpleBase.getX() + movement);
    }
    
    public void moveY(float movement)
    {
        base.setY(base.getY() + movement);
        simpleBase.setY(simpleBase.getY() + movement);
    }
    
    public void reverse(boolean horizontal, boolean vertical)
    {
        if (horizontal)
        {
            float prevRotateAngle = rotateAngle;
            
            rotate(-getRotateAngle());
            
            float[] polygonPoints = new float[base.getPointCount() * 2];
            
            for (int i = 0, j = 0; j < base.getPointCount(); i+=2, j++)
            {
                if (base.getPoint(j)[0] > simpleBase.getCenterX())
                {
                    polygonPoints[i] = simpleBase.getCenterX() - Math.abs(base.getPoint(j)[0] - simpleBase.getCenterX());
                }
                else
                {
                    if (base.getPoint(j)[0] < simpleBase.getCenterX())
                    {
                        polygonPoints[i] = simpleBase.getCenterX() + Math.abs(base.getPoint(j)[0] - simpleBase.getCenterX());
                    }
                    else
                    {
                        polygonPoints[i] = base.getPoint(j)[0];
                    }
                }
                
                polygonPoints[i + 1] = base.getPoint(j)[1];
            }
            
            base = new Polygon(polygonPoints);
            
            rotate(prevRotateAngle);
        }
    }
    
    public boolean isCollides()
    {
        return collides;
    }

    public void setCollides(boolean collides)
    {
        this.collides = collides;
    }
    
    public Direction isMoving()
    {
        return moving;
    }

    public Direction getDirection()
    {
        return direction;
    }
    
    public void setMoving(Direction fl)
    {
        moving = fl;
        
        if (fl != Direction.STOP)
            direction = fl;
    }

    public Climb isClimbing()
    {
        return climbing;
    }

    public void setClimbing(Climb climbing)
    {
        this.climbing = climbing;
    }
    
    
}
