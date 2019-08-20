/**
 * CollisionInfo class:
 * holds the point at which the collision occurs and the collidable object involved in the collision.
 */
public class CollisionInfo {

    private Point collision;
    private Collidable object;

    /**
     * @param collision collision point
     * @param object collidable object
     */
    public CollisionInfo(Point collision, Collidable object) {
        this.collision = collision;
        this.object = object;
    }

    /**
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collision;
    }

    /**
     *
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.object;
    }

}