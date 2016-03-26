import java.awt.Rectangle;
import java.util.Random;

public class Torterra extends GrassType {

    /**
     * Constructor
     * @param x The X position of Torterra
     * @param y The Y position of Torterra
     * @param bounds The bounding Rectangle
     */
    public Torterra(int x, int y, Rectangle bounds) {
        super(x, y, bounds);
        setImage("../resources/torterra.png");
    }

    @Override
    public boolean canReproduceWithPokemon(Pokemon other) {
        if (other instanceof GrassType && other.getChildren() < 2
            && this.getChildren() < 2) {
            return true;
        }
        return false;
    }

    @Override
    public Pokemon reproduceWithPokemon(Pokemon other) {
        Random reproOdds = getRand();
        if (canReproduceWithPokemon(other) && collidesWithPokemon(other)
            && reproOdds.nextDouble() < .2) {
            Torterra newbie = new Torterra(getXPos(), getYPos(), getBounds());
            setChildren(getChildren() + 1);
            other.setChildren(other.getChildren() + 1);
            return newbie;
        }
        return null;
    }

    @Override
    public boolean isOld() {
        if (getLevel() > 75) {
            return true;
        }
        return false;
    }

    @Override
    public boolean canHarmPokemon(Pokemon other) {
        Random rng = new Random();
        double chance = rng.nextDouble();
        if (other instanceof Poliwhirl && other.getLevel() > 3) {
            if (chance >= .3) {
                return true;
            }
        }
        if (this.collidesWithPokemon(other) && other instanceof GrassType
            && (chance >= .5) && other.getLevel() > 3) {
            return true;
        } else if (this.collidesWithPokemon(other) && other instanceof FireType
            && (chance >= .65) && other.getLevel() > 3) {
            return true;
        } else if (this.collidesWithPokemon(other) && other instanceof WaterType
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
