package com.chw.test.utils;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class HtmlParseWordUtil {

    public static void main(String[] args) throws Exception{

        XWPFDocument doc = new XWPFDocument();
        XWPFParagraph paragraph = doc.createParagraph();

        String html = "<p class=\"a DocDefaults \" style=\"margin-bottom: 0in;\">写一写。</span></p><p class=\"a DocDefaults \" style=\"text-align: center;margin-bottom: 0in;\"><span class=\"a0 \" style=\"color: #000000;\">电</span></p><p class=\"a DocDefaults \" style=\"margin-bottom: 0in;\"><span class=\"a0 \" style=\"color: #000000;\"><span class=\"\" style=\"\">电的用处可大啦！有了电，电灯就会亮，还可以看电视</span>、<span class=\"\" style=\"\">听音乐。爸爸说，有了电，工人叔叔会生产出许多东西，我们的生活会更好。</span></span></p><p class=\"a DocDefaults \" style=\"margin-bottom: 0in;\"><span class=\"a0 \" style=\"color: #000000;\"><span class=\"\" style=\"\">（1）选择正确的音节。</span></span></p><p class=\"a DocDefaults \" style=\"margin-bottom: 0in;\"><span class=\"a0 \" style=\"color: #000000;\"><span class=\"\" style=\"\">就（jiù&nbsp; jìu）________叔（shū&nbsp; sū）________&nbsp;&nbsp;&nbsp;&nbsp;许（xǔ&nbsp;&nbsp; qǔ）________</span></span></p><p class=\"a DocDefaults \" style=\"margin-bottom: 0in;\"><span class=\"a0 \" style=\"color: #000000;\"><span class=\"\" style=\"\">（2）这一段话共有________句。</span></span></p><p class=\"a DocDefaults \" style=\"margin-bottom: 0in;\"><span class=\"a0 \" style=\"color: #000000;\"><span class=\"\" style=\"\">（3）从这一段话中我知道________。</span></span></p>";
        //String html = "<div>my gold <span>you</span><p><span></span></p>just</div>";
        //String html = "<p class=\"a DocDefaults \" style=\"margin-bottom: 0in;\"></span><span class=\"a0 \" style=\"color: #000000;\"><span class=\"\" style=\"\">这类题目是考查学生的课外阅读理解能力。第一题考查学生对拼音的掌握，就，读作jiù，叔，一种身份，许，许多。这一段一共3句话，从这段话中我知道：电的用处可大啦！</span></span></p>";
        //String html = "<p class=\"a DocDefaults \" style=\"margin-bottom: 0in;\"></span><span class=\"a0 \" style=\"color: #000000;;white-space:pre-wrap;\">小小调音师。 </span></p><p class=\"a DocDefaults \" style=\"margin-bottom: 0in;\"><span class=\"a0 \" style=\"color: #000000;\"><span class=\"\" style=\"\">&nbsp;&nbsp;&nbsp; </span><span class=\"\" style=\"font-family: 'MS Gothic';\">“</span><span class=\"\" style=\"\">长</span><span class=\"\" style=\"font-family: 'MS Gothic';\">”</span><span class=\"\" style=\"\">有两个读音：</span><span class=\"\" style=\"font-family: 'MS Gothic';\">①</span><span class=\"\" style=\"\">读________时，可以组词为________；</span><span class=\"\" style=\"font-family: 'MS Gothic';\">②</span><span class=\"\" style=\"\">读________时</span>，<span class=\"\" style=\"\">可以组词为________。</span></span></p>";
        //String html = "<p class=\"a DocDefaults \" style=\"margin-bottom: 0in;\"><span class=\"\" style=\"white-space:pre-wrap;\">动物尾巴的妙用。先连线，再照样子写一写。 </span></span></p><p class=\"a DocDefaults \" style=\"margin-bottom: 0in;\"><span class=\"a0 \" style=\"color: #000000;;white-space:pre-wrap;\"> </span><span class=\"a0 \" style=\"\"><img height=\"40\" id=\"rId11\" src=\"http://jty-question-2.oss-cn-beijing.aliyuncs.com/images/2020/2020-11-27/160648604576519259069.jpeg\" style=\"width:56.4pt;height:40.1pt;visibility:visible;mso-wrap-style:square\" width=\"56\"></span><span class=\"a0 \" style=\"color: #000000;\"><span class=\"\" style=\"\">________&nbsp;&nbsp;&nbsp; 掌握方向</span></span></p><p class=\"a DocDefaults \" style=\"margin-bottom: 0in;\"><span class=\"a0 \" style=\"color: #000000;;white-space:pre-wrap;\"> </span><span class=\"a0 \" style=\"\"><img height=\"29\" id=\"rId12\" src=\"http://jty-question-2.oss-cn-beijing.aliyuncs.com/images/2020/2020-11-27/160648604580357010735.jpeg\" style=\"width:44.15pt;height:28.55pt;visibility:visible;mso-wrap-style:square\" width=\"44\"></span><span class=\"a0 \" style=\"color: #000000;\"><span class=\"\" style=\"\">________&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 表现喜怒哀乐</span></span></p><p class=\"a DocDefaults \" style=\"margin-bottom: 0in;\"><span class=\"a0 \" style=\"color: #000000;;white-space:pre-wrap;\"> </span><span class=\"a0 \" style=\"\"><img height=\"33\" id=\"rId13\" src=\"http://jty-question-2.oss-cn-beijing.aliyuncs.com/images/2020/2020-11-27/160648604583390493148.jpeg\" style=\"width:42.8pt;height:33.3pt;visibility:visible;mso-wrap-style:square\" width=\"43\"></span><span class=\"a0 \" style=\"color: #000000;\"><span class=\"\" style=\"\">________&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;赶走苍蝇和蚊子</span></span></p><p class=\"a DocDefaults \" style=\"margin-bottom: 0in;\"><span class=\"a0 \" style=\"color: #000000;\"><span class=\"\" style=\"\">例：谁的尾巴像耳朵?小鱼的尾巴像耳朵。</span></span></p><p class=\"a DocDefaults \" style=\"margin-bottom: 0in;\"><span class=\"a0 \" style=\"color: #000000;\"><span class=\"\" style=\"\">谁的尾巴像________?________的尾巴像________。</span></span></p>";
        //String html = "<p class=\"a DocDefaults \" style=\"margin-bottom: 0in;\"><span class=\"\" style=\"white-space:pre-wrap;\">读一读，回答问题。 </span></span></p><p class=\"a DocDefaults \" style=\"text-align: center;margin-bottom: 0in;\"><span class=\"a0 \" style=\"color: #000000;\">动物尾巴</span></p><p class=\"a DocDefaults \" style=\"margin-bottom: 0in;\"><span class=\"a0 \" style=\"color: #000000;\"><span class=\"\" style=\"\">&nbsp;&nbsp;&nbsp; 金鱼尾巴左右摆，游来游去多自在。牛的尾巴来回摆，苍蝇蚊子不敢来。小猴尾巴卷树枝，倒着身子往下挂。燕子尾巴像剪刀，飞行方向把握好。</span></span></p><p class=\"a DocDefaults \" style=\"margin-bottom: 0in;\"><span class=\"a0 \" style=\"color: #000000;\"><span class=\"\" style=\"white-space:pre-wrap;\">（1）找出儿歌中的两对意思相反的词。 </span></span></p><p class=\"a DocDefaults \" style=\"margin-bottom: 0in;\"><span class=\"a0 \" style=\"color: #000000;\"><span class=\"\" style=\"white-space:pre-wrap;\">（2）儿歌中写了哪几种动物的尾巴?用横线画一画。 </span></span></p>";

        //Document parse = Jsoup.parse(html);

        Document parse = Jsoup.parse(new File("index.html"), "UTF-8");

        Element body = parse.body();

        System.out.println(body);

        addElementToParagraph(new ElementToParagraphParam(paragraph,body));

        FileOutputStream out = new FileOutputStream("simple.docx");
        doc.write(out);

    }

    private static class ElementToParagraphParam{
        private XWPFParagraph paragraph;
        private Element element;
        private XWPFRun preRun;
        private XWPFRun currentRun;
        private CssStyle parentStyle;
        private boolean preIsBlock;
        private int level;

        private ElementToParagraphParam(XWPFParagraph paragraph, Element element) {
            this(paragraph,element,new CssStyle(),1);
        }

        private ElementToParagraphParam(XWPFParagraph paragraph, Element element,CssStyle cssStyle,int level) {
            this(paragraph,element,null,cssStyle,false,level);
        }

        private ElementToParagraphParam(XWPFParagraph paragraph, Element element, XWPFRun preRun, CssStyle parentStyle, boolean preIsBlock,int level) {
            this.paragraph = paragraph;
            this.element = element;
            this.preRun = preRun;
            this.parentStyle = parentStyle;
            this.preIsBlock = preIsBlock;
            this.level = level;
        }
    }

    private static class CssStyle{
        private int level;
        private int alignCode;
        private CssStyle() {
            this(1,1);
        }
        private CssStyle(int alignCode,int level) {
            this.alignCode = alignCode;
            this.level = level;
        }
    }

    public static ElementToParagraphParam addElementToParagraph(ElementToParagraphParam param) throws IOException, InvalidFormatException {
        String tagName = param.element.normalName();
        if("img".equals(tagName)){
            String src = param.element.attr("src");
            int type = getImageTypeOfUrl(src);
            String height = param.element.attr("height");
            int heightNum = Units.pixelToEMU(isBlank(height) ? 30 : Integer.valueOf(height));
            String width = param.element.attr("width");
            int widthNum = Units.pixelToEMU(isBlank(width) ? 30 : Integer.valueOf(width));
            XWPFRun run = param.paragraph.createRun();
            run.addPicture(getImageStream(src),type,String.valueOf(System.currentTimeMillis()),widthNum,heightNum);
        }else {

            //如果文本对齐发生改变则创建新段落
            int align = transferAlignCode(param.element.attr("style"),param.level,param.parentStyle);
            if(align!=param.parentStyle.alignCode){
                XWPFParagraph paragraph = param.paragraph.getDocument().createParagraph();
                paragraph.setAlignment(ParagraphAlignment.valueOf(align));
                param = new ElementToParagraphParam(paragraph,param.element,new CssStyle(align,param.level),param.level);
            }

            List<Node> nodes = param.element.childNodes();
            for (Node node : nodes) {
                if(node instanceof Element){
                    Element temp = (Element) node;
                    XWPFRun childPreRun = param.currentRun == null ? param.preRun : param.currentRun;
                    ElementToParagraphParam result = addElementToParagraph(new ElementToParagraphParam(
                            param.paragraph,temp,childPreRun,param.parentStyle,param.preIsBlock,param.level+1
                    ));
                    param.preRun=result.currentRun == null ? result.preRun : result.currentRun;
                    param.parentStyle = result.parentStyle;
                    param.paragraph = result.paragraph;
                    param.preIsBlock = temp.isBlock();
                    param.currentRun = null;
                }else if(node instanceof TextNode){
                    if(param.preRun!=null){
                        if(param.preIsBlock || param.element.isBlock()){
                            param.preRun.addCarriageReturn();
                        }
                    }
                    TextNode temp = (TextNode) node;
                    String text = temp.text();
                    if(isBlank(text)){
                        continue;
                    }
                    setText(param,text);
                    param.preRun=param.currentRun;
                    param.currentRun = null;
                }
            }
        }
        return param;
    }

    private static int getImageTypeOfUrl(String url){
        String[] split = url.split("\\.");
        String s = split[split.length - 1];
        if("jpg".equals(s) || "jpeg".equals(s)){
            return 5;
        }else if("png".equals(s)){
            return 6;
        }else if("gif".equals(s)){
            return 8;
        }else {
            throw new RuntimeException("Invalid URL:"+url);
        }
    }

    private static int transferAlignCode(String style,int level,CssStyle parentStyle){
        String align = getCssPatternValue(style, "text-align");
        if(align==null){
            if(parentStyle.level<level){
                return parentStyle.alignCode;
            }
        }else if("right".equals(align)){
            return 3;
        }else if("center".equals(align)){
            return 2;
        }else if("justify".equals(align)){
            return 4;
        }
        return 1;
    }

    private static String getCssPatternValue(String style,String getKey){
        if(isBlank(style)){
            return null;
        }
        String[] cssList = style.split(";");
        for (String css : cssList) {
            String[] pattern = css.split(":");
            if(pattern.length==2){
                String key = pattern[0].trim();
                String value = pattern[1].trim();
                if(getKey.equals(key)){
                    return value;
                }
            }
        }
        return null;
    }

    private static void setText(ElementToParagraphParam param,String text){
        XWPFRun run = param.paragraph.createRun();
        run.setText(text);
        param.currentRun=run;
    }

    private static boolean isTitleTag(String tagName){
        List<String> strings = Arrays.asList("h1", "h2", "h3", "h4", "h5", "h6");
        return strings.contains(tagName);
    }

    public static InputStream getImageStream(String url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setReadTimeout(5000);
        connection.setConnectTimeout(5000);
        connection.setRequestMethod("GET");
        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            return connection.getInputStream();
        }
        throw new RuntimeException("Invalid URL:"+url);
    }

    private static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

}
