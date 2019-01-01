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
 * a Green3 class, is a level in the game that implement LevelInformation.
 *
 * @author Afik Aharon.
 */
public class Green3 implements LevelInformation {
    private int numberOfBalls;
    private int paddleSpeed;
    private Counter numberOfBlocksToRemove;
    private int paddleWidth;
    private String levelName;
    private List<Block> blocks;
    private Counter remainingBlocks;

    /**
     * Constructor for the Green3 class.
     */
    public Green3() {
        this.numberOfBlocksToRemove = new Counter(0);
        this.numberOfBalls = 2;
        this.paddleSpeed = 10;
        this.paddleWidth = 100;
        this.levelName = "Green 3";
        this.blocks = new ArrayList<Block>();
        this.remainingBlocks = new Counter(0);
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
        ballsVelocity.add(Velocity.fromAngleAndSpeed(300, 8));
        ballsVelocity.add(Velocity.fromAngleAndSpeed(-300, 8));
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
                d.setColor(Color.decode("#28D106"));
                d.fillRectangle(MagN.WID_HEI_BLOCKS, MagN.WID_HEI_BLOCKS, MagN.GUI_WIDTH - 2 * MagN.WID_HEI_BLOCKS,
                        MagN.GUI_HEIGHT - MagN.WID_HEI_BLOCKS);
                d.setColor(Color.decode("#3C3F3B"));
                d.fillRectangle(60, 400, 100, 400);
                d.setColor(Color.decode("#585A57"));
                d.fillRectangle(90, 350, 40, 50);
                d.setColor(Color.decode("#626461"));
                d.fillRectangle(102, 200, 15, 150);
                d.setColor(Color.decode("#FAC411"));
                d.fillCircle(109, 190, 15);
                d.setColor(Color.decode("#FF5762"));
                d.fillCircle(109, 190, 10);
                d.setColor(Color.decode("#FFFFFF"));
                d.fillCircle(109, 190, 5);
                int moveRight = 0;
                int moveDown = 0;
                d.setColor(Color.decode("#FFFFFF"));
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        d.fillRectangle(65 + moveRight, 410 + moveDown, 10, 30);
                        moveRight += 20;
                    }
                    moveDown += 40;
                    moveRight = 0;
                }
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
        Color[] arrColors = new Color[5];
        arrColors[0] = Color.GRAY;
        arrColors[1] = Color.RED;
        arrColors[2] = Color.PINK;
        arrColors[3] = Color.BLUE;
        arrColors[4] = Color.CYAN;
        int counterBlocks = 10;
        int moveDown = 0;
        int moveLeft = 0;
        boolean flag = true;
        for (int i = 0; i < 5; i++) {
            double y = (MagN.GUI_HEIGHT / 4) + moveDown;
            for (int j = 0; j < counterBlocks; j++) {
                double x = MagN.GUI_WIDTH - 3 * MagN.WID_HEI_BLOCKS - moveLeft;
                Rectangle r = new Rectangle(new Point(x, y), 2 * MagN.WID_HEI_BLOCKS, MagN.WID_HEI_BLOCKS);
                this.blocks.add(new Block(r, arrColors[i]));
                this.remainingBlocks.increase(1);
                // sets the counterHits just for the first row.
                if (flag) {
                    this.blocks.get(j).increaseAmountHits(2);
                }
                this.numberOfBlocksToRemove.increase(1);
                moveLeft += 2 * MagN.WID_HEI_BLOCKS;
            }
            flag = false;
            counterBlocks--;
            // move the row down.
            moveDown += MagN.WID_HEI_BLOCKS;
            // initialize for the next row.
            moveLeft = 0;
        }
        return this.blocks;
    }


    /**
     * The function return the remain blocks to remove.
     *
     * @return remain blocks to remove.
     */
    public int numberOfBlocksToRemove() {
        //return this.remainingBlocks.getValue();
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
