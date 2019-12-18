/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.ContEstPrevActividadesDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.ContEstPrevActividadesDAO;
/*     */ import sinco.data.DBManager;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ContEstPrevActividadesDAO
/*     */ {
/*     */   ResultSet rs;
/*  19 */   DBManager dat = new DBManager();
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
/*  30 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/*     */     try {
/*  37 */       this.dat.close();
/*     */     }
/*  39 */     catch (Exception e) {
/*  40 */       Utilidades.writeError("SiauActividadesEstudioPrevio2DAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContEstPrevActividadesDTO next() {
/*     */     try {
/*  51 */       if (this.rs.next()) {
/*  52 */         return leerRegistro();
/*     */       }
/*     */     }
/*  55 */     catch (Exception e) {
/*  56 */       e.printStackTrace();
/*  57 */       Utilidades.writeError("SiauActividadesEstudioPrevio2DAO:next ", e);
/*     */     } 
/*  59 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContEstPrevActividadesDTO leerRegistro() {
/*     */     try {
/*  69 */       ContEstPrevActividadesDTO reg = new ContEstPrevActividadesDTO();
/*  70 */       reg.setConsecutivoActividad(this.rs.getInt("consecutivo_actividad"));
/*  71 */       reg.setPorcentaje(this.rs.getInt("porcentaje"));
/*  72 */       reg.setActividad(this.rs.getString("actividad"));
/*  73 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  74 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  75 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  76 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  77 */       return reg;
/*     */     }
/*  79 */     catch (Exception e) {
/*  80 */       e.printStackTrace();
/*  81 */       Utilidades.writeError("SiauActividadesEstudioPrevioDAO:leerRegistro ", e);
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
/*     */   public Collection<ContEstPrevActividadesDTO> cargarTodos(int numeroEstudio) {
/*  93 */     Collection<ContEstPrevActividadesDTO> resultados = new ArrayList<ContEstPrevActividadesDTO>();
/*     */     try {
/*  95 */       String s = "select   t.consecutivo_actividad, t.actividad, t.porcentaje, t.usuario_insercion, t.fecha_insercion, t.usuario_modificacion, t.fecha_modificacion from cont_est_prev_actividades t where  t.numero_estudio=" + numeroEstudio;
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
/* 106 */       s = s + " order by 1";
/* 107 */       boolean rtaDB = this.dat.parseSql(s);
/* 108 */       if (!rtaDB) {
/* 109 */         return resultados;
/*     */       }
/* 111 */       this.rs = this.dat.getResultSet();
/* 112 */       while (this.rs.next()) {
/* 113 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 116 */     catch (Exception e) {
/* 117 */       e.printStackTrace();
/* 118 */       Utilidades.writeError("SiauActividadesEstudioPrevio2DAO:cargarTodos ", e);
/*     */     } 
/* 120 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContEstPrevActividadesDTO cargarRegistro(int numeroEstudio, int consecutivoActividad) {
/*     */     try {
/* 132 */       String s = "select  t.consecutivo_actividad, t.actividad, t.porcentaje, t.usuario_insercion, t.fecha_insercion, t.usuario_modificacion, t.fecha_modificacion from cont_est_prev_actividades t where  t.numero_estudio=" + numeroEstudio + " and t.consecutivo_actividad=" + consecutivoActividad;
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
/* 144 */       boolean rtaDB = this.dat.parseSql(s);
/* 145 */       if (!rtaDB) {
/* 146 */         return null;
/*     */       }
/* 148 */       this.rs = this.dat.getResultSet();
/* 149 */       if (this.rs.next()) {
/* 150 */         return leerRegistro();
/*     */       }
/*     */     }
/* 153 */     catch (Exception e) {
/* 154 */       e.printStackTrace();
/* 155 */       Utilidades.writeError("SiauActividadesEstudioPrevio2DAO:cargarSiauActividadesEstudioPrevio", e);
/*     */     } 
/* 157 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int siguienteRegistro(int numeroEstudio) {
/* 168 */     int inumero = 1;
/* 169 */     String s = "select max(consecutivo_actividad)  from cont_est_prev_actividades  where  numero_estudio=" + numeroEstudio + "";
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 175 */       boolean rta = this.dat.parseSql(s);
/* 176 */       if (!rta) return 0; 
/* 177 */       this.rs = this.dat.getResultSet();
/* 178 */       if (this.rs.next()) {
/* 179 */         s = this.rs.getString(1);
/* 180 */         if (!this.rs.wasNull()) {
/* 181 */           inumero = Integer.parseInt(s) + 1;
/*     */         }
/*     */       } 
/* 184 */       return inumero;
/*     */     }
/* 186 */     catch (Exception e) {
/* 187 */       e.printStackTrace();
/* 188 */       Utilidades.writeError("SiauActividadesEstudioPrevio2DAO:siguienteRegistro ", e);
/*     */       
/* 190 */       return 0;
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
/*     */   public boolean eliminarRegistro(int numeroEstudio, int consecutivoActividad) {
/*     */     try {
/* 203 */       String s = "delete from cont_est_prev_actividades where  numero_estudio=" + numeroEstudio + " and consecutivo_actividad='" + consecutivoActividad + "'" + "";
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 208 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 211 */     catch (Exception e) {
/* 212 */       e.printStackTrace();
/* 213 */       Utilidades.writeError("SiauActividadesEstudioPrevioDAO:eliminarRegistro ", e);
/*     */       
/* 215 */       return false;
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
/*     */   public int crearRegistro(int numeroEstudio, String actividad, int porcentaje, String usuario) {
/* 229 */     int elSiguiente = siguienteRegistro(numeroEstudio);
/*     */ 
/*     */     
/* 232 */     if (elSiguiente == 0) {
/* 233 */       return 0;
/*     */     }
/*     */     
/*     */     try {
/* 237 */       String s = "insert into cont_est_prev_actividades(numero_estudio,consecutivo_actividad,actividad,porcentaje,usuario_insercion,fecha_insercion) values (" + numeroEstudio + "," + "'" + elSiguiente + "'," + "'" + actividad + "'," + "" + porcentaje + "," + "'" + usuario + "'," + "now()" + ")";
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
/* 252 */       this.dat.executeUpdate(s);
/* 253 */       return elSiguiente;
/*     */     }
/* 255 */     catch (Exception e) {
/* 256 */       e.printStackTrace();
/* 257 */       Utilidades.writeError("%SiauActividadesEstudioPrevio2DAO:crearRegistro ", e);
/*     */       
/* 259 */       return 0;
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
/*     */   public boolean modificarRegistro(int numeroEstudio, int consecutivoActividad, String actividad, int porcentaje, String usuario) {
/*     */     try {
/* 275 */       String s = "update cont_est_prev_actividades set  actividad='" + actividad + "'," + " porcentaje=" + porcentaje + " ,usuario_modificacion='" + usuario + "'" + " ,fecha_modificacion=now()" + " where" + " numero_estudio=" + numeroEstudio + " and consecutivo_actividad=" + consecutivoActividad + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 284 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 287 */     catch (Exception e) {
/* 288 */       e.printStackTrace();
/* 289 */       Utilidades.writeError("SiauActividadesEstudioPrevioDAO:modificarRegistro ", e);
/*     */       
/* 291 */       return false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\ContEstPrevActividadesDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */