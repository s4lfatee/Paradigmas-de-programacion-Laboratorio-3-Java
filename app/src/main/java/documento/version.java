package documento;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class version {
    private String contenido;
    private LocalDate fechaversion;
    private int idversion;
    
    public version(String contenido, int idversion){
        this.contenido = contenido;
        this.fechaversion = LocalDate.now();
        this.idversion = idversion;
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
        DateTimeFormatter formatofecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("version %d, contenido: %s, creacion: %s \n",
                this.getidversion(), 
                this.getcontenido(),
                formatofecha.format(this.getfechaversion()));
    }
}