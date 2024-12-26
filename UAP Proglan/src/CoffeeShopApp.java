import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Kelas Order untuk menyimpan informasi pesanan kopi.
 */
class Order {
    private String coffeeName;
    private int quantity;
    private double price;

    /**
     * Konstruktor untuk membuat objek Order.
     *
     * @param coffeeName Nama kopi yang dipesan.
     * @param quantity   Jumlah kopi yang dipesan.
     * @param price      Harga per kopi.
     */

    public Order(String coffeeName, int quantity, double price) {
        this.coffeeName = coffeeName;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * Mengambil nama kopi.
     *
     * @return Nama kopi.
     */
    public String getCoffeeName() {
        return coffeeName;
    }

    /**
     * Mengambil jumlah kopi yang dipesan.
     *
     * @return Jumlah kopi.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Mengambil harga per kopi.
     *
     * @return Harga per kopi.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Menghitung total harga dari pesanan.
     *
     * @return Total harga pesanan.
     */
    public double getTotalPrice() {
        return quantity * price;
    }
}

/**
 * Kelas OrderManager untuk mengelola daftar pesanan.
 */
class OrderManager {
    private List<Order> orders;

    /**
     * Konstruktor untuk membuat objek OrderManager.
     */
    public OrderManager() {
        orders = new ArrayList<>();
    }

    /**
     * Menambahkan pesanan ke dalam daftar pesanan.
     *
     * @param order Pesanan yang akan ditambahkan.
     */
    public void addOrder(Order order) {
        orders.add(order);
    }

    /**
     * Mengambil daftar pesanan.
     *
     * @return Daftar pesanan.
     */
    public List<Order> getOrders() {
        return orders;
    }

    /**
     * Menghapus semua pesanan dari daftar.
     */
    public void clearOrders() {
        orders.clear();
    }
}

/**
 * Kelas CoffeeShopGUI untuk antarmuka pengguna aplikasi pemesanan kopi.
 */
class CoffeeShopGUI extends JFrame {
    private JComboBox<String> coffeeMenu;
    private JTextField quantityField;
    private JTextArea orderListArea;
    private OrderManager orderManager;

    // Daftar menu kopi dan harga (dibuat lebih rapi dengan HashMap)
    private final String[] coffeeNames = {"Espresso", "Cappuccino", "Latte", "Americano", "Mocha"};
    private final double[] coffeePrices = {20000, 25000, 30000, 22000, 28000};
    // Menggunakan HashMap untuk menghubungkan nama kopi dengan harga
    private java.util.HashMap<String, Double> coffeePricesMap = new java.util.HashMap<>();

    /**
     * Konstruktor untuk membuat objek CoffeeShopGUI.
     */
    public CoffeeShopGUI() {
        orderManager = new OrderManager();
        setTitle("Coffee Shop Order System");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Inisialisasi HashMap
        for (int i = 0; i < coffeeNames.length; i++) {
            coffeePricesMap.put(coffeeNames[i], coffeePrices[i]);
        }

        coffeeMenu = new JComboBox<>(coffeeNames);
        quantityField = new JTextField(10);
        JButton addButton = new JButton("Add Order");
        JButton clearButton = new JButton("Clear Orders");
        JButton priceButton = new JButton("Show Prices");
        orderListArea = new JTextArea(10, 30);
        orderListArea.setEditable(false);

        add(new JLabel("Select Coffee:"));
        add(coffeeMenu);
        add(new JLabel("Quantity:"));
        add(quantityField);
        add(addButton);
        add(clearButton);
        add(priceButton);
        add(new JScrollPane(orderListArea));

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String coffeeName = (String) coffeeMenu.getSelectedItem();
                    int quantity = Integer.parseInt(quantityField.getText());
                    if (quantity <= 0) {
                        JOptionPane.showMessageDialog(null, "Quantity must be greater than 0.");
                        return;
                    }
                    double price = coffeePricesMap.get(coffeeName); // Menggunakan HashMap
                    Order order = new Order(coffeeName, quantity, price);
                    orderManager.addOrder(order);
                    updateOrderList();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Quantity must be a number.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage());
                }
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orderManager.clearOrders();
                updateOrderList();
            }
        });

        priceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPriceList();
            }
        });
    }

    // Menggunakan HashMap untuk mendapatkan harga kopi
    private double getCoffeePrice(String coffeeName) {
        return coffeePricesMap.getOrDefault(coffeeName, 0.0);
    }

    private void updateOrderList() {
        StringBuilder sb = new StringBuilder();
        for (Order order : orderManager.getOrders()) {
            sb.append(order.getCoffeeName()).append(" - ")
                    .append(order.getQuantity()).append(" x ")
                    .append(order.getPrice()).append(" = ")
                    .append(order.getTotalPrice()).append(" IDR\n");
        }
        orderListArea.setText(sb.toString());
    }

    private void showPriceList() {
        StringBuilder priceList = new StringBuilder("Daftar Harga Kopi:\n");
        for (String coffee : coffeeNames) {
            priceList.append(coffee).append(": ").append(coffeePricesMap.get(coffee)).append(" IDR\n");
        }
        JOptionPane.showMessageDialog(null, priceList.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CoffeeShopGUI gui = new CoffeeShopGUI();
            gui.setVisible(true);
        });
    }
}