/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rex1nlp;
import java.io.File;
import java.io.FileInputStream; 
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream; 
import opennlp.tools.namefind.NameFinderME; 
import opennlp.tools.namefind.TokenNameFinderModel; 
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.Span;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

public class rexNERex { 
   public static void main(String args[]) throws Exception{ 
 
    
      //Getting the sentence in the form of String array  
      String target = "C:\\Users\\RexPC\\Documents\\Haily.docx";
        
        File document = new File(target);
        Parser parser = new AutoDetectParser();
        
        ContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
      
           
         parser.parse(new FileInputStream(document), handler, metadata, new ParseContext());
         
       //  System.out.println(handler);
         
               //Loading the tokenizer model 
      InputStream inputStreamTokenizer = new 
         FileInputStream("C:\\Users\\RexPC\\Documents\\Programming\\Apache OpenNLP\\Models\\Original OpenNLP Models\\en-token.bin");
      TokenizerModel tokenModel = new TokenizerModel(inputStreamTokenizer); 
       
      //Instantiating the TokenizerME class 
      TokenizerME tokenizer = new TokenizerME(tokenModel); 
       
     //Tokenizing the sentence in to a string array  
      String tokens[] = tokenizer.tokenize(handler.toString()); 
         for(String tokenin: tokens)
      System.out.println(tokenin);
         
         InputStream inputStreamNameFinder = new FileInputStream("C:\\Users\\RexPC\\Documents\\Programming\\Apache OpenNLP\\Models\\Original OpenNLP Models\\en-ner-person.bin");     
         TokenNameFinderModel model = new TokenNameFinderModel(inputStreamNameFinder);
      
      //Instantiating the NameFinderME class 
      NameFinderME nameFinder = new NameFinderME(model);       
      
      //Finding the names in the sentence 
      Span nameSpans[] = nameFinder.find(tokens);        
      
      //Printing the names and their spans in a sentence 
     // for(Span s: nameSpans)        
     //    System.out.println(s.toString());  
      
       /*   
       InputStream modelIn = new FileInputStream("C:\\Users\\RexPC\\Documents\\Programming\\Apache OpenNLP\\Models\\Original OpenNLP Models\\en-sent.bin");
        SentenceModel stcmodel = null;
        try {
           stcmodel = new SentenceModel(modelIn);  
        }
        catch (IOException e) {
        }
      
        //Instantiating the SentenceDetectorME class 
SentenceDetectorME detector = new SentenceDetectorME(stcmodel);
        
  String sentences[];      
       sentences = detector.sentDetect(handler.toString());     
         
      //Finding the names in the sentence 
      Span nameSpans[] = nameFinder.find(sentences); 
       
      //Printing the spans of the names in the sentence 
      for(Span s: nameSpans) 
         System.out.println(s.toString()); 
*/
}      
}