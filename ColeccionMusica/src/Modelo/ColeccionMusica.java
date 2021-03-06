package Modelo;

import java.io.*;
import java.util.*;

import coleccionMusica.Modelo.ColeccionMusica;

public class ColeccionMusica {

	private String propietario;
    private Fecha fechaCreacion;
    private ArrayList<Artista> artistas;
    private int posicionArtistaActual;
    
    public static final String GENERO_SALSA="Salsa";
    public static final String GENERO_ROCK="Rock";
    public static final String GENERO_JAZZ="Jazz";
    public static final String GENERO_ESPIRITUAL="Espiritual";

    
    /*Crea la coleccion de musica con sus respectivos datos e informacion inicial*/
    public ColeccionMusica(String propietario, Fecha fechaCreacion, String rutaArchivo) {
        this.propietario = propietario;
        this.fechaCreacion = fechaCreacion;
        artistas = new ArrayList<Artista>();
        CargarInformacion(rutaArchivo);
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
    public String generoMasAlbumes() /*LISTO ac*/
    {
        String nombreGenero ="";
        int cuantosSalsa=0, cuantosEspiritual=0, cuantosJazz=0,cuantosRock=0,mayor=0;    
        
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
        if (cuantosSalsa>mayor) {
			mayor=cuantosSalsa;
			nombreGenero=GENERO_SALSA;
		}
        if (cuantosEspiritual>mayor) {
        	mayor=cuantosEspiritual;
			nombreGenero=GENERO_ESPIRITUAL;
		}
        if (cuantosJazz>mayor) {
        	mayor=cuantosJazz;
			nombreGenero=GENERO_JAZZ;
		}
        if (cuantosRock>mayor) {
			mayor=cuantosRock;
			nombreGenero=GENERO_ROCK;
		}
        
  
      return nombreGenero;  
    } 
    
    /**
     * 
     * @param pGenero: Nombre del Género: Salsa, Rock, Jazz o Religiosa
     * @return lista de artistas que pertenecen de ese género
     */
    public ArrayList<Album> albumesPorGenero(String pGenero)/*LISTO*/
    {
        ArrayList<Album> Resultado = new ArrayList<Album>();
        /*
        Completar... Completed
        */

        /*Recorre todos los artistas*/
        for (int i = 0; i < artistas.size(); i++) {
        	/*Recorre todos los albumes del artista*/
			for (int j = 0; j < artistas.get(i).getAlbum().size(); j++) {
				/*Verifica que albumes son del genero ingresado*/					
				if (artistas.get(i).getAlbum().get(j).getGenero().equalsIgnoreCase(pGenero)) {
					Album resultado = new Album(artistas.get(i).getAlbum().get(j).getTitulo(), 
												artistas.get(i).getAlbum().get(j).getGenero(),
												artistas.get(i).getAlbum().get(j).getImagen(),
												artistas.get(i).getAlbum().get(j).getDuracion(),
												artistas.get(i).getAlbum().get(j).getFechaLanzamiento());
					Resultado.add(resultado);
					
				}			

			}
        	
		}
        /*PRUEBA de que guarda
        for (int i = 0; i < Resultado.size(); i++) {
			System.out.println(Resultado.get(i).getTitulo());
		}*/
        
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
    
    /*Retorna el artista en la posicion actual de la coleccion de musica*/
    public Artista darArtistaActual() {
    	
    	Artista artista;
    	
    	if (darNumeroArtistas()==0) {
			artista = null;
		}
    	else {
			artista = artistas.get(posicionArtistaActual);
		}
    	
    	return artista;
    }
    
    /*Permite retroceder en la lista de artista de la coleccion de musica*/
    public Artista anteriorArtista() throws Exception{
    	
    	Artista anterior = null;
    	
    	if (darNumeroArtistas() == 0 || posicionArtistaActual <= 0) {
			throw new Exception ("No hay mas artistas anteriores");
		}else {
			posicionArtistaActual--;
			anterior = artistas.get(posicionArtistaActual);
		}
    	
    	return anterior;
    }
    
    /*Permite avanzar en la lista de artista de la coleccion de musica*/
    public Artista siguienteArtista() throws Exception{
    	
    	Artista siguiente = null;
    	
    	if (posicionArtistaActual >= darNumeroArtistas()-1) {
			throw new Exception("No hay mas artistas siguientes");
		}else {
			posicionArtistaActual++;
			siguiente = artistas.get(posicionArtistaActual);
		}
    	return siguiente;
    }
    
    /*Permite ir a un album de la lista del artista actual*/
    public void irAAlbum(int pIndice) throws Exception{
    	
    	if (darNumeroArtistas() == 0) {
			throw new Exception("No hay artistas");
		}else {
			Artista actual = darArtistaActual();
			actual.irAAlbum(pIndice);
		}
    }
    
    public int darNumeroArtistas() {
    	return artistas.size();
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
    public void CargarInformacion(String rutaArchivo) /*LISTO*/
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
  
         
            /*Lee informacion del album del ultimo artista*/
            int ultimo = artistas.size()-1;
            int numAlbum = Integer.parseInt(br.readLine());
            
            /*Leer informacion de albumes*/           
			for (int j = 0; j < numAlbum; j++) {//Life Between the Exit Signs,Jazz,./Datos/Imagenes/KeithJarrett1.jpg,43,1968-04-01
			linea = br.readLine().trim();
			String datosAlbum[] = linea.split(",");
			String nombreAlbum = datosAlbum[0];//"Life Between the Exit Signs"
			String generoAlbum = datosAlbum[1];//Jazz
			//800000??
			String rutaImagen = datosAlbum[2].trim();//./Datos/Imagenes/KeithJarrett1.jpg
			int duracionAlbum = Integer.parseInt(datosAlbum[3].trim());//43 minutos?
			String fechaLanAlbum[] = datosAlbum[4].trim().split("-");//{1968-04-04}
			Fecha fechaLan = convertirFecha(fechaLanAlbum);
			
			//Album nuevoAlbum = new Album(nombreAlbum,generoAlbum,rutaImagen,duracionAlbum,fechaLan);
			artistas.get(ultimo).agregarAlbum(nombreAlbum,generoAlbum,rutaImagen,duracionAlbum,fechaLan);
			
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
        System.out.print("El artista con mas albumes es: "+miColeccion.artistaMasAlbumes());
        /*si quieres puedes probar imprimir los datos que se cargaron para ver si funciona*/
    }

	
}
