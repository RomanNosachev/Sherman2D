package model.staticGameObject;

import model.gameObject.Builder;

public interface StaticGameObjectBuilder extends Builder {
    public void buildObject();
    
    public void buildBase(float[] polygonPoints);
    
    public StaticGameObject getObject();
}
