/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sigo.Siendo.Libre;
import java.util.Scanner;
/**
 *
 * @author Usuario
 */
public class AgarrarPalabras {
    private static boolean estado=true;

  
    public static void main(String[] args) {
        Scanner scaner=new Scanner(System.in);
        System.out.println("Escribe aqui");
        String palabras=scaner.nextLine();
        palabras+=" ";
        System.out.println((Separador(palabras)));
        int aux=0;
        
    }
    private static String Separador(String palabras){
        
        char[] letras=palabras.toCharArray();
        int num=0;
        String finalPalabras="";
        String auxPalabra="";
        for (char letra:letras) {
            if(BuscarCaracterEspecial(letra)){
                if(!estado){
                    if((num>0)){
                        finalPalabras+="\n";
                        num++;
                    }

                    finalPalabras+=auxPalabra;
                    auxPalabra="";
                    if((num<=0)){
                        num++;
                    }
                    estado=true;
                }
            }
            else{
                auxPalabra+=letra;
                estado=false;
            }
            
        }
        finalPalabras=Integer.toString(num)+"\n"+finalPalabras;
        return finalPalabras;
    }
    private static boolean BuscarCaracterEspecial(char letra){
        switch(letra){
            //" !,?._'@"
            case ' ':
                return true;
            case '!':
                return true;
            case ',':
                return true;
            case '?':
                return true;
            case '.':
                return true;
            case '_':
                return true;
            case '\'':
                return true;
            case '@':
                return true;
            
        }
        return false;
       /* for (int i = 0; i < condicion.length; i++) {
            if(condicion[i]==letra){
                
                return true;
            }
        }
        return false;
               */
    }
}
