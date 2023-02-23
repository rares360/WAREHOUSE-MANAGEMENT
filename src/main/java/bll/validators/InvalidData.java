package bll.validators;

public class InvalidData extends Exception{
    private String alert;
    public InvalidData(String alert){
        this.alert=alert;
    }
    public void setAlert(String alert) {
        this.alert = alert;
    }
    public String getAlert(){
        return this.alert;
    }
}
