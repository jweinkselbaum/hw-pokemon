/**
* A fire type pokemon
*
* @author Farhan Tejani
*/

import java.awt.Rectangle;
import java.util.Random;

public abstract class FireType extends Pokemon {

    /**
    * Constructor
    * @param x The X position of this Fire type
    * @param y The Y position of this Fire type
    * @param bounds The bounding Rectangle
    */
    public FireType(int x, int y, Rectangle bounds) {
        super(x, y, bounds);
    }

    @Override
    public boolean canHarmPokemon(Pokemon other) {
        Random rng = new Random();
        double chance = rng.nextDouble();
        if (this.collidesWithPokemon(other) && other instanceof FireType
            && (chance >= .5)) {
            return true;
        } else if (this.collidesWithPokemon(other) && other instanceof WaterType
            && (chance >= .75)) {
            return true;
        } else if (this.collidesWithPokemon(other)
            && other instanceof GrassType && (chance >= .25)) {
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
        int newSpeed = getSpeed() + 9;
        if (getXPos() + newMovex + width <= getBounds().getWidth()) {
            if (inRed()) {
                setXPos(getXPos() + newSpeed * newMovex);
            } else {
                setXPos(getXPos() + getSpeed() * newMovex);
            }
        } else {
            if (inRed()) {
                setXPos(getXPos() - newSpeed * rand.nextInt(3));
            } else {
                setXPos(getXPos() - getSpeed() * rand.nextInt(3));
            }
        }
        if (getXPos() + newMovex + width <= 0) {
            newMovex = rand.nextInt(3);
            if (inRed()) {
                setXPos(getXPos() + newSpeed * newMovex);
            } else {
                setXPos(getXPos() + getSpeed() * newMovex);
            }
        }
        if (getYPos() + newMovey + height <= getBounds().getHeight()) {
            if (inRed()) {
                setYPos(getYPos() + newSpeed * newMovey);
            } else {
                setYPos(getYPos() + getSpeed() * newMovey);
            }
        } else {
            if (inRed()) {
                setYPos(getYPos() - newSpeed * rand.nextInt(3));
            } else {
                setYPos(getYPos() - getSpeed() * rand.nextInt(3));
            }
        }
        if (getYPos() + newMovey + height <= 0) {
            newMovey = rand.nextInt(3);
            if (inRed()) {
                setYPos(getYPos() + newSpeed * newMovey);
            } else {
                setYPos(getYPos() + getSpeed() * newMovey);
            }
        }
        setLevel(getLevel() + 1);
        setHealth(getHealth() - 1);
    }
}
