/**
 * 
 */
package test;

import com.hujiang.basic.framework.core.util.JsonUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jglue.fluentjson.JsonBuilderFactory;
import org.junit.Test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yangkai
 *
 */
@Slf4j
public class TestJson {
    
    String key = null;
    String value = null;
    String jsonStr = "{\"study_tool\":{  \n" +
            "  \"style\": \"grid/horizontal\",\n" +
            "  \"display\": true,\n" +
            "  \"items\":[  \n" +
            "    {  \n" +
            "      \"id\":\"hjclass\",\n" +
            "      \"name\":\"??\",\n" +
            "      \"scheme\":\"hujiangclass3://hjclass.hujiang.com/\",\n" +
            "      \"icon\":\"http://www.qiniu.com/a.png\",\n" +
            "      \"url\":\"https://itunes.apple.com/cn/app/hu-jiang-wang-xiao-wai-yu/id738227542?l=zh&ls=1&mt=8\",\n" +
            "      \"subtitle\":\"????\"\n" +
            "    },\n" +
            "    {  \n" +
            "      \"id\":\"cctalk\",\n" +
            "      \"name\":\"???\",\n" +
            "      \"scheme\":\"hujiangcctalk://\",\n" +
            "      \"icon\":\"\",\n" +
            "      \"url\":\"https://itunes.apple.com/cn/app/hu-jiangcctalk-hu-dong-zhi/id843666882?l=zh&ls=1&mt=8\",\n" +
            "      \"subtitle\":\"??CCTalk\"\n" +
            "    },\n" +
            "    {  \n" +
            "      \"id\":\"dict\",\n" +
            "      \"name\":\"??\",\n" +
            "      \"scheme\":\"hjdict://\",\n" +
            "      \"icon\":\"\",\n" +
            "      \"url\":\"https://itunes.apple.com/cn/app/hu-jiang-xiaod-ci-dian-ying/id481584414?l=zh&ls=1&mt=8\",\n" +
            "      \"subtitle\":\"???D??\"\n" +
            "    },\n" +
            "    {  \n" +
            "      \"id\":\"cichang\",\n" +
            "      \"name\":\"??\",\n" +
            "      \"scheme\":\"cichang://\",\n" +
            "      \"icon\":\"\",\n" +
            "      \"url\":\"https://itunes.apple.com/cn/app/hu-jiang-kai-xin-ci-chang/id635206028?l=zh&ls=1&mt=8\",\n" +
            "      \"subtitle\":\"??????\"\n" +
            "    }\n" +
            "  ]\n" +
            "}}]\n";
    String jsonStrArr = "[{\"study_tool\":{  \n" +
            "  \"style\": \"grid/horizontal\",\n" +
            "  \"display\": true,\n" +
            "  \"items\":[  \n" +
            "    {  \n" +
            "      \"id\":\"hjclass\",\n" +
            "      \"name\":\"??\",\n" +
            "      \"scheme\":\"hujiangclass3://hjclass.hujiang.com/\",\n" +
            "      \"icon\":\"http://www.qiniu.com/a.png\",\n" +
            "      \"url\":\"https://itunes.apple.com/cn/app/hu-jiang-wang-xiao-wai-yu/id738227542?l=zh&ls=1&mt=8\",\n" +
            "      \"subtitle\":\"????\"\n" +
            "    },\n" +
            "    {  \n" +
            "      \"id\":\"cctalk\",\n" +
            "      \"name\":\"???\",\n" +
            "      \"scheme\":\"hujiangcctalk://\",\n" +
            "      \"icon\":\"\",\n" +
            "      \"url\":\"https://itunes.apple.com/cn/app/hu-jiangcctalk-hu-dong-zhi/id843666882?l=zh&ls=1&mt=8\",\n" +
            "      \"subtitle\":\"??CCTalk\"\n" +
            "    },\n" +
            "    {  \n" +
            "      \"id\":\"dict\",\n" +
            "      \"name\":\"??\",\n" +
            "      \"scheme\":\"hjdict://\",\n" +
            "      \"icon\":\"\",\n" +
            "      \"url\":\"https://itunes.apple.com/cn/app/hu-jiang-xiaod-ci-dian-ying/id481584414?l=zh&ls=1&mt=8\",\n" +
            "      \"subtitle\":\"???D??\"\n" +
            "    },\n" +
            "    {  \n" +
            "      \"id\":\"cichang\",\n" +
            "      \"name\":\"??\",\n" +
            "      \"scheme\":\"cichang://\",\n" +
            "      \"icon\":\"\",\n" +
            "      \"url\":\"https://itunes.apple.com/cn/app/hu-jiang-kai-xin-ci-chang/id635206028?l=zh&ls=1&mt=8\",\n" +
            "      \"subtitle\":\"??????\"\n" +
            "    }\n" +
            "  ]\n" +
            "}}]\n";


    String s = "{\n" +
            "    \"style\": \"grid/horizontal\",\n" +
            "    \"display\": true,\n" +
            "    \"items\": [\n" +
            "        {\n" +
            "            \"id\": \"hjclass\",\n" +
            "            \"name\": \"上课111\",\n" +
            "            \"scheme\": \"hujiangclass3://hjclass.hujiang.com/\",\n" +
            "            \"icon\": \"http://www.qiniu.com/a.png\",\n" +
            "            \"url\": \"https://itunes.apple.com/cn/app/hu-jiang-wang-xiao-wai-yu/id738227542?l=zh&ls=1&mt=8\",\n" +
            "            \"subtitle\": \"沪江网校\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": \"cctalk\",\n" +
            "            \"name\": \"直播课111\",\n" +
            "            \"scheme\": \"hujiangcctalk://\",\n" +
            "            \"icon\": \"\",\n" +
            "            \"url\": \"https://itunes.apple.com/cn/app/hu-jiangcctalk-hu-dong-zhi/id843666882?l=zh&ls=1&mt=8\",\n" +
            "            \"subtitle\": \"沪江CCTalk\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": \"dict\",\n" +
            "            \"name\": \"查词111\",\n" +
            "            \"scheme\": \"hjdict://\",\n" +
            "            \"icon\": \"\",\n" +
            "            \"url\": \"https://itunes.apple.com/cn/app/hu-jiang-xiaod-ci-dian-ying/id481584414?l=zh&ls=1&mt=8\",\n" +
            "            \"subtitle\": \"沪江小D词典\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": \"cichang\",\n" +
            "            \"name\": \"背词111\",\n" +
            "            \"scheme\": \"cichang://\",\n" +
            "            \n" +
            "        }\n" +
            "    ]\n" +
            "}";


    /**
     * 将data装换成json格式
     * @return
     */
    public static String wxData2Json(String data) {
        try {
            if (StringUtils.isNotBlank(data)) {
                List<String> elems = new ArrayList<>();
                Arrays.asList(StringUtils.split(data, ",")).stream().forEach(content -> {
                    StringBuilder sb = new StringBuilder();
                    //like this --> first:有小伙伴回复了你|||#000000
                    String[] text = StringUtils.split(content, ":");
                    if (text.length == 0) {
                        text = new String[]{"", ""};
                    } else if (text.length == 1) {
                        text = new String[]{text[0], ""};
                    }

                    sb.append("\"").append(text[0]).append("\"").append(":");
                    //like this --> 有小伙伴回复了你|||#000000
                    if (text[1].contains("|||")) {
                        //1.有小伙伴回复了你|||#000000
                        //2.|||
                        //3. |||
                        //4.有小伙伴回复了你|||
                        //5.|||#000000
                        String[] pairs = StringUtils.split(text[1], "|||");
                        //兼容逻辑，防止出现异常（如："keyword3:|||,keyword4:|||" 会出现异常）
                        if (pairs.length == 0) {
                            pairs = new String[]{"", ""};
                        } else if (pairs.length == 1) {
                            if (pairs[0].contains("#")) {
                                pairs = new String[]{"", pairs[0]};
                            } else {
                                pairs = new String[]{pairs[0], ""};
                            }
                        }

                        sb.append("{");
                        sb.append("\"value\"").append(":").append("\"").append(pairs[0].replaceAll("&colon;", ":").replaceAll("&comma;", ",")).append("\"").append(",");
                        sb.append("\"color\"").append(":").append("\"").append(pairs[1]).append("\"");
                        sb.append("}");
                    } else {
                        sb.append("{");
                        sb.append("\"value\"").append(":").append("\"").append(text[1]).append("\"").append(",");
                        sb.append("\"color\"").append(":").append("\"\"");
                        sb.append("}");
                    }

                    elems.add(sb.toString());
                });

                return StringUtils.join(elems, ",");
            }
        } catch (Exception e) {
            log.error(String.format("invalid template data: %s, error stack: ", data), e);
        }

        return null;
    }


    @Test
    public void test() {
       String s ="{\"study_tool\":{  \n" +
               "  \"style\": \"grid/horizontal\",\n" +
               "  \"display\": true,\n" +
               "  \"items\":[  \n" +
               "    {  \n" +
               "      \"id\":\"hjclass\",\n" +
               "      \"name\":\"??\",\n" +
               "      \"scheme\":\"hujiangclass3://hjclass.hujiang.com/\",\n" +
               "      \"icon\":\"http://www.qiniu.com/a.png\",\n" +
               "      \"url\":\"https://itunes.apple.com/cn/app/hu-jiang-wang-xiao-wai-yu/id738227542?l=zh&ls=1&mt=8\",\n" +
               "      \"subtitle\":\"????\"\n" +
               "    },\n" +
               "    {  \n" +
               "      \"id\":\"cctalk\",\n" +
               "      \"name\":\"???\",\n" +
               "      \"scheme\":\"hujiangcctalk://\",\n" +
               "      \"icon\":\"\",\n" +
               "      \"url\":\"https://itunes.apple.com/cn/app/hu-jiangcctalk-hu-dong-zhi/id843666882?l=zh&ls=1&mt=8\",\n" +
               "      \"subtitle\":\"??CCTalk\"\n" +
               "    },\n" +
               "    {  \n" +
               "      \"id\":\"dict\",\n" +
               "      \"name\":\"??\",\n" +
               "      \"scheme\":\"hjdict://\",\n" +
               "      \"icon\":\"\",\n" +
               "      \"url\":\"https://itunes.apple.com/cn/app/hu-jiang-xiaod-ci-dian-ying/id481584414?l=zh&ls=1&mt=8\",\n" +
               "      \"subtitle\":\"???D??\"\n" +
               "    },\n" +
               "    {  \n" +
               "      \"id\":\"cichang\",\n" +
               "      \"name\":\"??\",\n" +
               "      \"scheme\":\"cichang://\",\n" +
               "      \"icon\":\"\",\n" +
               "      \"url\":\"https://itunes.apple.com/cn/app/hu-jiang-kai-xin-ci-chang/id635206028?l=zh&ls=1&mt=8\",\n" +
               "      \"subtitle\":\"??????\"\n" +
               "    }\n" +
               "  ]\n" +
               "}}\n";
       
       try{
           JSONObject jsonObject = JSONObject.parseObject(s);
         
           System.out.println("JSONObject"+jsonObject);   
           

       } catch(Exception e){
           System.out.println(e);   
       }

       // Assert.assertEquals(true, s.trim().indexOf("{") == 0 && s.trim().lastIndexOf("}") == s.trim().length() - 1);

    }
    
