/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.LibreDAO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LibreDAO
/*     */ {
/*     */   ResultSet rs;
/*  16 */   DBManager dat = new DBManager();
/*     */ 
/*     */   
/*  19 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*     */   
/*     */   public void close() {
/*     */     try {
/*  23 */       this.dat.close();
/*     */     }
/*  25 */     catch (Exception e) {}
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean doSelect(String Tablas, String Campos, String Where) {
/*     */     try {
/*  31 */       boolean rta = this.dat.parseSql("select " + Campos + " from " + Tablas + " where " + Where);
/*  32 */       if (!rta) return false; 
/*  33 */       this.rs = this.dat.getResultSet();
/*  34 */       return true;
/*     */     }
/*  36 */     catch (Exception e) {
/*  37 */       e.printStackTrace();
/*  38 */       Utilidades.writeError("doselect ", e);
/*     */       
/*  40 */       return false;
/*     */     } 
/*     */   }
/*     */   public boolean executeUpdate(String sentencia) {
/*     */     try {
/*  45 */       return this.dat.executeUpdate(sentencia);
/*     */     }
/*  47 */     catch (Exception e) {
/*  48 */       e.printStackTrace();
/*     */       
/*  50 */       return false;
/*     */     } 
/*     */   }
/*     */   public boolean doExecute(String sentencia) {
/*     */     try {
/*  55 */       boolean rta = this.dat.parseSql(sentencia);
/*  56 */       if (!rta) return false; 
/*  57 */       this.rs = this.dat.getResultSet();
/*  58 */       return true;
/*     */     }
/*  60 */     catch (Exception e) {
/*  61 */       e.printStackTrace();
/*  62 */       Utilidades.writeError("doexecute ", e);
/*     */       
/*  64 */       return false;
/*     */     } 
/*     */   }
/*     */   public boolean next() {
/*     */     try {
/*  69 */       if (this.rs.next()) {
/*  70 */         return true;
/*     */       }
/*     */     }
/*  73 */     catch (Exception e) {
/*  74 */       e.printStackTrace();
/*  75 */       Utilidades.writeError("next ", e);
/*     */     } 
/*  77 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getString(int id) {
/*     */     try {
/*  83 */       String tmp = this.rs.getString(id);
/*  84 */       if (!this.rs.wasNull()) {
/*  85 */         return tmp;
/*     */       }
/*  87 */       return "";
/*     */     }
/*  89 */     catch (Exception e) {
/*  90 */       Utilidades.writeError("LibreFactory.getString: ", e);
/*     */ 
/*     */       
/*  93 */       return "";
/*     */     } 
/*     */   } public String getString(String id) {
/*     */     try {
/*  97 */       String tmp = this.rs.getString(id);
/*  98 */       if (!this.rs.wasNull()) {
/*  99 */         return tmp;
/*     */       }
/* 101 */       return "";
/*     */     }
/* 103 */     catch (Exception e) {
/* 104 */       Utilidades.writeError("LibreFactory.getString: ", e);
/*     */ 
/*     */       
/* 107 */       return "";
/*     */     } 
/*     */   }
/*     */   
/*     */   public double getDouble(String id) {
/*     */     try {
/* 113 */       double tmp = this.rs.getDouble(id);
/* 114 */       if (!this.rs.wasNull()) {
/* 115 */         return tmp;
/*     */       }
/* 117 */       return 0.0D;
/*     */     }
/* 119 */     catch (Exception e) {
/* 120 */       Utilidades.writeError("LibreFactory.getDouble: ", e);
/*     */ 
/*     */       
/* 123 */       return 0.0D;
/*     */     } 
/*     */   }
/*     */   public int getInt(String id) {
/*     */     try {
/* 128 */       int tmp = this.rs.getInt(id);
/* 129 */       if (!this.rs.wasNull()) {
/* 130 */         return tmp;
/*     */       }
/* 132 */       return 0;
/*     */     }
/* 134 */     catch (Exception e) {
/* 135 */       Utilidades.writeError("LibreFactory.getInt: ", e);
/*     */ 
/*     */       
/* 138 */       return 0;
/*     */     } 
/*     */   }
/*     */   public int getInt(int id) {
/*     */     try {
/* 143 */       int tmp = this.rs.getInt(id);
/* 144 */       if (!this.rs.wasNull()) {
/* 145 */         return tmp;
/*     */       }
/* 147 */       return 0;
/*     */     }
/* 149 */     catch (Exception e) {
/* 150 */       Utilidades.writeError("LibreFactory.getInt: ", e);
/*     */ 
/*     */       
/* 153 */       return 0;
/*     */     } 
/*     */   }
/*     */   
/*     */   public int maxivoValor(String tabla, String codigo, String condiciones) {
/* 158 */     int numero = 1;
/*     */     try {
/* 160 */       boolean rta = this.dat.parseSql("select MAX(" + codigo + ") from " + tabla + " where " + condiciones);
/* 161 */       if (!rta) return 1; 
/* 162 */       ResultSet rs = this.dat.getResultSet();
/* 163 */       if (rs.next()) {
/* 164 */         String temp = rs.getString(1);
/* 165 */         if (!rs.wasNull()) {
/* 166 */           numero = Integer.parseInt(temp) + 1;
/*     */         }
/*     */       }
/*     */     
/* 170 */     } catch (Exception e) {
/* 171 */       e.printStackTrace();
/*     */     } 
/* 173 */     return numero;
/*     */   }
/*     */ 
/*     */   
/*     */   public String buscarDescripcion(String cmd) {
/* 178 */     int numero = 1;
/*     */     try {
/* 180 */       boolean rta = this.dat.parseSql(cmd);
/* 181 */       if (!rta) return ""; 
/* 182 */       ResultSet rs = this.dat.getResultSet();
/* 183 */       if (rs.next()) {
/* 184 */         String temp = rs.getString(1);
/* 185 */         if (!rs.wasNull()) {
/* 186 */           return temp;
/*     */         }
/*     */       }
/*     */     
/* 190 */     } catch (Exception e) {
/* 191 */       e.printStackTrace();
/*     */     } 
/* 193 */     return "";
/*     */   }
/*     */   
/*     */   public int getValorInt(String s) {
/*     */     try {
/* 198 */       boolean rta = this.dat.parseSql(s);
/* 199 */       this.rs = this.dat.getResultSet();
/* 200 */       if (!rta) return 0; 
/* 201 */       if (this.rs.next()) {
/* 202 */         int numero = this.rs.getInt(1);
/* 203 */         this.rs.close();
/* 204 */         return numero;
/*     */       } 
/* 206 */       this.rs.close();
/*     */     }
/* 208 */     catch (Exception e) {
/* 209 */       e.printStackTrace();
/* 210 */       Utilidades.writeError("LibreFactory.getValorInt: ", e);
/*     */     } 
/* 212 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\LibreDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */