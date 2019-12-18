/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.PoaProcesosDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.PoaProcesosDAO;
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
/*     */ public class PoaProcesosDAO
/*     */ {
/*     */   public PoaProcesosDTO leerRegistro(ResultSet rs) {
/*     */     try {
/*  35 */       PoaProcesosDTO reg = new PoaProcesosDTO();
/*     */       
/*  37 */       reg.setCodigoProceso(rs.getInt("codigo_proceso"));
/*  38 */       reg.setDescripcion(rs.getString("descripcion"));
/*  39 */       reg.setTipoProceso(rs.getString("tipo_proceso"));
/*  40 */       reg.setLiderProceso(rs.getString("lider_proceso"));
/*  41 */       reg.setEstado(rs.getString("estado"));
/*  42 */       reg.setFechaInsercion(rs.getString("fecha_insercion"));
/*  43 */       reg.setUsuarioInsercion(rs.getString("usuario_insercion"));
/*  44 */       reg.setFechaModificacion(rs.getString("fecha_modificacion"));
/*  45 */       reg.setUsuarioModificacion(rs.getString("usuario_modificacion"));
/*  46 */       reg.setNombreTipoProceso(rs.getString("nombre_tipo_proceso"));
/*  47 */       reg.setNombreLiderProceso(rs.getString("nombre_lider_proceso"));
/*  48 */       reg.setNombreEstado(rs.getString("nombre_estado"));
/*  49 */       return reg;
/*     */     }
/*  51 */     catch (Exception e) {
/*  52 */       e.printStackTrace();
/*  53 */       Utilidades.writeError("PoaProcesosDAO:leerRegistro ", e);
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
/*     */   
/*     */   public Collection<PoaProcesosDTO> cargarTodos(String descripcion, String tipoProceso) {
/*  66 */     Collection<PoaProcesosDTO> resultados = new ArrayList<PoaProcesosDTO>();
/*     */     
/*  68 */     DBManager dat = new DBManager();
/*     */     try {
/*  70 */       String s = "select t.codigo_proceso,t.descripcion,t.tipo_proceso,m1.DESCRIPCION as nombre_tipo_proceso,t.lider_proceso,m2.DESCRIPCION as nombre_lider_proceso,t.estado,m3.DESCRIPCION as nombre_estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from poa_procesos t  left join sis_multivalores m1 on (m1.tabla='CAL_TIPO_PROCESO' and m1.VALOR=t.tipo_proceso) left join sis_multivalores m2 on (m2.tabla='CAL_LIDER_PROCESO' and m2.VALOR=t.lider_proceso) left join sis_multivalores m3 on (m3.tabla='ESTADO_REGISTRO' and m3.VALOR=t.estado) where 1=1";
/*     */ 

/*     */       
/*  88 */       if (descripcion.length() > 0) {
/*  89 */         s = s + " and upper(t.descripcion) like upper('%" + descripcion + "%')";
/*     */       }
/*  91 */       if (tipoProceso.length() > 0) {
/*  92 */         s = s + " and upper(t.tipo_proceso) like upper('%" + tipoProceso + "%')";
/*     */       }
/*  94 */       s = s + " order by 1";
/*  95 */       boolean rtaDB = dat.parseSql(s);
/*  96 */       if (!rtaDB) {
/*  97 */         return resultados;
/*     */       }
/*  99 */       ResultSet rs = dat.getResultSet();
/* 100 */       while (rs.next()) {
/* 101 */         resultados.add(leerRegistro(rs));
/*     */       }
/*     */     }
/* 104 */     catch (Exception e) {
/* 105 */       e.printStackTrace();
/* 106 */       Utilidades.writeError("PoaProcesosDAO:cargarTodos ", e);
/*     */     } finally {
/*     */       
/* 109 */       dat.close();
/*     */     } 
/* 111 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PoaProcesosDTO cargarRegistro(int codigoProceso) {
/* 121 */   DBManager  dat = new DBManager();
/*     */     try {
/* 123 */       String s = "select t.codigo_proceso,t.descripcion,t.tipo_proceso,m1.DESCRIPCION as nombre_tipo_proceso,t.lider_proceso,m2.DESCRIPCION as nombre_lider_proceso,t.estado,m3.DESCRIPCION as nombre_estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from poa_procesos t  left join sis_multivalores m1 on (m1.tabla='CAL_TIPO_PROCESO' and m1.VALOR=t.tipo_proceso) left join sis_multivalores m2 on (m2.tabla='CAL_LIDER_PROCESO' and m2.VALOR=t.lider_proceso) left join sis_multivalores m3 on (m3.tabla='ESTADO_REGISTRO' and m3.VALOR=t.estado) where  t.codigo_proceso=" + codigoProceso + "";
/*     */ 

/*     */       
/* 143 */       boolean rtaDB = dat.parseSql(s);
/* 144 */       if (!rtaDB) {
/* 145 */         return null;
/*     */       }
/* 147 */       ResultSet rs = dat.getResultSet();
/* 148 */       if (rs.next()) {
/* 149 */         return leerRegistro(rs);
/*     */       }
/*     */     }
/* 152 */     catch (Exception e) {
/* 153 */       e.printStackTrace();
/* 154 */       Utilidades.writeError("PoaProcesosDAO:cargarPoaProcesos", e);
/*     */     } finally {
/*     */       
/* 157 */       dat.close();
/*     */     } 
/* 159 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int siguienteRegistro() {
/* 168 */     int inumero = 1;
/* 169 */     String s = "select max(codigo_proceso) from poa_procesos ";
/*     */ 
/*     */     
/* 172 */    DBManager dat = new DBManager();
/*     */     try {
/* 174 */       boolean rta = dat.parseSql(s);
/* 175 */       if (!rta) return 0; 
/* 176 */       ResultSet rs = dat.getResultSet();
/* 177 */       if (rs.next()) {
/* 178 */         s = rs.getString(1);
/* 179 */         if (!rs.wasNull()) {
/* 180 */           inumero = Integer.parseInt(s) + 1;
/*     */         }
/*     */       } 
/* 183 */       return inumero;
/*     */     }
/* 185 */     catch (Exception e) {
/* 186 */       e.printStackTrace();
/* 187 */       Utilidades.writeError("PoaProcesosDAO:siguienteRegistro ", e);
/*     */     } finally {
/*     */       
/* 190 */       dat.close();
/*     */     } 
/* 192 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarRegistro(int codigoProceso) {
/* 202 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 204 */   DBManager  dat = new DBManager();
/*     */     try {
/* 206 */       String s = "delete from poa_procesos where  codigo_proceso=" + codigoProceso + "";
/*     */ 
/*     */ 
/*     */       
/* 210 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 212 */     catch (Exception e) {
/* 213 */       e.printStackTrace();
/* 214 */       Utilidades.writeError("PoaProcesosDAO:eliminarRegistro ", e);
/* 215 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 218 */       dat.close();
/*     */     } 
/* 220 */     return rta;
/*     */   }
 
/*     */   
/*     */   public RespuestaBD crearRegistro(int codigoProceso, String descripcion, String tipoProceso, String liderProceso, String estado, String usuarioInsercion) {
/* 236 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 238 */     int elSiguiente = siguienteRegistro();
/* 239 */     if (elSiguiente == 0) {
/* 240 */       rta.setMensaje("Generando secuencia");
/* 241 */       return rta;
/*     */     } 
/*     */     
/* 244 */   DBManager  dat = new DBManager();
/*     */     try {
/* 246 */       String s = "insert into poa_procesos(codigo_proceso,descripcion,tipo_proceso,lider_proceso,estado,fecha_insercion,usuario_insercion) values (" + elSiguiente + "," + "'" + descripcion + "'," + "'" + tipoProceso + "'," + "'" + liderProceso + "'," + "'" + estado + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
/*     */ 
 
/*     */       
/* 263 */       rta = dat.executeUpdate2(s);
/* 264 */       rta.setSecuencia(elSiguiente);
/*     */     }
/* 266 */     catch (Exception e) {
/* 267 */       e.printStackTrace();
/* 268 */       Utilidades.writeError("%PoaProcesosDAO:crearRegistro ", e);
/* 269 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 272 */       dat.close();
/*     */     } 
/* 274 */     return rta;
/*     */   }
/*     */ 
 
/*     */   
/*     */   public RespuestaBD modificarRegistro(int codigoProceso, String descripcion, String tipoProceso, String liderProceso, String estado, String usuarioModificacion) {
/* 290 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 292 */   DBManager  dat = new DBManager();
/*     */     try {
/* 294 */       String s = "update poa_procesos set  descripcion='" + descripcion + "'," + " tipo_proceso='" + tipoProceso + "'," + " lider_proceso='" + liderProceso + "'," + " estado='" + estado + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " codigo_proceso=" + codigoProceso + "";
/*     */ 
 
/*     */ 
/*     */       
/* 304 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 306 */     catch (Exception e) {
/* 307 */       e.printStackTrace();
/* 308 */       Utilidades.writeError("PoaProcesosDAO:modificarRegistro ", e);
/* 309 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 312 */       dat.close();
/*     */     } 
/* 314 */     return rta;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\PoaProcesosDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */