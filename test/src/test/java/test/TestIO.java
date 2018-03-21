package test;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by yangkai on 2017/8/5.
 */
public class TestIO {
    private static final String UPLOAD_PATH = File.separator + "tmp" + File.separator;
    private static final String UPLOAD_PATH2 = "/Users/yangkai/";
    private static final int[] IMAGE_SIZE = new int[] { 18, 48, 96, 120, 200 };

    @Test
    public void test() throws IOException {
        File file = new File(UPLOAD_PATH + "timg.jpeg");
        System.out.println(file.getName());
        System.out.println(file.getPath());
        System.out.println(file.getAbsolutePath());

    }

    @Test
    public void test2() throws IOException {
        File file = new File(UPLOAD_PATH + "timg.jpeg");

        for (int size : IMAGE_SIZE) {
            File copyFile = new File(file.getParent() + File.separator + size + "*" + size + "_" + file.getName());
            FileUtils.copyFile(file, copyFile);
            Thumbnails.of(file).size(size, size).outputQuality(0.7f).toFile(copyFile);
           // copyFile.deleteOnExit();
        }
    }
    @Test
    public void test3() throws IOException {
        File file = new File(UPLOAD_PATH2 + "tupu_pkcs8_private_key.pem");
        System.out.println(file.getName());
        System.out.println(file.getPath());
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getParent());
        System.out.println(file.getCanonicalPath());
        System.out.println(file.getParentFile().getAbsolutePath());

    }

    @Test
    public void test4() throws IOException {
        File file = new File(UPLOAD_PATH2 + "tupu_pkcs8_private_key.pem");
        System.out.println(StringUtils.split(file.getName(), '.')[1]);



    }


}
