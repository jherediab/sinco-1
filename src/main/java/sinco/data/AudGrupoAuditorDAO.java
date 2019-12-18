/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.AudGrupoAuditorDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.AudGrupoAuditorDAO;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AudGrupoAuditorDAO
/*     */ {
/*     */   public AudGrupoAuditorDTO leerRegistro(ResultSet rs) {
/*     */     try {
/*  35 */       AudGrupoAuditorDTO reg = new AudGrupoAuditorDTO();
/*     */       
/*  37 */       reg.setCodigoEmpleado(rs.getInt("codigo_empleado"));
/*  38 */       reg.setConsecutivo(rs.getInt("consecutivo"));
/*  39 */       reg.setRol(rs.getString("rol"));
/*  40 */       reg.setCiclo(rs.getInt("ciclo"));
/*  41 */       reg.setCodigoProceso(rs.getString("codigo_proceso"));
/*  42 */       reg.setCodigoInforme(rs.getInt("codigo_informe"));
/*  43 */       reg.setAsociadoA(rs.getString("asociado_a"));
/*  44 */       reg.setFechaInsercion(rs.getString("fecha_insercion"));
/*  45 */       reg.setUsuarioInsercion(rs.getString("usuario_insercion"));
/*  46 */       reg.setFechaModificacion(rs.getString("fecha_modificacion"));
/*  47 */       reg.setUsuarioModificacion(rs.getString("usuario_modificacion"));
/*  48 */       reg.setNombreCodigoEmpleado(rs.getString("nombre_codigo_empleado"));
/*  49 */       reg.setNombreRol(rs.getString("nombre_rol"));
/*  50 */       return reg;
/*     */     }
/*  52 */     catch (Exception e) {
/*  53 */       e.printStackTrace();
/*  54 */       Utilidades.writeError("AudGrupoAuditorDAO:leerRegistro ", e);
/*     */       
/*  56 */       return null;
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
/*     */   public Collection<AudGrupoAuditorDTO> cargarTodos(int ciclo, String codigoPadreProceso, int codigoPadreInforme, String asociadoA) {
/*  69 */     Collection<AudGrupoAuditorDTO> resultados = new ArrayList<AudGrupoAuditorDTO>();
/*     */     
/*  71 */    DBManager dat = new DBManager();
/*     */     try {
/*  73 */       String s = "select t.codigo_empleado,r1.apellidos||' '||r1.nombres as nombre_codigo_empleado,t.consecutivo,t.rol,m2.DESCRIPCION as nombre_rol,t.ciclo,t.codigo_proceso,t.codigo_informe,t.asociado_a,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from aud_grupo_auditor t  left join sis_usuarios r1 on (r1.codigo_empleado=t.codigo_empleado) left join sis_multivalores m2 on (m2.tabla='ROL_AUDITOR' and m2.VALOR=t.rol) where 1=1";
/*     */ 

/*     */       
/*  91 */       if (ciclo > 0) {
/*  92 */         s = s + " and t.ciclo=" + ciclo;
/*     */       }
/*  94 */       if (asociadoA.equals("P")) {
/*  95 */         s = s + " and t.codigo_proceso='" + codigoPadreProceso + "'";
/*     */       }
/*  97 */       if (asociadoA.equals("I")) {
/*  98 */         s = s + " and t.codigo_informe=" + codigoPadreInforme;
/*     */       }
/* 100 */       if (asociadoA.length() > 0) {
/* 101 */         s = s + " and t.asociado_a='" + asociadoA + "'";
/*     */       }
/* 103 */       s = s + " order by t.rol,t.codigo_empleado";
/* 104 */       boolean rtaDB = dat.parseSql(s);
/* 105 */       if (!rtaDB) {
/* 106 */         return resultados;
/*     */       }
/* 108 */       ResultSet rs = dat.getResultSet();
/* 109 */       while (rs.next()) {
/* 110 */         resultados.add(leerRegistro(rs));
/*     */       }
/*     */     }
/* 113 */     catch (Exception e) {
/* 114 */       e.printStackTrace();
/* 115 */       Utilidades.writeError("AudGrupoAuditorDAO:cargarTodos ", e);
/*     */     } finally {
/*     */       
/* 118 */       dat.close();
/*     */     } 
/* 120 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AudGrupoAuditorDTO cargarRegistro(int codigoEmpleado, int consecutivo) {
/* 131 */   DBManager  dat = new DBManager();
/*     */     try {
/* 133 */       String s = "select t.codigo_empleado,r1.apellidos||' '||r1.nombres as nombre_codigo_empleado,t.consecutivo,t.rol,m2.DESCRIPCION as nombre_rol,t.ciclo,t.codigo_proceso,t.codigo_informe,t.asociado_a,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from aud_grupo_auditor t  left join sis_usuarios r1 on (r1.codigo_empleado=t.codigo_empleado) left join sis_multivalores m2 on (m2.tabla='ROL_AUDITOR' and m2.VALOR=t.rol) where  t.codigo_empleado=" + codigoEmpleado + " and t.consecutivo=" + consecutivo + "";
/*     */ 

/*     */       
/* 154 */       boolean rtaDB = dat.parseSql(s);
/* 155 */       if (!rtaDB) {
/* 156 */         return null;
/*     */       }
/* 158 */       ResultSet rs = dat.getResultSet();
/* 159 */       if (rs.next()) {
/* 160 */         return leerRegistro(rs);
/*     */       }
/*     */     }
/* 163 */     catch (Exception e) {
/* 164 */       e.printStackTrace();
/* 165 */       Utilidades.writeError("AudGrupoAuditorDAO:cargarAudGrupoAuditor", e);
/*     */     } finally {
/*     */       
/* 168 */       dat.close();
/*     */     } 
/* 170 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int siguienteRegistro(int codigoEmpleado) {
/* 179 */     int inumero = 1;
/* 180 */     String s = "select max(consecutivo) from aud_grupo_auditor  where  codigo_empleado=" + codigoEmpleado + "";
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 185 */   DBManager  dat = new DBManager();
/*     */     try {
/* 187 */       boolean rta = dat.parseSql(s);
/* 188 */       if (!rta) return 0; 
/* 189 */       ResultSet rs = dat.getResultSet();
/* 190 */       if (rs.next()) {
/* 191 */         s = rs.getString(1);
/* 192 */         if (!rs.wasNull()) {
/* 193 */           inumero = Integer.parseInt(s) + 1;
/*     */         }
/*     */       } 
/* 196 */       return inumero;
/*     */     }
/* 198 */     catch (Exception e) {
/* 199 */       e.printStackTrace();
/* 200 */       Utilidades.writeError("AudGrupoAuditorDAO:siguienteRegistro ", e);
/*     */     } finally {
/*     */       
/* 203 */       dat.close();
/*     */     } 
/* 205 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarRegistro(int codigoEmpleado, int consecutivo) {
/* 216 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 218 */   DBManager  dat = new DBManager();
/*     */     try {
/* 220 */       String s = "delete from aud_grupo_auditor where  codigo_empleado=" + codigoEmpleado + "  and consecutivo=" + consecutivo + "";
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 225 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 227 */     catch (Exception e) {
/* 228 */       e.printStackTrace();
/* 229 */       Utilidades.writeError("AudGrupoAuditorDAO:eliminarRegistro ", e);
/* 230 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 233 */       dat.close();
/*     */     } 
/* 235 */     return rta;
/*     */   }
/*     */ 

/*     */   
/*     */   public RespuestaBD crearRegistro(int codigoEmpleado, int consecutivo, String rol, int ciclo, String codigoProceso, int codigoInforme, String asociadoA, String usuarioInsercion) {
/* 253 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 255 */     int elSiguiente = siguienteRegistro(codigoEmpleado);
/* 256 */     if (elSiguiente == 0) {
/* 257 */       rta.setMensaje("Generando secuencia");
/* 258 */       return rta;
/*     */     } 
/*     */     
/* 261 */   DBManager  dat = new DBManager();
/*     */     try {
/* 263 */       String s = "insert into aud_grupo_auditor(codigo_empleado,consecutivo,rol,ciclo,codigo_proceso,codigo_informe,asociado_a,fecha_insercion,usuario_insercion) values (" + codigoEmpleado + "," + "" + elSiguiente + "," + "'" + rol + "'," + "" + ciclo + "," + "'" + codigoProceso + "'," + "" + codigoInforme + "," + "'" + asociadoA + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
/*     */ 

/*     */       
/* 284 */       rta = dat.executeUpdate2(s);
/* 285 */       rta.setSecuencia(elSiguiente);
/*     */     }
/* 287 */     catch (Exception e) {
/* 288 */       e.printStackTrace();
/* 289 */       Utilidades.writeError("%AudGrupoAuditorDAO:crearRegistro ", e);
/* 290 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 293 */       dat.close();
/*     */     } 
/* 295 */     return rta;
/*     */   }
/*     */ 

/*     */   
/*     */   public RespuestaBD modificarRegistro(int codigoEmpleado, int consecutivo, String rol, int ciclo, String codigoProceso, int codigoInforme, String asociadoA, String usuarioModificacion) {
/* 313 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 315 */   DBManager  dat = new DBManager();
/*     */     try {
/* 317 */       String s = "update aud_grupo_auditor set  rol='" + rol + "'," + " ciclo=" + ciclo + "," + " codigo_proceso='" + codigoProceso + "'," + " codigo_informe=" + codigoInforme + "," + " asociado_a='" + asociadoA + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " codigo_empleado=" + codigoEmpleado + " and consecutivo=" + consecutivo + "";
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
/* 329 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 331 */     catch (Exception e) {
/* 332 */       e.printStackTrace();
/* 333 */       Utilidades.writeError("AudGrupoAuditorDAO:modificarRegistro ", e);
/* 334 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 337 */       dat.close();
/*     */     } 
/* 339 */     return rta;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\AudGrupoAuditorDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */