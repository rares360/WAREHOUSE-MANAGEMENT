package presentation;

import javax.swing.*;
import java.awt.*;

public class OrdersTableView extends JFrame {

    private JTable tableView;

    private JScrollPane jScrollPane;


    public OrdersTableView(){

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.getContentPane().setBackground(Color.DARK_GRAY);
        this.setBackground(Color.LIGHT_GRAY);
        this.setBounds(100, 100, 800, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        this.tableView=new JTable();
        tableView.setBounds(270, 19, 504, 331);
        this.getContentPane().add(tableView);
        this.jScrollPane = new JScrollPane();
        jScrollPane.setBounds(270, 19, 504, 331);
        this.getContentPane().add(jScrollPane);
        jScrollPane.setViewportView(tableView);

        this.setVisible(false);
    }

    public JTable getTableView() {
        return tableView;
    }

    public void setTableView(JTable tableView) {
        this.tableView = tableView;
    }

    public void showTable(JTable table){ jScrollPane.setViewportView(table);}
}
