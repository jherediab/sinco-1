package sinco.business;

public class AcumuladosPersonaDTO {
    private int codigo_empleado;
    private int solicitudes_recibidas;
    private int solicitudes_atendidas;
    private int solicitudes_noatendidas;
    private int solicitudes_escaladas;
    private int oportunidad_excelente;
    private int oportunidad_buena;
    private int oportunidad_regular;
    private int confiabilidad_excelente;
    private int confiabilidad_buena;
    private int confiabilidad_regular;
    private float indice_oportunidad;
    private float indice_confiabilidad;
    private String nombres;
    private String apellidos;

    public AcumuladosPersonaDTO() {
    }

    public void setCodigoEmpleado(int p) {
        this.codigo_empleado = p;
    }

    public void setSolicitudesRecibidas(int p) {
        this.solicitudes_recibidas = p;
    }

    public void setSolicitudesAtendidas(int p) {
        this.solicitudes_atendidas = p;
    }

    public void setSolicitudesNoAtendidas(int p) {
        this.solicitudes_noatendidas = p;
    }

    public void setSolicitudesEscaladas(int p) {
        this.solicitudes_escaladas = p;
    }

    public void setOportunidadExcelente(int p) {
        this.oportunidad_excelente = p;
    }

    public void setOportunidadBuena(int p) {
        this.oportunidad_buena = p;
    }

    public void setOportunidadRegular(int p) {
        this.oportunidad_regular = p;
    }

    public void setconfiabilidad_excelente(int p) {
        this.confiabilidad_excelente = p;
    }

    public void setConfiabilidadBuena(int p) {
        this.confiabilidad_buena = p;
    }

    public void setConfiabilidadRegular(int p) {
        this.confiabilidad_regular = p;
    }

    public void setIndiceOportunidad(float p) {
        this.indice_oportunidad = p;
    }

    public void setIndiceConfiabilidad(float p) {
        this.indice_confiabilidad = p;
    }

    public void setNombres(String p) {
        this.nombres = p;
    }

    public void setApellidos(String p) {
        this.apellidos = p;
    }

    public int getCodigoEmpleado() {
        return this.codigo_empleado;
    }

    public int getSolicitudesRecibidas() {
        return this.solicitudes_recibidas;
    }

    public int getSolicitudesAtendidas() {
        return this.solicitudes_atendidas;
    }

    public int getSolicitudesNoAtendidas() {
        return this.solicitudes_noatendidas;
    }

    public int getSolicitudesEscaladas() {
        return this.solicitudes_escaladas;
    }

    public int getOportunidadExcelente() {
        return this.oportunidad_excelente;
    }

    public int getOportunidadBuena() {
        return this.oportunidad_buena;
    }

    public int getOportunidadRegular() {
        return this.oportunidad_regular;
    }

    public int getconfiabilidad_excelente() {
        return this.oportunidad_regular;
    }

    public int getConfiabilidadBuena() {
        return this.confiabilidad_buena;
    }

    public int getConfiabilidadRegular() {
        return this.confiabilidad_regular;
    }

    public float getIndiceOportunidad() {
        int total = this.oportunidad_excelente + this.oportunidad_buena + this.oportunidad_regular;
        this.indice_oportunidad = 0.0F;
        if (total != 0) {
            this.indice_oportunidad = ((float)this.oportunidad_excelente * ParametrosDTO.getFloat("porcentajeE") + (float)this.oportunidad_buena * ParametrosDTO.getFloat("porcentajeB") + (float)this.oportunidad_regular * ParametrosDTO.getFloat("porcentajeR")) / (float)total;
        }

        return this.indice_oportunidad;
    }

    public float getIndiceConfiabilidad() {
        int total = this.confiabilidad_excelente + this.confiabilidad_buena + this.confiabilidad_regular;
        this.indice_confiabilidad = 0.0F;
        if (total != 0) {
            this.indice_confiabilidad = ((float)this.confiabilidad_excelente * ParametrosDTO.getFloat("porcentajeE") + (float)this.confiabilidad_buena * ParametrosDTO.getFloat("porcentajeB") + (float)this.confiabilidad_regular * ParametrosDTO.getFloat("porcentajeR")) / (float)total;
        }

        return this.indice_confiabilidad;
    }

    public float getIndiceTotal() {
        int total = this.oportunidad_excelente + this.oportunidad_buena + this.oportunidad_regular;
        this.indice_oportunidad = 0.0F;
        if (total != 0) {
            this.indice_oportunidad = ((float)this.oportunidad_excelente * ParametrosDTO.getFloat("porcentajeE") + (float)this.oportunidad_buena * ParametrosDTO.getFloat("porcentajeB") + (float)this.oportunidad_regular * ParametrosDTO.getFloat("porcentajeR")) / (float)total;
        }

        total = this.confiabilidad_excelente + this.confiabilidad_buena + this.confiabilidad_regular;
        this.indice_confiabilidad = 0.0F;
        if (total != 0) {
            this.indice_confiabilidad = ((float)this.confiabilidad_excelente * ParametrosDTO.getFloat("porcentajeE") + (float)this.confiabilidad_buena * ParametrosDTO.getFloat("porcentajeB") + (float)this.confiabilidad_regular * ParametrosDTO.getFloat("porcentajeR")) / (float)total;
        }

        return this.indice_oportunidad != 0.0F && this.indice_confiabilidad != 0.0F ? this.indice_oportunidad * (float)ParametrosDTO.getInt("porcentaje.oportunidad") + this.indice_confiabilidad * (float)ParametrosDTO.getInt("porcentaje.confiabilidad") : (this.indice_oportunidad + this.indice_confiabilidad) * 100.0F;
    }

    public String getNombres() {
        return this.nombres;
    }

    public String getApellidos() {
        return this.apellidos;
    }

    public String getNombre() {
        return this.apellidos + ' ' + this.nombres;
    }
}
