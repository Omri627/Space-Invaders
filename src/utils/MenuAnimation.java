package utils;


import animation.AnimationRunner;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Menu Animation responsible of create menu.
 * @param <T>
 */
public class MenuAnimation<T> implements Menu<T> {
    private KeyboardSensor keyboardSensor;
    private String title;
    //private AnimationRunner runner;
    private T status;
    private List<T> menuReturnVals;
    private List<String> menuItemNames;
    private List<String> menuItemKeys;
    private boolean stop;

    /**
     * Constructor.
     * @param keyboardSensor keyboard Sensor
     * @param title title of the game
     * @param runner animation runner
     */
    public MenuAnimation(KeyboardSensor keyboardSensor, String title, AnimationRunner runner) {
        this.keyboardSensor = keyboardSensor;
        this.title = title;
        //this.runner = runner;
        this.menuItemKeys = new ArrayList<String>();
        this.menuItemNames = new ArrayList<String>();
        this.menuReturnVals = new ArrayList<T>();
        this.stop = false;
    }


    @Override
    public void addSelection(String key, String message, T returnVal) {
        this.menuItemKeys.add(key);
        this.menuItemNames.add(message);
        this.menuReturnVals.add(returnVal);
    }

    @Override
    public T getStatus() {
        return this.status;
    }

    /**
     * reset the animation so it can be draw again.
     */
    public void reset() {
        this.status = null;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {

        d.setColor(Color.LIGHT_GRAY);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.BLACK);
        d.drawText(51, 50, this.title, 32);
        d.drawText(49, 50, this.title, 32);
        d.drawText(50, 51, this.title, 32);
        d.drawText(50, 49, this.title, 32);
        d.setColor(Color.YELLOW);
        d.drawText(50, 50, this.title, 32);
        for (int i = 0; i < this.menuItemNames.size(); ++i) {
            int optionX = 100;
            int optionY = 120 + i * 40;
            String optionText = "(" + this.menuItemKeys.get(i) + ") " + this.menuItemNames.get(i);
            d.setColor(Color.BLACK);
            d.drawText(optionX + 1, optionY, optionText, 24);
            d.drawText(optionX - 1, optionY, optionText, 24);
            d.drawText(optionX, optionY + 1, optionText, 24);
            d.drawText(optionX, optionY - 1, optionText, 24);
            d.setColor(Color.BLUE);
            d.drawText(optionX, optionY, optionText, 24);
        }

        for (int i = 0; i < this.menuReturnVals.size(); i++) {

            if (keyboardSensor.isPressed(this.menuItemKeys.get(i))) {
                this.status = this.menuReturnVals.get(i);
                this.stop = true;

            }
        }
    }

    @Override
    public boolean shouldStop() {
        //return stop;
        return this.status != null;
    }
}
