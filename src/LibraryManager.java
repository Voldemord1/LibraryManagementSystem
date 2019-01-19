/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public interface LibraryManager {
    
    void addNewItem(LibraryItem item);
    
    void deleteItem(LibraryItem item);
    
    void printItemList();
    
    void borrowItem(LibraryItem item);
    
    void returnItem(LibraryItem item);
    
    void generateReport();

    void reservationItem(LibraryItem item);
}
