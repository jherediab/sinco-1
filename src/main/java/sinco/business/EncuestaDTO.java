/*    */ package sinco.business;
/*    */ 
/*    */ import sinco.business.EncuestaDTO;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EncuestaDTO
/*    */ {
/*    */   private int numero;
/*    */   private int empleado_cliente;
/*    */   private int codigo_servicio;
/*    */   private int estado;
/*    */   private String nombreServicio;
/*    */   private String nombreEstado;
/*    */   private String descripcion;
/*    */   private String fecha;
/*    */   private String ciclo;
/*    */   
/* 25 */   public void setNumero(int p) { this.numero = p; }
/*    */ 
/*    */   
/* 28 */   public int getNumero() { return this.numero; }
/*    */ 
/*    */   
/* 31 */   public void setEmpleado_cliente(int p) { this.empleado_cliente = p; }
/*    */ 
/*    */   
/* 34 */   public int getEmpleado_cliente() { return this.empleado_cliente; }
/*    */ 
/*    */   
/* 37 */   public void setCodigo_servicio(int p) { this.codigo_servicio = p; }
/*    */ 
/*    */   
/* 40 */   public int getCodigo_servicio() { return this.codigo_servicio; }
/*    */ 
/*    */ 
/*    */   
/* 44 */   public void setEstado(int p) { this.estado = p; }
/*    */ 
/*    */   
/* 47 */   public int getEstado() { return this.estado; }
/*    */ 
/*    */ 
/*    */   
/* 51 */   public void setNombreServicio(String p) { this.nombreServicio = p; }
/*    */ 
/*    */   
/* 54 */   public String getNombreServicio() { return (this.nombreServicio == null) ? "" : this.nombreServicio; }
/*    */ 
/*    */ 
/*    */   
/* 58 */   public void setNombreEstado(String p) { this.nombreEstado = p; }
/*    */ 
/*    */   
/* 61 */   public String getNombreEstado() { return (this.nombreEstado == null) ? "" : this.nombreEstado; }
/*    */ 
/*    */ 
/*    */   
/* 65 */   public void setDescripcion(String p) { this.descripcion = p; }
/*    */ 
/*    */   
/* 68 */   public String getDescripcion() { return (this.descripcion == null) ? "" : this.descripcion; }
/*    */ 
/*    */ 
/*    */   
/* 72 */   public void setFecha(String p) { this.fecha = p; }
/*    */ 
/*    */   
/* 75 */   public String getFecha() { return (this.fecha == null) ? "" : this.fecha; }
/*    */ 
/*    */ 
/*    */   
/* 79 */   public void setCiclo(String p) { this.ciclo = p; }
/*    */ 
/*    */   
/* 82 */   public String getCiclo() { return (this.ciclo == null) ? "" : this.ciclo; }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\EncuestaDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */