/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.DocumentosDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.DocumentosDAO;
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
/*     */ public class DocumentosDAO
/*     */ {
/*     */   ResultSet rs;
/*  26 */   DBManager dat = new DBManager();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  36 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/*     */     try {
/*  43 */       this.dat.close();
/*     */     }
/*  45 */     catch (Exception e) {
/*  46 */       Utilidades.writeError("DocumentosDAO:close " + e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DocumentosDTO next() {
/*     */     try {
/*  57 */       if (this.rs.next()) {
/*  58 */         return leerRegistro();
/*     */       }
/*     */     }
/*  61 */     catch (Exception e) {
/*  62 */       e.printStackTrace();
/*  63 */       Utilidades.writeError("DocumentosDAO:next " + e.getMessage());
/*     */     } 
/*  65 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DocumentosDTO leerRegistro() {
/*     */     try {
/*  75 */       DocumentosDTO reg = new DocumentosDTO();
/*  76 */       reg.setTipoDocumento(this.rs.getString("tipo_documento"));
/*  77 */       reg.setNumeroDocumento(this.rs.getString("numero_documento"));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  87 */       reg.setDocumento(this.rs.getString("documento"));
/*     */       
/*  89 */       reg.setEstado(this.rs.getString("estado"));
/*  90 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  91 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  92 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  93 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  94 */       return reg;
/*     */     }
/*  96 */     catch (Exception e) {
/*  97 */       e.printStackTrace();
/*  98 */       Utilidades.writeError("DocumentosDAO:leerRegistro " + e.getMessage());
/*     */       
/* 100 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DocumentosDTO nextDocumento() {
/*     */     try {
/* 111 */       DocumentosDTO reg = new DocumentosDTO();
/* 112 */       reg.setTipoDocumento(this.rs.getString("tipo_documento"));
/* 113 */       reg.setNumeroDocumento(this.rs.getString("numero_documento"));
/* 114 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/* 115 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/* 116 */       return reg;
/*     */     }
/* 118 */     catch (Exception e) {
/* 119 */       e.printStackTrace();
/* 120 */       Utilidades.writeError("DocumentosDAO:next " + e.getMessage());
/*     */       
/* 122 */       return null;
/*     */     } 
/*     */   }
/*     */   public Collection<DocumentosDTO> cargarTodos(String tipoDocumento, int numeroEstudioPrevio) {
/* 126 */     Collection<DocumentosDTO> resultados = new ArrayList<DocumentosDTO>();
/*     */     try {
/* 128 */       String s = "SELECT  numero_documento,  tipo_documento,  fecha_insercion,  usuario_insercion  FROM siau_documentos WHERE tipo_documento like '" + tipoDocumento + "'" + " and numero_documento='" + numeroEstudioPrevio + "'";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 137 */       boolean rtaDB = this.dat.parseSql(s);
/* 138 */       if (!rtaDB) {
/* 139 */         return resultados;
/*     */       }
/* 141 */       this.rs = this.dat.getResultSet();
/* 142 */       while (this.rs.next()) {
/* 143 */         resultados.add(nextDocumento());
/*     */       }
/*     */     }
/* 146 */     catch (Exception e) {
/* 147 */       e.printStackTrace();
/* 148 */       Utilidades.writeError("DocumentosDAO:cargarTodosEstudioPrevio " + e.getMessage());
/*     */     } 
/* 150 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DocumentosDTO cargarRegistro(String tipoDocumento, String numeroDocumento) {
/*     */     try {
/* 160 */       String s = "select * from siau_documentos where tipo_documento='" + tipoDocumento + "'" + " and numero_documento='" + numeroDocumento + "'";
/*     */ 
/*     */       
/* 163 */       this.dat.parseSql(s);
/* 164 */       this.rs = this.dat.getResultSet();
/* 165 */       if (this.rs.next()) {
/* 166 */         return leerRegistro();
/*     */       }
/*     */     }
/* 169 */     catch (Exception e) {
/* 170 */       e.printStackTrace();
/* 171 */       Utilidades.writeError("DocumentosDAO:cargarDocumentos " + e.getMessage());
/*     */     } 
/* 173 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 183 */   public String crearFormatoEstudioPrevio(int numero, String usuario) { return this.dat.crearFormatoEstudioPrevio(numero, usuario); }
/*     */ 
/*     */ 
/*     */   
/* 187 */   public String crearFormatoContrato(int numero, String usuario) { return this.dat.crearFormatoContrato(numero, usuario); }
/*     */ 
/*     */ 
/*     */   
/* 191 */   public String crearFormatoAdicion(int consecutivoContrato, int consecutivoAdicion, String usuario) { return this.dat.crearFormatoAdicion(consecutivoContrato, consecutivoAdicion, usuario); }
/*     */ 
/*     */ 
/*     */   
/* 195 */   public String crearFormatoActaInicio(int numero, String usuario) { return this.dat.crearFormatoActaInicio(numero, usuario); }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\DocumentosDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */