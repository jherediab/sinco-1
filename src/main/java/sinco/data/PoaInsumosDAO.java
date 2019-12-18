/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.PoaInsumosDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.PoaInsumosDAO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PoaInsumosDAO
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
/*  50 */       Utilidades.writeError("PoaInsumosDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PoaInsumosDTO next() {
/*     */     try {
/*  61 */       if (this.rs.next()) {
/*  62 */         return leerRegistro();
/*     */       }
/*     */     }
/*  65 */     catch (Exception e) {
/*  66 */       e.printStackTrace();
/*  67 */       Utilidades.writeError("PoaInsumosDAO:next ", e);
/*     */     } 
/*  69 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PoaInsumosDTO leerRegistro() {
/*     */     try {
/*  79 */       PoaInsumosDTO reg = new PoaInsumosDTO();
/*     */       
/*  81 */       reg.setCodigoInsumo(this.rs.getInt("codigo_insumo"));
/*  82 */       reg.setDescrpcion(this.rs.getString("descrpcion"));
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
/*  93 */       Utilidades.writeError("PoaInsumosDAO:leerRegistro ", e);
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
/*     */   public Collection<PoaInsumosDTO> cargarTodos() {
/* 105 */     Collection<PoaInsumosDTO> resultados = new ArrayList<PoaInsumosDTO>();
/*     */     try {
/* 107 */       String s = "select t.codigo_insumo,t.descrpcion,t.estado,m1.DESCRIPCION as nombre_estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from POA_INSUMOS t  left join sis_multivalores m1 on (m1.tabla='ESTADO_REGISTRO' and m1.VALOR=t.estado) where 1=1";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 119 */       s = s + " order by 1";
/* 120 */       boolean rtaDB = this.dat.parseSql(s);
/* 121 */       if (!rtaDB) {
/* 122 */         return resultados;
/*     */       }
/* 124 */       this.rs = this.dat.getResultSet();
/* 125 */       while (this.rs.next()) {
/* 126 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 129 */     catch (Exception e) {
/* 130 */       e.printStackTrace();
/* 131 */       Utilidades.writeError("PoaInsumosDAO:cargarTodos ", e);
/*     */     } 
/* 133 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PoaInsumosDTO cargarRegistro(int codigoInsumo) {
/*     */     try {
/* 143 */       String s = "select t.codigo_insumo,t.descrpcion,t.estado,m1.DESCRIPCION as nombre_estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from POA_INSUMOS t  left join sis_multivalores m1 on (m1.tabla='ESTADO_REGISTRO' and m1.VALOR=t.estado) where  t.codigo_insumo=" + codigoInsumo + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 157 */       boolean rtaDB = this.dat.parseSql(s);
/* 158 */       if (!rtaDB) {
/* 159 */         return null;
/*     */       }
/* 161 */       this.rs = this.dat.getResultSet();
/* 162 */       if (this.rs.next()) {
/* 163 */         return leerRegistro();
/*     */       }
/*     */     }
/* 166 */     catch (Exception e) {
/* 167 */       e.printStackTrace();
/* 168 */       Utilidades.writeError("PoaInsumosDAO:cargarPoaInsumos", e);
/*     */     } 
/* 170 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int siguienteRegistro() {
/* 179 */     int inumero = 1;
/* 180 */     String s = "select max(codigo_insumo) from POA_INSUMOS ";
/*     */     
/*     */     try {
/* 183 */       boolean rta = this.dat.parseSql(s);
/* 184 */       if (!rta) return 0; 
/* 185 */       this.rs = this.dat.getResultSet();
/* 186 */       if (this.rs.next()) {
/* 187 */         s = this.rs.getString(1);
/* 188 */         if (!this.rs.wasNull()) {
/* 189 */           inumero = Integer.parseInt(s) + 1;
/*     */         }
/*     */       } 
/* 192 */       return inumero;
/*     */     }
/* 194 */     catch (Exception e) {
/* 195 */       e.printStackTrace();
/* 196 */       Utilidades.writeError("PoaInsumosDAO:siguienteRegistro ", e);
/*     */       
/* 198 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarRegistro(int codigoInsumo) {
/* 208 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 211 */       String s = "delete from POA_INSUMOS where  codigo_insumo=" + codigoInsumo + "";
/*     */ 
/*     */ 
/*     */       
/* 215 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 217 */     catch (Exception e) {
/* 218 */       e.printStackTrace();
/* 219 */       Utilidades.writeError("PoaInsumosDAO:eliminarRegistro ", e);
/* 220 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 222 */     return rta;
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
/*     */   public RespuestaBD crearRegistro(int codigoInsumo, String descrpcion, String estado, String usuarioInsercion) {
/* 236 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 238 */     int elSiguiente = siguienteRegistro();
/* 239 */     if (elSiguiente == 0) {
/* 240 */       rta.setMensaje("Generando secuencia");
/* 241 */       return rta;
/*     */     } 
/*     */     
/*     */     try {
/* 245 */       String s = "insert into POA_INSUMOS(codigo_insumo,descrpcion,estado,fecha_insercion,usuario_insercion) values (" + elSiguiente + "," + "'" + descrpcion + "'," + "'" + estado + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 258 */       rta = this.dat.executeUpdate2(s);
/* 259 */       rta.setSecuencia(elSiguiente);
/*     */     }
/* 261 */     catch (Exception e) {
/* 262 */       e.printStackTrace();
/* 263 */       Utilidades.writeError("%PoaInsumosDAO:crearRegistro ", e);
/* 264 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 266 */     return rta;
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
/*     */   public RespuestaBD modificarRegistro(int codigoInsumo, String descrpcion, String estado, String usuarioModificacion) {
/* 280 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 283 */       String s = "update POA_INSUMOS set  descrpcion='" + descrpcion + "'," + " estado='" + estado + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " codigo_insumo=" + codigoInsumo + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 291 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 293 */     catch (Exception e) {
/* 294 */       e.printStackTrace();
/* 295 */       Utilidades.writeError("PoaInsumosDAO:modificarRegistro ", e);
/* 296 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 298 */     return rta;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\PoaInsumosDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */