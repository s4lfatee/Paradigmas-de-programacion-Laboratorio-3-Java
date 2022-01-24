package Documento;

/**
 * Clase en la cual se define el objeto acceso,
 * la cual especifica que tipo de permiso tiene un usuario en particular
 * @author Francisco Salfate Garcés
 */
public class Acceso {
    // Atributos
    private String usernameaccess;
    private String permiso;


    // Constructor
    /**
     * Constructor de acceso
     * @param username Nombre de usuario
     * @param permiso Permiso concedido al usuario
     */
    public Acceso(String username, String permiso){
        this.usernameaccess = username;
        this.permiso = permiso;
    }

    // Getters

    /**
     * Método que obtiene el nombre de un usuario correspondiente a un acceso
     * @return Nombre de usuario que posee un permiso
     */
    public String getusernameaccess(){
        return this.usernameaccess;
    }

    /**
     * Método que obtiene el permiso de un usuario correspondiente a un acceso
     * @return Permiso que posee un usuario
     */
    public String getaccesses(){
        return this.permiso;
    }

    // Setters

    /**
     * Método que setea un nuevo permiso a un acceso
     * @param permiso Nuevo permiso de un usuario
     */
    public void setPermiso(String permiso){
        this.permiso = permiso;
    }

    // Otros métodos

    /**
     * Método que expresa toda la información de un acceso en un String
     * @return Toda la información contenida en un acceso en forma de un String
     */
    public String ToString(){
        return "El usuario " + this.usernameaccess + " posee el permiso " + this.permiso;
    }
}
