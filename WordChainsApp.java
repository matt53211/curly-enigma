import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class WordChainsApp {

    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in);
        Set<String> dictionary = new HashSet<>();
        ArrayList<String []> input = new ArrayList<>();
        boolean isDictionary = false;
        while(scanner.hasNextLine()){
            String in = scanner.nextLine();
            if(in.isEmpty()) {
                isDictionary = true;
                continue;
            } else if(!isDictionary) {
                String [] splitInput = in.split("\\s+");
                input.add(splitInput);
            } else {
                dictionary.add(in);
            }
        }
        WordChains wordChains = new WordChains(dictionary, input);
        scanner.close();
    }
}