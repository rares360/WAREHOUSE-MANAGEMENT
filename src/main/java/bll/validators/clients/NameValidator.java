package bll.validators.clients;

import bll.validators.InvalidData;
import bll.validators.Validator;
import model.Clients;

public class NameValidator implements Validator<Clients> {

    @Override
    public void validate(Clients clients) throws InvalidData {
        if(clients.getName().length()==0){
            throw new InvalidData("Fill the name!");
        }
    }
}
