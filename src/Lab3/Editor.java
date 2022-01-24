package Lab3;

import Documento.*;
import User.*;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.LocalDate;

/**
 * Clase que define el objeto editor, el cual posee todos los métodos
 * que harán cambios en la plataforma, además posee toda la información de la plataforma
 * tal como los usuarios registrados, documentos creados y sus caracteristicas, el usuario logueado
 * y los permisos válidos que se pueden emplear para los accesos de los documentos.
 * @author Francisco Salfate Garcés
 */
public class Editor {
    // Atributos
    private String nombreplataforma;
    private LocalDate fechaplataforma;
    private ArrayList<Usuario> listausers = new ArrayList<>();
    private ArrayList<Documento> listadocumentos = new ArrayList<>();
    private String usuariologueado = "";
    private ArrayList<String> permisosvalidos = new ArrayList<>();

    // Constructor

    /**
     * Constructor del editor, empieza inicialmente con 5 usuarios y 10 documentos
     * @param nombreplataforma Nombre de la plataforma del editor
     */
    public Editor(String nombreplataforma){
        this.nombreplataforma = nombreplataforma;
        this.fechaplataforma = LocalDate.now();
        this.listausers.add(new Usuario("salfate", "fco"));
        this.listausers.add(new Usuario("lmao", "hola123"));
        this.listausers.add(new Usuario("genericuser", "notapassword"));
        this.listausers.add(new Usuario("useer", "4321"));
        this.listausers.add(new Usuario("quintouser", "yelultimo"));
        this.listadocumentos.add(new Documento("titulodoc","content", "salfate"));
        this.listadocumentos.add(new Documento("otrodoc", "este es el contenido de otrodoc", "salfate"));
        this.listadocumentos.add(new Documento("documentooo", "Lorem ipsum dolor sit amet", "salfate"));
        this.listadocumentos.add(new Documento("ultimo doc de salfate", "definitivamente este es el ultimo", "salfate"));
        this.listadocumentos.add(new Documento("este documento no pertenece a fco", "adios fco", "lmao"));
        this.listadocumentos.add(new Documento("la verdad es que", "rellenar estos documentos no es muy divertido", "lmao"));
        this.listadocumentos.add(new Documento("pero tengo que hacerlo", "porque es requerido", "lmao"));
        this.listadocumentos.add(new Documento("kajkakaj", "minecraft fue desarrollado en java", "genericuser"));
        this.listadocumentos.add(new Documento("falta poco", "El lenguaje de programación Java fue desarrollado originalmente por James Gosling", "useer"));
        this.listadocumentos.add(new Documento("casi", "me gusta mucho jugar yugioh", "quintouser"));
        this.permisosvalidos.add("C");
        this.permisosvalidos.add("W");
        this.permisosvalidos.add("R");
    }

    // Getters

    /**
     * Método que obtiene el nombre de la plataforma
     * @return Nombre de la plataforma
     */
    public String getnombreplataforma(){
        return this.nombreplataforma;
    }

    /**
     * Método que obtiene la lista de documentos creados en la plataforma
     * @return Lista de documentos creados en la plataforma
     */
    public ArrayList getlistadocumentos(){
        return this.listadocumentos;
    }


    /**
     * Método que obtiene todos los nombres de usuario de los usuarios registrados en
     * la plataforma
     * @return Una lista de todos los nombres de usuarios
     */
    public ArrayList<String> getusernames(){
        ArrayList<String> usernames = new ArrayList<>();

        for(int i = 0; i < this.listausers.size(); i++){
            usernames.add(this.listausers.get(i).getusername());
        }
        return usernames;
    }

    // Setters

    /**
     * Método que obtiene el usuario que inició sesión
     * @param username Username del nuevo usuario logueado
     */
    public void setusuariologueado(String username){
        this.usuariologueado = username;
    }

    // Otros métodos


    /**
     * Método que registra a un usuario en la plataforma
     * @param username Nombre de usuario a registrar
     * @param password Contraseña de usuario a registrar
     * @return Valor booleano dependiendo si el registro fue éxitoso o no
     */
    public boolean registrarusuario(String username, String password){
        if (this.listausers.isEmpty()){
            this.listausers.add(new Usuario(username, password));
            return true;
        }
        else{
            for (int i = 0; i < this.listausers.size(); i++){
                if (this.listausers.get(i).getusername().equals(username))
                    return false;
            }
            this.listausers.add(new Usuario(username, password));
            return true;
        }
    }

