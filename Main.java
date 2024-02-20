import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.crypto.dsig.Transform;

import HW.HW6.Notebooks;

public class Main {
    public static void main(String[] args) {
        boolean flag = true;
        while (flag==true) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите данные");
            String text = scanner.nextLine();
            if ( Transform(text)=="1" || Transform(text) =="2" ) decode (Transform(text));
            else{
                WriteInFile (Transform(text),text.split(" ")[0]);
                flag = false;
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }

    public static void decode (String num){
        if (num=="1") System.out.println("Введено недостаточно данных");
        if (num=="2") System.out.println("Введено излишнее кол во данных");

    }
    public static String Transform (String text) throws Exception{
        String [] arr = new String [6];
        String name;
        String middleName;
        String surname;
        String date_= " ";
        String number;
        String sex;
        arr = text.split(" ");
        if (arr.length<6) return "1";
        if (arr.length>6) return "2";
        
        if (isOnlyLetter(arr[0])) name = arr[0];
        else {
            throw new Exception ("Неверно введено имя");
        }
        if (isOnlyLetter(arr[1])) middleName= arr[1];
        else {
            throw new Exception ("Неверно введено отчество");
            
        }
        if (isOnlyLetter(arr[2])) surname = arr[2];
        else {
            throw new Exception ("Неверно введена фамилия");
          
        }
        if (isDate(arr[3])) date_=arr[3];
        else {
            throw new Exception ("Неверно введена дата");
        }   

        if (arr[4].matches("\\d*")) number = arr[4];
        else {
            throw new Exception ("Неверно введен номер");
        }
        if (arr[5].equals("m")|| arr[5].equals("f") ) sex = arr[5];
        else {
            throw new Exception ("Неверно введен пол");
        }
        
        
      return (name+" "+middleName+" "+surname+" "+ date_ +" "+number+" "+ sex); 
    }

    public static boolean isOnlyLetter (String text){
        for (int i = 0; i < text.length(); ++i) {
            if (!Character.isLetter(text.charAt(i))) return false;
        }
    return true;
    
    }
    public static boolean isDate (String text){
        DateFormat df = new SimpleDateFormat("mm.dd.yyyy");
        try
        {
        df.parse(text);
        return true;
        }
        catch(Exception e)
        {
        return false;
        }

    }
    static void WriteInFile ( String text, String fileName){
        try (FileWriter fr = new FileWriter(fileName, true)) {
                fr.write (text +"\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
}
