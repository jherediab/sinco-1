/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.CalPlantillasDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.CalPlantillasDAO;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CalPlantillasDAO
/*     */ {
/*     */   ResultSet rs;
/*  28 */   DBManager dat = new DBManager();
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
/*  39 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/*     */     try {
/*  46 */       this.dat.close();
/*     */     }
/*  48 */     catch (Exception e) {
/*  49 */       Utilidades.writeError("CalPlantillasDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalPlantillasDTO next() {
/*     */     try {
/*  60 */       if (this.rs.next()) {
/*  61 */         return leerRegistro();
/*     */       }
/*     */     }
/*  64 */     catch (Exception e) {
/*  65 */       e.printStackTrace();
/*  66 */       Utilidades.writeError("CalPlantillasDAO:next ", e);
/*     */     } 
/*  68 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalPlantillasDTO leerRegistro() {
/*     */     try {
/*  78 */       CalPlantillasDTO reg = new CalPlantillasDTO();
/*     */       
/*  80 */       reg.setCodigo(this.rs.getInt("codigo"));
/*  81 */       reg.setDescripcion(this.rs.getString("descripcion"));
/*  82 */       reg.setEstado(this.rs.getString("estado"));
/*  83 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  84 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  85 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  86 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  87 */       return reg;
/*     */     }
/*  89 */     catch (Exception e) {
/*  90 */       e.printStackTrace();
/*  91 */       Utilidades.writeError("CalPlantillasDAO:leerRegistro ", e);
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
/*     */   
/*     */   public Collection cargarTodos(String descripcion, String estado) {
/* 104 */     Collection resultados = new ArrayList();
/*     */     try {
/* 106 */       String s = "select * from cal_plantillas where 1=1";
/*     */       
/* 108 */       if (descripcion.length() > 0) {
/* 109 */         s = s + " and upper(descripcion) like upper('%" + descripcion + "%')";
/*     */       }
/* 111 */       if (estado.length() > 0) {
/* 112 */         s = s + " and upper(estado) ='A'";
/*     */       }
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
/* 126 */       Utilidades.writeError("CalPlantillasDAO:cargarTodos ", e);
/*     */     } 
/* 128 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalPlantillasDTO cargarRegistro(int codigo) {
/*     */     try {
/* 137 */       String s = "select * from cal_plantillas  where  codigo=" + codigo + "";
/*     */ 
/*     */ 
/*     */       
/* 141 */       boolean rtaDB = this.dat.parseSql(s);
/* 142 */       if (!rtaDB) {
/* 143 */         return null;
/*     */       }
/* 145 */       this.rs = this.dat.getResultSet();
/* 146 */       if (this.rs.next()) {
/* 147 */         return leerRegistro();
/*     */       }
/*     */     }
/* 150 */     catch (Exception e) {
/* 151 */       e.printStackTrace();
/* 152 */       Utilidades.writeError("CalPlantillasDAO:cargarCalPlantillas", e);
/*     */     } 
/* 154 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int siguienteRegistro() {
/* 163 */     int inumero = 1;
/* 164 */     String s = "select max(codigo) from cal_plantillas ";
/*     */     try {
/* 166 */       boolean rta = this.dat.parseSql(s);
/* 167 */       if (!rta) return 0; 
/* 168 */       this.rs = this.dat.getResultSet();
/* 169 */       if (this.rs.next()) {
/* 170 */         s = this.rs.getString(1);
/* 171 */         if (!this.rs.wasNull()) {
/* 172 */           inumero = Integer.parseInt(s) + 1;
/*     */         }
/*     */       } 
/* 175 */       return inumero;
/*     */     }
/* 177 */     catch (Exception e) {
/* 178 */       e.printStackTrace();
/* 179 */       Utilidades.writeError("CalPlantillasDAO:siguienteRegistro ", e);
/*     */       
/* 181 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eliminarRegistro(int codigo) {
/*     */     try {
/* 191 */       String s = "delete from cal_plantillas where  codigo=" + codigo + "";
/*     */ 
/*     */ 
/*     */       
/* 195 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 198 */     catch (Exception e) {
/* 199 */       e.printStackTrace();
/* 200 */       Utilidades.writeError("CalPlantillasDAO:eliminarRegistro ", e);
/*     */       
/* 202 */       return false;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean crearRegistro(int codigo, String descripcion, String estado, int plantillaAnterior, String usuarioInsercion) {
/*     */     try {
/* 223 */       String s = "insert into cal_plantillas(codigo,descripcion,estado,fecha_insercion,usuario_insercion) values (" + codigo + "," + "'" + descripcion + "'," + "'" + estado + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
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
/* 236 */       boolean rta = this.dat.executeUpdate(s);
/* 237 */       if (rta) {
/* 238 */         s = "INSERT INTO cal_plantillas_objetivos(codigo_plantilla, codigo_objetivo, fecha_insercion, usuario_insercion, procesado, estado) select " + codigo + "," + " codigo_objetivo," + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'," + "procesado," + "estado" + " from cal_plantillas_objetivos c" + " where estado='A'" + " and c.codigo_plantilla=" + plantillaAnterior;
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
/* 250 */         this.dat.executeUpdate(s);
/*     */       } 
/* 252 */       return rta;
/*     */     }
/* 254 */     catch (Exception e) {
/* 255 */       e.printStackTrace();
/* 256 */       Utilidades.writeError("%CalPlantillasDAO:crearRegistro ", e);
/*     */       
/* 258 */       return false;
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
/*     */   public boolean modificarRegistro(int codigo, String descripcion, String estado, String usuarioModificacion) {
/*     */     try {
/* 273 */       String s = "update cal_plantillas set  descripcion='" + descripcion + "'," + " estado='" + estado + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " codigo=" + codigo + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 281 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 284 */     catch (Exception e) {
/* 285 */       e.printStackTrace();
/* 286 */       Utilidades.writeError("CalPlantillasDAO:modificarRegistro ", e);
/*     */       
/* 288 */       return false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\CalPlantillasDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */