// Elisha Catherasoo//
// 101148507 //

package myStore;

/**
 * Shopping Cart class
 */
public class ShoppingCart extends ProductStockContainer{
    private int cardId;

    /**
     * Default constructor for a Shopping Cart.
     *
     * @param cartId int value fro cart ID
     */
    public ShoppingCart(int cartId) {
        super();
        this.cardId = cartId;
    }

    /**
     * Removes item from customer's shopping cart.
     *
     * @param prod Product object
     * @param quantity int value for the quantity of products
     */
    public void removeProductQuantity(Product prod, int quantity) {
        int index = products.indexOf(prod);
        int newAmount = stocks.get(index) - quantity;

        if (newAmount <= 0) {
            stocks.remove(index);
            products.remove(prod);
        }
        else {
            stocks.set(index, newAmount);
        }
    }
}
