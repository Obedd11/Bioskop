package Core;

import Controllers.DetailTrans_Controller;
import Controllers.Film_Controller;
import Controllers.Login_Controller;
import Controllers.Logout_Controller;

public class Routes {

    public static Login_Controller loginController = new Login_Controller();
    public static Film_Controller film_controller = new Film_Controller();
    public static Logout_Controller logout_controller = new Logout_Controller();
    public static DetailTrans_Controller detailTrans_controller = new DetailTrans_Controller();
}
