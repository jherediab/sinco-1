/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.SisConsecutivosDAO;
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
/*     */ public class SisConsecutivosDAO
/*     */ {
/*     */   ResultSet rs;
/*  23 */   DBManager dat = new DBManager();
/*     */ 
/*     */ 
/*     */   
/*  27 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/*     */     try {
/*  33 */       this.dat.close();
/*     */     }
/*  35 */     catch (Exception e) {}
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
/*     */   public int siguienteRegistro() {
/*  47 */     int max = 1;
/*  48 */     String s = "select COALESCE(max(numero_radicado),0)+1 from rips_cargues";
/*     */     try {
/*  50 */       boolean rta = this.dat.parseSql(s);
/*  51 */       if (!rta) return 0; 
/*  52 */       this.rs = this.dat.getResultSet();
/*  53 */       if (this.rs.next()) {
/*  54 */         s = this.rs.getString(1);
/*  55 */         if (!this.rs.wasNull()) {
/*  56 */           max = Integer.parseInt(s);
/*     */         }
/*     */       } 
/*  59 */       return max;
/*     */     }
/*  61 */     catch (Exception e) {
/*  62 */       e.printStackTrace();
/*  63 */       Utilidades.writeError("SisConsecutivosDAO:siguienteRegistro " + e.getMessage());
/*     */       
/*  65 */       return 1;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int crearRegistro(String ips, String inicioPeriodo, String finPeriodo, String usuarioInsercion) {
/*  76 */     int numeroRadicacion = siguienteRegistro();
/*     */     try {
/*  78 */       String s = "insert into rips_cargues (numero_radicado,ips,inicio_periodo,fin_periodo,usuario_insercion,fecha_insercion)";
/*  79 */       s = s + " values (";
/*  80 */       s = s + "" + numeroRadicacion + ",";
/*  81 */       s = s + "'" + ips + "',";
/*  82 */       s = s + "" + Utilidades.formatoFecha(inicioPeriodo) + ",";
/*  83 */       s = s + "" + Utilidades.formatoFecha(finPeriodo) + ",";
/*  84 */       s = s + "'" + usuarioInsercion + "',";
/*  85 */       s = s + "" + Utilidades.getFechaBD() + "";
/*  86 */       s = s + ")";
/*  87 */       boolean rta = this.dat.executeUpdate(s);
/*  88 */       if (rta) {
/*  89 */         return numeroRadicacion;
/*     */       }
/*     */     }
/*  92 */     catch (Exception e) {
/*  93 */       e.printStackTrace();
/*  94 */       Utilidades.writeError("SisConsecutivosDAO:crearRegistro " + e.getMessage());
/*     */     } 
/*  96 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int siguienteConsecutivo(String codigoConsecutivo) {
/* 101 */     int max = 1;
/* 102 */     String s = "select COALESCE(max(valor_consecutivo),0)+1 from sis_consecutivos where codigo_consecutivo='" + codigoConsecutivo + "'";
/*     */     try {
/* 104 */       boolean rta = this.dat.parseSql(s);
/* 105 */       if (!rta) return 0; 
/* 106 */       this.rs = this.dat.getResultSet();
/* 107 */       if (this.rs.next()) {
/* 108 */         s = this.rs.getString(1);
/* 109 */         if (!this.rs.wasNull()) {
/* 110 */           max = Integer.parseInt(s);
/*     */         }
/*     */       } 
/* 113 */       return max;
/*     */     }
/* 115 */     catch (Exception e) {
/* 116 */       e.printStackTrace();
/* 117 */       Utilidades.writeError("SisConsecutivosDAO:siguienteRegistro " + e.getMessage());
/*     */       
/* 119 */       return 1;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int crearConsecutivo(String codigoConsecutivo, String usuarioInsercion) {
/* 128 */     int numeroRadicacion = siguienteConsecutivo(codigoConsecutivo);
/*     */     try {
/* 130 */       String s = "";
/* 131 */       if (numeroRadicacion == 1) {
/* 132 */         s = "insert into sis_consecutivos (codigo_consecutivo,valor_consecutivo,usuario_insercion,fecha_insercion)";
/* 133 */         s = s + " values (";
/* 134 */         s = s + "'" + codigoConsecutivo + "',";
/* 135 */         s = s + "" + numeroRadicacion + ",";
/* 136 */         s = s + "'" + usuarioInsercion + "',";
/* 137 */         s = s + "" + Utilidades.getFechaBD() + "";
/* 138 */         s = s + ")";
/*     */       } else {
/*     */         
/* 141 */         s = "update sis_consecutivos set  valor_consecutivo=" + numeroRadicacion + ",";
/* 142 */         s = s + "fecha_modificacion=" + Utilidades.getFechaBD() + ",";
/* 143 */         s = s + "usuario_modificacion='" + usuarioInsercion + "'";
/* 144 */         s = s + " where  codigo_consecutivo='" + codigoConsecutivo + "'";
/*     */       } 
/* 146 */       boolean rta = this.dat.executeUpdate(s);
/* 147 */       if (rta) {
/* 148 */         return numeroRadicacion;
/*     */       }
/*     */     }
/* 151 */     catch (Exception e) {
/* 152 */       e.printStackTrace();
/* 153 */       Utilidades.writeError("SisConsecutivosDAO:crearRegistro " + e.getMessage());
/*     */     } 
/* 155 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int consecutivoValidacionRips() {
/* 164 */     int max = 1;
/*     */     
/* 166 */     String s = "select nextval('rips_validar_seq') registro";
/*     */     try {
/* 168 */       boolean rta = this.dat.parseSql(s);
/* 169 */       if (!rta) return 0; 
/* 170 */       this.rs = this.dat.getResultSet();
/* 171 */       if (this.rs.next()) {
/* 172 */         max = this.rs.getInt("registro");
/*     */       }
/* 174 */       return max;
/*     */     }
/* 176 */     catch (Exception e) {
/* 177 */       e.printStackTrace();
/* 178 */       Utilidades.writeError("SisConsecutivosDAO:siguienteRegistro " + e.getMessage());
/*     */       
/* 180 */       return 1;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\SisConsecutivosDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */