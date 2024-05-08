package Twitter;

import java.util.Scanner;

public class Utils {
    private static final Scanner scanner = new Scanner(System.in);

    public static String leerCadena() {
        System.out.println("Ingrese un texto:");
        return scanner.nextLine();
    }

    public static int leerEntero() {
        System.out.println("Ingrese un número entero:");
        while (!scanner.hasNextInt()) {
            scanner.next(); // descarta la entrada incorrecta
            System.out.println("No es un número entero válido. Intente de nuevo:");
        }
        return scanner.nextInt();
    }

    public static float leerFloat() {
        System.out.println("Ingrese un número flotante:");
        while (!scanner.hasNextFloat()) {
            scanner.next(); // descarta la entrada incorrecta
            System.out.println("No es un número flotante válido. Intente de nuevo:");
        }
        return scanner.nextFloat();
    }

    public static double leerDouble() {
        System.out.println("Ingrese un número doble:");
        while (!scanner.hasNextDouble()) {
            scanner.next(); // descarta la entrada incorrecta
            System.out.println("No es un número doble válido. Intente de nuevo:");
        }
        return scanner.nextDouble();
    }

    public static boolean isValidEmail(String email) {
        return email != null && email.contains("@");
    }

    public static boolean isValidAlias(String alias) {
        return alias != null && alias.matches("[a-zA-Z0-9]+");
    }
}



