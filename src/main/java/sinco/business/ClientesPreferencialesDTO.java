/*    */ package sinco.business;
/*    */ 
/*    */ import sinco.business.ClientesPreferencialesDTO;
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
/*    */ 
/*    */ public class ClientesPreferencialesDTO
/*    */ {
/*    */   private int codigoServicio;
/*    */   private int codigoPersona;
/*    */   private String fechaInsercion;
/*    */   private String usuarioInsercion;
/*    */   private String fechaModificacion;
/*    */   private String usuarioModificacion;
/*    */   private String nombrePersona;
/*    */   private String nombreArea;
/*    */   
/* 28 */   public void setCodigoServicio(int p) { this.codigoServicio = p; }
/*    */ 
/*    */   
/* 31 */   public int getCodigoServicio() { return this.codigoServicio; }
/*    */ 
/*    */ 
/*    */   
/* 35 */   public void setCodigoPersona(int p) { this.codigoPersona = p; }
/*    */ 
/*    */   
/* 38 */   public int getCodigoPersona() { return this.codigoPersona; }
/*    */ 
/*    */ 
/*    */   
/* 42 */   public void setFechaInsercion(String p) { this.fechaInsercion = p; }
/*    */ 
/*    */   
/* 45 */   public String getFechaInsercion() { return (this.fechaInsercion == null) ? "" : this.fechaInsercion; }
/*    */ 
/*    */ 
/*    */   
/* 49 */   public void setUsuarioInsercion(String p) { this.usuarioInsercion = p; }
/*    */ 
/*    */   
/* 52 */   public String getUsuarioInsercion() { return (this.usuarioInsercion == null) ? "" : this.usuarioInsercion; }
/*    */ 
/*    */ 
/*    */   
/* 56 */   public void setFechaModificacion(String p) { this.fechaModificacion = p; }
/*    */ 
/*    */   
/* 59 */   public String getFechaModificacion() { return (this.fechaModificacion == null) ? "" : this.fechaModificacion; }
/*    */ 
/*    */ 
/*    */   
/* 63 */   public void setUsuarioModificacion(String p) { this.usuarioModificacion = p; }
/*    */ 
/*    */   
/* 66 */   public String getUsuarioModificacion() { return (this.usuarioModificacion == null) ? "" : this.usuarioModificacion; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 73 */   public String getNombreArea() { return (this.nombreArea == null) ? "" : this.nombreArea; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 79 */   public void setNombreArea(String nombreArea) { this.nombreArea = nombreArea; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 85 */   public String getNombrePersona() { return (this.nombrePersona == null) ? "" : this.nombrePersona; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 91 */   public void setNombrePersona(String nombrePersona) { this.nombrePersona = nombrePersona; }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\ClientesPreferencialesDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */