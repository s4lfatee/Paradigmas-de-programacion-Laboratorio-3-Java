package User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Clase que define el objeto usuario, el cual contiene
 * el nombre de usuario, contraseña y fecha de registro de un usuario
 * @author Francisco Salfate Garcés
 */
public class Usuario {
    // Atributos
    private int userID;
    private String username;
    private String password;
    private LocalDate registerdate;
    private static int ContadorID = 0;
    
    // Constructor

    /**
     * Constructor de usuario
     * @param username Nombre del usuario a crear
     * @param password Contraseña del usuario a crear
     */
    public Usuario(String username, String password){
        this.username = username;
        this.password = password;
        this.registerdate = LocalDate.now();
        this.userID = ContadorID++;
    }

    // Getters

    /**
     * Método que obtiene el nombre de usuario de un usuario
     * @return Nombre de usuario
     */
    public String getusername(){
        return this.username;
    }

    /**
     * Método que obtiene la contraseña de un usuario
     * @return Contraseña del usuario
     */
    public String getpassword(){
        return this.password;
    }


    // Otros métodos

    /**
     * Método que transforma a la clase Usuario en un String
     * @return Toda la información de un usuario contenida en forma de String
     */
    public String ToString(){
            DateTimeFormatter formatofecha = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            return "Nombre de usuario: " + this.username + "\n" +
                    "Fecha de registro: " + this.registerdate.format(formatofecha);
    }
}
