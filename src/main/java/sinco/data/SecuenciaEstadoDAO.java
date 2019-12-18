/*    */ package sinco.data;
/*    */ 
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.SQLException;
/*    */ import sinco.business.SecuenciaEstadoDTO;
/*    */ import sinco.business.Utilidades;
/*    */ import sinco.data.DBManager;
/*    */ import sinco.data.SecuenciaEstadoDAO;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SecuenciaEstadoDAO
/*    */ {
/*    */   ResultSet rs;
/* 15 */   DBManager dat = new DBManager();
/*    */ 
/*    */   
/* 18 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*    */ 
/*    */   
/*    */   public void close() {
/*    */     try {
/* 23 */       this.dat.close();
/*    */     }
/* 25 */     catch (Exception e) {}
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SecuenciaEstadoDTO leerRegistro() {
/*    */     try {
/* 36 */       SecuenciaEstadoDTO reg = new SecuenciaEstadoDTO();
/* 37 */       reg.setCodigoEstadoInicial(this.rs.getInt("codigo_estado_inicial"));
/* 38 */       reg.setCodigoEstadoFinal(this.rs.getInt("codigo_estado_final"));
/* 39 */       reg.setCodigoRol(this.rs.getInt("codigo_rol"));
/* 40 */       return reg;
/*    */     }
/* 42 */     catch (Exception e) {
/* 43 */       e.printStackTrace();
/* 44 */       Utilidades.writeError("ServicioAreaFactory.leerRegistro ", e);
/*    */       
/* 46 */       return null;
/*    */     } 
/*    */   }
/*    */   
/*    */   public SecuenciaEstadoDTO next() {
/*    */     try {
/* 52 */       if (this.rs.next());
/*    */     
/*    */     }
/* 55 */     catch (SQLException e) {
/* 56 */       e.printStackTrace();
/*    */     } 
/* 58 */     return null;
/*    */   }
/*    */   
/*    */   public SecuenciaEstadoDTO getSecuenciaEstado(int codfin, int codini) {
/*    */     try {
/* 63 */       boolean rta = this.dat.parseSql("select * from secuencia_estados  where codigo_estado_final=" + codfin + " and codigo_estado_inicial=" + codini);
/*    */ 
/*    */       
/* 66 */       if (!rta) return null; 
/* 67 */       this.rs = this.dat.getResultSet();
/* 68 */       if (this.rs.next()) {
/* 69 */         return leerRegistro();
/*    */       }
/*    */     }
/* 72 */     catch (SQLException e) {
/* 73 */       e.printStackTrace();
/*    */     } 
/* 75 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\SecuenciaEstadoDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */