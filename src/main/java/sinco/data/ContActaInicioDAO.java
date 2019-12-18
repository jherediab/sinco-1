/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.ContActaInicioDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.ContActaInicioDAO;
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
/*     */ public class ContActaInicioDAO
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
/*  49 */       Utilidades.writeError("ContActaInicioDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContActaInicioDTO next() {
/*     */     try {
/*  60 */       if (this.rs.next()) {
/*  61 */         return leerRegistro();
/*     */       }
/*     */     }
/*  64 */     catch (Exception e) {
/*  65 */       e.printStackTrace();
/*  66 */       Utilidades.writeError("ContActaInicioDAO:next ", e);
/*     */     } 
/*  68 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContActaInicioDTO leerRegistro() {
/*     */     try {
/*  78 */       ContActaInicioDTO reg = new ContActaInicioDTO();
/*     */       
/*  80 */       reg.setConsecutivoContrato(this.rs.getInt("consecutivo_contrato"));
/*  81 */       reg.setNumeroContrato(this.rs.getString("numero_contrato"));
/*  82 */       reg.setFechaActa(this.rs.getString("fecha_acta"));
/*  83 */       reg.setAnexoPasadojud(this.rs.getString("anexo_pasadojud"));
/*  84 */       reg.setAnexoRut(this.rs.getString("anexo_rut"));
/*  85 */       reg.setAnexoProcuraduria(this.rs.getString("anexo_procuraduria"));
/*  86 */       reg.setAnexoContraloria(this.rs.getString("anexo_contraloria"));
/*  87 */       reg.setAnexoContrato(this.rs.getString("anexo_contrato"));
/*  88 */       reg.setAnexoPolizas(this.rs.getString("anexo_polizas"));
/*  89 */       reg.setAnexoImpuestos(this.rs.getString("anexo_impuestos"));
/*  90 */       reg.setAnexoCedula(this.rs.getString("anexo_cedula"));
/*  91 */       reg.setAnexoRegHab(this.rs.getString("anexo_reg_hab"));
/*  92 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  93 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  94 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  95 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  96 */       return reg;
/*     */     }
/*  98 */     catch (Exception e) {
/*  99 */       e.printStackTrace();
/* 100 */       Utilidades.writeError("ContActaInicioDAO:leerRegistro ", e);
/*     */       
/* 102 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<ContActaInicioDTO> cargarTodos(int consecutivoContrato) {
/* 112 */     Collection<ContActaInicioDTO> resultados = new ArrayList<ContActaInicioDTO>();
/*     */     try {
/* 114 */       String s = "select  t.consecutivo_contrato,c.numero_contrato,t.fecha_acta,t.anexo_pasadojud,t.anexo_rut,t.anexo_procuraduria,t.anexo_contraloria,t.anexo_contrato,t.anexo_polizas,t.anexo_impuestos,t.anexo_cedula,t.anexo_reg_hab,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion  from cont_acta_inicio t, cont_contrato c where t.consecutivo_contrato=c.consecutivo_contrato";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 133 */       if (consecutivoContrato > 0) {
/* 134 */         s = s + " and t.consecutivo_contrato=" + consecutivoContrato;
/*     */       }
/* 136 */       s = s + " order by 1";
/* 137 */       boolean rtaDB = this.dat.parseSql(s);
/* 138 */       if (!rtaDB) {
/* 139 */         return resultados;
/*     */       }
/* 141 */       this.rs = this.dat.getResultSet();
/* 142 */       while (this.rs.next()) {
/* 143 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 146 */     catch (Exception e) {
/* 147 */       e.printStackTrace();
/* 148 */       Utilidades.writeError("ContActaInicioDAO:cargarTodos ", e);
/*     */     } 
/* 150 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContActaInicioDTO cargarRegistro(int consecutivoContrato) {
/*     */     try {
/* 159 */       String s = "select  t.consecutivo_contrato,c.numero_contrato,t.fecha_acta,t.anexo_pasadojud,t.anexo_rut,t.anexo_procuraduria,t.anexo_contraloria,t.anexo_contrato,t.anexo_polizas,t.anexo_impuestos,t.anexo_cedula,t.anexo_reg_hab,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion  from cont_acta_inicio t, cont_contrato c where t.consecutivo_contrato=c.consecutivo_contrato and t.consecutivo_contrato=" + consecutivoContrato + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 180 */       boolean rtaDB = this.dat.parseSql(s);
/* 181 */       if (!rtaDB) {
/* 182 */         return null;
/*     */       }
/* 184 */       this.rs = this.dat.getResultSet();
/* 185 */       if (this.rs.next()) {
/* 186 */         return leerRegistro();
/*     */       }
/*     */     }
/* 189 */     catch (Exception e) {
/* 190 */       e.printStackTrace();
/* 191 */       Utilidades.writeError("ContActaInicioDAO:cargarContActaInicio", e);
/*     */     } 
/* 193 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int siguienteRegistro(int consecutivoContrato) {
/* 202 */     int inumero = 1;
/* 203 */     String s = "select max(0) from cont_acta_inicio  where  consecutivo_contrato=" + consecutivoContrato + "";
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 208 */       boolean rta = this.dat.parseSql(s);
/* 209 */       if (!rta) return 0; 
/* 210 */       this.rs = this.dat.getResultSet();
/* 211 */       if (this.rs.next()) {
/* 212 */         s = this.rs.getString(1);
/* 213 */         if (!this.rs.wasNull()) {
/* 214 */           inumero = Integer.parseInt(s) + 1;
/*     */         }
/*     */       } 
/* 217 */       return inumero;
/*     */     }
/* 219 */     catch (Exception e) {
/* 220 */       e.printStackTrace();
/* 221 */       Utilidades.writeError("ContActaInicioDAO:siguienteRegistro ", e);
/*     */       
/* 223 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eliminarRegistro(int consecutivoContrato) {
/*     */     try {
/* 233 */       String s = "delete from cont_acta_inicio where  consecutivo_contrato=" + consecutivoContrato + "";
/*     */ 
/*     */ 
/*     */       
/* 237 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 240 */     catch (Exception e) {
/* 241 */       e.printStackTrace();
/* 242 */       Utilidades.writeError("ContActaInicioDAO:eliminarRegistro ", e);
/*     */       
/* 244 */       return false;
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
/*     */   public boolean crearRegistro(int consecutivoContrato, String fechaActa, String anexoPasadojud, String anexoRut, String anexoProcuraduria, String anexoContraloria, String anexoContrato, String anexoPolizas, String anexoImpuestos, String anexoCedula, String anexoRegHab, String usuarioInsercion) {
/*     */     try {
/* 272 */       String s = "insert into cont_acta_inicio(consecutivo_contrato,fecha_acta,anexo_pasadojud,anexo_rut,anexo_procuraduria,anexo_contraloria,anexo_contrato,anexo_polizas,anexo_impuestos,anexo_cedula,anexo_reg_hab,usuario_insercion,fecha_insercion) values (" + consecutivoContrato + "," + "" + Utilidades.formatoFecha2(fechaActa) + "," + "'" + anexoPasadojud + "'," + "'" + anexoRut + "'," + "'" + anexoProcuraduria + "'," + "'" + anexoContraloria + "'," + "'" + anexoContrato + "'," + "'" + anexoPolizas + "'," + "'" + anexoImpuestos + "'," + "'" + anexoCedula + "'," + "'" + anexoRegHab + "'," + "'" + usuarioInsercion + "'," + "" + Utilidades.getFechaBD() + "" + ")";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/* 306 */       Utilidades.writeError("%ContActaInicioDAO:crearRegistro ", e);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean modificarRegistro(int consecutivoContrato, String fechaActa, String anexoPasadojud, String anexoRut, String anexoProcuraduria, String anexoContraloria, String anexoContrato, String anexoPolizas, String anexoImpuestos, String anexoCedula, String anexoRegHab, String usuarioModificacion) {
/*     */     try {
/* 331 */       String s = "update cont_acta_inicio set  fecha_acta=" + Utilidades.formatoFecha2(fechaActa) + "," + " anexo_pasadojud='" + anexoPasadojud + "'," + " anexo_rut='" + anexoRut + "'," + " anexo_procuraduria='" + anexoProcuraduria + "'," + " anexo_contraloria='" + anexoContraloria + "'," + " anexo_contrato='" + anexoContrato + "'," + " anexo_polizas='" + anexoPolizas + "'," + " anexo_impuestos='" + anexoImpuestos + "'," + " anexo_cedula='" + anexoCedula + "'," + " anexo_reg_hab='" + anexoRegHab + "'," + " usuario_modificacion='" + usuarioModificacion + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "" + " where" + " consecutivo_contrato=" + consecutivoContrato + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 347 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 350 */     catch (Exception e) {
/* 351 */       e.printStackTrace();
/* 352 */       Utilidades.writeError("ContActaInicioDAO:modificarRegistro ", e);
/*     */       
/* 354 */       return false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\ContActaInicioDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */