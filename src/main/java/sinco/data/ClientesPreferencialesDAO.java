/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import sinco.business.ClientesPreferencialesDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.ClientesPreferencialesDAO;
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
/*     */ public class ClientesPreferencialesDAO
/*     */ {
/*     */   ResultSet rs;
/*  24 */   DBManager dat = new DBManager();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  33 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/*     */     try {
/*  40 */       this.dat.close();
/*     */     }
/*  42 */     catch (Exception e) {
/*  43 */       Utilidades.writeError("ClientesPreferencialesDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ClientesPreferencialesDTO next() {
/*     */     try {
/*  54 */       if (this.rs.next()) {
/*  55 */         return leerRegistro();
/*     */       }
/*     */     }
/*  58 */     catch (Exception e) {
/*  59 */       e.printStackTrace();
/*  60 */       Utilidades.writeError("ClientesPreferencialesDAO:next ", e);
/*     */     } 
/*  62 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ClientesPreferencialesDTO leerRegistro() {
/*     */     try {
/*  72 */       ClientesPreferencialesDTO reg = new ClientesPreferencialesDTO();
/*  73 */       reg.setCodigoServicio(this.rs.getInt("codigo_servicio"));
/*  74 */       reg.setCodigoPersona(this.rs.getInt("codigo_persona"));
/*  75 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  76 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  77 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  78 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  79 */       reg.setNombrePersona(this.rs.getString("nombre_persona"));
/*  80 */       reg.setNombreArea(this.rs.getString("nombre_area"));
/*  81 */       return reg;
/*     */     }
/*  83 */     catch (Exception e) {
/*  84 */       e.printStackTrace();
/*  85 */       Utilidades.writeError("ClientesPreferencialesDAO:leerRegistro ", e);
/*     */       
/*  87 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean cargarTodos(int codigoServicio) {
/*     */     try {
/*  97 */       String s = " select Cp.*,        u.Apellidos || ' ' || u.Nombres as Nombre_Persona,        a.Descripcion as Nombre_Area from   Clientes_Preferenciales Cp,        Sis_Usuarios            u,        Sis_Usuarios_Area       Ua,        Unidades_Dependencia    a where  Cp.Codigo_Persona = u.Codigo_Empleado        and u.Codigo_Empleado = Ua.Codigo_Empleado        and Ua.Codigo_Area = a.Codigo        and Ua.Area_Principal = 'S'        and Cp.Codigo_Servicio = " + codigoServicio + " order  by u.Apellidos || ' ' || u.Nombres";
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
/* 110 */       boolean rtaDB = this.dat.parseSql(s);
/* 111 */       if (!rtaDB) {
/* 112 */         return false;
/*     */       }
/* 114 */       this.rs = this.dat.getResultSet();
/* 115 */       return true;
/*     */     }
/* 117 */     catch (Exception e) {
/* 118 */       e.printStackTrace();
/* 119 */       Utilidades.writeError("ClientesPreferencialesDAO:cargarTodos ", e);
/*     */       
/* 121 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ClientesPreferencialesDTO cargarRegistro(int codigoServicio, int codigoPersona) {
/*     */     try {
/* 131 */       String s = " select Cp.*,        u.Apellidos || ' ' || u.Nombres as Nombre_Persona,        a.Descripcion as Nombre_Area from   Clientes_Preferenciales Cp,        Sis_Usuarios            u,        Sis_Usuarios_Area       Ua,        Unidades_Dependencia    a where  Cp.Codigo_Persona = u.Codigo_Empleado        and u.Codigo_Empleado = Ua.Codigo_Empleado        and Ua.Codigo_Area = a.Codigo        and Ua.Area_Principal = 'S'        and cp.codigo_persona=" + codigoPersona + "        and Cp.Codigo_Servicio = " + codigoServicio + " order  by u.Apellidos || ' ' || u.Nombres";
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
/* 145 */       boolean rtaDB = this.dat.parseSql(s);
/* 146 */       if (!rtaDB) {
/* 147 */         return null;
/*     */       }
/*     */       
/* 150 */       this.rs = this.dat.getResultSet();
/* 151 */       if (this.rs.next()) {
/* 152 */         return leerRegistro();
/*     */       }
/*     */     }
/* 155 */     catch (Exception e) {
/* 156 */       e.printStackTrace();
/* 157 */       Utilidades.writeError("ClientesPreferencialesDAO:cargarClientesPreferenciales ", e);
/*     */     } 
/* 159 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarRegistro(int codigoServicio, int codigoPersona) {
/* 169 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 172 */       String s = "delete from  clientes_preferenciales where codigo_servicio=" + codigoServicio + " and codigo_persona=" + codigoPersona;
/* 173 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 175 */     catch (Exception e) {
/* 176 */       e.printStackTrace();
/* 177 */       Utilidades.writeError("ClientesPreferencialesDAO:eliminarRegistro ", e);
/* 178 */       rta.setMensaje(e.getMessage());
/*     */     } 
/*     */     
/* 181 */     return rta;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD crearRegistro(int codigoServicio, int codigoPersona, String usuarioInsercion) {
/* 190 */     RespuestaBD rta = new RespuestaBD();
/*     */     try {
/* 192 */       String s = "insert into clientes_preferenciales (codigo_servicio,codigo_persona,fecha_insercion,usuario_insercion)";
/* 193 */       s = s + " values (";
/* 194 */       s = s + "" + codigoServicio + ",";
/* 195 */       s = s + "" + codigoPersona + ",";
/* 196 */       s = s + "" + Utilidades.getFechaBD() + ",";
/* 197 */       s = s + "'" + usuarioInsercion + "'";
/* 198 */       s = s + ")";
/* 199 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 201 */     catch (Exception e) {
/* 202 */       e.printStackTrace();
/* 203 */       rta.setMensaje(e.getMessage());
/* 204 */       Utilidades.writeError("ClientesPreferencialesDAO:crearRegistro ", e);
/*     */     } 
/* 206 */     return rta;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD modificarRegistro(int codigoServicio, int codigoPersona, String usuarioModificacion) {
/* 215 */     RespuestaBD rta = new RespuestaBD();
/*     */     try {
/* 217 */       String s = "update clientes_preferenciales set ";
/* 218 */       s = s + " fecha_modificacion=" + Utilidades.getFechaBD() + ",";
/* 219 */       s = s + " usuario_modificacion='" + usuarioModificacion + "'";
/* 220 */       s = s + " where ";
/* 221 */       s = s + " codigo_servicio=" + codigoServicio;
/* 222 */       s = s + " and codigo_persona=" + codigoPersona;
/* 223 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 225 */     catch (Exception e) {
/* 226 */       e.printStackTrace();
/* 227 */       Utilidades.writeError("ClientesPreferencialesDAO:modificarRegistro ", e);
/* 228 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 230 */     return rta;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\ClientesPreferencialesDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */