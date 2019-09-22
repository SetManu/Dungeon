package games.menace.items;

import games.menace.items.generic.Item;

/**
 * The Weapon a subclass of Item.
 *
 * @author Manuel Werder
 * @version 0.1
 */
public class Weapon extends Item {
    private int attackPoints;

    /**
     * The constructor for the Weapon.
     * @param name The name of the amazing weapon.
     * @param attackPoints Some nice attackPoints for the weapon.
     */
    public Weapon(String name, int attackPoints) {
        super(name);
        this.attackPoints = attackPoints;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    /**
     * toString gives you a nice view of what a Weapon is.
     * @return The nice and beauty.
     */
    @Override
    public String toString() {
        String buildName = super.toString() + " has " + attackPoints + " attack ";
        if (attackPoints == 1) {
            return buildName + "point";
        }
        return buildName + "points";
    }
}
