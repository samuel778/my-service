package com.micro.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookMarkUtils {
    public static void main(String[] args) {
        StringBuilder html = new StringBuilder("");

        for (String line : html.toString().split("\n")) {
            Matcher m = h3.matcher(line);
            Matcher ab = a.matcher(line);
            if (m.find()) {
                String key = m.group();
                Matcher temp = h3Name.matcher(key);
                if (temp.find()) {
                    System.out.println("## " + temp.group().substring(1, temp.group().length() - 1));
                }
            }
            if (ab.find()) {
                //- [Dom4j](https://dom4j.github.io/) - Flexible XML framework
                String key = ab.group();
                Matcher temp = aHref.matcher(key);
                String url = "";
                if (temp.find()) {
                    url=temp.group().substring(6, temp.group().length()-1);
                }
                String name = "";
                Matcher temp2 = aName.matcher(key);
                if(temp2.find()){
                    name = temp2.group().substring(1,temp2.group().length()-1);
                }
                System.out.println("- ["+name+"]("+url+") - ");
            }

        }
    }

    static Pattern h3 = Pattern.compile("<H3[^>]*>([^<]*)</H3>");
    static Pattern h3Name = Pattern.compile("(\\>[^\\}]*\\<)");
    static Pattern a = Pattern.compile("<A[^>]*>([^<]*)</A>");
    static Pattern aName = Pattern.compile("(\\>[^\\}]*\\<)");
    static Pattern aHref = Pattern.compile("HREF=\"\\S*");
}
