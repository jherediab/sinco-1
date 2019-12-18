/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.SisPermisosDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.SisPermisosDAO;
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
/*     */ public class SisPermisosDAO
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
/*  50 */       Utilidades.writeError("SisPermisosDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SisPermisosDTO next() {
/*     */     try {
/*  61 */       if (this.rs.next()) {
/*  62 */         return leerRegistro();
/*     */       }
/*     */     }
/*  65 */     catch (Exception e) {
/*  66 */       e.printStackTrace();
/*  67 */       Utilidades.writeError("SisPermisosDAO:next ", e);
/*     */     } 
/*  69 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SisPermisosDTO leerRegistro() {
/*     */     try {
/*  79 */       SisPermisosDTO reg = new SisPermisosDTO();
/*     */       
/*  81 */       reg.setCodigo(this.rs.getString("codigo"));
/*  82 */       reg.setNombre(this.rs.getString("nombre"));
/*  83 */       reg.setEstado(this.rs.getString("estado"));
/*  84 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  85 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  86 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  87 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  88 */       reg.setNombreEstado(this.rs.getString("nombre_estado"));
/*  89 */       return reg;
/*     */     }
/*  91 */     catch (Exception e) {
/*  92 */       e.printStackTrace();
/*  93 */       Utilidades.writeError("SisPermisosDAO:leerRegistro ", e);
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
/*     */   public Collection<SisPermisosDTO> cargarTodos(String codigo, String nombre) {
/* 106 */     Collection<SisPermisosDTO> resultados = new ArrayList<SisPermisosDTO>();
/*     */     try {
/* 108 */       String s = "select t.codigo,t.nombre,t.estado,m1.DESCRIPCION as nombre_estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from sis_permisos t  left join sis_multivalores m1 on (m1.tabla='ESTADO_REGISTRO' and m1.VALOR=t.estado) where 1=1";
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
/* 120 */       if (codigo.length() > 0) {
/* 121 */         s = s + " and upper(t.codigo) like upper('%" + codigo + "%')";
/*     */       }
/* 123 */       if (nombre.length() > 0) {
/* 124 */         s = s + " and upper(t.nombre) like upper('%" + nombre + "%')";
/*     */       }
/* 126 */       s = s + " order by 1";
/* 127 */       boolean rtaDB = this.dat.parseSql(s);
/* 128 */       if (!rtaDB) {
/* 129 */         return resultados;
/*     */       }
/* 131 */       this.rs = this.dat.getResultSet();
/* 132 */       while (this.rs.next()) {
/* 133 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 136 */     catch (Exception e) {
/* 137 */       e.printStackTrace();
/* 138 */       Utilidades.writeError("SisPermisosDAO:cargarTodos ", e);
/*     */     } 
/* 140 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SisPermisosDTO cargarRegistro(String codigo) {
/*     */     try {
/* 150 */       String s = "select t.codigo,t.nombre,t.estado,m1.DESCRIPCION as nombre_estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from sis_permisos t  left join sis_multivalores m1 on (m1.tabla='ESTADO_REGISTRO' and m1.VALOR=t.estado) where  t.codigo='" + codigo + "'" + "";
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
/* 164 */       boolean rtaDB = this.dat.parseSql(s);
/* 165 */       if (!rtaDB) {
/* 166 */         return null;
/*     */       }
/* 168 */       this.rs = this.dat.getResultSet();
/* 169 */       if (this.rs.next()) {
/* 170 */         return leerRegistro();
/*     */       }
/*     */     }
/* 173 */     catch (Exception e) {
/* 174 */       e.printStackTrace();
/* 175 */       Utilidades.writeError("SisPermisosDAO:cargarSisPermisos", e);
/*     */     } 
/* 177 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarRegistro(String codigo) {
/* 187 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 190 */       String s = "delete from sis_permisos where  codigo='" + codigo + "'" + "";
/*     */ 
/*     */ 
/*     */       
/* 194 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 196 */     catch (Exception e) {
/* 197 */       e.printStackTrace();
/* 198 */       Utilidades.writeError("SisPermisosDAO:eliminarRegistro ", e);
/* 199 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 201 */     return rta;
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
/*     */   public RespuestaBD crearRegistro(String codigo, String nombre, String estado, String usuarioInsercion) {
/* 215 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 218 */       String s = "insert into sis_permisos(codigo,nombre,estado,fecha_insercion,usuario_insercion) values ('" + codigo + "'," + "'" + nombre + "'," + "'" + estado + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
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
/* 231 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 233 */     catch (Exception e) {
/* 234 */       e.printStackTrace();
/* 235 */       Utilidades.writeError("%SisPermisosDAO:crearRegistro ", e);
/* 236 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 238 */     return rta;
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
/*     */   public RespuestaBD modificarRegistro(String codigo, String nombre, String estado, String usuarioModificacion) {
/* 252 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 255 */       String s = "update sis_permisos set  nombre='" + nombre + "'," + " estado='" + estado + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " codigo='" + codigo + "'" + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 263 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 265 */     catch (Exception e) {
/* 266 */       e.printStackTrace();
/* 267 */       Utilidades.writeError("SisPermisosDAO:modificarRegistro ", e);
/* 268 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 270 */     return rta;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\SisPermisosDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */