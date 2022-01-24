package UI;
import Lab3.Editor;

import java.util.*;

/**
 * Clase que define la interfaz visual de la plataforma
 * es decir, posee una vista de menú el cual es interactivo,
 * con la capabilidad de ejecutar varias funcionalidades de la plataforma
 * junto con retroalimentación de las funcionalidades.
 * @author Francisco Salfate Garcés
 */
public class UserInterface {
    // Atributos
    static Scanner input = new Scanner(System.in);

    // Otros métodos

    /**
     * Método que ejecuta las funcionalidades mostradas en el menú
     */
    public static void inicio(){
        Editor plataforma = new Editor("gdocs");
        Integer opcion = 1;

        do{
            authentication(plataforma);
            do{
                System.out.print("\n");
                opcion = menu();
                Scanner inputstring = new Scanner(System.in).useDelimiter("\n");

                switch(opcion){
                    case 1:
                        System.out.print("Creacion de documento\n");
                        System.out.print("Ingresar titulo del documento: ");
                        String titulo = inputstring.next();
                        System.out.print("\nIngresar contenido del documento: ");
                        String contenido = inputstring.next();
                        plataforma.create(titulo, contenido);
                        System.out.print("Documento creado con éxito\n");
                        PresionarEnter();
                        break;
                    case 2:
                        Scanner otherinput = new Scanner(System.in);
                        System.out.print("Compartir documento\n");
                        System.out.print("Ingresar el id del documento a compartir: ");
                        int idDoc = input.nextInt();
                        System.out.print("\nIngresar los usuarios para compartir (Separar con comas y un espacio, " +
                                "como ejemplo considerar lo siguiente: 'Usuario1, Usuario2, Usuario3': ");
                        String usuarios = inputstring.nextLine();
                        ArrayList<String> listausuarios = new ArrayList<>(Arrays.asList(usuarios.split(", ")));
                        System.out.print("\nIngresar el permiso a conceder, tenga en consideracion que los " +
                                        "permisos admitidos corresponden a C (comentar), W (escribir) y R (leer): ");
                        String permiso = otherinput.next();
                        if(plataforma.share(listausuarios, idDoc, permiso.toUpperCase())){
                            System.out.print("Permiso otorgado con exito\n");
                        }
                        PresionarEnter();
                        break;
                    case 3:
                        System.out.print("Agregar contenido a un documento\n");
                        System.out.print("Ingresar el id del documento a modificar: ");
                        idDoc = input.nextInt();
                        System.out.print("\nIngresar el texto a agregar: ");
                        contenido = inputstring.next();
                        if(plataforma.add(idDoc, contenido)){
                            System.out.print("Contenido agregado con exito\n");
                        }
                        PresionarEnter();
                        break;
                    case 4:
                        System.out.print("Restaurar versióo de un documento\n");
                        System.out.print("Ingresar el id del documento: ");
                        idDoc = input.nextInt();
                        System.out.print("\nIngresar el id de la version a restaurar: ");
                        int IdVer = input.nextInt();
                        if(plataforma.rollback(idDoc, IdVer)){
                            System.out.print("Version restaurada con exito\n");
                        }
                        PresionarEnter();
                        break;
                    case 5:
                        System.out.print("Revocar accesos a un documento\n");
                        System.out.print("Ingresar el id del documento: ");
                        idDoc = input.nextInt();
                        if(plataforma.revokeAccess(idDoc)){
                            System.out.print("Accesos revocados con exito\n");
                        }
                        PresionarEnter();
                        break;
                    case 6:
                        System.out.print("Buscar texto en los documentos\n");
                        System.out.print("Ingrese texto a buscar: ");
                        String texto = inputstring.nextLine();
                        System.out.print(plataforma.search(texto));
                        PresionarEnter();
                        break;
                    case 7:
                        System.out.print("Visualizar documentos\n");
                        plataforma.PrintEditor();
                        PresionarEnter();
                        break;
                    case 10:
                        plataforma.logout();
                        System.out.print("Sesion cerrada con exito\n");
                        PresionarEnter();
                        break;
                }
            }while(opcion != 10 && opcion != 0);
        }while(opcion != 0);
    }

    /**
     * Método que muestra y ejecuta el menú de registro o inicio de sesión de la plataforma
     * @param plataforma Plataforma a la cual se desea ingresar
     * @return Valor booleano dependiendo si el registro o inicio de sesión fue éxitoso
     */
    public static boolean authentication(Editor plataforma){
        String username;
        String password;
        boolean estadosesion = false;

        do{
            System.out.print("Plataforma: " + plataforma.getnombreplataforma());
            System.out.print("\n");
            System.out.print("1) Iniciar sesion");
            System.out.print("\n");
            System.out.print("2) Registrarse");
            System.out.print("\n");
            System.out.print("3) Visualizar plataforma");
            System.out.print("\n");
            System.out.print("INTRODUZCA SU OPCION: ");


            Integer opcion = Integer.parseInt(input.next());

            switch(opcion){
                case 1:
                    System.out.print("Inicio de sesion");
                    System.out.print("\n\n");
                    System.out.print("Ingresar nombre de usuario: ");
                    username = input.next();
                    System.out.print("\n");
                    System.out.print("Ingresar contrasenha: ");
                    password = input.next();
                    estadosesion = plataforma.autenticarusuario(username, password);
                    if (estadosesion){
                        System.out.print("La sesion se ha iniciado correctamente\n");
                        return true;
                    }
                    System.out.print("Fallo al iniciar la sesion\n");
                    break;

                case 2:
                    System.out.print("Registrarse");
                    System.out.print("\n\n");
                    System.out.print("Ingresar nombre de usuario a registrar: ");
                    username = input.next();
                    System.out.print("\n");
                    System.out.print("Ingresar clave de usuario: ");
                    password = input.next();
                    estadosesion = plataforma.registrarusuario(username, password);
                    if (estadosesion){
                        System.out.print("Usuario registrado correctamente\n");
                        return true;
                    }
                    System.out.print("El nombre de usuario ya existe\n");
                    break;

                case 3:
                    System.out.print("");
                    System.out.print("Vizualizar documentos\n");
                    plataforma.PrintEditor();
                    PresionarEnter();
                    break;
            }
        } while(!estadosesion);

        return true;
    }

    /**
     * Método que muestra el menú de funcionalidades de la plataforma
     * @return Opción seleccionada
     */
    public static int menu(){
        System.out.print("Editor\n");
        System.out.print("Escoja su opcion:\n");
        System.out.print("1. Crear nuevo documento\n");
        System.out.print("2. Compartir documento\n");
        System.out.print("3. Agregar contenido a un documento\n");
        System.out.print("4. Restaurar version de un documento\n");
        System.out.print("5. Revocar accesos a un documento\n");
        System.out.print("6. Buscar en los documentos\n");
        System.out.print("7. Visualizar plataforma\n");
        System.out.print("10. Cerrar sesion\n");
        System.out.print("0. Cerrar programa\n");
        System.out.print("INTRODUZCA SU OPCION: ");

        return Integer.parseInt(input.next());
    }

    /**
     * Método que permite pausar el programa hasta presionar ENTER
     * la útilidad de este método es que logra que el usuario pueda leer
     * la retroalimentación recibida por los métodos
     */
    public static void PresionarEnter()
    {
        System.out.println("Presione ENTER para continuar");
        try
        {
            System.in.read();
        }
        catch(Exception e)
        {}
    }
}
