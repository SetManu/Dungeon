package Controller;

import Controller.Model.Controller;
import Controller.Model.ControllerState;
import Controller.Model.Function;
import GameInterface.Gamer;
import Menace.MenaceGame;
import UserInterface.UserCommandLineInterface;
import javafx.util.Pair;

import java.util.function.Supplier;


/**
 * The DungeonController describes a way to play multiple text based Dungeon-Crawler-Games.
 * After init, you should call startController.
 *
 * @author Manuel Werder
 * @version 0.1
 */
public final class DungeonController extends Controller {

	// TODO: JAVADOC

	private UserCommandLineInterface userInterface;

	/**
	 * The Constructor for the DungeonController initializes the DungeonController
	 * and creates a new instance of UserCommandLineInterface.
	 */
	public DungeonController() {
		super();
		userInterface = new UserCommandLineInterface();
		playerName = userInterface.getInput("Hello, enter your name> ");
		userInterface.println(helloGamer(playerName));

		games.put("menace", MenaceGame::new);
	}

	/**
	 * The start method should be called right after init of the DungeonController.
	 */
	@Override
	public void startController() {
		state = ControllerState.CHOOSING_STATE;
		while (true) {
			switch (state) {
				case CHOOSING_STATE:
					controlCommand("dungeon portal> ");
					break;
				case HELP_STATE:
					controlCommand("dungeon help section> ");
					break;
				case GAMING_STATE:
					userInterface.println(executeCommand(game.playGame()));
					state = ControllerState.CHOOSING_STATE;
					game = null;
					break;
			}
		}
	}

	private void controlCommand(String prompt) {
		String input;
		Supplier<String> command;
		String output;
		input = userInterface.getInput(prompt);
		command = getCommand(input);
		output = executeCommand(command);
		userInterface.println(output);
	}

	@Override
	protected Supplier<String> getCommand(String command) {
		Supplier<String> com = null;
		if (state == ControllerState.CHOOSING_STATE)
			com = controllerCommands.get(command);
		else if (state == ControllerState.HELP_STATE)
			com = helpCommands.get(command);
		return com != null ? com : () -> wrongInput(command);
	}

	/**
	 * We simple execute the given Function.
	 * @param command Takes the function with no arguments and a String as Return value.
	 * @return Returns the String that is returned by the function.
	 */
	@Override
	protected String executeCommand(Supplier<String> command) {
		return command.get();
	}

	/**
	 * Looks up the gameName and initializes the game and runs it.
	 * Internally we set the State of the DungeonController, depending on if
	 * the Game exists or not.
	 *
	 * @return A message of success or failure.
	 */
	@Override
	protected String playGame() {
		games.keySet().forEach( (t) -> System.out.print("'" + t + "' "));
		String gameName = userInterface.getInput("\nenter dungeon name> ");
		Function<String, UserCommandLineInterface, Gamer> g = games.get(gameName);
		if (g == null) {
			state = ControllerState.CHOOSING_STATE;
			return "There is no dungeon '" + gameName + "' to explore...";
		}
		game = g.execute(playerName, userInterface);
		return playerName + " you have entered the " + gameName + " dungeon...good luck!";
	}


}
