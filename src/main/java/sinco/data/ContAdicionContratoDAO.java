/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.ContAdicionContratoDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.ContAdicionContratoDAO;
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
/*     */ public class ContAdicionContratoDAO
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
/*  49 */       Utilidades.writeError("ContAdicionContratoDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContAdicionContratoDTO next() {
/*     */     try {
/*  60 */       if (this.rs.next()) {
/*  61 */         return leerRegistro();
/*     */       }
/*     */     }
/*  64 */     catch (Exception e) {
/*  65 */       e.printStackTrace();
/*  66 */       Utilidades.writeError("ContAdicionContratoDAO:next ", e);
/*     */     } 
/*  68 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContAdicionContratoDTO leerRegistro() {
/*     */     try {
/*  78 */       ContAdicionContratoDTO reg = new ContAdicionContratoDTO();
/*  79 */       reg.setConsecutivoContrato(this.rs.getInt("consecutivo_contrato"));
/*  80 */       reg.setConsecutivoAdicion(this.rs.getInt("consecutivo_adicion"));
/*  81 */       reg.setTipoAdicion(this.rs.getString("tipo_adicion"));
/*  82 */       reg.setValorAdicionado(this.rs.getDouble("valor_adicionado"));
/*  83 */       reg.setPlazoAdicionado(this.rs.getInt("plazo_adicionado"));
/*  84 */       reg.setUnidadPlazo(this.rs.getInt("unidad_plazo"));
/*  85 */       reg.setClausulas(this.rs.getString("clausulas"));
/*  86 */       reg.setJustificacion(this.rs.getString("justificacion"));
/*  87 */       reg.setNumCertificadoAdd(this.rs.getString("num_certificacion_add"));
/*  88 */       reg.setFechaCertificacionAdd(this.rs.getString("fecha_certificacion_add"));
/*  89 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  90 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  91 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  92 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  93 */       reg.setNumeroContrato(this.rs.getString("numero_contrato"));
/*  94 */       reg.setValorContrato(this.rs.getString("valor"));
/*  95 */       reg.setDescripcionTipoAdicion(this.rs.getString("descripcion_tipo_adicion"));
/*  96 */       reg.setDescripcionUnidadPlazo(this.rs.getString("descripcion_unidad_plazo"));
/*  97 */       reg.setServicioAdicionado(this.rs.getString("servicio_adicionado"));
/*  98 */       return reg;
/*     */     }
/* 100 */     catch (Exception e) {
/* 101 */       e.printStackTrace();
/* 102 */       Utilidades.writeError("ContAdicionContratoDAO:leerRegistro ", e);
/*     */       
/* 104 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<ContAdicionContratoDTO> cargarTodos(int consecutivoContrato) {
/* 114 */     Collection<ContAdicionContratoDTO> resultados = new ArrayList<ContAdicionContratoDTO>();
/*     */     try {
/* 116 */       String s = "select  t.consecutivo_adicion,t.consecutivo_contrato,t.tipo_adicion,t.valor_adicionado,t.plazo_adicionado,t.unidad_plazo,t.clausulas,t.justificacion,t.num_certificacion_add,t.fecha_certificacion_add,c.numero_contrato,c.valor,tac.descripcion as descripcion_tipo_adicion,tp.descripcion as descripcion_unidad_plazo,t.servicio_adicionado,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion  from cont_adicion_contrato t  left join sis_multivalores tac on (tac.valor = t.tipo_adicion and tac.tabla='tipo_adicion_contrato')  left join sis_multivalores tp on (tp.valor = trim(to_char(t.unidad_plazo,'99999999999')) and tp.tabla='tipo_plazo'),  cont_contrato c where c.consecutivo_contrato=t.consecutivo_contrato ";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 141 */       if (consecutivoContrato > 0) {
/* 142 */         s = s + " and t.consecutivo_contrato=" + consecutivoContrato;
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
/* 156 */       Utilidades.writeError("ContAdicionContratoDAO:cargarTodos ", e);
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
/*     */   public ContAdicionContratoDTO cargarRegistro(int consecutivoContrato, int consecutivoAdicion) {
/*     */     try {
/* 169 */       String s = "select  t.consecutivo_adicion,t.consecutivo_contrato,t.tipo_adicion,t.valor_adicionado,t.plazo_adicionado,t.unidad_plazo,t.clausulas,t.justificacion,t.num_certificacion_add,t.fecha_certificacion_add,c.numero_contrato,c.valor,tac.descripcion as descripcion_tipo_adicion,tp.descripcion as descripcion_unidad_plazo,t.servicio_adicionado,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion  from cont_adicion_contrato t  left join sis_multivalores tac on (tac.valor = t.tipo_adicion and tac.tabla='tipo_adicion_contrato')  left join sis_multivalores tp on (tp.valor = to_char(t.unidad_plazo,'99999999999') and tp.tabla='tipo_plazo'),  cont_contrato c where c.consecutivo_contrato=t.consecutivo_contrato  and t.consecutivo_contrato=" + consecutivoContrato + " and t.consecutivo_adicion=" + consecutivoAdicion;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 196 */       boolean rtaDB = this.dat.parseSql(s);
/* 197 */       if (!rtaDB) {
/* 198 */         return null;
/*     */       }
/* 200 */       this.rs = this.dat.getResultSet();
/* 201 */       if (this.rs.next()) {
/* 202 */         return leerRegistro();
/*     */       }
/*     */     }
/* 205 */     catch (Exception e) {
/* 206 */       e.printStackTrace();
/* 207 */       Utilidades.writeError("ContAdicionContratoDAO:cargarContAdicionContrato", e);
/*     */     } 
/* 209 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int siguienteRegistro(int consecutivoContrato) {
/* 218 */     int inumero = 1;
/* 219 */     String s = "select max(consecutivo_adicion) from cont_adicion_contrato where consecutivo_contrato=" + consecutivoContrato;
/*     */     
/*     */     try {
/* 222 */       boolean rta = this.dat.parseSql(s);
/* 223 */       if (!rta) return 0; 
/* 224 */       this.rs = this.dat.getResultSet();
/* 225 */       if (this.rs.next()) {
/* 226 */         s = this.rs.getString(1);
/* 227 */         if (!this.rs.wasNull()) {
/* 228 */           inumero = Integer.parseInt(s) + 1;
/*     */         }
/*     */       } 
/* 231 */       return inumero;
/*     */     }
/* 233 */     catch (Exception e) {
/* 234 */       e.printStackTrace();
/* 235 */       Utilidades.writeError("ContAdicionContratoDAO:siguienteRegistro ", e);
/*     */       
/* 237 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eliminarRegistro(int consecutivoContrato, int consecutivoAdicion) {
/*     */     try {
/* 247 */       String s = "delete from cont_adicion_contrato where  consecutivo_adicion=" + consecutivoAdicion + " and consecutivo_contrato=" + consecutivoContrato + "";
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 252 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 255 */     catch (Exception e) {
/* 256 */       e.printStackTrace();
/* 257 */       Utilidades.writeError("ContAdicionContratoDAO:eliminarRegistro ", e);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int crearRegistro(int consecutivoContrato, String tipoAdicion, double valorAdicionado, int plazoAdicionado, int unidadPlazo, String clausulas, String justificacion, String numCertificadoAdd, String fechaCertificacionAdd, String valorAdicionadoTexto, String servicioAdicionado, String usuarioInsercion) {
/* 281 */     int elSiguiente = siguienteRegistro(consecutivoContrato);
/* 282 */     if (elSiguiente == 0) {
/* 283 */       return elSiguiente;
/*     */     }
/*     */     
/*     */     try {
/* 287 */       String s = "insert into cont_adicion_contrato(consecutivo_adicion,consecutivo_contrato,tipo_adicion,valor_adicionado,plazo_adicionado,unidad_plazo,clausulas,justificacion,num_certificacion_add,fecha_certificacion_add,valor_adicionado_texto,servicio_adicionado,usuario_insercion,fecha_insercion) values (" + elSiguiente + "," + "" + consecutivoContrato + "," + "'" + tipoAdicion + "'," + "" + valorAdicionado + "," + "" + plazoAdicionado + "," + "" + unidadPlazo + "," + "'" + clausulas + "'," + "'" + justificacion + "'," + "'" + numCertificadoAdd + "'," + "" + Utilidades.formatoFecha2(fechaCertificacionAdd) + "," + "'" + valorAdicionadoTexto + "'," + "'" + servicioAdicionado + "'," + "'" + usuarioInsercion + "'," + "" + Utilidades.getFechaBD() + "" + ")";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 318 */       this.dat.executeUpdate(s);
/* 319 */       return elSiguiente;
/*     */     }
/* 321 */     catch (Exception e) {
/* 322 */       e.printStackTrace();
/* 323 */       Utilidades.writeError("%ContAdicionContratoDAO:crearRegistro ", e);
/*     */       
/* 325 */       return elSiguiente;
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
/*     */   public boolean modificarRegistro(int consecutivoAdicion, int consecutivoContrato, String tipoAdicion, double valorAdicionado, int plazoAdicionado, int unidadPlazo, String clausulas, String justificacion, String numCertificadoAdd, String fechaCertificacionAdd, String valorAdicionadoTexto, String servicioAdicionado, String usuarioModificacion) {
/*     */     try {
/* 349 */       String s = "update cont_adicion_contrato set  tipo_adicion=" + tipoAdicion + "," + " valor_adicionado=" + valorAdicionado + "," + " plazo_adicionado=" + plazoAdicionado + "," + " unidad_plazo=" + unidadPlazo + "," + " num_certificacion_add='" + numCertificadoAdd + "'," + " fecha_certificacion_add=" + Utilidades.formatoFecha2(fechaCertificacionAdd) + "," + " clausulas='" + clausulas + "'," + " justificacion='" + justificacion + "'," + " valor_adicionado_texto='" + valorAdicionadoTexto + "'," + " servicio_adicionado='" + servicioAdicionado + "'," + " usuario_modificacion='" + usuarioModificacion + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "" + " where" + " consecutivo_adicion=" + consecutivoAdicion + " and consecutivo_contrato=" + consecutivoContrato + "" + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 366 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 369 */     catch (Exception e) {
/* 370 */       e.printStackTrace();
/* 371 */       Utilidades.writeError("ContAdicionContratoDAO:modificarRegistro ", e);
/*     */       
/* 373 */       return false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\ContAdicionContratoDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */