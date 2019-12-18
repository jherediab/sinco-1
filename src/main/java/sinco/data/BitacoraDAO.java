/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.BitacoraDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.BitacoraDAO;
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
/*     */ public class BitacoraDAO
/*     */ {
/*     */   ResultSet rs;
/*  25 */   DBManager dat = new DBManager();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  34 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/*     */     try {
/*  41 */       this.dat.close();
/*     */     }
/*  43 */     catch (Exception e) {
/*  44 */       Utilidades.writeError("BitacoraFactory:close", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BitacoraDTO next() {
/*     */     try {
/*  55 */       if (this.rs.next()) {
/*  56 */         return leerRegistro();
/*     */       }
/*     */     }
/*  59 */     catch (Exception e) {
/*  60 */       e.printStackTrace();
/*  61 */       Utilidades.writeError("BitacoraFactory:next ", e);
/*     */     } 
/*  63 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BitacoraDTO leerRegistro() {
/*     */     try {
/*  73 */       BitacoraDTO reg = new BitacoraDTO();
/*  74 */       reg.setConsecutivo(this.rs.getInt("consecutivo"));
/*  75 */       reg.setTabla(this.rs.getString("tabla"));
/*  76 */       reg.setLlave1(this.rs.getString("llave1"));
/*  77 */       reg.setLlave2(this.rs.getString("llave2"));
/*  78 */       reg.setLlave3(this.rs.getString("llave3"));
/*  79 */       reg.setLlave4(this.rs.getString("llave4"));
/*  80 */       reg.setLlave5(this.rs.getString("llave5"));
/*  81 */       reg.setAccion(this.rs.getString("accion"));
/*  82 */       reg.setCampo(this.rs.getString("campo"));
/*  83 */       reg.setValInicial(this.rs.getString("val_inicial"));
/*  84 */       reg.setValFinal(this.rs.getString("val_final"));
/*  85 */       reg.setUsuario(this.rs.getString("usuario"));
/*  86 */       reg.setFecha(this.rs.getString("fecha"));
/*  87 */       reg.setObservaciones(this.rs.getString("observaciones"));
/*  88 */       return reg;
/*     */     }
/*  90 */     catch (Exception e) {
/*  91 */       e.printStackTrace();
/*  92 */       Utilidades.writeError("BitacoraFactory:leerRegistro ", e);
/*     */       
/*  94 */       return null;
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
/*     */   public Collection cargarBitacora(String tabla, String llave1, String llave2, String llave3, String llave4, String llave5) {
/* 110 */     Collection resultados = new ArrayList();
/*     */     try {
/* 112 */       String s = "select * from sis_bitacora  where tabla='" + tabla + "'";
/*     */       
/* 114 */       if (llave1.length() > 0) {
/* 115 */         s = s + " and llave1='" + llave1 + "'";
/*     */       }
/* 117 */       if (llave2.length() > 0) {
/* 118 */         s = s + " and llave2='" + llave2 + "'";
/*     */       }
/* 120 */       if (llave3.length() > 0) {
/* 121 */         s = s + " and llave3='" + llave3 + "'";
/*     */       }
/* 123 */       if (llave4.length() > 0) {
/* 124 */         s = s + " and llave4='" + llave4 + "'";
/*     */       }
/* 126 */       if (llave5.length() > 0) {
/* 127 */         s = s + " and llave5='" + llave5 + "'";
/*     */       }
/* 129 */       s = s + " order by consecutivo";
/* 130 */       boolean rtaDB = this.dat.parseSql(s);
/* 131 */       if (!rtaDB) {
/* 132 */         return resultados;
/*     */       }
/* 134 */       this.rs = this.dat.getResultSet();
/* 135 */       while (this.rs.next()) {
/* 136 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 139 */     catch (Exception e) {
/* 140 */       e.printStackTrace();
/* 141 */       Utilidades.writeError("BitacoraDAO:cargarTodos ", e);
/*     */     } 
/* 143 */     return resultados;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\BitacoraDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */