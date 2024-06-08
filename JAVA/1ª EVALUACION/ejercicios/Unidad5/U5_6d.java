/*Haz que la salida del programa anterior se muestre como una cadena de números separados por coma. 
Ejemplo:
Escribe un número:17
2, 4, 6, 8, 10, 12, 14, 16*/
package ejercicios.Unidad5;

import java.util.Scanner;

public class U5_6d {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Dime un número: ");
        int num = in.nextInt();

        String pares = "";
        for (int i = 1; i < num; i++) {
            if (i == 4) {
                continue;
            }
            if (i % 2 == 0) {
                pares += i + ", ";
            }
        }
        pares = pares.substring(0, pares.length() - 2);

        System.out.println(pares);

        in.close();
    }
}