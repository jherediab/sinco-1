/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import sinco.business.AudBloquesDTO;
/*     */ import sinco.business.AudCriteriosDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.AudBloquesDAO;
/*     */ import sinco.data.DBManager;
/*     */ 

/*     */ 
/*     */ public class AudBloquesDAO
/*     */ {
/*     */   public AudBloquesDTO leerRegistro(ResultSet rs) {
/*     */     try {
/*  37 */       AudBloquesDTO reg = new AudBloquesDTO();
/*     */       
/*  39 */       reg.setCiclo(rs.getInt("ciclo"));
/*  40 */       reg.setBloque(rs.getInt("bloque"));
/*  41 */       reg.setCodigoProceso(rs.getString("codigo_proceso"));
/*  42 */       reg.setCodigoInforme(rs.getInt("codigo_informe"));
/*  43 */       reg.setAsociadoA(rs.getString("asociado_a"));
/*  44 */       reg.setFechaInsercion(rs.getString("fecha_insercion"));
/*  45 */       reg.setUsuarioInsercion(rs.getString("usuario_insercion"));
/*  46 */       reg.setFechaModificacion(rs.getString("fecha_modificacion"));
/*  47 */       reg.setUsuarioModificacion(rs.getString("usuario_modificacion"));
/*  48 */       return reg;
/*     */     }
/*  50 */     catch (Exception e) {
/*  51 */       e.printStackTrace();
/*  52 */       Utilidades.writeError("AudBloquesDAO:leerRegistro ", e);
/*     */       
/*  54 */       return null;
/*     */     } 
/*     */   }

/*     */   
/*     */   public Collection<AudBloquesDTO> cargarTodos(int ciclo, String codigoPadreProceso, int codigoPadreInforme, String asociadoA) {
/*  68 */     Collection<AudBloquesDTO> resultados = new ArrayList<AudBloquesDTO>();
/*     */     
/*  70 */    DBManager dat = new DBManager();
/*     */     try {
/*  72 */       String s = "select t.ciclo,t.bloque,t.codigo_proceso,t.codigo_informe,t.asociado_a,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from aud_bloques t  where 1=1";
/*     */ 

/*     */       
/*  84 */       if (ciclo > 0) {
/*  85 */         s = s + " and t.ciclo=" + ciclo;
/*     */       }
/*  87 */       if (asociadoA.equals("P")) {
/*  88 */         s = s + " and t.codigo_proceso='" + codigoPadreProceso + "'";
/*     */       }
/*  90 */       if (asociadoA.equals("I")) {
/*  91 */         s = s + " and t.codigo_informe=" + codigoPadreInforme;
/*     */       }
/*  93 */       s = s + " order by 1,2,3,4";
/*  94 */       boolean rtaDB = dat.parseSql(s);
/*  95 */       if (!rtaDB) {
/*  96 */         return resultados;
/*     */       }
/*  98 */       ResultSet rs = dat.getResultSet();
/*  99 */       while (rs.next()) {
/* 100 */         resultados.add(leerRegistro(rs));
/*     */       }
/*     */     }
/* 103 */     catch (Exception e) {
/* 104 */       e.printStackTrace();
/* 105 */       Utilidades.writeError("AudBloquesDAO:cargarTodos ", e);
/*     */     } finally {
/*     */       
/* 108 */       dat.close();
/*     */     } 
/* 110 */     return resultados;
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
/*     */   public Collection<AudBloquesDTO> cargarPreguntas(int ciclo, String codigoPadreProceso, int codigoPadreInforme, int bloque, String asociadoA) {
/* 126 */     Collection<AudBloquesDTO> resultados = new ArrayList<AudBloquesDTO>();
/*     */     
/* 128 */    DBManager dat = new DBManager();
/*     */     try {
/* 130 */       String s = "select t.Ciclo, t.Bloque, t.Codigo_Proceso, t.Codigo_informe, p.pregunta, p.descripcion_Pregunta, p.ind_conformidad, p.respuesta, p.anotaciones from   Aud_Bloques           t, \t\t Aud_Bloques_Preguntas p where  t.Ciclo = p.Ciclo and t.Bloque = p.Bloque and t.ciclo=" + ciclo + " and t.Bloque = " + bloque + " and t.asociado_A = '" + asociadoA + "'";
/*     */ 

/* 147 */       if (asociadoA.equals("P")) {
/* 148 */         s = s + " and t.codigo_proceso='" + codigoPadreProceso + "'";
/*     */       } else {
/*     */         
/* 151 */         s = s + " and t.codigo_informe=" + codigoPadreInforme;
/*     */       } 
/*     */       
/* 154 */       s = s + " order  by t.Bloque, \tp.Pregunta";
/*     */ 
/*     */       
/* 157 */       boolean rtaDB = dat.parseSql(s);
/* 158 */       if (!rtaDB) {
/* 159 */         return resultados;
/*     */       }
/* 161 */       ResultSet rs = dat.getResultSet();
/* 162 */       while (rs.next())
/*     */       {
/* 164 */         AudBloquesDTO reg = new AudBloquesDTO();
/*     */         
/* 166 */         reg.setCiclo(rs.getInt("ciclo"));
/* 167 */         reg.setBloque(rs.getInt("bloque"));
/* 168 */         reg.setCodigoProceso(rs.getString("codigo_proceso"));
/* 169 */         reg.setCodigoInforme(rs.getInt("codigo_informe"));
/* 170 */         reg.setPregunta(rs.getInt("pregunta"));
/* 171 */         reg.setDescripcionPregunta(rs.getString("descripcion_Pregunta"));
/* 172 */         reg.setIndConformidad(rs.getString("ind_conformidad"));
/* 173 */         reg.setRespuesta(rs.getString("respuesta"));
/* 174 */         reg.setAnotaciones(rs.getString("anotaciones"));
/*     */ 
/*     */         
/* 177 */         resultados.add(reg);
/*     */       }
/*     */     
/* 180 */     } catch (Exception e) {
/* 181 */       e.printStackTrace();
/* 182 */       Utilidades.writeError("AudBloquesDAO:cargarTodos ", e);
/*     */     } finally {
/*     */       
/* 185 */       dat.close();
/*     */     } 
/* 187 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<AudBloquesDTO> cargarInconformidades(int ciclo) {
/* 196 */     Collection<AudBloquesDTO> resultados = new ArrayList<AudBloquesDTO>();
/*     */     
/* 198 */     DBManager dat = new DBManager();
/*     */     try {
/* 200 */       String s = " select p.Ciclo,p.Bloque,p.Pregunta,t.Codigo_Proceso,t.Codigo_informe,  CASE WHEN t.asociado_a='P' THEN 'Proceso' WHEN t.asociado_a='I' THEN 'Informe' ELSE 'No aplica' END AS asociado_a, Pr.Descripcion Nombre, p.Descripcion_Pregunta, p.Respuesta, p.Anotaciones  from  Aud_Procesos_Plan_Anual a,Procesos Pr,Aud_Bloques t,Aud_Bloques_Preguntas p  where  a.Codigo_Proceso = Pr.Codigo  \t\t and t.Ciclo = a.Ciclo  \t\t and t.Codigo_proceso = a.Codigo_Proceso  \t\t and t.Ciclo = p.Ciclo  \t\t and t.Bloque = p.Bloque  \t\t and a.Ciclo = " + ciclo + " \t\t and coalesce(p.ind_acciones,'N')='N'" + " \t\t and p.Ind_Conformidad = 'N'" + " \t\t UNION " + " \t\t select p.Ciclo,p.Bloque,p.Pregunta,t.Codigo_Proceso,t.Codigo_informe," + " \t     CASE WHEN t.asociado_a='P' THEN 'Proceso' WHEN t.asociado_a='I' THEN 'Informe' ELSE 'No aplica' END AS asociado_a, i.Nombre, p.Descripcion_Pregunta, p.Respuesta,p.Anotaciones " + " \t\t from Aud_Informes_Plan_Anual a,aud_informes i,Aud_Bloques t,Aud_Bloques_Preguntas p " + " \t\t where  a.Codigo_Informe = i.Codigo" + " \t\t and t.Ciclo = a.Ciclo" + " \t\t and t.Codigo_informe = a.Codigo_informe" + " \t\t and t.Ciclo = p.Ciclo" + " \t\t and t.Bloque = p.Bloque" + " \t\t and a.Ciclo = " + ciclo + "        and coalesce(p.ind_acciones,'N')='N'" + " \t\t and p.Ind_Conformidad = 'N'";
/*     */ 

/*     */       
/* 224 */       boolean rtaDB = dat.parseSql(s);
/* 225 */       if (!rtaDB) {
/* 226 */         return resultados;
/*     */       }
/* 228 */       ResultSet rs = dat.getResultSet();
/* 229 */       while (rs.next())
/*     */       {
/* 231 */         AudBloquesDTO reg = new AudBloquesDTO();
/*     */         
/* 233 */         reg.setCiclo(rs.getInt("ciclo"));
/* 234 */         reg.setCodigoProceso(rs.getString("codigo_proceso"));
/* 235 */         reg.setCodigoInforme(rs.getInt("codigo_informe"));
/* 236 */         reg.setBloque(rs.getInt("bloque"));
/* 237 */         reg.setPregunta(rs.getInt("pregunta"));
/* 238 */         reg.setDescripcionPregunta(rs.getString("descripcion_Pregunta"));
/* 239 */         reg.setRespuesta(rs.getString("respuesta"));
/* 240 */         reg.setAnotaciones(rs.getString("anotaciones"));
/* 241 */         reg.setNombreProceso(rs.getString("Nombre"));
/* 242 */         reg.setAsociadoA(rs.getString("asociado_a"));
/*     */         
/* 244 */         resultados.add(reg);
/*     */       }
/*     */     
/* 247 */     } catch (Exception e) {
/* 248 */       e.printStackTrace();
/* 249 */       Utilidades.writeError("AudBloquesDAO:cargarInconformidades ", e);
/*     */     } finally {
/*     */       
/* 252 */       dat.close();
/*     */     } 
/* 254 */     return resultados;
/*     */   }

/*     */   
/*     */   public AudBloquesDTO cargarRegistro(int ciclo, int bloque) {
/* 333 */   DBManager  dat = new DBManager();
/*     */     try {
/* 335 */       String s = "select t.ciclo,t.bloque,t.codigo_proceso,t.codigo_informe,t.asociado_a,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from aud_bloques t  where  t.ciclo=" + ciclo + " and t.bloque=" + bloque + "";
/*     */ 

/*     */       
/* 350 */       boolean rtaDB = dat.parseSql(s);
/* 351 */       if (!rtaDB) {
/* 352 */         return null;
/*     */       }
/* 354 */       ResultSet rs = dat.getResultSet();
/* 355 */       if (rs.next()) {
/* 356 */         return leerRegistro(rs);
/*     */       }
/*     */     }
/* 359 */     catch (Exception e) {
/* 360 */       e.printStackTrace();
/* 361 */       Utilidades.writeError("AudBloquesDAO:cargarAudBloques", e);
/*     */     } finally {
/*     */       
/* 364 */       dat.close();
/*     */     } 
/* 366 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int siguienteRegistro(int ciclo) {
/* 375 */     int inumero = 1;
/* 376 */     String s = "select max(bloque) from aud_bloques  where  ciclo=" + ciclo;
/*     */ 
/*     */ 
/*     */     
/* 380 */    DBManager dat = new DBManager();
/*     */     try {
/* 382 */       boolean rta = dat.parseSql(s);
/* 383 */       if (!rta) return 0; 
/* 384 */       ResultSet rs = dat.getResultSet();
/* 385 */       if (rs.next()) {
/* 386 */         s = rs.getString(1);
/* 387 */         if (!rs.wasNull()) {
/* 388 */           inumero = Integer.parseInt(s) + 1;
/*     */         }
/*     */       } 
/* 391 */       return inumero;
/*     */     }
/* 393 */     catch (Exception e) {
/* 394 */       e.printStackTrace();
/* 395 */       Utilidades.writeError("AudBloquesDAO:siguienteRegistro ", e);
/*     */     } finally {
/*     */       
/* 398 */       dat.close();
/*     */     } 
/* 400 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarRegistro(int ciclo, int bloque) {
/* 411 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 413 */    DBManager dat = new DBManager();
/*     */     try {
/* 415 */       String s = "delete from aud_bloques where  ciclo=" + ciclo + "  and bloque=" + bloque + "";
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 420 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 422 */     catch (Exception e) {
/* 423 */       e.printStackTrace();
/* 424 */       Utilidades.writeError("AudBloquesDAO:eliminarRegistro ", e);
/* 425 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 428 */       dat.close();
/*     */     } 
/* 430 */     return rta;
/*     */   }
/*     */ 

/*     */   public RespuestaBD crearRegistro(int ciclo, String codigoPadreProceso, int codigoPadreInforme, int bloque, String asociadoA, String usuarioInsercion) {
/* 446 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 448 */     int elSiguiente = siguienteRegistro(ciclo);
/* 449 */     if (elSiguiente == 0) {
/* 450 */       rta.setMensaje("Generando secuencia");
/* 451 */       return rta;
/*     */     } 
/*     */     
/* 454 */   DBManager  dat = new DBManager();
/*     */     try {
/* 456 */       String s = "insert into aud_bloques(ciclo,bloque,codigo_proceso,codigo_informe,asociado_a,fecha_insercion,usuario_insercion) values (" + ciclo + "," + "" + elSiguiente + "," + "'" + (asociadoA.equals("P") ? ("" + codigoPadreProceso) : "null") + "'," + "" + (asociadoA.equals("I") ? ("" + codigoPadreInforme) : "null") + "," + "'" + asociadoA + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
/*     */ 

/* 473 */       rta = dat.executeUpdate2(s);
/* 474 */       rta.setSecuencia(elSiguiente);
/*     */     }
/* 476 */     catch (Exception e) {
/* 477 */       e.printStackTrace();
/* 478 */       Utilidades.writeError("%AudBloquesDAO:crearRegistro ", e);
/* 479 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 482 */       dat.close();
/*     */     } 
/* 484 */     return rta;
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
/*     */   public RespuestaBD modificarRegistro(int ciclo, int bloque, Collection<AudCriteriosDTO> criterios, Collection<AudBloquesDTO> preguntas, String usuarioModificacion) {
/* 499 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 501 */    DBManager dat = new DBManager();
/*     */     try {
/* 503 */       String s = "update aud_bloques set  fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " ciclo=" + ciclo + " and bloque=" + bloque + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 510 */       rta = dat.executeUpdate2(s);
/*     */ 
/*     */       
/* 513 */       s = "DELETE from   Aud_Bloques_Criterio where  Ciclo = " + ciclo + " and Bloque = " + bloque;
/*     */ 
/*     */       
/* 516 */       rta = dat.executeUpdate2(s);
/*     */       
/* 518 */       Iterator<AudCriteriosDTO> iterator = criterios.iterator();
/* 519 */       while (iterator.hasNext()) {
/* 520 */         AudCriteriosDTO reg = (AudCriteriosDTO)iterator.next();
/*     */         
/* 522 */         s = "insert into Aud_Bloques_Criterio\t(Ciclo,Bloque,Criterio,Fecha_Insercion,Usuario_Insercion) VALUES(" + ciclo + "," + "" + bloque + "," + "'" + reg.getCriterio() + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioModificacion + "')";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 528 */         rta = dat.executeUpdate2(s);
/*     */       } 
/*     */       
/* 531 */       s = "update aud_bloques_preguntas set  estado='I', Usuario_Modificacion='" + usuarioModificacion + "'," + " Fecha_Modificacion=" + Utilidades.getFechaBD() + " where  Ciclo = " + ciclo + " and Bloque = " + bloque;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 537 */       rta = dat.executeUpdate2(s);
/*     */       
/* 539 */       Iterator<AudBloquesDTO> ip = preguntas.iterator();
/* 540 */       while (ip.hasNext()) {
/* 541 */         AudBloquesDTO reg = (AudBloquesDTO)ip.next();
/*     */ 
/*     */         
/* 544 */         s = "update Aud_Bloques_Preguntas set descripcion_pregunta='" + reg.getDescripcionPregunta() + "'," + " estado='A'," + " Usuario_Modificacion='" + usuarioModificacion + "'," + " Fecha_Modificacion=" + Utilidades.getFechaBD() + " where Ciclo =" + ciclo + " and Bloque=" + bloque + " and Pregunta=" + reg.getPregunta();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 553 */         rta = dat.executeUpdate2(s);
/*     */         
/* 555 */         if (rta.getRegistrosAfectados() == 0) {
/* 556 */           s = "insert into aud_bloques_preguntas (Ciclo,Bloque,pregunta,descripcion_pregunta,Fecha_Insercion,Usuario_Insercion) VALUES(" + ciclo + "," + "" + bloque + "," + "" + reg.getPregunta() + "," + "'" + reg.getDescripcionPregunta() + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioModificacion + "')";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 563 */           rta = dat.executeUpdate2(s);
/*     */         } 
/*     */       } 
/*     */       
/* 567 */       s = "delete from aud_bloques_preguntas  where  Ciclo = " + ciclo + " and Bloque = " + bloque + " and estado='I'";
/*     */ 
/*     */ 
/*     */       
/* 571 */       rta = dat.executeUpdate2(s);
/*     */     
/*     */     }
/* 574 */     catch (Exception e) {
/* 575 */       e.printStackTrace();
/* 576 */       Utilidades.writeError("AudBloquesDAO:modificarRegistro ", e);
/* 577 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 580 */       dat.close();
/*     */     } 
/* 582 */     return rta;
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
/*     */   public RespuestaBD actualizarInconformidad(int ciclo, int bloque, int pregunta, String usuarioModificacion) {
/* 596 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 598 */    DBManager dat = new DBManager();
/*     */     
/*     */     try {
/* 601 */       String s = "update aud_bloques_preguntas set  ind_acciones='E', Usuario_Modificacion='" + usuarioModificacion + "'," + " Fecha_Modificacion=" + Utilidades.getFechaBD() + " where  Ciclo = " + ciclo + " and Bloque = " + bloque + " and pregunta=" + pregunta;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 608 */       rta = dat.executeUpdate2(s);
/*     */     
/*     */     }
/* 611 */     catch (Exception e) {
/* 612 */       e.printStackTrace();
/* 613 */       Utilidades.writeError("AudBloquesDAO:modificarRegistro ", e);
/* 614 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 617 */       dat.close();
/*     */     } 
/* 619 */     return rta;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<AudBloquesDTO> cargarPreguntas(int ciclo, int bloque) {
/* 629 */     Collection<AudBloquesDTO> resultados = new ArrayList<AudBloquesDTO>();
/*     */     
/* 631 */    DBManager dat = new DBManager();
/*     */     try {
/* 633 */       String s = "select Pregunta, Descripcion_Pregunta from   Aud_Bloques_Preguntas where  Ciclo = " + ciclo + " and Bloque = " + bloque + " order  by Pregunta";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 639 */       boolean rtaDB = dat.parseSql(s);
/* 640 */       if (!rtaDB) {
/* 641 */         return resultados;
/*     */       }
/* 643 */       ResultSet rs = dat.getResultSet();
/* 644 */       while (rs.next()) {
/* 645 */         AudBloquesDTO reg = new AudBloquesDTO();
/* 646 */         reg.setPregunta(rs.getInt("pregunta"));
/* 647 */         reg.setDescripcionPregunta(rs.getString("Descripcion_Pregunta"));
/* 648 */         resultados.add(reg);
/*     */       }
/*     */     
/* 651 */     } catch (Exception e) {
/* 652 */       e.printStackTrace();
/* 653 */       Utilidades.writeError("AudBloquesDAO:cargarTodos ", e);
/*     */     } finally {
/*     */       
/* 656 */       dat.close();
/*     */     } 
/* 658 */     return resultados;
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
/*     */   public RespuestaBD salvarRespuestas(int ciclo, String codigoPadre, Collection<AudBloquesDTO> preguntas, String usuarioModificacion) {
/* 674 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 676 */    DBManager dat = new DBManager();
/*     */     try {
/* 678 */       Iterator<AudBloquesDTO> ip = preguntas.iterator();
/* 679 */       while (ip.hasNext()) {
/* 680 */         AudBloquesDTO reg = (AudBloquesDTO)ip.next();
/*     */         
/* 682 */         String s = "update Aud_Bloques_Preguntas set Ind_Conformidad='" + reg.getIndConformidad() + "'," + " Respuesta='" + reg.getRespuesta() + "'," + " Anotaciones='" + reg.getAnotaciones() + "'," + " Usuario_Modificacion='" + usuarioModificacion + "'," + " Fecha_Modificacion=" + Utilidades.getFechaBD() + " where Ciclo =" + ciclo + " and Bloque=" + reg.getBloque() + " and Pregunta=" + reg.getPregunta();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 692 */         rta = dat.executeUpdate2(s);
/*     */ 
/*     */         
/* 695 */         s = "update aud_procesos_plan_anual set  fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " ciclo=" + ciclo + " and codigo_proceso='" + codigoPadre + "'";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 701 */         rta = dat.executeUpdate2(s);
/*     */       }
/*     */     
/*     */     }
/* 705 */     catch (Exception e) {
/* 706 */       e.printStackTrace();
/* 707 */       Utilidades.writeError("AudBloquesDAO:modificarRegistro ", e);
/* 708 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 711 */       dat.close();
/*     */     } 
/* 713 */     return rta;
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
/*     */   public RespuestaBD salvarRespuestasInformes(int ciclo, int codigoPadre, Collection<AudBloquesDTO> preguntas, String usuarioModificacion) {
/* 728 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 730 */    DBManager dat = new DBManager();
/*     */     try {
/* 732 */       Iterator<AudBloquesDTO> ip = preguntas.iterator();
/* 733 */       while (ip.hasNext()) {
/* 734 */         AudBloquesDTO reg = (AudBloquesDTO)ip.next();
/*     */         
/* 736 */         String s = "update Aud_Bloques_Preguntas set Ind_Conformidad='" + reg.getIndConformidad() + "'," + " Respuesta='" + reg.getRespuesta() + "'," + " Anotaciones='" + reg.getAnotaciones() + "'," + " Usuario_Modificacion='" + usuarioModificacion + "'," + " Fecha_Modificacion=" + Utilidades.getFechaBD() + " where Ciclo =" + ciclo + " and Bloque=" + reg.getBloque() + " and Pregunta=" + reg.getPregunta();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 746 */         rta = dat.executeUpdate2(s);
/*     */ 
/*     */         
/* 749 */         s = "update aud_informes_plan_anual set  fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " ciclo=" + ciclo + " and codigo_informe=" + codigoPadre;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 755 */         rta = dat.executeUpdate2(s);
/*     */       }
/*     */     
/*     */     }
/* 759 */     catch (Exception e) {
/* 760 */       e.printStackTrace();
/* 761 */       Utilidades.writeError("AudBloquesDAO:modificarRegistro ", e);
/* 762 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 765 */       dat.close();
/*     */     } 
/* 767 */     return rta;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\AudBloquesDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */