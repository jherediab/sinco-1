/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.ContInterventorDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.ContInterventorDAO;
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
/*     */ public class ContInterventorDAO
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
/*  49 */       Utilidades.writeError("ContInterventorDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContInterventorDTO next() {
/*     */     try {
/*  60 */       if (this.rs.next()) {
/*  61 */         return leerRegistro();
/*     */       }
/*     */     }
/*  64 */     catch (Exception e) {
/*  65 */       e.printStackTrace();
/*  66 */       Utilidades.writeError("ContInterventorDAO:next ", e);
/*     */     } 
/*  68 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContInterventorDTO leerRegistro() {
/*     */     try {
/*  78 */       ContInterventorDTO reg = new ContInterventorDTO();
/*     */       
/*  80 */       reg.setTipoDocumento(this.rs.getString("tipo_documento"));
/*  81 */       reg.setNumeroDocumento(this.rs.getLong("numero_documento"));
/*  82 */       reg.setApellidos(this.rs.getString("apellidos"));
/*  83 */       reg.setNombres(this.rs.getString("nombres"));
/*  84 */       reg.setDireccion(this.rs.getString("direccion"));
/*  85 */       reg.setTelefono(this.rs.getString("telefono"));
/*  86 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  87 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  88 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  89 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  90 */       return reg;
/*     */     }
/*  92 */     catch (Exception e) {
/*  93 */       e.printStackTrace();
/*  94 */       Utilidades.writeError("ContInterventorDAO:leerRegistro ", e);
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
/*     */   public Collection<ContInterventorDTO> cargarTodos() {
/* 106 */     Collection<ContInterventorDTO> resultados = new ArrayList<ContInterventorDTO>();
/*     */     try {
/* 108 */       String s = "select  t.tipo_documento,t.numero_documento,t.apellidos,t.nombres,t.direccion,t.telefono,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion  from cont_interventor t where 1=1";
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
/* 133 */       Utilidades.writeError("ContInterventorDAO:cargarTodos ", e);
/*     */     } 
/* 135 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContInterventorDTO cargarRegistro(String tipoDocumento, String numeroDocumento) {
/*     */     try {
/* 146 */       String s = "select  t.tipo_documento,t.numero_documento,t.apellidos,t.nombres,t.direccion,t.telefono,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion  from cont_interventor t where  t.tipo_documento='" + tipoDocumento + "'" + " and t.numero_documento='" + numeroDocumento + "'" + "";
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
/* 162 */       boolean rtaDB = this.dat.parseSql(s);
/* 163 */       if (!rtaDB) {
/* 164 */         return null;
/*     */       }
/* 166 */       this.rs = this.dat.getResultSet();
/* 167 */       if (this.rs.next()) {
/* 168 */         return leerRegistro();
/*     */       }
/*     */     }
/* 171 */     catch (Exception e) {
/* 172 */       e.printStackTrace();
/* 173 */       Utilidades.writeError("ContInterventorDAO:cargarInterventor", e);
/*     */     } 
/* 175 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int siguienteRegistro(String numeroDocumento) {
/* 185 */     int inumero = 1;
/* 186 */     String s = "select max(0) from cont_interventor  where  numero_documento='" + numeroDocumento + "'" + "";
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 191 */       boolean rta = this.dat.parseSql(s);
/* 192 */       if (!rta) return 0; 
/* 193 */       this.rs = this.dat.getResultSet();
/* 194 */       if (this.rs.next()) {
/* 195 */         s = this.rs.getString(1);
/* 196 */         if (!this.rs.wasNull()) {
/* 197 */           inumero = Integer.parseInt(s) + 1;
/*     */         }
/*     */       } 
/* 200 */       return inumero;
/*     */     }
/* 202 */     catch (Exception e) {
/* 203 */       e.printStackTrace();
/* 204 */       Utilidades.writeError("ContInterventorDAO:siguienteRegistro ", e);
/*     */       
/* 206 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eliminarRegistro(String tipoDocumento, long numeroDocumento) {
/*     */     try {
/* 218 */       String s = "delete from cont_interventor where  numero_documento='" + numeroDocumento + "'" + " and tipo_documento='" + tipoDocumento + "'" + "";
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 223 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 226 */     catch (Exception e) {
/* 227 */       e.printStackTrace();
/* 228 */       Utilidades.writeError("ContInterventorDAO:eliminarRegistro ", e);
/*     */       
/* 230 */       return false;
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
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean crearRegistro(String tipoDocumento, long numeroDocumento, String apellidos, String nombres, String direccion, String telefono, String usuarioInsercion) {
/*     */     try {
/* 254 */       String s = "insert into cont_interventor(tipo_documento,numero_documento,apellidos,nombres,direccion,telefono,usuario_insercion,fecha_insercion) values ('" + tipoDocumento + "'," + "'" + numeroDocumento + "'," + "'" + apellidos + "'," + "'" + nombres + "'," + "'" + direccion + "'," + "'" + telefono + "'," + "'" + usuarioInsercion + "'," + "" + Utilidades.getFechaBD() + "" + ")";
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
/* 273 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 276 */     catch (Exception e) {
/* 277 */       e.printStackTrace();
/* 278 */       Utilidades.writeError("%ContInterventorDAO:crearRegistro ", e);
/*     */       
/* 280 */       return false;
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
/*     */   public boolean modificarRegistro(String tipoDocumento, long numeroDocumento, String apellidos, String nombres, String direccion, String telefono, String usuarioModificacion) {
/*     */     try {
/* 297 */       String s = "update cont_interventor set  tipo_documento='" + tipoDocumento + "'," + " apellidos='" + apellidos + "'," + " nombres='" + nombres + "'," + " direccion='" + direccion + "'," + " telefono='" + telefono + "'," + " usuario_modificacion='" + usuarioModificacion + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "" + " where" + " numero_documento='" + numeroDocumento + "'" + "";
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
/* 308 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 311 */     catch (Exception e) {
/* 312 */       e.printStackTrace();
/* 313 */       Utilidades.writeError("ContInterventorDAO:modificarRegistro ", e);
/*     */       
/* 315 */       return false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\ContInterventorDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */