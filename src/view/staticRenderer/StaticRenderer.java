package view.staticRenderer;

import model.camera.Camera;
import view.renderer.Renderer;

public abstract class StaticRenderer implements Renderer {
    protected Camera camera;

    public void setCamera(Camera cam)
    {
        camera = cam;
    }
}
