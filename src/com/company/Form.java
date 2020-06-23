package com.company;

import com.mifmif.common.regex.Generex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;



public class Form extends JFrame {
    private JPanel startPanel;
    private JTabbedPane tabbedPane1;
    public JComboBox comboBox1;
    public JComboBox comboBox2;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton генерироватьButton;
    private JLabel l1;
    private JLabel l2;
    private JLabel l3;
    private JSpinner spinner1;
    private JSpinner spinner2;
    private JPanel textP;
    private JButton генерироватьButton1;
    private JTextPane textPane1;
    private JButton сохранитьButton;
    private JButton считатьИзФайлаButton;
    private JButton изменитьButton;
    private JEditorPane авторКияницаАПEditorPane;
    public boolean flag=true;

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
    static String genBase(int k, String base, String simbl, boolean fl){
        if(k==0)
            return "";
        String rez=(fl)?base+"(":"(";
        for(int i=0;i<k;i++){
            rez+=simbl+base;
        }

        rez+=")";
        return rez;
    }
    static List<String > rexToStrs(String rex,int end){
        List<String >words=new ArrayList<>();
        try {
            Generex generex = new Generex(rex);
            for (int j = 0; j <= end; j++)
                for (int i = 0; i < 100; i++) {
                    String s = generex.random(j, j);
                    if (!words.contains(s))
                        words.add(s);
                }
        }catch (Throwable t){};
        return words;
    }
    static List<String> genWords(String rex, String sAlf, int start, int end,int k){
        List<String> words=new ArrayList<>();


        String tempRez=rex,a,b;
        System.out.println(rex);
        String[] params = rex.split("[+]");
        for (int j=params.length-1;j>=0;j--){
            if(Pattern.matches("["+sAlf.replaceAll(",","")+"]+",params[j])){
                words.add(params[j]);
                tempRez=tempRez.substring(0,tempRez.length()-params[j].length()-1);
                System.out.println(tempRez);
            }else
                break;
        }
        System.out.println(tempRez+"//////////////////////////////////////////////////////////");
        String varA="",varB="";
        params=tempRez.split("(\\()");
        if(Pattern.matches("["+sAlf.replaceAll(",","")+"]+",params[0])){
            varA=params[0];
            tempRez=tempRez.substring(params[0].length());
            System.out.println(tempRez);
        }
        params=tempRez.split("(\\)\\*)");
        if(Pattern.matches("["+sAlf.replaceAll(",","")+"]+",params[params.length-1])){
            varB=params[params.length-1];
            tempRez=tempRez.substring(0,tempRez.length()-params[params.length-1].length());
            System.out.println(tempRez);
        }
        System.out.println("A="+varA+" B="+varB);

        String temp=rex,temp1;
        temp = temp.replaceAll("\\+", "|");
        temp = temp.replaceAll("\\*", "{0," + (end-((varA+varB).length()))+ "}");
        for(String s:rexToStrs(temp,end)){
            if (s.length() >= start && s.length() <= end)
                words.add(s);
        }
        return words;
    }
    static int  chekInputData(String alf, String a,String b){
        String rAlf="[ab]+";
        System.out.println(Pattern.matches("((.,)+).",alf));
       if(!Pattern.matches("((.,)+).",alf))
           return 1;
       else if(!Pattern.matches("["+alf.replaceAll(",","")+"]*",a))
           return 2;
       else if(!Pattern.matches("["+alf.replaceAll(",","")+"]*",b))
           return 3;
       return 0;
    }
    public Form() {

        this.setBounds(100, 100, 600, 400);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //JScrollPane scroll = new JScrollPane(textPane1);
        //textP.add(scroll);
        setContentPane(startPanel);
        textField2.addActionListener(e -> {
            comboBox1.removeAllItems();
            String[] alf=textField2.getText().split(",");
            for (int i = 0; i < alf.length; i++) {
                comboBox1.addItem(alf[i]);
            }
        });
        генерироватьButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flag=false;
                textField1.setText("");
               textField1.setEditable(false);
                l1.setText("");l2.setText("");l3.setText("");
                int ff=chekInputData(textField2.getText(), textField3.getText(), textField4.getText());
                if (ff == 1)
                    l1.setText("Неверный формат ввода");
                else if (ff == 2)
                    l2.setText("Подстрока содержит символ не из алфавита");
                else if (ff == 3)
                    l3.setText("Подстрока содержит символ не из алфавита");
                else {
                    String a = textField3.getText();
                    String b = textField4.getText();
                    String rez = "", sAlf = textField2.getText(), tempRez;
                    String[] alf = sAlf.split(",");
                    String simbl = comboBox1.getSelectedItem().toString();
                    String base = join(delItem(alf, simbl), "+");
                    int k = comboBox2.getSelectedIndex() + 1, addK = k - (countSimbol(a + b, simbl) % k);
                    if (addK == k) addK = 0;
                    if (k != 1)
                        rez += a + genBase(addK, base, simbl,true) + genBase(k, base, simbl,((countSimbol(a+b,simbl)%k==0)?true:false)) + "*" + b;
                    else
                        rez += a + join(alf, "+") + b;
                    int size, i = 0;
                    boolean f = false;
                    if (intersectionStr(a, b) != null && countSimbol(a + b, simbl) != 0) {
                        String inters = intersectionStr(a, b.substring(i));
                        size = inters.length();
                        do {
                            System.out.println(inters);
                            if (inters != null) {
                                if (countSimbol(a + b.substring(size - i), simbl) % k == 0) {
                                    rez += "+" + a + b.substring(size - i);
                                    f = true;
                                }
                            }
                            i++;
                            if ((size - 1 - i) < 0)

                                break;
                            //System.out.println("a="+a+" b="+b.substring(size-i));
                            inters = intersectionStr(a, b.substring(size - i));

                        } while (true);
                    }
                    textField1.setText(rez);
                }
            }
        });

        генерироватьButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textPane1.setText("");
                List<String> words=genWords(textField1.getText(), textField2.getText(), (int)spinner1.getValue(), (int)spinner2.getValue(),comboBox2.getSelectedIndex()+1);
                for(String s:words){
                    textPane1.setText(textPane1.getText()+s+"\n");
                }
            }
        });
        сохранитьButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileName="rez.txt";
               // Files.write("rez.txt", textPane1.getText(), StandardOpenOption.APPEND);
                byte[] bytes;
                 bytes=("От "+ spinner1.getValue()+"до"+spinner2.getValue()+"\n"+textField1.getText()+"\n"+textPane1.getText()).getBytes();
                if (!flag)
                    bytes=("Алфавит: "+textField2.getText()+"\n"+"Начальная подцепочка: "+textField3.getText()+"\n"+"Конечная подцепочка: "+textField4.getText()+"\n"+
                            "Символ: "+comboBox1.getSelectedItem()+"\n"+"Четность: "+comboBox2.getSelectedItem()+"\n"+
                            "От "+ spinner1.getValue()+"до"+spinner2.getValue()+"\n"+textField1.getText()+"\n"+textPane1.getText()).getBytes();
                JFileChooser fileChooser= new JFileChooser();
                fileChooser.setDialogTitle("Выбор директории");
                fileChooser.setSelectedFile(new File("C:\\Users\\admin\\Desktop\\rex.txt"));
                // Определение режима - только каталог
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int result = fileChooser.showSaveDialog(startPanel);
                // Если директория выбрана, покажем ее в сообщении
                if (result == JFileChooser.APPROVE_OPTION) {
                    fileName = fileChooser.getSelectedFile().toString();
                }

                try {
                    System.out.println(fileName);
                    Files.write(Paths.get(fileName),bytes , StandardOpenOption.CREATE);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        считатьИзФайлаButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileName="data.txt";
                // Files.write("rez.txt", textPane1.getText(), StandardOpenOption.APPEND);
                byte[] bytes =(textField1.getText()+"\n"+textPane1.getText()).getBytes();
                JFileChooser fileChooser= new JFileChooser();
                fileChooser.setDialogTitle("Выбор директории");
                fileChooser.setSelectedFile(new File("C:\\Users\\admin\\Desktop\\data.txt"));
                // Определение режима - только каталог
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int result = fileChooser.showOpenDialog(startPanel);
                // Если директория выбрана, покажем ее в сообщении
                if (result == JFileChooser.APPROVE_OPTION) {
                    fileName = fileChooser.getSelectedFile().toString();
                }

                try {
                    System.out.println(fileName);
                    List<String> data=Files.readAllLines(Paths.get(fileName));
                    if(data.size()==5)
                        if(chekInputData(data.get(0),data.get(1),data.get(2))==0){
                            textField2.setText(data.get(0));
                            textField3.setText(data.get(1));
                            textField4.setText(data.get(2));
                            String[] alf=data.get(0).split(",");
                            for (int i = 0; i < alf.length; i++) {
                                comboBox1.addItem(alf[i]);
                            }
                           // System.out.println(Integer.parseInt(data.get(4)));
                            comboBox2.setSelectedIndex(Integer.parseInt(data.get(4))-1);
                        }

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        изменитьButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flag=true;
                 textField1.setEditable(true);
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
