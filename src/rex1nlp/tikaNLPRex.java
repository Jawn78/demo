/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rex1nlp;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;
import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.commons.vfs2.FileNotFoundException;
import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

/**
 *
 * @author RexPC
 */
public class tikaNLPRex {
     
    String Tokens[];

    public static void main(String[] args) throws IOException, SAXException,
            TikaException {

        tikaNLPRex toi = new tikaNLPRex();


        String cnt;

        cnt="John is planning to specialize in Electrical Engineering in UC Berkley and pursue a career with IBM.";

                toi.tokenization(cnt);

        String names = toi.namefind(toi.Tokens);
        String org = toi.orgfind(toi.Tokens);

                System.out.println("person name is : "+names);
        System.out.println("organization name is: "+org);

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

    public String orgfind(String cnt[]) {
        InputStream is;
        TokenNameFinderModel tnf;
        NameFinderME nf;
        String sd = "";
        try {
            is = new FileInputStream(
                    "C:\\Users\\RexPC\\Documents\\Programming\\Apache OpenNLP\\Models\\Original OpenNLP Models\\en-ner-organization.bin");
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
   