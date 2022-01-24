package Lab3;

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
     *
     * @param username Nombre del usuario a crear
     * @param password Contraseña del usuario a crear
     */
    Usuario(String username, String password){
        this.username = username;
        this.password = password;
        this.registerdate = LocalDate.now();
        this.userID = ContadorID++;
    }

    // Getters

    /**
     *
     * @return Nombre de usuario
     */
    public String getusername(){
        return this.username;
    }

    /**
     *
     * @return Contraseña del usuario
     */
    public String getpassword(){
        return this.password;
    }

    /**
     *
     * @return Fecha de registro del usuario
     */
    public LocalDate getuserdate(){
        return this.registerdate;
    }

    /**
     *
     * @return Id del usuario
     */
    public int getId(){
        return this.userID;
    }

    // Otros métodos

    /**
     *
     * @return Toda la información de un usuario contenida en forma de String
     */
    public String ToString(){
            DateTimeFormatter formatofecha = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            return "Nombre de usuario: " + this.username + "\n" +
                    "Contrasenha: " + this.password + "\n" + "Fecha de registro: "
                    + this.registerdate.format(formatofecha);
    }
}
