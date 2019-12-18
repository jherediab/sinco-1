/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.PoaMaestroMultivaloresDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.PoaMaestroMultivaloresDAO;
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
/*     */ public class PoaMaestroMultivaloresDAO
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
/*  50 */       Utilidades.writeError("PoaMaestroMultivaloresDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PoaMaestroMultivaloresDTO next() {
/*     */     try {
/*  61 */       if (this.rs.next()) {
/*  62 */         return leerRegistro();
/*     */       }
/*     */     }
/*  65 */     catch (Exception e) {
/*  66 */       e.printStackTrace();
/*  67 */       Utilidades.writeError("PoaMaestroMultivaloresDAO:next ", e);
/*     */     } 
/*  69 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PoaMaestroMultivaloresDTO leerRegistro() {
/*     */     try {
/*  79 */       PoaMaestroMultivaloresDTO reg = new PoaMaestroMultivaloresDTO();
/*     */       
/*  81 */       reg.setCodigoPoa(this.rs.getInt("codigo_poa"));
/*  82 */       reg.setTabla(this.rs.getString("tabla"));
/*  83 */       reg.setValor(this.rs.getString("valor"));
/*  84 */       reg.setEstado(this.rs.getString("estado"));
/*  85 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  86 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  87 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  88 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  89 */       return reg;
/*     */     }
/*  91 */     catch (Exception e) {
/*  92 */       e.printStackTrace();
/*  93 */       Utilidades.writeError("PoaMaestroMultivaloresDAO:leerRegistro ", e);
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
/*     */   public Collection<PoaMaestroMultivaloresDTO> cargarTodos() {
/* 105 */     Collection<PoaMaestroMultivaloresDTO> resultados = new ArrayList<PoaMaestroMultivaloresDTO>();
/*     */     try {
/* 107 */       String s = "select t.codigo_poa,t.codigo_poa_actividad,t.tabla,t.valor,t.estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from POA_MAESTRO_MULTIVALORES t  where 1=1";
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
/* 119 */       s = s + " order by 1";
/* 120 */       boolean rtaDB = this.dat.parseSql(s);
/* 121 */       if (!rtaDB) {
/* 122 */         return resultados;
/*     */       }
/* 124 */       this.rs = this.dat.getResultSet();
/* 125 */       while (this.rs.next()) {
/* 126 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 129 */     catch (Exception e) {
/* 130 */       e.printStackTrace();
/* 131 */       Utilidades.writeError("PoaMaestroMultivaloresDAO:cargarTodos ", e);
/*     */     } 
/* 133 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<PoaMaestroMultivaloresDTO> cargarTodos(int codigoPoa, String tabla) {
/* 139 */     Collection<PoaMaestroMultivaloresDTO> resultados = new ArrayList<PoaMaestroMultivaloresDTO>();
/*     */     try {
/* 141 */       String s = "select t.codigo_poa,t.codigo_poa_actividad,t.tabla,t.valor,t.estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from POA_MAESTRO_MULTIVALORES t  where t.codigo_poa_actividad=" + codigoPoa + " and t.tabla='" + tabla + "' ";
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
/* 154 */       s = s + " order by 1";
/* 155 */       boolean rtaDB = this.dat.parseSql(s);
/* 156 */       if (!rtaDB) {
/* 157 */         return resultados;
/*     */       }
/* 159 */       this.rs = this.dat.getResultSet();
/* 160 */       while (this.rs.next()) {
/* 161 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 164 */     catch (Exception e) {
/* 165 */       e.printStackTrace();
/* 166 */       Utilidades.writeError("PoaMaestroMultivaloresDAO:cargarTodos ", e);
/*     */     } 
/* 168 */     return resultados;
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
/*     */   public PoaMaestroMultivaloresDTO cargarRegistro(int codigoPoa, int codigoPoaActividad, String tabla, String valor) {
/*     */     try {
/* 181 */       String s = "select t.codigo_poa,t.codigo_poa_actividad,t.tabla,t.valor,t.estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from POA_MAESTRO_MULTIVALORES t  where  t.codigo_poa=" + codigoPoa + " and t.tabla='" + tabla + "'" + " and t.valor='" + valor + "'" + "";
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
/* 197 */       boolean rtaDB = this.dat.parseSql(s);
/* 198 */       if (!rtaDB) {
/* 199 */         return null;
/*     */       }
/* 201 */       this.rs = this.dat.getResultSet();
/* 202 */       if (this.rs.next()) {
/* 203 */         return leerRegistro();
/*     */       }
/*     */     }
/* 206 */     catch (Exception e) {
/* 207 */       e.printStackTrace();
/* 208 */       Utilidades.writeError("PoaMaestroMultivaloresDAO:cargarPoaMaestroMultivalores", e);
/*     */     } 
/* 210 */     return null;
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
/*     */   public RespuestaBD eliminarRegistro(int codigoPoa, int codigoPoaActividad, String tabla, String valor) {
/* 224 */     RespuestaBD rta = new RespuestaBD();
/*     */     try {
/* 226 */       String s = "delete from POA_MAESTRO_MULTIVALORES where  codigo_poa=" + codigoPoa + " and codigo_poa_actividad=" + codigoPoaActividad + "  and tabla='" + tabla + "'" + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 232 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 234 */     catch (Exception e) {
/* 235 */       e.printStackTrace();
/* 236 */       Utilidades.writeError("PoaMaestroMultivaloresDAO:eliminarRegistro ", e);
/* 237 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 239 */     return rta;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarRegistroPorActividad(int codigoPoaActividad) {
/* 250 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 253 */       String s = "delete from POA_MAESTRO_MULTIVALORES where  codigo_poa_actividad=" + codigoPoaActividad;
/*     */ 
/*     */       
/* 256 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 258 */     catch (Exception e) {
/* 259 */       e.printStackTrace();
/* 260 */       Utilidades.writeError("PoaMaestroMultivaloresDAO:eliminarRegistro ", e);
/* 261 */       rta.setMensaje(e.getMessage());
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
/*     */   public RespuestaBD crearRegistro(int codigoPoa, int codigoPoaActividad, String tabla, String valor, String estado, String usuarioInsercion) {
/* 279 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 282 */       String s = "insert into POA_MAESTRO_MULTIVALORES(codigo_poa,codigo_poa_actividad,tabla,valor,estado,fecha_insercion,usuario_insercion) values (" + codigoPoa + "," + "" + codigoPoaActividad + "," + "'" + tabla + "'," + "'" + valor + "'," + "'" + estado + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
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
/* 299 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 301 */     catch (Exception e) {
/* 302 */       e.printStackTrace();
/* 303 */       Utilidades.writeError("%PoaMaestroMultivaloresDAO:crearRegistro ", e);
/* 304 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 306 */     return rta;
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
/*     */   public RespuestaBD modificarRegistro(int codigoPoa, int codigoPoaActividad, String tabla, String valor, String estado, String usuarioModificacion) {
/* 322 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 325 */       String s = "update POA_MAESTRO_MULTIVALORES set  estado='" + estado + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " codigo_poa=" + codigoPoa + " codigo_poa_actividad=" + codigoPoaActividad + " and tabla='" + tabla + "'" + " and valor='" + valor + "'" + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 335 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 337 */     catch (Exception e) {
/* 338 */       e.printStackTrace();
/* 339 */       Utilidades.writeError("PoaMaestroMultivaloresDAO:modificarRegistro ", e);
/* 340 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 342 */     return rta;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\PoaMaestroMultivaloresDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */