// Elisha Catherasoo//
// 101148507 //

package myStore;

import java.util.ArrayList;

/**
 * ProductStockContainer abstract class
 */
public abstract class ProductStockContainer {
    protected ArrayList<Integer> stocks;         // number of each product in shopping cart
    protected ArrayList<Product> products;        // product in the inventory

    ProductStockContainer(ArrayList<Product> prod, ArrayList<Integer> stock) {
        stocks = new ArrayList<>();
        products = new ArrayList<>();

        for (int i = 0; i < prod.size(); i++){
            this.stocks.add(stock.get(i));
            this.products.add(prod.get(i));
        }
    }

    ProductStockContainer() {
        stocks = new ArrayList<>();
        products = new ArrayList<>();
    }

    public abstract void removeProductQuantity(Product prod, int stock);

    /**
     * Get the amount of the products in the shopping cart or inventory
     *
     * @param prod Product object
     * @return int, shopper's shopping cart product amounts.
     */
    public int getProductQuantity(Product prod) {
        if (products.contains(prod)) {
            int index = products.indexOf(prod);
            return stocks.get(index);
        }
        return 0;
    }

    /**
     * Adds stock to customer's shopping cart or inventory.
     *
     * @param prod Product object
     * @param stock int value for the quantity of products
     */
    public void addProductQuantity(Product prod, int stock) {
        if (!products.contains(prod)) {
            products.add(prod);
            stocks.add(stock);
        }
        else {
            int index = products.indexOf(prod);
            int newStock = stocks.get(index) + stock;
            stocks.set(index, newStock);
        }
    }

    /**
     * Get the number of different types of products.
     *
     * @return int, number of products
     */
    public int getNumOfProducts() {
        return products.size();
    }

    /**
     * Get the product names.
     *
     * @return ArrayList<String>, list of product names
     */
    public ArrayList<String> getNames() {
        Product name;
        ArrayList<String> namesList = new ArrayList<>();

        for (Product product : products) {
            name = product;
            namesList.add(name.getName());
        }
        return namesList;
    }

    /**
     * Get the products in the cart or inventory.
     *
     * @return ArrayList<Product>, list of products
     */
    public ArrayList<Product> getProducts() {
        return products;
    }

    public ArrayList<Integer> getStocks() {
        return stocks;
    }

    /**
     * Get product ids.
     *
     * @return ArrayList<Integer>, list of product ids
     */
    public ArrayList<Integer> getProductId() {
        Product id;
        ArrayList<Integer> idsList = new ArrayList<>();

        for (Product product : products) {
            id = product;
            idsList.add(id.getId());
        }
        return idsList;
    }
}
