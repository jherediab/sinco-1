/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.ParametrosDTO;
/*     */ import sinco.business.TipoCalificacionDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.TipoCalificacionDAO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TipoCalificacionDAO
/*     */ {
/*     */   ResultSet rs;
/*  19 */   DBManager dat = new DBManager();
/*     */ 
/*     */ 
/*     */   
/*  23 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean cargarTodos(int numeroDevoluciones) {
/*     */     try {
/*  33 */       String s = "select * from tipos_calificacion where 1=1";
/*     */       
/*  35 */       if (numeroDevoluciones >= ParametrosDTO.getInt("maximo.numero.devoluciones")) {
/*  36 */         s = s + " and codigo not in ('0')";
/*     */       }
/*  38 */       s = s + " order by valor desc";
/*     */       
/*  40 */       boolean rta = this.dat.parseSql(s);
/*  41 */       if (!rta) return false; 
/*  42 */       this.rs = this.dat.getResultSet();
/*  43 */       return true;
/*     */     }
/*  45 */     catch (Exception e) {
/*  46 */       e.printStackTrace();
/*     */       
/*  48 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean cargarPorPorcentaje(int p) {
/*     */     try {
/*  59 */       String temp = "select * from tipos_calificacion  where codigo in ('E','B','R')  and rango_inicial<=" + p + " and rango_final>=" + p;
/*     */ 
/*     */ 
/*     */       
/*  63 */       boolean rta = this.dat.parseSql(temp);
/*  64 */       if (!rta) return false; 
/*  65 */       this.rs = this.dat.getResultSet();
/*  66 */       return true;
/*     */     }
/*  68 */     catch (Exception e) {
/*  69 */       e.printStackTrace();
/*     */       
/*  71 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean cargarPorPorcentajeIndice(int p) {
/*     */     try {
/*  81 */       String temp = "select * from tipos_calificacion  where codigo in ('E','B','R')   and rango_inicial_indice<=" + p + " and rango_final_indice>=" + p;
/*     */ 
/*     */ 
/*     */       
/*  85 */       boolean rta = this.dat.parseSql(temp);
/*  86 */       if (!rta) return false; 
/*  87 */       this.rs = this.dat.getResultSet();
/*  88 */       return true;
/*     */     }
/*  90 */     catch (Exception e) {
/*  91 */       e.printStackTrace();
/*     */       
/*  93 */       return false;
/*     */     } 
/*     */   }
/*     */   public void close() {
/*     */     try {
/*  98 */       this.dat.close();
/*     */     }
/* 100 */     catch (Exception e) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TipoCalificacionDTO leerRegistro() {
/*     */     try {
/* 111 */       TipoCalificacionDTO tc = new TipoCalificacionDTO();
/* 112 */       tc.setCodigo(this.rs.getString("codigo").charAt(0));
/* 113 */       tc.setDescripcion(this.rs.getString("descripcion"));
/* 114 */       tc.setValor(this.rs.getInt("valor"));
/* 115 */       tc.setRangoInicial(this.rs.getInt("rango_inicial"));
/* 116 */       tc.setRangoFinal(this.rs.getInt("rango_final"));
/* 117 */       if (this.rs.getString("justifica").toLowerCase().equals("s")) {
/* 118 */         tc.setJustifica(true);
/*     */       } else {
/*     */         
/* 121 */         tc.setJustifica(false);
/*     */       } 
/* 123 */       tc.setPorcentaje(this.rs.getInt("porcentaje"));
/* 124 */       tc.setSatisfaccion(this.rs.getString("indice_satisfaccion"));
/* 125 */       tc.setRangoInicialIndice(this.rs.getInt("rango_inicial_indice"));
/* 126 */       tc.setRangoFinalIndice(this.rs.getInt("rango_final_indice"));
/* 127 */       return tc;
/*     */     }
/* 129 */     catch (SQLException e) {
/* 130 */       e.printStackTrace();
/*     */       
/* 132 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TipoCalificacionDTO next() {
/*     */     try {
/* 142 */       if (this.rs.next()) {
/* 143 */         return leerRegistro();
/*     */       }
/*     */     }
/* 146 */     catch (SQLException e) {
/* 147 */       e.printStackTrace();
/*     */     } 
/* 149 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TipoCalificacionDTO getTipoCalificacion(char cod) {
/*     */     try {
/* 159 */       boolean rta = this.dat.parseSql("select * from tipos_calificacion where codigo='" + cod + "'");
/* 160 */       if (!rta) return null; 
/* 161 */       this.rs = this.dat.getResultSet();
/* 162 */       if (this.rs.next()) {
/* 163 */         return leerRegistro();
/*     */       }
/*     */     }
/* 166 */     catch (SQLException e) {
/* 167 */       e.printStackTrace();
/*     */     } 
/* 169 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<TipoCalificacionDTO> cargarEstados() {
/* 177 */     Collection<TipoCalificacionDTO> resultados = new ArrayList<TipoCalificacionDTO>();
/*     */     try {
/* 179 */       String s = "select * from tipos_calificacion where codigo not in ('0') order by valor desc";
/*     */       
/* 181 */       boolean rtaDB = this.dat.parseSql(s);
/* 182 */       if (!rtaDB) {
/* 183 */         return resultados;
/*     */       }
/* 185 */       this.rs = this.dat.getResultSet();
/* 186 */       while (this.rs.next()) {
/* 187 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 190 */     catch (Exception e) {
/* 191 */       e.printStackTrace();
/* 192 */       Utilidades.writeError("TipoCalificacionFactory:cargarTodos ", e);
/*     */     } 
/* 194 */     return resultados;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\TipoCalificacionDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */