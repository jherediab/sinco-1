/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.ContEstudioPrevioTarifasDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.ContEstudioPrevioTarifasDAO;
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
/*     */ public class ContEstudioPrevioTarifasDAO
/*     */ {
/*     */   ResultSet rs;
/*  24 */   DBManager dat = new DBManager();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  33 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/*     */     try {
/*  40 */       this.dat.close();
/*     */     }
/*  42 */     catch (Exception e) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContEstudioPrevioTarifasDTO next() {
/*     */     try {
/*  53 */       if (this.rs.next()) {
/*  54 */         return leerRegistro();
/*     */       }
/*     */     }
/*  57 */     catch (Exception e) {
/*  58 */       e.printStackTrace();
/*  59 */       Utilidades.writeError("SiauTarifasEstudioPrevioDAO:next ", e);
/*     */     } 
/*  61 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContEstudioPrevioTarifasDTO leerRegistro() {
/*     */     try {
/*  71 */       ContEstudioPrevioTarifasDTO reg = new ContEstudioPrevioTarifasDTO();
/*  72 */       reg.setNumeroEstudio(this.rs.getInt("numero_estudio"));
/*  73 */       reg.setTipoCuadro(this.rs.getString("tipo_cuadro"));
/*  74 */       reg.setTitulo(this.rs.getString("titulo"));
/*  75 */       reg.setConsecutivo(this.rs.getInt("consecutivo"));
/*  76 */       reg.setValor1(this.rs.getString("valor_1"));
/*  77 */       reg.setValor2(this.rs.getString("valor_2"));
/*  78 */       reg.setValor3(this.rs.getString("valor_3"));
/*  79 */       reg.setValor4(this.rs.getString("valor_4"));
/*  80 */       reg.setValor5(this.rs.getString("valor_5"));
/*  81 */       reg.setValor6(this.rs.getString("valor_6"));
/*  82 */       reg.setnombreCuadro(this.rs.getString("nombre_cuadro"));
/*  83 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  84 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  85 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  86 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  87 */       return reg;
/*     */     }
/*  89 */     catch (Exception e) {
/*  90 */       e.printStackTrace();
/*  91 */       Utilidades.writeError("SiauTarifasEstudioPrevioDAO:leerRegistro ", e);
/*     */       
/*  93 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<ContEstudioPrevioTarifasDTO> cargarTodos(int numeroEstudio) {
/* 102 */     Collection<ContEstudioPrevioTarifasDTO> resultados = new ArrayList<ContEstudioPrevioTarifasDTO>();
/*     */     try {
/* 104 */       String s = "select TAR.*, SIS.descripcion as nombre_cuadro  from cont_estudio_previo_tarifas TAR left join sis_multivalores SIS on (SIS.valor= TAR.tipo_cuadro)  where numero_estudio=" + numeroEstudio;
/*     */ 
/*     */ 
/*     */       
/* 108 */       s = s + " and SIS.tabla='cuadro_tarifa' order by tipo_cuadro,titulo,consecutivo";
/* 109 */       boolean rtaDB = this.dat.parseSql(s);
/* 110 */       if (!rtaDB) {
/* 111 */         return resultados;
/*     */       }
/* 113 */       this.rs = this.dat.getResultSet();
/* 114 */       while (this.rs.next()) {
/* 115 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 118 */     catch (Exception e) {
/* 119 */       e.printStackTrace();
/* 120 */       Utilidades.writeError("SiauTarifasEstudioPrevioDAO:cargarTodos ", e);
/*     */     } 
/* 122 */     return resultados;
/*     */   }
/*     */ 
/*     */   
/*     */   public Collection<ContEstudioPrevioTarifasDTO> cargarTodosPorCuadro(int numeroEstudio, String tipoCuadro, String titulo) {
/* 127 */     Collection<ContEstudioPrevioTarifasDTO> resultados = new ArrayList<ContEstudioPrevioTarifasDTO>();
/*     */     try {
/* 129 */       String s = "select TAR.*, SIS.descripcion as nombre_cuadro  from cont_estudio_previo_tarifas TAR left join sis_multivalores SIS on (SIS.valor= TAR.tipo_cuadro)  where numero_estudio=" + numeroEstudio + " and TAR.tipo_cuadro='" + tipoCuadro + "'" + " and TAR.titulo='" + titulo + "'";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 135 */       s = s + " and SIS.tabla='CUADRO_TARIFA' order by tipo_cuadro,titulo,consecutivo";
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
/* 147 */       Utilidades.writeError("SiauTarifasEstudioPrevioDAO:cargarTodos ", e);
/*     */     } 
/* 149 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContEstudioPrevioTarifasDTO cargarRegistro(int numeroEstudio, String tipoCuadro, String titulo, int consecutivo) {
/*     */     try {
/* 159 */       String s = "select TAR.*, SIS.descripcion as nombre_cuadro  from cont_estudio_previo_tarifas TAR left join sis_multivalores SIS on (SIS.valor= TAR.tipo_cuadro)  where  numero_estudio=" + numeroEstudio + " and tipo_cuadro='" + tipoCuadro + "'" + " and TAR.titulo='" + titulo + "'" + " and consecutivo=" + consecutivo + " and SIS.tabla='cuadro_tarifa' order by tipo_cuadro,titulo,consecutivo";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 169 */       boolean rtaDB = this.dat.parseSql(s);
/* 170 */       if (!rtaDB) {
/* 171 */         return null;
/*     */       }
/* 173 */       this.rs = this.dat.getResultSet();
/* 174 */       if (this.rs.next()) {
/* 175 */         return leerRegistro();
/*     */       }
/*     */     }
/* 178 */     catch (Exception e) {
/* 179 */       e.printStackTrace();
/* 180 */       Utilidades.writeError("SiauTarifasEstudioPrevioDAO:cargarSiauTarifasEstudioPrevio ", e);
/*     */     } 
/* 182 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int siguienteRegistro(int numeroEstudio, String tipoCuadro, String titulo) {
/* 191 */     int inumero = 1;
/* 192 */     String s = "select max(consecutivo) from cont_estudio_previo_tarifas  where  numero_estudio=" + numeroEstudio + " and tipo_cuadro='" + tipoCuadro + "'" + " and titulo='" + titulo + "'" + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 199 */       boolean rta = this.dat.parseSql(s);
/* 200 */       if (!rta) return 0; 
/* 201 */       this.rs = this.dat.getResultSet();
/* 202 */       if (this.rs.next()) {
/* 203 */         s = this.rs.getString(1);
/* 204 */         if (!this.rs.wasNull()) {
/* 205 */           inumero = Integer.parseInt(s) + 1;
/*     */         }
/*     */       } 
/* 208 */       return inumero;
/*     */     }
/* 210 */     catch (Exception e) {
/* 211 */       e.printStackTrace();
/* 212 */       Utilidades.writeError("SiauTarifasEstudioPrevioDAO:siguienteRegistro ", e);
/*     */       
/* 214 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eliminarRegistro(int numeroEstudio, String tipoCuadro, String titulo, int consecutivo) {
/*     */     try {
/* 224 */       String s = "delete from cont_estudio_previo_tarifas where   numero_estudio=" + numeroEstudio + "  and  tipo_cuadro='" + tipoCuadro + "'" + " and titulo='" + titulo + "'" + "  and  consecutivo=" + consecutivo + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 231 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 234 */     catch (Exception e) {
/* 235 */       e.printStackTrace();
/* 236 */       Utilidades.writeError("SiauTarifasEstudioPrevioDAO:eliminarRegistro ", e);
/*     */       
/* 238 */       return false;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean crearRegistro(int numeroEstudio, String tipoCuadro, String titulo, String valor1, String valor2, String valor3, String valor4, String valor5, String valor6, String tipo, String usuarioInsercion) {
/* 259 */     int elSiguiente = siguienteRegistro(numeroEstudio, tipoCuadro, titulo);
/* 260 */     if (elSiguiente == 0) {
/* 261 */       return false;
/*     */     }
/*     */     
/*     */     try {
/* 265 */       String s = "insert into cont_estudio_previo_tarifas (numero_estudio,tipo_cuadro,titulo,consecutivo,valor_1,valor_2,valor_3,valor_4,valor_5,valor_6,tipo,fecha_insercion,usuario_insercion) values (" + numeroEstudio + "," + "'" + tipoCuadro + "'," + "'" + titulo + "'," + "" + elSiguiente + "," + "'" + valor1 + "'," + "'" + valor2 + "'," + "'" + valor3 + "'," + "'" + valor4 + "'," + "'" + valor5 + "'," + "'" + valor6 + "'," + "'" + tipo + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 294 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 297 */     catch (Exception e) {
/* 298 */       e.printStackTrace();
/* 299 */       Utilidades.writeError("SiauTarifasEstudioPrevioDAO:crearRegistro ", e);
/*     */       
/* 301 */       return false;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean modificarRegistro(int numeroEstudio, String tipoCuadro, String titulo, int consecutivo, String valor1, String valor2, String valor3, String valor4, String valor5, String valor6, String tipo, String tituloAnt, String usuarioModificacion) {
/*     */     try {
/* 325 */       String s = "update cont_estudio_previo_tarifas set  valor_1='" + valor1 + "'," + " valor_2='" + valor2 + "'," + " valor_3='" + valor3 + "'," + " valor_4='" + valor4 + "'," + " valor_5='" + valor5 + "'," + " valor_6='" + valor6 + "'," + " titulo='" + titulo + "'," + " tipo='" + tipo + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " numero_estudio=" + numeroEstudio + " and tipo_cuadro='" + tipoCuadro + "'" + " and titulo='" + tituloAnt + "'" + " and consecutivo=" + consecutivo + "";
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
/* 342 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 345 */     catch (Exception e) {
/* 346 */       e.printStackTrace();
/* 347 */       Utilidades.writeError("SiauTarifasEstudioPrevioDAO:modificarRegistro ", e);
/*     */       
/* 349 */       return false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\ContEstudioPrevioTarifasDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */