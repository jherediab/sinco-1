/*    */ package sinco.business;
/*    */ 
/*    */ import sinco.business.ElemAreaDTO;
/*    */ 
/*    */ public class ElemAreaDTO {
/*    */   private int valor;
/*    */   private String descripcion;
/*    */   private String codigo;
/*    */   private boolean checked;
/*    */   private int acciones;
/*    */   
/*    */   public ElemAreaDTO() {}
/*    */   
/*    */   public ElemAreaDTO(int a, String b) {
/* 15 */     this.valor = a;
/* 16 */     this.descripcion = b;
/*    */   }
/*    */   
/*    */   public ElemAreaDTO(String a, String b, boolean c) {
/* 20 */     this.codigo = a;
/* 21 */     this.descripcion = b;
/* 22 */     this.checked = c;
/*    */   }
/*    */   
/*    */   public ElemAreaDTO(int a, String b, int c) {
/* 26 */     this.valor = a;
/* 27 */     this.descripcion = new String(b);
/* 28 */     this.acciones = c;
/*    */   }
/*    */ 
/*    */   
/* 32 */   public int getValor() { return this.valor; }
/*    */ 
/*    */ 
/*    */   
/* 36 */   public void setValor(int valor) { this.valor = valor; }
/*    */ 
/*    */ 
/*    */   
/* 40 */   public String getDescripcion() { return (this.descripcion == null) ? "" : this.descripcion; }
/*    */ 
/*    */ 
/*    */   
/* 44 */   public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
/*    */ 
/*    */ 
/*    */   
/* 48 */   public String getCodigo() { return (this.codigo == null) ? "" : this.codigo; }
/*    */ 
/*    */ 
/*    */   
/* 52 */   public void setCodigo(String codigo) { this.codigo = codigo; }
/*    */ 
/*    */ 
/*    */   
/* 56 */   public boolean isChecked() { return this.checked; }
/*    */ 
/*    */ 
/*    */   
/* 60 */   public void setChecked(boolean checked) { this.checked = checked; }
/*    */ 
/*    */ 
/*    */   
/* 64 */   public int getAcciones() { return this.acciones; }
/*    */ 
/*    */ 
/*    */   
/* 68 */   public void setAcciones(int acciones) { this.acciones = acciones; }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\ElemAreaDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */