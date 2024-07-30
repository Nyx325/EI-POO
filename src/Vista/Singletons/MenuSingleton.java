package Vista.Singletons;

import Vista.Forms.Menu;

public class MenuSingleton {
    private static Menu instancia;

    public static Menu getInstancia() {
        if (MenuSingleton.instancia == null) {
            MenuSingleton.instancia = new Menu();
        }
        return MenuSingleton.instancia;
    }
}
