/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.CalPlantillasDTO;
/*     */ import sinco.business.CalPlantillasObjetivosDTO;
/*     */ import sinco.business.ParametrosDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.CalPlantillasObjetivosDAO;
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
/*     */ public class CalPlantillasObjetivosDAO
/*     */ {
/*     */   ResultSet rs;
/*  27 */   DBManager dat = new DBManager();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  36 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/*     */     try {
/*  43 */       this.dat.close();
/*     */     }
/*  45 */     catch (Exception e) {
/*  46 */       Utilidades.writeError("CalPlantillasObjetivosFactory:close", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalPlantillasObjetivosDTO next() {
/*     */     try {
/*  57 */       if (this.rs.next()) {
/*  58 */         return leerRegistro();
/*     */       }
/*     */     }
/*  61 */     catch (Exception e) {
/*  62 */       e.printStackTrace();
/*  63 */       Utilidades.writeError("CalPlantillasObjetivosFactory:next ", e);
/*     */     } 
/*  65 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalPlantillasObjetivosDTO leerRegistro() {
/*     */     try {
/*  75 */       CalPlantillasObjetivosDTO reg = new CalPlantillasObjetivosDTO();
/*     */       
/*  77 */       reg.setCodigoObjetivo(this.rs.getInt("codigo_objetivo"));
/*  78 */       reg.setTipoObjetivo(this.rs.getString("tipo_objetivo"));
/*  79 */       reg.setNombreObjetivo(this.rs.getString("nombre_objetivo"));
/*  80 */       reg.setNombreProceso(this.rs.getString("nombre_proceso"));
/*  81 */       reg.setNombreSubproceso(this.rs.getString("nombre_subproceso"));
/*  82 */       reg.setExiste(this.rs.getString("existe"));
/*  83 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  84 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  85 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  86 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  87 */       return reg;
/*     */     }
/*  89 */     catch (Exception e) {
/*  90 */       e.printStackTrace();
/*  91 */       Utilidades.writeError("CalPlantillasObjetivosFactory:leerRegistro ", e);
/*     */       
/*  93 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean cargarTodos(int codigoPlantilla, String proceso, String subproceso, String tipoObjetivo) {
/*     */     try {
/* 104 */       String s = "select codigo_objetivo,existe,fecha_insercion,usuario_insercion,fecha_modificacion,usuario_modificacion,nombre_objetivo,";
/* 105 */       s = s + "tipo_objetivo,nombre_proceso,nombre_subproceso from (";
/* 106 */       s = s + "select cal_plantillas_objetivos.codigo_objetivo,";
/* 107 */       s = s + "'X' as existe,";
/* 108 */       s = s + "cal_plantillas_objetivos.fecha_insercion,";
/* 109 */       s = s + "cal_plantillas_objetivos.usuario_insercion,";
/* 110 */       s = s + "cal_plantillas_objetivos.fecha_modificacion,";
/* 111 */       s = s + "cal_plantillas_objetivos.usuario_modificacion,";
/* 112 */       s = s + "cal_objetivos.descripcion as nombre_objetivo,";
/* 113 */       s = s + "cal_objetivos.tipo_objetivo,";
/* 114 */       s = s + "procesos.descripcion as nombre_proceso,";
/* 115 */       s = s + "subprocesos.descripcion as nombre_subproceso";
/* 116 */       s = s + " from cal_plantillas_objetivos,cal_objetivos,procesos,subprocesos";
/* 117 */       s = s + " where cal_plantillas_objetivos.codigo_objetivo=cal_objetivos.codigo";
/* 118 */       s = s + " and cal_objetivos.proceso=procesos.codigo";
/* 119 */       s = s + " and cal_objetivos.proceso=subprocesos.proceso";
/* 120 */       s = s + " and cal_objetivos.subproceso=subprocesos.codigo";
/* 121 */       s = s + " and cal_plantillas_objetivos.codigo_plantilla=" + codigoPlantilla;
/* 122 */       s = s + " and cal_plantillas_objetivos.estado='A'";
/* 123 */       s = s + " and cal_objetivos.estado='A'";
/* 124 */       s = s + " union ";
/* 125 */       s = s + "select cal_objetivos.codigo as codigo_objetivo,";
/* 126 */       s = s + "' ' as existe,";
/* 127 */       s = s + "cal_objetivos.fecha_insercion,";
/* 128 */       s = s + "cal_objetivos.usuario_insercion,";
/* 129 */       s = s + "cal_objetivos.fecha_modificacion,";
/* 130 */       s = s + "cal_objetivos.usuario_modificacion,";
/* 131 */       s = s + "cal_objetivos.descripcion as nombre_objetivo,";
/* 132 */       s = s + "cal_objetivos.tipo_objetivo,";
/* 133 */       s = s + "procesos.descripcion as nombre_proceso,";
/* 134 */       s = s + "subprocesos.descripcion as nombre_subproceso";
/* 135 */       s = s + " from cal_objetivos,procesos,subprocesos";
/* 136 */       s = s + " where cal_objetivos.proceso=procesos.codigo";
/* 137 */       s = s + " and cal_objetivos.proceso=subprocesos.proceso";
/* 138 */       s = s + " and cal_objetivos.subproceso=subprocesos.codigo";
/* 139 */       s = s + " and cal_objetivos.estado='A'";
/* 140 */       if (!proceso.equals("X")) {
/* 141 */         s = s + " and cal_objetivos.proceso='" + proceso + "'";
/*     */       }
/* 143 */       if (!subproceso.equals("X")) {
/* 144 */         s = s + " and cal_objetivos.subproceso='" + subproceso + "'";
/*     */       }
/* 146 */       if (!tipoObjetivo.equals("X")) {
/* 147 */         s = s + " and cal_objetivos.tipo_objetivo='" + tipoObjetivo + "'";
/*     */       }
/* 149 */       s = s + " and cal_objetivos.codigo not in (";
/* 150 */       s = s + "select cal_plantillas_objetivos.codigo_objetivo";
/* 151 */       s = s + " from cal_plantillas_objetivos,cal_objetivos,procesos,subprocesos";
/* 152 */       s = s + " where cal_plantillas_objetivos.codigo_objetivo=cal_objetivos.codigo";
/* 153 */       s = s + " and cal_objetivos.proceso=procesos.codigo";
/* 154 */       s = s + " and cal_objetivos.proceso=subprocesos.proceso";
/* 155 */       s = s + " and cal_objetivos.subproceso=subprocesos.codigo";
/* 156 */       s = s + " and cal_plantillas_objetivos.codigo_plantilla=" + codigoPlantilla;
/* 157 */       s = s + " and cal_plantillas_objetivos.estado='A'";
/* 158 */       s = s + ")";
/* 159 */       s = s + ")";
/* 160 */       if (ParametrosDTO.getInt("tipoBaseDatos") == 1) {
/* 161 */         s = s + " as resultado";
/*     */       }
/* 163 */       s = s + " order by existe desc,nombre_proceso,nombre_subproceso ,nombre_objetivo";
/* 164 */       boolean rtaDB = this.dat.parseSql(s);
/* 165 */       if (!rtaDB) {
/* 166 */         return false;
/*     */       }
/* 168 */       this.rs = this.dat.getResultSet();
/*     */       
/* 170 */       return true;
/*     */     }
/* 172 */     catch (Exception e) {
/* 173 */       e.printStackTrace();
/* 174 */       Utilidades.writeError("CalPlantillasObjetivosFactory:cargarTodos ", e);
/*     */       
/* 176 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalPlantillasObjetivosDTO cargarRegistro(int codigoPlantilla, int codigoObjetivo) {
/*     */     try {
/* 186 */       String s = "select cal_plantillas_objetivos.* ,cal_objetivos.descripcion as nombre_objetivo,";
/* 187 */       s = s + "procesos.descripcion as nombre_proceso,";
/* 188 */       s = s + " subprocesos.descripcion as nombre_subproceso";
/* 189 */       s = s + " from cal_plantillas_objetivos,cal_objetivos,procesos,subprocesos";
/* 190 */       s = s + " where cal_plantillas_objetivos.codigo_objetivo=cal_objetivos.codigo";
/* 191 */       s = s + " and cal_objetivos.proceso=procesos.codigo";
/* 192 */       s = s + " and cal_objetivos.proceso=subprocesos.proceso";
/* 193 */       s = s + " and cal_objetivos.subproceso=subprocesos.codigo";
/* 194 */       s = s + " and cal_plantillas_objetivos.codigo_plantilla=" + codigoPlantilla;
/* 195 */       s = s + " and cal_plantillas_objetivos.codigo_objetivo=" + codigoObjetivo;
/* 196 */       boolean rtaDB = this.dat.parseSql(s);
/* 197 */       if (!rtaDB) {
/* 198 */         return null;
/*     */       }
/*     */       
/* 201 */       this.rs = this.dat.getResultSet();
/* 202 */       if (this.rs.next()) {
/* 203 */         return leerRegistro();
/*     */       }
/*     */     }
/* 206 */     catch (Exception e) {
/* 207 */       e.printStackTrace();
/* 208 */       Utilidades.writeError("CalPlantillasObjetivosFactory:cargarCalPlantillasObjetivos ", e);
/*     */     } 
/* 210 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eliminarRegistro(int codigoPlantilla, String usuario) {
/*     */     try {
/* 222 */       String s = "update cal_plantillas_objetivos set estado='E',";
/* 223 */       s = s + "fecha_modificacion=" + Utilidades.getFechaBD() + ",";
/* 224 */       s = s + "usuario_modificacion='" + usuario + "'";
/* 225 */       s = s + " where codigo_plantilla=" + codigoPlantilla;
/* 226 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 229 */     catch (Exception e) {
/* 230 */       e.printStackTrace();
/* 231 */       Utilidades.writeError("CalPlantillasObjetivosFactory:eliminarRegistro", e);
/*     */       
/* 233 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean crearRegistro(int codigoPlantilla, int codigoObjetivo, String usuarioInsercion) {
/* 243 */     boolean existe = false;
/*     */     try {
/* 245 */       String s = "select cal_plantillas_objetivos.*";
/* 246 */       s = s + " from cal_plantillas_objetivos";
/* 247 */       s = s + " where cal_plantillas_objetivos.codigo_plantilla=" + codigoPlantilla;
/* 248 */       s = s + " and cal_plantillas_objetivos.codigo_objetivo=" + codigoObjetivo;
/* 249 */       boolean rtaDB = this.dat.parseSql(s);
/* 250 */       if (!rtaDB) {
/* 251 */         return false;
/*     */       }
/*     */       
/* 254 */       this.rs = this.dat.getResultSet();
/* 255 */       if (this.rs.next()) {
/* 256 */         existe = true;
/*     */       }
/*     */     }
/* 259 */     catch (Exception e) {
/* 260 */       e.printStackTrace();
/* 261 */       Utilidades.writeError("CalPlantillasObjetivosFactory:cargarCalPlantillasObjetivos ", e);
/*     */     } 
/*     */     
/* 264 */     if (!existe) {
/*     */       try {
/* 266 */         String s = "insert into cal_plantillas_objetivos (codigo_plantilla,codigo_objetivo,estado,procesado,fecha_insercion,usuario_insercion)";
/* 267 */         s = s + " values (";
/* 268 */         s = s + "'" + codigoPlantilla + "',";
/* 269 */         s = s + "" + codigoObjetivo + ",";
/* 270 */         s = s + "'A',";
/* 271 */         s = s + "'N',";
/* 272 */         s = s + "" + Utilidades.getFechaBD() + ",";
/* 273 */         s = s + "'" + usuarioInsercion + "'";
/* 274 */         s = s + ")";
/* 275 */         return this.dat.executeUpdate(s);
/*     */       
/*     */       }
/* 278 */       catch (Exception e) {
/* 279 */         e.printStackTrace();
/* 280 */         Utilidades.writeError("CalPlantillasObjetivosFactory:crearRegistro", e);
/*     */         
/* 282 */         return false;
/*     */       } 
/*     */     }
/* 285 */     return modificarRegistro(codigoPlantilla, codigoObjetivo, usuarioInsercion);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean modificarRegistro(int codigoPlantilla, int codigoObjetivo, String usuarioModificacion) {
/*     */     try {
/* 296 */       String s = "update cal_plantillas_objetivos set ";
/* 297 */       s = s + " fecha_modificacion=" + Utilidades.getFechaBD() + ",";
/* 298 */       s = s + " usuario_modificacion='" + usuarioModificacion + "',";
/* 299 */       s = s + " estado='A',";
/* 300 */       s = s + " procesado='N'";
/* 301 */       s = s + " where ";
/* 302 */       s = s + " codigo_plantilla=" + codigoPlantilla;
/* 303 */       s = s + " and codigo_objetivo=" + codigoObjetivo;
/* 304 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 307 */     catch (Exception e) {
/* 308 */       e.printStackTrace();
/* 309 */       Utilidades.writeError("CalPlantillasObjetivosFactory:modificarRegistro", e);
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
/*     */   public CalPlantillasObjetivosDTO nextPlantilla() {
/*     */     try {
/* 323 */       if (this.rs.next()) {
/* 324 */         return leerRegistroPlantilla();
/*     */       }
/*     */     }
/* 327 */     catch (Exception e) {
/* 328 */       e.printStackTrace();
/* 329 */       Utilidades.writeError("CalPlantillasObjetivosFactory:next ", e);
/*     */     } 
/* 331 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalPlantillasObjetivosDTO leerRegistroPlantilla() {
/*     */     try {
/* 341 */       CalPlantillasObjetivosDTO reg = new CalPlantillasObjetivosDTO();
/* 342 */       reg.setCodigoPlantilla(this.rs.getInt("codigo_plantilla"));
/* 343 */       reg.setNombrePlantilla(this.rs.getString("nombre_plantilla"));
/* 344 */       return reg;
/*     */     }
/* 346 */     catch (Exception e) {
/* 347 */       e.printStackTrace();
/* 348 */       Utilidades.writeError("CalPlantillasObjetivosFactory:leerRegistro ", e);
/*     */       
/* 350 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean cargarPlantillas() {
/*     */     try {
/* 360 */       String s = "select codigo as codigo_plantilla,descripcion as nombre_plantilla from cal_plantillas order by descripcion";
/* 361 */       boolean rtaDB = this.dat.parseSql(s);
/* 362 */       if (!rtaDB) {
/* 363 */         return false;
/*     */       }
/*     */       
/* 366 */       this.rs = this.dat.getResultSet();
/* 367 */       return true;
/*     */     }
/* 369 */     catch (Exception e) {
/* 370 */       e.printStackTrace();
/* 371 */       Utilidades.writeError("CalPlantillasObjetivosFactory:cargarPlantillas", e);
/*     */       
/* 373 */       return false;
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
/*     */   public Collection<CalPlantillasDTO> cargarPlantillasObjetivos(int codigoObjetivo) {
/* 385 */     Collection<CalPlantillasDTO> resultados = new ArrayList<CalPlantillasDTO>();
/*     */ 
/*     */     
/*     */     try {
/* 389 */       String s = "select p.codigo,        p.descripcion,        p.estado,        'S' existe from   cal_plantillas_objetivos po,        cal_plantillas           p where  p.codigo = po.codigo_plantilla        and po.codigo_objetivo = " + codigoObjetivo + "        and po.estado='A'" + " union" + " " + " select p.codigo," + "        p.descripcion," + "        p.estado," + "        'N' existe" + " from   cal_plantillas p" + " where  " + " p.estado='A'" + " and p.codigo not in (select po.codigo_plantilla " + " from " + " cal_plantillas_objetivos po" + "   where  po.estado='A'" + "          and po.codigo_objetivo = " + codigoObjetivo + ")" + " order by existe desc,codigo";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 413 */       boolean rtaDB = this.dat.parseSql(s);
/* 414 */       if (!rtaDB) {
/* 415 */         return resultados;
/*     */       }
/*     */       
/* 418 */       this.rs = this.dat.getResultSet();
/* 419 */       while (this.rs.next()) {
/* 420 */         CalPlantillasDTO reg = new CalPlantillasDTO();
/* 421 */         reg.setCodigo(this.rs.getInt("codigo"));
/* 422 */         reg.setDescripcion(this.rs.getString("DESCRIPCION"));
/* 423 */         reg.setEstado(this.rs.getString("estado"));
/* 424 */         reg.setExiste(this.rs.getString("existe"));
/* 425 */         resultados.add(reg);
/*     */       }
/*     */     
/* 428 */     } catch (Exception e) {
/* 429 */       e.printStackTrace();
/* 430 */       Utilidades.writeError("CalPlantillasObjetivosFactory:cargarPlantillas", e);
/*     */     } 
/* 432 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eliminarPlantilla(int codigoObjetivo, String usuario) {
/*     */     try {
/* 444 */       String s = "update cal_plantillas_objetivos set estado='E',";
/* 445 */       s = s + "fecha_modificacion=" + Utilidades.getFechaBD() + ",";
/* 446 */       s = s + "usuario_modificacion='" + usuario + "'";
/* 447 */       s = s + " where codigo_objetivo=" + codigoObjetivo;
/* 448 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 451 */     catch (Exception e) {
/* 452 */       e.printStackTrace();
/* 453 */       Utilidades.writeError("CalPlantillasObjetivosFactory:eliminarRegistro", e);
/*     */       
/* 455 */       return false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\CalPlantillasObjetivosDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */