import java.util.ArrayList;

/**
 * The GameEnvironment.
 */
public class GameEnvironment {

    private ArrayList<Collidable> collidables;

    /**
     * GameEnvironment constructor.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }

    /**
     * add the given collidable to the environment.
     * @param c collidables object
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * remove the given collidable to the environment.
     * @param c collidables object
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * @param trajectory the path of an object moving from line.start() to line.end().
     * @return If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {

        if (collidables.isEmpty()) {
            return null;
        }
        CollisionInfo info = null;
        double distance = trajectory.length() + 10;

        for (int i = 0; i < this.collidables.size(); i++) {
            Point closest = trajectory.closestIntersectionToStartOfLine(
                    this.collidables.get(i).getCollisionRectangle());
            if (closest != null && closest.distance(trajectory.start()) < distance) {
                distance = closest.distance(trajectory.start());
                info = new CollisionInfo(closest, this.collidables.get(i));
                }
        }

        return info;
    }
}