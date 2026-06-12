import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /*进制转化
        二进制：满二进一，0b或者0B开头
        八进制：满八进一，以0开头
        十六进制：0-9级A-F（10-15），满十六进一，以0x或0X开头
        作业（1）：
        0b110001100=1*4+1*8+1*128+1*256=396(二进制到十进制）
        02345=5*1+4*8+3*64+2*512=5+32+192+1024=1253（八进制到十进制）
        0xA45=5*1+4*16+10*256=69+2560=2629（十六进制到十进制）
        作业（2）：
        123=0b1111011(十进制到二进制）
        678=01246（十进制转八进制）
        8912=0x22D0
        作业（3）：
        0b11100101=0345（二进制转八进制）
        0b1110010110=0x396（二进制转十六进制）
        作业（4）：
        01230=0b1010011000（八进制转二进制）
        0xAB29=0b1010101100101001（十六进制转二进制）

        */
        double[] array = new double[3];
        Scanner sc = new Scanner(System.in);
        
        for (int i = 0; i < array.length; i++) {
            System.out.println("请输入题目");
            String input = sc.next();
            array[i] = parseNumber(input);
            
            System.out.println("请输入答案");
            // 读取答案字符串以支持不同进制
            String answerInput = sc.next();
            double answer = parseNumber(answerInput);
            
            boolean result = judge(array[i], answer);
        }

        sc.close();
    }
    
    // 解析不同进制的数字字符串
    public static double parseNumber(String input) {
        // 去除前后空白
        input = input.trim();
        
        // 检查是哪种进制
        if (input.startsWith("0b") || input.startsWith("0B")) {
            return Integer.parseInt(input.substring(2), 2);
            //Integer的parseInt方法可以将字符串中的数据进行十进制转换，String的substring方法将字符串的第三位开始到结束截取作为二进制整数，然后转化为十进制整数返回
        } else if (input.startsWith("0x") || input.startsWith("0X")) {
            return Integer.parseInt(input.substring(2), 16);
        } else if (input.startsWith("0") && input.length() > 1) {
            return Integer.parseInt(input.substring(1), 8);
        } else {
            return Double.parseDouble(input);
        }
    }
    
    public static boolean judge(double question, double answer) {
        if (Math.abs(question - answer) < 0.0001) {
            System.out.println("正确");
            return true;
        } else {
            System.out.println("错误");
            return false;
        }
    }
}