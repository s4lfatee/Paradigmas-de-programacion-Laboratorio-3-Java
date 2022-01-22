package Lab3;

public class acceso {
    private String usernameaccess;
    private String permiso;
    
    public acceso(String username, String permiso){
        this.usernameaccess = username;
        this.permiso = permiso;
    }
    
    public String getusernameaccess(){
        return this.usernameaccess;
    }
    
    public String getaccesses(){
        return this.permiso;
    }

    public void setPermiso(String permiso){
        this.permiso = permiso;
    }

    public String ToString(){
        return "El usuario " + this.usernameaccess + "posee el permiso " + this.permiso;
    }
}
