# Sistem Pemesanan CoffeeShop

Sistem pemesanan kopi berbasis GUI (Graphical User Interface) menggunakan Java Swing. Program ini memungkinkan pengguna untuk:

- Memilih jenis kopi dari daftar menu.
- Memasukkan jumlah pesanan.
- Melihat total harga pesanan.
- Menampilkan daftar harga kopi.
- Melakukan pembayaran dan menghitung kembalian.

## Fitur

1. **Tambah Pesanan**  
   Pengguna dapat menambahkan pesanan ke daftar dengan memilih kopi dari menu, memasukkan jumlah pesanan, dan menekan tombol "Tambah Pesanan". Pesanan akan ditampilkan dalam daftar.

2. **Hapus Pesanan**  
   Menghapus semua pesanan dari daftar dengan menekan tombol "Hapus Pesanan".

3. **Tampilkan Harga**  
   Menampilkan daftar harga kopi dalam sebuah dialog untuk referensi pengguna.

4. **Pembayaran**  
   Pengguna dapat memasukkan jumlah uang yang dibayarkan dan sistem akan menghitung total harga serta kembalian. Jika jumlah pembayaran kurang, sistem akan memberikan peringatan.

## Struktur Kode

### Kelas Utama

- **Order**: Representasi dari pesanan kopi yang berisi informasi nama kopi, jumlah, harga per unit, dan total harga.
- **OrderManager**: Mengelola daftar pesanan, termasuk menambah, menghapus, dan menghitung total harga.
- **CoffeeShopGUI**: Kelas utama yang menyediakan antarmuka pengguna untuk sistem pemesanan kopi.

### Metode Penting

- `OrderManager.calculateTotalPrice()`: Menghitung total harga dari semua pesanan.
- `CoffeeShopGUI.updateOrderList()`: Memperbarui tampilan daftar pesanan dengan informasi terkini.
- `CoffeeShopGUI.showPriceList()`: Menampilkan daftar harga kopi dalam kotak dialog.
- `CoffeeShopGUI.payButton ActionListener`: Mengelola proses pembayaran dan menghitung kembalian.

## Cara Menggunakan

1. Jalankan program dengan menjalankan metode `main()` di kelas `CoffeeShopGUI`.
2. Pilih jenis kopi dari menu dropdown.
3. Masukkan jumlah pesanan pada kolom "Jumlah Pesanan".
4. Klik "Tambah Pesanan" untuk menambahkan pesanan.
5. Ulangi langkah 2-4 untuk menambahkan pesanan lain.
6. Klik "Tampilkan Harga" untuk melihat daftar harga kopi.
7. Masukkan jumlah pembayaran pada kolom "Jumlah Pembayaran".
8. Klik "Bayar" untuk menyelesaikan pembayaran.

## Prasyarat

- Java Development Kit (JDK) versi 8 atau lebih baru.
- IDE seperti IntelliJ IDEA, Eclipse, atau NetBeans (opsional).

## Instalasi dan Kompilasi

1. Clone repositori atau salin file kode sumber ke dalam direktori lokal.
2. Buka file di IDE atau gunakan terminal untuk mengkompilasi:
   ```bash
   javac CoffeeShopGUI.java
   ```
3. Jalankan program:
   ```bash
   java CoffeeShopGUI
   ```

## Daftar Harga Kopi

| Nama Kopi   | Harga (IDR) |
|-------------|-------------|
| Espresso    | 20,000      |
| Cappuccino  | 25,000      |
| Latte       | 30,000      |
| Americano   | 22,000      |
| Mocha       | 28,000      |






