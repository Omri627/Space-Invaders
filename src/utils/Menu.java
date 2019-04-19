package utils;
/**
 * Menu interface.
 */

import animation.Animation;

/**
 * @param <T>
 */
public interface Menu<T> extends Animation {
    /**
     * add selection option to the menu.
     *
     * @param key       key to wait for from keyboard
     * @param message   the message to display
     * @param returnVal what to return
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * get the status from the menu.
     *
     * @return status
     */
    T getStatus();

    /**
     * reset the animation.
     */
     void reset();

}
