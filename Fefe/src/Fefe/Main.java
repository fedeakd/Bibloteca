import Fefe.ElMetodoPush;
import java.util.Random;
public class  Main {
    Random  random = new Random ();
    private int a ;
    private int b ;
    private int c;
    private static  int [] racing = new int [5];
    public Main (String a , String b){
        this.a =  Integer.parseInt(a);
        this.b =  Integer.parseInt(b);
        
        
    } public int suma (){
       this.c = a+b;
       return c;
    } public int resta (){
        this.c = a-b;
        return c;
    }public int  multiplicar (){
        this.c = a * b ;
        return c ;
    }
    public int getNumerosA (){
        return a;
        
    }public int getNumerosB (){
        return b;
    
    }public int  random (){
    this.c = random.nextInt(a);
        return c ;
    }public void  nadaQueVer (){
       if ((this.a >= 20)&& (this.b > 20)){
           System.out.println("numeros mayores");
       }else{
           System.out.println("numeros menores");
       }
    }

    /**
     *
     * @return
     */
    public  int[] aletatorio (){
        for (int i =0;i<20;i++){
            racing[i] =+  random.nextInt(a);
        }return racing;
    }
    public  void terminar(){
            System.out.println(c);
    }
    public static void Push(){
        for (int i = 0; i <racing.length; i++) {
           racing[i]=i+1;
        }
    }

    public static int[] getRacing() {
        return racing;
    }
    
    public static void main(String[] args) {
        System.out.println("fila 1  fila 2 fila 3");
        for (int i = 1; i <= 36; i++) {
            if(i%3==0){
                System.out.println("\t|"+i+"|");
            }
            if(i%3==1){
                System.out.print("|"+i+"|");
            }
           if(i%3==2){
                System.out.print("\t|"+i+"|");
            }
        }
        
    }

}
