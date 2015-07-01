import java.util.Random;
public class  Main {
    Random  random = new Random ();
    private int a ;
    private int b ;
    private int c;
    private int [] racing = new int [20];
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
    
    public static void main(String[] args) {
        Main  fefe =new Main ("20","30");
        fefe.random();
        fefe.terminar();
        System.out.println(fefe.getNumerosA());
        int [] a =fefe.aletatorio();
        for (int i = 0 ; i<20; i++){
            System.out.print(fefe.aletatorio()[i]+" ,");
          
            
        }fefe.nadaQueVer();
        
    }

}
