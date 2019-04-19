package core;

import geometry.Point;

/**
 * class save the information of the collision objects that the ball hit.
 */
public class CollisionInfo {
    private Point collitionPoint;
    private Collidable collisionObject;

    /**
     *
     * @param collition point of collition.
     * @param collisionObject the object that the ball hit.
     */
    public CollisionInfo(Point collition, Collidable collisionObject) {
        this.collitionPoint = collition;
        this.collisionObject = collisionObject;
    }

    /**
     * @return colition geometry.Point.
     */
    public Point collisionPoint() {
        return this.collitionPoint;

    }

    /**
     * @return the collision object that the ball hit
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
