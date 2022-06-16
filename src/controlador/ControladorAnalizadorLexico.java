
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import vista.frmAnalizadorLexico;


public class ControladorAnalizadorLexico {
    private frmAnalizadorLexico vista;
    private String palabrasReservadas[]={"empezeira","inteira", "fluat", "double", "car", "corriente", "ante", "cuerpinho", 
        "ats_leitura", "ats_escrever", "se", "senao", "acordo", "caso", "predefinizao", "encuanto", 
        "fazer", "por", "vazio", "retorno", "public", "private", "class","this"};
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
        
        vista.btnAnalizar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiar();
                int token;
                indice = 0;
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
                    System.err.println("Error al concatenar");
                }
                cadena = cadena + '@';
                int r = 0, i = 0,lineaError;
                do{
                    token = scanner();
                    switch(token){
                        case 0: vista.txtResultado.setText("Finalizo el programa "); salir = false;break;
                        
                        case 4800: dtm1.addRow(new String[]{"4800","id",buffer}); dtm2.addRow(new String[]{"4800","id",buffer});break;
                        
                        case 5400: dtm1.addRow(new String[]{"5400", "Cadena", buffer}); break;
                        
                        case 3000: dtm1.addRow(new String[]{"3000", "Comentario", buffer}); break;
                        
                        case 5500: dtm1.addRow(new String[]{"5500", "Caracter", buffer}); break;
                        
                        case 5200: dtm1.addRow(new String[]{"5200", "Real", buffer}); break;
                        
                        case 5300: dtm1.addRow(new String[]{"5300", "Entero", buffer}); break;
                        
                        case 5100: dtm1.addRow(new String[]{"5100", "Real", buffer}); break;
                        
                        case 120: dtm1.addRow(new String[]{"120", "Pal.Reservada", buffer}); break;

                        case 121: dtm1.addRow(new String[]{"121", "Pal.Reservada", buffer}); break;

                        case 122: dtm1.addRow(new String[]{"122", "Pal.Reservada", buffer}); break;

                        case 123: dtm1.addRow(new String[]{"123", "Pal.Reservada", buffer}); break;

                        case 124: dtm1.addRow(new String[]{"124", "Pal.Reservada", buffer}); break;

                        case 125: dtm1.addRow(new String[]{"125", "Pal.Reservada", buffer}); break;

                        case 126: dtm1.addRow(new String[]{"126", "Pal.Reservada", buffer}); break;

                        case 127: dtm1.addRow(new String[]{"127", "Pal.Reservada", buffer}); break;

                        case 128: dtm1.addRow(new String[]{"128", "Pal.Reservada", buffer}); break;

                        case 129: dtm1.addRow(new String[]{"129", "Pal.Reservada", buffer}); break;  

                        case 130: dtm1.addRow(new String[]{"130", "Pal.Reservada", buffer}); break;

                        case 131: dtm1.addRow(new String[]{"131", "Pal.Reservada", buffer}); break;

                        case 132: dtm1.addRow(new String[]{"132", "Pal.Reservada", buffer}); break;

                        case 133: dtm1.addRow(new String[]{"133", "Pal.Reservada", buffer}); break;

                        case 134: dtm1.addRow(new String[]{"134", "Pal.Reservada", buffer}); break;

                        case 135: dtm1.addRow(new String[]{"135", "Pal.Reservada", buffer}); break;

                        case 136: dtm1.addRow(new String[]{"136", "Pal.Reservada", buffer}); break;

                        case 137: dtm1.addRow(new String[]{"137", "Pal.Reservada", buffer}); break;

                        case 138: dtm1.addRow(new String[]{"138", "Pal.Reservada", buffer}); break;

                        case 139: dtm1.addRow(new String[]{"139", "Pal.Reservada", buffer}); break;   

                        case 140: dtm1.addRow(new String[]{"140", "Pal.Reservada", buffer}); break;

                        case 141: dtm1.addRow(new String[]{"141", "Pal.Reservada", buffer}); break;

                        case 142: dtm1.addRow(new String[]{"142", "Pal.Reservada", buffer}); break;

                        case 143: dtm1.addRow(new String[]{"143", "Pal.Reservada", buffer}); break; 
                        
                        case 200: dtm1.addRow(new String[]{"200", "AND Logico", buffer}); break;
                        
                        case 201: dtm1.addRow(new String[]{"201", "OR Logico", buffer}); break;
                        
                        case 202: dtm1.addRow(new String[]{"202", "AND Condicio.", buffer}); break;
                        
                        case 203: dtm1.addRow(new String[]{"203", "OR Condicio.", buffer}); break;
                        
                        case 204: dtm1.addRow(new String[]{"204", "XOR Condicio.", buffer}); break;
                        
                        case 205: dtm1.addRow(new String[]{"205", "Negacion", buffer}); break;
                        
                        case 206: dtm1.addRow(new String[]{"206", "Sumatoria", buffer}); break;
                        
                        case 207: dtm1.addRow(new String[]{"207", "Resta", buffer}); break;
                        
                        case 208: dtm1.addRow(new String[]{"208", "Multiplicacion", buffer}); break;
                        
                        case 209: dtm1.addRow(new String[]{"209", "Division", buffer}); break;
                        
                        case 210: dtm1.addRow(new String[]{"210", "Modulo", buffer}); break;
                        
                        case 211: dtm1.addRow(new String[]{"211", "Incremento", buffer}); break;
                        
                        case 212: dtm1.addRow(new String[]{"212", "Decremento", buffer}); break;
                        
                        case 213: dtm1.addRow(new String[]{"213", "Asignacion", buffer}); break;
                        
                        case 214: dtm1.addRow(new String[]{"214", "Asignacion Sum.", buffer}); break;
                        
                        case 215: dtm1.addRow(new String[]{"215", "Asignacion Res.", buffer}); break;
                        
                        case 216: dtm1.addRow(new String[]{"216", "Asignacion Mult.", buffer}); break;
                        
                        case 217: dtm1.addRow(new String[]{"217", "Asignacino Div.", buffer}); break;
                        
                        case 218: dtm1.addRow(new String[]{"218", "Asignacion Mod.", buffer}); break;
                        
                        case 219: dtm1.addRow(new String[]{"219", "Igualdad", buffer}); break;
                        
                        case 220: dtm1.addRow(new String[]{"220", "Diferente", buffer}); break;
                        
                        case 221: dtm1.addRow(new String[]{"221", "Oper. Menor", buffer}); break;
                        
                        case 222: dtm1.addRow(new String[]{"222", "Oper. Menor igual", buffer}); break;
                        
                        case 223: dtm1.addRow(new String[]{"223", "Oper. Mayor", buffer}); break;
                        
                        case 224: dtm1.addRow(new String[]{"224", "Oper. Mayor igual", buffer}); break;
                        
                        case 225: dtm1.addRow(new String[]{"225", "Oper. Limitante", buffer}); break;
                        
                        case 226: dtm1.addRow(new String[]{"226", "Oper. Limitante", buffer}); break;
                        
                        case 227: dtm1.addRow(new String[]{"227", "Oper. Limitante", buffer}); break;
                        
                        case 228: dtm1.addRow(new String[]{"228", "Oper. Limitante", buffer}); break;
                        
                        case 229: dtm1.addRow(new String[]{"229", "Oper. Limitante", buffer}); break;
                        
                        case 230: dtm1.addRow(new String[]{"230", "Oper. Limitante", buffer}); break;
                        
                        case 231: dtm1.addRow(new String[]{"231", "Oper. Coment.", buffer}); break;
                        
                        case 232: dtm1.addRow(new String[]{"232", "Punto", buffer}); break;
                        
                        case 233: dtm1.addRow(new String[]{"233", "Punto y Coma", buffer}); break;
                        
                        case 234: dtm1.addRow(new String[]{"234", "Comilla", buffer}); break;
                        
                        case 235: dtm1.addRow(new String[]{"235", "Doble comilla", buffer}); break;
                        
                        case 236: dtm1.addRow(new String[]{"236", "Barra Vertical", buffer}); break;
                        
                        case 237: dtm1.addRow(new String[]{"237", "Coma", buffer}); break;
                        
                        case 911: dtm1.addRow(new String[]{"911", "Item no identificado", buffer}); break;

                        case 1000: dtm1.addRow(new String[]{"1000", "sale 1000", buffer}); break;
                        
                        
                        
                    }
                    
                }while(salir);
               
            }
        
        });
        
    }
    
    
    
    private int buscar(String x){
        String auxiliar =x;
        for (int k = 0; k < 24; k++) 
            if (auxiliar.equals(palabrasReservadas[k])) return 120 + k;
        return 4800;
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
                        case 49: buffer = buffer.substring(0,1);indice=1;return 235; //" comilla doble
                        //case 50: //ct
                        case 51: return 5400; // CADENA
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
                            }else if(Character.isDigit(caracter)){  estado=2; i++; }
                            else if(Character.isWhitespace(caracter)==false){
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
                                }else {
                                    buffer = buffer + caracter;
                                    estado = 37;
                                }
                                i++;
                                break;
                        case 38: if(caracter == '$'){
                                    buffer = buffer + caracter;
                                    estado = 39;
                                }else {
                                    buffer = buffer + caracter;
                                    estado = 37;
                                }
                                i++;
                                break;
                        case 39: return 3000; //COMENTARIO PARRAFO
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
                                  }else {buffer=buffer+caracter;estado=49;}
                                i++;
                                break; 
                        case 51: return 235;
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
    
    private void indice_tabla(){   
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
        this.indice=0;
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        vista.setResizable(false);
        vista.setTitle("Analizador Lexico");
        indice_tabla();
        
    }




}
