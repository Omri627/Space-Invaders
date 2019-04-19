package animation;

import biuoop.DrawSurface;

/**
 * animation.animation interface.
 */
public interface Animation {
    /**
     * game-specific logic and stopping conditions are handled in doOneFrame().
     * @param d drawSurface
     * @param dt specifies the amount of seconds passed since the last call
     */
    void doOneFrame(DrawSurface d, double dt);

    /**
     * determined if the animation should stop from any reason.
     * @return true it should stop, else false
     */
    boolean shouldStop();
}
