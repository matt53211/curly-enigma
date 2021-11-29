//Matthew Mouat
// 7552560
public class HarmoniousNumbers {
    public static void main(String []args){
        int number = 2000000;
        int Sum = 0;
        for(int i= 1; i < number; i++){
            for(int ii = 2; ii < i/2; ii++){
                if(ii != i && i % ii == 0 && i > ii){//
                    
                    Sum += ii + i;                
                }
                //System.out.println(Sum+" Sum \n");
                
            }
        if(Sum > i && Sum != i){
            System.out.println(i +" " + Sum);
            
        }
        Sum = 0;

        }

    }
}
/**if (input % i == 0) {
                Sum += i;
            
            if (input / i != i) {
                Sum += input / i;                
            } */