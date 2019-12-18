/*     */ package sinco.data;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import sinco.business.AcumuladosPersonaDTO;
/*     */ import sinco.data.AcumuladosPersonaDAO;
/*     */ import sinco.data.DBManager;
/*     */ 
/*     */ public class AcumuladosPersonaDAO {
/*     */   ResultSet rs;
/*     */   
/*     */   public void close() {
/*     */     try {
/*  13 */       this.dat.close();
/*     */     }
/*  15 */     catch (Exception e) {}
/*     */   }
/*     */ 
/*     */   
/*  19 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*     */ 
/*     */ 
/*     */   
/*  23 */   DBManager dat = new DBManager();
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean cargarAcumulados(int anno, int mes1, int mes2, int area) {
/*     */     try {
/*  29 */       String s = "SELECT ";
/*  30 */       s = s + " acumulados_persona.codigo_persona,";
/*  31 */       s = s + " personas.nombres,personas.apellidos,";
/*  32 */       s = s + " SUM (acumulados_persona.solicitudes_recibidas) as solicitudes_recibidas,";
/*  33 */       s = s + " SUM (acumulados_persona.solicitudes_atendidas) as solicitudes_atendidas,";
/*  34 */       s = s + " SUM (acumulados_persona.solicitudes_noatendidas) as solicitudes_noatendidas,";
/*  35 */       s = s + " SUM (acumulados_persona.solicitudes_escaladas) as solicitudes_escaladas,";
/*  36 */       s = s + " SUM (acumulados_persona.oportunidad_excelente) as oportunidad_excelente,";
/*  37 */       s = s + " SUM (acumulados_persona.oportunidad_buena) as oportunidad_buena,";
/*  38 */       s = s + " SUM (acumulados_persona.oportunidad_regular) as oportunidad_regular,";
/*  39 */       s = s + " SUM (acumulados_persona.confiabilidad_excelente) as confiabilidad_excelente,";
/*  40 */       s = s + " SUM (acumulados_persona.confiabilidad_buena) as confiabilidad_buena,";
/*  41 */       s = s + " SUM (acumulados_persona.confiabilidad_regular) as confiabilidad_regular";
/*  42 */       s = s + " FROM personas,acumulados_persona ";
/*  43 */       s = s + " where personas.codigo_empleado = acumulados_persona.codigo_persona";
/*     */       
/*  45 */       if (area != -1) {
/*  46 */         s = s + " and acumulados_persona.codigo_area=" + area;
/*     */       }
/*  48 */       s = s + " and anno=" + anno;
/*  49 */       s = s + " and mes>=" + mes1;
/*  50 */       s = s + " and mes<=" + mes2;
/*     */       
/*  52 */       s = s + " group by ";
/*  53 */       s = s + " acumulados_persona.codigo_persona,";
/*  54 */       s = s + " personas.nombres,personas.apellidos";
/*  55 */       s = s + " order by personas.apellidos";
/*  56 */       boolean rta = this.dat.parseSql(s);
/*  57 */       if (!rta) return false; 
/*  58 */       this.rs = this.dat.getResultSet();
/*  59 */       return true;
/*     */     }
/*  61 */     catch (Exception e) {
/*  62 */       e.printStackTrace();
/*     */       
/*  64 */       return false;
/*     */     } 
/*     */   } public AcumuladosPersonaDTO next() {
/*     */     try {
/*  68 */       if (this.rs.next()) {
/*  69 */         AcumuladosPersonaDTO reg = new AcumuladosPersonaDTO();
/*     */         
/*  71 */         if (this.rs.getString("codigo_persona") != null) {
/*  72 */           reg.setCodigoEmpleado(Integer.parseInt(this.rs.getString("codigo_persona")));
/*     */         }
/*  74 */         if (this.rs.getString("solicitudes_recibidas") != null) {
/*  75 */           reg.setSolicitudesRecibidas(Integer.parseInt(this.rs.getString("solicitudes_recibidas")));
/*     */         }
/*  77 */         if (this.rs.getString("solicitudes_atendidas") != null) {
/*  78 */           reg.setSolicitudesAtendidas(Integer.parseInt(this.rs.getString("solicitudes_atendidas")));
/*     */         }
/*  80 */         if (this.rs.getString("solicitudes_noatendidas") != null) {
/*  81 */           reg.setSolicitudesNoAtendidas(Integer.parseInt(this.rs.getString("solicitudes_noatendidas")));
/*     */         }
/*  83 */         if (this.rs.getString("solicitudes_escaladas") != null) {
/*  84 */           reg.setSolicitudesEscaladas(Integer.parseInt(this.rs.getString("solicitudes_escaladas")));
/*     */         }
/*  86 */         if (this.rs.getString("oportunidad_excelente") != null) {
/*  87 */           reg.setOportunidadExcelente(Integer.parseInt(this.rs.getString("oportunidad_excelente")));
/*     */         }
/*  89 */         if (this.rs.getString("oportunidad_buena") != null) {
/*  90 */           reg.setOportunidadBuena(Integer.parseInt(this.rs.getString("oportunidad_buena")));
/*     */         }
/*  92 */         if (this.rs.getString("oportunidad_regular") != null) {
/*  93 */           reg.setOportunidadRegular(Integer.parseInt(this.rs.getString("oportunidad_regular")));
/*     */         }
/*  95 */         if (this.rs.getString("confiabilidad_excelente") != null) {
/*  96 */           reg.setconfiabilidad_excelente(Integer.parseInt(this.rs.getString("confiabilidad_excelente")));
/*     */         }
/*  98 */         if (this.rs.getString("confiabilidad_buena") != null) {
/*  99 */           reg.setConfiabilidadBuena(Integer.parseInt(this.rs.getString("confiabilidad_buena")));
/*     */         }
/* 101 */         if (this.rs.getString("confiabilidad_regular") != null) {
/* 102 */           reg.setConfiabilidadRegular(Integer.parseInt(this.rs.getString("confiabilidad_regular")));
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 112 */         reg.setNombres(this.rs.getString("nombres"));
/* 113 */         reg.setApellidos(this.rs.getString("apellidos"));
/* 114 */         return reg;
/*     */       }
/*     */     
/* 117 */     } catch (SQLException e) {
/* 118 */       e.printStackTrace();
/*     */     } 
/* 120 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\AcumuladosPersonaDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */