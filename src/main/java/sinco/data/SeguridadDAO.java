/*    */ package sinco.data;
/*    */ 
/*    */ import java.sql.ResultSet;
/*    */ import sinco.business.Utilidades;
/*    */ import sinco.data.DBManager;
/*    */ import sinco.data.SeguridadDAO;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SeguridadDAO
/*    */ {
/*    */   ResultSet rs;
/* 19 */   DBManager dat = new DBManager();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 26 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void close() {
/*    */     try {
/* 34 */       this.dat.close();
/*    */     }
/* 36 */     catch (Exception e) {
/* 37 */       Utilidades.writeError("close", e);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean tieneLlave(int grupo, String permiso) {
/*    */     try {
/* 46 */       String s = "select * from sis_permisos_grupo where grupo=" + grupo + " and permiso='" + permiso + "'";
/* 47 */       this.dat.parseSql(s);
/* 48 */       this.rs = this.dat.getResultSet();
/* 49 */       if (this.rs.next()) {
/* 50 */         return true;
/*    */       }
/*    */     }
/* 53 */     catch (Exception e) {
/* 54 */       e.printStackTrace();
/* 55 */       Utilidades.writeError("cargarTodos ", e);
/*    */     } 
/* 57 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\SeguridadDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */