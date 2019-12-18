/*    */ package sinco.business;
/*    */ 
/*    */ import java.util.Date;
/*    */ import sinco.business.ParametrosDTO;
/*    */ import sinco.business.SemaforoDTO;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SemaforoDTO
/*    */ {
/* 15 */   private static int semaforo = 0;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void rojo() {
/* 23 */     while (semaforo == 1) {
/* 24 */       long timeout = ParametrosDTO.getInt("timeout");
/* 25 */       long startTime = (new Date()).getTime();
/* 26 */       boolean bSalir = false;
/* 27 */       while (!bSalir) {
/* 28 */         if ((new Date()).getTime() - startTime >= timeout) {
/* 29 */           bSalir = true;
/*    */         }
/*    */         
/* 32 */         if (semaforo == 0) {
/*    */           break;
/*    */         }
/*    */       } 
/*    */     } 
/*    */     
/* 38 */     semaforo = 1;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 45 */   public void verde() { semaforo = 0; }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\SemaforoDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */