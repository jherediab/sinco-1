/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.ParCiudadDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.ParCiudadDAO;
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
/*     */ public class ParCiudadDAO
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
/*  49 */       Utilidades.writeError("ParCiudadDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ParCiudadDTO next() {
/*     */     try {
/*  60 */       if (this.rs.next()) {
/*  61 */         return leerRegistro();
/*     */       }
/*     */     }
/*  64 */     catch (Exception e) {
/*  65 */       e.printStackTrace();
/*  66 */       Utilidades.writeError("ParCiudadDAO:next ", e);
/*     */     } 
/*  68 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ParCiudadDTO leerRegistro() {
/*     */     try {
/*  78 */       ParCiudadDTO reg = new ParCiudadDTO();
/*     */       
/*  80 */       reg.setCodigoCiudad(this.rs.getString("codigo_ciudad"));
/*  81 */       reg.setNombreCiudad(this.rs.getString("nombre_ciudad"));
/*  82 */       reg.setCodigoDepartamento(this.rs.getString("codigo_departamento"));
/*  83 */       return reg;
/*     */     }
/*  85 */     catch (Exception e) {
/*  86 */       e.printStackTrace();
/*  87 */       Utilidades.writeError("ParCiudadDAO:leerRegistro ", e);
/*     */       
/*  89 */       return null;
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
/*     */   public Collection cargarTodos(String codigoCiudad, String nombreCiudad, String codigoDepartamento) {
/* 101 */     Collection resultados = new ArrayList();
/*     */     try {
/* 103 */       String s = "select  t.codigo_ciudad,t.nombre_ciudad,t.codigo_departamento  from par_ciudad t where 1=1";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 109 */       if (codigoCiudad.length() > 0) {
/* 110 */         s = s + " and upper(t.codigo_ciudad) like upper('%" + codigoCiudad + "%')";
/*     */       }
/* 112 */       if (nombreCiudad.length() > 0) {
/* 113 */         s = s + " and upper(t.nombre_ciudad) like upper('%" + nombreCiudad + "%')";
/*     */       }
/* 115 */       if (codigoDepartamento.length() > 0) {
/* 116 */         s = s + " and upper(t.codigo_departamento) like upper('%" + codigoDepartamento + "%')";
/*     */       }
/* 118 */       s = s + " order by 1";
/* 119 */       boolean rtaDB = this.dat.parseSql(s);
/* 120 */       if (!rtaDB) {
/* 121 */         return resultados;
/*     */       }
/* 123 */       this.rs = this.dat.getResultSet();
/* 124 */       while (this.rs.next()) {
/* 125 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 128 */     catch (Exception e) {
/* 129 */       e.printStackTrace();
/* 130 */       Utilidades.writeError("ParCiudadDAO:cargarTodos ", e);
/*     */     } 
/* 132 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ParCiudadDTO cargarRegistro(String codigoCiudad, String codigoDepartamento) {
/*     */     try {
/* 142 */       String s = "select  t.codigo_ciudad,t.nombre_ciudad,t.codigo_departamento  from par_ciudad t where  t.codigo_ciudad='" + codigoCiudad + "'" + " and t.codigo_departamento='" + codigoDepartamento + "'" + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 151 */       boolean rtaDB = this.dat.parseSql(s);
/* 152 */       if (!rtaDB) {
/* 153 */         return null;
/*     */       }
/* 155 */       this.rs = this.dat.getResultSet();
/* 156 */       if (this.rs.next()) {
/* 157 */         return leerRegistro();
/*     */       }
/*     */     }
/* 160 */     catch (Exception e) {
/* 161 */       e.printStackTrace();
/* 162 */       Utilidades.writeError("ParCiudadDAO:cargarParCiudad", e);
/*     */     } 
/* 164 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int siguienteRegistro(String codigoCiudad, String codigoDepartamento) {
/* 174 */     int inumero = 1;
/* 175 */     String s = "select max(0) from par_ciudad  where  codigo_ciudad='" + codigoCiudad + "'" + " and codigo_departamento='" + codigoDepartamento + "'" + "";
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 181 */       boolean rta = this.dat.parseSql(s);
/* 182 */       if (!rta) return 0; 
/* 183 */       this.rs = this.dat.getResultSet();
/* 184 */       if (this.rs.next()) {
/* 185 */         s = this.rs.getString(1);
/* 186 */         if (!this.rs.wasNull()) {
/* 187 */           inumero = Integer.parseInt(s) + 1;
/*     */         }
/*     */       } 
/* 190 */       return inumero;
/*     */     }
/* 192 */     catch (Exception e) {
/* 193 */       e.printStackTrace();
/* 194 */       Utilidades.writeError("ParCiudadDAO:siguienteRegistro ", e);
/*     */       
/* 196 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eliminarRegistro(String codigoCiudad, String codigoDepartamento) {
/*     */     try {
/* 207 */       String s = "delete from par_ciudad where  codigo_ciudad='" + codigoCiudad + "'" + "  and codigo_departamento='" + codigoDepartamento + "'" + "";
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 212 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 215 */     catch (Exception e) {
/* 216 */       e.printStackTrace();
/* 217 */       Utilidades.writeError("ParCiudadDAO:eliminarRegistro ", e);
/*     */       
/* 219 */       return false;
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
/*     */   public boolean crearRegistro(String codigoCiudad, String nombreCiudad, String codigoDepartamento) {
/*     */     try {
/* 239 */       String s = "insert into par_ciudad(codigo_ciudad,nombre_ciudad,codigo_departamento) values ('" + codigoCiudad + "'," + "'" + nombreCiudad + "'," + "'" + codigoDepartamento + "'" + ")";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 248 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 251 */     catch (Exception e) {
/* 252 */       e.printStackTrace();
/* 253 */       Utilidades.writeError("%ParCiudadDAO:crearRegistro ", e);
/*     */       
/* 255 */       return false;
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
/*     */   public boolean modificarRegistro(String codigoCiudad, String nombreCiudad, String codigoDepartamento) {
/*     */     try {
/* 269 */       String s = "update par_ciudad set  nombre_ciudad='" + nombreCiudad + "'" + " where" + " codigo_ciudad='" + codigoCiudad + "'" + " and codigo_departamento='" + codigoDepartamento + "'" + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 275 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 278 */     catch (Exception e) {
/* 279 */       e.printStackTrace();
/* 280 */       Utilidades.writeError("ParCiudadDAO:modificarRegistro ", e);
/*     */       
/* 282 */       return false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\ParCiudadDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */