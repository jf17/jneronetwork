import com.opencsv.CSVReader;

// opencsv-3.8.jar
import java.io.FileReader;
import java.io.IOException;

public class NeuroNET {
    public static void main(String[] args) {

        BinaryClassificationNeuron neronClass = new BinaryClassificationNeuron();
        BinaryClassificationNeuron neronSex = new BinaryClassificationNeuron();
        BinaryClassificationNeuron neronAge = new BinaryClassificationNeuron();


        boolean life = true;
        boolean die = false;


        String csvFile = "titanic.csv";

        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(csvFile));
            String[] line;
            reader.readNext();
            while ((line = reader.readNext()) != null) {

                if(line[0].equals("0")){ neronClass.put(line[1],die); neronSex.put(line[3],die);neronAge.put(line[4],die); }
                else if(line[0].equals("1")){neronClass.put(line[1],life); neronSex.put(line[3],life); neronAge.put(line[4],life);}
                else{ throw  new RuntimeException(); }

          //      System.out.println("Country [id= " + line[0] + ", code= " + line[1] + " , name=" + line[2] + "]");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

/*
        System.out.println("Class:");
        System.out.println("1: "+neronClass.getScoreByKey("1"));
        System.out.println("2: "+neronClass.getScoreByKey("2"));
        System.out.println("3: "+neronClass.getScoreByKey("3"));

        System.out.println(" ");
        System.out.println("Sex: ");
        System.out.println("female: "+neronSex.getScoreByKey("female"));
        System.out.println("male: "+neronSex.getScoreByKey("male"));


        System.out.println(" ");
        System.out.println("Age: ");
        System.out.println("65: "+neronAge.getScoreByKey("65"));
        System.out.println("22: "+neronAge.getScoreByKey("22"));

        System.out.println(" ");
        int result = neronClass.getScoreByKey("1") + neronSex.getScoreByKey("female") + neronAge.getScoreByKey("44");
        System.out.println("Die or Life : " + result);
*/
        String testData = "titanic.csv";

        CSVReader readerTest = null;
        int erroeCount =0;

        try {
            int count =2;
            readerTest = new CSVReader(new FileReader(testData));
            String[] line;
            readerTest.readNext();
            while ((line = readerTest.readNext()) != null) {

                int result = neronClass.getScoreByKey(line[1]) +  neronSex.getScoreByKey(line[3])  + neronAge.getScoreByKey(line[4])  ;

                if(result==0){System.out.println("result==0");erroeCount++; }
                else if(line[0].equals("0") && result<0){}
                else if(line[0].equals("0") && result>0){ System.out.println("Строка :" +count+ "  значение :" +result + "  пол:" + line[3] + "  возраст: " + line[4]); erroeCount++;}
                else if(line[0].equals("1") && result>0){}
                else if(line[0].equals("1") && result<0){System.out.println("Строка :" +count+ "  значение :" +result+ "  пол:" + line[3]+ "  возраст: " + line[4]); erroeCount++;}
                else {throw  new RuntimeException();}

                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        float gh = 1.0f/887.0f;
        float ghRes = gh * erroeCount;

        float ghTrue = 1- ghRes;


        System.out.println(" ");
        System.out.println("Верность алгоритма : " + ghTrue);
        System.out.println("Кол-во ошибок: " + ghRes);

    }
}
