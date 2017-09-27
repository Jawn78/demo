/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rex1nlp;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import org.apache.commons.vfs2.FileNotFoundException;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;



/**
 *
 * @author RexPC
 */
public class rexNER {

    public rexNER(InputStream modelIn) {
    }
    
    public String contentEx() throws IOException, SAXException, TikaException {
        InputStream is = new BufferedInputStream(new FileInputStream(new File(
                "C:\\Users\\RexPC\\Documents\\Haily.p")));
        // URL url=new URL("http://in.linkedin.com/in/rahulkulhari");
        // InputStream is=url.openStream();
        Parser ps = new AutoDetectParser(); // for detect parser related to

        BodyContentHandler bch = new BodyContentHandler();

        ps.parse(is, bch, new Metadata(), new ParseContext());

        return bch.toString();

    }
}
     


