/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.ContEstPrevSubactividadesDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.ContEstPrevSubactividadesDAO;
/*     */ import sinco.data.DBManager;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ContEstPrevSubactividadesDAO
/*     */ {
/*     */   ResultSet rs;
/*  20 */   DBManager dat = new DBManager();
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
/*  31 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/*     */     try {
/*  38 */       this.dat.close();
/*     */     }
/*  40 */     catch (Exception e) {
/*  41 */       Utilidades.writeError("SiauActividadesEstudioPrevio2DetalleDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContEstPrevSubactividadesDTO next() {
/*     */     try {
/*  52 */       if (this.rs.next()) {
/*  53 */         return leerRegistro();
/*     */       }
/*     */     }
/*  56 */     catch (Exception e) {
/*  57 */       e.printStackTrace();
/*  58 */       Utilidades.writeError("SiauActividadesEstudioPrevio2DetalleDAO:next ", e);
/*     */     } 
/*  60 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContEstPrevSubactividadesDTO leerRegistro() {
/*     */     try {
/*  70 */       ContEstPrevSubactividadesDTO reg = new ContEstPrevSubactividadesDTO();
/*  71 */       reg.setConsecutivoSubactividad(this.rs.getInt("consecutivo_subactividad"));
/*  72 */       reg.setSubActividad(this.rs.getString("subactividad"));
/*  73 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  74 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  75 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  76 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  77 */       return reg;
/*     */     }
/*  79 */     catch (Exception e) {
/*  80 */       e.printStackTrace();
/*  81 */       Utilidades.writeError("SiauActividadesEstudioPrevio2DetalleDAO:leerRegistro ", e);
/*     */       
/*  83 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<ContEstPrevSubactividadesDTO> cargarTodos(int numeroEstudio, int consecutivoActividad) {
/*  94 */     Collection<ContEstPrevSubactividadesDTO> resultados = new ArrayList<ContEstPrevSubactividadesDTO>();
/*     */     try {
/*  96 */       String s = "select   t.consecutivo_subactividad, t.subactividad, t.usuario_insercion, t.fecha_insercion, t.usuario_modificacion, t.fecha_modificacion from cont_est_prev_subactividades t where  t.numero_estudio=" + numeroEstudio + " and t.consecutivo_actividad=" + consecutivoActividad;
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
/* 107 */       s = s + " order by 1";
/* 108 */       boolean rtaDB = this.dat.parseSql(s);
/* 109 */       if (!rtaDB) {
/* 110 */         return resultados;
/*     */       }
/* 112 */       this.rs = this.dat.getResultSet();
/* 113 */       while (this.rs.next()) {
/* 114 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 117 */     catch (Exception e) {
/* 118 */       e.printStackTrace();
/* 119 */       Utilidades.writeError("SiauActividadesEstudioPrevio2DAO:cargarTodos ", e);
/*     */     } 
/* 121 */     return resultados;
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
/*     */   public ContEstPrevSubactividadesDTO cargarRegistro(int numeroEstudio, int consecutivoActividad, int consecutivoSubActividad) {
/*     */     try {
/* 134 */       String s = "select   t.consecutivo_subactividad, t.subactividad, t.usuario_insercion, t.fecha_insercion, t.usuario_modificacion, t.fecha_modificacion from cont_est_prev_subactividades t where  t.numero_estudio=" + numeroEstudio + " and t.consecutivo_actividad=" + consecutivoActividad + " and t.consecutivo_subactividad=" + consecutivoSubActividad;
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
/* 146 */       boolean rtaDB = this.dat.parseSql(s);
/* 147 */       if (!rtaDB) {
/* 148 */         return null;
/*     */       }
/* 150 */       this.rs = this.dat.getResultSet();
/* 151 */       if (this.rs.next()) {
/* 152 */         return leerRegistro();
/*     */       }
/*     */     }
/* 155 */     catch (Exception e) {
/* 156 */       e.printStackTrace();
/* 157 */       Utilidades.writeError("SiauActividadesEstudioPrevio2DetalleDAO:cargarSiauActividadesEstudioPrevio", e);
/*     */     } 
/* 159 */     return null;
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
/*     */   public int siguienteRegistro(int numeroEstudio, int consecutivoActividad) {
/* 171 */     int inumero = 1;
/* 172 */     String s = "select max(consecutivo_subactividad)  from cont_est_prev_subactividades  where  numero_estudio=" + numeroEstudio + " and consecutivo_actividad=" + consecutivoActividad;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 178 */       boolean rta = this.dat.parseSql(s);
/* 179 */       if (!rta) return 0; 
/* 180 */       this.rs = this.dat.getResultSet();
/* 181 */       if (this.rs.next()) {
/* 182 */         s = this.rs.getString(1);
/* 183 */         if (!this.rs.wasNull()) {
/* 184 */           inumero = Integer.parseInt(s) + 1;
/*     */         }
/*     */       } 
/* 187 */       return inumero;
/*     */     }
/* 189 */     catch (Exception e) {
/* 190 */       e.printStackTrace();
/* 191 */       Utilidades.writeError("SiauActividadesEstudioPrevio2DAO:siguienteRegistro ", e);
/*     */       
/* 193 */       return 0;
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
/*     */   public boolean eliminarRegistro(int numeroEstudio, int consecutivoActividad, int consecutivoSubactividad) {
/*     */     try {
/* 207 */       String s = "delete from cont_est_prev_subactividades where  numero_estudio=" + numeroEstudio + " and consecutivo_actividad='" + consecutivoActividad + "'" + " and consecutivo_subactividad=" + consecutivoSubactividad + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 213 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 216 */     catch (Exception e) {
/* 217 */       e.printStackTrace();
/* 218 */       Utilidades.writeError("SiauActividadesEstudioPrevioDAO:eliminarRegistro ", e);
/*     */       
/* 220 */       return false;
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
/*     */   public int crearRegistro(int numeroEstudio, int consecutivoActividad, String subActividad, String usuario) {
/* 234 */     int elSiguiente = siguienteRegistro(numeroEstudio, consecutivoActividad);
/*     */ 
/*     */ 
/*     */     
/* 238 */     if (elSiguiente == 0) {
/* 239 */       return 0;
/*     */     }
/*     */     
/*     */     try {
/* 243 */       String s = "insert into cont_est_prev_subactividades(numero_estudio,consecutivo_actividad,consecutivo_subactividad,subactividad,usuario_insercion,fecha_insercion) values (" + numeroEstudio + "," + "" + consecutivoActividad + "," + "" + elSiguiente + "," + "'" + subActividad + "'," + "'" + usuario + "'," + "now()" + ")";
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
/* 258 */       this.dat.executeUpdate(s);
/* 259 */       return elSiguiente;
/*     */     }
/* 261 */     catch (Exception e) {
/* 262 */       e.printStackTrace();
/* 263 */       Utilidades.writeError("%SiauActividadesEstudioPrevio2DetalleDAO:crearRegistro ", e);
/*     */       
/* 265 */       return 0;
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
/*     */   public boolean modificarRegistro(int numeroEstudio, int consecutivoActividad, int consecutivoSubactividad, String subactividad, String usuario) {
/*     */     try {
/* 281 */       String s = "update cont_est_prev_subactividades set subactividad='" + subactividad + "'," + " usuario_modificacion='" + usuario + "'" + " ,fecha_modificacion=now()" + " where" + " numero_estudio=" + numeroEstudio + " and consecutivo_actividad=" + consecutivoActividad + " and consecutivo_subactividad=" + consecutivoSubactividad;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 289 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 292 */     catch (Exception e) {
/* 293 */       e.printStackTrace();
/* 294 */       Utilidades.writeError("SiauActividadesEstudioPrevio2DetalleDAO:modificarRegistro ", e);
/*     */       
/* 296 */       return false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\ContEstPrevSubactividadesDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */