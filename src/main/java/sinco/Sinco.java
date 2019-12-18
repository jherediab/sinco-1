/*     */ package sinco;
/*     */ 
import com.lutris.appserver.server.ApplicationException;
import com.lutris.appserver.server.StandardApplication;
import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
import com.lutris.util.Config;
import com.lutris.util.ConfigException;
/*     */ import sinco.business.ParametrosDTO;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;


/*     */ 
/*     */ public class Sinco
/*     */   extends StandardApplication
/*     */ {
/*     */   public void startup(Config appConfig) throws ApplicationException {
/*     */     String sTemp;
/*  36 */     super.startup(appConfig);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  43 */       sTemp = appConfig.getString("TipoBaseDatos", "1");
/*  44 */     } catch (ConfigException e) {
/*  45 */       throw new ApplicationException("TipoBaseDatos no encontrado en archivo de configuraciÃ³n.", e);
/*     */     } 
/*  47 */     ParametrosDTO.put("tipoBaseDatos", sTemp);
/*     */     
/*     */     try {
/*  50 */       sTemp = appConfig.getString("logs", "/apps/logs/");
/*  51 */     } catch (ConfigException e) {
/*  52 */       throw new ApplicationException("TipoBaseDatos no encontrado en archivo de configuraciÃ³n.", e);
/*     */     } 
/*  54 */     ParametrosDTO.put("logs", sTemp);
/*     */ 
/*     */     
/*     */     try {
/*  58 */       sTemp = appConfig.getString("pruebas", "0");
/*  59 */     } catch (ConfigException e) {
/*  60 */       throw new ApplicationException("password", e);
/*     */     } 
/*  62 */     ParametrosDTO.put("pruebas", sTemp);
/*     */     
/*     */     try {
/*  65 */       sTemp = appConfig.getString("traceo", "0");
/*  66 */     } catch (ConfigException e) {
/*  67 */       throw new ApplicationException("password", e);
/*     */     } 
/*  69 */     ParametrosDTO.put("traceo", sTemp);
/*     */ 
/*     */     
/*     */     try {
/*  73 */       sTemp = appConfig.getString("ConectorBaseDatos", "jdbc/SincoDB");
/*  74 */     } catch (ConfigException e) {
/*  75 */       throw new ApplicationException("traceo", e);
/*     */     } 
/*  77 */     ParametrosDTO.put("ConectorBaseDatos", sTemp);
/*     */ 
/*     */     
/*  80 */     Varios ovarios = new Varios();
/*  81 */     boolean rta = ovarios.cargarParametros();
/*  82 */     if (!rta) {
/*  83 */       throw new ApplicationException("Error Inicializando sinco");
/*     */     }
/*     */     
/*  86 */     ParametrosDTO.put("sinco.maxconn", "205");
/*     */     
/*  88 */     MenuDO.iniciarMenu();
/*     */     
/*     */     try {
/*  91 */       ParametrosDTO.save();
/*     */     }
/*  93 */     catch (Exception e) {
/*  94 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 102 */   public boolean requestPreprocessor(HttpPresentationComms comms) throws Exception { return super.requestPreprocessor(comms); }
/*     */ 
/*     */ 
/*     */   
/*     */   public void shutdown() {
/*     */     try {
/* 108 */       DBManager dat = new DBManager();
/* 109 */       dat.shutdown();
/*     */     }
/* 111 */     catch (Exception e) {}
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
/* 123 */   public String toHtml() { return "This is <I>sinco</I>"; }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\Sinco.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */