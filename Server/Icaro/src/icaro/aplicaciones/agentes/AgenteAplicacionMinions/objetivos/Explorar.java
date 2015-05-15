package icaro.aplicaciones.agentes.AgenteAplicacionMinions.objetivos;

import icaro.aplicaciones.informacion.gestionCitas.Notificacion;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;

public class Explorar extends Objetivo {

    public String zone;
    public Objetivo parent;
    
	public Objetivo getParent() {
		return parent;
	}

	public void setParent(Objetivo parent) {
		this.parent = parent;
	}

    public Explorar(Notificacion notif) {
        super.setgoalId("Explorar");
        this.zone = "norte";
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }


    
    

}
