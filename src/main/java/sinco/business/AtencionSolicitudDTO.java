/*    */ package sinco.business;
/*    */ 
/*    */ import sinco.business.AtencionSolicitudDTO;
/*    */ 
/*    */ public class AtencionSolicitudDTO {
/*    */   private int numerosolicitud;
/*    */   private int consecutivo;
/*    */   private String observacion;
/*    */   private String nombreCaracteristica;
/*    */   private String fecha;
/*    */   int PersonaAtendio;
/*    */   
/* 13 */   public void setNumeroSolicitud(int n) { this.numerosolicitud = n; }
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
/* 27 */   public void setObservacion(String s) { this.observacion = s; }
/*    */ 
/*    */   
/* 30 */   public String getObservacion() { return (this.observacion == null) ? "" : ("" + this.observacion); }
/*    */ 
/*    */ 
/*    */   
/* 34 */   public void setFecha(String fe) { this.fecha = fe; }
/*    */ 
/*    */   
/* 37 */   public String getFecha() { return this.fecha; }
/*    */ 
/*    */ 
/*    */   
/* 41 */   public void setPersonaAtendio(int n) { this.PersonaAtendio = n; }
/*    */ 
/*    */ 
/*    */   
/* 45 */   public int getPersonaAtendio() { return this.PersonaAtendio; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 52 */   public String getNombreCaracteristica() { return (this.nombreCaracteristica == null) ? "" : this.nombreCaracteristica; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 58 */   public void setNombreCaracteristica(String nombreCaracteristica) { this.nombreCaracteristica = nombreCaracteristica; }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\AtencionSolicitudDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */