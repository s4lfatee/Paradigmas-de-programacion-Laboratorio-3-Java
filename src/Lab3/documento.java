package Lab3;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class documento {
    private int DocId;
    private String titulo;
    private LocalDate fechacreacion;
    private String contenido = "";
    private String ownerdocumento;
    private static int ContadorIDdoc = 0;
    private LocalDate fechamodificacion;
    private ArrayList<version> listaversiones = new ArrayList<>();
    private ArrayList<acceso> listacceso = new ArrayList<>();
    
    public documento(String titulo, String contenido){
        this.titulo = titulo;
        this.fechacreacion = LocalDate.now();
        this.DocId = ContadorIDdoc++;
        this.contenido = contenido;
        this.listaversiones.add(new version(contenido, 0));
    }

    public documento(String titulo, String contenido, String ownerdoc){
        this.titulo = titulo;
        this.fechacreacion = LocalDate.now();
        this.DocId = ContadorIDdoc++;
        this.contenido = contenido;
        this.ownerdocumento = ownerdoc;
    }

    public void setListacceso(ArrayList<acceso> listacceso) {
        this.listacceso = listacceso;
    }

    public boolean verificaracceso(String usuario, String permiso){
         for(int i = 0; i < listacceso.size() ; i++){
             if(listacceso.get(i).getusernameaccess().equals(usuario) && listacceso.get(i).getaccesses().equals(permiso)){
                 return true;
             }
         }
         return false;
    }

    public String gettitulo(){
        return this.titulo;
    }
    
    public LocalDate getfechacreacion(){
        return this.fechacreacion;
    }
    
    public String getcontenido(){
        return this.contenido;
    }
    
    public int getid(){
        return this.DocId;
    }

    public String getOwnerdocumento(){
        return this.ownerdocumento;
    }
    
    public ArrayList getlistaversiones(){
        return this.listaversiones;
    }
    
    public ArrayList getlistaaccessos(){
        return this.listacceso;
    }

    public ArrayList getusernamesaccesos(){
        ArrayList<String> listausernames = new ArrayList<>();
        for(int i = 0; i < listacceso.size() ; i++){
            listausernames.add(listacceso.get(i).getusernameaccess());
        }
        return listausernames;
    }
    
    public void setcontenido(String contenido){
        this.contenido = contenido;
    }

    public void setowner(String username){
        this.ownerdocumento = username;
    }
}