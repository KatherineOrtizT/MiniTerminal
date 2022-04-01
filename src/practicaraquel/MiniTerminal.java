/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practicaraquel;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DAW
 */
public class MiniTerminal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int opcion = 0;
        Scanner leer = new Scanner(System.in);
        MiniFileManager e = new MiniFileManager();
        do {
            System.out.print(">");
            String comando = leer.nextLine();

            if (comando.contains(" ")) {
                String[] parts = comando.split("\\s+");
                String part1 = parts[0];
                String part2 = parts[1];

                switch (part1) {
                    case "cd":
                        System.out.println(e.changeDir(part2));
                        break;
                    case "mkdir":
                        e.mkdir(part2);
                        break;
                    case "rm":
                        e.rm(part2);
                        break;
                    case "mv":
                        String part3 = parts[2];
                        e.mv(part2, part3);
                        break;
                    case "info":
                    {
                        try {
                            e.info(part2);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                        break;

                    default:
                        System.out.println(part1 + " No se reconoce como un comando interno o externo");
                        break;
                }

            } else {
                switch (comando) {
                    case "pwd":
                        System.out.println(e.getPWD());
                        break;
                    case "ls":
                        e.printList(false);
                        break;
                    case "ll":
                        e.printList(true);
                        break;
                    case "help":
                        e.help();
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
