import biuoop.DrawSurface;
import java.awt.Image;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * A block class.
 */
public class Block implements Collidable, Sprite, HitNotifier {

    private Rectangle rec;
    private java.awt.Color color;
    private int hits;
    private List<HitListener> hitListeners;
    private Object[] fillKlist;

    /**
     * Rectangle constructor.
     * @param upperLeft The rectangle upper Left point
     * @param width The width of the block
     * @param height The height of the block
     * @param color thr color of the block
     */
    public Block(Point upperLeft, double width, double height, java.awt.Color color) {
        this.rec = new Rectangle(upperLeft,  width, height);
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * Rectangle constructor.
     * @param upperLeft The rectangle upper Left point
     * @param width The width of the block
     * @param height The height of the block
     * @param color thr color of the block
     * @param hits the number of hits allowed
     */
    public Block(Point upperLeft, double width, double height, java.awt.Color color, int hits) {
        this.rec = new Rectangle(upperLeft,  width, height);
        this.color = color;
        this.hits = hits;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     *
     * @param upperLeft The rectangle upper Left point
     * @param width The width of the block
     * @param height The height of the block
     * @param color blocks border color
     * @param hits the number of hits allowed
     * @param fillKList block fill color
     */
    public Block(Point upperLeft, double width, double height, Color color, int hits, Object[] fillKList) {
        this.rec = new Rectangle(upperLeft,  width, height);
        this.hits = hits;
        this.hitListeners = new ArrayList<HitListener>();
        this.fillKlist = fillKList;
        this.color = color;
    }

    /**
     * @return the "collision shape" of the object.
     */
    public Rectangle getCollisionRectangle() {
        return this.rec;
    }

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     *
     * @param hitter the hitting ball.
     * @param collisionPoint the collision point of the objects
     * @param currentVelocity current velocity of the hitting object.
     * @return new velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        if (collisionPoint.isPointOnRectangle(rec.getUp())) {
            currentVelocity = new Velocity(currentVelocity.getDx(), -(currentVelocity.getDy()));
        }

        if (collisionPoint.isPointOnRectangle(rec.getDown())) {
            currentVelocity = new Velocity(currentVelocity.getDx(), -(currentVelocity.getDy()));
        }
        if (collisionPoint.isPointOnRectangle(rec.getLeft())) {
            currentVelocity = new Velocity(-currentVelocity.getDx(), (currentVelocity.getDy()));
        }
        if (collisionPoint.isPointOnRectangle(rec.getRight())) {
            currentVelocity = new Velocity(-currentVelocity.getDx(), (currentVelocity.getDy()));
        }

        notifyHit(hitter);

        return currentVelocity;
    }

    /**
     *
     * @param surface The draw surface
     */
    public void drawOn(DrawSurface surface) {
        if (fillKlist == null) {
            surface.setColor(this.color);
            surface.fillRectangle((int) this.rec.getUpperLeft().getX(), (int) this.rec.getUpperLeft().getY(),
                    (int) this.rec.getWidth(), (int) this.rec.getHeight());
        } else
        if (this.fillKlist[this.hits - 1] instanceof Color) {
            surface.setColor((Color) this.fillKlist[this.hits - 1]);
            surface.fillRectangle((int) this.rec.getUpperLeft().getX(), (int) this.rec.getUpperLeft().getY(),
                    (int) this.rec.getWidth(), (int) this.rec.getHeight());
        } else
        if (this.fillKlist[this.hits - 1] instanceof Image) {
            surface.drawImage((int) this.rec.getUpperLeft().getX(), (int) this.rec.getUpperLeft().getY(),
                    (Image) this.fillKlist[this.hits - 1]);
        }
        if (this.color != null) {
            surface.setColor(this.color);
            surface.drawRectangle((int) this.rec.getUpperLeft().getX(), (int) this.rec.getUpperLeft().getY(),
                    (int) this.rec.getWidth(), (int) this.rec.getHeight());
        }
        //        surface.setColor(this.color);
//        surface.fillRectangle((int) this.rec.getUpperLeft().getX(), (int) this.rec.getUpperLeft().getY(),
//                (int) this.rec.getWidth(), (int) this.rec.getHeight());
//        surface.setColor(Color.black);
//        if (this.hits != 0) {
//            surface.drawRectangle((int) this.rec.getUpperLeft().getX(), (int) this.rec.getUpperLeft().getY(),
//                    (int) this.rec.getWidth(), (int) this.rec.getHeight());
//        }
    }

    /**
     * Sprite implementation.
     * @param dt specifies the amount of seconds passed since the last call
     */
    public void timePassed(double dt) {
    }

    /**
     * adding the block to the game, calling the appropriate game methods.
     * @param g the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     *
     * @param gameLevel the gameLevel to remove from
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    /**
     * Add hl as a listener to hit events.
     *
     * @param hl Hit Listener
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl A Hit Listener
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     *
     * @param hitter the hitting ball.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     *
     * @return number of hit points.
     */
    public int getHitPoints() {
        return this.hits;
    }

    /**
     * decrease hit by 1.
     */
    public void decreaseHit() {
        if (this.hits > 0) {
            this.hits--;
        }
    }
}
