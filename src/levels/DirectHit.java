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
 * a DirectHit class, is a level in the game that implement LevelInformation.
 *
 * @author Afik Aharon.
 */
public class DirectHit implements LevelInformation {
    private int numberOfBalls;
    private int paddleSpeed;
    private Counter numberOfBlocksToRemove;
    private int paddleWidth;
    private String levelName;
    private List<Block> blocks;


    /**
     * Constructor for the DirectHit class.
     */
    public DirectHit() {
        this.numberOfBlocksToRemove = new Counter(0);
        this.numberOfBalls = 1;
        this.paddleSpeed = 10;
        this.paddleWidth = 100;
        this.levelName = "Direct Hit";
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
        ballsVelocity.add(new Velocity(0, -10));
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
                d.setColor(Color.BLACK);
                d.fillRectangle(MagN.WID_HEI_BLOCKS, MagN.WID_HEI_BLOCKS, MagN.GUI_WIDTH - 2 * MagN.WID_HEI_BLOCKS,
                        MagN.GUI_HEIGHT - MagN.WID_HEI_BLOCKS);
                d.setColor(Color.BLUE);
                d.drawLine(MagN.GUI_WIDTH / 2, 40, MagN.GUI_WIDTH / 2, 320);
                d.drawLine(MagN.GUI_WIDTH / 2 - 160, 180, MagN.GUI_WIDTH / 2 + 160, 180);
                d.drawCircle(MagN.GUI_WIDTH / 2, 180, 40);
                d.drawCircle(MagN.GUI_WIDTH / 2, 180, 80);
                d.drawCircle(MagN.GUI_WIDTH / 2, 180, 120);
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
        //Block List<Block> blocks = new ArrayList<Block>();
        blocks.add(new Block(new Rectangle(new Point(380, 160), 40, 40), Color.RED));
        this.numberOfBlocksToRemove.increase(1);
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
