public class Competicion {
    int id;
    String Competicion;
    String tipo_evento,categoria,premio,sede,fecha_inicio,fecha_fin;
    public Competicion() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompeticion() {
        return Competicion;
    }

    public void setCompeticion(String competicion) {
        Competicion = competicion;
    }

    public String getTipo_evento() {
        return tipo_evento;
    }

    public void setTipo_evento(String tipo_evento) {
        this.tipo_evento = tipo_evento;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getPremio() {
        return premio;
    }

    public void setPremio(String premio) {
        this.premio = premio;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }
}
