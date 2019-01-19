//класс книг с конструкторами, геттерами и сеттерами
public class Book extends LibraryItem{
    private String authors;
    private String publisher;
    private int totalNumberPages;
    
    public Book(){
        authors = null;
        publisher = null;
        totalNumberPages = 0;
    }

    public Book(String ISBN, String title, String sector, DateTime publicationDate, DateTime borrowTime, Reader currentReader, String authors, String publisher, int totalNumberPages) {
        super(ISBN, title, sector, publicationDate, borrowTime, currentReader);
        this.authors = authors;
        this.publisher = publisher;
        this.totalNumberPages = totalNumberPages;
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
    
    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getTotalNumberPages() {
        return totalNumberPages;
    }

    public void setTotalNumberPages(int totalNumberPages) {
        this.totalNumberPages = totalNumberPages;
    }    

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

       
}
