package file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class ManageFile {
    private HashMap<String, Float> map_of_person = new HashMap<String, Float>();
    private FileReader fileReader = null;
    private BufferedReader bufferedReader = null;

    private Calculate calculate;

    public ManageFile(String url_file, Float money_per_hour) {

        map_of_person.clear();
        calculate = new Calculate(money_per_hour);
        readFile(url_file);
    }

    public void readFile(String fileName) {
        try {
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(new FileReader(fileName));
            String line;
            boolean no_have_name = true;
            while ((line = bufferedReader.readLine()) != null) {
                //System.out.println(line);
                String name = splitString(0, line);
                if(map_of_person.isEmpty()){
                    map_of_person.put(name, 0f);
                }else {
                    for (String map_name : map_of_person.keySet()) {
                        if(map_name .equals(name)){ no_have_name = false; }
                    }
                    if(no_have_name){
                        map_of_person.put(name, 0f);
                    }
                }
                Float money = calculate.getCost(name, splitString(1, line),splitString(2, line), splitString(4, line));
                //System.out.println(name);
                for (String map_name : map_of_person.keySet()) {
                    if(map_name.equals(name)){
                        Float set_money = map_of_person.get(map_name) + money;
                        map_of_person.replace(map_name, set_money);
                        //System.out.println(name + " " + map_of_person.get(map_name));
                    }
                }
            }
            //check data in map
            System.out.println();
            System.out.println("----รวมรายได้ของแต่ละคน----");
            for (String map_name : map_of_person.keySet()) {
                System.out.printf("%s ได้เงิน %.2f บาท", map_name, map_of_person.get(map_name));
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String splitString(int state, String data) {
        String[] splitData = data.split("\\|");
        int i = 0;
        String getString = null;
        for (String string : splitData) {
            if(state == 0 && i == 0) { // get Name
                //System.out.println(string);
                getString = string;
                //break;
            }
            if(state == 1 && i == 1) { // get start date
                //System.out.println(string);
                getString = string;
                //break;
            }
            if(state == 2 && i == 2) { // get start time
                //System.out.println(string);
                getString = string;
                //break;
            }
            if(state == 3 && i == 3) { // get end date
                //System.out.println(string);
                getString = string;
                //break;
            }
            if(state == 4 && i == 4) { // get end time
                //System.out.println(string);
                getString = string;
                //break;
            }
            i++;
        }
        return getString;
    }

}
