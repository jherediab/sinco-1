/*     */ package sinco.spec;
/*     */ 
/*     */ import com.lowagie.text.Chunk;
/*     */ import com.lowagie.text.Document;
/*     */ import com.lowagie.text.DocumentException;
/*     */ import com.lowagie.text.Font;
/*     */ import com.lowagie.text.PageSize;
/*     */ import com.lowagie.text.Paragraph;
/*     */ import com.lowagie.text.Phrase;
/*     */ import com.lowagie.text.pdf.PdfPCell;
/*     */ import com.lowagie.text.pdf.PdfPTable;
/*     */ import com.lowagie.text.pdf.PdfWriter;
/*     */ import java.awt.Color;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import sinco.business.CalDocumentosDTO;
/*     */ import sinco.business.FechaDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.spec.PdfListaMaestra;
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
/*     */ public class PdfListaMaestra
/*     */ {
/*     */   private Document document;
/*     */   private PdfPTable laTabla;
/*     */   private PdfWriter writer;
/*     */   
/*     */   public boolean abrirArchivo(ByteArrayOutputStream baos) {
/*     */     try {
/*  39 */       this.document = new Document(PageSize.LETTER);
/*  40 */       PdfWriter.getInstance(this.document, baos);
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
/*  51 */       this.document.open();
/*  52 */       return true;
/*     */     }
/*  54 */     catch (DocumentException ee) {
/*  55 */       ee.printStackTrace();
/*     */     }
/*  57 */     catch (Exception e) {
/*  58 */       e.printStackTrace();
/*     */     } 
/*  60 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean escribirCabecera() {
/*     */     try {
/*  71 */       PdfPTable table = new PdfPTable(1);
/*  72 */       table.setWidths(new float[] { 100.0F });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  80 */       table.addCell(addCell("Matriz de Documentos internos del Sistema de Gestión de Calidad", new Font(1, 14.0F, 1, new Color(255, 0, 0)), new Color(255, 255, 255), new Color(255, 255, 255), 1, 7));
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
/*  91 */       table.addCell(addCell("Hospital Regional de Chiquinquirá", new Font(1, 10.0F, 1, new Color(0, 0, 0)), new Color(255, 255, 255), new Color(255, 255, 255), 1, 7));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 113 */       FechaDTO miFecha = new FechaDTO(Utilidades.fechaActual());
/* 114 */       table.addCell(addCell("Fecha de actualización: " + miFecha.nombreMes(), new Font(1, 10.0F, 1, new Color(0, 0, 0)), new Color(255, 255, 255), new Color(255, 255, 255), 1, 7));
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
/* 125 */       this.document.add(table);
/*     */       
/* 127 */       this.laTabla = new PdfPTable(5);
/* 128 */       this.laTabla.setWidths(new float[] { 5.0F, 30.0F, 15.0F, 25.0F, 25.0F });
/* 129 */       titulos(this.laTabla);
/* 130 */       return true;
/*     */     }
/* 132 */     catch (Exception e) {
/* 133 */       e.printStackTrace();
/*     */       
/* 135 */       return false;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 158 */   public boolean copiar(String origen, String destino) { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean nombreProceso(String nombreProceso) {
/*     */     try {
/* 169 */       this.laTabla.addCell(addCell(nombreProceso, new Font(1, 9.0F, 1, new Color(16, 21, 226)), new Color(255, 255, 255), new Color(0, 0, 0), 1, 5));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 179 */       return true;
/*     */     }
/* 181 */     catch (Exception e) {
/* 182 */       e.printStackTrace();
/*     */       
/* 184 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean nombreSubProceso(String nombreSubProceso) {
/*     */     try {
/* 195 */       this.laTabla.addCell(addCell(nombreSubProceso, new Font(1, 9.0F, 1, new Color(255, 255, 255)), new Color(255, 0, 0), new Color(0, 0, 0), 1, 5));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 205 */       return true;
/*     */     }
/* 207 */     catch (Exception e) {
/* 208 */       e.printStackTrace();
/*     */       
/* 210 */       return false;
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
/*     */ 
/*     */   
/*     */   public boolean detalle(int i, String codigo, String descripcion, String version, String fechaVersion, Collection arrDistribuido, Collection arrResponsables) {
/*     */     try {
/* 229 */       this.laTabla.addCell(addCell("" + i, new Font(1, 8.0F, 1, new Color(0, 0, 0)), null, new Color(0, 0, 0), 0, 0));
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
/* 253 */       this.laTabla.addCell(addCell(descripcion, new Font(1, 8.0F, 1, new Color(0, 0, 0)), null, new Color(0, 0, 0), 0, 0));
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
/* 264 */       this.laTabla.addCell(addCell(fechaVersion, new Font(1, 8.0F, 0, new Color(0, 0, 0)), null, new Color(0, 0, 0), 0, 0));
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
/* 277 */       PdfPTable miTabla2 = new PdfPTable(1);
/* 278 */       miTabla2.setWidths(new float[] { 100.0F });
/*     */       
/* 280 */       Iterator iteratorRes = arrResponsables.iterator();
/* 281 */       while (iteratorRes.hasNext()) {
/* 282 */         CalDocumentosDTO regD = (CalDocumentosDTO)iteratorRes.next();
/* 283 */         miTabla2.addCell(addCell(regD.getDescripcion().toLowerCase(), new Font(1, 8.0F, 0, new Color(0, 0, 0)), null, new Color(255, 255, 255), 0, 0));
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 294 */       this.laTabla.addCell(miTabla2);
/*     */ 
/*     */       
/* 297 */       PdfPTable miTabla = new PdfPTable(1);
/* 298 */       miTabla.setWidths(new float[] { 100.0F });
/*     */       
/* 300 */       Iterator iteratorDoc = arrDistribuido.iterator();
/* 301 */       while (iteratorDoc.hasNext()) {
/* 302 */         CalDocumentosDTO regD = (CalDocumentosDTO)iteratorDoc.next();
/* 303 */         miTabla.addCell(addCell(regD.getDescripcion().toLowerCase(), new Font(1, 8.0F, 0, new Color(0, 0, 0)), null, new Color(255, 255, 255), 0, 0));
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 314 */       this.laTabla.addCell(miTabla);
/*     */ 
/*     */ 
/*     */       
/* 318 */       return true;
/*     */     }
/* 320 */     catch (Exception e) {
/* 321 */       e.printStackTrace();
/*     */       
/* 323 */       return false;
/*     */     } 
/*     */   }
/*     */   public boolean finDetalle() {
/*     */     try {
/* 328 */       this.document.add(this.laTabla);
/*     */       
/* 330 */       this.document.add(new Paragraph("\n"));
/*     */       
/* 332 */       return true;
/*     */     }
/* 334 */     catch (Exception e) {
/* 335 */       e.printStackTrace();
/*     */       
/* 337 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean cerrarArchivo() {
/*     */     try {
/* 347 */       this.document.close();
/* 348 */       return true;
/*     */     }
/* 350 */     catch (Exception e) {
/* 351 */       e.printStackTrace();
/*     */       
/* 353 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean titulos(PdfPTable table) {
/*     */     try {
/* 365 */       this.laTabla.addCell(addCell("#", new Font(1, 8.0F, 1, new Color(255, 255, 255)), new Color(0, 0, 255), new Color(0, 0, 0), 1, 0));
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
/* 376 */       this.laTabla.addCell(addCell("NOMBRE", new Font(1, 8.0F, 1, new Color(255, 255, 255)), new Color(0, 0, 255), new Color(0, 0, 0), 1, 0));
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
/* 387 */       this.laTabla.addCell(addCell("ULTIMO CAMBIO", new Font(1, 8.0F, 1, new Color(255, 255, 255)), new Color(0, 0, 255), new Color(0, 0, 0), 1, 0));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 397 */       this.laTabla.addCell(addCell("RESPONSABLE", new Font(1, 8.0F, 1, new Color(255, 255, 255)), new Color(0, 0, 255), new Color(0, 0, 0), 1, 0));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 407 */       this.laTabla.addCell(addCell("DISTRIBUIDO", new Font(1, 8.0F, 1, new Color(255, 255, 255)), new Color(0, 0, 255), new Color(0, 0, 0), 1, 0));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 418 */     catch (Exception e) {
/* 419 */       e.printStackTrace();
/* 420 */       return false;
/*     */     } 
/* 422 */     return true;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private PdfPCell addCell(String contenido, Font letra, Color colorFondo, Color colorBorde, int alineacion, int colspan) {
/*     */     try {
/* 446 */       Phrase pg = new Phrase();
/* 447 */       pg.add(new Chunk(contenido, letra));
/*     */       
/* 449 */       PdfPCell cell = new PdfPCell(pg);
/* 450 */       if (colorBorde != null) {
/* 451 */         cell.setBorderColor(colorBorde);
/*     */       }
/* 453 */       cell.setHorizontalAlignment(alineacion);
/*     */       
/* 455 */       if (colorFondo != null) {
/* 456 */         cell.setBackgroundColor(colorFondo);
/*     */       }
/*     */       
/* 459 */       cell.setVerticalAlignment(5);
/*     */       
/* 461 */       if (colspan > 0) {
/* 462 */         cell.setColspan(colspan);
/*     */       }
/*     */       
/* 465 */       return cell;
/*     */     }
/* 467 */     catch (Exception e) {
/* 468 */       e.printStackTrace();
/*     */       
/* 470 */       return null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\spec\PdfListaMaestra.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */