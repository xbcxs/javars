package com.xbcxs.common.path;

import com.xbcxs.common.exception.base.UncheckedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

/**
 * 计算系统路径
 * 需要考虑不同系统windows,linux,mac
 * 需要考虑不同程序执行方式，bin,springboot,webroot
 */
public class SystemPath {

    private static Logger log = LoggerFactory.getLogger(SystemPath.class);

    public static void main(String[] args) {
        log.debug("11111111111111");
    }

    /**
     * 得到classpath路径
     * 可用于windows和Linux
     * 不可用于jar工程
     *
     * @return
     */
    public static String getClasspath() {
        try {
            return new ClassPathResource("").getFile().getPath();
        } catch (IOException e) {
            throw new UncheckedException(e.getMessage());
        }
    }

    /**
     * 得到classpath流
     * 可用于windows和Linux
     * 可用jar,bin,webroot工程环境
     *
     * @return
     */
    public static InputStream getClasspathStream() {
        try {
            return new ClassPathResource("").getInputStream();
        } catch (IOException e) {
            throw new UncheckedException(e.getMessage());
        }
    }

    /**
     * 根据文件的classpath相对路径得到文件流
     * 可用于windows和Linux
     * 可用jar,bin,webroot工程环境
     *
     * @return
     */
    public static InputStream getClasspath(String relativeFilePath) {
        try {
            return new ClassPathResource(relativeFilePath).getInputStream();
        } catch (IOException e) {
            throw new UncheckedException(relativeFilePath + "not found ！");
        }
    }

}
