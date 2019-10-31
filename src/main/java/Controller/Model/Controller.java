package Controller.Model;

import Commander.Command;

import java.util.HashMap;
import java.util.function.Supplier;

/**
 *
 * @author Manuel Werder
 * @version 0.1
 */
public abstract class Controller extends Command<String, Supplier<String>> {

	protected Controller() {
		commands = new HashMap<>();
		commands.put("help", this::help);
		commands.put("commands", this::commands);
		commands.put("exit", this::exit);
	}

	abstract public void startController();

	protected String helloGamer(String name) {
		return "Hello " + name + ", welcome to a Text base Dungeon Crawler Game.\n" +
				"Enter the command 'help' to see some basic infos.\n" +
				name + " you are at the beginning of you journey enter 'commands' to see" +
				"what is possible at the Dungeon Portal.";
	}

	protected String wrongInput(String command) {
		return "Sorry there is no command named: " + command;
	}

	private String help() {
		return "This is the help text...Enter 'help' for this text.\n" +
				"Most commands that can be run have a simple syntax, \n" +
				"'<command name>', exchange  everything between the <> with a given command.\n" +
				"For example 'commands'. Enter 'play' to play a game.\n" +
				"You will then have to chose from a list of games.\n" +
				"Note: While you are playing a game you can not use any of the commands that are\n" +
				"listed under 'commands'.";
	}

	private String commands() {
		return "The Dungeon Portal has the following commands:\n" +
				"'help' - gives you a nice general help to the dungeon portal\n" +
				"'commands' - gives you this out what you are reading right know\n" +
				"'play' - displays you a list of all possible games to play\n" +
				"'exit' - stops the game";
	}


	/**
	 * Calling this Functions kills the Program with exit code 0.
	 *
	 * @return String, but gives null back. This is done due to the command Pattern.
	 */
	private String exit() {
		System.exit(0);
		return null;
	}

}