/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.SisMensajesDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.SisMensajesDAO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SisMensajesDAO
/*     */ {
/*     */   ResultSet rs;
/*  29 */   DBManager dat = new DBManager();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  40 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/*     */     try {
/*  47 */       this.dat.close();
/*     */     }
/*  49 */     catch (Exception e) {
/*  50 */       Utilidades.writeError("SisMensajesDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SisMensajesDTO next() {
/*     */     try {
/*  61 */       if (this.rs.next()) {
/*  62 */         return leerRegistro();
/*     */       }
/*     */     }
/*  65 */     catch (Exception e) {
/*  66 */       e.printStackTrace();
/*  67 */       Utilidades.writeError("SisMensajesDAO:next ", e);
/*     */     } 
/*  69 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SisMensajesDTO leerRegistro() {
/*     */     try {
/*  79 */       SisMensajesDTO reg = new SisMensajesDTO();
/*     */       
/*  81 */       reg.setCodigo(this.rs.getString("codigo"));
/*  82 */       reg.setDescripcion(this.rs.getString("descripcion"));
/*  83 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  84 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  85 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  86 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  87 */       return reg;
/*     */     }
/*  89 */     catch (Exception e) {
/*  90 */       e.printStackTrace();
/*  91 */       Utilidades.writeError("SisMensajesDAO:leerRegistro ", e);
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
/*     */   
/*     */   public Collection<SisMensajesDTO> cargarTodos(String codigo, String descripcion) {
/* 104 */     Collection<SisMensajesDTO> resultados = new ArrayList<SisMensajesDTO>();
/*     */     try {
/* 106 */       String s = "select t.codigo,t.descripcion,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from mensajes t  where 1=1";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 116 */       if (codigo.length() > 0) {
/* 117 */         s = s + " and upper(t.codigo) like upper('%" + codigo + "%')";
/*     */       }
/*     */       
/* 120 */       if (descripcion.length() > 0) {
/* 121 */         s = s + " and upper(t.descripcion) like upper('%" + descripcion + "%')";
/*     */       }
/*     */       
/* 124 */       s = s + " order by 1";
/* 125 */       boolean rtaDB = this.dat.parseSql(s);
/* 126 */       if (!rtaDB) {
/* 127 */         return resultados;
/*     */       }
/* 129 */       this.rs = this.dat.getResultSet();
/* 130 */       while (this.rs.next()) {
/* 131 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 134 */     catch (Exception e) {
/* 135 */       e.printStackTrace();
/* 136 */       Utilidades.writeError("SisMensajesDAO:cargarTodos ", e);
/*     */     } 
/* 138 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SisMensajesDTO cargarRegistro(String codigo) {
/*     */     try {
/* 147 */       String s = "select t.codigo,t.descripcion,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from mensajes t  where  t.codigo='" + codigo + "'" + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 159 */       boolean rtaDB = this.dat.parseSql(s);
/*     */       
/* 161 */       if (!rtaDB) {
/* 162 */         return null;
/*     */       }
/* 164 */       this.rs = this.dat.getResultSet();
/*     */       
/* 166 */       if (this.rs.next()) {
/* 167 */         return leerRegistro();
/*     */       }
/*     */     }
/* 170 */     catch (Exception e) {
/* 171 */       e.printStackTrace();
/* 172 */       Utilidades.writeError("SisMensajesDAO:cargarSisMensajes", e);
/*     */     } 
/* 174 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarRegistro(String codigo) {
/* 184 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 187 */       String s = "delete from mensajes where  codigo='" + codigo + "'" + "";
/*     */ 
/*     */ 
/*     */       
/* 191 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 193 */     catch (Exception e) {
/* 194 */       e.printStackTrace();
/* 195 */       Utilidades.writeError("SisMensajesDAO:eliminarRegistro ", e);
/* 196 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 198 */     return rta;
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
/*     */   public RespuestaBD crearRegistro(String codigo, String descripcion, String usuarioInsercion) {
/* 211 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 214 */       String s = "insert into mensajes(codigo,descripcion,fecha_insercion,usuario_insercion) values ('" + codigo + "'," + "'" + descripcion + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 225 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 227 */     catch (Exception e) {
/* 228 */       e.printStackTrace();
/* 229 */       Utilidades.writeError("%SisMensajesDAO:crearRegistro ", e);
/* 230 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 232 */     return rta;
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
/*     */   public RespuestaBD modificarRegistro(String codigo, String descripcion, String usuarioModificacion) {
/* 245 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 248 */       String s = "update mensajes set  descripcion='" + descripcion + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " codigo='" + codigo + "'" + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 255 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 257 */     catch (Exception e) {
/* 258 */       e.printStackTrace();
/* 259 */       Utilidades.writeError("SisMensajesDAO:modificarRegistro ", e);
/* 260 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 262 */     return rta;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\SisMensajesDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */