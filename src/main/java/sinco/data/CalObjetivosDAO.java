/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.CalObjetivosDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.CalObjetivosDAO;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CalObjetivosDAO
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
/*  50 */       Utilidades.writeError("CalObjetivosDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalObjetivosDTO next() {
/*     */     try {
/*  61 */       if (this.rs.next()) {
/*  62 */         return leerRegistro();
/*     */       }
/*     */     }
/*  65 */     catch (Exception e) {
/*  66 */       e.printStackTrace();
/*  67 */       Utilidades.writeError("CalObjetivosDAO:next ", e);
/*     */     } 
/*  69 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalObjetivosDTO leerRegistro() {
/*     */     try {
/*  79 */       CalObjetivosDTO reg = new CalObjetivosDTO();
/*     */ 
/*     */ 
/*     */       
/*  83 */       reg.setCodigoObjetivo(this.rs.getInt("codigo"));
/*     */       
/*  85 */       reg.setProceso(this.rs.getString("proceso"));
/*  86 */       reg.setSubProceso(this.rs.getString("subproceso"));
/*  87 */       reg.setDescripcion(this.rs.getString("descripcion"));
/*  88 */       reg.setJustificacion(this.rs.getString("justificacion"));
/*  89 */       reg.setTipoObjetivo(this.rs.getString("tipo_objetivo"));
/*  90 */       reg.setPerspectiva(this.rs.getInt("perspectiva"));
/*  91 */       reg.setEstado(this.rs.getString("estado"));
/*  92 */       reg.setAgregaValor(this.rs.getString("agrega_valor"));
/*  93 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  94 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  95 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  96 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*     */       
/*  98 */       reg.setNombreTipoObjetivo(this.rs.getString("nombre_tipo_objetivo"));
/*  99 */       reg.setNombreProceso(this.rs.getString("nombre_proceso"));
/* 100 */       reg.setNombreSubProceso(this.rs.getString("nombre_subproceso"));
/*     */       
/* 102 */       return reg;
/*     */     }
/* 104 */     catch (Exception e) {
/* 105 */       e.printStackTrace();
/* 106 */       Utilidades.writeError("CalObjetivosDAO:leerRegistro ", e);
/*     */       
/* 108 */       return null;
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
/*     */   public Collection<CalObjetivosDTO> cargarTodos(String proceso, String descripcion, String tipoObjetivo) {
/* 121 */     Collection<CalObjetivosDTO> resultados = new ArrayList<CalObjetivosDTO>();
/*     */     try {
/* 123 */       String s = "select o.*,m.descripcion as nombre_tipo_objetivo,p.descripcion as nombre_proceso,s.descripcion as nombre_subproceso from cal_objetivos o,procesos p,subprocesos s,sis_multivalores m where o.proceso=p.codigo and o.proceso=s.proceso and o.subproceso=s.codigo and m.tabla='CAL_TIPO_OBJETIVO' and m.valor=o.tipo_objetivo and p.estado='A' and s.estado='A'";
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
/* 136 */       if (proceso.length() > 0) {
/* 137 */         s = s + " and upper(o.proceso) like upper('%" + proceso + "%')";
/*     */       }
/* 139 */       if (descripcion.length() > 0) {
/* 140 */         s = s + " and upper(o.descripcion) like upper('%" + descripcion + "%')";
/*     */       }
/* 142 */       if (tipoObjetivo.length() > 0) {
/* 143 */         s = s + " and upper(o.tipo_objetivo) like upper('%" + tipoObjetivo + "%')";
/*     */       }
/* 145 */       s = s + " order by 1";
/* 146 */       boolean rtaDB = this.dat.parseSql(s);
/* 147 */       if (!rtaDB) {
/* 148 */         return resultados;
/*     */       }
/* 150 */       this.rs = this.dat.getResultSet();
/* 151 */       while (this.rs.next()) {
/* 152 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 155 */     catch (Exception e) {
/* 156 */       e.printStackTrace();
/* 157 */       Utilidades.writeError("CalObjetivosDAO:cargarTodos ", e);
/*     */     } 
/* 159 */     return resultados;
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
/*     */   public Collection<CalObjetivosDTO> cargarUsadosEnPeriodo(int ciclo, String proceso) {
/* 172 */     Collection<CalObjetivosDTO> resultados = new ArrayList<CalObjetivosDTO>();
/*     */     try {
/* 174 */       String s = "select o.Codigo,        o.Descripcion from   Cal_Objetivos    o,        Procesos         p,        Subprocesos      s,        Sis_Multivalores m where  o.Proceso = p.Codigo    and o.Proceso = s.Proceso    and o.Subproceso = s.Codigo    and m.Tabla = 'CAL_TIPO_OBJETIVO'    and m.Valor = o.Tipo_Objetivo    and p.Estado = 'A'    and s.Estado = 'A'    and o.Estado = 'A'";
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
/* 188 */       if (proceso.length() > 0) {
/* 189 */         s = s + "    and o.Proceso = '" + proceso + "'";
/*     */       }
/* 191 */       s = s + "    and exists (select 'X'       from   Cal_Plan_Objetivos Po       where  Po.Codigo_Ciclo = " + ciclo + "           and Po.Codigo_Objetivo = o.Codigo)" + " order  by o.Descripcion";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 197 */       boolean rtaDB = this.dat.parseSql(s);
/* 198 */       if (!rtaDB) {
/* 199 */         return resultados;
/*     */       }
/* 201 */       this.rs = this.dat.getResultSet();
/* 202 */       while (this.rs.next()) {
/* 203 */         CalObjetivosDTO reg = new CalObjetivosDTO();
/*     */         
/* 205 */         reg.setCodigoObjetivo(this.rs.getInt("codigo"));
/* 206 */         reg.setDescripcion(this.rs.getString("descripcion"));
/* 207 */         resultados.add(reg);
/*     */       }
/*     */     
/* 210 */     } catch (Exception e) {
/* 211 */       e.printStackTrace();
/* 212 */       Utilidades.writeError("CalObjetivosDAO:cargarTodos ", e);
/*     */     } 
/* 214 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalObjetivosDTO cargarRegistro(int codigo) {
/*     */     try {
/* 224 */       String s = "select o.*,' ' as nombre_tipo_objetivo,' ' as nombre_proceso,' ' as nombre_subproceso from cal_objetivos o where  codigo=" + codigo;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 231 */       boolean rtaDB = this.dat.parseSql(s);
/* 232 */       if (!rtaDB) {
/* 233 */         return null;
/*     */       }
/* 235 */       this.rs = this.dat.getResultSet();
/* 236 */       if (this.rs.next()) {
/* 237 */         return leerRegistro();
/*     */       }
/*     */     }
/* 240 */     catch (Exception e) {
/* 241 */       e.printStackTrace();
/* 242 */       Utilidades.writeError("CalObjetivosDAO:cargarCalObjetivos", e);
/*     */     } 
/* 244 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalObjetivosDTO cargarRegistroPorProceso(String proceso) {
/*     */     try {
/* 255 */       String s = "select o.*,' ' as nombre_tipo_objetivo,' ' as nombre_proceso,' ' as nombre_subproceso from cal_objetivos o where  proceso='" + proceso + "'" + " and tipo_objetivo='G'" + " and subproceso=''";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 264 */       boolean rtaDB = this.dat.parseSql(s);
/* 265 */       if (!rtaDB) {
/* 266 */         return null;
/*     */       }
/* 268 */       this.rs = this.dat.getResultSet();
/* 269 */       if (this.rs.next()) {
/* 270 */         return leerRegistro();
/*     */       }
/*     */     }
/* 273 */     catch (Exception e) {
/* 274 */       e.printStackTrace();
/* 275 */       Utilidades.writeError("CalObjetivosDAO:cargarCalObjetivos", e);
/*     */     } 
/* 277 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int siguienteRegistro() {
/* 286 */     int inumero = 1;
/* 287 */     String s = "select max(codigo) from cal_objetivos ";
/*     */     try {
/* 289 */       boolean rta = this.dat.parseSql(s);
/* 290 */       if (!rta) return 0; 
/* 291 */       this.rs = this.dat.getResultSet();
/* 292 */       if (this.rs.next()) {
/* 293 */         s = this.rs.getString(1);
/* 294 */         if (!this.rs.wasNull()) {
/* 295 */           inumero = Integer.parseInt(s) + 1;
/*     */         }
/*     */       } 
/* 298 */       return inumero;
/*     */     }
/* 300 */     catch (Exception e) {
/* 301 */       e.printStackTrace();
/* 302 */       Utilidades.writeError("CalObjetivosDAO:siguienteRegistro ", e);
/*     */       
/* 304 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eliminarRegistro(int codigo) {
/*     */     try {
/* 314 */       String s = "delete from cal_objetivos where  codigo=" + codigo + "";
/*     */ 
/*     */ 
/*     */       
/* 318 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 321 */     catch (Exception e) {
/* 322 */       e.printStackTrace();
/* 323 */       Utilidades.writeError("CalObjetivosDAO:eliminarRegistro ", e);
/*     */       
/* 325 */       return false;
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
/*     */   public boolean crearRegistro(int codigo, String proceso, String subproceso, String descripcion, String justificacion, String tipoObjetivo, int perspectiva, String estado, String agregaValor, String usuarioInsercion) {
/* 345 */     RespuestaBD rta = new RespuestaBD();
/* 346 */     int elSiguiente = siguienteRegistro();
/* 347 */     if (elSiguiente == 0) {
/* 348 */       rta.setMensaje("Generando secuencia");
/* 349 */       return false;
/*     */     } 
/*     */ 
/*     */     
/*     */     try {
/* 354 */       String s = "insert into cal_objetivos(codigo,proceso,subproceso,descripcion,justificacion,tipo_objetivo,perspectiva,estado,agrega_valor,fecha_insercion,usuario_insercion) values (" + elSiguiente + "," + "'" + proceso + "'," + "'" + subproceso + "'," + "'" + descripcion + "'," + "'" + justificacion + "'," + "'" + tipoObjetivo + "'," + "" + perspectiva + "," + "'" + estado + "'," + "'" + agregaValor + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
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
/*     */ 
/*     */ 
/*     */       
/* 379 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 382 */     catch (Exception e) {
/* 383 */       e.printStackTrace();
/* 384 */       Utilidades.writeError("%CalObjetivosDAO:crearRegistro ", e);
/*     */       
/* 386 */       return false;
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
/*     */   public boolean modificarRegistro(int codigo, String proceso, String subproceso, String descripcion, String justificacion, String tipoObjetivo, int perspectiva, String estado, String agregaValor, String usuarioModificacion) {
/*     */     try {
/* 407 */       String s = "update cal_objetivos set  proceso='" + proceso + "'," + " subproceso='" + subproceso + "'," + " descripcion='" + descripcion + "'," + " justificacion='" + justificacion + "'," + " tipo_objetivo='" + tipoObjetivo + "'," + " perspectiva=" + perspectiva + "," + " estado='" + estado + "'," + " agrega_valor='" + agregaValor + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " codigo=" + codigo + "";
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
/* 421 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 424 */     catch (Exception e) {
/* 425 */       e.printStackTrace();
/* 426 */       Utilidades.writeError("CalObjetivosDAO:modificarRegistro ", e);
/*     */       
/* 428 */       return false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\CalObjetivosDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */