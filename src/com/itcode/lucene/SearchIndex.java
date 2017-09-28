package com.itcode.lucene;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;

import java.io.File;

/**
 * Created by along on 17/9/28 15:54.
 * Email:466210864@qq.com
 */
public class SearchIndex {
    @Test
    public void search() {
        try {
            FSDirectory directory = FSDirectory.open(new File(CreateIndex.indexDir));
            DirectoryReader directoryReader = DirectoryReader.open(directory);
            IndexSearcher indexSearcher = new IndexSearcher(directoryReader);
            StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_4_9);
            QueryParser queryParser = new QueryParser(Version.LUCENE_4_9, "content", analyzer);
            Query query = queryParser.parse("java");
            TopDocs topDocs = indexSearcher.search(query, 10);
            for (ScoreDoc sc : topDocs.scoreDocs) {
                Document document = directoryReader.document(sc.doc);
                System.out.println(document.get("filename"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
