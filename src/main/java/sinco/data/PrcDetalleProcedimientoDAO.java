/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.PrcDetalleProcedimientoDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.PrcDetalleProcedimientoDAO;
/*     */ 
/*     */ 
 
/*     */ 
/*     */ public class PrcDetalleProcedimientoDAO
/*     */ {
/*     */   public PrcDetalleProcedimientoDTO leerRegistro(ResultSet rs) {
/*     */     try {
/*  35 */       PrcDetalleProcedimientoDTO reg = new PrcDetalleProcedimientoDTO();
/*     */       
/*  37 */       reg.setTipoDimension(rs.getString("tipo_dimension"));
/*  38 */       reg.setDescripcionDetalle(rs.getString("descripcion_detalle"));
/*  39 */       reg.setCodigoEmpleado(rs.getInt("codigo_empleado"));
/*  40 */       reg.setRegistroDetalle(rs.getString("registro_detalle"));
/*     */       
/*  42 */       return reg;
/*     */     }
/*  44 */     catch (Exception e) {
/*  45 */       e.printStackTrace();
/*  46 */       Utilidades.writeError("PrcDetalleProcedimientoDAO:leerRegistro ", e);
/*     */       
/*  48 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<PrcDetalleProcedimientoDTO> cargarTodos(int id_procedimiento) {
/*  58 */     Collection<PrcDetalleProcedimientoDTO> resultados = new ArrayList<PrcDetalleProcedimientoDTO>();
/*     */     
/*  60 */    DBManager dat = new DBManager();
/*     */     try {
/*  62 */       String s = "select t.tipo_dimension,t.descripcion_detalle,t.codigo_empleado,t.registro_detalle from prc_detalle_procedimiento t  where 1=1";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  69 */       if (id_procedimiento > 0) {
/*  70 */         s = s + "   and t.id_procedimiento = " + id_procedimiento;
/*     */       }
/*     */       
/*  73 */       s = s + " order by 1";
/*  74 */       boolean rtaDB = dat.parseSql(s);
/*  75 */       if (!rtaDB) {
/*  76 */         return resultados;
/*     */       }
/*  78 */       ResultSet rs = dat.getResultSet();
/*  79 */       while (rs.next()) {
/*  80 */         resultados.add(leerRegistro(rs));
/*     */       }
/*     */     }
/*  83 */     catch (Exception e) {
/*  84 */       e.printStackTrace();
/*  85 */       Utilidades.writeError("PrcDetalleProcedimientoDAO:cargarTodos ", e);
/*     */     } finally {
/*     */       
/*  88 */       dat.close();
/*     */     } 
/*  90 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PrcDetalleProcedimientoDTO cargarRegistro(int idDetalleProcedimiento) {
/* 100 */  DBManager   dat = new DBManager();
/*     */     try {
/* 102 */       String s = "select t.id_detalle_procedimiento,t.tipo_dimension,m1.DESCRIPCION as nombre_tipo_dimension,t.id_procedimiento,m2.objetivo as nombre_id_procedimiento,t.descripcion_detalle,t.codigo_empleado,r3.apellidos as nombre_codigo_empleado,t.registro_detalle,t.estado,m4.descripcion as nombre_estado,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion from prc_detalle_procedimiento t  left join sis_multivalores m1 on (m1.tabla='TIPO_DIMENSION' and m1.VALOR=t.tipo_dimension) left join sis_multivalores m2 on (m2.tabla='prc_procedimiento' and m2.id_procedimiento=t.id_procedimiento) left join sis_usuarios r3 on (r3.codigo_empleado=t.codigo_empleado) left join sis_multivalores m4 on (m4.tabla='estado_activo_inactivo' and m4.valor=t.estado) where  t.id_detalle_procedimiento=" + idDetalleProcedimiento + "";
/*     */ 
/*     */ 
 
/*     */       
/* 126 */       boolean rtaDB = dat.parseSql(s);
/* 127 */       if (!rtaDB) {
/* 128 */         return null;
/*     */       }
/* 130 */       ResultSet rs = dat.getResultSet();
/* 131 */       if (rs.next()) {
/* 132 */         return leerRegistro(rs);
/*     */       }
/*     */     }
/* 135 */     catch (Exception e) {
/* 136 */       e.printStackTrace();
/* 137 */       Utilidades.writeError("PrcDetalleProcedimientoDAO:cargarPrcDetalleProcedimiento", e);
/*     */     } finally {
/*     */       
/* 140 */       dat.close();
/*     */     } 
/* 142 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int siguienteRegistro() {
/* 151 */     int inumero = 1;
/* 152 */     String s = "select max(id_detalle_procedimiento) from prc_detalle_procedimiento ";
/*     */ 
/*     */     
/* 155 */   DBManager  dat = new DBManager();
/*     */     try {
/* 157 */       boolean rta = dat.parseSql(s);
/* 158 */       if (!rta) return 0; 
/* 159 */       ResultSet rs = dat.getResultSet();
/* 160 */       if (rs.next()) {
/* 161 */         s = rs.getString(1);
/* 162 */         if (!rs.wasNull()) {
/* 163 */           inumero = Integer.parseInt(s) + 1;
/*     */         }
/*     */       } 
/* 166 */       return inumero;
/*     */     }
/* 168 */     catch (Exception e) {
/* 169 */       e.printStackTrace();
/* 170 */       Utilidades.writeError("PrcDetalleProcedimientoDAO:siguienteRegistro ", e);
/*     */     } finally {
/*     */       
/* 173 */       dat.close();
/*     */     } 
/* 175 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarRegistro(int idDetalleProcedimiento) {
/* 185 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 187 */  DBManager   dat = new DBManager();
/*     */     try {
/* 189 */       String s = "delete from prc_detalle_procedimiento where  id_detalle_procedimiento=" + idDetalleProcedimiento + "";
/*     */ 
/*     */ 
/*     */       
/* 193 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 195 */     catch (Exception e) {
/* 196 */       e.printStackTrace();
/* 197 */       Utilidades.writeError("PrcDetalleProcedimientoDAO:eliminarRegistro ", e);
/* 198 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 201 */       dat.close();
/*     */     } 
/* 203 */     return rta;
/*     */   }
/*     */ 
 
/*     */   
/*     */   public RespuestaBD crearRegistro(int idDetalleProcedimiento, String tipoDimension, int idProcedimiento, String descripcionDetalle, int codigoEmpleado, String registroDetalle, String estado, String usuarioInsercion) {
/* 221 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 223 */     int elSiguiente = siguienteRegistro();
/* 224 */     if (elSiguiente == 0) {
/* 225 */       rta.setMensaje("Generando secuencia");
/* 226 */       return rta;
/*     */     } 
/*     */     
/* 229 */    DBManager dat = new DBManager();
/*     */     try {
/* 231 */       String s = "insert into prc_detalle_procedimiento(id_detalle_procedimiento,tipo_dimension,id_procedimiento,descripcion_detalle,codigo_empleado,registro_detalle,estado,usuario_insercion,fecha_insercion) values (" + elSiguiente + "," + "'" + tipoDimension + "'," + "" + idProcedimiento + "," + "'" + descripcionDetalle + "'," + "" + codigoEmpleado + "," + "'" + registroDetalle + "'," + "'" + estado + "'," + "'" + usuarioInsercion + "'," + "" + Utilidades.getFechaBD() + "" + ")";
/*     */ 
/*     */ 
 
/* 252 */       rta = dat.executeUpdate2(s);
/* 253 */       rta.setSecuencia(elSiguiente);
/*     */     }
/* 255 */     catch (Exception e) {
/* 256 */       e.printStackTrace();
/* 257 */       Utilidades.writeError("%PrcDetalleProcedimientoDAO:crearRegistro ", e);
/* 258 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 261 */       dat.close();
/*     */     } 
/* 263 */     return rta;
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
/*     */   public RespuestaBD modificarRegistro(int idDetalleProcedimiento, String tipoDimension, int idProcedimiento, String descripcionDetalle, int codigoEmpleado, String registroDetalle, String estado, String usuarioModificacion) {
/* 281 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 283 */   DBManager  dat = new DBManager();
/*     */     try {
/* 285 */       String s = "update prc_detalle_procedimiento set  tipo_dimension='" + tipoDimension + "'," + " id_procedimiento=" + idProcedimiento + "," + " descripcion_detalle='" + descripcionDetalle + "'," + " codigo_empleado=" + codigoEmpleado + "," + " registro_detalle='" + registroDetalle + "'," + " estado='" + estado + "'," + " usuario_modificacion='" + usuarioModificacion + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "" + " where" + " id_detalle_procedimiento=" + idDetalleProcedimiento + "";
/*     */ 
/*     */       
/* 297 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 299 */     catch (Exception e) {
/* 300 */       e.printStackTrace();
/* 301 */       Utilidades.writeError("PrcDetalleProcedimientoDAO:modificarRegistro ", e);
/* 302 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 305 */       dat.close();
/*     */     } 
/* 307 */     return rta;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\PrcDetalleProcedimientoDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */