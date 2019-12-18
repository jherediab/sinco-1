/*     */ package sinco.business;
/*     */ 
/*     */ import sinco.business.AreasAuditadasDTO;
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
/*     */ 
/*     */ 
/*     */ public class AreasAuditadasDTO
/*     */ {
/*     */   private int ciclo;
/*     */   private int area;
/*     */   private String nombreArea;
/*     */   private String codigoProceso;
/*     */   private String nombreProceso;
/*     */   private String coordinadorProceso;
/*     */   private String equipoProceso;
/*     */   private int codigoInforme;
/*     */   private String nombreInforme;
/*     */   private String coordinadorInforme;
/*     */   private String equipoInforme;
/*     */   private String asociadoA;
/*     */   private String usuarioInsercion;
/*     */   private String fechaInsercion;
/*     */   private String usuarioModificacion;
/*     */   private String fechaModificacion;
/*     */   private String nombreCoordinador;
/*     */   
/*  49 */   public void setArea(int p) { this.area = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  57 */   public int getArea() { return this.area; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  65 */   public void setUsuarioInsercion(String p) { this.usuarioInsercion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  73 */   public String getUsuarioInsercion() { return (this.usuarioInsercion == null) ? "" : this.usuarioInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  81 */   public void setFechaInsercion(String p) { this.fechaInsercion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  89 */   public String getFechaInsercion() { return (this.fechaInsercion == null) ? "" : this.fechaInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  97 */   public void setUsuarioModificacion(String p) { this.usuarioModificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 105 */   public String getUsuarioModificacion() { return (this.usuarioModificacion == null) ? "" : this.usuarioModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 113 */   public void setFechaModificacion(String p) { this.fechaModificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 121 */   public String getFechaModificacion() { return (this.fechaModificacion == null) ? "" : this.fechaModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 126 */   public void setNombreArea(String p) { this.nombreArea = p; }
/*     */ 
/*     */   
/* 129 */   public String getNombreArea() { return (this.nombreArea == null) ? "" : this.nombreArea; }
/*     */ 
/*     */ 
/*     */   
/* 133 */   public int getCiclo() { return this.ciclo; }
/*     */ 
/*     */ 
/*     */   
/* 137 */   public void setCiclo(int ciclo) { this.ciclo = ciclo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 143 */   public String getNombreProceso() { return (this.nombreProceso == null) ? "" : this.nombreProceso; }
/*     */ 
/*     */ 
/*     */   
/* 147 */   public void setNombreProceso(String nombreProceso) { this.nombreProceso = nombreProceso; }
/*     */ 
/*     */ 
/*     */   
/* 151 */   public String getCoordinadorProceso() { return (this.coordinadorProceso == null) ? "" : this.coordinadorProceso; }
/*     */ 
/*     */ 
/*     */   
/* 155 */   public void setCoordinadorProceso(String coordinadorProceso) { this.coordinadorProceso = coordinadorProceso; }
/*     */ 
/*     */ 
/*     */   
/* 159 */   public String getEquipoProceso() { return (this.equipoProceso == null) ? "" : this.equipoProceso; }
/*     */ 
/*     */ 
/*     */   
/* 163 */   public void setEquipoProceso(String equipoProceso) { this.equipoProceso = equipoProceso; }
/*     */ 
/*     */ 
/*     */   
/* 167 */   public int getCodigoInforme() { return this.codigoInforme; }
/*     */ 
/*     */ 
/*     */   
/* 171 */   public void setCodigoInforme(int codigoInforme) { this.codigoInforme = codigoInforme; }
/*     */ 
/*     */ 
/*     */   
/* 175 */   public String getNombreInforme() { return (this.nombreInforme == null) ? "" : this.nombreInforme; }
/*     */ 
/*     */ 
/*     */   
/* 179 */   public void setNombreInforme(String nombreInforme) { this.nombreInforme = nombreInforme; }
/*     */ 
/*     */ 
/*     */   
/* 183 */   public String getCoordinadorInforme() { return (this.coordinadorInforme == null) ? "" : this.coordinadorInforme; }
/*     */ 
/*     */ 
/*     */   
/* 187 */   public void setCoordinadorInforme(String coordinadorInforme) { this.coordinadorInforme = coordinadorInforme; }
/*     */ 
/*     */ 
/*     */   
/* 191 */   public String getEquipoInforme() { return (this.equipoInforme == null) ? "" : this.equipoInforme; }
/*     */ 
/*     */ 
/*     */   
/* 195 */   public void setEquipoInforme(String equipoInforme) { this.equipoInforme = equipoInforme; }
/*     */ 
/*     */ 
/*     */   
/* 199 */   public String getAsociadoA() { return (this.asociadoA == null) ? "" : this.asociadoA; }
/*     */ 
/*     */ 
/*     */   
/* 203 */   public void setAsociadoA(String asociadoA) { this.asociadoA = asociadoA; }
/*     */ 
/*     */ 
/*     */   
/* 207 */   public String getNombreCoordinador() { return (this.nombreCoordinador == null) ? "" : this.nombreCoordinador; }
/*     */ 
/*     */ 
/*     */   
/* 211 */   public void setNombreCoordinador(String nombreCoordinador) { this.nombreCoordinador = nombreCoordinador; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 218 */   public String getCodigoProceso() { return (this.codigoProceso == null) ? "" : this.codigoProceso; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 225 */   public void setCodigoProceso(String codigoProceso) { this.codigoProceso = codigoProceso; }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\AreasAuditadasDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */