package com.itcode.lucene;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by along on 17/9/28 14:42.
 * Email:466210864@qq.com
 */
public class CreateIndex {
    public static final String indexDir = "/Users/along/ATest/index";
    public static final String dataDir = "/Users/along/ATest/data";

    @Test
    public void createIndex() {
        try {
            FSDirectory fsDirectory = FSDirectory.open(new File(indexDir));
            StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_4_9);
            IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_4_9, analyzer);
            config.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
            //1.创建indexWriter
            IndexWriter indexWriter = new IndexWriter(fsDirectory, config);
            File file = new File(dataDir);
            File[] files = file.listFiles();
            for (File f :files) {
                Document doc = new Document();
                doc.add(new StringField("filename",f.getName(), Field.Store.YES));
                doc.add(new TextField("content", FileUtils.readFileToString(f),Field.Store.YES));;
                doc.add(new LongField("lastMOdify",f.lastModified(),Field.Store.YES));
                //2.使用indexWriter向document中写数据
                indexWriter.addDocument(doc);
            }
            indexWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
