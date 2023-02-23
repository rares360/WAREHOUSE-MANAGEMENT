package bll.validators.orders;

import bll.validators.InvalidData;
import bll.validators.Validator;
import model.Orders;

public class OrdersPriceValidator implements Validator<Orders> {
    @Override
    public void validate(Orders orders) throws InvalidData {
        if(orders.getProductPrice()<0){
            throw new InvalidData("Fill in the price with a positive number!");
        }
    }
}
