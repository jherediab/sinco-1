/*    */ package sinco.business;
/*    */ 
/*    */ import sinco.business.SisPermisosGrupoDTO;
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
/*    */ public class SisPermisosGrupoDTO
/*    */ {
/*    */   private int grupo;
/*    */   private String permiso;
/*    */   private String descripcion;
/*    */   private String usuarioInsercion;
/*    */   private String fechaInsercion;
/*    */   private String usuarioModificacion;
/*    */   private String fechaModificacion;
/*    */   
/* 26 */   public int getGrupo() { return this.grupo; }
/*    */ 
/*    */ 
/*    */   
/* 30 */   public void setGrupo(int grupo) { this.grupo = grupo; }
/*    */ 
/*    */ 
/*    */   
/* 34 */   public String getPermiso() { return (this.permiso == null) ? "" : this.permiso; }
/*    */ 
/*    */ 
/*    */   
/* 38 */   public void setPermiso(String permiso) { this.permiso = permiso; }
/*    */ 
/*    */ 
/*    */   
/* 42 */   public String getDescripcion() { return (this.descripcion == null) ? "" : this.descripcion; }
/*    */ 
/*    */ 
/*    */   
/* 46 */   public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
/*    */ 
/*    */ 
/*    */   
/* 50 */   public String getUsuarioInsercion() { return (this.usuarioInsercion == null) ? "" : this.usuarioInsercion; }
/*    */ 
/*    */ 
/*    */   
/* 54 */   public void setUsuarioInsercion(String usuarioInsercion) { this.usuarioInsercion = usuarioInsercion; }
/*    */ 
/*    */ 
/*    */   
/* 58 */   public String getFechaInsercion() { return (this.fechaInsercion == null) ? "" : this.fechaInsercion; }
/*    */ 
/*    */ 
/*    */   
/* 62 */   public void setFechaInsercion(String fechaInsercion) { this.fechaInsercion = fechaInsercion; }
/*    */ 
/*    */ 
/*    */   
/* 66 */   public String getUsuarioModificacion() { return (this.usuarioModificacion == null) ? "" : this.usuarioModificacion; }
/*    */ 
/*    */ 
/*    */   
/* 70 */   public void setUsuarioModificacion(String usuarioModificacion) { this.usuarioModificacion = usuarioModificacion; }
/*    */ 
/*    */ 
/*    */   
/* 74 */   public String getFechaModificacion() { return (this.fechaModificacion == null) ? "" : this.fechaModificacion; }
/*    */ 
/*    */ 
/*    */   
/* 78 */   public void setFechaModificacion(String fechaModificacion) { this.fechaModificacion = fechaModificacion; }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\SisPermisosGrupoDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */