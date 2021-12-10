package sharedObject;

import javafx.scene.canvas.GraphicsContext;

public interface IRenderable {
	
	public abstract int getZ();
	public abstract void draw(GraphicsContext gc);
	public abstract boolean isVisible();
	
}