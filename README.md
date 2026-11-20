# 🎓 Student Management System (Java Swing & OOP)

> **Aplikasi Desktop Manajemen Data & Penilaian Mahasiswa** berbasis **Java Swing** dengan penerapan **Object-Oriented Programming (OOP)** (*Interface*, *Abstract Class*, *Encapsulation*, *Inheritance*, dan *Polymorphism*).

![Java](https://img.shields.io/badge/Language-Java-ED8B00?style=flat-square&logo=openjdk&logoColor=white)
![GUI](https://img.shields.io/badge/GUI-Java%20Swing-007396?style=flat-square)
![Paradigm](https://img.shields.io/badge/Paradigm-OOP-2E8B57?style=flat-square)

---

## 👤 Identitas Penyusun

| Keterangan | Detail |
| :--- | :--- |
| **Nama** | Anak Agung Ayu Intan Putri Maharani |
| **NIM** | F1D02510037 |
| **Kelas** | B |

---

## ✨ Fitur Utama

- **Manajemen Data (CRUD)** — Tambah data (`Simpan`), perbarui data (`Edit`), hapus data (`Hapus`), dan bersihkan form (`Reset`).
- **Kalkulasi Nilai Akhir Otomatis** — Bobot penilaian dihitung secara dinamis sesuai jenis mahasiswa:

| Jenis Mahasiswa | Tugas | UTS | UAS | Rumus Nilai Akhir |
| :--- | :---: | :---: | :---: | :--- |
| **Reguler** (`MahasiswaReguler`) | `30%` | `30%` | `40%` | `0.30*Tugas + 0.30*UTS + 0.40*UAS` |
| **Beasiswa** (`MahasiswaBeasiswa`) | `40%` | `30%` | `30%` | `0.40*Tugas + 0.30*UTS + 0.30*UAS` |

- **Validasi & Keamanan Input** — Mencegah NIM ganda, mewajibkan pengisian NIM dan Nama, membatasi nilai pada rentang `0`–`100`, serta menangani kesalahan input non-angka.
- **Sinkronisasi Tabel & Form** — Klik baris pada tabel untuk memuat data ke form secara otomatis sekaligus mengunci `NIM` agar tidak tertukar saat proses edit.

---

## 🧩 Arsitektur & Penerapan OOP

| Komponen / Kelas | Peran dalam Program | Konsep OOP yang Diterapkan |
| :--- | :--- | :--- |
| `DapatDinilai` | Kontrak method `hitungNilaiAkhir()` | **Interface** |
| `Mahasiswa` | Menyimpan data dasar (`nim`, `nama`, `jurusan`, `tugas`, `uts`, `uas`) & method abstrak `getJenisMahasiswa()` | **Abstract Class & Encapsulation** |
| `MahasiswaReguler` | Implementasi bobot nilai & label jenis `"Reguler"` | **Inheritance & Polymorphism** |
| `MahasiswaBeasiswa` | Implementasi bobot nilai & label jenis `"Beasiswa"` | **Inheritance & Polymorphism** |
| `MahasiswaManager` | Mengelola `ArrayList<Mahasiswa>` (`tambah`, `getSemua`, `cari`, `hapus`) | **Collection & Data Encapsulation** |
| `Main` | Window utama, tata letak GUI, dan *event listener* | **GUI Composition & Event Handling** |

---

## 🖥️ Komponen GUI yang Digunakan

Antarmuka disusun menggunakan **Java Swing** dengan kombinasi `BorderLayout` (tata letak utama kiri, tengah, atas) dan `GridLayout` (form input & tombol), melibatkan **7 komponen utama**:

1. **`JFrame` (`Main`)** — Jendela utama aplikasi berukuran `950x600` yang diposisikan di tengah layar (`setLocationRelativeTo(null)`) dengan `EXIT_ON_CLOSE`.
2. **`JLabel`** — Menampilkan header judul `STUDENT MANAGEMENT SYSTEM` serta label untuk setiap kolom input (`NIM`, `Nama`, `Jurusan`, `Jenis Mahasiswa`, `Nilai Tugas`, `Nilai UTS`, `Nilai UAS`).
3. **`JTextField`** — Kolom input teks satu baris untuk mengisi `NIM`, `Nama`, `Nilai Tugas`, `Nilai UTS`, dan `Nilai UAS`.
4. **`JComboBox<String>`** — Menu *dropdown* untuk memilih **Jurusan** (`Informatika`, `Sistem Informasi`, `Teknik Komputer`, `Manajemen`) dan **Jenis Mahasiswa** (`Reguler`, `Beasiswa`).
5. **`JButton`** — Empat tombol aksi (`Simpan`, `Edit`, `Hapus`, `Reset`) yang memicu logika CRUD melalui *lambda* `ActionListener`.
6. **`JTable` & `DefaultTableModel` (dalam `JScrollPane`)** — Tabel 8 kolom (`NIM`, `Nama`, `Jurusan`, `Jenis`, `Tugas`, `UTS`, `UAS`, `Nilai Akhir`) yang dapat di-*scroll* dengan sel *read-only* (`isCellEditable` mengembalikan `false`).
7. **`JOptionPane`** — Dialog *pop-up* untuk menampilkan pesan sukses/peringatan/error serta konfirmasi `YES_NO_OPTION` sebelum menghapus data.

---

## 🚀 Cara Menjalankan

> **Prasyarat:** Pastikan **JDK** sudah terinstal dan perintah `javac` serta `java` dapat diakses melalui terminal/CMD.

1. Simpan kode sumber ke dalam berkas bernama `Main.java`.
2. Kompilasi program:
   ```bash
   javac Main.java
   ```
3. Jalankan aplikasi:
   ```bash
   java Main
   ```