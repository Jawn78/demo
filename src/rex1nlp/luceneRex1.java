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
import org.apache.lucene.analysis.Analyzer;
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
public class luceneRex1 {
public static luceneRex1 indexFile(Analyzer analyzer, File file) throws IOException, SAXException {
        Metadata metadata = new Metadata();
        ContentHandler handler = new BodyContentHandler(10 * 1024 * 1024);
        ParseContext context = new ParseContext();
        Parser parser = new AutoDetectParser();
        InputStream stream = new FileInputStream(file); //open stream
        try {
            parser.parse(stream, handler, metadata, context); //parse the stream 
        } catch (TikaException | SAXException e) {
        } finally {
            stream.close(); //close the stream
        }
   //more code here 
    return null;
 }
    
}
