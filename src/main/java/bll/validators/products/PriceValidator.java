package bll.validators.products;

import bll.validators.InvalidData;
import bll.validators.Validator;
import model.Clients;
import model.Products;

public class PriceValidator implements Validator<Products> {
    @Override
    public void validate(Products products) throws InvalidData {
        if(products.getPrice()<0){
            throw new InvalidData("Fill in the price with a positive number!");
        }
    }
}
