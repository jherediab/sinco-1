/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import sinco.business.DiaFestivoDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.DiaFestivoDAO;
/*     */ 
/*     */ public class DiaFestivoDAO
/*     */ {
/*     */   ResultSet rs;
/*     */   
/*     */   public void close() {
/*     */     try {
/*  16 */       this.dat.close();
/*     */     }
/*  18 */     catch (Exception e) {}
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  23 */   DBManager dat = new DBManager();
/*     */ 
/*     */ 
/*     */   
/*     */   public DiaFestivoDTO leerRegistro() {
/*     */     try {
/*  29 */       DiaFestivoDTO reg = new DiaFestivoDTO();
/*  30 */       reg.setFecha(this.rs.getString("fecha"));
/*  31 */       return reg;
/*     */     }
/*  33 */     catch (Exception e) {
/*  34 */       e.printStackTrace();
/*  35 */       Utilidades.writeError("ServicioAreaFactory.leerRegistro ", e);
/*     */       
/*  37 */       return null;
/*     */     } 
/*     */   }
/*     */   public DiaFestivoDTO next() {
/*     */     try {
/*  42 */       if (!this.rs.next())
/*     */       {
/*     */         
/*  45 */         return null;
/*     */       
/*     */       }
/*     */     }
/*  49 */     catch (SQLException e) {
/*  50 */       e.printStackTrace();
/*     */     } 
/*  52 */     return null;
/*     */   }
/*     */   
/*     */   public int diasFestivosEntre(String vieja, String nueva) {
/*     */     try {
/*  57 */       String s = "SELECT COUNT(0) as resultado FROM dias_festivos WHERE dias_festivos.fecha>=" + Utilidades.formatoFecha2(vieja) + " AND  dias_festivos.fecha<=" + Utilidades.formatoFecha2(nueva);
/*  58 */       boolean rta = this.dat.parseSql(s);
/*  59 */       if (!rta) return 0; 
/*  60 */       this.rs = this.dat.getResultSet();
/*  61 */       if (this.rs.next()) {
/*  62 */         return this.rs.getInt("resultado");
/*     */       }
/*     */     }
/*  65 */     catch (SQLException e) {
/*  66 */       e.printStackTrace();
/*     */     } 
/*  68 */     return 0;
/*     */   }
/*     */   
/*     */   public DiaFestivoDTO getDiaFestivo(String diafes) {
/*     */     try {
/*  73 */       String s = "select * from dias_festivos where fecha=" + Utilidades.formatoFecha2(diafes);
/*  74 */       boolean rta = this.dat.parseSql(s);
/*  75 */       if (!rta) return null; 
/*  76 */       this.rs = this.dat.getResultSet();
/*  77 */       if (this.rs.next()) {
/*  78 */         return leerRegistro();
/*     */       }
/*     */     }
/*  81 */     catch (SQLException e) {
/*  82 */       e.printStackTrace();
/*     */     } 
/*  84 */     return null;
/*     */   }
/*     */   
/*     */   public boolean esDiaFestivo(String diafes) {
/*     */     try {
/*  89 */       String s = "select fecha from dias_festivos where fecha=" + Utilidades.formatoFecha2(diafes);
/*  90 */       boolean rta = this.dat.parseSql(s);
/*  91 */       if (!rta) return false; 
/*  92 */       this.rs = this.dat.getResultSet();
/*  93 */       if (this.rs.next()) {
/*  94 */         return true;
/*     */       }
/*     */     }
/*  97 */     catch (SQLException e) {
/*  98 */       e.printStackTrace();
/*     */     } 
/* 100 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\DiaFestivoDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */