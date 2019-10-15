package com.xbcxs.markdown;

import com.github.houbb.markdown.toc.core.impl.AtxMarkdownToc;
import org.junit.Test;

/**
 * @author xiaosh
 * @date 2019/9/26
 */
public class MarkdownTest {

    @Test
    public void test1(){
        AtxMarkdownToc.newInstance()
                .charset("UTF-8")
                .write(true)
                .subTree(true).genTocFile("C://a.md");
    }
}
