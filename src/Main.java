import Modelo.Entidad.Signatario;
import Modelo.Repositorio.RepositorioSignatarios;
import Vista.Singletons.LoginSingleton;

public class Main {
    public static void main(String[] args) throws Exception {
        LoginSingleton.getInstancia().setVisible(true);
        var repo = new RepositorioSignatarios();
        for (Signatario s : repo.getAllSignatarios()) {
            System.out.println(s);
        }
        System.out.println(repo.getSiglas(8));
    }
}
