/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rex1nlp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import opennlp.tools.formats.ad.ADSentenceStream.Sentence;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerFactory;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;

/**
 *
 * @author RexPC
 */
public class rexNER5 {
         public static void main(String args[]) throws Exception{        
      
      //Loading the tokenizer model 
      InputStream inputStreamTokenizer = new 
         FileInputStream("C:\\Users\\RexPC\\Documents\\Programming\\Apache OpenNLP\\Models\\Original OpenNLP Models\\en-token.bin");
      TokenizerModel tokenModel = new TokenizerModel(inputStreamTokenizer); 
       
      //Instantiating the TokenizerME class 
      TokenizerME tokenizer = new TokenizerME(tokenModel); 
      
      String target = "C:\\Users\\RexPC\\Documents\\sklep.docx";
        
        File document = new File(target);
        Parser parser = new AutoDetectParser();
        
        ContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
      
           
         parser.parse(new FileInputStream(document), handler, metadata, new ParseContext());
         
   InputStream modelIn = new FileInputStream("C:\\Users\\RexPC\\Documents\\Programming\\Apache OpenNLP\\Models\\Original OpenNLP Models\\en-sent.bin");
        SentenceModel model = null;
        try {
           model = new SentenceModel(modelIn);  
        }
        catch (IOException e) {
        }
        finally {
          if (modelIn != null) {
            try {
              modelIn.close();
            }
            catch (IOException e) {
            }
          }
        }
      //SentenceDetectorME sentenceDetector = new SentenceDetectorME(model);
      //  String sentences[] = sentenceDetector.sentDetect(handler.toString());

        // System.out.println(sentences[0]);
      
     
      //Tokenizing the sentence in to a string array 
      String tokens[]; 
             tokens = tokenizer.tokenize(handler.toString());
       
      //Loading the NER-person model 
      InputStream inputStreamNameFinder = new  FileInputStream("C:\\Users\\RexPC\\Documents\\Programming\\Apache OpenNLP\\Models\\Original OpenNLP Models\\en-ner-person.bin");       
      TokenNameFinderModel model8 = new TokenNameFinderModel(inputStreamNameFinder);
      
      //Instantiating the NameFinderME class 
      NameFinderME nameFinder = new NameFinderME(model8);       
      
      //Finding the names in the sentence 
      Span nameSpans[] = nameFinder.find(tokens);        
      
      //Printing the names and their spans in a sentence 
  for(Span s: nameSpans)        
      System.out.println(s.toString()+"  "+tokens[s.getStart()]);  
  
  
}
}
               
  
