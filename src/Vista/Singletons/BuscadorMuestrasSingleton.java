package Vista.Singletons;

import Vista.Forms.BuscadorMuestras;

public class BuscadorMuestrasSingleton {
    private static BuscadorMuestras instancia;

    public static BuscadorMuestras getInstancia(){
        if(BuscadorMuestrasSingleton.instancia == null){
            instancia = new BuscadorMuestras();
        }

        return instancia;
    }
}
