package presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;

public class MainView extends JFrame{

    private JButton btnClients;
    private JButton btnOrders;
    private JButton btnProducts;

    private JFrame frame;

    public MainView() {
        frame = new JFrame();
        this.getContentPane().setBackground(new Color(64, 64, 64));
        this.setBounds(100, 100, 800, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        btnClients = new JButton("CLIENTS");
        btnClients.setBounds(281, 11, 200, 70);
        this.getContentPane().add(btnClients);

        btnOrders = new JButton("ORDERS");
        btnOrders.setBounds(281, 145, 200, 70);
        this.getContentPane().add(btnOrders);

        btnProducts = new JButton("PRODUCTS");
        btnProducts.setBounds(281, 280, 200, 70);
        this.getContentPane().add(btnProducts);

        this.setVisible(true);
    }
    public void getBtnClients(ActionListener action){btnClients.addActionListener(action);}
    public void getBtnOrders(ActionListener action){btnOrders.addActionListener(action);}
    public void getBtnProducts(ActionListener action){btnProducts.addActionListener(action);}
}
