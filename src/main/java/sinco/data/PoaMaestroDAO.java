/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.PoaMaestroDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.PoaMaestroDAO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PoaMaestroDAO
/*     */ {
/*     */   ResultSet rs;
/*  29 */   DBManager dat = new DBManager();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  40 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/*     */     try {
/*  47 */       this.dat.close();
/*     */     }
/*  49 */     catch (Exception e) {
/*  50 */       Utilidades.writeError("PoaMaestroDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PoaMaestroDTO next() {
/*     */     try {
/*  61 */       if (this.rs.next()) {
/*  62 */         return leerRegistro();
/*     */       }
/*     */     }
/*  65 */     catch (Exception e) {
/*  66 */       e.printStackTrace();
/*  67 */       Utilidades.writeError("PoaMaestroDAO:next ", e);
/*     */     } 
/*  69 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PoaMaestroDTO leerRegistro() {
/*     */     try {
/*  79 */       PoaMaestroDTO reg = new PoaMaestroDTO();
/*     */       
/*  81 */       reg.setCodigoPoa(this.rs.getInt("codigo_poa"));
/*  82 */       reg.setProceso(this.rs.getInt("proceso"));
/*  83 */       reg.setArea(this.rs.getInt("area"));
/*  84 */       reg.setResponsable(this.rs.getInt("responsable"));
/*  85 */       reg.setCiclo(this.rs.getInt("ciclo"));
/*  86 */       reg.setEstado(this.rs.getString("estado"));
/*  87 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  88 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  89 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  90 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  91 */       reg.setNombreProceso(this.rs.getString("nombre_proceso"));
/*  92 */       reg.setNombreArea(this.rs.getString("nombre_area"));
/*  93 */       reg.setNombreResponsable(this.rs.getString("nombre_responsable"));
/*  94 */       reg.setNombreCiclo(this.rs.getString("nombre_ciclo"));
/*  95 */       reg.setNombreEstado(this.rs.getString("nombre_estado"));
/*  96 */       return reg;
/*     */     }
/*  98 */     catch (Exception e) {
/*  99 */       e.printStackTrace();
/* 100 */       Utilidades.writeError("PoaMaestroDAO:leerRegistro ", e);
/*     */       
/* 102 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<PoaMaestroDTO> cargarTodos(int ciclo) {
/* 112 */     Collection<PoaMaestroDTO> resultados = new ArrayList<PoaMaestroDTO>();
/*     */     try {
/* 114 */       String s = "select t.codigo_poa,t.proceso,r1.DESCRIPCION as nombre_proceso,t.area,r2.DESCRIPCION as nombre_area,t.responsable,r3.NOMBRES,APELLIDOS as nombre_responsable,t.ciclo,r4.DESCRIPCION as nombre_ciclo,t.estado,m5.DESCRIPCION as nombre_estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from POA_MAESTRO t  left join POA_PROCESOS r1 on (r1.CODIGO_PROCESO=t.proceso) left join UNIDADES_DEPENDENCIA r2 on (r2.CODIGO=t.area) left join SIS_USUARIOS r3 on (r3.CODIGO_EMPLEADO=t.responsable) left join POA_CICLOS r4 on (r4.CODIGO_CICLO=t.ciclo) left join sis_multivalores m5 on (m5.tabla='ESTADO_REGISTRO' and m5.VALOR=t.estado) where 1=1";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 138 */       if (ciclo > 0) {
/* 139 */         s = s + " and t.ciclo=" + ciclo;
/*     */       }
/* 141 */       s = s + " order by 1";
/* 142 */       boolean rtaDB = this.dat.parseSql(s);
/* 143 */       if (!rtaDB) {
/* 144 */         return resultados;
/*     */       }
/* 146 */       this.rs = this.dat.getResultSet();
/* 147 */       while (this.rs.next()) {
/* 148 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 151 */     catch (Exception e) {
/* 152 */       e.printStackTrace();
/* 153 */       Utilidades.writeError("PoaMaestroDAO:cargarTodos ", e);
/*     */     } 
/* 155 */     return resultados;
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
/*     */   public Collection<PoaMaestroDTO> cargarRegistros(int area, int ciclo, String proceso) {
/* 167 */     Collection<PoaMaestroDTO> resultados = new ArrayList<PoaMaestroDTO>();
/*     */     try {
/* 169 */       String s = "select t.codigo_poa,t.proceso,r1.DESCRIPCION as nombre_proceso,t.area,r2.DESCRIPCION as nombre_area,t.responsable,r3.NOMBRES,APELLIDOS as nombre_responsable,t.ciclo,r4.DESCRIPCION as nombre_ciclo,t.estado,m5.DESCRIPCION as nombre_estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from POA_MAESTRO t  left join POA_PROCESOS r1 on (r1.CODIGO_PROCESO=t.proceso) left join UNIDADES_DEPENDENCIA r2 on (r2.CODIGO=t.area) left join SIS_USUARIOS r3 on (r3.CODIGO_EMPLEADO=t.responsable) left join POA_CICLOS r4 on (r4.CODIGO_CICLO=t.ciclo) left join sis_multivalores m5 on (m5.tabla='ESTADO_REGISTRO' and m5.VALOR=t.estado) where 1=1";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 192 */       if (area > 0) {
/* 193 */         s = s + " AND t.area = " + area;
/*     */       }
/* 195 */       if (ciclo > 0) {
/* 196 */         s = s + " AND t.ciclo = " + ciclo;
/*     */       }
/* 198 */       if (!proceso.equals("")) {
/* 199 */         s = s + " AND t.proceso = " + proceso;
/*     */       }
/* 201 */       s = s + " order by 1";
/* 202 */       boolean rtaDB = this.dat.parseSql(s);
/* 203 */       if (!rtaDB) {
/* 204 */         return resultados;
/*     */       }
/* 206 */       this.rs = this.dat.getResultSet();
/* 207 */       while (this.rs.next()) {
/* 208 */         resultados.add(leerRegistro());
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 213 */     catch (Exception e) {
/* 214 */       e.printStackTrace();
/* 215 */       Utilidades.writeError("PoaMaestroDAO:cargarTodos ", e);
/*     */     } 
/* 217 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<PoaMaestroDTO> cargarRegistros(int area, int ciclo, int proceso) {
/* 223 */     Collection<PoaMaestroDTO> resultados = new ArrayList<PoaMaestroDTO>();
/*     */     try {
/* 225 */       String s = "select t.codigo_poa,t.proceso,r1.DESCRIPCION as nombre_proceso,t.area,r2.DESCRIPCION as nombre_area,t.responsable,r3.NOMBRES,APELLIDOS as nombre_responsable,t.ciclo,r4.DESCRIPCION as nombre_ciclo,t.estado,m5.DESCRIPCION as nombre_estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from POA_MAESTRO t  left join POA_PROCESOS r1 on (r1.CODIGO_PROCESO=t.proceso) left join UNIDADES_DEPENDENCIA r2 on (r2.CODIGO=t.area) left join SIS_USUARIOS r3 on (r3.CODIGO_EMPLEADO=t.responsable) left join POA_CICLOS r4 on (r4.CODIGO_CICLO=t.ciclo) left join sis_multivalores m5 on (m5.tabla='ESTADO_REGISTRO' and m5.VALOR=t.estado) where 1=1";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 248 */       if (area > 0) {
/* 249 */         s = s + " AND t.area = " + area;
/*     */       }
/* 251 */       if (ciclo > 0) {
/* 252 */         s = s + " AND t.ciclo = " + ciclo;
/*     */       }
/* 254 */       if (proceso > 0) {
/* 255 */         s = s + " AND t.proceso = " + proceso;
/*     */       }
/*     */       
/* 258 */       s = s + " order by 1";
/* 259 */       boolean rtaDB = this.dat.parseSql(s);
/* 260 */       if (!rtaDB) {
/* 261 */         return resultados;
/*     */       }
/* 263 */       this.rs = this.dat.getResultSet();
/* 264 */       while (this.rs.next()) {
/* 265 */         resultados.add(leerRegistro());
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 270 */     catch (Exception e) {
/* 271 */       e.printStackTrace();
/* 272 */       Utilidades.writeError("PoaMaestroDAO:cargarTodos ", e);
/*     */     } 
/* 274 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PoaMaestroDTO cargarRegistro(int codigoPoa) {
/*     */     try {
/* 286 */       String s = "select t.codigo_poa,t.proceso,r1.DESCRIPCION as nombre_proceso,t.area,r2.DESCRIPCION as nombre_area,t.responsable,r3.NOMBRES,APELLIDOS as nombre_responsable,t.ciclo,r4.DESCRIPCION as nombre_ciclo,t.estado,m5.DESCRIPCION as nombre_estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from POA_MAESTRO t  left join POA_PROCESOS r1 on (r1.CODIGO_PROCESO=t.proceso) left join UNIDADES_DEPENDENCIA r2 on (r2.CODIGO=t.area) left join SIS_USUARIOS r3 on (r3.CODIGO_EMPLEADO=t.responsable) left join POA_CICLOS r4 on (r4.CODIGO_CICLO=t.ciclo) left join sis_multivalores m5 on (m5.tabla='ESTADO_REGISTRO' and m5.VALOR=t.estado) where  t.codigo_poa=" + codigoPoa + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 311 */       boolean rtaDB = this.dat.parseSql(s);
/* 312 */       if (!rtaDB) {
/* 313 */         return null;
/*     */       }
/* 315 */       this.rs = this.dat.getResultSet();
/* 316 */       if (this.rs.next()) {
/* 317 */         return leerRegistro();
/*     */       }
/*     */     }
/* 320 */     catch (Exception e) {
/* 321 */       e.printStackTrace();
/* 322 */       Utilidades.writeError("PoaMaestroDAO:cargarPoaMaestro", e);
/*     */     } 
/* 324 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public PoaMaestroDTO cargarRegistro(int area, int proceso, int ciclo) {
/*     */     try {
/* 330 */       String s = "select t.codigo_poa,t.proceso,r1.DESCRIPCION as nombre_proceso,t.area,r2.DESCRIPCION as nombre_area,t.responsable,r3.NOMBRES,APELLIDOS as nombre_responsable,t.ciclo,r4.DESCRIPCION as nombre_ciclo,t.estado,m5.DESCRIPCION as nombre_estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from POA_MAESTRO t  left join POA_PROCESOS r1 on (r1.CODIGO_PROCESO=t.proceso) left join UNIDADES_DEPENDENCIA r2 on (r2.CODIGO=t.area) left join SIS_USUARIOS r3 on (r3.CODIGO_EMPLEADO=t.responsable) left join POA_CICLOS r4 on (r4.CODIGO_CICLO=t.ciclo) left join sis_multivalores m5 on (m5.tabla='ESTADO_REGISTRO' and m5.VALOR=t.estado) where 1=1";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 353 */       if (area > 0) {
/* 354 */         s = s + " AND t.area = " + area;
/*     */       }
/* 356 */       if (ciclo > 0) {
/* 357 */         s = s + " AND t.ciclo = " + ciclo;
/*     */       }
/* 359 */       if (proceso > 0) {
/* 360 */         s = s + " AND t.proceso = " + proceso;
/*     */       }
/*     */ 
/*     */       
/* 364 */       boolean rtaDB = this.dat.parseSql(s);
/* 365 */       if (!rtaDB) {
/* 366 */         return null;
/*     */       }
/* 368 */       this.rs = this.dat.getResultSet();
/* 369 */       if (this.rs.next()) {
/* 370 */         return leerRegistro();
/*     */       }
/*     */     }
/* 373 */     catch (Exception e) {
/* 374 */       e.printStackTrace();
/* 375 */       Utilidades.writeError("PoaMaestroDAO:cargarPoaMaestro", e);
/*     */     } 
/* 377 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int siguienteRegistro() {
/* 386 */     int inumero = 1;
/* 387 */     String s = "select max(codigo_poa) from POA_MAESTRO ";
/*     */     
/*     */     try {
/* 390 */       boolean rta = this.dat.parseSql(s);
/* 391 */       if (!rta) return 0; 
/* 392 */       this.rs = this.dat.getResultSet();
/* 393 */       if (this.rs.next()) {
/* 394 */         s = this.rs.getString(1);
/* 395 */         if (!this.rs.wasNull()) {
/* 396 */           inumero = Integer.parseInt(s) + 1;
/*     */         }
/*     */       } 
/* 399 */       return inumero;
/*     */     }
/* 401 */     catch (Exception e) {
/* 402 */       e.printStackTrace();
/* 403 */       Utilidades.writeError("PoamAESTRODAO:siguienteRegistro ", e);
/*     */       
/* 405 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarRegistro(int codigoPoa) {
/* 414 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 417 */       String s = "delete from POA_MAESTRO where  codigo_poa=" + codigoPoa + "";
/*     */ 
/*     */ 
/*     */       
/* 421 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 423 */     catch (Exception e) {
/* 424 */       e.printStackTrace();
/* 425 */       Utilidades.writeError("PoaMaestroDAO:eliminarRegistro ", e);
/* 426 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 428 */     return rta;
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
/*     */   public RespuestaBD crearRegistro(int codigoPoa, int proceso, int area, int responsable, int ciclo, String estado, String usuarioInsercion) {
/* 445 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 447 */     int elSiguiente = siguienteRegistro();
/* 448 */     if (elSiguiente == 0) {
/* 449 */       rta.setMensaje("Generando secuencia");
/* 450 */       return rta;
/*     */     } 
/*     */     
/*     */     try {
/* 454 */       String s = "insert into POA_MAESTRO(codigo_poa,proceso,area,responsable,ciclo,estado,fecha_insercion,usuario_insercion) values (" + elSiguiente + "," + "" + proceso + "," + "" + area + "," + "" + responsable + "," + "" + ciclo + "," + "'" + estado + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 473 */       rta = this.dat.executeUpdate2(s);
/* 474 */       rta.setSecuencia(elSiguiente);
/*     */     }
/* 476 */     catch (Exception e) {
/* 477 */       e.printStackTrace();
/* 478 */       Utilidades.writeError("%PoaMaestroDAO:crearRegistro ", e);
/* 479 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 481 */     return rta;
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
/*     */   public RespuestaBD modificarRegistro(int codigoPoa, int proceso, int area, int responsable, int ciclo, String estado, String usuarioModificacion) {
/* 498 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 501 */       String s = "update POA_MAESTRO set  proceso=" + proceso + "," + " area=" + area + "," + " responsable=" + responsable + "," + " ciclo=" + ciclo + "," + " estado='" + estado + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " codigo_poa=" + codigoPoa + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 512 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 514 */     catch (Exception e) {
/* 515 */       e.printStackTrace();
/* 516 */       Utilidades.writeError("PoaMaestroDAO:modificarRegistro ", e);
/* 517 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 519 */     return rta;
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
/*     */   public Collection<Integer> cargarAreas(int area, int ciclo, String proceso) {
/* 531 */     Collection<Integer> resultados = new ArrayList<Integer>();
/*     */     try {
/* 533 */       String s = "select distinct(t.area) from poa_maestro t where 1=1 ";
/*     */       
/* 535 */       if (area > 0) {
/* 536 */         s = s + " AND t.area = " + area;
/*     */       }
/* 538 */       if (ciclo > 0) {
/* 539 */         s = s + " AND t.ciclo = " + ciclo;
/*     */       }
/* 541 */       if (!proceso.equals("")) {
/* 542 */         s = s + " AND t.proceso = " + proceso;
/*     */       }
/* 544 */       s = s + " order by 1";
/* 545 */       boolean rtaDB = this.dat.parseSql(s);
/* 546 */       if (!rtaDB) {
/* 547 */         return resultados;
/*     */       }
/* 549 */       this.rs = this.dat.getResultSet();
/* 550 */       while (this.rs.next()) {
/* 551 */         resultados.add(Integer.valueOf(this.rs.getInt("area")));
/*     */       }
/*     */     }
/* 554 */     catch (Exception e) {
/* 555 */       e.printStackTrace();
/* 556 */       Utilidades.writeError("PoaMaestroDAO:cargarTodos ", e);
/*     */     } 
/* 558 */     return resultados;
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
/*     */   public Collection<Integer> cargarProcesos(int area, int ciclo, String proceso) {
/* 570 */     Collection<Integer> resultados = new ArrayList<Integer>();
/*     */     try {
/* 572 */       String s = "select distinct(t.proceso) from poa_maestro t where 1=1 ";
/*     */       
/* 574 */       if (area > 0) {
/* 575 */         s = s + " AND t.area = " + area;
/*     */       }
/* 577 */       if (ciclo > 0) {
/* 578 */         s = s + " AND t.ciclo = " + ciclo;
/*     */       }
/* 580 */       if (!proceso.equals("")) {
/* 581 */         s = s + " AND t.proceso = " + proceso;
/*     */       }
/* 583 */       s = s + " order by 1";
/* 584 */       boolean rtaDB = this.dat.parseSql(s);
/* 585 */       if (!rtaDB) {
/* 586 */         return resultados;
/*     */       }
/* 588 */       this.rs = this.dat.getResultSet();
/* 589 */       while (this.rs.next()) {
/* 590 */         resultados.add(Integer.valueOf(Integer.parseInt(this.rs.getString("proceso"))));
/*     */       }
/*     */     }
/* 593 */     catch (Exception e) {
/* 594 */       e.printStackTrace();
/* 595 */       Utilidades.writeError("PoaMaestroDAO:cargarTodos ", e);
/*     */     } 
/* 597 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int contarActividades(int area, int ciclo, String proceso) {
/*     */     try {
/* 609 */       String s = "select count(*) as numero_actividades from poa_maestro t left join poa_maestro_actividades a on (a.codigo_poa = t.codigo_poa) where 1=1 ";
/*     */       
/* 611 */       if (area > 0) {
/* 612 */         s = s + " AND t.area = " + area;
/*     */       }
/* 614 */       if (ciclo > 0) {
/* 615 */         s = s + " AND t.ciclo = " + ciclo;
/*     */       }
/* 617 */       if (!proceso.equals("")) {
/* 618 */         s = s + " AND t.proceso = " + proceso;
/*     */       }
/* 620 */       boolean rtaDB = this.dat.parseSql(s);
/* 621 */       if (!rtaDB) {
/* 622 */         return 0;
/*     */       }
/* 624 */       this.rs = this.dat.getResultSet();
/* 625 */       if (this.rs.next()) {
/* 626 */         return this.rs.getInt("numero_actividades");
/*     */       }
/*     */     }
/* 629 */     catch (Exception e) {
/* 630 */       e.printStackTrace();
/* 631 */       Utilidades.writeError("PoaMaestroDAO:contarActividades ", e);
/*     */     } 
/* 633 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<Integer> cargarCodigosPorObjetivo(int area, int ciclo, String proceso, int objetivo) {
/* 644 */     Collection<Integer> resultados = new ArrayList<Integer>();
/*     */     try {
/* 646 */       String s = "select distinct(t.codigo_poa) from poa_maestro t  left join poa_maestro_actividades ac on (ac.codigo_poa = t.codigo_poa) where 1=1 ";
/*     */ 
/*     */       
/* 649 */       if (area > 0) {
/* 650 */         s = s + " AND t.area = " + area;
/*     */       }
/* 652 */       if (ciclo > 0) {
/* 653 */         s = s + " AND t.ciclo = " + ciclo;
/*     */       }
/* 655 */       if (!proceso.equals("")) {
/* 656 */         s = s + " AND t.proceso = " + proceso;
/*     */       }
/* 658 */       if (objetivo > 0) {
/* 659 */         s = s + " AND ac.ac.objetivo_estrategico = " + objetivo;
/*     */       }
/* 661 */       s = s + " order by 1";
/* 662 */       boolean rtaDB = this.dat.parseSql(s);
/* 663 */       if (!rtaDB) {
/* 664 */         return resultados;
/*     */       }
/* 666 */       this.rs = this.dat.getResultSet();
/* 667 */       while (this.rs.next()) {
/* 668 */         resultados.add(Integer.valueOf(Integer.parseInt(this.rs.getString("codigo_poa"))));
/*     */       }
/*     */     }
/* 671 */     catch (Exception e) {
/* 672 */       e.printStackTrace();
/* 673 */       Utilidades.writeError("PoaMaestroDAO:cargarCodigosPorObjetivos ", e);
/*     */     } 
/* 675 */     return resultados;
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
/*     */   public Collection<Integer> cargarProcesosPorObjetivo(int area, int ciclo, String proceso, int objetivo) {
/* 687 */     Collection<Integer> resultados = new ArrayList<Integer>();
/*     */     try {
/* 689 */       String s = "select distinct(t.proceso) from poa_maestro t  left join poa_maestro_actividades ac on (ac.codigo_poa = t.codigo_poa) where 1=1 ";
/*     */ 
/*     */       
/* 692 */       if (area > 0) {
/* 693 */         s = s + " AND t.area = " + area;
/*     */       }
/* 695 */       if (ciclo > 0) {
/* 696 */         s = s + " AND t.ciclo = " + ciclo;
/*     */       }
/* 698 */       if (!proceso.equals("")) {
/* 699 */         s = s + " AND t.proceso = " + proceso;
/*     */       }
/* 701 */       if (objetivo > 0) {
/* 702 */         s = s + " AND ac.objetivo_estrategico = " + objetivo;
/*     */       }
/* 704 */       s = s + " order by 1";
/* 705 */       boolean rtaDB = this.dat.parseSql(s);
/* 706 */       if (!rtaDB) {
/* 707 */         return resultados;
/*     */       }
/* 709 */       this.rs = this.dat.getResultSet();
/* 710 */       while (this.rs.next()) {
/* 711 */         resultados.add(Integer.valueOf(Integer.parseInt(this.rs.getString("proceso"))));
/*     */       }
/*     */     }
/* 714 */     catch (Exception e) {
/* 715 */       e.printStackTrace();
/* 716 */       Utilidades.writeError("PoaMaestroDAO:cargarProcesosPorObjetivos ", e);
/*     */     } 
/* 718 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int cantidadActividadesPorObjetivo(int area, int ciclo, String proceso, int objetivo) {
/* 729 */     int resultado = 0;
/*     */     try {
/* 731 */       String s = "select count(*) as cantidad from poa_maestro t  left join poa_maestro_actividades ac on (ac.codigo_poa = t.codigo_poa) where 1=1 ";
/*     */ 
/*     */       
/* 734 */       if (area > 0) {
/* 735 */         s = s + " AND t.area = " + area;
/*     */       }
/* 737 */       if (ciclo > 0) {
/* 738 */         s = s + " AND t.ciclo = " + ciclo;
/*     */       }
/* 740 */       if (!proceso.equals("")) {
/* 741 */         s = s + " AND t.proceso = " + proceso;
/*     */       }
/* 743 */       if (objetivo > 0) {
/* 744 */         s = s + " AND ac.objetivo_estrategico = " + objetivo;
/*     */       }
/* 746 */       s = s + " order by 1";
/* 747 */       boolean rtaDB = this.dat.parseSql(s);
/* 748 */       if (!rtaDB) {
/* 749 */         return resultado;
/*     */       }
/* 751 */       this.rs = this.dat.getResultSet();
/* 752 */       while (this.rs.next()) {
/* 753 */         resultado = Integer.parseInt(this.rs.getString("cantidad"));
/*     */       }
/*     */     }
/* 756 */     catch (Exception e) {
/* 757 */       e.printStackTrace();
/* 758 */       Utilidades.writeError("PoaMaestroDAO:númeroActividadesPorObjetivos ", e);
/*     */     } 
/* 760 */     return resultado;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\PoaMaestroDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */