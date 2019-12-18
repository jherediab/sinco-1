/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.AMSeguimientoDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.AMSeguimientoDAO;
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
/*     */ public class AMSeguimientoDAO
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
/*  43 */       Utilidades.writeError("AMSeguimientoDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AMSeguimientoDTO next() {
/*     */     try {
/*  54 */       if (this.rs.next()) {
/*  55 */         return leerRegistro();
/*     */       }
/*     */     }
/*  58 */     catch (Exception e) {
/*  59 */       e.printStackTrace();
/*  60 */       Utilidades.writeError("AMSeguimientoDAO:next ", e);
/*     */     } 
/*  62 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AMSeguimientoDTO leerRegistro() {
/*     */     try {
/*  72 */       AMSeguimientoDTO reg = new AMSeguimientoDTO();
/*  73 */       reg.setNumero(this.rs.getInt("numero"));
/*  74 */       reg.setCausa(this.rs.getInt("causa"));
/*  75 */       reg.setConsecutivo(this.rs.getInt("consecutivo"));
/*  76 */       reg.setObservacion(this.rs.getString("observacion"));
/*  77 */       reg.setFecha(this.rs.getString("fecha"));
/*  78 */       reg.setPersonaatendio(this.rs.getInt("personaatendio"));
/*  79 */       reg.setAutomatico(this.rs.getString("automatico"));
/*  80 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  81 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  82 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  83 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  84 */       reg.setNombre(this.rs.getString("nombre"));
/*  85 */       return reg;
/*     */     }
/*  87 */     catch (Exception e) {
/*  88 */       e.printStackTrace();
/*  89 */       Utilidades.writeError("AMSeguimientoDAO:leerRegistro ", e);
/*     */       
/*  91 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection cargarTodos(int numero, int causa) {
/* 102 */     Collection resultados = new ArrayList();
/*     */     try {
/* 104 */       String s = "select  am_seguimiento.*, p.apellidos||' '||p.nombres as nombre from am_seguimiento,sis_usuarios p  where am_seguimiento.personaatendio=p.codigo_empleado  and numero=" + numero + " and causa=" + causa + " order by am_seguimiento.consecutivo";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 112 */       boolean rtaDB = this.dat.parseSql(s);
/* 113 */       if (!rtaDB) {
/* 114 */         return resultados;
/*     */       }
/* 116 */       this.rs = this.dat.getResultSet();
/* 117 */       while (this.rs.next()) {
/* 118 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 121 */     catch (Exception e) {
/* 122 */       e.printStackTrace();
/* 123 */       Utilidades.writeError("AMSeguimientoDAO:cargarTodos ", e);
/*     */     } 
/* 125 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AMSeguimientoDTO cargarRegistro(int numero, int causa, int consecutivo) {
/*     */     try {
/* 135 */       String s = "select  am_seguimiento.*, p.apellidos||' '||p.nombres as nombre from am_seguimiento,sis_usuarios p where am_seguimiento.personaatendio=p.codigo_empleado  and numero=" + numero + " and causa=" + causa + " and consecutivo=" + consecutivo;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 143 */       boolean rtaDB = this.dat.parseSql(s);
/* 144 */       if (!rtaDB) {
/* 145 */         return null;
/*     */       }
/* 147 */       this.rs = this.dat.getResultSet();
/* 148 */       if (this.rs.next()) {
/* 149 */         return leerRegistro();
/*     */       }
/*     */     }
/* 152 */     catch (Exception e) {
/* 153 */       e.printStackTrace();
/* 154 */       Utilidades.writeError("AMSeguimientoDAO:cargarAMSeguimiento ", e);
/*     */     } 
/* 156 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int siguienteRegistro(int numero, int causa, int consecutivo) {
/* 165 */     int inumero = 1;
/* 166 */     String s = "select max(0) from am_seguimiento  where  numero=" + numero + " and causa=" + causa + " and consecutivo=" + consecutivo + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 173 */       boolean rta = this.dat.parseSql(s);
/* 174 */       if (!rta) return 0; 
/* 175 */       this.rs = this.dat.getResultSet();
/* 176 */       if (this.rs.next()) {
/* 177 */         s = this.rs.getString(1);
/* 178 */         if (!this.rs.wasNull()) {
/* 179 */           inumero = Integer.parseInt(s) + 1;
/*     */         }
/*     */       } 
/* 182 */       return inumero;
/*     */     }
/* 184 */     catch (Exception e) {
/* 185 */       e.printStackTrace();
/* 186 */       Utilidades.writeError("AMSeguimientoDAO:siguienteRegistro ", e);
/*     */       
/* 188 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eliminarRegistro(int numero, int causa, int consecutivo) {
/*     */     try {
/* 198 */       String s = "delete from am_seguimiento where   numero=" + numero + "  and  causa=" + causa + "  and  consecutivo=" + consecutivo + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 204 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 207 */     catch (Exception e) {
/* 208 */       e.printStackTrace();
/* 209 */       Utilidades.writeError("AMSeguimientoDAO:eliminarRegistro ", e);
/*     */       
/* 211 */       return false;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean crearRegistro(int numero, int causa, int consecutivo, String observacion, String fecha, int personaatendio, String automatico, String usuarioInsercion) {
/*     */     try {
/* 235 */       String s = "insert into am_seguimiento (numero,causa,consecutivo,observacion,fecha,personaatendio,automatico,usuario_insercion,fecha_insercion) values (" + numero + "," + "" + causa + "," + "" + consecutivo + "," + "'" + observacion + "'," + "" + Utilidades.formatoFecha2(fecha) + "," + "" + personaatendio + "," + "'" + automatico + "'," + "'" + usuarioInsercion + "'," + "" + Utilidades.getFechaBD() + "" + ")";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 256 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 259 */     catch (Exception e) {
/* 260 */       e.printStackTrace();
/* 261 */       Utilidades.writeError("AMSeguimientoDAO:crearRegistro ", e);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean modificarRegistro(int numero, int causa, int consecutivo, String observacion, String fecha, int personaatendio, String automatico, String usuarioModificacion) {
/*     */     try {
/* 282 */       String s = "update am_seguimiento set  observacion='" + observacion + "'," + " fecha=" + Utilidades.formatoFecha2(fecha) + "," + " personaatendio=" + personaatendio + "," + " automatico='" + automatico + "'," + " usuario_modificacion='" + usuarioModificacion + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "" + " where" + " numero=" + numero + " and causa=" + causa + " and consecutivo=" + consecutivo + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 294 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 297 */     catch (Exception e) {
/* 298 */       e.printStackTrace();
/* 299 */       Utilidades.writeError("AMSeguimientoDAO:modificarRegistro ", e);
/*     */       
/* 301 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AMSeguimientoDTO cargarUltimoSeguimiento(int numero, int causa) {
/*     */     try {
/* 311 */       String s = "select  s.*, p.apellidos||' '||p.nombres as nombre from am_seguimiento s,sis_usuarios p  where s.personaatendio=p.codigo_empleado  and s.numero=" + numero + " and s.causa=" + causa + " and s.automatico='N'" + " and s.consecutivo=(select max(consecutivo) " + " from am_seguimiento si" + " where si.numero=s.numero" + " and si.causa=s.causa)";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 323 */       boolean rtaDB = this.dat.parseSql(s);
/* 324 */       if (!rtaDB) {
/* 325 */         return null;
/*     */       }
/* 327 */       this.rs = this.dat.getResultSet();
/* 328 */       if (this.rs.next()) {
/* 329 */         return leerRegistro();
/*     */       }
/*     */     }
/* 332 */     catch (Exception e) {
/* 333 */       e.printStackTrace();
/* 334 */       Utilidades.writeError("AMSeguimientoDAO:cargarAMSeguimiento ", e);
/*     */     } 
/* 336 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\AMSeguimientoDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */