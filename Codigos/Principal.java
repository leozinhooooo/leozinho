import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Principal {

  private static List<Alumno> listaAlumnos;

  public static void main(String[] args) throws IOException {

    cargarArchivoCSV();

    contarAlumnosPorEspecializacion();

    contarMujeresPorEspecializacion();

    contarHombresPorEspecializacion();

    alumnoPuntajeAltoPorEspecializacion();

    alumnoPuntajeAltoGeneral();

    promedioPuntosPorEspecializacion();

  }

  private static void cargarArchivoCSV() throws IOException {

    Pattern patron = Pattern.compile(";");

    try (Stream<String> lineas = Files.lines(Path.of("puntos_alumnos.csv"))) {

      listaAlumnos = lineas
        .skip(1)
        .map(linea -> {

          String[] partes = patron.split(linea);

          Alumno alumno = new Alumno();
          alumno.setIdentificacion(Integer.parseInt(partes[0]));
          alumno.setNombre(partes[1]);
          alumno.setApellido(partes[2]);
          alumno.setSexo(partes[3]);
          alumno.setEspecializacion(partes[4]);

          try {
            alumno.setPuntosExamenMate(Integer.parseInt(partes[5].trim()));
          } catch (NumberFormatException e) {
            System.out.println("Error convirtiendo puntos a int para alumno: " + linea);
            alumno.setPuntosExamenMate(0);
          }

          return alumno;

        })
        .collect(Collectors.toList());

    } catch (IOException e) {
      System.out.println("Error leyendo archivo CSV: " + e.getMessage());
    }

  }

  private static void contarAlumnosPorEspecializacion() {

    Map<String, Long> conteoPorEspecializacion = listaAlumnos.stream()
      .collect(Collectors.groupingBy(Alumno::getEspecializacion, Collectors.counting()));

    System.out.println("Alumnos por especializacion:");
    conteoPorEspecializacion.forEach((especializacion, conteo) -> {
      System.out.println(especializacion + ": " + conteo);
    });

  }

  private static void contarMujeresPorEspecializacion() {

    Map<String, Long> contadorMujeresPorEspecializacion = listaAlumnos.stream()
      .filter(a -> a.getSexo().equals("F"))
      .collect(Collectors.groupingBy(Alumno::getEspecializacion, Collectors.counting()));

    System.out.println("\nMujeres por especializacion:");
    contadorMujeresPorEspecializacion.forEach((especializacion, contador) -> {
      System.out.println(especializacion + ": " + contador);
    });

  }

  private static void contarHombresPorEspecializacion() {

    Map<String, Long> contadorHombresPorEspecializacion = listaAlumnos.stream()
      .filter(a -> a.getSexo().equals("M"))
      .collect(Collectors.groupingBy(Alumno::getEspecializacion, Collectors.counting()));

    System.out.println("\nHombres por especializacion:");
    contadorHombresPorEspecializacion.forEach((especializacion, contador) -> {
      System.out.println(especializacion + ": " + contador);
    });

  }

  private static void alumnoPuntajeAltoPorEspecializacion() {

    Map<String, Optional<Alumno>> mayorPuntajePorEspecializacion = listaAlumnos.stream()
      .collect(Collectors.groupingBy(Alumno::getEspecializacion,
        Collectors.maxBy(Comparator.comparingInt(Alumno::getPuntosExamenMate))));

    System.out.println("\nAlumno con mayor puntaje por especializacion:");
    mayorPuntajePorEspecializacion.forEach((especializacion, alumno) -> {
      System.out.println(especializacion + ": " + alumno.get().getNombre() + " " + alumno.get().getApellido());
    });

  }

  private static void alumnoPuntajeAltoGeneral() {

    Alumno mejorAlumno = listaAlumnos.stream()
      .max(Comparator.comparingInt(Alumno::getPuntosExamenMate))
      .get();

    System.out.println("\nAlumno con mayor puntaje en general:");
    System.out.println(mejorAlumno.getNombre() + " " + mejorAlumno.getApellido());

  }

  private static void promedioPuntosPorEspecializacion() {

    Map<String, Double> promedioPorEspecializacion = listaAlumnos.stream()
      .collect(Collectors.groupingBy(Alumno::getEspecializacion, 
        Collectors.averagingInt(Alumno::getPuntosExamenMate)));

    System.out.println("\nPromedio de puntos por especializacion:");
    promedioPorEspecializacion.forEach((especializacion, promedio) -> {
      System.out.println(especializacion + ": " + promedio);
    });

  }
  
}