    @Test
    public void test1_1() {
       String s ="          {\"study_tool\":{  \n" +
               "  \"style\": \"grid/horizontal\",\n" +
               "  \"display\": true,\n" +
               "  \"items\":[  \n" +
               "    {  \n" +
               "      \"id\":\"hjclass\",\n" +
               "      \"name\":\"??\",\n" +
               "      \"scheme\":\"hujiangclass3://hjclass.hujiang.com/\",\n" +
               "      \"icon\":\"http://www.qiniu.com/a.png\",\n" +
               "      \"url\":\"https://itunes.apple.com/cn/app/hu-jiang-wang-xiao-wai-yu/id738227542?l=zh&ls=1&mt=8\",\n" +
               "      \"subtitle\":\"????\"\n" +
               "    },\n" +
               "    {  \n" +
               "      \"id\":\"cctalk\",\n" +
               "      \"name\":\"???\",\n" +
               "      \"scheme\":\"hujiangcctalk://\",\n" +
               "      \"icon\":\"\",\n" +
               "      \"url\":\"https://itunes.apple.com/cn/app/hu-jiangcctalk-hu-dong-zhi/id843666882?l=zh&ls=1&mt=8\",\n" +
               "      \"subtitle\":\"??CCTalk\"\n" +
               "    },\n" +
               "    {  \n" +
               "      \"id\":\"dict\",\n" +
               "      \"name\":\"??\",\n" +
               "      \"scheme\":\"hjdict://\",\n" +
               "      \"icon\":\"\",\n" +
               "      \"url\":\"https://itunes.apple.com/cn/app/hu-jiang-xiaod-ci-dian-ying/id481584414?l=zh&ls=1&mt=8\",\n" +
               "      \"subtitle\":\"???D??\"\n" +
               "    },\n" +
               "    {  \n" +
               "      \"id\":\"cichang\",\n" +
               "      \"name\":\"??\",\n" +
               "      \"scheme\":\"cichang://\",\n" +
               "      \"icon\":\"\",\n" +
               "      \"url\":\"https://itunes.apple.com/cn/app/hu-jiang-kai-xin-ci-chang/id635206028?l=zh&ls=1&mt=8\",\n" +
               "      \"subtitle\":\"??????\"\n" +
               "    }\n" +
               "  ]\n" +
               "}}\n";
       
       try{
           JSONArray ja = JSONArray.parseArray(s);
         
           System.out.println("JSONArray"+ja);   
           

       } catch(Exception e){
           System.out.println(e);   
       }

       // Assert.assertEquals(true, s.trim().indexOf("{") == 0 && s.trim().lastIndexOf("}") == s.trim().length() - 1);

    }
    
    
    @Test
    public void test2() {
        ScriptEngineManager sem = new ScriptEngineManager ();
        ScriptEngine se = sem.getEngineByName ("js");
        String jsonstr = "({name: 1, obj: 3,[dd]})";
        try
        {
            System.out.println (se.eval (jsonstr));
        }
        catch (ScriptException e)
        {
            System.out.println ("json格式有误");
        }
    }
    
    @Test
    public void test3() {
        JSONObject jo = new JSONObject();
        jo.put("status", 401);
        jo.put("message", "permission denied");
        jo.put("data", "");
        System.out.println(JSONObject.toJSON(jo));
    }
    
    @Test
    public void test4() {
        String key = "name";
        String value = null;
        String a = "\"" + key + "\"" + ":" + "\"" + value + "\"";
        System.out.println(a);
    }
    
    @Test
    public void test5() {
        
        Pattern pattern = Pattern.compile("^(true|false|TRUE|FALSE)*$"/*,Pattern.CASE_INSENSITIVE*/);  
        Matcher matcher = pattern.matcher("true");  

        System.out.println(matcher.matches());  
    }
    
    @Test
    public void test6() {
        
        boolean isJson = false,isNum = false,isBool = false,isNull = false; 
        
        System.out.println(isJson);
        System.out.println(isNum);
        System.out.println(isBool);
        System.out.println(isNull);
    }
    
    @Test
    public void testNumPattern() {
        
        Pattern pattern2 = Pattern.compile("^[0-9]*$");  
        Matcher matcher2 = pattern2.matcher("2a");  
        System.out.println(matcher2.matches());   
    }
    
    @Test
    public void testJSON() {
        System.out.println(this.jsonStr.indexOf("{"));
        System.out.println(this.jsonStr.lastIndexOf("}"));
        System.out.println(this.jsonStr.length() - 1);
        System.out.println("---------------------------------------------");
        System.out.println(this.jsonStr.trim().indexOf("{"));
        System.out.println(this.jsonStr.trim().lastIndexOf("}"));
        System.out.println(this.jsonStr.trim().length() - 1);
        System.out.println("---------------------------------------------");
        System.out.println(this.jsonStr.startsWith("{",2));
        System.out.println(this.jsonStr.trim().startsWith("{"));
        System.out.println(this.jsonStrArr.trim().startsWith("["));
    }

    @Test
    public void testFastjson() throws Exception {
       JSONObject jo =  JSONObject.parseObject(jsonStr);
        System.out.print(jo);
    }

