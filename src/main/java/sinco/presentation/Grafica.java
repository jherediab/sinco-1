/*     */ package sinco.presentation;
/*     */ 
/*     */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*     */ import com.lutris.util.KeywordValueException;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import org.jfree.chart.ChartFactory;
/*     */ import org.jfree.chart.ChartUtilities;
/*     */ import org.jfree.chart.JFreeChart;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.data.category.DefaultCategoryDataset;
/*     */ import sinco.business.IndicadorActividadesDTO;
/*     */ import sinco.business.IndicadoresDTO;
/*     */ import sinco.data.IndicadorActividadesDAO;
/*     */ import sinco.data.IndicadoresDAO;
/*     */ import sinco.presentation.Grafica;
/*     */ 
/*     */ 
/*     */ public class Grafica
/*     */ {
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  24 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  25 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  28 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*     */     
/*  30 */     String _operacion = comms.request.getParameter("_operacion");
/*  31 */     if (_operacion == null || _operacion.length() == 0) {
/*  32 */       _operacion = "X";
/*     */     }
/*     */     
/*  35 */     if (_operacion.equals("GR1")) {
/*  36 */       grafica(comms, idNav);
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
/*     */   private void grafica(HttpPresentationComms comms, String idNav) throws HttpPresentationException, KeywordValueException {
/*  52 */     IndicadoresDAO res = new IndicadoresDAO();
/*  53 */     IndicadoresDTO ind = res.cargarRegistro(comms.request.getParameter("cod"));
/*     */     
/*  55 */     IndicadorActividadesDAO rsAct = new IndicadorActividadesDAO();
/*  56 */     Collection<IndicadorActividadesDTO> actividades = rsAct.cargarTodos(comms.request.getParameter("cod"));
/*  57 */     rsAct.close();
/*     */ 
/*     */     
/*  60 */     Iterator<IndicadorActividadesDTO> iteActividades = actividades.iterator();
/*  61 */     IndicadorActividadesDTO actividad = (IndicadorActividadesDTO)iteActividades.next();
/*     */ 
/*     */ 
/*     */     
/*  65 */     int esperado1 = ind.getValormes1();
/*  66 */     int esperado2 = ind.getValormes2();
/*  67 */     int esperado3 = ind.getValormes3();
/*  68 */     int esperado4 = ind.getValormes4();
/*  69 */     int esperado5 = ind.getValormes5();
/*  70 */     int esperado6 = ind.getValormes6();
/*  71 */     int esperado7 = ind.getValormes7();
/*  72 */     int esperado8 = ind.getValormes8();
/*  73 */     int esperado9 = ind.getValormes9();
/*  74 */     int esperado10 = ind.getValormes10();
/*  75 */     int esperado11 = ind.getValormes11();
/*  76 */     int esperado12 = ind.getValormes12();
/*     */     
/*  78 */     int valorReal1 = valorReal(actividad.getIdIndicador(), "Enero");
/*  79 */     int valorReal2 = valorReal(actividad.getIdIndicador(), "Febrero");
/*  80 */     int valorReal3 = valorReal(actividad.getIdIndicador(), "Marzo");
/*  81 */     int valorReal4 = valorReal(actividad.getIdIndicador(), "Abril");
/*  82 */     int valorReal5 = valorReal(actividad.getIdIndicador(), "Mayo");
/*  83 */     int valorReal6 = valorReal(actividad.getIdIndicador(), "Junio");
/*  84 */     int valorReal7 = valorReal(actividad.getIdIndicador(), "Julio");
/*  85 */     int valorReal8 = valorReal(actividad.getIdIndicador(), "Agosto");
/*  86 */     int valorReal9 = valorReal(actividad.getIdIndicador(), "Septiembre");
/*  87 */     int valorReal10 = valorReal(actividad.getIdIndicador(), "Octubre");
/*  88 */     int valorReal11 = valorReal(actividad.getIdIndicador(), "Noviembre");
/*  89 */     int valorReal12 = valorReal(actividad.getIdIndicador(), "Diciembre");
/*     */ 
/*     */ 
/*     */     
/*  93 */     DefaultCategoryDataset objDatos = new DefaultCategoryDataset();
/*     */     
/*  95 */     objDatos.addValue(esperado1, "esperado", "Enero");
/*  96 */     objDatos.addValue(esperado2, "esperado", "Febrero");
/*  97 */     objDatos.addValue(esperado3, "esperado", "Marzo");
/*  98 */     objDatos.addValue(esperado4, "esperado", "Abril");
/*  99 */     objDatos.addValue(esperado5, "esperado", "Mayo");
/* 100 */     objDatos.addValue(esperado6, "esperado", "Junio");
/* 101 */     objDatos.addValue(esperado7, "esperado", "Julio");
/* 102 */     objDatos.addValue(esperado8, "esperado", "Agosto");
/* 103 */     objDatos.addValue(esperado9, "esperado", "Septiembre");
/* 104 */     objDatos.addValue(esperado10, "esperado", "Ooctubre");
/* 105 */     objDatos.addValue(esperado11, "esperado", "Noviembre");
/* 106 */     objDatos.addValue(esperado12, "esperado", "Diciembre");
/*     */ 
/*     */     
/* 109 */     objDatos.addValue(valorReal1, "Real", "Enero");
/* 110 */     objDatos.addValue(valorReal2, "Real", "Febrero");
/* 111 */     objDatos.addValue(valorReal3, "Real", "Marzo");
/* 112 */     objDatos.addValue(valorReal4, "Real", "Abril");
/* 113 */     objDatos.addValue(valorReal5, "Real", "Mayo");
/* 114 */     objDatos.addValue(valorReal6, "Real", "Junio");
/* 115 */     objDatos.addValue(valorReal7, "Real", "Julio");
/* 116 */     objDatos.addValue(valorReal8, "Real", "Agosto");
/* 117 */     objDatos.addValue(valorReal9, "Real", "Septiembre");
/* 118 */     objDatos.addValue(valorReal10, "Real", "Octubre");
/* 119 */     objDatos.addValue(valorReal11, "Real", "Noviembre");
/* 120 */     objDatos.addValue(valorReal12, "Real", "Diciembre");
/*     */ 
/*     */ 
/*     */     
/* 124 */     JFreeChart objGrafico = ChartFactory.createLineChart("Resultados por periodos", "Metas", "Valores", objDatos, PlotOrientation.VERTICAL, true, true, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 135 */       ChartUtilities.writeChartAsJPEG(comms.response.getOutputStream(), objGrafico, 700, 350);
/*     */     }
/* 137 */     catch (Exception e) {}
/*     */   }
/*     */ 
/*     */   
/*     */   private int valorReal(String string, String mes) {
/*     */     try {
/* 143 */       IndicadorActividadesDAO rs = new IndicadorActividadesDAO();
/* 144 */       IndicadorActividadesDTO reg = rs.cargarRegistro(string, mes);
/* 145 */       rs.close();
/* 146 */       return reg.getPorcentaje();
/* 147 */     } catch (Exception e) {
/*     */ 
/*     */       
/* 150 */       return 0;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\Grafica.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */