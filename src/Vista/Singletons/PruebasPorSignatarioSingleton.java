package Vista.Singletons;

import Vista.Forms.PruebasPorSignatario;

public class PruebasPorSignatarioSingleton {
    private static PruebasPorSignatario instancia;

    public static PruebasPorSignatario getInstancia(){
        if(PruebasPorSignatarioSingleton.instancia == null){
            PruebasPorSignatarioSingleton.instancia = new PruebasPorSignatario();
        }

        return PruebasPorSignatarioSingleton.instancia;
    }
}
