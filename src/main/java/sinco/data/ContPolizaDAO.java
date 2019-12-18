/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.ContPolizaDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.ContPolizaDAO;
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
/*     */ public class ContPolizaDAO
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
/*  49 */       Utilidades.writeError("ContPolizaDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContPolizaDTO next() {
/*     */     try {
/*  60 */       if (this.rs.next()) {
/*  61 */         return leerRegistro();
/*     */       }
/*     */     }
/*  64 */     catch (Exception e) {
/*  65 */       e.printStackTrace();
/*  66 */       Utilidades.writeError("ContPolizaDAO:next ", e);
/*     */     } 
/*  68 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContPolizaDTO leerRegistro() {
/*     */     try {
/*  78 */       ContPolizaDTO reg = new ContPolizaDTO();
/*     */       
/*  80 */       reg.setConsecutivoContrato(this.rs.getInt("consecutivo_contrato"));
/*  81 */       reg.setNumeroContrato(this.rs.getString("numero_contrato"));
/*  82 */       reg.setNumeroPoliza(this.rs.getString("numero_poliza"));
/*  83 */       reg.setTipoPoliza(this.rs.getString("tipo_poliza"));
/*  84 */       reg.setDescripcionTipoPoliza(this.rs.getString("descripcion"));
/*  85 */       reg.setEntidadExpide(this.rs.getString("entidad_expide"));
/*  86 */       reg.setFechaExpedicion(this.rs.getString("fecha_expedicion"));
/*  87 */       reg.setFechaAprobacion(this.rs.getString("fecha_aprobacion"));
/*  88 */       reg.setFechaInicio(this.rs.getString("fecha_inicio"));
/*  89 */       reg.setFechaFinal(this.rs.getString("fecha_final"));
/*  90 */       reg.setFechaInicioCont(this.rs.getString("fecha_inicio_contrato"));
/*  91 */       reg.setFechaFinalCont(this.rs.getString("fecha_final_contrato"));
/*  92 */       reg.setValor(this.rs.getDouble("valor"));
/*  93 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  94 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  95 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  96 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  97 */       return reg;
/*     */     }
/*  99 */     catch (Exception e) {
/* 100 */       e.printStackTrace();
/* 101 */       Utilidades.writeError("ContPolizaDAO:leerRegistro ", e);
/*     */       
/* 103 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<ContPolizaDTO> cargarTodos(int consecutivoContrato) {
/* 113 */     Collection<ContPolizaDTO> resultados = new ArrayList<ContPolizaDTO>();
/*     */     try {
/* 115 */       String s = "select  t.consecutivo_contrato,t.numero_poliza,c.numero_contrato,t.tipo_poliza,s.descripcion,t.entidad_expide,t.fecha_expedicion,t.fecha_aprobacion,t.fecha_inicio,t.fecha_final,c.fecha_inicio as fecha_inicio_contrato,c.fecha_final as fecha_final_contrato,t.valor,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion  from cont_poliza t, sis_multivalores s, cont_contrato c where s.tabla='tipo_poliza' and s.valor=t.tipo_poliza and t.consecutivo_contrato=c.consecutivo_contrato";
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
/* 137 */       if (consecutivoContrato > 0) {
/* 138 */         s = s + " and c.consecutivo_contrato = " + consecutivoContrato;
/*     */       }
/* 140 */       s = s + " order by 1";
/* 141 */       boolean rtaDB = this.dat.parseSql(s);
/* 142 */       if (!rtaDB) {
/* 143 */         return resultados;
/*     */       }
/* 145 */       this.rs = this.dat.getResultSet();
/* 146 */       while (this.rs.next()) {
/* 147 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 150 */     catch (Exception e) {
/* 151 */       e.printStackTrace();
/* 152 */       Utilidades.writeError("ContPolizaDAO:cargarTodos ", e);
/*     */     } 
/* 154 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContPolizaDTO cargarRegistro(int consecutivoContrato, String numeroPoliza, String tipoPoliza) {
/*     */     try {
/* 166 */       String s = "select  t.consecutivo_contrato,t.numero_poliza,c.numero_contrato,t.tipo_poliza,s.descripcion,t.entidad_expide,t.fecha_expedicion,t.fecha_aprobacion,t.fecha_inicio,t.fecha_final,c.fecha_inicio as fecha_inicio_contrato,c.fecha_final as fecha_final_contrato,t.valor,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion  from cont_poliza t, sis_multivalores s, cont_contrato c where s.tabla='tipo_poliza' and s.valor=t.tipo_poliza and t.consecutivo_contrato=c.consecutivo_contrato and t.numero_poliza='" + numeroPoliza + "'" + " and t.tipo_poliza='" + tipoPoliza + "'" + " and t.consecutivo_contrato=" + consecutivoContrato + "";
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
/*     */ 
/*     */ 
/*     */       
/* 192 */       boolean rtaDB = this.dat.parseSql(s);
/* 193 */       if (!rtaDB) {
/* 194 */         return null;
/*     */       }
/* 196 */       this.rs = this.dat.getResultSet();
/* 197 */       if (this.rs.next()) {
/* 198 */         return leerRegistro();
/*     */       }
/*     */     }
/* 201 */     catch (Exception e) {
/* 202 */       e.printStackTrace();
/* 203 */       Utilidades.writeError("ContPolizaDAO:cargarContPoliza", e);
/*     */     } 
/* 205 */     return null;
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
/*     */   public boolean eliminarRegistro(int consecutivoContrato, String numeroPoliza, String tipoPoliza) {
/*     */     try {
/* 220 */       String s = "delete from cont_poliza where  numero_poliza='" + numeroPoliza + "'" + "  and tipo_poliza='" + tipoPoliza + "'" + " and consecutivo_contrato=" + consecutivoContrato + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 226 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 229 */     catch (Exception e) {
/* 230 */       e.printStackTrace();
/* 231 */       Utilidades.writeError("ContPolizaDAO:eliminarRegistro ", e);
/*     */       
/* 233 */       return false;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean crearRegistro(int consecutivoContrato, String numeroPoliza, String tipoPoliza, String entidadExpide, String fechaExpedicion, String fechaAprobacion, String fechaInicio, String fechaFinal, double valor, String usuarioInsercion) {
/*     */     try {
/* 261 */       String s = "insert into cont_poliza(consecutivo_contrato,numero_poliza,tipo_poliza,entidad_expide,fecha_expedicion,fecha_aprobacion,fecha_inicio,fecha_final,valor,usuario_insercion,fecha_insercion) values (" + consecutivoContrato + "," + "'" + numeroPoliza + "'," + "'" + tipoPoliza + "'," + "'" + entidadExpide + "'," + "" + Utilidades.formatoFecha2(fechaExpedicion) + "," + "" + Utilidades.formatoFecha2(fechaAprobacion) + "," + "" + Utilidades.formatoFecha2(fechaInicio) + "," + "" + Utilidades.formatoFecha2(fechaFinal) + "," + "" + valor + "," + "'" + usuarioInsercion + "'," + "" + Utilidades.getFechaBD() + "" + ")";
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
/*     */ 
/*     */       
/* 286 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 289 */     catch (Exception e) {
/* 290 */       e.printStackTrace();
/* 291 */       Utilidades.writeError("%ContPolizaDAO:crearRegistro ", e);
/*     */       
/* 293 */       return false;
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
/*     */   public boolean modificarRegistro(int consecutivoContrato, String numeroPoliza, String tipoPoliza, String entidadExpide, String fechaExpedicion, String fechaAprobacion, String fechaInicio, String fechaFinal, double valor, String usuarioModificacion) {
/*     */     try {
/* 314 */       String s = "update cont_poliza set  consecutivo_contrato=" + consecutivoContrato + "," + " entidad_expide='" + entidadExpide + "'," + " fecha_expedicion=" + Utilidades.formatoFecha2(fechaExpedicion) + "," + " fecha_aprobacion=" + Utilidades.formatoFecha2(fechaAprobacion) + "," + " fecha_inicio=" + Utilidades.formatoFecha2(fechaInicio) + "," + " fecha_final=" + Utilidades.formatoFecha2(fechaFinal) + "," + " valor=" + valor + "," + " usuario_modificacion='" + usuarioModificacion + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "" + " where" + " numero_poliza='" + numeroPoliza + "'" + " and tipo_poliza='" + tipoPoliza + "'" + "";
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
/* 328 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 331 */     catch (Exception e) {
/* 332 */       e.printStackTrace();
/* 333 */       Utilidades.writeError("ContPolizaDAO:modificarRegistro ", e);
/*     */       
/* 335 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double cargarPorcentajePoliza(int numeroEstudio, String poliza) {
/* 345 */    double porcentaje = 0.0D;
/*     */     try {
/* 347 */       String s = "select CASE WHEN length(substr(descripcion_item, (strpos(descripcion_item,'%')-2),2))=0 THEN '0' ELSE substr(descripcion_item, (strpos(descripcion_item,'%')-2),2) END as porcentaje from cont_estudio_previo_items where tipo_item='poliza' and numero_estudio=" + numeroEstudio + "and upper(descripcion_item) like upper('%" + poliza + "%')" + "order by porcentaje desc limit 1";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 356 */       boolean rtaDB = this.dat.parseSql(s);
/* 357 */       if (!rtaDB) {
/* 358 */         return porcentaje;
/*     */       }
/* 360 */       this.rs = this.dat.getResultSet();
/* 361 */       if (this.rs.next()) {
/* 362 */         return this.rs.getDouble("porcentaje");
/*     */       
/*     */       }
/*     */     }
/* 366 */     catch (Exception e) {
/* 367 */       e.printStackTrace();
/* 368 */       Utilidades.writeError("ContPolizaDAO:cargarPorcentajePoliza", e);
/*     */     } 
/* 370 */     return porcentaje;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<ContPolizaDTO> cargarPorContratoEstudio(int consecutivoContrato) {
/* 380 */     Collection<ContPolizaDTO> resultados = new ArrayList<ContPolizaDTO>();
/*     */     try {
/* 382 */       String s = "select i.descripcion_item as tipo_poliza,  coalesce(to_char(p.fecha_aprobacion,'dd/mm/yyyy'),'NO REGISTRA') as fecha_aprobacion,  null  from cont_estudio_previo_items i  left join cont_contrato c on (c.numero_estudio=i.numero_estudio)  left join cont_poliza p on (p.consecutivo_contrato=c.consecutivo_contrato and( upper(i.descripcion_item) like '%'||upper(p.tipo_poliza)||'%'))  where i.tipo_item='poliza'  and c.consecutivo_contrato= " + consecutivoContrato + " UNION " + " select * from (select m.descripcion, to_char(p.fecha_aprobacion,'dd/mm/yyyy'), i.numero_estudio " + " from  cont_poliza p " + " left join cont_contrato c on (c.consecutivo_contrato=p.consecutivo_contrato) " + " left join cont_estudio_previo_items i on (c.numero_estudio=i.numero_estudio and( upper(i.descripcion_item) " + " like '%'||upper(p.tipo_poliza)||'%') and i.tipo_item='poliza') " + " left join sis_multivalores m ON (m.valor=p.tipo_poliza and m.tabla='tipo_poliza') " + " where c.consecutivo_contrato = " + consecutivoContrato + " ) as foo " + " where numero_estudio is null ";
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
/* 400 */       boolean rtaDB = this.dat.parseSql(s);
/* 401 */       if (!rtaDB) {
/* 402 */         return resultados;
/*     */       }
/* 404 */       this.rs = this.dat.getResultSet();
/* 405 */       while (this.rs.next()) {
/* 406 */         resultados.add(leerRegistroResumen());
/*     */       }
/*     */     }
/* 409 */     catch (Exception e) {
/* 410 */       e.printStackTrace();
/* 411 */       Utilidades.writeError("ContPolizaDAO:cargarTodos ", e);
/*     */     } 
/* 413 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContPolizaDTO leerRegistroResumen() {
/*     */     try {
/* 422 */       ContPolizaDTO reg = new ContPolizaDTO();
/* 423 */       reg.setTipoPoliza(this.rs.getString("tipo_poliza"));
/* 424 */       reg.setFechaAprobacion(this.rs.getString("fecha_aprobacion"));
/* 425 */       return reg;
/*     */     }
/* 427 */     catch (Exception e) {
/* 428 */       e.printStackTrace();
/* 429 */       Utilidades.writeError("ContPolizaDAO:leerRegistro ", e);
/*     */       
/* 431 */       return null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\ContPolizaDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */