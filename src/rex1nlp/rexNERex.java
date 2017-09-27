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
      //Loading the NER - Person model    
      InputStream inputStream = new 
         FileInputStream("C:\\Users\\RexPC\\Documents\\Programming\\Apache OpenNLP\\Models\\Original OpenNLP Models\\en-ner-person.bin"); 
      TokenNameFinderModel model = new TokenNameFinderModel(inputStream);
      
      //Instantiating the NameFinder class 
      NameFinderME nameFinder = new NameFinderME(model); 
    
      //Getting the sentence in the form of String array  
           String target = "C:\\Users\\RexPC\\Documents\\Haily.docx";
        
        File document = new File(target);
        Parser parser = new AutoDetectParser();
        
        ContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
      try {
          parser.parse(new FileInputStream(document), handler, metadata, new ParseContext());
          
          
        } catch (FileNotFoundException e) {
        } catch (IOException | SAXException | TikaException e) {
        }
       //Finding the names in the sentence 
       
       //Finding the names in the sentence
       
      //Finding the names in the sentence
  
      System.out.println(handler);
}      
}