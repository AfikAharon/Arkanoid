package arkanoid;

import animation.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import useful.MagN;

import java.awt.Color;

/**
 * a PauseScreen class is in charge of pause the screen.
 *
 * @author Afik Aharon.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * Constructor for PauseScreen class.
     *
     * @param k the KeyboardSensor game.
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    /**
     * The function check if the user press the space KeyboardSensor (if yes change the boolean
     * member to true for return the game) and draw the pause screen.
     *
     * @param d the given DrawSurface.
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.decode("#67489E"));
        d.fillRectangle(0, 0, MagN.GUI_WIDTH, MagN.GUI_HEIGHT);
        d.setColor(Color.decode("#7CD21A"));
        d.drawText(180, d.getHeight() / 2, MagN.PAUSE_MESSAGE, 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    /**
     * The function is in charge of stopping condition animation lop.
     *
     * @return boolean true for stop the animation lop and false for continue.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}