/*    */ package sinco.business;
/*    */ 
/*    */ import sinco.business.HistoriaSolicitudDTO;
/*    */ 
/*    */ public class HistoriaSolicitudDTO {
/*    */   private int numerosolicitud;
/*    */   private int consecutivo;
/*    */   private int estadofinal;
/*    */   private String fecha;
/*    */   private int estadoinicial;
/*    */   private String observaciones;
/*    */   
/* 13 */   public void setNumeroSolicitud(int i) { this.numerosolicitud = i; }
/*    */ 
/*    */   
/* 16 */   public int getNumeroSolicitud() { return this.numerosolicitud; }
/*    */ 
/*    */ 
/*    */   
/* 20 */   public void setConsecutivo(int c) { this.consecutivo = c; }
/*    */ 
/*    */   
/* 23 */   public int getConsecutivo() { return this.consecutivo; }
/*    */ 
/*    */ 
/*    */   
/* 27 */   public void setEstadoFinal(int i) { this.estadofinal = i; }
/*    */ 
/*    */   
/* 30 */   public int getEstadoFinal() { return this.estadofinal; }
/*    */ 
/*    */ 
/*    */   
/* 34 */   public void setFecha(String f) { this.fecha = f; }
/*    */ 
/*    */   
/* 37 */   public String getFecha() { return this.fecha; }
/*    */ 
/*    */ 
/*    */   
/* 41 */   public void setEstadoInicial(int i) { this.estadoinicial = i; }
/*    */ 
/*    */   
/* 44 */   public int getEstadoInicial() { return this.estadoinicial; }
/*    */ 
/*    */ 
/*    */   
/* 48 */   public void setObservaciones(String s) { this.observaciones = s; }
/*    */ 
/*    */   
/* 51 */   public String getObservaciones() { return (this.observaciones == null) ? "" : ("" + this.observaciones); }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\HistoriaSolicitudDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */