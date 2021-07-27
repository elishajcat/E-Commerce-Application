// Elisha Catherasoo//
// 101148507 //

package myStore;

/**
 * Product class
 */
public class Product {
	private String name;                      // the product's name
    private int id;                           // the product's id
    private double price;                        //the product's price

    /**
     * default constructor for a Product.
     *
     * @param name String value for product name
     * @param id int value for product ID
     * @param price double value for a product's price
     */
    public Product(String name, int id, double price) {
        this.name = name;
        this.id = id;
        this.price = price;
    }

    /**
     * Get the name of this product.
     *
     * @return String, product's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the id of this product.
     *
     * @return int, product's id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Get the price of this product.
     *
     * @return int, product's price
     */
    public double getPrice() {
        return this.price;
    }

}
