package bll.validators.orders;

import bll.validators.InvalidData;
import bll.validators.Validator;
import model.Orders;

public class OrdersAddressValidator implements Validator<Orders> {
    @Override
    public void validate(Orders orders) throws InvalidData {
        if(orders.getClientAddress().length()==0){
            throw new InvalidData("Fill the address!");
        }
    }
}
