// utilizes StdDraw found here https://introcs.cs.princeton.edu/java/stdlib/StdDraw.java
// Matthew Mouat 7552560
import java.util.*;
import java.awt.*;

public class Quilt {
    public static ArrayList<Color> cl = new ArrayList<>();
    public static ArrayList<Double> scle = new ArrayList<>();
    public static double screenSize = 800;
    public static double firstSqSize;

    public static void drawSquare(double x, double y, double size) {
        StdDraw.filledSquare(x, y, size/2);
        StdDraw.square(x, y, size/2);
    }

    public static void draw(int n, double x, double y, double size) {
        int noOfSq = 0;
        Queue<ArrayList<Double>> q = new ArrayDeque<>();              
        ArrayList<Double> node1 = new ArrayList<>();
        node1.add(x);
        node1.add(y);
        node1.add(0.0);
        
        q.add(node1);// initalising the queue with first square
        while(!q.isEmpty()){
            ArrayList<Double>box = q.poll();
            StdDraw.setPenColor(cl.get((int)Math.round(box.get(2))));// colour input here
            size = firstSqSize*scle.get((int)Math.round(box.get(2)));// size calculated here

            drawSquare(box.get(0), box.get(1), size);

            if(box.get(2) < n-1){// needs a n-1 because it adds an extra box otherwise
                int counter = (int)Math.round(box.get(2));
                ArrayList<Double>subBoxBL = new ArrayList<>();
                ArrayList<Double>subBoxTL = new ArrayList<>();
                ArrayList<Double>subBoxBR = new ArrayList<>();
                ArrayList<Double>subBoxTR = new ArrayList<>();
                // BL = bottom left
                // TL = top left
                // BR = bottom right
                // TR = top right
                
                subBoxBL.add(box.get(0)-size/2);
                subBoxBL.add(box.get(1)-size/2);
                subBoxBL.add((double)counter+1.0);// weird glicch to do with the counter i think
                noOfSq++;

                subBoxTL.add(box.get(0)-size/2); 
                subBoxTL.add(box.get(1)+size/2);
                subBoxTL.add((double)counter+1.0);
                noOfSq++;

                subBoxBR.add(box.get(0)+size/2);
                subBoxBR.add(box.get(1)-size/2);
                subBoxBR.add((double)counter+1.0);
                noOfSq++;

                subBoxTR.add(box.get(0)+size/2);
                subBoxTR.add(box.get(1)+size/2);
                subBoxTR.add((double)counter+1.0);
                noOfSq++;
                
                q.add(subBoxBL);
                q.add(subBoxTL);
                q.add(subBoxBR);
                q.add(subBoxTR);

                if((noOfSq+1) % 4 == 0){
                    n--;
                    noOfSq = 0;
                }
                //System.out.println(q);
                

            }
            
        }

        
    }
    
    public static void colourSetter(int r,int g, int b){
        Color col = new Color(r, g, b);
        cl.add(col);
    }
    public static void main(String[] args) {
        int n = 0;
        double sumOfScales = 0;
        Scanner sc = new Scanner(System.in);
        StdDraw.setCanvasSize(800,800);
        StdDraw.setScale(0,800);
        double x = screenSize/2.0, y = screenSize/2.0;   // center of square
        
        while(sc.hasNextLine()){
            if(sc.hasNext()){
                scle.add(sc.nextDouble());
                int r = sc.nextInt();
                int g = sc.nextInt();
                int b = sc.nextInt();
                colourSetter(r,g,b);
                n++;
            }else{
                break;
            }
        }
        for(int i = 0;i < scle.size();i++){ 
            sumOfScales += scle.get(i);
        }

        firstSqSize = (((screenSize-20)/sumOfScales));
        sc.close();        
        draw(n, x, y, firstSqSize);// n is number of lines of input
        
        
    }

}