package com.chw.test.utils;

public class NumberUtilsChw {

    /**
     * 判断数字是否是正数
     */
    public static boolean checkNumberLargerZero(Integer number){
        return (number!=null && number>0);
    }

    public static boolean checkNumberLargerZero(Long number){
        return (number!=null && number>0);
    }

    public static boolean checkNumberLargerZero(Float number){
        return (number!=null && number>0);
    }

    /**
     * 获取字符串中最左边的数字
     */
    public static int getFirstNumberInString(String string){
        return getIndexNumberInString(string,0);
    }

    /**
     * 获取字符串中左边第（index+1）个数字
     */
    public static int getIndexNumberInString(String string,int index){
        if(string==null || string.isEmpty()){
            return 0;
        }
        byte[] bytes = new byte[9];
        int find=0;
        int location=0;
        for (byte b : string.getBytes()) {
            boolean is = false;
            if(b>47 && b<58){
                is=true;
                bytes[location]=b;
                location++;
            }
            if((!is && location>0) || location>8){
                if(find<index){
                    find++;
                    bytes = new byte[9];
                    location=0;
                }else {
                    break;
                }
            }
        }
        if(find<index || location==0){
            return 0;
        }
        return Integer.valueOf(new String(bytes).trim());
    }

    /**
     * 根据字符串中包含的数字比较字符串
     * 可用于比较题号、班级
     */
    public static int compareMixNumberString(String str1,String str2){
        int count=0;
        while (count<4){
            int i = getIndexNumberInString(str1, count) - getIndexNumberInString(str2, count);
            if(i==0){
                count++;
            }else {
                return i;
            }
        }
        return 0;
    }

    /**
     * 使用java正则表达式去掉多余的.与0
     */
    public static String subZeroAndDot(String s){
        if(s.indexOf(".") > 0){
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }
}
