package icaro.aplicaciones.agentes.AgenteAplicacionMinions.objetivos;

public class ObservarEntorno extends Subobjetivo {

    public ObservarEntorno() {
        super.setgoalId("ObservarEntorno");
    }

    @Override
    public boolean esAtomico() {
        return true;
    }
}
