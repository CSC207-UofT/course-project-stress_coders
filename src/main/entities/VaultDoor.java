package entities;

public class VaultDoor extends Door {
    public VaultDoor(String id, String[] hints, String answer) {
        super(id, hints, "Oh no a vault door, this one will be harder to crack! Good luck!", answer);
    }

    @Override
    public String userAttempt(String input) {
        return super.userAttempt(input);
    }

    public VaultDoor(){}
}
