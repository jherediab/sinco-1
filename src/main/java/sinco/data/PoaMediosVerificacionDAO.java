/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.PoaMediosVerificacionDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.PoaMediosVerificacionDAO;
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
/*     */ public class PoaMediosVerificacionDAO
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
/*  50 */       Utilidades.writeError("PoaMediosVerificacionDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PoaMediosVerificacionDTO next() {
/*     */     try {
/*  61 */       if (this.rs.next()) {
/*  62 */         return leerRegistro();
/*     */       }
/*     */     }
/*  65 */     catch (Exception e) {
/*  66 */       e.printStackTrace();
/*  67 */       Utilidades.writeError("PoaMediosVerificacionDAO:next ", e);
/*     */     } 
/*  69 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PoaMediosVerificacionDTO leerRegistro() {
/*     */     try {
/*  79 */       PoaMediosVerificacionDTO reg = new PoaMediosVerificacionDTO();
/*     */       
/*  81 */       reg.setCODIGOMEDIOVERIFICACION(this.rs.getInt("CODIGO_MEDIO_VERIFICACION"));
/*  82 */       reg.setDescripcion(this.rs.getString("descripcion"));
/*  83 */       reg.setEstado(this.rs.getString("estado"));
/*  84 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  85 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  86 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  87 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  88 */       reg.setNombreEstado(this.rs.getString("nombre_estado"));
/*  89 */       return reg;
/*     */     }
/*  91 */     catch (Exception e) {
/*  92 */       e.printStackTrace();
/*  93 */       Utilidades.writeError("PoaMediosVerificacionDAO:leerRegistro ", e);
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
/*     */   
/*     */   public Collection<PoaMediosVerificacionDTO> cargarTodos(String descripcion, String estado) {
/* 106 */     Collection<PoaMediosVerificacionDTO> resultados = new ArrayList<PoaMediosVerificacionDTO>();
/*     */     try {
/* 108 */       String s = "select t.CODIGO_MEDIO_VERIFICACION,t.descripcion,t.estado,m1.DESCRIPCION as nombre_estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from POA_MEDIOS_VERIFICACION t  left join sis_multivalores m1 on (m1.tabla='ESTADO_REGISTRO' and m1.VALOR=t.estado) where 1=1";
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
/* 120 */       if (descripcion.length() > 0) {
/* 121 */         s = s + " and upper(t.descripcion) like upper('%" + descripcion + "%')";
/*     */       }
/* 123 */       if (estado.length() > 0) {
/* 124 */         s = s + " and upper(t.estado) like upper('%" + estado + "%')";
/*     */       }
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
/* 138 */       Utilidades.writeError("PoaMediosVerificacionDAO:cargarTodos ", e);
/*     */     } 
/* 140 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PoaMediosVerificacionDTO cargarRegistro(int CODIGOMEDIOVERIFICACION) {
/*     */     try {
/* 150 */       String s = "select t.CODIGO_MEDIO_VERIFICACION,t.descripcion,t.estado,m1.DESCRIPCION as nombre_estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from POA_MEDIOS_VERIFICACION t  left join sis_multivalores m1 on (m1.tabla='ESTADO_REGISTRO' and m1.VALOR=t.estado) where  t.CODIGO_MEDIO_VERIFICACION=" + CODIGOMEDIOVERIFICACION + "";
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
/* 164 */       boolean rtaDB = this.dat.parseSql(s);
/* 165 */       if (!rtaDB) {
/* 166 */         return null;
/*     */       }
/* 168 */       this.rs = this.dat.getResultSet();
/* 169 */       if (this.rs.next()) {
/* 170 */         return leerRegistro();
/*     */       }
/*     */     }
/* 173 */     catch (Exception e) {
/* 174 */       e.printStackTrace();
/* 175 */       Utilidades.writeError("PoaMediosVerificacionDAO:cargarPoaMediosVerificacion", e);
/*     */     } 
/* 177 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int siguienteRegistro() {
/* 186 */     int inumero = 1;
/* 187 */     String s = "select max(CODIGO_MEDIO_VERIFICACION) from POA_MEDIOS_VERIFICACION ";
/*     */     
/*     */     try {
/* 190 */       boolean rta = this.dat.parseSql(s);
/* 191 */       if (!rta) return 0; 
/* 192 */       this.rs = this.dat.getResultSet();
/* 193 */       if (this.rs.next()) {
/* 194 */         s = this.rs.getString(1);
/* 195 */         if (!this.rs.wasNull()) {
/* 196 */           inumero = Integer.parseInt(s) + 1;
/*     */         }
/*     */       } 
/* 199 */       return inumero;
/*     */     }
/* 201 */     catch (Exception e) {
/* 202 */       e.printStackTrace();
/* 203 */       Utilidades.writeError("PoaMediosVerificacionDAO:siguienteRegistro ", e);
/*     */       
/* 205 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarRegistro(int CODIGOMEDIOVERIFICACION) {
/* 215 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 218 */       String s = "delete from POA_MEDIOS_VERIFICACION where  CODIGO_MEDIO_VERIFICACION=" + CODIGOMEDIOVERIFICACION + "";
/*     */ 
/*     */ 
/*     */       
/* 222 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 224 */     catch (Exception e) {
/* 225 */       e.printStackTrace();
/* 226 */       Utilidades.writeError("PoaMediosVerificacionDAO:eliminarRegistro ", e);
/* 227 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 229 */     return rta;
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
/*     */   public RespuestaBD crearRegistro(int CODIGOMEDIOVERIFICACION, String descripcion, String estado, String usuarioInsercion) {
/* 243 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 245 */     int elSiguiente = siguienteRegistro();
/* 246 */     if (elSiguiente == 0) {
/* 247 */       rta.setMensaje("Generando secuencia");
/* 248 */       return rta;
/*     */     } 
/*     */     
/*     */     try {
/* 252 */       String s = "insert into POA_MEDIOS_VERIFICACION(CODIGO_MEDIO_VERIFICACION,descripcion,estado,fecha_insercion,usuario_insercion) values (" + elSiguiente + "," + "'" + descripcion + "'," + "'" + estado + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
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
/* 265 */       rta = this.dat.executeUpdate2(s);
/* 266 */       rta.setSecuencia(elSiguiente);
/*     */     }
/* 268 */     catch (Exception e) {
/* 269 */       e.printStackTrace();
/* 270 */       Utilidades.writeError("%PoaMediosVerificacionDAO:crearRegistro ", e);
/* 271 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 273 */     return rta;
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
/*     */   public RespuestaBD modificarRegistro(int CODIGOMEDIOVERIFICACION, String descripcion, String estado, String usuarioModificacion) {
/* 287 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 290 */       String s = "update POA_MEDIOS_VERIFICACION set  descripcion='" + descripcion + "'," + " estado='" + estado + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " CODIGO_MEDIO_VERIFICACION=" + CODIGOMEDIOVERIFICACION + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 298 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 300 */     catch (Exception e) {
/* 301 */       e.printStackTrace();
/* 302 */       Utilidades.writeError("PoaMediosVerificacionDAO:modificarRegistro ", e);
/* 303 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 305 */     return rta;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\PoaMediosVerificacionDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */