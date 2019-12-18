/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.CaracteristicasValorDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.CaracteristicasValorDAO;
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
/*     */ public class CaracteristicasValorDAO
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
/*  50 */       Utilidades.writeError("CaracteristicasValorDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CaracteristicasValorDTO next() {
/*     */     try {
/*  61 */       if (this.rs.next()) {
/*  62 */         return leerRegistro();
/*     */       }
/*     */     }
/*  65 */     catch (Exception e) {
/*  66 */       e.printStackTrace();
/*  67 */       Utilidades.writeError("CaracteristicasValorDAO:next ", e);
/*     */     } 
/*  69 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CaracteristicasValorDTO leerRegistro() {
/*     */     try {
/*  79 */       CaracteristicasValorDTO reg = new CaracteristicasValorDTO();
/*     */       
/*  81 */       reg.setCaracteristica(this.rs.getInt("caracteristica"));
/*  82 */       reg.setValor(this.rs.getInt("valor"));
/*  83 */       reg.setDescripcion(this.rs.getString("descripcion"));
/*  84 */       reg.setAreaAplica(this.rs.getInt("area_aplica"));
/*  85 */       reg.setDuracion(this.rs.getInt("duracion"));
/*  86 */       reg.setUnidadMedida(this.rs.getString("unidad_medida"));
/*  87 */       reg.setValorPadre(this.rs.getInt("valor_padre"));
/*  88 */       reg.setEstado(this.rs.getString("estado"));
/*  89 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  90 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  91 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  92 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*     */ 
/*     */       
/*     */       try {
/*  96 */         reg.setNombreUnidadMedida(this.rs.getString("nombre_unidad_medida"));
/*  97 */       } catch (Exception e) {}
/*     */ 
/*     */       
/* 100 */       return reg;
/*     */     }
/* 102 */     catch (Exception e) {
/* 103 */       e.printStackTrace();
/* 104 */       Utilidades.writeError("CaracteristicasValorDAO:leerRegistro ", e);
/*     */       
/* 106 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<CaracteristicasValorDTO> cargarTodos(int caracteristica) {
/* 116 */     Collection<CaracteristicasValorDTO> resultados = new ArrayList<CaracteristicasValorDTO>();
/*     */     try {
/* 118 */       String s = "select t.caracteristica,t.valor,t.descripcion,t.area_aplica,t.duracion,t.unidad_medida,r2.nombre_unidad as nombre_unidad_medida,t.valor_padre,t.estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from caracteristicas_valor t  left join sis_unidades_medida r2 on (r2.codigo_unidad=t.unidad_medida) where t.caracteristica=" + caracteristica + " order by 1,2";
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
/* 136 */       boolean rtaDB = this.dat.parseSql(s);
/* 137 */       if (!rtaDB) {
/* 138 */         return resultados;
/*     */       }
/* 140 */       this.rs = this.dat.getResultSet();
/* 141 */       while (this.rs.next()) {
/* 142 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 145 */     catch (Exception e) {
/* 146 */       e.printStackTrace();
/* 147 */       Utilidades.writeError("CaracteristicasValorDAO:cargarTodos ", e);
/*     */     } 
/* 149 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CaracteristicasValorDTO cargarRegistro(int caracteristica, int valor) {
/*     */     try {
/* 160 */       String s = "select t.caracteristica,t.valor,t.descripcion,t.area_aplica,t.duracion,t.unidad_medida,t.valor_padre,t.estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from caracteristicas_valor t  where t.caracteristica=" + caracteristica + " and t.valor=" + valor;
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
/* 176 */       boolean rtaDB = this.dat.parseSql(s);
/* 177 */       if (!rtaDB) {
/* 178 */         return null;
/*     */       }
/* 180 */       this.rs = this.dat.getResultSet();
/* 181 */       if (this.rs.next()) {
/* 182 */         return leerRegistro();
/*     */       }
/*     */     }
/* 185 */     catch (Exception e) {
/* 186 */       e.printStackTrace();
/* 187 */       Utilidades.writeError("CaracteristicasValorDAO:cargarCaracteristicasValor", e);
/*     */     } 
/* 189 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarRegistro(int caracteristica, int valor) {
/* 200 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 203 */       String s = "delete from caracteristicas_valor where  caracteristica=" + caracteristica + "  and valor=" + valor + "";
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 208 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 210 */     catch (Exception e) {
/* 211 */       e.printStackTrace();
/* 212 */       Utilidades.writeError("CaracteristicasValorDAO:eliminarRegistro ", e);
/* 213 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 215 */     return rta;
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
/*     */   public RespuestaBD crearRegistro(int caracteristica, int valor, String descripcion, int areaAplica, int duracion, String unidadMedida, int valorPadre, String estado, String usuarioInsercion) {
/* 234 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 237 */       String s = "insert into caracteristicas_valor(caracteristica,valor,descripcion,area_aplica,duracion,unidad_medida,valor_padre,estado,fecha_insercion,usuario_insercion) values (" + caracteristica + "," + "" + valor + "," + "'" + descripcion + "'," + "" + ((areaAplica > 0) ? ("" + areaAplica) : "null") + "," + "" + ((duracion > 0) ? ("" + duracion) : "null") + "," + "'" + unidadMedida + "'," + "" + ((valorPadre > 0) ? ("" + valorPadre) : "null") + "," + "'" + estado + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
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
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 260 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 262 */     catch (Exception e) {
/* 263 */       e.printStackTrace();
/* 264 */       Utilidades.writeError("%CaracteristicasValorDAO:crearRegistro ", e);
/* 265 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 267 */     return rta;
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
/*     */   public RespuestaBD modificarRegistro(int caracteristica, int valor, String descripcion, int areaAplica, int duracion, String unidadMedida, int valorPadre, String estado, String usuarioModificacion) {
/* 286 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 289 */       String s = "update caracteristicas_valor set  descripcion='" + descripcion + "'," + " area_aplica=" + ((areaAplica > 0) ? ("" + areaAplica) : "null") + "," + " duracion=" + ((duracion > 0) ? ("" + duracion) : "null") + "," + " unidad_medida='" + unidadMedida + "'," + " valor_padre=" + ((valorPadre > 0) ? ("" + valorPadre) : "null") + "," + " estado='" + estado + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " caracteristica=" + caracteristica + " and valor=" + valor + "";
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
/* 302 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 304 */     catch (Exception e) {
/* 305 */       e.printStackTrace();
/* 306 */       Utilidades.writeError("CaracteristicasValorDAO:modificarRegistro ", e);
/* 307 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 309 */     return rta;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<CaracteristicasValorDTO> cargarParaCaracteristica(int caracteristica) {
/* 320 */     Collection<CaracteristicasValorDTO> resultados = new ArrayList<CaracteristicasValorDTO>();
/*     */     try {
/* 322 */       String s = "select c.valor,c.descripcion  from caracteristicas_valor c where c.caracteristica = " + caracteristica + "  order by c.descripcion";
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 327 */       boolean rta = this.dat.parseSql(s);
/* 328 */       if (!rta) return resultados;
/*     */       
/* 330 */       this.rs = this.dat.getResultSet();
/* 331 */       while (this.rs.next())
/*     */       {
/* 333 */         CaracteristicasValorDTO reg = new CaracteristicasValorDTO();
/* 334 */         reg.setValor(this.rs.getInt("valor"));
/* 335 */         reg.setDescripcion(this.rs.getString("descripcion"));
/* 336 */         resultados.add(reg);
/*     */       }
/*     */     
/* 339 */     } catch (Exception e) {
/* 340 */       e.printStackTrace();
/* 341 */       Utilidades.writeError("CaracteristicasDAO:cargarTodosParaServicio ", e);
/*     */     } 
/* 343 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<CaracteristicasValorDTO> cargarAnidadas(int caracteristica, int valorCaracteristica) {
/* 354 */     Collection<CaracteristicasValorDTO> resultados = new ArrayList<CaracteristicasValorDTO>();
/*     */     
/*     */     try {
/* 357 */       String s = "select cv.valor,cv.descripcion from caracteristicas c, caracteristicas_valor  o, caracteristicas_valor cv where c.codigo=o.caracteristica and c.caracteristica_anida=cv.caracteristica and o.valor=cv.valor_padre and c.codigo=" + caracteristica + " and o.valor=" + valorCaracteristica + " and cv.estado='A'" + " and o.estado='A'";
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
/* 369 */       boolean rta = this.dat.parseSql(s);
/* 370 */       if (!rta) {
/* 371 */         return resultados;
/*     */       }
/*     */       
/* 374 */       this.rs = this.dat.getResultSet();
/* 375 */       while (this.rs.next())
/*     */       {
/* 377 */         CaracteristicasValorDTO reg = new CaracteristicasValorDTO();
/*     */         
/* 379 */         reg.setValor(this.rs.getInt("valor"));
/* 380 */         reg.setDescripcion(this.rs.getString("descripcion"));
/* 381 */         resultados.add(reg);
/*     */       }
/*     */     
/* 384 */     } catch (Exception e) {
/* 385 */       e.printStackTrace();
/* 386 */       Utilidades.writeError("CaracteristicasValorFactory.cargarTodos ", e);
/*     */     } 
/* 388 */     return resultados;
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
/*     */   public Collection<CaracteristicasValorDTO> cargarTodos(int caracteristica, int areaProveedor, String tipoCaracteristica, int valorDepende) {
/* 403 */     Collection<CaracteristicasValorDTO> resultados = new ArrayList<CaracteristicasValorDTO>();
/*     */     
/*     */     try {
/* 406 */       String s = "";
/* 407 */       if (tipoCaracteristica.equals("8")) {
/* 408 */         s = "select cv.caracteristica, cv.valor, cv.descripcion cv.usuario_insercion, cv.fecha_insercion, cv.usuario_modificacion, cv.fecha_modificacion from caracteristicas_valor cv where  cv.caracteristica=" + caracteristica + "       and cv.estado='A'" + "       and coalesce(cv.area_aplica ," + areaProveedor + ")=" + areaProveedor + " order by cv.descripcion";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 422 */         s = "select cv.* from caracteristicas_valor cv where cv.caracteristica=" + caracteristica + "       and cv.estado='A'" + "       and coalesce(cv.area_aplica ," + areaProveedor + ")=" + areaProveedor + "       and 1= CASE WHEN cv.VALOR_PADRE IS NULL THEN 1 when " + valorDepende + "=0 then 1 WHEN  cv.VALOR_PADRE = " + valorDepende + " THEN 1 ELSE 0 END" + " order by cv.descripcion";
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 429 */       boolean rta = this.dat.parseSql(s);
/* 430 */       if (!rta) {
/* 431 */         return resultados;
/*     */       }
/*     */       
/* 434 */       this.rs = this.dat.getResultSet();
/* 435 */       while (this.rs.next()) {
/* 436 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 439 */     catch (Exception e) {
/* 440 */       e.printStackTrace();
/* 441 */       Utilidades.writeError("CaracteristicasValorFactory.cargarTodos ", e);
/*     */     } 
/* 443 */     return resultados;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\CaracteristicasValorDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */