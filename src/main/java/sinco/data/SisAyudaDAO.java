/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.SisAyudaDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.SisAyudaDAO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SisAyudaDAO
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
/*  49 */       Utilidades.writeError("SisAyudaDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SisAyudaDTO next() {
/*     */     try {
/*  60 */       if (this.rs.next()) {
/*  61 */         return leerRegistro();
/*     */       }
/*     */     }
/*  64 */     catch (Exception e) {
/*  65 */       e.printStackTrace();
/*  66 */       Utilidades.writeError("SisAyudaDAO:next ", e);
/*     */     } 
/*  68 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SisAyudaDTO leerRegistro() {
/*     */     try {
/*  78 */       SisAyudaDTO reg = new SisAyudaDTO();
/*     */       
/*  80 */       reg.setAplicacion(this.rs.getString("aplicacion"));
/*  81 */       reg.setPantalla(this.rs.getString("pantalla"));
/*  82 */       reg.setDescripcion(this.rs.getString("descripcion"));
/*  83 */       reg.setArchivoAnexo(this.rs.getString("archivo_anexo"));
/*  84 */       reg.setTamanoAnexo(this.rs.getInt("tamano_anexo"));
/*  85 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  86 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  87 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  88 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  89 */       return reg;
/*     */     }
/*  91 */     catch (Exception e) {
/*  92 */       e.printStackTrace();
/*  93 */       Utilidades.writeError("SisAyudaDAO:leerRegistro ", e);
/*     */       
/*  95 */       return null;
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
/*     */   public Collection cargarTodos(String aplicacion, String pantalla, String descripcion) {
/* 107 */     Collection resultados = new ArrayList();
/*     */     try {
/* 109 */       String s = "select t.aplicacion,t.pantalla,t.descripcion,t.archivo_anexo,t.tamano_anexo,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion from SIS_AYUDAS t  where 1=1";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 121 */       if (aplicacion.length() > 0) {
/* 122 */         s = s + " and upper(t.aplicacion) like upper('%" + aplicacion + "%')";
/*     */       }
/* 124 */       if (pantalla.length() > 0) {
/* 125 */         s = s + " and upper(t.pantalla) like upper('%" + pantalla + "%')";
/*     */       }
/* 127 */       if (descripcion.length() > 0) {
/* 128 */         s = s + " and upper(t.descripcion) like upper('%" + descripcion + "%')";
/*     */       }
/* 130 */       s = s + " order by 1";
/* 131 */       boolean rtaDB = this.dat.parseSql(s);
/* 132 */       if (!rtaDB) {
/* 133 */         return resultados;
/*     */       }
/* 135 */       this.rs = this.dat.getResultSet();
/* 136 */       while (this.rs.next()) {
/* 137 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 140 */     catch (Exception e) {
/* 141 */       e.printStackTrace();
/* 142 */       Utilidades.writeError("SisAyudaDAO:cargarTodos ", e);
/*     */     } 
/* 144 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SisAyudaDTO cargarRegistro(String aplicacion, String pantalla) {
/*     */     try {
/* 155 */       String s = "select t.aplicacion,t.pantalla,t.descripcion,t.archivo_anexo,t.tamano_anexo,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion from SIS_AYUDAS t  where  t.aplicacion='" + aplicacion + "'" + " and t.pantalla='" + pantalla + "'" + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 170 */       boolean rtaDB = this.dat.parseSql(s);
/* 171 */       if (!rtaDB) {
/* 172 */         return null;
/*     */       }
/* 174 */       this.rs = this.dat.getResultSet();
/* 175 */       if (this.rs.next()) {
/* 176 */         return leerRegistro();
/*     */       }
/*     */     }
/* 179 */     catch (Exception e) {
/* 180 */       e.printStackTrace();
/* 181 */       Utilidades.writeError("SisAyudaDAO:cargarSisAyuda", e);
/*     */     } 
/* 183 */     return null;
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
/*     */   public boolean eliminarRegistro(String aplicacion, String pantalla) {
/*     */     try {
/* 197 */       String s = "delete from SIS_AYUDAS where  aplicacion='" + aplicacion + "'" + "  and pantalla='" + pantalla + "'" + "";
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 202 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 205 */     catch (Exception e) {
/* 206 */       e.printStackTrace();
/* 207 */       Utilidades.writeError("SisAyudaDAO:eliminarRegistro ", e);
/*     */       
/* 209 */       return false;
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
/*     */   public boolean crearRegistro(String aplicacion, String pantalla, String descripcion, String usuarioInsercion) {
/*     */     try {
/* 224 */       String s = "insert into SIS_AYUDAS(aplicacion,pantalla,descripcion,usuario_insercion,fecha_insercion) values ('" + aplicacion + "'," + "'" + pantalla + "'," + "'" + descripcion + "'," + "'" + usuarioInsercion + "'," + "" + Utilidades.getFechaBD() + "" + ")";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 237 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 240 */     catch (Exception e) {
/* 241 */       e.printStackTrace();
/* 242 */       Utilidades.writeError("%SisAyudaDAO:crearRegistro ", e);
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
/*     */   public boolean modificarRegistro(String aplicacion, String pantalla, String descripcion, String usuarioModificacion) {
/*     */     try {
/* 259 */       String s = "update SIS_AYUDAS set  descripcion='" + descripcion + "'," + " usuario_modificacion='" + usuarioModificacion + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "" + " where" + " aplicacion='" + aplicacion + "'" + " and pantalla='" + pantalla + "'" + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 267 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 270 */     catch (Exception e) {
/* 271 */       e.printStackTrace();
/* 272 */       Utilidades.writeError("SisAyudaDAO:modificarRegistro ", e);
/*     */       
/* 274 */       return false;
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
/*     */   public boolean asociarAnexo(String aplicacion, String pantalla, String archivoAnexo, int tamano, String usuarioModificacion) {
/*     */     try {
/* 290 */       String s = "update SIS_AYUDAS set  archivo_anexo='" + archivoAnexo + "'," + " tamano_anexo= " + tamano + "," + " usuario_modificacion='" + usuarioModificacion + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "" + " where" + " aplicacion='" + aplicacion + "'" + " and pantalla='" + pantalla + "'" + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 299 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 302 */     catch (Exception e) {
/* 303 */       e.printStackTrace();
/* 304 */       Utilidades.writeError("SisAyudaDAO:modificarRegistro ", e);
/*     */       
/* 306 */       return false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\SisAyudaDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */