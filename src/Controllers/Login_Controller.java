package Controllers;

import Entities.User_Entity;
import Models.Data.User_Model;
import views.DashboardAdmin_View;
import views.DashboardUser_View;
import views.Login_View;

public class Login_Controller {

    public User_Entity login(String email, String password) {
        User_Model.initialData();

        User_Entity user = User_Model.read().stream()
                .filter(u -> u.getEmail().equals(email) && u.getPassword().equals(password))
                .findFirst()
                .orElse(null);

        if (user != null) {
            if ("admin".equals(user.getRole())) {
                DashboardAdmin_View dashboardAdmin_view = new DashboardAdmin_View(user.getUsername(), user.getEmail());
                return user ;
            } else if ("user".equals(user.getRole())) {
                DashboardUser_View dashboardAdmin_view = new DashboardUser_View(user.getUsername(), user.getEmail());
                return user;
            }
        }

        return null;
    }

    public void toLogin()
    {
        Login_View login_view = new Login_View();
    }
}
