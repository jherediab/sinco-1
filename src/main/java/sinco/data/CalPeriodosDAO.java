/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import sinco.business.CalPeriodosDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.CalPeriodosDAO;
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
/*     */ public class CalPeriodosDAO
/*     */ {
/*     */   ResultSet rs;
/*  23 */   DBManager dat = new DBManager();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  32 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/*     */     try {
/*  39 */       this.dat.close();
/*     */     }
/*  41 */     catch (Exception e) {
/*  42 */       Utilidades.writeError("PeriodosDAO:close", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalPeriodosDTO next() {
/*     */     try {
/*  53 */       if (this.rs.next()) {
/*  54 */         return leerRegistro();
/*     */       }
/*     */     }
/*  57 */     catch (Exception e) {
/*  58 */       e.printStackTrace();
/*  59 */       Utilidades.writeError("PeriodosDAO:next ", e);
/*     */     } 
/*  61 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private CalPeriodosDTO leerRegistro() {
/*     */     try {
/*  71 */       CalPeriodosDTO reg = new CalPeriodosDTO();
/*  72 */       reg.setCiclo(this.rs.getInt("ciclo"));
/*  73 */       reg.setPeriodo(this.rs.getInt("periodo"));
/*  74 */       reg.setDescripcion(this.rs.getString("descripcion"));
/*  75 */       reg.setEstado(this.rs.getString("estado"));
/*  76 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  77 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  78 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  79 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  80 */       return reg;
/*     */     }
/*  82 */     catch (Exception e) {
/*  83 */       e.printStackTrace();
/*  84 */       Utilidades.writeError("PeriodosDAO:leerRegistro ", e);
/*     */       
/*  86 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean cargarTodos(int ciclo) {
/*     */     try {
/*  96 */       String s = "select * from cal_periodos where ciclo=" + ciclo + " order by periodo";
/*  97 */       boolean rtaDB = this.dat.parseSql(s);
/*  98 */       if (!rtaDB) {
/*  99 */         return false;
/*     */       }
/*     */       
/* 102 */       this.rs = this.dat.getResultSet();
/* 103 */       return true;
/*     */     }
/* 105 */     catch (Exception e) {
/* 106 */       e.printStackTrace();
/* 107 */       Utilidades.writeError("PeriodosDAO:cargarTodos ", e);
/*     */       
/* 109 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean cargarAbiertos(int ciclo, int mes) {
/*     */     try {
/* 120 */       String s = "select * from cal_periodos where ciclo=" + ciclo + " and periodo<=" + mes + " and estado='A' order by periodo";
/*     */ 
/*     */       
/* 123 */       boolean rtaDB = this.dat.parseSql(s);
/* 124 */       if (!rtaDB) {
/* 125 */         return false;
/*     */       }
/*     */       
/* 128 */       this.rs = this.dat.getResultSet();
/* 129 */       return true;
/*     */     }
/* 131 */     catch (Exception e) {
/* 132 */       e.printStackTrace();
/* 133 */       Utilidades.writeError("PeriodosDAO:cargarTodos ", e);
/*     */       
/* 135 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalPeriodosDTO cargarRegistro(int ciclo, int periodo) {
/*     */     try {
/* 145 */       String s = "select * from cal_periodos where ciclo=" + ciclo + " and periodo=" + periodo;
/* 146 */       boolean rtaDB = this.dat.parseSql(s);
/* 147 */       if (!rtaDB) {
/* 148 */         return null;
/*     */       }
/*     */       
/* 151 */       this.rs = this.dat.getResultSet();
/* 152 */       if (this.rs.next()) {
/* 153 */         return leerRegistro();
/*     */       }
/*     */     }
/* 156 */     catch (Exception e) {
/* 157 */       e.printStackTrace();
/* 158 */       Utilidades.writeError("PeriodosDAO:cargarPeriodos ", e);
/*     */     } 
/* 160 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean modificarRegistro(int ciclo, int periodo, String estado, String usuarioModificacion) {
/*     */     try {
/* 171 */       String s = "update cal_periodos set ";
/* 172 */       s = s + " estado='" + estado + "',";
/* 173 */       s = s + " fecha_modificacion=" + Utilidades.getFechaBD() + ",";
/* 174 */       s = s + " usuario_modificacion='" + usuarioModificacion + "'";
/* 175 */       s = s + " where ";
/* 176 */       s = s + " ciclo=" + ciclo;
/* 177 */       s = s + " and periodo=" + periodo;
/* 178 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 181 */     catch (Exception e) {
/* 182 */       e.printStackTrace();
/* 183 */       Utilidades.writeError("PeriodosDAO:modificarRegistro", e);
/*     */       
/* 185 */       return false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\CalPeriodosDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */