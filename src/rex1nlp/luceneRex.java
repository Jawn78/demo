
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rex1nlp;

import java.io.IOException;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.queryparser.flexible.standard.parser.ParseException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
public class luceneRex {
   
         
      public static void main(String[] args) throws IOException, ParseException, org.apache.lucene.queryparser.classic.ParseException {
        // 0. Specify the analyzer for tokenizing text.
        //    The same analyzer should be used for indexing and searching
        StandardAnalyzer analyzer = new StandardAnalyzer();

        // 1. create the index
        Directory index = new RAMDirectory();

        IndexWriterConfig config = new IndexWriterConfig(analyzer);

          try (IndexWriter w = new IndexWriter(index, config)) {
              addDoc(w, "Lucene in Action", "193398817");
              addDoc(w, "Lucene for Dummies", "55320055Z");
              addDoc(w, "Managing Gigabytes", "55063554A");
              addDoc(w, "The Art of Computer Science", "9900333X");
          }

        // 2. query
        String querystr = args.length > 0 ? args[0] : "science";

        // the "title" arg specifies the default field to use
        // when no field is explicitly specified in the query.
        Query q = new QueryParser("title", analyzer).parse(querystr);

        // 3. search
        int hitsPerPage = 10;
          try (IndexReader reader = DirectoryReader.open(index)) {
              IndexSearcher searcher = new IndexSearcher(reader);
              TopDocs docs = searcher.search(q, hitsPerPage);
              ScoreDoc[] hits = docs.scoreDocs;
              // 4. display results
              System.out.println("Found " + hits.length + " hits.");
              for (int i = 0; i<hits.length; ++i) {
                  int docId = hits[i].doc;
                  Document d = searcher.doc(docId);
                  System.out.println((i + 1) + ". " + d.get("isbn") + "\t" + d.get("title"));
              }
              // reader can only be closed when there
              // is no need to access the documents any more.
          }
    }

    private static void addDoc(IndexWriter w, String title, String isbn) throws IOException {
        Document doc = new Document();
        doc.add(new TextField("title", title, Field.Store.YES));

        // use a string field for isbn because we don't want it tokenized
        doc.add(new StringField("isbn", isbn, Field.Store.YES));
        w.addDocument(doc);
    }
}
    
