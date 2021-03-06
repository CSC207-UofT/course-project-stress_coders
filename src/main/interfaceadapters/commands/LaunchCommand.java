package interfaceadapters.commands;

import entities.Interactable;

import usecases.Launch;

import java.util.HashMap;

public class LaunchCommand extends Command {

    final Launch launch;

    public LaunchCommand() {this.launch = new Launch(); }

    @Override
    public String execute(HashMap<String, Interactable> args) {
        return this.launch.playInLaunch(args);
    }
}
