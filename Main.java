
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

// =====================================GIG
// INTERFACE
// =====================================
interface DapatDinilai {
    double hitungNilaiAkhir();
}

// =====================================
// ABSTRACT CLASS
// =====================================
abstract class Mahasiswa implements DapatDinilai {
    private String nim;
    private String nama;
    private String jurusan;
    private double tugas, uts, uas;

    public Mahasiswa(String nim, String nama, String jurusan,
                     double tugas, double uts, double uas) {
        this.nim = nim;
        this.nama = nama;
        this.jurusan = jurusan;
        this.tugas = tugas;
        this.uts = uts;
        this.uas = uas;
    }

    public abstract String getJenisMahasiswa();

    public String getNim() { return nim; }
    public String getNama() { return nama; }
    public String getJurusan() { return jurusan; }
    public double getTugas() { return tugas; }
    public double getUts() { return uts; }
    public double getUas() { return uas; }

    public void setData(String nama, String jurusan,
                        double tugas, double uts, double uas) {
        this.nama = nama;
        this.jurusan = jurusan;
        this.tugas = tugas;
        this.uts = uts;
        this.uas = uas;
    }
}

// =====================================
// INHERITANCE: MAHASISWA REGULER
// =====================================
class MahasiswaReguler extends Mahasiswa {

    public MahasiswaReguler(String nim, String nama, String jurusan,
                            double tugas, double uts, double uas) {
        super(nim, nama, jurusan, tugas, uts, uas);
    }

    @Override
    public double hitungNilaiAkhir() {
        return getTugas() * 0.30
             + getUts() * 0.30
             + getUas() * 0.40;
    }

    @Override
    public String getJenisMahasiswa() {
        return "Reguler";
    }
}

// =====================================
// INHERITANCE: MAHASISWA BEASISWA
// =====================================
class MahasiswaBeasiswa extends Mahasiswa {

    public MahasiswaBeasiswa(String nim, String nama, String jurusan,
                             double tugas, double uts, double uas) {
        super(nim, nama, jurusan, tugas, uts, uas);
    }

    @Override
    public double hitungNilaiAkhir() {
        return getTugas() * 0.40
             + getUts() * 0.30
             + getUas() * 0.30;
    }

    @Override
    public String getJenisMahasiswa() {
        return "Beasiswa";
    }
}

// =====================================
// CLASS MANAJEMEN DATA
// =====================================
class MahasiswaManager {
    private ArrayList<Mahasiswa> daftar = new ArrayList<>();

    public void tambah(Mahasiswa m) {
        daftar.add(m);
    }

    public ArrayList<Mahasiswa> getSemua() {
        return daftar;
    }

    public Mahasiswa cari(String nim) {
        for (Mahasiswa m : daftar) {
            if (m.getNim().equals(nim)) {
                return m;
            }
        }
        return null;
    }

    public boolean hapus(String nim) {
        Mahasiswa m = cari(nim);
        if (m != null) {
            daftar.remove(m);
            return true;
        }
        return false;
    }
}

// =====================================
// MAIN CLASS + GUI
// =====================================
public class Main extends JFrame {

    private MahasiswaManager manager = new MahasiswaManager();

    private JTextField txtNim, txtNama;
    private JTextField txtTugas, txtUts, txtUas;
    private JComboBox<String> cbJurusan, cbJenis;

    private JTable tabel;
    private DefaultTableModel model;

    public Main() {
        setTitle("Student Management System");
        setSize(950, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        buatGUI();
    }

    private void buatGUI() {
        setLayout(new BorderLayout(10, 10));

        JLabel judul = new JLabel(
            "STUDENT MANAGEMENT SYSTEM",
            SwingConstants.CENTER
        );

        judul.setFont(new Font("Arial", Font.BOLD, 23));
        judul.setForeground(new Color(35, 75, 130));
        judul.setBorder(
            BorderFactory.createEmptyBorder(20, 10, 10, 10)
        );

        add(judul, BorderLayout.NORTH);

        // FORM INPUT
        JPanel form = new JPanel(new GridLayout(9, 2, 8, 8));
        form.setBorder(
            BorderFactory.createTitledBorder("Data Mahasiswa")
        );

        txtNim = new JTextField();
        txtNama = new JTextField();
        txtTugas = new JTextField();
        txtUts = new JTextField();
        txtUas = new JTextField();

        cbJurusan = new JComboBox<>(new String[]{
            "Informatika",
            "Sistem Informasi",
            "Teknik Komputer",
            "Manajemen"
        });

        cbJenis = new JComboBox<>(new String[]{
            "Reguler", "Beasiswa"
        });

        form.add(new JLabel("NIM"));
        form.add(txtNim);

        form.add(new JLabel("Nama"));
        form.add(txtNama);

        form.add(new JLabel("Jurusan"));
        form.add(cbJurusan);

        form.add(new JLabel("Jenis Mahasiswa"));
        form.add(cbJenis);

        form.add(new JLabel("Nilai Tugas"));
        form.add(txtTugas);

        form.add(new JLabel("Nilai UTS"));
        form.add(txtUts);

        form.add(new JLabel("Nilai UAS"));
        form.add(txtUas);

        JButton btnSimpan = new JButton("Simpan");
        JButton btnEdit = new JButton("Edit");
        JButton btnHapus = new JButton("Hapus");
        JButton btnReset = new JButton("Reset");

        form.add(btnSimpan);
        form.add(btnEdit);
        form.add(btnHapus);
        form.add(btnReset);

        JPanel kiri = new JPanel(new BorderLayout());
        kiri.add(form, BorderLayout.NORTH);
        kiri.setPreferredSize(new Dimension(340, 0));

        add(kiri, BorderLayout.WEST);

        // TABEL DATA
        model = new DefaultTableModel(
            new String[]{
                "NIM", "Nama", "Jurusan", "Jenis",
                "Tugas", "UTS", "UAS", "Nilai Akhir"
            }, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabel = new JTable(model);
        tabel.setRowHeight(25);

        JScrollPane scroll = new JScrollPane(tabel);
        scroll.setBorder(
            BorderFactory.createTitledBorder("Daftar Mahasiswa")
        );

        add(scroll, BorderLayout.CENTER);

        // EVENT BUTTON
        btnSimpan.addActionListener(e -> simpanData());
        btnEdit.addActionListener(e -> editData());
        btnHapus.addActionListener(e -> hapusData());
        btnReset.addActionListener(e -> resetForm());

        tabel.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = tabel.getSelectedRow();
                if (row >= 0) isiForm(row);
            }
        });
    }

    private Mahasiswa buatMahasiswa() {
        String nim = txtNim.getText().trim();
        String nama = txtNama.getText().trim();

        if (nim.isEmpty() || nama.isEmpty()) {
            throw new IllegalArgumentException(
                "NIM dan Nama wajib diisi!"
            );
        }

        double tugas = Double.parseDouble(txtTugas.getText());
        double uts = Double.parseDouble(txtUts.getText());
        double uas = Double.parseDouble(txtUas.getText());

        if (tugas < 0 || tugas > 100 ||
            uts < 0 || uts > 100 ||
            uas < 0 || uas > 100) {
            throw new IllegalArgumentException(
                "Nilai harus antara 0 sampai 100!"
            );
        }

        String jurusan = cbJurusan.getSelectedItem().toString();
        String jenis = cbJenis.getSelectedItem().toString();

        if (jenis.equals("Reguler")) {
            return new MahasiswaReguler(
                nim, nama, jurusan, tugas, uts, uas
            );
        }

        return new MahasiswaBeasiswa(
            nim, nama, jurusan, tugas, uts, uas
        );
    }

    private void simpanData() {
        try {
            Mahasiswa m = buatMahasiswa();

            if (manager.cari(m.getNim()) != null) {
                JOptionPane.showMessageDialog(
                    this, "NIM sudah terdaftar!"
                );
                return;
            }

            manager.tambah(m);
            tampilkanData();
            resetForm();

            JOptionPane.showMessageDialog(
                this, "Data berhasil disimpan!"
            );

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                this, "Masukkan nilai dalam format angka!"
            );
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void editData() {
        int row = tabel.getSelectedRow();

        if (row < 0) {
            JOptionPane.showMessageDialog(
                this, "Pilih data yang ingin diedit!"
            );
            return;
        }

        try {
            String nim = txtNim.getText().trim();
            Mahasiswa lama = manager.cari(nim);

            if (lama == null) {
                JOptionPane.showMessageDialog(
                    this, "Data tidak ditemukan!"
                );
                return;
            }

            Mahasiswa baru = buatMahasiswa();

            if (!lama.getJenisMahasiswa().equals(
                    baru.getJenisMahasiswa())) {
                manager.hapus(nim);
                manager.tambah(baru);
            } else {
                lama.setData(
                    baru.getNama(),
                    baru.getJurusan(),
                    baru.getTugas(),
                    baru.getUts(),
                    baru.getUas()
                );
            }

            tampilkanData();
            resetForm();

            JOptionPane.showMessageDialog(
                this, "Data berhasil diperbarui!"
            );

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                this, "Masukkan nilai dalam format angka!"
            );
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void hapusData() {
        int row = tabel.getSelectedRow();

        if (row < 0) {
            JOptionPane.showMessageDialog(
                this, "Pilih data yang ingin dihapus!"
            );
            return;
        }

        String nim = model.getValueAt(row, 0).toString();

        int konfirmasi = JOptionPane.showConfirmDialog(
            this,
            "Yakin ingin menghapus data ini?",
            "Konfirmasi",
            JOptionPane.YES_NO_OPTION
        );

        if (konfirmasi == JOptionPane.YES_OPTION) {
            manager.hapus(nim);
            tampilkanData();
            resetForm();
        }
    }

    private void tampilkanData() {
        model.setRowCount(0);

        for (Mahasiswa m : manager.getSemua()) {
            model.addRow(new Object[]{
                m.getNim(),
                m.getNama(),
                m.getJurusan(),
                m.getJenisMahasiswa(),
                m.getTugas(),
                m.getUts(),
                m.getUas(),
                String.format("%.2f", m.hitungNilaiAkhir())
            });
        }
    }

    private void isiForm(int row) {
        txtNim.setText(model.getValueAt(row, 0).toString());
        txtNama.setText(model.getValueAt(row, 1).toString());
        cbJurusan.setSelectedItem(model.getValueAt(row, 2));
        cbJenis.setSelectedItem(model.getValueAt(row, 3));
        txtTugas.setText(model.getValueAt(row, 4).toString());
        txtUts.setText(model.getValueAt(row, 5).toString());
        txtUas.setText(model.getValueAt(row, 6).toString());

        txtNim.setEditable(false);
    }

    private void resetForm() {
        txtNim.setText("");
        txtNama.setText("");
        txtTugas.setText("");
        txtUts.setText("");
        txtUas.setText("");

        txtNim.setEditable(true);
        tabel.clearSelection();

        cbJurusan.setSelectedIndex(0);
        cbJenis.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }
}
