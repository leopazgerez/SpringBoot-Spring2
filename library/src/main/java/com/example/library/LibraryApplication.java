package com.example.library;

import com.example.library.models.*;
import com.example.library.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class LibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(UsersRepository usersRepository,
                                      BookCopiesRepository bookCopiesRepository,
                                      BooksRepository booksRepository,
                                      AuthorsRepository authorsRepository,
                                      LocationRepository locationRepository,
                                      AddressRepository addressRepository) {
        return args -> {
//          <<<<<<<<<<<<<<<<<<<<<<<<<<< Users and address
            Address address1 = new Address("Simon Bolivar", "1100");
            addressRepository.save(address1);
            Address address2 = new Address("Mariano Boedo", "656");
            addressRepository.save(address2);
            Address address3 = new Address("Sargento Cabral", "635");
            addressRepository.save(address3);
            User user1 = new User(12L, "Leonel", "3877434090", address1);
            usersRepository.save(user1);
            User user2 = new User(13L, "Maria", "3877434091", address2);
            usersRepository.save(user2);
            User user3 = new User(14L, "Ramón", "3877434092", address3);
            usersRepository.save(user3);
//            >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

//            <<<<<<<<<<<<<<<<<<<<<<<<< Books
            Author author1 = new Author(2L, "Enrique");
            authorsRepository.save(author1);
            Author author2 = new Author(3L, "Kike");
            authorsRepository.save(author2);
            Author author3 = new Author(4L, "Jaime");
            authorsRepository.save(author3);
            Book book1 = new Book(2L, "Las maravillas del code", "ñalskdj33", "Tacañas", author1);
            booksRepository.save(book1);
            Book book2 = new Book(3L, "Las maravillas del code V2", "ñalffe3skdj33", "Floriponti", author2);
            booksRepository.save(book2);
            Book book3 = new Book(4L, "Las maravillas del code V3", "ñalsffkdj33", "Inmaduras", author3);
            booksRepository.save(book3);
//            >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

//            <<<<<<<<<<<<<<<<<<<<<<<<<<< Books Copies' location
            Location location = new Location("A", "3");
            locationRepository.save(location);
            Location location2 = new Location("A", "4");
            locationRepository.save(location2);
            Location location3 = new Location("A", "5");
            locationRepository.save(location3);
            Location location4 = new Location("A", "6");
            locationRepository.save(location4);
            Location location5 = new Location("A", "7");
            locationRepository.save(location5);
            Location location6 = new Location("A", "8");
            locationRepository.save(location6);
            Location location7 = new Location("A", "9");
            locationRepository.save(location7);
            Location location8 = new Location("A", "10");
            locationRepository.save(location8);
            Location location9 = new Location("A", "11");
            locationRepository.save(location9);
            List<Location> allLocations = locationRepository.findAll();
//            >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

//            <<<<<<<<<<<<<<<<<<<<<<<< Books Copies
//            BookCopy bookCopy1 = new BookCopy(2L, location, book1);
//            bookCopiesRepository.save(bookCopy1);
//            Location found = allLocations.stream().filter(inLocation -> inLocation.getShelve().equals(location.getShelve())).findFirst().get();
//            found.setBookCopy(bookCopy1);
//            locationRepository.save(found);
//            BookCopy bookCopy2 = new BookCopy(3L, location2, book1);
//            bookCopiesRepository.save(bookCopy2);
//            Location found2 = allLocations.stream().filter(inLocation -> inLocation.getShelve().equals(location2.getShelve())).findFirst().get();
//            found2.setBookCopy(bookCopy2);
//            locationRepository.save(found2);
//            BookCopy bookCopy3 = new BookCopy(4L, location3, book1);
//            bookCopiesRepository.save(bookCopy3);
//            Location found3 = allLocations.stream().filter(inLocation -> inLocation.getShelve().equals(location3.getShelve())).findFirst().get();
//            found3.setBookCopy(bookCopy3);
//            locationRepository.save(found3);
            BookCopy bookCopy4 = new BookCopy(5L, location4, book2);
            bookCopiesRepository.save(bookCopy4);
            Location found4 = allLocations.stream().filter(inLocation -> inLocation.getShelve().equals(location4.getShelve())).findFirst().get();
            found4.setBookCopy(bookCopy4);
            locationRepository.save(found4);
            BookCopy bookCopy5 = new BookCopy(6L, location5, book2);
            bookCopiesRepository.save(bookCopy5);
            Location found5 = allLocations.stream().filter(inLocation -> inLocation.getShelve().equals(location5.getShelve())).findFirst().get();
            found5.setBookCopy(bookCopy5);
            locationRepository.save(found5);
            BookCopy bookCopy6 = new BookCopy(7L, location6, book2);
            bookCopiesRepository.save(bookCopy6);
            Location found6 = allLocations.stream().filter(inLocation -> inLocation.getShelve().equals(location6.getShelve())).findFirst().get();
            found6.setBookCopy(bookCopy6);
            locationRepository.save(found6);
            BookCopy bookCopy7 = new BookCopy(8L, location7, book3);
            bookCopiesRepository.save(bookCopy7);
            Location found7 = allLocations.stream().filter(inLocation -> inLocation.getShelve().equals(location7.getShelve())).findFirst().get();
            found7.setBookCopy(bookCopy7);
            locationRepository.save(found7);
            BookCopy bookCopy8 = new BookCopy(9L, location8, book3);
            bookCopiesRepository.save(bookCopy8);
            Location found8 = allLocations.stream().filter(inLocation -> inLocation.getShelve().equals(location8.getShelve())).findFirst().get();
            found8.setBookCopy(bookCopy8);
            locationRepository.save(found8);
            BookCopy bookCopy9 = new BookCopy(10L, location9, book3);
            bookCopiesRepository.save(bookCopy9);
            Location found9 = allLocations.stream().filter(inLocation -> inLocation.getShelve().equals(location9.getShelve())).findFirst().get();
            found9.setBookCopy(bookCopy9);
            locationRepository.save(found9);
//            >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        };
    }
}
