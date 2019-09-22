package games.menace.items.generic;


/**
 * The Item Superclass provides the basic API for Shields, Weapons and Potions.
 *
 * @author Manuel Werder
 * @version 0.1
 */
public class Item {
    private String name;

    /**
     * The constructor for the Item BaseClass.
     * @param name Type: String. The name of the item.
     */
    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}