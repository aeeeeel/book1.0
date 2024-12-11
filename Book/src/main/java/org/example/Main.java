package org.example;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Book> LIST;

    public static void main(String[] args) {
        load();
        System.out.println("init system success");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("    welcome    ");
            System.out.println("1 .create book info");
            System.out.println("2 .research book info");
            System.out.println("3 .update book info");
            System.out.println("4 .delete book info");
            System.out.println("5 .exit ");

            switch (scanner.nextInt()) {
                case 1:
                    insert(scanner);
                    break;
                case 2:
                    research();
                    break;
                case 3:
                    update(scanner);
                    break;
                case 4:
                    delete(scanner);
                    break;
                case 5:
                    save();
                    return;
            }

        }
    }

    private static void insert(Scanner scanner) {
        String name = scanner.nextLine();
        String author = scanner.nextLine();
        int price = scanner.nextInt();
        //Book book=new Book(name,author,price);
        Book book = Book.builder().name(name).author(author).price(price).build();
        scanner.nextLine();
        LIST.add(book);
        System.out.println("insert success" + book.toString());
    }

    private static void research() {
        for (int i = 0; i < LIST.size(); i++) {
            System.out.println(i + "." + LIST.get(i));
        }
    }

    private static void update(Scanner scanner) {
        scanner.nextLine();
        int index=scanner.nextInt();
        System.out.println("write book ID update");
        scanner.nextLine();
        while (index>LIST.size()-1||index<0) {
            System.out.println("update fail try again");
            index=scanner.nextInt();
            scanner.nextLine();
        }
        Book book = LIST.get(index);
        book.setName(scanner.nextLine());
        book.setAuthor(scanner.nextLine());
        book.setPrice(scanner.nextInt());
        scanner.nextLine();
        System.out.println("update success" + book.toString());
    }
    private static void load() {
        File file = new File("books.txt");
        if (file.exists()) {
            try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file))) {
                LIST = (List<Book>) stream.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            LIST = new LinkedList<>();
        }

    }

    private static void save() {
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream("books.txt"))) {
            stream.writeObject(LIST);
            stream.flush();
            System.out.println("save success");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void delete(Scanner scanner) {
        scanner.nextLine();
        int index=scanner.nextInt();
        System.out.println("write book ID delete");
        scanner.nextLine();
        while(index>LIST.size()-1||index<0) {
            System.out.println("delete fail try again");
            index=scanner.nextInt();
            scanner.nextLine();
        }
        LIST.remove(index);
        System.out.println("delete success");
    }
}
