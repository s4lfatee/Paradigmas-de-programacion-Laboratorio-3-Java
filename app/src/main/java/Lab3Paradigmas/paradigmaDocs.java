package Lab3Paradigmas;
import documento.*;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class paradigmaDocs {
    private String nombreplataforma;
    private LocalDate fechaplataforma;
    private ArrayList<usuario> listausers = new ArrayList<>();
    private ArrayList<documento> listadocumentos = new ArrayList<>();
    private String usuariologueado;
    
    public paradigmaDocs(String nombreplataforma){
        this.nombreplataforma = nombreplataforma;
        this.fechaplataforma = LocalDate.now();
    }
    
    public String getnombreplataforma(){
        return this.nombreplataforma;
    }
    
    public LocalDate getfechaplataforma(){
        return this.fechaplataforma;
    }
    
    public ArrayList getlistausers(){
        return this.listausers;
    }
    
    public ArrayList getlistadocumentos(){
        return this.listadocumentos;
    }
    
    public String getusuariologueado(){
        return this.usuariologueado;
    }
    
    public void setusuariologueado(String username){
        this.usuariologueado = username;
    }
    
    public boolean registrarusuario(String username, String password){
        if (this.listausers.isEmpty()){
            this.listausers.add(new usuario(username, password));
            return true;
        }
        else{
            for (int i = 0; i < this.listausers.size(); i++){
                if (this.listausers.get(i).getusername().equals(username))
                    return false;
            }
            this.listausers.add(new usuario(username, password));
            return true;
        }
    }
    
    public boolean autenticarusuario(String username, String password){
        for (int i = 0; i < this.listausers.size(); i++){
            if (this.listausers.get(i).getusername().equals(username) && 
                    this.listausers.get(i).getpassword().equals(password)){
                this.setusuariologueado(username);
                return true;
            }
        }
        return false;
    }
    
    public String ToString(){
        DateTimeFormatter formatofecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "Nombre de la plataforma: " + this.nombreplataforma + "\n" 
                + formatofecha.format(this.fechaplataforma);
    }
}
