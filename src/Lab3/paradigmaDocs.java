package Lab3;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class paradigmaDocs {
    private String nombreplataforma;
    private LocalDate fechaplataforma;
    private ArrayList<usuario> listausers = new ArrayList<>();
    private ArrayList<documento> listadocumentos = new ArrayList<>();
    private String usuariologueado;
    private ArrayList<String> permisosvalidos = new ArrayList<>(Arrays.asList("C", "R", "W"));


    public paradigmaDocs(String nombreplataforma){
        this.nombreplataforma = nombreplataforma;
        this.fechaplataforma = LocalDate.now();
        this.listausers.add(new usuario("salfate", "fco"));
        this.listausers.add(new usuario("lmao", "hola123"));
        this.listausers.add(new usuario("genericuser", "notapassword"));
        this.listausers.add(new usuario("useer", "4321"));
        this.listausers.add(new usuario("quintouser", "yelultimo"));
        this.listadocumentos.add(new documento("titulodoc","content", "salfate"));
        this.listadocumentos.add(new documento("otrodoc", "este es el contenido de otrodoc", "salfate"));
        this.listadocumentos.add(new documento("documentooo", "Lorem ipsum dolor sit amet", "salfate"));
        this.listadocumentos.add(new documento("ultimo doc de salfate", "definitivamente este es el ultimo", "salfate"));
        this.listadocumentos.add(new documento("este documento no pertenece a fco", "adios fco", "lmao"));
        this.listadocumentos.add(new documento("la verdad es que", "rellenar estos documentos no es muy divertido", "lmao"));
        this.listadocumentos.add(new documento("pero tengo que hacerlo", "porque es requerido", "lmao"));
        this.listadocumentos.add(new documento("kajkakaj", "contenido generico", "genericuser"));
        this.listadocumentos.add(new documento("falta poco", "El lenguaje de programación Java fue desarrollado originalmente por James Gosling", "useer"));
        this.listadocumentos.add(new documento("casi", "me gusta mucho jugar yugioh", "quintouser"));
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

    public ArrayList gepermisosvalidos(){
        return this.permisosvalidos;
    }
    
    public ArrayList getlistadocumentos(){
        return this.listadocumentos;
    }
    
    public String getusuariologueado(){
        return this.usuariologueado;
    }

    public ArrayList getusernames(){
        ArrayList<String> usernames = new ArrayList<>();

        for(int i = 0; i < this.listausers.size(); i++){
            usernames.add(this.listausers.get(i).getusername());
        }
        return usernames;
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

    public void create(String nombredoc, String contenido){
        documento nuevodocumento = new documento(nombredoc, contenido);
        nuevodocumento.setowner(this.usuariologueado);
        this.listadocumentos.add(nuevodocumento);
    }

    public boolean share(ArrayList<String> listadeusuarios, int IdDoc, String permiso){
        if(IdDoc > this.getlistadocumentos().size() - 1){
            System.out.print("No existe el documento especificado\n");
            return false;
        }

        documento Doc = this.listadocumentos.get(IdDoc);

        if(!(this.usuariologueado.equals(Doc.getOwnerdocumento()) || Doc.verificaracceso(this.usuariologueado, "W"))){
            System.out.print("No eres propietario del documento o no posees los permisos necesarios para compartir este documento\n");
            return false;

        }

        if(!this.gepermisosvalidos().contains(permiso)){
            System.out.print("El permiso ingresado no es valido\n");
            return false;
        }

        for (int i = 0; i < listadeusuarios.size(); i++){
            if(listadeusuarios.get(i).equals(Doc.getOwnerdocumento())){
                System.out.print("Uno de los usuarios ingresados corresponde al propietario del documento, intente de nuevo");
                return false;
            }
        }

        for (int i = 0; i < listadeusuarios.size(); i++){
            if(this.getusernames().contains(listadeusuarios.get(i))){
                ;
            }
            else{
                System.out.print("Uno o mas usuarios ingresados no existen\n");
                return false;
            }
        }

        ArrayList<acceso> accesosdoc = Doc.getlistaaccessos();
        for (int i = 0; i < listadeusuarios.size(); i++){
            boolean encontrado = false;
            for (int j = 0; j < accesosdoc.size(); j++){
                if(accesosdoc.get(j).getusernameaccess().equals(listadeusuarios.get(i))){
                    accesosdoc.get(j).setPermiso(permiso);
                    encontrado = true;
                }
            }
            if(!encontrado){
                accesosdoc.add(new acceso(listadeusuarios.get(i), permiso));
            }
        }

        Doc.setListacceso(accesosdoc);
        this.listadocumentos.set(IdDoc, Doc);
        return true;
    }
}
