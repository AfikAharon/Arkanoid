package arkanoid;

import biuoop.DrawSurface;

import java.awt.Color;

import biuoop.KeyboardSensor;
import core.Sprite;
import core.Velocity;
import geometry.Point;
import geometry.Rectangle;
import useful.MagN;
import core.Collidable;

/**
 * a paddle class is a player on the game,
 * that controlled by the keyboard sensor and move (left or right)
 * according to the user.
 *
 * @author Afik Aharon.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle recPadle;
    private Color color;
    private int paddleSpeed;

    /**
     * Constructor for the paddle class.
     *
     * @param r           the Rectangle of the paddle.
     * @param keyboard    the given keyboard sensor.
     * @param paddleSpeed the paddle speed movement.
     * @param color       the paddle color.
     */
    public Paddle(Rectangle r, KeyboardSensor keyboard, Color color, int paddleSpeed) {
        this.recPadle = r;
        this.keyboard = keyboard;
        this.color = color;
        this.paddleSpeed = paddleSpeed;
    }

    /**
     * The function move the paddle Left,check if the paddle cross the borders game.
     */
    public void moveLeft() {
        // check if the paddle arrived to the Left border after the movement.
        if (this.recPadle.getUpperLeft().getX() - this.paddleSpeed < MagN.WID_HEI_BLOCKS) {
            this.recPadle = new Rectangle(new Point(MagN.WID_HEI_BLOCKS + 2.0001,
                    this.recPadle.getUpperLeft().getY()),
                    this.recPadle.getWidth(), this.recPadle.getHeight());
            return;
        }
        // else move the paddle Left (10 int each move)
        Point newP = new Point(this.recPadle.getUpperLeft().getX() - this.paddleSpeed,
                this.recPadle.getUpperLeft().getY());
        this.recPadle = new Rectangle(newP, this.recPadle.getWidth(), this.recPadle.getHeight());
    }

    /**
     * The function move the paddle ,check if the paddle cross the borders game.
     */
    public void moveRight() {
        // check if the paddle arrived to the Right border after the movement.
        if (this.recPadle.getUpperLeft().getX() + this.paddleSpeed
                + this.recPadle.getWidth() > MagN.GUI_WIDTH - MagN.WID_HEI_BLOCKS) {
            this.recPadle = new Rectangle(new Point(MagN.GUI_WIDTH - MagN.WID_HEI_BLOCKS
                    - this.recPadle.getWidth() - 0.001, this.recPadle.getUpperLeft().getY()),
                    this.recPadle.getWidth(), this.recPadle.getHeight());
            return;
        }
        // else move the paddle Right
        Point newP = new Point(this.recPadle.getUpperLeft().getX() + this.paddleSpeed,
                this.recPadle.getUpperLeft().getY());
        this.recPadle = new Rectangle(newP, this.recPadle.getWidth(), this.recPadle.getHeight());
    }

    /**
     * The function check if the Left or the Right keyboard pressed,
     * and call the function that move the ball.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * The function draw the paddle on the surface.
     *
     * @param d the draw surface.
     */
    public void drawOn(DrawSurface d) {
        int startX = (int) this.recPadle.getUpperLeft().getX();
        int startY = (int) this.recPadle.getUpperLeft().getY();
        int endX = (int) this.recPadle.getWidth() + startX;
        int endY = (int) this.recPadle.getHeight() + startY;
        d.setColor(this.color);
        d.fillRectangle(startX, startY, (int) this.recPadle.getWidth(), (int) this.recPadle.getHeight());
        d.setColor(Color.BLACK);
        d.drawLine(startX, startY, endX, startY);
        d.drawLine(startX, startY, startX, endY);
        d.drawLine(endX, startY, endX, endY);
        d.drawLine(startX, endY, endX, endY);
    }

    /**
     * The function sets the paddle color.
     *
     * @param c a given color.
     */
    public void setColor(Color c) {
        this.color = c;
    }

    /**
     * @return return the geometry.Rectangle member.
     */
    public Rectangle getCollisionRectangle() {
        return this.recPadle;
    }

    /**
     * The function check where the object hit on the paddle,
     * and change the velocity depend on which region the hit:
     * region 1 - the ball will bounce back with an angle of 300 degrees.
     * region 2 - the ball will bounce back with an angle of 330 degrees.
     * region 3 - changes the vertical direction.
     * region 4 - the ball will bounce back with an angle of 30 degrees.
     * region 5 - the ball will bounce back with an angle of 60 degrees.
     * the speed will be a root of current velocity  square dx + square dy.
     *
     * @param collisionPoint  the collision point.
     * @param currentVelocity the current velocity of the object.
     * @param hitter          the hitter ball.
     * @return the new velocity after the change.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity vel = currentVelocity;
        double speed = Math.sqrt(Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2));
        if (collisionPoint.getY() == this.recPadle.getUpperLeft().getY()) {
            double dividePart = this.recPadle.getWidth() / 5;
            double collisionX = collisionPoint.getX();
            double region1 = this.recPadle.getUpperLeft().getX() + dividePart;
            double region2 = region1 + dividePart;
            double region3 = region2 + dividePart;
            double region4 = region3 + dividePart;
            double region5 = region4 + dividePart;
            // the ball hits on region 1
            if (collisionX >= this.recPadle.getUpperLeft().getX() && collisionX <= region1) {
                vel = Velocity.fromAngleAndSpeed(300, speed);
            }
            // the ball hits on region 2
            if (collisionX >= region1 && collisionX <= region2) {
                vel = Velocity.fromAngleAndSpeed(330, speed);
            }
            // the ball hits on region 3
            if (collisionX >= region2 && collisionX <= region3) {
                vel = new Velocity(currentVelocity.getDx(), (-1) * currentVelocity.getDy());
            }
            // the ball hits on region 4
            if (collisionX >= region3 && collisionX <= region4) {
                vel = Velocity.fromAngleAndSpeed(30, speed);
            }
            // the ball hits on region 5
            if (collisionX >= region4 && collisionX <= region5) {
                vel = Velocity.fromAngleAndSpeed(60, speed);
            }
            return vel;
        }
        // check if the ball hits on the paddle sides.
        if (collisionPoint.getX() == this.recPadle.getUpperLeft().getX()) {
            vel = Velocity.fromAngleAndSpeed(300, speed);
        }
        if (collisionPoint.getX() == this.recPadle.getUpperLeft().getX() + this.recPadle.getWidth()) {
            vel = Velocity.fromAngleAndSpeed(60, speed);
        }
        return vel;
    }

    /**
     * The function insert the paddle to the given game.
     *
     * @param g a given game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * the function removed the ball from the game.
     *
     * @param game a given game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * The function sets the Rectangle paddle.
     *
     * @param rec a given Rectangle.
     */
    public void setRectangle(Rectangle rec) {
        this.recPadle = rec;
    }
}