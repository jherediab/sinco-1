/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import sinco.business.CaracteristicasDTO;
/*     */ import sinco.business.CaracteristicasValorDTO;
/*     */ import sinco.business.DetalleSolicitudDTO;
/*     */ import sinco.business.SisUsuariosDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.CaracteristicasDAO;
/*     */ import sinco.data.CaracteristicasValorDAO;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.DetalleSolicitudDAO;
/*     */ import sinco.data.SisUsuariosDAO;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DetalleSolicitudDAO
/*     */ {
/*     */   ResultSet rs;
/*  21 */   DBManager dat = new DBManager();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  29 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/*     */     try {
/*  37 */       this.dat.close();
/*     */     }
/*  39 */     catch (Exception e) {}
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
/*     */   public boolean actualizarDetalle(int sol, int car, int consec, String observacion, String usuario) {
/*  54 */     boolean rta = true;
/*  55 */     if (observacion == null || observacion.equals("")) {
/*  56 */       rta = this.dat.executeUpdate("delete from detalles_solicitud where numero_solicitud=" + sol + " and codigo_caracteristica=" + car + " and consecutivo_detalle=" + consec);
/*     */     }
/*     */     else {
/*     */       
/*  60 */       CaracteristicasDAO rsCaracteristicas = new CaracteristicasDAO();
/*  61 */       CaracteristicasDTO regCar = rsCaracteristicas.cargarRegistro(car);
/*  62 */       rsCaracteristicas.close();
/*     */       
/*  64 */       String tipo = "1";
/*  65 */       if (regCar != null) {
/*  66 */         tipo = regCar.getTipo();
/*     */       }
/*  68 */       boolean sirve = true;
/*     */       
/*  70 */       String s = "update detalles_solicitud set ";
/*     */       
/*  72 */       int valor = 0;
/*  73 */       if (tipo.equals("2")) {
/*     */         try {
/*  75 */           valor = Integer.parseInt(observacion);
/*     */           
/*  77 */           CaracteristicasValorDAO rsCarValor = new CaracteristicasValorDAO();
/*  78 */           CaracteristicasValorDTO regCarValor = rsCarValor.cargarRegistro(car, valor);
/*  79 */           rsCarValor.close();
/*     */           
/*  81 */           if (regCarValor != null) {
/*  82 */             observacion = regCarValor.getDescripcion();
/*     */           } else {
/*     */             
/*  85 */             sirve = false;
/*     */           }
/*     */         
/*  88 */         } catch (Exception e) {}
/*     */       }
/*     */ 
/*     */       
/*  92 */       s = s + "observacion='" + observacion + "',";
/*  93 */       s = s + "valor=" + valor + ",";
/*  94 */       s = s + "fecha_modificacion=" + Utilidades.formatoFecha(Utilidades.ahora()) + ",";
/*  95 */       s = s + "usuario_modificacion='" + usuario + "'";
/*     */       
/*  97 */       s = s + " where numero_solicitud=" + sol + " and codigo_caracteristica=" + car + " and consecutivo_detalle=" + consec;
/*     */ 
/*     */       
/* 100 */       if (sirve) {
/* 101 */         rta = this.dat.executeUpdate(s);
/*     */       }
/*     */     } 
/* 104 */     return rta;
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
/*     */   public CaracteristicasValorDTO crearDetalle(int sol, int car, String observacion, String usuario) {
/*     */     try {
/* 123 */       CaracteristicasDAO rsCaracteristicas = new CaracteristicasDAO();
/* 124 */       CaracteristicasDTO regCar = rsCaracteristicas.cargarRegistro(car);
/* 125 */       rsCaracteristicas.close();
/*     */       
/* 127 */       String tipo = "1";
/* 128 */       if (regCar != null) {
/* 129 */         tipo = regCar.getTipo();
/*     */       }
/*     */       
/* 132 */       boolean sirve = true;
/* 133 */       boolean rta = this.dat.parseSql("select coalesce(MAX(consecutivo_detalle),0)+1 as consecutivo_detalle from detalles_solicitud where numero_solicitud=" + sol);
/*     */       
/* 135 */       if (!rta) return null; 
/* 136 */       ResultSet rs = this.dat.getResultSet();
/* 137 */       int numero = 1;
/* 138 */       if (rs.next()) {
/* 139 */         numero = rs.getInt("consecutivo_detalle");
/*     */       }
/*     */       
/* 142 */       String s = "insert into detalles_solicitud ( numero_solicitud, consecutivo_detalle, codigo_caracteristica, fecha_diligenciamiento, observacion, valor, usuario_insercion) VALUES(" + sol + "," + numero + "," + car + ",";
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
/* 155 */       s = s + Utilidades.formatoFecha(Utilidades.fechaActual()) + ",";
/* 156 */       CaracteristicasValorDTO regCarValor = null;
/* 157 */       int valor = 0;
/* 158 */       if (tipo.equals("2")) {
/*     */         try {
/* 160 */           valor = Integer.parseInt(observacion);
/* 161 */           CaracteristicasValorDAO rsCarValor = new CaracteristicasValorDAO();
/* 162 */           regCarValor = rsCarValor.cargarRegistro(car, valor);
/* 163 */           rsCarValor.close();
/* 164 */           if (regCarValor != null) {
/* 165 */             observacion = regCarValor.getDescripcion();
/* 166 */             regCarValor.setNombreCaracteristica(regCar.getDescripcion());
/*     */           } else {
/*     */             
/* 169 */             sirve = false;
/*     */           }
/*     */         
/* 172 */         } catch (Exception e) {}
/*     */       
/*     */       }
/* 175 */       else if (tipo.equals("5")) {
/*     */         try {
/* 177 */           valor = Integer.parseInt(observacion);
/* 178 */           SisUsuariosDAO perf = new SisUsuariosDAO();
/* 179 */           SisUsuariosDTO regPersona = perf.cargarRegistro(valor);
/* 180 */           if (regPersona != null) {
/* 181 */             observacion = regPersona.getNombre();
/*     */           } else {
/*     */             
/* 184 */             sirve = false;
/*     */           }
/*     */         
/* 187 */         } catch (Exception e) {}
/*     */       } 
/*     */ 
/*     */       
/* 191 */       s = s + "'" + observacion + "',";
/* 192 */       s = s + ((tipo.equals("2") || tipo.equals("8") || tipo.equals("5")) ? valor : 0) + ",";
/* 193 */       s = s + "'" + usuario + "'";
/* 194 */       s = s + ")";
/* 195 */       if (sirve) {
/* 196 */         rta = this.dat.executeUpdate(s);
/*     */       }
/*     */       
/* 199 */       return regCarValor;
/*     */     
/*     */     }
/* 202 */     catch (SQLException e) {
/* 203 */       e.printStackTrace();
/* 204 */       Utilidades.writeError("crear Detalle", e);
/*     */       
/* 206 */       return null;
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
/*     */   public boolean crearHallazgos(int sol, int codigoServicio, String usuario) {
/*     */     try {
/* 224 */       boolean rta = this.dat.parseSql("select MAX(consecutivo_detalle) from detalles_solicitud where numero_solicitud=" + sol);
/* 225 */       if (!rta) return false; 
/* 226 */       ResultSet rs = this.dat.getResultSet();
/* 227 */       int numero = 1;
/* 228 */       if (rs.next()) {
/* 229 */         String temp = rs.getString(1);
/* 230 */         if (!rs.wasNull()) {
/* 231 */           numero = Integer.parseInt(temp) + 1;
/*     */         }
/*     */       } 
/*     */       
/* 235 */       String s = " insert into Detalles_Solicitud    (Numero_Solicitud,     Consecutivo_Detalle,     Codigo_Caracteristica,     Fecha_Diligenciamiento,     Observacion,     Usuario_Insercion)    select " + sol + "," + "           rownum + " + numero + "," + "           cs.Codigo_Caracteristica," + "           " + Utilidades.getFechaBD() + "," + "           null," + "           '" + usuario + "'" + "    from   caracteristicas_servicio cs" + "    where  cs.codigo_servicio = " + codigoServicio + "           and cs.rol = 'P'" + "           and COALESCE(cs.estado,'A') = 'A'" + "           and cs.codigo_caracteristica not in" + "           (select ds.codigo_caracteristica" + "                from   detalles_Solicitud ds" + "                where  ds.Numero_Solicitud = " + sol + ")";
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
/* 257 */       return this.dat.executeUpdate(s);
/*     */     }
/* 259 */     catch (SQLException e) {
/* 260 */       e.printStackTrace();
/* 261 */       Utilidades.writeError("crear Hallazgos ", e);
/*     */       
/* 263 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean cargarParaSolicitud(int sol, int car) {
/*     */     try {
/* 274 */       boolean rta = this.dat.parseSql("select * from detalles_solicitud where numero_solicitud=" + sol + " and codigo_caracteristica=" + car + " order by consecutivo_detalle");
/* 275 */       if (!rta) return false; 
/* 276 */       this.rs = this.dat.getResultSet();
/* 277 */       return true;
/*     */     }
/* 279 */     catch (Exception e) {
/* 280 */       e.printStackTrace();
/*     */       
/* 282 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean cargarParaSolicitud(int sol) {
/*     */     try {
/* 294 */       String s = "select * from detalles_solicitud  where numero_solicitud=" + sol + " order by consecutivo_detalle";
/*     */ 
/*     */       
/* 297 */       boolean rta = this.dat.parseSql(s);
/* 298 */       if (!rta) return false; 
/* 299 */       this.rs = this.dat.getResultSet();
/* 300 */       return true;
/*     */     }
/* 302 */     catch (Exception e) {
/* 303 */       e.printStackTrace();
/*     */       
/* 305 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DetalleSolicitudDTO leerRegistro() {
/*     */     try {
/* 314 */       DetalleSolicitudDTO reg = new DetalleSolicitudDTO();
/* 315 */       reg.setNumeroSolicitud(this.rs.getInt("numero_solicitud"));
/* 316 */       reg.setConsecutivoDetalle(this.rs.getInt("consecutivo_detalle"));
/* 317 */       reg.setCodigoCaracteristica(this.rs.getInt("codigo_caracteristica"));
/* 318 */       reg.setFechaDiligenciamiento(this.rs.getString("fecha_diligenciamiento"));
/* 319 */       reg.setObservacion(this.rs.getString("observacion"));
/* 320 */       reg.setValor(this.rs.getInt("valor"));
/* 321 */       return reg;
/*     */     }
/* 323 */     catch (SQLException e) {
/* 324 */       e.printStackTrace();
/* 325 */       Utilidades.writeError("DetalleSolicitudFactory ", e);
/*     */       
/* 327 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DetalleSolicitudDTO next() {
/*     */     try {
/* 336 */       if (this.rs.next()) {
/* 337 */         return leerRegistro();
/*     */       }
/*     */     }
/* 340 */     catch (SQLException e) {
/* 341 */       e.printStackTrace();
/*     */     } 
/* 343 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean cargarParaSolicitud2(int sol) {
/*     */     try {
/* 354 */       boolean rta = this.dat.parseSql("select * from detalles_solicitud where numero_solicitud=" + sol + " order by codigo_caracteristica,consecutivo_detalle");
/* 355 */       if (!rta) return false; 
/* 356 */       this.rs = this.dat.getResultSet();
/* 357 */       return true;
/*     */     }
/* 359 */     catch (Exception e) {
/* 360 */       e.printStackTrace();
/* 361 */       Utilidades.writeError("DetalleSolicitudFactory ", e);
/*     */       
/* 363 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DetalleSolicitudDTO leerRegistro2() {
/*     */     try {
/* 372 */       DetalleSolicitudDTO reg = new DetalleSolicitudDTO();
/* 373 */       reg.setNumeroSolicitud(this.rs.getInt("numero_solicitud"));
/* 374 */       reg.setConsecutivoDetalle(this.rs.getInt("consecutivo_detalle"));
/* 375 */       reg.setCodigoCaracteristica(this.rs.getInt("codigo_caracteristica"));
/* 376 */       reg.setFechaDiligenciamiento(this.rs.getString("fecha_diligenciamiento"));
/* 377 */       reg.setObservacion(this.rs.getString("observacion"));
/* 378 */       reg.setValor(this.rs.getInt("valor"));
/* 379 */       return reg;
/*     */     }
/* 381 */     catch (SQLException e) {
/* 382 */       e.printStackTrace();
/* 383 */       Utilidades.writeError("DetalleSolicitudFactory ", e);
/*     */       
/* 385 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DetalleSolicitudDTO next2() {
/*     */     try {
/* 394 */       if (this.rs.next()) {
/* 395 */         return leerRegistro();
/*     */       }
/*     */     }
/* 398 */     catch (SQLException e) {
/* 399 */       e.printStackTrace();
/* 400 */       Utilidades.writeError("DetalleSolicitudFactory ", e);
/*     */     } 
/* 402 */     return null;
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
/*     */   public DetalleSolicitudDTO getDetalleSolicitud(int numero_solicitud, int consecutivo_detalle) {
/*     */     try {
/* 415 */       boolean rta = this.dat.parseSql("select * from detalles_solicitud where numero_solicitud=" + numero_solicitud + " AND consecutivo_detalle=" + consecutivo_detalle);
/* 416 */       if (!rta) return null; 
/* 417 */       this.rs = this.dat.getResultSet();
/* 418 */       if (this.rs.next()) {
/* 419 */         return leerRegistro();
/*     */       }
/*     */     }
/* 422 */     catch (SQLException e) {
/* 423 */       e.printStackTrace();
/* 424 */       Utilidades.writeError("DetalleSolicitudFactory ", e);
/*     */     } 
/* 426 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\DetalleSolicitudDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */