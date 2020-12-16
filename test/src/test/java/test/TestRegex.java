package test;

import com.alibaba.druid.util.PatternMatcher;
import com.alibaba.druid.util.ServletPathMatcher;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yangkai on 2017/10/2.
 */
public class TestRegex {
    protected PatternMatcher pathMatcher = new ServletPathMatcher();

    @Test
    public void test() {
        String exclusions = "/do_not_delete/*,*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*";
        String[] patterns = exclusions.split("\\s*,\\s*");
        //String uri = "/do_not_delete/health_check";
        //String uri = "/do_not_delete/health_check/index.js";
      //  String uri = "pass.hjapi.com/yangkai.ico";
        String uri = "/do_not_delete/";
        for (String pattern : patterns) {
            boolean isMatch = pathMatcher.matches(pattern, uri);
            System.out.println(pattern + " --> " + isMatch);
        }

    }

    @Test
    public void test2() {

        System.out.println(StringUtils.replacePattern("{0}, xxx, {name}, yyy{{{age}}}", "/\\{(?!\\{)[^]+\\}(?!\\})/g", "{{$&}}"));
        System.out.println(StringUtils.replacePattern("你好，{0}, (0){{xxx}}, {name}, {{{age}}}, {\"name\": \"yk\"}", "\\{(?!\\{)[^}]+\\}(?!\\})", "{{$0}}"));
    }
}
