package documento;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class documento {
    private String titulo;
    private LocalDate fechacreacion;
    private String contenido = "";
    private static int ContadorIDdoc = 0; 
    private int DocId;
    private ArrayList<version> listaversiones = new ArrayList<>();
    private ArrayList<acceso> listacceso = new ArrayList<>();
    
    public documento(String titulo, String contenido){
        this.titulo = titulo;
        this.fechacreacion = LocalDate.now();
        this.DocId = ContadorIDdoc++;
        this.contenido = contenido;
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
    
    public ArrayList<version> getlistaversiones(){
        return this.listaversiones;
    }
    
    public ArrayList<acceso> getlistaaccessos(){
        return this.listacceso;
    }
    
    public void setcontenido(String contenido){
        this.contenido = contenido;
    }
    
    public String ToString(){
        DateTimeFormatter formatofecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        ArrayList<String> stringsversion = new ArrayList<>();
        ArrayList<String> stringsacceso = new ArrayList<>();
        
        for (int i = 0; i < listaversiones.size(); i++){
            stringsversion.add(this.listaversiones.get(i).ToString());
        }
        
        for (int i = 0; i < listacceso.size(); i++){
            stringsacceso.add(this.listacceso.get(i).ToString());
        }
        
        String allaccesostostring = String.join(" -- ", stringsacceso);
        String allversiontostring = String.join(" -- ", stringsversion);
   
        return "Nombre del documento: " + this.titulo + "\n" + "Fecha de creacion: " 
                + this.fechacreacion.toString() + "\n" + "Contenido del documento: " 
                + this.contenido + "\n" + "ID del documento: " + this.DocId 
                + "\n" + "Lista de accesos: " + allaccesostostring 
                + "\n" + "Lista de versiones: " 
                + allversiontostring;
    }
}