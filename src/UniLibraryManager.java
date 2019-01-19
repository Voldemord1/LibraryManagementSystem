/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class UniLibraryManager implements LibraryManager{
    List<LibraryItem> listItem = new ArrayList();

    //добавляем несколько объектов (книг и DVD) в список - для отображения в списке
    public UniLibraryManager(){
        listItem.add(new Book("0-7475-3269-9", "Harry Potter. Philosopher's stone", "Sect4", new DateTime(27,6,1997), new DateTime(14,11,2018, 11,10), new Reader("316473", "Bill Gates", "1234567890", "examplw@gmail.com"), "	J. K. Rowling","Bloomsbury ",332));
        listItem.add(new Book("0-7475-3849-2", "Harry Potter and the Chamber of Secrets", "Sect5", new DateTime(2,7,1998), new DateTime(29,11,2018, 9, 14), new Reader("329018", "Jim Kerry", "345271832", "example@gmail.com"), "	J. K. Rowling","Bloomsbury ",251));
        listItem.add(new DVD("1-9834-2367-1", "Venom", "Sect6", new DateTime(12,10,2018), new DateTime(01,12,2018, 13, 22), new Reader("321567", "Albert Einstein", "63221234", "example2@gmail.com"), "EN, RU","YES","Avi Arad, Matt Tolmach, Amy Pascal","Tom Hardy, Michelle Williams, Riz Ahmed"));
        listItem.add(new DVD("1-9834-2367-2", "Mission: Impossible", "Sect6", new DateTime(22,5,1996), new DateTime(18,11,2018, 16,47), new Reader("526789", "Steve Jobs", "652345123", "example3@gmail.com"), "EN, RU","NO","Tom Cruise, Paula Wagner","Tom Cruise, Ving Rhames, Simon Pegg"));
        listItem.add(new Book("978-0-553-10953-5", "A Brief History of Time", "Sect7", new DateTime(01,03,1988), null,null, "Stephen Hawking", "Bantam Dell Publishing Group", 256));
    }

    //метод добавить элемент (книгу или диск)
    @Override
    public void addNewItem(LibraryItem item) {
        // если есть место (кол-во меньше 150, то проверяем к какому типу объекта относится элемент)
        //иначе - выводим сообщение, что список элементов полон
        if(listItem.size() < 150) {
            if (item instanceof Book) {
                listItem.add(item);
                System.out.println("The " + item.getClass().getName() + " added to the Item list");
            }else if(item instanceof DVD) {
                listItem.add(item);
                System.out.println("The " + item.getClass().getName() + " added to the Item list");
            }
        }else
            System.out.println("The Item list is full");
    }

    //метод для удаления элемента из списка
    @Override
    public void deleteItem(LibraryItem item) {
        if(listItem.contains(item)){ //если в списке есть элемент - то удаляем его и печатаем соответствующее сообщение
            System.out.println("The " +item.getClass().getName() +" \""+ item.getTitle() + "\" removed from the Item list");
            listItem.remove(item);
        }
    }

    //метод печати в консоль списка элементов
    @Override
    public void printItemList() {
        for(int i = 0; i < listItem.size(); i++){
            System.out.println(
                    listItem.get(i).toString());
        }
    }

    //метод определяет взята книга/DVD и доступна ли на данный момент
    @Override
    public void borrowItem(LibraryItem item) {
        Scanner scan = new Scanner(System.in);
        int day, month, year, hours, minutes;
        double days;
        String  ID = "", name = "", email = "", mobileNumber = "";
        if (item.getBorrowTime()== null) { //если время чтения пустое, значит никто книгу не читает и добавляем нового читателя
            System.out.println("The " + item.getClass().getName() + " is available at the moment.");
            day = Calendar.getInstance().getTime().getDate(); //текущий день
            month = Calendar.getInstance().getTime().getMonth() + 1; // текущий месяц
            year = Calendar.getInstance().getTime().getYear() + 1900; //текущий год
            hours = Calendar.getInstance().getTime().getHours();
            minutes = Calendar.getInstance().getTime().getMinutes();
            DateTime dateTime = new DateTime(day, month, year, hours, minutes); //добавляем все это в новый объект "время, когда читетель взял на прочтение"
            System.out.print("Enter reader's ID: ");
            ID = scan.next();
            System.out.print("Enter reader's name: ");
            name = scan.next();
            System.out.print("Enter reader's mobile number: ");
            mobileNumber = scan.next();
            System.out.print("Enter reader's E-mail: ");
            email = scan.next();
            Reader reader = new Reader(ID, name, mobileNumber, email);// создаем нового читателя
            item.setBorrowTime(dateTime); // добавляем время, когда читатель взял книгу
            item.setCurrentReader(reader);//добавляем читателя к элементу
        }else {//если время чтения не пустое
            //считаем количество "просроченных" дней
            days = daysDifference(item);
            if (item instanceof Book) { //если элемент - книга
                if (days <= 7) {//Если количество дней не более 7, то выводим сообщение, когда книга будет доступна
                    System.out.println("The " + item.getClass().getName() + " is not available at the moment.\n" +
                            "It will be available again in " + (7 - (int)days) + " day(s)");
                }else
                    System.out.println("The borrow time of the last reader is over.\nYou can take the book when the reader returns it.");
            } else if (item instanceof DVD) {//если элемент - DVD
                if (days <= 3) {//Если количество дней не более 3, то выводим сообщение, когда DVD будет доступно
                    System.out.println("The " + item.getClass().getName() + " is not available at the moment.\n" +
                            "It will be available again in " + (3 - (int)days) + " day(s)");
                }else
                    System.out.println("The borrow time of the last reader is over.\nYou can take the book when the reader returns it.");
            }
        }
    }

    @Override
    public void returnItem(LibraryItem item) {
        double forfeitDay; //штрафные дни
        double pay = 0.0, days = 0.0; //штраф, который пользователь должен будет заплатить
        //считаем количество "просроченных" дней
        days = daysDifference(item);
        if (item.getBorrowTime() != null) {
            if (days > 7) {// если кол-во дней больше 7 - считем штарфние дни
                forfeitDay = days - 7;
                if (forfeitDay < 4) { //если кол-во штрафных дней не больше 3, то ситаем по фор-ле:
                    pay = forfeitDay * 24 * 0.2;//дни * 24 часа * $0,2 за каждый час
                } else if (forfeitDay > 3) {  //если кол-во штрафных дней больше 3, то ситаем по фор-ле:
                    pay = 3 * 24 * 0.2 + (forfeitDay - 3) * 24 * 0.5; // первые 3 дня * 24 часа * $0,2 + (оставшиеся дни) * 24 часа * $0,5 за каждый час
                }
                //выводим сообщение на экран сколько пользователь должен заплатить
                System.out.printf(Locale.ENGLISH, "The %s should pay $%.2f%n", item.getCurrentReader().getName(), pay);
            } else// если кол-во дней < 7 - возвращаем книгу
                System.out.println("The " + item.getClass().getName() + " " + item.getTitle() + " has been returned.");

            item.setCurrentReader(null);//обнуляем читателя, т.к. книгу он вернул
            item.setBorrowTime(null);//...и дату
        }else//если время на прочтение пустое - значит можно взять книгу почитать, что отображено в сообщении
            System.out.println("The " + item.getClass().getName() + " is available at the moment.");
    }

    @Override
    public void generateReport() {
        double forfeitDay; //штрафные дни
        double pay = 0.0; //штраф, который пользователь должен будет заплатить
        try(FileWriter fileWriter = new FileWriter("report.txt", false)){
            fileWriter.write(String.format(Locale.ENGLISH,
                    "%-10s|%-17s|%-35s|%-16s|%-15s|%-5s%n",
                    "Type item","ISBN","Title","Borrow Time","Reader","Fee"));
            fileWriter.write("--------------------------------------------------" +
                    "---------------------------------------------------\n");
            for (LibraryItem item:listItem) {
                //считаем количество "просроченных" дней
                double days = daysDifference(item);
                if (item.getBorrowTime() != null) {
                    if (days > 7) {// если кол-во дней больше 7 - считем штарфние дни
                        forfeitDay = days - 7;
                        if(forfeitDay < 4){ //если кол-во штрафных дней не больше 3, то ситаем по фор-ле:
                            pay = forfeitDay*24*0.2;//дни * 24 часа * $0,2 за каждый час
                        }else if (forfeitDay > 3){  //если кол-во штрафных дней больше 3, то ситаем по фор-ле:
                            pay = 3*24*0.2 + (forfeitDay-3)*24*0.5;
                        }
                        fileWriter.write(String.format(Locale.ENGLISH,
                                "%-10s|%-17s|%-35s|%-16s|%-15s|$%.2f%n",
                                item.getClass().getName(), item.getISBN(), item.getTitle(),
                                item.getBorrowTime(), item.getCurrentReader().getName(),pay));
                    }
                }
            }
            System.out.println("The report file was generated");
        }catch (IOException e){
            System.out.println("File not found.");
        }
    }

    @Override
    public void reservationItem(LibraryItem item){
        Scanner scan = new Scanner(System.in);
        int day, month, year, hours, minutes;
        String  ID = "", name = "", email = "", mobileNumber = "";
        if (item.getBorrowTime() != null) { //если время чтения не пустое, значит книгу читают и добавляем нового читателя для бронирования книги
            DateTime reservationDate;
            while (true) {
                try {
                    System.out.print("Enter date and time in format dd/mm/yyyy hh:mm to reserve a " + item.getClass().getName() + ": ");
                    String[] dateStr = scan.nextLine().split(" ");
                    String[] date = dateStr[0].split("/");
                    day = Integer.parseInt(date[0]); //день
                    month = Integer.parseInt(date[1]); //месяц
                    year = Integer.parseInt(date[2]); //год
                    String[] time = dateStr[1].split(":");
                    hours = Integer.parseInt(time[0]);
                    minutes = Integer.parseInt(time[1]);
                    reservationDate = new DateTime(day, month, year, hours, minutes);
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid data. Try again");
                }
            }
                System.out.print("Enter reader's ID: ");
                ID = scan.next();
                System.out.print("Enter reader's name: ");
                name = scan.next();
                System.out.print("Enter reader's mobile number: ");
                mobileNumber = scan.next();
                System.out.print("Enter reader's E-mail: ");
                email = scan.next();
                Reader reader = new Reader(ID, name, mobileNumber, email);// создаем нового читателя
                item.setReservationTime(reservationDate); // добавляем время, когда читатель взял книгу
                item.setNextReader(reader);//добавляем читателя к элементу

        }else {//если время чтения пустое - выводим сообщение, что пользователь может взять почитать эту книгу
            System.out.println("You can borrowed this " + item.getClass().getName());
        }
    }
    //метод позволяет получить кол-во дней типа double
    private double daysDifference(LibraryItem item){
        double days = 0.0;
        SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy hh:mm"); //задаем формат даты
        Date parsingDate;
        try {
            parsingDate = ft.parse(item.getBorrowTime().toString());
            //ищем разницу и переводим значение из милисекунд в дни
            days = (double)(Calendar.getInstance().getTimeInMillis() - parsingDate.getTime()) / 1000 / 3600 / 24;
        } catch (Exception e) {
        }
        return days;
    }
}

