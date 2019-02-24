package product_inventory;

import java.awt.HeadlessException;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;


public class Main_Window extends javax.swing.JFrame {


    String ImagePath=null;
    
    public Main_Window() {
        initComponents();
        showTableData();
        //getConnection();
    }

    public Connection getConnection(){
        
        Connection con;
        try{
        con = DriverManager.getConnection("jdbc:mysql://localhost/inventory","root","");         
            //JOptionPane.showMessageDialog(null, "Connected Successfully");
            return con;
        }
        catch(SQLException  e){
        JOptionPane.showMessageDialog(null, " Not Connected");
            return  null;
        }
    }
    
    //Resize Image
    public ImageIcon ResizeImage(String ImagePath,byte[] pic){
        
         ImageIcon mypic = null;
         
         if(ImagePath!=null){
             mypic=new ImageIcon(ImagePath);
         }
         else{
             mypic=new ImageIcon(pic);
         }
        
         Image img = mypic.getImage();
         Image img2 = img.getScaledInstance(img_area.getWidth(), img_area.getHeight(), Image.SCALE_SMOOTH);
         ImageIcon image = new ImageIcon(img2);
         
         return image;
        
        
    }
    
    //Fill the table
    // 1- Fill ArrayList with data

    public ArrayList<Product_Inventory> getProducts(){
        
        ArrayList<Product_Inventory>  products = new ArrayList<Product_Inventory>();
        
        Connection con =getConnection();
        String fetch = "SELECT * FROM products";
        
        Statement st;
        ResultSet rs;
        
        
        try {
            st=con.createStatement();
            rs=st.executeQuery(fetch);
            
            Product_Inventory product;
            
            while(rs.next()){
                
                product = new Product_Inventory(rs.getInt("id"), rs.getString("name"),rs.getString("add_date"),Float.parseFloat(rs.getString("price")),rs.getBytes("image") );
                
                products.add(product);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return products;
        
    }
    
        // 2- Populate the table
    
    public void showTableData(){
        
        ArrayList<Product_Inventory> list = getProducts(); 
        DefaultTableModel model = (DefaultTableModel) JTable.getModel();    
        //Clearing Table Content
        model.setRowCount(0);
        Object [] row = new Object[4];
        for(int i=0; i<list.size();i++){
            
            row[0]=list.get(i).getId();
            row[1]=list.get(i).getName();
            row[2]=list.get(i).getPrice();
            row[3]=list.get(i).getAdd_Date();
         
            model.addRow(row);
        }
        
    }
    
public void ShowItem(int index) throws ParseException{
    
    txt_id.setText(Integer.toString(getProducts().get(index).getId()));
    txt_name.setText(getProducts().get(index).getName());
    txt_price.setText(Float.toString(getProducts().get(index).getPrice()));
    
//    Date addDate;
//        addDate = new SimpleDateFormat("dd/mm/yyyy").parse((String)getProducts().get(index).getAdd_Date());
//        
//        //String strDate = dateFormat.format(addDate);
       
    txt_adddate.setText((String)getProducts().get(index).getAdd_Date());
    
    img_area.setIcon(ResizeImage(null, getProducts().get(index).getPicture()));
    
    
}
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_name = new javax.swing.JTextField();
        txt_id = new javax.swing.JTextField();
        txt_price = new javax.swing.JTextField();
        txt_adddate = new datechooser.beans.DateChooserCombo();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTable = new javax.swing.JTable();
        btn_update = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        btn_choose_img = new javax.swing.JButton();
        btn_add = new javax.swing.JButton();
        img_area = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setForeground(new java.awt.Color(51, 0, 255));

        jLabel1.setFont(new java.awt.Font("Microsoft PhagsPa", 1, 18)); // NOI18N
        jLabel1.setText("NAME:");

        jLabel4.setFont(new java.awt.Font("Microsoft PhagsPa", 1, 18)); // NOI18N
        jLabel4.setText("PRICE:");

        jLabel5.setFont(new java.awt.Font("Microsoft PhagsPa", 1, 18)); // NOI18N
        jLabel5.setText("ID:");

        jLabel6.setFont(new java.awt.Font("Microsoft PhagsPa", 1, 18)); // NOI18N
        jLabel6.setText("ADD-DATE:");

        jLabel7.setFont(new java.awt.Font("Microsoft PhagsPa", 1, 18)); // NOI18N
        jLabel7.setText("PICTURE:");

        JTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "NAME", "PRICE ", "ADD DATE"
            }
        ));
        JTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTable);
        JTable.getAccessibleContext().setAccessibleDescription("");
        JTable.getAccessibleContext().setAccessibleParent(txt_id);

        btn_update.setBackground(new java.awt.Color(51, 255, 51));
        btn_update.setText("Update");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });

        btn_delete.setBackground(new java.awt.Color(153, 0, 0));
        btn_delete.setText("Delete");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        btn_choose_img.setLabel("Choose Image");
        btn_choose_img.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_choose_imgActionPerformed(evt);
            }
        });

        btn_add.setBackground(new java.awt.Color(0, 51, 204));
        btn_add.setText("Add");
        btn_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addActionPerformed(evt);
            }
        });

        img_area.setBackground(new java.awt.Color(255, 51, 102));
        img_area.setForeground(new java.awt.Color(51, 51, 255));

        jLabel2.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 255));
        jLabel2.setText("INVENTORY");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(188, 188, 188)
                        .addComponent(btn_choose_img))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(btn_add)
                                .addGap(59, 59, 59)
                                .addComponent(btn_update)
                                .addGap(71, 71, 71)
                                .addComponent(btn_delete))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel6)))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_adddate, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_price, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(img_area, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addComponent(jLabel2)))
                .addContainerGap(256, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt_price, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_adddate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jLabel7))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(img_area, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(btn_choose_img))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_add, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_update)
                    .addComponent(btn_delete))
                .addContainerGap(102, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        
        if(txt_id.getText()!=null){
            String UpdateSql=null;
            PreparedStatement ps=null;
                    
            Connection con =getConnection();     
            
            if(ImagePath!=null){
            UpdateSql = "UPDATE products SET name=? , price=? , add_date=? WHERE id=? ";
                try {
                    ps= con.prepareStatement(UpdateSql);
                    ps.setString(1, txt_name.getText());
                    ps.setString(2, txt_price.getText());
                    String date1 = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
                    ps.setString(3, date1);
                    ps.setInt(4, Integer.parseInt(txt_id.getText()));
                    
                    ps.executeUpdate();
                    showTableData();
                    JOptionPane.showMessageDialog(null, "Data Updated successfully");
                } catch (SQLException ex) {
                    Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
                }
            
                    }
        }
    }//GEN-LAST:event_btn_updateActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        
        if(txt_id.getText()!=null){
            
            String DeleteSql=null;
            PreparedStatement ps=null;
                    
            Connection con =getConnection();     
            
            DeleteSql = "DELETE FROM products WHERE id=? ";
            try {
                ps= con.prepareStatement(DeleteSql);
                ps.setInt(1, Integer.parseInt(txt_id.getText()));
                ps.executeUpdate();
                    
                showTableData();
                JOptionPane.showMessageDialog(null, "Data Deleted successfully");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Data Not Deleted ");
                Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
            }    
        }
        else{
            JOptionPane.showMessageDialog(null, "Data Not Deleted , Enter correct ID");
        }
        
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_choose_imgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_choose_imgActionPerformed
       
        JFileChooser file=new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images", "jpg","png");
        file.addChoosableFileFilter(filter);
        
        int result = file.showSaveDialog(null);
        
        if(result == JFileChooser.APPROVE_OPTION){
            
            File selectedFile = file.getSelectedFile();
            String path=selectedFile.getAbsolutePath();
            img_area.setIcon(ResizeImage(path,null));
            ImagePath=path;
        }
        else{
            System.out.println("NO File Selected");
        }
    }//GEN-LAST:event_btn_choose_imgActionPerformed

    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed
        
        
        if(ImagePath!=null){
            
            try{
                 Connection con =getConnection();
                 String sql= "INSERT INTO products(name,price,add_date,image,id)"+ "values(?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, txt_name.getText());
            ps.setString(2, txt_price.getText());
            
           // SimpleDateFormat dateformat = new SimpleDateFormat("mm-dd-yyyy");
            
//            Date date;
//            JDateChooser date_choo = new JDateChooser(); 
//            
//                date = txt_adddate.getDate();
//            
//              SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//               String adddate = dateformat.format(txt_adddate.getDate());
                String date1 = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
                ps.setString(3, date1);
   
                try {
                    InputStream img = new FileInputStream(new File(ImagePath));
                    ps.setBlob(4, img);
                    //System.out.println("next");
                    ps.setInt(5, Integer.parseInt(txt_id.getText()));
                    ps.executeUpdate();
                    //System.out.println("Data entered successfully");
                    showTableData();
                    JOptionPane.showMessageDialog(null, "Data entered successfully");
                } catch (Exception ex) {
                    System.out.println("Data not entered");
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            catch(SQLException e){              
            }
        }
        else{
            JOptionPane.showMessageDialog(null, " Enter all fields ");
        }
//        
//        System.out.println("Name :"+ txt_name.getText());
//        System.out.println("Price :"+ txt_price.getText());
        //System.out.println("id :"+ txt_id.getText());
       // System.out.println("Image :"+ ImagePath);
        
    }//GEN-LAST:event_btn_addActionPerformed

    private void JTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTableMouseClicked
      
        
        try {
            int index= JTable.getSelectedRow();
            ShowItem(index);
        } catch (ParseException ex) {
            Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
        }
   
        
    }//GEN-LAST:event_JTableMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main_Window().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JTable;
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_choose_img;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_update;
    private javax.swing.JLabel img_area;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private datechooser.beans.DateChooserCombo txt_adddate;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_name;
    private javax.swing.JTextField txt_price;
    // End of variables declaration//GEN-END:variables
}
