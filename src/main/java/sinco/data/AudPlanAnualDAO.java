/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.AudPlanAnualDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.AudPlanAnualDAO;
/*     */ import sinco.data.DBManager;
/*     */ 

/*     */ public class AudPlanAnualDAO
/*     */ {
/*     */   public AudPlanAnualDTO leerRegistro(ResultSet rs) {
/*     */     try {
/*  35 */       AudPlanAnualDTO reg = new AudPlanAnualDTO();
/*     */       
/*  37 */       reg.setCiclo(rs.getInt("ciclo"));
/*  38 */       reg.setObjetivo(rs.getString("objetivo"));
/*  39 */       reg.setAlcance(rs.getString("alcance"));
/*  40 */       reg.setCriterios(rs.getString("criterios"));
/*  41 */       reg.setRecursos(rs.getString("recursos"));
/*  42 */       reg.setFechaInsercion(rs.getString("fecha_insercion"));
/*  43 */       reg.setUsuarioInsercion(rs.getString("usuario_insercion"));
/*  44 */       reg.setFechaModificacion(rs.getString("fecha_modificacion"));
/*  45 */       reg.setUsuarioModificacion(rs.getString("usuario_modificacion"));
/*  46 */       return reg;
/*     */     }
/*  48 */     catch (Exception e) {
/*  49 */       e.printStackTrace();
/*  50 */       Utilidades.writeError("AudPlanAnualDAO:leerRegistro ", e);
/*     */       
/*  52 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<AudPlanAnualDTO> cargarTodos(int ciclo) {
/*  62 */     Collection<AudPlanAnualDTO> resultados = new ArrayList<AudPlanAnualDTO>();
/*     */     
/*  64 */   DBManager  dat = new DBManager();
/*     */     try {
/*  66 */       String s = "select t.ciclo,t.objetivo,t.alcance,t.criterios,t.recursos,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from aud_plan_anual t  where 1=1";

/*     */       
/*  78 */       if (ciclo > 0) {
/*  79 */         s = s + " and t.ciclo=" + ciclo;
/*     */       }
/*  81 */       s = s + " order by 1";
/*  82 */       boolean rtaDB = dat.parseSql(s);
/*  83 */       if (!rtaDB) {
/*  84 */         return resultados;
/*     */       }
/*  86 */       ResultSet rs = dat.getResultSet();
/*  87 */       while (rs.next()) {
/*  88 */         resultados.add(leerRegistro(rs));
/*     */       }
/*     */     }
/*  91 */     catch (Exception e) {
/*  92 */       e.printStackTrace();
/*  93 */       Utilidades.writeError("AudPlanAnualDAO:cargarTodos ", e);
/*     */     } finally {
/*     */       
/*  96 */       dat.close();
/*     */     } 
/*  98 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AudPlanAnualDTO cargarRegistro(int ciclo) {
/* 108 */   DBManager  dat = new DBManager();
/*     */     try {
/* 110 */       String s = "select t.ciclo,t.objetivo,t.alcance,t.criterios,t.recursos,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from aud_plan_anual t  where  t.ciclo=" + ciclo + "";
/*     */ 
  
/* 124 */       boolean rtaDB = dat.parseSql(s);
/* 125 */       if (!rtaDB) {
/* 126 */         return null;
/*     */       }
/* 128 */       ResultSet rs = dat.getResultSet();
/* 129 */       if (rs.next()) {
/* 130 */         return leerRegistro(rs);
/*     */       }
/*     */     }
/* 133 */     catch (Exception e) {
/* 134 */       e.printStackTrace();
/* 135 */       Utilidades.writeError("AudPlanAnualDAO:cargarAudPlanAnual", e);
/*     */     } finally {
/*     */       
/* 138 */       dat.close();
/*     */     } 
/* 140 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarRegistro(int ciclo) {
/* 150 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 152 */   DBManager  dat = new DBManager();
/*     */     try {
/* 154 */       String s = "delete from aud_plan_anual where  ciclo=" + ciclo + "";
/*     */ 
/*     */ 
/*     */       
/* 158 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 160 */     catch (Exception e) {
/* 161 */       e.printStackTrace();
/* 162 */       Utilidades.writeError("AudPlanAnualDAO:eliminarRegistro ", e);
/* 163 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 166 */       dat.close();
/*     */     } 
/* 168 */     return rta;
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
/*     */   public RespuestaBD crearRegistro(int ciclo, String objetivo, String alcance, String criterios, String recursos, String usuarioInsercion) {
/* 184 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 186 */   DBManager  dat = new DBManager();
/*     */     try {
/* 188 */       String s = "insert into aud_plan_anual(ciclo,objetivo,alcance,criterios,recursos,fecha_insercion,usuario_insercion) values (" + ciclo + "," + "'" + objetivo + "'," + "'" + alcance + "'," + "'" + criterios + "'," + "'" + recursos + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";

/*     */       
/* 205 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 207 */     catch (Exception e) {
/* 208 */       e.printStackTrace();
/* 209 */       Utilidades.writeError("%AudPlanAnualDAO:crearRegistro ", e);
/* 210 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 213 */       dat.close();
/*     */     } 
/* 215 */     return rta;
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
/*     */   public RespuestaBD modificarRegistro(int ciclo, String objetivo, String alcance, String criterios, String recursos, String usuarioModificacion) {
/* 231 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 233 */   DBManager  dat = new DBManager();
/*     */     try {
/* 235 */       String s = "update aud_plan_anual set  objetivo='" + objetivo + "'," + " alcance='" + alcance + "'," + " criterios='" + criterios + "'," + " recursos='" + recursos + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " ciclo=" + ciclo + "";
/*     */ 

/*     */ 
/*     */       
/* 245 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 247 */     catch (Exception e) {
/* 248 */       e.printStackTrace();
/* 249 */       Utilidades.writeError("AudPlanAnualDAO:modificarRegistro ", e);
/* 250 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 253 */       dat.close();
/*     */     } 
/* 255 */     return rta;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\AudPlanAnualDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */