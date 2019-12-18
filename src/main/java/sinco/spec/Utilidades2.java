//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package sinco.spec;

import java.util.GregorianCalendar;
import java.util.StringTokenizer;
import sinco.business.FechaDTO;
import sinco.business.ParametrosDTO;
import sinco.business.UnidadMedidaDTO;
import sinco.business.Utilidades;
import sinco.data.DiaFestivoDAO;
import sinco.data.LibreDAO;
import sinco.data.UnidadMedidaDAO;

public class Utilidades2 {
    public Utilidades2() {
    }

    public static int diferenciaDias(String vieja, String nueva) {
        nueva = Utilidades.darFormatoFecha(nueva);
        vieja = Utilidades.darFormatoFecha(vieja);
        if (vieja.compareTo(nueva) > 0) {
            String temp = vieja;
            vieja = nueva;
            nueva = temp;
        }

        GregorianCalendar viejo = Utilidades.StringtoGC(vieja);
        GregorianCalendar nuevo = Utilidades.StringtoGC(nueva);

        int i;
        for(i = 0; !viejo.equals(nuevo) && i < 10000; ++i) {
            siguienteDia(viejo, false);
        }

        return i;
    }

    public static String diaSiguiente() {
        GregorianCalendar gc = new GregorianCalendar();
        siguienteDia(gc, false);
        return Utilidades.GCtoString(gc);
    }

    public static String manana() {
        GregorianCalendar gc = new GregorianCalendar();
        siguienteDia(gc, true);
        String fecha = Utilidades.GCtoString(gc);
        FechaDTO miFecha = new FechaDTO(fecha);
        miFecha.setHora(8, 0, 0);
        return miFecha.getFechaHora();
    }

    public static String fechaMasDias(String fecha, int numerodedias) {
        if (numerodedias <= 0) {
            return fecha;
        } else {
            GregorianCalendar gc = Utilidades.StringtoGC(fecha);

            for(int i = 0; i < numerodedias; ++i) {
                siguienteDia(gc, true);
            }

            if (noHabil(gc)) {
                siguienteDia(gc, true);
            }

            return Utilidades.GCtoString(gc);
        }
    }

    public static long diferenciaEnDias(String vieja, String nueva) {
        nueva = Utilidades.darFormatoFecha(nueva);
        vieja = Utilidades.darFormatoFecha(vieja);
        boolean cambio = false;
        if (vieja.compareTo(nueva) > 0) {
            String temp = vieja;
            vieja = nueva;
            nueva = temp;
            cambio = true;
        }

        DiaFestivoDAO dff = new DiaFestivoDAO();
        int iDiasFestivos = dff.diasFestivosEntre(vieja, nueva);
        dff.close();
        FechaDTO miFecham = new FechaDTO(vieja);
        FechaDTO miFechaM = new FechaDTO(nueva);
        long diasServicio = miFechaM.getJuliano() - miFecham.getJuliano();
        long retorno = diasServicio - (long)iDiasFestivos;
        if (cambio) {
            retorno = -retorno;
        }

        return retorno;
    }

    public static int diasDelServicio(int cuantos, String unidad) {
        int factor = 0;

        try {
            UnidadMedidaDAO umf = new UnidadMedidaDAO();
            UnidadMedidaDTO um = umf.getUnidadMedida(unidad);
            factor = um.getFactor();
            umf.close();
        } catch (Exception var5) {
        }

        return cuantos * factor;
    }

    public static String nuevaFechaEscalamientos(String fecha, int cuantos, String unidad) {
        GregorianCalendar gc = Utilidades.StringtoGC(fecha);
        UnidadMedidaDAO umf = new UnidadMedidaDAO();
        UnidadMedidaDTO um = umf.getUnidadMedida(unidad);
        int factor = um.getFactor();
        umf.close();
        int numerodedias = cuantos * factor;
        boolean saltarnohabiles = unidad.equals("DI");

        for(int i = 0; i < numerodedias; ++i) {
            diaAnterior(gc, saltarnohabiles);
        }

        if (noHabil(gc)) {
            diaAnterior(gc, true);
        }

        return Utilidades.GCtoString(gc);
    }

    public static boolean noHabil(GregorianCalendar gc) {
        DiaFestivoDAO dff = new DiaFestivoDAO();
        if (dff.getDiaFestivo(Utilidades.GCtoString(gc)) != null) {
            dff.close();
            return true;
        } else {
            dff.close();
            return false;
        }
    }

    private static void diaAnterior(GregorianCalendar gc, boolean saltar) {
        gc.add(5, -1);

        while(saltar && noHabil(gc)) {
            gc.add(5, -1);
        }

    }

    private static void siguienteDia(GregorianCalendar gc, boolean saltar) {
        gc.add(5, 1);

        while(saltar && noHabil(gc)) {
            gc.add(5, 1);
        }

    }

    public static String fechaVigencia(String medida) {
        GregorianCalendar gc = new GregorianCalendar();
        int hora = gc.get(11) - ParametrosDTO.getInt("horasdif");
        if (medida.equals("HO")) {
            return hora >= ParametrosDTO.getInt("hora.inicio.vigencia.solicitudes.horas") ? manana() : Utilidades.ahora();
        } else if (hora >= ParametrosDTO.getInt("hora.inicio.vigencia.siguiente.dia")) {
            return manana();
        } else {
            return noHabil(gc) ? manana() : Utilidades.ahora();
        }
    }

    public static int validarFecha(String fecha) {
        StringTokenizer st = new StringTokenizer(fecha, "/");

        try {
            String dia = st.nextToken();
            String mes = st.nextToken();
            String ano = st.nextToken();
            String total = ano + "-" + mes + "-" + dia;
            GregorianCalendar gc = Utilidades.StringtoGC(total);
            if (noHabil(gc)) {
                return 1;
            } else {
                GregorianCalendar gcHoy = new GregorianCalendar();
                return compararFechas(gc, gcHoy) <= 0 ? 1 : 0;
            }
        } catch (Exception var8) {
            return -1;
        }
    }

    public static int compararFechas(String fecha1, String fecha2) {
        GregorianCalendar gc1 = Utilidades.StringtoGC(fecha1);
        GregorianCalendar gc2 = Utilidades.StringtoGC(fecha2);
        return compararFechas(gc1, gc2);
    }

    public static int compararFechas(GregorianCalendar gc1, GregorianCalendar gc2) {
        int dias1 = gc1.get(5);
        int meses1 = gc1.get(2) + 1;
        int anos1 = gc1.get(1);
        int dias2 = gc2.get(5);
        int meses2 = gc2.get(2) + 1;
        int anos2 = gc2.get(1);
        if (anos1 < anos2) {
            return -1;
        } else if (anos1 > anos2) {
            return 1;
        } else if (meses1 < meses2) {
            return -1;
        } else if (meses1 > meses2) {
            return 1;
        } else if (dias1 < dias2) {
            return -1;
        } else {
            return dias1 > dias2 ? 1 : 0;
        }
    }

    public static boolean esNoHabil(String fecha) {
        DiaFestivoDAO dff = new DiaFestivoDAO();
        if (dff.getDiaFestivo(fecha) != null) {
            dff.close();
            return true;
        } else {
            dff.close();
            return false;
        }
    }

    public static String fechaTerminacion(String fecha, int duracion, String unidad) {
        if (unidad.equals("HO")) {
            return Utilidades.fechaMasHoras(fecha, duracion);
        } else {
            UnidadMedidaDAO umf = new UnidadMedidaDAO();
            UnidadMedidaDTO um = umf.getUnidadMedida(unidad);
            int factor = um.getFactor();
            umf.close();
            int numerodedias = duracion * factor;
            FechaDTO miFecha = new FechaDTO(fecha);
            int i = 0;

            DiaFestivoDAO rsd;
            for(rsd = new DiaFestivoDAO(); i < numerodedias - 1; miFecha.fechaMasDias(1L)) {
                if (!rsd.esDiaFestivo(miFecha.getFecha())) {
                    ++i;
                }
            }

            while(rsd.esDiaFestivo(miFecha.getFecha())) {
                miFecha.fechaMasDias(1L);
            }

            rsd.close();
            miFecha.setHora(18, 0, 0);
            return miFecha.getFechaHora();
        }
    }

    public static int festivosEntre(String fechaInicial, String fechaFinal) {
        LibreDAO rsaux = new LibreDAO();
        String s = " SELECT COUNT(0) FROM DIAS_FESTIVOS  WHERE fecha >=" + Utilidades.formatoFecha2(fechaInicial);
        if (ParametrosDTO.getInt("tipoBaseDatos") == 1) {
            s = s + " AND  fecha <" + Utilidades.formatoFecha2(fechaFinal) + "::date+1";
        } else {
            s = s + " AND  fecha <" + Utilidades.formatoFecha2(fechaFinal) + "+1";
        }

        int iDiasFestivos = rsaux.getValorInt(s);
        rsaux.close();
        return iDiasFestivos;
    }
}
