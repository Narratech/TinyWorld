package icaro.aplicaciones.agentes.AgenteAplicacionMinions.objetivos;

import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;

public class ObtenerObjeto extends Objetivo {

    public String objeto;
    
    public Objetivo parent;
    
	public Objetivo getParent() {
		return parent;
	}

	public void setParent(Objetivo parent) {
		this.parent = parent;
	}

    public ObtenerObjeto(String objeto) {
        super.setgoalId("ObtenerObjeto");
        this.objeto = objeto;
    }

}
