package icaro.infraestructura.entidadesBasicas.componentesBasicos.automatas;

import icaro.infraestructura.entidadesBasicas.componentesBasicos.automatas.automataEFconGesAcciones.InterpreteAutomataEFconGestAcciones;
import icaro.infraestructura.entidadesBasicas.componentesBasicos.automatas.automataEFconGesAcciones.estadosyTransiciones.TablaEstadosAutomataEFinputObjts;
import icaro.infraestructura.entidadesBasicas.componentesBasicos.automatas.automataEFconGesAcciones.estadosyTransiciones.XMLParserTablaEstadosAutomataEFinputObj;
import icaro.infraestructura.patronAgenteReactivo.control.GestorAccionesAgteReactivoImp;
import icaro.infraestructura.entidadesBasicas.factorias.FactoriaComponenteIcaro;
import icaro.infraestructura.entidadesBasicas.componentesBasicos.automatas.gestorAcciones.GestorAccionesAbstr;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FactoriaAutomatas extends FactoriaComponenteIcaro {

    public synchronized InterpreteAutomataEFconGestAcciones crearInterpreteAutomataEFconGestorAcciones(String identPropietario,
            String rutaFicheroAutomata, String rutaCarpetaAcciones, Boolean trazar) {
        XMLParserTablaEstadosAutomataEFinputObj parser = new XMLParserTablaEstadosAutomataEFinputObj(identPropietario);
        TablaEstadosAutomataEFinputObjts tablaEF = parser.extraeTablaEstadosDesdeFicheroXML(rutaFicheroAutomata, rutaCarpetaAcciones);
        return new InterpreteAutomataEFconGestAcciones(tablaEF, trazar);
    }

    public synchronized GestorAccionesAbstr crearGestorAcciones(Class claseGestor) {
        try {
            return (GestorAccionesAbstr) claseGestor.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(FactoriaAutomatas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public synchronized InterpreteAutomataEFconGestAcciones crearAutomataParaControlAgteReactivo(
            String identPropietario, String rutaFicheroAutomata, String rutaCarpetaAcciones, Boolean trazar) {

        XMLParserTablaEstadosAutomataEFinputObj prueba1 = new XMLParserTablaEstadosAutomataEFinputObj(identPropietario);

        TablaEstadosAutomataEFinputObjts tablaEF = prueba1.extraeTablaEstadosDesdeFicheroXML(rutaFicheroAutomata, rutaCarpetaAcciones);
        // crear el ejecutor de acciones
        GestorAccionesAbstr gestAcciones = crearGestorAcciones(GestorAccionesAgteReactivoImp.class);
        gestAcciones.setPropietario(identPropietario);
        InterpreteAutomataEFconGestAcciones interpreteAutomata = new InterpreteAutomataEFconGestAcciones(tablaEF, trazar);
        interpreteAutomata.setGestorAcciones(gestAcciones);
        return interpreteAutomata;
    }
}
