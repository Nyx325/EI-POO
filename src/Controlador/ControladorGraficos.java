package Controlador;

import Modelo.Repositorio.RepositorioCliente;
import Modelo.Repositorio.RepositorioNorma;
import Modelo.Repositorio.RepositorioPrueba;
import Modelo.Repositorio.RepositorioSignatarios;
import Modelo.Repositorio.RepositorioSitio;

import java.util.Map;
import org.jfree.data.category.DefaultCategoryDataset;

public class ControladorGraficos {
    RepositorioPrueba repoPrueba = new RepositorioPrueba();
    RepositorioSignatarios repoSig = new RepositorioSignatarios();
    RepositorioNorma repoNorm = new RepositorioNorma();
    RepositorioSitio repoSit = new RepositorioSitio();
    RepositorioCliente repoCli = new RepositorioCliente();
    
    private DefaultCategoryDataset dataSetFromMap(Map<String, Long> map, String group) throws Exception {
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        for (String key : map.keySet()) {
            dataSet.setValue(map.get(key), group, key);
        }
        return dataSet;
    }
    
    public DefaultCategoryDataset chartDataSet(int chartNum) throws Exception {
        switch (chartNum) {
            case 1:
                return dataSetFromMap(
                    repoPrueba.pruebasRealizadasPorAnio(2024, 5), 
                    "Pruebas realizadas por año"
                );
            case 2:
                return dataSetFromMap(
                    repoSig.topSignatarionResultados(5), 
                    "Signatarios con más resultados"
                );
            case 3:
                return dataSetFromMap(
                    repoNorm.normasMasPruebas(5), 
                    "Pruebas por norma"
                );
            case 4:
                return dataSetFromMap(
                    repoSit.sitiosConMasResultados(), 
                    "Sitios con más resultados"
                );
            case 5:
                return dataSetFromMap(
                    repoCli.clientesFrecuentes(), 
                    "Clientes frecuentes"
                );
            default:
                throw new AssertionError();
        }
    }
}
