/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.CalResponsablesDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.CalResponsablesDAO;
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
/*     */ public class CalResponsablesDAO
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
/*  44 */       Utilidades.writeError("CalResponsablesFactory:close", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalResponsablesDTO next() {
/*     */     try {
/*  55 */       if (this.rs.next()) {
/*  56 */         return leerRegistro();
/*     */       }
/*     */     }
/*  59 */     catch (Exception e) {
/*  60 */       e.printStackTrace();
/*  61 */       Utilidades.writeError("CalResponsablesFactory:next ", e);
/*     */     } 
/*  63 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalResponsablesDTO leerRegistro() {
/*     */     try {
/*  73 */       CalResponsablesDTO reg = new CalResponsablesDTO();
/*  74 */       reg.setCodigo(this.rs.getInt("codigo"));
/*  75 */       reg.setDescripcion(this.rs.getString("descripcion"));
/*  76 */       reg.setEstado(this.rs.getString("estado"));
/*  77 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  78 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  79 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  80 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  81 */       return reg;
/*     */     }
/*  83 */     catch (Exception e) {
/*  84 */       e.printStackTrace();
/*  85 */       Utilidades.writeError("CalResponsablesFactory:leerRegistro ", e);
/*     */       
/*  87 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean cargarTodos() {
/*     */     try {
/*  97 */       String s = "select * from cal_responsables order by descripcion";
/*  98 */       boolean rtaDB = this.dat.parseSql(s);
/*  99 */       if (!rtaDB) {
/* 100 */         return false;
/*     */       }
/*     */       
/* 103 */       this.rs = this.dat.getResultSet();
/* 104 */       return true;
/*     */     }
/* 106 */     catch (Exception e) {
/* 107 */       e.printStackTrace();
/* 108 */       Utilidades.writeError("CalResponsablesFactory:cargarTodos ", e);
/*     */       
/* 110 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalResponsablesDTO cargarCalResponsables(int codigo) {
/*     */     try {
/* 120 */       String s = "select * from cal_responsables where codigo=" + codigo;
/* 121 */       boolean rtaDB = this.dat.parseSql(s);
/* 122 */       if (!rtaDB) {
/* 123 */         return null;
/*     */       }
/*     */       
/* 126 */       this.rs = this.dat.getResultSet();
/* 127 */       if (this.rs.next()) {
/* 128 */         return leerRegistro();
/*     */       }
/*     */     }
/* 131 */     catch (Exception e) {
/* 132 */       e.printStackTrace();
/* 133 */       Utilidades.writeError("CalResponsablesFactory:cargarCalResponsables ", e);
/*     */     } 
/* 135 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int siguienteRegistro() {
/* 144 */     int numero = 1;
/* 145 */     String s = "select max(codigo) from cal_responsables";
/*     */     try {
/* 147 */       boolean rta = this.dat.parseSql(s);
/* 148 */       if (!rta) return 0; 
/* 149 */       this.rs = this.dat.getResultSet();
/* 150 */       if (this.rs.next()) {
/* 151 */         s = this.rs.getString(1);
/* 152 */         if (!this.rs.wasNull()) {
/* 153 */           numero = Integer.parseInt(s) + 1;
/*     */         }
/*     */       } 
/* 156 */       return numero;
/*     */     }
/* 158 */     catch (Exception e) {
/* 159 */       e.printStackTrace();
/* 160 */       Utilidades.writeError("CalResponsablesFactory:siguienteRegistro", e);
/*     */       
/* 162 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eliminarRegistro(int codigo) {
/*     */     try {
/* 172 */       String s = "delete from  cal_responsables where codigo=" + codigo;
/* 173 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 176 */     catch (Exception e) {
/* 177 */       e.printStackTrace();
/* 178 */       Utilidades.writeError("CalResponsablesFactory:eliminarRegistro", e);
/*     */       
/* 180 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean crearRegistro(String descripcion, String estado, String usuarioInsercion) {
/* 189 */     int elSiguiente = siguienteRegistro();
/* 190 */     if (elSiguiente == 0) {
/* 191 */       return false;
/*     */     }
/*     */     try {
/* 194 */       String s = "insert into cal_responsables ( codigo,descripcion,estado,fecha_insercion,usuario_insercion)";
/* 195 */       s = s + " values (";
/* 196 */       s = s + "" + elSiguiente + ",";
/* 197 */       s = s + "'" + descripcion + "',";
/* 198 */       s = s + "'" + estado + "',";
/* 199 */       s = s + "" + Utilidades.getFechaBD() + ",";
/* 200 */       s = s + "'" + usuarioInsercion + "'";
/* 201 */       s = s + ")";
/* 202 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 205 */     catch (Exception e) {
/* 206 */       e.printStackTrace();
/* 207 */       Utilidades.writeError("CalResponsablesFactory:crearRegistro", e);
/*     */       
/* 209 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean modificarRegistro(int codigo, String descripcion, String estado, String usuarioModificacion) {
/*     */     try {
/* 219 */       String s = "update cal_responsables set ";
/* 220 */       s = s + " descripcion='" + descripcion + "',";
/* 221 */       s = s + " estado='" + estado + "',";
/* 222 */       s = s + " fecha_modificacion=" + Utilidades.getFechaBD() + ",";
/* 223 */       s = s + " usuario_modificacion='" + usuarioModificacion + "'";
/* 224 */       s = s + " where ";
/* 225 */       s = s + " codigo=" + codigo;
/* 226 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 229 */     catch (Exception e) {
/* 230 */       e.printStackTrace();
/* 231 */       Utilidades.writeError("CalResponsablesFactory:modificarRegistro", e);
/*     */       
/* 233 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean cargarParecidas(String descripcion) {
/*     */     try {
/* 240 */       boolean rta = this.dat.parseSql("select * from view_responsables\twhere estado='A' and upper(descripcion) like upper('" + descripcion + "%') order by descripcion");
/* 241 */       if (!rta) return false; 
/* 242 */       this.rs = this.dat.getResultSet();
/* 243 */       return true;
/*     */     }
/* 245 */     catch (Exception e) {
/* 246 */       e.printStackTrace();
/*     */       
/* 248 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalResponsablesDTO next2() {
/*     */     try {
/* 258 */       if (this.rs.next()) {
/* 259 */         return leerRegistro2();
/*     */       }
/*     */     }
/* 262 */     catch (Exception e) {
/* 263 */       e.printStackTrace();
/* 264 */       Utilidades.writeError("CalResponsablesFactory:next ", e);
/*     */     } 
/* 266 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalResponsablesDTO leerRegistro2() {
/*     */     try {
/* 276 */       CalResponsablesDTO reg = new CalResponsablesDTO();
/* 277 */       reg.setCodigo(this.rs.getInt("codigo"));
/* 278 */       reg.setDescripcion(this.rs.getString("descripcion"));
/* 279 */       reg.setEstado(this.rs.getString("estado"));
/* 280 */       return reg;
/*     */     }
/* 282 */     catch (Exception e) {
/* 283 */       e.printStackTrace();
/* 284 */       Utilidades.writeError("CalResponsablesFactory:leerRegistro ", e);
/*     */       
/* 286 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<CalResponsablesDTO> cargarAreasCalidad() {
/* 296 */     Collection<CalResponsablesDTO> resultados = new ArrayList<CalResponsablesDTO>();
/*     */     try {
/* 298 */       String s = "select * from view_responsables  where estado='A' order by descripcion";
/*     */       
/* 300 */       boolean rtaDB = this.dat.parseSql(s);
/* 301 */       if (!rtaDB) {
/* 302 */         return resultados;
/*     */       }
/* 304 */       this.rs = this.dat.getResultSet();
/* 305 */       while (this.rs.next()) {
/* 306 */         CalResponsablesDTO reg = new CalResponsablesDTO();
/* 307 */         reg.setCodigo(this.rs.getInt("codigo"));
/* 308 */         reg.setDescripcion(this.rs.getString("descripcion"));
/* 309 */         reg.setEstado(this.rs.getString("estado"));
/* 310 */         resultados.add(reg);
/*     */       }
/*     */     
/* 313 */     } catch (Exception e) {
/* 314 */       e.printStackTrace();
/* 315 */       Utilidades.writeError("DocAsuntosDAO:cargarAreasCalidad", e);
/*     */     } 
/* 317 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<CalResponsablesDTO> cargarCargos() {
/* 327 */     Collection<CalResponsablesDTO> resultados = new ArrayList<CalResponsablesDTO>();
/*     */     try {
/* 329 */       String s = "select * from sis_cargos order by descripcion";
/*     */       
/* 331 */       boolean rtaDB = this.dat.parseSql(s);
/* 332 */       if (!rtaDB) {
/* 333 */         return resultados;
/*     */       }
/* 335 */       this.rs = this.dat.getResultSet();
/* 336 */       while (this.rs.next()) {
/* 337 */         CalResponsablesDTO reg = new CalResponsablesDTO();
/* 338 */         reg.setCodigoCargo(this.rs.getString("codigo"));
/* 339 */         reg.setDescripcion(this.rs.getString("descripcion"));
/* 340 */         resultados.add(reg);
/*     */       }
/*     */     
/* 343 */     } catch (Exception e) {
/* 344 */       e.printStackTrace();
/* 345 */       Utilidades.writeError("CalResponsablesDAO:cargarCargos", e);
/*     */     } 
/* 347 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<CalResponsablesDTO> cargarCargosMeta(int idMeta) {
/* 357 */     Collection<CalResponsablesDTO> resultados = new ArrayList<CalResponsablesDTO>();
/*     */     try {
/* 359 */       String s = "select c.codigo, c.descripcion from pde_metas_responsables p, sis_cargos c where c.codigo=p.codigo_cargo ";
/* 360 */       if (idMeta > 0) {
/* 361 */         s = s + "and id_meta=" + idMeta;
/*     */       }
/*     */       
/* 364 */       s = s + " order by descripcion";
/*     */       
/* 366 */       boolean rtaDB = this.dat.parseSql(s);
/* 367 */       if (!rtaDB) {
/* 368 */         return resultados;
/*     */       }
/* 370 */       this.rs = this.dat.getResultSet();
/* 371 */       while (this.rs.next()) {
/* 372 */         CalResponsablesDTO reg = new CalResponsablesDTO();
/* 373 */         reg.setCodigoCargo(this.rs.getString("codigo"));
/* 374 */         reg.setDescripcion(this.rs.getString("descripcion"));
/* 375 */         resultados.add(reg);
/*     */       }
/*     */     
/* 378 */     } catch (Exception e) {
/* 379 */       e.printStackTrace();
/* 380 */       Utilidades.writeError("CalResponsablesDAO:cargarCargosMeta", e);
/*     */     } 
/* 382 */     return resultados;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\CalResponsablesDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */