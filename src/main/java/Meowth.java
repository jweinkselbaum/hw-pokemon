import java.awt.Rectangle;
import java.util.Random;

public class Meowth extends NormalType {
    public Meowth(int x, int y, Rectangle bounds) {
        super(x, y, bounds);
        setImage("../resources/meowth.png");
    }

    @Override
    public boolean canReproduceWithPokemon(Pokemon other) {
        if (other instanceof Meowth && other.getChildren() < 2
            && this.getChildren() < 2) {
            return true;
        }
        return false;
    }

    @Override
    public Pokemon reproduceWithPokemon(Pokemon other) {
        Random reproOdds = getRand();
        if (canReproduceWithPokemon(other) && collidesWithPokemon(other)
            && reproOdds.nextDouble() < .15) {
            Meowth newbie = new Meowth(getXPos(), getYPos(), getBounds());
            setChildren(getChildren() + 1);
            other.setChildren(other.getChildren() + 1);
            return newbie;
        }
        return null;
    }

    @Override
    public boolean isOld() {
        if (getLevel() > 125) {
            return true;
        }
        return false;
    }
}
