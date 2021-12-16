package gui;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
/**
 * SpriteAnimation extends Transition class is used to make something moving with its own time.
 * @author Mos
 *
 */
public class SpriteAnimation extends Transition {
	/**
	 * ImageView (JavaFx) that might contain one frame or more of an animation this class will create.
	 */
    private final ImageView imageView;
    /**
     * count. (I do not know honestly)
     */
    private final int count;
    /**
     * Columns is like a number of frames there will be in our animation
     */
    private final int columns;
    /**
     * horizontal offset
     */
    private final int offsetX;
    /**
     * vertical offset
     */
    private final int offsetY;
    /**
     * Width of one frame.
     */
    private final int width;
    /**
     * Height of animation we want it to be.
     */
    private final int height;
    /**
     * Last index of the frame.
     */
    private int lastIndex;
    /**
     * Constructor for SpriteAnimation class, we need to call this constructor and put the ImageView object that we want to make it move.
     * @param imageView
     * @param duration
     * @param count
     * @param columns
     * @param offsetX
     * @param offsetY
     * @param width
     * @param height
     */
    public SpriteAnimation(
            ImageView imageView, 
            Duration duration, 
            int count,   int columns,
            int offsetX, int offsetY,
            int width,   int height) {
        this.imageView = imageView;
        this.count     = count;
        this.columns   = columns;
        this.offsetX   = offsetX;
        this.offsetY   = offsetY;
        this.width     = width;
        this.height    = height;
        setCycleDuration(duration);
        setInterpolator(Interpolator.LINEAR);
    }
    /**
     * interpolate is a method that need to be override (this class is extended from Transition class)
     * @param k
     */
    protected void interpolate(double k) {
        final int index = Math.min((int) Math.floor(k * count), count - 1);
        if (index != lastIndex) {
            final int x = (index % columns) * width  + offsetX;
            final int y = (index / columns) * height + offsetY;
            imageView.setViewport(new Rectangle2D(x, y, width, height));
            lastIndex = index;
        }
    }
}