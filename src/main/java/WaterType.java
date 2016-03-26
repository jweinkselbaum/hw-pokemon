/**
 * A water type pokemon
 *
 * @author Farhan Tejani
 */

import java.awt.Rectangle;
import java.util.Random;

public abstract class WaterType extends Pokemon {

    /**
     * Constructor
     * @param x The X position of this Water Type Pokemon
     * @param y The Y position of this Water Type Pokemon
     * @param bounds The bounding Rectangle
     */
    public WaterType(int x, int y, Rectangle bounds) {
        super(x, y, bounds);
    }

    @Override
    public boolean canHarmPokemon(Pokemon other) {
        Random rng = new Random();
        double chance = rng.nextDouble();
        if (this.collidesWithPokemon(other) && other instanceof WaterType
            && (chance >= .5)) {
            return true;
        } else if (this.collidesWithPokemon(other) && other instanceof GrassType
            && (chance >= .75)) {
            return true;
        } else if (this.collidesWithPokemon(other) && other instanceof FireType
            && (chance >= .25)) {
            return true;
        } else if (this.collidesWithPokemon(other)
            && other instanceof NormalType && (chance >= .65)) {
            return true;
        }

        return false;
    }

    @Override
    public void move() {
        Random rand = getRand();
        int newMovex = ((rand.nextInt(3) - 1));
        int newMovey = ((rand.nextInt(3) - 1));
        int height = getImage().getIconHeight();
        int width = getImage().getIconWidth();
        if (getXPos() + newMovex + width <= getBounds().getWidth()) {
            setXPos(getXPos() + getSpeed() * newMovex);
        } else {
            setXPos(getXPos() - getSpeed() * rand.nextInt(3));
        }
        if (getXPos() + newMovex + width <= 0) {
            newMovex = rand.nextInt(3);
            setXPos(getXPos() + getSpeed() * newMovex);
        }
        if (getYPos() + newMovey + height <= getBounds().getHeight()) {
            setYPos(getYPos() + getSpeed() * newMovey);
        } else {
            setYPos(getYPos() - getSpeed() * rand.nextInt(3));
        }
        if (getYPos() + newMovey + height <= 0) {
            newMovey = rand.nextInt(3);
            setYPos(getYPos() + getSpeed() * newMovey);
        }
        if (inBlue()) {
            setLevel(getLevel() + 2);
        }
        setLevel(getLevel() + 1);
        setHealth(getHealth() - 1);
    }
}
