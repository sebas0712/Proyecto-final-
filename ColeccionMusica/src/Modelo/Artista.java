package Modelo;

import java.util.*;

import coleccionMusica.Modelo.Album;

public class Artista {
	
	/* atributos */
    private String nombre;
    private boolean estaActivo;
    private String rutaImagen;
    private int posicionAlbumActual;
    
    /* asociaciones con Fecha */
    private Fecha fechaNacimiento;    
    private Fecha fechaDebut;
    
    private ArrayList<Album> albumes;
    
    /* métodos */
    /* métodos constructores: */
    public Artista(String nombre, 
                   boolean activo,
                   Fecha fechaNac,
                   Fecha fechaDebut)
    {
        this.nombre = nombre;
        this.estaActivo = activo;
        this.fechaNacimiento = fechaNac;
        this.fechaDebut = fechaDebut;
        albumes = new ArrayList<Album>();
    }
    
    public Artista()
    {
        this.nombre = "";
        this.estaActivo = false;
        this.fechaNacimiento = new Fecha();
        this.fechaDebut = new Fecha();
        albumes = new ArrayList<Album>();
    }
    
    /*modificado*/
    public void agregarAlbum(String nombreAlbum,String generoAlbum,String rutaImagen,int duracionAlbum,Fecha fechaLan)
    {
    	Album nuevoAlbum = new Album(nombreAlbum,generoAlbum,rutaImagen,duracionAlbum,fechaLan);
        albumes.add(nuevoAlbum);
    }
    
    public boolean buscarAlbum(String nombreAlbum)
    {
        boolean encontrado=false;
        for(int i=0; i< albumes.size(); i++)
        {
           if(albumes.get(i).getTitulo().equalsIgnoreCase(nombreAlbum)  ) ;
           {
               encontrado=true;
           }
        }
        return encontrado;
    }
    
    public int contarAlbumesGenero(String genero)
    {
        int cuantos = 0;        
        for(int i = 0; i < albumes.size(); i++)
        {
            if(albumes.get(i).getGenero().equalsIgnoreCase(genero))
            {
                cuantos++;
            }
        }        
        return cuantos;        
    }
    
    public int numAlbumes()
    {
        return albumes.size();
    }
    
    /*Retorna el album en la posicion actual del artista*/
    public Album darAlbumActual() {
    	
    	Album album;
    	if (numAlbumes()==0) {
    		album = null;
		}else {
			album = albumes.get(posicionAlbumActual);
		}
    	
    	return album;
    }
    
    /*Permite ir a un album de la lista en el artista*/
    public void irAAlbum(int indice) throws Exception{
    	
    	if (numAlbumes()==0) {
    		
			throw new Exception("No hay albumes");
			
		}else if (posicionAlbumActual==indice) {
			
			throw new Exception("Ya se encuentra en este album");
			
		}else {
			
			posicionAlbumActual=indice;
		}
    }
    
    /*Permite retroceder en la lista de albumes del artista*/
    public Album anteriorAlbum() throws Exception{
    	
    	Album anterior = null;
    	
    	if (numAlbumes()==0 || posicionAlbumActual<=0) {
			throw new Exception("No hay mas albumes anteriores");
		}else {
			posicionAlbumActual--;
			anterior = albumes.get(posicionAlbumActual);
		}
    	
    	return anterior;
    }
    
    /*Permite avanzar en la lista de albumes del artista*/
    public Album siguienteAlbum() throws Exception{
    	
    	Album siguiente = null;
    	
    	if (posicionAlbumActual >= numAlbumes()-1) {
			throw new Exception("No hay mas albumes siguientes");
		}else {
			posicionAlbumActual++;
			siguiente = albumes.get(posicionAlbumActual);
		}
    	return siguiente;
    }
    
    public Album buscarAlbum2(String nombreAlbum)
    {
        Album elAlbum=null;
        for(int i=0; i< albumes.size(); i++)
        {
           if(albumes.get(i).getTitulo().equalsIgnoreCase(nombreAlbum)  ) ;
           {
               elAlbum = albumes.get(i);
           }
        }
        return elAlbum;
    }
    
    public ArrayList<Album> getAlbum(){/*ac*/
    	return albumes;
    }
    
    public void setAlbumes(ArrayList<Album> albumes) {/*ac*/
        this.albumes = albumes;
    } 
    
    public String getImagen() {
        return rutaImagen;
    }

    public void setImagen(String imagen) {
        this.rutaImagen = imagen;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isEstaActivo() {
        return estaActivo;
    }

    public void setEstaActivo(boolean estaActivo) {
        this.estaActivo = estaActivo;
    }

    public Fecha getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Fecha fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Fecha getFechaDebut() {
        return fechaDebut;
    }

    public void setFechaDebut(Fecha fechaDebut) {
        this.fechaDebut = fechaDebut;
    }


}
