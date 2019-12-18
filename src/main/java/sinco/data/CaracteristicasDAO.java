/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.CaracteristicasDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.CaracteristicasDAO;
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
/*     */ public class CaracteristicasDAO
/*     */ {
/*     */   ResultSet rs;
/*  30 */   DBManager dat = new DBManager();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  41 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/*     */     try {
/*  48 */       this.dat.close();
/*     */     }
/*  50 */     catch (Exception e) {
/*  51 */       Utilidades.writeError("CaracteristicasDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CaracteristicasDTO next() {
/*     */     try {
/*  62 */       if (this.rs.next()) {
/*  63 */         return leerRegistro();
/*     */       }
/*     */     }
/*  66 */     catch (Exception e) {
/*  67 */       e.printStackTrace();
/*  68 */       Utilidades.writeError("CaracteristicasDAO:next ", e);
/*     */     } 
/*  70 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CaracteristicasDTO leerRegistroFull() {
/*     */     try {
/*  82 */       CaracteristicasDTO reg = new CaracteristicasDTO();
/*     */       
/*  84 */       reg.setCodigo(this.rs.getInt("codigo"));
/*  85 */       reg.setDescripcion(this.rs.getString("descripcion"));
/*  86 */       reg.setTipo(this.rs.getString("tipo"));
/*  87 */       reg.setCalificar(this.rs.getString("calificar"));
/*  88 */       reg.setLongitud(this.rs.getInt("longitud"));
/*  89 */       reg.setCompetencia(this.rs.getString("competencia"));
/*  90 */       reg.setCaracteristicaAnida(this.rs.getInt("caracteristica_anida"));
/*  91 */       reg.setPermiteExtender(this.rs.getString("permite_extender"));
/*  92 */       reg.setCaracteristicaDepende(this.rs.getInt("caracteristica_depende"));
/*  93 */       reg.setValorDepende(this.rs.getInt("valor_depende"));
/*  94 */       reg.setTipoValidacion(this.rs.getString("tipo_validacion"));
/*  95 */       reg.setCaracteristicaValida(this.rs.getInt("caracteristica_valida"));
/*  96 */       reg.setNombreProcedimiento(this.rs.getString("nombre_procedimiento"));
/*  97 */       reg.setNumeroDecimales(this.rs.getInt("numero_decimales"));
/*  98 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  99 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/* 100 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/* 101 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/* 102 */       reg.setNombreTipo(this.rs.getString("nombre_tipo"));
/* 103 */       reg.setNombreCompetencia(this.rs.getString("nombre_competencia"));
/* 104 */       reg.setNombreValorDepende(this.rs.getString("nombre_valor_depende"));
/* 105 */       reg.setNombreTipoValidacion(this.rs.getString("nombre_tipo_validacion"));
/* 106 */       return reg;
/*     */     }
/* 108 */     catch (Exception e) {
/* 109 */       e.printStackTrace();
/* 110 */       Utilidades.writeError("CaracteristicasDAO:leerRegistro ", e);
/*     */       
/* 112 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private CaracteristicasDTO leerRegistro() {
/*     */     try {
/* 124 */       CaracteristicasDTO reg = new CaracteristicasDTO();
/* 125 */       reg.setCodigo(this.rs.getInt("codigo"));
/* 126 */       reg.setDescripcion(this.rs.getString("descripcion"));
/* 127 */       reg.setTipo(this.rs.getString("tipo"));
/* 128 */       reg.setCalificar(this.rs.getString("calificar"));
/* 129 */       reg.setObliga(this.rs.getString("obligatoria").toLowerCase().equals("s"));
/* 130 */       reg.setRol(this.rs.getString("rol"));
/* 131 */       reg.setLongitud(this.rs.getInt("longitud"));
/* 132 */       reg.setCaracteristicaAnida(this.rs.getInt("caracteristica_anida"));
/* 133 */       reg.setPermiteExtender(this.rs.getString("permite_extender"));
/* 134 */       reg.setCaracteristicaDepende(this.rs.getInt("caracteristica_depende"));
/* 135 */       reg.setValorDepende(this.rs.getInt("valor_depende"));
/* 136 */       reg.setTipoValidacion(this.rs.getString("tipo_validacion"));
/* 137 */       reg.setCaracteristicaValida(this.rs.getInt("caracteristica_valida"));
/* 138 */       reg.setNombreProcedimiento(this.rs.getString("nombre_procedimiento"));
/*     */       
/* 140 */       return reg;
/*     */     }
/* 142 */     catch (SQLException e) {
/* 143 */       e.printStackTrace();
/* 144 */       Utilidades.writeError("CaracteristicaFactory", e);
/*     */ 
/*     */       
/* 147 */       return null;
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
/*     */   public Collection<CaracteristicasDTO> cargarTodos(int codigo, String descripcion, String tipo) {
/* 161 */     Collection<CaracteristicasDTO> resultados = new ArrayList<CaracteristicasDTO>();
/*     */     try {
/* 163 */       String s = "select t.codigo,t.descripcion,t.tipo,m1.DESCRIPCION as nombre_tipo,t.calificar,t.longitud,t.competencia,m2.DESCRIPCION as nombre_competencia,t.caracteristica_anida,t.permite_extender,t.caracteristica_depende,t.valor_depende,r3.DESCRIPCION as nombre_valor_depende,t.tipo_validacion,m4.DESCRIPCION as nombre_tipo_validacion,t.caracteristica_valida,t.nombre_procedimiento,t.numero_decimales,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from caracteristicas t  left join sis_multivalores m1 on (m1.tabla='TIPO_CARACTERISTICA' and m1.VALOR=t.tipo) left join sis_multivalores m2 on (m2.tabla='COMPETENCIAS' and m2.VALOR=t.competencia) left join CARACTERISTICAS r3 on (r3.CODIGO=t.valor_depende) left join sis_multivalores m4 on (m4.tabla='VALIDACION_CARACTERISTICA' and m4.VALOR=t.tipo_validacion) where 1=1";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 192 */       if (codigo > 0) {
/* 193 */         s = s + " and t.codigo=" + codigo;
/*     */       }
/* 195 */       if (descripcion.length() > 0) {
/* 196 */         s = s + " and upper(t.descripcion) like upper('%" + descripcion + "%')";
/*     */       }
/* 198 */       if (tipo.length() > 0) {
/* 199 */         s = s + " and t.tipo='" + tipo + "'";
/*     */       }
/* 201 */       s = s + " order by 1";
/* 202 */       boolean rtaDB = this.dat.parseSql(s);
/* 203 */       if (!rtaDB) {
/* 204 */         return resultados;
/*     */       }
/* 206 */       this.rs = this.dat.getResultSet();
/* 207 */       while (this.rs.next()) {
/* 208 */         resultados.add(leerRegistroFull());
/*     */       }
/*     */     }
/* 211 */     catch (Exception e) {
/* 212 */       e.printStackTrace();
/* 213 */       Utilidades.writeError("CaracteristicasDAO:cargarTodos ", e);
/*     */     } 
/* 215 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CaracteristicasDTO cargarRegistro(int codigo) {
/*     */     try {
/* 227 */       String s = "select t.codigo,t.descripcion,t.tipo,m1.DESCRIPCION as nombre_tipo,t.calificar,t.longitud,t.competencia,m2.DESCRIPCION as nombre_competencia,t.caracteristica_anida,t.permite_extender,t.caracteristica_depende,t.valor_depende,r3.DESCRIPCION as nombre_valor_depende,t.tipo_validacion,m4.DESCRIPCION as nombre_tipo_validacion,t.caracteristica_valida,t.nombre_procedimiento,t.numero_decimales,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from caracteristicas t  left join sis_multivalores m1 on (m1.tabla='TIPO_CARACTERISTICA' and m1.VALOR=t.tipo) left join sis_multivalores m2 on (m2.tabla='COMPETENCIAS' and m2.VALOR=t.competencia) left join CARACTERISTICAS r3 on (r3.CODIGO=t.valor_depende) left join sis_multivalores m4 on (m4.tabla='VALIDACION_CARACTERISTICA' and m4.VALOR=t.tipo_validacion) where  t.codigo=" + codigo + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 258 */       boolean rtaDB = this.dat.parseSql(s);
/* 259 */       if (!rtaDB) {
/* 260 */         return null;
/*     */       }
/* 262 */       this.rs = this.dat.getResultSet();
/* 263 */       if (this.rs.next()) {
/* 264 */         return leerRegistroFull();
/*     */       }
/*     */     }
/* 267 */     catch (Exception e) {
/* 268 */       e.printStackTrace();
/* 269 */       Utilidades.writeError("CaracteristicasDAO:cargarCaracteristicas", e);
/*     */     } 
/* 271 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int siguienteRegistro() {
/* 280 */     int inumero = 1;
/* 281 */     String s = "select max(codigo) from caracteristicas ";
/*     */     
/*     */     try {
/* 284 */       boolean rta = this.dat.parseSql(s);
/* 285 */       if (!rta) return 0; 
/* 286 */       this.rs = this.dat.getResultSet();
/* 287 */       if (this.rs.next()) {
/* 288 */         s = this.rs.getString(1);
/* 289 */         if (!this.rs.wasNull()) {
/* 290 */           inumero = Integer.parseInt(s) + 1;
/*     */         }
/*     */       } 
/* 293 */       return inumero;
/*     */     }
/* 295 */     catch (Exception e) {
/* 296 */       e.printStackTrace();
/* 297 */       Utilidades.writeError("CaracteristicasDAO:siguienteRegistro ", e);
/*     */       
/* 299 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarRegistro(int codigo) {
/* 309 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 312 */       String s = "delete from caracteristicas where  codigo=" + codigo + "";
/*     */ 
/*     */ 
/*     */       
/* 316 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 318 */     catch (Exception e) {
/* 319 */       e.printStackTrace();
/* 320 */       Utilidades.writeError("CaracteristicasDAO:eliminarRegistro ", e);
/* 321 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 323 */     return rta;
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
/*     */   public RespuestaBD crearRegistro(int codigo, String descripcion, String tipo, String calificar, int longitud, String competencia, int caracteristicaAnida, String permiteExtender, int caracteristicaDepende, int valorDepende, String tipoValidacion, int caracteristicaValida, String nombreProcedimiento, int numeroDecimales, String usuarioInsercion) {
/* 348 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 350 */     int elSiguiente = siguienteRegistro();
/* 351 */     if (elSiguiente == 0) {
/* 352 */       rta.setMensaje("Generando secuencia");
/* 353 */       return rta;
/*     */     } 
/*     */     
/*     */     try {
/* 357 */       String s = "insert into caracteristicas(codigo,descripcion,tipo,calificar,longitud,competencia,caracteristica_anida,permite_extender,caracteristica_depende,valor_depende,tipo_validacion,caracteristica_valida,nombre_procedimiento,numero_decimales,fecha_insercion,usuario_insercion) values (" + elSiguiente + "," + "'" + descripcion + "'," + "'" + tipo + "'," + "'" + calificar + "'," + "" + longitud + "," + "'" + competencia + "'," + "" + caracteristicaAnida + "," + "'" + permiteExtender + "'," + "" + caracteristicaDepende + "," + "" + valorDepende + "," + "'" + tipoValidacion + "'," + "" + caracteristicaValida + "," + "'" + nombreProcedimiento + "'," + "" + numeroDecimales + "," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 392 */       rta = this.dat.executeUpdate2(s);
/* 393 */       rta.setSecuencia(elSiguiente);
/*     */     }
/* 395 */     catch (Exception e) {
/* 396 */       e.printStackTrace();
/* 397 */       Utilidades.writeError("%CaracteristicasDAO:crearRegistro ", e);
/* 398 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 400 */     return rta;
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
/*     */   public RespuestaBD modificarRegistro(int codigo, String descripcion, String tipo, String calificar, int longitud, String competencia, int caracteristicaAnida, String permiteExtender, int caracteristicaDepende, int valorDepende, String tipoValidacion, int caracteristicaValida, String nombreProcedimiento, int numeroDecimales, String usuarioModificacion) {
/* 425 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 428 */       String s = "update caracteristicas set  descripcion='" + descripcion + "'," + " tipo='" + tipo + "'," + " calificar='" + calificar + "'," + " longitud=" + longitud + "," + " competencia='" + competencia + "'," + " caracteristica_anida=" + caracteristicaAnida + "," + " permite_extender='" + permiteExtender + "'," + " caracteristica_depende=" + caracteristicaDepende + "," + " valor_depende=" + valorDepende + "," + " tipo_validacion='" + tipoValidacion + "'," + " caracteristica_valida=" + caracteristicaValida + "," + " nombre_procedimiento='" + nombreProcedimiento + "'," + " numero_decimales=" + numeroDecimales + "," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " codigo=" + codigo + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 447 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 449 */     catch (Exception e) {
/* 450 */       e.printStackTrace();
/* 451 */       Utilidades.writeError("CaracteristicasDAO:modificarRegistro ", e);
/* 452 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 454 */     return rta;
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
/*     */   public boolean cargarTodosParaServicioObligatorias(int servicio, String rol) {
/*     */     try {
/* 467 */       String s = "select c.*,cs.obligatoria,cs.rol,cs.estado from caracteristicas c,caracteristicas_servicio cs where cs.codigo_caracteristica=c.codigo  and cs.obligatoria='S' and coalesce(cs.estado,'A')='A' and cs.codigo_servicio=" + servicio + " and cs.rol='" + rol + "'" + " order by cs.Indice";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 478 */       boolean rta = this.dat.parseSql(s);
/* 479 */       if (!rta) return false; 
/* 480 */       this.rs = this.dat.getResultSet();
/* 481 */       return true;
/*     */     }
/* 483 */     catch (Exception e) {
/* 484 */       e.printStackTrace();
/* 485 */       Utilidades.writeError("CaracteristicaFactory", e);
/*     */       
/* 487 */       return false;
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
/*     */   public RespuestaBD validarProcedimiento(int idsol, int caracteristica, String valor, String procedimiento, int anidada) {
/* 506 */     if (anidada > 0) {
/* 507 */       return this.dat.procedimientoCursor(idsol, caracteristica, valor, procedimiento);
/*     */     }
/* 509 */     return this.dat.validarProcedimiento(idsol, caracteristica, valor, procedimiento);
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
/*     */   public CaracteristicasDTO cargarValorTiempo(int solicitud, int servicio) {
/*     */     try {
/* 522 */       String s = "select cv.duracion,cv.unidad_medida From  caracteristicas_valor cv,  caracteristicas_servicio s,  detalles_solicitud ds where    cv.caracteristica=s.codigo_caracteristica   and s.codigo_servicio=" + servicio + "   and ds.codigo_caracteristica = s.codigo_caracteristica" + "   and ds.numero_solicitud= " + solicitud + "   and ds.valor =cv.valor" + "   and coalesce(cv.estado,'A')='A'" + "   and coalesce(s.estado,'A')='A'" + "   and cv.duracion is not null" + "   and cv.unidad_medida is not null";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 538 */       boolean rta = this.dat.parseSql(s);
/* 539 */       if (!rta) return null;
/*     */       
/* 541 */       this.rs = this.dat.getResultSet();
/* 542 */       if (this.rs.next()) {
/* 543 */         CaracteristicasDTO reg = new CaracteristicasDTO();
/* 544 */         reg.setUnidadMedida(this.rs.getString("unidad_medida"));
/* 545 */         reg.setDuracion(this.rs.getInt("duracion"));
/* 546 */         return reg;
/*     */       }
/*     */     
/* 549 */     } catch (Exception e) {
/* 550 */       e.printStackTrace();
/* 551 */       Utilidades.writeError("CaracteristicaFactory", e);
/*     */     } 
/* 553 */     return null;
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
/*     */   public int pendientes(int solicitud, int servicio, String rol) {
/*     */     try {
/* 568 */       String s = "select sum(case when d.numero_solicitud is null then 1 else 0 end) as faltantes, sum(case when d.numero_solicitud is not null then 1 else 0 end) as asignados, count(0) as cuantas from  caracteristicas_servicio c left join detalles_solicitud  d on ( c.codigo_caracteristica  = d.codigo_caracteristica and  d.numero_solicitud = " + solicitud + " )" + " where   c.codigo_servicio = " + servicio + " and c.obligatoria='S'" + " and coalesce(c.estado,'A')='A'" + " and c.rol='" + rol + "'";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 581 */       boolean rta = this.dat.parseSql(s);
/* 582 */       if (!rta) return 0; 
/* 583 */       this.rs = this.dat.getResultSet();
/* 584 */       if (this.rs.next()) {
/* 585 */         return this.rs.getInt("faltantes");
/*     */       
/*     */       }
/*     */     }
/* 589 */     catch (Exception e) {
/* 590 */       e.printStackTrace();
/* 591 */       Utilidades.writeError("pendientes ", e);
/*     */     } 
/*     */     
/* 594 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<CaracteristicasDTO> cargarTodosParaServicio(int servicio, String rol, String estado) {
/* 605 */     Collection<CaracteristicasDTO> resultados = new ArrayList<CaracteristicasDTO>();
/*     */     
/*     */     try {
/* 608 */       String s = "select c.*,cs.obligatoria,cs.rol,cs.estado,(SELECT COUNT(0) FROM CARACTERISTICAS ci WHERE ci.CARACTERISTICA_DEPENDE=c.CODIGO) dependen from caracteristicas c, caracteristicas_servicio cs  where cs.codigo_caracteristica=c.codigo    and cs.codigo_servicio=" + servicio;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 618 */       if (rol.equals("C") || rol.equals("P")) {
/* 619 */         s = s + " and cs.rol='" + rol + "'";
/*     */       }
/* 621 */       else if (rol.equals("ACEPTA")) {
/* 622 */         s = s + " and c.calificar='S'";
/*     */       } 
/*     */       
/* 625 */       if (estado.equals("A")) {
/* 626 */         s = s + " and coalesce(cs.estado,'A')='A'";
/*     */       }
/*     */       
/* 629 */       if (rol.equals("F")) {
/* 630 */         s = s + " order by cs.Indice";
/*     */       } else {
/*     */         
/* 633 */         s = s + " order by cs.rol,cs.Indice";
/*     */       } 
/* 635 */       boolean rta = this.dat.parseSql(s);
/* 636 */       if (!rta) {
/* 637 */         return resultados;
/*     */       }
/* 639 */       this.rs = this.dat.getResultSet();
/* 640 */       while (this.rs.next())
/*     */       {
/* 642 */         CaracteristicasDTO reg = new CaracteristicasDTO();
/* 643 */         reg.setCodigo(this.rs.getInt("codigo"));
/* 644 */         reg.setDescripcion(this.rs.getString("descripcion"));
/* 645 */         reg.setTipo(this.rs.getString("tipo"));
/* 646 */         reg.setCalificar(this.rs.getString("calificar"));
/* 647 */         reg.setObliga(this.rs.getString("obligatoria").toLowerCase().equals("s"));
/* 648 */         reg.setRol(this.rs.getString("rol"));
/* 649 */         reg.setLongitud(this.rs.getInt("longitud"));
/* 650 */         reg.setCaracteristicaAnida(this.rs.getInt("caracteristica_anida"));
/* 651 */         reg.setPermiteExtender(this.rs.getString("permite_extender"));
/* 652 */         reg.setCaracteristicaDepende(this.rs.getInt("caracteristica_depende"));
/* 653 */         reg.setValorDepende(this.rs.getInt("valor_depende"));
/* 654 */         reg.setCuantasDependen(this.rs.getInt("dependen"));
/* 655 */         reg.setEstado(this.rs.getString("estado"));
/* 656 */         reg.setTipoValidacion(this.rs.getString("tipo_validacion"));
/* 657 */         reg.setCaracteristicaValida(this.rs.getInt("caracteristica_valida"));
/* 658 */         reg.setNombreProcedimiento(this.rs.getString("nombre_procedimiento"));
/* 659 */         reg.setNumeroDecimales(this.rs.getInt("numero_decimales"));
/* 660 */         resultados.add(reg);
/*     */       }
/*     */     
/* 663 */     } catch (Exception e) {
/* 664 */       e.printStackTrace();
/* 665 */       Utilidades.writeError("CaracteristicaFactory", e);
/*     */     } 
/* 667 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CaracteristicasDTO cargarRegistro(int servicio, int car) {
/*     */     try {
/* 678 */       String s = "select c.*,cs.obligatoria,cs.rol  from caracteristicas c,caracteristicas_servicio cs where cs.codigo_caracteristica=c.codigo  and cs.codigo_servicio=" + servicio + " and c.codigo=" + car;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 685 */       boolean rta = this.dat.parseSql(s);
/* 686 */       if (!rta) return null; 
/* 687 */       this.rs = this.dat.getResultSet();
/* 688 */       if (this.rs.next()) {
/* 689 */         return leerRegistro();
/*     */       }
/*     */     }
/* 692 */     catch (Exception e) {
/* 693 */       e.printStackTrace();
/* 694 */       Utilidades.writeError("CaracteristicaFactory", e);
/*     */     } 
/* 696 */     return null;
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
/*     */   public CaracteristicasDTO cargarRegistro(int servicio, int car, String rol) {
/*     */     try {
/* 709 */       String s = "select c.*,cs.obligatoria,cs.rol from caracteristicas c,caracteristicas_servicio cs where cs.codigo_caracteristica=c.codigo  and cs.codigo_servicio=" + servicio + " and c.codigo=" + car + " and cs.rol='" + rol + "'";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 717 */       boolean rta = this.dat.parseSql(s);
/* 718 */       if (!rta) return null; 
/* 719 */       this.rs = this.dat.getResultSet();
/* 720 */       if (this.rs.next()) {
/* 721 */         return leerRegistro();
/*     */       }
/*     */     }
/* 724 */     catch (Exception e) {
/* 725 */       e.printStackTrace();
/* 726 */       Utilidades.writeError("CaracteristicaFactory", e);
/*     */     } 
/* 728 */     return null;
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
/*     */   public Collection<CaracteristicasDTO> cargarCaracteristicaServicio(int servicio, int caracteristica) {
/* 741 */     Collection<CaracteristicasDTO> resultados = new ArrayList<CaracteristicasDTO>();
/*     */     try {
/* 743 */       String s = "select caracteristicas.*,sis_multivalores.descripcion as nombre_tipo from caracteristicas,sis_multivalores where caracteristicas.tipo=sis_multivalores.valor and sis_multivalores.tabla='TIPO_CARACTERISTICA'";
/*     */ 
/*     */ 
/*     */       
/* 747 */       if (caracteristica == 0) {
/* 748 */         s = s + " and caracteristicas.codigo not in(select codigo_caracteristica from caracteristicas_servicio";
/* 749 */         s = s + " where codigo_servicio=" + servicio + ")";
/*     */       } else {
/*     */         
/* 752 */         s = s + " and caracteristicas.codigo =" + caracteristica;
/*     */       } 
/* 754 */       s = s + " order by caracteristicas.descripcion";
/*     */ 
/*     */       
/* 757 */       boolean rtaDB = this.dat.parseSql(s);
/* 758 */       if (!rtaDB) {
/* 759 */         return resultados;
/*     */       }
/* 761 */       this.rs = this.dat.getResultSet();
/* 762 */       while (this.rs.next())
/*     */       {
/* 764 */         CaracteristicasDTO reg = new CaracteristicasDTO();
/* 765 */         reg.setCodigo(this.rs.getInt("codigo"));
/* 766 */         reg.setDescripcion(this.rs.getString("descripcion"));
/* 767 */         resultados.add(reg);
/*     */       }
/*     */     
/* 770 */     } catch (Exception e) {
/* 771 */       e.printStackTrace();
/* 772 */       Utilidades.writeError("CaracteristicasDAO:cargarTodos ", e);
/*     */     } 
/* 774 */     return resultados;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\CaracteristicasDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */