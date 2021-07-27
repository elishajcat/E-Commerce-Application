// Elisha Catherasoo//
// 101148507 //

package myStore;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Inventory class
 */
public class Inventory extends ProductStockContainer{
    private ArrayList<String> pictures = new ArrayList<>();

    /**
     * Default constructor for an Inventory.
     */
    public Inventory() {
        super(new ArrayList<Product>(Arrays.asList(new Product("food", 0, 10.0),
                new Product("shirt", 1, 15.0), new Product("shoe", 2, 30.0))),
                new ArrayList<Integer>(Arrays.asList(20, 10, 40)));

        this.pictures.add("food.png");
        this.pictures.add("shirt.png");
        this.pictures.add("shoe.png");
    }

    /**
     * Remove stock of a product. There can't be negative stock. If the stock reaches 0, leave it
     *
     * @param prod the Product object to remove
     * @param stock int value for a product's stock to add
     */
    public void removeProductQuantity(Product prod, int stock) {
        if (!products.contains(prod)) {
            products.add(prod);
            stocks.add(0);
        }

        int index = products.indexOf(prod);

        int newStock = stocks.get(index) - stock;

        if (newStock <= 0) {
                newStock = 0;
        }

        stocks.set(index, newStock);
    }

    /**
     * Get list of product prices.
     *
     * @return ArrayList<Double>, list of product prices
     */
    public ArrayList<Double> getPrices() {
        Product price;
        ArrayList<Double> pricesList = new ArrayList<>();

        for (Product product : products) {
            price = product;
            pricesList.add(price.getPrice());
        }
        return pricesList;
    }

    /**
     * Get product info from the product id
     *
     * @param productId int value for a product ID
     * @return String[], Product info
     */
    public Product getProductInfo(int productId) {
        Product id;
        ArrayList<Integer> idsList = new ArrayList<>();

        for (Product product : products) {
            id = product;
            idsList.add(id.getId());
        }

        int index = idsList.indexOf(productId);

        return products.get(index);
    }

    /**
     * Get list of pictures of the products
     *
     * @return ArrayList<String> list of product pictures
     */
    public ArrayList<String> getPictures() {
        return this.pictures;
    }
}
