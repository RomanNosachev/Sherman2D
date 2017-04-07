package game;

import model.StaticGameObject;

public interface StaticGameObjectBuilder extends Builder{
	public void buildObject();
	public void buildBase(float[] polygonPoints);
	public StaticGameObject getObject();
}
