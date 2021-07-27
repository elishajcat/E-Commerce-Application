// Elisha Catherasoo//
// 101148507 //

package myStore;

import java.util.ArrayList;

/**
 * Store Manager class
 */
public class StoreManager{
	private Inventory inventory;							// inventory they are in charge of
	private ShoppingCart shoppingCart;
	private ArrayList<Integer> cartId = new ArrayList<>();

	/**
	 * Initialize Store Manager
	 */
	public StoreManager() {
		this.inventory = new Inventory();        // inventory the Store Manager is in charge of
		this.shoppingCart = new ShoppingCart(0);
		this.cartId.add(0);
	}

	public ArrayList<Integer> getCartId() {
		return this.cartId;
	}

	/**
	 * Check how much stock is in the inventory
	 *
	 * @param product Product object to get stock of
	 * @return int, product's stock
	 */
	public int getStock(Product product) {
		return inventory.getProductQuantity(product);
	}

	/**
	 * Get a list of every product's stocks.
	 *
	 * @return ArrayList<Integer>, list of stock
	 */
	public ArrayList<Integer> getStockList() {
		return this.inventory.getStocks();
	}

	/**
	 * Add stock to Store Manager's inventory,
	 * @param product Product object to add
	 * @param stock int value for the stock
	 */
	public void addStock(Product product, int stock) {
		inventory.addProductQuantity(product, stock);
	}

	/**
	 * Remove stock to Store Manager's inventory,
	 * @param product Product object to remove
	 * @param stock int value for the stock
	 */
	public void removeStock(Product product, int stock) {
		inventory.removeProductQuantity(product, stock);
	}

	/**
	 * Get product ids in the inventory.
	 *
	 * @return ArrayList<Integer>, list of product ids
	 */
	public ArrayList<Product> getProducts() {
		return inventory.getProducts();
	}

	/**
	 * Get product ids in the shopping cart.
	 *
	 * @return ArrayList<Integer>, list of product ids
	 */
	public ArrayList<Product> getCartProducts() {
		return shoppingCart.getProducts();
	}

	/**
	 * Get product names.
	 *
	 * @return ArrayList<String>, list of product names
	 */
	public ArrayList<String> getNames() {
		return inventory.getNames();
	}

	/**
	 * Get names of products in the cart.
	 *
	 * @return ArrayList<String>, list a product names
	 */
	public ArrayList<String> getCartNames() {
		return shoppingCart.getNames();
	}

	/**
	 * Get product prices.
	 *
	 * @return ArrayList<Double>, list of product prices
	 */
	public ArrayList<Double> getPrices() {
		return inventory.getPrices();
	}

	/**
	 * Get product ids.
	 *
	 * @return ArrayList<Integer>, list of product ids
	 */
	public ArrayList<Integer> getProductsId() {
		return inventory.getProductId();
	}

	/**
	 * Get product id'=s of products in shopping cart.
	 *
	 * @return ArrayList<Integer>, list of product ids
	 */
	public ArrayList<Integer> getCartProductId() {
		return shoppingCart.getProductId();
	}

	/**
	 * Get amount of products in shopping cart.
	 *
	 * @return ArrayList<Integer>, list of amount of products
	 */
	public int getCartStock(Product product) {
		return shoppingCart.getProductQuantity(product);
	}

	/**
	 * Get list of stocks in shopping cart.
	 *
	 * @return ArrayList<Integer>, list of amount of products
	 */
	public ArrayList<Integer> getCartStockList() {
		return shoppingCart.getStocks();
	}

	/**
	 * Get products pictures.
	 *
	 * @return ArrayList<Integer>, list of product pictures
	 */
	public ArrayList<String> getPictures(){
		return inventory.getPictures();
	}

	/**
	 * Get the number of different products in inventory.
	 *
	 * @return int, number of products
	 */
	public int getNumOfProductsInventory() {
		return inventory.getNumOfProducts();
	}

	/**
	 * Get the number of different products in the shopping cart.
	 *
	 * @return int, number of products
	 */
	public int getNumOfProductsCart() {
		return shoppingCart.getNumOfProducts();
	}

	/**
	 * Assign new cart ID to customers
	 *
	 * @return int, cartID
	 */
	public int assignNewCartID() {
		cartId.add(cartId.size());
		return cartId.get(cartId.size() - 1);
	}

	/**
	 * Add to shopping cart.
	 *
	 * @param product Product object to add
	 * @param quantity int value for the quantity of the product to add
	 */
	public void addToShoppingCart(Product product, int quantity) {
		shoppingCart.addProductQuantity(product, quantity);
	}

	/**
	 * Remove from shopping cart.
	 *
	 * @param product Product object to remove
	 * @param quantity int value for the quantity of the product to remove
	 */
	public void removeFromShoppingCart(Product product, int quantity) {
		shoppingCart.removeProductQuantity(product, quantity);
	}

	/**
	 * Check if the customer's desired quantity exists in the Inventory and return the total cost. If there's
	 * not enough stock of the product return -1. Subtract the quantities from the Inventory.
	 *
	 * @return int, transaction total cost
	 */
    public double transaction() {
    	Inventory inv = new Inventory();
    	int totalCost = 0;
    	for (int i = 0; i < getNumOfProductsCart(); i++) {
    		Product product = shoppingCart.getProducts().get(i);

			if (shoppingCart.getStocks().get(i) > inv.getStocks().get(i)) {
				return -1;
			}
		}

		for (int i = 0; i < getNumOfProductsCart(); i++) {
			Product product = shoppingCart.getProducts().get(i);
			totalCost += inventory.getProductInfo(getCartProductId().get(i)).getPrice() * getCartStock(product);
			removeStock(product, getCartStock(product));
    	}
    	
    	return totalCost;
    }
}
