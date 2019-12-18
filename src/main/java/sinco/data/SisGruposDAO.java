/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.SisGruposDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.SisGruposDAO;
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
/*     */ public class SisGruposDAO
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
/*  50 */       Utilidades.writeError("SisGruposDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SisGruposDTO next() {
/*     */     try {
/*  61 */       if (this.rs.next()) {
/*  62 */         return leerRegistro();
/*     */       }
/*     */     }
/*  65 */     catch (Exception e) {
/*  66 */       e.printStackTrace();
/*  67 */       Utilidades.writeError("SisGruposDAO:next ", e);
/*     */     } 
/*  69 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SisGruposDTO leerRegistro() {
/*     */     try {
/*  79 */       SisGruposDTO reg = new SisGruposDTO();
/*     */       
/*  81 */       reg.setCodigo(this.rs.getInt("codigo"));
/*  82 */       reg.setDescripcion(this.rs.getString("descripcion"));
/*  83 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  84 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  85 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  86 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  87 */       return reg;
/*     */     }
/*  89 */     catch (Exception e) {
/*  90 */       e.printStackTrace();
/*  91 */       Utilidades.writeError("SisGruposDAO:leerRegistro ", e);
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
/*     */   public Collection<SisGruposDTO> cargarTodos(int codigo, String descripcion) {
/* 104 */     Collection<SisGruposDTO> resultados = new ArrayList<SisGruposDTO>();
/*     */     try {
/* 106 */       String s = "select t.codigo,t.descripcion,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion from sis_grupos t  where 1=1";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 115 */       if (codigo > 0) {
/* 116 */         s = s + " and t.codigo=" + codigo;
/*     */       }
/* 118 */       if (descripcion.length() > 0) {
/* 119 */         s = s + " and upper(t.descripcion) like upper('%" + descripcion + "%')";
/*     */       }
/* 121 */       s = s + " order by 1";
/* 122 */       boolean rtaDB = this.dat.parseSql(s);
/* 123 */       if (!rtaDB) {
/* 124 */         return resultados;
/*     */       }
/* 126 */       this.rs = this.dat.getResultSet();
/* 127 */       while (this.rs.next()) {
/* 128 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 131 */     catch (Exception e) {
/* 132 */       e.printStackTrace();
/* 133 */       Utilidades.writeError("SisGruposDAO:cargarTodos ", e);
/*     */     } 
/* 135 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SisGruposDTO cargarRegistro(int codigo) {
/*     */     try {
/* 145 */       String s = "select t.codigo,t.descripcion,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion from sis_grupos t  where  t.codigo=" + codigo + "";
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
/* 156 */       boolean rtaDB = this.dat.parseSql(s);
/* 157 */       if (!rtaDB) {
/* 158 */         return null;
/*     */       }
/* 160 */       this.rs = this.dat.getResultSet();
/* 161 */       if (this.rs.next()) {
/* 162 */         return leerRegistro();
/*     */       }
/*     */     }
/* 165 */     catch (Exception e) {
/* 166 */       e.printStackTrace();
/* 167 */       Utilidades.writeError("SisGruposDAO:cargarSisGrupos", e);
/*     */     } 
/* 169 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarRegistro(int codigo) {
/* 179 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 182 */       String s = "delete from sis_grupos where  codigo=" + codigo + "";
/*     */ 
/*     */ 
/*     */       
/* 186 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 188 */     catch (Exception e) {
/* 189 */       e.printStackTrace();
/* 190 */       Utilidades.writeError("SisGruposDAO:eliminarRegistro ", e);
/* 191 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 193 */     return rta;
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
/*     */   public RespuestaBD crearRegistro(int codigo, String descripcion, String usuarioInsercion) {
/* 206 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 209 */       String s = "insert into sis_grupos(codigo,descripcion,usuario_insercion,fecha_insercion) values (" + codigo + "," + "'" + descripcion + "'," + "'" + usuarioInsercion + "'," + "" + Utilidades.getFechaBD() + "" + ")";
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
/* 220 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 222 */     catch (Exception e) {
/* 223 */       e.printStackTrace();
/* 224 */       Utilidades.writeError("%SisGruposDAO:crearRegistro ", e);
/* 225 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 227 */     return rta;
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
/*     */   public RespuestaBD modificarRegistro(int codigo, String descripcion, String usuarioModificacion) {
/* 240 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 243 */       String s = "update sis_grupos set  descripcion='" + descripcion + "'," + " usuario_modificacion='" + usuarioModificacion + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "" + " where" + " codigo=" + codigo + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 250 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 252 */     catch (Exception e) {
/* 253 */       e.printStackTrace();
/* 254 */       Utilidades.writeError("SisGruposDAO:modificarRegistro ", e);
/* 255 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 257 */     return rta;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\SisGruposDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */