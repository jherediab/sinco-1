/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.ActasDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.ActasDAO;
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
/*     */ public class ActasDAO
/*     */ {
/*     */   ResultSet rs;
/*  26 */   DBManager dat = new DBManager();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  35 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/*     */     try {
/*  42 */       this.dat.close();
/*     */     }
/*  44 */     catch (Exception e) {
/*  45 */       Utilidades.writeError("ActasDAO:close", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ActasDTO next() {
/*     */     try {
/*  56 */       if (this.rs.next()) {
/*  57 */         return leerRegistro();
/*     */       }
/*     */     }
/*  60 */     catch (Exception e) {
/*  61 */       e.printStackTrace();
/*  62 */       Utilidades.writeError("ActasDAO:next ", e);
/*     */     } 
/*  64 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ActasDTO leerRegistro() {
/*     */     try {
/*  74 */       ActasDTO reg = new ActasDTO();
/*  75 */       reg.setAnnoActa(this.rs.getInt("anno_acta"));
/*  76 */       reg.setCodigoArea(this.rs.getInt("codigo_area"));
/*  77 */       reg.setNumeroActa(this.rs.getInt("numero_acta"));
/*  78 */       reg.setAnotador(this.rs.getInt("anotador"));
/*  79 */       reg.setLecturaActaAnt(this.rs.getString("lectura_acta_ant"));
/*  80 */       reg.setAuditoriaInterna(this.rs.getString("auditoria_interna"));
/*  81 */       reg.setAuditoriaExterna(this.rs.getString("auditoria_externa"));
/*  82 */       reg.setRetroAudiServicio(this.rs.getString("retro_audi_servicio"));
/*  83 */       reg.setRetroQuejasReclamos(this.rs.getString("retro_quejas_reclamos"));
/*  84 */       reg.setProductoNoConforme(this.rs.getString("producto_no_conforme"));
/*  85 */       reg.setAccionesMejora(this.rs.getString("acciones_mejora"));
/*  86 */       reg.setSegumientoAcciones(this.rs.getString("segumiento_acciones"));
/*  87 */       reg.setCambiosAfectaCalidad(this.rs.getString("cambios_afecta_calidad"));
/*  88 */       reg.setRecomendacionesMejora(this.rs.getString("recomendaciones_mejora"));
/*  89 */       reg.setEficaciaSimasol(this.rs.getString("eficacia_simasol"));
/*  90 */       reg.setEficaciaSeguridad(this.rs.getString("eficacia_seguridad"));
/*  91 */       reg.setEficaciaDesempeno(this.rs.getString("eficacia_desempeno"));
/*  92 */       reg.setNecesidadRecursos(this.rs.getString("necesidad_recursos"));
/*  93 */       reg.setTemasVarios(this.rs.getString("temas_varios"));
/*  94 */       reg.setMedioAmbiente(this.rs.getString("medio_ambiente"));
/*  95 */       reg.setIndSeguimientoActividades(this.rs.getString("ind_seguimiento_actividades"));
/*  96 */       reg.setProximoAnotador(this.rs.getInt("proximo_anotador"));
/*  97 */       reg.setProximaReunion(this.rs.getString("proxima_reunion"));
/*  98 */       reg.setEstado(this.rs.getString("estado"));
/*  99 */       reg.setNombreAnotador(this.rs.getString("nombre_anotador"));
/*     */       
/* 101 */       reg.setIndLecturaActaAnt(this.rs.getString("ind_lectura_acta_ant"));
/* 102 */       reg.setIndResultadosAuditoria(this.rs.getString("ind_resultados_auditoria"));
/* 103 */       reg.setIndRetroalimentacion(this.rs.getString("ind_retroalimentacion"));
/* 104 */       reg.setIndProductoNoConforme(this.rs.getString("ind_producto_no_conforme"));
/* 105 */       reg.setIndAccionesMejora(this.rs.getString("ind_acciones_mejora"));
/* 106 */       reg.setIndCambiosAfectaCalidad(this.rs.getString("ind_cambios_afecta_calidad"));
/* 107 */       reg.setIndRecomendacionesMejora(this.rs.getString("ind_recomendaciones_mejora"));
/* 108 */       reg.setIndEficaciaGestion(this.rs.getString("ind_eficacia_gestion"));
/* 109 */       reg.setIndNecesidadRecursos(this.rs.getString("ind_necesidad_recursos"));
/* 110 */       reg.setIndTemasVarios(this.rs.getString("ind_temas_varios"));
/*     */       
/* 112 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/* 113 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/* 114 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/* 115 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*     */       
/* 117 */       reg.setNombreProximoAnotador(this.rs.getString("nombre_proxi_anotador"));
/* 118 */       return reg;
/*     */     }
/* 120 */     catch (Exception e) {
/* 121 */       e.printStackTrace();
/* 122 */       Utilidades.writeError("ActasDAO:leerRegistro ", e);
/*     */       
/* 124 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection cargarDeArea(int area, int anno) {
/* 134 */     Collection resultados = new ArrayList();
/*     */     
/*     */     try {
/* 137 */       String s = "select act_actas.*,anotador.nombres||' ' ||anotador.apellidos as nombre_anotador,pant.nombres||' ' ||pant.apellidos as nombre_proxi_anotador  from act_actas left join sis_usuarios  pant on (act_actas.proximo_anotador=pant.codigo_empleado),sis_usuarios anotador where act_actas.anotador=anotador.codigo_empleado   and act_actas.anno_acta=" + anno + "   and act_actas.codigo_area =" + area + " order by act_actas.numero_acta";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 145 */       boolean rtaDB = this.dat.parseSql(s);
/* 146 */       if (!rtaDB) {
/* 147 */         return resultados;
/*     */       }
/*     */       
/* 150 */       this.rs = this.dat.getResultSet();
/*     */       
/* 152 */       while (this.rs.next()) {
/* 153 */         resultados.add(leerRegistro());
/*     */       
/*     */       }
/*     */     }
/* 157 */     catch (Exception e) {
/* 158 */       e.printStackTrace();
/* 159 */       Utilidades.writeError("ActasDAO:cargarTodos ", e);
/*     */     } 
/* 161 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ActasDTO cargarRegistro(int annoActa, int codigoArea, int numeroActa) {
/*     */     try {
/* 171 */       String s = "select act_actas.*,";
/* 172 */       s = s + "anotador.nombres||' ' ||anotador.apellidos as nombre_anotador,";
/* 173 */       s = s + "pant.nombres||' ' ||pant.apellidos as nombre_proxi_anotador ";
/* 174 */       s = s + " from act_actas left join sis_usuarios pant on (act_actas.proximo_anotador=pant.codigo_empleado),sis_usuarios anotador";
/* 175 */       s = s + " where act_actas.anotador=anotador.codigo_empleado";
/* 176 */       s = s + " and anno_acta=" + annoActa;
/* 177 */       s = s + " and codigo_area=" + codigoArea;
/* 178 */       s = s + " and numero_acta=" + numeroActa;
/* 179 */       boolean rtaDB = this.dat.parseSql(s);
/* 180 */       if (!rtaDB) {
/* 181 */         return null;
/*     */       }
/*     */       
/* 184 */       this.rs = this.dat.getResultSet();
/* 185 */       if (this.rs.next()) {
/* 186 */         return leerRegistro();
/*     */       }
/*     */     }
/* 189 */     catch (Exception e) {
/* 190 */       e.printStackTrace();
/* 191 */       Utilidades.writeError("ActasDAO:cargarActas ", e);
/*     */     } 
/* 193 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\ActasDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */