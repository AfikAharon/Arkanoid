package arkanoid;

import useful.MagN;
import biuoop.DrawSurface;
import core.Sprite;


/**
 * a DrawLevelName class.
 * The class is in charge of draw the level name.
 *
 * @author Afik Aharon.
 */
public class DrawLevelName implements Sprite {
    private String levelName;

    /**
     * Constructor for CountdownAnimation class.
     *
     * @param levelName the level name
     */
    public DrawLevelName(String levelName) {
        this.levelName = levelName;
    }

    /**
     * The function is in charge of draw the level name.
     * @param d a given draw surface
     */
    public void drawOn(DrawSurface d) {
        d.drawText(MagN.POSITION_LEVEL_MESSAGE_X, MagN.POSITION_MESSAGE_Y,
                MagN.LEVEL_MESSAGE + this.levelName, MagN.SIZE_INFO_TEXT);
    }

    /**
     * notify the sprite that time has passed.
     */
    public void timePassed() {
    }
}
