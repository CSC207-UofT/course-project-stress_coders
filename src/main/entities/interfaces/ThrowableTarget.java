package entities.interfaces;

import entities.Interactable;

public interface ThrowableTarget extends Target {
    /**
    Update the targets state after being hit by the given throwable object
    Note that it is of type interactable since it must be cast anyways to access
    the throwable's properties and the throwable implementing throwable is checked before
     @see usecases.Throw
     **/
    String handleHit(Interactable throwable);
}
