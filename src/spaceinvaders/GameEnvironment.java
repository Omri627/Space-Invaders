package spaceinvaders;

import core.Collidable;
import core.CollisionInfo;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * The GameEnvironment class represent all the thing a ball can collide with and the collision info about it.
 */
public class GameEnvironment {
    private List<Collidable> list;

    /**
     * constructor, initialize ArrayList of collidable.
     */
    public GameEnvironment() {
        this.list = new ArrayList<>();
    }

    /**
     * add the given collidable to the environment.
     *
     * @param c collidable object
     */
    public void addCollidable(Collidable c) {
        list.add(c);
    }
    /**
     * remove the given collidable from the environment.
     *
     * @param c collidable object
     */
    public void removeCollidable(Collidable c) {
        list.remove(c);
    }

    /**
     * return core.CollisionInfo object that represent the hit point and the object.
     *
     * @param trajectory line that represent the movement line of the ball.
     * @return core.CollisionInfo, null if not collide with any of the collidables.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        boolean isFirstCheck = true;
        if (list.isEmpty()) {
            return null;
        } else {
            Collidable collidableObj = null;
            Point closestCoalitionPoint = null;
            for (Collidable col : list) {
                Rectangle rectangle = col.getCollisionRectangle();
                Point temp = trajectory.closestIntersectionToStartOfLine(rectangle);
                if (temp != null) {
                    if (isFirstCheck) {
                        collidableObj = col;
                        closestCoalitionPoint = temp;
                        isFirstCheck = false;
                    } else {
                        if (trajectory.start().distanceTo(temp)
                                < trajectory.start().distanceTo(closestCoalitionPoint)) {
                            closestCoalitionPoint = temp;
                            collidableObj = col;
                        }
                    }
                }

            }
            if (closestCoalitionPoint == null) {
                return null;
            }
            return new CollisionInfo(closestCoalitionPoint, collidableObj);

        }
    }
}
