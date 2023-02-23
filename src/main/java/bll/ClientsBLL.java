package bll;

import bll.validators.InvalidData;
import bll.validators.Validator;
import bll.validators.clients.AddressValidator;
import bll.validators.clients.EmailValidator;
import bll.validators.clients.NameValidator;
import dao.ClientsDAO;
import model.Clients;

import java.util.ArrayList;
import java.util.List;

public class ClientsBLL {
    //lista validatorilor
    private List<Validator<Clients>> validators;
    //var pt realizarea operatiilor
    private ClientsDAO clientsDAO;

    public ClientsBLL(){
        validators=new ArrayList<Validator<Clients>>();
        validators.add(new NameValidator());
        validators.add(new AddressValidator());
        validators.add(new EmailValidator());
        clientsDAO=new ClientsDAO();
    }
    public void validate(Clients client)throws InvalidData{
        for(Validator<Clients> validator: validators){
            validator.validate(client);
        }
    }
    public void insertClient(Clients client) throws InvalidData {
        validate(client);
        clientsDAO.insert(client);
    }
    public void updateClient(Clients client) throws InvalidData {
        validate(client);
        clientsDAO.update(client.getId(),client);
    }
    public void deleteClient(int id) throws InvalidData {
        clientsDAO.delete(id);
    }
    public Clients findClientById(int id) throws InvalidData {
        return  clientsDAO.findById(id);
    }
    public List<Clients> findAllClients(){
        return clientsDAO.findAll();
    }


}
