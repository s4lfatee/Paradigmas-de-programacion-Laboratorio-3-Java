package Lab3;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Arrays;

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
    private String usuariologueado;
    private ArrayList<String> permisosvalidos = new ArrayList<>(Arrays.asList("C", "R", "W"));

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
    }

    // Getters

    /**
     *
     * @return Nombre de la plataforma
     */
    public String getnombreplataforma(){
        return this.nombreplataforma;
    }

    /**
     *
     * @return Fecha de creación de la plataforma
     */
    public LocalDate getfechaplataforma(){
        return this.fechaplataforma;
    }

    /**
     *
     * @return Lista de usuarios registrados en la plataforma
     */
    public ArrayList getlistausers(){
        return this.listausers;
    }

    /**
     *
     * @return Permisos válidos de acceso de la plataforma
     */
    public ArrayList getpermisosvalidos(){
        return this.permisosvalidos;
    }

    /**
     *
     * @return Lista de documentos creados en la plataforma
     */
    public ArrayList getlistadocumentos(){
        return this.listadocumentos;
    }

    /**
     *
     * @return Usuario logueado en la plataforma
     */
    public String getusuariologueado(){
        return this.usuariologueado;
    }

    /**
     *
     * @return Una lista de todos los nombres de usuarios
     */
    public ArrayList getusernames(){
        ArrayList<String> usernames = new ArrayList<>();

        for(int i = 0; i < this.listausers.size(); i++){
            usernames.add(this.listausers.get(i).getusername());
        }
        return usernames;
    }

    // Setters

    /**
     *
     * @param username Username del nuevo usuario logueado
     */
    public void setusuariologueado(String username){
        this.usuariologueado = username;
    }

    // Otros métodos


    /**
     *
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
     *
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
     *
     * @param nombredoc Titulo del documento a crear
     * @param contenido Contenido del documento a crear
     */
    public void create(String nombredoc, String contenido){
        Documento nuevodocumento = new Documento(nombredoc, contenido);
        nuevodocumento.setowner(this.usuariologueado);
        this.listadocumentos.add(nuevodocumento);
    }

    /**
     *
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

        if(!this.getpermisosvalidos().contains(permiso)){
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
     *
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
     *
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

        Doc.getlistaversiones().add(Ver);

        this.listadocumentos.set(IdDoc, Doc);
        return true;
    }

    /**
     *
     * @param IdDoc Id del documento donde se realizará la revocación de permisos
     * @return
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
     *
     * @param texto Texto a buscar en la lista de documentos
     * @return String que indica cuales documentos han sido encontrados
     */
    public String search(String texto){
        ArrayList<String> docsencontrados = new ArrayList<>();

        for(int i = 0; i < this.listadocumentos.size(); i++){
            if(this.listadocumentos.get(i).getcontenido().contains(texto)){
                if(this.listadocumentos.get(i).getOwnerdocumento().equals(this.usuariologueado)){
                    docsencontrados.add(this.listadocumentos.get(i).gettitulo());
                }
                else if(this.listadocumentos.get(i).getusernamesaccesos().contains(this.usuariologueado)){
                    docsencontrados.add(this.listadocumentos.get(i).gettitulo());
                }
                else{
                    ;
                }
            }
        }
        String docs = String.join(", ", docsencontrados);
        return "Los documentos encontrados son (En forma de titulos): " + docs;
    }
}
