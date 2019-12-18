/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import sinco.business.HistoriaSolicitudDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.HistoriaSolicitudDAO;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HistoriaSolicitudDAO
/*     */ {
/*     */   ResultSet rs;
/*  15 */   DBManager dat = new DBManager();
/*     */ 
/*     */   
/*  18 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*     */ 
/*     */   
/*     */   public void close() {
/*     */     try {
/*  23 */       this.dat.close();
/*     */     }
/*  25 */     catch (Exception e) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public HistoriaSolicitudDTO leerRegistro() {
/*     */     try {
/*  36 */       HistoriaSolicitudDTO reg = new HistoriaSolicitudDTO();
/*  37 */       reg.setNumeroSolicitud(this.rs.getInt("numero_solicitud"));
/*  38 */       reg.setConsecutivo(this.rs.getInt("consecutivo_historia"));
/*  39 */       String temp = this.rs.getString("estado_inicial");
/*  40 */       if (this.rs.wasNull()) {
/*  41 */         reg.setEstadoInicial(-1);
/*     */       } else {
/*     */         
/*  44 */         reg.setEstadoInicial(Integer.parseInt(temp));
/*     */       } 
/*     */       
/*  47 */       reg.setFecha(this.rs.getString("fecha_cambio"));
/*  48 */       reg.setEstadoFinal(this.rs.getInt("estado_final"));
/*  49 */       temp = this.rs.getString("observaciones");
/*  50 */       if (this.rs.wasNull()) {
/*  51 */         reg.setObservaciones("");
/*     */       } else {
/*     */         
/*  54 */         reg.setObservaciones(temp);
/*     */       } 
/*  56 */       return reg;
/*     */     }
/*  58 */     catch (Exception e) {
/*  59 */       e.printStackTrace();
/*  60 */       Utilidades.writeError("HistoriaSolicitudFactory.leerRegistro ", e);
/*     */       
/*  62 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean crearHistoria(int solicitud, int efinal, int inicial, String observacion, String usuario) {
/*     */     try {
/*  73 */       boolean rta = this.dat.parseSql("select MAX(consecutivo_historia) from historia_solicitud where numero_solicitud=" + solicitud);
/*  74 */       if (!rta) return false; 
/*  75 */       ResultSet rs = this.dat.getResultSet();
/*  76 */       int consecutivo = 1;
/*  77 */       if (rs.next()) {
/*  78 */         String temp = rs.getString(1);
/*  79 */         if (!rs.wasNull()) {
/*  80 */           consecutivo = Integer.parseInt(temp) + 1;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/*  85 */       String sinicial = "NULL";
/*  86 */       if (inicial != -1) {
/*  87 */         sinicial = "" + inicial;
/*     */       }
/*     */       
/*  90 */       String s = "insert into historia_solicitud (numero_solicitud,consecutivo_historia,estado_final,fecha_cambio,estado_inicial,observaciones,usuario_insercion) VALUES(";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  98 */       s = s + solicitud + ",";
/*  99 */       s = s + consecutivo + ",";
/* 100 */       s = s + efinal + ",";
/* 101 */       s = s + Utilidades.getFechaBD() + ",";
/* 102 */       s = s + "" + sinicial + ",";
/* 103 */       s = s + "'" + observacion + "',";
/* 104 */       s = s + "'" + usuario + "'";
/* 105 */       s = s + ")";
/*     */       
/* 107 */       return this.dat.executeUpdate(s);
/*     */     }
/* 109 */     catch (SQLException e) {
/* 110 */       e.printStackTrace();
/* 111 */       Utilidades.writeError("Historia solicitud ", e);
/*     */       
/* 113 */       return false;
/*     */     } 
/*     */   }
/*     */   public boolean cargarTodosParaSolicitud(int sol) {
/*     */     try {
/* 118 */       boolean rta = this.dat.parseSql("select historia_solicitud.* from historia_solicitud where numero_solicitud=" + sol + " order by consecutivo_historia ASC");
/* 119 */       if (!rta) return false; 
/* 120 */       this.rs = this.dat.getResultSet();
/* 121 */       return true;
/*     */     }
/* 123 */     catch (Exception e) {
/* 124 */       e.printStackTrace();
/* 125 */       Utilidades.writeError("Historia solicitud ", e);
/*     */       
/* 127 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public HistoriaSolicitudDTO getHistoriaSolicitud(int numsol, int consec) {
/*     */     try {
/* 133 */       boolean rta = this.dat.parseSql("select historia_solicitud.* from historia_solicitud where numerosolicitud=" + numsol + " and consecutivo_historia=" + consec);
/* 134 */       if (!rta) return null; 
/* 135 */       this.rs = this.dat.getResultSet();
/* 136 */       if (this.rs.next()) {
/* 137 */         return leerRegistro();
/*     */       }
/*     */     }
/* 140 */     catch (SQLException e) {
/* 141 */       e.printStackTrace();
/* 142 */       Utilidades.writeError("Historia solicitud ", e);
/*     */     } 
/* 144 */     return null;
/*     */   }
/*     */   
/*     */   public HistoriaSolicitudDTO next() {
/*     */     try {
/* 149 */       if (this.rs.next()) {
/* 150 */         return leerRegistro();
/*     */       }
/*     */     }
/* 153 */     catch (SQLException e) {
/* 154 */       e.printStackTrace();
/* 155 */       Utilidades.writeError("Historia solicitud ", e);
/*     */     } 
/* 157 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\HistoriaSolicitudDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */