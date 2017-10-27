package com.itcode.lucene;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;

import java.io.File;
import java.io.IOException;

/**
 * Created by along on 17/9/28 17:20.
 * Email:466210864@qq.com
 */
public class HtmlBeanUtils {
    public static HtmlBean parseHtml(File file) {
        try {
            Source source = new Source(file);
            Element titleElement = source.getFirstElement(HTMLElementName.TITLE);
            String titleStr = null;
            if (titleElement == null || titleElement.getTextExtractor() == null)
                titleStr = "空标题";
            else
                titleStr = titleElement.getTextExtractor().toString();
            HtmlBean htmlBean = new HtmlBean();
            htmlBean.setTitle(titleStr);
            htmlBean.setContent(source.getTextExtractor().toString());
            htmlBean.setUrl("http://" + file.getAbsolutePath().substring(3));
            return htmlBean;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
