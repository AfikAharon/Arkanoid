package animation;

import biuoop.DrawSurface;

/**
 * a Animation interface.
 *
 * @author Afik Aharon.
 */
public interface Animation {
    /**
     * The function is in charge of draw the surface in every class that implement
     * of Animation interface.
     * @param d the given DrawSurface.
     */
    void doOneFrame(DrawSurface d);

    /**
     * The function is in charge of stopping condition for the animation lop.
     *
     * @return boolean true for stop the animation lop and false for continue.
     */
    boolean shouldStop();
}