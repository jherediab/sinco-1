/*    */ package sinco.business;
/*    */ 
/*    */ import sinco.business.UnidadMedidaDTO;
/*    */ 
/*    */ public class UnidadMedidaDTO
/*    */ {
/*    */   private String codigo;
/*    */   
/*  9 */   public void setCodigo(String s) { this.codigo = s; }
/*    */   private String descripcion; private int factor;
/*    */   
/* 12 */   public String getCodigo() { return this.codigo; }
/*    */ 
/*    */   
/* 15 */   public void setDescripcion(String s) { this.descripcion = s; }
/*    */ 
/*    */   
/* 18 */   public String getDescripcion() { return this.descripcion; }
/*    */ 
/*    */   
/* 21 */   public int getFactor() { return this.factor; }
/*    */ 
/*    */   
/* 24 */   public void setFactor(int f) { this.factor = f; }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\UnidadMedidaDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */