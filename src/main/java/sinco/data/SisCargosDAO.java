/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.SisCargosDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.SisCargosDAO;
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
/*     */ public class SisCargosDAO
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
/*  50 */       Utilidades.writeError("SisCargosDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SisCargosDTO next() {
/*     */     try {
/*  61 */       if (this.rs.next()) {
/*  62 */         return leerRegistro();
/*     */       }
/*     */     }
/*  65 */     catch (Exception e) {
/*  66 */       e.printStackTrace();
/*  67 */       Utilidades.writeError("SisCargosDAO:next ", e);
/*     */     } 
/*  69 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SisCargosDTO leerRegistro() {
/*     */     try {
/*  79 */       SisCargosDTO reg = new SisCargosDTO();
/*     */       
/*  81 */       reg.setCodigo(this.rs.getString("codigo"));
/*  82 */       reg.setDescripcion(this.rs.getString("descripcion"));
/*  83 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  84 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  85 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  86 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  87 */       return reg;
/*     */     }
/*  89 */     catch (Exception e) {
/*  90 */       e.printStackTrace();
/*  91 */       Utilidades.writeError("SisCargosDAO:leerRegistro ", e);
/*     */       
/*  93 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<SisCargosDTO> cargarTodos() {
/* 103 */     Collection<SisCargosDTO> resultados = new ArrayList<SisCargosDTO>();
/*     */     try {
/* 105 */       String s = "select t.codigo,t.descripcion,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from sis_cargos t  where 1=1";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 114 */       s = s + " order by 1";
/* 115 */       boolean rtaDB = this.dat.parseSql(s);
/* 116 */       if (!rtaDB) {
/* 117 */         return resultados;
/*     */       }
/* 119 */       this.rs = this.dat.getResultSet();
/* 120 */       while (this.rs.next()) {
/* 121 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 124 */     catch (Exception e) {
/* 125 */       e.printStackTrace();
/* 126 */       Utilidades.writeError("SisCargosDAO:cargarTodos ", e);
/*     */     } 
/* 128 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SisCargosDTO cargarRegistro(String codigo) {
/*     */     try {
/* 138 */       String s = "select t.codigo,t.descripcion,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from sis_cargos t  where  t.codigo='" + codigo + "'" + "";
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
/* 149 */       boolean rtaDB = this.dat.parseSql(s);
/* 150 */       if (!rtaDB) {
/* 151 */         return null;
/*     */       }
/* 153 */       this.rs = this.dat.getResultSet();
/* 154 */       if (this.rs.next()) {
/* 155 */         return leerRegistro();
/*     */       }
/*     */     }
/* 158 */     catch (Exception e) {
/* 159 */       e.printStackTrace();
/* 160 */       Utilidades.writeError("SisCargosDAO:cargarSisCargos", e);
/*     */     } 
/* 162 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarRegistro(String codigo) {
/* 172 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 175 */       String s = "delete from sis_cargos where  codigo='" + codigo + "'" + "";
/*     */ 
/*     */ 
/*     */       
/* 179 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 181 */     catch (Exception e) {
/* 182 */       e.printStackTrace();
/* 183 */       Utilidades.writeError("SisCargosDAO:eliminarRegistro ", e);
/* 184 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 186 */     return rta;
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
/*     */   public RespuestaBD crearRegistro(String codigo, String descripcion, String usuarioInsercion) {
/* 199 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 202 */       String s = "insert into sis_cargos(codigo,descripcion,fecha_insercion,usuario_insercion) values ('" + codigo + "'," + "'" + descripcion + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
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
/* 213 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 215 */     catch (Exception e) {
/* 216 */       e.printStackTrace();
/* 217 */       Utilidades.writeError("%SisCargosDAO:crearRegistro ", e);
/* 218 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 220 */     return rta;
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
/*     */   public RespuestaBD modificarRegistro(String codigo, String descripcion, String usuarioModificacion) {
/* 233 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 236 */       String s = "update sis_cargos set  descripcion='" + descripcion + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " codigo='" + codigo + "'" + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 243 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 245 */     catch (Exception e) {
/* 246 */       e.printStackTrace();
/* 247 */       Utilidades.writeError("SisCargosDAO:modificarRegistro ", e);
/* 248 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 250 */     return rta;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\SisCargosDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */