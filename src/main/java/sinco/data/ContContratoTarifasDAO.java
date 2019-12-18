/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.ContContratoTarifasDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.ContContratoTarifasDAO;
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
/*     */ public class ContContratoTarifasDAO
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
/*  49 */       Utilidades.writeError("ContContratoTarifasDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContContratoTarifasDTO next() {
/*     */     try {
/*  60 */       if (this.rs.next()) {
/*  61 */         return leerRegistro();
/*     */       }
/*     */     }
/*  64 */     catch (Exception e) {
/*  65 */       e.printStackTrace();
/*  66 */       Utilidades.writeError("ContContratoTarifasDAO:next ", e);
/*     */     } 
/*  68 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContContratoTarifasDTO leerRegistro() {
/*     */     try {
/*  78 */       ContContratoTarifasDTO reg = new ContContratoTarifasDTO();
/*     */       
/*  80 */       reg.setConsecutivoContrato(this.rs.getInt("consecutivo_contrato"));
/*  81 */       reg.setCodigoProcedimiento(this.rs.getString("codigo_procedimiento"));
/*  82 */       reg.setValor(this.rs.getDouble("valor"));
/*  83 */       reg.setNombreProcedimiento(this.rs.getString("nombre_procedimiento"));
/*  84 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  85 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  86 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  87 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  88 */       return reg;
/*     */     }
/*  90 */     catch (Exception e) {
/*  91 */       e.printStackTrace();
/*  92 */       Utilidades.writeError("ContContratoTarifasDAO:leerRegistro ", e);
/*     */       
/*  94 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<ContContratoTarifasDTO> cargarTodos(int consecutivoContrato, String codigoProcedimiento) {
/* 105 */     Collection<ContContratoTarifasDTO> resultados = new ArrayList<ContContratoTarifasDTO>();
/*     */     try {
/* 107 */       String s = "select  t.consecutivo_contrato,t.codigo_procedimiento,p.nombre as nombre_procedimiento,t.valor,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion  from cont_contrato_tarifas t left join siau_procedimiento p on (t.codigo_procedimiento = p.codigo_procedimiento)  where 1=1";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 119 */       if (consecutivoContrato > 0) {
/* 120 */         s = s + " and t.consecutivo_contrato=" + consecutivoContrato;
/*     */       }
/* 122 */       if (codigoProcedimiento.length() > 0) {
/* 123 */         s = s + " and upper(t.codigo_procedimiento) like upper('%" + codigoProcedimiento + "%')";
/*     */       }
/* 125 */       s = s + " order by 1";
/* 126 */       boolean rtaDB = this.dat.parseSql(s);
/* 127 */       if (!rtaDB) {
/* 128 */         return resultados;
/*     */       }
/* 130 */       this.rs = this.dat.getResultSet();
/* 131 */       while (this.rs.next()) {
/* 132 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 135 */     catch (Exception e) {
/* 136 */       e.printStackTrace();
/* 137 */       Utilidades.writeError("ContContratoTarifasDAO:cargarTodos ", e);
/*     */     } 
/* 139 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContContratoTarifasDTO cargarRegistro(int consecutivoContrato, String codigoProcedimiento) {
/*     */     try {
/* 150 */       String s = "select  t.consecutivo_contrato,t.codigo_procedimiento,p.nombre as nombre_procedimiento,t.valor,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion  from cont_contrato_tarifas t left join siau_procedimiento p on (t.codigo_procedimiento = p.codigo_procedimiento)  where  t.consecutivo_contrato=" + consecutivoContrato + " and t.codigo_procedimiento='" + codigoProcedimiento + "'" + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 165 */       boolean rtaDB = this.dat.parseSql(s);
/* 166 */       if (!rtaDB) {
/* 167 */         return null;
/*     */       }
/* 169 */       this.rs = this.dat.getResultSet();
/* 170 */       if (this.rs.next()) {
/* 171 */         return leerRegistro();
/*     */       }
/*     */     }
/* 174 */     catch (Exception e) {
/* 175 */       e.printStackTrace();
/* 176 */       Utilidades.writeError("ContContratoTarifasDAO:cargarContContratoTarifas", e);
/*     */     } 
/* 178 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int siguienteRegistro(int consecutivoContrato, String codigoProcedimiento) {
/* 188 */     int inumero = 1;
/* 189 */     String s = "select max(0) from cont_contrato_tarifas  where  consecutivo_contrato=" + consecutivoContrato + " and codigo_procedimiento='" + codigoProcedimiento + "'" + "";
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 195 */       boolean rta = this.dat.parseSql(s);
/* 196 */       if (!rta) return 0; 
/* 197 */       this.rs = this.dat.getResultSet();
/* 198 */       if (this.rs.next()) {
/* 199 */         s = this.rs.getString(1);
/* 200 */         if (!this.rs.wasNull()) {
/* 201 */           inumero = Integer.parseInt(s) + 1;
/*     */         }
/*     */       } 
/* 204 */       return inumero;
/*     */     }
/* 206 */     catch (Exception e) {
/* 207 */       e.printStackTrace();
/* 208 */       Utilidades.writeError("ContContratoTarifasDAO:siguienteRegistro ", e);
/*     */       
/* 210 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eliminarRegistro(int consecutivoContrato, String codigoProcedimiento) {
/*     */     try {
/* 221 */       String s = "delete from cont_contrato_tarifas where  consecutivo_contrato=" + consecutivoContrato + "  and codigo_procedimiento='" + codigoProcedimiento + "'" + "";
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 226 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 229 */     catch (Exception e) {
/* 230 */       e.printStackTrace();
/* 231 */       Utilidades.writeError("ContContratoTarifasDAO:eliminarRegistro ", e);
/*     */       
/* 233 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eliminarDeContrato(int consecutivoContrato) {
/*     */     try {
/* 242 */       String s = "delete from cont_contrato_tarifas where  consecutivo_contrato=" + consecutivoContrato + "";
/*     */ 
/*     */ 
/*     */       
/* 246 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 249 */     catch (Exception e) {
/* 250 */       e.printStackTrace();
/* 251 */       Utilidades.writeError("ContContratoTarifasDAO:eliminarDeContrato ", e);
/*     */       
/* 253 */       return false;
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
/*     */   public boolean crearRegistro(int consecutivoContrato, String codigoProcedimiento, double valor, String usuarioInsercion) {
/*     */     try {
/* 274 */       String s = "insert into cont_contrato_tarifas(consecutivo_contrato,codigo_procedimiento,valor,fecha_insercion,usuario_insercion) values (" + consecutivoContrato + "," + "'" + codigoProcedimiento + "'," + "" + valor + "," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 287 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 290 */     catch (Exception e) {
/* 291 */       e.printStackTrace();
/* 292 */       Utilidades.writeError("%ContContratoTarifasDAO:crearRegistro ", e);
/*     */       
/* 294 */       return false;
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
/*     */   public boolean insertarRegistro(int consecutivoContrato, String campos, String valores, String usuarioInsercion) {
/*     */     try {
/* 311 */       String s = "insert into cont_contrato_tarifas(consecutivo_contrato," + campos + "," + "fecha_insercion," + "usuario_insercion" + ") values (" + "" + consecutivoContrato + "," + "" + valores + "," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 322 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 325 */     catch (Exception e) {
/* 326 */       e.printStackTrace();
/* 327 */       Utilidades.writeError("%ContContratoTarifasDAO:crearRegistroEnsamblado ", e);
/*     */       
/* 329 */       return false;
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
/*     */   public boolean modificarRegistro(int consecutivoContrato, String codigoProcedimiento, double valor, String usuarioModificacion) {
/*     */     try {
/* 344 */       String s = "update cont_contrato_tarifas set  valor=" + valor + "," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " consecutivo_contrato=" + consecutivoContrato + " and codigo_procedimiento='" + codigoProcedimiento + "'" + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 352 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 355 */     catch (Exception e) {
/* 356 */       e.printStackTrace();
/* 357 */       Utilidades.writeError("ContContratoTarifasDAO:modificarRegistro ", e);
/*     */       
/* 359 */       return false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\ContContratoTarifasDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */