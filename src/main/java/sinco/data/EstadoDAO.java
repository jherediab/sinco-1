/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.EstadoDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.EstadoDAO;
/*     */ 
/*     */ public class EstadoDAO {
/*     */   ResultSet rs;
/*     */   
/*     */   public void close() {
/*     */     try {
/*  17 */       this.dat.close();
/*     */     }
/*  19 */     catch (Exception e) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  27 */   DBManager dat = new DBManager();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  34 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EstadoDTO leerRegistro() {
/*     */     try {
/*  44 */       EstadoDTO reg = new EstadoDTO();
/*  45 */       reg.setCodigo(this.rs.getInt("codigo"));
/*  46 */       reg.setTipoEstado(this.rs.getString("tipo_estado"));
/*  47 */       reg.setDescripcion(this.rs.getString("descripcion"));
/*  48 */       if (this.rs.getString("califica").toLowerCase().equals("s")) {
/*  49 */         reg.setCalifica(true);
/*     */       } else {
/*     */         
/*  52 */         reg.setCalifica(false);
/*     */       } 
/*  54 */       if (this.rs.getString("confiabilidad").toLowerCase().equals("s")) {
/*  55 */         reg.setConfiabilidad(true);
/*     */       } else {
/*     */         
/*  58 */         reg.setConfiabilidad(false);
/*     */       } 
/*  60 */       if (this.rs.getString("oportunidad").toLowerCase().equals("s")) {
/*  61 */         reg.setOportunidad(true);
/*     */       } else {
/*     */         
/*  64 */         reg.setOportunidad(false);
/*     */       } 
/*  66 */       if (this.rs.getString("cierra").toLowerCase().equals("s")) {
/*  67 */         reg.setCierra(true);
/*     */       } else {
/*     */         
/*  70 */         reg.setCierra(false);
/*     */       } 
/*  72 */       String temp = this.rs.getString("rol_genera_mail");
/*  73 */       if (this.rs.wasNull()) {
/*  74 */         reg.setRolGeneraMail(-1);
/*     */       } else {
/*     */         
/*  77 */         reg.setRolGeneraMail(Integer.parseInt(temp));
/*     */       } 
/*     */       
/*  80 */       reg.setPorcentaje(this.rs.getInt("porcentaje_aparece"));
/*     */       
/*  82 */       return reg;
/*     */     }
/*  84 */     catch (Exception e) {
/*  85 */       e.printStackTrace();
/*  86 */       Utilidades.writeError("EstadoFactory.leerRegistro ", e);
/*     */       
/*  88 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean cargarTodosTipo(String tipoestado) {
/*     */     try {
/*  98 */       String temp = "select * from estados where tipo_estado='" + tipoestado + "'";
/*  99 */       boolean rta = this.dat.parseSql(temp);
/* 100 */       if (!rta) return false; 
/* 101 */       this.rs = this.dat.getResultSet();
/* 102 */       return true;
/*     */     }
/* 104 */     catch (Exception e) {
/* 105 */       e.printStackTrace();
/*     */       
/* 107 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean cargarTodosSiguientesParaEstadoParaCliente(int est) {
/*     */     try {
/* 113 */       String temp = "select * from estados,secuencia_estados  where codigo_estado_inicial=" + est + " and codigo_estado_final=codigo " + " and codigo_rol=2 ";
/*     */ 
/*     */ 
/*     */       
/* 117 */       boolean rta = this.dat.parseSql(temp);
/* 118 */       if (!rta) return false; 
/* 119 */       this.rs = this.dat.getResultSet();
/* 120 */       return true;
/*     */     }
/* 122 */     catch (Exception e) {
/* 123 */       e.printStackTrace();
/*     */       
/* 125 */       return false;
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
/*     */   public boolean cargarTodosSiguientesParaEstadoParaProveedor(int est, float porcentaje, int devoluciones) {
/*     */     try {
/* 140 */       String temp = "select * from estados e,secuencia_estados s  where s.codigo_estado_inicial=" + est + " and s.codigo_estado_final=e.codigo " + " and s.codigo_rol=3 " + " and " + porcentaje + "<=e.porcentaje_aparece";
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 145 */       if (devoluciones > 0) {
/* 146 */         temp = temp + " and e.tipo_estado not in('DV')";
/*     */       }
/* 148 */       temp = temp + " ORDER BY CASE WHEN e.TIPO_ESTADO='CAL' THEN 1 ELSE 2 END , E.DESCRIPCION";
/* 149 */       boolean rta = this.dat.parseSql(temp);
/* 150 */       if (!rta) return false; 
/* 151 */       this.rs = this.dat.getResultSet();
/* 152 */       return true;
/*     */     }
/* 154 */     catch (Exception e) {
/* 155 */       e.printStackTrace();
/*     */       
/* 157 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EstadoDTO next() {
/*     */     try {
/* 167 */       if (this.rs.next()) {
/* 168 */         return leerRegistro();
/*     */       }
/*     */     }
/* 171 */     catch (SQLException e) {
/* 172 */       e.printStackTrace();
/*     */     } 
/* 174 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EstadoDTO getEstado(int codigo) {
/*     */     try {
/* 184 */       boolean rta = this.dat.parseSql("select * from estados where codigo=" + codigo);
/* 185 */       if (!rta) return null; 
/* 186 */       this.rs = this.dat.getResultSet();
/* 187 */       if (this.rs.next()) {
/* 188 */         return leerRegistro();
/*     */       
/*     */       }
/*     */     }
/* 192 */     catch (SQLException e) {
/* 193 */       e.printStackTrace();
/* 194 */       Utilidades.writeError("EstadoFactory:getEstado ", e);
/*     */     } 
/* 196 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EstadoDTO getEstado(String codigo) {
/*     */     try {
/* 207 */       String s = "select * from estados  where codigo in(  SELECT codigo FROM estados     WHERE tipo_estado='" + codigo + "'" + ")";
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 212 */       boolean rta = this.dat.parseSql(s);
/* 213 */       if (!rta) return null; 
/* 214 */       this.rs = this.dat.getResultSet();
/* 215 */       if (this.rs.next()) {
/* 216 */         return leerRegistro();
/*     */       
/*     */       }
/*     */     }
/* 220 */     catch (SQLException e) {
/* 221 */       e.printStackTrace();
/* 222 */       Utilidades.writeError("EstadoFactory:getEstado ", e);
/*     */     } 
/* 224 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection todosLosEstados() {
/* 235 */     Collection resultados = new ArrayList();
/*     */     try {
/* 237 */       String s = "select * from estados e order by descripcion";
/*     */       
/* 239 */       boolean rtaDB = this.dat.parseSql(s);
/* 240 */       if (!rtaDB) {
/* 241 */         return resultados;
/*     */       }
/* 243 */       this.rs = this.dat.getResultSet();
/* 244 */       while (this.rs.next()) {
/* 245 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 248 */     catch (Exception e) {
/* 249 */       e.printStackTrace();
/* 250 */       Utilidades.writeError("EstadoFactory::todosLosEstados", e);
/*     */     } 
/* 252 */     return resultados;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\EstadoDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */