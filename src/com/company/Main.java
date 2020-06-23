package com.company;

import com.mifmif.common.regex.Generex;

import java.util.ArrayList;
import java.util.List;

public class Main {
    static String[] delItem(String[] data,String item){
        String[] rez=new String[data.length-1];
        int i=0;
        for (String s:data)
            if(!s.equals(item))
                rez[i++]=s;
        return rez;
    }
    static int countSimbol(String str, String simbol){
        return str.length()-str.replaceAll(simbol,"").length();
    }
    static  String intersectionStr(String a, String b){
        for(int i=0;i<a.length();i++){
            if(b.indexOf(a.substring(i))==0){
                return a.substring(i);
            }

        }
        return null;
    }
    static String join(String[] data, String split){
        String rez="(";
        for(String s:data){
            rez+=s+split;
        }
        rez=rez.substring(0,rez.length()-1);
        rez+=")*";
        return rez;

    }
    static String genBase(int k, String base, String simbl){
        if(k==0)
            return "";
        String rez="("+base;
        for(int i=0;i<k;i++){
            rez+=simbl+base;
        }

        rez+=")";
        return rez;
    }
    static List<String> genWords(String rex, String sAlf, int start, int end){
        List<String> words=new ArrayList<>();
//        String tempRez=rex,a,b;
//        System.out.println(rex);
//        String[] params = rex.split("[+]");
//        for(String s:params)
//            System.out.println(s);
//        System.out.println("["+sAlf.replaceAll(",","")+"]+");
//        System.out.println(Pattern.matches("["+sAlf.replaceAll(",","")+"]+",params[params.length-1]));
//        for (int j=params.length-1;j>=0;j--){
//            if(Pattern.matches("["+sAlf.replaceAll(",","")+"]+",params[j])){
//                words.add(params[j]);
//                tempRez=tempRez.substring(0,tempRez.length()-params[j].length()-1);
//                System.out.println(tempRez);
//            }else
//                break;
//        }
//
//        params=tempRez.split("[*]");
//        if(Pattern.matches("["+sAlf.replaceAll(",","")+"]+",params[0])){
//            words.add(params[0]);
//            tempRez=tempRez.substring(params[0].length()+1);
//            System.out.println(tempRez);
//        }
//        if(Pattern.matches("["+sAlf.replaceAll(",","")+"]+",params[params.length-1])){
//            words.add(params[params.length-1]);
//            tempRez=tempRez.substring(0,tempRez.length()-params[params.length-1].length()-1);
//            System.out.println(tempRez);
//        }
//
//
//        params = tempRez.split("[')*(']");
//        for(String s:params)
//            System.out.println(s);

        Generex generex = new Generex("bc([ab]{0,3}c[ab]{0,3})([ab]{0,3}c[ab]{0,3}c[ab]{0,3}){0,3}cc|bcc");
        List<String> matchedStrs = generex.getAllMatchedStrings();
//       for(String s:matchedStrs){
//           if(s.length()<10 && !words.contains(s)){
//               words.add(s);
////               if(s.length()==3)
////                   System.out.println(s);
//           }
//       }
//        System.out.println(words);
        // Using Generex iterator
        // System.out.println(generex.random(2,3));
        return words;
    }
    public static void main(String[] args) {
        List<String> words=new ArrayList<>();
//        String a="bc";
//        String b="cc";
//        String rez="", sAlf="a,b,c", tempRez;
//        String[] alf={"a","b","c"};
//        String simbl="c";
//        String base=join(delItem(alf,simbl),"+");
//        int k=2, addK=k-(countSimbol(a+b,simbl)%k);
//        if(addK==k) addK=0;
//        if(k!=1)
//            rez+=a+genBase(addK,base,simbl)+genBase(k,base,simbl)+"*"+b;
//        else
//            rez+=a+join(alf,"+")+b;
//        int size,i=0;
//        boolean f=false;
//        if (intersectionStr(a, b) != null && countSimbol(a+b,simbl)!=0){
//            String inters = intersectionStr(a, b.substring(i));
//            size=inters.length();
//            do {
//                System.out.println(inters);
//                if (inters != null) {
//                    if (countSimbol(a + b.substring(size-i), simbl) % k == 0){
//                        rez += "+" + a + b.substring(size-i);
//                        f=true;
//                    }
//                }
//                i++;
//                if((size-1-i)<0)
//
//                    break;
//                //System.out.println("a="+a+" b="+b.substring(size-i));
//                inters = intersectionStr(a, b.substring(size-i));
//
//            }while (true);
//        }
////        if(f)
////            rez="("+rez+")";
//        System.out.println(rez);
//    //    genWords(rez,sAlf,2,5);

//
//                Generex generex = new Generex("(1{0,6}|0{0,6})");
//
//     //  words=generex.getAllMatchedStrings();
//        for(int i=0;i<100;i++) {
//            String s=generex.random(0,5);
//            if (!words.contains(s))
//                words.add(s);
//        }
//      System.out.println(words);
        new Form();
    }
}
