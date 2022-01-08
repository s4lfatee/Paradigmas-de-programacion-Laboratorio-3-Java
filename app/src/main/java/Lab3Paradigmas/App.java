package Lab3Paradigmas;
import documento.*;

import java.util.Scanner;

public class App {
    static Scanner input = new Scanner(System.in).useDelimiter("\n");
    
    public static void main(String[] args) {
        paradigmaDocs plataforma = new paradigmaDocs("gdocs");
        int opcion = 1;
        
        do{
            authentication(plataforma);
            do{
                System.out.print("\n");
                opcion = menu();
                
                switch(opcion){
                    case 1:
                        System.out.print("FUNCIONA!!!!!!!!!!!!!!!!!!!!!\n");
                        System.out.print("Presione ENTER para continuar\n");
                        input.next();                        
                        break;
                    case 2:
                        System.out.print("FUNCIONA!!!!!!!!!!!!!!!!!!!!!\n");
                        System.out.print("Presione ENTER para continuar\n");
                        input.next();
                        break;
                    case 3:
                        System.out.print("FUNCIONA!!!!!!!!!!!!!!!!!!!!!\n");
                        System.out.print("Presione ENTER para continuar\n");
                        input.next();
                        break;
                }
            }while(opcion != 10 && opcion != 0);
        }while(opcion != 0);
    }
    
    public static boolean authentication(paradigmaDocs plataforma){
        String username;
        String password;
        boolean estadosesion = false;
        
        do{
            System.out.print("\n");
            System.out.print("1) Iniciar sesion");
            System.out.print("\n");
            System.out.print("2) Registrarse");
            System.out.print("\n");
            
            
            int opcion = Integer.parseInt(input.next());
            
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
                    System.out.print("Ingresar contrasenha: ");
                    password = input.next();
                    estadosesion = plataforma.registrarusuario(username, password);
                    if (estadosesion){
                        System.out.print("Usuario registrado correctamente\n");
                        return true;
                    }
                    System.out.print("El nombre de usuario ya existe\n");
                    break;
                    
            }
        } while(!estadosesion);
        
        return true;
        }
    
    public static int menu(){
        System.out.print("Editor\n");
        System.out.print("Escoja su opci�n:\n");
        System.out.print("1. Crear nuevo documento\n");
        System.out.print("2. Compartir documento\n");
        System.out.print("3. Agregar contenido a un documento\n");
        System.out.print("10. Cerrar sesion\n");
        System.out.print("0. Cerrar programa\n");
        System.out.print("INTRODUZCA SU OPCION: ");
        
        return Integer.parseInt(input.next());
    }
}

