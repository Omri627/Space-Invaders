package core;

import spaceinvaders.Ball;
import geometry.Point;
import geometry.Rectangle;

/**
 * The core.Collidable interface will be used by things that can be collided with.
 */
public interface Collidable {
    /**
     * the "collision shape" of the object.
     * @return the rectangle that have been hit.
     */
    Rectangle getCollisionRectangle();

    /**
     *
     * @param hitter the hitter ball
     * @param collisionPoint  the location of the hitting point
     * @param currentVelocity the speed and angle at the moment of collision
     * @return new velocity expected after the hit (based on the force the object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
