package core;

/**
 * * The core.HitNotifier interface indicate that objects that implement it send notifications when
 * they are being hit.
 * @param <E> generic
 */
public interface HitNotifier<E> {

    /**
     * Add hl as a listener to hit events.
     * @param hl core.HitListener
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl core.HitListener
     */
    void removeHitListener(HitListener hl);
}
