import java.awt.Rectangle;
import java.util.Random;

public class Poliwhirl extends WaterType {

    /**
     * Constructor
     * @param x The X position of Poliwhirl
     * @param y The Y position of Poliwhirl
     * @param bounds The bounding Rectangle
     */
    public Poliwhirl(int x, int y, Rectangle bounds) {
        super(x, y, bounds);
        setImage("../resources/poliwhirl.png");
    }

    @Override
    public boolean canReproduceWithPokemon(Pokemon other) {
        if (other instanceof Poliwhirl && other.getChildren() < 2
            && this.getChildren() < 2) {
            return true;
        }
        return false;
    }

    @Override
    public Pokemon reproduceWithPokemon(Pokemon other) {
        Random reproOdds = getRand();
        if (canReproduceWithPokemon(other) && collidesWithPokemon(other)
            && reproOdds.nextDouble() < .25) {
            Poliwhirl newbie = new Poliwhirl(getXPos(), getYPos(), getBounds());
            setChildren(getChildren() + 1);
            other.setChildren(other.getChildren() + 1);
            return newbie;
        }
        return null;
    }

    @Override
    public boolean isOld() {
        if (getLevel() > 200) {
            return true;
        }
        return false;
    }

    @Override
    public boolean canHarmPokemon(Pokemon other) {
        Random rng = new Random();
        double chance = rng.nextDouble();
        if (other instanceof Poliwhirl && other.getLevel() > 3) {
            if (chance >= .62) {
                return true;
            }
        }
        if (this.collidesWithPokemon(other) && other instanceof WaterType
            && (chance >= .5) && other.getLevel() > 3) {
            return true;
        } else if (this.collidesWithPokemon(other) && other instanceof GrassType
            && (chance >= .75) && other.getLevel() > 3) {
            return true;
        } else if (this.collidesWithPokemon(other) && other instanceof FireType
            && (chance >= .25) && other.getLevel() > 3) {
            return true;
        } else if (this.collidesWithPokemon(other)
            && other instanceof NormalType && (chance >= .65)
            && other.getLevel() > 3) {
            return true;
        }

        return false;
    }
}
