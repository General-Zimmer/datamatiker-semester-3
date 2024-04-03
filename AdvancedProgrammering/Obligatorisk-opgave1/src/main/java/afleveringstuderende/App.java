package afleveringstuderende;

public class App {
    public static void main(String[] args) {
        DictionaryDoubleHashing<String, Integer> dictionary = new DictionaryDoubleHashing<>(10);

        dictionary.put("a", 1);
        dictionary.put("b", 2);
        dictionary.put("c", 3);
        dictionary.put("d", 4);
        dictionary.put("e", 5);

        System.out.println(dictionary.get("a"));
        System.out.println(dictionary.get("b"));

        dictionary.remove("a");

        System.out.println(dictionary.get("a"));

        dictionary.put("a", 1);
        System.out.println("YEEEET");
        System.out.println(dictionary.get("a"));

        System.out.println(dictionary.size());

        dictionary.put("f", 6);

        System.out.println(dictionary.size());

        dictionary.put("a", 7);

        System.out.println(dictionary.get("a"));

        System.out.println("THIS IS THE DICTIONARY:");
        dictionary.writeOut();

        dictionary.put("g", 8);
        dictionary.put("h", 9);
        dictionary.put("i", 10);

        System.out.println("THIS IS THE DICTIONARY:");
        dictionary.writeOut();
    }
}
