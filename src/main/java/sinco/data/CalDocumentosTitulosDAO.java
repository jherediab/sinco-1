/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.CalDocumentosTitulosDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.CalDocumentosTitulosDAO;
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
/*     */ public class CalDocumentosTitulosDAO
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
/*  49 */       Utilidades.writeError("CalDocumentosTitulosDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalDocumentosTitulosDTO next() {
/*     */     try {
/*  60 */       if (this.rs.next()) {
/*  61 */         return leerRegistro();
/*     */       }
/*     */     }
/*  64 */     catch (Exception e) {
/*  65 */       e.printStackTrace();
/*  66 */       Utilidades.writeError("CalDocumentosTitulosDAO:next ", e);
/*     */     } 
/*  68 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalDocumentosTitulosDTO leerRegistro() {
/*     */     try {
/*  78 */       CalDocumentosTitulosDTO reg = new CalDocumentosTitulosDTO();
/*     */       
/*  80 */       reg.setCodigo(this.rs.getString("codigo"));
/*  81 */       reg.setTitulo(this.rs.getString("titulo"));
/*  82 */       reg.setTituloPadre(this.rs.getString("titulo_padre"));
/*  83 */       reg.setDescripcion(this.rs.getString("descripcion"));
/*  84 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  85 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  86 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  87 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  88 */       return reg;
/*     */     }
/*  90 */     catch (Exception e) {
/*  91 */       e.printStackTrace();
/*  92 */       Utilidades.writeError("CalDocumentosTitulosDAO:leerRegistro ", e);
/*     */       
/*  94 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection cargarTodos(String codigo) {
/* 105 */     Collection resultados = new ArrayList();
/*     */     try {
/* 107 */       String s = "select t.codigo,t.titulo,t.titulo_padre,t.descripcion,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from cal_documentos_titulos t  where upper(t.codigo) ='" + codigo + "'" + " order by COALESCE(t.titulo_padre,titulo),t.titulo";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 119 */       boolean rtaDB = this.dat.parseSql(s);
/* 120 */       if (!rtaDB) {
/* 121 */         return resultados;
/*     */       }
/* 123 */       this.rs = this.dat.getResultSet();
/* 124 */       while (this.rs.next()) {
/* 125 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 128 */     catch (Exception e) {
/* 129 */       e.printStackTrace();
/* 130 */       Utilidades.writeError("CalDocumentosTitulosDAO:cargarTodos ", e);
/*     */     } 
/* 132 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection cargar(String codigo, String titulo) {
/* 141 */     Collection resultados = new ArrayList();
/*     */     try {
/* 143 */       String s = "select t.codigo,t.titulo,t.titulo_padre,t.descripcion,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from cal_documentos_titulos t  where t.codigo='" + codigo + "'";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 154 */       if (titulo.length() > 0) {
/* 155 */         s = s + " and t.titulo<>'" + titulo + "'";
/*     */       }
/* 157 */       s = s + " order by COALESCE(t.titulo_padre,titulo),t.titulo";
/* 158 */       boolean rtaDB = this.dat.parseSql(s);
/* 159 */       if (!rtaDB) {
/* 160 */         return resultados;
/*     */       }
/* 162 */       this.rs = this.dat.getResultSet();
/* 163 */       while (this.rs.next()) {
/* 164 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 167 */     catch (Exception e) {
/* 168 */       e.printStackTrace();
/* 169 */       Utilidades.writeError("CalDocumentosTitulosDAO:cargar ", e);
/*     */     } 
/* 171 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalDocumentosTitulosDTO cargarRegistro(String codigo, String titulo) {
/*     */     try {
/* 183 */       String s = "select t.codigo,t.titulo,t.titulo_padre,t.descripcion,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from cal_documentos_titulos t  where  t.codigo='" + codigo + "'" + " and t.titulo='" + titulo + "'" + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 197 */       boolean rtaDB = this.dat.parseSql(s);
/* 198 */       if (!rtaDB) {
/* 199 */         return null;
/*     */       }
/* 201 */       this.rs = this.dat.getResultSet();
/* 202 */       if (this.rs.next()) {
/* 203 */         return leerRegistro();
/*     */       }
/*     */     }
/* 206 */     catch (Exception e) {
/* 207 */       e.printStackTrace();
/* 208 */       Utilidades.writeError("CalDocumentosTitulosDAO:cargarCalDocumentosTitulos", e);
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
/*     */   public int siguienteRegistro(String codigo, String titulo) {
/* 220 */     int inumero = 1;
/* 221 */     String s = "select max(0) from cal_documentos_titulos  where  codigo='" + codigo + "'" + " and titulo='" + titulo + "'" + "";
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 227 */       boolean rta = this.dat.parseSql(s);
/* 228 */       if (!rta) return 0; 
/* 229 */       this.rs = this.dat.getResultSet();
/* 230 */       if (this.rs.next()) {
/* 231 */         s = this.rs.getString(1);
/* 232 */         if (!this.rs.wasNull()) {
/* 233 */           inumero = Integer.parseInt(s) + 1;
/*     */         }
/*     */       } 
/* 236 */       return inumero;
/*     */     }
/* 238 */     catch (Exception e) {
/* 239 */       e.printStackTrace();
/* 240 */       Utilidades.writeError("CalDocumentosTitulosDAO:siguienteRegistro ", e);
/*     */       
/* 242 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eliminarRegistro(String codigo, String titulo) {
/*     */     try {
/* 253 */       String s = "delete from cal_documentos_titulos where  codigo='" + codigo + "'" + "  and titulo='" + titulo + "'" + "";
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 258 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 261 */     catch (Exception e) {
/* 262 */       e.printStackTrace();
/* 263 */       Utilidades.writeError("CalDocumentosTitulosDAO:eliminarRegistro ", e);
/*     */       
/* 265 */       return false;
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
/*     */   public boolean crearRegistro(String codigo, String titulo, String tituloPadre, String descripcion, String usuarioInsercion) {
/*     */     try {
/* 287 */       String s = "insert into cal_documentos_titulos(codigo,titulo,titulo_padre,descripcion,fecha_insercion,usuario_insercion) values ('" + codigo + "'," + "'" + titulo + "'," + "'" + tituloPadre + "'," + "'" + descripcion + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 302 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 305 */     catch (Exception e) {
/* 306 */       e.printStackTrace();
/* 307 */       Utilidades.writeError("%CalDocumentosTitulosDAO:crearRegistro ", e);
/*     */       
/* 309 */       return false;
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
/*     */   public boolean modificarRegistro(String codigo, String titulo, String tituloPadre, String descripcion, String usuarioModificacion) {
/*     */     try {
/* 325 */       String s = "update cal_documentos_titulos set  titulo_padre='" + tituloPadre + "'," + " descripcion='" + descripcion + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " codigo='" + codigo + "'" + " and titulo='" + titulo + "'" + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 334 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 337 */     catch (Exception e) {
/* 338 */       e.printStackTrace();
/* 339 */       Utilidades.writeError("CalDocumentosTitulosDAO:modificarRegistro ", e);
/*     */       
/* 341 */       return false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\CalDocumentosTitulosDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */