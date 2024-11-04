
package java2;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.List;

public class GUI_insertTS extends JFrame {
    // Các thành phần giao diện
    private JTextField txtSoBD, txtHoTen, txtTongD;
    private JComboBox<String> cmbNganhH;
    private JRadioButton rdoNam, rdoNu;
    private JButton btnAdd;
    private JTable table;
    private DefaultTableModel tableModel;
    private ButtonGroup genderGroup;

    private XLTS xlts; // Đối tượng xử lý dữ liệu

    public GUI_insertTS() {
        // Khởi tạo giao diện
        setTitle("Thêm Thí Sinh");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        xlts = new XLTS(); // Tạo đối tượng XLTS để xử lý dữ liệu
        
        // Tạo các JTextField
        txtSoBD = new JTextField(15);
        txtHoTen = new JTextField(15);
        txtTongD = new JTextField(15);
        
        // Tạo JComboBox với các ngành học
        cmbNganhH = new JComboBox<>(new String[]{"Trí tuệ nhân tạo", "Cơ khí", "Công trình thủy"});
        
        // Tạo JRadioButton cho giới tính
        rdoNam = new JRadioButton("Nam");
        rdoNu = new JRadioButton("Nữ");
        genderGroup = new ButtonGroup();
        genderGroup.add(rdoNam);
        genderGroup.add(rdoNu);
        
        // Tạo JButton thêm thí sinh mới
        btnAdd = new JButton("Thêm thí sinh mới");
        
        // Tạo JTable để hiển thị thông tin thí sinh
        tableModel = new DefaultTableModel(new Object[]{"Số báo danh", "Họ tên", "Giới tính", "Ngành học", "Tổng điểm"}, 0);
        table = new JTable(tableModel);
        
        // Sắp xếp các thành phần trên giao diện
        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));
        panel.add(new JLabel("Số báo danh:"));
        panel.add(txtSoBD);
        panel.add(new JLabel("Họ tên:"));
        panel.add(txtHoTen);
        panel.add(new JLabel("Tổng điểm:"));
        panel.add(txtTongD);
        panel.add(new JLabel("Ngành học:"));
        panel.add(cmbNganhH);
        panel.add(rdoNam);
        panel.add(rdoNu);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(btnAdd, BorderLayout.SOUTH);

        // Hiển thị tất cả thí sinh khi bắt đầu chạy JFrame
        loadAllStudents();

        // Xử lý sự kiện khi nhấn nút "Thêm thí sinh mới"
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });
    }

    // Phương thức load tất cả thí sinh từ database và hiển thị lên JTable
    private void loadAllStudents() {
        List<Thisinh> danhSachThiSinh = xlts.getTS();
        tableModel.setRowCount(0); // Xóa dữ liệu cũ
        for (Thisinh ts : danhSachThiSinh) {
            String gender = ts.getGT();
            tableModel.addRow(new Object[]{
                    ts.getSoBD(),
                    ts.getHoTen(),
                    gender,
                    ts.getNganhH(),
                    ts.getTongD()
            });
        }
    }

    // Phương thức thêm thí sinh mới vào database và cập nhật JTable
    private void addStudent() {
        try {
            int soBD = Integer.parseInt(txtSoBD.getText());
            String hoTen = txtHoTen.getText();
            String gt = rdoNam.isSelected() ? "Nam" : "Nu";
            String nganhH = (String) cmbNganhH.getSelectedItem();
            double tongD = Double.parseDouble(txtTongD.getText());

            Thisinh ts = new Thisinh(soBD, hoTen, gt, nganhH, tongD);
            xlts.insertTS(ts); // Thêm vào database
            tableModel.addRow(new Object[]{soBD, hoTen, gt, nganhH, tongD}); // Thêm vào JTable

            JOptionPane.showMessageDialog(this, "Thêm thí sinh thành công!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi khi thêm thí sinh: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GUI_insertTS().setVisible(true);
        });
    }
}

