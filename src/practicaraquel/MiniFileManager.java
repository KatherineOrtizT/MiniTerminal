/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practicaraquel;

import java.io.File;
import java.util.Date;

/**
 *
 * @author Raquel Tapia Oriz
 */
public class MiniFileManager {

    /**
     * File con el directorio donde vamos a trabajar
     */
    private File file = new File("");

    /**
     * El comando info recibe como argumento una ruta y muestra por pantalla el
     * tamaño en bytes y megas de la ruta, así como la fecha de su última
     * modificación.
     *
     * @param argumento del que vamos a imprimir la informacion
     * @throws Exception cuando no existe el archivo
     */
    public void info(String argumento) throws Exception {
        String ruta = creaRuta(argumento);
        File fileInfo = new File(ruta);   //creamos el file que necesitamos la informacion
        long bytes = 0;
        if (fileInfo.exists()) {
            if (fileInfo.isFile()) {
                Date fecha = new Date(fileInfo.lastModified());
                bytes = fileInfo.length();
                long mb = conversor(bytes);   //pasar bytes a MB
                System.out.println(fileInfo.getName() + " Tamaño " + bytes + " bytes " + mb + " M " + fecha);
            } else {
                Date fecha = new Date(fileInfo.lastModified());
                bytes = calcularTamaño(fileInfo); //en el caso de las carpetas necesitamos toda lo informacion del interior
                long mb = conversor(bytes);
                System.out.println(fileInfo.getName() + " Tamaño " + bytes + " bytes " + mb + " M " + fecha);
            }

        } else {
            throw new Exception("Ruta o Nombre incorrecto");
        }

    }

    /**
     *Conversor de Bytes
     * @param bytes que pasamos a KB o MB
     * @return la cantidad en MB
     */
    public long conversor(long bytes) {
        long kb = 0, mb = 0;
        kb = bytes / 1024;
        mb = kb / 1024;
        return mb;
    }

    /**
     * Imprime toda la informacion de los comandos disponibles
     */
    public void help() {
        System.out.println(" pwd: Muestra cual es la carpeta actual.\n"
                + "cd: Cambia la carpeta actual a ‘DIR’. Con .. cambia a la carpeta superior.\n"
                + "ls: Muestra la lista de directorios y archivos de la carpeta actual (primero directorios, luego\n"
                + "archivos, ambos ordenados alfabéticamente).\n"
                + "ll: Muestra la lista de directorios y archivos además el tamaño y la fecha de última modificación.\n"
                + "mkdir <DIR>: Crea el directorio ‘DIR’ en la carpeta actual.\n"
                + "rm <FILE>: Borra ‘FILE’. Si es una carpeta, borrará primero sus archivos y luego la carpeta. Si\n"
                + "tiene subcarpetas, las dejará intactas y mostrará un aviso al usuario.\n"
                + "mv <FILE1> <FILE2>: Mueve o renombra ‘FILE1’ a ‘FILE2’.\n"
                + "exit: Termina el programa.");
    }

    /**
     * Da la ruta del directorio donde estas ubicado
     *
     * @return String con la ruta del directorio donde estas ubicado
     */
    public String getPWD() {
        return file.getAbsolutePath();
    }

    /**
     * Sirve para cambiar la ruta de donde estamos trabajando al directorio
     * superior con ".." o al directorio que le indiques con la ruta absoluta o
     * relativa
     *
     * @param part2 El String que distingue a que ruta cambiamos
     * @return true en el caso de cambiar la ruta y false en el caso de que no
     * se haga ninguin cambio
     */
    public boolean changeDir(String part2) {
        String ruta;
        if (part2.equals("..")) {
            ruta = file.getParentFile().getAbsolutePath();
        } else {
            ruta = creaRuta(part2);
        }
        File fileNuevo = new File(ruta);
        if (fileNuevo.exists() && fileNuevo.isDirectory()) {
            file = fileNuevo;
            return true;
        }

        return false;
    }

