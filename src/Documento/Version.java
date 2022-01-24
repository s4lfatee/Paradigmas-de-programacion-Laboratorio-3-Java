package Documento;

import java.time.LocalDate;

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
     * Método que obtiene el contenido de una versión
     * @return El contenido de la versión
     */
    public String getcontenido(){
        return this.contenido;
    }
}