    /**
     * Método que verifica la información de un usuario e inicia su sesión
     * @param username Nombre de usuario para loguear
     * @param password Contraseña de usuario para loguear
     * @return Valor booleano dependiendo si el inicio de sesión fue éxitoso o no
     */
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

    /**
     * Método que cierra la sesión de un usuario
     */
    public void logout(){
        this.usuariologueado = "";
    }

    /**
     * Método que crea un nuevo documento en la plataforma
     * @param nombredoc Titulo del documento a crear
     * @param contenido Contenido del documento a crear
     */
    public void create(String nombredoc, String contenido){
        Documento nuevodocumento = new Documento(nombredoc, contenido);
        nuevodocumento.setowner(this.usuariologueado);
        this.listadocumentos.add(nuevodocumento);
    }

    /**
     * Método que logra que un usuario pueda compartir un documento
     * @param listadeusuarios Lista de usuarios ingresada para compartir un documento
     * @param IdDoc Id del documento donde se aplicará la creación o actualización de permisos
     * @param permiso Permiso el cual es concedido a cada uno de los usuarios ingresados
     * @return Valor booleano dependiendo si el compartir un documento fue éxitoso o no
     */
    public boolean share(ArrayList<String> listadeusuarios, int IdDoc, String permiso){
        if(IdDoc > this.getlistadocumentos().size() - 1){
            System.out.print("No existe el documento especificado\n");
            return false;
        }

        Documento Doc = this.listadocumentos.get(IdDoc);

        if(!(this.usuariologueado.equals(Doc.getOwnerdocumento()) || Doc.verificaracceso(this.usuariologueado, "W"))){
            System.out.print("No eres propietario del documento o no posees los permisos necesarios para compartir este documento\n");
            return false;
        }

        if(!this.permisosvalidos.contains(permiso)){
            System.out.print("El permiso ingresado no es valido\n");
            return false;
        }

        for (int i = 0; i < listadeusuarios.size(); i++){
            if(listadeusuarios.get(i).equals(Doc.getOwnerdocumento())){
                System.out.print("Uno de los usuarios ingresados corresponde al " +
                        "propietario del documento, intente de nuevo\n");
                return false;
            }
        }

        if(!(this.getusernames().containsAll(listadeusuarios))){
            System.out.print("Uno o mas usuarios ingresados no existen\n");
            return false;
        }

        ArrayList<Acceso> accesosdoc = Doc.getlistaaccessos();
        for (int i = 0; i < listadeusuarios.size(); i++){
            boolean encontrado = false;
            for (int j = 0; j < accesosdoc.size(); j++){
                if(accesosdoc.get(j).getusernameaccess().equals(listadeusuarios.get(i))){
                    accesosdoc.get(j).setPermiso(permiso);
                    encontrado = true;
                }
            }
            if(!encontrado){
                accesosdoc.add(new Acceso(listadeusuarios.get(i), permiso));
            }
        }

        Doc.setListacceso(accesosdoc);
        this.listadocumentos.set(IdDoc, Doc);
        return true;
    }

    /**
     * Método que permite agregar contenido a un documento
     * @param IdDoc Id del documento donde se modificará el contenido
     * @param texto Contenido que se agregará al contenido del documento
     * @return Valor booleano
     */
    public boolean add(int IdDoc, String texto){
        if(IdDoc > this.getlistadocumentos().size() - 1){
            System.out.print("No existe el documento especificado\n");
            return false;
        }

        Documento Doc = this.listadocumentos.get(IdDoc);

        if(!(this.usuariologueado.equals(Doc.getOwnerdocumento()) || Doc.verificaracceso(this.usuariologueado, "W"))){
            System.out.print("No eres propietario del documento o no posees los permisos necesarios para compartir este documento\n");
            return false;
        }

        Doc.setcontenido(Doc.getcontenido() + texto);
        Doc.getlistaversiones().add(new Version(Doc.getcontenido(), Doc.getlistaversiones().size()));
        this.listadocumentos.set(IdDoc, Doc);
        return true;
    }

