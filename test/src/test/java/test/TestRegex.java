package test;

import com.alibaba.druid.util.PatternMatcher;
import com.alibaba.druid.util.ServletPathMatcher;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

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
}
