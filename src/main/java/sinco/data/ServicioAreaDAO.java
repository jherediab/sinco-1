/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.ServicioAreaDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.ServicioAreaDAO;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ServicioAreaDAO
/*     */ {
/*     */   ResultSet rs;
/*  18 */   DBManager dat = new DBManager();
/*     */ 
/*     */   
/*  21 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*     */ 
/*     */   
/*     */   public void close() {
/*     */     try {
/*  26 */       this.dat.close();
/*     */     }
/*  28 */     catch (Exception e) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ServicioAreaDTO leerRegistro() {
/*     */     try {
/*  39 */       ServicioAreaDTO reg = new ServicioAreaDTO();
/*  40 */       reg.setCodigoArea(this.rs.getInt("codigo_area"));
/*  41 */       reg.setCodigoServicio(this.rs.getInt("codigo_servicio"));
/*  42 */       String temp = this.rs.getString("persona_cargo");
/*  43 */       if (this.rs.wasNull() || temp.equals("0")) {
/*  44 */         reg.setPersonaCargo(-1);
/*     */       } else {
/*     */         
/*  47 */         reg.setPersonaCargo(Integer.parseInt(temp));
/*     */       } 
/*  49 */       return reg;
/*     */     }
/*  51 */     catch (Exception e) {
/*  52 */       e.printStackTrace();
/*  53 */       Utilidades.writeError("ServicioAreaFactory.leerRegistro ", e);
/*     */       
/*  55 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public ServicioAreaDTO next() {
/*     */     try {
/*  61 */       if (this.rs.next()) {
/*  62 */         return leerRegistro();
/*     */       }
/*     */     }
/*  65 */     catch (SQLException e) {
/*  66 */       e.printStackTrace();
/*     */     } 
/*  68 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean cargarServiciosDeFuncionario(int funcionario) {
/*     */     try {
/*  80 */       boolean rta = this.dat.parseSql("select * from servicios_area where persona_cargo=" + funcionario);
/*  81 */       if (!rta) return false; 
/*  82 */       this.rs = this.dat.getResultSet();
/*  83 */       return true;
/*     */     }
/*  85 */     catch (Exception e) {
/*  86 */       e.printStackTrace();
/*     */       
/*  88 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int cuantosEspecializados(int funcionario) {
/*     */     try {
/*  99 */       boolean rta = this.dat.parseSql("select count(0) as numero from servicios,servicios_area where SERVICIOS_AREA.codigo_servicio=SERVICIOS.codigo AND SERVICIOS.especializado='S' AND persona_cargo=" + funcionario);
/* 100 */       if (!rta) return 0; 
/* 101 */       this.rs = this.dat.getResultSet();
/* 102 */       if (this.rs.next()) {
/* 103 */         return this.rs.getInt("numero");
/*     */       }
/*     */     }
/* 106 */     catch (Exception e) {
/* 107 */       e.printStackTrace();
/*     */     } 
/* 109 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ServicioAreaDTO getServicioArea(int codarea, int codservicio) {
/*     */     try {
/* 121 */       boolean rta = this.dat.parseSql("select * from servicios_area where codigo_area=" + codarea + " and codigo_servicio=" + codservicio);
/* 122 */       if (!rta) return null; 
/* 123 */       this.rs = this.dat.getResultSet();
/* 124 */       if (this.rs.next()) {
/* 125 */         return leerRegistro();
/*     */       }
/*     */     }
/* 128 */     catch (SQLException e) {
/* 129 */       e.printStackTrace();
/*     */     } 
/* 131 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSolicitudesAbiertas(int funcionario) {
/*     */     try {
/* 142 */       String s = "select count(0) as numero from solicitudes";
/* 143 */       s = s + " where empleado_proveedor=" + funcionario;
/* 144 */       s = s + " and abierta='S' ";
/* 145 */       s = s + " and codigo_estado in ( ";
/* 146 */       s = s + "   select codigo from estados where tipo_estado in('ESC','PRV'))";
/* 147 */       boolean rta = this.dat.parseSql(s);
/* 148 */       if (!rta) return 0; 
/* 149 */       this.rs = this.dat.getResultSet();
/* 150 */       if (this.rs.next()) {
/* 151 */         return this.rs.getInt("numero");
/*     */       }
/*     */     }
/* 154 */     catch (SQLException e) {
/* 155 */       e.printStackTrace();
/*     */     } 
/* 157 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSolicitudesEscaladas(int funcionario) {
/*     */     try {
/* 167 */       String s = "select count(0) as numero from solicitudes";
/* 168 */       s = s + " where empleado_proveedor=" + funcionario;
/* 169 */       s = s + " and abierta='S' ";
/* 170 */       s = s + " and codigo_estado in ( ";
/* 171 */       s = s + "   select codigo from estados where tipo_estado in('ESC'))";
/* 172 */       boolean rta = this.dat.parseSql(s);
/* 173 */       if (!rta) return 0; 
/* 174 */       this.rs = this.dat.getResultSet();
/* 175 */       if (this.rs.next()) {
/* 176 */         return this.rs.getInt("numero");
/*     */       }
/*     */     }
/* 179 */     catch (SQLException e) {
/* 180 */       e.printStackTrace();
/*     */     } 
/* 182 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD crearRegistro(int codigoArea, int codigoServicio, int personaCargo, String usuarioInsercion) {
/* 193 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 196 */       String s = "insert into servicios_area ( codigo_area,codigo_servicio,persona_cargo,usuario_insercion,fecha_insercion )";
/* 197 */       s = s + " values (";
/* 198 */       s = s + "" + codigoArea + ",";
/* 199 */       s = s + "" + codigoServicio + ",";
/* 200 */       s = s + "" + personaCargo + ",";
/* 201 */       s = s + "'" + usuarioInsercion + "',";
/* 202 */       s = s + "" + Utilidades.getFechaBD();
/* 203 */       s = s + ")";
/* 204 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 206 */     catch (Exception e) {
/* 207 */       e.printStackTrace();
/* 208 */       Utilidades.writeError("ServiciosAreaDAO:crearRegistro", e);
/* 209 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 211 */     return rta;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarRegistro(int codigoArea, int codigoServicio) {
/* 222 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 225 */       String s = "delete from  servicios_area where codigo_area=" + codigoArea + " and codigo_servicio=" + codigoServicio;
/* 226 */       rta = this.dat.executeUpdate2(s);
/* 227 */       s = "delete from  PROVEEDOR_MULTIPLE where codigo_area=" + codigoArea + " and codigo_servicio=" + codigoServicio;
/* 228 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 230 */     catch (Exception e) {
/* 231 */       e.printStackTrace();
/* 232 */       Utilidades.writeError("ServiciosAreaDAO:eliminarRegistro", e);
/* 233 */       rta.setMensaje(e.getMessage());
/*     */     } 
/*     */     
/* 236 */     return rta;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD modificarRegistro(int codigoArea, int codigoServicio, int personaCargo, String usuario) {
/* 246 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 249 */       String s = "update servicios_area set ";
/* 250 */       s = s + "persona_cargo=" + personaCargo + ",";
/* 251 */       s = s + "fecha_modificacion=" + Utilidades.formatoFecha(Utilidades.ahora()) + ",";
/* 252 */       s = s + "usuario_modificacion='" + usuario + "'";
/* 253 */       s = s + " where ";
/* 254 */       s = s + " codigo_area=" + codigoArea + " and";
/* 255 */       s = s + " codigo_servicio=" + codigoServicio + "";
/* 256 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 258 */     catch (Exception e) {
/* 259 */       e.printStackTrace();
/* 260 */       Utilidades.writeError("CaracteristiasDAO:modificarRegistro ", e);
/* 261 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 263 */     return rta;
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
/*     */   public Collection cargarTodos(int codigoServicio) {
/* 276 */     Collection resultados = new ArrayList();
/*     */     try {
/* 278 */       String s = "select  u.descripcion nombre_area, u.ubicacion, case when s.especializado='S' then ( select p.nombres|| ' '||p.apellidos nombre_responsable from sis_usuarios p,sis_usuarios_area pa where p.codigo_empleado=sa.persona_cargo and pa.codigo_empleado=p.codigo_empleado and pa.codigo_area=u.codigo) when s.especializado='M' then fun_responsables_servicio(s.codigo,u.codigo) else null end as nombre_especialista from servicios s,servicios_area sa,unidades_dependencia u where s.codigo=sa.codigo_servicio and u.codigo=sa.codigo_area and u.estado='A' and s.codigo=" + codigoServicio + " order by 1";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 296 */       boolean rtaDB = this.dat.parseSql(s);
/* 297 */       if (!rtaDB) {
/* 298 */         return resultados;
/*     */       }
/* 300 */       this.rs = this.dat.getResultSet();
/* 301 */       while (this.rs.next()) {
/* 302 */         ServicioAreaDTO reg = new ServicioAreaDTO();
/* 303 */         reg.setNombreArea(this.rs.getString("nombre_area"));
/* 304 */         reg.setUbicacion(this.rs.getString("ubicacion"));
/* 305 */         reg.setNombreEspecialista(this.rs.getString("nombre_especialista"));
/* 306 */         resultados.add(reg);
/*     */       }
/*     */     
/* 309 */     } catch (Exception e) {
/* 310 */       e.printStackTrace();
/* 311 */       Utilidades.writeError("ServiciosDAO:cargarTodos ", e);
/*     */     } 
/* 313 */     return resultados;
/*     */   }
/*     */   
/*     */   public boolean cargarAreasDeServicio(int servicio) {
/*     */     try {
/* 318 */       String s = "select servicios_area.*,unidades_dependencia.descripcion as nombre_area,servicios.descripcion as nombre_servicio, personas.nombres||' '||personas.apellidos as nombre_responsable from unidades_dependencia,servicios,servicios_area left join sis_usuarios personas on(servicios_area.persona_cargo=personas.codigo_empleado) where servicios_area.codigo_servicio=servicios.codigo and servicios_area.codigo_area=unidades_dependencia.codigo and  servicios_area.codigo_servicio=" + servicio + " order by unidades_dependencia.descripcion";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 327 */       boolean rta = this.dat.parseSql(s);
/* 328 */       if (!rta) return false; 
/* 329 */       this.rs = this.dat.getResultSet();
/* 330 */       return true;
/*     */     }
/* 332 */     catch (Exception e) {
/* 333 */       e.printStackTrace();
/*     */       
/* 335 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ServicioAreaDTO leerRegistro2() {
/*     */     try {
/* 345 */       ServicioAreaDTO reg = new ServicioAreaDTO();
/* 346 */       reg.setCodigoArea(this.rs.getInt("codigo_area"));
/* 347 */       reg.setCodigoServicio(this.rs.getInt("codigo_servicio"));
/*     */       
/* 349 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/* 350 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/* 351 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/* 352 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*     */       
/* 354 */       String temp = this.rs.getString("persona_cargo");
/* 355 */       if (this.rs.wasNull() || temp.equals("0")) {
/* 356 */         reg.setPersonaCargo(-1);
/*     */       } else {
/*     */         
/* 359 */         reg.setPersonaCargo(Integer.parseInt(temp));
/*     */       } 
/*     */       
/* 362 */       reg.setNombreArea(this.rs.getString("nombre_area"));
/* 363 */       reg.setNombreServicio(this.rs.getString("nombre_servicio"));
/* 364 */       reg.setNombreResponsable(this.rs.getString("nombre_responsable"));
/* 365 */       return reg;
/*     */     }
/* 367 */     catch (Exception e) {
/* 368 */       e.printStackTrace();
/* 369 */       Utilidades.writeError("ServicioAreaFactory.leerRegistro2 ", e);
/*     */       
/* 371 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public ServicioAreaDTO next2() {
/*     */     try {
/* 377 */       if (this.rs.next()) {
/* 378 */         return leerRegistro2();
/*     */       }
/*     */     }
/* 381 */     catch (SQLException e) {
/* 382 */       e.printStackTrace();
/*     */     } 
/* 384 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection cargarServiciosDeArea(int area) {
/* 395 */     Collection resultados = new ArrayList();
/*     */     
/*     */     try {
/* 398 */       String s = "select sa.*,area.descripcion as nombre_area,ser.descripcion as nombre_servicio, res.nombres||' '||res.apellidos as nombre_responsable from unidades_dependencia area,servicios ser,servicios_area sa left join sis_usuarios res on(sa.persona_cargo=res.codigo_empleado) where sa.codigo_servicio=ser.codigo and sa.codigo_area=area.codigo and sa.codigo_area=" + area + " order by ser.descripcion";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 408 */       boolean rta = this.dat.parseSql(s);
/* 409 */       if (!rta) return resultados; 
/* 410 */       this.rs = this.dat.getResultSet();
/*     */       
/* 412 */       this.rs = this.dat.getResultSet();
/* 413 */       while (this.rs.next()) {
/* 414 */         resultados.add(leerRegistro2());
/*     */       }
/*     */     }
/* 417 */     catch (Exception e) {
/* 418 */       e.printStackTrace();
/*     */     } 
/* 420 */     return resultados;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\ServicioAreaDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */