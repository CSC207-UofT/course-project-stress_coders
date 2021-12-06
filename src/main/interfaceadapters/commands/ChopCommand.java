package interfaceadapters.commands;

import entities.*;
import usecases.Chop;

import java.util.*;

/**
 * Chop command. Uses a tool to chop the target, assuming all is possible. Only for harvesting.
 */
public class ChopCommand extends Command {
    Chop chop;

   public ChopCommand(){
       this.chop = new Chop();
   }
   @Override
   public String execute(HashMap<String, Interactable> args) {
        return chop.chopHarvestable(args);
    }
}
