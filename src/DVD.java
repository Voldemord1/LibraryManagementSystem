/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//класс DVD с конструкторами, геттерами и сеттерами
public class DVD extends LibraryItem {
    private String languages;
    private String availableSubtitles;
    private String producer;
    private String actors;
    
    public DVD(){
        languages = "EN";
        availableSubtitles = "No";
        producer = null;
        actors = "";
    }

    public DVD(String ISBN, String title, String sector, DateTime publicationDate, DateTime borrowTime, Reader currentReader, String languages, String availableSubtitles, String producer, String actors) {
        super(ISBN, title, sector, publicationDate, borrowTime, currentReader);
        this.languages = languages;
        this.availableSubtitles = availableSubtitles;
        this.producer = producer;
        this.actors = actors;
    }

    @Override
    public void setBorrowTime(DateTime borrowTime) {
        super.setBorrowTime(borrowTime); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DateTime getBorrowTime() {
        return super.getBorrowTime(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPublicationDate(DateTime publicationDate) {
        super.setPublicationDate(publicationDate); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DateTime getPublicationDate() {
        return super.getPublicationDate(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setSector(String sector) {
        super.setSector(sector); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSector() {
        return super.getSector(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setTitle(String title) {
        super.setTitle(title); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getTitle() {
        return super.getTitle(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setISBN(String ISBN) {
        super.setISBN(ISBN); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getISBN() {
        return super.getISBN(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCurrentReader(Reader currentReader) {
        super.setCurrentReader(currentReader); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Reader getCurrentReader() {
        return super.getCurrentReader(); //To change body of generated methods, choose Tools | Templates.
    }
    
    

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public String isAvailableSubtitles() {
        return availableSubtitles;
    }

    public void setAvailableSubtitles(String availableSubtitles) {
        this.availableSubtitles = availableSubtitles;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }  

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
