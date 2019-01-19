/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author User
 */
// абстрактный класс элементов библиотеки
public abstract class LibraryItem {
    private String ISBN, title, sector;
    private DateTime publicationDate;
    private DateTime borrowTime;
    private Reader currentReader;
    private Reader nextReader;
    private DateTime reservationTime;
    
    public LibraryItem(){
        ISBN = null;
        title = null;
        sector = null;
        publicationDate = null;
        borrowTime = new DateTime(1,1,2000);
        currentReader = null;
    }

    public LibraryItem(String ISBN, String title, String sector, DateTime publicationDate, DateTime borrowTime, Reader currentReader) {
        this.ISBN = ISBN;
        this.title = title;
        this.sector = sector;
        this.publicationDate = publicationDate;
        this.borrowTime = borrowTime;
        this.currentReader = currentReader;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public DateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(DateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

    public DateTime getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(DateTime borrowTime) {
        this.borrowTime = borrowTime;
    }

    public Reader getCurrentReader() {
        return currentReader;
    }

    public void setCurrentReader(Reader currentReader) {
        this.currentReader = currentReader;
    }

    public void setReservationTime(DateTime reservationTime){
        this.reservationTime = reservationTime;
    }

    public DateTime getReservationTime() {
        return reservationTime;
    }

    public Reader getNextReader() {
        return nextReader;
    }

    public void setNextReader(Reader nextReader) {
        this.nextReader = nextReader;
    }

    @Override
    public String toString() {
        return "LibraryItem{" + "ISBN=" + ISBN + ", title=" + title + ", sector=" + sector + ", "
                + "publicationDate=" + publicationDate + ", borrowTime=" + borrowTime + ", currentReader=" + (currentReader!=null?currentReader.getName():null) + '}';
    }
}
