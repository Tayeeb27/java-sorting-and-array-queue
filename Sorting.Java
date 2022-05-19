// Name: Tayeeb Islam
// Student Number: c2003538
// Module Code: CM1210 CW2
import java.util.*;
import java.io.*;
public class Sorting{
  // Question 4 is throughout the methods
  static int comparisons;
  static int swaps;
  public static void main(String[] args) {
      ArrayList<String> A = removeStopWords("GPT3","stopwords");
      // Loop for Q4 to check every 100 words
      for(int i = 100; i <= A.size(); i+= 100){
        if (i > A.size()){
          i = A.size();
        }
        //sub ArrayList of every 100 words
        ArrayList<String> D = new ArrayList<String>(A.subList(0, i));

        comparisons = 0;
        swaps = 0;
        // start the timer do the sort then end timer
        long StartI = System.nanoTime();
        List<String> B = insertionSort(D);
        long EndI = System.nanoTime();
        long TimeTakenI = (EndI - StartI);
        System.out.println("Insertion Sort: ");
        System.out.println("Words:" + i);
        System.out.println("Comparisons:" + comparisons);
        System.out.println("Swaps: " + swaps);
        System.out.println("Time: " + TimeTakenI + " ns");
        System.out.println();

        comparisons = 0;
        swaps = 0;
        // start the timer do the sort then end timer
        long StartM = System.nanoTime();
        List<String> C = mergeSort(D);
        long EndM = System.nanoTime();
        long TimeTakenM = (EndM - StartM);

        System.out.println("Merge Sort: ");
        System.out.println("Words:" + i);
        System.out.println("Comparisons:" + comparisons);
        System.out.println("Swaps: " + swaps);
        System.out.println("Time: " + TimeTakenM + " ns");
        System.out.println();
      }

}
  // Question 1
  public static ArrayList<String> txtFileToList(String file) {


        ArrayList<String> ListOfWords = new ArrayList<>();
        try{

        // reader to read txt file
        BufferedReader TxtFileReader = new BufferedReader(new FileReader(file));

        //reads each line
        String SingleLine = TxtFileReader.readLine();
        while (SingleLine != null) {
          //creates array and includes each word that is seperated with whitespace
          String word[] = SingleLine.toLowerCase().split(" ");

          for(int i=0; i < word.length-1; i++){
            //removes punctuation
            String P = word[i].replaceAll("\\p{Punct}", "");
            ListOfWords.add(P);

         }
         //next line
         SingleLine = TxtFileReader.readLine();
        }

        TxtFileReader.close();





      }
      catch (IOException e) {
       System.out.println("An error occurred.");
     }
     return ListOfWords;

      }
 public static ArrayList<String> removeStopWords(String GPT3, String stopwords){
    ArrayList<String> GPT3List = new ArrayList<String>();
    ArrayList<String> stopWordsList = new ArrayList<String>();
    try{
      GPT3List = txtFileToList(GPT3 + ".txt");
      // the reason the txt files are done differently is because one is paragraphs, other is single words
      File stopWordsFile = new File(stopwords +".txt");
      Scanner readFileSW = new Scanner(stopWordsFile);
      while (readFileSW.hasNext()) {
        //adds all words from text file to list
        stopWordsList.add(readFileSW.next());
      }

      readFileSW.close();

      //removes all words in stopwords list that are in GPT3List
      GPT3List.removeAll(stopWordsList);



    } catch (IOException e) {
      System.out.println("An error occurred.");
    }


    return GPT3List;
}

  // Question 2
  public static List<String> insertionSort(List<String> NewList){
    //convert list to array
    String[] ArrayOfWords = NewList.toArray(new String[NewList.size()]);
    for(int i = 1; i < ArrayOfWords.length; i++){
      String x = ArrayOfWords[i];
      int j = i - 1;
      while(j >= 0){
        comparisons++;
        //checks if it is higher or lower than x
        if(x.compareTo(ArrayOfWords[j]) > 0 ){
          break;
        }
        swaps++;
        //swaps them over
        ArrayOfWords[j + 1] = ArrayOfWords[j];
        j--;
      }
      //increments x
      ArrayOfWords[j+1] = x;
    }
      return NewList;
}
  // Question 3
  public static List<String> mergeSort(List<String> NewList){
      List<String> S = new ArrayList<String>(NewList);
      // list of first elements and last elements respectivefully
      List<String> p = new ArrayList<String>();
      List<String> r = new ArrayList<String>();
      if (S.size() < 2){
        comparisons++;
        return S;
      }
      int index = 0;
      for (String element : S) {
        comparisons++;
        // if the index is less than half of ArrayList S then it will add the the element to r list
        if(index < S.size()/2){
          r.add(element);
          swaps++;
        } else{
          //else to the p list
          p.add(element);
          swaps++;
        }
        index++;
      }
      // recursion on p and r
      p = mergeSort(p);
      r = mergeSort(r);
      //return the merge of p and r
    return merge(p,r);
  }
  public static List<String> merge(List<String> S1, List<String> S2){
    List<String> S = new ArrayList<String>();

    while (S1.size() > 0 && S2.size() > 0){
      comparisons++;
      // comparing S1 and S2 element by element
      if(S1.get(0).toLowerCase().compareTo(S2.get(0).toLowerCase()) < 0){
        // if less than 0 it will add to S and remove element from the list it came from
        S.add(S1.get(0));
        S1.remove(0);
        swaps++;
      } else{
        S.add(S2.get(0));
        S2.remove(0);
        swaps++;
      }
    }
    //moving remaining elements into S from S1
    while (S1.size() != 0){
      comparisons++;
      S.add(S1.get(0));
      S1.remove(0);
      swaps++;
    }
    //moving remaining elements into S from S2
    while (S2.size() != 0){
      comparisons++;
      S.add(S2.get(0));
      S2.remove(0);
      swaps++;
    }
    return S;
  }

}
