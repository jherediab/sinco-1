/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.PoaLogrosDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.IndicadoresEficienciaDAO;
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
/*     */ public class IndicadoresEficienciaDAO
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
/*  50 */       Utilidades.writeError("PoaLogrosDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PoaLogrosDTO next() {
/*     */     try {
/*  61 */       if (this.rs.next()) {
/*  62 */         return leerRegistro();
/*     */       }
/*     */     }
/*  65 */     catch (Exception e) {
/*  66 */       e.printStackTrace();
/*  67 */       Utilidades.writeError("PoaLogrosDAO:next ", e);
/*     */     } 
/*  69 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PoaLogrosDTO leerRegistro() {
/*     */     try {
/*  79 */       PoaLogrosDTO reg = new PoaLogrosDTO();
/*     */       
/*  81 */       reg.setIdLogro(this.rs.getInt("id_logro"));
/*  82 */       reg.setCodigoPoa(this.rs.getInt("codigo_poa"));
/*  83 */       reg.setMes(this.rs.getString("mes"));
/*  84 */       reg.setEjecucion(this.rs.getInt("ejecucion"));
/*  85 */       reg.setAvances(this.rs.getString("avances"));
/*  86 */       reg.setLogrosYResultados(this.rs.getString("logros_y_resultados"));
/*  87 */       reg.setRetrasosDificultades(this.rs.getString("retrasos_dificultades"));
/*  88 */       reg.setEstado(this.rs.getString("estado"));
/*  89 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  90 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  91 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  92 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  93 */       return reg;
/*     */     }
/*  95 */     catch (Exception e) {
/*  96 */       e.printStackTrace();
/*  97 */       Utilidades.writeError("PoaLogrosDAO:leerRegistro ", e);
/*     */       
/*  99 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<PoaLogrosDTO> cargarTodos() {
/* 109 */     Collection<PoaLogrosDTO> resultados = new ArrayList<PoaLogrosDTO>();
/*     */     try {
/* 111 */       String s = "select t.id_logro,t.codigo_poa,t.mes,t.ejecucion,t.avances,t.logros_y_resultados,t.retrasos_dificultades,t.estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from POA_LOGROS t  where 1=1";
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
/* 138 */       Utilidades.writeError("PoaLogrosDAO:cargarTodos ", e);
/*     */     } 
/* 140 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PoaLogrosDTO cargarRegistro(int idLogro) {
/*     */     try {
/* 150 */       String s = "select t.id_logro,t.codigo_poa,t.mes,t.ejecucion,t.avances,t.logros_y_resultados,t.retrasos_dificultades,t.estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from POA_LOGROS t  where  t.id_logro=" + idLogro + "";
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
/* 167 */       boolean rtaDB = this.dat.parseSql(s);
/* 168 */       if (!rtaDB) {
/* 169 */         return null;
/*     */       }
/* 171 */       this.rs = this.dat.getResultSet();
/* 172 */       if (this.rs.next()) {
/* 173 */         return leerRegistro();
/*     */       }
/*     */     }
/* 176 */     catch (Exception e) {
/* 177 */       e.printStackTrace();
/* 178 */       Utilidades.writeError("PoaLogrosDAO:cargarPoaLogros", e);
/*     */     } 
/* 180 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PoaLogrosDTO cargarRegistro(int idPoa, String mes) {
/*     */     try {
/* 188 */       String s = "select t.id_logro,t.codigo_poa,t.mes,t.ejecucion,t.avances,t.logros_y_resultados,t.retrasos_dificultades,t.estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from POA_LOGROS t  where  t.codigo_poa=" + idPoa + "and t.mes= '" + mes + "'" + "";
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
/* 206 */       boolean rtaDB = this.dat.parseSql(s);
/* 207 */       if (!rtaDB) {
/* 208 */         return null;
/*     */       }
/* 210 */       this.rs = this.dat.getResultSet();
/* 211 */       if (this.rs.next()) {
/* 212 */         return leerRegistro();
/*     */       }
/*     */     }
/* 215 */     catch (Exception e) {
/* 216 */       e.printStackTrace();
/* 217 */       Utilidades.writeError("PoaLogrosDAO:cargarPoaLogros", e);
/*     */     } 
/* 219 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int siguienteRegistro() {
/* 228 */     int inumero = 1;
/* 229 */     String s = "select max(id_logro) from POA_LOGROS ";
/*     */     
/*     */     try {
/* 232 */       boolean rta = this.dat.parseSql(s);
/* 233 */       if (!rta) return 0; 
/* 234 */       this.rs = this.dat.getResultSet();
/* 235 */       if (this.rs.next()) {
/* 236 */         s = this.rs.getString(1);
/* 237 */         if (!this.rs.wasNull()) {
/* 238 */           inumero = Integer.parseInt(s) + 1;
/*     */         }
/*     */       } 
/* 241 */       return inumero;
/*     */     }
/* 243 */     catch (Exception e) {
/* 244 */       e.printStackTrace();
/* 245 */       Utilidades.writeError("PoaLogrosDAO:siguienteRegistro ", e);
/*     */       
/* 247 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarRegistro(int idLogro) {
/* 257 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 260 */       String s = "delete from POA_LOGROS where  id_logro=" + idLogro + "";
/*     */ 
/*     */ 
/*     */       
/* 264 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 266 */     catch (Exception e) {
/* 267 */       e.printStackTrace();
/* 268 */       Utilidades.writeError("PoaLogrosDAO:eliminarRegistro ", e);
/* 269 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 271 */     return rta;
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
/*     */   public RespuestaBD crearRegistro(int idLogro, int codigoPoa, String mes, int ejecucion, String avances, String logrosYResultados, String retrasosDificultades, String estado, String usuarioInsercion) {
/* 290 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 292 */     int elSiguiente = siguienteRegistro();
/* 293 */     if (elSiguiente == 0) {
/* 294 */       rta.setMensaje("Generando secuencia");
/* 295 */       return rta;
/*     */     } 
/*     */     
/*     */     try {
/* 299 */       String s = "insert into POA_LOGROS(id_logro,codigo_poa,mes,ejecucion,avances,logros_y_resultados,retrasos_dificultades,estado,fecha_insercion,usuario_insercion) values (" + elSiguiente + "," + "" + codigoPoa + "," + "'" + mes + "'," + "" + ejecucion + "," + "'" + avances + "'," + "'" + logrosYResultados + "'," + "'" + retrasosDificultades + "'," + "'" + estado + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
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
/* 322 */       rta = this.dat.executeUpdate2(s);
/* 323 */       rta.setSecuencia(elSiguiente);
/*     */     }
/* 325 */     catch (Exception e) {
/* 326 */       e.printStackTrace();
/* 327 */       Utilidades.writeError("%PoaLogrosDAO:crearRegistro ", e);
/* 328 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 330 */     return rta;
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
/*     */   public RespuestaBD modificarRegistro(int idLogro, int codigoPoa, String mes, int ejecucion, String avances, String logrosYResultados, String retrasosDificultades, String estado, String usuarioModificacion) {
/* 349 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 352 */       String s = "update POA_LOGROS set  codigo_poa=" + codigoPoa + "," + " mes='" + mes + "'," + " ejecucion=" + ejecucion + "," + " avances='" + avances + "'," + " logros_y_resultados='" + logrosYResultados + "'," + " retrasos_dificultades='" + retrasosDificultades + "'," + " estado='" + estado + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " id_logro=" + idLogro + "";
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
/* 365 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 367 */     catch (Exception e) {
/* 368 */       e.printStackTrace();
/* 369 */       Utilidades.writeError("PoaLogrosDAO:modificarRegistro ", e);
/* 370 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 372 */     return rta;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\IndicadoresEficienciaDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */