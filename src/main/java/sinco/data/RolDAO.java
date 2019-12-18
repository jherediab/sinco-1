/*    */ package sinco.data;
/*    */ 
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.SQLException;
/*    */ import sinco.business.RolDTO;
/*    */ import sinco.business.Utilidades;
/*    */ import sinco.data.DBManager;
/*    */ import sinco.data.RolDAO;
/*    */ 
/*    */ public class RolDAO
/*    */ {
/*    */   ResultSet rs;
/*    */   
/*    */   public void close() {
/*    */     try {
/* 16 */       this.dat.close();
/*    */     }
/* 18 */     catch (Exception e) {}
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 23 */   DBManager dat = new DBManager();
/*    */ 
/*    */   
/* 26 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public RolDTO leerRegistro() {
/*    */     try {
/* 36 */       RolDTO reg = new RolDTO();
/* 37 */       reg.setCodigo(this.rs.getInt("codigo"));
/* 38 */       reg.setDescripcion(this.rs.getString("descripcion"));
/* 39 */       return reg;
/*    */     }
/* 41 */     catch (Exception e) {
/* 42 */       e.printStackTrace();
/* 43 */       Utilidades.writeError("ServicioAreaFactory.leerRegistro ", e);
/*    */       
/* 45 */       return null;
/*    */     } 
/*    */   }
/*    */   
/*    */   public RolDTO next() {
/*    */     try {
/* 51 */       if (this.rs.next()) {
/* 52 */         return leerRegistro();
/*    */       }
/*    */     }
/* 55 */     catch (SQLException e) {
/* 56 */       e.printStackTrace();
/*    */     } 
/* 58 */     return null;
/*    */   }
/*    */   
/*    */   public RolDTO getRol(int cod) {
/*    */     try {
/* 63 */       boolean rta = this.dat.parseSql("select * from roles where codigo=" + cod);
/* 64 */       if (!rta) return null; 
/* 65 */       this.rs = this.dat.getResultSet();
/* 66 */       if (this.rs.next()) {
/* 67 */         return leerRegistro();
/*    */       }
/*    */     }
/* 70 */     catch (SQLException e) {
/* 71 */       e.printStackTrace();
/*    */     } 
/* 73 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\RolDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */