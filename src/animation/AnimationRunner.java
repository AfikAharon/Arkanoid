package animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * a AnimationRunner class.
 * The class is in charge to run the animation lop.
 *
 * @author Afik Aharon.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * Constructor for AnimationRunner class.
     *
     * @param g               the given gui
     * @param s               the given sleeper
     * @param framesPerSecond the given framesPerSecond.
     */
    public AnimationRunner(GUI g, Sleeper s, int framesPerSecond) {
        this.framesPerSecond = framesPerSecond;
        this.gui = g;
        this.sleeper = s;
    }

    /**
     * The function is in charge of run the animation lop.
     *
     * @param animation the given animation class for run.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}