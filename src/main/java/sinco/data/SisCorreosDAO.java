/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.SisCorreosDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.SisCorreosDAO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SisCorreosDAO
/*     */ {
/*     */   ResultSet rs;
/*  19 */   DBManager dat = new DBManager();
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
/*  30 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/*     */     try {
/*  37 */       this.dat.close();
/*     */     }
/*  39 */     catch (Exception e) {
/*  40 */       Utilidades.writeError("SisCorreosDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SisCorreosDTO next() {
/*     */     try {
/*  51 */       if (this.rs.next()) {
/*  52 */         return leerRegistro();
/*     */       }
/*     */     }
/*  55 */     catch (Exception e) {
/*  56 */       e.printStackTrace();
/*  57 */       Utilidades.writeError("SisCorreosDAO:next ", e);
/*     */     } 
/*  59 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SisCorreosDTO leerRegistro() {
/*     */     try {
/*  69 */       SisCorreosDTO reg = new SisCorreosDTO();
/*     */       
/*  71 */       reg.setConsecutivo(this.rs.getInt("consecutivo"));
/*  72 */       reg.setOrigen(this.rs.getString("origen"));
/*  73 */       reg.setDestino(this.rs.getString("destino"));
/*  74 */       reg.setAsunto(this.rs.getString("asunto"));
/*  75 */       reg.setMensaje(this.rs.getString("mensaje"));
/*  76 */       reg.setEstado(this.rs.getString("estado"));
/*  77 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  78 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  79 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  80 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  81 */       return reg;
/*     */     }
/*  83 */     catch (Exception e) {
/*  84 */       e.printStackTrace();
/*  85 */       Utilidades.writeError("SisCorreosDAO:leerRegistro ", e);
/*     */       
/*  87 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<SisCorreosDTO> cargarPendientes(int numeroSolicitud) {
/*  97 */     Collection<SisCorreosDTO> resultados = new ArrayList<SisCorreosDTO>();
/*     */     try {
/*  99 */       String s = "select  consecutivo,origen,destino,asunto,mensaje,estado,usuario_insercion,fecha_insercion,usuario_modificacion,fecha_modificacion from sis_correos where estado='P' and aplicacion='S' and numero_solicitud=" + numeroSolicitud + " order by consecutivo";
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
/* 114 */       boolean rtaDB = this.dat.parseSql(s);
/* 115 */       if (!rtaDB) {
/* 116 */         return resultados;
/*     */       }
/* 118 */       this.rs = this.dat.getResultSet();
/* 119 */       while (this.rs.next()) {
/* 120 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 123 */     catch (Exception e) {
/* 124 */       e.printStackTrace();
/* 125 */       Utilidades.writeError("SisCorreosDAO:cargarTodos ", e);
/*     */     } 
/* 127 */     return resultados;
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
/*     */   public boolean crearRegistro(int consecutivo, String origen, String destino, String asunto, String mensaje, String estado, String usuarioInsercion) {
/*     */     try {
/* 150 */       String s = "insert into sis_correos(consecutivo,origen,destino,asunto,mensaje,estado,usuario_insercion,fecha_insercion) values (" + consecutivo + "," + "'" + origen + "'," + "'" + destino + "'," + "'" + asunto + "'," + "'" + mensaje + "'," + "'" + estado + "'," + "'" + usuarioInsercion + "'," + "" + Utilidades.getFechaBD() + "" + ")";
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
/* 169 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 172 */     catch (Exception e) {
/* 173 */       e.printStackTrace();
/* 174 */       Utilidades.writeError("%SisCorreosDAO:crearRegistro ", e);
/*     */       
/* 176 */       return false;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean modificarRegistro(int consecutivo, String origen, String destino, String asunto, String mensaje, String estado, String usuarioModificacion) {
/*     */     try {
/* 194 */       String s = "update sis_correos set  origen='" + origen + "'," + " destino='" + destino + "'," + " asunto='" + asunto + "'," + " mensaje='" + mensaje + "'," + " estado='" + estado + "'," + " usuario_modificacion='" + usuarioModificacion + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "" + " where" + " consecutivo=" + consecutivo + "";
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
/* 205 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 208 */     catch (Exception e) {
/* 209 */       e.printStackTrace();
/* 210 */       Utilidades.writeError("SisCorreosDAO:modificarRegistro ", e);
/*     */       
/* 212 */       return false;
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
/*     */   public boolean actualizarEstado(int consecutivo, String estado, String usuarioModificacion) {
/*     */     try {
/* 226 */       String s = "update sis_correos set  estado='" + estado + "'," + " usuario_modificacion='" + usuarioModificacion + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "" + " where" + " consecutivo=" + consecutivo + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 233 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 236 */     catch (Exception e) {
/* 237 */       e.printStackTrace();
/* 238 */       Utilidades.writeError("SisCorreosDAO:modificarRegistro ", e);
/*     */       
/* 240 */       return false;
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
/*     */   public Collection<SisCorreosDTO> cargarPendientes(int numeroSolicitud, String tipoCorreo) {
/* 252 */     Collection<SisCorreosDTO> resultados = new ArrayList<SisCorreosDTO>();
/*     */     try {
/* 254 */       String s = "select  consecutivo,origen,destino,asunto,mensaje,estado,usuario_insercion,fecha_insercion,usuario_modificacion,fecha_modificacion from sis_correos where estado='P' and aplicacion='" + tipoCorreo + "'" + " and numero_solicitud=" + numeroSolicitud + " order by consecutivo";
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
/* 269 */       boolean rtaDB = this.dat.parseSql(s);
/* 270 */       if (!rtaDB) {
/* 271 */         return resultados;
/*     */       }
/* 273 */       this.rs = this.dat.getResultSet();
/* 274 */       while (this.rs.next()) {
/* 275 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 278 */     catch (Exception e) {
/* 279 */       e.printStackTrace();
/* 280 */       Utilidades.writeError("SisCorreosDAO:cargarTodos ", e);
/*     */     } 
/* 282 */     return resultados;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\SisCorreosDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */