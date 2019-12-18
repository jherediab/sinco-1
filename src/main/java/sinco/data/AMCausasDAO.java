/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.AMCausasDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.AMCausasDAO;
/*     */ import sinco.data.DBManager;
/*     */ 

/*     */ 
/*     */ public class AMCausasDAO
/*     */ {
/*     */   ResultSet rs;
/*  26 */   DBManager dat = new DBManager();
/*     */ 
 
/*  35 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/*     */     try {
/*  42 */       this.dat.close();
/*     */     }
/*  44 */     catch (Exception e) {
/*  45 */       Utilidades.writeError("AMCausasFactory:close", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AMCausasDTO next() {
/*     */     try {
/*  56 */       if (this.rs.next()) {
/*  57 */         return leerRegistro();
/*     */       }
/*     */     }
/*  60 */     catch (Exception e) {
/*  61 */       e.printStackTrace();
/*  62 */       Utilidades.writeError("AMCausasFactory:next ", e);
/*     */     } 
/*  64 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AMCausasDTO leerRegistro() {
/*     */     try {
/*  74 */       AMCausasDTO reg = new AMCausasDTO();
/*  75 */       reg.setNumero(this.rs.getInt("numero"));
/*  76 */       reg.setConsecutivo(this.rs.getInt("consecutivo"));
/*  77 */       reg.setTipo(this.rs.getString("tipo"));
/*  78 */       reg.setPorque(this.rs.getString("porque"));
/*  79 */       reg.setAccion(this.rs.getString("accion"));
/*  80 */       reg.setBeneficios(this.rs.getString("beneficios"));
/*  81 */       reg.setBeneficio(this.rs.getString("beneficio"));
/*  82 */       reg.setResponsable(this.rs.getInt("responsable"));
/*  83 */       reg.setFechaInicio(this.rs.getString("fecha_inicio"));
/*  84 */       reg.setFechaEstimadaTerminacion(this.rs.getString("fecha_estimada_terminacion"));
/*  85 */       reg.setFechaRealTerminacion(this.rs.getString("fecha_real_terminacion"));
/*  86 */       reg.setEstado(this.rs.getInt("estado"));
/*  87 */       reg.setProrrogas(this.rs.getInt("prorrogas"));
/*  88 */       reg.setNombreEstado(this.rs.getString("nombreEstado"));
/*  89 */       reg.setApellidos(this.rs.getString("apellidos"));
/*  90 */       reg.setNombres(this.rs.getString("nombres"));
/*     */       try {
/*  92 */         reg.setSeguimientos(this.rs.getInt("seguimientos"));
/*     */       }
/*  94 */       catch (Exception e) {}
/*     */ 
/*     */ 
/*     */       
/*  98 */       return reg;
/*     */     }
/* 100 */     catch (Exception e) {
/* 101 */       e.printStackTrace();
/* 102 */       Utilidades.writeError("AMCausasFactory:leerRegistro ", e);
/*     */       
/* 104 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<AMCausasDTO> cargarTodos(int numero) {
/* 113 */     Collection<AMCausasDTO> resultados = new ArrayList<AMCausasDTO>();
/*     */     
/*     */     try {
/* 116 */       String s = "select c.numero, c.consecutivo, c.tipo, c.porque, c.accion, c.beneficios, c.beneficio, c.responsable, c.fecha_inicio, c.fecha_estimada_terminacion, c.fecha_real_terminacion, c.fecha_revision, c.estado, c.prorrogas, c.fecha_modificacion, c.usuario_insercion, c.fecha_insercion, c.usuario_modificacion, c.justificacion, ec.Descripcion as Nombreestado, p.Apellidos, p.Nombres, sum(case when seg.observacion is not null then 1 else 0 end) seguimientos from   Am_Causas c  \t     left join Am_Seguimiento   Seg on(   c.Numero = Seg.Numero   and c.Consecutivo = Seg.Causa   and Seg.Automatico = 'N'),        Am_Estado_Causas ec,        sis_usuarios p where  c.Estado = ec.Codigo   and c.Responsable = p.Codigo_Empleado   and c.numero=" + numero + " group by " + " c.numero," + " c.consecutivo," + " c.tipo," + " c.porque," + " c.accion," + " c.beneficios," + " c.beneficio," + " c.responsable," + " c.fecha_inicio," + " c.fecha_estimada_terminacion," + " c.fecha_real_terminacion," + " c.fecha_revision," + " c.estado," + " c.prorrogas," + " c.fecha_modificacion," + " c.usuario_insercion," + " c.fecha_insercion," + " c.usuario_modificacion," + " c.justificacion," + " ec.Descripcion," + " p.Apellidos," + " p.Nombres" + " order by c.consecutivo";
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
/*     */       
/* 173 */       boolean rtaDB = this.dat.parseSql(s);
/* 174 */       if (!rtaDB) {
/* 175 */         return resultados;
/*     */       }
/*     */       
/* 178 */       this.rs = this.dat.getResultSet();
/* 179 */       while (this.rs.next()) {
/* 180 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 183 */     catch (Exception e) {
/* 184 */       e.printStackTrace();
/* 185 */       Utilidades.writeError("AMCausasFactory:cargarTodos ", e);
/*     */     } 
/* 187 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AMCausasDTO cargarAMCausa(int numero, int consecutivo) {
/*     */     try {
/* 197 */       String s = "select c.numero, c.consecutivo, c.tipo, c.porque, c.accion, c.beneficios, c.beneficio, c.responsable, c.fecha_inicio, c.fecha_estimada_terminacion, c.fecha_real_terminacion, c.fecha_revision, c.estado, c.prorrogas, c.fecha_modificacion, c.usuario_insercion, c.fecha_insercion, c.usuario_modificacion, c.justificacion, ec.Descripcion as Nombreestado, p.Apellidos, p.Nombres from   Am_Causas c,        Am_Estado_Causas ec,        sis_usuarios p where  c.Estado = ec.Codigo  and c.Responsable = p.Codigo_Empleado  and c.numero=" + numero + "  and c.consecutivo=" + consecutivo;
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
/* 226 */       boolean rtaDB = this.dat.parseSql(s);
/* 227 */       if (!rtaDB) {
/* 228 */         return null;
/*     */       }
/*     */       
/* 231 */       this.rs = this.dat.getResultSet();
/* 232 */       if (this.rs.next()) {
/* 233 */         return leerRegistro();
/*     */       }
/*     */     }
/* 236 */     catch (Exception e) {
/* 237 */       e.printStackTrace();
/* 238 */       Utilidades.writeError("AMCausasFactory:cargarAMCausas ", e);
/*     */     } 
/* 240 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int siguienteRegistro(int numero) {
/* 249 */     int retorno = 1;
/* 250 */     String s = "select max(consecutivo) from am_causas  where numero=" + numero;
/*     */     try {
/* 252 */       boolean rta = this.dat.parseSql(s);
/* 253 */       if (!rta) return 0; 
/* 254 */       this.rs = this.dat.getResultSet();
/* 255 */       if (this.rs.next()) {
/* 256 */         s = this.rs.getString(1);
/* 257 */         if (!this.rs.wasNull()) {
/* 258 */           retorno = Integer.parseInt(s) + 1;
/*     */         }
/*     */       } 
/* 261 */       return retorno;
/*     */     }
/* 263 */     catch (Exception e) {
/* 264 */       e.printStackTrace();
/* 265 */       Utilidades.writeError("AMCausasFactory:siguienteRegistro", e);
/*     */       
/* 267 */       return 0;
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
/*     */   public boolean crearRegistro(int numero, String tipo, String porque, String accion, int beneficio_mc, int beneficio_ads, int beneficio_rdc, int beneficio_avp, int beneficio_otr, int beneficio_mo, String beneficio, int responsable, String fechaEstimadaTerminacion, int estado, String usuario) {
/* 292 */     int elSiguiente = siguienteRegistro(numero);
/* 293 */     if (elSiguiente == 0) {
/* 294 */       return false;
/*     */     }
/*     */     try {
/* 297 */       String s = "insert into am_causas ( numero,consecutivo,tipo,porque,accion,beneficios,beneficio,responsable,fecha_inicio,fecha_estimada_terminacion,estado,prorrogas,fecha_insercion,usuario_insercion)";
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
/* 313 */       s = s + " values (";
/* 314 */       s = s + "" + numero + ",";
/* 315 */       s = s + "" + elSiguiente + ",";
/* 316 */       s = s + "'" + tipo + "',";
/* 317 */       s = s + "'" + porque + "',";
/* 318 */       s = s + "'" + accion + "',";
/* 319 */       String beneficios = ((beneficio_mc == 1) ? "S" : "N") + ((beneficio_ads == 1) ? "S" : "N") + ((beneficio_rdc == 1) ? "S" : "N") + ((beneficio_avp == 1) ? "S" : "N") + ((beneficio_otr == 1) ? "S" : "N") + ((beneficio_mo == 1) ? "S" : "N");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 326 */       s = s + "'" + beneficios + "',";
/* 327 */       s = s + "'" + beneficio + "',";
/* 328 */       s = s + "" + responsable + ",";
/* 329 */       s = s + "" + Utilidades.getFechaBD() + ",";
/* 330 */       s = s + "" + Utilidades.formatoFecha2(fechaEstimadaTerminacion) + ",";
/* 331 */       s = s + "" + estado + ",";
/* 332 */       s = s + "0,";
/* 333 */       s = s + "" + Utilidades.getFechaBD() + ",";
/* 334 */       s = s + "'" + usuario + "'";
/* 335 */       s = s + ")";
/* 336 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 339 */     catch (Exception e) {
/* 340 */       e.printStackTrace();
/* 341 */       Utilidades.writeError("AMCausasFactory:crearRegistro", e);
/*     */       
/* 343 */       return false;
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
/*     */   public boolean modificarRegistro(int numero, int consecutivo, String tipo, String porque, String accion, int beneficio_mc, int beneficio_ads, int beneficio_rdc, int beneficio_avp, int beneficio_otr, int beneficio_mo, String beneficio, int responsable, String fechaEstimadaTerminacion, int estado, String usuario, String justificacion) {
/*     */     try {
/* 371 */       String s = "update am_causas set ";
/* 372 */       s = s + "tipo='" + tipo + "',";
/* 373 */       s = s + "porque='" + porque + "',";
/* 374 */       s = s + "accion='" + accion + "',";
/*     */       
/* 376 */       String beneficios = ((beneficio_mc == 1) ? "S" : "N") + ((beneficio_ads == 1) ? "S" : "N") + ((beneficio_rdc == 1) ? "S" : "N") + ((beneficio_avp == 1) ? "S" : "N") + ((beneficio_otr == 1) ? "S" : "N") + ((beneficio_mo == 1) ? "S" : "N");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 383 */       s = s + "beneficios='" + beneficios + "',";
/* 384 */       s = s + "beneficio='" + beneficio + "',";
/* 385 */       s = s + "responsable=" + responsable + ",";
/* 386 */       s = s + "fecha_estimada_terminacion=" + Utilidades.formatoFecha2(fechaEstimadaTerminacion) + ",";
/* 387 */       s = s + "estado=" + estado + ",";
/* 388 */       s = s + "justificacion='" + justificacion + "',";
/* 389 */       s = s + "fecha_modificacion=" + Utilidades.getFechaBD() + ",";
/* 390 */       s = s + "usuario_modificacion='" + usuario + "'";
/* 391 */       s = s + " where ";
/* 392 */       s = s + " numero=" + numero;
/* 393 */       s = s + " and consecutivo=" + consecutivo;
/* 394 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 397 */     catch (Exception e) {
/* 398 */       e.printStackTrace();
/* 399 */       Utilidades.writeError("AMCausasFactory:modificarRegistro", e);
/*     */       
/* 401 */       return false;
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
/*     */   public boolean modificarResponsable(int numero, int consecutivo, int responsable, String usuario) {
/*     */     try {
/* 414 */       String s = "update am_causas set ";
/* 415 */       s = s + "justificacion='Cambio de responsable',";
/* 416 */       s = s + "responsable=" + responsable + ",";
/* 417 */       s = s + "fecha_modificacion=" + Utilidades.getFechaBD() + ",";
/* 418 */       s = s + "usuario_modificacion='" + usuario + "'";
/* 419 */       s = s + " where ";
/* 420 */       s = s + " numero=" + numero;
/* 421 */       s = s + " and consecutivo=" + consecutivo;
/* 422 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 425 */     catch (Exception e) {
/* 426 */       e.printStackTrace();
/* 427 */       Utilidades.writeError("AMCausasFactory:modificarResponsable", e);
/*     */       
/* 429 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String fechaEstimadaTerminacion(int numero) {
/* 439 */     String fecha = "";
/*     */     try {
/* 441 */       String s = "select max(fecha_estimada_terminacion) from am_causas where numero=" + numero;
/* 442 */       boolean rta = this.dat.parseSql(s);
/* 443 */       if (!rta) return ""; 
/* 444 */       this.rs = this.dat.getResultSet();
/* 445 */       if (this.rs.next()) {
/* 446 */         fecha = this.rs.getString(1);
/* 447 */         if (!this.rs.wasNull()) {
/* 448 */           return fecha;
/*     */         }
/*     */       }
/*     */     
/* 452 */     } catch (Exception e) {
/* 453 */       e.printStackTrace();
/* 454 */       Utilidades.writeError("AMAccionesFactory:fechaEstimadaTerminacion ", e);
/*     */     } 
/* 456 */     return "";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean actualizarFechaEstimadaTerminacion(int numero, String usuario) {
/*     */     try {
/* 466 */       String s = "update am_acciones set ";
/* 467 */       s = s + "fecha_estimada_terminacion=" + Utilidades.formatoFecha2(fechaEstimadaTerminacion(numero)) + ",";
/* 468 */       s = s + "justificacion='Actualización de fecha estimada de terminación',";
/* 469 */       s = s + "fecha_modificacion=" + Utilidades.getFechaBD() + ",";
/* 470 */       s = s + "usuario_modificacion='" + usuario + "'";
/* 471 */       s = s + " where ";
/* 472 */       s = s + " numero=" + numero;
/* 473 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 476 */     catch (Exception e) {
/* 477 */       e.printStackTrace();
/* 478 */       Utilidades.writeError("AMCausasFactory:modificarRegistro", e);
/*     */       
/* 480 */       return false;
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
/*     */   public boolean actulizarRevision(int numero, int consecutivo, String fechaEstimadaTerminacion, char prorroga, String usuario, String justificacion) {
/*     */     try {
/* 497 */       String s = "update am_causas set ";
/* 498 */       s = s + "justificacion='" + justificacion + "'";
/* 499 */       s = s + ",fecha_estimada_terminacion = case when fecha_estimada_terminacion<" + Utilidades.formatoFecha2(fechaEstimadaTerminacion) + " then " + Utilidades.formatoFecha2(fechaEstimadaTerminacion) + " else fecha_estimada_terminacion end";
/* 500 */       s = s + ",fecha_modificacion=" + Utilidades.getFechaBD();
/* 501 */       s = s + ",usuario_modificacion='" + usuario + "'";
/* 502 */       if (prorroga == 'S') {
/* 503 */         s = s + ",prorrogas=prorrogas+1";
/*     */       }
/* 505 */       s = s + " where ";
/* 506 */       s = s + " numero=" + numero;
/* 507 */       s = s + " and consecutivo=" + consecutivo;
/* 508 */      boolean rta = this.dat.executeUpdate(s);
/*     */       
/* 510 */       if (!rta) return false;
/*     */       
/* 512 */       s = "update am_acciones set ";
/* 513 */       s = s + "fecha_estimada_terminacion=" + Utilidades.formatoFecha2(fechaEstimadaTerminacion(numero)) + ",";
/* 514 */       s = s + "justificacion='" + justificacion + "',";
/* 515 */       s = s + "fecha_modificacion=" + Utilidades.getFechaBD() + ",";
/* 516 */       s = s + "usuario_modificacion='" + usuario + "'";
/* 517 */       s = s + " where ";
/* 518 */       s = s + " numero=" + numero;
/* 519 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 522 */     catch (Exception e) {
/* 523 */       e.printStackTrace();
/* 524 */       Utilidades.writeError("AMCausasFactory:modificarRegistro", e);
/*     */       
/* 526 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean actulizarEstado(int numero, int consecutivo, int estado, String usuario, String justificacion) {
/*     */     try {
/* 536 */       String s = "update am_causas set ";
/* 537 */       s = s + "estado=" + estado + ",";
/* 538 */       s = s + "justificacion='" + justificacion + "',";
/* 539 */       s = s + "fecha_modificacion=" + Utilidades.getFechaBD() + ",";
/* 540 */       s = s + "usuario_modificacion='" + usuario + "'";
/* 541 */       if (estado == 2) {
/* 542 */         s = s + ",fecha_real_terminacion=" + Utilidades.formatoFecha2(Utilidades.ahora());
/*     */       }
/* 544 */       s = s + " where ";
/* 545 */       s = s + " numero=" + numero;
/* 546 */       s = s + " and consecutivo=" + consecutivo;
/* 547 */       return this.dat.executeUpdate(s);
/*     */     }
/* 549 */     catch (Exception e) {
/* 550 */       e.printStackTrace();
/* 551 */       Utilidades.writeError("AMCausasFactory:modificarRegistro", e);
/*     */       
/* 553 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int numeroCausas(int numero) {
/* 563 */     int causas = 0;
/*     */     try {
/* 565 */       String s = "select count(0) as cuantas  from am_causas  where numero=" + numero;
/*     */ 
/*     */       
/* 568 */       boolean rta = this.dat.parseSql(s);
/* 569 */       if (!rta) return 0; 
/* 570 */       this.rs = this.dat.getResultSet();
/* 571 */       if (this.rs.next()) {
/* 572 */         causas = this.rs.getInt("cuantas");
/*     */       }
/*     */     }
/* 575 */     catch (Exception e) {
/* 576 */       e.printStackTrace();
/* 577 */       Utilidades.writeError("AMAccionesFactory:numeroCausas ", e);
/*     */     } 
/* 579 */     return causas;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int numeroCausasAbiertas(int numero) {
/* 588 */     int causas = 0;
/*     */     try {
/* 590 */       String s = "select count(0) as cuantas  from am_causas  where numero=" + numero + "  and estado=1";
/*     */ 
/*     */ 
/*     */       
/* 594 */       boolean rta = this.dat.parseSql(s);
/* 595 */       if (!rta) return 0; 
/* 596 */       this.rs = this.dat.getResultSet();
/* 597 */       if (this.rs.next()) {
/* 598 */         causas = this.rs.getInt("cuantas");
/*     */       }
/*     */     }
/* 601 */     catch (Exception e) {
/* 602 */       e.printStackTrace();
/* 603 */       Utilidades.writeError("AMAccionesFactory:numeroCausas ", e);
/*     */     } 
/* 605 */     return causas;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int siguienteSeguimiento(int numero, int causa) {
/* 615 */     int retorno = 1;
/* 616 */     String s = "select max(consecutivo) from am_seguimiento where numero=" + numero + " and causa=" + causa;
/*     */     try {
/* 618 */       boolean rta = this.dat.parseSql(s);
/* 619 */       if (!rta) return 0; 
/* 620 */       this.rs = this.dat.getResultSet();
/* 621 */       if (this.rs.next()) {
/* 622 */         s = this.rs.getString(1);
/* 623 */         if (!this.rs.wasNull()) {
/* 624 */           retorno = Integer.parseInt(s) + 1;
/*     */         }
/*     */       } 
/* 627 */       return retorno;
/*     */     }
/* 629 */     catch (Exception e) {
/* 630 */       e.printStackTrace();
/* 631 */       Utilidades.writeError("AMSeguimientoFactory:siguienteRegistro", e);
/*     */       
/* 633 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eliminarSeguimiento(int numero, int causa, int consecutivo) {
/*     */     try {
/* 643 */       String s = "delete from  am_seguimiento where numero=" + numero + " and causa=" + causa + " and consecutivo=" + consecutivo;
/* 644 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 647 */     catch (Exception e) {
/* 648 */       e.printStackTrace();
/* 649 */       Utilidades.writeError("AMSeguimientoFactory:eliminarRegistro", e);
/*     */       
/* 651 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean crearSeguimiento(int numero, int causa, String observacion, int personaatendio, char automatico, String usuario) {
/* 660 */     int elSiguiente = siguienteSeguimiento(numero, causa);
/* 661 */     if (elSiguiente == 0) {
/* 662 */       return false;
/*     */     }
/*     */     try {
/* 665 */       String s = "insert into am_seguimiento (numero,causa,consecutivo,observacion,fecha,personaatendio,automatico,fecha_insercion,usuario_insercion)";
/*     */ 
/*     */ 
/*     */       
/* 669 */       s = s + " values (";
/* 670 */       s = s + "" + numero + ",";
/* 671 */       s = s + "" + causa + ",";
/* 672 */       s = s + "" + elSiguiente + ",";
/* 673 */       s = s + "'" + observacion + "',";
/* 674 */       s = s + "" + Utilidades.getFechaBD() + ",";
/* 675 */       s = s + "" + personaatendio + ",";
/* 676 */       s = s + "'" + automatico + "',";
/* 677 */       s = s + "" + Utilidades.getFechaBD() + ",";
/* 678 */       s = s + "'" + usuario + "'";
/* 679 */       s = s + ")";
/* 680 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 683 */     catch (Exception e) {
/* 684 */       e.printStackTrace();
/* 685 */       Utilidades.writeError("AMSeguimientoFactory:crearRegistro", e);
/*     */       
/* 687 */       return false;
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
/*     */   public boolean modificarSeguimiento(int numero, int causa, int consecutivo, String observacion, String usuario) {
/*     */     try {
/* 703 */       String s = "update am_seguimiento set ";
/* 704 */       s = s + "observacion='" + observacion + "',";
/* 705 */       s = s + "fecha_modificacion=" + Utilidades.getFechaBD() + ",";
/* 706 */       s = s + "usuario_modificacion='" + usuario + "'";
/* 707 */       s = s + " where ";
/* 708 */       s = s + " numero=" + numero;
/* 709 */       s = s + " and causa=" + causa;
/* 710 */       s = s + " and consecutivo=" + consecutivo;
/* 711 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 714 */     catch (Exception e) {
/* 715 */       e.printStackTrace();
/* 716 */       Utilidades.writeError("AMSeguimientoFactory:modificarRegistro", e);
/*     */       
/* 718 */       return false;
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
/*     */   public boolean crearCorreccion(int numero, String accion, int responsable, String fechaEstimadaTerminacion, int estado, String usuario) {
/* 737 */     int elSiguiente = siguienteRegistro(numero);
/* 738 */     if (elSiguiente == 0) {
/* 739 */       return false;
/*     */     }
/*     */     try {
/* 742 */       String s = "insert into am_causas (numero,consecutivo,accion,responsable,fecha_inicio,fecha_estimada_terminacion,estado,prorrogas,fecha_insercion,usuario_insercion)";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 748 */       s = s + " values (";
/* 749 */       s = s + "" + numero + ",";
/* 750 */       s = s + "" + elSiguiente + ",";
/* 751 */       s = s + "'" + accion + "',";
/* 752 */       s = s + "" + responsable + ",";
/* 753 */       s = s + "" + Utilidades.getFechaBD() + ",";
/* 754 */       s = s + "" + Utilidades.formatoFecha2(fechaEstimadaTerminacion) + ",";
/* 755 */       s = s + "" + estado + ",";
/* 756 */       s = s + "0,";
/* 757 */       s = s + "" + Utilidades.getFechaBD() + ",";
/* 758 */       s = s + "'" + usuario + "'";
/* 759 */       s = s + ")";
/* 760 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 763 */     catch (Exception e) {
/* 764 */       e.printStackTrace();
/* 765 */       Utilidades.writeError("AMCausasFactory:crearRegistro", e);
/*     */       
/* 767 */       return false;
/*     */     } 
/*     */   }
/*     */ 

/*     */   
/*     */   public boolean modificarCorreccion(int numero, int consecutivo, String accion, int responsable, String fechaEstimadaTerminacion, int estado, String usuario, String justificacion) {
/*     */     try {
/* 786 */       String s = "update am_causas set ";
/* 787 */       s = s + "accion='" + accion + "',";
/* 788 */       s = s + "responsable=" + responsable + ",";
/* 789 */       s = s + "fecha_estimada_terminacion=" + Utilidades.formatoFecha2(fechaEstimadaTerminacion) + ",";
/* 790 */       s = s + "estado=" + estado + ",";
/* 791 */       s = s + "justificacion='" + justificacion + "',";
/* 792 */       s = s + "fecha_modificacion=" + Utilidades.getFechaBD() + ",";
/* 793 */       s = s + "usuario_modificacion='" + usuario + "'";
/* 794 */       s = s + " where ";
/* 795 */       s = s + " numero=" + numero;
/* 796 */       s = s + " and consecutivo=" + consecutivo;
/* 797 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 800 */     catch (Exception e) {
/* 801 */       e.printStackTrace();
/* 802 */       Utilidades.writeError("AMCausasFactory:modificarRegistro", e);
/*     */       
/* 804 */       return false;
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
/*     */   public RespuestaBD crearRegistro(int numero, String porque, String accion, String beneficio, int responsable, String fechaEstimadaTerminacion, int estado, String usuario) {
/* 825 */    RespuestaBD rta = new RespuestaBD();
/*     */     
/* 827 */     int elSiguiente = siguienteRegistro(numero);
/* 828 */     if (elSiguiente == 0) {
/* 829 */       rta.setMensaje("Error obteniendo consecutivo");
/* 830 */       rta.setRta(false);
/* 831 */       return rta;
/*     */     } 
/*     */     try {
/* 834 */       String s = "insert into am_causas ( numero,consecutivo,porque,accion,beneficio,responsable,fecha_inicio,fecha_estimada_terminacion,estado,prorrogas,fecha_insercion,usuario_insercion)";
/*     */ 

/*     */       
/* 848 */       s = s + " values (";
/* 849 */       s = s + "" + numero + ",";
/* 850 */       s = s + "" + elSiguiente + ",";
/* 851 */       s = s + "'" + porque + "',";
/* 852 */       s = s + "'" + accion + "',";
/* 853 */       s = s + "'" + beneficio + "',";
/* 854 */       s = s + "" + responsable + ",";
/* 855 */       s = s + "" + Utilidades.getFechaBD() + ",";
/* 856 */       s = s + "" + Utilidades.formatoFecha2(fechaEstimadaTerminacion) + ",";
/* 857 */       s = s + "" + estado + ",";
/* 858 */       s = s + "0,";
/* 859 */       s = s + "" + Utilidades.getFechaBD() + ",";
/* 860 */       s = s + "'" + usuario + "'";
/* 861 */       s = s + ")";
/* 862 */       return this.dat.executeUpdate2(s);
/*     */     
/*     */     }
/* 865 */     catch (Exception e) {
/* 866 */       e.printStackTrace();
/* 867 */       Utilidades.writeError("AMCausasFactory:crearRegistro", e);
/* 868 */       rta.setMensaje("" + e.getMessage());
/* 869 */       rta.setRta(false);
/*     */ 
/*     */       
/* 872 */       return rta;
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
/*     */   public RespuestaBD modificarRegistro(int numero, int consecutivo, String porque, String accion, String beneficio, int responsable, String fechaEstimadaTerminacion, int estado, String usuario, String justificacion) {
/* 892 */    RespuestaBD rta = new RespuestaBD();
/*     */     try {
/* 894 */       String s = "update am_causas set ";
/* 895 */       s = s + "porque='" + porque + "',";
/* 896 */       s = s + "accion='" + accion + "',";
/* 897 */       s = s + "beneficio='" + beneficio + "',";
/* 898 */       s = s + "responsable=" + responsable + ",";
/* 899 */       s = s + "fecha_estimada_terminacion=" + Utilidades.formatoFecha2(fechaEstimadaTerminacion) + ",";
/* 900 */       s = s + "estado=" + estado + ",";
/* 901 */       s = s + "justificacion='" + justificacion + "',";
/* 902 */       s = s + "fecha_modificacion=" + Utilidades.getFechaBD() + ",";
/* 903 */       s = s + "usuario_modificacion='" + usuario + "'";
/* 904 */       s = s + " where ";
/* 905 */       s = s + " numero=" + numero;
/* 906 */       s = s + " and consecutivo=" + consecutivo;
/* 907 */       return this.dat.executeUpdate2(s);
/*     */     
/*     */     }
/* 910 */     catch (Exception e) {
/* 911 */       e.printStackTrace();
/* 912 */       Utilidades.writeError("modificarRegistro", e);
/* 913 */       rta.setMensaje("" + e.getMessage());
/* 914 */       rta.setRta(false);
/*     */       
/* 916 */       return rta;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\AMCausasDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */