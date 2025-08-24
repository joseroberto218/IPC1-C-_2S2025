package com.mycompany.actividad5lab;

import java.util.Scanner;

public class Actividad5lab {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int opcion = 0;
            
            do {
                System.out.println("MENU PRINCIPAL");
                System.out.println("1. CON try-catch");
                System.out.println("2. SIN try-catch");
                System.out.println("3. SALIR");
                System.out.print("Seleccione una opcion: ");
                
                try {
                    opcion = sc.nextInt();
                } catch (Exception e) {
                    System.out.println("Debe ingresar un numero.");
                    sc.nextLine();
                    continue; // vuelve a mostrar el menú
                }
                
                switch (opcion) {
                    case 1:
                        // Opción con try-catch
                        System.out.println("PROGRAMA DE DIVISION :D");
                        try {
                            System.out.print("Ingrese numerador: ");
                            int num = sc.nextInt();
                            System.out.print("Ingrese denominador: ");
                            int den = sc.nextInt();
                            
                            int resultado = num / den;
                            System.out.println("Resultado: " + resultado);
                        } catch (Exception e) {
                            System.out.println("ERROR: " + e.getMessage());
                            System.out.println("INTENTELO DE NUEVO");
                        }
                        break;
                        
                    case 2:
                        // Opción sin try-catch
                        System.out.println("PROGRAMA DE DIVISION D:");
                        System.out.print("Ingrese numerador: ");
                        int num = sc.nextInt();
                        System.out.print("Ingrese denominador: ");
                        int den = sc.nextInt();

                        // Aquí no hay try-catch
                        int resultado = num / den;
                        System.out.println("Resultado: " + resultado);
                        break;
                        
                    case 3:
                        System.out.println("INTENTELO MAS TARDE");
                        break;
                        
                    default:
                        System.out.println("INGRESE DEL OPCIONES DEL 1-3 :)");
                }
            } while (opcion != 3);
        }
    }
}
