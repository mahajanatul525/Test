/**
 * Enum Singleton Pattern
 *
 * Summary:
 * The Enum Singleton pattern is a highly effective and simple way to implement the Singleton pattern in Java.
 * It leverages the Java Enum type, which provides built-in protection against multiple instantiation, serialization,
 * and reflection issues. The Enum Singleton is often preferred because it is thread-safe, prevents multiple instances,
 * and works well for most use cases requiring a single instance.
 *
 * Advantages:
 * 1. Thread Safety:
 *    - Enum singletons are inherently thread-safe without requiring additional synchronization.
 *      The JVM handles the creation and initialization of enum instances, ensuring only one instance is created,
 *      even with concurrent access.
 *
 * 2. Serialization Protection:
 *    - Unlike standard singleton implementations, which require implementing special methods (`readResolve`) to
 *      maintain singleton properties during serialization, Enum singletons maintain their singleton guarantee
 *      through Java's native serialization mechanism.
 *
 * 3. Reflection Protection:
 *    - Enum singletons prevent reflection from creating additional instances, a common vulnerability in other
 *      singleton implementations. In an enum, reflection cannot bypass the singleton property.
 *
 * 4. Simplicity and Readability:
 *    - The Enum Singleton pattern requires minimal code and is easy to understand and implement, reducing
 *      complexity in the codebase.
 *
 * Limitations:
 * 1. Limited Flexibility in Extensibility:
 *    - Enum types cannot extend any class, as they implicitly extend java.lang.Enum. If the Singleton needs
 *      to extend another class, the Enum Singleton pattern cannot be used.
 *    - Enum Singleton is less flexible if you need inheritance, polymorphism, or to implement multiple behaviors.
 *
 * 2. Eager Initialization:
 *    - Enum singletons are eagerly initialized when the enum class is first loaded, making them unsuitable for
 *      situations where lazy initialization is desired.
 *    - This can be inefficient if the Singleton instance has complex initialization logic or depends on external
 *      resources not ready at the time of class loading.
 *
 * 3. Dependency Injection Incompatibility:
 *    - Dependency Injection (DI) frameworks (like Spring) expect singleton beans to be classes rather than enums.
 *      While you can configure a DI framework to use the enum instance, this requires additional configuration,
 *      which may reduce the simplicity benefits of using an Enum Singleton.
 *
 * 4. Serialization Constraints with External Libraries:
 *    - Although Java’s native serialization supports Enum singletons, some external serialization libraries may
 *      require additional configuration for enum types. This could complicate serialization requirements if
 *      custom serialization is needed.
 *
 * 5. Specific to Java:
 *    - The Enum Singleton pattern is unique to Java, and may not be directly transferrable to other languages.
 *      This could be a limitation in polyglot environments or where the codebase might need to be ported.
 *
 * When to Avoid Enum Singleton:
 * - Consider other singleton implementations if:
 *   - You require lazy initialization for the singleton.
 *   - The singleton must extend a superclass or implement polymorphic behavior.
 *   - You are working with a DI framework that does not support enum singletons easily.
 *
 * Summary:
 * - The Enum Singleton pattern is ideal for single-instance requirements that do not involve inheritance or lazy
 *   initialization. While it offers strong thread safety and serialization advantages, it may be less suitable for
 *   scenarios requiring flexibility, dependency injection, or complex initialization. For these cases, consider
 *   alternative Singleton patterns like the Bill Pugh method or double-checked locking.
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

enum SingleTonUsingEnum {
    INSTANCE;

    private int value;

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public void printValue() {
        System.out.println("Value is: " + this.value);
    }
}

public class SingleTonUsingEnumTest {

    public static void main(String[] args) {

        SingleTonUsingEnum singleTon1 = SingleTonUsingEnum.INSTANCE;
        singleTon1.setValue(35);

        try {

            // Serialize object to a file
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("single.ser"));
            out.writeObject(singleTon1);
            out.close();

            // Deserialize object from file
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("single.ser"));
            SingleTonUsingEnum singleTon2 = (SingleTonUsingEnum) in.readObject();
            in.close();

            System.out.println("Object 1 Value: " + singleTon1.getValue());
            System.out.println("Object 2 Value: " + singleTon2.getValue());
            System.out.println("Are the object equals: " + (singleTon1 == singleTon2));

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
