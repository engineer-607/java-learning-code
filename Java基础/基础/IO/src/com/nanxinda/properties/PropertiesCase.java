package com.nanxinda.properties;

import org.junit.Test;

import java.io.*;
import java.util.Properties;

public class PropertiesCase {
    public static void main(String[] args) throws IOException {
        //读取mysql.properties文件，并得到ip，user和pwd
        BufferedReader bufferedReader = new BufferedReader(new FileReader("src\\mysql.properties"));
        String line = "";
        while ((line = bufferedReader.readLine()) != null) {
            String[] strings = line.split("=");
            System.out.println(strings[0] + "值是" + strings[1]);
        }
        /**1）专门用于读写配置文件的集合类
         * 配置文件的格式：
         * 键=值
         * 2）注意：键值对不需要空格，值不需要用引号（默认类型是String）
         */
    }
    @Test
    @SuppressWarnings({"all"})
    public void m1() throws IOException {
        //1.创建Properties对象
        Properties properties = new Properties();
        //2.加载指定配置文件（字符流的方式读取）load(Reader reader)
        properties.load(new FileReader("src\\mysql.properties"));
        //3.把k-v键值对显示到控制台list(PrintStream out)
        properties.list(System.out);
        //4.根据key获取对应的值
        String user = properties.getProperty("user");
        String pwd = properties.getProperty("pwd");
        System.out.println("用户名="+user);
        System.out.println("密码="+pwd);
    }
    @Test
    @SuppressWarnings({"all"})
    public void m2() throws IOException {
        //使用Properties 类来创建配置文件 ，修改配置文件内容
        Properties properties = new Properties();
        //创建
        //1.如果该文件没有key就是创建
        //2.如果该文件有key，就是修改替换
        /*
        Properties父类是Hashtable，底层就是Hashtable核心方法
        class Properties extends Hashtable<Object,Object>
    public synchronized V put(K key, V value) {
        // Make sure the value is not null
        if (value == null) {
            throw new NullPointerException();
        }

        // Makes sure the key is not already in the hashtable.
        Entry<?,?> tab[] = table;
        int hash = key.hashCode();
        int index = (hash & 0x7FFFFFFF) % tab.length;
        @SuppressWarnings("unchecked")
        Entry<K,V> entry = (Entry<K,V>)tab[index];
        for(; entry != null ; entry = entry.next) {
            if ((entry.hash == hash) && entry.key.equals(key)) {
                V old = entry.value;
                entry.value = value;//如果key存在就将值替换
                return old;
            }
        }

        addEntry(hash, key, value, index);//如果是新的k，就addEntry
        return null;
    }
         */
        properties.setProperty("charset","utf-8");
        properties.setProperty("user","汤姆");
        properties.setProperty("pwd","abc111");
        //将k-v存储文件中即可(comments意为注释，不需要注释就设置为null)
        properties.store(new OutputStreamWriter(new FileOutputStream("src\\mysql2.properties"),"utf-8"),null);
    }


}
