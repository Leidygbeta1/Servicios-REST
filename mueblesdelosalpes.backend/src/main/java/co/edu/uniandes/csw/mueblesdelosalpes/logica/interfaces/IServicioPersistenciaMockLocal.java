/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ IServicioPersistenciaMockLocal.java
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Licenciado bajo el esquema Academic Free License version 3.0
 *
 * Ejercicio: Muebles de los Alpes
 * Autor: Juan Sebastián Urrego
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package co.edu.uniandes.csw.mueblesdelosalpes.logica.interfaces;




import co.edu.uniandes.csw.mueblesdelosalpes.excepciones.OperacionInvalidaException;
import java.util.List;

/**
 * Contrato funcional de los servicios de persistencia de manera local.
 */
public interface IServicioPersistenciaMockLocal
{
    /**
     * Crea un objeto dentro de la persistencia del sistema.
     */
    public void create(Object obj) throws OperacionInvalidaException;

    /**
     * Modifica un objeto dentro de la persistencia del sistema.
     */
    public void update(Object obj);

    /**
     * Elimina un objeto dentro de la persistencia del sistema.
     */
    public void delete(Object obj) throws OperacionInvalidaException;

    /**
     * Devuelve una lista con todos los elementos de una clase dada.
     */
    public <T> List<T> findAll(Class<T> c);

    /**
     * Retorna la instancia de una entidad dado un identificador y la clase de la entidad.
     */
    public <T> T findById(Class<T> c, Object id);
}

