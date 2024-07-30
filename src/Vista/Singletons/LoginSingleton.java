package Vista.Singletons;

import Vista.Forms.Login;

public class LoginSingleton {
    private static Login instancia;

    public static Login getInstancia(){
        if (LoginSingleton.instancia == null) {
            LoginSingleton.instancia = new Login();
        }
        return instancia;
    }
}
