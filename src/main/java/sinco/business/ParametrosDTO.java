/*     */ package sinco.business;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.util.Properties;
/*     */ import sinco.business.ParametrosDTO;
/*     */ 
/*     */ public class ParametrosDTO {
/*  12 */   private static Properties props = new Properties();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean leerParametros(String archivo) {
/*  20 */     File file = new File(archivo);
/*  21 */     FileInputStream fos = null;
/*     */     try {
/*  23 */       fos = new FileInputStream(file);
/*  24 */       props.load(fos);
/*  25 */       fos.close();
/*  26 */       return true;
/*     */     }
/*  28 */     catch (IOException ex) {
/*  29 */       System.out.println(ex.getMessage());
/*  30 */       ex.printStackTrace();
/*     */       
/*  32 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getString(String key) {
/*     */     try {
/*  42 */       String retorno = props.getProperty(key);
/*  43 */       if (retorno == null) return ""; 
/*  44 */       return retorno;
/*     */     }
/*  46 */     catch (Exception e) {
/*     */ 
/*     */       
/*  49 */       return "";
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getInt(String key) {
/*     */     try {
/*  59 */       return Integer.parseInt(props.getProperty(key));
/*     */     }
/*  61 */     catch (Exception e) {
/*     */       
/*  63 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean getBoolean(String key) {
/*     */     try {
/*  73 */       return Boolean.getBoolean(props.getProperty(key));
/*     */     }
/*  75 */     catch (Exception e) {
/*     */       
/*  77 */       return true;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float getFloat(String key) {
/*     */     try {
/*  88 */       return Float.parseFloat(props.getProperty(key));
/*     */     }
/*  90 */     catch (Exception e) {
/*     */       
/*  92 */       return 0.0F;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean put(String key, String value) {
/*     */     try {
/* 103 */       props.put(key, value);
/* 104 */       return true;
/*     */     }
/* 106 */     catch (Exception e) {
/*     */       
/* 108 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean list() {
/* 116 */     props.list(System.out);
/* 117 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 125 */   public static Properties getProp() { return props; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void save() throws IOException {
        OutputStream out = new FileOutputStream(getString("logs") + "sinco.txt");
        props.save(out, "propiedades");
        out.close();
    }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\ParametrosDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */