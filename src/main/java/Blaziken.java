import java.awt.Rectangle;
import java.util.Random;

public class Blaziken extends FireType {

    /**
    * Constructor
    * @param x The X position of Blaziken
    * @param y The Y position of Blaziken
    * @param bounds The bounding Rectangle
    */
    public Blaziken(int x, int y, Rectangle bounds) {
        super(x, y, bounds);
        setImage("../resources/blaziken.png");
    }

    @Override
    public boolean canReproduceWithPokemon(Pokemon other) {
        if (other instanceof Blaziken && other.getChildren() < 2
            && this.getChildren() < 2) {
            return true;
        }
        return false;
    }

    @Override
    public Pokemon reproduceWithPokemon(Pokemon other) {
        Random reproOdds = getRand();
        if (canReproduceWithPokemon(other) && collidesWithPokemon(other)
            && reproOdds.nextDouble() < .3) {
            Blaziken newbie = new Blaziken(getXPos(), getYPos(), getBounds());
            setChildren(getChildren() + 1);
            other.setChildren(other.getChildren() + 1);
            return newbie;
        }
        return null;
    }

    @Override
    public boolean isOld() {
        if (getLevel() > 150) {
            return true;
        }
        return false;
    }

    @Override
    public boolean canHarmPokemon(Pokemon other) {
        Random rng = getRand();
        double chance = rng.nextDouble();
        if (this.collidesWithPokemon(other) && other instanceof FireType) {
            if (this.getLevel() > other.getLevel() && other.getLevel() > 3) {
                if (chance >= .1) {
                    return true;
                }
            } else {
                if (chance >= .88) {
                    return true;
                }
            }
        } else if (this.collidesWithPokemon(other) && other instanceof WaterType
            && (chance >= .75) && other.getLevel() > 3) {
            return true;
        } else if (this.collidesWithPokemon(other) && other instanceof GrassType
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