    /**
     * Te indica la lista de directorios y ficheros de donde estas ubicado con
     * un boolean dice si te imprime solo nombre o nombre, tamaño y fecha de la
     * ultima modficacion
     *
     * @param info un true si va a imprimir toda la informacion Nombre, tamaño y
     * fecha de la ultima modificacion False imprime solo el nombre
     */
    public void printList(boolean info) {
        String ruta = file.getAbsolutePath();
        file = new File(ruta);
        if (info) {
            String[] lista = file.list();
            for (int i = 0; i < lista.length; i++) {
                Date fecha = new Date(file.lastModified());
                System.out.println(file.length() + " " + fecha + " " + lista[i]);
            }
        } else {
            String[] lista = file.list();
            for (int i = 0; i < lista.length; i++) {
                System.out.println(lista[i]);
            }
        }
    }

    /**
     * Crea el directorio ‘DIR’ en la carpeta actual.
     *
     * @param part2 El String el nombre del Directorio/Fichero que vamos a crear
     */
    public void mkdir(String part2) {

        File fileCreado = new File(creaRuta(part2));
        if (fileCreado.exists()) {
            System.out.println("Ya existe");
        } else {
            boolean comprobar = fileCreado.mkdir();
            System.out.println(comprobar);
        }

    }

    /**
     * Borra ‘FILE’. Si es una carpeta, borrará primero sus archivos y luego la
     * carpeta. Si tiene subcarpetas, las dejará intactas y mostrará un aviso al
     * usuario.
     *
     * @param part2 El String el nombre del Directorio/Fichero que vamos a
     * borrar
     */
    public void rm(String part2) {
        File fileBorrar = new File(creaRuta(part2));
        boolean comprueba = true;
        if (fileBorrar.listFiles().length != 0) {
            for (File i : fileBorrar.listFiles()) {
                if (i.isDirectory()) {
                    comprueba = false;
                }
            }
            if (comprueba) {
                File borrarArchivo[] = fileBorrar.listFiles();//CREA UN ITERADOR
                for (int i = 0; i < fileBorrar.listFiles().length; i++) {
                    borrarArchivo[i].delete();
                }
                fileBorrar.delete();
            }

        } else if (fileBorrar.delete()) {
            System.out.println("borrado");
        } else {
            System.out.println("ERROR");
        }

    }

    /**
     * Mueve o renombra ‘FILE1’ a ‘FILE2’
     *
     * @param antes El String del nombre del Directorio/Fichero que vamos a
     * mover o renombrar
     * @param despues El String con el nombre nuevo o ubicacion del
     * Directorio/Fichero
     */
    public void mv(String antes, String despues) {
        File antiguo = new File(creaRuta(antes));
        File nuevo = new File(creaRuta(despues));
        if (antiguo.exists()) {
            boolean cambioF = antiguo.renameTo(nuevo);
            System.out.println(cambioF);
        }
    }

    /**
     * Te permite distinguir si es una ruta absoluta o relativa en caso de que
     * no sea absoluta la crea y la envia en forma de cadena de texto
     *
     * @param part2 El String que distingue la ruta
     * @return Un String con la ruta absoluta
     */
    public String creaRuta(String part2) {
        String ruta;
        if (part2.contains("/")) {
            ruta = part2;
        } else {
            ruta = (file.getAbsolutePath() + "/" + part2);
        }
        return ruta;
    }
    /**
     * Calculamos el tamaño de toda la carpeta y sus subcarpetas
     * @param directorio para calcular tamaño
     * @return tamaño en Bytes de la carpeta 
     */
    public long calcularTamaño(File directorio) {
        long tamanio = 0;
        if (directorio.isDirectory()) {
            for (File i : directorio.listFiles()) {
                if (i.isFile()) {
                    tamanio += i.length();
                } else {
                    tamanio += calcularTamaño(i);
                }
            }
        }
        return tamanio;
    }

}
