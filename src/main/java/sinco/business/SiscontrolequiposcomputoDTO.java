/*     */ package sinco.business;
/*     */ 
/*     */ import sinco.business.SiscontrolequiposcomputoDTO;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SiscontrolequiposcomputoDTO
/*     */ {
/*     */   private int nHojaControl;
/*     */   private String fechaInventario;
/*     */   private String marca;
/*     */   private int serial;
/*     */   private int nInventarioPc;
/*     */   private String ubicacion;
/*     */   private String estadoPc;
/*     */   private String marcaCpu;
/*     */   private int serialCpu;
/*     */   private int nInventarioCpu;
/*     */   private String memoriaRam;
/*     */   private String marcaDiscoDuro;
/*     */   private int serialDiscoDuro;
/*     */   private String procesador;
/*     */   private String marcaBoard;
/*     */   private int serialBoard;
/*     */   private String marcaTeclado;
/*     */   private int nInventarioT;
/*     */   private String estadoT;
/*     */   private String marcaMouse;
/*     */   private int nInventario;
/*     */   private String estadoM;
/*     */   private String conectorMause;
/*     */   private String conectorTeclado;
/*     */   private String marcaUnidadCd;
/*     */   private int serialUnidadCd;
/*     */   private String marcaFuentePoder;
/*     */   private String targetaRed;
/*     */   private String sistemaOperativo;
/*     */   private String particionDiscoDuro;
/*     */   private String microsoftOffice;
/*     */   private String versionOffice;
/*     */   private String navegadores;
/*     */   private String adobeReader;
/*     */   private String versionAdobe;
/*     */   private String antivirus;
/*     */   private String deepFreeze;
/*     */   private String versionDeepFreeze;
/*     */   private String otrosSoftware;
/*     */   private String versionSoftware;
/*     */   private String tipoMatenimiento;
/*     */   private String fechaRealizado;
/*     */   private String realizo;
/*     */   private String recibio;
/*     */   private String descripcion;
/*     */   private String observaciones;
/*     */   
/* 108 */   public void setNHojaControl(int p) { this.nHojaControl = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 116 */   public int getNHojaControl() { return this.nHojaControl; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 124 */   public void setFechaInventario(String p) { this.fechaInventario = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 132 */   public String getFechaInventario() { return (this.fechaInventario == null) ? "" : this.fechaInventario; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 140 */   public void setMarca(String p) { this.marca = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 148 */   public String getMarca() { return (this.marca == null) ? "" : this.marca; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 156 */   public void setSerial(int p) { this.serial = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 164 */   public int getSerial() { return this.serial; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 172 */   public void setNInventarioPc(int p) { this.nInventarioPc = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 180 */   public int getNInventarioPc() { return this.nInventarioPc; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 188 */   public void setUbicacion(String p) { this.ubicacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 196 */   public String getUbicacion() { return (this.ubicacion == null) ? "" : this.ubicacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 204 */   public void setEstadoPc(String p) { this.estadoPc = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 212 */   public String getEstadoPc() { return (this.estadoPc == null) ? "" : this.estadoPc; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 220 */   public void setMarcaCpu(String p) { this.marcaCpu = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 228 */   public String getMarcaCpu() { return (this.marcaCpu == null) ? "" : this.marcaCpu; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 236 */   public void setSerialCpu(int p) { this.serialCpu = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 244 */   public int getSerialCpu() { return this.serialCpu; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 252 */   public void setNInventarioCpu(int p) { this.nInventarioCpu = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 260 */   public int getNInventarioCpu() { return this.nInventarioCpu; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 268 */   public void setMemoriaRam(String p) { this.memoriaRam = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 276 */   public String getMemoriaRam() { return (this.memoriaRam == null) ? "" : this.memoriaRam; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 284 */   public void setMarcaDiscoDuro(String p) { this.marcaDiscoDuro = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 292 */   public String getMarcaDiscoDuro() { return (this.marcaDiscoDuro == null) ? "" : this.marcaDiscoDuro; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 300 */   public void setSerialDiscoDuro(int p) { this.serialDiscoDuro = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 308 */   public int getSerialDiscoDuro() { return this.serialDiscoDuro; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 316 */   public void setProcesador(String p) { this.procesador = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 324 */   public String getProcesador() { return (this.procesador == null) ? "" : this.procesador; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 332 */   public void setMarcaBoard(String p) { this.marcaBoard = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 340 */   public String getMarcaBoard() { return (this.marcaBoard == null) ? "" : this.marcaBoard; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 348 */   public void setSerialBoard(int p) { this.serialBoard = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 356 */   public int getSerialBoard() { return this.serialBoard; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 364 */   public void setMarcaTeclado(String p) { this.marcaTeclado = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 372 */   public String getMarcaTeclado() { return (this.marcaTeclado == null) ? "" : this.marcaTeclado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 380 */   public void setNInventarioT(int p) { this.nInventarioT = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 388 */   public int getNInventarioT() { return this.nInventarioT; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 396 */   public void setEstadoT(String p) { this.estadoT = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 404 */   public String getEstadoT() { return (this.estadoT == null) ? "" : this.estadoT; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 412 */   public void setMarcaMouse(String p) { this.marcaMouse = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 420 */   public String getMarcaMouse() { return (this.marcaMouse == null) ? "" : this.marcaMouse; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 428 */   public void setNInventario(int p) { this.nInventario = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 436 */   public int getNInventario() { return this.nInventario; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 444 */   public void setEstadoM(String p) { this.estadoM = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 452 */   public String getEstadoM() { return (this.estadoM == null) ? "" : this.estadoM; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 460 */   public void setConectorMause(String p) { this.conectorMause = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 468 */   public String getConectorMause() { return (this.conectorMause == null) ? "" : this.conectorMause; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 476 */   public void setConectorTeclado(String p) { this.conectorTeclado = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 484 */   public String getConectorTeclado() { return (this.conectorTeclado == null) ? "" : this.conectorTeclado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 492 */   public void setMarcaUnidadCd(String p) { this.marcaUnidadCd = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 500 */   public String getMarcaUnidadCd() { return (this.marcaUnidadCd == null) ? "" : this.marcaUnidadCd; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 508 */   public void setSerialUnidadCd(int p) { this.serialUnidadCd = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 516 */   public int getSerialUnidadCd() { return this.serialUnidadCd; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 524 */   public void setMarcaFuentePoder(String p) { this.marcaFuentePoder = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 532 */   public String getMarcaFuentePoder() { return (this.marcaFuentePoder == null) ? "" : this.marcaFuentePoder; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 540 */   public void setTargetaRed(String p) { this.targetaRed = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 548 */   public String getTargetaRed() { return (this.targetaRed == null) ? "" : this.targetaRed; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 556 */   public void setSistemaOperativo(String p) { this.sistemaOperativo = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 564 */   public String getSistemaOperativo() { return (this.sistemaOperativo == null) ? "" : this.sistemaOperativo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 572 */   public void setParticionDiscoDuro(String p) { this.particionDiscoDuro = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 580 */   public String getParticionDiscoDuro() { return (this.particionDiscoDuro == null) ? "" : this.particionDiscoDuro; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 588 */   public void setMicrosoftOffice(String p) { this.microsoftOffice = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 596 */   public String getMicrosoftOffice() { return (this.microsoftOffice == null) ? "" : this.microsoftOffice; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 604 */   public void setVersionOffice(String p) { this.versionOffice = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 612 */   public String getVersionOffice() { return (this.versionOffice == null) ? "" : this.versionOffice; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 620 */   public void setNavegadores(String p) { this.navegadores = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 628 */   public String getNavegadores() { return (this.navegadores == null) ? "" : this.navegadores; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 636 */   public void setAdobeReader(String p) { this.adobeReader = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 644 */   public String getAdobeReader() { return (this.adobeReader == null) ? "" : this.adobeReader; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 652 */   public void setVersionAdobe(String p) { this.versionAdobe = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 660 */   public String getVersionAdobe() { return (this.versionAdobe == null) ? "" : this.versionAdobe; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 668 */   public void setAntivirus(String p) { this.antivirus = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 676 */   public String getAntivirus() { return (this.antivirus == null) ? "" : this.antivirus; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 684 */   public void setDeepFreeze(String p) { this.deepFreeze = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 692 */   public String getDeepFreeze() { return (this.deepFreeze == null) ? "" : this.deepFreeze; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 700 */   public void setVersionDeepFreeze(String p) { this.versionDeepFreeze = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 708 */   public String getVersionDeepFreeze() { return (this.versionDeepFreeze == null) ? "" : this.versionDeepFreeze; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 716 */   public void setOtrosSoftware(String p) { this.otrosSoftware = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 724 */   public String getOtrosSoftware() { return (this.otrosSoftware == null) ? "" : this.otrosSoftware; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 732 */   public void setVersionSoftware(String p) { this.versionSoftware = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 740 */   public String getVersionSoftware() { return (this.versionSoftware == null) ? "" : this.versionSoftware; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 748 */   public void setTipoMatenimiento(String p) { this.tipoMatenimiento = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 756 */   public String getTipoMatenimiento() { return (this.tipoMatenimiento == null) ? "" : this.tipoMatenimiento; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 764 */   public void setFechaRealizado(String p) { this.fechaRealizado = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 772 */   public String getFechaRealizado() { return (this.fechaRealizado == null) ? "" : this.fechaRealizado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 780 */   public void setRealizo(String p) { this.realizo = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 788 */   public String getRealizo() { return (this.realizo == null) ? "" : this.realizo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 796 */   public void setRecibio(String p) { this.recibio = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 804 */   public String getRecibio() { return (this.recibio == null) ? "" : this.recibio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 812 */   public void setDescripcion(String p) { this.descripcion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 820 */   public String getDescripcion() { return (this.descripcion == null) ? "" : this.descripcion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 828 */   public void setObservaciones(String p) { this.observaciones = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 836 */   public String getObservaciones() { return (this.observaciones == null) ? "" : this.observaciones; }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\SiscontrolequiposcomputoDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */