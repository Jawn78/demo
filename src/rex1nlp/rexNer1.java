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
import opennlp.tools.tokenize.Tokenizer;
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


/**
 *
 * @author RexPC
 */
public class rexNer1 {
     public static void main(String args[]) throws Exception{
         
         
         
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
      
      //inputs sentence tokenizer
        InputStream modelIn = new FileInputStream("\"C:\\\\Users\\\\RexPC\\\\Documents\\\\Programming\\\\Apache OpenNLP\\\\Models\\\\Original OpenNLP Models\\\\en-sent.bin\"");
        TokenizerModel tokenModel = new TokenizerModel(modelIn);
        //loads tokenizer model through tokenized text? 
        TokenizerME tokenizer = new TokenizerME(tokenModel);     
      
      //created tokens array , assigns the tokenizer method to the array
     String tokens[];
     tokens = tokenizer.tokenize(handler.toString());
     
     //Instantiating the NameFinderME Class
     
     NameFinderMe namefinder = new NameFinderMe()
     
     
    
        
		
	}
}
   
