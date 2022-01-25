package Documento;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Clase en la cual se define el objeto documento, el cual contiene
 * distintas versiones y permisos, junto con su contenido, titulo y fecha de creación correspondiente,
 * junto con un ID autoincremental
 * @author Francisco Salfate Garcés
 */

public class Documento {
    // Atributos
    private int DocId;
    private String titulo;
    private LocalDate fechacreacion;
    private String contenido = "";
    private String ownerdocumento;
    private static int ContadorIDdoc = 0;
    private ArrayList<Version> listaversiones = new ArrayList<>();
    private ArrayList<Acceso> listacceso = new ArrayList<>();

    // Constructores

    /**
     * Constructor de documento
     *
     * @param titulo    Titulo del documento
     * @param contenido Contenido del documento
     */
    public Documento(String titulo, String contenido) {
        this.titulo = titulo;
        this.fechacreacion = LocalDate.now();
        this.DocId = ContadorIDdoc++;
        this.contenido = contenido;
        this.listaversiones.add(new Version(contenido, 0));
    }

    /**
     * Segundo constructor (Sobrecarga)
     *
     * @param titulo    Titulo del documento
     * @param contenido Contenido del documento
     * @param ownerdoc  Dueño del documeto
     */
    public Documento(String titulo, String contenido, String ownerdoc) {
        this.titulo = titulo;
        this.fechacreacion = LocalDate.now();
        this.DocId = ContadorIDdoc++;
        this.contenido = contenido;
        this.ownerdocumento = ownerdoc;
        this.listaversiones.add(new Version(contenido, 0));
    }

    // Getters

    /**
     * Método que obtiene el contenido de un documento
     *
     * @return Contenido del documento
     */
    public String getcontenido() {
        return this.contenido;
    }


    /**
     * Método que obtiene el propietario de un documento
     *
     * @return Propietario del documento
     */
    public String getOwnerdocumento() {
        return this.ownerdocumento;
    }

    /**
     * Método que obtiene la lista de versiones de un documento
     *
     * @return Lista de versiones de un documento
     */
    public ArrayList<Version> getlistaversiones() {
        return this.listaversiones;
    }

    /**
     * Método que obtiene la lista de permisos de un documento
     *
     * @return Lista de permisos (o accesos) de un documento
     */
    public ArrayList getlistaaccessos() {
        return this.listacceso;
    }

    /**
     * Método que obtiene todos los nombres de usuario que correspondan a los permisos otorgados
     *
     * @return Lista de los nombres de usuarios
     */
    public ArrayList getusernamesaccesos() {
        ArrayList<String> listausernames = new ArrayList<>();
        for (int i = 0; i < listacceso.size(); i++) {
            listausernames.add(listacceso.get(i).getusernameaccess());
        }
        return listausernames;
    }

    // Setters

    /**
     * Método que setea un nuevo contenido en el documento
     *
     * @param contenido El nuevo contenido del documento
     */
    public void setcontenido(String contenido) {
        this.contenido = contenido;
    }

    /**
     * Método que setea un propietario de un documento
     *
     * @param username El nuevo propietario del documento
     */
    public void setowner(String username) {
        this.ownerdocumento = username;
    }

    /**
     * Método que setea una nueva lista de accesos
     *
     * @param listacceso Lista de accesos modificada
     */
    public void setListacceso(ArrayList<Acceso> listacceso) {
        this.listacceso = listacceso;
    }

    // Otros métodos

    /**
     * Método que verifica si un usuario posee un acceso en especifico
     *
     * @param usuario Usuario a verificar si posee un permiso
     * @param permiso Permiso a verificar que posea el usuario
     * @return Valor booleano dependiendo si se encuentra o no el usuario con el
     * permiso especificado
     */
    public boolean verificaracceso(String usuario, String permiso) {
        for (int i = 0; i < listacceso.size(); i++) {
            if (listacceso.get(i).getusernameaccess().equals(usuario) && listacceso.get(i).getaccesses().equals(permiso)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método que convierte toda la información de un documento en un String
     *
     * @return String que contiene toda la información de un documento, el cual es contenido en forma de String
     */

    public String ToString() {
        DateTimeFormatter formatofecha = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        ArrayList<String> accesosdoc = new ArrayList<>();

        for (int i = 0; i < this.listacceso.size(); i++) {
            accesosdoc.add(this.listacceso.get(i).ToString());
        }

        String accesosdocstring = String.join(" ||| ", accesosdoc);

        return "Titulo del documento: " + this.titulo + "\n" + "Propietario del documento: " + this.ownerdocumento + "\n"
                + "Contenido activo del documento: " + this.contenido + "\n" + "Fecha de creacion del documento: "
                + this.fechacreacion.format(formatofecha) + "\n" + "ID del documento: " + this.DocId + "\n"
                + "Permisos del documento: " + accesosdocstring + "\n"
                + "Numero de versiones: " + this.getlistaversiones().size() + "\n";
    }

    /**
     * Método que convierte toda la información de un documento en un String, pero funciona de forma que
     * presenta información distinta debido a la ausencia de un usuario
     * @return String que contiene toda la información de un documento, el cual es contenido en forma de String
     */
    public String ToStringLoggedOff(){
        DateTimeFormatter formatofecha = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        return "Titulo del documento: " + this.titulo + "\n" + "Propietario del documento: " + this.ownerdocumento + "\n"
                + "Contenido activo del documento: " + this.contenido + "\n" + "Fecha de creacion del documento: "
                + this.fechacreacion.format(formatofecha) + "\n" + "ID del documento: " + this.DocId + "\n"
                + "Numero de versiones: " + this.getlistaversiones().size() + "\n";
    }
}