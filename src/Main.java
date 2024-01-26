import Core.Routes;
import views.DashboardAdmin_View;

public class Main {
    public static void main(String[] args) {
        Routes.detailTrans_controller.inititalData();
        Routes.loginController.toLogin();
    }
}