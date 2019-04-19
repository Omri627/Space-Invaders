package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * wrap an existing animation and add a "waiting-for-key" behavior to it.
 */
public class KeypressStoppableAnimation implements Animation {
    private KeyboardSensor keyboardSensor;
    private String key;
    private Animation decoratorAnimation;
    private boolean isAlreadyPressed;
    private boolean stop;

    /**
     * cosntructor.
     *
     * @param keyboardSensor keyboardSensor
     * @param key            the key to press
     * @param animation      animation to stop and draw
     */
    public KeypressStoppableAnimation(KeyboardSensor keyboardSensor, String key, Animation animation) {
        this.keyboardSensor = keyboardSensor;
        this.key = key;
        this.decoratorAnimation = animation;
        this.isAlreadyPressed = true;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {

        decoratorAnimation.doOneFrame(d, dt);
        if (keyboardSensor.isPressed(key)) {
            if (!isAlreadyPressed) {
                stop = true;
            }
        } else {
            isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * reset the animation so it can be draw again.
     */
    public void reset() {
        this.stop = false;
    }
}
