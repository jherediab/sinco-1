/*    */ package sinco.business;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import sinco.business.RespuestaBD;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RespuestaBD
/*    */ {
/*    */   private String mensaje;
/*    */   private String cerrarSolicitud;
/*    */   private boolean rta = false;
/*    */   private int secuencia;
/*    */   private String causal;
/*    */   private int registrosAfectados;
/*    */   Collection resultados;
/*    */   
/* 27 */   public String getMensaje() { return (this.mensaje == null) ? "" : this.mensaje; }
/*    */ 
/*    */   
/* 30 */   public void setMensaje(String p) { this.mensaje = p; }
/*    */ 
/*    */   
/* 33 */   public boolean isRta() { return this.rta; }
/*    */ 
/*    */   
/* 36 */   public void setRta(boolean rta) { this.rta = rta; }
/*    */ 
/*    */ 
/*    */   
/* 40 */   public int getSecuencia() { return this.secuencia; }
/*    */ 
/*    */   
/* 43 */   public void setSecuencia(int secuencia) { this.secuencia = secuencia; }
/*    */ 
/*    */   
/* 46 */   public String getCerrarSolicitud() { return (this.cerrarSolicitud == null) ? "" : this.cerrarSolicitud; }
/*    */ 
/*    */   
/* 49 */   public void setCerrarSolicitud(String cerrarSolicitud) { this.cerrarSolicitud = cerrarSolicitud; }
/*    */ 
/*    */   
/* 52 */   public String getCausal() { return (this.causal == null) ? "" : this.causal; }
/*    */ 
/*    */   
/* 55 */   public void setCausal(String causal) { this.causal = causal; }
/*    */ 
/*    */ 
/*    */   
/* 59 */   public Collection getResultados() { return this.resultados; }
/*    */ 
/*    */ 
/*    */   
/* 63 */   public void setResultados(Collection resultados) { this.resultados = resultados; }
/*    */ 
/*    */   
/* 66 */   public int getRegistrosAfectados() { return this.registrosAfectados; }
/*    */ 
/*    */   
/* 69 */   public void setRegistrosAfectados(int registrosAfectados) { this.registrosAfectados = registrosAfectados; }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\RespuestaBD.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */