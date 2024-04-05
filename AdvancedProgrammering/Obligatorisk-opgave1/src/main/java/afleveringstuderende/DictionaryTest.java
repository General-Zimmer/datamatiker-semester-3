package afleveringstuderende;

public class DictionaryTest {

    public static void main(String[] args) {

        DictionaryDoubleHashing<Integer, String> testDictionar = new DictionaryDoubleHashing<>(10);
        // Ville ønske opgaven sagde et ulige tal, for så skulle jeg ikke undgå at få en kollision indtil rehashing.
        // Eller at jeg selv må komme op med en hash2 metode.
        testDictionar.put(27, "Person1");
        testDictionar.put(270, "Person2");
        testDictionar.put(13, "Person3");
        testDictionar.put(15, "Person4");
        testDictionar.put(16, "Person5");
        testDictionar.writeOut();

        System.out.println("---------- New list printput -------------");
        // Testing rehash
        testDictionar.put(31, "Person6");
        testDictionar.put(42, "Person7");
        testDictionar.put(28, "Person8");

        testDictionar.writeOut();
        System.out.println("Testing get()");
        System.out.println(testDictionar.get(37)); // Person6
        System.out.println(testDictionar.get(270)); // Person2
        System.out.println("---------- New list printput -------------");
        System.out.println("Removed 13");
        testDictionar.remove(13);
        testDictionar.put(13, "Jensen");
        testDictionar.put(14, "Person9");
        testDictionar.put(24, "Person10");
        testDictionar.put(18, "Person11");
        testDictionar.writeOut();
    }
} 