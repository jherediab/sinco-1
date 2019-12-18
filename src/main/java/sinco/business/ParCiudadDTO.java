/*    */ package sinco.business;
/*    */ 
/*    */ import sinco.business.ParCiudadDTO;
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
/*    */ public class ParCiudadDTO
/*    */ {
/*    */   private String codigoCiudad;
/*    */   private String nombreCiudad;
/*    */   private String codigoDepartamento;
/*    */   
/* 22 */   public void setCodigoCiudad(String p) { this.codigoCiudad = p; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 30 */   public String getCodigoCiudad() { return (this.codigoCiudad == null) ? "" : this.codigoCiudad; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 38 */   public void setNombreCiudad(String p) { this.nombreCiudad = p; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 46 */   public String getNombreCiudad() { return (this.nombreCiudad == null) ? "" : this.nombreCiudad; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 54 */   public void setCodigoDepartamento(String p) { this.codigoDepartamento = p; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 62 */   public String getCodigoDepartamento() { return (this.codigoDepartamento == null) ? "" : this.codigoDepartamento; }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\ParCiudadDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */