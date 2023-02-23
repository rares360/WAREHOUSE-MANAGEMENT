package bll.validators.orders;

import bll.validators.InvalidData;
import bll.validators.Validator;
import model.Orders;

public class OrdersNameProductsValidator implements Validator<Orders> {
    @Override
    public void validate(Orders orders) throws InvalidData {
        if(orders.getProductName().length()==0){
            throw new InvalidData("Fill the name!");
        }
    }
}
