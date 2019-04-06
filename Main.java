package com.company;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Set<Integer> set1 = new HashSet();
        set1.add(0);
        set1.add(2);
        set1.add(3);
        Set<Integer> set2 = new HashSet();
        set2.add(1);
        set2.add(2);
        set2.add(3);
        Set<Integer> set3 = symmetricDifference(set1, set2);
        System.out.println(set3.toString());

        Animal[] animals = new Animal[5];
        animals[0] = new Animal("Dog");
        animals[1] = new Animal("cat");
        animals[2] = new Animal("wolf");
        animals[3] = new Animal("lion");
        animals[4] = new Animal("Bird");
        byte[] arr = serializeAnimalArray(animals);
        for (byte a : arr) {
            System.out.println(a);
        }

        Animal[] animals1 = deserializeAnimalArray(arr);
        for (Animal a : animals1) {
            System.out.println(a.name);
        }
        for (Animal a : animals) {
            System.out.println(a.name);
        }

        Pair<Integer, String> pair = Pair.of(1, "hello");
        Integer i = pair.getFirst(); 
        String s = pair.getSecond(); 
        System.out.println(pair.hashCode() + ": " + pair.getFirst() + ", " + pair.getSecond());
        Pair<Integer, String> pair2 = Pair.of(2, "hello");
        System.out.println(pair2.hashCode() + ": " + pair2.getFirst() + ", " + pair2.getSecond());
        boolean mustBeTrue = pair.equals(pair2); 
        System.out.println(mustBeTrue);
        boolean mustAlsoBeTrue = pair.hashCode() == pair2.hashCode(); 
        System.out.println(pair.hashCode() + " " + pair2.hashCode());
        System.out.println(mustAlsoBeTrue);

        String randomFrom = "Me";
        String randomTo = "You";
        int randomSalary = 100;
        
        MailMessage firstMessage = new MailMessage(
                "Robert Howard",
                "H.P. Lovecraft",
                "This \"The Shadow over Innsmouth\" story is real masterpiece, Howard!"
        );

        assert firstMessage.getFrom().equals("Robert Howard") : "Wrong firstMessage from address";
        assert firstMessage.getTo().equals("H.P. Lovecraft") : "Wrong firstMessage to address";
        assert firstMessage.getContent().endsWith("Howard!") : "Wrong firstMessage content ending";

        MailMessage secondMessage = new MailMessage(
                "Jonathan Nolan",
                "Christopher Nolan",
                "Брат, почему все так хвалят только тебя, когда практически все сценарии написал я. Так не честно!"
        );

        MailMessage thirdMessage = new MailMessage(
                "Stephen Hawking",
                "Christopher Nolan",
                "Я так и не понял Интерстеллар."
        );

        List<MailMessage> messages = Arrays.asList(
                firstMessage, secondMessage, thirdMessage
        );

        MailService<String> mailService = new MailService<>();

        messages.stream().forEachOrdered(mailService);

        Map<String, List<String>> mailBox = mailService.getMailBox();

        assert mailBox.get("H.P. Lovecraft").equals(
                Arrays.asList(
                        "This \"The Shadow over Innsmouth\" story is real masterpiece, Howard!"
                )
        ) : "wrong mailService mailbox content (1)";

        assert mailBox.get("Christopher Nolan").equals(
                Arrays.asList(
                        "Брат, почему все так хвалят только тебя, когда практически все сценарии написал я. Так не честно!",
                        "Я так и не понял Интерстеллар."
                )
        ) : "wrong mailService mailbox content (2)";

        assert mailBox.get(randomTo).equals(Collections.<String>emptyList()) : "wrong mailService mailbox content (3)";

        Salary salary1 = new Salary("Facebook", "Mark Zuckerberg", 1);
        Salary salary2 = new Salary("FC Barcelona", "Lionel Messi", Integer.MAX_VALUE);
        Salary salary3 = new Salary(randomFrom, randomTo, randomSalary);

        MailService<Integer> salaryService = new MailService<>();

        Arrays.asList(salary1, salary2, salary3).forEach(salaryService);


        Map<String, List<Integer>> salaries = salaryService.getMailBox();
        assert salaries.get(salary1.getTo()).equals(Arrays.asList(1)) : "wrong salaries mailbox content (1)";
        assert salaries.get(salary2.getTo()).equals(Arrays.asList(Integer.MAX_VALUE)) : "wrong salaries mailbox content (2)";
        assert salaries.get(randomTo).equals(Arrays.asList(randomSalary)) : "wrong salaries mailbox content (3)";
    }

    public static Animal[] deserializeAnimalArray(byte[] data) {
        try {
            FileInputStream fis = new FileInputStream(String.valueOf(data));
            ObjectInputStream ois = new ObjectInputStream(fis);
            Animal[] animals = new Animal[ois.readInt()];
            for (int i = 0; i < animals.length; i++) {
                animals[i] = (Animal) ois.readObject();
            }
            ois.close();
            fis.close();
            return animals;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Illegal Argument Exception");

        }
    }


    public static byte[] serializeAnimalArray(Animal[] animals) throws IOException {
        byte[] a = new byte[0];
        FileOutputStream fos = new FileOutputStream(String.valueOf(a));
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeInt(animals.length);
        for (int i = 0; i < animals.length; i++) {
            oos.writeObject(animals[i]);
        }
        return a;
    }

    public static <T> Set<T> symmetricDifference(Set<? extends T> set1, Set<? extends T> set2) {
        Set<T> temp = new HashSet(set1);
        temp.removeAll(set2);
        set2.removeAll(set1);
        temp.addAll(set2);
        return temp;
    }
}
