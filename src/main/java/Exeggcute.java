import java.awt.Rectangle;
import java.util.Random;

public class Exeggcute extends GrassType {
    public Exeggcute(int x, int y, Rectangle bounds) {
        super(x, y, bounds);
        setImage("../resources/exeggcute.png");
    }
    @Override
    public boolean canReproduceWithPokemon(Pokemon other) {
        if (other instanceof Exeggcute && other.getChildren() < 2
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
            Exeggcute newbie = new Exeggcute(getXPos(), getYPos(), getBounds());
            setChildren(getChildren() + 1);
            other.setChildren(other.getChildren() + 1);
            return newbie;
        }
        return null;
    }
    @Override
    public boolean isOld() {
        if (getLevel() > 100) {
            return true;
        }
        return false;
    }

}
