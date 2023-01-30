/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConnectDb;

import Class.*;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class dbconnect {

    private String hostName = "localhost:3306";
    private String dbName = "coffe";
    private String username = "root";
    private String password = "";

    private String connectionURL = "jdbc:mysql://" + hostName + "/" + dbName;
    

    public Connection connect() {
        //Tạo đối tượng Connection
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(connectionURL, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    public boolean checkid(String a,ResultSet rs) {
        boolean check = true;
        try {
            if(!rs.isBeforeFirst()){
                return true;
            } else{
            while(rs.next()){
                if (a.equals(rs.getString(1))) {
                    check = false;
                    break;
                } else {
                    check = true;
                }
            }
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return check;
    }

    // Lấy danh sách sản phẩm
    public ArrayList<Product> ListProduct() {
        ArrayList<Product> list = new ArrayList<>();
        ResultSet result = null;
        String sql = "SELECT * FROM product INNER JOIN producttype ON product.IdType = producttype.IDType";
        try {
            PreparedStatement statement = connect().prepareStatement(sql);
            result = statement.executeQuery();
            while (result.next()) {
                Product p = new Product();
                p.setID(result.getString("IdProduct"));
                p.setProductName(result.getString("ProductName"));
                p.setIDType(result.getString("IDType"));
                p.setTypeName(result.getString("TypeName"));
                p.setPrice(result.getInt("Price"));
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    // Lấy danh sách loại sản phẩm
    public ArrayList<ProductType> ListProductType() {
        ArrayList<ProductType> list = new ArrayList<>();
        ResultSet result;
        String sql = "SELECT * FROM producttype";
        try {
            PreparedStatement statement = connect().prepareStatement(sql);
            result = statement.executeQuery();
            while (result.next()) {
                ProductType p = new ProductType();
                p.setIDType(result.getString("IDType"));
                p.setTypeName(result.getString("TypeName"));
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    //Tìm kiếm loại sản phẩm 
    public ArrayList<ProductType> SearchProductType(String s) {
        ArrayList<ProductType> list = new ArrayList<>();
        ResultSet result;
        String sql = "SELECT * FROM producttype Where TypeName like '%" + s + "%'";
        try {
            PreparedStatement statement = connect().prepareStatement(sql);
            result = statement.executeQuery();
            while (result.next()) {
                ProductType p = new ProductType();
                p.setIDType(result.getString("IDType"));
                p.setTypeName(result.getString("TypeName"));
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public ArrayList<Product> SearchProduct(String name) {
        ArrayList<Product> list = new ArrayList<>();
        ResultSet result = null;
        String sql = "SELECT * FROM product INNER JOIN producttype ON product.IdType = producttype.IDType Where ProductName like '%" + name + "%'";
        try {
            PreparedStatement statement = connect().prepareStatement(sql);
            result = statement.executeQuery();
            while (result.next()) {
                Product p = new Product();
                p.setID(result.getString("IdProduct"));
                p.setProductName(result.getString("ProductName"));
                p.setIDType(result.getString("IDType"));
                p.setTypeName(result.getString("TypeName"));
                p.setPrice(result.getInt("Price"));
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public ArrayList<User> ListEmp(ResultSet result) {
        ArrayList<User> list = new ArrayList<>();
        try {
            while (result.next()) {
                User user = new User();
                user.setUserName(result.getString("Username"));
                user.setName(result.getString("NameEmp"));
                user.setGender(result.getString("Gender"));
                user.setBirthday(result.getString("Birthday"));
                user.setIdCart(result.getString("IdCart"));
                user.setPhone(result.getString("Phone"));
                user.setEmail(result.getString("Email"));
                user.setAddress(result.getString("Address"));
                user.setPasswd(result.getString("passwd"));
                user.setRole(result.getString("Role"));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    // Hàm show danh sách nhân viên
    public ResultSet getEmployee() {
        ResultSet result = null;
        String sql = "SELECT * FROM employee";
        try {
            Statement statement = connect().createStatement();
            result = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Lấy danh sách bàn
    public ResultSet getTable() {
        ResultSet result = null;
        String sql = "SELECT * FROM tables";
        try {
            Statement statement = connect().createStatement();
            result = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Hàm lấy danh sách sản phẩm dựa trên id hoá đơn
    public ResultSet getOrderDetails(Integer IDOrder) {
        ResultSet result = null;
        String sql = "SELECT * FROM orderdetails JOIN product ON orderdetails.IdProduct = product.IdProduct WHERE IdOrder = " + IDOrder + " ";
        try {
            Statement statement = connect().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    // Hàm tính tổng tiền hoá đơn
    public int getTotalMoney(ResultSet rs){
        int tongtien = 0;
        try {
            while (rs.next()) {
                tongtien += rs.getInt("Amount") * rs.getInt("Price");            
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return tongtien;
    }

    //Hàm thêm chi tiết hoá đơn
    public void addOrderDetail(int IdOrder, String Idproduct, int amount) {
        String sql = "Insert into orderdetails VALUES (?,?,?)";
        try {
            PreparedStatement prest = connect().prepareStatement(sql);
            prest.setInt(1, IdOrder);
            prest.setString(2, Idproduct);
            prest.setInt(3, amount);
            prest.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Thêm món thất bại");
            System.err.println(e);
        }
    }

    public void UpdateAmount(int Amount, int idOrder, String Idproduct) {
        String sql = "Update orderdetails Set Amount = Amount + ? Where IdOrder = ? AND IdProduct = ?";
        PreparedStatement prest;
        try {
            prest = connect().prepareStatement(sql);
            prest.setInt(1, Amount);
            prest.setInt(2, idOrder);
            prest.setString(3, Idproduct);
            prest.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public void UpdateSttTable(String status, String idTable) {
        try {
            String sql2 = "Update tables SET Status = ? Where TableID = ?";
            PreparedStatement pres2 = connect().prepareStatement(sql2);
            pres2.setString(1, status);
            pres2.setString(2, idTable);
            pres2.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public ResultSet getCustommer() {
        ResultSet result = null;
        String sql = "SELECT * FROM customer";
        try {
            Statement statement = connect().createStatement();
            result = statement.executeQuery(sql);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi");
        }
        return result;
    }
    
    public ResultSet getOrder() {
        ResultSet result = null;
        String sql = "SELECT * FROM orderr JOIN employee ON orderr.EmpID = employee.Username LEFT JOIN tables ON orderr.TableID = tables.TableID LEFT JOIN customer ON orderr.CusId = customer.IDCus Where orderr.Status = 1";
        try {
            Statement statement = connect().createStatement();
            result = statement.executeQuery(sql);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi");
        }
        return result;
    }
}
