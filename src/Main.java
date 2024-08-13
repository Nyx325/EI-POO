import Modelo.Repositorio.Conector;
import Vista.Forms.Login;
import Vista.Forms.ParametroVista;

public class Main {
    public static void main(String[] args) throws Exception {
        Conector.login("rubenor", "");
        ParametroVista.getInstancia().preparar("gestion");
        ParametroVista.getInstancia().show();
        //Login.getInstancia().setVisible(true);
    }
}
