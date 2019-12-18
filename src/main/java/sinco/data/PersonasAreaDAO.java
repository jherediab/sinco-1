/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.PersonasAreaDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.PersonasAreaDAO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PersonasAreaDAO
/*     */ {
/*     */   ResultSet rs;
/*  25 */   DBManager dat = new DBManager();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  34 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/*     */     try {
/*  41 */       this.dat.close();
/*     */     }
/*  43 */     catch (Exception e) {
/*  44 */       Utilidades.writeError("PersonasAreaDAO:close", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PersonasAreaDTO next() {
/*     */     try {
/*  55 */       if (this.rs.next()) {
/*  56 */         return leerRegistro();
/*     */       }
/*     */     }
/*  59 */     catch (Exception e) {
/*  60 */       e.printStackTrace();
/*  61 */       Utilidades.writeError("PersonasAreaDAO:next ", e);
/*     */     } 
/*  63 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PersonasAreaDTO leerRegistro() {
/*     */     try {
/*  73 */       PersonasAreaDTO reg = new PersonasAreaDTO();
/*  74 */       reg.setCodigoArea(this.rs.getInt("codigo_area"));
/*  75 */       reg.setCodigoEmpleado(this.rs.getInt("codigo_empleado"));
/*  76 */       reg.setClase(this.rs.getInt("clase"));
/*  77 */       reg.setResponsableArea(this.rs.getString("responsable_area"));
/*  78 */       reg.setAreaPrincipal(this.rs.getString("area_principal"));
/*  79 */       reg.setNombreArea(this.rs.getString("nombre_area"));
/*  80 */       reg.setNombrePersona(this.rs.getString("nombre_persona"));
/*  81 */       reg.setNombreClase(this.rs.getString("nombre_clase"));
/*  82 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  83 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  84 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  85 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  86 */       return reg;
/*     */     }
/*  88 */     catch (Exception e) {
/*  89 */       e.printStackTrace();
/*  90 */       Utilidades.writeError("PersonasAreaDAO:leerRegistro ", e);
/*     */       
/*  92 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean cargarDePersona(int codigoEmpleado) {
/*     */     try {
/* 102 */       String s = "select sis_usuarios_area.*,personas.apellidos||' '||personas.nombres as nombre_persona, sis_grupos.descripcion as nombre_clase, unidades_dependencia.descripcion as nombre_area from sis_usuarios_area sis_usuarios_area,unidades_dependencia,sis_usuarios personas,sis_grupos sis_grupos  where sis_usuarios_area.codigo_area=unidades_dependencia.codigo and sis_usuarios_area.codigo_empleado =personas.codigo_empleado and sis_usuarios_area.clase=sis_grupos.codigo and personas.estado<>'I' and sis_usuarios_area.codigo_empleado=" + codigoEmpleado;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 111 */       boolean rtaDB = this.dat.parseSql(s);
/* 112 */       if (!rtaDB) {
/* 113 */         return false;
/*     */       }
/*     */       
/* 116 */       this.rs = this.dat.getResultSet();
/* 117 */       return true;
/*     */     }
/* 119 */     catch (Exception e) {
/* 120 */       e.printStackTrace();
/* 121 */       Utilidades.writeError("PersonasAreaDAO:cargarTodos ", e);
/*     */       
/* 123 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PersonasAreaDTO cargarRegistro(int codigoArea, int codigoEmpleado) {
/*     */     try {
/* 133 */       String s = "select ua.*,per.apellidos||' '||per.nombres as nombre_persona, sis_grupos.descripcion as nombre_clase, u.descripcion as nombre_area from sis_usuarios_area ua,unidades_dependencia u,sis_usuarios per,sis_grupos  where ua.codigo_area=u.codigo and ua.codigo_empleado =per.codigo_empleado and ua.clase=sis_grupos.codigo and per.estado<>'I' and ua.codigo_empleado=" + codigoEmpleado + " and ua.codigo_area=" + codigoArea;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 143 */       boolean rtaDB = this.dat.parseSql(s);
/* 144 */       if (!rtaDB) {
/* 145 */         return null;
/*     */       }
/* 147 */       this.rs = this.dat.getResultSet();
/* 148 */       if (this.rs.next()) {
/* 149 */         return leerRegistro();
/*     */       }
/*     */     }
/* 152 */     catch (Exception e) {
/* 153 */       e.printStackTrace();
/* 154 */       Utilidades.writeError("PersonasAreaDAO:cargarPersonasArea ", e);
/*     */     } 
/* 156 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PersonasAreaDTO cargarJefe(int codigoArea) {
/*     */     try {
/* 166 */       String s = "select pa.*,p.apellidos||' '||p.nombres as nombre_persona, cu.descripcion as nombre_clase, u.descripcion as nombre_area from sis_usuarios_area pa,unidades_dependencia u,sis_usuarios p,sis_grupos cu where pa.codigo_area=u.codigo   and pa.codigo_empleado =p.codigo_empleado   and pa.clase=cu.codigo   and p.estado<>'I'   and pa.codigo_area=" + codigoArea + "   and pa.responsable_area='S'";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 176 */       boolean rtaDB = this.dat.parseSql(s);
/* 177 */       if (!rtaDB) {
/* 178 */         return null;
/*     */       }
/* 180 */       this.rs = this.dat.getResultSet();
/* 181 */       if (this.rs.next()) {
/* 182 */         return leerRegistro();
/*     */       }
/*     */     }
/* 185 */     catch (Exception e) {
/* 186 */       e.printStackTrace();
/* 187 */       Utilidades.writeError("PersonasAreaDAO:cargarTodos ", e);
/*     */     } 
/* 189 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PersonasAreaDTO cargarPersona(int codigoEmpleado) {
/*     */     try {
/* 199 */       String s = "select sis_usuarios_area.*,   personas.apellidos||' '||personas.nombres as nombre_persona,   sis_grupos.descripcion as nombre_clase,   unidades_dependencia.descripcion as nombre_area from sis_usuarios_area sis_usuarios_area,unidades_dependencia,sis_usuarios personas,sis_grupos  where sis_usuarios_area.codigo_area=unidades_dependencia.codigo       and sis_usuarios_area.codigo_empleado =personas.codigo_empleado       and sis_usuarios_area.clase=sis_grupos.codigo       and personas.estado<>'I'       and coalesce(sis_usuarios_area.area_principal,'S')='S'       and sis_usuarios_area.codigo_empleado=" + codigoEmpleado;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 210 */       boolean rtaDB = this.dat.parseSql(s);
/* 211 */       if (!rtaDB) {
/* 212 */         return null;
/*     */       }
/* 214 */       this.rs = this.dat.getResultSet();
/* 215 */       if (this.rs.next()) {
/* 216 */         return leerRegistro();
/*     */       }
/*     */     }
/* 219 */     catch (Exception e) {
/* 220 */       e.printStackTrace();
/* 221 */       Utilidades.writeError("PersonasAreaDAO:cargarPersonasArea ", e);
/*     */     } 
/* 223 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PersonasAreaDTO cargarPersonaUnArea(int codigoEmpleado) {
/*     */     try {
/* 233 */       String s = "select sis_usuarios_area.*,   personas.apellidos||' '||personas.nombres as nombre_persona,   sis_grupos.descripcion as nombre_clase,   unidades_dependencia.descripcion as nombre_area from sis_usuarios_area sis_usuarios_area,unidades_dependencia,sis_usuarios personas,sis_grupos  where sis_usuarios_area.codigo_area=unidades_dependencia.codigo       and sis_usuarios_area.codigo_empleado =personas.codigo_empleado       and sis_usuarios_area.clase=sis_grupos.codigo       and personas.estado<>'I'       and sis_usuarios_area.codigo_empleado=" + codigoEmpleado;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 243 */       boolean rtaDB = this.dat.parseSql(s);
/* 244 */       if (!rtaDB) {
/* 245 */         return null;
/*     */       }
/* 247 */       this.rs = this.dat.getResultSet();
/* 248 */       if (this.rs.next()) {
/* 249 */         return leerRegistro();
/*     */       }
/*     */     }
/* 252 */     catch (Exception e) {
/* 253 */       e.printStackTrace();
/* 254 */       Utilidades.writeError("PersonasAreaDAO:cargarPersonasArea ", e);
/*     */     } 
/* 256 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumeroAreas(int codigoEmpleado) {
/*     */     try {
/* 267 */       String s = "select count(0) as numero from sis_usuarios_area sis_usuarios_area where sis_usuarios_area.codigo_empleado=" + codigoEmpleado;
/*     */       
/* 269 */       boolean rtaDB = this.dat.parseSql(s);
/* 270 */       if (!rtaDB) {
/* 271 */         return 0;
/*     */       }
/*     */       
/* 274 */       this.rs = this.dat.getResultSet();
/* 275 */       if (this.rs.next()) {
/* 276 */         return this.rs.getInt("numero");
/*     */       }
/*     */     }
/* 279 */     catch (Exception e) {
/* 280 */       e.printStackTrace();
/* 281 */       Utilidades.writeError("PersonasAreaDAO:getNumeroAreas ", e);
/*     */     } 
/* 283 */     return 0;
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
/*     */   public boolean actualizarAreaPersona(int codigoEmpleado, int codigoArea, int clase, String elUsuario) {
/*     */     try {
/* 297 */       String s = "update sis_usuarios set area= " + codigoArea + "," + " clase=" + clase + "," + " usuario_modificacion='" + elUsuario + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + " where codigo_empleado =" + codigoEmpleado;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 303 */       return this.dat.executeUpdate(s);
/*     */ 
/*     */     
/*     */     }
/* 307 */     catch (Exception e) {
/* 308 */       e.printStackTrace();
/* 309 */       Utilidades.writeError("PersonasAreaDAO:eliminarRegistro", e);
/*     */       
/* 311 */       return false;
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
/*     */   public Collection<PersonasAreaDTO> personasArea(int codigoArea) {
/* 324 */     String s = "select p.codigo_empleado,p.apellidos||' '||p.nombres as nombre from sis_usuarios p, sis_usuarios_area pa where p.codigo_empleado=pa.codigo_empleado and p.estado in ('A','T') AND PA.codigo_area=" + codigoArea + " order by 2";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 332 */     Collection<PersonasAreaDTO> resultados = new ArrayList<PersonasAreaDTO>();
/*     */     try {
/* 334 */       boolean rta = this.dat.parseSql(s);
/* 335 */       if (!rta) {
/* 336 */         return resultados;
/*     */       }
/* 338 */       this.rs = this.dat.getResultSet();
/* 339 */       while (this.rs.next()) {
/* 340 */         PersonasAreaDTO reg = new PersonasAreaDTO();
/* 341 */         reg.setCodigoEmpleado(this.rs.getInt("codigo_empleado"));
/* 342 */         reg.setNombrePersona(this.rs.getString("nombre"));
/* 343 */         resultados.add(reg);
/*     */       }
/*     */     
/* 346 */     } catch (Exception e) {
/* 347 */       e.printStackTrace();
/* 348 */       Utilidades.writeError("cargarSubTipoProblema", e);
/*     */     } 
/* 350 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<PersonasAreaDTO> cargarTodos(int codigoArea) {
/* 361 */     String s = "select ua.*,u.apellidos||' '||u.nombres as nombre_persona, cu.descripcion as nombre_clase, a.descripcion as nombre_area from sis_usuarios_area ua,unidades_dependencia a,sis_usuarios u,sis_grupos cu where ua.codigo_area=a.codigo and ua.codigo_empleado =u.codigo_empleado and ua.clase=cu.codigo and ua.codigo_area=" + codigoArea + " and u.estado<>'I'" + " order by u.apellidos,u.nombres";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 372 */     Collection<PersonasAreaDTO> resultados = new ArrayList<PersonasAreaDTO>();
/*     */     try {
/* 374 */       boolean rta = this.dat.parseSql(s);
/* 375 */       if (!rta) {
/* 376 */         return resultados;
/*     */       }
/* 378 */       this.rs = this.dat.getResultSet();
/* 379 */       while (this.rs.next()) {
/* 380 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 383 */     catch (Exception e) {
/* 384 */       e.printStackTrace();
/* 385 */       Utilidades.writeError("cargarSubTipoProblema", e);
/*     */     } 
/* 387 */     return resultados;
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
/*     */   public boolean reemplazarAreaPrincipal(int codigoEmpleado, String usuarioModificacion) {
/*     */     try {
/* 402 */       String s = "update sis_usuarios_area set ";
/* 403 */       s = s + " area_principal='N',";
/* 404 */       s = s + " usuario_modificacion='" + usuarioModificacion + "',";
/* 405 */       s = s + " fecha_modificacion=" + Utilidades.getFechaBD() + "";
/* 406 */       s = s + " where ";
/* 407 */       s = s + " area_principal='S'";
/* 408 */       s = s + " and codigo_empleado=" + codigoEmpleado;
/* 409 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 412 */     catch (Exception e) {
/* 413 */       e.printStackTrace();
/* 414 */       Utilidades.writeError("PersonasAreaDAO:reemplazarAreaPrincipal ", e);
/*     */       
/* 416 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean reemplazarJefe(int codigoArea, int codigoEmpleado, String usuarioModificacion) {
/*     */     try {
/* 427 */       String s = "update sis_usuarios_area set ";
/* 428 */       s = s + " responsable_area='N',";
/* 429 */       s = s + " usuario_modificacion='" + usuarioModificacion + "',";
/* 430 */       s = s + " fecha_modificacion=" + Utilidades.getFechaBD() + "";
/* 431 */       s = s + " where ";
/* 432 */       s = s + " responsable_area='S'";
/* 433 */       s = s + " and codigo_area=" + codigoArea;
/* 434 */    boolean   rta = this.dat.executeUpdate(s);
/*     */       
/* 436 */       s = "update unidades_dependencia set persona_responsable=" + codigoEmpleado + "," + " usuario_modificacion='" + usuarioModificacion + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + " where codigo =" + codigoArea;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 441 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 444 */     catch (Exception e) {
/* 445 */       e.printStackTrace();
/* 446 */       Utilidades.writeError("PersonasAreaDAO:reemplazarJefe ", e);
/*     */       
/* 448 */       return false;
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
/*     */   public boolean eliminarRegistro(int codigoArea, int codigoEmpleado, String elUsuario) {
/*     */     try {
/* 461 */       String s = "update unidades_dependencia set persona_responsable= 0, usuario_modificacion='" + elUsuario + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + " where codigo =" + codigoArea + " and persona_responsable=" + codigoEmpleado;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 467 */     boolean   rta = this.dat.executeUpdate(s);
/*     */       
/* 469 */       s = "delete from  sis_usuarios_area where codigo_area=" + codigoArea + " and codigo_empleado=" + codigoEmpleado;
/* 470 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 473 */     catch (Exception e) {
/* 474 */       e.printStackTrace();
/* 475 */       Utilidades.writeError("PersonasAreaDAO:eliminarRegistro", e);
/*     */       
/* 477 */       return false;
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
/*     */   public boolean actualizarAreaPersona(int codigoEmpleado, int codigoArea, String elUsuario) {
/*     */     try {
/* 490 */       String s = "update personas set area= " + codigoArea + "," + " usuario_modificacion='" + elUsuario + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + " where codigo_empleado =" + codigoEmpleado + " and area<>" + codigoArea;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 496 */       return this.dat.executeUpdate(s);
/*     */ 
/*     */     
/*     */     }
/* 500 */     catch (Exception e) {
/* 501 */       e.printStackTrace();
/* 502 */       Utilidades.writeError("PersonasAreaDAO:eliminarRegistro", e);
/*     */       
/* 504 */       return false;
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
/*     */   public boolean crearRegistro(int codigoArea, int codigoEmpleado, int clase, String responsableArea, String areaPrincipal, String usuarioInsercion) {
/*     */     try {
/* 522 */       if (responsableArea.equals("S")) {
/* 523 */         reemplazarJefe(codigoArea, codigoEmpleado, usuarioInsercion);
/*     */       }
/*     */       
/* 526 */       if (areaPrincipal.equals("S")) {
/* 527 */         reemplazarAreaPrincipal(codigoEmpleado, usuarioInsercion);
/*     */       }
/*     */       
/* 530 */       String s = "insert into sis_usuarios_area ( codigo_area,codigo_empleado,clase,responsable_area,area_principal,usuario_insercion,fecha_insercion)";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 538 */       s = s + " values (";
/* 539 */       s = s + "" + codigoArea + ",";
/* 540 */       s = s + "" + codigoEmpleado + ",";
/* 541 */       s = s + "" + clase + ",";
/* 542 */       s = s + "'" + responsableArea + "',";
/* 543 */       s = s + "'" + areaPrincipal + "',";
/* 544 */       s = s + "'" + usuarioInsercion + "',";
/* 545 */       s = s + "" + Utilidades.getFechaBD();
/* 546 */       s = s + ")";
/* 547 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 550 */     catch (Exception e) {
/* 551 */       e.printStackTrace();
/* 552 */       Utilidades.writeError("PersonasAreaDAO:crearRegistro", e);
/*     */       
/* 554 */       return false;
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
/*     */   public boolean modificarRegistro(int codigoArea, int codigoEmpleado, int clase, String responsableArea, String areaPrincipal, String usuarioModificacion) {
/*     */     try {
/* 571 */       if (responsableArea.equals("S")) {
/* 572 */         reemplazarJefe(codigoArea, codigoEmpleado, usuarioModificacion);
/*     */       }
/*     */       
/* 575 */       if (areaPrincipal.equals("S")) {
/* 576 */         reemplazarAreaPrincipal(codigoEmpleado, usuarioModificacion);
/*     */       }
/*     */       
/* 579 */       String s = "update sis_usuarios_area set ";
/* 580 */       s = s + " clase=" + clase + ",";
/* 581 */       s = s + " area_principal='" + areaPrincipal + "',";
/* 582 */       s = s + " responsable_area='" + responsableArea + "',";
/* 583 */       s = s + " usuario_modificacion='" + usuarioModificacion + "',";
/* 584 */       s = s + " fecha_modificacion=" + Utilidades.getFechaBD() + "";
/* 585 */       s = s + " where ";
/* 586 */       s = s + " codigo_area=" + codigoArea;
/* 587 */       s = s + " and codigo_empleado=" + codigoEmpleado;
/* 588 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 591 */     catch (Exception e) {
/* 592 */       e.printStackTrace();
/* 593 */       Utilidades.writeError("PersonasAreaDAO:modificarRegistro", e);
/*     */       
/* 595 */       return false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\PersonasAreaDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */