package game;

import org.newdawn.slick.geom.Vector2f;

import model.DynamicGameObject;

public interface DynamicGameObjectBuilder extends Builder {
	public void buildObject();
	public void buildBase(float[] polygonPoints);
	public void buildStartPosition(Vector2f pos);
	public DynamicGameObject getObject();
}
