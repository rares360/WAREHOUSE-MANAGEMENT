package bll;

import bll.validators.InvalidData;
import bll.validators.Validator;
import bll.validators.clients.AddressValidator;
import bll.validators.clients.EmailValidator;
import bll.validators.clients.NameValidator;
import bll.validators.products.NameProductsValidator;
import bll.validators.products.PriceValidator;
import bll.validators.products.QuantityValidator;
import dao.ProductsDAO;
import model.Clients;
import model.Products;

import java.util.ArrayList;
import java.util.List;

public class ProductsBLL {
    //lista validatorilor
    private List<Validator<Products>> validators;
    //var pt realizarea operatiilor
    private ProductsDAO productsDAO;

    public ProductsBLL(){
        validators=new ArrayList<Validator<Products>>();
        validators.add(new NameProductsValidator());
        validators.add(new PriceValidator());
        validators.add(new QuantityValidator());

        productsDAO=new ProductsDAO();
    }
    public void validate(Products product)throws InvalidData{
        for(Validator<Products> validator: validators){
            validator.validate(product);
        }
    }
    public void insertProduct(Products product){
        productsDAO.insert(product);
    }
    public void updateProduct(Products product) throws InvalidData {
        validate(product);
        productsDAO.update(product.getId(),product);
    }
    public List<Products> findAllProducts(){
        return productsDAO.findAll();
    }
    public void deleteProduct(int id) throws InvalidData {
        productsDAO.delete(id);
    }

    public Products findProductsById(int id) throws InvalidData {
        return productsDAO.findById(id);
    }

}
