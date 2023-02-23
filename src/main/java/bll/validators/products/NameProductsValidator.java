package bll.validators.products;

import bll.validators.InvalidData;
import bll.validators.Validator;
import model.Clients;
import model.Products;

public class NameProductsValidator implements Validator<Products> {
    @Override
    public void validate(Products products) throws InvalidData {
        if(products.getName().length()==0){
            throw new InvalidData("Fill the name!");
        }
    }
}
