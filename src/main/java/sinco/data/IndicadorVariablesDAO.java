/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.IndicadorVariablesDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.IndicadorVariablesDAO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class IndicadorVariablesDAO
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
/*  50 */       Utilidades.writeError("IndicadorVariablesDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IndicadorVariablesDTO next() {
/*     */     try {
/*  61 */       if (this.rs.next()) {
/*  62 */         return leerRegistro();
/*     */       }
/*     */     }
/*  65 */     catch (Exception e) {
/*  66 */       e.printStackTrace();
/*  67 */       Utilidades.writeError("IndicadorVariablesDAO:next ", e);
/*     */     } 
/*  69 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IndicadorVariablesDTO leerRegistro() {
/*     */     try {
/*  79 */       IndicadorVariablesDTO reg = new IndicadorVariablesDTO();
/*  80 */       reg.setDescripcion(this.rs.getString("descripcion"));
/*  81 */       reg.setNombre(this.rs.getString("nombre"));
/*  82 */       reg.setFuente(this.rs.getString("fuente_informacion"));
/*  83 */       reg.setIdIndicador(this.rs.getString("indicador"));
/*  84 */       reg.setIdVariable(this.rs.getInt("variable"));
/*  85 */       reg.setEstado(this.rs.getString("estado"));
/*  86 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  87 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*     */       
/*  89 */       return reg;
/*     */     }
/*  91 */     catch (Exception e) {
/*  92 */       e.printStackTrace();
/*  93 */       Utilidades.writeError("IndicadorVariablesDAO:leerRegistro ", e);
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
/*     */   public Collection<IndicadorVariablesDTO> cargarTodos() {
/* 105 */     Collection<IndicadorVariablesDTO> resultados = new ArrayList<IndicadorVariablesDTO>();
/*     */     try {
/* 107 */       String s = "select t.VARIABLE,t.descripcion,t.indicador,t.estado,t.fecha_insercion,t.usuario_insercion from INDICADOR_VARIABLES t  where 1=1";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 116 */       s = s + " order by 1";
/* 117 */       boolean rtaDB = this.dat.parseSql(s);
/* 118 */       if (!rtaDB) {
/* 119 */         return resultados;
/*     */       }
/* 121 */       this.rs = this.dat.getResultSet();
/* 122 */       while (this.rs.next()) {
/* 123 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 126 */     catch (Exception e) {
/* 127 */       e.printStackTrace();
/* 128 */       Utilidades.writeError("IndicadorVariablesDAO:cargarTodos ", e);
/*     */     } 
/* 130 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<Integer> cargarVariables(int indicador) {
/* 137 */     Collection<Integer> resultados = new ArrayList<Integer>();
/*     */     try {
/* 139 */       String s = "select distinct(t.descripcion) from INDICADOR_VARIABLES t where 1=1 ";
/*     */       
/* 141 */       if (indicador > 0) {
/* 142 */         s = s + " AND t.indicador = " + indicador;
/*     */       }
/* 144 */       s = s + " order by 1";
/* 145 */       boolean rtaDB = this.dat.parseSql(s);
/* 146 */       if (!rtaDB) {
/* 147 */         return resultados;
/*     */       }
/* 149 */       this.rs = this.dat.getResultSet();
/* 150 */       while (this.rs.next()) {
/* 151 */         resultados.add(Integer.valueOf(Integer.parseInt(this.rs.getString("variable"))));
/*     */       }
/*     */     }
/* 154 */     catch (Exception e) {
/* 155 */       e.printStackTrace();
/* 156 */       Utilidades.writeError("PoaMaestroDAO:cargarTodos ", e);
/*     */     } 
/* 158 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<IndicadorVariablesDTO> cargarTodos(String codigoIndicador) {
/* 166 */     Collection<IndicadorVariablesDTO> resultados = new ArrayList<IndicadorVariablesDTO>();
/*     */     try {
/* 168 */       String s = "select t.VARIABLE,t.nombre,t.descripcion,t.fuente_informacion,t.indicador,t.estado,t.fecha_insercion,t.usuario_insercion from INDICADOR_VARIABLES t where t.indicador='" + codigoIndicador + "'";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 179 */       s = s + " order by 1";
/* 180 */       boolean rtaDB = this.dat.parseSql(s);
/* 181 */       if (!rtaDB) {
/* 182 */         return resultados;
/*     */       }
/* 184 */       this.rs = this.dat.getResultSet();
/* 185 */       while (this.rs.next()) {
/* 186 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 189 */     catch (Exception e) {
/* 190 */       e.printStackTrace();
/* 191 */       Utilidades.writeError("IndicadorVariablesDAO:cargarTodos ", e);
/*     */     } 
/* 193 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IndicadorVariablesDTO cargarRegistro(int idVariable) {
/*     */     try {
/* 204 */       String s = "select t.VARIABLE,t.descripcion,t.nombre,t.fuente_informacion,t.indicador,t.estado,t.fecha_insercion,t.usuario_insercion from INDICADOR_VARIABLES t  where  t.VARIABLE=" + idVariable + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 218 */       boolean rtaDB = this.dat.parseSql(s);
/* 219 */       if (!rtaDB) {
/* 220 */         return null;
/*     */       }
/* 222 */       this.rs = this.dat.getResultSet();
/* 223 */       if (this.rs.next()) {
/* 224 */         return leerRegistro();
/*     */       }
/*     */     }
/* 227 */     catch (Exception e) {
/* 228 */       e.printStackTrace();
/* 229 */       Utilidades.writeError("IndicadorVariablesDAO:cargarPoaMaestroInsumoProveedor", e);
/*     */     } 
/* 231 */     return null;
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
/*     */   public RespuestaBD eliminarRegistro(String idIndicador) {
/* 246 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 249 */       String s = "delete from INDICADOR_VARIABLES where   INDICADOR='" + idIndicador + "'" + "";
/*     */ 
/*     */ 
/*     */       
/* 253 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 255 */     catch (Exception e) {
/* 256 */       e.printStackTrace();
/* 257 */       Utilidades.writeError("PoaMaestroInsumoProveedorDAO:eliminarRegistro ", e);
/* 258 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 260 */     return rta;
/*     */   }
/*     */   
/*     */   public int siguienteRegistro() {
/* 264 */     int inumero = 1;
/* 265 */     String s = "select max(variable) from INDICADOR_VARIABLES ";
/*     */     
/*     */     try {
/* 268 */       boolean rta = this.dat.parseSql(s);
/* 269 */       if (!rta) return 0; 
/* 270 */       this.rs = this.dat.getResultSet();
/* 271 */       if (this.rs.next()) {
/* 272 */         s = this.rs.getString(1);
/* 273 */         if (!this.rs.wasNull()) {
/* 274 */           inumero = Integer.parseInt(s) + 1;
/*     */         }
/*     */       } 
/* 277 */       return inumero;
/*     */     }
/* 279 */     catch (Exception e) {
/* 280 */       e.printStackTrace();
/* 281 */       Utilidades.writeError("PoaMaestroDAO:siguienteRegistro ", e);
/*     */       
/* 283 */       return 0;
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
/*     */   public RespuestaBD crearRegistro(String variable, String descripcion, String fuente, String idIndicador, String estado, String usuarioInsercion) {
/* 298 */     RespuestaBD rta = new RespuestaBD();
/* 299 */     int elSiguiente = siguienteRegistro();
/* 300 */     if (elSiguiente == 0) {
/* 301 */       rta.setMensaje("Generando secuencia");
/* 302 */       return rta;
/*     */     } 
/*     */     
/*     */     try {
/* 306 */       String s = "insert into INDICADOR_VARIABLES(VARIABLE,nombre,fuente_informacion,descripcion,indicador,estado,fecha_insercion,usuario_insercion) values (" + elSiguiente + "," + "'" + variable + "'," + "'" + descripcion + "'," + "'" + fuente + "'," + "'" + idIndicador + "'," + "'" + estado + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 325 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 327 */     catch (Exception e) {
/* 328 */       e.printStackTrace();
/* 329 */       Utilidades.writeError("%PoaMaestroInsumoProveedorDAO:crearRegistro ", e);
/* 330 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 332 */     return rta;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\IndicadorVariablesDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */