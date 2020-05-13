package Modelo;

import java.util.*;

public class Album {

	private String titulo;
    private String genero;
    private int numCopias;
    private String rutaImagen;
    private int duracion;
    private Fecha fechaLanzamiento;    
        
    private ArrayList<Cancion> canciones;
    
    public Album()
    {        
        canciones = new ArrayList<Cancion>();
    }  

    public Album(String titulo, 
                 String genero, 
                 String imagen, 
                 int duracion, 
                 Fecha fechaLanzamiento) {
        this.titulo = titulo;
        this.genero = genero;        
        this.rutaImagen = imagen;
        this.duracion = duracion;
        this.fechaLanzamiento = fechaLanzamiento;
        this.canciones = new ArrayList<>();
    }
    
    public int darDuracionAlbum()
    {
        int duracion=0;        
        /* recorrer arreglo de canciones y sumar duracion de *cada* cancion */
        for(int i=0; i < canciones.size(); i++   )
        {
            duracion += canciones.get(i).getDuracion();
        }
        /**/        
        return duracion;
    }
    
    public void agregarCancion(Cancion nuevaCancion)
    {
        canciones.add(nuevaCancion);
    }
    
    
    public boolean existeCancion(String tituloCancion)
    {
        //Buscar canción en el album:        
        //Recorrer arreglo de canciones:
        boolean encontrada = false;
        for(int i = 0; i < canciones.size(); i++)
        {
            if( canciones.get(i).getTitulo().equalsIgnoreCase(tituloCancion)  )
            {
                encontrada = true;
            }            
        }
        return encontrada;
    }
    
    public Cancion buscarCancion(String tituloCancion)
    {
        //Buscar canción en el album:        
        //Recorrer arreglo de canciones:
        Cancion laCancion = null;
        for(int i = 0; i < canciones.size(); i++)
        {
            if( canciones.get(i).getTitulo().equalsIgnoreCase(tituloCancion)  )
            {
                laCancion = canciones.get(i);
            }            
        }
        return laCancion;
    }
    
    /* tarea: constructor con parámetros */

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getNumCopias() {
        return numCopias;
    }

    public void setNumCopias(int numCopias) {
        this.numCopias = numCopias;
    }

    public String getImagen() {
        return rutaImagen;
    }

    public void setImagen(String imagen) {
        this.rutaImagen = imagen;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public Fecha getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(Fecha fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public ArrayList<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(ArrayList<Cancion> canciones) {
        this.canciones = canciones;
    }
	
}
