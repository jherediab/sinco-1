/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.AtencionSolicitudDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.AtencionSolicitudDAO;
/*     */ import sinco.data.DBManager;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AtencionSolicitudDAO
/*     */ {
/*     */   ResultSet rs;
/*  18 */   DBManager dat = new DBManager();
/*     */ 
/*     */   
/*  21 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*     */ 
/*     */   
/*     */   public void close() {
/*     */     try {
/*  26 */       this.dat.close();
/*     */     }
/*  28 */     catch (Exception e) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AtencionSolicitudDTO leerRegistro() {
/*     */     try {
/*  38 */       AtencionSolicitudDTO reg = new AtencionSolicitudDTO();
/*  39 */       reg.setNumeroSolicitud(this.rs.getInt("numero_solicitud"));
/*  40 */       reg.setConsecutivo(this.rs.getInt("consecutivo_atencion"));
/*  41 */       reg.setFecha(this.rs.getString("fecha"));
/*  42 */       reg.setObservacion(this.rs.getString("observacion"));
/*  43 */       reg.setPersonaAtendio(this.rs.getInt("PersonaAtendio"));
/*  44 */       return reg;
/*     */     }
/*  46 */     catch (Exception e) {
/*  47 */       e.printStackTrace();
/*  48 */       Utilidades.writeError("AtencionSolicitudFactory.leerRegistro ", e);
/*     */       
/*  50 */       return null;
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
/*     */   public boolean actualizarDetalle(int sol, int consec, String valor, String usuario) {
/*  63 */     boolean rta = false;
/*  64 */     if (valor == null || valor.equals("")) {
/*  65 */       rta = this.dat.executeUpdate("delete from atencion_solicitud where numero_solicitud=" + sol + " and consecutivo_atencion=" + consec);
/*     */     } else {
/*     */       
/*  68 */       rta = this.dat.executeUpdate("update atencion_solicitud set observacion='" + valor + "',fecha_modificacion=" + Utilidades.formatoFecha(Utilidades.ahora()) + ",usuario_modificacion='" + usuario + "' where numero_solicitud=" + sol + " and consecutivo_atencion=" + consec);
/*     */     } 
/*  70 */     return rta;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean cargarTodosParaSolicitud(int idsol) {
/*     */     try {
/*  80 */       boolean rta = this.dat.parseSql("select * from atencion_solicitud where numero_solicitud=" + idsol + " order by consecutivo_atencion");
/*  81 */       if (!rta) return false; 
/*  82 */       this.rs = this.dat.getResultSet();
/*  83 */       return true;
/*     */     }
/*  85 */     catch (Exception e) {
/*  86 */       e.printStackTrace();
/*  87 */       Utilidades.writeError("AtencionSolicitudFactory ", e);
/*     */       
/*  89 */       return false;
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
/*     */   
/*     */   public boolean crearAtencion(int sol, String observacion, int id, String usuario) {
/*     */     try {
/* 108 */      boolean rta = this.dat.parseSql("select MAX(consecutivo_atencion) from atencion_solicitud where numero_solicitud=" + sol);
/* 109 */       if (!rta) return false; 
/* 110 */       ResultSet rs = this.dat.getResultSet();
/* 111 */       int consecutivo = 1;
/* 112 */       if (rs.next()) {
/* 113 */         String temp = rs.getString(1);
/* 114 */         if (!rs.wasNull()) {
/* 115 */           consecutivo = Integer.parseInt(temp) + 1;
/*     */         }
/*     */       } 
/*     */       
/* 119 */       String s = "insert into atencion_solicitud (numero_solicitud,consecutivo_atencion,observacion,fecha,personaAtendio,usuario_insercion) VALUES(";
/* 120 */       s = s + "" + sol + ",";
/* 121 */       s = s + "" + consecutivo + ",";
/* 122 */       s = s + "'" + observacion + "',";
/* 123 */       s = s + Utilidades.formatoFecha(Utilidades.fechaActual()) + ",";
/* 124 */       s = s + "" + id + ",";
/* 125 */       s = s + "'" + usuario + "'";
/* 126 */       s = s + ")";
/* 127 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 130 */     catch (SQLException e) {
/* 131 */       e.printStackTrace();
/* 132 */       Utilidades.writeError("crear atencion ", e);
/*     */       
/* 134 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AtencionSolicitudDTO getAtencionSolicitud(int numsol, int consec) {
/*     */     try {
/* 145 */       boolean rta = this.dat.parseSql("select * from atencion_solicitud where numero_solicitud=" + numsol + " and consecutivo_atencion=" + consec);
/* 146 */       if (!rta) return null; 
/* 147 */       this.rs = this.dat.getResultSet();
/* 148 */       if (this.rs.next()) {
/* 149 */         return leerRegistro();
/*     */       }
/*     */     }
/* 152 */     catch (SQLException e) {
/* 153 */       e.printStackTrace();
/* 154 */       Utilidades.writeError("AtencionSolicitudFactory ", e);
/*     */     } 
/* 156 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AtencionSolicitudDTO next() {
/*     */     try {
/* 165 */       if (this.rs.next()) {
/* 166 */         return leerRegistro();
/*     */       }
/*     */     }
/* 169 */     catch (SQLException e) {
/* 170 */       e.printStackTrace();
/* 171 */       Utilidades.writeError("AtencionSolicitudFactory ", e);
/*     */     } 
/* 173 */     return null;
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
/*     */   public boolean incluirTarea(int sol, String tipoTarea, String usuario) {
/*     */     try {
/* 186 */       boolean rta = this.dat.parseSql("select MAX(consecutivo) from labores where solicitud=" + sol);
/* 187 */       if (!rta) return false; 
/* 188 */       ResultSet rs = this.dat.getResultSet();
/* 189 */       int numero = 1;
/* 190 */       if (rs.next()) {
/* 191 */         String temp = rs.getString(1);
/* 192 */         if (!rs.wasNull()) {
/* 193 */           numero = Integer.parseInt(temp) + 1;
/*     */         }
/*     */       } 
/* 196 */       String s = "insert into labores (solicitud,consecutivo,tipo_labor,estado,fecha_insercion,usuario_insercion)";
/* 197 */       s = s + " VALUES(" + sol + "," + numero + ",'" + tipoTarea + "','P',";
/* 198 */       s = s + "" + Utilidades.formatoFecha(Utilidades.ahora()) + ",";
/* 199 */       s = s + "'" + usuario + "'";
/* 200 */       s = s + ")";
/* 201 */       return this.dat.executeUpdate(s);
/*     */     }
/* 203 */     catch (SQLException e) {
/* 204 */       e.printStackTrace();
/* 205 */       Utilidades.writeError("AtencionSolicitudFactory ", e);
/*     */       
/* 207 */       return false;
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
/*     */   public Collection cargarHistoria(int codigoServicio, String idActivo) {
/* 220 */     Collection resultados = new ArrayList();
/*     */     try {
/* 222 */       String s = "select s.Numero,        c.Descripcion as Nombre_Caracteristica,        Ds.Fecha_Diligenciamiento,        Ds.Fecha_Diligenciamiento,        Ds.Observacion from   Solicitudes              s,        Detalles_Solicitud       Ds,        Caracteristicas_Servicio Cs,        Caracteristicas          c where  s.Codigo_Servicio = Cs.Codigo_Servicio        and s.Numero = Ds.Numero_Solicitud        and Cs.Codigo_Caracteristica = c.Codigo        and Ds.Codigo_Caracteristica = Cs.Codigo_Caracteristica        and exists (select 'x'         from   Solicitudes        Si,                Detalles_Solicitud Di         where  Si.Numero = Di.Numero_Solicitud                and Si.Codigo_Servicio = " + codigoServicio + "                and Di.Observacion = '" + idActivo + "'" + "                and s.Numero = Si.Numero)" + " order  by s.Numero," + "           Ds.Consecutivo_Detalle";
/*     */ 

/*     */ 
/*     */       
/* 246 */       boolean rta = this.dat.parseSql(s);
/* 247 */       if (!rta) {
/* 248 */         return resultados;
/*     */       }
/* 250 */       this.rs = this.dat.getResultSet();
/* 251 */       this.rs = this.dat.getResultSet();
/* 252 */       while (this.rs.next()) {
/* 253 */         AtencionSolicitudDTO reg = new AtencionSolicitudDTO();
/*     */         
/* 255 */         reg.setNumeroSolicitud(this.rs.getInt("numero"));
/* 256 */         reg.setNombreCaracteristica(this.rs.getString("nombre_caracteristica"));
/* 257 */         reg.setFecha(this.rs.getString("fecha_diligenciamiento"));
/* 258 */         reg.setObservacion(this.rs.getString("observacion"));
/* 259 */         resultados.add(reg);
/*     */       }
/*     */     
/* 262 */     } catch (Exception e) {
/* 263 */       e.printStackTrace();
/* 264 */       Utilidades.writeError("CargarActivo", e);
/*     */     } 
/* 266 */     return resultados;
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
/* 282 */   public RespuestaBD registrarCorreos(int solicitud, String usuario) { return this.dat.registrarCorreos(solicitud, usuario); }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\AtencionSolicitudDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */