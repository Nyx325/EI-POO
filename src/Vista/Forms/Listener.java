package Vista.Forms;

/**
 * Interfaz que permite conectar clases que
 * usan otra como buscador y deben almacenar
 * la respuesta, No se me ocurrió un nombre más
 * descriptivo
 */
public interface Listener {
    /**
     * Método mediante el cual la clase encargada de mandar
     * una respuesta va a enviar la misma
     * 
     * @param tipoRespuesta para admitir varios tipos de respuesta
     *                      es decir, más de un tipo de variable de respuesta, se
     *                      colocará
     *                      un tipo
     * @param respuesta     el objeto que se va asignar en cuestión
     */
    void getResponse(int tipoRespuesta, Object respuesta);

    /**
     * Método encargado de castear y procesar como se prefiera
     * la respuesta dado el tipo de respuesta, ya sea casteando
     * o realizando lo que necesite mediante un switch o algo
     * 
     * @param tipoRespuesta el tipo de respuesta
     */
    void matchResponse(int tipoRespuesta);

}
