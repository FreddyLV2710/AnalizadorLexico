/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package app;

import controlador.ControladorAnalizadorLexico;
import vista.frmAnalizadorLexico;

/**
 *
 * @author rodri
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        frmAnalizadorLexico frame = new frmAnalizadorLexico();
        ControladorAnalizadorLexico controlador = new ControladorAnalizadorLexico(frame);
        controlador.iniciar();
            int array[] = {1, 2, 3, 4};
    for (int i = 0; i < array.length; i++) {
        System.out.print(array[i]);
    }
    }
    
}
