/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.PoaColoresSemaforoDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.PoaColoresSemaforoDAO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PoaColoresSemaforoDAO
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
/*  50 */       Utilidades.writeError("PoaColoresSemaforoDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PoaColoresSemaforoDTO next() {
/*     */     try {
/*  61 */       if (this.rs.next()) {
/*  62 */         return leerRegistro();
/*     */       }
/*     */     }
/*  65 */     catch (Exception e) {
/*  66 */       e.printStackTrace();
/*  67 */       Utilidades.writeError("PoaColoresSemaforoDAO:next ", e);
/*     */     } 
/*  69 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PoaColoresSemaforoDTO leerRegistro() {
/*     */     try {
/*  79 */       PoaColoresSemaforoDTO reg = new PoaColoresSemaforoDTO();
/*     */       
/*  81 */       reg.setCodigo(this.rs.getInt("codigo"));
/*  82 */       reg.setTipoSemaforo(this.rs.getString("tipo_semaforo"));
/*  83 */       reg.setColor(this.rs.getString("color"));
/*  84 */       reg.setValorInicial(this.rs.getInt("valor_inicial"));
/*  85 */       reg.setValorFinal(this.rs.getInt("valor_final"));
/*  86 */       reg.setEstado(this.rs.getString("estado"));
/*  87 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  88 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  89 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  90 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  91 */       reg.setNombreTipoSemaforo(this.rs.getString("nombre_tipo_semaforo"));
/*  92 */       reg.setNombreEstado(this.rs.getString("nombre_estado"));
/*  93 */       return reg;
/*     */     }
/*  95 */     catch (Exception e) {
/*  96 */       e.printStackTrace();
/*  97 */       Utilidades.writeError("PoaColoresSemaforoDAO:leerRegistro ", e);
/*     */       
/*  99 */       return null;
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
/*     */   public Collection<PoaColoresSemaforoDTO> cargarTodos(String tipoSemaforo, int valorInicial, int valorFinal) {
/* 111 */     Collection<PoaColoresSemaforoDTO> resultados = new ArrayList<PoaColoresSemaforoDTO>();
/*     */     try {
/* 113 */       String s = "select t.codigo,t.tipo_semaforo,m1.DESCRIPCION as nombre_tipo_semaforo,t.color,t.valor_inicial,t.valor_final,t.estado,m2.DESCRIPCION as nombre_estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from POA_COLORES_SEMAFORO t  left join sis_multivalores m1 on (m1.tabla='TABLERO_CONTROL_SEMAFORO' and m1.VALOR=t.tipo_semaforo) left join sis_multivalores m2 on (m2.tabla='ESTADO_REGISTRO' and m2.VALOR=t.estado) where 1=1";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 130 */       if (tipoSemaforo.length() > 0) {
/* 131 */         s = s + " and upper(t.tipo_semaforo) like upper('%" + tipoSemaforo + "%')";
/*     */       }
/* 133 */       if (valorInicial > 0) {
/* 134 */         s = s + " and t.valor_inicial=" + valorInicial;
/*     */       }
/* 136 */       if (valorFinal > 0) {
/* 137 */         s = s + " and t.valor_final=" + valorFinal;
/*     */       }
/* 139 */       s = s + " order by 1";
/* 140 */       boolean rtaDB = this.dat.parseSql(s);
/* 141 */       if (!rtaDB) {
/* 142 */         return resultados;
/*     */       }
/* 144 */       this.rs = this.dat.getResultSet();
/* 145 */       while (this.rs.next()) {
/* 146 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 149 */     catch (Exception e) {
/* 150 */       e.printStackTrace();
/* 151 */       Utilidades.writeError("PoaColoresSemaforoDAO:cargarTodos ", e);
/*     */     } 
/* 153 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PoaColoresSemaforoDTO cargarRegistro(int codigo) {
/*     */     try {
/* 163 */       String s = "select t.codigo,t.tipo_semaforo,m1.DESCRIPCION as nombre_tipo_semaforo,t.color,t.valor_inicial,t.valor_final,t.estado,m2.DESCRIPCION as nombre_estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from POA_COLORES_SEMAFORO t  left join sis_multivalores m1 on (m1.tabla='TABLERO_CONTROL_SEMAFORO' and m1.VALOR=t.tipo_semaforo) left join sis_multivalores m2 on (m2.tabla='ESTADO_REGISTRO' and m2.VALOR=t.estado) where  t.codigo=" + codigo + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 182 */       boolean rtaDB = this.dat.parseSql(s);
/* 183 */       if (!rtaDB) {
/* 184 */         return null;
/*     */       }
/* 186 */       this.rs = this.dat.getResultSet();
/* 187 */       if (this.rs.next()) {
/* 188 */         return leerRegistro();
/*     */       }
/*     */     }
/* 191 */     catch (Exception e) {
/* 192 */       e.printStackTrace();
/* 193 */       Utilidades.writeError("PoaColoresSemaforoDAO:cargarPoaColoresSemaforo", e);
/*     */     } 
/* 195 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int siguienteRegistro() {
/* 204 */     int inumero = 1;
/* 205 */     String s = "select max(codigo) from POA_COLORES_SEMAFORO ";
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
/* 221 */       Utilidades.writeError("PoaColoresSemaforoDAO:siguienteRegistro ", e);
/*     */       
/* 223 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarRegistro(int codigo) {
/* 233 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 236 */       String s = "delete from POA_COLORES_SEMAFORO where  codigo=" + codigo + "";
/*     */ 
/*     */ 
/*     */       
/* 240 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 242 */     catch (Exception e) {
/* 243 */       e.printStackTrace();
/* 244 */       Utilidades.writeError("PoaColoresSemaforoDAO:eliminarRegistro ", e);
/* 245 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 247 */     return rta;
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
/*     */   public RespuestaBD crearRegistro(int codigo, String tipoSemaforo, String color, int valorInicial, int valorFinal, String estado, String usuarioInsercion) {
/* 264 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 266 */     int elSiguiente = siguienteRegistro();
/* 267 */     if (elSiguiente == 0) {
/* 268 */       rta.setMensaje("Generando secuencia");
/* 269 */       return rta;
/*     */     } 
/*     */     
/*     */     try {
/* 273 */       String s = "insert into POA_COLORES_SEMAFORO(codigo,tipo_semaforo,color,valor_inicial,valor_final,estado,fecha_insercion,usuario_insercion) values (" + elSiguiente + "," + "'" + tipoSemaforo + "'," + "'" + color + "'," + "" + valorInicial + "," + "" + valorFinal + "," + "'" + estado + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 292 */       rta = this.dat.executeUpdate2(s);
/* 293 */       rta.setSecuencia(elSiguiente);
/*     */     }
/* 295 */     catch (Exception e) {
/* 296 */       e.printStackTrace();
/* 297 */       Utilidades.writeError("%PoaColoresSemaforoDAO:crearRegistro ", e);
/* 298 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 300 */     return rta;
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
/*     */   public RespuestaBD modificarRegistro(int codigo, String tipoSemaforo, String color, int valorInicial, int valorFinal, String estado, String usuarioModificacion) {
/* 317 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 320 */       String s = "update POA_COLORES_SEMAFORO set  tipo_semaforo='" + tipoSemaforo + "'," + " color='" + color + "'," + " valor_inicial=" + valorInicial + "," + " valor_final=" + valorFinal + "," + " estado='" + estado + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " codigo=" + codigo + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 331 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 333 */     catch (Exception e) {
/* 334 */       e.printStackTrace();
/* 335 */       Utilidades.writeError("PoaColoresSemaforoDAO:modificarRegistro ", e);
/* 336 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 338 */     return rta;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\PoaColoresSemaforoDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */