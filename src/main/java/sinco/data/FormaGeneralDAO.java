/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import sinco.business.FormaGeneralDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.FormaGeneralDAO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FormaGeneralDAO
/*     */ {
/*     */   ResultSet rs;
/*     */   DBManager dat;
/*     */   String nombreTabla;
/*     */   String campo1;
/*     */   String campo2;
/*     */   int numerico;
/*     */   
/*     */   public FormaGeneralDAO(String t, String c1, String c2, int n) {
/*  24 */     this.numerico = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  30 */     this.dat = new DBManager();
/*  31 */     this.nombreTabla = t;
/*  32 */     this.campo1 = c1;
/*  33 */     this.campo2 = c2;
/*  34 */     this.numerico = n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  41 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/*     */     try {
/*  49 */       this.dat.close();
/*     */     }
/*  51 */     catch (Exception e) {
/*  52 */       Utilidades.writeError("close", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FormaGeneralDTO next() {
/*     */     try {
/*  62 */       if (this.rs.next()) {
/*  63 */         return leerRegistro();
/*     */       }
/*     */     }
/*  66 */     catch (Exception e) {
/*  67 */       e.printStackTrace();
/*  68 */       Utilidades.writeError("next ", e);
/*     */     } 
/*  70 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FormaGeneralDTO leerRegistro() {
/*     */     try {
/*  78 */       FormaGeneralDTO reg = new FormaGeneralDTO();
/*  79 */       reg.setCodigo(this.rs.getString(this.campo1));
/*  80 */       reg.setNombre(this.rs.getString(this.campo2));
/*  81 */       reg.setEstado(this.rs.getString("estado"));
/*  82 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  83 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  84 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  85 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  86 */       return reg;
/*     */     }
/*  88 */     catch (Exception e) {
/*  89 */       e.printStackTrace();
/*  90 */       Utilidades.writeError("leerRegistro ", e);
/*     */       
/*  92 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean cargarTodos(String filtro) {
/*     */     try {
/* 100 */       String s = "select * From " + this.nombreTabla + " where upper(" + this.campo2 + ") like upper('" + filtro + "%') order by " + this.campo1;
/* 101 */       boolean rtaDB = this.dat.parseSql(s);
/* 102 */       this.rs = this.dat.getResultSet();
/* 103 */       return rtaDB;
/*     */     }
/* 105 */     catch (Exception e) {
/* 106 */       e.printStackTrace();
/* 107 */       Utilidades.writeError("cargarTodos ", e);
/*     */       
/* 109 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public FormaGeneralDTO cargarFormaGeneral(String codigo) {
/*     */     try {
/* 117 */       String s = "select * From " + this.nombreTabla + " where " + this.campo1 + "=" + num() + codigo + num();
/* 118 */       boolean rtaDB = this.dat.parseSql(s);
/* 119 */       this.rs = this.dat.getResultSet();
/* 120 */       if (this.rs.next()) {
/* 121 */         return leerRegistro();
/*     */       }
/*     */     }
/* 124 */     catch (Exception e) {
/* 125 */       e.printStackTrace();
/* 126 */       Utilidades.writeError("cargarFormaGeneral ", e);
/*     */     } 
/* 128 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int maximoValor() {
/*     */     try {
/* 136 */       String s = "select max(" + this.campo1 + ") as numero From " + this.nombreTabla;
/* 137 */       boolean rtaDB = this.dat.parseSql(s);
/* 138 */       this.rs = this.dat.getResultSet();
/* 139 */       if (this.rs.next()) {
/* 140 */         return this.rs.getInt("numero") + 1;
/*     */       
/*     */       }
/*     */     }
/* 144 */     catch (Exception e) {
/* 145 */       e.printStackTrace();
/* 146 */       Utilidades.writeError("FormaGeneralFactory.maximoValor ", e);
/*     */     } 
/* 148 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eliminarRegistro(String codigo) {
/*     */     try {
/* 156 */       String s = "delete from " + this.nombreTabla + " where " + this.campo1 + "=" + num() + codigo + num();
/* 157 */       return this.dat.executeUpdate(s);
/*     */     }
/* 159 */     catch (Exception e) {
/* 160 */       e.printStackTrace();
/* 161 */       Utilidades.writeError("eliminarRegistro", e);
/*     */       
/* 163 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean crearRegistro(String codigo, String nombre, String estado, String usuarioInsercion) {
/*     */     try {
/* 171 */       String s = "insert into " + this.nombreTabla + " ( " + this.campo1 + "," + this.campo2 + ",estado,fecha_insercion,usuario_insercion)";
/* 172 */       s = s + " values (";
/* 173 */       s = s + "" + num() + codigo + num() + ",";
/* 174 */       s = s + "'" + nombre + "',";
/* 175 */       s = s + "'" + estado + "',";
/* 176 */       s = s + "" + Utilidades.getFechaBD() + ",";
/* 177 */       s = s + "'" + usuarioInsercion + "'";
/* 178 */       s = s + ")";
/* 179 */       return this.dat.executeUpdate(s);
/*     */     }
/* 181 */     catch (Exception e) {
/* 182 */       e.printStackTrace();
/* 183 */       Utilidades.writeError("CrearRegistro", e);
/*     */       
/* 185 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean modificarRegistro(String codigo, String nombre, String estado, String usuarioModificacion) {
/*     */     try {
/* 193 */       String s = "update " + this.nombreTabla + " set ";
/* 194 */       s = s + this.campo2 + "='" + nombre + "',";
/* 195 */       s = s + "estado='" + estado + "',";
/* 196 */       s = s + "fecha_modificacion=" + Utilidades.getFechaBD() + ",";
/* 197 */       s = s + "usuario_modificacion='" + usuarioModificacion + "'";
/* 198 */       s = s + " where " + this.campo1 + "=" + num() + codigo + num();
/* 199 */       return this.dat.executeUpdate(s);
/*     */     }
/* 201 */     catch (Exception e) {
/* 202 */       e.printStackTrace();
/* 203 */       Utilidades.writeError("modificarRegistro", e);
/*     */       
/* 205 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private String num() {
/* 212 */     if (this.numerico == 0) {
/* 213 */       return "'";
/*     */     }
/* 215 */     return "";
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\FormaGeneralDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */