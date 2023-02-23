package bll;

import bll.validators.InvalidData;
import bll.validators.Validator;
import bll.validators.clients.AddressValidator;
import bll.validators.clients.EmailValidator;
import bll.validators.clients.NameValidator;
import bll.validators.orders.*;
import bll.validators.products.NameProductsValidator;
import bll.validators.products.PriceValidator;
import bll.validators.products.QuantityValidator;
import dao.ClientsDAO;
import dao.OrdersDAO;
import model.Clients;
import model.Orders;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrdersBLL{
    //lista validatorilor
    private List<Validator<Orders>> validators;
    //var pt realizarea operatiilor
    private OrdersDAO ordersDAO;
    public OrdersBLL(){
        validators=new ArrayList<Validator<Orders>>();
        validators.add(new OrdersNameValidator());
        validators.add(new OrdersAddressValidator());
        validators.add(new OrdersEmailValidator());
        validators.add(new OrdersNameProductsValidator());
        validators.add(new OrdersPriceValidator());
        validators.add(new OrdersQuantityValidator());

        ordersDAO=new OrdersDAO();
    }
    public void validate(Orders order)throws InvalidData{
        for(Validator<Orders> validator: validators){
            validator.validate(order);
        }
    }
    //metoda de inserare client
    public void insertOrder(Orders order) throws IOException {
        FileWriter writer = new FileWriter("bill"+order.getId()+".txt");
        writer.write("ORDER NO. " + order.getId() + "\n" + order);
        writer.flush();
        ordersDAO.insert(order);
    }
    //metoda ce returneaza lista de orders din tabelul Orders
    public List<Orders> findAllOrders(){
        return ordersDAO.findAll();
    }


}
