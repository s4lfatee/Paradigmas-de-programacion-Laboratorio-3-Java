package Lab3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class usuario {
    private int userID;
    private String username;
    private String password;
    private LocalDate registerdate;
    private static int ContadorID = 0;
    
    
    usuario(String username, String password){
        this.username = username;
        this.password = password;
        this.registerdate = LocalDate.now();
        this.userID = ContadorID++;
    }
    
    public String getusername(){
        return this.username;
    }
    
    public String getpassword(){
        return this.password;
    }
    
    public LocalDate getuserdate(){
        return this.registerdate;
    }


    public int getId(){
        return this.userID;
    }

    public String ToString(){
            DateTimeFormatter formatofecha = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            return "Nombre de usuario: " + this.username + "\n" +
                    "Contrasenha: " + this.password + "\n" + "Fecha de registro: "
                    + this.registerdate.format(formatofecha);
    }
}
