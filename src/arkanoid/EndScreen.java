package arkanoid;

import animation.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import core.Counter;
import useful.MagN;

import java.awt.Color;

/**
 * a EndScreen class, the class is in charge of draw the end screen:
 * win or lost.
 *
 * @author Afik Aharon.
 */
public class EndScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private Counter score;
    private String endString;

    /**
     * Constructor for EndScreen class.
     *
     * @param k         the KeyboardSensor
     * @param endString the end screen message
     * @param score     the score game
     */
    public EndScreen(KeyboardSensor k, String endString, Counter score) {
        this.keyboard = k;
        this.stop = false;
        this.score = score;
        this.endString = endString;
    }

    /**
     * The function is in charge for draw the end screen.
     * @param d the given DrawSurface.
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.decode("#67489E"));
        d.fillRectangle(0, 0, MagN.GUI_WIDTH, MagN.GUI_HEIGHT);
        d.setColor(Color.decode("#7CD21A"));
        d.drawText(200, d.getHeight() / 2, this.endString + this.score.getValue(), MagN.SIZE_SCREEN_TEXT);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) { this.stop = true; }
    }

    /**
     * The function is in charge of stopping condition animation lop.
     *
     * @return boolean true for stop the animation lop and false for continue.
     */
    public boolean shouldStop() { return this.stop; }
}
