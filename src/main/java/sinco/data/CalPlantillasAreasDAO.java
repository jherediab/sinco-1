/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import sinco.business.CalPlantillasAreasDTO;
/*     */ import sinco.business.ParametrosDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.CalPlantillasAreasDAO;
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
/*     */ public class CalPlantillasAreasDAO
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
/*  44 */       Utilidades.writeError("CalPlantillasAreasFactory:close", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalPlantillasAreasDTO next() {
/*     */     try {
/*  55 */       if (this.rs.next()) {
/*  56 */         return leerRegistro();
/*     */       }
/*     */     }
/*  59 */     catch (Exception e) {
/*  60 */       e.printStackTrace();
/*  61 */       Utilidades.writeError("CalPlantillasAreasFactory:next ", e);
/*     */     } 
/*  63 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalPlantillasAreasDTO leerRegistro() {
/*     */     try {
/*  73 */       CalPlantillasAreasDTO reg = new CalPlantillasAreasDTO();
/*     */       
/*  75 */       reg.setCodigoArea(this.rs.getInt("codigo_area"));
/*  76 */       reg.setNombreArea(this.rs.getString("nombre_area"));
/*  77 */       reg.setPermiteEspecificos(this.rs.getString("modifica_especificos"));
/*     */       
/*  79 */       return reg;
/*     */     }
/*  81 */     catch (Exception e) {
/*  82 */       e.printStackTrace();
/*  83 */       Utilidades.writeError("CalPlantillasAreasFactory:leerRegistro ", e);
/*     */       
/*  85 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean cargarTodos(int codigoCiclo, int codigoPlantilla) {
/*     */     try {
/*  95 */       String s = "select pa.codigo_area ,";
/*  96 */       s = s + " u.descripcion as nombre_area,";
/*  97 */       s = s + " coalesce(u.modifica_especificos,'N') modifica_especificos,";
/*  98 */       s = s + " pa.estado";
/*  99 */       s = s + " from cal_plantillas_areas pa ,unidades_dependencia u";
/* 100 */       s = s + " where ";
/* 101 */       s = s + " pa.codigo_area=u.codigo";
/* 102 */       s = s + " and pa.codigo_ciclo=" + codigoCiclo;
/* 103 */       s = s + " and pa.codigo_Plantilla=" + codigoPlantilla;
/* 104 */       s = s + " and pa.estado='A'";
/* 105 */       s = s + " order by u.descripcion";
/*     */       
/* 107 */       boolean rtaDB = this.dat.parseSql(s);
/* 108 */       if (!rtaDB) {
/* 109 */         return false;
/*     */       }
/*     */       
/* 112 */       this.rs = this.dat.getResultSet();
/* 113 */       return true;
/*     */     }
/* 115 */     catch (Exception e) {
/* 116 */       e.printStackTrace();
/* 117 */       Utilidades.writeError("CalPlantillasAreasFactory:cargarTodos ", e);
/*     */       
/* 119 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean cargarLibres(int codigoCiclo, String filtro) {
/*     */     try {
/* 130 */       String s = "select a.codigo as codigo_area, a.descripcion as nombre_area, coalesce(a.modifica_especificos,'N') modifica_especificos from unidades_dependencia a where a.estado='A'  AND a.codigo not in( select  pa.codigo_area from cal_plantillas_areas pa where pa.CODIGO_Ciclo=" + codigoCiclo + "       and pa.estado='A'" + " )" + " and upper(a.descripcion) like upper('" + filtro + "%')" + " order by a.descripcion";
/*     */ 
/*     */ 
/*     */ 
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
/* 145 */         return false;
/*     */       }
/*     */       
/* 148 */       this.rs = this.dat.getResultSet();
/* 149 */       return true;
/*     */     }
/* 151 */     catch (Exception e) {
/* 152 */       e.printStackTrace();
/* 153 */       Utilidades.writeError("CalPlantillasAreasFactory:cargarTodos ", e);
/*     */       
/* 155 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalPlantillasAreasDTO cargarRegistro(int codigoCiclo, int codigoPlantilla, int codigoArea) {
/*     */     try {
/* 165 */       String s = "select * from cal_plantillas_areas  where codigo_ciclo=" + codigoCiclo + " and codigo_plantilla=" + codigoPlantilla + " and codigo_area=" + codigoArea;
/*     */ 
/*     */ 
/*     */       
/* 169 */       boolean rtaDB = this.dat.parseSql(s);
/* 170 */       if (!rtaDB) {
/* 171 */         return null;
/*     */       }
/*     */       
/* 174 */       this.rs = this.dat.getResultSet();
/* 175 */       if (this.rs.next()) {
/* 176 */         CalPlantillasAreasDTO reg = new CalPlantillasAreasDTO();
/* 177 */         reg.setCodigoCiclo(this.rs.getInt("codigo_ciclo"));
/* 178 */         reg.setCodigoPlantilla(this.rs.getInt("codigo_plantilla"));
/* 179 */         reg.setEstado(this.rs.getString("estado"));
/* 180 */         reg.setCodigoArea(this.rs.getInt("codigo_area"));
/* 181 */         return reg;
/*     */       }
/*     */     
/* 184 */     } catch (Exception e) {
/* 185 */       e.printStackTrace();
/* 186 */       Utilidades.writeError("CalPlantillasAreasFactory:cargarRegistro ", e);
/*     */     } 
/* 188 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eliminarRegistro(int codigoCiclo, int codigoPlantilla, int codigoArea, String usuario) {
/*     */     try {
/* 198 */       String s = "update cal_plantillas_areas set estado='E',";
/* 199 */       s = s + "fecha_modificacion=" + Utilidades.getFechaBD() + ",";
/* 200 */       s = s + "usuario_modificacion='" + usuario + "'";
/* 201 */       s = s + " where codigo_ciclo=" + codigoCiclo;
/* 202 */       s = s + " and codigo_plantilla=" + codigoPlantilla;
/* 203 */       s = s + " and codigo_area=" + codigoArea;
/* 204 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 207 */     catch (Exception e) {
/* 208 */       e.printStackTrace();
/* 209 */       Utilidades.writeError("CalPlantillasAreasFactory:eliminarRegistro", e);
/*     */       
/* 211 */       return false;
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
/*     */   public boolean crearRegistro(int codigoCiclo, int codigoPlantilla, int codigoArea, String estado, String usuarioInsercion) {
/*     */     try {
/* 227 */       String s = "insert into cal_plantillas_areas (codigo_ciclo,codigo_plantilla,codigo_area,estado,procesado,fecha_insercion,usuario_insercion)";
/* 228 */       s = s + " values (";
/* 229 */       s = s + "" + codigoCiclo + ",";
/* 230 */       s = s + "" + codigoPlantilla + ",";
/* 231 */       s = s + "" + codigoArea + ",";
/* 232 */       s = s + "'" + estado + "',";
/* 233 */       s = s + "'N',";
/* 234 */       s = s + "" + Utilidades.getFechaBD() + ",";
/* 235 */       s = s + "'" + usuarioInsercion + "'";
/* 236 */       s = s + ")";
/* 237 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 240 */     catch (Exception e) {
/* 241 */       e.printStackTrace();
/* 242 */       Utilidades.writeError("CalPlantillasAreasFactory:crearRegistro", e);
/*     */       
/* 244 */       return false;
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
/*     */   public boolean modificarRegistro(int codigoCiclo, int codigoPlantilla, int codigoArea, String estado, String usuarioModificacion) {
/*     */     try {
/* 260 */       String s = "update cal_plantillas_areas set ";
/* 261 */       s = s + " estado='" + estado + "',";
/* 262 */       s = s + " procesado='N',";
/* 263 */       s = s + " fecha_modificacion=" + Utilidades.getFechaBD() + ",";
/* 264 */       s = s + " usuario_modificacion='" + usuarioModificacion + "'";
/* 265 */       s = s + " where ";
/* 266 */       s = s + " codigo_ciclo=" + codigoCiclo;
/* 267 */       s = s + " and codigo_plantilla=" + codigoPlantilla;
/* 268 */       s = s + " and codigo_area=" + codigoArea;
/* 269 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 272 */     catch (Exception e) {
/* 273 */       e.printStackTrace();
/* 274 */       Utilidades.writeError("CalPlantillasAreasFactory:modificarRegistro", e);
/*     */       
/* 276 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNombrePlantilla(int codigo) {
/*     */     try {
/* 285 */       boolean rta = this.dat.parseSql("select descripcion from cal_plantillas where codigo=" + codigo);
/* 286 */       if (!rta) return null; 
/* 287 */       this.rs = this.dat.getResultSet();
/* 288 */       if (this.rs.next()) {
/* 289 */         return this.rs.getString("descripcion");
/*     */       }
/*     */     }
/* 292 */     catch (SQLException e) {
/* 293 */       e.printStackTrace();
/*     */     } 
/* 295 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean cargarPlantillasCiclo(int codigoCiclo) {
/*     */     try {
/* 305 */       String s = "select resultado.codigo_ciclo,";
/* 306 */       s = s + "resultado.codigo_plantilla,cal_ciclos.descripcion as nombre_ciclo,cal_plantillas.descripcion as nombre_plantilla,";
/* 307 */       s = s + "resultado.numero_areas";
/* 308 */       s = s + " from (";
/* 309 */       s = s + "select codigo_ciclo,codigo_plantilla,count(0) as numero_areas from cal_plantillas_areas";
/* 310 */       s = s + " where codigo_ciclo=" + codigoCiclo;
/* 311 */       s = s + " and estado='A'";
/* 312 */       s = s + " group by codigo_ciclo,codigo_plantilla";
/* 313 */       s = s + ")";
/* 314 */       if (ParametrosDTO.getInt("tipoBaseDatos") == 1) {
/* 315 */         s = s + " as ";
/*     */       }
/* 317 */       s = s + " resultado";
/* 318 */       s = s + ",cal_ciclos,cal_plantillas";
/* 319 */       s = s + " where resultado.codigo_ciclo=cal_ciclos.ciclo";
/* 320 */       s = s + " and resultado.codigo_plantilla=cal_plantillas.codigo";
/*     */       
/* 322 */       boolean rtaDB = this.dat.parseSql(s);
/* 323 */       if (!rtaDB) {
/* 324 */         return false;
/*     */       }
/*     */       
/* 327 */       this.rs = this.dat.getResultSet();
/* 328 */       return true;
/*     */     }
/* 330 */     catch (Exception e) {
/* 331 */       e.printStackTrace();
/* 332 */       Utilidades.writeError("CalPlantillasAreasFactory:cargarTodos ", e);
/*     */       
/* 334 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalPlantillasAreasDTO leerRegistroPlantilla() {
/*     */     try {
/* 344 */       CalPlantillasAreasDTO reg = new CalPlantillasAreasDTO();
/* 345 */       reg.setCodigoCiclo(this.rs.getInt("codigo_ciclo"));
/* 346 */       reg.setCodigoPlantilla(this.rs.getInt("codigo_plantilla"));
/* 347 */       reg.setNombreCiclo(this.rs.getString("nombre_ciclo"));
/* 348 */       reg.setNombrePlantilla(this.rs.getString("nombre_plantilla"));
/* 349 */       reg.setNumeroAreas(this.rs.getInt("numero_areas"));
/* 350 */       return reg;
/*     */     }
/* 352 */     catch (Exception e) {
/* 353 */       e.printStackTrace();
/* 354 */       Utilidades.writeError("CalPlantillasAreasFactory:leerRegistro ", e);
/*     */       
/* 356 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalPlantillasAreasDTO nextPlantilla() {
/*     */     try {
/* 366 */       if (this.rs.next()) {
/* 367 */         return leerRegistroPlantilla();
/*     */       }
/*     */     }
/* 370 */     catch (Exception e) {
/* 371 */       e.printStackTrace();
/* 372 */       Utilidades.writeError("CalPlantillasAreasFactory:next ", e);
/*     */     } 
/* 374 */     return null;
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
/*     */   public boolean cargarTodos(int codigoCiclo) {
/*     */     try {
/* 387 */       String s = " select ";
/* 388 */       s = s + " cal_plantillas_areas.codigo_ciclo,cal_plantillas_areas.codigo_area,cal_plantillas_areas.codigo_plantilla,";
/* 389 */       s = s + " unidades_dependencia.descripcion as nombre_area,";
/* 390 */       s = s + " cal_plantillas.descripcion as nombre_plantilla,";
/* 391 */       s = s + " cal_plantillas_areas.estado";
/* 392 */       s = s + " from cal_plantillas_areas,unidades_dependencia,cal_plantillas";
/* 393 */       s = s + " where";
/* 394 */       s = s + " cal_plantillas_areas.codigo_area=unidades_dependencia.codigo";
/* 395 */       s = s + " and cal_plantillas_areas.codigo_plantilla=cal_plantillas.codigo";
/* 396 */       s = s + " and cal_plantillas_areas.codigo_ciclo=" + codigoCiclo;
/* 397 */       s = s + " order by unidades_dependencia.descripcion,cal_plantillas.descripcion";
/*     */       
/* 399 */       boolean rtaDB = this.dat.parseSql(s);
/* 400 */       if (!rtaDB) {
/* 401 */         return false;
/*     */       }
/*     */       
/* 404 */       this.rs = this.dat.getResultSet();
/* 405 */       return true;
/*     */     }
/* 407 */     catch (Exception e) {
/* 408 */       e.printStackTrace();
/* 409 */       Utilidades.writeError("CalPlantillasAreasFactory:cargarTodos ", e);
/*     */       
/* 411 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalPlantillasAreasDTO next3() {
/*     */     try {
/* 422 */       if (this.rs.next()) {
/* 423 */         return leerRegistro3();
/*     */       }
/*     */     }
/* 426 */     catch (Exception e) {
/* 427 */       e.printStackTrace();
/* 428 */       Utilidades.writeError("CalPlantillasAreasFactory:next3 ", e);
/*     */     } 
/* 430 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalPlantillasAreasDTO leerRegistro3() {
/*     */     try {
/* 440 */       CalPlantillasAreasDTO reg = new CalPlantillasAreasDTO();
/* 441 */       reg.setCodigoCiclo(this.rs.getInt("codigo_ciclo"));
/* 442 */       reg.setCodigoPlantilla(this.rs.getInt("codigo_plantilla"));
/* 443 */       reg.setCodigoArea(this.rs.getInt("codigo_area"));
/* 444 */       reg.setEstado(this.rs.getString("estado"));
/* 445 */       reg.setNombreArea(this.rs.getString("nombre_area"));
/* 446 */       reg.setNombrePlantilla(this.rs.getString("nombre_plantilla"));
/*     */       
/* 448 */       return reg;
/*     */     }
/* 450 */     catch (Exception e) {
/* 451 */       e.printStackTrace();
/* 452 */       Utilidades.writeError("CalPlantillasAreasFactory:leerRegistro3 ", e);
/*     */       
/* 454 */       return null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\CalPlantillasAreasDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */