import java.io.*;

public class App {
    public static void main(String[] args) throws Exception {

        SingleTonUsingEnum  singleTon1 = SingleTonUsingEnum.INSTANCE;
        singleTon1.setValue(24);

        
        ObjectOutputStream  out = new  ObjectOutputStream(new FileOutputStream("obj.ser"));
        out.writeObject(singleTon1);
        out.close();

        ObjectInputStream in = new ObjectInputStream(new FileInputStream("obj.ser"));
        SingleTonUsingEnum singleTon2 = (SingleTonUsingEnum) in.readObject();
        in.close();

        System.out.println("Value of singleton1: " + singleTon1.getValue());
        System.out.println("Value of singleton2: " + singleTon2.getValue());
        System.out.println("Are Object are equals: " + (singleTon1 == singleTon2));

    }
}
