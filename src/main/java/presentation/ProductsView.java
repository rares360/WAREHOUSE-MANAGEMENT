package presentation;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

public class ProductsView extends JFrame {

    private JFrame frame;
    private JTextField textFieldQuantity;
    private JTextField textFieldName;
    private JTextField textFieldId;
    private JTextField textFieldPrice;
    private JTable tableView;
    private JScrollPane jScrollPane;

    private JButton btnInsert;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnView;

    public ProductsView() {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.getContentPane().setBackground(Color.DARK_GRAY);
        this.setBackground(Color.LIGHT_GRAY);
        this.setBounds(100, 100, 800, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        /*JLabel lblID = new JLabel("ID");
        lblID.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblID.setForeground(Color.WHITE);
        lblID.setBounds(10, 11, 70, 30);
        this.getContentPane().add(lblID);*/

        JLabel lblName = new JLabel("NAME");
        lblName.setForeground(Color.WHITE);
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblName.setBounds(10, 52, 70, 30);
        this.getContentPane().add(lblName);

        JLabel lblQuantity = new JLabel("QUANTITY");
        lblQuantity.setForeground(Color.WHITE);
        lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblQuantity.setBounds(10, 93, 90, 30);
        this.getContentPane().add(lblQuantity);

        JLabel lblPrice = new JLabel("PRICE");
        lblPrice.setForeground(Color.WHITE);
        lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblPrice.setBounds(10, 134, 70, 30);
        this.getContentPane().add(lblPrice);

        this.textFieldQuantity=new JTextField();
        textFieldQuantity.setBounds(110, 101, 150, 20);
        this.getContentPane().add(textFieldQuantity);
        textFieldQuantity.setColumns(10);

        this.textFieldName=new JTextField();
        textFieldName.setColumns(10);
        textFieldName.setBounds(110, 60, 150, 20);
        this.getContentPane().add(textFieldName);

        /*this.textFieldId=new JTextField();
        textFieldId.setColumns(10);
        textFieldId.setBounds(110, 19, 150, 20);
        this.getContentPane().add(textFieldId);*/

        this.textFieldPrice=new JTextField();
        textFieldPrice.setColumns(10);
        textFieldPrice.setBounds(110, 142, 150, 20);
        this.getContentPane().add(textFieldPrice);

        this.tableView=new JTable();
        tableView.setBounds(270, 19, 504, 331);
        this.getContentPane().add(tableView);
        this.jScrollPane = new JScrollPane();
        jScrollPane.setBounds(270, 19, 504, 331);
        this.getContentPane().add(jScrollPane);
        jScrollPane.setViewportView(tableView);

        btnView = new JButton("VIEW ALL PRODUCTS");
        btnView.setBackground(Color.WHITE);
        btnView.setForeground(Color.DARK_GRAY);
        btnView.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnView.setBounds(10, 327, 250, 23);
        this.getContentPane().add(btnView);

        btnDelete = new JButton("DELETE PRODUCT");
        btnDelete.setForeground(Color.DARK_GRAY);
        btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnDelete.setBackground(Color.WHITE);
        btnDelete.setBounds(10, 293, 250, 23);
        this.getContentPane().add(btnDelete);

        btnUpdate = new JButton("UPDATE PRODUCT");
        btnUpdate.setForeground(Color.DARK_GRAY);
        btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnUpdate.setBackground(Color.WHITE);
        btnUpdate.setBounds(10, 259, 250, 23);
        this.getContentPane().add(btnUpdate);

        btnInsert = new JButton("INSERT PRODUCT");
        btnInsert.setForeground(Color.DARK_GRAY);
        btnInsert.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnInsert.setBackground(Color.WHITE);
        btnInsert.setBounds(10, 225, 250, 23);
        this.getContentPane().add(btnInsert);

        this.setVisible(false);
    }
    public void getBtnInsert(ActionListener action){btnInsert.addActionListener(action);}
    public void getBtnDelete(ActionListener action){btnDelete.addActionListener(action);}
    public void getBtnUpdate(ActionListener action){btnUpdate.addActionListener(action);}
    public void getBtnView(ActionListener action){btnView.addActionListener(action);}



    public String getTextFieldQuantity() {
        return textFieldQuantity.getText();
    }

    public void setTextFieldQuantity(JTextField textFieldAddress) {
        this.textFieldQuantity.setText(String.valueOf(textFieldAddress));
    }

    public JTable getTableView() {
        return tableView;
    }

    public void setTableView(JTable tableView) {
        this.tableView = tableView;
    }

    public String getTextFieldName() {
        return textFieldName.getText();
    }

    public void setTextFieldName(JTextField textFieldName) {
        this.textFieldName.setText(String.valueOf(textFieldName));
    }

    public String getTextFieldId() {
        return textFieldId.getText();
    }

    public void setTextFieldId(JTextField textFieldId) {
        this.textFieldId.setText(String.valueOf(textFieldId));
    }

    public String getTextFieldPrice() {
        return textFieldPrice.getText();
    }

    public void setTextFieldPrice(JTextField textFieldEmail) {
        this.textFieldPrice.setText(String.valueOf(textFieldEmail));
    }
    public void showTable(JTable table){ jScrollPane.setViewportView(table);}
}
