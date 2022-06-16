
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.BadLocationException;
import vista.frmAnalizadorLexico;


public class ControladorAnalizadorLexico {
    private frmAnalizadorLexico vista;
    private String palabrasReservadas[]={"INICIOCONS","VFIN","FINCONS","ENTERO","REAL","LOG","KDENA","KRACTER",
        "read","write","yes","yesnt","consiguiente","recorrer","Loprincipal","INICIO","FIN","true","false","retornar","resumen","clase",
        "BUILD","Crear","declase","call","resi","and","or"};
    private int indice;
    private String cadena;
    private String buffer;
    
    public ControladorAnalizadorLexico(frmAnalizadorLexico vista) {
        this.vista = vista;

        

        System.out.println(buscar(cadena));

        vista.btnAnalizar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int t;
                boolean salir = true;
                cadena="";
                int lines = vista.txtEntrada.getLineCount();
                try {
                    for (int i = 0; i < lines; i++) {
                        int start = vista.txtEntrada.getLineStartOffset(i);
                        int end = vista.txtEntrada.getLineEndOffset(i);
                        cadena = cadena.concat(" ");
                        cadena = cadena.concat(vista.txtEntrada.getText(start, end-start)).trim();
                    }
                } catch (BadLocationException ex) {
                    System.out.println("Error al concatenar");
                }
                cadena = cadena + '@';
                int r = 0, i = 0,lineaError;
               
            }
        
        });
        
    }
    
    
    
    private int buscar(String x){
        for (int k = 0; k < 29; k++) 
            if (x.equals(palabrasReservadas[k])) return 110 + k;
        return 1000;
    }
    
    private int scanner(){
        buffer="";
        Character caracter;
        int i = 0, estado = 0;char a;String b ="";
        
        for(;;){
            caracter = cadena.charAt(indice);
            if(caracter=='@'&&i==0){
                return 0;
            }else {
                if(caracter=='@' && validar()){
                    switch(estado){
                        case 1: return buscar(buffer);  // identificador                         
                        case 2: return 300; // operador >           //entero return
                        case 3: return 225;// punto
                        case 4: return 223;//<                      //real return
                        case 5: return 234; //comilla simple    
                        case 6: return 215; //  
                        case 7: return 207;                         //caracter return 1000
                        case 8: return 206;                         //divisor return 209
                        case 9: return 208;                         //asignacionDivision return 217
                        case 10: return 911;//para mi 911           //coma return 237
                        case 11: return 210;                        //asignacion return 213
                        case 12: return 205;                        //igualdad return 219
                        case 13: return 212;                        //operadorMayor return 223
                        case 14: return 200;                        //operadorMayorIgual return 224
                        case 15: return 233;//puntoYComa
                        case 16: buffer=buffer+b; return buscar(buffer);//operadorMenor return 221
                        case 17: buffer=buffer+b; return buscar(buffer);//operadorMenorIgual return 222
                        case 18:
                        case 19: return 228; //llave abierta ->
                        case 20: 
                        case 21: return 229; //llave cerrada <-
                        case 22: return 200; //^ and logico
                        case 23: return 201; //v or logico
                        case 24: return 205; //~ negacion
                        case 25: return 220; //~= diferente a
                        case 26: return 208; //* multiplicacion
                        case 27: return 216; //*= asignacionMultiplicacion
                        case 28: return 225; //( parentesisAbierto
                        case 29: return 226; //) parentesisCerrado
                        case 30: //>
                        case 31://w
                        case 32: return 204; // >w< XOR logico
                        case 33: //^    
                        case 34: //_
                        case 35: return 202;//^_^ AND condicional
                        case 36: //v
                        case 37: //_
                        case 38: return 203;//v_v OR condicional
                        case 39: //$
                        case 40: //$
                        case 41: //*
                        case 42: //*
                        case 43: //RETURN COMENTARIO
                        case 44: return 207; //- resta
                        case 45: return 212; //-- decremento
                        case 46: return 215; //-= asignacion resta
                        case 47:
                        case 48:
                        case 49:
                        case 50:
                        case 51:
                        case 52:
                        case 53:
                        case 54:
                        case 55:
  
                        default: return 911;
                    } 
                }else{
                    buffer = buffer.trim();
                    switch(estado){
                        
                    }
                }
            }
        }
    }
    
    private boolean validar(){
        try {
            char caracter= cadena.charAt(indice+1);           
            return false;
        } catch (Exception StringIndexOutOfBoundsException) {
            return true;
        }
           
    }
    
    
    /**
     *Inicializa el frame
     */
    public void iniciar(){
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        vista.setResizable(false);
        vista.setTitle("Analizador Lexico");
        
    }


}
