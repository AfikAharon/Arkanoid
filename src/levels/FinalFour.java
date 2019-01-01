package levels;

import biuoop.DrawSurface;
import core.Counter;
import arkanoid.Block;
import core.Velocity;
import geometry.Point;
import geometry.Rectangle;
import useful.MagN;
import core.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * a FinalFour class, is a level in the game that implement LevelInformation.
 *
 * @author Afik Aharon.
 */
public class FinalFour implements LevelInformation {
    private int numberOfBalls;
    private int paddleSpeed;
    private Counter numberOfBlocksToRemove;
    private int paddleWidth;
    private String levelName;
    private List<Block> blocks;

    /**
     * Constructor for the FinalFour class.
     */
    public FinalFour() {
        this.numberOfBlocksToRemove = new Counter(0);
        this.numberOfBalls = 3;
        this.paddleSpeed = 10;
        this.paddleWidth = 100;
        this.levelName = "Final Four";
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
        ballsVelocity.add(Velocity.fromAngleAndSpeed(300, 5));
        ballsVelocity.add(Velocity.fromAngleAndSpeed(0, 5));
        ballsVelocity.add(Velocity.fromAngleAndSpeed(-300, 5));
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
                d.setColor(Color.decode("#4B85FA"));
                d.fillRectangle(MagN.WID_HEI_BLOCKS, MagN.WID_HEI_BLOCKS, MagN.GUI_WIDTH - 2 * MagN.WID_HEI_BLOCKS,
                        MagN.GUI_HEIGHT - MagN.WID_HEI_BLOCKS);
                d.setColor(Color.WHITE);
                int moveLeft = 0;
                int moveDown = 0;
                int startX = 260;
                int startY = 400;
                // the function draw a 2 drop of rain animation.
                for (int k = 0; k < 2; k++) {
                    for (int i = 0; i < 10; i++) {
                        for (int j = 0; j < 30; j++) {
                            d.drawLine(startX - 2 * j - moveLeft, startY + moveDown, startX - 2 * j - moveLeft,
                                    startY + 8 + moveDown);
                            moveDown += 8;
                        }
                        moveDown = 0;
                        moveLeft += 15;
                    }
                    moveLeft = 0;
                    moveDown = 0;
                    // move the coordinate to the next draw
                    startX = 730;
                    startY = 500;
                }
                d.setColor(Color.decode("#D0D0D0"));
                d.fillCircle(150, 400, 35);
                d.setColor(Color.decode("#D0D0D0"));
                d.fillCircle(190, 390, 40);
                d.setColor(Color.decode("#BEBEBF"));
                d.fillCircle(220, 400, 40);
                d.setColor(Color.decode("#B3B3B5"));
                d.fillCircle(240, 420, 35);
                d.setColor(Color.decode("#D0D0D0"));
                d.fillCircle(620, 490, 35);
                d.setColor(Color.decode("#D0D0D0"));
                d.fillCircle(660, 470, 40);
                d.setColor(Color.decode("#BEBEBF"));
                d.fillCircle(680, 480, 40);
                d.setColor(Color.decode("#B3B3B5"));
                d.fillCircle(700, 500, 35);
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
        int moveLeft = 0;
        int moveDown = 0;
        boolean flag = false;
        Color[] arrColors = new Color[7];
        arrColors[0] = Color.RED;
        arrColors[1] = Color.CYAN;
        arrColors[2] = Color.PINK;
        arrColors[3] = Color.BLUE;
        arrColors[4] = Color.GREEN;
        arrColors[5] = Color.MAGENTA;
        arrColors[6] = Color.GRAY;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 15; j++) {
                Rectangle rect = new Rectangle(new Point(MagN.GUI_WIDTH - 75 - moveLeft,
                        11 * MagN.WID_HEI_BLOCKS - moveDown), 50, MagN.WID_HEI_BLOCKS);
                this.blocks.add(new Block(rect, arrColors[i]));
                // in crease the blocks hit counter.
                if (flag) {
                    this.blocks.get(j).increaseAmountHits(2);
                }
                moveLeft += 50;
                // increase the ball counter
                this.numberOfBlocksToRemove.increase(1);
            }
            moveLeft = 0;
            moveDown += MagN.WID_HEI_BLOCKS;
            // The condition is for the last row (in th screen is the first) to increase the blocks hit.
            if (i == 6) {
                flag = true;
            }
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
