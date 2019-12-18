/*    */ package sinco.data;
/*    */ 
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.SQLException;
/*    */ import sinco.business.UnidadMedidaDTO;
/*    */ import sinco.data.DBManager;
/*    */ import sinco.data.UnidadMedidaDAO;
/*    */ 
/*    */ public class UnidadMedidaDAO
/*    */ {
/*    */   ResultSet rs;
/*    */   
/*    */   public void close() {
/*    */     try {
/* 15 */       this.dat.close();
/*    */     }
/* 17 */     catch (Exception e) {}
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 22 */   DBManager dat = new DBManager();
/*    */ 
/*    */   
/* 25 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*    */ 
/*    */   
/*    */   public UnidadMedidaDTO leerRegistro() {
/*    */     try {
/* 30 */       UnidadMedidaDTO um = new UnidadMedidaDTO();
/* 31 */       um.setCodigo(this.rs.getString("codigo_unidad"));
/* 32 */       um.setDescripcion(this.rs.getString("nombre_unidad"));
/* 33 */       um.setFactor(this.rs.getInt("factor_conversion"));
/* 34 */       return um;
/*    */     }
/* 36 */     catch (SQLException e) {
/* 37 */       e.printStackTrace();
/*    */       
/* 39 */       return null;
/*    */     } 
/*    */   }
/*    */   public UnidadMedidaDTO next() {
/*    */     try {
/* 44 */       if (this.rs.next()) {
/* 45 */         return leerRegistro();
/*    */       }
/*    */     }
/* 48 */     catch (SQLException e) {
/* 49 */       e.printStackTrace();
/*    */     } 
/* 51 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public UnidadMedidaDTO getUnidadMedida(String cod) {
/*    */     try {
/* 57 */       boolean rta = this.dat.parseSql("select * from sis_unidades_medida where codigo_unidad='" + cod + "'");
/* 58 */       if (!rta) return null; 
/* 59 */       this.rs = this.dat.getResultSet();
/* 60 */       if (this.rs.next()) {
/* 61 */         return leerRegistro();
/*    */       }
/*    */     }
/* 64 */     catch (SQLException e) {
/* 65 */       e.printStackTrace();
/*    */     } 
/* 67 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\UnidadMedidaDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */