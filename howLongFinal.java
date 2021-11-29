//Matthew Mouat
// 7552560
import java.util.*;

public class howLongFinal{

    public static final long NAN = -1;
    
    // It's not best style to have these as effectively 'global' variables
    // but it saves passing them around to various methods.
    
    static HashMap<Character,String> unknown = new HashMap<>();
    static HashMap<Character, Long> known = new HashMap<>();
    static ArrayList<Character> chars = new ArrayList<>();
    static Scanner scan = new Scanner(System.in);
   
    public static long isNumber(String input){
        try{
            long output = Long.parseLong(input);
            return output;
        }catch(NumberFormatException e){
            return NAN;
        }
    }

    public static void main(String []args){
    
        while(scan.hasNextLine()){
            // Check if the next line is empty      
            String nextLine = scan.nextLine().trim();
            if (nextLine.equals("")) { // And if so, solve this problem
              solve();
              print();
              known.clear();   // And clear the globals
              unknown.clear();
              chars.clear();
            } else { // Otherwise, add the data from that line to the current
                     // problem
              String[] line = nextLine.split(" ");
              char c = line[0].charAt(0);
              chars.add(c);
              String rule = line[1];
              long val = isNumber(rule);
              if(val != NAN) {
                 known.put(c, val);
              } else {
                 unknown.put(c, rule);
              }
            }
        }
        solve(); // For the last problem (if any)
        print();
    }
    
    public static void solve() {
      int knownCount = 0;
      while (known.size() > knownCount) {
        knownCount = known.size();
        for(Character c : unknown.keySet()) {
          if (known.containsKey(c)) continue; // c's value already computed
          long value = computeValue(unknown.get(c));
          if (value != NAN) {
            known.put(c, value);  // A new value is found - known.size() will
                                  // change and the loop will run again.
          }          
        }
      }    
    }
    
    public static long computeValue(String rule) {
      char[] letters = rule.toCharArray();
      long sum = 0;
      for (char c : letters) {
        if(!known.containsKey(c)){// If the string contains any characters that are not keys of 'known'
          return NAN;
        }else{
          try{
            sum = Math.addExact(sum, known.get(c));
          }catch(ArithmeticException e){
            return NAN;
          }
        }
      }
      return sum;
      // Complete this method so that it computes the value of a string.
      
      // then it should return NAN
      // If computing the value leads to overflow it should return NAN
      // Otherwise it should return the value
    }
    
    public static void print() {
      if (chars.size() == 0) return;  // Allows for multiple blank lines where
                                      // we'll get problems with no input.
      for(Character c : chars) {
        if (known.containsKey(c)) {
          System.out.println(c + " " + known.get(c));
        } else {
          System.out.println(c + " NaN");
        }
      }
      System.out.println();
    }
 
    
}
