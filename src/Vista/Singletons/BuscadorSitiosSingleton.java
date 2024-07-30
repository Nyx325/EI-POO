package Vista.Singletons;

import Vista.Forms.BuscadorSitios;

public class BuscadorSitiosSingleton {
    private static BuscadorSitios instancia;

    public static BuscadorSitios getInstancia() {
        if (BuscadorSitiosSingleton.instancia == null) {
            BuscadorSitiosSingleton.instancia = new BuscadorSitios();
        }

        return BuscadorSitiosSingleton.instancia;
    }
}
