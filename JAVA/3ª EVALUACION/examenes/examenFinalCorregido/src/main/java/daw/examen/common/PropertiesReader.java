package daw.examen.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

    // Atributos
    private static PropertiesReader instance;
    private final Properties properties = new Properties();

    // Constructor
    private PropertiesReader() {
        loadProperties("application.properties"); // Cargar las propiedades por defecto sino devolverá null
        loadProfileProperties(); // Cargar las propiedades del perfil
    }

    // Método para cargar las propiedades del perfil
    private void loadProfileProperties() {

        // Detectar el perfil y cargar las propiedades correspondientes
        String activeProfile = getProperty("spring.profiles.active");

        if (activeProfile == null) {
            throw new RuntimeException("\u001B[31m" + "No se ha especificado un perfil activo" + "\u001B[0m");
        } else {
            System.out.println("Perfil activo: " + activeProfile);
            loadProperties("application-" + activeProfile + ".properties");
        }
    }

    // Método para cargar las propiedades
    private void loadProperties(String filename) {

        try (InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename)) {
            if (input == null) {
                throw new RuntimeException("\u001B[31m" + "No se ha podido encontrar el fichero de configuración: "
                        + filename + "\u001B[0m");
            }
            properties.load(input); // Cargar las propiedades desde el archivo de configuración

        } catch (IOException e) {
            throw new RuntimeException("\u001B[31m" + "Error loading application.properties file" + "\u001B[0m");
        }
    }

    // Método para obtener una propiedad
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    // Método para obtener la instancia del Singleton con doble comprobación de nulidad.
    public static PropertiesReader getInstance() {
        if (instance == null) {
            synchronized (PropertiesReader.class) {
                if (instance == null) {
                    instance = new PropertiesReader();
                }
            }
        }
        return instance;
    }
}