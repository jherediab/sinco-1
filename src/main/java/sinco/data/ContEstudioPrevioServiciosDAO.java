/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.ContEstudioPrevioServiciosDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.ContEstudioPrevioServiciosDAO;
/*     */ import sinco.data.DBManager;
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
/*     */ public class ContEstudioPrevioServiciosDAO
/*     */ {
/*     */   ResultSet rs;
/*  28 */   DBManager dat = new DBManager();
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
/*  39 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/*     */     try {
/*  46 */       this.dat.close();
/*     */     }
/*  48 */     catch (Exception e) {
/*  49 */       Utilidades.writeError("SiauEstudioPrevioFactoresDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContEstudioPrevioServiciosDTO next() {
/*     */     try {
/*  60 */       if (this.rs.next()) {
/*  61 */         return leerRegistro();
/*     */       }
/*     */     }
/*  64 */     catch (Exception e) {
/*  65 */       e.printStackTrace();
/*  66 */       Utilidades.writeError("SiauEstudioPrevioFactoresDAO:next ", e);
/*     */     } 
/*  68 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContEstudioPrevioServiciosDTO leerRegistro() {
/*     */     try {
/*  78 */       ContEstudioPrevioServiciosDTO reg = new ContEstudioPrevioServiciosDTO();
/*     */       
/*  80 */       reg.setNumeroEstudio(this.rs.getInt("numero_estudio"));
/*  81 */       reg.setCodigoServicio(this.rs.getInt("codigo_servicio"));
/*  82 */       reg.setNombreServicio(this.rs.getString("nombre_servicio"));
/*  83 */       reg.setTipoServicio(this.rs.getInt("tipo_servicio"));
/*  84 */       reg.setDepartamento(this.rs.getString("departamento"));
/*  85 */       reg.setCiudad(this.rs.getString("ciudad"));
/*  86 */       reg.setNombreMunicipio(this.rs.getString("nombre_municipio"));
/*  87 */       reg.setFactor(this.rs.getDouble("factor"));
/*  88 */       reg.setAfiliados(this.rs.getInt("afiliados"));
/*  89 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  90 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  91 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  92 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  93 */       reg.setImputacion(this.rs.getString("imputacion"));
/*  94 */       reg.setNumeroDias(this.rs.getInt("dias"));
/*  95 */       reg.setValoUpc(this.rs.getDouble("valor_upc"));
/*  96 */       reg.setValorMes(this.rs.getDouble("valor_mes"));
/*  97 */       reg.setNumeroMeses(this.rs.getInt("meses"));
/*  98 */       reg.setPorcentajeAfiliados(this.rs.getDouble("porcentaje_afiliados"));
/*  99 */       return reg;
/*     */     }
/* 101 */     catch (Exception e) {
/* 102 */       e.printStackTrace();
/* 103 */       Utilidades.writeError("SiauEstudioPrevioFactoresDAO:leerRegistro ", e);
/*     */       
/* 105 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<ContEstudioPrevioServiciosDTO> cargarTodos(int numeroEstudio) {
/* 116 */     Collection<ContEstudioPrevioServiciosDTO> resultados = new ArrayList<ContEstudioPrevioServiciosDTO>();
/*     */     try {
/* 118 */       String s = "select   t.numero_estudio, t.codigo_servicio, t.departamento, t.ciudad, t.factor, t.afiliados, t.fecha_insercion, t.valor_upc, t.valor_mes, t.meses, t.dias, t.imputacion, t.usuario_insercion, t.fecha_modificacion, t.usuario_modificacion, s.nombre_servicio, s.codigo_macroservicio as tipo_servicio, c.nombre_ciudad as nombre_municipio, t.porcentaje_afiliados  from cont_estudio_previo_servicios t  left join par_departamento d on (d.codigo_departamento = t.departamento) left join par_ciudad c on (c.codigo_ciudad = t.ciudad and c.codigo_departamento=d.codigo_departamento) ,siau_servicio_salud s  where  s.codigo_servicio = t.codigo_servicio and t.numero_estudio=" + numeroEstudio;
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
/* 145 */       s = s + " order by t.imputacion";
/* 146 */       boolean rtaDB = this.dat.parseSql(s);
/* 147 */       if (!rtaDB) {
/* 148 */         return resultados;
/*     */       }
/* 150 */       this.rs = this.dat.getResultSet();
/* 151 */       while (this.rs.next()) {
/* 152 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 155 */     catch (Exception e) {
/* 156 */       e.printStackTrace();
/* 157 */       Utilidades.writeError("SiauEstudioPrevioFactoresDAO:cargarTodos ", e);
/*     */     } 
/* 159 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<ContEstudioPrevioServiciosDTO> cargarTodosAdicion(int numeroEstudio, int consecutivoContrato) {
/* 165 */     Collection<ContEstudioPrevioServiciosDTO> resultados = new ArrayList<ContEstudioPrevioServiciosDTO>();
/*     */     try {
/* 167 */       String s = "select   t.numero_estudio, t.codigo_servicio, t.departamento, t.ciudad, t.factor, t.afiliados, t.fecha_insercion, t.valor_upc, t.valor_mes, t.meses, t.dias, t.imputacion, t.usuario_insercion, t.fecha_modificacion, t.usuario_modificacion, s.nombre_servicio, s.codigo_macroservicio as tipo_servicio, c.nombre_ciudad as nombre_municipio, t.porcentaje_afiliados  from cont_estudio_previo_servicios t  left join par_departamento d on (d.codigo_departamento = t.departamento) left join par_ciudad c on (c.codigo_ciudad = t.ciudad and c.codigo_departamento=d.codigo_departamento) left join cont_contrato con on (con.numero_estudio=t.numero_estudio) left join cont_contrato_servicio  cs on (t.codigo_servicio = cs.codigo_servicio and con.consecutivo_contrato=cs.consecutivo_contrato) ,siau_servicio_salud s  where  s.codigo_servicio = t.codigo_servicio and t.numero_estudio=" + numeroEstudio + " and con.consecutivo_contrato=" + consecutivoContrato + " and cs.tipo='A'";
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
/* 200 */       s = s + " order by t.imputacion";
/* 201 */       boolean rtaDB = this.dat.parseSql(s);
/* 202 */       if (!rtaDB) {
/* 203 */         return resultados;
/*     */       }
/* 205 */       this.rs = this.dat.getResultSet();
/* 206 */       while (this.rs.next()) {
/* 207 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 210 */     catch (Exception e) {
/* 211 */       e.printStackTrace();
/* 212 */       Utilidades.writeError("SiauEstudioPrevioFactoresDAO:cargarTodos ", e);
/*     */     } 
/* 214 */     return resultados;
/*     */   }
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
/*     */   public ContEstudioPrevioServiciosDTO cargarRegistro(int numeroEstudio, int codigoServicio, String departamento, String ciudad) {
/*     */     try {
/* 229 */       String s = "select   t.numero_estudio, t.codigo_servicio, t.departamento, t.ciudad, t.factor, t.afiliados, t.fecha_insercion, t.valor_upc, t.valor_mes, t.meses, t.dias, t.imputacion, t.usuario_insercion, t.fecha_modificacion, t.usuario_modificacion, s.nombre_servicio, s.codigo_macroservicio as tipo_servicio, c.nombre_ciudad as nombre_municipio, t.porcentaje_afiliados  from cont_estudio_previo_servicios t  left join par_departamento d on (d.codigo_departamento = t.departamento) left join par_ciudad c on (c.codigo_ciudad = t.ciudad and c.codigo_departamento=d.codigo_departamento) ,siau_servicio_salud s  where  s.codigo_servicio = t.codigo_servicio and t.numero_estudio=" + numeroEstudio + " and t.codigo_servicio=" + codigoServicio + "  and departamento='" + departamento + "'" + "  and ciudad='" + ciudad + "'" + "";
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
/* 260 */       boolean rtaDB = this.dat.parseSql(s);
/* 261 */       if (!rtaDB) {
/* 262 */         return null;
/*     */       }
/* 264 */       this.rs = this.dat.getResultSet();
/* 265 */       if (this.rs.next()) {
/* 266 */         return leerRegistro();
/*     */       }
/*     */     }
/* 269 */     catch (Exception e) {
/* 270 */       e.printStackTrace();
/* 271 */       Utilidades.writeError("SiauEstudioPrevioFactoresDAO:cargarSiauEstudioPrevioFactores", e);
/*     */     } 
/* 273 */     return null;
/*     */   }
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
/*     */   public boolean eliminarRegistro(int numeroEstudio, int codigoServicio, String departamento, String ciudad) {
/*     */     try {
/* 287 */       String s = "delete from cont_estudio_previo_servicios where  numero_estudio=" + numeroEstudio + "  and codigo_servicio=" + codigoServicio + "  and departamento='" + departamento + "'" + "  and ciudad='" + ciudad + "'" + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 294 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 297 */     catch (Exception e) {
/* 298 */       e.printStackTrace();
/* 299 */       Utilidades.writeError("ContEstudioPrevioServiciosDAO:eliminarRegistro ", e);
/*     */       
/* 301 */       return false;
/*     */     } 
/*     */   }
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
/*     */   public boolean crearRegistro(int numeroEstudio, int codigoServicio, String departamento, String ciudad, String imputacion, double valorUpc, double factor, double afiliados, int meses, int dias, double porcentajeAfiliados, String usuarioInsercion) {
/*     */     try {
/* 332 */       String s = "insert into cont_estudio_previo_servicios(numero_estudio,codigo_servicio,departamento,ciudad,imputacion,valor_upc,factor,afiliados,meses,dias,porcentaje_afiliados,fecha_insercion,usuario_insercion) values (" + numeroEstudio + "," + "" + codigoServicio + "," + "'" + departamento + "'," + "'" + ciudad + "'," + "'" + imputacion + "'," + "" + valorUpc + "," + "" + factor + "," + "" + afiliados + "," + "" + meses + "," + "" + dias + "," + "" + porcentajeAfiliados + "," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
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
/* 361 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 364 */     catch (Exception e) {
/* 365 */       e.printStackTrace();
/* 366 */       Utilidades.writeError("%ContEstudioPrevioServiciosDAO:crearRegistro ", e);
/*     */       
/* 368 */       return false;
/*     */     } 
/*     */   }
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
/*     */   public boolean modificarRegistro(int numeroEstudio, int codigoServicio, String departamento, String ciudad, String imputacion, double valorUpc, double factor, double afiliados, int meses, int dias, double porcentajeAfiliados, String dptoAnt, String munAnt, String usuarioModificacion) {
/*     */     try {
/* 393 */       String s = "update cont_estudio_previo_servicios set  imputacion='" + imputacion + "'," + " valor_upc=" + valorUpc + "," + " factor=" + factor + "," + " afiliados=" + afiliados + "," + " meses=" + meses + "," + " dias=" + dias + "," + " porcentaje_afiliados=" + porcentajeAfiliados + "," + " departamento='" + departamento + "'," + " ciudad='" + ciudad + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " numero_estudio=" + numeroEstudio + " and codigo_servicio=" + codigoServicio + " and departamento='" + dptoAnt + "'" + " and ciudad='" + munAnt + "'" + "";
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
/* 411 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 414 */     catch (Exception e) {
/* 415 */       e.printStackTrace();
/* 416 */       Utilidades.writeError("ContEstudioPrevioServiciosDAO:modificarRegistro ", e);
/*     */       
/* 418 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean eliminarDeEstudio(int numeroEstudio) {
/*     */     try {
/* 424 */       String s = "delete from cont_estudio_previo_servicios where numero_estudio=" + numeroEstudio;
/*     */       
/* 426 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 429 */     catch (Exception e) {
/* 430 */       e.printStackTrace();
/* 431 */       Utilidades.writeError("ContratoFactoresDAO:eliminarRegistro " + e.getMessage());
/*     */       
/* 433 */       return false;
/*     */     } 
/*     */   }
/*     */   public double existeEnEstudio(int numeroEstudio, int codigoServicio, String departamento, String ciudad) {
/* 437 */     String s = "select factor from cont_estudio_previo_servicios  where numero_estudio=" + numeroEstudio + " and codigo_servicio=" + codigoServicio + "  and departamento='" + departamento + "'" + "  and ciudad='" + ciudad + "'";
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 443 */       boolean rta = this.dat.parseSql(s);
/* 444 */       if (!rta) return 99999.0D; 
/* 445 */       this.rs = this.dat.getResultSet();
/* 446 */       if (this.rs.next() && 
/* 447 */         !this.rs.wasNull()) {
/* 448 */         return this.rs.getDouble("factor");
/*     */       
/*     */       }
/*     */     }
/* 452 */     catch (Exception e) {
/* 453 */       e.printStackTrace();
/* 454 */       Utilidades.writeError("ContratoFactoresDAO:existeEnEstudio " + e.getMessage());
/*     */     } 
/* 456 */     return 99999.0D;
/*     */   }
/*     */   
/*     */   public int afiliadosEnEstudio(int numeroEstudio, int codigoServicio, String departamento, String ciudad) {
/* 460 */     String s = "select afiliados from cont_estudio_previo_servicios  where numero_estudio=" + numeroEstudio + " and codigo_servicio=" + codigoServicio + "  and departamento='" + departamento + "'" + "  and ciudad='" + ciudad + "'";
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 466 */       boolean rta = this.dat.parseSql(s);
/* 467 */       if (!rta) return 99999999; 
/* 468 */       this.rs = this.dat.getResultSet();
/* 469 */       if (this.rs.next() && 
/* 470 */         !this.rs.wasNull()) {
/* 471 */         return this.rs.getInt("afiliados");
/*     */       
/*     */       }
/*     */     }
/* 475 */     catch (Exception e) {
/* 476 */       e.printStackTrace();
/* 477 */       Utilidades.writeError("ContratoFactoresDAO:existeEnEstudio " + e.getMessage());
/*     */     } 
/* 479 */     return 99999999;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean copiarDeEstudio(int numeroEstudio, int nuevoNumeroEstudio) {
/* 485 */     String s = " insert into cont_estudio_previo_servicios (numero_estudio,codigo_servicio,departamento,ciudad,imputacion,valor_upc,factor,afiliados,meses,dias,porcentaje_afiliados,fecha_insercion,usuario_insercion), select " + nuevoNumeroEstudio + "," + " codigo_servicio,departamento,ciudad,imputacion,valor_upc,factor,afiliados," + " meses,dias,porcentaje_afiliados,fecha_insercion,usuario_insercion)," + " from cont_estudio_previo_servicios where numero_estudio=" + numeroEstudio;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 493 */     return this.dat.executeUpdate(s);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean existeServicioEnSucursal(int codSucursal, int codigoServicio) {
/*     */     try {
/* 499 */       String s = "select  t.numero_estudio,t.codigo_servicio,t.municipio,t.factor,t.afiliados,t.valor_mes,t.valor_upc,t.meses,t.dias,t.imputacion,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion,t.porcentaje_afiliados,s.nombre_servicio as nombre_servicio,s.tipo_servicio,c.nombreciud as nombre_municipio  from cont_estudio_previo_servicios t left join sldciud c on (t.municipio = c.idcoddaneciud) ,siau_servicios_salud s, siau_servicios_sucursal suc  where  s.codigo_servicio = t.codigo_servicio and t.codigo_servicio= suc.codigo_servicio and t.codigo_servicio=" + codigoServicio + "" + " and suc.codigo_sucursal=" + codSucursal;
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
/* 525 */       this.dat.parseSql(s);
/* 526 */       this.rs = this.dat.getResultSet();
/* 527 */       if (this.rs.next()) {
/* 528 */         return true;
/*     */       }
/*     */     }
/* 531 */     catch (Exception e) {
/* 532 */       e.printStackTrace();
/* 533 */       Utilidades.writeError("ContratoFactoresDAO:cargarContratoFactores " + e.getMessage());
/*     */     } 
/* 535 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean cargarDeEstudio(int numeroEstudio, String departamento, String ciudad) {
/*     */     try {
/* 545 */       String s = "select 0 as tipo_servicio, '' as nombre_municipio, factores.*,  salud.nombre_servicio as nombre_servicio  from siau_servicios_salud salud, cont_estudio_previo_servicios factores  where factores.codigo_servicio = salud.codigo_servicio  and factores.numero_estudio=" + numeroEstudio + "  and departamento='" + departamento + "'" + "  and ciudad='" + ciudad + "'" + " order by codigo_servicio";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 554 */       boolean rtaDB = this.dat.parseSql(s);
/* 555 */       this.rs = this.dat.getResultSet();
/* 556 */       return rtaDB;
/*     */     }
/* 558 */     catch (Exception e) {
/* 559 */       e.printStackTrace();
/* 560 */       Utilidades.writeError("ContratoFactoresDAO:cargarDeEstudio " + e.getMessage());
/*     */       
/* 562 */       return false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\ContEstudioPrevioServiciosDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */