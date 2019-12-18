/*     */ package sinco.business;
/*     */ 
/*     */ import sinco.business.ServicioAreaDTO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ServicioAreaDTO
/*     */ {
/*     */   private int codigoArea;
/*     */   private int codigoServicio;
/*     */   private int personaCargo;
/*     */   private String nombreArea;
/*     */   private String ubicacion;
/*     */   private String nombreEspecialista;
/*     */   private String nombreServicio;
/*     */   private String nombreResponsable;
/*     */   private String usuarioInsercion;
/*     */   private String fechaInsercion;
/*     */   private String usuarioModificacion;
/*     */   private String fechaModificacion;
/*     */   
/*  24 */   public void setCodigoArea(int i) { this.codigoArea = i; }
/*     */ 
/*     */   
/*  27 */   public int getCodigoArea() { return this.codigoArea; }
/*     */ 
/*     */ 
/*     */   
/*  31 */   public void setCodigoServicio(int i) { this.codigoServicio = i; }
/*     */ 
/*     */ 
/*     */   
/*  35 */   public int getCodigoServicio() { return this.codigoServicio; }
/*     */ 
/*     */ 
/*     */   
/*  39 */   public void setPersonaCargo(int personacargo) { this.personaCargo = personacargo; }
/*     */ 
/*     */ 
/*     */   
/*  43 */   public int getPersonaCargo() { return this.personaCargo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  49 */   public String getNombreArea() { return (this.nombreArea == null) ? "" : this.nombreArea; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  55 */   public void setNombreArea(String nombreArea) { this.nombreArea = nombreArea; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  61 */   public String getUbicacion() { return (this.ubicacion == null) ? "" : this.ubicacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  67 */   public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  73 */   public String getNombreEspecialista() { return (this.nombreEspecialista == null) ? "" : this.nombreEspecialista; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  79 */   public void setNombreEspecialista(String nombreEspecialista) { this.nombreEspecialista = nombreEspecialista; }
/*     */ 
/*     */   
/*  82 */   public String getUsuarioInsercion() { return (this.usuarioInsercion == null) ? "" : this.usuarioInsercion; }
/*     */ 
/*     */   
/*  85 */   public void setUsuarioInsercion(String usuarioInsercion) { this.usuarioInsercion = usuarioInsercion; }
/*     */ 
/*     */   
/*  88 */   public String getFechaInsercion() { return (this.fechaInsercion == null) ? "" : this.fechaInsercion; }
/*     */ 
/*     */   
/*  91 */   public void setFechaInsercion(String fechaInsercion) { this.fechaInsercion = fechaInsercion; }
/*     */ 
/*     */   
/*  94 */   public String getUsuarioModificacion() { return (this.usuarioModificacion == null) ? "" : this.usuarioModificacion; }
/*     */ 
/*     */   
/*  97 */   public void setUsuarioModificacion(String usuarioModificacion) { this.usuarioModificacion = usuarioModificacion; }
/*     */ 
/*     */   
/* 100 */   public String getFechaModificacion() { return (this.fechaModificacion == null) ? "" : this.fechaModificacion; }
/*     */ 
/*     */   
/* 103 */   public void setFechaModificacion(String fechaModificacion) { this.fechaModificacion = fechaModificacion; }
/*     */ 
/*     */   
/* 106 */   public String getNombreServicio() { return (this.nombreServicio == null) ? "" : this.nombreServicio; }
/*     */ 
/*     */   
/* 109 */   public void setNombreServicio(String nombreServicio) { this.nombreServicio = nombreServicio; }
/*     */ 
/*     */   
/* 112 */   public String getNombreResponsable() { return (this.nombreResponsable == null) ? "" : this.nombreResponsable; }
/*     */ 
/*     */   
/* 115 */   public void setNombreResponsable(String nombreResponsable) { this.nombreResponsable = nombreResponsable; }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\ServicioAreaDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */