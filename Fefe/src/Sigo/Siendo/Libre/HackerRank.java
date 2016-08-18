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
public class HackerRank {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

           Scanner in = new Scanner(System.in);
           int testCases = Integer.parseInt(in.nextLine());
           while(testCases>0){
               String line = in.nextLine();
               String texto=Validar(line);
               if(texto.equals("")){
                   System.out.println("None");
               }
               else{
                    System.out.println(texto);
               }
                   //Write your code here

               testCases--;
    }
    }
    public static String Validar(String texto){
        String palabra="";
        String aux="";
        int saltoDeLinea=0;
        int buscaDeBarra=0;
        boolean estado=false;
        int parte1=-1;
        int parte2=-1;
        int parte3=-1;
        int parte4=-1;
        texto+=" ";
        char[] letras=texto.toCharArray();

        for (int i =0; i <letras.length; i++) {
            estado=false;
              if((parte1!=-1)&&(parte2!=-1)){
                if((parte3!=-1)&&(parte4!=-1)){
                    if((parte2-parte1)!=((parte4-parte3)-buscaDeBarra)){
                        
                        return"";
                    }
                    if(BuscarSiSonIguales(parte1, parte2, parte3, parte4, letras)){
            
                      if((aux!="")){
                          if(saltoDeLinea>0){
                            palabra+="\n";
                          }
                          saltoDeLinea++;
                           
                        }
                      
                        palabra+=aux;
             
                        aux="";
                        buscaDeBarra=0;
                        parte1=-1;
                        parte2=-1;
                        parte3=-1;
                        parte4=-1;
                        estado=true;
                    }
                    else{             
                            aux="";
                            buscaDeBarra=0;
                            parte1=-1;
                            parte2=-1;
                            parte3=-1;
                            parte4=-1;

                    }
                    
                    //System.out.println(aux);
                }  
                if((parte3==-1)&&(letras[i]=='<')&&(parte1!=-1)){
                    if(letras[i+1]=='/'){
                         i++;
                     }
                    
                    parte3=i+1;
                }
               
                else{
                    if((parte3==-1)&&(parte1!=-1)){
                        
                        aux+=letras[i];
                    }
                    if((parte4==-1)&&(letras[i]=='>')){
                        parte4=i-1;
                    }
                }
            }
         
            if((parte1==-1)&&(letras[i]=='<')){
                    if(letras[i+1]=='/'){
                        i++;
                    }
                    parte1=i+1;
                
            }
            else{
                if((letras[i]=='>')&&(parte2==-1)){
                    parte2=i-1;
                }
            }
            if(parte1==-1){
                 aux+=letras[i];
            }
     
            
        }

       

        return palabra;
    }
    private static boolean BuscarSiSonIguales(int parte1,int parte2,int parte3,int parte4,char[] letras){
        int num2=parte3;
  
        String aux="";
        if(((parte2-parte1)<0)){

            return false;
        }
        for (int i =parte1; i <= parte2; i++) {
            aux+=letras[i];
            if(letras[num2]=='/'){
                num2++;
            }
            if(letras[i]!=letras[num2]){
                return false;
            }
            num2++;
        }
        if(aux.equals("par")||(aux.equals("had"))){
 
            return false;
        }
        return !false;
    }
    
}
