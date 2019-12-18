/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.CalTiposDocumentoDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.CalTiposDocumentoDAO;
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
/*     */ public class CalTiposDocumentoDAO
/*     */ {
/*     */   ResultSet rs;
/*  24 */   DBManager dat = new DBManager();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  33 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/*     */     try {
/*  40 */       this.dat.close();
/*     */     }
/*  42 */     catch (Exception e) {
/*  43 */       Utilidades.writeError("CalTiposDocumentoDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalTiposDocumentoDTO next() {
/*     */     try {
/*  54 */       if (this.rs.next()) {
/*  55 */         return leerRegistro();
/*     */       }
/*     */     }
/*  58 */     catch (Exception e) {
/*  59 */       e.printStackTrace();
/*  60 */       Utilidades.writeError("CalTiposDocumentoDAO:next ", e);
/*     */     } 
/*  62 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalTiposDocumentoDTO leerRegistro() {
/*     */     try {
/*  72 */       CalTiposDocumentoDTO reg = new CalTiposDocumentoDTO();
/*  73 */       reg.setCodigo(this.rs.getString("codigo"));
/*  74 */       reg.setDescripcion(this.rs.getString("descripcion"));
/*  75 */       reg.setMostrarEnListaMaestra(this.rs.getString("mostrar_en_lista_maestra"));
/*  76 */       reg.setMostrarEnMapa(this.rs.getString("mostrar_en_mapa"));
/*  77 */       reg.setMostrarEnPlanes(this.rs.getString("mostrar_en_planes"));
/*  78 */       reg.setEstado(this.rs.getString("estado"));
/*  79 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  80 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  81 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  82 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  83 */       return reg;
/*     */     }
/*  85 */     catch (Exception e) {
/*  86 */       e.printStackTrace();
/*  87 */       Utilidades.writeError("CalTiposDocumentoDAO:leerRegistro ", e);
/*     */       
/*  89 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<CalTiposDocumentoDTO> cargarTodos(String descripcion) {
/* 100 */     Collection<CalTiposDocumentoDTO> resultados = new ArrayList<CalTiposDocumentoDTO>();
/*     */     try {
/* 102 */       String s = "select * from cal_tipos_documento where 1=1";
/*     */       
/* 104 */       if (descripcion.length() > 0) {
/* 105 */         s = s + " and upper(descripcion) like upper('%" + descripcion + "%')";
/*     */       }
/* 107 */       s = s + " order by 1";
/* 108 */       boolean rtaDB = this.dat.parseSql(s);
/* 109 */       if (!rtaDB) {
/* 110 */         return resultados;
/*     */       }
/* 112 */       this.rs = this.dat.getResultSet();
/* 113 */       while (this.rs.next()) {
/* 114 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 117 */     catch (Exception e) {
/* 118 */       e.printStackTrace();
/* 119 */       Utilidades.writeError("CalTiposDocumentoDAO:cargarTodos ", e);
/*     */     } 
/* 121 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalTiposDocumentoDTO cargarRegistro(String codigo) {
/*     */     try {
/* 131 */       String s = "select * from cal_tipos_documento  where  codigo='" + codigo + "'" + "";
/*     */ 
/*     */ 
/*     */       
/* 135 */       boolean rtaDB = this.dat.parseSql(s);
/* 136 */       if (!rtaDB) {
/* 137 */         return null;
/*     */       }
/* 139 */       this.rs = this.dat.getResultSet();
/* 140 */       if (this.rs.next()) {
/* 141 */         return leerRegistro();
/*     */       }
/*     */     }
/* 144 */     catch (Exception e) {
/* 145 */       e.printStackTrace();
/* 146 */       Utilidades.writeError("CalTiposDocumentoDAO:cargarCalTiposDocumento ", e);
/*     */     } 
/* 148 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int siguienteRegistro(String codigo) {
/* 157 */     int inumero = 1;
/* 158 */     String s = "select max(0) from cal_tipos_documento  where  codigo='" + codigo + "'" + "";
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 163 */       boolean rta = this.dat.parseSql(s);
/* 164 */       if (!rta) return 0; 
/* 165 */       this.rs = this.dat.getResultSet();
/* 166 */       if (this.rs.next()) {
/* 167 */         s = this.rs.getString(1);
/* 168 */         if (!this.rs.wasNull()) {
/* 169 */           inumero = Integer.parseInt(s) + 1;
/*     */         }
/*     */       } 
/* 172 */       return inumero;
/*     */     }
/* 174 */     catch (Exception e) {
/* 175 */       e.printStackTrace();
/* 176 */       Utilidades.writeError("CalTiposDocumentoDAO:siguienteRegistro ", e);
/*     */       
/* 178 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eliminarRegistro(String codigo) {
/*     */     try {
/* 188 */       String s = "delete from cal_tipos_documento where   codigo='" + codigo + "'" + "";
/*     */ 
/*     */ 
/*     */       
/* 192 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 195 */     catch (Exception e) {
/* 196 */       e.printStackTrace();
/* 197 */       Utilidades.writeError("CalTiposDocumentoDAO:eliminarRegistro ", e);
/*     */       
/* 199 */       return false;
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
/*     */   
/*     */   public boolean crearRegistro(String codigo, String descripcion, String mostrarEnListaMaestra, String mostrarEnMapa, String mostrarEnPlanes, String estado, String usuarioInsercion) {
/*     */     try {
/* 222 */       String s = "insert into cal_tipos_documento (codigo,descripcion,mostrar_en_lista_maestra,mostrar_en_mapa,mostrar_en_planes,estado,fecha_insercion,usuario_insercion) values ('" + codigo + "'," + "'" + descripcion + "'," + "'" + mostrarEnListaMaestra + "'," + "'" + mostrarEnMapa + "'," + "'" + mostrarEnPlanes + "'," + "'" + estado + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 241 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 244 */     catch (Exception e) {
/* 245 */       e.printStackTrace();
/* 246 */       Utilidades.writeError("CalTiposDocumentoDAO:crearRegistro ", e);
/*     */       
/* 248 */       return false;
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
/*     */   public boolean modificarRegistro(String codigo, String descripcion, String mostrarEnListaMaestra, String mostrarEnMapa, String mostrarEnPlanes, String estado, String usuarioModificacion) {
/*     */     try {
/* 266 */       String s = "update cal_tipos_documento set  descripcion='" + descripcion + "'," + " mostrar_en_lista_maestra='" + mostrarEnListaMaestra + "'," + " mostrar_en_mapa='" + mostrarEnMapa + "'," + " mostrar_en_planes='" + mostrarEnPlanes + "'," + " estado='" + estado + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " codigo='" + codigo + "'" + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 277 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 280 */     catch (Exception e) {
/* 281 */       e.printStackTrace();
/* 282 */       Utilidades.writeError("CalTiposDocumentoDAO:modificarRegistro ", e);
/*     */       
/* 284 */       return false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\CalTiposDocumentoDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */