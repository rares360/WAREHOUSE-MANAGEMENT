package presentation;

import bll.ClientsBLL;
import bll.OrdersBLL;
import bll.ProductsBLL;
import bll.validators.InvalidData;
import dao.ClientsDAO;
import jdk.jshell.EvalException;
import model.Clients;
import model.Orders;
import model.Products;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private MainView mainView;
    private ClientsView clientsView=new ClientsView();
    private ProductsView productsView=new ProductsView();
    private OrdersView ordersView=new OrdersView();
    private OrdersTableView ordersTableView=new OrdersTableView();

    private ClientsBLL clientBLL=new ClientsBLL();
    private OrdersBLL ordersBLL=new OrdersBLL();
    private ProductsBLL productsBLL=new ProductsBLL();

    private Clients client;
    private Products product;

    public Controller(MainView mainView){
        this.mainView=mainView;
        this.mainView.getBtnClients(new BtnClientsListener());
        this.mainView.getBtnOrders(new BtnOrdersListener());
        this.mainView.getBtnProducts(new BtnProductsListener());

        this.clientsView.getBtnView(new ClientsBtnViewListener());
        this.clientsView.getBtnInsert(new ClientsBtnInsertListener());
        this.clientsView.getBtnUpdate(new ClientsBtnUpdateListener());
        this.clientsView.getBtnDelete(new ClientsBtnDeleteListener());

        this.productsView.getBtnView(new ProductsBtnViewListener());
        this.productsView.getBtnInsert(new ProductsBtnInsertListener());
        this.productsView.getBtnUpdate(new ProductsBtnUpdateListener());
        this.productsView.getBtnDelete(new ProductsBtnDeleteListener());

        this.ordersView.getBtnAdd(new OrdersBtnAddListener());
        this.ordersView.getBtnView(new OrdersBtnViewListener());

    }

    public JTable createTable(Object[] objects){
        ArrayList<String> columns = new ArrayList<>();
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        for (Field field : objects[0].getClass().getDeclaredFields()){
            field.setAccessible(true);
            columns.add(field.getName());
        }
        for (int j = 0; j < objects.length; j++){
            data.add(new ArrayList<>());
            for (Field field : objects[j].getClass().getDeclaredFields()){
                field.setAccessible(true);
                Object v;
                try{
                    v = field.get(objects[j]);
                    data.get(j).add(v.toString());
                }catch (IllegalArgumentException e){
                    e.printStackTrace();
                }catch (IllegalAccessException e){
                    e.printStackTrace();
                }
            }
        }
        String data_array[][] = new String[data.size()][data.get(0).size()];
        for (int i = 0; i < data.size(); i++){
            for (int j = 0; j < data.get(i).size(); j++){
                data_array[i][j] = data.get(i).get(j);
            }
        }
        JTable table = new JTable();
        table.setModel(new DefaultTableModel(data_array, columns.toArray()));
         return table;
    }

    class BtnClientsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            clientsView.setVisible(true);
            clientsView.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);}
    }
    class BtnOrdersListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ordersView.setVisible(true);
            List<Clients> clientsList=clientBLL.findAllClients();
            JTable tableClients = createTable(clientsList.toArray());
            ordersView.showTableClients(tableClients);

            List<Products> productsList=productsBLL.findAllProducts();
            JTable tableProducts = createTable(productsList.toArray());
            ordersView.showTableProducts(tableProducts);

            ordersView.setTableViewClients(tableClients);
            ordersView.setTableViewProducts(tableProducts);

            ordersView.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        }
    }
    class BtnProductsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            productsView.setVisible(true);
            productsView.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        }
    }


    //Clients
    class ClientsBtnViewListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Clients> clientsList=clientBLL.findAllClients();
            JTable table = createTable(clientsList.toArray());
            clientsView.showTable(table);
            clientsView.setTableView(table);
        }
    }
    class ClientsBtnInsertListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(clientsView.getTextFieldName().isEmpty() || clientsView.getTextFieldAddress().isEmpty() || clientsView.getTextFieldEmail().isEmpty()){
                JOptionPane.showMessageDialog(clientsView, "Please don't leave the boxes empty!");
            }else {
                // String id=clientsView.getTextFieldId();
                String name= clientsView.getTextFieldName();
                String address=clientsView.getTextFieldAddress();
                String email=clientsView.getTextFieldEmail();
                client=new Clients(name,address,email);
                try {
                    clientBLL.insertClient(client);
                    List<Clients> clientsList=clientBLL.findAllClients();
                    JTable table = createTable(clientsList.toArray());
                    clientsView.showTable(table);
                    clientsView.setTableView(table);
                } catch (InvalidData ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    class ClientsBtnUpdateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(clientsView.getTableView().getSelectionModel().isSelectionEmpty()){
                JOptionPane.showMessageDialog(clientsView,"Please choose 1!");
            }else{
                String name= clientsView.getTextFieldName();
                String address=clientsView.getTextFieldAddress();
                String email=clientsView.getTextFieldEmail();
                client=new Clients(name,address,email);
                try {
                    int id=Integer.parseInt(clientsView.getTableView().getValueAt(clientsView.getTableView().getSelectedRow(),0).toString());
                    client=new Clients(id,name,address,email);
                    clientBLL.updateClient(client);
                    List<Clients> clientsList=clientBLL.findAllClients();
                    JTable table = createTable(clientsList.toArray());
                    clientsView.showTable(table);
                    clientsView.setTableView(table);
                } catch (InvalidData ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    class ClientsBtnDeleteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(clientsView.getTableView().getSelectionModel().isSelectionEmpty()){
                JOptionPane.showMessageDialog(clientsView,"Please choose 1!");
            }else{
                try {
                    int id=Integer.parseInt(clientsView.getTableView().getValueAt(clientsView.getTableView().getSelectedRow(),0).toString());
                    clientBLL.deleteClient(id);
                    List<Clients> clientsList=clientBLL.findAllClients();
                    JTable table = createTable(clientsList.toArray());
                    clientsView.showTable(table);
                    clientsView.setTableView(table);
                } catch (InvalidData ex) {
                    ex.printStackTrace();
                }
            }
        }
    }


    //Products
    class ProductsBtnViewListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Products> productsList=productsBLL.findAllProducts();
            JTable tableOrders = createTable(productsList.toArray());
            productsView.showTable(tableOrders);
            productsView.setTableView(tableOrders);
        }
    }
    class ProductsBtnInsertListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            if(productsView.getTextFieldName().isEmpty() || productsView.getTextFieldPrice().isEmpty() || productsView.getTextFieldQuantity().isEmpty()){
                JOptionPane.showMessageDialog(clientsView, "Please don't leave the boxes empty!");
            }else {
                String name= productsView.getTextFieldName();
                String price=productsView.getTextFieldPrice();
                String qty=productsView.getTextFieldQuantity();
                product=new Products(name,Integer.parseInt(qty),Double.parseDouble(price));

                    productsBLL.insertProduct(product);
                    List<Products> productsList=productsBLL.findAllProducts();
                    JTable table=createTable(productsList.toArray());
                    productsView.showTable(table);
                    productsView.setTableView(table);
            }
        }
    }
    class ProductsBtnUpdateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            if(productsView.getTableView().getSelectionModel().isSelectionEmpty()){
                JOptionPane.showMessageDialog(productsView,"Please choose 1!");
            }else{
                    String name= productsView.getTextFieldName();
                    String price=productsView.getTextFieldPrice();
                    String qty=productsView.getTextFieldQuantity();
                    product=new Products(name,Integer.parseInt(qty),Double.parseDouble(price));
                try {
                    System.out.println("ok");
                    int id=Integer.parseInt(productsView.getTableView().getValueAt(productsView.getTableView().getSelectedRow(),0).toString());
                    System.out.println(id);
                    product=new Products(id,name,Integer.parseInt(qty),Double.parseDouble(price));
                    productsBLL.updateProduct(product);

                    List<Products> productsList=productsBLL.findAllProducts();
                    JTable table=createTable(productsList.toArray());
                    productsView.showTable(table);
                    productsView.setTableView(table);
                } catch (InvalidData ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    class ProductsBtnDeleteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            if(productsView.getTableView().getSelectionModel().isSelectionEmpty()){
                JOptionPane.showMessageDialog(productsView,"Please choose 1!");
            }else{
                try {
                    int id=Integer.parseInt(productsView.getTableView().getValueAt(productsView.getTableView().getSelectedRow(),0).toString());
                    productsBLL.deleteProduct(id);

                    List<Products> productsList=productsBLL.findAllProducts();
                    JTable table=createTable(productsList.toArray());
                    productsView.showTable(table);
                    productsView.setTableView(table);

                } catch (InvalidData ex) {
                    ex.printStackTrace();
                }
            }
        }
    }


    //Orders
    class OrdersBtnAddListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(ordersView.getTextFieldProductQty().isEmpty()){
                JOptionPane.showMessageDialog(ordersView,"Fill the quantity box!");
            }else{
                if(ordersView.getTableViewClients().getSelectionModel().isSelectionEmpty() || ordersView.getTableViewProducts().getSelectionModel().isSelectionEmpty()){
                    JOptionPane.showMessageDialog(ordersView,"Please choose 1 client and 1 product!");
                }else{
                    String idClient=ordersView.getTableViewClients().getValueAt(ordersView.getTableViewClients().getSelectedRow(),0).toString();
                    String idProduct=ordersView.getTableViewProducts().getValueAt(ordersView.getTableViewProducts().getSelectedRow(),0).toString();
                    String quantityProduct=ordersView.getTextFieldProductQty();
                    if(Integer.parseInt(quantityProduct)>Integer.parseInt(ordersView.getTableViewProducts().getValueAt(ordersView.getTableViewProducts().getSelectedRow(),2).toString()))
                    {
                        JOptionPane.showMessageDialog(ordersView,"Lower the quantity!");
                    }else{
                        try {
                            Clients clients=clientBLL.findClientById(Integer.parseInt(idClient));
                            Products products=productsBLL.findProductsById(Integer.parseInt(idProduct));
                            String clientName = clients.getName();
                            String clientAddress = clients.getAddress();
                            String clientEmail= clients.getEmail();
                            String productName=products.getName();
                            double productPrice=products.getPrice();
                            ordersBLL.insertOrder(new Orders(Integer.parseInt(idClient),clientName,clientAddress,clientEmail,Integer.parseInt(idProduct),productName,Integer.parseInt(quantityProduct),productPrice));
                            ordersTableView.setVisible(true);

                            List<Orders> ordersList=ordersBLL.findAllOrders();

                            JTable table = createTable(ordersList.toArray());
                            ordersTableView.showTable(table);
                             ordersTableView.setTableView(table);

                        } catch (InvalidData | IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        }
    }
    class OrdersBtnViewListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            List<Orders> ordersList=ordersBLL.findAllOrders();

            JTable tableOrders = createTable(ordersList.toArray());
            ordersTableView.showTable(tableOrders);
            ordersTableView.setVisible(true);
        }
    }

}
