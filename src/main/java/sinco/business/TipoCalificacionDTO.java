/*    */ package sinco.business;
/*    */ 
/*    */ import sinco.business.TipoCalificacionDTO;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TipoCalificacionDTO
/*    */ {
/*    */   private char codigo;
/*    */   private String descripcion;
/*    */   private int valor;
/*    */   private int rangoinicial;
/*    */   private int rangofinal;
/*    */   
/* 16 */   public void setCodigo(char c) { this.codigo = c; }
/*    */   private boolean justifica; private int porcentaje; private String satisfaccion; private int rangoiniindice; private int rangofinindice;
/*    */   
/* 19 */   public char getCodigo() { return this.codigo; }
/*    */ 
/*    */   
/* 22 */   public void setDescripcion(String d) { this.descripcion = d; }
/*    */ 
/*    */   
/* 25 */   public String getDescripcion() { return this.descripcion; }
/*    */ 
/*    */   
/* 28 */   public void setValor(int v) { this.valor = v; }
/*    */ 
/*    */   
/* 31 */   public int getValor() { return this.valor; }
/*    */ 
/*    */   
/* 34 */   public void setRangoInicial(int r) { this.rangoinicial = r; }
/*    */ 
/*    */   
/* 37 */   public int getRangoInicial() { return this.rangoinicial; }
/*    */ 
/*    */   
/* 40 */   public void setRangoFinal(int r) { this.rangofinal = r; }
/*    */ 
/*    */   
/* 43 */   public int getRangoFinal() { return this.rangofinal; }
/*    */ 
/*    */   
/* 46 */   public void setJustifica(boolean j) { this.justifica = j; }
/*    */ 
/*    */   
/* 49 */   public boolean getJustifica() { return this.justifica; }
/*    */ 
/*    */   
/* 52 */   public void setPorcentaje(int p) { this.porcentaje = p; }
/*    */ 
/*    */   
/* 55 */   public float getPorcentaje() { return this.porcentaje / 100.0F; }
/*    */ 
/*    */   
/* 58 */   public void setSatisfaccion(String s) { this.satisfaccion = s; }
/*    */ 
/*    */   
/* 61 */   public String getSatisfaccion() { return this.satisfaccion; }
/*    */ 
/*    */   
/* 64 */   public void setRangoInicialIndice(int r) { this.rangoiniindice = r; }
/*    */ 
/*    */   
/* 67 */   public int getRangoInicialIndice() { return this.rangoiniindice; }
/*    */ 
/*    */   
/* 70 */   public void setRangoFinalIndice(int r) { this.rangofinindice = r; }
/*    */ 
/*    */   
/* 73 */   public int getRangoFinalIndice() { return this.rangofinindice; }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\TipoCalificacionDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */