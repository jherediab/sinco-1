/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.RedPrestadorDocumentosDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.RedPrestadorDocumentosDAO;
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
/*     */ public class RedPrestadorDocumentosDAO
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
/*  49 */       Utilidades.writeError("RedPrestadorDocumentosDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RedPrestadorDocumentosDTO next() {
/*     */     try {
/*  60 */       if (this.rs.next()) {
/*  61 */         return leerRegistro();
/*     */       }
/*     */     }
/*  64 */     catch (Exception e) {
/*  65 */       e.printStackTrace();
/*  66 */       Utilidades.writeError("RedPrestadorDocumentosDAO:next ", e);
/*     */     } 
/*  68 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RedPrestadorDocumentosDTO leerRegistro() {
/*     */     try {
/*  78 */       RedPrestadorDocumentosDTO reg = new RedPrestadorDocumentosDTO();
/*     */       
/*  80 */       reg.setNumeroIdentificacion(this.rs.getString("numero_identificacion"));
/*  81 */       reg.setTipoDocumento(this.rs.getString("tipo_documento"));
/*  82 */       reg.setCodigoSucursal(this.rs.getInt("codigo_sucursal"));
/*  83 */       reg.setTieneImagen(this.rs.getString("tiene_imagen"));
/*  84 */       reg.setFechaEmision(this.rs.getString("fecha_emision"));
/*  85 */       reg.setFechaCaducidad(this.rs.getString("fecha_caducidad"));
/*  86 */       return reg;
/*     */     }
/*  88 */     catch (Exception e) {
/*  89 */       e.printStackTrace();
/*  90 */       Utilidades.writeError("RedPrestadorDocumentosDAO:leerRegistro ", e);
/*     */       
/*  92 */       return null;
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
/*     */   public Collection<RedPrestadorDocumentosDTO> cargarTodos(String numeroIdentificacion, String tipoDocumento, int codigoSucursal, String tieneImagen, String fechaEmisionDesde, String fechaEmisionHasta, String fechaCaducidadDesde, String fechaCaducidadHasta) {
/* 109 */     Collection<RedPrestadorDocumentosDTO> resultados = new ArrayList<RedPrestadorDocumentosDTO>();
/*     */     try {
/* 111 */       String s = "select  t.numero_identificacion,t.tipo_documento,t.codigo_sucursal,t.tiene_imagen,t.fecha_emision,t.fecha_caducidad  from red_prestador_documentos t   where 1=1";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 120 */       if (numeroIdentificacion.length() > 0) {
/* 121 */         s = s + " and upper(t.numero_identificacion) like upper('%" + numeroIdentificacion + "%')";
/*     */       }
/* 123 */       if (tipoDocumento.length() > 0) {
/* 124 */         s = s + " and upper(t.tipo_documento) like upper('%" + tipoDocumento + "%')";
/*     */       }
/* 126 */       if (codigoSucursal > -1) {
/* 127 */         s = s + " and t.codigo_sucursal=" + codigoSucursal;
/*     */       }
/* 129 */       if (tieneImagen.length() > 0) {
/* 130 */         s = s + " and upper(t.tiene_imagen) like upper('%" + tieneImagen + "%')";
/*     */       }
/* 132 */       if (fechaEmisionDesde.length() > 0) {
/* 133 */         s = s + " and t.fecha_emision>=" + Utilidades.formatoFecha2(fechaEmisionDesde);
/*     */       }
/* 135 */       if (fechaEmisionHasta.length() > 0) {
/* 136 */         s = s + " and t.fecha_emision < " + Utilidades.formatoFecha2(fechaEmisionHasta);
/*     */       }
/* 138 */       if (fechaCaducidadDesde.length() > 0) {
/* 139 */         s = s + " and t.fecha_caducidad>=" + Utilidades.formatoFecha2(fechaCaducidadDesde);
/*     */       }
/* 141 */       if (fechaCaducidadHasta.length() > 0) {
/* 142 */         s = s + " and t.fecha_caducidad < " + Utilidades.formatoFecha2(fechaCaducidadHasta);
/*     */       }
/* 144 */       s = s + " order by 1";
/* 145 */       boolean rtaDB = this.dat.parseSql(s);
/* 146 */       if (!rtaDB) {
/* 147 */         return resultados;
/*     */       }
/* 149 */       this.rs = this.dat.getResultSet();
/* 150 */       while (this.rs.next()) {
/* 151 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 154 */     catch (Exception e) {
/* 155 */       e.printStackTrace();
/* 156 */       Utilidades.writeError("RedPrestadorDocumentosDAO:cargarTodos ", e);
/*     */     } 
/* 158 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RedPrestadorDocumentosDTO cargarRegistro(String numeroIdentificacion, String tipoDocumento, int codigoSucursal) {
/*     */     try {
/* 169 */       String s = "select  t.numero_identificacion,t.tipo_documento,t.codigo_sucursal,t.tiene_imagen,t.fecha_emision,t.fecha_caducidad  from red_prestador_documentos t where  t.numero_identificacion='" + numeroIdentificacion + "'" + " and t.tipo_documento='" + tipoDocumento + "'" + " and t.codigo_sucursal=" + codigoSucursal + "";
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
/* 182 */       boolean rtaDB = this.dat.parseSql(s);
/* 183 */       if (!rtaDB) {
/* 184 */         return null;
/*     */       }
/* 186 */       this.rs = this.dat.getResultSet();
/* 187 */       if (this.rs.next()) {
/* 188 */         return leerRegistro();
/*     */       }
/*     */     }
/* 191 */     catch (Exception e) {
/* 192 */       e.printStackTrace();
/* 193 */       Utilidades.writeError("RedPrestadorDocumentosDAO:cargarRedPrestadorDocumentos", e);
/*     */     } 
/* 195 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int siguienteRegistro(String numeroIdentificacion, String tipoDocumento, int codigoSucursal) {
/* 206 */     int inumero = 1;
/* 207 */     String s = "select max(0) from red_prestador_documentos  where  numero_identificacion='" + numeroIdentificacion + "'" + " and tipo_documento='" + tipoDocumento + "'" + " and codigo_sucursal=" + codigoSucursal + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 214 */       boolean rta = this.dat.parseSql(s);
/* 215 */       if (!rta) return 0; 
/* 216 */       this.rs = this.dat.getResultSet();
/* 217 */       if (this.rs.next()) {
/* 218 */         s = this.rs.getString(1);
/* 219 */         if (!this.rs.wasNull()) {
/* 220 */           inumero = Integer.parseInt(s) + 1;
/*     */         }
/*     */       } 
/* 223 */       return inumero;
/*     */     }
/* 225 */     catch (Exception e) {
/* 226 */       e.printStackTrace();
/* 227 */       Utilidades.writeError("RedPrestadorDocumentosDAO:siguienteRegistro ", e);
/*     */       
/* 229 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eliminarRegistro(String numeroIdentificacion, String tipoDocumento, int codigoSucursal) {
/*     */     try {
/* 241 */       String s = "delete from red_prestador_documentos where  numero_identificacion='" + numeroIdentificacion + "'";
/*     */ 
/*     */       
/* 244 */       if (tipoDocumento.length() > 0) {
/* 245 */         s = s + "  and tipo_documento='" + tipoDocumento + "'";
/*     */       }
/* 247 */       if (codigoSucursal >= 0) {
/* 248 */         s = s + "  and codigo_sucursal=" + codigoSucursal + "";
/*     */       }
/*     */ 
/*     */       
/* 252 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 255 */     catch (Exception e) {
/* 256 */       e.printStackTrace();
/* 257 */       Utilidades.writeError("RedPrestadorDocumentosDAO:eliminarRegistro ", e);
/*     */       
/* 259 */       return false;
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
/*     */   public boolean crearRegistro(String numeroIdentificacion, String tipoDocumento, int codigoSucursal, String tieneImagen, String fechaEmision, String fechaCaducidad) {
/*     */     try {
/* 277 */       String s = "insert into red_prestador_documentos(numero_identificacion,tipo_documento,codigo_sucursal,tiene_imagen,fecha_emision,fecha_caducidad) values ('" + numeroIdentificacion + "'," + "'" + tipoDocumento + "'," + "" + codigoSucursal + "," + "'" + tieneImagen + "'," + "" + ((fechaEmision.length() > 0) ? Utilidades.formatoFecha2(fechaEmision) : "null") + "," + "" + ((fechaCaducidad.length() > 0) ? Utilidades.formatoFecha2(fechaCaducidad) : "null") + "" + ")";
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
/* 292 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 295 */     catch (Exception e) {
/* 296 */       e.printStackTrace();
/* 297 */       Utilidades.writeError("%RedPrestadorDocumentosDAO:crearRegistro ", e);
/*     */       
/* 299 */       return false;
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
/*     */   public boolean modificarRegistro(String numeroIdentificacion, String tipoDocumento, int codigoSucursal, String tieneImagen, String fechaEmision, String fechaCaducidad) {
/*     */     try {
/* 316 */       String s = "update red_prestador_documentos set  tiene_imagen='" + tieneImagen + "'," + "fecha_emision=" + ((fechaEmision.length() > 0) ? Utilidades.formatoFecha2(fechaEmision) : "null") + "," + "fecha_caducidad=" + ((fechaCaducidad.length() > 0) ? Utilidades.formatoFecha2(fechaCaducidad) : "null") + "" + " where" + " numero_identificacion='" + numeroIdentificacion + "'" + " and tipo_documento='" + tipoDocumento + "'" + " and codigo_sucursal=" + codigoSucursal + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 325 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 328 */     catch (Exception e) {
/* 329 */       e.printStackTrace();
/* 330 */       Utilidades.writeError("RedPrestadorDocumentosDAO:modificarRegistro ", e);
/*     */       
/* 332 */       return false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\RedPrestadorDocumentosDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */