/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.ContEstudioPrevioItemsDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.ContEstudioPrevioItemsDAO;
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
/*     */ public class ContEstudioPrevioItemsDAO
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
/*  49 */       Utilidades.writeError("ContEstudioPrevioItemsDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContEstudioPrevioItemsDTO next() {
/*     */     try {
/*  60 */       if (this.rs.next()) {
/*  61 */         return leerRegistro();
/*     */       }
/*     */     }
/*  64 */     catch (Exception e) {
/*  65 */       e.printStackTrace();
/*  66 */       Utilidades.writeError("ContEstudioPrevioItemsDAO:next ", e);
/*     */     } 
/*  68 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContEstudioPrevioItemsDTO leerRegistro() {
/*     */     try {
/*  78 */       ContEstudioPrevioItemsDTO reg = new ContEstudioPrevioItemsDTO();
/*     */       
/*  80 */       reg.setNumeroEstudio(this.rs.getInt("numero_estudio"));
/*  81 */       reg.setConsecutivoItem(this.rs.getInt("consecutivo_item"));
/*  82 */       reg.setTipoItem(this.rs.getString("tipo_item"));
/*  83 */       reg.setDescripcionItem(this.rs.getString("descripcion_item"));
/*  84 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  85 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  86 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  87 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  88 */       return reg;
/*     */     }
/*  90 */     catch (Exception e) {
/*  91 */       e.printStackTrace();
/*  92 */       Utilidades.writeError("ContEstudioPrevioItemsDAO:leerRegistro ", e);
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
/*     */   public Collection<ContEstudioPrevioItemsDTO> cargarTodos(int numeroEstudio, String tipoItem) {
/* 105 */     Collection<ContEstudioPrevioItemsDTO> resultados = new ArrayList<ContEstudioPrevioItemsDTO>();
/*     */     try {
/* 107 */       String s = "select  t.numero_estudio,t.consecutivo_item,t.tipo_item,t.descripcion_item,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion  from cont_estudio_previo_items t where 1=1";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 118 */       if (numeroEstudio > 0) {
/* 119 */         s = s + " and numero_estudio=" + numeroEstudio;
/*     */       }
/*     */       
/* 122 */       if (tipoItem != "") {
/* 123 */         s = s + " and upper(t.tipo_item) like upper('%" + tipoItem + "%')";
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
/* 137 */       Utilidades.writeError("ContEstudioPrevioItemsDAO:cargarTodos ", e);
/*     */     } 
/* 139 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContEstudioPrevioItemsDTO cargarRegistro(int numeroEstudio, int consecutivoItem) {
/*     */     try {
/* 149 */       String s = "select  t.numero_estudio,t.consecutivo_item,t.tipo_item,t.descripcion_item,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion  from cont_estudio_previo_items t where  t.numero_estudio=" + numeroEstudio + " and t.consecutivo_item=" + consecutivoItem + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 163 */       boolean rtaDB = this.dat.parseSql(s);
/* 164 */       if (!rtaDB) {
/* 165 */         return null;
/*     */       }
/* 167 */       this.rs = this.dat.getResultSet();
/* 168 */       if (this.rs.next()) {
/* 169 */         return leerRegistro();
/*     */       }
/*     */     }
/* 172 */     catch (Exception e) {
/* 173 */       e.printStackTrace();
/* 174 */       Utilidades.writeError("ContEstudioPrevioItemsDAO:cargarContEstudioPrevioItems", e);
/*     */     } 
/* 176 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ContEstudioPrevioItemsDTO cargarRegistro(int numeroEstudio, String tipoItem, String descripcion) {
/*     */     try {
/* 183 */       String s = "select  t.numero_estudio,t.consecutivo_item,t.tipo_item,t.descripcion_item,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion  from cont_estudio_previo_items t where  t.numero_estudio=" + numeroEstudio;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 195 */       if (tipoItem != "") {
/* 196 */         s = s + " and upper(t.tipo_item) like upper('%" + tipoItem + "%')";
/*     */       }
/* 198 */       if (descripcion != "") {
/* 199 */         s = s + " and upper(t.descripcion_item) like upper('%" + descripcion + "%')";
/*     */       }
/* 201 */       boolean rtaDB = this.dat.parseSql(s);
/* 202 */       if (!rtaDB) {
/* 203 */         return null;
/*     */       }
/* 205 */       this.rs = this.dat.getResultSet();
/* 206 */       if (this.rs.next()) {
/* 207 */         return leerRegistro();
/*     */       }
/*     */     }
/* 210 */     catch (Exception e) {
/* 211 */       e.printStackTrace();
/* 212 */       Utilidades.writeError("ContEstudioPrevioItemsDAO:cargarContEstudioPrevioItems", e);
/*     */     } 
/* 214 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int siguienteRegistro(int numeroEstudio, int consecutivoItem) {
/* 224 */     int inumero = 1;
/* 225 */     String s = "select max(0) from cont_estudio_previo_items  where  numero_estudio=" + numeroEstudio + " and consecutivo_item=" + consecutivoItem + "";
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 231 */       boolean rta = this.dat.parseSql(s);
/* 232 */       if (!rta) return 0; 
/* 233 */       this.rs = this.dat.getResultSet();
/* 234 */       if (this.rs.next()) {
/* 235 */         s = this.rs.getString(1);
/* 236 */         if (!this.rs.wasNull()) {
/* 237 */           inumero = Integer.parseInt(s) + 1;
/*     */         }
/*     */       } 
/* 240 */       return inumero;
/*     */     }
/* 242 */     catch (Exception e) {
/* 243 */       e.printStackTrace();
/* 244 */       Utilidades.writeError("ContEstudioPrevioItemsDAO:siguienteRegistro ", e);
/*     */       
/* 246 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eliminarRegistros(int numeroEstudio) {
/*     */     try {
/* 256 */       String s = "delete from cont_estudio_previo_items where  numero_estudio=" + numeroEstudio + "";
/*     */ 
/*     */ 
/*     */       
/* 260 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 263 */     catch (Exception e) {
/* 264 */       e.printStackTrace();
/* 265 */       Utilidades.writeError("ContEstudioPrevioItemsDAO:eliminarRegistro ", e);
/*     */       
/* 267 */       return false;
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
/*     */   public boolean crearRegistro(int numeroEstudio, int consecutivoItem, String tipoItem, String descripcionItem, String usuarioInsercion) {
/*     */     try {
/* 289 */       String s = "insert into cont_estudio_previo_items(numero_estudio,consecutivo_item,tipo_item,descripcion_item,fecha_insercion,usuario_insercion) values (" + numeroEstudio + "," + "" + consecutivoItem + "," + "'" + tipoItem + "'," + "'" + descripcionItem + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 304 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 307 */     catch (Exception e) {
/* 308 */       e.printStackTrace();
/* 309 */       Utilidades.writeError("%ContEstudioPrevioItemsDAO:crearRegistro ", e);
/*     */       
/* 311 */       return false;
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
/*     */   public boolean modificarRegistro(int numeroEstudio, int consecutivoItem, String tipoItem, String descripcionItem, String usuarioModificacion) {
/*     */     try {
/* 327 */       String s = "update cont_estudio_previo_items set  tipo_item='" + tipoItem + "'," + " descripcion_item='" + descripcionItem + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " numero_estudio=" + numeroEstudio + " and consecutivo_item=" + consecutivoItem + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 336 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 339 */     catch (Exception e) {
/* 340 */       e.printStackTrace();
/* 341 */       Utilidades.writeError("ContEstudioPrevioItemsDAO:modificarRegistro ", e);
/*     */       
/* 343 */       return false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\ContEstudioPrevioItemsDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */