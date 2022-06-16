
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
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
    
    DefaultTableModel dtm1;
    DefaultTableModel dtm2;
 
    public ControladorAnalizadorLexico(frmAnalizadorLexico vista) {
        
        this.dtm1 = new DefaultTableModel(){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        this.dtm2 = new DefaultTableModel(){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        
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
                        case 2: return 5300; // operador >           //entero return
                        case 3: return 225;// punto
                        case 4: return 5200;//<                      //real return
                        case 5: return 234; //comilla simple    
                        case 6: return 215; //  
                        case 7: return 5500;                         //caracter return 1000
                        case 8: return 209;                         //divisor return 209
                        case 9: return 217;                         //asignacionDivision return 217
                        case 10: return 237;           //coma return 237
                        case 11: return 213;                        //asignacion return 213
                        case 12: return 219;                        //igualdad return 219
                        case 13: return 223;                        //operadorMayor return 223
                        case 14: return 200;                        //w
                        case 15: return 204;                        // >w< XOR logico
                        case 16: return 224;//operadorMayorIgual 
                        case 17: return 233; //puntoYComa
                        case 18: return 221;                           //operadorMenor return 221
                        case 19: return 229; //llave cerrada <-
                        case 20: return 222; //operador <=
                        case 21: return 200; //^ and logico
                        case 22: return 666; //_
                        case 23: return 202; //^_^ AND condicional
                        case 24: return 201; //v logico
                        case 25: return 666; //_
                        case 26: return 203; //v_v OR condicional
                        case 27: return 205; // ~
                        case 28: return 220; //~= diferente a 
                        case 29: return 208; //* multiplicacion
                        case 30: return 216; //*= asignacion multiplicacion
                        case 31: return 230; //*$ cierre comentario
                        case 32: return 225; //parentesis abierto
                        case 33: return 226; //parentesis cerrado    
                        case 34: return 911;
                        case 35: return 231;// Entrada comentario
                        case 36: return 227;//Entrada comentario de parrafo
                        case 37: return 911;
                        case 38: return 911;
                        case 39: return 3000;//COMENTARIO
                        case 40: return 207; //- resta
                        case 41: return 212; //-- decremento
                        case 42: return 228; //-> llave abierta
                        case 43: return 215; //-= asignacion resta
                        case 44: return 210; //& modulo
                        case 45: return 218; //&= asignacion modulo
                        case 46: return 206; //+ suma
                        case 47: return 211; //++ incremento
                        case 48: return 214; //+= Asignacion suma
                        case 49: return 235; //" comilla doble
                        //case 50: //ct
                        case 51: return 666; // CADENA
                        case 52: return 236; // | Barra vertical
                        case 53: return 232; //. Punto
                        case 54: return 666; //REAL INICIADO EN PUNTO
                        
  
                        default: return 911;
                    } 
                }else{
                    buffer = buffer.trim();
                    switch(estado){
                        case 0: buffer = buffer + caracter;
                            if(Character.isLetter(caracter)){
                                estado = 1; i++;
                            }else if(Character.isWhitespace(caracter)==false){
                                switch(caracter){
                                    case '\'':estado=5; i++;break;
                                    case '/': estado=8; i++;break;
                                    case ',': estado=10; i++;break;
                                    case '=': estado=11; i++;break;
                                    case '>': estado=13; i++;break;
                                    case ';': estado=17; i++;break;
                                    case '<': estado=18; i++;break;
                                    case '^': estado=21; i++;break;
                                    case 'v': estado=24; i++;break;
                                    case '~': estado=27; i++;break;
                                    case '*': estado=29; i++;break;
                                    case '(': estado=32; i++;break;
                                    case ')': estado=33; i++;break;
                                    case '$': estado=34; i++;break;
                                    case '-': estado=40; i++;break;
                                    case '&': estado=44; i++;break;
                                    case '+': estado=46; i++;break;
                                    case '"': estado=49; i++;break;
                                    case '|': estado=52; i++;break;
                                    case '.': estado=53; i++;break;
                                    default: return 911;
                                }
                            }else estado = 0;
                        break;
                        case 1: if((Character.isDigit(caracter)) || (Character.isLetter(caracter)) ){
                                    buffer = buffer + caracter;
                                    estado = 1;
                                }else{return buscar(buffer);}
                                i++;
                                break;
                        case 2: if(Character.isDigit(caracter)){
                                    buffer = buffer + caracter;
                                    estado = 2;
                                }else{ if(caracter=='.'){
                                    buffer = buffer + caracter; estado = 3;
                                }else{return 5300;}}//entero
                                i++; 
                                break;
                        case 3: if(Character.isDigit(caracter)){
                                    buffer = buffer + caracter;
                                    estado = 4;

                                }else{
                                    buffer=buffer.substring(0,buffer.length()-1);indice++;
                                    return 5300;
                                }
                                i++;
                                break;
                        case 4: if(Character.isDigit(caracter)){
                                    buffer = buffer + caracter;
                                    estado = 4;
                                } else{return 5200;}
                                i++;
                                break;
                        case 5: if(caracter == '\''){
                                    buffer = buffer + caracter;
                                    estado = 7;
                                }else{
                                    buffer = buffer + caracter;
                                    estado = 6;
                                }
                                i++;
                                break;
                        case 6: if(caracter == '\''){
                                    buffer = buffer + caracter;
                                    estado = 7;
                                }else{
                                    return 911;
                                }
                                i++;
                                break;
                        case 7: return 5500;
                            
                        case 8: if(caracter == '='){
                                    buffer = buffer + caracter;
                                    estado = 9;
                                }else{
                                    return 209;
                                }
                                i++;
                                break;
                        case 9: return 217;
                        case 10: return 237;
                        case 11: if(caracter == '='){
                                    buffer = buffer + caracter;
                                    estado = 12;
                                }else return 213;
                                i++;
                                break;
                        case 12: return 219;
                        case 13: if(caracter == 'w'){
                                    buffer = buffer + caracter;
                                    estado = 14;
                                }else if(caracter == '='){
                                    buffer = buffer + caracter;
                                    estado = 16;
                                }
                                i++;
                                break;
                        case 14: if(caracter == '<'){
                                    buffer = buffer + caracter;
                                    estado = 15;
                                }
                                i++;
                                break;
                        case 15: return 204;
                        case 16: return 224;
                        case 17: return 233;
                        case 18: if(caracter == '-'){
                                    buffer = buffer + caracter;
                                    estado = 19;
                                }else if(caracter == '='){
                                    buffer = buffer + caracter;
                                    estado = 20;
                                }
                        case 19: return 229;
                        case 20: return 222;
                        case 21: if(caracter == '_'){
                                    buffer = buffer + caracter;
                                    estado = 22;
                                }else{
                                    return 200;
                                }
                                i++;
                                break;
                        case 22: if(caracter == '^'){
                                    buffer = buffer + caracter;
                                    estado = 23;
                                }i++;
                                break;
                        case 23: return 202;
                        case 24:if (caracter == '_') {
                                    buffer = buffer + caracter;
                                    estado = 25;
                                }else if(Character.isDigit(caracter) || Character.isLetter(caracter)){
                                    buffer = buffer + caracter;
                                    estado = 2;
                                }
                                i++;
                                break;
                        case 25: if(caracter =='v'){
                                    buffer = buffer + caracter;
                                    estado = 26;
                                }else{
                                    return 911;
                                }
                                i++;
                                break;
                        case 26: return 203;
                        case 27: if(caracter == '='){
                                    buffer = buffer + caracter;
                                    estado = 28;
                                }else{
                                    return 205;
                                }
                                i++;
                                break;
                        case 28: return 220;
                        case 29: if(caracter=='='){
                                    buffer = buffer + caracter;
                                    estado = 30;
                                }else if(caracter == '$'){
                                    buffer = buffer +caracter;
                                    estado = 31;
                                }else{
                                    return 208;
                                }
                                i++;
                                break;
                        case 30: return 216;
                        case 31: return 230;
                        case 32: return 225;
                        case 33: return 226;
                        case 34: switch (caracter) {
                                    case '$':
                                        buffer = buffer + caracter;
                                        estado = 35;
                                        break;
                                    case '*':
                                        buffer = buffer + caracter;
                                        estado = 36;
                                        break;
                                    default:
                                        return 34;
                                }
                                i++;
                                break;
                        case 35: return 231;
                        case 36: if(caracter == '*'){
                                    buffer = buffer + caracter;
                                    estado = 38;
                                }else{
                                    buffer = buffer + caracter;
                                    estado = 37;
                                }i++;
                                break;
                        case 37: if(caracter =='*'){
                                    buffer = buffer + caracter;
                                    estado = 38;
                                }else estado = 37;
                                i++;
                                break;
                        case 38: if(caracter == '$'){
                                    buffer = buffer + caracter;
                                    estado = 39;
                                }else estado = 37;
                                i++;
                                break;
                        case 39: return 4900; //COMENTARIO PARRAFO
                        case 40: switch (caracter) {
                                    case '-':
                                        buffer = buffer + caracter;
                                        estado = 41;
                                        break;
                                    case '>':
                                        buffer = buffer + caracter;
                                        estado = 42;
                                        break;
                                    case '=':
                                        buffer = buffer + caracter;
                                        estado = 43;
                                        break;
                                    default:
                                        return 207;
                                }
                                i++;
                                break;
                        case 41: return 212;
                        case 42: return 228;
                        case 43: return 215;
                        case 44: if(caracter =='='){
                                    buffer= buffer + caracter;
                                    estado = 45;
                                }else return 210;
                                i++;
                                break;
                        case 45: return 218;
                        case 46: if(caracter == '+'){
                                    buffer = buffer + caracter;
                                    estado = 47;
                                }else if(caracter == '='){
                                    buffer = buffer + caracter;
                                    estado = 48;
                                }else return 206;
                        case 47: return 211;
                        case 48: return 214;
                        case 49: if(caracter=='\"'){
                                   buffer=buffer+caracter;estado=51;
                                     }else {buffer=buffer+estado;
                                 estado=49;}
                                i++;
                                break; 
                        case 51: return 5400;
                        case 52: return 236;
                        case 53: if(Character.isDigit(caracter)){
                                    buffer = buffer + caracter;
                                    estado = 54;
                                }
                                i++;
                                break;
                        case 54: if(Character.isDigit(caracter)){
                                    buffer = buffer + caracter;
                                    estado = 54;
                                }else return 5100;
                                i++;
                                break;
                        
                            
                    }
                    indice++;
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
    
    private void indice_tabla1(){   
        String [] titulo = new String []{"Tokem", "Descripcion", "Lexema"};  
        
        dtm1.setColumnIdentifiers(titulo); //Asigna los valores a la tabla
        dtm2.setColumnIdentifiers(titulo); //Asigna los valores a la tabla
        
        vista.tblReconocimiento.setModel(dtm1); 
        vista.tblSimbolos.setModel(dtm2); 
    }   
    private void limpiar(){      
        int filas=dtm1.getRowCount();//nos da la cantidad de fila
        for (int i = 0; i < filas; i++) {
            dtm1.removeRow(0);
        }
        int filas2=dtm2.getRowCount();//nos da la cantidad de fila
        for (int i = 0; i < filas2; i++) {
            dtm2.removeRow(0);
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
