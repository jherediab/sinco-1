/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.ContEstudioPrevioFirmaDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.ContEstudioPrevioFirmaDAO;
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
/*     */ public class ContEstudioPrevioFirmaDAO
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
/*  49 */       Utilidades.writeError("ContEstudioPrevioFirmaDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContEstudioPrevioFirmaDTO next() {
/*     */     try {
/*  60 */       if (this.rs.next()) {
/*  61 */         return leerRegistro();
/*     */       }
/*     */     }
/*  64 */     catch (Exception e) {
/*  65 */       e.printStackTrace();
/*  66 */       Utilidades.writeError("ContEstudioPrevioFirmaDAO:next ", e);
/*     */     } 
/*  68 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContEstudioPrevioFirmaDTO leerRegistro() {
/*     */     try {
/*  78 */       ContEstudioPrevioFirmaDTO reg = new ContEstudioPrevioFirmaDTO();
/*     */       
/*  80 */       reg.setNumeroEstudio(this.rs.getInt("numero_estudio"));
/*  81 */       reg.setTipoFirma(this.rs.getString("tipo_firma"));
/*  82 */       reg.setDescripcionTipoFirma(this.rs.getString("descripcion_tipo_firma"));
/*  83 */       reg.setFuncionario(this.rs.getInt("funcionario"));
/*  84 */       reg.setNombreFuncionario(this.rs.getString("nombre_funcionario"));
/*  85 */       reg.setDescripcionFirma(this.rs.getString("descripcion_firma"));
/*     */       
/*  87 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  88 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  89 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  90 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  91 */       return reg;
/*     */     }
/*  93 */     catch (Exception e) {
/*  94 */       e.printStackTrace();
/*  95 */       Utilidades.writeError("ContEstudioPrevioFirmaDAO:leerRegistro ", e);
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
/*     */   public Collection<ContEstudioPrevioFirmaDTO> cargarTodos(int numeroEstudio) {
/* 107 */     Collection<ContEstudioPrevioFirmaDTO> resultados = new ArrayList<ContEstudioPrevioFirmaDTO>();
/*     */     try {
/* 109 */       String s = "select  t.numero_estudio,t.tipo_firma,t.funcionario,t.descripcion_firma,p.nombres||' '||p.apellidos as nombre_funcionario,dt.descripcion as descripcion_tipo_firma,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion  from cont_estudio_previo_firma t left join sis_usuarios p on (t.funcionario = p.codigo_empleado)  left join sis_multivalores dt on (t.tipo_firma=dt.valor and dt.tabla='tipo_firma') where 1=1";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 124 */       if (numeroEstudio > 0) {
/* 125 */         s = s + " and t.numero_estudio=" + numeroEstudio;
/*     */       }
/* 127 */       s = s + " order by 1";
/* 128 */       boolean rtaDB = this.dat.parseSql(s);
/* 129 */       if (!rtaDB) {
/* 130 */         return resultados;
/*     */       }
/* 132 */       this.rs = this.dat.getResultSet();
/* 133 */       while (this.rs.next()) {
/* 134 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 137 */     catch (Exception e) {
/* 138 */       e.printStackTrace();
/* 139 */       Utilidades.writeError("ContEstudioPrevioFirmaDAO:cargarTodos ", e);
/*     */     } 
/* 141 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContEstudioPrevioFirmaDTO cargarRegistro(int numeroEstudio, String tipoFirma) {
/*     */     try {
/* 151 */       String s = "select  t.numero_estudio,t.tipo_firma,t.funcionario,t.descripcion_firma,p.nombres||' '||p.apellidos as nombre_funcionario,dt.descripcion as descripcion_tipo_firma,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion  from cont_estudio_previo_firma t left join sis_usuarios p on (t.funcionario = p.codigo_empleado)  left join sis_multivalores dt on (t.tipo_firma=dt.valor and dt.tabla='tipo_firma') where  t.numero_estudio=" + numeroEstudio + " and t.tipo_firma='" + tipoFirma + "'" + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 169 */       boolean rtaDB = this.dat.parseSql(s);
/* 170 */       if (!rtaDB) {
/* 171 */         return null;
/*     */       }
/* 173 */       this.rs = this.dat.getResultSet();
/* 174 */       if (this.rs.next()) {
/* 175 */         return leerRegistro();
/*     */       }
/*     */     }
/* 178 */     catch (Exception e) {
/* 179 */       e.printStackTrace();
/* 180 */       Utilidades.writeError("ContEstudioPrevioFirmaDAO:cargarContEstudioPrevioFirma", e);
/*     */     } 
/* 182 */     return null;
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
/*     */   public boolean eliminarRegistro(int numeroEstudio, String tipoFirma) {
/*     */     try {
/* 195 */       String s = "delete from cont_estudio_previo_firma where  numero_estudio=" + numeroEstudio + "  and tipo_firma='" + tipoFirma + "'" + "";
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 200 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 203 */     catch (Exception e) {
/* 204 */       e.printStackTrace();
/* 205 */       Utilidades.writeError("ContEstudioPrevioFirmaDAO:eliminarRegistro ", e);
/*     */       
/* 207 */       return false;
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
/*     */   public boolean crearRegistro(int numeroEstudio, String tipoFirma, int funcionario, String descripcionFirma, String usuarioInsercion) {
/*     */     try {
/* 223 */       String s = "insert into cont_estudio_previo_firma(numero_estudio,tipo_firma,funcionario,descripcion_firma,fecha_insercion,usuario_insercion) values (" + numeroEstudio + "," + "'" + tipoFirma + "'," + "" + funcionario + "," + "'" + descripcionFirma + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 238 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 241 */     catch (Exception e) {
/* 242 */       e.printStackTrace();
/* 243 */       Utilidades.writeError("%ContEstudioPrevioFirmaDAO:crearRegistro ", e);
/*     */       
/* 245 */       return false;
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
/*     */   public boolean modificarRegistro(int numeroEstudio, String tipoFirma, int funcionario, String descripcionFirma, String usuarioModificacion) {
/*     */     try {
/* 261 */       String s = "update cont_estudio_previo_firma set  funcionario=" + funcionario + "," + " descripcion_firma='" + descripcionFirma + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " numero_estudio=" + numeroEstudio + " and tipo_firma='" + tipoFirma + "'" + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 270 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 273 */     catch (Exception e) {
/* 274 */       e.printStackTrace();
/* 275 */       Utilidades.writeError("ContEstudioPrevioFirmaDAO:modificarRegistro ", e);
/*     */       
/* 277 */       return false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\ContEstudioPrevioFirmaDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */