/*    */ package sinco.spec;
/*    */ import java.util.Hashtable;
/*    */ import sinco.spec.Cache;
/*    */ 
/*    */ public class Cache {
/*  6 */   private static Cache instance = null;
/*    */ 
/*    */ 
/*    */   
/* 10 */   private Hashtable cache = new Hashtable();
/*    */ 
/*    */   
/*    */   public Cache getInstance() {
/* 14 */     if (instance == null) {
/* 15 */       instance = new Cache();
/*    */     }
/* 17 */     return instance;
/*    */   }
/*    */ 
/*    */   
/* 21 */   public void addObject(String usuario, StringBuffer buf) { this.cache.put(usuario, buf); }
/*    */ 
/*    */   
/*    */   public String getObject(String usuario) {
/*    */     try {
/* 26 */       StringBuffer menu = (StringBuffer)this.cache.get(usuario);
/* 27 */       if (menu != null) {
/* 28 */         return menu.toString();
/*    */       }
/*    */     }
/* 31 */     catch (Exception e) {}
/* 32 */     return "";
/*    */   }
/*    */ 
/*    */   
/* 36 */   public void expire() { this.cache.clear(); }
/*    */ 
/*    */ 
/*    */   
/* 40 */   public void expire(String key) { this.cache.remove(key); }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\spec\Cache.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */