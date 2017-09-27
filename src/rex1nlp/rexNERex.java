/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rex1nlp;
import java.io.FileInputStream; 
import java.io.InputStream; 
import opennlp.tools.namefind.NameFinderME; 
import opennlp.tools.namefind.TokenNameFinderModel; 
import opennlp.tools.util.Span;  

public class rexNERex { 
   public static void main(String args[]) throws Exception{ 
      //Loading the NER - Person model    
      InputStream inputStream = new 
         FileInputStream("C:\\Users\\RexPC\\Documents\\Programming\\Apache OpenNLP\\Models\\Original OpenNLP Models\\en-ner-person.bin"); 
      TokenNameFinderModel model = new TokenNameFinderModel(inputStream);
      
      //Instantiating the NameFinder class 
      NameFinderME nameFinder = new NameFinderME(model); 
    
      //Getting the sentence in the form of String array  
      String [] sentence = new String[]{ 
         "Mike", 
         "and", 
         "Smith", 
         "are", 
         "good", 
         "friends" 
      }; 
       
      //Finding the names in the sentence 
      Span nameSpans[] = nameFinder.find(sentence); 
       
      //Printing the spans of the names in the sentence 
      for(Span s: nameSpans) 
         System.out.println(s.toString());    
   }    
}      