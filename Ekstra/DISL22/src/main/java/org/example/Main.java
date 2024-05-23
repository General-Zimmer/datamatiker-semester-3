package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println(krypt("abcdefg", 1000));
        System.out.println(dekrypt("bcd", 1000));

    }

    public static String krypt(String s, int key) {
        StringBuffer temp = new StringBuffer();
        for (int i = 0; i < s.length(); i++)
            temp.append((char) (((int) (s.charAt(i)) + key) % 256));
        return temp.toString();
    }

    public static String dekrypt(String s,int key) {
        StringBuffer temp = new StringBuffer();
        for (int i = 0; i < s.length();i++)
            temp.append((char)(((int)(s.charAt(i))-key)%256));
        return temp.toString();
    }
}