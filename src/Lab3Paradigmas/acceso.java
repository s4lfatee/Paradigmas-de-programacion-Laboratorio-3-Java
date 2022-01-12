package Lab3Paradigmas;

public class acceso {
    private String username;
    private String acceso;
    
    public acceso(String username){
        this.username = username;
    }
    
    public String getusername(){
        return this.username;
    }
    
    public String getaccesses(){
        return this.acceso;
    }
 
    public String ToString(){
        return "Nombre de usuario: " + this.username + " " + "Tipo de acceso: " 
                + this.acceso + "\n";
    }
}
