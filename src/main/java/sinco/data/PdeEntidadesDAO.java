/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.PdeEntidadesDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.PdeEntidadesDAO;
/*     */ 

/*     */ public class PdeEntidadesDAO
/*     */ {
/*     */   public PdeEntidadesDTO leerRegistro(ResultSet rs) {
/*     */     try {
/*  35 */       PdeEntidadesDTO reg = new PdeEntidadesDTO();
/*     */       
/*  37 */       reg.setNitEntidad(rs.getLong("nit_entidad"));
/*  38 */       reg.setNombre(rs.getString("nombre"));
/*  39 */       reg.setDireccion(rs.getString("direccion"));
/*  40 */       reg.setDepartamento(rs.getString("departamento"));
/*  41 */       reg.setMunicipio(rs.getString("municipio"));
/*  42 */       reg.setTelefono(rs.getInt("telefono"));
/*  43 */       reg.setFechaInsercion(rs.getString("fecha_insercion"));
/*  44 */       reg.setUsuarioInsercion(rs.getString("usuario_insercion"));
/*  45 */       reg.setFechaModificacion(rs.getString("fecha_modificacion"));
/*  46 */       reg.setUsuarioModificacion(rs.getString("usuario_modificacion"));
/*  47 */       reg.setNombreDepartamento(rs.getString("nombre_departamento"));
/*  48 */       reg.setNombreMunicipio(rs.getString("nombre_ciudad"));
/*  49 */       return reg;
/*     */     }
/*  51 */     catch (Exception e) {
/*  52 */       e.printStackTrace();
/*  53 */       Utilidades.writeError("PdeEntidadesDAO:leerRegistro ", e);
/*     */       
/*  55 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<PdeEntidadesDTO> cargarTodos(String nombre) {
/*  65 */     Collection<PdeEntidadesDTO> resultados = new ArrayList<PdeEntidadesDTO>();
/*     */     
/*  67 */    DBManager dat = new DBManager();
/*     */     try {
/*  69 */       String s = "select t.nit_entidad,t.nombre,t.direccion,t.departamento,r1.nombre_departamento as nombre_departamento,t.municipio,r2.nombre_ciudad,t.telefono,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from sis_entidad t  left join par_departamento r1 on (r1.codigo_departamento=t.departamento) left join par_ciudad r2 on (r2.codigo_departamento=t.departamento and r2.codigo_ciudad=t.municipio) where 1=1";
/*     */ 

/*     */       
/*  86 */       if (nombre.length() > 0) {
/*  87 */         s = s + " and upper(t.nombre) like upper('%" + nombre + "%')";
/*     */       }
/*  89 */       s = s + " order by 1";
/*  90 */       boolean rtaDB = dat.parseSql(s);
/*  91 */       if (!rtaDB) {
/*  92 */         return resultados;
/*     */       }
/*  94 */       ResultSet rs = dat.getResultSet();
/*  95 */       while (rs.next()) {
/*  96 */         resultados.add(leerRegistro(rs));
/*     */       }
/*     */     }
/*  99 */     catch (Exception e) {
/* 100 */       e.printStackTrace();
/* 101 */       Utilidades.writeError("PdeEntidadesDAO:cargarTodos ", e);
/*     */     } finally {
/*     */       
/* 104 */       dat.close();
/*     */     } 
/* 106 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PdeEntidadesDTO cargarRegistro(long nitEntidad) {
/* 116 */    DBManager dat = new DBManager();
/*     */     try {
/* 118 */       String s = "select t.nit_entidad,t.nombre,t.direccion,t.departamento,r1.nombre_departamento as nombre_departamento,t.municipio,r2.nombre_ciudad,t.telefono,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from sis_entidad t  left join par_departamento r1 on (r1.codigo_departamento=t.departamento) left join par_ciudad r2 on (r2.codigo_departamento=t.departamento and r2.codigo_ciudad=t.municipio) where  t.nit_entidad=" + nitEntidad + "";
/*     */ 

/*     */       
/* 137 */       boolean rtaDB = dat.parseSql(s);
/* 138 */       if (!rtaDB) {
/* 139 */         return null;
/*     */       }
/* 141 */       ResultSet rs = dat.getResultSet();
/* 142 */       if (rs.next()) {
/* 143 */         return leerRegistro(rs);
/*     */       }
/*     */     }
/* 146 */     catch (Exception e) {
/* 147 */       e.printStackTrace();
/* 148 */       Utilidades.writeError("PdeEntidadesDAO:cargarPdeEntidades", e);
/*     */     } finally {
/*     */       
/* 151 */       dat.close();
/*     */     } 
/* 153 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarRegistro(long nitEntidad) {
/* 163 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 165 */    DBManager dat = new DBManager();
/*     */     try {
/* 167 */       String s = "delete from sis_entidad where  nit_entidad=" + nitEntidad + "";
/*     */ 
/*     */ 
/*     */       
/* 171 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 173 */     catch (Exception e) {
/* 174 */       e.printStackTrace();
/* 175 */       Utilidades.writeError("PdeEntidadesDAO:eliminarRegistro ", e);
/* 176 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 179 */       dat.close();
/*     */     } 
/* 181 */     return rta;
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
/*     */   public RespuestaBD crearRegistro(long nitEntidad, String nombre, String direccion, String departamento, String municipio, int telefono, String usuarioInsercion) {
/* 198 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 200 */   DBManager dat = new DBManager();
/*     */     try {
/* 202 */       String s = "insert into sis_entidad(nit_entidad,nombre,direccion,departamento,municipio,telefono,fecha_insercion,usuario_insercion) values (" + nitEntidad + "," + "'" + nombre + "'," + "'" + direccion + "'," + "'" + departamento + "'," + "'" + municipio + "'," + "" + telefono + "," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
/*     */ 

/*     */       
/* 221 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 223 */     catch (Exception e) {
/* 224 */       e.printStackTrace();
/* 225 */       Utilidades.writeError("%PdeEntidadesDAO:crearRegistro ", e);
/* 226 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 229 */       dat.close();
/*     */     } 
/* 231 */     return rta;
/*     */   }

/*     */ 
/*     */   
/*     */   public RespuestaBD modificarRegistro(long nitEntidad, String nombre, String direccion, String departamento, String municipio, int telefono, String usuarioModificacion) {
/* 248 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 250 */    DBManager dat = new DBManager();
/*     */     try {
/* 252 */       String s = "update sis_entidad set  nombre='" + nombre + "'," + " direccion='" + direccion + "'," + " departamento='" + departamento + "'," + " municipio='" + municipio + "'," + " telefono=" + telefono + "," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " nit_entidad=" + nitEntidad + "";
/*     */ 

/*     */       
/* 263 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 265 */     catch (Exception e) {
/* 266 */       e.printStackTrace();
/* 267 */       Utilidades.writeError("PdeEntidadesDAO:modificarRegistro ", e);
/* 268 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 271 */       dat.close();
/*     */     } 
/* 273 */     return rta;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\PdeEntidadesDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */