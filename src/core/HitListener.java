package core;

import spaceinvaders.Ball;

/**
 * Objects that want to be notified of hit events, should implement the core.HitListener interface.
 *
 * @param <E>
 */
public interface HitListener<E> {
    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit the object that been hit.
     * @param hitter   the spaceinvaders.Ball that's doing the hitting.
     */
    void hitEvent(E beingHit, Ball hitter);
}
