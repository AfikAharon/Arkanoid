package arkanoid;

import animation.AnimationRunner;
import biuoop.KeyboardSensor;
import core.Counter;
import levels.LevelInformation;
import useful.MagN;

import java.util.List;

/**
 * a GameFlow class, the class run the levels game.
 *
 * @author Afik Aharon.
 */
public class GameFlow {
    private ScoreIndicator scoreIndicator;
    private LivesIndicator livesIndicator;
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    /**
     * Constructor for EndScreen class.
     *
     * @param ar the AnimationRunner
     * @param ks the KeyboardSensor
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.scoreIndicator = new ScoreIndicator(new Counter(0));
        this.livesIndicator = new LivesIndicator(new Counter(7));
        this.animationRunner = ar;
        this.keyboardSensor = ks;
    }

    /**
     * the function run the given levels game.
     *
     * @param levels the levels game
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.animationRunner, this.keyboardSensor,
                                     this.scoreIndicator, this.livesIndicator);
            level.initialize();
            // while the player have more lives and have more blocks to removed.
            while (this.livesIndicator.getLives().getValue() > 0 && levelInfo.numberOfBlocksToRemove() > 0) {
                level.playOneTurn();
            }
            // If the player not have more lives game.
            if (this.livesIndicator.getLives().getValue() == 0) {
                break;
            }
        }
        // if the player win run a win end screen.
        if (this.livesIndicator.getLives().getValue() > 0) {
            this.animationRunner.run(new EndScreen(this.keyboardSensor, MagN.WIN_MESSAGE, scoreIndicator.getScore()));
        // otherwise the player lost run a lost end screen.
        } else {
            this.animationRunner.run(new EndScreen(this.keyboardSensor, MagN.LOST_MESSAGE, scoreIndicator.getScore()));
        }
    }
}