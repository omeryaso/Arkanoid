/**
 * Collidable interface.
 */
public interface Collidable {

    /**
     *
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     *
     * @param hitter the hitting ball.
     * @param collisionPoint the collision point with the object that we collided with
     * @param currentVelocity the current velocity when the collision happen
     * @return the new velocity expected after the hit (based on The return is the force the object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
