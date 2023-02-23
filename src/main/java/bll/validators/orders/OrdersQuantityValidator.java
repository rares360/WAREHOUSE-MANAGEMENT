package bll.validators.orders;

import bll.validators.InvalidData;
import bll.validators.Validator;
import model.Orders;

public class OrdersQuantityValidator implements Validator<Orders> {
    @Override
    public void validate(Orders orders) throws InvalidData {
        if(orders.getProductQuantity()<0){
            throw new InvalidData("Fill in the quantity with a positive number!");
        }
    }
}