    @Test
    public void testJackson() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        // objectMapper.enable(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY);
        JsonNode jn = objectMapper.readTree(jsonStr);
        System.out.print(jn);


    }


    @Test
    public void testWXPush() {
        String msg = "{\n" +
                "    \"Body\": {\n" +
                "        \"batchid\": null,\n" +
                "        \"touser\": \"4578993\",\n" +
                "        \"template_id\": \"w0TEGQ0Axu99N6Xbh1t_y3hfeOZ8WuOoZF526cnV40M\",\n" +
                "        \"data\": \"first:亲爱的沪江网校，你收到1条新回复,keyword1:foomfoom,keyword2:2016年11月18日14时32分,keyword3:你好~ “foomfoom”在《【召集令】同学会兼职管理团队招募ing》帖子里回复你啦！,remark:快去看看Ta说了些什么吧\",\n" +
                "        \"url\": \"http://ms.hujiang.com/st/stat/WeixinClickStat.aspx?type=%e5%9b%9e%e5%a4%8d%e6%8f%90%e9%86%92&url=http%3a%2f%2fms.hujiang.com%2fst%2ftopic%2f1710600201470%2f19934259%2f%3fch_source%3dch_streply_hjwxgzh\",\n" +
                "        \"topcolor\": null,\n" +
                "        \"isopenid\": false,\n" +
                "        \"TemplateType\": 0,\n" +
                "        \"WechatAppID\": \"wx99bcf174724d0ae0\"\n" +
                "    },\n" +
                "    \"Initiator\": \"CRMSOA\",\n" +
                "    \"InitiatorID\": 910,\n" +
                "    \"ReceiverIDs\": null,\n" +
                "    \"Receivers\": null,\n" +
                "    \"Token\": \"4F676326-3E51-4555-B444-0D7CA02371CE\",\n" +
                "    \"Topic\": null\n" +
                "}";

        JSONObject jo =  JSONObject.parseObject(msg);
        System.out.print(jo);

    }
    
    public boolean validate(String data) {
        boolean valid = true;
        if (StringUtils.isNotBlank(data)) {
            for (String content : StringUtils.split(data, ",")){
                if (StringUtils.split(content, ":").length !=2) {
                    valid = false;
                    break;
                }
            }
        } else {
            valid = false;
        }
        return  valid;
    }
    
    @Test
    public void testConvertJson() {
      //  String data = "first:有小伙伴回复了你\r\n|||#000000,remark:快去看看吧|||#000000,keyword1:暗夜之白雪\r\n|||,keyword2:2016-12-06 14&colon;40\r\n|||,keyword3:哦，好吧，因为我想申请一个东西，怕赶不上时间限制\r\n|||";
        String data = "first:有小伙伴回复了你|||#000000,remark:快去看看吧|||#000000,keyword1:暗夜之白雪|||,keyword2:2016-12-06 14&colon;40|||,keyword3:哦，好吧，因为我想申请一个东西，怕赶不上时间限制|||";
      //  String data = "first:,remark:快去看看吧|||#000000,keyword1:暗夜之白雪|||,keyword2:2016-12-06 14&colon;40|||,keyword3:哦，好吧，因为我想申请一个东西，怕赶不上时间限制|||";
       // String data = "first:,remark:快去看看吧|||#000000,keyword1:暗夜之白雪,keyword2:2016-12-06 14&colon;40,keyword3:哦，好吧，因为我想申请一个东西，怕赶不上时间限制";
/*       System.out.println(Utils.isNotBlank(data) && Utils.split(Utils.split(data, ",")[1], ":").length ==2);
       System.out.println(Utils.isNotBlank(data) && Utils.split(Utils.split(data, ",")[0], ":").length ==2);*/
        System.out.println(validate(data));
        String[] ss1 = StringUtils.split(data, ",");
        String[] ss2 = StringUtils.split(ss1[0], ":");

        
        String[] ss = StringUtils.split(data, ",");
        List<String> elems = new ArrayList<>();
        String blank = null;
        Arrays.asList(ss).stream().forEach(content ->{
            StringBuilder sb = new StringBuilder();
            // content like this --> first:有小伙伴回复了你\r\n|||#000000
            String[] text = StringUtils.split(content, ":");
            sb.append("\"").append(text[0]).append("\"").append(":");
             if (text.length == 2) {
                 //like this --> 有小伙伴回复了你\r\n|||#000000
                 String[] pairs = StringUtils.split(text[1], "|||");
                sb.append("{");
                sb.append("\"value\"").append(":").append("\"").append(pairs[0].replaceAll("&colon;", ":").replaceAll("&comma;", ",")).append("\"").append(",");
                sb.append("\"color\"").append(":");
                if (pairs.length == 2) {
                   sb.append("\"").append(pairs[1]).append("\"");
                } else {
                 // sb.append(blank);
                    sb.append("\"\"");
                }
                sb.append("}");
            } else {
               // sb.append(blank);
                sb.append("\"\"");
            }
             elems.add(sb.toString());
        });
        JSONObject jo = JSONObject.parseObject("{" + StringUtils.join(elems, ",") + "}");
        System.out.println(jo);
        System.out.println("{"+ StringUtils.join(elems, ",") + "}");
    }
    
    @Test
    public void testSplit() {
/*        String data = "first:有小伙伴回复了你";
        String[] ss = data.split(",");
        String[] string  = ss[0].split(":");
        System.out.println(ss);*/
        
/*        String str = "xxx";
        String[] ss = str.split(",");
        System.out.println(ss[1]);*/
        
        String str = "有小伙伴回复了你|||#000000";
       // String[] ss =Utils.splitByWholeSeparator(str, "|||");
        String[] ss =StringUtils.split(str, "|||");
        //String[] ss = str.split("|||");
        System.out.println(ss);
    }
    
    @Test
    public void testColl() {
        String touser = "yangkai,kerwin,alisa";
        Set<String> originUserIds = new HashSet<>(Arrays.asList(StringUtils.split(touser, ",")));
        Set<String> unshieldedUserIds = new HashSet<>(Arrays.asList(StringUtils.split(touser, ",")));
        originUserIds.remove("yangkai");
        System.out.println(originUserIds);
        System.out.println( unshieldedUserIds );
    }
    
    @Test
    public void testFluentJson() {
        JsonObject jsonObject = 
                JsonBuilderFactory.buildObject()
                    .add("prop1", "1")
                    .add("prop2", 2)
                    .addNull("prop3")
                    .add("prop4", (String)null)
                    .addObject("prop5")
                        .add("np1", 4)
                        .end()
                    .addArray("prop6")
                        .addObject()
                            .end()
                        .add("ae1")
                    .end()
                .getJson();
        
        System.out.println(jsonObject.toString());
    }
    
    @Test
    public void testAllSplit() {
        
        String[] s1 = ":ab:cd:ef::".split(":");
        String[] s2 = StringUtils.split(":ab:cd:ef::", ":");
        String[] s3 = StringUtils.splitByWholeSeparator(":ab:cd:ef::", ":");
        String[] s4 = StringUtils.splitPreserveAllTokens(":ab:cd:ef::", ":");
        String[] s5 = StringUtils.split("ab:", ":");
        System.out.println(":ab:cd:ef::".split(":").length);//末尾分隔符全部忽略    
        System.out.println(":ab:cd:ef::".split(":",-1).length);//不忽略任何一个分隔符    
        System.out.println(StringUtils.split(":ab:cd:ef::",":").length);//最前面的和末尾的分隔符全部都忽略,apache commons   
        System.out.println(StringUtils.splitByWholeSeparator(":ab:cd:ef::",":").length);
        System.out.println(StringUtils.splitPreserveAllTokens(":ab:cd:ef::",":").length);//不忽略任何一个分隔符 apache commons 
    }
    
    @Test
    public void testJoinObject() {
        System.out.println(StringUtils.join(new User[]{new User("yk", 18),new User("gwj", 18)}, ","));

    }
    
    
    public static class User {
        private String name;
        private Integer age;
        public User(String name, Integer age) {
            this.name = name;
            this.age = age;
        }
        
        @Override
        public String toString() {
            return "User[name: " + name + ", age: " + age + "]";
        }
        
    }
    
    @Data
    @NoArgsConstructor
    public static class Employee {
        private String name;
        private int age;
        private long size;
    } 
    
    @Test
    public void testParse() {
    	String a = "{\"name\":\"yangkai\", \"age\":28, \"company\":\"hijaing\", \"size\":null}";
    	
    	Employee employee = JSONObject.parseObject(a, Employee.class);
    	System.out.println(employee);
    	
    }
    
    @Test
    public void testJsonFormat() {
        //String data = "first:有小伙伴回复了你\r\n|||#000000,remark:快去看看吧|||#000000,keyword1:暗夜之白雪\r\n|||,keyword2:2016-12-06 14&colon;40\r\n|||,keyword3:哦，好吧，因为我想申请一个东西，怕赶不上时间限制\r\n|||";
        String data = "first:有小伙伴回复了你|||#000000,remark:快去看看吧|||#000000,keyword1:暗夜之白雪|||,keyword2:2016-12-06 14&colon;40|||,keyword3:哦，好吧，因为我想申请一个东西，怕赶不上时间限制|||";
        //String data = "first:,remark:快去看看吧|||#000000,keyword1:暗夜之白雪|||,keyword2:2016-12-06 14&colon;40|||,keyword3:哦，好吧，因为我想申请一个东西，怕赶不上时间限制|||";
        //String data = "first:,remark:快去看看吧|||#000000,keyword1:暗夜之白雪,keyword2:2016-12-06 14&colon;40,keyword3:哦，好吧，因为我想申请一个东西，怕赶不上时间限制";
        List<String> elems = new ArrayList<>();
        Arrays.asList(StringUtils.split(data, ",")).stream().forEach(content -> {
            StringBuilder sb = new StringBuilder();
            //like this --> first:有小伙伴回复了你|||#000000
            String[] text = StringUtils.split(content, ":");
            sb.append("\"").append(text[0]).append("\"").append(":");
            if (text.length == 2) {
                //like this --> 有小伙伴回复了你|||#000000
                String[] pairs = StringUtils.split(text[1], "|||");
                sb.append("{");
                sb.append("\"value\"").append(":").append("\"").append(pairs[0].replaceAll("&colon;", ":").replaceAll("&comma;", ",")).append("\"").append(",");
                sb.append("\"color\"").append(":");
                if (pairs.length == 2) {
                    sb.append("\"").append(pairs[1]).append("\"");
                } else {
                    sb.append("\"\"");
                }
                sb.append("}");
            } else {
                sb.append("\"\"");
            }
            elems.add(sb.toString());
        });
        
        System.out.println(StringUtils.join(elems, ",")); 
        JSONObject jo = JSONObject.parseObject("{" + StringUtils.join(elems, ",") + "}", Feature.OrderedField);
        JSONObject jo2 = jo.getJSONObject("first");
        System.out.println(jo2);
        System.out.println(jo.get("first"));
    }
    
    
    @Test
    public void test9() {

        String data = "";
        System.out.println(NotifyCenterWXJsonUtils.wxData2JSON(data));

        data = "first";
        System.out.println(NotifyCenterWXJsonUtils.wxData2JSON(data));

        data = "first:";
        System.out.println(NotifyCenterWXJsonUtils.wxData2JSON(data));

        data = ":hello";
        System.out.println(NotifyCenterWXJsonUtils.wxData2JSON(data));

        data = "first:|||";
        System.out.println(NotifyCenterWXJsonUtils.wxData2JSON(data));

        data = "first:你预约的课已开播";
        System.out.println(NotifyCenterWXJsonUtils.wxData2JSON(data));

        data = "first:你预约的课已开播|||#000000,remark:快去参加吧！点击进入|||#000000,keyword1:测试|||#000000,keyword2:2017年05月27日 11&colon;50 AM-12&colon;50 PM|||#000000,keyword3: |||,keyword4: |||";
        System.out.println(NotifyCenterWXJsonUtils.wxData2JSON(data));

        data = "first:你预约的课已开播|||#000000,remark:快去参加吧！点击进入|||#000000,keyword1:测试|||#000000,keyword2:2017年05月27日 11&colon;50 AM-12&colon;50 PM|||#000000,keyword3:||| ,keyword4:||| ";
        System.out.println(NotifyCenterWXJsonUtils.wxData2JSON(data));

        data ="first:你预约的课已开播|||#000000,remark:快去参加吧！点击进入|||#000000,keyword1:测试|||#000000,keyword2:2017年05月27日 11&colon;50 AM-12&colon;50 PM|||#000000,keyword3:|||,keyword4:|||";
        System.out.println(NotifyCenterWXJsonUtils.wxData2JSON(data));

        data ="first:你预约的课已开播|||#000000,remark:快去参加吧！点击进入|||#000000,keyword1:测试|||#000000,keyword2:2017年05月27日 11&colon;50 AM-12&colon;50 PM|||#000000,keyword3:|||,keyword4:shit";
        System.out.println(NotifyCenterWXJsonUtils.wxData2JSON(data));

        data ="first:你预约的课已开播|||#000000,remark:快去参加吧！点击进入|||#000000,keyword1:测试|||#000000,keyword2:2017年05月27日 11&colon;50 AM-12&colon;50 PM|||#000000,keyword3:|||,keyword4:shit";
        System.out.println(NotifyCenterWXJsonUtils.wxData2JSON(data));

        data ="first:你预约的课已开播|||#000000,remark:快去参加吧！点击进入|||#000000,keyword1:测试|||#000000,keyword2:2017年05月27日 11&colon;50 AM-12&colon;50 PM|||#000000,keyword3:|||#ffffff,keyword4:shit";
        System.out.println(NotifyCenterWXJsonUtils.wxData2JSON(data));

    }

    public static class NotifyCenterWXJsonUtils {
        public static String wxData2JSON(String data) {
            if (StringUtils.isNotBlank(data)) {
                List<String> elems = new ArrayList<>();
                Arrays.asList(StringUtils.split(data, ",")).stream().forEach(content -> {
                    StringBuilder sb = new StringBuilder();
                    //like this --> first:有小伙伴回复了你|||#000000
                    String[] text = StringUtils.split(content, ":");
                    if (text.length == 0) {
                        text = new String[]{"", ""};
                    } else if (text.length == 1) {
                        text = new String[] {text[0], ""};
                    }

                    sb.append("\"").append(text[0]).append("\"").append(":");
                    //like this --> 有小伙伴回复了你|||#000000
                    if (text[1].contains("|||")) {
                        //1.有小伙伴回复了你|||#000000
                        //2.|||
                        //3. |||
                        //4.有小伙伴回复了你|||
                        //5.|||#000000
                        String[] pairs = StringUtils.split(text[1], "|||");
                        //兼容逻辑，防止出现异常（如："keyword3:|||,keyword4:|||" 会出现异常）
                        if (pairs.length == 0) {
                            pairs = new String[]{"", ""};
                        } else if (pairs.length == 1) {
                            if (pairs[0].contains("#")) {
                                pairs = new String[]{"", pairs[0]};
                            } else {
                                pairs = new String[]{pairs[0], ""};
                            }
                        }

                        sb.append("{");
                        sb.append("\"value\"").append(":").append("\"").append(pairs[0].replaceAll("&colon;", ":").replaceAll("&comma;", ",")).append("\"").append(",");
                        sb.append("\"color\"").append(":").append("\"").append(pairs[1]).append("\"");
                        sb.append("}");
                    } else {
                        sb.append("{");
                        sb.append("\"value\"").append(":").append("\"").append(text[1]).append("\"").append(",");
                        sb.append("\"color\"").append(":").append("\"\"");
                        sb.append("}");
                    }

                    elems.add(sb.toString());
                });

                return StringUtils.join(elems, ",");
            }

            return null;
        }


        public static String wxData2JSON_V2(String data) {
            try {
                if (StringUtils.isNotBlank(data)) {
                    List<String> elems = new ArrayList<>();
                    Arrays.asList(StringUtils.split(data, ",")).stream().forEach(content -> {
                        StringBuilder sb = new StringBuilder();
                        //like this --> first:有小伙伴回复了你|||#000000
                        String[] text = StringUtils.split(content, ":");
                        if (text.length == 0) {
                            text = new String[]{"", ""};
                        } else if (text.length == 1) {
                            text = new String[]{text[0], ""};
                        }

                        sb.append("\"").append(text[0].trim()).append("\"").append(":");
                        //like this --> 有小伙伴回复了你|||#000000
                        if (text[1].contains("|||")) {
                            //1.有小伙伴回复了你|||#000000
                            //2.|||
                            //3. |||
                            //4.有小伙伴回复了你|||
                            //5.|||#000000
                            String[] pairs = StringUtils.split(text[1], "|||");
                            //兼容逻辑，防止出现异常（如："keyword3:|||,keyword4:|||" 会出现异常）
                            if (pairs.length == 0) {
                                pairs = new String[]{"", ""};
                            } else if (pairs.length == 1) {
                                if (pairs[0].contains("#")) {
                                    pairs = new String[]{"", pairs[0]};
                                } else {
                                    pairs = new String[]{pairs[0], ""};
                                }
                            }

                            if (StringUtils.isNotBlank(pairs[1])) {
                                pairs[1] = pairs[1].trim();
                                if (!pairs[1].startsWith("#")) {
                                    pairs[1] = "#" + pairs[1];
                                }
                            }

                            sb.append("{");
                            sb.append("\"value\"").append(":").append("\"").append(pairs[0].trim().replaceAll("&colon;", ":").replaceAll("&comma;", ",")).append("\"").append(",");
                            sb.append("\"color\"").append(":").append("\"").append(pairs[1]).append("\"");
                            sb.append("}");
                        } else {
                            sb.append("{");
                            sb.append("\"value\"").append(":").append("\"").append(text[1]).append("\"").append(",");
                            sb.append("\"color\"").append(":").append("\"\"");
                            sb.append("}");
                        }

                        elems.add(sb.toString());
                    });

                    return StringUtils.join(elems, ",");
                }
            } catch (Exception e) {
                log.error(String.format("invalid template data: %s, error stack: ", data), e);
            }

            return null;
        }

        public static String wxData2JSON_V3(String data) {
            try {
                if (StringUtils.isNotBlank(data)) {
                    List<String> elems = new ArrayList<>();
                    //like this --> first:xxx|||#020202,remark:xxx|||,keyword1:xxx|||#020202,keyword2:xxx|||#020202,keyword3:xxx|||#020202
                    Arrays.asList(StringUtils.split(data, ",")).stream().forEach(content -> {
                        if (StringUtils.isNotBlank(content)) {
                            StringBuilder sb = new StringBuilder();
                            //like this --> first:有小伙伴回复了你|||#000000
                            String[] text = StringUtils.split(content, ":");
                            if (text.length == 0) {
                                text = new String[]{"", ""};
                            } else if (text.length == 1) {
                                text = new String[]{text[0], ""};
                            }

                            sb.append("\"").append(text[0].trim()).append("\"").append(":");
                            //like this --> 有小伙伴回复了你|||#000000
                            //1.有小伙伴回复了你|||#000000
                            //2.|||
                            //3. |||
                            //4.有小伙伴回复了你|||
                            //5.|||#000000
                            String[] pairs = StringUtils.split(text[1], "|||");
                            //兼容逻辑，防止出现异常（如："keyword3:|||,keyword4:|||" 会出现异常）
                            if (pairs.length == 0) {
                                pairs = new String[]{"", ""};
                            } else if (pairs.length == 1) {
                                if (pairs[0].contains("#")) {
                                    pairs = new String[]{"", pairs[0]};
                                } else {
                                    pairs = new String[]{pairs[0], ""};
                                }
                            }

                            if (StringUtils.isNotBlank(pairs[1])) {
                                pairs[1] = pairs[1].trim();
                                if (!pairs[1].startsWith("#")) {
                                    pairs[1] = "#" + pairs[1];
                                }
                            }

                            sb.append("{");
                            sb.append("\"value\"").append(":").append("\"").append(pairs[0].trim().replaceAll("&colon;", ":").replaceAll("&comma;", ",")).append("\"").append(",");
                            sb.append("\"color\"").append(":").append("\"").append(pairs[1]).append("\"");
                            sb.append("}");

                            elems.add(sb.toString());
                        }
                    });

                    return StringUtils.join(elems, ",");
                }
            } catch (Exception e) {
                log.error(String.format("invalid template data: %s, error stack: ", data), e);
                //throw new AppException(ErrorCode.invalid_args.errorCode(), ErrorCode.invalid_args.errorMsg());
            }

            return null;
        }
    }

    @Test
    public void test10() {
        String data = "first:  anneyuholi|||020202 ,remark:||| ,keyword1:中级日语会话【外教随到随学班】 |||020202 ,keyword2:欢迎同学加入中级日语会话【外教随到随学班】 。班级将于2017-07-14开班，2017-09-12关班。同学可以在讨论区查看班级使用指南，做好开课准备哦！|||020202 ,keyword3:2017-07-14|||020202 ";
        System.out.println("{" + NotifyCenterWXJsonUtils.wxData2JSON_V2(data) + "}");
    }

    @Test
    public void test11() {

        String data = "";
        System.out.println("1--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V2(data) + "}");
        System.out.println("1--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V3(data) + "}");
        System.out.println("-----------------------------------------------------------------------------------------");

        data = "first";
        System.out.println("2--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V2(data) + "}");
        System.out.println("2--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V3(data) + "}");
        System.out.println("-----------------------------------------------------------------------------------------");

        data = "first:";
        System.out.println("3--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V2(data) + "}");
        System.out.println("3--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V3(data) + "}");
        System.out.println("-----------------------------------------------------------------------------------------");

        data = ":你预约的课已开播";
        System.out.println("4--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V2(data) + "}");
        System.out.println("4--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V3(data) + "}");
        System.out.println("-----------------------------------------------------------------------------------------");

        data = ":";
        System.out.println("5--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V2(data) + "}");
        System.out.println("5--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V3(data) + "}");
        System.out.println("-----------------------------------------------------------------------------------------");

        data = ": ";
        System.out.println("6--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V2(data) + "}");
        System.out.println("6--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V3(data) + "}");
        System.out.println("-----------------------------------------------------------------------------------------");

        data = " :";
        System.out.println("7--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V2(data) + "}");
        System.out.println("7--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V3(data) + "}");
        System.out.println("-----------------------------------------------------------------------------------------");

        data = " : ";
        System.out.println("8--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V2(data) + "}");
        System.out.println("8--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V3(data) + "}");
        System.out.println("-----------------------------------------------------------------------------------------");

        data = "first:|||";
        System.out.println("9--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V2(data) + "}");
        System.out.println("9--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V3(data) + "}");
        System.out.println("-----------------------------------------------------------------------------------------");

        data = "first:你预约的课已开播";
        System.out.println("10--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V2(data) + "}");
        System.out.println("10--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V3(data) + "}");
        System.out.println("-----------------------------------------------------------------------------------------");

        data = "first:你预约的课已开播|||";
        System.out.println("11--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V2(data) + "}");
        System.out.println("11--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V3(data) + "}");
        System.out.println("-----------------------------------------------------------------------------------------");

        data = "first:你预约的课已开播|||#ffffff";
        System.out.println("12--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V2(data) + "}");
        System.out.println("12--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V3(data) + "}");
        System.out.println("-----------------------------------------------------------------------------------------");

        data = "first:|||#ffffff";
        System.out.println("13--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V2(data) + "}");
        System.out.println("13--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V3(data) + "}");
        System.out.println("-----------------------------------------------------------------------------------------");

        data = " :你预约的课已开播";
        System.out.println("14--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V2(data) + "}");
        System.out.println("14--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V3(data) + "}");
        System.out.println("-----------------------------------------------------------------------------------------");

        data = ":|||";
        System.out.println("15--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V2(data) + "}");
        System.out.println("15--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V3(data) + "}");
        System.out.println("-----------------------------------------------------------------------------------------");

        data = " :|||";
        System.out.println("16--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V2(data) + "}");
        System.out.println("16--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V3(data) + "}");
        System.out.println("-----------------------------------------------------------------------------------------");

        data = ": |||";
        System.out.println("17--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V2(data) + "}");
        System.out.println("17--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V3(data) + "}");
        System.out.println("-----------------------------------------------------------------------------------------");

        data = " : |||";
        System.out.println("18--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V2(data) + "}");
        System.out.println("18--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V3(data) + "}");
        System.out.println("-----------------------------------------------------------------------------------------");

        data = " : ||| ";
        System.out.println("19--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V2(data) + "}");
        System.out.println("19--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V3(data) + "}");
        System.out.println("-----------------------------------------------------------------------------------------");

        data = ":|||000000";
        System.out.println("20--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V2(data) + "}");
        System.out.println("20--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V3(data) + "}");
        System.out.println("-----------------------------------------------------------------------------------------");

        data = ":你预约的课已开播|||";
        System.out.println("21--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V2(data) + "}");
        System.out.println("21--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V3(data) + "}");
        System.out.println("-----------------------------------------------------------------------------------------");

        data = ":你预约的课已开播|||000000";
        System.out.println("22--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V2(data) + "}");
        System.out.println("22--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V3(data) + "}");
        System.out.println("-----------------------------------------------------------------------------------------");

        data = ":你预约的课已开播||| 000000 ";
        System.out.println("23--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V2(data) + "}");
        System.out.println("23--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V3(data) + "}");
        System.out.println("-----------------------------------------------------------------------------------------");

        data = "first:你预约的课已开播||| #ffffff ";
        System.out.println("24--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V2(data) + "}");
        System.out.println("24--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V3(data) + "}");
        System.out.println("-----------------------------------------------------------------------------------------");

        data = "first:你预约的课已开播||| ffffff ";
        System.out.println("25--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V2(data) + "}");
        System.out.println("25--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V3(data) + "}");
        System.out.println("-----------------------------------------------------------------------------------------");

        data = "first:你预约的课已开播|||ffffff ";
        System.out.println("26--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V2(data) + "}");
        System.out.println("26--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V3(data) + "}");
        System.out.println("-----------------------------------------------------------------------------------------");

        data = " first : 你预约的课已开播 ||| #ffffff ";
        System.out.println("27--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V2(data) + "}");
        System.out.println("27--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V3(data) + "}");
        System.out.println("-----------------------------------------------------------------------------------------");

        data = " first : 你预约的课已开播 ";
        System.out.println("28--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V2(data) + "}");
        System.out.println("28--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V3(data) + "}");
        System.out.println("-----------------------------------------------------------------------------------------");

        data = "first:||| #ffffff ";
        System.out.println("29--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V2(data) + "}");
        System.out.println("29--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V3(data) + "}");
        System.out.println("-----------------------------------------------------------------------------------------");

        data = "first:||| ffffff ";
        System.out.println("30--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V2(data) + "}");
        System.out.println("30--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V3(data) + "}");
        System.out.println("-----------------------------------------------------------------------------------------");

        data = "你预约的课已开播||| ffffff ";
        System.out.println("31--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V2(data) + "}");
        System.out.println("31--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V3(data) + "}");
        System.out.println("-----------------------------------------------------------------------------------------");

        data = "first:你预约的课已开播|||  ffffff ";
        System.out.println("32--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V2(data) + "}");
        System.out.println("32--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V3(data) + "}");
        System.out.println("-----------------------------------------------------------------------------------------");

        data = null;
        System.out.println("33--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V2(data) + "}");
        System.out.println("33--> {" + NotifyCenterWXJsonUtils.wxData2JSON_V3(data) + "}");
        System.out.println("-----------------------------------------------------------------------------------------");
    }

    @Test
    public void test12() {
        String data = ":|||000000";
        String[] ss = StringUtils.split(data, ":");
        System.out.println(ss);

        String s2 = "";
        s2 = s2.trim();
        System.out.println(s2);

        String s3 = "  ";
        s3 = s3.trim();
        System.out.println(s3);
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Student {
        private String name;
        private Integer age;
        private long phone;
        private Date date;
    }

    @Test
    public void test2JsonWithoutGetter() {
        Student s = new Student("yk", 18, 13554576965L, new Date());
        String json1 = JsonUtil.object2JSON(s, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat);
        System.out.println(json1);
        String json2 = JsonUtil.object2JSON(s, SerializerFeature.WriteMapNullValue);
        System.out.println(json2);
    }
}
