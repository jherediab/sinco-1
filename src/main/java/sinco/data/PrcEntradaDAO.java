/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.PrcEntradaDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.PrcEntradaDAO;
/*     */ 
/*     */ 
 
/*     */ 
/*     */ public class PrcEntradaDAO
/*     */ {
/*     */   public PrcEntradaDTO leerRegistro(ResultSet rs) {
/*     */     try {
/*  35 */       PrcEntradaDTO reg = new PrcEntradaDTO();
/*     */       
/*  37 */       reg.setIdEntrada(rs.getInt("id_entrada"));
/*  38 */       reg.setCodigoEntrada(rs.getString("codigo_entrada"));
/*  39 */       reg.setDescripcionEntrada(rs.getString("descripcion_entrada"));
/*  40 */       reg.setEstado(rs.getString("estado"));
/*  41 */       reg.setUsuarioInsercion(rs.getString("usuario_insercion"));
/*  42 */       reg.setFechaInsercion(rs.getString("fecha_insercion"));
/*  43 */       reg.setUsuarioModificacion(rs.getString("usuario_modificacion"));
/*  44 */       reg.setFechaModificacion(rs.getString("fecha_modificacion"));
/*  45 */       reg.setNombreEstado(rs.getString("nombre_estado"));
/*  46 */       return reg;
/*     */     }
/*  48 */     catch (Exception e) {
/*  49 */       e.printStackTrace();
/*  50 */       Utilidades.writeError("PrcEntradaDAO:leerRegistro ", e);
/*     */       
/*  52 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<PrcEntradaDTO> cargarTodos(String codigoEntrada, String estado) {
/*  63 */     Collection<PrcEntradaDTO> resultados = new ArrayList<PrcEntradaDTO>();
/*     */     
/*  65 */  DBManager   dat = new DBManager();
/*     */     try {
/*  67 */       String s = "select t.id_entrada,t.codigo_entrada,t.descripcion_entrada,t.estado,m1.descripcion as nombre_estado,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion from prc_entrada t  left join sis_multivalores m1 on (m1.tabla='estado_activo_inactivo' and m1.valor=t.estado) where 1=1";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  80 */       if (codigoEntrada.length() > 0) {
/*  81 */         s = s + " and upper(t.codigo_entrada) like upper('%" + codigoEntrada + "%')";
/*     */       }
/*  83 */       if (estado.length() > 0) {
/*  84 */         s = s + " and upper(t.estado) like upper('%" + estado + "%')";
/*     */       }
/*  86 */       s = s + " order by 1";
/*  87 */       boolean rtaDB = dat.parseSql(s);
/*  88 */       if (!rtaDB) {
/*  89 */         return resultados;
/*     */       }
/*  91 */       ResultSet rs = dat.getResultSet();
/*  92 */       while (rs.next()) {
/*  93 */         resultados.add(leerRegistro(rs));
/*     */       }
/*     */     }
/*  96 */     catch (Exception e) {
/*  97 */       e.printStackTrace();
/*  98 */       Utilidades.writeError("PrcEntradaDAO:cargarTodos ", e);
/*     */     } finally {
/*     */       
/* 101 */       dat.close();
/*     */     } 
/* 103 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PrcEntradaDTO cargarRegistro(int idEntrada) {
/* 113 */   DBManager  dat = new DBManager();
/*     */     try {
/* 115 */       String s = "select t.id_entrada,t.codigo_entrada,t.descripcion_entrada,t.estado,m1.descripcion as nombre_estado,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion from prc_entrada t  left join sis_multivalores m1 on (m1.tabla='estado_activo_inactivo' and m1.valor=t.estado) where  t.id_entrada=" + idEntrada + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 130 */       boolean rtaDB = dat.parseSql(s);
/* 131 */       if (!rtaDB) {
/* 132 */         return null;
/*     */       }
/* 134 */       ResultSet rs = dat.getResultSet();
/* 135 */       if (rs.next()) {
/* 136 */         return leerRegistro(rs);
/*     */       }
/*     */     }
/* 139 */     catch (Exception e) {
/* 140 */       e.printStackTrace();
/* 141 */       Utilidades.writeError("PrcEntradaDAO:cargarPrcEntrada", e);
/*     */     } finally {
/*     */       
/* 144 */       dat.close();
/*     */     } 
/* 146 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int siguienteRegistro() {
/* 155 */     int inumero = 1;
/* 156 */     String s = "select max(id_entrada) from prc_entrada ";
/*     */ 
/*     */     
/* 159 */   DBManager  dat = new DBManager();
/*     */     try {
/* 161 */       boolean rta = dat.parseSql(s);
/* 162 */       if (!rta) return 0; 
/* 163 */       ResultSet rs = dat.getResultSet();
/* 164 */       if (rs.next()) {
/* 165 */         s = rs.getString(1);
/* 166 */         if (!rs.wasNull()) {
/* 167 */           inumero = Integer.parseInt(s) + 1;
/*     */         }
/*     */       } 
/* 170 */       return inumero;
/*     */     }
/* 172 */     catch (Exception e) {
/* 173 */       e.printStackTrace();
/* 174 */       Utilidades.writeError("PrcEntradaDAO:siguienteRegistro ", e);
/*     */     } finally {
/*     */       
/* 177 */       dat.close();
/*     */     } 
/* 179 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarRegistro(int idEntrada) {
/* 189 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 191 */    DBManager dat = new DBManager();
/*     */     try {
/* 193 */       String s = "delete from prc_entrada where  id_entrada=" + idEntrada + "";
/*     */ 
/*     */ 
/*     */       
/* 197 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 199 */     catch (Exception e) {
/* 200 */       e.printStackTrace();
/* 201 */       Utilidades.writeError("PrcEntradaDAO:eliminarRegistro ", e);
/* 202 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 205 */       dat.close();
/*     */     } 
/* 207 */     return rta;
/*     */   }
 
/*     */   
/*     */   public RespuestaBD crearRegistro(int idEntrada, String codigoEntrada, String descripcionEntrada, String estado, String usuarioInsercion) {
/* 222 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 224 */     int elSiguiente = siguienteRegistro();
/* 225 */     if (elSiguiente == 0) {
/* 226 */       rta.setMensaje("Generando secuencia");
/* 227 */       return rta;
/*     */     } 
/*     */     
/* 230 */   DBManager  dat = new DBManager();
/*     */     try {
/* 232 */       String s = "insert into prc_entrada(id_entrada,codigo_entrada,descripcion_entrada,estado,usuario_insercion,fecha_insercion) values (" + elSiguiente + "," + "'" + codigoEntrada + "'," + "'" + descripcionEntrada + "'," + "'" + estado + "'," + "'" + usuarioInsercion + "'," + "" + Utilidades.getFechaBD() + "" + ")";
/*     */ 
 
/*     */       
/* 247 */       rta = dat.executeUpdate2(s);
/* 248 */       rta.setSecuencia(elSiguiente);
/*     */     }
/* 250 */     catch (Exception e) {
/* 251 */       e.printStackTrace();
/* 252 */       Utilidades.writeError("%PrcEntradaDAO:crearRegistro ", e);
/* 253 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 256 */       dat.close();
/*     */     } 
/* 258 */     return rta;
/*     */   }
 
/*     */   
/*     */   public RespuestaBD modificarRegistro(int idEntrada, String codigoEntrada, String descripcionEntrada, String estado, String usuarioModificacion) {
/* 273 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 275 */   DBManager  dat = new DBManager();
/*     */     try {
/* 277 */       String s = "update prc_entrada set  codigo_entrada='" + codigoEntrada + "'," + " descripcion_entrada='" + descripcionEntrada + "'," + " estado='" + estado + "'," + " usuario_modificacion='" + usuarioModificacion + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "" + " where" + " id_entrada=" + idEntrada + "";
/*     */ 
 
/*     */       
/* 286 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 288 */     catch (Exception e) {
/* 289 */       e.printStackTrace();
/* 290 */       Utilidades.writeError("PrcEntradaDAO:modificarRegistro ", e);
/* 291 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 294 */       dat.close();
/*     */     } 
/* 296 */     return rta;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\PrcEntradaDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */