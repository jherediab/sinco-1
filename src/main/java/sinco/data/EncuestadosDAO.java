/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import sinco.business.Encuestados;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.EncuestadosDAO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EncuestadosDAO
/*     */ {
/*     */   ResultSet rs;
/*  27 */   DBManager dat = new DBManager();
/*     */ 
/*     */   
/*  30 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/*     */     try {
/*  39 */       this.dat.close();
/*     */     }
/*  41 */     catch (Exception e) {
/*  42 */       Utilidades.writeError("EncuestadosFactory.close", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Encuestados next() {
/*     */     try {
/*  52 */       if (this.rs.next()) {
/*  53 */         return leerRegistro();
/*     */       }
/*     */     }
/*  56 */     catch (Exception e) {
/*  57 */       e.printStackTrace();
/*  58 */       Utilidades.writeError("EncuestadosFactory.next ", e);
/*     */     } 
/*  60 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Encuestados leerRegistro() {
/*     */     try {
/*  69 */       Encuestados reg = new Encuestados();
/*  70 */       reg.setNumero(this.rs.getInt("numero"));
/*  71 */       reg.setProveedor(this.rs.getInt("proveedor"));
/*  72 */       reg.setAreaProveedor(this.rs.getInt("area_proveedor"));
/*  73 */       reg.setNombres(this.rs.getString("nombres"));
/*  74 */       reg.setApellidos(this.rs.getString("apellidos"));
/*  75 */       reg.setNombreAreaProveedor(this.rs.getString("nombre_area_proveedor"));
/*  76 */       reg.setNombreClase(this.rs.getString("nombre_clase"));
/*  77 */       return reg;
/*     */     }
/*  79 */     catch (Exception e) {
/*  80 */       e.printStackTrace();
/*  81 */       Utilidades.writeError("EncuestadosFactory.leerRegistro ", e);
/*     */       
/*  83 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean cargarTodos(int numero) {
/*     */     try {
/*  92 */       String s = "select e.*,p.nombres,p.apellidos,u.descripcion as nombre_area_proveedor,cu.descripcion as nombre_clase  from Encuestados e,sis_usuarios p,unidades_dependencia u,sis_usuarios_area pa,sis_grupos cu where e.proveedor=pa.codigo_empleado  and pa.codigo_empleado=p.codigo_empleado and pa.codigo_area=u.codigo  and pa.codigo_area=e.area_proveedor and pa.clase=cu.codigo and e.numero=" + numero + " and pa.area_principal='S'" + " order by p.apellidos,p.nombres,u.descripcion";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 106 */       boolean rta = this.dat.parseSql(s);
/* 107 */       if (!rta) return false; 
/* 108 */       this.rs = this.dat.getResultSet();
/* 109 */       return true;
/*     */     }
/* 111 */     catch (Exception e) {
/* 112 */       e.printStackTrace();
/* 113 */       Utilidades.writeError("EncuestadosFactory.cargarTodos ", e);
/*     */       
/* 115 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int cuantos(int numero) {
/*     */     try {
/* 124 */       String s = "select count(0) from encuestados where encuestados.numero=" + numero;
/* 125 */       boolean rta = this.dat.parseSql(s);
/* 126 */       if (!rta) return 0; 
/* 127 */       this.rs = this.dat.getResultSet();
/* 128 */       if (this.rs.next()) {
/* 129 */         String temp = this.rs.getString(1);
/* 130 */         if (!this.rs.wasNull()) {
/* 131 */           return Integer.parseInt(temp);
/*     */         }
/*     */       }
/*     */     
/* 135 */     } catch (Exception e) {
/* 136 */       e.printStackTrace();
/* 137 */       Utilidades.writeError("EncuestadosFactory.cuantos ", e);
/*     */     } 
/* 139 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean jefesArea(int numero, int servicio, String especializado, String usuario) {
/*     */     try {
/* 149 */       String s = "insert into encuestados (numero,proveedor,area_proveedor,fecha_insercion,usuario_insercion)";
/* 150 */       s = s + " select distinct " + numero + ",pa.codigo_empleado,pa.codigo_area," + Utilidades.formatoFecha(Utilidades.ahora()) + ",'" + usuario + "'";
/* 151 */       s = s + " from unidades_dependencia u,servicios_area sa,sis_usuarios_area pa,sis_usuarios p";
/* 152 */       if (especializado.equals("M")) {
/* 153 */         s = s + ",proveedor_multiple pm";
/*     */       }
/*     */       
/* 156 */       s = s + " where u.codigo=sa.codigo_area" + " and pa.codigo_area = sa.codigo_area" + " and pa.responsable_area='S'" + " and pa.clase not in (99) " + " and pa.Area_Principal='S'" + " and pa.codigo_empleado=p.codigo_empleado" + " and p.estado = 'A'";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 164 */       if (especializado.equals("M")) {
/* 165 */         s = s + " and pm.codigo_area=u.codigo";
/* 166 */         s = s + " and pm.codigo_servicio=sa.codigo_servicio";
/* 167 */         s = s + " and pm.persona_cargo=pa.codigo_empleado";
/*     */       } 
/* 169 */       if (especializado.equals("S")) {
/* 170 */         s = s + " and sa.persona_cargo=pa.codigo_empleado";
/*     */       }
/*     */       
/* 173 */       s = s + "  and u.estado='A' " + " and sa.codigo_servicio=" + servicio + " and pa.codigo_empleado not in ( select proveedor from encuestados where numero=" + numero + ")";
/*     */ 
/*     */       
/* 176 */       return this.dat.executeUpdate(s);
/*     */     }
/* 178 */     catch (Exception e) {
/*     */       
/* 180 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean incluirTodos(int numero, int servicio, String especializado, String usuario) {
/*     */     try {
/* 189 */       String s = "insert into encuestados (numero,proveedor,area_proveedor,fecha_insercion,usuario_insercion)";
/* 190 */       s = s + " select distinct " + numero + ",p.codigo_empleado,pa.codigo_area," + Utilidades.formatoFecha(Utilidades.ahora()) + ",'" + usuario + "'";
/* 191 */       s = s + " from sis_usuarios p,servicios_area sa,sis_usuarios_area pa";
/* 192 */       if (especializado.equals("M")) {
/* 193 */         s = s + ",proveedor_multiple pm";
/*     */       }
/*     */       
/* 196 */       s = s + " where p.estado = 'A' " + " and pa.clase not in (99) " + " and pa.Area_Principal='S'";
/*     */ 
/*     */       
/* 199 */       if (especializado.equals("M")) {
/* 200 */         s = s + " and pm.codigo_area=pa.codigo_area";
/* 201 */         s = s + " and pm.persona_cargo=pa.codigo_empleado";
/* 202 */         s = s + " and pm.codigo_servicio=sa.codigo_servicio";
/*     */       } 
/* 204 */       s = s + " and pa.codigo_area = sa.codigo_area" + " and pa.codigo_empleado=p.codigo_empleado";
/*     */       
/* 206 */       if (especializado.equals("S")) {
/* 207 */         s = s + " and sa.persona_cargo=p.codigo_empleado";
/*     */       }
/* 209 */       s = s + " and sa.codigo_servicio=" + servicio;
/* 210 */       s = s + " and p.codigo_empleado not in ( " + "select proveedor from encuestados where numero=" + numero + ")";
/*     */       
/* 212 */       return this.dat.executeUpdate(s);
/*     */     }
/* 214 */     catch (Exception e) {
/*     */       
/* 216 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean convocatoria(int numero, String usuario) {
/*     */     try {
/* 225 */       String s = "insert into encuestados (numero,proveedor,area_proveedor,fecha_insercion,usuario_insercion)";
/* 226 */       s = s + " select distinct " + numero + ",p.codigo_empleado,pa.codigo_area," + Utilidades.formatoFecha(Utilidades.ahora()) + ",'" + usuario + "'";
/* 227 */       s = s + " from sis_usuarios p,sis_usuarios_area pa" + " where p.codigo_empleado=pa.codigo_empleado " + " and p.auditor in(1) " + " and pa.clase not in (99) " + " and p.estado='A'" + " and pa.area_principal='S'" + " and p.codigo_empleado not in ( select proveedor from encuestados where numero=" + numero + ")";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 234 */       return this.dat.executeUpdate(s);
/*     */     }
/* 236 */     catch (Exception e) {
/*     */       
/* 238 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean auditoresLideres(int numero, String ciclo, String usuario) {
/*     */     try {
/* 247 */       String s = "insert into encuestados (numero,proveedor,area_proveedor,fecha_insercion,usuario_insercion)";
/* 248 */       s = s + " select distinct " + numero + ",da.codigo_empleado,pa.codigo_area," + Utilidades.formatoFecha(Utilidades.ahora()) + ",'" + usuario + "'" + " from detalle_auditoria da,ciclos_auditoria ca,sis_usuarios_area pa" + " where da.ciclo=ca.ciclo " + " and da.codigo_empleado=pa.codigo_empleado" + " and ca.ciclo='" + ciclo + "'" + " and da.rol=1" + " and pa.area_principal='S'" + " and pa.clase not in (99) " + " and da.codigo_empleado not in ( select proveedor from encuestados where numero=" + numero + ")";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 257 */       return this.dat.executeUpdate(s);
/*     */     }
/* 259 */     catch (Exception e) {
/*     */       
/* 261 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean incluirArea(int numero, int area, int servicio, String especializado, String usuario) {
/*     */     try {
/* 273 */       String s = "insert into encuestados (numero,proveedor,area_proveedor,fecha_insercion,usuario_insercion)";
/* 274 */       s = s + " select distinct " + numero + ",p.codigo_empleado,pa.codigo_area," + Utilidades.formatoFecha(Utilidades.ahora()) + ",'" + usuario + "'";
/* 275 */       s = s + " from sis_usuarios p,servicios_area sa,sis_usuarios_area pa";
/* 276 */       if (especializado.equals("M")) {
/* 277 */         s = s + ",proveedor_multiple pm";
/*     */       }
/*     */       
/* 280 */       s = s + " where " + " p.codigo_empleado=pa.codigo_empleado" + " and sa.codigo_area=pa.codigo_area" + " and pa.clase not in (99) ";
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 285 */       if (especializado.equals("M")) {
/* 286 */         s = s + "  and pm.codigo_area=pa.codigo_area";
/* 287 */         s = s + "  and pm.persona_cargo=p.codigo_empleado";
/* 288 */         s = s + "  and pm.codigo_servicio=sa.codigo_servicio";
/*     */       } 
/*     */       
/* 291 */       if (especializado.equals("S")) {
/* 292 */         s = s + " and sa.persona_cargo=p.codigo_empleado";
/*     */       }
/*     */       
/* 295 */       s = s + " and p.estado='A'" + " and pa.area_principal='S'";
/*     */       
/* 297 */       s = s + " and pa.codigo_area=" + area;
/* 298 */       s = s + " and sa.codigo_servicio=" + servicio;
/* 299 */       s = s + " and p.codigo_empleado not in ( select proveedor from encuestados where numero=" + numero + ")";
/* 300 */       return this.dat.executeUpdate(s);
/*     */     }
/* 302 */     catch (Exception e) {
/*     */       
/* 304 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean incluirPersona(int numero, int persona, int area, String usuario) {
/*     */     try {
/* 313 */       String s = "insert into encuestados (numero,proveedor,area_proveedor,fecha_insercion,usuario_insercion) values(" + numero + "," + persona + "," + area + "," + "" + Utilidades.formatoFecha(Utilidades.ahora()) + "," + "'" + usuario + "'" + ")";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 319 */       return this.dat.executeUpdate(s);
/*     */     }
/* 321 */     catch (Exception e) {
/*     */       
/* 323 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean borrarPersona(int numero, int persona, int area) {
/*     */     try {
/* 332 */       String s = "delete from encuestados  where numero=" + numero + " and proveedor=" + persona + " and area_proveedor=" + area;
/*     */ 
/*     */ 
/*     */       
/* 336 */       return this.dat.executeUpdate(s);
/*     */     }
/* 338 */     catch (Exception e) {
/*     */       
/* 340 */       return false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\EncuestadosDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */