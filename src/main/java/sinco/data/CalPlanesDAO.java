/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import sinco.business.CalPlanesDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.CalPlanesDAO;
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
/*     */ public class CalPlanesDAO
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
/*  42 */       Utilidades.writeError("CalPlanesFactory:close", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalPlanesDTO next() {
/*     */     try {
/*  53 */       if (this.rs.next()) {
/*  54 */         return leerRegistro();
/*     */       }
/*     */     }
/*  57 */     catch (Exception e) {
/*  58 */       e.printStackTrace();
/*  59 */       Utilidades.writeError("CalPlanesFactory:next ", e);
/*     */     } 
/*  61 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalPlanesDTO leerRegistro() {
/*     */     try {
/*  71 */       CalPlanesDTO reg = new CalPlanesDTO();
/*  72 */       reg.setCiclo(this.rs.getInt("ciclo"));
/*  73 */       reg.setCodigoPlan(this.rs.getInt("codigo_plan"));
/*  74 */       reg.setCodigoArea(this.rs.getInt("codigo_area"));
/*  75 */       reg.setEstado(this.rs.getString("estado"));
/*  76 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  77 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  78 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  79 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  80 */       reg.setNombreArea(this.rs.getString("nombre_area"));
/*  81 */       return reg;
/*     */     }
/*  83 */     catch (Exception e) {
/*  84 */       e.printStackTrace();
/*  85 */       Utilidades.writeError("CalPlanesFactory:leerRegistro ", e);
/*     */       
/*  87 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalPlanesDTO cargarPlanArea(int ciclo, int area) {
/*     */     try {
/*  97 */       String s = "select p.*,u.Descripcion Nombre_Area from   Cal_Planes p, Unidades_Dependencia u where  p.Codigo_Area = u.Codigo and p.Ciclo = " + ciclo + " and p.CODIGO_AREA =" + area;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 104 */       boolean rtaDB = this.dat.parseSql(s);
/* 105 */       if (!rtaDB) {
/* 106 */         return null;
/*     */       }
/*     */       
/* 109 */       this.rs = this.dat.getResultSet();
/* 110 */       if (this.rs.next()) {
/* 111 */         return leerRegistro();
/*     */       }
/* 113 */       return null;
/*     */     }
/* 115 */     catch (Exception e) {
/* 116 */       e.printStackTrace();
/* 117 */       Utilidades.writeError("CalPlanesFactory:cargarTodos ", e);
/*     */       
/* 119 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalPlanesDTO cargarArea(int ciclo, int plan) {
/*     */     try {
/* 130 */       String s = "select p.*,u.Descripcion Nombre_Area from   Cal_Planes p, Unidades_Dependencia u where  p.Codigo_Area = u.Codigo and p.Ciclo = " + ciclo + " and p.Codigo_Plan = " + plan;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 137 */       boolean rtaDB = this.dat.parseSql(s);
/* 138 */       if (!rtaDB) {
/* 139 */         return null;
/*     */       }
/*     */       
/* 142 */       this.rs = this.dat.getResultSet();
/* 143 */       if (this.rs.next()) {
/* 144 */         return leerRegistro();
/*     */       }
/* 146 */       return null;
/*     */     }
/* 148 */     catch (Exception e) {
/* 149 */       e.printStackTrace();
/* 150 */       Utilidades.writeError("CalPlanesFactory:cargarTodos ", e);
/*     */       
/* 152 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean crearRegistro(int ciclo, int codigoPlan, int codigoArea, String estado, String fechaInsercion, String usuarioInsercion, String fechaModificacion, String usuarioModificacion) {
/*     */     try {
/* 163 */       String s = "insert into cal_planes ( ciclo,codigo_plan,codigo_area,estado,fecha_insercion,usuario_insercion,fecha_modificacion,usuario_modificacion )";
/* 164 */       s = s + " values (";
/* 165 */       s = s + "" + ciclo + ",";
/* 166 */       s = s + "" + codigoPlan + ",";
/* 167 */       s = s + "" + codigoArea + ",";
/* 168 */       s = s + "'" + estado + "',";
/* 169 */       s = s + "" + Utilidades.formatoFecha(fechaInsercion) + ",";
/* 170 */       s = s + "'" + usuarioInsercion + "',";
/* 171 */       s = s + "" + Utilidades.formatoFecha(fechaModificacion) + ",";
/* 172 */       s = s + "'" + usuarioModificacion + "'";
/* 173 */       s = s + ")";
/* 174 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 177 */     catch (Exception e) {
/* 178 */       e.printStackTrace();
/* 179 */       Utilidades.writeError("CalPlanesFactory:crearRegistro", e);
/*     */       
/* 181 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean modificarRegistro(int ciclo, int codigoPlan, int codigoArea, String estado, String fechaInsercion, String usuarioInsercion, String fechaModificacion, String usuarioModificacion) {
/*     */     try {
/* 191 */       String s = "update cal_planes set ";
/* 192 */       s = s + " ciclo=" + ciclo + ",";
/* 193 */       s = s + " codigo_plan=" + codigoPlan + ",";
/* 194 */       s = s + " codigo_area=" + codigoArea + ",";
/* 195 */       s = s + " estado='" + estado + "',";
/* 196 */       s = s + " fecha_insercion=" + Utilidades.formatoFecha(fechaInsercion) + ",";
/* 197 */       s = s + " usuario_insercion='" + usuarioInsercion + "',";
/* 198 */       s = s + " fecha_modificacion=" + Utilidades.formatoFecha(fechaModificacion) + ",";
/* 199 */       s = s + " usuario_modificacion='" + usuarioModificacion + "'";
/* 200 */       s = s + " where ";
/* 201 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 204 */     catch (Exception e) {
/* 205 */       e.printStackTrace();
/* 206 */       Utilidades.writeError("CalPlanesFactory:modificarRegistro", e);
/*     */       
/* 208 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean cargarPlanesArea(int ciclo) {
/*     */     try {
/* 218 */       String s = "select p.*, \t\t a.Descripcion as Nombre_Area, \t\t Per.Nombres, \t\t Per.Apellidos, \t\t Per.email, \t\t Per.Estado    as Estado_Persona, \t\t Per.Dominio from   Cal_Planes           p, \t\t Unidades_Dependencia a, \t\t sis_usuarios         Per, \t\t sis_usuarios_Area    Pa where  Pa.Codigo_Area = a.Codigo \t\t and Pa.Codigo_Empleado = Per.Codigo_Empleado \t\t and a.Codigo = p.Codigo_Area \t\t and Pa.Responsable_Area = 'S' \t\t and p.Estado in ('A','T') \t\t and p.Ciclo = " + ciclo + " order  by a.Descripcion";
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
/*     */       
/* 236 */       boolean rtaDB = this.dat.parseSql(s);
/* 237 */       this.rs = this.dat.getResultSet();
/* 238 */       return rtaDB;
/*     */     }
/* 240 */     catch (Exception e) {
/* 241 */       e.printStackTrace();
/* 242 */       Utilidades.writeError("CalPlanesFactory:cargarTodos ", e);
/*     */       
/* 244 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalPlanesDTO next2() {
/*     */     try {
/* 254 */       if (this.rs.next()) {
/* 255 */         CalPlanesDTO reg = new CalPlanesDTO();
/* 256 */         reg.setCiclo(this.rs.getInt("ciclo"));
/* 257 */         reg.setCodigoPlan(this.rs.getInt("codigo_plan"));
/* 258 */         reg.setCodigoArea(this.rs.getInt("codigo_area"));
/* 259 */         reg.setEstado(this.rs.getString("estado"));
/* 260 */         reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/* 261 */         reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/* 262 */         reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/* 263 */         reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/* 264 */         reg.setNombreArea(this.rs.getString("nombre_area"));
/* 265 */         reg.setNombres(this.rs.getString("nombres"));
/* 266 */         reg.setApellidos(this.rs.getString("apellidos"));
/* 267 */         reg.setEmail(this.rs.getString("email"));
/* 268 */         reg.setEstadoPersona(this.rs.getString("estado_persona"));
/* 269 */         reg.setDominio(this.rs.getString("dominio"));
/* 270 */         return reg;
/*     */       }
/*     */     
/* 273 */     } catch (Exception e) {
/* 274 */       e.printStackTrace();
/* 275 */       Utilidades.writeError("CalPlanesFactory:next ", e);
/*     */     } 
/* 277 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\CalPlanesDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */