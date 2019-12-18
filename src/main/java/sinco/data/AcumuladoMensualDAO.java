/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import sinco.business.AcumuladoMensualDTO;
/*     */ import sinco.data.AcumuladoMensualDAO;
/*     */ import sinco.data.DBManager;
/*     */ 
/*     */ 
/*     */ public class AcumuladoMensualDAO
/*     */ {
/*     */   ResultSet rs;
/*     */   ResultSet rs2;
/*  14 */   DBManager dat = new DBManager();
/*     */ 
/*     */   
/*  17 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*     */ 
/*     */   
/*     */   public boolean cargarTodos() {
/*     */     try {
/*  22 */       boolean rta = this.dat.parseSql("select * from acumulados_mensuales ORDER BY anno");
/*  23 */       if (!rta) return false; 
/*  24 */       this.rs = this.dat.getResultSet();
/*  25 */       return rta;
/*     */     }
/*  27 */     catch (Exception e) {
/*  28 */       e.printStackTrace();
/*     */       
/*  30 */       return false;
/*     */     } 
/*     */   } public void close() {
/*     */     try {
/*  34 */       this.dat.close();
/*     */     }
/*  36 */     catch (Exception e) {}
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean cargarPorParametros(int codarea, int codservicio, int anno, int mes1, int mes2) {
/*     */     try {
/*  42 */       boolean rta = false;
/*  43 */       if (codarea != -1 && codservicio != -1) {
/*  44 */         rta = this.dat.parseSql("select AVG(anno) as ano, AVG(mes) as mesi,AVG(codigo_area) as cai,AVG(codigo_servicio) as csi,AVG(tiempo_establecido) as tei,SUM(tiempo_promedio*servicios_solicitados)/SUM(servicios_solicitados) as tpi,SUM(esc_nivel_1) as en1,SUM(esc_nivel_2) as en2,SUM(esc_nivel_3) as en3,SUM(esc_nivel_4) as en4,SUM(esc_nivel_5) as en5,SUM(esc_nivel_6) as en6,SUM(cantidad_clientes) as cci,SUM(servicios_solicitados) as ssi,SUM(servicios_tiempo) as sti,SUM(servicios_fuera_tiempo) as sfti,SUM(servicios_no_prestados) as snpi,SUM(confiabilidad_excelentes) as cei,SUM(confiabilidad_buenas) as cbi,SUM(confiabilidad_regulares) as cri from acumulados_mensuales where codigo_area=" + codarea + " AND codigo_servicio=" + codservicio + " AND anno=" + anno + " AND mes>=" + mes1 + " AND mes <=" + mes2);
/*  45 */       } else if (codarea == -1 && codservicio != -1) {
/*  46 */         rta = this.dat.parseSql("select AVG(anno) as ano, AVG(mes) as mesi,AVG(codigo_area) as cai,AVG(codigo_servicio) as csi,AVG(tiempo_establecido) as tei,SUM(tiempo_promedio*servicios_solicitados)/SUM(servicios_solicitados) as tpi,SUM(esc_nivel_1) as en1,SUM(esc_nivel_2) as en2,SUM(esc_nivel_3) as en3,SUM(esc_nivel_4) as en4,SUM(esc_nivel_5) as en5,SUM(esc_nivel_6) as en6,SUM(cantidad_clientes) as cci,SUM(servicios_solicitados) as ssi,SUM(servicios_tiempo) as sti,SUM(servicios_fuera_tiempo) as sfti,SUM(servicios_no_prestados) as snpi,SUM(confiabilidad_excelentes) as cei,SUM(confiabilidad_buenas) as cbi,SUM(confiabilidad_regulares) as cri from acumulados_mensuales where codigo_servicio=" + codservicio + " AND anno=" + anno + " AND mes>=" + mes1 + " AND mes <=" + mes2);
/*  47 */       } else if (codarea != -1 && codservicio == -1) {
/*  48 */         rta = this.dat.parseSql("select AVG(anno) as ano, AVG(mes) as mesi,AVG(codigo_area) as cai,AVG(codigo_servicio) as csi,AVG(tiempo_establecido) as tei,SUM(tiempo_promedio*servicios_solicitados)/SUM(servicios_solicitados) as tpi,SUM(esc_nivel_1) as en1,SUM(esc_nivel_2) as en2,SUM(esc_nivel_3) as en3,SUM(esc_nivel_4) as en4,SUM(esc_nivel_5) as en5,SUM(esc_nivel_6) as en6,SUM(cantidad_clientes) as cci,SUM(servicios_solicitados) as ssi,SUM(servicios_tiempo) as sti,SUM(servicios_fuera_tiempo) as sfti,SUM(servicios_no_prestados) as snpi,SUM(confiabilidad_excelentes) as cei,SUM(confiabilidad_buenas) as cbi,SUM(confiabilidad_regulares) as cri from acumulados_mensuales where codigo_area=" + codarea + " AND anno=" + anno + " AND mes>=" + mes1 + " AND mes <=" + mes2 + " GROUP BY codigo_servicio");
/*     */       } 
/*  50 */       if (!rta) return false;
/*     */       
/*  52 */       this.rs = this.dat.getResultSet();
/*  53 */       return true;
/*     */     }
/*  55 */     catch (Exception e) {
/*  56 */       e.printStackTrace();
/*     */       
/*  58 */       return false;
/*     */     } 
/*     */   }
/*     */   public boolean cargarTodosAnnos() {
/*     */     try {
/*  63 */       boolean rta = this.dat.parseSql("select DISTINCT(anno) from acumulados_mensuales ORDER BY anno");
/*  64 */       if (!rta) return false; 
/*  65 */       this.rs2 = this.dat.getResultSet();
/*  66 */       return true;
/*     */     }
/*  68 */     catch (Exception e) {
/*  69 */       e.printStackTrace();
/*     */       
/*  71 */       return false;
/*     */     } 
/*     */   }
/*     */   public int nextAnno() {
/*     */     try {
/*  76 */       if (this.rs2.next()) {
/*  77 */         return Integer.parseInt(this.rs2.getString("anno"));
/*     */       }
/*     */       
/*  80 */       return -1;
/*     */     
/*     */     }
/*  83 */     catch (SQLException e) {
/*  84 */       e.printStackTrace();
/*     */       
/*  86 */       return -1;
/*     */     } 
/*     */   }
/*     */   
/*     */   public AcumuladoMensualDTO getAcumuladoMensual(int anno, int mes, int codigoarea, int codigoservicio) {
/*     */     try {
/*  92 */       boolean rta = this.dat.parseSql("select * from acumulados_mensuales where anno=" + anno + " AND mes=" + mes + " AND codigo_area=" + codigoarea + " AND codigo_servicio=" + codigoservicio);
/*  93 */       if (!rta) return null; 
/*  94 */       this.rs = this.dat.getResultSet();
/*  95 */       if (this.rs.next()) {
/*  96 */         AcumuladoMensualDTO am = new AcumuladoMensualDTO();
/*  97 */         am.setAnno(anno);
/*  98 */         am.setMes(mes);
/*  99 */         am.setCodigoArea(codigoarea);
/* 100 */         am.setCodigoServicio(codigoservicio);
/* 101 */         am.setTiempoEstablecido(Integer.parseInt(this.rs.getString("tiempo_establecido")));
/* 102 */         String temp = new String(this.rs.getString("tiempo_promedio"));
/* 103 */         if (!this.rs.wasNull()) {
/* 104 */           am.setTiempoPromedio(Integer.parseInt(this.rs.getString("tiempo_promedio")));
/*     */         }
/* 106 */         temp = this.rs.getString("esc_nivel_1");
/* 107 */         if (!this.rs.wasNull()) {
/* 108 */           am.setEscNivel1(Integer.parseInt(this.rs.getString("esc_nivel_1")));
/*     */         }
/* 110 */         temp = this.rs.getString("esc_nivel_2");
/* 111 */         if (!this.rs.wasNull()) {
/* 112 */           am.setEscNivel2(Integer.parseInt(this.rs.getString("esc_nivel_2")));
/*     */         }
/* 114 */         temp = this.rs.getString("esc_nivel_3");
/* 115 */         if (!this.rs.wasNull()) {
/* 116 */           am.setEscNivel3(Integer.parseInt(this.rs.getString("esc_nivel_3")));
/*     */         }
/* 118 */         temp = this.rs.getString("esc_nivel_4");
/* 119 */         if (!this.rs.wasNull()) {
/* 120 */           am.setEscNivel4(Integer.parseInt(this.rs.getString("esc_nivel_4")));
/*     */         }
/* 122 */         temp = this.rs.getString("esc_nivel_5");
/* 123 */         if (!this.rs.wasNull()) {
/* 124 */           am.setEscNivel5(Integer.parseInt(this.rs.getString("esc_nivel_5")));
/*     */         }
/* 126 */         temp = this.rs.getString("esc_nivel_6");
/* 127 */         if (!this.rs.wasNull()) {
/* 128 */           am.setEscNivel6(Integer.parseInt(this.rs.getString("esc_nivel_6")));
/*     */         }
/* 130 */         temp = this.rs.getString("cantidad_clientes");
/* 131 */         if (!this.rs.wasNull()) {
/* 132 */           am.setCantidadClientes(Integer.parseInt(this.rs.getString("cantidad_clientes")));
/*     */         }
/* 134 */         temp = this.rs.getString("servicios_solicitados");
/* 135 */         if (!this.rs.wasNull()) {
/* 136 */           am.setServiciosSolicitados(Integer.parseInt(this.rs.getString("servicios_solicitados")));
/*     */         }
/* 138 */         temp = this.rs.getString("servicios_tiempo");
/* 139 */         if (!this.rs.wasNull()) {
/* 140 */           am.setServiciosTiempo(Integer.parseInt(this.rs.getString("servicios_tiempo")));
/*     */         }
/* 142 */         temp = this.rs.getString("servicios_fuera_tiempo");
/* 143 */         if (!this.rs.wasNull()) {
/* 144 */           am.setServiciosFueraTiempo(Integer.parseInt(this.rs.getString("servicios_fuera_tiempo")));
/*     */         }
/* 146 */         temp = this.rs.getString("servicios_no_prestados");
/* 147 */         if (!this.rs.wasNull()) {
/* 148 */           am.setServiciosNoPrestados(Integer.parseInt(this.rs.getString("servicios_no_prestados")));
/*     */         }
/* 150 */         temp = this.rs.getString("confiabilidad_excelentes");
/* 151 */         if (!this.rs.wasNull()) {
/* 152 */           am.setConfiabilidadExcelentes(Integer.parseInt(this.rs.getString("confiabilidad_excelentes")));
/*     */         }
/* 154 */         temp = this.rs.getString("confiabilidad_buenas");
/* 155 */         if (!this.rs.wasNull()) {
/* 156 */           am.setConfiabilidadBuenas(Integer.parseInt(this.rs.getString("confiabilidad_buenas")));
/*     */         }
/* 158 */         temp = this.rs.getString("confiabilidad_regulares");
/* 159 */         if (!this.rs.wasNull()) {
/* 160 */           am.setConfiabilidadRegulares(Integer.parseInt(this.rs.getString("confiabilidad_regulares")));
/*     */         }
/* 162 */         temp = this.rs.getString("indice_satisfaccion");
/* 163 */         if (!this.rs.wasNull()) {
/* 164 */           am.setIndiceSatisfaccion(this.rs.getString("indice_satisfaccion"));
/*     */         }
/* 166 */         return am;
/*     */       } 
/* 168 */       return null;
/*     */     }
/* 170 */     catch (SQLException e) {
/* 171 */       e.printStackTrace();
/*     */       
/* 173 */       return null;
/*     */     } 
/*     */   }
/*     */   public AcumuladoMensualDTO next() {
/*     */     try {
/* 178 */       if (this.rs.next()) {
/* 179 */         AcumuladoMensualDTO am = new AcumuladoMensualDTO();
/* 180 */         String temp = this.rs.getString("ano");
/* 181 */         if (!this.rs.wasNull()) {
/* 182 */           am.setAnno(Integer.parseInt(temp));
/*     */         } else {
/*     */           
/* 185 */           return null;
/*     */         } 
/* 187 */         temp = this.rs.getString("mesi");
/* 188 */         if (!this.rs.wasNull()) {
/* 189 */           am.setMes(Integer.parseInt(temp));
/*     */         }
/*     */         
/* 192 */         temp = this.rs.getString("cai");
/* 193 */         if (!this.rs.wasNull()) {
/* 194 */           am.setCodigoArea(Integer.parseInt(temp));
/*     */         }
/*     */         
/* 197 */         am.setCodigoServicio(Integer.parseInt(this.rs.getString("csi")));
/* 198 */         am.setTiempoEstablecido(Integer.parseInt(this.rs.getString("tei")));
/* 199 */         temp = this.rs.getString("tpi");
/* 200 */         if (!this.rs.wasNull()) {
/* 201 */           am.setTiempoPromedio(Integer.parseInt(this.rs.getString("tpi")));
/*     */         }
/* 203 */         temp = this.rs.getString("en1");
/* 204 */         if (!this.rs.wasNull()) {
/* 205 */           am.setEscNivel1(Integer.parseInt(this.rs.getString("en1")));
/*     */         }
/* 207 */         temp = this.rs.getString("en2");
/* 208 */         if (!this.rs.wasNull()) {
/* 209 */           am.setEscNivel2(Integer.parseInt(this.rs.getString("en2")));
/*     */         }
/* 211 */         temp = this.rs.getString("en3");
/* 212 */         if (!this.rs.wasNull()) {
/* 213 */           am.setEscNivel3(Integer.parseInt(this.rs.getString("en3")));
/*     */         }
/* 215 */         temp = this.rs.getString("en4");
/* 216 */         if (!this.rs.wasNull()) {
/* 217 */           am.setEscNivel4(Integer.parseInt(this.rs.getString("en4")));
/*     */         }
/* 219 */         temp = this.rs.getString("en5");
/* 220 */         if (!this.rs.wasNull()) {
/* 221 */           am.setEscNivel5(Integer.parseInt(this.rs.getString("en5")));
/*     */         }
/* 223 */         temp = this.rs.getString("en6");
/* 224 */         if (!this.rs.wasNull()) {
/* 225 */           am.setEscNivel6(Integer.parseInt(this.rs.getString("en6")));
/*     */         }
/* 227 */         temp = this.rs.getString("cci");
/* 228 */         if (!this.rs.wasNull()) {
/* 229 */           am.setCantidadClientes(Integer.parseInt(this.rs.getString("cci")));
/*     */         }
/* 231 */         temp = this.rs.getString("ssi");
/* 232 */         if (!this.rs.wasNull()) {
/* 233 */           am.setServiciosSolicitados(Integer.parseInt(this.rs.getString("ssi")));
/*     */         }
/* 235 */         temp = this.rs.getString("sti");
/* 236 */         if (!this.rs.wasNull()) {
/* 237 */           am.setServiciosTiempo(Integer.parseInt(this.rs.getString("sti")));
/*     */         }
/* 239 */         temp = this.rs.getString("sfti");
/* 240 */         if (!this.rs.wasNull()) {
/* 241 */           am.setServiciosFueraTiempo(Integer.parseInt(this.rs.getString("sfti")));
/*     */         }
/* 243 */         temp = this.rs.getString("snpi");
/* 244 */         if (!this.rs.wasNull()) {
/* 245 */           am.setServiciosNoPrestados(Integer.parseInt(this.rs.getString("snpi")));
/*     */         }
/* 247 */         temp = this.rs.getString("cei");
/* 248 */         if (!this.rs.wasNull()) {
/* 249 */           am.setConfiabilidadExcelentes(Integer.parseInt(this.rs.getString("cei")));
/*     */         }
/* 251 */         temp = this.rs.getString("cbi");
/* 252 */         if (!this.rs.wasNull()) {
/* 253 */           am.setConfiabilidadBuenas(Integer.parseInt(this.rs.getString("cbi")));
/*     */         }
/* 255 */         temp = this.rs.getString("cri");
/* 256 */         if (!this.rs.wasNull()) {
/* 257 */           am.setConfiabilidadRegulares(Integer.parseInt(this.rs.getString("cri")));
/*     */         }
/* 259 */         return am;
/*     */       } 
/*     */       
/* 262 */       return null;
/*     */ 
/*     */     
/*     */     }
/* 266 */     catch (SQLException e) {
/* 267 */       e.printStackTrace();
/*     */       
/* 269 */       return null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\AcumuladoMensualDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */