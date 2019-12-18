/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import sinco.business.CaracteristicasDTO;
/*     */ import sinco.business.CaracteristicasValorDTO;
/*     */ import sinco.business.DetalleSolicitudDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.CaracteristicasDAO;
/*     */ import sinco.data.CaracteristicasValorDAO;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.DetalleEncuestaDAO;
/*     */ 
/*     */ public class DetalleEncuestaDAO
/*     */ {
/*     */   ResultSet rs;
/*  17 */   DBManager dat = new DBManager();
/*     */ 
/*     */   
/*  20 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*     */ 
/*     */   
/*     */   public void close() {
/*     */     try {
/*  25 */       this.dat.close();
/*     */     }
/*  27 */     catch (Exception e) {}
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
/*     */   public boolean actualizarDetalle(int sol, int car, int consec, String observacion, String usuario) {
/*  41 */     boolean rta = true;
/*  42 */     if (observacion == null || observacion.equals("")) {
/*  43 */       rta = this.dat.executeUpdate("delete from detalle_encuesta where numero=" + sol + " and caracteristica=" + car + " and consecutivo=" + consec);
/*     */     }
/*     */     else {
/*     */       
/*  47 */       CaracteristicasDAO rsCaracteristicas = new CaracteristicasDAO();
/*  48 */       CaracteristicasDTO regCar = rsCaracteristicas.cargarRegistro(car);
/*  49 */       rsCaracteristicas.close();
/*     */       
/*  51 */       String tipo = "1";
/*  52 */       if (regCar != null) {
/*  53 */         tipo = regCar.getTipo();
/*     */       }
/*  55 */       boolean sirve = true;
/*     */       
/*  57 */       String s = "update detalle_encuesta set ";
/*     */       
/*  59 */       int valor = 0;
/*  60 */       if (tipo.equals("2")) {
/*     */         try {
/*  62 */           valor = Integer.parseInt(observacion);
/*     */           
/*  64 */           if (valor == 0) {
/*  65 */             return true;
/*     */           }
/*     */           
/*  68 */           CaracteristicasValorDAO rsCarValor = new CaracteristicasValorDAO();
/*  69 */           CaracteristicasValorDTO regCarValor = rsCarValor.cargarRegistro(car, valor);
/*  70 */           rsCarValor.close();
/*     */           
/*  72 */           if (regCarValor != null) {
/*  73 */             observacion = regCarValor.getDescripcion();
/*     */           } else {
/*     */             
/*  76 */             sirve = false;
/*     */           }
/*     */         
/*  79 */         } catch (Exception e) {}
/*     */       }
/*     */       
/*  82 */       s = s + "observacion='" + observacion + "',";
/*  83 */       s = s + "valor=" + valor + ",";
/*  84 */       s = s + "fecha_modificacion=" + Utilidades.formatoFecha(Utilidades.ahora()) + ",";
/*  85 */       s = s + "usuario_modificacion='" + usuario + "'";
/*  86 */       s = s + " where numero=" + sol + " and caracteristica=" + car + " and consecutivo=" + consec;
/*  87 */       if (sirve) {
/*  88 */         rta = this.dat.executeUpdate(s);
/*     */       }
/*     */     } 
/*  91 */     return rta;
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
/*     */   public boolean crearDetalle(int sol, int car, String observacion, String usuario) {
/*     */     try {
/* 106 */       CaracteristicasDAO rsCaracteristicas = new CaracteristicasDAO();
/* 107 */       CaracteristicasDTO regCar = rsCaracteristicas.cargarRegistro(car);
/* 108 */       rsCaracteristicas.close();
/*     */       
/* 110 */       String tipo = "1";
/* 111 */       if (regCar != null) {
/* 112 */         tipo = regCar.getTipo();
/*     */       }
/*     */       
/* 115 */       boolean rta = this.dat.parseSql("select MAX(consecutivo) from detalle_encuesta where numero=" + sol + " and caracteristica=" + car);
/* 116 */       if (!rta) return false; 
/* 117 */       ResultSet rs = this.dat.getResultSet();
/* 118 */       int consecutivo = 1;
/* 119 */       if (rs.next()) {
/* 120 */         String temp = rs.getString(1);
/* 121 */         if (!rs.wasNull()) {
/* 122 */           consecutivo = Integer.parseInt(temp) + 1;
/*     */         }
/*     */       } 
/*     */       
/* 126 */       boolean sirve = true;
/* 127 */       String s = "insert into detalle_encuesta (numero,caracteristica,consecutivo,observacion,valor,fecha_insercion,usuario_insercion)";
/* 128 */       s = s + " VALUES(" + sol + "," + car + "," + consecutivo + ",";
/* 129 */       int valor = 0;
/* 130 */       if (tipo.equals("2")) {
/*     */         try {
/* 132 */           valor = Integer.parseInt(observacion);
/*     */           
/* 134 */           if (valor == 0) {
/* 135 */             return true;
/*     */           }
/*     */           
/* 138 */           CaracteristicasValorDAO rsCarValor = new CaracteristicasValorDAO();
/* 139 */           CaracteristicasValorDTO regCarValor = rsCarValor.cargarRegistro(car, valor);
/* 140 */           rsCarValor.close();
/*     */           
/* 142 */           if (regCarValor != null) {
/* 143 */             observacion = regCarValor.getDescripcion();
/*     */           } else {
/*     */             
/* 146 */             sirve = false;
/*     */           }
/*     */         
/* 149 */         } catch (Exception e) {}
/*     */       }
/*     */       
/* 152 */       s = s + "'" + observacion + "',";
/* 153 */       s = s + (tipo.equals("2") ? valor : 0) + ",";
/* 154 */       s = s + "" + Utilidades.formatoFecha(Utilidades.ahora()) + ",";
/* 155 */       s = s + "'" + usuario + "'";
/* 156 */       s = s + ")";
/* 157 */       if (sirve) {
/* 158 */         rta = this.dat.executeUpdate(s);
/*     */       }
/* 160 */       return rta;
/*     */     }
/* 162 */     catch (SQLException e) {
/* 163 */       e.printStackTrace();
/*     */       
/* 165 */       return false;
/*     */     } 
/*     */   }
/*     */   public boolean cargarParaSolicitud(int sol, int car) {
/*     */     try {
/* 170 */       boolean rta = this.dat.parseSql("select * from detalle_encuesta where numero=" + sol + " and caracteristica=" + car + " order by consecutivo");
/* 171 */       if (!rta) return false; 
/* 172 */       this.rs = this.dat.getResultSet();
/* 173 */       return true;
/*     */     }
/* 175 */     catch (Exception e) {
/* 176 */       e.printStackTrace();
/*     */       
/* 178 */       return false;
/*     */     } 
/*     */   }
/*     */   public boolean cargarParaSolicitud(int sol) {
/*     */     try {
/* 183 */       boolean rta = this.dat.parseSql("select * from detalle_encuesta where numero=" + sol + " order by caracteristica,consecutivo");
/* 184 */       if (!rta) return false; 
/* 185 */       this.rs = this.dat.getResultSet();
/* 186 */       return true;
/*     */     }
/* 188 */     catch (Exception e) {
/* 189 */       e.printStackTrace();
/*     */       
/* 191 */       return false;
/*     */     } 
/*     */   }
/*     */   public DetalleSolicitudDTO leerRegistro() {
/*     */     try {
/* 196 */       DetalleSolicitudDTO detallesolicitud = new DetalleSolicitudDTO();
/* 197 */       detallesolicitud.setNumeroSolicitud(Integer.parseInt(this.rs.getString("numero")));
/* 198 */       detallesolicitud.setConsecutivoDetalle(Integer.parseInt(this.rs.getString("consecutivo")));
/* 199 */       detallesolicitud.setCodigoCaracteristica(Integer.parseInt(this.rs.getString("caracteristica")));
/* 200 */       detallesolicitud.setObservacion(this.rs.getString("observacion"));
/* 201 */       detallesolicitud.setValor(this.rs.getInt("valor"));
/* 202 */       return detallesolicitud;
/*     */     }
/* 204 */     catch (SQLException e) {
/* 205 */       e.printStackTrace();
/*     */       
/* 207 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public DetalleSolicitudDTO next() {
/*     */     try {
/* 213 */       if (this.rs.next()) {
/* 214 */         return leerRegistro();
/*     */       }
/*     */     }
/* 217 */     catch (SQLException e) {
/* 218 */       e.printStackTrace();
/*     */     } 
/* 220 */     return null;
/*     */   }
/*     */   
/*     */   public DetalleSolicitudDTO getDetalleSolicitud(int numero, int consecutivo) {
/*     */     try {
/* 225 */       boolean rta = this.dat.parseSql("select * from detalle_encuesta where numero=" + numero + " AND consecutivo=" + consecutivo);
/* 226 */       if (!rta) return null; 
/* 227 */       this.rs = this.dat.getResultSet();
/* 228 */       if (this.rs.next()) {
/* 229 */         return leerRegistro();
/*     */       }
/*     */     }
/* 232 */     catch (SQLException e) {
/* 233 */       e.printStackTrace();
/*     */     } 
/* 235 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\DetalleEncuestaDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */