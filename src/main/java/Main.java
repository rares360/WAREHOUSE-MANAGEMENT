import model.Products;
import presentation.*;

public class Main {
    public static void main(String[] args) {
        MainView view=new MainView();
        Controller controller=new Controller(view);
    }
}
