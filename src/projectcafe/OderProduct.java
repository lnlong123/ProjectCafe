/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package projectcafe;

import ConnectDb.*;
import Class.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class OderProduct extends javax.swing.JFrame {

    dbconnect db = new dbconnect();
    ArrayList<Product> listOder = new ArrayList<>();
    int idOrder;

    /**
     * Creates new form OderProduct
     */
    public ArrayList<Product> getThucDon(String IDType) {
        ArrayList<Product> list = new ArrayList<>();
        ResultSet result = null;
        String sql = "SELECT * FROM product WHERE IdType = ?";
        try {
            PreparedStatement statement = db.connect().prepareStatement(sql);
            statement.setString(1, IDType);
            result = statement.executeQuery();
            while (result.next()) {
                Product p = new Product();
                p.setID(result.getString("IdProduct"));
                p.setProductName(result.getString("ProductName"));
                p.setPrice(result.getInt("Price"));
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;

    }

    private int check(String s) {
        int i = 0, row = -1;
        if (tblDetails.getRowCount() == 0) {
            row = -1;
        } else {
            while (i < tblDetails.getRowCount()) {
                if (s.equals(tblDetails.getValueAt(i, 0))) {
                    row = i;
                    break;
                } else {
                    row = -1;
                    i++;
                }
            }
        }
        return row;
    }

    public OderProduct(Integer id) {
        this.idOrder = id;
        initComponents();
        ShowProductType();
    }

    private boolean checkIdProduct(String Id) {
        boolean check = true;
        ResultSet rs = db.getOrderDetails(idOrder);
        try {
            if (!rs.isBeforeFirst()) {
                check = true;
            } else {
                while (rs.next()) {
                    if (Id.equals(rs.getString("IdProduct"))) {
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

    public void ShowProductType() {
        ArrayList<ProductType> listProductType = db.ListProductType();
        for (ProductType i : listProductType) {
            JButton btn = new JButton();
            btn.setText(i.getTypeName());
            btn.setName(i.getIDType());
            btn.setPreferredSize(new Dimension(120, 29));
            btn.setForeground(Color.WHITE);
            btn.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
            btn.setBackground(Color.DARK_GRAY);
            PnType.add(btn);
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    PanelMon.removeAll();
                    PanelMon.updateUI();
                    ArrayList<Product> ListThucdon = getThucDon(btn.getName());
                    JPanel[] pn = new JPanel[ListThucdon.size()];
                    for (int i = 0; i < ListThucdon.size(); i++) {
                        pn[i] = new JPanel();
                        pn[i].setName(String.valueOf(i));
                        pn[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
                        pn[i].setBackground(Color.decode("#dfff80"));
                        pn[i].setBorder(BorderFactory.createLineBorder(Color.decode("#a3a375"), 2));
                        pn[i].setPreferredSize(new Dimension(120, 60));
                        pn[i].add(new JLabel("   " + ListThucdon.get(i).getProductName() + "   "));
                        pn[i].add(new JLabel("   " + ListThucdon.get(i).getPrice() + " VNĐ   ")).setForeground(Color.RED);
                        pn[i].addMouseListener(new MouseAdapter() {
                            @Override
                            public void mousePressed(MouseEvent e) {
                                Product product = ListThucdon.get(Integer.parseInt(e.getComponent().getName()));
                                DefaultTableModel tblModel = (DefaultTableModel) tblDetails.getModel();
                                int row = check(product.getID());
                                if (row != -1) {
                                    int n = (int) tblDetails.getValueAt(row, 2);
                                    n += 1;
                                    tblDetails.setValueAt(n, row, 2);
                                } else {
                                    Object data[] = {product.getID(), product.getProductName(), 1, product.getPrice()};
                                    tblModel.addRow(data);
                                }
                            }
                        });
                        PanelMon.add(pn[i]);
                    }
                }
            });
        }
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PnType = new javax.swing.JPanel();
        Label = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetails = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        btndelete = new javax.swing.JButton();
        btnDown = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        BtnOk = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        PanelMon = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        PnType.setBackground(new java.awt.Color(204, 255, 204));

        Label.setBackground(new java.awt.Color(255, 255, 255));
        Label.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Label.setForeground(new java.awt.Color(51, 51, 255));
        Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label.setText("Gọi món");

        tblDetails.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tblDetails.setForeground(new java.awt.Color(0, 204, 204));
        tblDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDetails.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblDetails.setUpdateSelectionOnSort(false);
        tblDetails.setVerifyInputWhenFocusTarget(false);
        tblDetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDetailsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDetails);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setText("Danh sách sản phẩm đã thêm");

        btndelete.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btndelete.setForeground(new java.awt.Color(255, 255, 255));
        btndelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/delete-icon.png"))); // NOI18N
        btndelete.setEnabled(false);
        btndelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteActionPerformed(evt);
            }
        });

        btnDown.setForeground(new java.awt.Color(153, 51, 0));
        btnDown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/math-minus-icon.png"))); // NOI18N
        btnDown.setEnabled(false);
        btnDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDownActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDown)
                .addGap(29, 29, 29)
                .addComponent(btndelete)
                .addGap(25, 25, 25))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addComponent(jLabel2)
                        .addGap(0, 6, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btndelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDown, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84))
        );

        jLabel1.setForeground(new java.awt.Color(255, 102, 102));
        jLabel1.setText("Danh sách sản phẩm");

        BtnOk.setBackground(new java.awt.Color(51, 255, 51));
        BtnOk.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtnOk.setForeground(new java.awt.Color(255, 255, 255));
        BtnOk.setText("Xác nhận");
        BtnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnOkActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setText("Huỷ");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setToolTipText("");

        PanelMon.setBackground(new java.awt.Color(255, 204, 255));
        PanelMon.setAutoscrolls(true);
        PanelMon.setPreferredSize(new java.awt.Dimension(320, 5000));
        jScrollPane2.setViewportView(PanelMon);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(PnType, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtnOk)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Label)
                .addGap(21, 21, 21)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 579, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(PnType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BtnOk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(11, 11, 11))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tblModel = (DefaultTableModel) tblDetails.getModel();
        int i = tblDetails.getSelectedRow();
        tblModel.removeRow(i);

    }//GEN-LAST:event_btndeleteActionPerformed

    private void BtnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnOkActionPerformed
        // TODO add your handling code here:
        boolean check = false;
        for (int i = 0; i < tblDetails.getRowCount(); i++) {
            if (checkIdProduct((String) tblDetails.getValueAt(i, 0))) {
                db.addOrderDetail(idOrder, (String) tblDetails.getValueAt(i, 0), (Integer) tblDetails.getValueAt(i, 2));
                check = true;
            } else {
                db.UpdateAmount((int) tblDetails.getValueAt(i, 2), idOrder, (String) tblDetails.getValueAt(i, 0));
                check = true;
            }
        }
        if (check = true) {
            JOptionPane.showMessageDialog(null, "Thêm món thành công");
            this.setVisible(false);
            MainForm.ShowOrderTotable(MainForm.tblOrder, db.getOrderDetails(idOrder));
            int tongtien = db.getTotalMoney(db.getOrderDetails(idOrder));
            MainForm.lbTongTien.setText(String.valueOf(tongtien) + " VND");
        }
    }//GEN-LAST:event_BtnOkActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDownActionPerformed
        int i = tblDetails.getSelectedRow();
        if ((int) tblDetails.getValueAt(i, 2) > 1) {
            tblDetails.setValueAt((int) tblDetails.getValueAt(i, 2) - 1, i, 2);
        } else {
        }
    }//GEN-LAST:event_btnDownActionPerformed

    private void tblDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDetailsMouseClicked
        // TODO add your handling code here:
        btnDown.setEnabled(true);
        btndelete.setEnabled(true);
    }//GEN-LAST:event_tblDetailsMouseClicked

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnOk;
    private javax.swing.JLabel Label;
    private javax.swing.JPanel PanelMon;
    private javax.swing.JPanel PnType;
    private javax.swing.JButton btnDown;
    private javax.swing.JButton btndelete;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblDetails;
    // End of variables declaration//GEN-END:variables
}
