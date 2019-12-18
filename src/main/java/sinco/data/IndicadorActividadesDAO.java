/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.IndicadorActividadesDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.IndicadorActividadesDAO;
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
/*     */ public class IndicadorActividadesDAO
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
/*  50 */       Utilidades.writeError("IndicadorActividadesDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IndicadorActividadesDTO next() {
/*     */     try {
/*  61 */       if (this.rs.next()) {
/*  62 */         return leerRegistro();
/*     */       }
/*     */     }
/*  65 */     catch (Exception e) {
/*  66 */       e.printStackTrace();
/*  67 */       Utilidades.writeError("IndicadorActividadesDAO:next ", e);
/*     */     } 
/*  69 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IndicadorActividadesDTO leerRegistro() {
/*     */     try {
/*  79 */       IndicadorActividadesDTO reg = new IndicadorActividadesDTO();
/*     */       
/*  81 */       reg.setIdIndicador(this.rs.getString("id_indicador"));
/*  82 */       reg.setMes(this.rs.getString("mes"));
/*  83 */       reg.setPorcentaje(this.rs.getInt("porcentaje"));
/*  84 */       reg.setCiclo(this.rs.getInt("ciclo"));
/*  85 */       reg.setEstado(this.rs.getString("estado"));
/*  86 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  87 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  88 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  89 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  90 */       reg.setRetrasosSoluciones(this.rs.getString("RETRASOS_SOLUCIONES"));
/*  91 */       reg.setAvances(this.rs.getString("AVANCES"));
/*  92 */       reg.setLogrosBeneficios(this.rs.getString("LOGROS_BENEFICIOS"));
/*  93 */       reg.setComentariosAdicionales(this.rs.getString("COMENTARIOS_ADICIONALES"));
/*  94 */       reg.setAccionTomada(this.rs.getString("ACCION_TOMADA"));
/*  95 */       return reg;
/*     */     }
/*  97 */     catch (Exception e) {
/*  98 */       e.printStackTrace();
/*  99 */       Utilidades.writeError("IndicadorActividadesDAO:leerRegistro ", e);
/*     */       
/* 101 */       return null;
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
/*     */   public Collection<IndicadorActividadesDTO> cargarTodos(String idIndicador, String mes, int ciclo) {
/* 113 */     Collection<IndicadorActividadesDTO> resultados = new ArrayList<IndicadorActividadesDTO>();
/*     */     try {
/* 115 */       String s = "select t.id_indicador,t.id_actividad,t.mes,t.porcentaje,t.ciclo,retrasos_soluciones, avances, logros_beneficios, comentarios_adicionales, accion_tomada, t.estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from INDICADOR_ACTIVIDADES t  where 1=1";
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
/* 133 */       if (idIndicador != "") {
/* 134 */         s = s + " and t.id_indicador= " + idIndicador;
/*     */       }
/* 136 */       if (mes.length() > 0) {
/* 137 */         s = s + " and upper(t.mes) like upper('%" + mes + "%')";
/*     */       }
/* 139 */       if (ciclo > 0) {
/* 140 */         s = s + " and t.ciclo=" + ciclo;
/*     */       }
/* 142 */       s = s + " order by 1";
/* 143 */       boolean rtaDB = this.dat.parseSql(s);
/* 144 */       if (!rtaDB) {
/* 145 */         return resultados;
/*     */       }
/* 147 */       this.rs = this.dat.getResultSet();
/* 148 */       while (this.rs.next()) {
/* 149 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 152 */     catch (Exception e) {
/* 153 */       e.printStackTrace();
/* 154 */       Utilidades.writeError("IndicadorActividadesDAO:cargarTodos ", e);
/*     */     } 
/* 156 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<IndicadorActividadesDTO> cargarTodos(String idIndicador) {
/* 163 */     Collection<IndicadorActividadesDTO> resultados = new ArrayList<IndicadorActividadesDTO>();
/*     */     try {
/* 165 */       String s = "select t.id_indicador,t.mes,t.porcentaje,t.ciclo,retrasos_soluciones, avances, logros_beneficios, comentarios_adicionales, accion_tomada, t.estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from INDICADOR_ACTIVIDADES t  where 1=1";
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
/* 182 */       if (idIndicador != "") {
/* 183 */         s = s + " and t.id_indicador= '" + idIndicador + "'";
/*     */       }
/*     */ 
/*     */       
/* 187 */       s = s + " order by 1";
/* 188 */       boolean rtaDB = this.dat.parseSql(s);
/* 189 */       if (!rtaDB) {
/* 190 */         return resultados;
/*     */       }
/* 192 */       this.rs = this.dat.getResultSet();
/* 193 */       while (this.rs.next()) {
/* 194 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 197 */     catch (Exception e) {
/* 198 */       e.printStackTrace();
/* 199 */       Utilidades.writeError("IndicadorActividadesDAO:cargarTodos ", e);
/*     */     } 
/* 201 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IndicadorActividadesDTO cargarRegistro(int idIndicador) {
/*     */     try {
/* 212 */       String s = "select t.id_indicador,t.mes,t.porcentaje,t.ciclo,t.estado,retrasos_soluciones, avances, logros_beneficios, comentarios_adicionales, accion_tomada, t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from INDICADOR_ACTIVIDADES t  where  t.id_indicador=" + idIndicador + "";
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
/* 232 */       boolean rtaDB = this.dat.parseSql(s);
/* 233 */       if (!rtaDB) {
/* 234 */         return null;
/*     */       }
/* 236 */       this.rs = this.dat.getResultSet();
/* 237 */       if (this.rs.next()) {
/* 238 */         return leerRegistro();
/*     */       }
/*     */     }
/* 241 */     catch (Exception e) {
/* 242 */       e.printStackTrace();
/* 243 */       Utilidades.writeError("IndicadorActividadesDAO:cargarIndicadorActividades", e);
/*     */     } 
/* 245 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IndicadorActividadesDTO cargarRegistro(String idIndicador, String mes) {
/*     */     try {
/* 252 */       String s = "select t.id_indicador,t.mes,t.porcentaje,t.ciclo,t.estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,retrasos_soluciones, avances, logros_beneficios, comentarios_adicionales, accion_tomada, t.usuario_modificacion from INDICADOR_ACTIVIDADES t  where  t.id_indicador=" + idIndicador + " and t.mes=" + "'" + mes + "'" + "";
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
/* 272 */       boolean rtaDB = this.dat.parseSql(s);
/* 273 */       if (!rtaDB) {
/* 274 */         return null;
/*     */       }
/* 276 */       this.rs = this.dat.getResultSet();
/* 277 */       if (this.rs.next()) {
/* 278 */         return leerRegistro();
/*     */       }
/*     */     }
/* 281 */     catch (Exception e) {
/* 282 */       e.printStackTrace();
/* 283 */       Utilidades.writeError("IndicadorActividadesDAO:cargarIndicadorActividades", e);
/*     */     } 
/* 285 */     return null;
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
/*     */   public RespuestaBD eliminarRegistro(String idIndicador, String mes) {
/* 297 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 300 */       String s = "delete from INDICADOR_ACTIVIDADES where  id_indicador=" + idIndicador + "  and mes=" + "'" + mes + "'" + "";
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 305 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 307 */     catch (Exception e) {
/* 308 */       e.printStackTrace();
/* 309 */       Utilidades.writeError("IndicadorActividadesDAO:eliminarRegistro ", e);
/* 310 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 312 */     return rta;
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
/*     */   public RespuestaBD crearRegistro(String idIndicador, String mes, int porcentaje, int ciclo, String estado, String usuarioInsercion, String retrasosSoluciones, String avances, String logrosBeneficios, String comentariosAdicionales, String accionTomada) {
/* 333 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 336 */       String s = "insert into INDICADOR_ACTIVIDADES(id_indicador,mes,porcentaje,ciclo,estado,fecha_insercion,usuario_insercion,retrasos_soluciones, avances, logros_beneficios, comentarios_adicionales, accion_tomada ) values (" + idIndicador + "," + "'" + mes + "'," + "" + porcentaje + "," + "" + ciclo + "," + "'" + estado + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'," + "'" + retrasosSoluciones + "'," + "'" + avances + "'," + "'" + logrosBeneficios + "'," + "'" + comentariosAdicionales + "'," + "'" + accionTomada + "'" + ")";
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
/*     */ 
/*     */       
/* 363 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 365 */     catch (Exception e) {
/* 366 */       e.printStackTrace();
/* 367 */       Utilidades.writeError("%IndicadorActividadesDAO:crearRegistro ", e);
/* 368 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 370 */     return rta;
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
/*     */   public RespuestaBD modificarRegistro(String idIndicador, String mes, int porcentaje, int ciclo, String estado, String usuarioModificacion, String retrasosSoluciones, String avances, String logrosBeneficios, String comentariosAdicionales, String accionTomada) {
/* 391 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 394 */       String s = "update INDICADOR_ACTIVIDADES set  mes='" + mes + "'," + " porcentaje=" + porcentaje + "," + " ciclo=" + ciclo + "," + " estado='" + estado + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'," + " retrasos_soluciones = '" + retrasosSoluciones + "'," + " avances = '" + avances + "'," + " logros_beneficios = '" + logrosBeneficios + "'," + " comentarios_adicionales = '" + comentariosAdicionales + "'," + " accion_tomada = '" + accionTomada + "'" + " where" + " id_indicador=" + idIndicador + " and mes='" + mes + "'" + "";
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
/* 410 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 412 */     catch (Exception e) {
/* 413 */       e.printStackTrace();
/* 414 */       Utilidades.writeError("IndicadorActividadesDAO:modificarRegistro ", e);
/* 415 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 417 */     return rta;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\IndicadorActividadesDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */