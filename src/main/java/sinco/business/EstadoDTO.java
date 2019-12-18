/*    */ package sinco.business;
/*    */ 
/*    */ import sinco.business.EstadoDTO;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EstadoDTO
/*    */ {
/*    */   private int codigo;
/*    */   private String descripcion;
/*    */   private String tipoestado;
/*    */   private boolean califica;
/*    */   
/* 15 */   public void setCodigo(int codigo) { this.codigo = codigo; }
/*    */   private boolean confiabilidad; private boolean oportunidad; private boolean cierra; private int rolgeneramail;
/*    */   private int porcentaje;
/*    */   
/* 19 */   public int getCodigo() { return this.codigo; }
/*    */ 
/*    */ 
/*    */   
/* 23 */   public void setDescripcion(String d) { this.descripcion = d; }
/*    */ 
/*    */ 
/*    */   
/* 27 */   public String getDescripcion() { return this.descripcion; }
/*    */ 
/*    */ 
/*    */   
/* 31 */   public void setTipoEstado(String tipoestado) { this.tipoestado = tipoestado; }
/*    */ 
/*    */ 
/*    */   
/* 35 */   public String getTipoEstado() { return this.tipoestado; }
/*    */ 
/*    */ 
/*    */   
/* 39 */   public void setCalifica(boolean califica) { this.califica = califica; }
/*    */ 
/*    */ 
/*    */   
/* 43 */   public boolean getCalifica() { return this.califica; }
/*    */ 
/*    */ 
/*    */   
/* 47 */   public void setConfiabilidad(boolean confiabilidad) { this.confiabilidad = confiabilidad; }
/*    */ 
/*    */ 
/*    */   
/* 51 */   public boolean getConfiabilidad() { return this.confiabilidad; }
/*    */ 
/*    */ 
/*    */   
/* 55 */   public void setOportunidad(boolean oportunidad) { this.oportunidad = oportunidad; }
/*    */ 
/*    */ 
/*    */   
/* 59 */   public boolean getOportunidad() { return this.oportunidad; }
/*    */ 
/*    */ 
/*    */   
/* 63 */   public void setCierra(boolean cierra) { this.cierra = cierra; }
/*    */ 
/*    */ 
/*    */   
/* 67 */   public boolean getCierra() { return this.cierra; }
/*    */ 
/*    */ 
/*    */   
/* 71 */   public void setRolGeneraMail(int rolgeneramail) { this.rolgeneramail = rolgeneramail; }
/*    */ 
/*    */ 
/*    */   
/* 75 */   public int getRolGeneraMail() { return this.rolgeneramail; }
/*    */ 
/*    */ 
/*    */   
/* 79 */   public void setPorcentaje(int i) { this.porcentaje = i; }
/*    */ 
/*    */ 
/*    */   
/* 83 */   public int getPorcentaje() { return this.porcentaje; }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\EstadoDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */