package icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes;

import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.comunicacion.EventoSimple;
import icaro.infraestructura.entidadesBasicas.comunicacion.MensajeSimple;
import icaro.infraestructura.entidadesBasicas.descEntidadesOrganizacion.jaxb.TipoAgente;
import icaro.infraestructura.patronAgenteReactivo.factoriaEInterfaces.ItfUsoAgenteReactivo;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.NotificacionesRecTrazas;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.gui.PanelTrazasAbstracto;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.gui.PanelTrazasClasificadas;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import javax.swing.JOptionPane;

public class VisualizacionTrazasContr {

    private Map tablaPanelesEspecificos;
    private LinkedList<String> listaElementosTrazables;
    private Set identsTiposEntidades;
    private PanelTrazasClasificadas panelTrazasNiveles;
    private NotificacionesRecTrazas notificador;
    private Boolean activacionPanelTrazas = false;
    private InfoPanelesEspecificos infoPanels;
    private String identUltimaEntidad = null;
    private NombresPredefinidos.TipoEntidad tipoEntidadEmisora;
    private PanelTrazasAbstracto panelActual;

    public VisualizacionTrazasContr() {
        tablaPanelesEspecificos = new HashMap();
        identsTiposEntidades = new HashSet();
        listaElementosTrazables = new LinkedList<String>();
        for (TipoAgente tg : TipoAgente.values()) {
            identsTiposEntidades.add(tg.value());
        }
        identsTiposEntidades.add(NombresPredefinidos.NOMBRE_ENTIDAD_RECURSO);
        infoPanels = new InfoPanelesEspecificos();
        notificador = new NotificacionesRecTrazas();
        panelTrazasNiveles = new PanelTrazasClasificadas(notificador, infoPanels);
    }

    public void setIdentAgenteAReportar(String nombreAgente) {
        notificador.setIdentGestoraReportar(nombreAgente);
    }

    public void setItfAgenteAReportar(ItfUsoAgenteReactivo itfAgente) {
    }

    public void activarVisualizacionTrazas() {
        activacionPanelTrazas = true;
    }

    public void desactivarVisualizacionTrazas() {
        activacionPanelTrazas = false;
    }

    public synchronized void visualizarNuevaTraza(InfoTraza traza) {
        String idEntidad = traza.getEntidadEmisora();
        if (idEntidad == null) {
            idEntidad = NombresPredefinidos.NOMBRE_ENTIDAD_INDEFINIDA;
        }
        tipoEntidadEmisora = traza.getTipoEntidadEmisora();
        if (tipoEntidadEmisora == null) {
            tipoEntidadEmisora = NombresPredefinidos.TipoEntidad.noDefinido;
        }
        if (activacionPanelTrazas) {
            panelTrazasNiveles.setVisible(true);
            panelActual = this.getpanelParaVisualizar(tipoEntidadEmisora, idEntidad);
            panelActual.muestraInfoTraza(traza);
            panelTrazasNiveles.muestraMensaje(traza);
        } else if (traza.getNivel() == InfoTraza.NivelTraza.error) {
            panelTrazasNiveles.setVisible(true);
            panelTrazasNiveles.muestraMensaje(traza);
            panelActual = this.getpanelParaVisualizar(tipoEntidadEmisora, idEntidad);
            panelActual.muestraInfoTraza(traza);
        }
    }

    private PanelTrazasAbstracto getpanelParaVisualizar(NombresPredefinidos.TipoEntidad tipoEnti, String idEntidad) {
        if (!idEntidad.equals(identUltimaEntidad)) {
            panelActual = (PanelTrazasAbstracto) tablaPanelesEspecificos.get(idEntidad);
            identUltimaEntidad = idEntidad;
            if (panelActual == null) {
                panelActual = this.infoPanels.crearPanelparaEntidad(idEntidad, tipoEnti);
                panelTrazasNiveles.visualizarElementoTrazable(idEntidad);
            }
        }
        return panelActual;
    }

    public synchronized void visualizarEnvioEvento(NombresPredefinidos.TipoEntidad tipoEnt, EventoSimple trazaEvento) {
        String identEntidad = trazaEvento.getOrigen();
        panelActual = this.getpanelParaVisualizar(tipoEnt, identEntidad);
        panelActual.muestraEventoEnviado(trazaEvento);
    }

    public synchronized void visualizarEnvioMensaje(NombresPredefinidos.TipoEntidad tipoEnt, MensajeSimple trazaMensaje) {
        String identEntidad = trazaMensaje.getEmisor().toString();
        panelActual = this.getpanelParaVisualizar(tipoEnt, identEntidad);
        panelActual.muestraMensajeEnviado(trazaMensaje);
    }

    public synchronized void visualizarRecibirMensaje(NombresPredefinidos.TipoEntidad tipoEnt, MensajeSimple trazaMensaje) {
        String identEntidad = trazaMensaje.getReceptor().toString();
        panelActual = this.getpanelParaVisualizar(tipoEnt, identEntidad);
        panelActual.muestraMensajeRecibido(trazaMensaje);
    }

    public synchronized void visualizarRecibirEvento(NombresPredefinidos.TipoEntidad tipoEnt, EventoSimple trazaEvento) {
        String identEntidad = trazaEvento.getOrigen();
        panelActual = this.getpanelParaVisualizar(tipoEnt, identEntidad);
        panelActual.muestraEventoRecibido(trazaEvento);
    }

    public synchronized void visualizarTrazaEjecReglas(String entityId, String infoAtrazar) {
        panelActual = this.getpanelParaVisualizar(NombresPredefinidos.TipoEntidad.Cognitivo, entityId);
        panelActual.muestraTrazaEjecucionReglas(infoAtrazar);
    }

    public synchronized void visualizarTrazaActivacionReglas(String entityId, String infoAtrazar) {
        panelActual = this.getpanelParaVisualizar(NombresPredefinidos.TipoEntidad.Cognitivo, entityId);
        panelActual.muestraTrazaActivacionReglas(infoAtrazar);
    }

    public void visualizarIdentFicheroDescrOrganizacion() {
        panelTrazasNiveles.visualizarInfoGeneral("Fichero Descripcion organizacion : " + NombresPredefinidos.DESCRIPCION_XML_POR_DEFECTO);
    }

    public synchronized void pedirConfirmacionTerminacionAlUsuario() {
        int res = JOptionPane.showConfirmDialog(null, "Confirmar terminacion", "Confirmar terminacion", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
        switch (res) {
            case JOptionPane.YES_OPTION: {
                try {
                    notificador.confirmarTerminacionOrganizacion();
                } catch (Exception e) {
                    System.out.println("Ha habido un error al enviar el evento terminacion_confirmada al gestor de organizacin");
                    e.printStackTrace();
                }
            }
            case JOptionPane.NO_OPTION: {
                try {
                    notificador.anularTerminacionOrganizacion();
                } catch (Exception e) {
                    System.out.println("Ha habido un error al enviar el evento terminacion_anulada al gestor de organizacin");
                    e.printStackTrace();
                }
            }
        }
    }

    public void cerrarVentanas() {
        panelTrazasNiveles.cierraVentana();
    }
}
