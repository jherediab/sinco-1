/*    */ package sinco.business;
/*    */ 
/*    */ import sinco.business.DetalleSolicitudDTO;
/*    */ 
/*    */ 
/*    */ public class DetalleSolicitudDTO
/*    */ {
/*    */   private int numero_solicitud;
/*    */   private int consecutivodetalle;
/*    */   private int codigocaracteristica;
/*    */   
/* 12 */   public void setNumeroSolicitud(int numero) { this.numero_solicitud = numero; }
/*    */   private String fechadiligenciamiento; private String observacion;
/*    */   private int valor;
/*    */   
/* 16 */   public int getNumeroSolicitud() { return this.numero_solicitud; }
/*    */ 
/*    */ 
/*    */   
/* 20 */   public void setConsecutivoDetalle(int consecutivodetalle) { this.consecutivodetalle = consecutivodetalle; }
/*    */ 
/*    */ 
/*    */   
/* 24 */   public int getConsecutivoDetalle() { return this.consecutivodetalle; }
/*    */ 
/*    */ 
/*    */   
/* 28 */   public void setCodigoCaracteristica(int codigocaracteristica) { this.codigocaracteristica = codigocaracteristica; }
/*    */ 
/*    */ 
/*    */   
/* 32 */   public int getCodigoCaracteristica() { return this.codigocaracteristica; }
/*    */ 
/*    */ 
/*    */   
/* 36 */   public void setFechaDiligenciamiento(String fechadiligenciamiento) { this.fechadiligenciamiento = fechadiligenciamiento; }
/*    */ 
/*    */ 
/*    */   
/* 40 */   public String getFechaDiligenciamiento() { return this.fechadiligenciamiento; }
/*    */ 
/*    */ 
/*    */   
/* 44 */   public void setObservacion(String observacion) { this.observacion = observacion; }
/*    */ 
/*    */ 
/*    */   
/* 48 */   public String getObservacion() { return (this.observacion == null) ? "" : this.observacion; }
/*    */ 
/*    */ 
/*    */   
/* 52 */   public void setValor(int i) { this.valor = i; }
/*    */ 
/*    */ 
/*    */   
/* 56 */   public int getValor() { return this.valor; }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\DetalleSolicitudDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */