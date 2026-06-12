package com.nanxinda.class_.StringBuffer_;

public class Exercise01 {
    public static void main(String[] args) {
        String string = null;
        StringBuffer s = new StringBuffer();
        s.append(string);
        /*
    private AbstractStringBuilder appendNull() {
        int c = count;
        ensureCapacityInternal(c + 4);
        final char[] value = this.value;
        value[c++] = 'n';
        value[c++] = 'u';
        value[c++] = 'l';
        value[c++] = 'l';
        count = c;
        return this;
    }
         */
        System.out.println(s.length());//4
        //当其不为空时，才会16+string.length
        try {
            StringBuffer stringBuffer = new StringBuffer(string);
        } catch (Exception e) {
            System.out.println("错误信息="+e.getMessage());
        }
        /*
    public StringBuffer(String str) {
        super(str.length() + 16);
        append(str);
    }
         */
    }

}
