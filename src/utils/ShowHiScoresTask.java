package utils;

import animation.Animation;
import animation.AnimationRunner;

/**
 * ShowHiScoresTask shows the high score menu.
 */
public class ShowHiScoresTask implements Task<Void> {
    private AnimationRunner runner;
    private Animation highScoresAnimation;

    /**
     * Constructor.
     *
     * @param runner              the animation runner to run.
     * @param highScoresAnimation highScoresAnimation
     */
    public ShowHiScoresTask(AnimationRunner runner, Animation highScoresAnimation) {
        this.runner = runner;
        this.highScoresAnimation = highScoresAnimation;
    }

    @Override
    public Void run() {
        this.runner.run(this.highScoresAnimation);
        return null;
    }

}
