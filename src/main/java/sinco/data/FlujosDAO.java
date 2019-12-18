/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.FlujosDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.FlujosDAO;
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
/*     */ public class FlujosDAO
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
/*  50 */       Utilidades.writeError("FlujosDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FlujosDTO next() {
/*     */     try {
/*  61 */       if (this.rs.next()) {
/*  62 */         return leerRegistro();
/*     */       }
/*     */     }
/*  65 */     catch (Exception e) {
/*  66 */       e.printStackTrace();
/*  67 */       Utilidades.writeError("FlujosDAO:next ", e);
/*     */     } 
/*  69 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FlujosDTO leerRegistro() {
/*     */     try {
/*  79 */       FlujosDTO reg = new FlujosDTO();
/*     */       
/*  81 */       reg.setCodigoFlujo(this.rs.getInt("codigo_flujo"));
/*  82 */       reg.setDescripcion(this.rs.getString("descripcion"));
/*  83 */       reg.setServicioInicial(this.rs.getInt("servicio_inicial"));
/*  84 */       reg.setEstado(this.rs.getString("estado"));
/*  85 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  86 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  87 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  88 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  89 */       reg.setNombreServicioInicial(this.rs.getString("nombre_servicio_inicial"));
/*  90 */       reg.setNombreEstado(this.rs.getString("nombre_estado"));
/*  91 */       return reg;
/*     */     }
/*  93 */     catch (Exception e) {
/*  94 */       e.printStackTrace();
/*  95 */       Utilidades.writeError("FlujosDAO:leerRegistro ", e);
/*     */       
/*  97 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<FlujosDTO> cargarTodos(int codigoFlujo, String descripcion) {
/* 108 */     Collection<FlujosDTO> resultados = new ArrayList<FlujosDTO>();
/*     */     try {
/* 110 */       String s = "select t.codigo_flujo,t.descripcion,t.servicio_inicial,r1.descripcion as nombre_servicio_inicial,t.estado,m2.descripcion as nombre_estado,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion from wkf_flujos t  left join SERVICIOS r1 on (r1.CODIGO=t.servicio_inicial) left join sis_multivalores m2 on (m2.tabla='ESTADO_REGISTRO' and m2.valor=t.estado) where 1=1";
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
/* 125 */       if (codigoFlujo > 0) {
/* 126 */         s = s + " and t.codigo_flujo=" + codigoFlujo;
/*     */       }
/* 128 */       if (descripcion.length() > 0) {
/* 129 */         s = s + " and upper(t.descripcion) like upper('%" + descripcion + "%')";
/*     */       }
/* 131 */       s = s + " order by 1";
/* 132 */       boolean rtaDB = this.dat.parseSql(s);
/* 133 */       if (!rtaDB) {
/* 134 */         return resultados;
/*     */       }
/* 136 */       this.rs = this.dat.getResultSet();
/* 137 */       while (this.rs.next()) {
/* 138 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 141 */     catch (Exception e) {
/* 142 */       e.printStackTrace();
/* 143 */       Utilidades.writeError("FlujosDAO:cargarTodos ", e);
/*     */     } 
/* 145 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FlujosDTO cargarRegistro(int codigoFlujo) {
/*     */     try {
/* 154 */       String s = "select t.codigo_flujo,t.descripcion,t.servicio_inicial,r1.descripcion as nombre_servicio_inicial,t.estado,m2.descripcion as nombre_estado,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion from wkf_flujos t  left join SERVICIOS r1 on (r1.CODIGO=t.servicio_inicial) left join sis_multivalores m2 on (m2.tabla='ESTADO_REGISTRO' and m2.valor=t.estado) where  t.codigo_flujo=" + codigoFlujo + "";
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
/* 171 */       boolean rtaDB = this.dat.parseSql(s);
/* 172 */       if (!rtaDB) {
/* 173 */         return null;
/*     */       }
/* 175 */       this.rs = this.dat.getResultSet();
/* 176 */       if (this.rs.next()) {
/* 177 */         return leerRegistro();
/*     */       }
/*     */     }
/* 180 */     catch (Exception e) {
/* 181 */       e.printStackTrace();
/* 182 */       Utilidades.writeError("FlujosDAO:cargarFlujos", e);
/*     */     } 
/* 184 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int siguienteRegistro() {
/* 193 */     int inumero = 1;
/* 194 */     String s = "select max(codigo_flujo) from wkf_flujos ";
/*     */     
/*     */     try {
/* 197 */       boolean rta = this.dat.parseSql(s);
/* 198 */       if (!rta) return 0; 
/* 199 */       this.rs = this.dat.getResultSet();
/* 200 */       if (this.rs.next()) {
/* 201 */         s = this.rs.getString(1);
/* 202 */         if (!this.rs.wasNull()) {
/* 203 */           inumero = Integer.parseInt(s) + 1;
/*     */         }
/*     */       } 
/* 206 */       return inumero;
/*     */     }
/* 208 */     catch (Exception e) {
/* 209 */       e.printStackTrace();
/* 210 */       Utilidades.writeError("FlujosDAO:siguienteRegistro ", e);
/*     */       
/* 212 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarRegistro(int codigoFlujo) {
/* 221 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 224 */       String s = "delete from wkf_flujos where  codigo_flujo=" + codigoFlujo + "";
/*     */ 
/*     */ 
/*     */       
/* 228 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 230 */     catch (Exception e) {
/* 231 */       e.printStackTrace();
/* 232 */       Utilidades.writeError("FlujosDAO:eliminarRegistro ", e);
/* 233 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 235 */     return rta;
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
/*     */   public RespuestaBD crearRegistro(int codigoFlujo, String descripcion, int servicioInicial, String estado, String usuarioInsercion) {
/* 250 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 252 */     int elSiguiente = siguienteRegistro();
/* 253 */     if (elSiguiente == 0) {
/* 254 */       rta.setMensaje("Generando secuencia");
/* 255 */       return rta;
/*     */     } 
/*     */     
/*     */     try {
/* 259 */       String s = "insert into wkf_flujos(codigo_flujo,descripcion,servicio_inicial,estado,usuario_insercion,fecha_insercion) values (" + elSiguiente + "," + "'" + descripcion + "'," + "" + servicioInicial + "," + "'" + estado + "'," + "'" + usuarioInsercion + "'," + "" + Utilidades.getFechaBD() + "" + ")";
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
/* 274 */       rta = this.dat.executeUpdate2(s);
/* 275 */       rta.setSecuencia(elSiguiente);
/*     */     }
/* 277 */     catch (Exception e) {
/* 278 */       e.printStackTrace();
/* 279 */       Utilidades.writeError("%FlujosDAO:crearRegistro ", e);
/* 280 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 282 */     return rta;
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
/*     */   public RespuestaBD modificarRegistro(int codigoFlujo, String descripcion, int servicioInicial, String estado, String usuarioModificacion) {
/* 297 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 300 */       String s = "update wkf_flujos set  descripcion='" + descripcion + "'," + " servicio_inicial=" + servicioInicial + "," + " estado='" + estado + "'," + " usuario_modificacion='" + usuarioModificacion + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "" + " where" + " codigo_flujo=" + codigoFlujo + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 309 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 311 */     catch (Exception e) {
/* 312 */       e.printStackTrace();
/* 313 */       Utilidades.writeError("FlujosDAO:modificarRegistro ", e);
/* 314 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 316 */     return rta;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\FlujosDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */