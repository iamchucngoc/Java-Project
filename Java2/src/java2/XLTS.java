
package java2;

import java.sql.Connection;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class XLTS implements IThisinh {
    //Thuộc tính kết nối 
    private Connection cn;
    //Ham khoi tao lop XLTS , tao ket noi khi doi tuong duoc tao
    public XLTS(){
        try{
            cn = getCon();
        }catch(SQLException e){
            System.out.println("Khong the ket noi den csdl"+e.getMessage());
        }
    }
    
    @Override
     public Connection getCon() throws SQLException {
        String driverName = "com.mysql.cj.jdbc.Driver"; // Driver MySQL
        String jdbcUrl = "jdbc:mysql://localhost:3306/csdl?useSSL=false&allowPublicKeyRetrieval=true"; // Chuỗi kết nối MySQL
        String user = "ngoc"; // Tài khoản MySQL
        String pass = "1412"; // Mật khẩu MySQL

        try {
            Class.forName(driverName); // Nạp driver MySQL
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return DriverManager.getConnection(jdbcUrl, user, pass); // Kết nối đến MySQL
    }
     
     @Override
     // Lấy tất cả thông tin thí sinh từ bảng tbThisinh trong csdl
    public  List<Thisinh> getTS() {
        List<Thisinh> danhSachThiSinh = new ArrayList<>();

        try  {
            String sql = "SELECT * FROM tbThisinh";
            PreparedStatement stmt = cn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int soBD = rs.getInt("SoBD");
                String hoTen = rs.getString("Hoten");
                String gT = rs.getString("GT");
                String nganhH = rs.getString("NganhH");
                double tongD = rs.getDouble("TongD");
                //Tao doi tuong thisinh với các dữ liệu đã lấy từ csdl
                Thisinh ts = new Thisinh(soBD, hoTen, gT, nganhH, tongD);
                danhSachThiSinh.add(ts);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return danhSachThiSinh;
    }
    
    @Override
        
    //Ham nhap moi mot thi sinh vao bang csdl
    public  void insertTS(Thisinh ts) {
        String sql = "INSERT INTO tbThisinh(SoBD, Hoten, GT, NganhH, TongD) VALUES(?,?,?,?,?)";
        try ( PreparedStatement stmt = cn.prepareStatement(sql)) {
            stmt.setInt(1, ts.getSoBD());
            stmt.setString(2, ts.getHoTen());
            stmt.setString(3, ts.getGT());
            stmt.setString(4, ts.getNganhH());
            stmt.setDouble(5, ts.getTongD());
            
             int rowsInserted = stmt.executeUpdate();
             if (rowsInserted > 0){
                 System.out.println("Thi sinh da duoc them thanh cong!");
             }
        

    } catch (SQLException e){
       System.out.println(e);
}

}

    
   
}
