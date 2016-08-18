/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fefe;

/**
 *
 * @author Usuario
 */
public class ElMetodoPush {
    private int[] numeros;
    public ElMetodoPush(){
       numeros=new int [5];
       CargarNumero();
    }
    private void CargarNumero(){
        for (int i = 0; i <numeros.length; i++) {
            numeros[i]=i+1;
        }
        
    }
    public void PushEnAccion(){
        int aux=numeros[numeros.length-1];
        for(int i=numeros.length-1;i>=0;i--){
            //System.out.println(aux);
            if(i==0){
                numeros[0]=aux;
                break;   
            }
             numeros[i]=numeros[i-1];
        }
        
    }
    @Override
    public String toString(){
        String mostrar="";
        for(int  num:numeros){
            mostrar+=String.format("numero:%d\n",num);
        }
        return mostrar;
    }
}
