import java.awt.Rectangle;
import java.util.Random;

public abstract class NormalType extends Pokemon {

    public NormalType(int x, int y, Rectangle bounds) {
        super(x, y, bounds);
    }

    @Override
    public boolean canHarmPokemon(Pokemon other) {
        Random rng = new Random();
        double chance = rng.nextDouble();
        if (inWhite()) {
            if (this.collidesWithPokemon(other) && other instanceof Pokemon
                && (chance >= .5)) {
                return true;
            }
        } else {
            if (this.collidesWithPokemon(other) && other instanceof Pokemon
                && (chance >= .65)) {
                return true;
            }
        }
        return false;
    }
    @Override
    public void move() {
        Random rand = getRand();
        int newMovex = (rand.nextInt(3) - 1);
        int newMovey = (rand.nextInt(3) - 1);
        int height = getImage().getIconHeight();
        int width = getImage().getIconWidth();
        if (getXPos() + newMovex + width < getBounds().getWidth()) {
            setXPos(getXPos() + getSpeed() * newMovex);
        } else {
            setXPos(getXPos() - getSpeed() * newMovex);
        }
        if (getXPos() + newMovex + width <= 0) {
            newMovex = rand.nextInt(3);
            setXPos(getXPos() + getSpeed() * newMovex);
        }
        if (getYPos() + newMovey + height < getBounds().getHeight()) {
            setYPos(getYPos() + getSpeed() * newMovey);
        } else {
            setYPos(getYPos() - getSpeed() * newMovey);
        }
        if (getYPos() + newMovey + height <= 0) {
            newMovey = rand.nextInt(3);
            setYPos(getYPos() + getSpeed() * newMovey);
        }
        if (inWhite()) {
            setHealth(getHealth() + 3);
            setLevel(getLevel() - 10);
        }
        setHealth(getHealth() - 1);
        setLevel(getLevel() + 1);
    }
}
