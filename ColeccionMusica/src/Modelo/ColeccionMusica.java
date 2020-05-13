package Modelo;

import java.io.*;
import java.util.*;

public class ColeccionMusica {

	private String propietario;
    private Fecha fechaCreacion;
    private ArrayList<Artista> artistas;
    
    public static final String GENERO_SALSA="Salsa";
    public static final String GENERO_ROCK="Rock";
    public static final String GENERO_JAZZ="Jazz";
    public static final String GENERO_ESPIRITUAL="Espiritual";

    public ColeccionMusica(String propietario, Fecha fechaCreacion) {
        this.propietario = propietario;
        this.fechaCreacion = fechaCreacion;
        artistas = new ArrayList<Artista>();
    } 
    
    public ColeccionMusica()
    {
        artistas = new ArrayList<Artista>();
    }
    
    public void agregarArtista(Artista nuevoArtista)
    {
        artistas.add(nuevoArtista);
    }
    
    public boolean buscarArtista(String nombreArtista)
    {
        boolean encontrado = false;        
        for(int i=0; i < artistas.size(); i++)
        {
            if(artistas.get(i).getNombre().equalsIgnoreCase(nombreArtista))
            {
                encontrado = true;
            }
        }        
        return encontrado;
    }
    
    public String artistaMasAlbumes()
    {
        String nombreMayor = "";
        int mayorNumAlbumes = 0;
        /* recorrer todos los artistas :*/
        for(int i = 0; i< artistas.size(); i++)
        {
            /* obtener el número álbumes de cada artista */
            if(   artistas.get(i).numAlbumes() >  mayorNumAlbumes    )
            {
                mayorNumAlbumes = artistas.get(i).numAlbumes();
                nombreMayor = artistas.get(i).getNombre();
            }
        }        
        return nombreMayor;
    }
    
    /*
     COMPLETAR: Completar método
    */
    public String generoMasAlbumes()
    {
        String nombreGenero ="";
        int cuantosSalsa=0, cuantosEspiritual=0, cuantosJazz=0,cuantosRock=0;    
        
        /* 1. recorrer  todos los artistas */
        for(int i = 0; i < artistas.size(); i++)
        {
            /* 2. por cada artista, contar cuantos albumes pertenecen a cada
             genero (usar funcion contarAlbumesGenero)  */
            cuantosSalsa += artistas.get(i).contarAlbumesGenero(ColeccionMusica.GENERO_SALSA);
            cuantosEspiritual += artistas.get(i).contarAlbumesGenero(ColeccionMusica.GENERO_ESPIRITUAL);
            cuantosJazz += artistas.get(i).contarAlbumesGenero(ColeccionMusica.GENERO_JAZZ);
            cuantosRock += artistas.get(i).contarAlbumesGenero(ColeccionMusica.GENERO_ROCK);            
        }
        /* 3. determinar cuál genero tiene mayor cantidad de álbumes y devolver el nombre
        */
        
                
      return nombreGenero;  
    } 
    
    /**
     * 
     * @param pGenero: Nombre del Género: Salsa, Rock, Jazz o Religiosa
     * @return lista de artistas que pertenecen de ese género
     */
    public ArrayList<Album> albumesPorGenero(String pGenero)
    {
        ArrayList<Album> Resultado = new ArrayList<Album>();
        /*
        Completar...
        */
        return Resultado;
    }
    
    /**
     * 
     * @param pArtista: Nombre del artista
     * @return lista de álbumes que pertenecen al artista
     */
    public ArrayList<Artista> albumesPorArtista(String pArtista)
    {
       ArrayList<Artista> Resultado = new ArrayList<Artista>();
        /*
        Completar...
        */
        return Resultado;   
    }
    
    public Artista buscarArtista2(String nombreArtista)
    {
        Artista elArtista = null;        
        for(int i=0; i < artistas.size(); i++)
        {
            if(artistas.get(i).getNombre().equalsIgnoreCase(nombreArtista))
            {
                elArtista = artistas.get(i);
            }
        }        
        return elArtista;
    }

    public ArrayList<Artista> getArtistas() {
        return artistas;
    }

    public void setArtistas(ArrayList<Artista> artistas) {
        this.artistas = artistas;
    }   

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public Fecha getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Fecha fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    
    /* Modificar método para que se adecúe al acrhivo de entrada */
    public void CargarInformacion(String rutaArchivo)
    {
        try {
            
            BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
           
            String linea;
            /* Leer información de la colección de música: */
            linea = br.readLine().trim(); //"Diego Loaiza,2020-04-22"
            
            String datosCol[] = linea.split(","); //datosCol[]={"Diego Loaiza", "2020-04-22"};
            String fecha = datosCol[1]; //fecha = "2020-04-22"
            String datosFecha[] = fecha.split("-"); //datosFecha[] = {"2020","04","22"};            
            propietario = datosCol[0];
            fechaCreacion = convertirFecha(datosFecha); 
            
            /* leer número de artistas que se van a leer: */
            int numArtistas =  Integer.parseInt(br.readLine().trim());
            
            /* leer información del primer artista:*/
            for(int i = 0; i < numArtistas; i++)
            {
                    linea = br.readLine().trim(); //Ejemplo: Mick Jagger,true,1943-07-26,1964-12-07            
                    String datosArtista[] = linea.split(",");
                    String nombreArtista = datosArtista[0]; //"Mick Jagger"
                    boolean activo =  Boolean.valueOf(datosArtista[1].trim()); //true
                    String fechaNacArtista[] = datosArtista[2].trim().split("-"); //{"1943","07","26"}
                    String fechaDebutArtista[] = datosArtista[3].trim().split("-"); //{"1964","12","07"}            
                    Fecha fechaNac = convertirFecha(fechaNacArtista);            
                    Fecha fechaDebut = convertirFecha(fechaDebutArtista);            
                    Artista nuevoArtista = new Artista(nombreArtista, activo, fechaNac, fechaDebut);
                    artistas.add(nuevoArtista);
            }
            
            br.close();
            
            
        } catch (FileNotFoundException ex) {
            
        } catch (IOException ex) {
            
        }
    }
    
    public Fecha convertirFecha(String fechaArr[])
    {
        Fecha laFecha = new Fecha(Integer.valueOf(fechaArr[2]),
                                  Integer.valueOf(fechaArr[1]),
                                  Integer.valueOf(fechaArr[0]));
        return laFecha;
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ColeccionMusica miColeccion = new ColeccionMusica();         
        miColeccion.CargarInformacion("./Datos/DatosIniciales1.txt");
        System.out.print("El artista con mas alnnbnmbumes es: "+miColeccion.artistaMasAlbumes());
        
    }

	
}
