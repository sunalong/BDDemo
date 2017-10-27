package com.itcode.lucene;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

/**
 * Created by along on 17/9/28 14:42.
 * Email:466210864@qq.com
 */
public class CreateIndex {
    public static final String indexDir = "/Users/along/ATest/index";
    public static final String dataDir = "/Users/along/ATest/data";
    public static final String siteDir = "/Users/along/ATest/gnn.ztgame.com";

    @Test
    public void createIndexForsiteDir() {
        try {
            FSDirectory dir = FSDirectory.open(new File(indexDir));
            StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_4_9);
            IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_4_9, analyzer);
            IndexWriter indexWriter = new IndexWriter(dir, conf);
            File fileDir = new File(siteDir);
            Collection<File> fileCollection = FileUtils.listFiles(fileDir, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
            for (File file : fileCollection) {
                Document doc = new Document();
                HtmlBean htmlBean = HtmlBeanUtils.parseHtml(file);
                doc.add(new StringField("title", htmlBean.getTitle(), Field.Store.YES));
                doc.add(new TextField("content", htmlBean.getContent(), Field.Store.YES));
                doc.add(new StringField("url", htmlBean.getUrl(), Field.Store.YES));
                indexWriter.addDocument(doc);
            }
            indexWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
            for (File f : files) {
                Document doc = new Document();
                doc.add(new StringField("filename", f.getName(), Field.Store.YES));
                doc.add(new TextField("content", FileUtils.readFileToString(f), Field.Store.YES));
                ;
                doc.add(new LongField("lastMOdify", f.lastModified(), Field.Store.YES));
                //2.使用indexWriter向document中写数据
                indexWriter.addDocument(doc);
            }
            indexWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
