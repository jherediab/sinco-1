/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.ContContratoServicioDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.ContContratoServicioDAO;
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
/*     */ public class ContContratoServicioDAO
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
/*  49 */       Utilidades.writeError("ContContratoServicioDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContContratoServicioDTO next() {
/*     */     try {
/*  60 */       if (this.rs.next()) {
/*  61 */         return leerRegistro();
/*     */       }
/*     */     }
/*  64 */     catch (Exception e) {
/*  65 */       e.printStackTrace();
/*  66 */       Utilidades.writeError("ContContratoServicioDAO:next ", e);
/*     */     } 
/*  68 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContContratoServicioDTO leerRegistro() {
/*     */     try {
/*  78 */       ContContratoServicioDTO reg = new ContContratoServicioDTO();
/*     */       
/*  80 */       reg.setConsecutivoContrato(this.rs.getInt("consecutivo_contrato"));
/*  81 */       reg.setCodigoServicio(this.rs.getInt("codigo_servicio"));
/*  82 */       reg.setTipo(this.rs.getString("tipo"));
/*  83 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  84 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  85 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  86 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  87 */       reg.setNombreServicio(this.rs.getString("nombre_servicio"));
/*  88 */       return reg;
/*     */     }
/*  90 */     catch (Exception e) {
/*  91 */       e.printStackTrace();
/*  92 */       Utilidades.writeError("ContContratoServicioDAO:leerRegistro ", e);
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
/*     */   
/*     */   public Collection<ContContratoServicioDTO> cargarTodos(int consecutivoContrato, int codigoServicio, String tipo) {
/* 106 */     Collection<ContContratoServicioDTO> resultados = new ArrayList<ContContratoServicioDTO>();
/*     */     try {
/* 108 */       String s = "select t.consecutivo_contrato,t.codigo_servicio,ss.nombre_servicio,t.tipo,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion  from cont_contrato_servicio t left join cont_contrato c on (t.consecutivo_contrato = c.consecutivo_contrato) left join cont_estudio_previo_servicios es on (c.numero_estudio = es.numero_estudio) left join siau_servicio_salud ss on (t.codigo_servicio = t.codigo_servicio) where 1=1";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 122 */       if (consecutivoContrato > 0) {
/* 123 */         s = s + " and t.consecutivo_contrato=" + consecutivoContrato;
/*     */       }
/* 125 */       if (codigoServicio > 0) {
/* 126 */         s = s + " and t.codigo_servicio=" + codigoServicio;
/*     */       }
/* 128 */       if (tipo.length() > 0) {
/* 129 */         s = s + " and t.tipo='" + tipo + "'";
/*     */       }
/* 131 */       s = s + " order by 1";
/* 132 */       boolean rtaDB = this.dat.parseSql(s);
/* 133 */       if (!rtaDB) {
/* 134 */         return resultados;
/*     */       }
/* 136 */       this.rs = this.dat.getResultSet();
/* 137 */       while (this.rs.next()) {
/* 138 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 141 */     catch (Exception e) {
/* 142 */       e.printStackTrace();
/* 143 */       Utilidades.writeError("ContContratoServicioDAO:cargarTodos ", e);
/*     */     } 
/* 145 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContContratoServicioDTO cargarRegistro(int consecutivoContrato, int codigoServicio) {
/*     */     try {
/* 156 */       String s = "select t.consecutivo_contrato,t.codigo_servicio,ss.nombre_servicio,t.tipo,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion  from cont_contrato_servicio t left join cont_contrato c on (t.consecutivo_contrato = c.consecutivo_contrato) left join cont_estudio_previo_servicios es on (c.numero_estudio = es.numero_estudio) left join siau_servicio_salud ss on (t.codigo_servicio = t.codigo_servicio) where  t.consecutivo_contrato=" + consecutivoContrato + " and t.codigo_servicio=" + codigoServicio + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 173 */       boolean rtaDB = this.dat.parseSql(s);
/* 174 */       if (!rtaDB) {
/* 175 */         return null;
/*     */       }
/* 177 */       this.rs = this.dat.getResultSet();
/* 178 */       if (this.rs.next()) {
/* 179 */         return leerRegistro();
/*     */       }
/*     */     }
/* 182 */     catch (Exception e) {
/* 183 */       e.printStackTrace();
/* 184 */       Utilidades.writeError("ContContratoServicioDAO:cargarContContratoServicio", e);
/*     */     } 
/* 186 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int siguienteRegistro(int consecutivoContrato, int codigoServicio) {
/* 196 */     int inumero = 1;
/* 197 */     String s = "select max(0) from cont_contrato_servicio  where  consecutivo_contrato=" + consecutivoContrato + " and codigo_servicio=" + codigoServicio + "";
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 203 */       boolean rta = this.dat.parseSql(s);
/* 204 */       if (!rta) return 0; 
/* 205 */       this.rs = this.dat.getResultSet();
/* 206 */       if (this.rs.next()) {
/* 207 */         s = this.rs.getString(1);
/* 208 */         if (!this.rs.wasNull()) {
/* 209 */           inumero = Integer.parseInt(s) + 1;
/*     */         }
/*     */       } 
/* 212 */       return inumero;
/*     */     }
/* 214 */     catch (Exception e) {
/* 215 */       e.printStackTrace();
/* 216 */       Utilidades.writeError("ContContratoServicioDAO:siguienteRegistro ", e);
/*     */       
/* 218 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eliminarRegistro(int consecutivoContrato, int codigoServicio) {
/*     */     try {
/* 230 */       String s = "delete from cont_contrato_servicio where  consecutivo_contrato=" + consecutivoContrato;
/*     */ 
/*     */       
/* 233 */       if (codigoServicio > -1) {
/* 234 */         s = s + "  and codigo_servicio=" + codigoServicio;
/*     */       }
/* 236 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 239 */     catch (Exception e) {
/* 240 */       e.printStackTrace();
/* 241 */       Utilidades.writeError("ContContratoServicioDAO:eliminarRegistro ", e);
/*     */       
/* 243 */       return false;
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
/*     */   public boolean crearRegistro(int consecutivoContrato, int codigoServicio, String usuarioInsercion, String tipo) {
/*     */     try {
/* 259 */       String s = "insert into cont_contrato_servicio(consecutivo_contrato,codigo_servicio,usuario_insercion,fecha_insercion,tipo) values (" + consecutivoContrato + "," + "" + codigoServicio + "," + "'" + usuarioInsercion + "'," + "" + Utilidades.getFechaBD() + "," + "'" + tipo + "'" + ")";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 271 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 274 */     catch (Exception e) {
/* 275 */       e.printStackTrace();
/* 276 */       Utilidades.writeError("%ContContratoServicioDAO:crearRegistro ", e);
/*     */       
/* 278 */       return false;
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
/*     */   public boolean modificarRegistro(int consecutivoContrato, int codigoServicio, String usuarioModificacion, String tipo) {
/*     */     try {
/* 293 */       String s = "update cont_contrato_servicio set  usuario_modificacion='" + usuarioModificacion + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " tipo='" + tipo + "'" + " where" + " consecutivo_contrato=" + consecutivoContrato + " and codigo_servicio=" + codigoServicio + "";
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
/* 306 */       Utilidades.writeError("ContContratoServicioDAO:modificarRegistro ", e);
/*     */       
/* 308 */       return false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\ContContratoServicioDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */