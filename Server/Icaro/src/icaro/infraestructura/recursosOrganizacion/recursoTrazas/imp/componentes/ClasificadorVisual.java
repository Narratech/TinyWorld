package icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes;

import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.comunicacion.EventoSimple;
import icaro.infraestructura.entidadesBasicas.comunicacion.MensajeSimple;
import icaro.infraestructura.entidadesBasicas.descEntidadesOrganizacion.DescInstancia;
import icaro.infraestructura.entidadesBasicas.excepciones.ExcepcionEnComponente;
import icaro.infraestructura.recursosOrganizacion.configuracion.ItfUsoConfiguracion;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.NotificacionesRecTrazas;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.gui.*;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClasificadorVisual implements Serializable {

    private PanelTrazasClasificadas panelTrazasNiveles;
    private InfoPanelesEspecificos tablaPanelesEspecificos;
    public static int PANEL_GENERAl = 0;
    public static int PANEL_AGENTE_REACTIVO = 1;
    public static int PANEL_AGENTE_COGNITIVO = 2;
    public static int PANEL_RECURSO = 3;
    public static int PANEL_GENERICO = 3;
    private ItfUsoConfiguracion itfConfig;
    private List<String> listaElementosTrazables;
    private NotificacionesRecTrazas notificador;
    private PanelTrazasAbstracto panelActual;

    public ClasificadorVisual(NotificacionesRecTrazas notifEventos) {
        notificador = notifEventos;
        listaElementosTrazables = new LinkedList<String>();
        tablaPanelesEspecificos = new InfoPanelesEspecificos();
        //pasamos la lista de elementos trazables a la interfaz grfica para permitir su traza
        panelTrazasNiveles = new PanelTrazasClasificadas(notificador, tablaPanelesEspecificos);
        panelTrazasNiveles.setVisible(true);
    }

    public void visualizarElementosTrazables(List<String> listaElementosTrazables) {
        if (listaElementosTrazables != null) {
            tablaPanelesEspecificos = construirPanelesEspecificos(listaElementosTrazables);
        }
    }

    public void setPanelPrincipal(PanelTrazasClasificadas p) {
        this.panelTrazasNiveles = p;
    }

    public PanelTrazasClasificadas getPanelPrincipal() {
        return this.panelTrazasNiveles;
    }

    public void cerrarVentanas() {
        //elimino todas las ventanas
        this.getPanelPrincipal().dispose();
        if (tablaPanelesEspecificos != null) {
            String identPanel = "";
            Set conjIdentPanels = tablaPanelesEspecificos.getIdentsPenels();
            Iterator<String> iter = conjIdentPanels.iterator();
            while (iter.hasNext()) {
                identPanel = iter.next();
                PanelTrazasAgteReactivo panel = (PanelTrazasAgteReactivo) tablaPanelesEspecificos.getPanelEspecifico(identPanel);
                panel.dispose();
            }
        }
    }

    private InfoPanelesEspecificos construirPanelesEspecificos(List<String> listaElementosTrazables) {
        // Se crea una lista de paneles y una tabla con las descripciones de
        // los paneles donde el identificador del panel es el identificador que se pasa en la lista
        if (listaElementosTrazables != null) {
            //construimos los paneles especificos para que se pueda  trazar en ellos
            Iterator<String> iterador = listaElementosTrazables.iterator();
            while (iterador.hasNext()) {
                String identEntidad = iterador.next();
                InfoPanelEspecifico panel = new InfoPanelEspecifico(identEntidad, "");
                panel.setPanelEspecifico(new PanelTrazasAgteReactivo(identEntidad, ""));
                //panel.setVisible(false);
                tablaPanelesEspecificos.addNewPanelEspecifico(panel);
            }
        }
        return tablaPanelesEspecificos;
    }

    public void muestraTrazaEnviarMensaje(MensajeSimple trazaMensaje) {
        panelActual = (PanelTrazasAbstracto) tablaPanelesEspecificos.getPanelEspecifico(trazaMensaje.getEmisor().toString());
        // Se debe mostrar en el panel del agente emisor del mensaje
        panelActual.muestraMensajeEnviado(trazaMensaje);

    }

    public void muestraTrazaEnviarEvento(EventoSimple trazaEvento) {
        panelActual = (PanelTrazasAbstracto) tablaPanelesEspecificos.getPanelEspecifico(trazaEvento.getOrigen());
        // Se debe mostrar en el panel del agente emisor del mensaje
        panelActual.muestraEventoEnviado(trazaEvento);

    }

    public void muestraTrazaMensajeRecibido(MensajeSimple trazaMensaje) {
        panelActual = (PanelTrazasAbstracto) tablaPanelesEspecificos.getPanelEspecifico(trazaMensaje.getReceptor().toString());
        panelActual.muestraMensajeRecibido(trazaMensaje);

    }

    public void muestraTrazaEventoRecibido(EventoSimple trazaEvento) {
        panelActual = (PanelTrazasAbstracto) tablaPanelesEspecificos.getPanelEspecifico(trazaEvento.getOrigen());
        panelActual.muestraEventoRecibido(trazaEvento);

    }

    public void muestraTrazaMensajeEvento(String id, EventoSimple ev) {
        panelActual = (PanelTrazasAbstracto) tablaPanelesEspecificos.getPanelEspecifico(id);
        panelActual.muestraEventoRecibido(ev);

    }

    public void muestraTrazaActivacionReglas(String entityId, String infoAtrazar) {
        panelActual = (PanelTrazasAbstracto) tablaPanelesEspecificos.getPanelEspecifico(entityId);
        panelActual.muestraTrazaActivacionReglas(infoAtrazar);

    }

    public void muestraTrazaEjecucionReglas(String entityId, String infoAtrazar) {
        panelActual = (PanelTrazasAbstracto) tablaPanelesEspecificos.getPanelEspecifico(entityId);
        panelActual.muestraTrazaEjecucionReglas(infoAtrazar);
    }

    public synchronized void muestraTraza(InfoTraza traza) {
        // actualiza la nueva traza en todos los paneles
        panelTrazasNiveles.muestraMensaje(traza);
        String identElementTraza = traza.getEntidadEmisora();

        InfoPanelEspecifico panelInfoActual = (InfoPanelEspecifico) tablaPanelesEspecificos.getInfoPanel(identElementTraza);
        if (panelInfoActual != null) {
            panelActual = (PanelTrazasAbstracto) tablaPanelesEspecificos.getPanelEspecifico(identElementTraza);
        } else {
            // creamos el info panel y visualizamos la ventana
            panelActual = crearPanelparaEntidad(identElementTraza);
            panelTrazasNiveles.visualizarElementoTrazable(identElementTraza);
        }
        panelActual.muestraInfoTraza(traza);
    }

    private PanelTrazasAbstracto crearPanelparaEntidad(String entityId) {
        PanelTrazasAbstracto nuevoPanel = null;
        try {
            // Obtener el tipo de entidad
            if ((itfConfig == null) && (NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ != null)) {
                itfConfig = (ItfUsoConfiguracion) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ.obtenerInterfaz("Itf_Uso_Configuracion");
            }
        } catch (Exception ex) {
            Logger.getLogger(ClasificadorVisual.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (itfConfig == null) {
                nuevoPanel = new PanelTrazasAgteReactivo(entityId, "");
            } else {
                DescInstancia descComponente = itfConfig.getDescInstancia(entityId);
                if (descComponente == null) {
                    nuevoPanel = new PanelTrazasGenerico(entityId, "");
                } else if (descComponente.getCategoriaComponente().equals(NombresPredefinidos.NOMBRE_ENTIDAD_AGENTE)) {
                    if (descComponente.getTipoComponente().equalsIgnoreCase("Cognitivo")) {
                        nuevoPanel = new PanelTrazasAgteCognitivo(entityId, "");
                    } else {
                        nuevoPanel = new PanelTrazasAgteReactivo(entityId, "");
                    }
                } else if (descComponente.getCategoriaComponente().equals(NombresPredefinidos.NOMBRE_ENTIDAD_RECURSO)) {
                    nuevoPanel = new PanelTrazasRecurso(entityId, "");
                } else {
                    nuevoPanel = new PanelTrazasGenerico(entityId, "");
                }
            }
            InfoPanelEspecifico infoPanel = new InfoPanelEspecifico(entityId, "");
            infoPanel.setPanelEspecifico(nuevoPanel);
            tablaPanelesEspecificos.addNewPanelEspecifico(infoPanel);
        } catch (ExcepcionEnComponente | RemoteException ex) {
            Logger.getLogger(ClasificadorVisual.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nuevoPanel;
    }

    public void cierraVentanaPrincipal() {
        this.panelTrazasNiveles.setVisible(false);
    }

    public void setItfConfiguracion(ItfUsoConfiguracion itfConf) {
        // lo define el iniciador via la clase de implementacion	
        this.itfConfig = itfConf;
    }

    public void cierraVentanaEspecifica(String nombreVentana) {
        InfoPanelEspecifico panel = tablaPanelesEspecificos.getInfoPanel(nombreVentana);
        if (panel != null) {
            panel.getPanelEspecifico().setVisible(false);
        }
    }
}
