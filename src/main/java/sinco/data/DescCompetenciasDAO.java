/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import sinco.business.DescCompetenciasDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.DescCompetenciasDAO;
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
/*     */ public class DescCompetenciasDAO
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
/*  42 */       Utilidades.writeError("DescCompetenciasDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DescCompetenciasDTO next() {
/*     */     try {
/*  53 */       if (this.rs.next()) {
/*  54 */         return leerRegistro();
/*     */       }
/*     */     }
/*  57 */     catch (Exception e) {
/*  58 */       e.printStackTrace();
/*  59 */       Utilidades.writeError("DescCompetenciasDAO:next ", e);
/*     */     } 
/*  61 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DescCompetenciasDTO leerRegistro() {
/*     */     try {
/*  71 */       DescCompetenciasDTO reg = new DescCompetenciasDTO();
/*  72 */       reg.setValor(this.rs.getDouble("valor"));
/*  73 */       reg.setDescripcion(this.rs.getString("descripcion"));
/*  74 */       reg.setEstado(this.rs.getString("estado"));
/*  75 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  76 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  77 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  78 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  79 */       return reg;
/*     */     }
/*  81 */     catch (Exception e) {
/*  82 */       e.printStackTrace();
/*  83 */       Utilidades.writeError("DescCompetenciasDAO:leerRegistro ", e);
/*     */       
/*  85 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean cargarTodos() {
/*     */     try {
/*  95 */       String s = "select * from aud_descripcion_competencia order by 1";
/*  96 */       boolean rtaDB = this.dat.parseSql(s);
/*  97 */       if (!rtaDB) {
/*  98 */         return false;
/*     */       }
/* 100 */       this.rs = this.dat.getResultSet();
/* 101 */       return true;
/*     */     }
/* 103 */     catch (Exception e) {
/* 104 */       e.printStackTrace();
/* 105 */       Utilidades.writeError("DescCompetenciasDAO:cargarTodos ", e);
/*     */       
/* 107 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DescCompetenciasDTO cargarRegistro(double valor) {
/*     */     try {
/* 117 */       String s = "select * from aud_descripcion_competencia where valor=" + valor;
/* 118 */       boolean rtaDB = this.dat.parseSql(s);
/* 119 */       if (!rtaDB) {
/* 120 */         return null;
/*     */       }
/*     */       
/* 123 */       this.rs = this.dat.getResultSet();
/* 124 */       if (this.rs.next()) {
/* 125 */         return leerRegistro();
/*     */       }
/*     */     }
/* 128 */     catch (Exception e) {
/* 129 */       e.printStackTrace();
/* 130 */       Utilidades.writeError("DescCompetenciasDAO:cargarRegistro ", e);
/*     */     } 
/* 132 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DescCompetenciasDTO cargarSuperior(double valor) {
/*     */     try {
/* 142 */       String s = "select * from aud_descripcion_competencia where valor= (select max(valor) from aud_descripcion_competencia where valor<=" + valor + ")";
/*     */       
/* 144 */       boolean rtaDB = this.dat.parseSql(s);
/* 145 */       if (!rtaDB) {
/* 146 */         return null;
/*     */       }
/*     */       
/* 149 */       if (!rtaDB) {
/* 150 */         return null;
/*     */       }
/* 152 */       this.rs = this.dat.getResultSet();
/* 153 */       if (this.rs.next()) {
/* 154 */         return leerRegistro();
/*     */       }
/*     */     }
/* 157 */     catch (Exception e) {
/* 158 */       e.printStackTrace();
/* 159 */       Utilidades.writeError("DescCompetenciasDAO:cargarSuperior  ", e);
/*     */     } 
/* 161 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eliminarRegistro(double valor) {
/*     */     try {
/* 173 */       String s = "delete from  aud_descripcion_competencia where valor=" + valor;
/* 174 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 177 */     catch (Exception e) {
/* 178 */       e.printStackTrace();
/* 179 */       Utilidades.writeError("DescCompetenciasDAO:eliminarRegistro ", e);
/*     */       
/* 181 */       return false;
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
/*     */   public boolean crearRegistro(double valor, String descripcion, String estado, String usuarioInsercion, String fechaInsercion, String usuarioModificacion, String fechaModificacion) {
/*     */     try {
/* 196 */       String s = "insert into aud_descripcion_competencia ( valor,descripcion,estado,usuario_insercion,fecha_insercion,usuario_modificacion,fecha_modificacion )";
/* 197 */       s = s + " values (";
/* 198 */       s = s + "" + valor + ",";
/* 199 */       s = s + "'" + descripcion + "',";
/* 200 */       s = s + "'" + estado + "',";
/* 201 */       s = s + "'" + usuarioInsercion + "',";
/* 202 */       s = s + "" + Utilidades.formatoFecha(fechaInsercion) + ",";
/* 203 */       s = s + "'" + usuarioModificacion + "',";
/* 204 */       s = s + "" + Utilidades.formatoFecha(fechaModificacion) + "";
/* 205 */       s = s + ")";
/* 206 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 209 */     catch (Exception e) {
/* 210 */       e.printStackTrace();
/* 211 */       Utilidades.writeError("DescCompetenciasDAO:crearRegistro ", e);
/*     */       
/* 213 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean modificarRegistro(double valor, String descripcion, String estado, String usuarioInsercion, String fechaInsercion, String usuarioModificacion, String fechaModificacion) {
/*     */     try {
/* 223 */       String s = "update aud_descripcion_competencia set ";
/* 224 */       s = s + " descripcion='" + descripcion + "',";
/* 225 */       s = s + " estado='" + estado + "',";
/* 226 */       s = s + " usuario_insercion='" + usuarioInsercion + "',";
/* 227 */       s = s + " fecha_insercion=" + Utilidades.formatoFecha(fechaInsercion) + ",";
/* 228 */       s = s + " usuario_modificacion='" + usuarioModificacion + "',";
/* 229 */       s = s + " fecha_modificacion=" + Utilidades.formatoFecha(fechaModificacion) + "";
/* 230 */       s = s + " where ";
/* 231 */       s = s + " valor=" + valor;
/* 232 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 235 */     catch (Exception e) {
/* 236 */       e.printStackTrace();
/* 237 */       Utilidades.writeError("DescCompetenciasDAO:modificarRegistro ", e);
/*     */       
/* 239 */       return false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\DescCompetenciasDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */