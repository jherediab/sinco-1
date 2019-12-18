/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.PoaObjetivosEstrategicosDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.PoaObjetivosEstrategicosDAO;
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
/*     */ public class PoaObjetivosEstrategicosDAO
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
/*  50 */       Utilidades.writeError("PoaObjetivosEstrategicosDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PoaObjetivosEstrategicosDTO next() {
/*     */     try {
/*  61 */       if (this.rs.next()) {
/*  62 */         return leerRegistro();
/*     */       }
/*     */     }
/*  65 */     catch (Exception e) {
/*  66 */       e.printStackTrace();
/*  67 */       Utilidades.writeError("PoaObjetivosEstrategicosDAO:next ", e);
/*     */     } 
/*  69 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PoaObjetivosEstrategicosDTO leerRegistro() {
/*     */     try {
/*  79 */       PoaObjetivosEstrategicosDTO reg = new PoaObjetivosEstrategicosDTO();
/*     */       
/*  81 */       reg.setCODIGOOBJETIVO(this.rs.getInt("CODIGO_OBJETIVO"));
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
/*  93 */       Utilidades.writeError("PoaObjetivosEstrategicosDAO:leerRegistro ", e);
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
/*     */   public Collection<PoaObjetivosEstrategicosDTO> cargarTodos(String descripcion, String estado) {
/* 106 */     Collection<PoaObjetivosEstrategicosDTO> resultados = new ArrayList<PoaObjetivosEstrategicosDTO>();
/*     */     try {
/* 108 */       String s = "select t.CODIGO_OBJETIVO,t.descripcion,t.estado,m1.DESCRIPCION as nombre_estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from POA_OBJETIVOS_ESTRATEGICOS t  left join sis_multivalores m1 on (m1.tabla='ESTADO_REGISTRO' and m1.VALOR=t.estado) where 1=1";
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
/* 138 */       Utilidades.writeError("PoaObjetivosEstrategicosDAO:cargarTodos ", e);
/*     */     } 
/* 140 */     return resultados;
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
/*     */   public Collection<PoaObjetivosEstrategicosDTO> cargarTodos(int codigo) {
/* 152 */     Collection<PoaObjetivosEstrategicosDTO> resultados = new ArrayList<PoaObjetivosEstrategicosDTO>();
/*     */     try {
/* 154 */       String s = "select t.CODIGO_OBJETIVO,t.descripcion,t.estado,m1.DESCRIPCION as nombre_estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from POA_OBJETIVOS_ESTRATEGICOS t  left join sis_multivalores m1 on (m1.tabla='ESTADO_REGISTRO' and m1.VALOR=t.estado) where 1=1";
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
/* 166 */       if (codigo > 0) {
/* 167 */         s = s + " AND t.CODIGO_OBJETIVO = " + codigo;
/*     */       }
/*     */       
/* 170 */       s = s + " order by 1";
/* 171 */       boolean rtaDB = this.dat.parseSql(s);
/* 172 */       if (!rtaDB) {
/* 173 */         return resultados;
/*     */       }
/* 175 */       this.rs = this.dat.getResultSet();
/* 176 */       while (this.rs.next()) {
/* 177 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 180 */     catch (Exception e) {
/* 181 */       e.printStackTrace();
/* 182 */       Utilidades.writeError("PoaObjetivosEstrategicosDAO:cargarTodos ", e);
/*     */     } 
/* 184 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PoaObjetivosEstrategicosDTO cargarRegistro(int CODIGOOBJETIVO) {
/*     */     try {
/* 195 */       String s = "select t.CODIGO_OBJETIVO,t.descripcion,t.estado,m1.DESCRIPCION as nombre_estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from POA_OBJETIVOS_ESTRATEGICOS t  left join sis_multivalores m1 on (m1.tabla='ESTADO_REGISTRO' and m1.VALOR=t.estado) where  t.CODIGO_OBJETIVO=" + CODIGOOBJETIVO + "";
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
/* 209 */       boolean rtaDB = this.dat.parseSql(s);
/* 210 */       if (!rtaDB) {
/* 211 */         return null;
/*     */       }
/* 213 */       this.rs = this.dat.getResultSet();
/* 214 */       if (this.rs.next()) {
/* 215 */         return leerRegistro();
/*     */       }
/*     */     }
/* 218 */     catch (Exception e) {
/* 219 */       e.printStackTrace();
/* 220 */       Utilidades.writeError("PoaObjetivosEstrategicosDAO:cargarPoaObjetivosEstrategicos", e);
/*     */     } 
/* 222 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int siguienteRegistro() {
/* 231 */     int inumero = 1;
/* 232 */     String s = "select max(CODIGO_OBJETIVO) from POA_OBJETIVOS_ESTRATEGICOS ";
/*     */     
/*     */     try {
/* 235 */       boolean rta = this.dat.parseSql(s);
/* 236 */       if (!rta) return 0; 
/* 237 */       this.rs = this.dat.getResultSet();
/* 238 */       if (this.rs.next()) {
/* 239 */         s = this.rs.getString(1);
/* 240 */         if (!this.rs.wasNull()) {
/* 241 */           inumero = Integer.parseInt(s) + 1;
/*     */         }
/*     */       } 
/* 244 */       return inumero;
/*     */     }
/* 246 */     catch (Exception e) {
/* 247 */       e.printStackTrace();
/* 248 */       Utilidades.writeError("PoaObjetivosEstrategicosDAO:siguienteRegistro ", e);
/*     */       
/* 250 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarRegistro(int CODIGOOBJETIVO) {
/* 260 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 263 */       String s = "delete from POA_OBJETIVOS_ESTRATEGICOS where  CODIGO_OBJETIVO=" + CODIGOOBJETIVO + "";
/*     */ 
/*     */ 
/*     */       
/* 267 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 269 */     catch (Exception e) {
/* 270 */       e.printStackTrace();
/* 271 */       Utilidades.writeError("PoaObjetivosEstrategicosDAO:eliminarRegistro ", e);
/* 272 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 274 */     return rta;
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
/*     */   public RespuestaBD crearRegistro(int CODIGOOBJETIVO, String descripcion, String estado, String usuarioInsercion) {
/* 288 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 290 */     int elSiguiente = siguienteRegistro();
/* 291 */     if (elSiguiente == 0) {
/* 292 */       rta.setMensaje("Generando secuencia");
/* 293 */       return rta;
/*     */     } 
/*     */     
/*     */     try {
/* 297 */       String s = "insert into POA_OBJETIVOS_ESTRATEGICOS(CODIGO_OBJETIVO,descripcion,estado,fecha_insercion,usuario_insercion) values (" + elSiguiente + "," + "'" + descripcion + "'," + "'" + estado + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
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
/* 310 */       rta = this.dat.executeUpdate2(s);
/* 311 */       rta.setSecuencia(elSiguiente);
/*     */     }
/* 313 */     catch (Exception e) {
/* 314 */       e.printStackTrace();
/* 315 */       Utilidades.writeError("%PoaObjetivosEstrategicosDAO:crearRegistro ", e);
/* 316 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 318 */     return rta;
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
/*     */   public RespuestaBD modificarRegistro(int CODIGOOBJETIVO, String descripcion, String estado, String usuarioModificacion) {
/* 332 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 335 */       String s = "update POA_OBJETIVOS_ESTRATEGICOS set  descripcion='" + descripcion + "'," + " estado='" + estado + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " CODIGO_OBJETIVO=" + CODIGOOBJETIVO + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 343 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 345 */     catch (Exception e) {
/* 346 */       e.printStackTrace();
/* 347 */       Utilidades.writeError("PoaObjetivosEstrategicosDAO:modificarRegistro ", e);
/* 348 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 350 */     return rta;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\PoaObjetivosEstrategicosDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */