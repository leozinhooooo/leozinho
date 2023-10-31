public class Alumno {

  private int identificacion;
  private String nombre;
  private String apellido;
  private String sexo; 
  private String especializacion;
  private int puntosExamenMate;

  public int getIdentificacion() {
    return identificacion;
  }

  public void setIdentificacion(int identificacion) {
    this.identificacion = identificacion;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public String getSexo() {
    return sexo;
  }

  public void setSexo(String sexo) {
    this.sexo = sexo;
  }

  public String getEspecializacion() {
    return especializacion;
  }

  public void setEspecializacion(String especializacion) {
    this.especializacion = especializacion;
  }

  public int getPuntosExamenMate() {
    return puntosExamenMate;
  }

  public void setPuntosExamenMate(int puntosExamenMate) {
    this.puntosExamenMate = puntosExamenMate;
  }

  @Override
  public String toString() {
    return "Alumno [identificacion=" + identificacion + ", nombre=" + nombre + ", apellido=" + apellido + ", sexo=" + sexo + ", especializacion=" + especializacion + ", puntosExamenMate=" + puntosExamenMate + "]";
  }

}