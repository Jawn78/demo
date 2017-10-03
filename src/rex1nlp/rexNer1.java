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
     
    
         InputStream modelInToken = null;
         InputStream modelIn = null;
               
		
		try {
			
			//1. convert sentence into tokens
		    	modelInToken = new FileInputStream("C:\\Users\\RexPC\\Documents\\Programming\\Apache OpenNLP\\Models\\Original OpenNLP Models\\en-token.bin"); 
		    	TokenizerModel modelToken = new TokenizerModel(modelInToken); 
		    	Tokenizer tokenizer = new TokenizerME(modelToken);  
		    	String tokens[] = tokenizer.tokenize(sentence);

		    	//2. find names
		    	modelIn = new FileInputStream("C:\\Users\\RexPC\\Documents\\Programming\\Apache OpenNLP\\Models\\Original OpenNLP Models\\en-ner-person.bin");
		    	TokenNameFinderModel model = new TokenNameFinderModel(modelIn);
		    	NameFinderME nameFinder = new NameFinderME(model);
				
		    	Span nameSpans[] = nameFinder.find(tokens);
		    	
		    	//find probabilities for names
		    	double[] spanProbs = nameFinder.probs(nameSpans);
		    	
		    	//3. print names
		    	for( int i = 0; i<nameSpans.length; i++) {
		    		System.out.println("Span: "+nameSpans[i].toString());
		    		System.out.println("Covered text is: "+tokens[nameSpans[i].getStart()] + " " + tokens[nameSpans[i].getStart()+1]);
		    		System.out.println("Probability is: "+spanProbs[i]);
		    	}		    	
		    	//Span: [0..2) person
		    	//Covered text is: Jack London
		    	//Probability is: 0.7081556539712883
			}
			catch (Exception ex) {}
			finally {
				  try { if (modelInToken != null) modelInToken.close(); } catch (IOException e){};
				  try { if (modelIn != null) modelIn.close(); } catch (IOException e){};
			}
	}
}
   
