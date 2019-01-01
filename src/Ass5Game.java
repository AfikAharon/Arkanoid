import animation.AnimationRunner;
import arkanoid.GameFlow;
import biuoop.GUI;
import biuoop.Sleeper;
import levels.LevelInformation;
import levels.DirectHit;
import levels.WideEasy;
import levels.FinalFour;
import levels.Green3;
import useful.MagN;

import java.util.ArrayList;
import java.util.List;

/**
 * a Ass5Game class, is in charge of get a list of argument
 * from the user and run the given levels of the game.
 *
 * @author Afik Aharon.
 */
public class Ass5Game {
    /**
     * The main function create a Ass5Game object, and call
     * the function runTheGame.
     *
     * @param args a empty List
     */
    public static void main(String[] args) {
        Ass5Game run = new Ass5Game();
        run.runTheGame(args);
    }

    /**
     * The function create a list of levels,GUI,and a GameFlow object , call the function
     * createListOfLevels to create a list of levels from the user, and run the game by call the function
     * runLevels from GameFlow object.
     * If the user not input a game levels the function run a game with 4 levels.
     *
     * @param args the given arguments from the user.
     */
    public void runTheGame(String[] args) {
        List<LevelInformation> givenLevels = createListOfLevels(args);
        GUI gui = new GUI("arkonid", MagN.GUI_WIDTH, MagN.GUI_HEIGHT);
        GameFlow game = new GameFlow(new AnimationRunner(gui, new Sleeper(), 60),
                gui.getKeyboardSensor());
        // if the function return a empty list the user not input a game levels.
        if (givenLevels.isEmpty()) {
            List<LevelInformation> levels = new ArrayList<LevelInformation>();
            levels.add(new DirectHit());
            levels.add(new WideEasy());
            levels.add(new Green3());
            levels.add(new FinalFour());
            game.runLevels(levels);
        } else {
            game.runLevels(givenLevels);
        }
        gui.close();
    }

    /**
     * The function create a list of game levels accordingly to the user input.
     *
     * @param args the given arguments from the user.
     * @return a new list with a game levels
     */
    public List<LevelInformation> createListOfLevels(String[] args) {
        List<LevelInformation> givenLevels = new ArrayList<LevelInformation>();
        // the loop check if the arguments are a game levels.
        for (String s : args) {
            if (s.equals("1")) {
                givenLevels.add(new DirectHit());
            } else if (s.equals("2")) {
                givenLevels.add(new WideEasy());
            } else if (s.equals("3")) {
                givenLevels.add(new Green3());
            } else if (s.equals("4")) {
                givenLevels.add(new FinalFour());
            }
        }
        return givenLevels;
    }
}
