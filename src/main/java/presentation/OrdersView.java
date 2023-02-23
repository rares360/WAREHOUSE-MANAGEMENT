package presentation;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

public class OrdersView extends JFrame {

    private JTextField textFieldProductQty;

    private JButton btnAdd;
    private JButton btnView;

    private JScrollPane jScrollPaneClients;
    private JScrollPane jScrollPaneProducts;
    private JTable tableViewClients;
    private JTable tableViewProducts;


    public OrdersView() {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.getContentPane().setBackground(Color.DARK_GRAY);
        this.setBackground(Color.LIGHT_GRAY);
        this.setBounds(100, 100, 800, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        JLabel lblID = new JLabel("QUANTITY");
        lblID.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblID.setForeground(Color.WHITE);
        lblID.setBounds(10, 11, 70, 20);
        this.getContentPane().add(lblID);

        textFieldProductQty = new JTextField();
        textFieldProductQty.setColumns(10);
        textFieldProductQty.setBounds(110, 12, 150, 20);
        this.getContentPane().add(textFieldProductQty);

        btnView = new JButton("VIEW ALL ORDERS");
        btnView.setBackground(Color.WHITE);
        btnView.setForeground(Color.DARK_GRAY);
        btnView.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnView.setBounds(10, 327, 250, 23);
        this.getContentPane().add(btnView);

        btnAdd = new JButton("ADD ORDER");
        btnAdd.setForeground(Color.DARK_GRAY);
        btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnAdd.setBackground(Color.WHITE);
        btnAdd.setBounds(10, 293, 250, 23);
        this.getContentPane().add(btnAdd);

        tableViewClients = new JTable();
        tableViewClients.setBounds(270, 19, 504, 150);
        this.getContentPane().add(tableViewClients);
        this.jScrollPaneClients = new JScrollPane();
        jScrollPaneClients.setBounds(270, 19, 504, 150);
        this.getContentPane().add(jScrollPaneClients);
        jScrollPaneClients.setViewportView(tableViewClients);


        tableViewProducts = new JTable();
        tableViewProducts.setBounds(270, 180, 504, 150);
        this.getContentPane().add(tableViewProducts);
        this.jScrollPaneProducts = new JScrollPane();
        jScrollPaneProducts.setBounds(270, 180, 504, 150);
        this.getContentPane().add(jScrollPaneProducts);
        jScrollPaneProducts.setViewportView(tableViewProducts);

        this.setVisible(false);
    }

    public JTable getTableViewClients() {
        return tableViewClients;
    }

    public void setTableViewClients(JTable tableViewClients) {
        this.tableViewClients = tableViewClients;
    }

    public JTable getTableViewProducts() {
        return tableViewProducts;
    }

    public void setTableViewProducts(JTable tableViewProducts) {
        this.tableViewProducts = tableViewProducts;
    }

    public String getTextFieldProductQty() {
        return textFieldProductQty.getText();
    }

    public void setTextFieldProductQty(JTextField textFieldProductQty) {
        this.textFieldProductQty = textFieldProductQty;
    }

    public void getBtnAdd(ActionListener action){btnAdd.addActionListener(action);}
    public void getBtnView(ActionListener action){btnView.addActionListener(action);}

    public void showTableClients(JTable table){ jScrollPaneClients.setViewportView(table);}

    public void showTableProducts(JTable table){ jScrollPaneProducts.setViewportView(table);}
}
