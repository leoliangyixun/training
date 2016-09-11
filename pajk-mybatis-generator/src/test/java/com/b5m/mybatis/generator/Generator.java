package com.b5m.mybatis.generator;

import org.mybatis.generator.api.ShellRunner;

import java.io.File;

/**
 * 生成工具<br/>
 * 支持无参模式、简略模式和原生模式
 *
 * @author 丹青生
 * @date 2015-9-2
 */
public class Generator {

    public static void main(String[] args) {
        if (args.length == 0) {
            args = new String[]{"resource:generator-mmp.xml"};
        }
        if (args.length == 1 && !isEmpty(args[0]) && !"-h".equalsIgnoreCase(args[0])) {
            String configFile = args[0];
            if (configFile.startsWith("/")) { // Linux绝对路径

            } else if (configFile.length() > 2 && configFile.charAt(1) == ':') { // Windows绝对路径

            } else if (configFile.startsWith("resource:")) { // resource路径
                String folder = String.format("%csrc%cmain%cresources%c", File.separatorChar, File.separatorChar, File.separatorChar, File.separatorChar);
                configFile = new File("").getAbsolutePath() +File.separatorChar + "pajk-mybatis-generator" + folder + configFile.replace("resource:", "");
            } else { // 当前目录
                configFile = new File("").getAbsolutePath() + "\\" + configFile;
            }
            System.out.println("使用配置文件:" + configFile);
            ShellRunner.main(new String[]{"-overwrite", "-verbose", "-configfile", configFile});
        } else {
            ShellRunner.main(args);
        }
    }

    public static boolean isEmpty(String string) {
        return string == null || string.trim().length() == 0;
    }

}

