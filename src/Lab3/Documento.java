package Lab3;
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
    //Atributos
    private int DocId;
    private String titulo;
    private LocalDate fechacreacion;
    private String contenido = "";
    private String ownerdocumento;
    private static int ContadorIDdoc = 0;
    private LocalDate fechamodificacion;
    private ArrayList<Version> listaversiones = new ArrayList<>();
    private ArrayList<Acceso> listacceso = new ArrayList<>();

    //Constructores

    /**
     *
     * @param titulo Titulo del documento
     * @param contenido Contenido del documento
     */
    public Documento(String titulo, String contenido){
        this.titulo = titulo;
        this.fechacreacion = LocalDate.now();
        this.DocId = ContadorIDdoc++;
        this.contenido = contenido;
        this.listaversiones.add(new Version(contenido, 0));
    }

    /**
     * Segundo constructor (Sobrecarga)
     * @param titulo Titulo del documento
     * @param contenido Contenido del documento
     * @param ownerdoc Dueño del documeto
     */
    public Documento(String titulo, String contenido, String ownerdoc){
        this.titulo = titulo;
        this.fechacreacion = LocalDate.now();
        this.DocId = ContadorIDdoc++;
        this.contenido = contenido;
        this.ownerdocumento = ownerdoc;
    }

    //Getters

    /**
     *
     * @return El título del documento
     */
    public String gettitulo(){
        return this.titulo;
    }

    /**
     *
     * @return La fecha de creación del documento
     */
    public LocalDate getfechacreacion(){
        return this.fechacreacion;
    }

    /**
     *
     * @return Contenido del documento
     */
    public String getcontenido(){
        return this.contenido;
    }

    /**
     *
     * @return ID del documento
     */
    public int getid(){
        return this.DocId;
    }

    /**
     *
     * @return Propietario del documento
     */
    public String getOwnerdocumento(){
        return this.ownerdocumento;
    }

    /**
     *
     * @return Lista de versiones de un documento
     */
    public ArrayList getlistaversiones(){
        return this.listaversiones;
    }

    /**
     *
     * @return Lista de permisos (o accesos) de un documento
     */
    public ArrayList getlistaaccessos(){
        return this.listacceso;
    }

    /**
     *
     * @return Lista de los nombres de usuarios
     */
    public ArrayList getusernamesaccesos(){
        ArrayList<String> listausernames = new ArrayList<>();
        for(int i = 0; i < listacceso.size() ; i++){
            listausernames.add(listacceso.get(i).getusernameaccess());
        }
        return listausernames;
    }

    //Setters

    /**
     *
     * @param contenido El nuevo contenido del documento
     */
    public void setcontenido(String contenido){
        this.contenido = contenido;
    }

    /**
     *
     * @param username El nuevo propietario del documento
     */
    public void setowner(String username){
        this.ownerdocumento = username;
    }

    /**
     *
     * @param listacceso Lista de accesos modificada
     */
    public void setListacceso(ArrayList<Acceso> listacceso) {
        this.listacceso = listacceso;
    }

    //Otros métodos

    /**
     *
     * @param usuario Usuario a verificar si posee un permiso
     * @param permiso Permiso a verificar que posea el usuario
     * @return Valor booleano dependiendo si se encuentra o no el usuario con el
     * permiso especificado
     */
    public boolean verificaracceso(String usuario, String permiso){
        for(int i = 0; i < listacceso.size() ; i++){
            if(listacceso.get(i).getusernameaccess().equals(usuario) && listacceso.get(i).getaccesses().equals(permiso)){
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @return String que contiene toda la información de un documento, el cual es contenido en forma de String
     */

    public String ToString(){
        DateTimeFormatter formatofecha = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        ArrayList<String> accesosdoc = new ArrayList<>();

        for(int i = 0; i < this.listacceso.size(); i++){
            accesosdoc.add(this.listacceso.get(i).ToString());
        }

        String accesosdocstring = String.join(" ||| ", accesosdoc);

        return "Titulo del documento: " + this.titulo + "\n" + "Propietario del documento: " + this.ownerdocumento + "\n"
                + "Contenido activo del documento: " + this.contenido + "\n" + "Fecha de creacion del documento: "
                + this.fechacreacion.format(formatofecha) + "\n" + "ID del documento: " + this.DocId + "\n"
                + "Permisos del documento: " + accesosdocstring + "\n"
                + "Numero de versiones: " + this.getlistaversiones().size() + "\n";
    }
}
