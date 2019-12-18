/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.IndiceSatisfaccionDTO;
/*     */ import sinco.business.ParametrosDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.AcumuladosDAO;
/*     */ import sinco.data.DBManager;
/*     */ 
/*     */ public class AcumuladosDAO
/*     */ {
/*     */   public void close() {
/*     */     try {
/*  17 */       this.dat.close();
/*     */     }
/*  19 */     catch (Exception e) {}
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   ResultSet rs;
/*     */ 
/*     */   
/*  27 */   DBManager dat = new DBManager();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  35 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean cargarAcumulados(int anno, int mes1, int mes2, String secuencia) {
/*     */     try {
/*  49 */       String s = "SELECT acumulados.codigo_area,areas.descripcion,";
/*  50 */       s = s + " SUM (acumulados.solicitudes_recibidas) as solicitudes_recibidas,";
/*  51 */       s = s + " SUM (acumulados.solicitudes_atendidas) as solicitudes_atendidas,";
/*  52 */       s = s + " SUM (acumulados.solicitudes_noatendidas) as solicitudes_noatendidas,";
/*  53 */       s = s + " SUM (acumulados.solicitudes_escaladas) as solicitudes_escaladas,";
/*  54 */       s = s + " SUM (acumulados.oportunidad_excelente) as oportunidad_excelente,";
/*  55 */       s = s + " SUM (acumulados.oportunidad_buena) as oportunidad_buena,";
/*  56 */       s = s + " SUM (acumulados.oportunidad_regular) as oportunidad_regular,";
/*  57 */       s = s + " SUM (acumulados.confiabilidad_excelente) as confiabilidad_excelente,";
/*  58 */       s = s + " SUM (acumulados.confiabilidad_buena) as confiabilidad_buena,";
/*  59 */       s = s + " SUM (acumulados.confiabilidad_regular) as confiabilidad_regular";
/*  60 */       s = s + " FROM unidades_dependencia areas,acumulados ";
/*  61 */       s = s + " where areas.codigo = acumulados.codigo_area";
/*  62 */       s = s + " and areas.secuencia like '" + secuencia + "%'";
/*  63 */       s = s + " and anno=" + anno;
/*  64 */       s = s + " and mes>=" + mes1;
/*  65 */       s = s + " and mes<=" + mes2;
/*  66 */       s = s + " GROUP BY acumulados.codigo_area,areas.descripcion";
/*  67 */       s = s + " order by areas.descripcion";
/*  68 */       boolean rta = this.dat.parseSql(s);
/*  69 */       if (!rta) return false; 
/*  70 */       this.rs = this.dat.getResultSet();
/*  71 */       return true;
/*     */     }
/*  73 */     catch (Exception e) {
/*  74 */       e.printStackTrace();
/*     */       
/*  76 */       return false;
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
/*     */   public boolean cargarAcumuladosAdmin(int anno, int mes1, int mes2) {
/*     */     try {
/*  89 */       String s = "SELECT acumulados.codigo_area,areas.descripcion,";
/*  90 */       s = s + " SUM (acumulados.solicitudes_recibidas) as solicitudes_recibidas,";
/*  91 */       s = s + " SUM (acumulados.solicitudes_atendidas) as solicitudes_atendidas,";
/*  92 */       s = s + " SUM (acumulados.solicitudes_noatendidas) as solicitudes_noatendidas,";
/*  93 */       s = s + " SUM (acumulados.solicitudes_escaladas) as solicitudes_escaladas,";
/*  94 */       s = s + " SUM (acumulados.oportunidad_excelente) as oportunidad_excelente,";
/*  95 */       s = s + " SUM (acumulados.oportunidad_buena) as oportunidad_buena,";
/*  96 */       s = s + " SUM (acumulados.oportunidad_regular) as oportunidad_regular,";
/*  97 */       s = s + " SUM (acumulados.confiabilidad_excelente) as confiabilidad_excelente,";
/*  98 */       s = s + " SUM (acumulados.confiabilidad_buena) as confiabilidad_buena,";
/*  99 */       s = s + " SUM (acumulados.confiabilidad_regular) as confiabilidad_regular";
/* 100 */       s = s + " FROM unidades_dependencia areas LEFT JOIN acumulados ON (areas.codigo = acumulados.codigo_area)";
/* 101 */       s = s + " where areas.nivel<=" + ParametrosDTO.getInt("nivel.produndidad");
/* 102 */       s = s + " and anno=" + anno;
/* 103 */       s = s + " and mes>=" + mes1;
/* 104 */       s = s + " and mes<=" + mes2;
/* 105 */       s = s + " GROUP BY acumulados.codigo_area,areas.descripcion";
/* 106 */       s = s + " order by areas.descripcion";
/* 107 */       boolean rta = this.dat.parseSql(s);
/* 108 */       if (!rta) return false; 
/* 109 */       this.rs = this.dat.getResultSet();
/* 110 */       return true;
/*     */     }
/* 112 */     catch (Exception e) {
/* 113 */       e.printStackTrace();
/*     */       
/* 115 */       return false;
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
/*     */   public boolean cargarAreaSuperior(int anno, int mes1, int mes2, int area) {
/*     */     try {
/* 129 */       String s = "SELECT acumulados.codigo_area,areas.descripcion,";
/* 130 */       s = s + " SUM (acumulados.solicitudes_recibidas) as solicitudes_recibidas,";
/* 131 */       s = s + " SUM (acumulados.solicitudes_atendidas) as solicitudes_atendidas,";
/* 132 */       s = s + " SUM (acumulados.solicitudes_noatendidas) as solicitudes_noatendidas,";
/* 133 */       s = s + " SUM (acumulados.solicitudes_escaladas) as solicitudes_escaladas,";
/* 134 */       s = s + " SUM (acumulados.oportunidad_excelente) as oportunidad_excelente,";
/* 135 */       s = s + " SUM (acumulados.oportunidad_buena) as oportunidad_buena,";
/* 136 */       s = s + " SUM (acumulados.oportunidad_regular) as oportunidad_regular,";
/* 137 */       s = s + " SUM (acumulados.confiabilidad_excelente) as confiabilidad_excelente,";
/* 138 */       s = s + " SUM (acumulados.confiabilidad_buena) as confiabilidad_buena,";
/* 139 */       s = s + " SUM (acumulados.confiabilidad_regular) as confiabilidad_regular";
/* 140 */       s = s + " FROM unidades_dependencia areas,acumulados ";
/* 141 */       s = s + " where areas.codigo = acumulados.codigo_area";
/* 142 */       s = s + " and acumulados.area_superior=" + area;
/* 143 */       s = s + " and anno=" + anno;
/* 144 */       s = s + " and mes>=" + mes1;
/* 145 */       s = s + " and mes<=" + mes2;
/* 146 */       s = s + " GROUP BY acumulados.codigo_area,areas.descripcion";
/* 147 */       s = s + " order by areas.descripcion";
/* 148 */       boolean rta = this.dat.parseSql(s);
/* 149 */       if (!rta) return false; 
/* 150 */       this.rs = this.dat.getResultSet();
/* 151 */       return true;
/*     */     }
/* 153 */     catch (Exception e) {
/* 154 */       e.printStackTrace();
/*     */       
/* 156 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IndiceSatisfaccionDTO next() {
/*     */     try {
/* 165 */       if (this.rs.next()) {
/* 166 */         IndiceSatisfaccionDTO reg = new IndiceSatisfaccionDTO();
/* 167 */         reg.setCodigoArea(this.rs.getInt("codigo_area"));
/* 168 */         reg.setRecibidas(this.rs.getInt("solicitudes_recibidas"));
/* 169 */         reg.setAtendidas(this.rs.getInt("solicitudes_atendidas"));
/* 170 */         reg.setPorAtender(this.rs.getInt("solicitudes_noatendidas"));
/* 171 */         reg.setEscaladas(this.rs.getInt("solicitudes_escaladas"));
/* 172 */         reg.setOportunidadExcelente(this.rs.getInt("oportunidad_excelente"));
/* 173 */         reg.setOportunidadBuena(this.rs.getInt("oportunidad_buena"));
/* 174 */         reg.setOportunidadRegular(this.rs.getInt("oportunidad_regular"));
/* 175 */         reg.setConfiabilidadExcelente(this.rs.getInt("confiabilidad_excelente"));
/* 176 */         reg.setConfiabilidadBuena(this.rs.getInt("confiabilidad_buena"));
/* 177 */         reg.setConfiabilidadRegular(this.rs.getInt("confiabilidad_regular"));
/* 178 */         reg.setNombreArea(this.rs.getString("descripcion"));
/* 179 */         return reg;
/*     */       }
/*     */     
/* 182 */     } catch (SQLException e) {
/* 183 */       e.printStackTrace();
/*     */     } 
/* 185 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection cargarIndiceSecuencia(String secuencia, int anno, int mes1, int mes2) {
/* 196 */     Collection resultados = new ArrayList();
/*     */     
/*     */     try {
/* 199 */       String s = "SELECT ac.codigo_area,areas.descripcion, ac.anno, ac.mes, SUM (ac.solicitudes_recibidas) as solicitudes_recibidas, SUM (ac.solicitudes_atendidas) as solicitudes_atendidas, SUM (ac.solicitudes_noatendidas) as solicitudes_noatendidas, SUM (ac.solicitudes_escaladas) as solicitudes_escaladas, SUM (ac.oportunidad_excelente) as oportunidad_excelente, SUM (ac.oportunidad_buena) as oportunidad_buena, SUM (ac.oportunidad_regular) as oportunidad_regular, SUM (ac.confiabilidad_excelente) as confiabilidad_excelente, SUM (ac.confiabilidad_buena) as confiabilidad_buena, SUM (ac.confiabilidad_regular) as confiabilidad_regular FROM unidades_dependencia areas,acumulados ac where areas.codigo = ac.codigo_area and areas.secuencia like '" + secuencia + "%'" + " and ac.anno=" + anno + " and ac.mes>=" + mes1 + " and ac.mes<=" + mes2 + " GROUP BY ac.codigo_area,areas.descripcion,ac.anno,ac.mes" + " order by areas.descripcion,ac.anno,ac.mes";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 221 */       boolean rtaDB = this.dat.parseSql(s);
/*     */       
/* 223 */       if (!rtaDB) {
/* 224 */         return resultados;
/*     */       }
/* 226 */       this.rs = this.dat.getResultSet();
/* 227 */       while (this.rs.next()) {
/*     */         
/* 229 */         IndiceSatisfaccionDTO reg = new IndiceSatisfaccionDTO();
/*     */         
/* 231 */         reg.setCodigoArea(this.rs.getInt("codigo_area"));
/* 232 */         reg.setAnno(this.rs.getInt("anno"));
/* 233 */         reg.setMes(this.rs.getInt("mes"));
/* 234 */         reg.setRecibidas(Integer.parseInt(this.rs.getString("solicitudes_recibidas")));
/* 235 */         reg.setAtendidas(Integer.parseInt(this.rs.getString("solicitudes_atendidas")));
/* 236 */         reg.setPorAtender(Integer.parseInt(this.rs.getString("solicitudes_noatendidas")));
/* 237 */         reg.setEscaladas(Integer.parseInt(this.rs.getString("solicitudes_escaladas")));
/* 238 */         reg.setOportunidadExcelente(Integer.parseInt(this.rs.getString("oportunidad_excelente")));
/* 239 */         reg.setOportunidadBuena(Integer.parseInt(this.rs.getString("oportunidad_buena")));
/* 240 */         reg.setOportunidadRegular(Integer.parseInt(this.rs.getString("oportunidad_regular")));
/* 241 */         reg.setConfiabilidadExcelente(Integer.parseInt(this.rs.getString("confiabilidad_excelente")));
/* 242 */         reg.setConfiabilidadBuena(Integer.parseInt(this.rs.getString("confiabilidad_buena")));
/* 243 */         reg.setConfiabilidadRegular(Integer.parseInt(this.rs.getString("confiabilidad_regular")));
/* 244 */         reg.setNombreArea(this.rs.getString("descripcion"));
/* 245 */         resultados.add(reg);
/*     */       } 
/* 247 */       this.rs = this.dat.getResultSet();
/*     */     }
/* 249 */     catch (Exception e) {
/* 250 */       e.printStackTrace();
/* 251 */       Utilidades.writeError("AcumuladosFactory:cargarIndiceSecuencia ", e);
/*     */     } 
/* 253 */     return resultados;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\AcumuladosDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */