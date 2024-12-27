import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Kelas Order mewakili pesanan kopi dengan detail seperti nama kopi, jumlah, dan harga.
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
     * @param price      Harga per unit kopi.
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
     * Mengambil harga per unit kopi.
     *
     * @return Harga per unit kopi.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Menghitung total harga pesanan.
     *
     * @return Total harga pesanan.
     */
    public double getTotalPrice() {
        return quantity * price;
    }
}

/**
 * Kelas OrderManager mengelola daftar pesanan kopi.
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

    /**
     * Menghitung total harga dari semua pesanan.
     *
     * @return Total harga semua pesanan.
     */
    public double calculateTotalPrice() {
        return orders.stream().mapToDouble(Order::getTotalPrice).sum();
    }
}

/**
 * Kelas CoffeeShopGUI menyediakan antarmuka pengguna grafis untuk sistem pemesanan kopi.
 */
class CoffeeShopGUI extends JFrame {
    private JComboBox<String> coffeeMenu;
    private JTextField quantityField;
    private JTextField paymentField;
    private JTextArea orderListArea;
    private OrderManager orderManager;

    private final String[] coffeeNames = {"Espresso", "Cappuccino", "Latte", "Americano", "Mocha"};
    private final double[] coffeePrices = {20000, 25000, 30000, 22000, 28000};
    private HashMap<String, Double> coffeePricesMap = new HashMap<>();

    /**
     * Konstruktor untuk membuat CoffeeShopGUI.
     * Menginisialisasi komponen GUI dan penanganan event.
     */
    public CoffeeShopGUI() {
        orderManager = new OrderManager();
        setTitle("Sistem Pemesanan Kopi");
        setSize(500, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        for (int i = 0; i < coffeeNames.length; i++) {
            coffeePricesMap.put(coffeeNames[i], coffeePrices[i]);
        }

        coffeeMenu = new JComboBox<>(coffeeNames);
        quantityField = new JTextField(10);
        paymentField = new JTextField(10);
        JButton addButton = new JButton("Tambah Pesanan");
        JButton clearButton = new JButton("Hapus Pesanan");
        JButton priceButton = new JButton("Tampilkan Harga");
        JButton payButton = new JButton("Bayar");
        orderListArea = new JTextArea(15, 30);
        orderListArea.setEditable(false);

        add(new JLabel("Pilih Coffee:"));
        add(coffeeMenu);
        add(new JLabel("Jumlah Pesanan:"));
        add(quantityField);
        add(addButton);
        add(clearButton);
        add(priceButton);
        add(new JLabel("Jumlah Pembayaran:"));
        add(paymentField);
        add(payButton);
        add(new JScrollPane(orderListArea));

        addButton.addActionListener(new ActionListener() {
            /**
             * Menangani penambahan pesanan ke daftar saat tombol diklik.
             * Memvalidasi input dan memperbarui tampilan daftar pesanan.
             *
             * @param e Event aksi.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String coffeeName = (String) coffeeMenu.getSelectedItem();
                    int quantity = Integer.parseInt(quantityField.getText());
                    if (quantity <= 0) {
                        JOptionPane.showMessageDialog(null, "Jumlah harus lebih dari 0.");
                        return;
                    }
                    double price = coffeePricesMap.get(coffeeName);
                    Order order = new Order(coffeeName, quantity, price);
                    orderManager.addOrder(order);
                    updateOrderList();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Jumlah harus berupa angka.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + ex.getMessage());
                }
            }
        });

        clearButton.addActionListener(new ActionListener() {
            /**
             * Menangani penghapusan semua pesanan dari daftar saat tombol diklik.
             *
             * @param e Event aksi.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                orderManager.clearOrders();
                updateOrderList();
            }
        });

        priceButton.addActionListener(new ActionListener() {
            /**
             * Menampilkan daftar harga kopi saat tombol diklik.
             *
             * @param e Event aksi.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                showPriceList();
            }
        });

        payButton.addActionListener(new ActionListener() {
            /**
             * Menangani proses pembayaran saat tombol diklik.
             * Memvalidasi input, menghitung kembalian, dan menampilkan hasil.
             *
             * @param e Event aksi.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double paymentAmount = Double.parseDouble(paymentField.getText());
                    double totalPrice = orderManager.calculateTotalPrice();

                    if (paymentAmount < totalPrice) {
                        JOptionPane.showMessageDialog(null, "Uang tidak Cukup! Total harga: " + totalPrice + " IDR.");
                    } else {
                        double change = paymentAmount - totalPrice;
                        JOptionPane.showMessageDialog(null, "Pembayaran berhasil! Kembalian: " + change + " IDR.");
                        orderManager.clearOrders();
                        updateOrderList();
                        paymentField.setText("");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Jumlah pembayaran harus berupa angka.");
                }
            }
        });
    }

    /**
     * Memperbarui tampilan daftar pesanan dengan pesanan terkini dan total harga.
     */
    private void updateOrderList() {
        StringBuilder sb = new StringBuilder();
        for (Order order : orderManager.getOrders()) {
            sb.append(order.getCoffeeName()).append(" - ")
                    .append(order.getQuantity()).append(" x ")
                    .append(order.getPrice()).append(" = ")
                    .append(order.getTotalPrice()).append(" IDR\n");
        }
        sb.append("\nTotal Harga: ").append(orderManager.calculateTotalPrice()).append(" IDR");
        orderListArea.setText(sb.toString());
    }

    /**
     * Menampilkan daftar harga kopi dalam kotak dialog.
     */
    private void showPriceList() {
        StringBuilder priceList = new StringBuilder("Daftar Harga Kopi:\n");
        for (String coffee : coffeeNames) {
            priceList.append(coffee).append(": ").append(coffeePricesMap.get(coffee)).append(" IDR\n");
        }
        JOptionPane.showMessageDialog(null, priceList.toString());
    }

    /**
     * Metode utama untuk menjalankan aplikasi CoffeeShopGUI.
     *
     * @param args Argumen baris perintah (tidak digunakan).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CoffeeShopGUI gui = new CoffeeShopGUI();
            gui.setVisible(true);
        });
    }
}
