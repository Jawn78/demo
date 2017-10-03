/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rex1nlp;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.InvalidFormatException;
import opennlp.tools.util.Span;
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
public class rexNER3 {
    public static void main(String[] args) throws IOException, SAXException,
            TikaException {

        rexNER3 toi = new rexNER3();
        toi.filest("");
        String cnt = toi.contentEx();
        toi.sentenceD(cnt);
        toi.tokenization(cnt);

        String names = toi.namefind(toi.Tokens);
        toi.files(names);

    }

    public String Tokens[];

    public String contentEx() throws IOException, SAXException, TikaException {
        InputStream is = new BufferedInputStream(new FileInputStream(new File(
                "C:\\Users\\RexPC\\Documents\\Haily.docx")));
        // URL url=new URL("http://in.linkedin.com/in/rahulkulhari");
        // InputStream is=url.openStream();
        Parser ps = new AutoDetectParser(); // for detect parser related to

        BodyContentHandler bch = new BodyContentHandler();

        ps.parse(is, bch, new Metadata(), new ParseContext());

        return bch.toString();

    }

    public void files(String st) throws IOException {
        FileWriter fw = new FileWriter("C:\\Users\\RexPC\\Documents\\extrdata.txt",
                true);
        try (BufferedWriter bufferWritter = new BufferedWriter(fw)) {
            bufferWritter.write(st + "\n");
        }
    }

    public void filest(String st) throws IOException {
        FileWriter fw = new FileWriter("C:\\Users\\RexPC\\Documents\\extrdata.txt",
                false);

        try (BufferedWriter bufferWritter = new BufferedWriter(fw)) {
            bufferWritter.write(st);
        }
    }

    public String namefind(String cnt[]) {
        InputStream is;
        TokenNameFinderModel tnf;
        NameFinderME nf;
        String sd = "";
        try {
            is = new FileInputStream(
                    "C:\\Users\\RexPC\\Documents\\Programming\\Apache OpenNLP\\Models\\Original OpenNLP Models\\en-ner-person.bin");
            tnf = new TokenNameFinderModel(is);
            nf = new NameFinderME(tnf);

            Span sp[] = nf.find(cnt);

            String a[] = Span.spansToStrings(sp, cnt);
            StringBuilder fd = new StringBuilder();
            int l = a.length;

            for (int j = 0; j < l; j++) {
                fd = fd.append(a[j]).append("\n");

            }
            sd = fd.toString();

        } catch (FileNotFoundException e) {
        } catch (InvalidFormatException e) {
        } catch (IOException e) {
        }
        return sd;
    }


    public void sentenceD(String content) {
        String cnt[] = null;
        InputStream om;
        SentenceModel sm;
        SentenceDetectorME sdm;
        try {
            om = new FileInputStream("C:\\Users\\RexPC\\Documents\\Programming\\Apache OpenNLP\\Models\\Original OpenNLP Models\\en-sent.bin");
            sm = new SentenceModel(om);
            sdm = new SentenceDetectorME(sm);
            cnt = sdm.sentDetect(content);

        } catch (IOException e) {
        }

    }

    public void tokenization(String tokens) {

        InputStream is;
        TokenizerModel tm;

        try {
            is = new FileInputStream("C:\\Users\\RexPC\\Documents\\Programming\\Apache OpenNLP\\Models\\Original OpenNLP Models\\en-token.bin");
            tm = new TokenizerModel(is);
            Tokenizer tz = new TokenizerME(tm);
            Tokens = tz.tokenize(tokens);
            // System.out.println(Tokens[1]);
        } catch (IOException e) {
        }
    }

}

