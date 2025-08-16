package com.mycompany.practica1_202200030;


import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Practica1_202200030 {
    
    static class avatar {
        int id;
        String name;
        String wpn;
        ArrayList<String> strgh;
        int lvlpwr;
        
        avatar(int id, String name, String wpn, ArrayList<String> strgh, int lvlpwr){
            this.id = id;
            this.name = name;
            this.wpn = wpn;
            this.strgh = strgh;
            this.lvlpwr = lvlpwr;
        }
    }
    
    static class fght {
        int fght1, fght2;
        String namep1, namep2;
        String date;
        
        fght(int fght1, String namep1, int fght2, String namep2, String date){
           this.fght1 = fght1;
           this.namep1 = namep1;
           this.fght2 = fght2;
           this.namep2 = namep2;
           this.date = date;
        }
    }
    
    static ArrayList<avatar> Peleadores = new ArrayList<>();
    static ArrayList<fght> Peleas = new ArrayList<>();
    static int nxtid = 1;
    static Scanner scanner = new Scanner(System.in);
    
     public static void main(String[] args){
         int opcion;

         
         do {
            System.out.println("BIENVENIDOS AL MENU DE GESTION DE PERSONAJES :D");
            System.out.println("Seleccione una opcion:");
            System.out.println("1. Agregar Personaje");
            System.out.println("2. Modificar Personaje");
            System.out.println("3. Eliminar Personaje");
            System.out.println("4. Ver Datos de un Personaje");
            System.out.println("5. Ver Listado de Personajes");
            System.out.println("6. Realizar Pelea entre Personajes");
            System.out.println("7. Ver Historial de Peleas");
            System.out.println("8. Ver Datos del Estudiante");
            System.out.println("9. Salir");

            opcion = sinleer("Elige una opcion:");
              //opcion = scanner.nextInt();
         switch (opcion){
             case 1 -> agregarp();
            case 2 -> modificarp();
            case 3 -> eliminarp();
            case 4 -> verp();
            case 5 -> verlistap();
            case 6 -> progfght();
            case 7 -> historialfght();
            case 8 -> datosmi();
            case 9 -> {
                 }
            default -> System.out.println("OPCION INCORRECTA, INTENTE DE NUEVO!!");
            }
         } while(opcion != 9);
         
         System.out.println("Gracias por visitarnos!");
       
     }
     
   static int leer(String msj, int min, int max){
       while (true){
           System.out.print(msj);
           String line = scanner.nextLine().trim();
           try{
               int pwr = Integer.parseInt(line);
               if (pwr < min || pwr > max){
                    System.out.printf("Ingrese un numero entre %d y %d.%n", min, max);
                    continue;
               }
               return pwr;
           } catch (NumberFormatException e){
               System.out.println("RANGO INVALIDO, INTENTE DE NUEVO!!");
           }
       }  
   }
   
   static int sinleer(String msj) {
       while (true){
           System.out.println(msj);
           String line = scanner.nextLine().trim();
           try{
               return Integer.parseInt(line);
           } catch (NumberFormatException e){
              System.out.println("INVALIDO, INTENTE DE NUEVO");
           }
       }
   }
   
   static String nottxt (String msj){
       while (true){
           System.out.println(msj);
           String line = scanner.nextLine().trim();
           if (!line.isEmpty())return line;
           System.out.println("LLENE EL ESPACIO EN BLANCO");
       }
   }
   
   static avatar buscarid(int id){
       for (avatar p : Peleadores) if (p.id == id) return p;
       return null;
   }
   
   static avatar buscarname(String name){
       for (avatar p : Peleadores) if (p.name.equalsIgnoreCase(name)) return p;
       return null;
   }
   
   static int indcid(int id){
       for (int i = 0; i < Peleadores.size(); i++) if (Peleadores.get(i).id == id) return i;
       return -1;
   }
   
   static boolean nameeql(String name) {
       return buscarname(name) != null;
   }
   
  static void agregarp() {
      String name = nottxt("INGRESA EL NOMBRE UNICO DE TU PERSONAJE: ");
      if (nameeql(name)) {
          System.out.println("YA EXISTE EL NOMBRE :c, INTENTA CON OTRO!");
          return;
      }
      
      String wpn = nottxt("ESCOGE TU ARMA: ");
      int nstrgh = leer("DE ENTRE 0-5, CUANTAS HABILIDADES TIENE TU PELEADOR? ", 0, 5);
      ArrayList<String> strgh = new ArrayList<>();
      for (int i = 0; i < nstrgh; i++) {
          strgh.add(nottxt("Habilidad de  " + (i + 1)+ ": "));
      }
      int lvlpwr = leer("DE 1-100, CUANTO NIVEL TIENE TU PERSONAJE? ", 1, 100);
      
      avatar p = new avatar(nxtid++, name, wpn, strgh, lvlpwr);
      Peleadores.add(p);
      System.out.println("PERSONAJE AGREGADO CON EXITO :D. Su ID es: " + p.id);   
  } 
  
  static void modificarp(){
      System.out.println("SELECCIONE COMO DESEA BUSCAR A TU PELEADOR: 1) POR ID 2) POR NOMBRE)");
      int b = leer("OPCION? ", 1, 2);
      avatar p = null;
      if (b == 1){
          int id = sinleer("ID: ");
          p = buscarid(id);
      } else {
          String name = nottxt("NOMBRE: ");
          p = buscarname(name);
      }
      if (p == null){
          System.out.println("NO SE ENCONTRO TU PERSONAJE, INTENTALO DE NUEVO");
          return;
      }
      mostrarp(p);
      
      System.out.println("INGRESE NUEVA ARMA (ENTER si no quieres cambiar): ");
      String wpn = scanner.nextLine().trim();
      if (!wpn.isEmpty()) p.wpn = wpn;
      
      System.out.println("DESEAS CAMBIAR HABILIDADES? (s/n) ");
      String ans = scanner.nextLine().trim();
      if (ans.equalsIgnoreCase("s") || ans.equalsIgnoreCase("si")){
          int nstrgh = leer("DE 0-5 CUANTAS HABILIDADES DESEA CAMBIAR? ", 0, 5);
          ArrayList<String> hab = new ArrayList<>();
          for (int i = 0; i < nstrgh; i++) hab.add(nottxt("Habilidad de  " + (i+1) + ": ")); 
          p.strgh = hab;
      }
      System.out.println("INGRESE NUEVO NIVEL DE PODER o ENTER para mantener("+p.lvlpwr+"): ");
      String nlvlpwr = scanner.nextLine().trim();
      if (!nlvlpwr.isEmpty()){
          try{
              int nlvl = Integer.parseInt(nlvlpwr);
              if (nlvl >= 1 && nlvl <= 100) p.lvlpwr = nlvl;
              else System.out.println("DEMASIADO ROTO :/, SE MANTIENE EL PODER ACTUAL.");
          } catch(NumberFormatException e){
              System.out.println("INVALIDO, SE MANTIENE PODER ACTUAL");
          }
      }
      System.out.println("PERSONAJE MODIFICADO c:");     
  }
  
  static void eliminarp(){
      int id = sinleer("INGRESA ID DEL PERSONAJE QUE DESEAS ELIMINIAR: ");
      int elimid = indcid(id);
      if (elimid == -1){
          System.out.println("ID NO ENCONTRADO, REVISA BIEN EL ID!!");
          return;
      }
      
      avatar p = Peleadores.get(elimid);
      System.out.printf("CONFIRMAR QUE LO ELIMINARAS AL PERSONAJE CON ID=%d y NOMBRE=%s (s/n): ", p.id, p.name);
      String a = scanner.nextLine().trim();
      if (a.equalsIgnoreCase("s") || a.equalsIgnoreCase("si")){
          Peleadores.remove(elimid);
          System.out.println("PERSONAJE ELIMINADO ;)");
      } else {
          System.out.println("ELIMINACION CANCELADA");
      } 
  }
  
  static void verp(){
      int id = sinleer("INGRESA ID DEL PELEADOR >:)");
      avatar p = buscarid(id);
      if (p == null){
          System.out.println("NO SE ENCONTRO :/");
          return;
      }
      mostrarp(p);
  }
  
  static void mostrarp(avatar p){
      System.out.println("-LOS DATOS DEL PERSONAJE-");
      System.out.println("ID: " + p.id);
      System.out.println("NOMBRE: " + p.name);
      System.out.println("ARMAS: " + p.wpn);
      System.out.println("NIVEL DE PODER: " + p.lvlpwr);
      System.out.println("HABILIDADES: ");
      
      if (p.strgh == null || p.strgh.isEmpty()){
          System.out.println("NINGUNA :c");
      } else {
          for (int i = 0; i < p.strgh.size(); i++){
              System.out.println(" " + (i + 1) + ". " + p.strgh.get(i));
          }
      }
  }

  static void verlistap(){
      if (Peleadores.isEmpty()){
          System.out.println("NO HAY PERSONAJE REGISTRADO :c");
          return;
      }
      System.out.println("ID | NOMBRE | NIVEL");
      for (avatar p : Peleadores){
          System.out.printf("ID: %d | %s | %d%n", p.id, p.name, p.lvlpwr);
      }
  }
  
  static void progfght(){
      if (Peleadores.size() < 2){
          System.out.println("RECUERDA. SON 2 PELEADORES :)");
          return;
      }
      
      int idp1 = sinleer("ID DEL PRIMER PELEADOR >:)");
      int idp2 = sinleer("ID DEL SEGUNDO PELEADOR >:)");
      if (idp1 == idp2){
          System.out.println("No somos smash para pelear con el mismo peleador jeje, INGRESA OTRO");
          return;
      }
      
      avatar p1 = buscarid(idp1);
      avatar p2 = buscarid(idp2);
      if (p1 == null || p2 == null){
          System.out.println("REVISA LOS ID'S, porque no coinciden");
          return;
      }
      String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
      Peleas.add(new fght(p1.id, p1.name, p2.id, p2.name, date));
      System.out.printf("PELEA REGISTRADA: %s (ID: %d) VS %s (ID: %d) [%s]%n", p1.name, p1.id, p2.name, p2.id, date);
  }
  
  static void historialfght(){
      if (Peleas.isEmpty()){
          System.out.println("NO HAY PELEAS REGISTRADAS :c");
          return;
      }
      
      System.out.println("HISTORIAL DE PELEAS >:)");
      for (int i = 0; i < Peleas.size(); i++){
          fght pl = Peleas.get(i);
          System.out.printf("%d) %s (ID: %d) VS %s (ID: %d) - %s%n",
            i + 1, pl.namep1, pl.fght1, pl.namep2, pl.fght2, pl.date);
      }
  }
  
   static void datosmi(){
       System.out.println("Nombre completo: Jose Roberto Orozco Orozco");
       System.out.println("CUI: 3304741891202");
       System.out.println("Carnet: 202200030");
       System.out.println("Curso: IPC1");
       System.out.println("Seccion: C");
   } 
}
