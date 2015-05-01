package icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.gui;

import icaro.infraestructura.entidadesBasicas.comunicacion.EventoSimple;
import icaro.infraestructura.entidadesBasicas.comunicacion.MensajeSimple;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;
import java.awt.Color;
import java.awt.Font;

public class PanelTrazasAgteCognitivo extends PanelTrazasAbstracto {

    private String nombreComponente; //identificacin de la ventana

    public PanelTrazasAgteCognitivo(String nombre, String contenido) {
        initComponents();
        this.nombreComponente = nombre;
        this.setTitle(nombreComponente);
        this.areaTrazas.setText(contenido);
        this.setResizable(true);
        areaTrazas.setFont(new Font("Trebuchet", Font.PLAIN, 12));
        areaTrazas.setForeground(Color.BLUE);
        areaTrazaMensajes.setFont(new Font("Times", Font.ITALIC, 12));
        areaTrazaMensajes.setForeground(Color.BLACK);
        areaEjecReglas.setFont(new Font("Times", Font.PLAIN, 12));
        areaEjecReglas.setForeground(Color.BLUE);
        areaActivReglas.setFont(new Font("Times", Font.PLAIN, 12));
        areaEjecReglas.setForeground(Color.BLACK);
    }

    public String getIdentificador() {
        return nombreComponente;
    }

    @Override
    public synchronized void muestraInfoTraza(InfoTraza traza) {
        //Concateno el nuevo mensaje con el que habia antes
        areaTrazas.append(traza.getNivel() + " : " + traza.getMensaje() + "\n");
        //si escribo null,borra lo anterior
    }

    @Override
    public synchronized void muestraMensajeEnviado(MensajeSimple m) {
        //Concateno el nuevo mensaje con el que habia antes
        areaTrazaMensajes.append("Mensaje Enviado--> Emisor : " + m.getEmisor() + "  envia   mensaje al agente : " +
                m.getReceptor() + " Clase del Contenido: " + m.getContenido().getClass().getSimpleName() + "\n" +
                "      Valores Contenido : " + "\n" + m.getContenido() + "\n");
        //si escribo null,borra lo anterior
    }

    @Override
    public synchronized void muestraMensajeRecibido(MensajeSimple m) {
        //Concateno el nuevo mensaje con el que habia antes
        areaTrazaMensajes.append("Mensaje Recibido --> Emisor : " + m.getEmisor() + ". Clase del Contenido: " +
                m.getContenido().getClass().getSimpleName() + "\n" + "Valores Contenido : " + "\n" +
                m.getContenido() + "\n");
        //si escribo null,borra lo anterior
    }

    @Override
    public synchronized void muestraEventoRecibido(EventoSimple evto) {
        Font f = new Font("Trebuchet", Font.PLAIN, 12);
        areaTrazaEventos.setFont(f);
        areaTrazaEventos.setForeground(Color.BLUE);
        //Concateno el nuevo mensaje con el que habia antes
        Object contenido = evto.getContenido();
        if (contenido != null) {
            areaTrazaEventos.append("Evento Recibido Emisor : " + evto.getOrigen() +
                    ". Tipo Contenido: " + evto.getContenido().getClass().getSimpleName() +
                    "" + ". Contenido : " + contenido.toString() + "\n");
        }
    }

    @Override
    public synchronized void muestraTrazaEjecucionReglas(String infoAtrazar) {
        areaEjecReglas.append(infoAtrazar + "\n");
    }

    @Override
    public synchronized void muestraTrazaActivacionReglas(String infoAtrazar) {
        areaActivReglas.append(infoAtrazar + "\n");
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT
     * modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        panelCognitivo = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        areaTrazas = new java.awt.TextArea();
        jPanel2 = new javax.swing.JPanel();
        areaTrazaMensajes = new java.awt.TextArea();
        jPanel3 = new javax.swing.JPanel();
        areaTrazaEventos = new java.awt.TextArea();
        jPanel5 = new javax.swing.JPanel();
        areaEjecReglas = new java.awt.TextArea();
        jPanel6 = new javax.swing.JPanel();
        areaActivReglas = new java.awt.TextArea();

        jScrollPane4.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 393, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 218, Short.MAX_VALUE)
        );

        jScrollPane4.setViewportView(jPanel4);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Titulo");

        areaTrazas.setBackground(new java.awt.Color(231, 252, 252));
        areaTrazas.setForeground(new java.awt.Color(102, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(areaTrazas, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(areaTrazas, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
        );

        panelCognitivo.addTab("General", jPanel1);

        areaTrazaMensajes.setBackground(new java.awt.Color(217, 241, 217));
        areaTrazaMensajes.setForeground(new java.awt.Color(0, 0, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(areaTrazaMensajes, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(areaTrazaMensajes, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
        );

        areaTrazaMensajes.getAccessibleContext().setAccessibleParent(jPanel1);

        panelCognitivo.addTab("Mensajes", jPanel2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(areaTrazaEventos, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(areaTrazaEventos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
        );

        panelCognitivo.addTab("Eventos", jPanel3);

        areaEjecReglas.setBackground(new java.awt.Color(204, 255, 255));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(areaEjecReglas, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(areaEjecReglas, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
        );

        panelCognitivo.addTab("Ejec Reglas", jPanel5);

        areaActivReglas.setBackground(new java.awt.Color(255, 255, 204));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(areaActivReglas, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(areaActivReglas, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
        );

        panelCognitivo.addTab("Activ Reglas", jPanel6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCognitivo, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCognitivo, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.TextArea areaActivReglas;
    private java.awt.TextArea areaEjecReglas;
    private java.awt.TextArea areaTrazaEventos;
    private java.awt.TextArea areaTrazaMensajes;
    private java.awt.TextArea areaTrazas;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane panelCognitivo;
    // End of variables declaration//GEN-END:variables
}
