/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import sinco.business.CalGenericaDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.CalGenericaDAO;
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
/*     */ public class CalGenericaDAO
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
/*  42 */       Utilidades.writeError("CalGenericaFactory:close", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalGenericaDTO next() {
/*     */     try {
/*  53 */       if (this.rs.next()) {
/*  54 */         return leerRegistro();
/*     */       }
/*     */     }
/*  57 */     catch (Exception e) {
/*  58 */       e.printStackTrace();
/*  59 */       Utilidades.writeError("CalGenericaFactory:next ", e);
/*     */     } 
/*  61 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalGenericaDTO leerRegistro() {
/*     */     try {
/*  71 */       CalGenericaDTO reg = new CalGenericaDTO();
/*  72 */       reg.setCodigo(this.rs.getInt("codigo"));
/*  73 */       reg.setDescripcion(this.rs.getString("descripcion"));
/*  74 */       reg.setEstado(this.rs.getString("estado"));
/*  75 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  76 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  77 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  78 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  79 */       return reg;
/*     */     }
/*  81 */     catch (Exception e) {
/*  82 */       e.printStackTrace();
/*  83 */       Utilidades.writeError("CalGenericaFactory:leerRegistro ", e);
/*     */       
/*  85 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean cargarPlantillas() {
/*     */     try {
/*  95 */       String s = "select * from cal_plantillas order by descripcion";
/*  96 */       boolean rtaDB = this.dat.parseSql(s);
/*  97 */       if (!rtaDB) {
/*  98 */         return false;
/*     */       }
/*     */       
/* 101 */       this.rs = this.dat.getResultSet();
/* 102 */       return true;
/*     */     }
/* 104 */     catch (Exception e) {
/* 105 */       e.printStackTrace();
/* 106 */       Utilidades.writeError("CalGenericaFactory:cargarTodos ", e);
/*     */       
/* 108 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalGenericaDTO cargarPlantilla(int codigo) {
/*     */     try {
/* 118 */       String s = "select * from cal_plantillas where codigo=" + codigo;
/* 119 */       boolean rtaDB = this.dat.parseSql(s);
/* 120 */       if (!rtaDB) {
/* 121 */         return null;
/*     */       }
/*     */       
/* 124 */       this.rs = this.dat.getResultSet();
/* 125 */       if (this.rs.next()) {
/* 126 */         return leerRegistro();
/*     */       }
/*     */     }
/* 129 */     catch (Exception e) {
/* 130 */       e.printStackTrace();
/* 131 */       Utilidades.writeError("CalGenericaFactory:cargarCalGenerica ", e);
/*     */     } 
/* 133 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eliminarRegistro(int codigo) {
/*     */     try {
/* 144 */       String s = "delete from  cal_plantillas where codigo=" + codigo;
/* 145 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 148 */     catch (Exception e) {
/* 149 */       e.printStackTrace();
/* 150 */       Utilidades.writeError("CalGenericaFactory:eliminarRegistro", e);
/*     */       
/* 152 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean crearRegistro(int codigo, String descripcion, String estado, String usuarioInsercion) {
/*     */     try {
/* 162 */       String s = "insert into cal_plantillas (codigo,descripcion,estado,fecha_insercion,usuario_insercion)";
/* 163 */       s = s + " values (";
/* 164 */       s = s + "" + codigo + ",";
/* 165 */       s = s + "'" + descripcion + "',";
/* 166 */       s = s + "'" + estado + "',";
/* 167 */       s = s + "" + Utilidades.getFechaBD() + ",";
/* 168 */       s = s + "'" + usuarioInsercion + "'";
/* 169 */       s = s + ")";
/* 170 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 173 */     catch (Exception e) {
/* 174 */       e.printStackTrace();
/* 175 */       Utilidades.writeError("CalGenericaFactory:crearRegistro", e);
/*     */       
/* 177 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean modificarRegistro(int codigo, String descripcion, String estado, String usuarioModificacion) {
/*     */     try {
/* 187 */       String s = "update cal_plantillas set ";
/* 188 */       s = s + " descripcion='" + descripcion + "',";
/* 189 */       s = s + " estado='" + estado + "',";
/* 190 */       s = s + " fecha_modificacion=" + Utilidades.getFechaBD() + ",";
/* 191 */       s = s + " usuario_modificacion='" + usuarioModificacion + "',";
/* 192 */       s = s + " where ";
/* 193 */       s = s + " codigo=" + codigo;
/* 194 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 197 */     catch (Exception e) {
/* 198 */       e.printStackTrace();
/* 199 */       Utilidades.writeError("CalGenericaFactory:modificarRegistro", e);
/*     */       
/* 201 */       return false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\CalGenericaDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */