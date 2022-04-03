/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practicaraquel;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Mini terminal con diferentes funciones, se puede usar con rutas relativas o
 * rutas absolutas se usa las mismas barras que en la terminal de linux
 *
 * @author Raquel Tapia Ortiz
 */
public class MiniTerminal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int opcion = 0;
        Scanner leer = new Scanner(System.in);
        // creamos un MiniFileManager prueba para probar todas las funciones del sistema
        MiniFileManager prueba = new MiniFileManager();
        // se repetira el proyecto hasta que el usuario elija la opcion exit
        do {
            System.out.print(">");
            String comando = leer.nextLine();
            // Los comandos se dividen en dos los simples y los que tienen un espacio mas un argumento
            if (comando.contains(" ")) {
                String[] parts = comando.split("\\s+"); // separamos el comando del argumento
                String part1 = parts[0];
                String part2 = parts[1];

                switch (part1) {
                    case "cd":
                        System.out.println(prueba.changeDir(part2));
                        break;
                    case "mkdir":
                        prueba.mkdir(part2);
                        break;
                    case "rm":
                        prueba.rm(part2);
                        break;
                    case "mv":
                        String part3 = parts[2];
                        prueba.mv(part2, part3);
                        break;
                    case "info":                  
                        try {
                        prueba.info(part2);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        break;
                    default:
                        System.out.println(part1 + " No se reconoce como un comando interno o externo");
                        break;
                }

            } else {
                switch (comando) {
                    case "pwd":
                        System.out.println(prueba.getPWD());
                        break;
                    case "ls":
                        prueba.printList(false);
                        break;
                    case "ll":
                        prueba.printList(true);
                        break;
                    case "help":
                        prueba.help();
                        break;
                    case "exit":
                        opcion = 1;
                        break;
                    default:
                        System.out.println(comando + " No se reconoce como un comando interno o externo");
                        break;
                }
            }

        } while (opcion != 1);
    }

}
