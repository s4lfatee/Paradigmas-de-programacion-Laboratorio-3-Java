package Lab3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class version {
    private int idversion;
    private String contenido;
    private LocalDate fechaversion;
    private static int ContadorID = 0;
    
    public version(String contenido, int idversion){
        this.idversion = ContadorID++;
        this.contenido = contenido;
        this.fechaversion = LocalDate.now();

    }
    
    public String getcontenido(){
        return this.contenido;
    }
    
    public LocalDate getfechaversion(){
        return this.fechaversion;
    }
    
    public int getidversion(){
        return this.idversion;
    }

    public String ToString(){
        DateTimeFormatter formatofecha = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return "Version numero: " + this.idversion + "\n" + "Fecha de version: " + this.fechaversion.format(formatofecha)
                + "\n" + "Contenido de la version: " + this.contenido;
    }
}