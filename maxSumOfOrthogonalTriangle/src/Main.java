import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {



    static boolean isPrime(int number){
        int i;
        int flag =0;
        if(number > 2){
            for(i=2;i<number;i++){
                if(number%i == 0)
                flag = 1;
            }
            if(flag == 0)
             return true;
            else
             return false;
        }
        else if(number == 2)
             return true;
        else 
             return false;
    }

    static int findNumOfLines(File file){
        int numOfLines=0;
        String line;

        try (Scanner fileScanner = new Scanner(file)) {
            while(fileScanner.hasNextLine()) {
                line = fileScanner.nextLine();
                if(line.equals("\n"));
                numOfLines++; 
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return numOfLines;
    }

    static int findMaxSumOfOrthogonalTriangle(File file, int numOfLines){
       try (Scanner fileScanner = new Scanner(file)) {
        int triangleArr[][] = new int[numOfLines][numOfLines];        
        while(fileScanner.hasNextLine()) {
            for(int i=0;i<numOfLines;i++){
                for(int j=0;j<=i;j++){
                    int number = fileScanner.nextInt();
                    if(!isPrime(number))
                    triangleArr[i][j] = number; 
                    else
                    triangleArr[i][j] = 0;                           
                }             
            }
         }
        if (numOfLines > 1)
            triangleArr[1][1] = triangleArr[1][1] + triangleArr[0][0];
            triangleArr[1][0] = triangleArr[1][0] + triangleArr[0][0];

        for(int i = 2; i < numOfLines; i++){
            triangleArr[i][0] = triangleArr[i][0] + triangleArr[i-1][0]; 
            triangleArr[i][i] = triangleArr[i][i] + triangleArr[i-1][i-1];            
            for (int j = 1; j < i; j++){
            if (triangleArr[i][j] + triangleArr[i-1][j-1] >=  
               triangleArr[i][j] + triangleArr[i-1][j])
                    triangleArr[i][j] = triangleArr[i][j]+ triangleArr[i-1][j-1]; 
                      
                else
                    triangleArr[i][j] = triangleArr[i][j]+triangleArr[i-1][j];
            }
         }
         int max = triangleArr[numOfLines-1][0]; 
          
         int i=1;
         while(i < numOfLines) 
         { 
             if(max < triangleArr[numOfLines-1][i]) 
                 max = triangleArr[numOfLines-1][i]; 
            i++;
         }

        
        
          return max;
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
    return 0;
             
    }


    public static void main(String[] args) throws Exception {

        File triangleFile = new File("files/orthogonalTriangle.txt");
        if(triangleFile.exists()) {
           // System.out.println("file is found.");
        } 
        else {
            System.out.println("file is not found!");
        }


       int numOfLines = findNumOfLines(triangleFile);

       System.out.println(findMaxSumOfOrthogonalTriangle(triangleFile, numOfLines));

 }

}
