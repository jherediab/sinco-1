/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.TGeneralDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.TGeneralDAO;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TGeneralDAO
/*     */ {
/*     */   ResultSet rs;
/*  17 */   DBManager dat = new DBManager();
/*     */ 
/*     */   
/*  20 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*     */ 
/*     */   
/*     */   public void close() {
/*     */     try {
/*  25 */       this.dat.close();
/*     */     }
/*  27 */     catch (Exception e) {}
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public TGeneralDTO leerRegistro() {
/*     */     try {
/*  34 */       TGeneralDTO regTGeneral = new TGeneralDTO();
/*  35 */       regTGeneral.setCodigo(this.rs.getString(1));
/*  36 */       regTGeneral.setDescripcion(this.rs.getString(2));
/*  37 */       return regTGeneral;
/*     */     }
/*  39 */     catch (SQLException e) {
/*  40 */       e.printStackTrace();
/*     */       
/*  42 */       return null;
/*     */     } 
/*     */   }
/*     */   public TGeneralDTO next() {
/*     */     try {
/*  47 */       if (this.rs.next()) {
/*  48 */         return leerRegistro();
/*     */       }
/*     */       
/*  51 */       return null;
/*     */     
/*     */     }
/*  54 */     catch (Exception e) {
/*  55 */       e.printStackTrace();
/*     */       
/*  57 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean cargarTodos(String tabla, String codigo, String nombre) {
/*     */     try {
/*  67 */       boolean rta = this.dat.parseSql("select " + codigo + ',' + nombre + " from " + tabla + " order by " + nombre);
/*  68 */       if (!rta) return false; 
/*  69 */       this.rs = this.dat.getResultSet();
/*  70 */       return true;
/*     */     }
/*  72 */     catch (Exception e) {
/*  73 */       e.printStackTrace();
/*     */       
/*  75 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean executeQuery(String cmd) {
/*     */     try {
/*  84 */       boolean rta = this.dat.parseSql(cmd);
/*  85 */       if (!rta) return false; 
/*  86 */       this.rs = this.dat.getResultSet();
/*  87 */       return true;
/*     */     }
/*  89 */     catch (Exception e) {
/*  90 */       e.printStackTrace();
/*     */       
/*  92 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean cargarTodos(String tabla, String codigo, String nombre, String where) {
/*     */     try {
/* 100 */       boolean rta = this.dat.parseSql("select " + codigo + ',' + nombre + " from " + tabla + " where " + where);
/* 101 */       if (!rta) return false; 
/* 102 */       this.rs = this.dat.getResultSet();
/* 103 */       return true;
/*     */     }
/* 105 */     catch (Exception e) {
/* 106 */       e.printStackTrace();
/*     */       
/* 108 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean cargarUnion(String tabla, String codigo, String nombre, String condicion) {
/*     */     try {
/* 116 */       String s = "select 0 as " + codigo + ",'Todos los valores' as " + nombre + " union ";
/* 117 */       s = s + "select " + codigo + ',' + nombre + " from " + tabla + " where " + condicion;
/* 118 */       boolean rta = this.dat.parseSql(s);
/* 119 */       if (!rta) return false; 
/* 120 */       this.rs = this.dat.getResultSet();
/* 121 */       return true;
/*     */     }
/* 123 */     catch (Exception e) {
/* 124 */       e.printStackTrace();
/*     */       
/* 126 */       return false;
/*     */     } 
/*     */   }
/*     */   public boolean cargarUnionString(String tabla, String codigo, String nombre, String condicion) {
/*     */     try {
/* 131 */       String s = "select '0' as " + codigo + ",'Todos los valores' as " + nombre + " union ";
/* 132 */       s = s + "select " + codigo + ',' + nombre + " from " + tabla + " where " + condicion;
/* 133 */       boolean rta = this.dat.parseSql(s);
/* 134 */       if (!rta) return false; 
/* 135 */       this.rs = this.dat.getResultSet();
/* 136 */       return true;
/*     */     }
/* 138 */     catch (Exception e) {
/* 139 */       e.printStackTrace();
/*     */       
/* 141 */       return false;
/*     */     } 
/*     */   }
/*     */   public TGeneralDTO get(String tabla, String codigo, String nombre, int valor) {
/*     */     try {
/* 146 */       boolean rta = this.dat.parseSql("select " + codigo + ',' + nombre + " from " + tabla + " where " + codigo + "=" + valor);
/* 147 */       if (!rta) return null; 
/* 148 */       this.rs = this.dat.getResultSet();
/* 149 */       if (this.rs.next()) {
/* 150 */         return leerRegistro();
/*     */       }
/*     */     }
/* 153 */     catch (SQLException e) {
/* 154 */       e.printStackTrace();
/*     */     } 
/* 156 */     return null;
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
/*     */   public Collection<TGeneralDTO> cargarTabla(String tabla, String codigo, String nombre, String where) {
/* 173 */     Collection<TGeneralDTO> resultados = new ArrayList<TGeneralDTO>();
/*     */     try {
/* 175 */       String s = "select " + codigo + " as codigo ," + nombre + " as nombre" + " from " + tabla + " where " + where + " order by 2";
/*     */ 
/*     */ 
/*     */       
/* 179 */       boolean rtaDB = this.dat.parseSql(s);
/* 180 */       if (!rtaDB) {
/* 181 */         return resultados;
/*     */       }
/* 183 */       this.rs = this.dat.getResultSet();
/*     */       
/* 185 */       boolean rta = this.dat.parseSql(s);
/* 186 */       if (!rta) {
/* 187 */         return resultados;
/*     */       }
/* 189 */       this.rs = this.dat.getResultSet();
/* 190 */       while (this.rs.next()) {
/* 191 */         TGeneralDTO reg = new TGeneralDTO();
/* 192 */         reg.setCodigo(this.rs.getString("codigo"));
/* 193 */         reg.setDescripcion(this.rs.getString("nombre"));
/* 194 */         resultados.add(reg);
/*     */       }
/*     */     
/* 197 */     } catch (Exception e) {
/* 198 */       e.printStackTrace();
/*     */     } 
/* 200 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<TGeneralDTO> cargarResponsableAsunto(int asunto) {
/* 209 */     String s = " select  p.codigo_empleado as codigo,  p.apellidos||' ' ||p.nombres as nombre  from doc_asuntos_responsable a,       sis_usuarios p  where  a.responsable=p.codigo_empleado     and p.ESTADO='A'     and a.asunto=" + asunto + " order by p.apellidos||' ' ||p.nombres ";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 219 */     Collection<TGeneralDTO> resultados = new ArrayList<TGeneralDTO>();
/*     */     try {
/* 221 */       boolean rta = this.dat.parseSql(s);
/* 222 */       if (!rta) {
/* 223 */         return resultados;
/*     */       }
/* 225 */       this.rs = this.dat.getResultSet();
/* 226 */       while (this.rs.next()) {
/* 227 */         TGeneralDTO reg = new TGeneralDTO();
/* 228 */         reg.setCodigo(this.rs.getString("codigo"));
/* 229 */         reg.setDescripcion(this.rs.getString("nombre"));
/* 230 */         resultados.add(reg);
/*     */       }
/*     */     
/* 233 */     } catch (Exception e) {
/* 234 */       e.printStackTrace();
/*     */     } 
/* 236 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<TGeneralDTO> cargarAsuntos(int area) {
/* 245 */     String s = "select asunto,descripcion from doc_asuntos a where a.area_destinatario=" + area + " order by a.descripcion";
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 250 */     Collection<TGeneralDTO> resultados = new ArrayList<TGeneralDTO>();
/*     */     try {
/* 252 */       boolean rta = this.dat.parseSql(s);
/* 253 */       if (!rta) {
/* 254 */         return resultados;
/*     */       }
/* 256 */       this.rs = this.dat.getResultSet();
/* 257 */       while (this.rs.next()) {
/* 258 */         TGeneralDTO reg = new TGeneralDTO();
/* 259 */         reg.setCodigo(this.rs.getString("asunto"));
/* 260 */         reg.setDescripcion(this.rs.getString("descripcion"));
/* 261 */         resultados.add(reg);
/*     */       }
/*     */     
/* 264 */     } catch (Exception e) {
/* 265 */       e.printStackTrace();
/* 266 */       Utilidades.writeError("cargarAsuntos", e);
/*     */     } 
/* 268 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<TGeneralDTO> cargarDocumentos() {
/* 278 */     String s = "select t.codigo,p.descripcion||' - ' ||t.descripcion descripcion  from cal_documentos t , procesos p where  t.proceso=p.codigo and coalesce(t.estado,'A') NOT IN ('I') order by 2";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 284 */     Collection<TGeneralDTO> resultados = new ArrayList<TGeneralDTO>();
/*     */     try {
/* 286 */       boolean rta = this.dat.parseSql(s);
/* 287 */       if (!rta) {
/* 288 */         return resultados;
/*     */       }
/* 290 */       this.rs = this.dat.getResultSet();
/* 291 */       while (this.rs.next()) {
/* 292 */         TGeneralDTO reg = new TGeneralDTO();
/* 293 */         reg.setCodigo(this.rs.getString("codigo"));
/* 294 */         reg.setDescripcion(this.rs.getString("descripcion"));
/* 295 */         resultados.add(reg);
/*     */       }
/*     */     
/* 298 */     } catch (Exception e) {
/* 299 */       e.printStackTrace();
/* 300 */       Utilidades.writeError("cargarDocumentos", e);
/*     */     } 
/* 302 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TGeneralDTO cargarDocumento(String codigo) {
/* 313 */     String s = "select d.nombre_archivo_word,d.nombre_archivo_pdf from cal_documentos d where d.codigo='" + codigo + "'";
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 318 */       boolean rta = this.dat.parseSql(s);
/* 319 */       if (!rta) {
/* 320 */         return null;
/*     */       }
/* 322 */       this.rs = this.dat.getResultSet();
/* 323 */       if (this.rs.next()) {
/* 324 */         TGeneralDTO reg = new TGeneralDTO();
/* 325 */         reg.setCodigo(this.rs.getString("nombre_archivo_word"));
/* 326 */         reg.setDescripcion(this.rs.getString("nombre_archivo_pdf"));
/* 327 */         return reg;
/*     */       }
/*     */     
/* 330 */     } catch (Exception e) {
/* 331 */       e.printStackTrace();
/* 332 */       Utilidades.writeError("cargarDocumento", e);
/*     */     } 
/* 334 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<TGeneralDTO> cargarTodosArray(String tabla, String codigo, String nombre, String where) {
/* 343 */     Collection<TGeneralDTO> resultados = new ArrayList<TGeneralDTO>();
/*     */     try {
/* 345 */       String s = "select " + codigo + " as codigo ," + nombre + " as nombre" + " from " + tabla + " where " + where;
/*     */ 
/*     */       
/* 348 */       boolean rtaDB = this.dat.parseSql(s);
/* 349 */       if (!rtaDB) {
/* 350 */         return resultados;
/*     */       }
/* 352 */       this.rs = this.dat.getResultSet();
/*     */       
/* 354 */       boolean rta = this.dat.parseSql(s);
/* 355 */       if (!rta) {
/* 356 */         return resultados;
/*     */       }
/* 358 */       this.rs = this.dat.getResultSet();
/* 359 */       while (this.rs.next()) {
/* 360 */         TGeneralDTO reg = new TGeneralDTO();
/* 361 */         reg.setCodigo(this.rs.getString("codigo"));
/* 362 */         reg.setDescripcion(this.rs.getString("nombre"));
/* 363 */         resultados.add(reg);
/*     */       }
/*     */     
/* 366 */     } catch (Exception e) {
/* 367 */       e.printStackTrace();
/*     */     } 
/* 369 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<TGeneralDTO> cargarAmOrigen(int area) {
/* 377 */     Collection<TGeneralDTO> resultados = new ArrayList<TGeneralDTO>();
/*     */     try {
/* 379 */       String s = " select o.codigo,o.descripcion,o.captura_numeral from   am_origen o where  o.estado='A'";
/*     */ 
/*     */       
/* 382 */       s = s + " order by o.descripcion";
/* 383 */       boolean rtaDB = this.dat.parseSql(s);
/* 384 */       if (!rtaDB) {
/* 385 */         return resultados;
/*     */       }
/* 387 */       this.rs = this.dat.getResultSet();
/* 388 */       while (this.rs.next()) {
/* 389 */         TGeneralDTO reg = new TGeneralDTO();
/* 390 */         reg.setCodigo(this.rs.getString("codigo"));
/* 391 */         reg.setDescripcion(this.rs.getString("descripcion"));
/* 392 */         reg.setCapturaNumeral(this.rs.getString("captura_numeral"));
/* 393 */         resultados.add(reg);
/*     */       }
/*     */     
/* 396 */     } catch (Exception e) {
/* 397 */       Utilidades.writeError("", e);
/* 398 */       e.printStackTrace();
/*     */     } 
/* 400 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<TGeneralDTO> cargarTodosArr(String tabla, String codigo, String nombre, String where) {
/* 409 */     Collection<TGeneralDTO> resultados = new ArrayList<TGeneralDTO>();
/*     */     try {
/* 411 */       boolean rtaDB = this.dat.parseSql("select " + codigo + ',' + nombre + " from " + tabla + " where " + where);
/*     */       
/* 413 */       if (!rtaDB) {
/* 414 */         return resultados;
/*     */       }
/* 416 */       this.rs = this.dat.getResultSet();
/* 417 */       while (this.rs.next()) {
/* 418 */         resultados.add(leerRegistro());
/*     */       }
/*     */       
/* 421 */       if (!rtaDB) return resultados; 
/* 422 */       this.rs = this.dat.getResultSet();
/* 423 */       return resultados;
/*     */     }
/* 425 */     catch (Exception e) {
/* 426 */       e.printStackTrace();
/* 427 */       Utilidades.writeError("cargarTodos", e);
/*     */       
/* 429 */       return resultados;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\TGeneralDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */