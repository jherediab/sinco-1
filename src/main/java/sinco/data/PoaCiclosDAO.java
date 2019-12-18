/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.PoaCiclosDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.PoaCiclosDAO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PoaCiclosDAO
/*     */ {
/*     */   ResultSet rs;
/*  29 */   DBManager dat = new DBManager();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  40 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/*     */     try {
/*  47 */       this.dat.close();
/*     */     }
/*  49 */     catch (Exception e) {
/*  50 */       Utilidades.writeError("PoaCiclosDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PoaCiclosDTO next() {
/*     */     try {
/*  61 */       if (this.rs.next()) {
/*  62 */         return leerRegistro();
/*     */       }
/*     */     }
/*  65 */     catch (Exception e) {
/*  66 */       e.printStackTrace();
/*  67 */       Utilidades.writeError("PoaCiclosDAO:next ", e);
/*     */     } 
/*  69 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PoaCiclosDTO leerRegistro() {
/*     */     try {
/*  79 */       PoaCiclosDTO reg = new PoaCiclosDTO();
/*     */       
/*  81 */       reg.setCodigoCiclo(this.rs.getInt("codigo_ciclo"));
/*  82 */       reg.setDescripcion(this.rs.getString("descripcion"));
/*  83 */       reg.setEstado(this.rs.getString("estado"));
/*  84 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  85 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  86 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  87 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  88 */       reg.setNombreEstado(this.rs.getString("nombre_estado"));
/*  89 */       return reg;
/*     */     }
/*  91 */     catch (Exception e) {
/*  92 */       e.printStackTrace();
/*  93 */       Utilidades.writeError("PoaCiclosDAO:leerRegistro ", e);
/*     */       
/*  95 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<PoaCiclosDTO> cargarTodos(String descripcion, String estado) {
/* 106 */     Collection<PoaCiclosDTO> resultados = new ArrayList<PoaCiclosDTO>();
/*     */     try {
/* 108 */       String s = "select t.codigo_ciclo,t.descripcion,t.estado,m1.DESCRIPCION as nombre_estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from POA_CICLOS t  left join sis_multivalores m1 on (m1.tabla='ESTADO_REGISTRO' and m1.VALOR=t.estado) where 1=1";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 120 */       if (descripcion.length() > 0) {
/* 121 */         s = s + " and upper(t.descripcion) like upper('%" + descripcion + "%')";
/*     */       }
/* 123 */       if (estado.length() > 0) {
/* 124 */         s = s + " and upper(t.estado) like upper('%" + estado + "%')";
/*     */       }
/* 126 */       s = s + " order by 1";
/* 127 */       boolean rtaDB = this.dat.parseSql(s);
/* 128 */       if (!rtaDB) {
/* 129 */         return resultados;
/*     */       }
/* 131 */       this.rs = this.dat.getResultSet();
/* 132 */       while (this.rs.next()) {
/* 133 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 136 */     catch (Exception e) {
/* 137 */       e.printStackTrace();
/* 138 */       Utilidades.writeError("PoaCiclosDAO:cargarTodos ", e);
/*     */     } 
/* 140 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PoaCiclosDTO cargarRegistro(int codigoCiclo) {
/*     */     try {
/* 150 */       String s = "select t.codigo_ciclo,t.descripcion,t.estado,m1.DESCRIPCION as nombre_estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from POA_CICLOS t  left join sis_multivalores m1 on (m1.tabla='ESTADO_REGISTRO' and m1.VALOR=t.estado) where  t.codigo_ciclo=" + codigoCiclo + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 164 */       boolean rtaDB = this.dat.parseSql(s);
/* 165 */       if (!rtaDB) {
/* 166 */         return null;
/*     */       }
/* 168 */       this.rs = this.dat.getResultSet();
/* 169 */       if (this.rs.next()) {
/* 170 */         return leerRegistro();
/*     */       }
/*     */     }
/* 173 */     catch (Exception e) {
/* 174 */       e.printStackTrace();
/* 175 */       Utilidades.writeError("PoaCiclosDAO:cargarPoaCiclos", e);
/*     */     } 
/* 177 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarRegistro(int codigoCiclo) {
/* 187 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 190 */       String s = "delete from POA_CICLOS where  codigo_ciclo=" + codigoCiclo + "";
/*     */ 
/*     */ 
/*     */       
/* 194 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 196 */     catch (Exception e) {
/* 197 */       e.printStackTrace();
/* 198 */       Utilidades.writeError("PoaCiclosDAO:eliminarRegistro ", e);
/* 199 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 201 */     return rta;
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
/*     */   public RespuestaBD crearRegistro(int codigoCiclo, String descripcion, String estado, String usuarioInsercion) {
/* 215 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 218 */       String s = "insert into POA_CICLOS(codigo_ciclo,descripcion,estado,fecha_insercion,usuario_insercion) values (" + codigoCiclo + "," + "'" + descripcion + "'," + "'" + estado + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 231 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 233 */     catch (Exception e) {
/* 234 */       e.printStackTrace();
/* 235 */       Utilidades.writeError("%PoaCiclosDAO:crearRegistro ", e);
/* 236 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 238 */     return rta;
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
/*     */   public RespuestaBD modificarRegistro(int codigoCiclo, String descripcion, String estado, String usuarioModificacion) {
/* 252 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 255 */       String s = "update POA_CICLOS set  descripcion='" + descripcion + "'," + " estado='" + estado + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " codigo_ciclo=" + codigoCiclo + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 263 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 265 */     catch (Exception e) {
/* 266 */       e.printStackTrace();
/* 267 */       Utilidades.writeError("PoaCiclosDAO:modificarRegistro ", e);
/* 268 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 270 */     return rta;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\PoaCiclosDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */