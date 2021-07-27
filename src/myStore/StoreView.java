// Elisha Catherasoo//
// 101148507 //

package myStore;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Store View class
 */
public class StoreView {
    private StoreManager sm;            //Store View's manager
    private int cartId;                 //card IDs for the customers at Store View
    private JFrame frame;
    private JLabel headerLabel;
    private JPanel mainPanel;
    private JPanel headerPanel;
    private JPanel bodyPanel;
    private JPanel cartPanel;
    private ArrayList<JPanel> products;
    private ArrayList<JPanel> information;
    private ArrayList<JLabel> prodPrice;
    private ArrayList<JLabel> prodStock;
    private JLabel totalText;
    private double total;

    /**
     * Initialize Store View.
     *
     * @param sm      a StoreManager object
     * @param cartID  int value for cart Id
     */
    public StoreView(StoreManager sm, int cartID) {
        this.sm = sm;
        this.cartId = cartID;
        this.frame = new JFrame();
        this.headerLabel = new JLabel("Welcome to The Store! (ID: " + cartId + ")");
        this.mainPanel = new JPanel(new BorderLayout());
        this.headerPanel = new JPanel();
        this.bodyPanel = new JPanel(new GridLayout(sm.getNames().size(), 2, 2, 10));
        this.cartPanel = new JPanel();
        this.products = new ArrayList<>();
        this.information = new ArrayList<>();
        this.prodPrice = new ArrayList<>();
        this.prodStock = new ArrayList<>();
        this.totalText = new JLabel("TOTAL: $0.00");
        this.total = 0.0;

        for (int i = 0; i < sm.getNumOfProductsInventory(); i++) {
            this.products.add(new JPanel());
            this.information.add(new JPanel());
            this.prodPrice.add(new JLabel("Price: $" + sm.getPrices().get(i) + " \n\n"));
            this.prodStock.add(new JLabel("Stock: " + sm.getStockList().get(i) + "\n\n"));
        }
    }

    /**
     * Get colours of a certain brightness. Wow!
     * @return Color : A Color object with the generated colour.
    */
    private Color getColour() {
        int r = (int)(Math.random()*256);
        int g = (int)(Math.random()*256);
        int b = (int)(Math.random()*256);
        double luma = (0.2126 * r) + (0.7152 * g) + (0.0722 * b);

        while (luma < 75) {
            r = (int)(Math.random()*256);
            g = (int)(Math.random()*256);
            b = (int)(Math.random()*256);
            luma = (0.2126 * r) + (0.7152 * g) + (0.0722 * b);
        }
        return new Color(r, g, b);
    }

    /**
     * Get a quit button to quit the program when pressed. There is also a confirmation to check if the user really wants
     * to quit
     *
     * @return JButton, the result of clicking the button, quitting the program
     */
    private JButton getQuitButton() {
        JButton button = new JButton("Quit");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(frame, "Are you sure you want to quit?")
                        == JOptionPane.OK_OPTION) {
                    // close it down!
                    frame.setVisible(false);
                    frame.dispose();
                }
            }
        });

        return button;
    }

    /**
     * Get a button to view the user's shopping cart.
     *
     * @return JButton : a JButton object
     */
    private JButton getViewCartButton() throws IOException {
        BufferedImage image = ImageIO.read(new File("src/myStore/cart.png"));
        Image resized = image.getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING);
        ImageIcon icon = new ImageIcon(resized);

        JButton button = new JButton("View Cart", icon);

        button.addActionListener(new ActionListener() {

            // this method will be called when we click the button
            @Override
            public void actionPerformed(ActionEvent ae) {
                String message = "";
                String tempMessage;

                //print the amount of product, name and price
                for (int i = 0; i < sm.getNumOfProductsCart(); i++) {
                    tempMessage = message;
                    Product product = sm.getProducts().get(i);

                    String name = sm.getCartNames().get(i);
                    int priceIndex = sm.getNames().indexOf(name);

                    message = tempMessage + sm.getCartStockList().get(i) + " | " + sm.getCartNames().get(i)
                            + " | " + sm.getPrices().get(priceIndex) + "\n";                }

                JOptionPane.showMessageDialog(frame, message, "MY CART", JOptionPane.PLAIN_MESSAGE);
            }
        });

        return button;
    }

    /**
     * Get a button to checkout of the program. Buying the item in the shopping cart
     *
     * @return JButton : a JButton object
     */
    private JButton getCheckoutButton() {
        JButton button = new JButton("Checkout");

        button.addActionListener(new ActionListener() {

            // this method will be called when we click the button
            @Override
            public void actionPerformed(ActionEvent ae) {
                String message = "";
                String tempMessage;

                //print the amount of product, name and price
                for(int i = 0; i < sm.getNumOfProductsCart(); i++) {
                    tempMessage = message;

                    String name = sm.getCartNames().get(i);
                    int priceIndex = sm.getNames().indexOf(name);

                    message = tempMessage + sm.getCartStockList().get(i) + " | " + sm.getCartNames().get(i)
                            + " | " + sm.getPrices().get(priceIndex) + "\n";
                }

                tempMessage = message;

                message = tempMessage + "\n" +  "TOTAL: $" + sm.transaction() + "0";

                JOptionPane.showMessageDialog(frame, message, "CHECKOUT", JOptionPane.PLAIN_MESSAGE);

                frame.setVisible(false);
                frame.dispose();
            }
        });

        return button;
    }

    /**
     * Get a button to add items to the user's shopping cart by 1. Removes item from stock.
     *
     * @param prodToAdd int, product index to add
     * @return JButton : a JButton object
    */
    private JButton getAddButton(int prodToAdd) {
        JButton button = new JButton("ADD (+1)");

        // add the action listener
        button.addActionListener(new ActionListener() {

            // this method will be called when we click the button
            @Override
            public void actionPerformed(ActionEvent ae) {
                int index = sm.getProductsId().indexOf(prodToAdd);
                Product product = sm.getProducts().get(index);

                int stock = sm.getStock(product) - 1;

                //check if there's enough stock left
                if (stock < 0) {
                    JOptionPane.showMessageDialog(frame, "You can't add more than " + sm.getCartStock(product) + " " + sm.getNames().get(prodToAdd) + "s to your cart!");
                }
                else {
                    sm.addToShoppingCart(product, 1);
                    sm.removeStock(product, 1);

                    //update stock value
                    prodStock.get(prodToAdd).setText("Stock: " + stock + "\n");

                    total += sm.getPrices().get(prodToAdd);
                    totalText.setText("TOTAL: $" + total + "0");
                }
            }
        });

        return button;
    }

    /**
     * Get a button to remove items from a shopping cart by 1. Adds item back to stock.
     *
     * @param prodToRemove int, product index to remove
     * @return JButton : a JButton object
     */
    private JButton getRemoveButton(int prodToRemove) {
        JButton button = new JButton("REMOVE (-1)");

        // add the action listener
        button.addActionListener(new ActionListener() {

            // this method will be called when we click the button
            @Override
            public void actionPerformed(ActionEvent ae) {
                int index = sm.getProductsId().indexOf(prodToRemove);
                Product product = sm.getProducts().get(index);

                int stock = sm.getStock(product) + 1;
                String name = sm.getNames().get(prodToRemove);

                //check if there are products in the shopping cart
                if (!sm.getCartNames().contains(name)) {
                    JOptionPane.showMessageDialog(frame, "You can't remove items that aren't in your cart!");
                }
                else {
                    sm.removeFromShoppingCart(product, 1);
                    sm.addStock(product, 1);

                    //update stock value
                    prodStock.get(prodToRemove).setText("Stock: " + stock + "\n");
                    total -= sm.getPrices().get(prodToRemove);
                    totalText.setText("Total: $" + total + "0");
                }
            }
        });

        return button;
    }

    /**
     * The GUI to browse, add, remove, and checkout items from the store. Creates the visuals using Swing
     */
    public void displayGUI() throws IOException{
        frame.setTitle("Client StoreView");
        frame.setSize(750, 500);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


        // adding JLabels to the respective JPanel
        headerPanel.add(headerLabel);

        for (int i = 0; i < sm.getNumOfProductsInventory(); i++) {
            products.get(i).setBorder(BorderFactory.createTitledBorder(sm.getNames().get(i).toUpperCase()));

            information.get(i).setBorder(BorderFactory.createTitledBorder("INFO"));
            BoxLayout layout = new BoxLayout(information.get(i), BoxLayout.Y_AXIS);
            information.get(i).setLayout(layout);

            prodPrice.get(i).setAlignmentX(Component.LEFT_ALIGNMENT);
            information.get(i).add(prodPrice.get(i));

            prodStock.get(i).setAlignmentX(Component.LEFT_ALIGNMENT);
            information.get(i).add(prodStock.get(i));

            getAddButton(i).setAlignmentX(Component.LEFT_ALIGNMENT);
            information.get(i).add(getAddButton(i));

            getRemoveButton(i).setAlignmentX(Component.LEFT_ALIGNMENT);
            information.get(i).add(getRemoveButton(i));

            BufferedImage image = ImageIO.read(new File("src/myStore/" + sm.getPictures().get(i)));
            Image resized = image.getScaledInstance(100, 100, Image.SCALE_AREA_AVERAGING);
            JLabel label = new JLabel(new ImageIcon(resized));

            products.get(i).add(label);
        }

        // set the preferred sizes and colours here
        headerPanel.setPreferredSize(new Dimension(250, 50));
        headerPanel.setBackground(Color.white);

        bodyPanel.setSize(new Dimension(100, 100));
        bodyPanel.setBackground(Color.white);

        cartPanel.setPreferredSize(new Dimension(125, 100));
        cartPanel.setBackground(Color.white);

        for (int i = 0; i < sm.getNumOfProductsInventory(); i++) {
            Color color = getColour();
            products.get(i).setSize(new Dimension(125, 100));
            products.get(i).setBackground(color);

            information.get(i).setSize(new Dimension(125, 100));
            information.get(i).setBackground(color);
        }

        // add your JLabels to the panel here
        for (int i = 0; i < sm.getNumOfProductsInventory(); i++) {
            bodyPanel.add(products.get(i));
            bodyPanel.add(information.get(i));
        }

        scrollPane.getViewport().setView(bodyPanel);

        cartPanel.add(getViewCartButton());
        cartPanel.add(getCheckoutButton());
        cartPanel.add(getQuitButton());
        cartPanel.add(totalText);

        mainPanel.add(headerPanel, BorderLayout.PAGE_START);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(cartPanel, BorderLayout.LINE_END);

        // pack
        frame.add(mainPanel);

        // add the window listener
        // we no longer want the frame to close immediately when we press "quit"
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                if (JOptionPane.showConfirmDialog(frame, "Are you sure you want to quit?")
                        == JOptionPane.OK_OPTION) {
                    // close it down!
                    frame.setVisible(false);
                    frame.dispose();
                }
            }
        });

        // the frame is not visible until we set it to be so
        frame.setVisible(true);
    }

    /**
     * Main method
     *
     * @param args String[]
     */
    public static void main(String[] args) throws IOException {
        StoreManager sm = new StoreManager();

        StoreView sv1 = new StoreView(sm, sm.getCartId().get(0));

        sv1.displayGUI();
    }
}



