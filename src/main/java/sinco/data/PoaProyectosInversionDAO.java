/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.PoaProyectosInversionDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.PoaProyectosInversionDAO;
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
/*     */ public class PoaProyectosInversionDAO
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
/*  50 */       Utilidades.writeError("PoaProyectosInversionDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PoaProyectosInversionDTO next() {
/*     */     try {
/*  61 */       if (this.rs.next()) {
/*  62 */         return leerRegistro();
/*     */       }
/*     */     }
/*  65 */     catch (Exception e) {
/*  66 */       e.printStackTrace();
/*  67 */       Utilidades.writeError("PoaProyectosInversionDAO:next ", e);
/*     */     } 
/*  69 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PoaProyectosInversionDTO leerRegistro() {
/*     */     try {
/*  79 */       PoaProyectosInversionDTO reg = new PoaProyectosInversionDTO();
/*     */       
/*  81 */       reg.setCodigoProyectoInversion(this.rs.getString("codigo_proyecto_inversion"));
/*  82 */       reg.setDescripcion(this.rs.getString("descripcion"));
/*  83 */       reg.setObjetivo(this.rs.getString("objetivo_proyecto"));
/*  84 */       reg.setFechaRadicado(this.rs.getString("fecha_radicado"));
/*  85 */       reg.setValor(this.rs.getDouble("valor_proyecto"));
/*  86 */       reg.setFechaInicio(this.rs.getString("fecha_inicio"));
/*  87 */       reg.setFechaTerminacion(this.rs.getString("fecha_terminacion"));
/*  88 */       reg.setFase(this.rs.getString("fase"));
/*  89 */       reg.setEjecutor(this.rs.getString("ejecutor"));
/*  90 */       reg.setFuente(this.rs.getString("fuente"));
/*  91 */       reg.setEstado(this.rs.getString("estado"));
/*  92 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  93 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  94 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  95 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  96 */       reg.setNombreEstado(this.rs.getString("nombre_estado"));
/*  97 */       return reg;
/*     */     }
/*  99 */     catch (Exception e) {
/* 100 */       e.printStackTrace();
/* 101 */       Utilidades.writeError("PoaProyectosInversionDAO:leerRegistro ", e);
/*     */       
/* 103 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<PoaProyectosInversionDTO> cargarTodos() {
/* 113 */     Collection<PoaProyectosInversionDTO> resultados = new ArrayList<PoaProyectosInversionDTO>();
/*     */     try {
/* 115 */       String s = "select t.codigo_proyecto_inversion,t.descripcion,t.objetivo_proyecto,t.fecha_radicado,t.valor_proyecto,t.fecha_inicio,t.fecha_terminacion,t.fase,t.ejecutor,t.fuente,t.estado,m1.DESCRIPCION as nombre_estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from POA_PROYECTOS_INVERSION t  left join sis_multivalores m1 on (m1.tabla='ESTADO_REGISTRO' and m1.VALOR=t.estado) where 1=1";
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
/* 135 */       s = s + " order by 1";
/* 136 */       boolean rtaDB = this.dat.parseSql(s);
/* 137 */       if (!rtaDB) {
/* 138 */         return resultados;
/*     */       }
/* 140 */       this.rs = this.dat.getResultSet();
/* 141 */       while (this.rs.next()) {
/* 142 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 145 */     catch (Exception e) {
/* 146 */       e.printStackTrace();
/* 147 */       Utilidades.writeError("PoaProyectosInversionDAO:cargarTodos ", e);
/*     */     } 
/* 149 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PoaProyectosInversionDTO cargarRegistro(String codigoProyectoInversion) {
/*     */     try {
/* 159 */       String s = "select t.codigo_proyecto_inversion,t.descripcion,t.objetivo_proyecto,t.fecha_radicado,t.valor_proyecto,t.fecha_inicio,t.fecha_terminacion,t.fase,t.ejecutor,t.fuente,t.estado,m1.DESCRIPCION as nombre_estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from POA_PROYECTOS_INVERSION t  left join sis_multivalores m1 on (m1.tabla='ESTADO_REGISTRO' and m1.VALOR=t.estado) where  t.codigo_proyecto_inversion='" + codigoProyectoInversion + "'" + "";
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
/*     */       
/* 181 */       boolean rtaDB = this.dat.parseSql(s);
/* 182 */       if (!rtaDB) {
/* 183 */         return null;
/*     */       }
/* 185 */       this.rs = this.dat.getResultSet();
/* 186 */       if (this.rs.next()) {
/* 187 */         return leerRegistro();
/*     */       }
/*     */     }
/* 190 */     catch (Exception e) {
/* 191 */       e.printStackTrace();
/* 192 */       Utilidades.writeError("PoaProyectosInversionDAO:cargarPoaProyectosInversion", e);
/*     */     } 
/* 194 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarRegistro(String codigoProyectoInversion) {
/* 204 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 207 */       String s = "delete from POA_PROYECTOS_INVERSION where  codigo_proyecto_inversion='" + codigoProyectoInversion + "'" + "";
/*     */ 
/*     */ 
/*     */       
/* 211 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 213 */     catch (Exception e) {
/* 214 */       e.printStackTrace();
/* 215 */       Utilidades.writeError("PoaProyectosInversionDAO:eliminarRegistro ", e);
/* 216 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 218 */     return rta;
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
/*     */   
/*     */   public RespuestaBD crearRegistro(String codigoProyectoInversion, String descripcion, String objetivo_proyecto, String fecha_radicado, double valor_proyecto, String fecha_inicio, String fecha_terminacion, String fase, String ejecutor, String fuente, String estado, String usuarioInsercion) {
/* 240 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 243 */       String s = "insert into POA_PROYECTOS_INVERSION(codigo_proyecto_inversion,descripcion,objetivo_proyecto,fecha_radicado,valor_proyecto,fecha_inicio,fecha_terminacion,fase,ejecutor,fuente,estado,fecha_insercion,usuario_insercion) values ('" + codigoProyectoInversion + "'," + "'" + descripcion + "'," + "'" + objetivo_proyecto + "'," + "'" + fecha_radicado + "'," + "" + valor_proyecto + "," + "'" + fecha_inicio + "'," + "'" + fecha_terminacion + "'," + "'" + fase + "'," + "'" + ejecutor + "'," + "'" + fuente + "'," + "'" + estado + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 272 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 274 */     catch (Exception e) {
/* 275 */       e.printStackTrace();
/* 276 */       Utilidades.writeError("%PoaProyectosInversionDAO:crearRegistro ", e);
/* 277 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 279 */     return rta;
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
/*     */   
/*     */   public RespuestaBD modificarRegistro(String codigoProyectoInversion, String descripcion, String objetivo_proyecto, String fecha_radicado, double valor_proyecto, String fecha_inicio, String fecha_terminacion, String fase, String ejecutor, String fuente, String estado, String usuarioModificacion) {
/* 301 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 304 */       String s = "update POA_PROYECTOS_INVERSION set  descripcion='" + descripcion + "'," + " objetivo_proyecto='" + objetivo_proyecto + "'," + " fecha_radicado='" + fecha_radicado + "'," + " valor_proyecto=" + valor_proyecto + "," + " fecha_inicio='" + fecha_inicio + "'," + " fecha_terminacion='" + fecha_terminacion + "'," + " fase='" + fase + "'," + " ejecutor='" + ejecutor + "'," + " fuente='" + fuente + "'," + " estado='" + estado + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " codigo_proyecto_inversion='" + codigoProyectoInversion + "'" + "";
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
/* 320 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 322 */     catch (Exception e) {
/* 323 */       e.printStackTrace();
/* 324 */       Utilidades.writeError("PoaProyectosInversionDAO:modificarRegistro ", e);
/* 325 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 327 */     return rta;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\PoaProyectosInversionDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */