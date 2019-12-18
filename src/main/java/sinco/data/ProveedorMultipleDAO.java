/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import sinco.business.ProveedorMultipleDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.ProveedorMultipleDAO;
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
/*     */ public class ProveedorMultipleDAO
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
/*  42 */       Utilidades.writeError("ProveedorMultipleDAO:close", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ProveedorMultipleDTO next() {
/*     */     try {
/*  53 */       if (this.rs.next()) {
/*  54 */         return leerRegistro();
/*     */       }
/*     */     }
/*  57 */     catch (Exception e) {
/*  58 */       e.printStackTrace();
/*  59 */       Utilidades.writeError("ProveedorMultipleDAO:next ", e);
/*     */     } 
/*  61 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ProveedorMultipleDTO leerRegistro() {
/*     */     try {
/*  71 */       ProveedorMultipleDTO reg = new ProveedorMultipleDTO();
/*  72 */       reg.setCodigoArea(this.rs.getInt("codigo_area"));
/*  73 */       reg.setCodigoServicio(this.rs.getInt("codigo_servicio"));
/*  74 */       reg.setPersonaCargo(this.rs.getInt("persona_cargo"));
/*  75 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  76 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  77 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  78 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  79 */       reg.setNombreResponsable(this.rs.getString("nombre_responsable"));
/*  80 */       return reg;
/*     */     }
/*  82 */     catch (Exception e) {
/*  83 */       e.printStackTrace();
/*  84 */       Utilidades.writeError("ProveedorMultipleDAO:leerRegistro ", e);
/*     */       
/*  86 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ProveedorMultipleDTO next2() {
/*     */     try {
/*  97 */       if (this.rs.next()) {
/*  98 */         return leerRegistro2();
/*     */       }
/*     */     }
/* 101 */     catch (Exception e) {
/* 102 */       e.printStackTrace();
/* 103 */       Utilidades.writeError("ProveedorMultipleDAO:next ", e);
/*     */     } 
/* 105 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ProveedorMultipleDTO leerRegistro2() {
/*     */     try {
/* 115 */       ProveedorMultipleDTO reg = new ProveedorMultipleDTO();
/* 116 */       reg.setPersonaCargo(this.rs.getInt("persona_cargo"));
/* 117 */       reg.setNombreResponsable(this.rs.getString("nombre_responsable"));
/* 118 */       reg.setExiste(this.rs.getString("existe"));
/* 119 */       return reg;
/*     */     }
/* 121 */     catch (Exception e) {
/* 122 */       e.printStackTrace();
/* 123 */       Utilidades.writeError("ProveedorMultipleDAO:leerRegistro ", e);
/*     */       
/* 125 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean cargarMultiplesArea(int codigoArea, int codigoServicio) {
/*     */     try {
/* 136 */       String s = "select personas.codigo_empleado as persona_cargo,personas.nombres||' '||personas.apellidos as nombre_responsable, 'S' AS existe from proveedor_multiple,sis_usuarios personas where proveedor_multiple.persona_cargo=personas.codigo_empleado and proveedor_multiple.codigo_area=" + codigoArea + " and proveedor_multiple.codigo_servicio=" + codigoServicio + " and personas.estado IN('A','T')" + " union " + " select personas.codigo_empleado as persona_cargo," + "personas.nombres||' '||personas.apellidos as nombre_responsable," + " 'N' AS existe" + " from sis_usuarios  personas,Sis_Usuarios_Area Ua" + " where  Personas.Codigo_Empleado = Ua.Codigo_Empleado" + " and Ua.Codigo_Area = " + codigoArea + " and personas.estado IN('A','T')" + " and personas.codigo_empleado not in(select persona_cargo " + " from proveedor_multiple,sis_usuarios personas" + " where proveedor_multiple.persona_cargo=personas.codigo_empleado" + " and proveedor_multiple.codigo_area=" + codigoArea + " and proveedor_multiple.codigo_servicio=" + codigoServicio + " and personas.estado IN('A','T'))" + " order by 2";
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 159 */       boolean rtaDB = this.dat.parseSql(s);
/* 160 */       if (!rtaDB) {
/* 161 */         return false;
/*     */       }
/*     */       
/* 164 */       this.rs = this.dat.getResultSet();
/* 165 */       return true;
/*     */     }
/* 167 */     catch (Exception e) {
/* 168 */       e.printStackTrace();
/* 169 */       Utilidades.writeError("ProveedorMultipleDAO:cargarTodos ", e);
/*     */       
/* 171 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean cargarTodos(int codigoArea, int codigoServicio) {
/*     */     try {
/* 181 */       String s = "select proveedor_multiple.* personas.nombres||' '||personas.apellidos as nombre_responsablefrom proveedor_multiple,sis_usuarios personas where proveedor_multiple.persona_cargo=personas.codigo_empleado and proveedor_multiple.codigo_area=" + codigoArea + " and proveedor_multiplecodigo_servicio=" + codigoServicio + " and personas.estado IN('A','T')" + " order by persona_cargo";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 189 */       boolean rtaDB = this.dat.parseSql(s);
/* 190 */       if (!rtaDB) {
/* 191 */         return false;
/*     */       }
/*     */       
/* 194 */       this.rs = this.dat.getResultSet();
/* 195 */       return true;
/*     */     }
/* 197 */     catch (Exception e) {
/* 198 */       e.printStackTrace();
/* 199 */       Utilidades.writeError("ProveedorMultipleDAO:cargarTodos ", e);
/*     */       
/* 201 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eliminarRegistro(int codigoArea, int codigoServicio) {
/*     */     try {
/* 211 */       String s = "delete from  proveedor_multiple where codigo_area=" + codigoArea + " and codigo_servicio=" + codigoServicio;
/* 212 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 215 */     catch (Exception e) {
/* 216 */       e.printStackTrace();
/* 217 */       Utilidades.writeError("ProveedorMultipleDAO:eliminarRegistro", e);
/*     */       
/* 219 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean crearRegistro(int codigoArea, int codigoServicio, int personaCargo, String usuarioInsercion) {
/*     */     try {
/* 229 */       String s = "insert into proveedor_multiple ( codigo_area,codigo_servicio,persona_cargo,usuario_insercion,fecha_insercion )";
/* 230 */       s = s + " values (";
/* 231 */       s = s + "" + codigoArea + ",";
/* 232 */       s = s + "" + codigoServicio + ",";
/* 233 */       s = s + "" + personaCargo + ",";
/* 234 */       s = s + "'" + usuarioInsercion + "',";
/* 235 */       s = s + "" + Utilidades.getFechaBD();
/* 236 */       s = s + ")";
/* 237 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 240 */     catch (Exception e) {
/* 241 */       e.printStackTrace();
/* 242 */       Utilidades.writeError("ProveedorMultipleDAO:crearRegistro", e);
/*     */       
/* 244 */       return false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\ProveedorMultipleDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */