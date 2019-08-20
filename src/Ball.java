import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * A Ball class - Ball (actually, a circle) have size (radius), color, and location (a Point).
 *
 */
public class Ball implements Sprite, HitNotifier  {

    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment environment;
    private List<HitListener> hitListeners;


    /**
     * Construct a ball given a point coordinates, radius and color.
     *
     * @param center the ball center point
     * @param r the ball radius
     * @param color the ball color
     * @param environment the game environment.
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment environment) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.environment = environment;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * Construct a ball given x and y coordinates, radius and color.
     *
     * @param x the x coordinate of the ball center point
     * @param y the y coordinate of the ball center point
     * @param r the ball radius
     * @param color the ball color
     * @param environment the ball game environment
     */
    public Ball(double x, double y, int r, java.awt.Color color, GameEnvironment environment) {

        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.environment = environment;
    }

    /**
     * @param v the velocity of the ball
     */
    public void setVelocity(Velocity v) {
        this.velocity = new Velocity(v.getDx(), v.getDy());
    }

    /**
     * @param dx velocity change in position on the `x` axes.
     * @param dy velocity change in position on the `y` axes.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
        }

    /**
     *
     * @return the velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * moves the ball one step according to velocity and the boundaries of the ball.
     * @param dt specifies the amount of seconds passed since the last call.
     */
    public void moveOneStep(double dt) {

        Line trajectory = new Line(this.center, this.getVelocity().applyToPoint(this.center, dt));
        CollisionInfo info =  this.environment.getClosestCollision(trajectory);

        if (info == null) {
            this.center = this.getVelocity().applyToPoint(this.center, dt);
        } else {
            Point hitPoint = info.collisionPoint();
            if (this.getVelocity().getDx() >= 0) {
                this.center = new Point(hitPoint.getX() - 0.0001, this.center.getY());
            } else {
                this.center = new Point(hitPoint.getX() + 0.0001, this.center.getY());
            }

            if (this.getVelocity().getDy() >= 0) {
                this.center = new Point(this.center.getX(), hitPoint.getY() - 0.0001);
            } else {
                this.center = new Point(this.center.getX(), hitPoint.getY() + 0.0001);
            }

            this.setVelocity(info.collisionObject().hit(this, hitPoint, this.getVelocity()));

            if (this.ballIsInRectangle(info.collisionObject().getCollisionRectangle())) {
                this.center = new Point(info.collisionObject().getCollisionRectangle().getUp().middle().getX(),
                        info.collisionObject().getCollisionRectangle().getUpperLeft().getY() - this.r - 0.0001);
                this.setVelocity(this.getVelocity());
            }

        }
    }

    /**
     *
     * @return x center coordinate of the ball
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     *
     * @return y center coordinate of the ball
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     *
     * @return the radius of the ball
     */
    public int getSize() {
        return this.r;
    }

    /**
     *
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     *
     * @param surface the given DrawSurface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), r);
        surface.setColor(Color.black);
        surface.drawCircle((int) this.center.getX(), (int) this.center.getY(), r);
    }

    /**
     *  checks if a ball inside a rectangle.
     * @param rec the rectangle that we check if the is in it.
     * @return true if the ball is inside the ball, else otherwise
     */
    public boolean ballIsInRectangle(Rectangle rec) {
        return  (this.center.getX() > rec.getUpperLeft().getX())
                && (this.center.getX() < rec.getRight().start().getX())
                && (this.center.getY() > rec.getUp().start().getY())
                && (this.center.getY() < rec.getDown().start().getY());
    }


    /**
     * the ball behavior as time is passing.
     * @param dt specifies the amount of seconds passed since the last call.
     */
    @Override
    public void timePassed(double dt) {
        this.moveOneStep(dt);
    }

    /**
     * adding the ball to the game, calling the appropriate game methods.
     * @param g the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);

    }

    /**
     * Add hl as a listener to hit events.
     *
     * @param hl Hit Listener
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.addHitListener(hl);
    }

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl A Hit Listener
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.removeHitListener(hl);
    }

    /**
     *
     * @param info information about the collision.
     */
    private void notifyHit(CollisionInfo info) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent((Block) info.collisionObject(), this);
        }
    }

    /**
     *
     * @param gameLevel the gameLevel to remove from
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }
}