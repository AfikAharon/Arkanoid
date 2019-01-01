package levels;

import biuoop.DrawSurface;
import core.Counter;
import arkanoid.Block;
import core.Sprite;
import core.Velocity;
import geometry.Point;
import geometry.Rectangle;
import useful.MagN;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * a WideEasy class, is a level in the game that implement LevelInformation.
 *
 * @author Afik Aharon.
 */
public class WideEasy implements LevelInformation {
    private int numberOfBalls;
    private int paddleSpeed;
    private Counter numberOfBlocksToRemove;
    private int paddleWidth;
    private String levelName;
    private List<Block> blocks;

    /**
     * Constructor for the WideEasy class.
     */
    public WideEasy() {
        this.numberOfBlocksToRemove = new Counter(0);
        this.numberOfBalls = 10;
        this.paddleSpeed = 2;
        this.paddleWidth = 600;
        this.levelName = "Wide Easy";
        this.blocks = new ArrayList<Block>();

    }

    /**
     * the function return the amount of balls game.
     *
     * @return the number of balls game.
     */
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    /**
     * The function initialize the Velocity of each ball in the game.
     *
     * @return the list of Velocity.
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballsVelocity = new ArrayList<Velocity>();
        int changeAngle = 0;
        for (int i = 0; i < this.numberOfBalls; i++) {
            ballsVelocity.add(Velocity.fromAngleAndSpeed(-45 + changeAngle, 8));
            changeAngle += 10;
        }
        return ballsVelocity;
    }

    /**
     * The function return the Paddle speed.
     *
     * @return the Paddle speed
     */
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * The function return the Paddle width.
     *
     * @return the Paddle width.
     */
    public int paddleWidth() {
        return this.paddleWidth;
    }

    /**
     * the function return the level name string.
     *
     * @return the level name string
     */
    public String levelName() {
        return this.levelName;
    }

    /**
     * The function return a Sprite that draw the background level.
     *
     * @return the background Sprite.
     */
    public Sprite getBackground() {
        // To avoid of create a new class for one use i create a inner class.
        return new Sprite() {
            /**
             * The function draw the background level.
             * @param d a given draw surface
             */
            public void drawOn(DrawSurface d) {
                d.setColor(Color.LIGHT_GRAY);
                d.fillRectangle(MagN.WID_HEI_BLOCKS, MagN.WID_HEI_BLOCKS, MagN.GUI_WIDTH - 2 * MagN.WID_HEI_BLOCKS,
                        MagN.GUI_HEIGHT - MagN.WID_HEI_BLOCKS);
                d.setColor(Color.decode("#F5FC37"));
                // The lop draw the sun rays
                for (int i = 0; i < MagN.GUI_WIDTH; i += 10) {
                    d.drawLine(120, 120, i, MagN.GUI_HEIGHT / 3 + 2 * MagN.WID_HEI_BLOCKS);
                }
                d.setColor(Color.decode("#FCFF81"));
                d.fillCircle(120, 120, 60);
                d.setColor(Color.decode("#F5FC37"));
                d.fillCircle(120, 120, 50);
                d.setColor(Color.decode("#F6FC43"));
                d.fillCircle(120, 120, 40);
            }

            /**
             * notify the sprite that time has passed.
             */
            public void timePassed() {
            }
        };
    }

    /**
     * The function create a list of blocks that make up this level.
     *
     * @return the list blocks
     */
    public List<Block> blocks() {
        Color[] arrColors = new Color[MagN.AMOUNT_COLORS_BLOCKS];
        arrColors[0] = Color.RED;
        arrColors[1] = Color.GRAY;
        arrColors[2] = Color.PINK;
        arrColors[3] = Color.BLUE;
        arrColors[4] = Color.GREEN;
        arrColors[5] = Color.MAGENTA;
        arrColors[6] = Color.CYAN;
        arrColors[7] = Color.orange;
        for (int i = 0; i < 15; i++) {
            Rectangle rect = new Rectangle(new Point(MagN.GUI_WIDTH - 76 - i * 50,
                    MagN.GUI_HEIGHT / 3 + 2 * MagN.WID_HEI_BLOCKS), 51, MagN.WID_HEI_BLOCKS);
            this.blocks.add(new Block(rect, arrColors[i / 2]));
            this.numberOfBlocksToRemove.increase(1);
        }
        return this.blocks;
    }

    /**
     * The function return the remain blocks to remove.
     *
     * @return remain blocks to remove.
     */
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove.getValue();
    }


    /**
     * The function return the counter of number of blocks to remove.
     *
     * @return the counter of number of blocks to remove
     */
    public Counter getCounterOfNumberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }
}
