/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.IndicadorResultadoDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.IndicadorResultadoDAO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class IndicadorResultadoDAO
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
/*  50 */       Utilidades.writeError("IndicadorResultadoDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IndicadorResultadoDTO next() {
/*     */     try {
/*  61 */       if (this.rs.next()) {
/*  62 */         return leerRegistro();
/*     */       }
/*     */     }
/*  65 */     catch (Exception e) {
/*  66 */       e.printStackTrace();
/*  67 */       Utilidades.writeError("IndicadorResultadoDAO:next ", e);
/*     */     } 
/*  69 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IndicadorResultadoDTO leerRegistro() {
/*     */     try {
/*  79 */       IndicadorResultadoDTO reg = new IndicadorResultadoDTO();
/*     */       
/*  81 */       reg.setCiclo(this.rs.getInt("ciclo"));
/*  82 */       reg.setIndicador(this.rs.getString("indicador"));
/*  83 */       reg.setMes(this.rs.getString("mes"));
/*  84 */       reg.setValorProgramado(this.rs.getDouble("valor_programado"));
/*  85 */       reg.setValorEjecutado(this.rs.getDouble("valor_ejecutado"));
/*  86 */       reg.setEstado(this.rs.getString("estado"));
/*  87 */       reg.setNombreEstado(this.rs.getString("nombre_estado"));
/*  88 */       return reg;
/*     */     }
/*  90 */     catch (Exception e) {
/*  91 */       e.printStackTrace();
/*  92 */       Utilidades.writeError("IndicadorResultadoDAO:leerRegistro ", e);
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
/*     */   public Collection<IndicadorResultadoDTO> cargarTodos() {
/* 104 */     Collection<IndicadorResultadoDTO> resultados = new ArrayList<IndicadorResultadoDTO>();
/*     */     try {
/* 106 */       String s = "select t.ciclo,t.indicador,t.mes,t.valor_programado,t.valor_ejecutado,t.estadom1.DESCRIPCION as nombre_estado from INDICADOR_RESULTADOS t  left join sis_multivalores m1 on (m1.tabla='ESTADO_REGISTRO' and m1.VALOR=t.estado) where 1=1";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 117 */       s = s + " order by 1";
/* 118 */       boolean rtaDB = this.dat.parseSql(s);
/* 119 */       if (!rtaDB) {
/* 120 */         return resultados;
/*     */       }
/* 122 */       this.rs = this.dat.getResultSet();
/* 123 */       while (this.rs.next()) {
/* 124 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 127 */     catch (Exception e) {
/* 128 */       e.printStackTrace();
/* 129 */       Utilidades.writeError("IndicadorResultadoDAO:cargarTodos ", e);
/*     */     } 
/* 131 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IndicadorResultadoDTO cargarRegistro(int ciclo, String indicador, String mes) {
/*     */     try {
/* 143 */       String s = "select t.ciclo,t.indicador,t.mes,t.valor_programado,t.valor_ejecutado,t.estado, m1.DESCRIPCION as nombre_estado from INDICADOR_RESULTADOS t  left join sis_multivalores m1 on (m1.tabla='ESTADO_REGISTRO' and m1.VALOR=t.estado) where  t.ciclo=" + ciclo + " and t.indicador='" + indicador + "'" + " and t.mes='" + mes + "'" + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 158 */       boolean rtaDB = this.dat.parseSql(s);
/* 159 */       if (!rtaDB) {
/* 160 */         return null;
/*     */       }
/* 162 */       this.rs = this.dat.getResultSet();
/* 163 */       if (this.rs.next()) {
/* 164 */         return leerRegistro();
/*     */       }
/*     */     }
/* 167 */     catch (Exception e) {
/* 168 */       e.printStackTrace();
/* 169 */       Utilidades.writeError("IndicadorResultadoDAO:cargarIndicadorResultado", e);
/*     */     } 
/* 171 */     return null;
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
/*     */   public RespuestaBD eliminarRegistro(int ciclo, String indicador, String mes) {
/* 183 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 186 */       String s = "delete from INDICADOR_RESULTADOS where  ciclo=" + ciclo + "  and indicador='" + indicador + "'" + "  and mes='" + mes + "'" + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 192 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 194 */     catch (Exception e) {
/* 195 */       e.printStackTrace();
/* 196 */       Utilidades.writeError("IndicadorResultadoDAO:eliminarRegistro ", e);
/* 197 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 199 */     return rta;
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
/*     */   public RespuestaBD crearRegistro(int ciclo, String indicador, String mes, double valorProgramado, double valorEjecutado, String estado) {
/* 215 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 218 */       String s = "insert into INDICADOR_RESULTADOS(ciclo,indicador,mes,valor_programado,valor_ejecutado,estado) values (" + ciclo + "," + "'" + indicador + "'," + "'" + mes + "'," + "" + valorProgramado + "," + "" + valorEjecutado + "," + "'" + estado + "'" + ")";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 233 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 235 */     catch (Exception e) {
/* 236 */       e.printStackTrace();
/* 237 */       Utilidades.writeError("%IndicadorResultadoDAO:crearRegistro ", e);
/* 238 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 240 */     return rta;
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
/*     */   public RespuestaBD modificarRegistro(int ciclo, String indicador, String mes, double valorProgramado, double valorEjecutado, String estado) {
/* 256 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 259 */       String s = "update INDICADOR_RESULTADOS set  valor_programado=" + valorProgramado + "," + " valor_ejecutado=" + valorEjecutado + "," + " estado='" + estado + "'" + " where" + " ciclo=" + ciclo + " and indicador='" + indicador + "'" + " and mes='" + mes + "'" + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 268 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 270 */     catch (Exception e) {
/* 271 */       e.printStackTrace();
/* 272 */       Utilidades.writeError("IndicadorResultadoDAO:modificarRegistro ", e);
/* 273 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 275 */     return rta;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\IndicadorResultadoDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */