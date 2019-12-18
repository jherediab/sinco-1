/*     */ package sinco.business;
/*     */ 
/*     */ import sinco.business.LetrasDTO;
/*     */ 
/*     */ 
/*     */ public class LetrasDTO
/*     */ {
/*     */   String sCadenaSalida;
/*     */   
/*     */   public LetrasDTO(int iValor) {
/*     */     try {
/*  12 */       this.sCadenaSalida = LetrasCientos(iValor, 0);
/*     */     }
/*  14 */     catch (Exception e) {}
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
/*     */   public LetrasDTO(double dValor) {
/*     */     try {
/*  28 */       double[] Cifras = new double[5];
/*     */ 
/*     */       
/*  31 */       int Bandera = 0;
/*     */       
/*  33 */       this.sCadenaSalida = "";
/*  34 */       double dDenominador = 1.0E12D; int i;
/*  35 */       for (i = 4; i >= 0; i--) {
/*  36 */         Cifras[i] = (dValor - dValor % dDenominador) / dDenominador;
/*  37 */         dValor %= dDenominador;
/*  38 */         if (dDenominador >= 1000000.0D && Cifras[i] > 0.0D) {
/*  39 */           Bandera = 1;
/*     */         
/*     */         }
/*  42 */         else if (Bandera == 1 && Cifras[i] > 0.0D) {
/*  43 */           Bandera = 0;
/*     */         } 
/*  45 */         dDenominador /= 1000.0D;
/*     */       } 
/*  47 */       for (i = 4; i >= 0; i--) {
/*  48 */         if (Cifras[i] > 0.0D) {
/*  49 */           this.sCadenaSalida += LetrasCientos(Cifras[i], i);
/*     */         }
/*     */       } 
/*  52 */       if (Bandera == 1) {
/*  53 */         this.sCadenaSalida += "DE PESOS. M/CTE";
/*     */       } else {
/*     */         
/*  56 */         this.sCadenaSalida += "PESOS. M/CTE";
/*     */       }
/*     */     
/*  59 */     } catch (Exception e) {}
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
/*     */   private String LetrasCentenas(int cientos, boolean bControl) {
/*  72 */     String sCadena = "";
/*     */     
/*  74 */     switch (cientos) {
/*     */ 
/*     */ 
/*     */       
/*     */       case 1:
/*  79 */         sCadena = bControl ? "CIEN " : "CIENTO ";
/*     */         break;
/*     */       case 2:
/*  82 */         sCadena = "DOSCIENTOS ";
/*     */         break;
/*     */       case 3:
/*  85 */         sCadena = "TRESCIENTOS ";
/*     */         break;
/*     */       case 4:
/*  88 */         sCadena = "CUATROCIENTOS ";
/*     */         break;
/*     */       case 5:
/*  91 */         sCadena = "QUINIENTOS ";
/*     */         break;
/*     */       case 6:
/*  94 */         sCadena = "SEISCIENTOS ";
/*     */         break;
/*     */       case 7:
/*  97 */         sCadena = "SETECIENTOS ";
/*     */         break;
/*     */       case 8:
/* 100 */         sCadena = "OCHOCIENTOS ";
/*     */         break;
/*     */       case 9:
/* 103 */         sCadena = "NOVECIENTOS ";
/*     */         break;
/*     */     } 
/* 106 */     return sCadena;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String LetrasDigito(int unidades) {
/* 115 */     String sCadena = "";
/*     */     
/* 117 */     switch (unidades) {
/*     */ 
/*     */ 
/*     */       
/*     */       case 1:
/* 122 */         sCadena = "UN ";
/*     */         break;
/*     */       case 2:
/* 125 */         sCadena = "DOS ";
/*     */         break;
/*     */       case 3:
/* 128 */         sCadena = "TRES ";
/*     */         break;
/*     */       case 4:
/* 131 */         sCadena = "CUATRO ";
/*     */         break;
/*     */       case 5:
/* 134 */         sCadena = "CINCO ";
/*     */         break;
/*     */       case 6:
/* 137 */         sCadena = "SEIS ";
/*     */         break;
/*     */       case 7:
/* 140 */         sCadena = "SIETE ";
/*     */         break;
/*     */       case 8:
/* 143 */         sCadena = "OCHO ";
/*     */         break;
/*     */       case 9:
/* 146 */         sCadena = "NUEVE ";
/*     */         break;
/*     */     } 
/* 149 */     return sCadena;
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
/*     */   private String LetrasCientos(double dValor, int miles) {
/* 161 */     String sCadena = "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 167 */     int Centenas = (int)dValor / 100;
/* 168 */     int Decenas = (int)(dValor % 100.0D) / 10;
/* 169 */     int unidades = (int)dValor % 10;
/*     */     
/* 171 */     if (Centenas == 1 && Decenas == 0 && unidades == 0) {
/* 172 */       sCadena = LetrasCentenas(Centenas, true);
/*     */     
/*     */     }
/* 175 */     else if (Centenas > 0) {
/* 176 */       sCadena = LetrasCentenas(Centenas, false);
/*     */     } 
/*     */ 
/*     */     
/* 180 */     if (Decenas == 1) {
/* 181 */       switch (unidades) {
/*     */         case 0:
/* 183 */           sCadena = sCadena + "DIEZ ";
/*     */           break;
/*     */         case 1:
/* 186 */           sCadena = sCadena + "ONCE ";
/*     */           break;
/*     */         case 2:
/* 189 */           sCadena = sCadena + "DOCE ";
/*     */           break;
/*     */         case 3:
/* 192 */           sCadena = sCadena + "TRECE ";
/*     */           break;
/*     */         case 4:
/* 195 */           sCadena = sCadena + "CATORCE ";
/*     */           break;
/*     */         case 5:
/* 198 */           sCadena = sCadena + "QUINCE ";
/*     */           break;
/*     */         case 6:
/* 201 */           sCadena = sCadena + "DIECISEIS ";
/*     */           break;
/*     */         case 7:
/* 204 */           sCadena = sCadena + "DIECISIETE ";
/*     */           break;
/*     */         case 8:
/* 207 */           sCadena = sCadena + "DIECIOCHO ";
/*     */           break;
/*     */         case 9:
/* 210 */           sCadena = sCadena + "DIECINUEVE ";
/*     */           break;
/*     */       } 
/*     */     
/*     */     } else {
/* 215 */       switch (Decenas) {
/*     */ 
/*     */ 
/*     */         
/*     */         case 2:
/* 220 */           sCadena = sCadena + ((unidades == 0) ? "VEINTE " : "VEINTI ");
/*     */           break;
/*     */         case 3:
/* 223 */           sCadena = sCadena + "TREINTA ";
/*     */           break;
/*     */         case 4:
/* 226 */           sCadena = sCadena + "CUARENTA ";
/*     */           break;
/*     */         case 5:
/* 229 */           sCadena = sCadena + "CINCUENTA ";
/*     */           break;
/*     */         case 6:
/* 232 */           sCadena = sCadena + "SESENTA ";
/*     */           break;
/*     */         case 7:
/* 235 */           sCadena = sCadena + "SETENTA ";
/*     */           break;
/*     */         case 8:
/* 238 */           sCadena = sCadena + "OCHENTA ";
/*     */           break;
/*     */         case 9:
/* 241 */           sCadena = sCadena + "NOVENTA ";
/*     */           break;
/*     */       } 
/* 244 */       if (unidades > 0) {
/* 245 */         if (Decenas > 0 && Decenas != 2)
/* 246 */           sCadena = sCadena + "Y "; 
/* 247 */         sCadena = sCadena + LetrasDigito(unidades);
/*     */       } 
/*     */     } 
/*     */     
/* 251 */     switch (miles) {
/*     */       case 4:
/* 253 */         sCadena = sCadena + ((dValor == 1.0D) ? "BILLON " : "BILLONES ");
/*     */         break;
/*     */       case 3:
/* 256 */         sCadena = sCadena + "MIL ";
/*     */         break;
/*     */       case 2:
/* 259 */         sCadena = sCadena + ((dValor == 1.0D) ? "MILLON " : "MILLONES ");
/*     */         break;
/*     */       case 1:
/* 262 */         sCadena = sCadena + "MIL ";
/*     */         break;
/*     */     } 
/* 265 */     return sCadena;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 274 */   public String toString() { return this.sCadenaSalida; }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\LetrasDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */