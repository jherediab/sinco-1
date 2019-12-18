/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.AplazamientosSolicitudDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.AplazamientosSolicitudDAO;
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
/*     */ public class AplazamientosSolicitudDAO
/*     */ {
/*     */   ResultSet rs;
/*  24 */   DBManager dat = new DBManager();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  33 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/*     */     try {
/*  40 */       this.dat.close();
/*     */     }
/*  42 */     catch (Exception e) {
/*  43 */       Utilidades.writeError("AplazamientosSolicitudDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AplazamientosSolicitudDTO next() {
/*     */     try {
/*  54 */       if (this.rs.next()) {
/*  55 */         return leerRegistro();
/*     */       }
/*     */     }
/*  58 */     catch (Exception e) {
/*  59 */       e.printStackTrace();
/*  60 */       Utilidades.writeError("AplazamientosSolicitudDAO:next ", e);
/*     */     } 
/*  62 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AplazamientosSolicitudDTO leerRegistro() {
/*     */     try {
/*  72 */       AplazamientosSolicitudDTO reg = new AplazamientosSolicitudDTO();
/*  73 */       reg.setNumeroSolicitud(this.rs.getInt("numero_solicitud"));
/*  74 */       reg.setConsecutivo(this.rs.getInt("consecutivo"));
/*  75 */       reg.setJustificacion(this.rs.getString("justificacion"));
/*  76 */       reg.setFecha(this.rs.getString("fecha"));
/*  77 */       reg.setEstado(this.rs.getInt("estado"));
/*  78 */       reg.setJustificacionNega(this.rs.getString("justificacion_nega"));
/*  79 */       reg.setFechaestado(this.rs.getString("fecha_estado"));
/*  80 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  81 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  82 */       return reg;
/*     */     }
/*  84 */     catch (Exception e) {
/*  85 */       e.printStackTrace();
/*  86 */       Utilidades.writeError("AplazamientosSolicitudDAO:leerRegistro ", e);
/*     */       
/*  88 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection cargarTodos(int numeroSolicitud) {
/*  97 */     Collection resultados = new ArrayList();
/*     */     try {
/*  99 */       String s = "select * from aplazamientos_solicitud where numero_solicitud=" + numeroSolicitud + " order by 2";
/*     */ 
/*     */       
/* 102 */       boolean rtaDB = this.dat.parseSql(s);
/* 103 */       if (!rtaDB) {
/* 104 */         return resultados;
/*     */       }
/* 106 */       this.rs = this.dat.getResultSet();
/* 107 */       while (this.rs.next()) {
/* 108 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 111 */     catch (Exception e) {
/* 112 */       e.printStackTrace();
/* 113 */       Utilidades.writeError("AplazamientosSolicitudDAO:cargarTodos ", e);
/*     */     } 
/* 115 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AplazamientosSolicitudDTO cargarRegistro(int numeroSolicitud, int consecutivo) {
/*     */     try {
/* 125 */       String s = "select * from aplazamientos_solicitud  where  numero_solicitud=" + numeroSolicitud + " and consecutivo=" + consecutivo + "";
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 130 */       boolean rtaDB = this.dat.parseSql(s);
/* 131 */       if (!rtaDB) {
/* 132 */         return null;
/*     */       }
/* 134 */       this.rs = this.dat.getResultSet();
/* 135 */       if (this.rs.next()) {
/* 136 */         return leerRegistro();
/*     */       }
/*     */     }
/* 139 */     catch (Exception e) {
/* 140 */       e.printStackTrace();
/* 141 */       Utilidades.writeError("AplazamientosSolicitudDAO:cargarAplazamientosSolicitud ", e);
/*     */     } 
/* 143 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int siguienteRegistro(int numeroSolicitud) {
/* 152 */     int inumero = 1;
/* 153 */     String s = "select max(consecutivo) from aplazamientos_solicitud  where  numero_solicitud=" + numeroSolicitud;
/*     */ 
/*     */     
/*     */     try {
/* 157 */       boolean rta = this.dat.parseSql(s);
/* 158 */       if (!rta) return 0; 
/* 159 */       this.rs = this.dat.getResultSet();
/* 160 */       if (this.rs.next()) {
/* 161 */         s = this.rs.getString(1);
/* 162 */         if (!this.rs.wasNull()) {
/* 163 */           inumero = Integer.parseInt(s) + 1;
/*     */         }
/*     */       } 
/* 166 */       return inumero;
/*     */     }
/* 168 */     catch (Exception e) {
/* 169 */       e.printStackTrace();
/* 170 */       Utilidades.writeError("AplazamientosSolicitudDAO:siguienteRegistro ", e);
/*     */       
/* 172 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eliminarRegistro(int numeroSolicitud, int consecutivo) {
/*     */     try {
/* 182 */       String s = "delete from aplazamientos_solicitud where   numero_solicitud=" + numeroSolicitud + "  and  consecutivo=" + consecutivo + "";
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 187 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 190 */     catch (Exception e) {
/* 191 */       e.printStackTrace();
/* 192 */       Utilidades.writeError("AplazamientosSolicitudDAO:eliminarRegistro ", e);
/*     */       
/* 194 */       return false;
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
/*     */   public boolean crearRegistro(int numeroSolicitud, String justificacion, String fecha, int estado, String usuarioInsercion) {
/* 209 */     int elSiguiente = siguienteRegistro(numeroSolicitud);
/* 210 */     if (elSiguiente == 0) {
/* 211 */       return false;
/*     */     }
/*     */     
/*     */     try {
/* 215 */       String s = "insert into aplazamientos_solicitud (numero_solicitud,consecutivo,justificacion,fecha,estado,fecha_estado,usuario_insercion) values (" + numeroSolicitud + "," + "" + elSiguiente + "," + "'" + justificacion + "'," + "" + Utilidades.formatoFecha2(fecha) + "," + "" + estado + "," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
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
/* 232 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 235 */     catch (Exception e) {
/* 236 */       e.printStackTrace();
/* 237 */       Utilidades.writeError("AplazamientosSolicitudDAO:crearRegistro ", e);
/*     */       
/* 239 */       return false;
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
/*     */   public boolean modificarRegistro(int numeroSolicitud, int consecutivo, int estado, String justificacion, String usuarioModificacion) {
/*     */     try {
/* 255 */       String s = "update aplazamientos_solicitud set  estado=" + estado + "," + " justificacion_nega='" + justificacion + "'," + " fecha_estado=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " numero_solicitud=" + numeroSolicitud + " and consecutivo=" + consecutivo + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 264 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 267 */     catch (Exception e) {
/* 268 */       e.printStackTrace();
/* 269 */       Utilidades.writeError("AplazamientosSolicitudDAO:modificarRegistro ", e);
/*     */       
/* 271 */       return false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\AplazamientosSolicitudDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */