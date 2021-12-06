package entities.minigames;

import entities.Interactable;
import entities.characters.Character;
import entities.characters.Enemy;
import entities.characters.Player;
import entities.interfaces.Duelable;

import java.util.Objects;
import java.util.Random;
import java.util.Optional;

public class Joust extends Interactable implements Duelable {

    Player player;
    boolean defeatedEnemy;
    int defeatedAmount = 50;
    /**
     * Construct a Joust
     *
     * @param id the Joust object's id
     * @param p The current player participating in the joust
     */
    public Joust(String id, Player p) {
        super(id, "duel: joust=[joust_id]");
        player = p;
    }

    /**
     * playerWon returns which an Optional wrapper containing the character that won the joust, or
     * Optional.empty().
     *
     * As of now, it randomly decides who wins the joust immediately.
     * After the joust winner is decided, we set this.setCompleted(true) to signify that
     * the interactable set has completed.
     *
     * In future, we can make it use more features of Enemy and Player to deal damage and have
     * more rounds, and use them each have weapons such as an Axe or a Lance.
     *
     * We use Optional so that we can verify the String parameter check is valid
     *
     * @param  check    a String used to decide if the joust game occured,
     *                  if its equal to "yes", then we return an Optional of player or enemy if the joust was
     *                  successful, and empty if it was unsuccessful, and
     *                  otherwise we return an empty Optional.
     *
     * @return          a character, either the enemy or the player depending on who wins the joust
     */
    @Override
    public Optional<Character> playerWon(String check) {

        if (Objects.equals(check, "yes")) {

            Enemy enemy = new Enemy("joustEnemy", this.player, defeatedAmount);

            Optional<Enemy> enemyOptional = Optional.of(enemy);
            Optional<Player> playerOptional = Optional.of(player);

            Random r = new Random();
            int chosen = r.nextInt(2);

            if (chosen == 0 && enemyOptional.isPresent()) {
                this.setCompleted(true);
                // we know enemyOptional is not empty, so it must be enemy.
                // we do this to turn it into an Optional of character for return purposes
                // since in other parts its possible to return an empty optional
                Character enemyCharacter = (Character) enemyOptional.get();
                Optional<Character> result = Optional.of(enemyCharacter);
                return result;  // player lost the Joust to enemy

            } else if (chosen == 1 && playerOptional.isPresent()) {
                this.setCompleted(true);
                defeatedEnemy = true;

                Character playerCharacter = (Character) playerOptional.get();
                Optional<Character> result = Optional.of(playerCharacter);

                return result;
            }

            this.setCompleted(true);
            defeatedEnemy = true;
            return Optional.empty();
            // player won the Joust and defeated enemy, getting valueDefeated defeatedAmount
        }

        else {  // if check is any other string value
            this.setCompleted(true);
            defeatedEnemy = true;
            return Optional.empty();
        }

    }

    /**
     * Return the defeatedAmount field.
     *
     * @return defeatedAmount the amount the player recieves after defeated enemy
     */
    public int getValueDefeated() {

        if (defeatedEnemy) {
            return defeatedAmount;
        }

        return  -1;
    }

    public Joust(){}
  
    public void setPlayer(Player p) {
        this.player = p;
    }
}