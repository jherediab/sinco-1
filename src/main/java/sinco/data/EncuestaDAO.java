/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import sinco.business.EncuestaDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.EncuestaDAO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EncuestaDAO
/*     */ {
/*     */   ResultSet rs;
/*  27 */   DBManager dat = new DBManager();
/*     */ 
/*     */   
/*  30 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/*     */     try {
/*  39 */       this.dat.close();
/*     */     }
/*  41 */     catch (Exception e) {
/*  42 */       Utilidades.writeError("EncuestaFactory.close", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EncuestaDTO next() {
/*     */     try {
/*  52 */       if (this.rs.next()) {
/*  53 */         return leerRegistro();
/*     */       }
/*     */     }
/*  56 */     catch (Exception e) {
/*  57 */       e.printStackTrace();
/*  58 */       Utilidades.writeError("EncuestaFactory.next ", e);
/*     */     } 
/*  60 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EncuestaDTO leerRegistro() {
/*     */     try {
/*  69 */       EncuestaDTO reg = new EncuestaDTO();
/*  70 */       reg.setNumero(this.rs.getInt("numero"));
/*  71 */       reg.setEmpleado_cliente(this.rs.getInt("empleado_cliente"));
/*  72 */       reg.setCodigo_servicio(this.rs.getInt("codigo_servicio"));
/*  73 */       reg.setEstado(this.rs.getInt("estado"));
/*  74 */       reg.setNombreServicio(this.rs.getString("nombreServicio"));
/*  75 */       reg.setNombreEstado(this.rs.getString("nombreEstado"));
/*  76 */       reg.setDescripcion(this.rs.getString("descripcion"));
/*  77 */       reg.setFecha(this.rs.getString("fecha_insercion"));
/*  78 */       reg.setCiclo(this.rs.getString("ciclo"));
/*  79 */       return reg;
/*     */     }
/*  81 */     catch (Exception e) {
/*  82 */       e.printStackTrace();
/*  83 */       Utilidades.writeError("EncuestaFactory.leerRegistro ", e);
/*     */       
/*  85 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean cargarPendientesEnvio(int cliente) {
/*     */     try {
/*  94 */       String s = "select encuesta.*,Servicios.descripcion as nombreServicio,estado_encuesta.descripcion as nombreEstado from encuesta,Servicios,estado_encuesta where encuesta.codigo_servicio=Servicios.codigo and encuesta.estado=estado_encuesta.codigo and encuesta.empleado_cliente=" + cliente + " and encuesta.estado=1";
/*  95 */       boolean rta = this.dat.parseSql(s);
/*  96 */       if (!rta) return false; 
/*  97 */       this.rs = this.dat.getResultSet();
/*  98 */       return true;
/*     */     }
/* 100 */     catch (Exception e) {
/* 101 */       e.printStackTrace();
/* 102 */       Utilidades.writeError("EncuestaFactory.cargarPendientesEnvio ", e);
/*     */       
/* 104 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean cargarPendientesCalificacion(int cliente) {
/*     */     try {
/* 113 */       String s = "select encuesta.*,Servicios.descripcion as nombreServicio,estado_encuesta.descripcion as nombreEstado from encuesta,Servicios,estado_encuesta where encuesta.codigo_servicio=Servicios.codigo and encuesta.estado=estado_encuesta.codigo and encuesta.empleado_cliente=" + cliente + " and encuesta.estado=3";
/* 114 */       boolean rta = this.dat.parseSql(s);
/* 115 */       if (!rta) return false; 
/* 116 */       this.rs = this.dat.getResultSet();
/* 117 */       return true;
/*     */     }
/* 119 */     catch (Exception e) {
/* 120 */       e.printStackTrace();
/* 121 */       Utilidades.writeError("EncuestaFactory.cargarPendientesCalificacion ", e);
/*     */       
/* 123 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EncuestaDTO cargaRegistro(int numero) {
/*     */     try {
/* 133 */       String s = "select encuesta.*,Servicios.descripcion as nombreServicio,estado_encuesta.descripcion as nombreEstado from encuesta,Servicios,estado_encuesta where encuesta.codigo_servicio=Servicios.codigo and encuesta.estado=estado_encuesta.codigo and encuesta.numero=" + numero;
/* 134 */       boolean rta = this.dat.parseSql(s);
/* 135 */       if (!rta) return null; 
/* 136 */       this.rs = this.dat.getResultSet();
/* 137 */       if (this.rs.next()) {
/* 138 */         return leerRegistro();
/*     */       }
/*     */     }
/* 141 */     catch (Exception e) {
/* 142 */       e.printStackTrace();
/* 143 */       Utilidades.writeError("EncuestaFactory.cargarRegistro ", e);
/*     */     } 
/* 145 */     return null;
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
/*     */   public int crearRegistro(int empleadoCliente, int areaCliente, int codigoServicio, int estado, String ciclo, String usuario) {
/*     */     try {
/* 163 */       boolean rta = this.dat.parseSql("select MAX(numero) from encuesta");
/* 164 */       if (!rta) return 0; 
/* 165 */       ResultSet rs = this.dat.getResultSet();
/* 166 */       int numero = 1;
/* 167 */       if (rs.next()) {
/* 168 */         String temp = rs.getString(1);
/*     */         
/* 170 */         if (!rs.wasNull()) {
/* 171 */           numero = Integer.parseInt(temp) + 1;
/*     */         }
/*     */       } 
/*     */       
/* 175 */       String s = "insert into encuesta (numero,empleado_cliente,area_cliente,codigo_servicio,estado,ciclo,fecha_insercion,usuario_insercion)";
/* 176 */       s = s + " values (";
/* 177 */       s = s + "" + numero + ",";
/* 178 */       s = s + "" + empleadoCliente + ",";
/* 179 */       s = s + "" + areaCliente + ",";
/* 180 */       s = s + "" + codigoServicio + ",";
/* 181 */       s = s + "" + estado + ",";
/* 182 */       s = s + "'" + ciclo + "',";
/* 183 */       s = s + "" + Utilidades.formatoFecha(Utilidades.ahora()) + ",";
/* 184 */       s = s + "'" + usuario + "'";
/* 185 */       s = s + ")";
/* 186 */       rta = this.dat.executeUpdate(s);
/* 187 */       if (rta == true) {
/* 188 */         return numero;
/*     */       }
/*     */     }
/* 191 */     catch (Exception e) {
/* 192 */       e.printStackTrace();
/* 193 */       Utilidades.writeError("EncuestaFactory.CrearRegistro", e);
/*     */     } 
/* 195 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean modificarRegistro(int numero, int estado, String usuario) {
/*     */     try {
/* 205 */       String s = "update encuesta set estado=" + estado + ",";
/* 206 */       s = s + "fecha_modificacion=" + Utilidades.formatoFecha(Utilidades.ahora()) + ",";
/* 207 */       s = s + "usuario_modificacion='" + usuario + "'";
/* 208 */       s = s + " where numero=" + numero;
/* 209 */       return this.dat.executeUpdate(s);
/*     */     }
/* 211 */     catch (Exception e) {
/* 212 */       e.printStackTrace();
/* 213 */       Utilidades.writeError("EncuestaFactory.modificarRegistro", e);
/*     */       
/* 215 */       return false;
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
/*     */   public boolean modificarRegistro(int numero, int estado, String observacion, String usuario) {
/*     */     try {
/* 230 */       String s = "update encuesta set estado=" + estado + ",descripcion='" + observacion + "',";
/* 231 */       s = s + "fecha_modificacion=" + Utilidades.formatoFecha(Utilidades.ahora()) + ",";
/* 232 */       s = s + "usuario_modificacion='" + usuario + "'";
/* 233 */       s = s + " where numero=" + numero;
/* 234 */       return this.dat.executeUpdate(s);
/*     */     }
/* 236 */     catch (Exception e) {
/* 237 */       e.printStackTrace();
/* 238 */       Utilidades.writeError("EncuestaFactory.modificarRegistro", e);
/*     */       
/* 240 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean calificarEncuesta(int numero, String confiabilidad, String justificacion, String usuario) {
/*     */     try {
/* 249 */       String s = "update encuesta set estado=5,";
/* 250 */       s = s + "codigo_confiabilidad='" + confiabilidad + "',";
/* 251 */       s = s + "justificacion='" + justificacion + "',";
/* 252 */       s = s + "fecha_modificacion=" + Utilidades.formatoFecha(Utilidades.ahora()) + ",";
/* 253 */       s = s + "usuario_modificacion='" + usuario + "'";
/* 254 */       s = s + " where numero=" + numero;
/* 255 */       return this.dat.executeUpdate(s);
/*     */     }
/* 257 */     catch (Exception e) {
/* 258 */       e.printStackTrace();
/* 259 */       Utilidades.writeError("EncuestaFactory.calificarEncuesta: ", e);
/*     */       
/* 261 */       return false;
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
/*     */   
/*     */   public RespuestaBD enviarEncuesta(int numero, int estado, String observacion, String usuario) {
/* 280 */     RespuestaBD rtaBD = new RespuestaBD();
/*     */     
/*     */     try {
/* 283 */       String s = "update encuesta set estado=" + estado + ",descripcion='" + observacion + "',";
/* 284 */       s = s + "fecha_modificacion=" + Utilidades.formatoFecha(Utilidades.ahora()) + ",";
/* 285 */       s = s + "usuario_modificacion='" + usuario + "'";
/* 286 */       s = s + " where numero=" + numero;
/*     */       
/* 288 */       rtaBD = this.dat.executeUpdate2(s);
/* 289 */       if (rtaBD.isRta()) {
/* 290 */         rtaBD = this.dat.enviarSolicitudesEncuesta(numero, usuario);
/*     */       }
/*     */     }
/* 293 */     catch (Exception e) {
/* 294 */       e.printStackTrace();
/* 295 */       Utilidades.writeError("EncuestaFactory.modificarRegistro", e);
/*     */     } 
/* 297 */     return rtaBD;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\EncuestaDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */