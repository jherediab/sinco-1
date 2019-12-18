/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.ContImpuestoDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.ContImpuestoDAO;
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
/*     */ public class ContImpuestoDAO
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
/*  49 */       Utilidades.writeError("ContImpuestoDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContImpuestoDTO next() {
/*     */     try {
/*  60 */       if (this.rs.next()) {
/*  61 */         return leerRegistro();
/*     */       }
/*     */     }
/*  64 */     catch (Exception e) {
/*  65 */       e.printStackTrace();
/*  66 */       Utilidades.writeError("ContImpuestoDAO:next ", e);
/*     */     } 
/*  68 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContImpuestoDTO leerRegistro() {
/*     */     try {
/*  78 */       ContImpuestoDTO reg = new ContImpuestoDTO();
/*     */       
/*  80 */       reg.setConsecutivoContrato(this.rs.getInt("consecutivo_contrato"));
/*  81 */       reg.setNumeroContrato(this.rs.getString("numero_contrato"));
/*  82 */       reg.setNumeroRecibo(this.rs.getString("numero_recibo"));
/*  83 */       reg.setFechaPago(this.rs.getString("fecha_pago"));
/*  84 */       reg.setValorPago(this.rs.getInt("valor_pago"));
/*  85 */       reg.setDescripcion(this.rs.getString("descripcion"));
/*  86 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  87 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  88 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  89 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  90 */       reg.setTipoImpuestoDescripcion(this.rs.getString("tipo_impuesto"));
/*  91 */       return reg;
/*     */     }
/*  93 */     catch (Exception e) {
/*  94 */       e.printStackTrace();
/*  95 */       Utilidades.writeError("ContImpuestoDAO:leerRegistro ", e);
/*     */       
/*  97 */       return null;
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
/*     */   public Collection<ContImpuestoDTO> cargarTodos(int consecutivoContrato, String numeroRecibo, String fechaPagoDesde, String fechaPagoHasta, String descripcion) {
/* 111 */     Collection<ContImpuestoDTO> resultados = new ArrayList<ContImpuestoDTO>();
/*     */     try {
/* 113 */       String s = "select  t.consecutivo_contrato,c.numero_contrato,t.numero_recibo,t.fecha_pago,t.valor_pago,t.descripcion,s.descripcion as tipo_impuesto,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion  from cont_impuesto t, sis_multivalores s, cont_contrato c where s.tabla='tipo_impuesto' and s.valor=t.descripcion and t.consecutivo_contrato=c.consecutivo_contrato";
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
/* 129 */       if (consecutivoContrato > 0) {
/* 130 */         s = s + " and t.consecutivo_contrato=" + consecutivoContrato;
/*     */       }
/* 132 */       if (numeroRecibo.length() > 0) {
/* 133 */         s = s + " and upper(t.numero_recibo) like upper('%" + numeroRecibo + "%')";
/*     */       }
/* 135 */       if (fechaPagoDesde.length() > 0) {
/* 136 */         s = s + " and t.fecha_pago>=" + Utilidades.formatoFecha2(fechaPagoDesde);
/*     */       }
/* 138 */       if (fechaPagoHasta.length() > 0) {
/* 139 */         s = s + " and t.fecha_pago < " + Utilidades.formatoFecha2(fechaPagoHasta);
/*     */       }
/* 141 */       if (descripcion.length() > 0) {
/* 142 */         s = s + " and upper(t.descripcion) like upper('%" + descripcion + "%')";
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
/* 156 */       Utilidades.writeError("ContImpuestoDAO:cargarTodos ", e);
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
/*     */   public ContImpuestoDTO cargarRegistro(int consecutivoContrato, String numeroRecibo, String descripcion) {
/*     */     try {
/* 169 */       String s = "select  t.consecutivo_contrato,c.numero_contrato,t.numero_recibo,t.fecha_pago,t.valor_pago,t.descripcion,s.descripcion as tipo_impuesto,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion  from cont_impuesto t, sis_multivalores s, cont_contrato c where s.tabla='tipo_impuesto' and s.valor=t.descripcion and t.consecutivo_contrato=" + consecutivoContrato + " and t.numero_recibo='" + numeroRecibo + "'" + " and t.descripcion='" + descripcion + "'" + " and t.consecutivo_contrato=c.consecutivo_contrato" + "";
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
/* 188 */       boolean rtaDB = this.dat.parseSql(s);
/* 189 */       if (!rtaDB) {
/* 190 */         return null;
/*     */       }
/* 192 */       this.rs = this.dat.getResultSet();
/* 193 */       if (this.rs.next()) {
/* 194 */         return leerRegistro();
/*     */       }
/*     */     }
/* 197 */     catch (Exception e) {
/* 198 */       e.printStackTrace();
/* 199 */       Utilidades.writeError("ContImpuestoDAO:cargarContImpuesto", e);
/*     */     } 
/* 201 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int siguienteRegistro(int consecutivoContrato, String numeroRecibo, String descripcion) {
/* 212 */     int inumero = 1;
/* 213 */     String s = "select max(0) from cont_impuesto  where  consecutivo_contrato=" + consecutivoContrato + " and numero_recibo='" + numeroRecibo + "'" + " and descripcion='" + descripcion + "'" + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 220 */       boolean rta = this.dat.parseSql(s);
/* 221 */       if (!rta) return 0; 
/* 222 */       this.rs = this.dat.getResultSet();
/* 223 */       if (this.rs.next()) {
/* 224 */         s = this.rs.getString(1);
/* 225 */         if (!this.rs.wasNull()) {
/* 226 */           inumero = Integer.parseInt(s) + 1;
/*     */         }
/*     */       } 
/* 229 */       return inumero;
/*     */     }
/* 231 */     catch (Exception e) {
/* 232 */       e.printStackTrace();
/* 233 */       Utilidades.writeError("ContImpuestoDAO:siguienteRegistro ", e);
/*     */       
/* 235 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eliminarRegistro(int consecutivoContrato, String numeroRecibo, String descripcion) {
/*     */     try {
/* 247 */       String s = "delete from cont_impuesto where  consecutivo_contrato=" + consecutivoContrato + "  and numero_recibo='" + numeroRecibo + "'" + "  and descripcion='" + descripcion + "'" + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 253 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 256 */     catch (Exception e) {
/* 257 */       e.printStackTrace();
/* 258 */       Utilidades.writeError("ContImpuestoDAO:eliminarRegistro ", e);
/*     */       
/* 260 */       return false;
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
/*     */   public boolean crearRegistro(int consecutivoContrato, String numeroRecibo, String fechaPago, int valorPago, String descripcion, String usuarioInsercion) {
/*     */     try {
/* 284 */       String s = "insert into cont_impuesto(consecutivo_contrato,numero_recibo,fecha_pago,valor_pago,descripcion,usuario_insercion,fecha_insercion) values (" + consecutivoContrato + "," + "'" + numeroRecibo + "'," + "" + Utilidades.formatoFecha2(fechaPago) + "," + "" + valorPago + "," + "'" + descripcion + "'," + "'" + usuarioInsercion + "'," + "" + Utilidades.getFechaBD() + "" + ")";
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
/* 301 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 304 */     catch (Exception e) {
/* 305 */       e.printStackTrace();
/* 306 */       Utilidades.writeError("%ContImpuestoDAO:crearRegistro ", e);
/*     */       
/* 308 */       return false;
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
/*     */   public boolean modificarRegistro(int consecutivoContrato, String numeroRecibo, String fechaPago, int valorPago, String descripcion, String usuarioModificacion) {
/*     */     try {
/* 325 */       String s = "update cont_impuesto set  fecha_pago=" + Utilidades.formatoFecha2(fechaPago) + "," + " valor_pago=" + valorPago + "," + " usuario_modificacion='" + usuarioModificacion + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "" + " where" + " consecutivo_contrato=" + consecutivoContrato + " and numero_recibo='" + numeroRecibo + "'" + " and descripcion='" + descripcion + "'" + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 335 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 338 */     catch (Exception e) {
/* 339 */       e.printStackTrace();
/* 340 */       Utilidades.writeError("ContImpuestoDAO:modificarRegistro ", e);
/*     */       
/* 342 */       return false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\ContImpuestoDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */