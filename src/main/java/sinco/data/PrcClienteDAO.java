/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.PrcClienteDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.PrcClienteDAO;
/*     */ 
 
/*     */ 
/*     */ public class PrcClienteDAO
/*     */ {
/*     */   public PrcClienteDTO leerRegistro(ResultSet rs) {
/*     */     try {
/*  35 */       PrcClienteDTO reg = new PrcClienteDTO();
/*     */       
/*  37 */       reg.setIdCliente(rs.getInt("id_cliente"));
/*  38 */       reg.setIdentificacionCliente(rs.getString("identificacion_cliente"));
/*  39 */       reg.setTipoIdentificacion(rs.getString("tipo_identificacion"));
/*  40 */       reg.setNombreCliente(rs.getString("nombre_cliente"));
/*  41 */       reg.setDireccionCliente(rs.getString("direccion_cliente"));
/*  42 */       reg.setTelefono(rs.getString("telefono"));
/*  43 */       reg.setCorreoElectronico(rs.getString("correo_electronico"));
/*  44 */       reg.setEstado(rs.getString("estado"));
/*  45 */       reg.setUsuarioInsercion(rs.getString("usuario_insercion"));
/*  46 */       reg.setFechaInsercion(rs.getString("fecha_insercion"));
/*  47 */       reg.setUsuarioModificacion(rs.getString("usuario_modificacion"));
/*  48 */       reg.setFechaModificacacion(rs.getString("fecha_modificacacion"));
/*  49 */       reg.setNombreTipoIdentificacion(rs.getString("nombre_tipo_identificacion"));
/*  50 */       reg.setNombreEstado(rs.getString("nombre_estado"));
/*  51 */       return reg;
/*     */     }
/*  53 */     catch (Exception e) {
/*  54 */       e.printStackTrace();
/*  55 */       Utilidades.writeError("PrcClienteDAO:leerRegistro ", e);
/*     */       
/*  57 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<PrcClienteDTO> cargarTodos(String identificacionCliente, String tipoIdentificacion) {
/*  68 */     Collection<PrcClienteDTO> resultados = new ArrayList<PrcClienteDTO>();
/*     */     
/*  70 */    DBManager dat = new DBManager();
/*     */     try {
/*  72 */       String s = "select t.id_cliente,t.identificacion_cliente,t.tipo_identificacion,m1.descripcion as nombre_tipo_identificacion,t.nombre_cliente,t.direccion_cliente,t.telefono,t.correo_electronico,t.estado,m2.descripcion as nombre_estado,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacacion from prc_cliente t  left join sis_multivalores m1 on (m1.tabla='TIPO_DOCUMENTO' and m1.valor =t.tipo_identificacion) left join sis_multivalores m2 on (m2.tabla='estado_activo_inactivo' and m2.valor=t.estado) where 1=1";
/*     */ 
 
/*     */       
/*  91 */       if (identificacionCliente.length() > 0) {
/*  92 */         s = s + " and upper(t.identificacion_cliente) like upper('%" + identificacionCliente + "%')";
/*     */       }
/*  94 */       if (tipoIdentificacion.length() > 0) {
/*  95 */         s = s + " and upper(t.tipo_identificacion) like upper('%" + tipoIdentificacion + "%')";
/*     */       }
/*  97 */       s = s + " order by 1";
/*  98 */       boolean rtaDB = dat.parseSql(s);
/*  99 */       if (!rtaDB) {
/* 100 */         return resultados;
/*     */       }
/* 102 */       ResultSet rs = dat.getResultSet();
/* 103 */       while (rs.next()) {
/* 104 */         resultados.add(leerRegistro(rs));
/*     */       }
/*     */     }
/* 107 */     catch (Exception e) {
/* 108 */       e.printStackTrace();
/* 109 */       Utilidades.writeError("PrcClienteDAO:cargarTodos ", e);
/*     */     } finally {
/*     */       
/* 112 */       dat.close();
/*     */     } 
/* 114 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PrcClienteDTO cargarRegistro(int idCliente) {
/* 124 */   DBManager  dat = new DBManager();
/*     */     try {
/* 126 */       String s = "select t.id_cliente,t.identificacion_cliente,t.tipo_identificacion,m1.descripcion as nombre_tipo_identificacion,t.nombre_cliente,t.direccion_cliente,t.telefono,t.correo_electronico,t.estado,m2.descripcion as nombre_estado,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacacion from prc_cliente t  left join sis_multivalores m1 on (m1.tabla='TIPO_DOCUMENTO' and m1.valor =t.tipo_identificacion) left join sis_multivalores m2 on (m2.tabla='estado_activo_inactivo' and m2.valor=t.estado) where  t.id_cliente=" + idCliente + "";
/*     */ 
 
/*     */       
/* 147 */       boolean rtaDB = dat.parseSql(s);
/* 148 */       if (!rtaDB) {
/* 149 */         return null;
/*     */       }
/* 151 */       ResultSet rs = dat.getResultSet();
/* 152 */       if (rs.next()) {
/* 153 */         return leerRegistro(rs);
/*     */       }
/*     */     }
/* 156 */     catch (Exception e) {
/* 157 */       e.printStackTrace();
/* 158 */       Utilidades.writeError("PrcClienteDAO:cargarPrcCliente", e);
/*     */     } finally {
/*     */       
/* 161 */       dat.close();
/*     */     } 
/* 163 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int siguienteRegistro() {
/* 172 */     int inumero = 1;
/* 173 */     String s = "select max(id_cliente) from prc_cliente ";
/*     */ 
/*     */     
/* 176 */   DBManager  dat = new DBManager();
/*     */     try {
/* 178 */       boolean rta = dat.parseSql(s);
/* 179 */       if (!rta) return 0; 
/* 180 */       ResultSet rs = dat.getResultSet();
/* 181 */       if (rs.next()) {
/* 182 */         s = rs.getString(1);
/* 183 */         if (!rs.wasNull()) {
/* 184 */           inumero = Integer.parseInt(s) + 1;
/*     */         }
/*     */       } 
/* 187 */       return inumero;
/*     */     }
/* 189 */     catch (Exception e) {
/* 190 */       e.printStackTrace();
/* 191 */       Utilidades.writeError("PrcClienteDAO:siguienteRegistro ", e);
/*     */     } finally {
/*     */       
/* 194 */       dat.close();
/*     */     } 
/* 196 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarRegistro(int idCliente) {
/* 206 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 208 */   DBManager  dat = new DBManager();
/*     */     try {
/* 210 */       String s = "delete from prc_cliente where  id_cliente=" + idCliente + "";
/*     */ 
/*     */ 
/*     */       
/* 214 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 216 */     catch (Exception e) {
/* 217 */       e.printStackTrace();
/* 218 */       Utilidades.writeError("PrcClienteDAO:eliminarRegistro ", e);
/* 219 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 222 */       dat.close();
/*     */     } 
/* 224 */     return rta;
/*     */   }
/*     */ 
 
/*     */   
/*     */   public RespuestaBD crearRegistro(int idCliente, String identificacionCliente, String tipoIdentificacion, String nombreCliente, String direccionCliente, String telefono, String correoElectronico, String estado, String usuarioInsercion, String fechaModificacacion) {
/* 244 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 246 */     int elSiguiente = siguienteRegistro();
/* 247 */     if (elSiguiente == 0) {
/* 248 */       rta.setMensaje("Generando secuencia");
/* 249 */       return rta;
/*     */     } 
/*     */     
/* 252 */   DBManager  dat = new DBManager();
/*     */     try {
/* 254 */       String s = "insert into prc_cliente(id_cliente,identificacion_cliente,tipo_identificacion,nombre_cliente,direccion_cliente,telefono,correo_electronico,estado,usuario_insercion,fecha_insercion,fecha_modificacacion) values (" + elSiguiente + "," + "'" + identificacionCliente + "'," + "'" + tipoIdentificacion + "'," + "'" + nombreCliente + "'," + "'" + direccionCliente + "'," + "'" + telefono + "'," + "'" + correoElectronico + "'," + "'" + estado + "'," + "'" + usuarioInsercion + "'," + "" + Utilidades.getFechaBD() + "," + "" + ((fechaModificacacion.length() > 0) ? Utilidades.formatoFecha2(fechaModificacacion) : "null") + "" + ")";
/*     */ 
 
/*     */       
/* 279 */       rta = dat.executeUpdate2(s);
/* 280 */       rta.setSecuencia(elSiguiente);
/*     */     }
/* 282 */     catch (Exception e) {
/* 283 */       e.printStackTrace();
/* 284 */       Utilidades.writeError("%PrcClienteDAO:crearRegistro ", e);
/* 285 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 288 */       dat.close();
/*     */     } 
/* 290 */     return rta;
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
/*     */   public RespuestaBD modificarRegistro(int idCliente, String identificacionCliente, String tipoIdentificacion, String nombreCliente, String direccionCliente, String telefono, String correoElectronico, String estado, String usuarioModificacion, String fechaModificacacion) {
/* 310 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 312 */    DBManager dat = new DBManager();
/*     */     try {
/* 314 */       String s = "update prc_cliente set  identificacion_cliente='" + identificacionCliente + "'," + " tipo_identificacion='" + tipoIdentificacion + "'," + " nombre_cliente='" + nombreCliente + "'," + " direccion_cliente='" + direccionCliente + "'," + " telefono='" + telefono + "'," + " correo_electronico='" + correoElectronico + "'," + " estado='" + estado + "'," + " usuario_modificacion='" + usuarioModificacion + "'," + " fecha_modificacacion=" + ((fechaModificacacion.length() > 0) ? Utilidades.formatoFecha2(fechaModificacacion) : "null") + "" + " where" + " id_cliente=" + idCliente + "";
/*     */ 
 
/*     */       
/* 327 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 329 */     catch (Exception e) {
/* 330 */       e.printStackTrace();
/* 331 */       Utilidades.writeError("PrcClienteDAO:modificarRegistro ", e);
/* 332 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 335 */       dat.close();
/*     */     } 
/* 337 */     return rta;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\PrcClienteDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */