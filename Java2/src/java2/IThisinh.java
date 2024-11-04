package java2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public interface IThisinh {
    Connection getCon() throws SQLException; // Phương thức kết nối đến CSDL
    
    List<Thisinh> getTS(); // Phương thức lấy danh sách thí sinh
    
    void insertTS(Thisinh ts); // Phương thức thêm thí sinh mới
}
