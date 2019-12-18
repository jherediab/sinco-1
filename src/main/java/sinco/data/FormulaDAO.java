/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.FormulaDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.FormulaDAO;
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
/*     */ 
/*     */ 
/*     */ public class FormulaDAO
/*     */ {
/*     */   ResultSet rs;
/*  29 */   DBManager dat = new DBManager();
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
/*  40 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/*     */     try {
/*  47 */       this.dat.close();
/*     */     }
/*  49 */     catch (Exception e) {
/*  50 */       Utilidades.writeError("FormulaDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FormulaDTO next() {
/*     */     try {
/*  61 */       if (this.rs.next()) {
/*  62 */         return leerRegistro();
/*     */       }
/*     */     }
/*  65 */     catch (Exception e) {
/*  66 */       e.printStackTrace();
/*  67 */       Utilidades.writeError("FormulaDAO:next ", e);
/*     */     } 
/*  69 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FormulaDTO leerRegistro() {
/*     */     try {
/*  79 */       FormulaDTO reg = new FormulaDTO();
/*     */       
/*  81 */       reg.setIdFormula(this.rs.getInt("id_formula"));
/*  82 */       reg.setFormula(this.rs.getString("formula"));
/*  83 */       reg.setDescripcion(this.rs.getString("descripcion"));
/*  84 */       reg.setEstado(this.rs.getString("estado"));
/*  85 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  86 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  87 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  88 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  89 */       reg.setNombreEstado(this.rs.getString("nombre_estado"));
/*  90 */       return reg;
/*     */     }
/*  92 */     catch (Exception e) {
/*  93 */       e.printStackTrace();
/*  94 */       Utilidades.writeError("FormulaDAO:leerRegistro ", e);
/*     */       
/*  96 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<FormulaDTO> cargarTodos() {
/* 106 */     Collection<FormulaDTO> resultados = new ArrayList<FormulaDTO>();
/*     */     try {
/* 108 */       String s = "select t.id_formula,t.formula,t.descripcion,t.estado,m1.DESCRIPCION as nombre_estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from FORMULA t  left join sis_multivalores m1 on (m1.tabla='ESTADO_REGISTRO' and m1.VALOR=t.estado) where 1=1";
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
/* 121 */       s = s + " order by 1";
/* 122 */       boolean rtaDB = this.dat.parseSql(s);
/* 123 */       if (!rtaDB) {
/* 124 */         return resultados;
/*     */       }
/* 126 */       this.rs = this.dat.getResultSet();
/* 127 */       while (this.rs.next()) {
/* 128 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 131 */     catch (Exception e) {
/* 132 */       e.printStackTrace();
/* 133 */       Utilidades.writeError("FormulaDAO:cargarTodos ", e);
/*     */     } 
/* 135 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FormulaDTO cargarRegistro(int idFormula) {
/*     */     try {
/* 145 */       String s = "select t.id_formula,t.formula,t.descripcion,t.estado,m1.DESCRIPCION as nombre_estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from FORMULA t  left join sis_multivalores m1 on (m1.tabla='ESTADO_REGISTRO' and m1.VALOR=t.estado) where  t.id_formula=" + idFormula + "";
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
/*     */       
/* 160 */       boolean rtaDB = this.dat.parseSql(s);
/* 161 */       if (!rtaDB) {
/* 162 */         return null;
/*     */       }
/* 164 */       this.rs = this.dat.getResultSet();
/* 165 */       if (this.rs.next()) {
/* 166 */         return leerRegistro();
/*     */       }
/*     */     }
/* 169 */     catch (Exception e) {
/* 170 */       e.printStackTrace();
/* 171 */       Utilidades.writeError("FormulaDAO:cargarFormula", e);
/*     */     } 
/* 173 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarRegistro(int idFormula) {
/* 183 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 186 */       String s = "delete from FORMULA where  id_formula=" + idFormula + "";
/*     */ 
/*     */ 
/*     */       
/* 190 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 192 */     catch (Exception e) {
/* 193 */       e.printStackTrace();
/* 194 */       Utilidades.writeError("FormulaDAO:eliminarRegistro ", e);
/* 195 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 197 */     return rta;
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
/*     */   public RespuestaBD crearRegistro(int idFormula, String formula, String descripcion, String estado, String usuarioInsercion) {
/* 212 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 215 */       String s = "insert into FORMULA(id_formula,formula,descripcion,estado,fecha_insercion,usuario_insercion) values (" + idFormula + "," + "'" + formula + "'," + "'" + descripcion + "'," + "'" + estado + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
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
/*     */       
/* 230 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 232 */     catch (Exception e) {
/* 233 */       e.printStackTrace();
/* 234 */       Utilidades.writeError("%FormulaDAO:crearRegistro ", e);
/* 235 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 237 */     return rta;
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
/*     */   public RespuestaBD modificarRegistro(int idFormula, String formula, String descripcion, String estado, String usuarioModificacion) {
/* 252 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 255 */       String s = "update FORMULA set  formula='" + formula + "'," + " descripcion='" + descripcion + "'," + " estado='" + estado + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " id_formula=" + idFormula + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 264 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 266 */     catch (Exception e) {
/* 267 */       e.printStackTrace();
/* 268 */       Utilidades.writeError("FormulaDAO:modificarRegistro ", e);
/* 269 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 271 */     return rta;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\FormulaDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */