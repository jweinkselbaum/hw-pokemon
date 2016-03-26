/**
 * The abstract Pokemon for the PokeBattle Simulation
 *
 * @author Heather, Aniruddha
 */
import java.util.Random;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import java.awt.Graphics;

public abstract class Pokemon {

    private Rectangle bounds;
    private int xPos;
    private int yPos;
    private ImageIcon image;
    private int health;
    private int level;
    private int children;
    private int speed;
    private static Random rand = new Random();

    /**
     * Constructor
     *
     * Represents a Pokemon in the PokeWorld. Each Pokemon
     * has a location in the world and attributes which help
     * it reproduce and thrive.
     * @param xPos The X position of this Pokemon
     * @param yPos The Y position of this Pokemon
     * @param bounds The boundaries of the PokeWorld where
     *               the Pokemon can exist
     */
    public Pokemon(int xPos, int yPos, Rectangle bounds) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.bounds = bounds;
        this.level = 0;
        this.health = 75;
        this.children = 0;
        this.speed = 20;
    }

    /**
     * @return the bounding rectangle of the PokeWorld
     *             that this Pokemon exists in
     */
    public Rectangle getBounds() {
        return bounds;
    }

    /**
     * @return the X position of this Pokemon
     */
    public int getXPos() {
        return xPos;
    }

    /**
     * @return the Y position of this Pokemon
     */
    public int getYPos() {
        return yPos;
    }


    /**
     * @return the current health value of this Pokemon
     */
    public int getHealth() {
        return health;
    }

    /**
     * @return the current level of this Pokemon
     */
    public int getLevel() {
        return level;
    }

    /**
     * @return the number of children this Pokemon has
     */
    public int getChildren() {
        return children;
    }

    /**
     * @return the current speed of this pokemon
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * @return the Random object used by Pokemon for random events
     */
    public static Random getRand() {
        return rand;
    }

    public ImageIcon getImage() {
        return image;
    }

    /**
    * Sets the image attribute for this pokemon
    * @param image the ImageIcon to use to represent this Pokemon
    */
    public void setImage(ImageIcon image) {
        this.image = image;
    }

    /**
     * Sets the image attribute for this pokemon
     * @param image the path to the image to use for this Pokemon
     */
    public void setImage(String path) {
        this.image = new ImageIcon(path);
    }

    /**
     * Sets the Pokemon's health value
     * @param health The new health of the Pokemon
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Sets the Pokemon's level
     * @param level The new level of the Pokemon
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Sets the number of children of this Pokemon
     * @param children The number of children this Pokemon now has
     */
    public void setChildren(int children) {
        this.children = children;
    }

    /**
     * Sets the speed of this Pokemon
     * @param speed the updated speed
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setXPos(int xPos) {
        this.xPos = xPos;
    }

    public void setYPos(int yPos) {
        this.yPos = yPos;
    }

    /**
     * Should draw the Pokemon at its location.
     * @param Graphics object for drawing use
     */
    public void draw(Graphics g) {
        image.paintIcon(null, g, xPos, yPos);
    }

    public boolean inRed() {
        if (getXPos() > (getBounds().getWidth()) / 2
            && getYPos() < (getBounds().getHeight()) / 2) {
            return true;
        }
        return false;
    }

    public boolean inGreen() {
        if (getXPos() < (getBounds().getWidth()) / 2
            && getYPos() < (getBounds().getHeight()) / 2) {
            return true;
        }
        return false;
    }

    public boolean inBlue() {
        if (getXPos() < (getBounds().getWidth()) / 2
            && getYPos() > (getBounds().getHeight()) / 2) {
            return true;
        }
        return false;
    }

    public boolean inWhite() {
        if (getXPos() > (getBounds().getWidth()) / 2
            && getYPos() > (getBounds().getHeight()) / 2) {
            return true;
        }
        return false;
    }



    /**
     * Will move the Pokemon in a random yet effective manner (it doesn't move
     * in circles). Each time a Pokemon moves, it's health should decrease and
     * its level should increase.
     */
    public void move() {
        double newMovex = (2 * rand.nextDouble() - 1);
        double newMovey = (2 * rand.nextDouble() - 1);
        int height = this.image.getIconHeight();
        int width = this.image.getIconWidth();
        if (xPos + newMovex + width < getBounds().getWidth()) {
            xPos += speed * newMovex;
        } else {
            xPos -= speed * newMovex;
        }
        if (getXPos() + newMovex + width <= 0) {
            newMovex = rand.nextInt(3);
            xPos += getSpeed() * newMovex;
        }
        if (yPos + newMovey + height < getBounds().getHeight()) {
            yPos += speed * newMovey;
        } else {
            yPos -= speed * newMovey;
        }
        if (getYPos() + newMovey + height <= 0) {
            newMovey = rand.nextInt(3);
            yPos += getSpeed() * newMovey;
        }
        health--;
        level++;
    }


    public boolean otherIsAbove(Pokemon other) {
        int height = this.image.getIconHeight();
        int width = this.image.getIconWidth();
        int otherHeight = other.getImage().getIconHeight();
        int otherWidth = other.getImage().getIconWidth();
        int otherY = other.getYPos();
        int otherX = other.getXPos();
        if (yPos <= otherY && yPos >= (otherY - otherHeight)) {
            return true;
        }
        return false;
    }

    public boolean otherIsBelow(Pokemon other) {
        int height = this.image.getIconHeight();
        int width = this.image.getIconWidth();
        int otherHeight = other.getImage().getIconHeight();
        int otherWidth = other.getImage().getIconWidth();
        int otherY = other.getYPos();
        int otherX = other.getXPos();
        if (yPos >= otherY && (yPos - height) <= otherY) {
            return true;
        }
        return false;
    }

    public boolean otherIsRight(Pokemon other) {
        int height = this.image.getIconHeight();
        int width = this.image.getIconWidth();
        int otherHeight = other.getImage().getIconHeight();
        int otherWidth = other.getImage().getIconWidth();
        int otherY = other.getYPos();
        int otherX = other.getXPos();
        if (xPos <= otherX && (xPos + width) >= otherX) {
            return true;
        }
        return false;
    }

    public boolean otherIsLeft(Pokemon other) {
        int height = this.image.getIconHeight();
        int width = this.image.getIconWidth();
        int otherHeight = other.getImage().getIconHeight();
        int otherWidth = other.getImage().getIconWidth();
        int otherY = other.getYPos();
        int otherX = other.getXPos();
        if (xPos >= otherX && xPos <= (otherX + otherWidth)) {
            return true;
        }
        return false;
    }

    /**
     * Returns whether or not this Pokemon is colliding with a given Pokemon.
     * This should be determined by the Pokemon's location and the dimensions of
     * its image.
     * @param other the Pokemon to check for collision with
     * @return true if the two Pokemon have collided, false otherwise
     */
    public boolean collidesWithPokemon(Pokemon other) {
        if (otherIsAbove(other) && (otherIsLeft(other)
            || otherIsRight(other))) {
            return true;
        } else if (otherIsBelow(other) && (otherIsLeft(other)
            || otherIsRight(other))) {
            return true;
        }
        return false;
    }

    /**
     * Returns whether or not the two Pokemon can reproduce
     * @param other the Pokemon to check if this can reproduce with
     * @return true if the two Pokemon can reproduce, false otherwise
     */
    public abstract boolean canReproduceWithPokemon(Pokemon other);

    /**
     * If the two Pokemon are able to reproduce (as determined by
     * canReproduceWithPokemon(Pokemon)), this method returns a new Pokemon of
     * the same type and in the same location. If for some reason, reproduction
     * does not happen, null should be returned.
     * Reproduction rates should be controlled in some manner so that infinite
     * population growth does not occur. This can be acheived in a variety of
     * ways: reproduction probability, limiting total offspring per
     * Pokemon, etc.
     * @param other the Pokemon to reproduce with
     * @return the offspring Pokemon
     */
    public abstract Pokemon reproduceWithPokemon(Pokemon other);

    /**
     * Returns whether or not a Pokemon has surpassed some determined max level.
     * @return true if the Pokemon has passed the specified level,
     *              false otherwise
     */
    public abstract boolean isOld();

    /**
    * Returns whether or not a Pokemon can damage another Pokemon
    * @param other the Pokemon for which to check if harming is possible
    * @return true if this can harm Pokemon a, false otherwise
    */
    public abstract boolean canHarmPokemon(Pokemon other);

    /**
    * Harms another instance of a Pokemon by decreasing its health by a fixed
    * amount.
    * @param other the Pokemon to harm
    */
    public void harmPokemon(Pokemon other) {
        if (canHarmPokemon(other)) {
            other.setHealth(other.getHealth() - 10);
        }
    }

    /**
     * Called when an Pokemon faints.
     */
    public void faint() {
        this.health = 0;
    }

    /**
     * Returns whether or not the Pokemon has fainted (ie health 0).
     * @return true if this Pokemon has fainted, false otherwise
     */
    public boolean hasFainted() {
        if (getHealth() <= 0 || isOld()) {
            return true;
        }
        return false;
    }

}