    /**
     * Método que restaura una versión antigua de un documento
     * @param IdDoc Id del documento se realizará la restauración de versión
     * @param IdVersion Id de la versión a restaurar
     * @return Valor booleano dependiendo si la restauración fue éxitosa o no
     */
    public boolean rollback(int IdDoc, int IdVersion){
        if(IdDoc > this.getlistadocumentos().size() - 1){
            System.out.print("No existe el documento especificado\n");
            return false;
        }

        Documento Doc = this.listadocumentos.get(IdDoc);

        if(IdVersion > Doc.getlistaversiones().size() - 1) {
            System.out.print("No existe la version especificada\n");
            return false;
        }

        ArrayList<Version> Versionesdoc = Doc.getlistaversiones();

        Version Ver = Versionesdoc.get(IdVersion);

        this.listadocumentos.get(IdDoc).getlistaversiones().add(new Version(Ver.getcontenido(),
                Doc.getlistaversiones().size()));

        this.listadocumentos.get(IdDoc).setcontenido(Ver.getcontenido());
        this.listadocumentos.set(IdDoc, Doc);
        return true;
    }

    /**
     * Método que revoca todos los accesos de un documento
     * @param IdDoc Id del documento donde se realizará la revocación de permisos
     * @return Valor booleano dependiendo si la revocación fue éxitosa o no
     */
    public boolean revokeAccess(int IdDoc){
        if(IdDoc > this.getlistadocumentos().size() - 1){
            System.out.print("No existe el documento especificado\n");
            return false;
        }

        Documento Doc = this.listadocumentos.get(IdDoc);

        if(!this.usuariologueado.equals(Doc.getOwnerdocumento())){
            System.out.print("No eres propietario de este documento\n");
            return false;
        }

        Doc.getlistaaccessos().clear();
        this.listadocumentos.set(IdDoc, Doc);
        return true;
    }

    /**
     * Método que muestra los documentos que coincidan con la busqueda de un texto
     * @param texto Texto a buscar en la lista de documentos
     * @return String que indica cuales documentos han sido encontrados
     */
    public String search(String texto){
        ArrayList<String> docsencontrados = new ArrayList<>();

        for(int i = 0; i < this.listadocumentos.size(); i++){
            if(this.listadocumentos.get(i).getcontenido().contains(texto)){
                if(this.listadocumentos.get(i).getOwnerdocumento().equals(this.usuariologueado)){
                    docsencontrados.add(this.listadocumentos.get(i).ToString());
                }
                else if(this.listadocumentos.get(i).getusernamesaccesos().contains(this.usuariologueado)){
                    docsencontrados.add(this.listadocumentos.get(i).ToString());
                }
            }
        }
        String docs = String.join(" \n----------\n ", docsencontrados);
        return "Los documentos encontrados son: \n" + docs;
    }

    /**
     * Método que genera un String de toda la información de la plataforma
     * @return Toda la información de la plataforma contenida en un String
     */
    public String EditorToString(){
        DateTimeFormatter formatofecha = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        ArrayList<String> usuariosdoc = new ArrayList<>();
        ArrayList<String> documentosdoc = new ArrayList<>();

        if(!this.usuariologueado.equals("")){
            for(int i = 0; i < this.listausers.size(); i++){
                if(this.listausers.get(i).getusername().equals(this.usuariologueado)) {
                    usuariosdoc.add(this.listausers.get(i).ToString());
                }
            }

            for(int i = 0; i < this.listadocumentos.size(); i++){
                if(this.listadocumentos.get(i).getusernamesaccesos().contains(this.usuariologueado) ||
                        this.listadocumentos.get(i).getOwnerdocumento().equals(this.usuariologueado)){
                    documentosdoc.add(this.listadocumentos.get(i).ToString());
                }
            }

            String usuariosdocstring = String.join("\n----------\n", usuariosdoc);
            String documentosdocstring = String.join("\n----------\n", documentosdoc);

            return "Nombre de la plataforma: " + this.nombreplataforma + "\n" + "Fecha de creacion de la plataforma: " +
                    this.fechaplataforma.format(formatofecha) + "\n" + "Usuarios registrados: \n" + usuariosdocstring + "\n"
                    + "\n" + "Documentos de la plataforma: \n" + documentosdocstring;
        }

        for(int i = 0; i < this.listadocumentos.size(); i++){
            documentosdoc.add(this.listadocumentos.get(i).ToStringLoggedOff());
        }

        String documentosdocstring = String.join(" \n----------\n", documentosdoc);

        return "Nombre de la plataforma: " + this.nombreplataforma + "\n" + "Fecha de creacion de la plataforma: " +
                "\n" + this.fechaplataforma.format(formatofecha) + "\n" + "Documentos de la plataforma: " + "\n\n" + documentosdocstring;
    }

    /**
     * Método que imprime el String generado por EditorToString()
     */
    public void PrintEditor(){
        System.out.print(this.EditorToString());
    }
}
