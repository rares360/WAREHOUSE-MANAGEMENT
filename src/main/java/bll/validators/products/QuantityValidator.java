package bll.validators.products;

import bll.validators.InvalidData;
import bll.validators.Validator;
import model.Clients;
import model.Products;

public class QuantityValidator implements Validator<Products> {
    @Override
    public void validate(Products products) throws InvalidData {
        if(products.getQuantity()<0){
            throw new InvalidData("Fill in the quantity with a positive number!");
        }
    }
}