package Lab3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Clase en la cual se define el objeto versión, el cual
 * se genera a través de ciertos métodos del editor.
 * Una versión almacena un ID para ser identificado, y
 * guarda el nuevo contenido generado a través de una modificación
 * causada un método, y almacena la fecha en la que es creada la versión
 * @author Francisco Salfate Garcés
 */
public class Version {
    // Atributos
    private int idversion;
    private String contenido;
    private LocalDate fechaversion;

    // Constructor

    /**
     * Constructor de versión
     * @param contenido Contenido de la versión
     * @param idversion ID de la versión
     */
    public Version(String contenido, int idversion){
        this.idversion = idversion;
        this.contenido = contenido;
        this.fechaversion = LocalDate.now();
    }

    // Getters

    /**
     *
     * @return El contenido de la versión
     */
    public String getcontenido(){
        return this.contenido;
    }

    /**
     *
     * @return La fecha de generación de la versión
     */
    public LocalDate getfechaversion(){
        return this.fechaversion;
    }

    /**
     *
     * @return El ID de la versión
     */
    public int getidversion(){
        return this.idversion;
    }

    // Otros métodos

    /**
     *
     * @return Todos los datos de una versión contenidos en un solo String
     */
    public String ToString(){
        DateTimeFormatter formatofecha = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return "Version numero: " + this.idversion + "\n" + "Fecha de version: " + this.fechaversion.format(formatofecha)
                + "\n" + "Contenido de la version: " + this.contenido + "\n";
    }
}