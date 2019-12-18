/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.CalEstadisticasDTO;
/*     */ import sinco.business.CalPlanesDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.CalEstadisticasDAO;
/*     */ import sinco.data.DBManager;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CalEstadisticasDAO
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
/*  29 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/*     */     try {
/*  36 */       this.dat.close();
/*     */     }
/*  38 */     catch (Exception e) {
/*  39 */       Utilidades.writeError("CalEstadisticasDAO:close", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalPlanesDTO leerRegistro() {
/*     */     try {
/*  50 */       CalPlanesDTO reg = new CalPlanesDTO();
/*  51 */       reg.setCiclo(this.rs.getInt("ciclo"));
/*  52 */       reg.setCodigoPlan(this.rs.getInt("codigo_plan"));
/*  53 */       reg.setCodigoArea(this.rs.getInt("codigo_area"));
/*  54 */       reg.setEstado(this.rs.getString("estado"));
/*  55 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  56 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  57 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  58 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  59 */       return reg;
/*     */     }
/*  61 */     catch (Exception e) {
/*  62 */       e.printStackTrace();
/*  63 */       Utilidades.writeError("CalEstadisticasDAO:leerRegistro ", e);
/*     */       
/*  65 */       return null;
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
/*     */   public Collection cargarNoDiligenciadas(int codigoCiclo) {
/*  78 */     Collection resultados = new ArrayList();
/*     */     try {
/*  80 */       String s = "SELECT  p.CICLO codigo_ciclo, p.CODIGO_PLAN, p.CODIGO_AREA, m.CODIGO_OBJETIVO, m.CODIGO_META, A.DESCRIPCION NOMBRE_AREA, o.DESCRIPCION nombre_objetivo, M.DESCRIPCION nombre_meta, CASE  WHEN coalesce(m.MES01,'N')='S' AND L.ENE IS NULL THEN 'N' END P01, CASE  WHEN coalesce(m.MES02,'N')='S' AND L.feb IS NULL THEN 'N' END P02, CASE  WHEN coalesce(m.MES03,'N')='S' AND L.MAR IS NULL THEN 'N' END P03, CASE  WHEN coalesce(m.MES04,'N')='S' AND L.ABR IS NULL THEN 'N' END P04, CASE  WHEN coalesce(m.MES05,'N')='S' AND L.MAY IS NULL THEN 'N' END P05, CASE  WHEN coalesce(m.MES06,'N')='S' AND L.JUN IS NULL THEN 'N' END P06, CASE  WHEN coalesce(m.MES07,'N')='S' AND L.JUL IS NULL THEN 'N' END P07, CASE  WHEN coalesce(m.MES08,'N')='S' AND L.AGO IS NULL THEN 'N' END P08, CASE  WHEN coalesce(m.MES09,'N')='S' AND L.SEP IS NULL THEN 'N' END P09, CASE  WHEN coalesce(m.MES10,'N')='S' AND L.OCT IS NULL THEN 'N' END P10, CASE  WHEN coalesce(m.MES11,'N')='S' AND L.NOV IS NULL THEN 'N' END P11, CASE  WHEN coalesce(m.MES12,'N')='S' AND L.DIC IS NULL THEN 'N' END P12, l.ene, l.feb, l.mar, l.abr, l.may, l.jun, l.jul, l.ago, l.sep, l.oct, l.nov, l.dic, l.mene, l.mfeb, l.mmar, l.mabr, l.mmay, l.mjun, l.mjul, l.mago, l.msep, l.moct, l.mnov, l.mdic FROM UNIDADES_DEPENDENCIA A, CAL_PLANES P, CAL_PLAN_OBJETIVOS o, CAL_PLAN_METAS m left join VIEW_CAL_LOGROS l on( m.CODIGO_CICLO=l.CODIGO_CICLO AND m.CODIGO_PLAN =l.CODIGO_PLAN AND m.CODIGO_OBJETIVO= l.CODIGO_OBJETIVO AND m.CODIGO_META= l.CODIGO_META) WHERE  A.CODIGO=P.CODIGO_AREA AND P.CICLO=O.CODIGO_CICLO AND P.CODIGO_PLAN=O.CODIGO_PLAN AND o.CODIGO_CICLO=m.CODIGO_CICLO AND o.CODIGO_PLAN=m.CODIGO_PLAN AND o.CODIGO_OBJETIVO=m.CODIGO_OBJETIVO AND m.CODIGO_CICLO=" + codigoCiclo + " ORDER BY a.descripcion,m.codigo_objetivo,m.codigo_meta";
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 144 */       boolean rtaDB = this.dat.parseSql(s);
/* 145 */       if (!rtaDB) {
/* 146 */         return resultados;
/*     */       }
/* 148 */       this.rs = this.dat.getResultSet();
/* 149 */       while (this.rs.next()) {
/* 150 */         CalEstadisticasDTO reg = new CalEstadisticasDTO();
/* 151 */         reg.setCodigoCiclo(this.rs.getInt("codigo_ciclo"));
/* 152 */         reg.setCodigoPlan(this.rs.getInt("codigo_plan"));
/* 153 */         reg.setCodigoArea(this.rs.getInt("codigo_area"));
/* 154 */         reg.setCodigoObjetivo(this.rs.getInt("codigo_objetivo"));
/* 155 */         reg.setCodigoMeta(this.rs.getInt("codigo_meta"));
/* 156 */         reg.setNombreArea(this.rs.getString("nombre_area"));
/* 157 */         reg.setNombreObjetivo(this.rs.getString("nombre_objetivo"));
/* 158 */         reg.setNombreMeta(this.rs.getString("nombre_meta"));
/* 159 */         reg.setP01(this.rs.getString("P01"));
/* 160 */         reg.setP02(this.rs.getString("P02"));
/* 161 */         reg.setP03(this.rs.getString("P03"));
/* 162 */         reg.setP04(this.rs.getString("P04"));
/* 163 */         reg.setP05(this.rs.getString("P05"));
/* 164 */         reg.setP06(this.rs.getString("P06"));
/* 165 */         reg.setP07(this.rs.getString("P07"));
/* 166 */         reg.setP08(this.rs.getString("P08"));
/* 167 */         reg.setP09(this.rs.getString("P09"));
/* 168 */         reg.setP10(this.rs.getString("P10"));
/* 169 */         reg.setP11(this.rs.getString("P11"));
/* 170 */         reg.setP12(this.rs.getString("P12"));
/* 171 */         reg.setLogroEne(this.rs.getDouble("Ene"));
/* 172 */         reg.setLogroFeb(this.rs.getDouble("Feb"));
/* 173 */         reg.setLogroMar(this.rs.getDouble("Mar"));
/* 174 */         reg.setLogroAbr(this.rs.getDouble("Abr"));
/* 175 */         reg.setLogroMay(this.rs.getDouble("May"));
/* 176 */         reg.setLogroJun(this.rs.getDouble("Jun"));
/* 177 */         reg.setLogroJul(this.rs.getDouble("Jul"));
/* 178 */         reg.setLogroAgo(this.rs.getDouble("Ago"));
/* 179 */         reg.setLogroSep(this.rs.getDouble("Sep"));
/* 180 */         reg.setLogroOct(this.rs.getDouble("Oct"));
/* 181 */         reg.setLogroNov(this.rs.getDouble("Nov"));
/* 182 */         reg.setLogroDic(this.rs.getDouble("Dic"));
/* 183 */         reg.setMetaEne(this.rs.getDouble("mEne"));
/* 184 */         reg.setMetaFeb(this.rs.getDouble("mFeb"));
/* 185 */         reg.setMetaMar(this.rs.getDouble("mMar"));
/* 186 */         reg.setMetaAbr(this.rs.getDouble("mAbr"));
/* 187 */         reg.setMetaMay(this.rs.getDouble("mMay"));
/* 188 */         reg.setMetaJun(this.rs.getDouble("mJun"));
/* 189 */         reg.setMetaJul(this.rs.getDouble("mJul"));
/* 190 */         reg.setMetaAgo(this.rs.getDouble("mAgo"));
/* 191 */         reg.setMetaSep(this.rs.getDouble("mSep"));
/* 192 */         reg.setMetaOct(this.rs.getDouble("mOct"));
/* 193 */         reg.setMetaNov(this.rs.getDouble("mNov"));
/* 194 */         reg.setMetaDic(this.rs.getDouble("mDic"));
/*     */         
/* 196 */         resultados.add(reg);
/*     */       }
/*     */     
/* 199 */     } catch (Exception e) {
/* 200 */       e.printStackTrace();
/* 201 */       Utilidades.writeError("CalObjetivosDAO:cargarNoDiligenciadas ", e);
/*     */     } 
/* 203 */     return resultados;
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
/*     */   public Collection cargarPlanEspecial(int codigoCiclo, String secuencia) {
/* 217 */     Collection resultados = new ArrayList();
/*     */     try {
/* 219 */       String s = "select  PRO.DESCRIPCION NOMBRE_PROCESO, SUB.DESCRIPCION NOMBRE_SUBPROCESO, M.CODIGO_OBJETIVO,        M.CODIGO_META,        O.DESCRIPCION NOMBRE_OBJETIVO,        M.DESCRIPCION NOMBRE_META,        m.TIPO_MEDICION,        m.UNIDAD_MEDIDA,        max(L.ENE) MENE,        max(L.FEB) MFEB,        max(L.MAR) MMAR,        max(L.ABR) MABR,        max(L.MAY) MMAY,        max(L.JUN) MJUN,        max(L.JUL) MJUL,        max(L.AGO) MAGO,        max(L.SEP) MSEP,        max(L.OCT) MOCT,        max(L.NOV) MNOV,        max(L.DIC) MDIC,        MIN(L.ENE) AENE,        MIN(L.FEB) AFEB,        MIN(L.MAR) AMAR,        MIN(L.ABR) AABR,        MIN(L.MAY) AMAY,        MIN(L.JUN) AJUN,        MIN(L.JUL) AJUL,        MIN(L.AGO) AAGO,        MIN(L.SEP) ASEP,        MIN(L.OCT) AOCT,        MIN(L.NOV) ANOV,        MIN(L.DIC) ADIC,        AVG(L.ENE) PENE,        AVG(L.FEB) PFEB,        AVG(L.MAR) PMAR,        AVG(L.ABR) PABR,        AVG(L.MAY) PMAY,        AVG(L.JUN) PJUN,        AVG(L.JUL) PJUL,        AVG(L.AGO) PAGO,        AVG(L.SEP) PSEP,        AVG(L.OCT) POCT,        AVG(L.NOV) PNOV,        AVG(L.DIC) PDIC ,        AVG(L.MENE) PMENE,        AVG(L.MFEB) PMFEB,        AVG(L.MMAR) PMMAR,        AVG(L.MABR) PMABR,        AVG(L.MMAY) PMMAY,        AVG(L.MJUN) PMJUN,        AVG(L.MJUL) PMJUL,        AVG(L.MAGO) PMAGO,        AVG(L.MSEP) PMSEP,        AVG(L.MOCT) PMOCT,        AVG(L.MNOV) PMNOV,        AVG(L.MDIC) PMDIC          from   UNIDADES_DEPENDENCIA A,        CAL_PLANES           P,        CAL_PLAN_OBJETIVOS   O,        CAL_PLAN_METAS       M left join         VIEW_CAL_LOGROS      L on(        M.CODIGO_CICLO = L.CODIGO_CICLO        and M.CODIGO_PLAN = L.CODIGO_PLAN        and M.CODIGO_OBJETIVO = L.CODIGO_OBJETIVO        and M.CODIGO_META = L.CODIGO_META),      PROCESOS PRO,      SUBPROCESOS SUB where  A.CODIGO = P.CODIGO_AREA        and P.CICLO = O.CODIGO_CICLO        and P.CODIGO_PLAN = O.CODIGO_PLAN        and O.CODIGO_CICLO = M.CODIGO_CICLO        and O.CODIGO_PLAN = M.CODIGO_PLAN        and O.CODIGO_OBJETIVO = M.CODIGO_OBJETIVO      AND O.PROCESO=PRO.CODIGO      AND O.SUBPROCESO=SUB.CODIGO      AND O.PROCESO=SUB.PROCESO        and M.CODIGO_CICLO = " + codigoCiclo + "        and A.SECUENCIA like '" + secuencia + "%'" + " group  by " + " PRO.DESCRIPCION ," + " SUB.DESCRIPCION ," + " M.CODIGO_OBJETIVO," + "     M.CODIGO_META," + "     O.DESCRIPCION," + "     M.DESCRIPCION," + "     m.TIPO_MEDICION," + "     m.UNIDAD_MEDIDA" + " order  by 1," + "           2," + "           3," + "           4," + "           5";
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 313 */       boolean rtaDB = this.dat.parseSql(s);
/* 314 */       if (!rtaDB) {
/* 315 */         return resultados;
/*     */       }
/* 317 */       this.rs = this.dat.getResultSet();
/* 318 */       while (this.rs.next()) {
/* 319 */         CalEstadisticasDTO reg = new CalEstadisticasDTO();
/* 320 */         reg.setCodigoCiclo(codigoCiclo);
/* 321 */         reg.setNombreProceso(this.rs.getString("nombre_proceso"));
/* 322 */         reg.setNombreSubProceso(this.rs.getString("nombre_subproceso"));
/* 323 */         reg.setCodigoObjetivo(this.rs.getInt("codigo_objetivo"));
/* 324 */         reg.setCodigoMeta(this.rs.getInt("codigo_meta"));
/* 325 */         reg.setNombreObjetivo(this.rs.getString("nombre_objetivo"));
/* 326 */         reg.setNombreMeta(this.rs.getString("nombre_meta"));
/* 327 */         reg.setTipoMedicion(this.rs.getString("tipo_medicion"));
/* 328 */         reg.setUnidadMedida(this.rs.getString("unidad_medida"));
/* 329 */         reg.setLogroEne(this.rs.getDouble("pEne"));
/* 330 */         reg.setLogroFeb(this.rs.getDouble("pFeb"));
/* 331 */         reg.setLogroMar(this.rs.getDouble("pMar"));
/* 332 */         reg.setLogroAbr(this.rs.getDouble("pAbr"));
/* 333 */         reg.setLogroMay(this.rs.getDouble("pMay"));
/* 334 */         reg.setLogroJun(this.rs.getDouble("pJun"));
/* 335 */         reg.setLogroJul(this.rs.getDouble("pJul"));
/* 336 */         reg.setLogroAgo(this.rs.getDouble("pAgo"));
/* 337 */         reg.setLogroSep(this.rs.getDouble("pSep"));
/* 338 */         reg.setLogroOct(this.rs.getDouble("pOct"));
/* 339 */         reg.setLogroNov(this.rs.getDouble("pNov"));
/* 340 */         reg.setLogroDic(this.rs.getDouble("pDic"));
/*     */         
/* 342 */         reg.setMetaEne(this.rs.getDouble("pmEne"));
/* 343 */         reg.setMetaFeb(this.rs.getDouble("pmFeb"));
/* 344 */         reg.setMetaMar(this.rs.getDouble("pmMar"));
/* 345 */         reg.setMetaAbr(this.rs.getDouble("pmAbr"));
/* 346 */         reg.setMetaMay(this.rs.getDouble("pmMay"));
/* 347 */         reg.setMetaJun(this.rs.getDouble("pmJun"));
/* 348 */         reg.setMetaJul(this.rs.getDouble("pmJul"));
/* 349 */         reg.setMetaAgo(this.rs.getDouble("pmAgo"));
/* 350 */         reg.setMetaSep(this.rs.getDouble("pmSep"));
/* 351 */         reg.setMetaOct(this.rs.getDouble("pmOct"));
/* 352 */         reg.setMetaNov(this.rs.getDouble("pmNov"));
/* 353 */         reg.setMetaDic(this.rs.getDouble("pmDic"));
/*     */         
/* 355 */         reg.setMaxiEne(this.rs.getDouble("mEne"));
/* 356 */         reg.setMaxiFeb(this.rs.getDouble("mFeb"));
/* 357 */         reg.setMaxiMar(this.rs.getDouble("mMar"));
/* 358 */         reg.setMaxiAbr(this.rs.getDouble("mAbr"));
/* 359 */         reg.setMaxiMay(this.rs.getDouble("mMay"));
/* 360 */         reg.setMaxiJun(this.rs.getDouble("mJun"));
/* 361 */         reg.setMaxiJul(this.rs.getDouble("mJul"));
/* 362 */         reg.setMaxiAgo(this.rs.getDouble("mAgo"));
/* 363 */         reg.setMaxiSep(this.rs.getDouble("mSep"));
/* 364 */         reg.setMaxiOct(this.rs.getDouble("mOct"));
/* 365 */         reg.setMaxiNov(this.rs.getDouble("mNov"));
/* 366 */         reg.setMaxiDic(this.rs.getDouble("mDic"));
/*     */         
/* 368 */         reg.setMiniEne(this.rs.getDouble("aEne"));
/* 369 */         reg.setMiniFeb(this.rs.getDouble("aFeb"));
/* 370 */         reg.setMiniMar(this.rs.getDouble("aMar"));
/* 371 */         reg.setMiniAbr(this.rs.getDouble("aAbr"));
/* 372 */         reg.setMiniMay(this.rs.getDouble("aMay"));
/* 373 */         reg.setMiniJun(this.rs.getDouble("aJun"));
/* 374 */         reg.setMiniJul(this.rs.getDouble("aJul"));
/* 375 */         reg.setMiniAgo(this.rs.getDouble("aAgo"));
/* 376 */         reg.setMiniSep(this.rs.getDouble("aSep"));
/* 377 */         reg.setMiniOct(this.rs.getDouble("aOct"));
/* 378 */         reg.setMiniNov(this.rs.getDouble("aNov"));
/* 379 */         reg.setMiniDic(this.rs.getDouble("aDic"));
/*     */         
/* 381 */         resultados.add(reg);
/*     */       }
/*     */     
/* 384 */     } catch (Exception e) {
/* 385 */       e.printStackTrace();
/* 386 */       Utilidades.writeError("CalObjetivosDAO:cargarPlanEspecial ", e);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection cargarValorPlanEspecial(int codigoCiclo, int periodo, String secuencia, int codigoObjetivo, int codigoMeta, double valor, double meta) {
/* 407 */     Collection resultados = new ArrayList();
/*     */     try {
/* 409 */       String s = "SELECT A.DESCRIPCION NOMBRE_AREA, A.CODIGO FROM   UNIDADES_DEPENDENCIA A, CAL_PLANES           P, CAL_PLAN_OBJETIVOS   O, CAL_PLAN_METAS       M, CAL_LOGROS      L, PROCESOS             PRO, SUBPROCESOS          SUB WHERE  A.CODIGO = P.CODIGO_AREA AND P.CICLO = O.CODIGO_CICLO AND P.CODIGO_PLAN = O.CODIGO_PLAN AND O.CODIGO_CICLO = M.CODIGO_CICLO AND O.CODIGO_PLAN = M.CODIGO_PLAN AND O.CODIGO_OBJETIVO = M.CODIGO_OBJETIVO AND O.PROCESO = PRO.CODIGO AND O.SUBPROCESO = SUB.CODIGO AND O.PROCESO = SUB.PROCESO AND M.CODIGO_CICLO = L.CODIGO_CICLO AND M.CODIGO_PLAN = L.CODIGO_PLAN AND M.CODIGO_OBJETIVO = L.CODIGO_OBJETIVO AND M.CODIGO_META = L.CODIGO_META AND M.CODIGO_CICLO = " + codigoCiclo + " AND L.CODIGO_OBJETIVO=" + codigoObjetivo + " AND L.CODIGO_META=" + codigoMeta + " AND L.PERIODO=" + periodo + " AND A.SECUENCIA LIKE '" + secuencia + "%'";
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
/* 436 */       if (meta == -9999.0D) {
/* 437 */         s = s + " AND ROUND(L.VALOR_LOGRO,2)=" + valor;
/*     */       } else {
/*     */         
/* 440 */         s = s + " and 1=CASE WHEN m.TIPO_MEDICION='A' AND l.VALOR_LOGRO <= L.VALOR_META THEN 1 WHEN m.TIPO_MEDICION='B' AND l.VALOR_LOGRO < L.VALOR_META THEN 1 WHEN m.TIPO_MEDICION='C' AND l.VALOR_LOGRO <> L.VALOR_META THEN 1 WHEN m.TIPO_MEDICION='D' AND l.VALOR_LOGRO > L.VALOR_META THEN 1 WHEN m.TIPO_MEDICION='E' AND l.VALOR_LOGRO >= L.VALOR_META THEN 1 ELSE 0 END";
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 447 */       s = s + " ORDER  BY 1";
/*     */       
/* 449 */       boolean rtaDB = this.dat.parseSql(s);
/* 450 */       if (!rtaDB) {
/* 451 */         return resultados;
/*     */       }
/* 453 */       this.rs = this.dat.getResultSet();
/* 454 */       while (this.rs.next()) {
/* 455 */         CalEstadisticasDTO reg = new CalEstadisticasDTO();
/* 456 */         reg.setNombreArea(this.rs.getString("nombre_area"));
/* 457 */         reg.setCodigoArea(this.rs.getInt("codigo"));
/* 458 */         resultados.add(reg);
/*     */       }
/*     */     
/* 461 */     } catch (Exception e) {
/* 462 */       e.printStackTrace();
/* 463 */       Utilidades.writeError("CalObjetivosDAO:cargarPlanEspecial ", e);
/*     */     } 
/* 465 */     return resultados;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\CalEstadisticasDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */