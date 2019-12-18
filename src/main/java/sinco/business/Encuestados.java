/*     */ package sinco.business;
/*     */ 
/*     */ import sinco.business.Encuestados;
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
/*     */ public class Encuestados
/*     */ {
/*     */   private int numero;
/*     */   private int proveedor;
/*     */   private String nombres;
/*     */   private String apellidos;
/*     */   private int areaProveedor;
/*     */   private String nombreAreaProveedor;
/*     */   private String nombreClase;
/*     */   
/*  25 */   public String getApellidos() { return (this.apellidos == null) ? "" : this.apellidos; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  31 */   public void setApellidos(String apellidos) { this.apellidos = apellidos; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  37 */   public int getAreaProveedor() { return this.areaProveedor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  43 */   public void setAreaProveedor(int areaProveedor) { this.areaProveedor = areaProveedor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  49 */   public String getNombreAreaProveedor() { return (this.nombreAreaProveedor == null) ? "" : this.nombreAreaProveedor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  55 */   public void setNombreAreaProveedor(String nombreAreaProveedor) { this.nombreAreaProveedor = nombreAreaProveedor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  61 */   public String getNombres() { return (this.nombres == null) ? "" : this.nombres; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  67 */   public void setNombres(String nombres) { this.nombres = nombres; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  73 */   public int getNumero() { return this.numero; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  79 */   public void setNumero(int numero) { this.numero = numero; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  85 */   public int getProveedor() { return this.proveedor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  91 */   public void setProveedor(int proveedor) { this.proveedor = proveedor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  97 */   public String getNombreClase() { return (this.nombreClase == null) ? "" : this.nombreClase; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 103 */   public void setNombreClase(String nombreClase) { this.nombreClase = nombreClase; }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\Encuestados.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */