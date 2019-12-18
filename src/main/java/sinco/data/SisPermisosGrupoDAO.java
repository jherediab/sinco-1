/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.StringTokenizer;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.SisPermisosGrupoDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.SisPermisosGrupoDAO;
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
/*     */ public class SisPermisosGrupoDAO
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
/*  49 */       Utilidades.writeError("SisPermisosGrupoDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SisPermisosGrupoDTO next() {
/*     */     try {
/*  60 */       if (this.rs.next()) {
/*  61 */         return leerRegistro();
/*     */       }
/*     */     }
/*  64 */     catch (Exception e) {
/*  65 */       e.printStackTrace();
/*  66 */       Utilidades.writeError("SisPermisosGrupoDAO:next ", e);
/*     */     } 
/*  68 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SisPermisosGrupoDTO leerRegistro() {
/*     */     try {
/*  78 */       SisPermisosGrupoDTO reg = new SisPermisosGrupoDTO();
/*     */       
/*  80 */       reg.setPermiso(this.rs.getString("permiso"));
/*  81 */       reg.setDescripcion(this.rs.getString("descripcion"));
/*  82 */       return reg;
/*     */     }
/*  84 */     catch (Exception e) {
/*  85 */       e.printStackTrace();
/*  86 */       Utilidades.writeError("SisPermisosGrupoDAO:leerRegistro ", e);
/*     */       
/*  88 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean cargarTodos(int grupo) {
/*     */     try {
/* 100 */       String s = "select permisos.codigo as permiso, permisos.nombre descripcion from sis_permisos permisos,sis_permisos_grupo g where permisos.codigo=g.permiso  and g.grupo=" + grupo + " order by 2";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 106 */       boolean rtaDB = this.dat.parseSql(s);
/*     */       
/* 108 */       if (!rtaDB) {
/* 109 */         return false;
/*     */       }
/*     */       
/* 112 */       this.rs = this.dat.getResultSet();
/* 113 */       return true;
/*     */     }
/* 115 */     catch (Exception e) {
/* 116 */       e.printStackTrace();
/* 117 */       Utilidades.writeError("PermisosGrupoDAO:cargarTodos ", e);
/*     */       
/* 119 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean cargarSinAsignar(int grupo) {
/*     */     try {
/* 125 */       String s = "select codigo as permiso,nombre descripcion  from sis_permisos  where codigo not in(select g.permiso from sis_permisos_grupo g where g.grupo=" + grupo + ") order by 2";
/*     */ 
/*     */       
/* 128 */       boolean rtaDB = this.dat.parseSql(s);
/*     */       
/* 130 */       if (!rtaDB) {
/* 131 */         return false;
/*     */       }
/*     */       
/* 134 */       this.rs = this.dat.getResultSet();
/* 135 */       return true;
/*     */     }
/* 137 */     catch (Exception e) {
/* 138 */       e.printStackTrace();
/* 139 */       Utilidades.writeError("PermisosGrupoDAO:cargarTodos ", e);
/*     */       
/* 141 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarRegistro(int grupo) {
/* 151 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 154 */       String s = "delete from sis_permisos_grupo where  grupo=" + grupo;
/*     */ 
/*     */       
/* 157 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 159 */     catch (Exception e) {
/* 160 */       e.printStackTrace();
/* 161 */       Utilidades.writeError("SisPermisosGrupoDAO:eliminarRegistro ", e);
/* 162 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 164 */     return rta;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD crearRegistro(int grupo, String permisos, String usuarioInsercion) {
/* 174 */     RespuestaBD rta = new RespuestaBD();
/*     */     try {
/* 176 */       eliminarRegistro(grupo);
/*     */       
/* 178 */       StringTokenizer st = new StringTokenizer(permisos, ",");
/* 179 */       while (st.hasMoreElements()) {
/* 180 */         String permiso = st.nextToken();
/* 181 */         String s = "insert into sis_permisos_grupo ( grupo,permiso,usuario_insercion,fecha_insercion)";
/* 182 */         s = s + " values (";
/* 183 */         s = s + "" + grupo + ",";
/* 184 */         s = s + "'" + permiso + "',";
/* 185 */         s = s + "'" + usuarioInsercion + "',";
/* 186 */         s = s + "" + Utilidades.getFechaBD() + "";
/* 187 */         s = s + ")";
/* 188 */         rta = this.dat.executeUpdate2(s);
/*     */       }
/*     */     
/* 191 */     } catch (Exception e) {
/* 192 */       e.printStackTrace();
/* 193 */       Utilidades.writeError("PermisosGrupoDAO:crearRegistro", e);
/*     */     } 
/* 195 */     return rta;
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
/*     */   public RespuestaBD modificarRegistro(int grupo, String permiso, String usuarioModificacion) {
/* 210 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 213 */       String s = "update sis_permisos_grupo set  usuario_modificacion='" + usuarioModificacion + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "" + " where" + " grupo=" + grupo + " and permiso='" + permiso + "'" + "";
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
/* 224 */       Utilidades.writeError("SisPermisosGrupoDAO:modificarRegistro ", e);
/* 225 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 227 */     return rta;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\SisPermisosGrupoDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */