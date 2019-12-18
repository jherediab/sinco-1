/*     */ package sinco.business;
/*     */ 
/*     */ import sinco.business.SisSedesDTO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SisSedesDTO
/*     */ {
/*     */   private int idSede;
/*     */   private long nitEntidad;
/*     */   private String nombreSede;
/*     */   private String direccion;
/*     */   private String departamento;
/*     */   private String municipio;
/*     */   private String telefono;
/*     */   private String usuarioInsercion;
/*     */   private String fechaInsercion;
/*     */   private String usuarioModificacion;
/*     */   private String fechaModificacion;
/*     */   private String nombreNitEntidad;
/*     */   private String nombreDepartamento;
/*     */   private String nombreMunicipio;
/*     */   
/*  44 */   public void setIdSede(int p) { this.idSede = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  52 */   public int getIdSede() { return this.idSede; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  60 */   public void setNitEntidad(long p) { this.nitEntidad = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  68 */   public long getNitEntidad() { return this.nitEntidad; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  76 */   public void setNombreSede(String p) { this.nombreSede = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  84 */   public String getNombreSede() { return (this.nombreSede == null) ? "" : this.nombreSede; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  92 */   public void setDireccion(String p) { this.direccion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 100 */   public String getDireccion() { return (this.direccion == null) ? "" : this.direccion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 108 */   public void setDepartamento(String p) { this.departamento = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 116 */   public String getDepartamento() { return (this.departamento == null) ? "" : this.departamento; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 124 */   public void setMunicipio(String p) { this.municipio = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 132 */   public String getMunicipio() { return (this.municipio == null) ? "" : this.municipio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 140 */   public void setTelefono(String p) { this.telefono = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 148 */   public String getTelefono() { return (this.telefono == null) ? "" : this.telefono; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 156 */   public void setUsuarioInsercion(String p) { this.usuarioInsercion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 164 */   public String getUsuarioInsercion() { return (this.usuarioInsercion == null) ? "" : this.usuarioInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 172 */   public void setFechaInsercion(String p) { this.fechaInsercion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 180 */   public String getFechaInsercion() { return (this.fechaInsercion == null) ? "" : this.fechaInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 188 */   public void setUsuarioModificacion(String p) { this.usuarioModificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 196 */   public String getUsuarioModificacion() { return (this.usuarioModificacion == null) ? "" : this.usuarioModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 204 */   public void setFechaModificacion(String p) { this.fechaModificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 212 */   public String getFechaModificacion() { return (this.fechaModificacion == null) ? "" : this.fechaModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 217 */   public void setNombreNitEntidad(String p) { this.nombreNitEntidad = p; }
/*     */ 
/*     */   
/* 220 */   public String getNombreNitEntidad() { return (this.nombreNitEntidad == null) ? "" : this.nombreNitEntidad; }
/*     */ 
/*     */   
/* 223 */   public void setNombreDepartamento(String p) { this.nombreDepartamento = p; }
/*     */ 
/*     */   
/* 226 */   public String getNombreDepartamento() { return (this.nombreDepartamento == null) ? "" : this.nombreDepartamento; }
/*     */ 
/*     */   
/* 229 */   public void setNombreMunicipio(String p) { this.nombreMunicipio = p; }
/*     */ 
/*     */   
/* 232 */   public String getNombreMunicipio() { return (this.nombreMunicipio == null) ? "" : this.nombreMunicipio; }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\SisSedesDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */