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
    
    public String ToString(){
        DateTimeFormatter formatofecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "Nombre de la plataforma: " + this.nombreplataforma + "\n" 
                + formatofecha.format(this.fechaplataforma);
    }
}
