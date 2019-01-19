public interface LibraryManager {
    
    void addNewItem(LibraryItem item);
    
    void deleteItem(LibraryItem item);
    
    void printItemList();
    
    void borrowItem(LibraryItem item);
    
    void returnItem(LibraryItem item);
    
    void generateReport();

    void reservationItem(LibraryItem item);
}
