/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.PoaTableroColoresDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.PoaTableroColoresDAO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PoaTableroColoresDAO
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
/*  50 */       Utilidades.writeError("PoaTableroColoresDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PoaTableroColoresDTO next() {
/*     */     try {
/*  61 */       if (this.rs.next()) {
/*  62 */         return leerRegistro();
/*     */       }
/*     */     }
/*  65 */     catch (Exception e) {
/*  66 */       e.printStackTrace();
/*  67 */       Utilidades.writeError("PoaTableroColoresDAO:next ", e);
/*     */     } 
/*  69 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PoaTableroColoresDTO leerRegistro() {
/*     */     try {
/*  79 */       PoaTableroColoresDTO reg = new PoaTableroColoresDTO();
/*     */       
/*  81 */       reg.setCodigo(this.rs.getInt("codigo"));
/*  82 */       reg.setColor(this.rs.getString("color"));
/*  83 */       reg.setValorInicial(this.rs.getInt("valor_inicial"));
/*  84 */       reg.setValorFinal(this.rs.getInt("valor_final"));
/*  85 */       reg.setEstado(this.rs.getString("estado"));
/*  86 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  87 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  88 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  89 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  90 */       reg.setNombreEstado(this.rs.getString("nombre_estado"));
/*  91 */       return reg;
/*     */     }
/*  93 */     catch (Exception e) {
/*  94 */       e.printStackTrace();
/*  95 */       Utilidades.writeError("PoaTableroColoresDAO:leerRegistro ", e);
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
/*     */   public Collection<PoaTableroColoresDTO> cargarTodos(String color) {
/* 107 */     Collection<PoaTableroColoresDTO> resultados = new ArrayList<PoaTableroColoresDTO>();
/*     */     try {
/* 109 */       String s = "select t.codigo,t.color,t.valor_inicial,t.valor_final,t.estado,m1.DESCRIPCION as nombre_estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from POA_TABLERO_COLORES t  left join sis_multivalores m1 on (m1.tabla='ESTADO_REGISTRO' and m1.VALOR=t.estado) where 1=1";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 123 */       if (color.length() > 0) {
/* 124 */         s = s + " and upper(t.color) like upper('%" + color + "%')";
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
/* 138 */       Utilidades.writeError("PoaTableroColoresDAO:cargarTodos ", e);
/*     */     } 
/* 140 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PoaTableroColoresDTO cargarRegistro(int codigo) {
/*     */     try {
/* 150 */       String s = "select t.codigo,t.color,t.valor_inicial,t.valor_final,t.estado,m1.DESCRIPCION as nombre_estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from POA_TABLERO_COLORES t  left join sis_multivalores m1 on (m1.tabla='ESTADO_REGISTRO' and m1.VALOR=t.estado) where  t.codigo=" + codigo + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 166 */       boolean rtaDB = this.dat.parseSql(s);
/* 167 */       if (!rtaDB) {
/* 168 */         return null;
/*     */       }
/* 170 */       this.rs = this.dat.getResultSet();
/* 171 */       if (this.rs.next()) {
/* 172 */         return leerRegistro();
/*     */       }
/*     */     }
/* 175 */     catch (Exception e) {
/* 176 */       e.printStackTrace();
/* 177 */       Utilidades.writeError("PoaTableroColoresDAO:cargarPoaTableroColores", e);
/*     */     } 
/* 179 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int siguienteRegistro() {
/* 188 */     int inumero = 1;
/* 189 */     String s = "select max(codigo) from POA_TABLERO_COLORES ";
/*     */     
/*     */     try {
/* 192 */       boolean rta = this.dat.parseSql(s);
/* 193 */       if (!rta) return 0; 
/* 194 */       this.rs = this.dat.getResultSet();
/* 195 */       if (this.rs.next()) {
/* 196 */         s = this.rs.getString(1);
/* 197 */         if (!this.rs.wasNull()) {
/* 198 */           inumero = Integer.parseInt(s) + 1;
/*     */         }
/*     */       } 
/* 201 */       return inumero;
/*     */     }
/* 203 */     catch (Exception e) {
/* 204 */       e.printStackTrace();
/* 205 */       Utilidades.writeError("PoaTableroColoresDAO:siguienteRegistro ", e);
/*     */       
/* 207 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarRegistro(int codigo) {
/* 217 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 220 */       String s = "delete from POA_TABLERO_COLORES where  codigo=" + codigo + "";
/*     */ 
/*     */ 
/*     */       
/* 224 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 226 */     catch (Exception e) {
/* 227 */       e.printStackTrace();
/* 228 */       Utilidades.writeError("PoaTableroColoresDAO:eliminarRegistro ", e);
/* 229 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 231 */     return rta;
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
/*     */   public RespuestaBD crearRegistro(int codigo, String color, int valorInicial, int valorFinal, String estado, String usuarioInsercion) {
/* 247 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 249 */     int elSiguiente = siguienteRegistro();
/* 250 */     if (elSiguiente == 0) {
/* 251 */       rta.setMensaje("Generando secuencia");
/* 252 */       return rta;
/*     */     } 
/*     */     
/*     */     try {
/* 256 */       String s = "insert into POA_TABLERO_COLORES(codigo,color,valor_inicial,valor_final,estado,fecha_insercion,usuario_insercion) values (" + elSiguiente + "," + "'" + color + "'," + "" + valorInicial + "," + "" + valorFinal + "," + "'" + estado + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 273 */       rta = this.dat.executeUpdate2(s);
/* 274 */       rta.setSecuencia(elSiguiente);
/*     */     }
/* 276 */     catch (Exception e) {
/* 277 */       e.printStackTrace();
/* 278 */       Utilidades.writeError("%PoaTableroColoresDAO:crearRegistro ", e);
/* 279 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 281 */     return rta;
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
/*     */   public RespuestaBD modificarRegistro(int codigo, String color, int valorInicial, int valorFinal, String estado, String usuarioModificacion) {
/* 297 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 300 */       String s = "update POA_TABLERO_COLORES set  color='" + color + "'," + " valor_inicial=" + valorInicial + "," + " valor_final=" + valorFinal + "," + " estado='" + estado + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " codigo=" + codigo + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 310 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 312 */     catch (Exception e) {
/* 313 */       e.printStackTrace();
/* 314 */       Utilidades.writeError("PoaTableroColoresDAO:modificarRegistro ", e);
/* 315 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 317 */     return rta;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\PoaTableroColoresDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */