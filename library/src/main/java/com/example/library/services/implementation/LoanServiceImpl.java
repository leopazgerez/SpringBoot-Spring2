package com.example.library.services.implementation;

import com.example.library.dtos.request.LoansRequestDTO;
import com.example.library.dtos.response.LoanResponseDTO;
import com.example.library.exceptions.BookException;
import com.example.library.models.Book;
import com.example.library.models.BookCopy;
import com.example.library.models.Loans;
import com.example.library.models.User;
import com.example.library.repository.BooksRepository;
import com.example.library.repository.LoansRepository;
import com.example.library.repository.UsersRepository;
import com.example.library.services.LoanService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LoanServiceImpl implements LoanService {
    @Autowired
    LoansRepository loansRepository;
    @Autowired
    BooksRepository booksRepository;
    @Autowired
    UsersRepository usersRepository;

    @Override
    public LoanResponseDTO createLoan(Long bookId) {
        //        TODO: get user from token
        // Busco el usuario
        User userResult = getUserById(2L);

        // Busco el libro
        Book bookResult = booksRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Libro no encontrado"));
        boolean userHasThisCopy = checkUserHasThisCopy(bookResult, userResult);
        if (userHasThisCopy) {
            throw new RuntimeException("El usuario ya tiene un préstamo activo de este libro.");
        }
        BookCopy availableCopy = getAvailableCopy(bookResult);
        if (availableCopy == null) {
            throw new RuntimeException("No hay copias disponibles para préstamo.");
        }
        // Creo el préstamo
        Loans resultLoan = new Loans(availableCopy, userResult);
        return new LoanResponseDTO(loansRepository.save(resultLoan));
    }

    @Override
    @Transactional()
    public Set<LoanResponseDTO> createLoanMultipleBook(List<Book> books) throws BookException {
        List<Book> booksAlreadyHas = new ArrayList<>();
        List<Book> booksWithoutCopies = new ArrayList<>();
        List<BookCopy> booksCopies = new ArrayList<>();
//        TODO: get user from token
        User userResult = getUserById(2L);
        List<Book> booksFounded = booksRepository
                .findAllById(books
                        .stream()
                        .map(Book::getId)
                        .collect(Collectors.toList()));
        booksFounded.forEach(book -> {
            boolean userHasThisCopy = checkUserHasThisCopy(book, userResult);
            BookCopy availableCopy = getAvailableCopy(book);
            if (availableCopy == null) {
                booksWithoutCopies.add(book);
            } else {
                booksCopies.add(availableCopy);
            }
            if (userHasThisCopy) {
                booksAlreadyHas.add(book);
            }
        });
        if (!booksAlreadyHas.isEmpty() && !booksWithoutCopies.isEmpty()) {
            throw new BookException(booksAlreadyHas, booksWithoutCopies, "Conflict");
        }
        if (!booksAlreadyHas.isEmpty()) {
            throw new BookException(booksAlreadyHas, null, "One or more books are own for this user");
        }
        if (!booksWithoutCopies.isEmpty()) {
            throw new BookException(null, booksWithoutCopies, "One ore more books have not copies");
        }
        List<Loans> result = null;
        try {
            result = booksCopies.stream().map(copy -> new Loans(copy, userResult)).toList();
            loansRepository.saveAll(result);
        } catch (Exception exception) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw exception;
        }
        return result.stream().map(LoanResponseDTO::new).collect(Collectors.toSet());
    }

    private User getUserById(Long id) {
        return usersRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }


    /**
     * Verifico si el usuario ya tiene un ejemplar activo de este libro
     * flatMap() se utiliza cuando hay colecciones dentro de los objetos. Es útil para "aplanar" es decir, tener un único stream de las colecciones contenidas en los objetos
     * En este caso, dentro de cada Ejemplar, tengo una lista de préstamos. Entonces aplano a una sola lista, todos los préstamos de ese libro.
     */
    private boolean checkUserHasThisCopy(Book book, User user) {
        return book
                .getBookCopies()
                .stream().flatMap(bookCopy -> bookCopy
                        .getLoans()
                        .stream())
                .anyMatch(loan -> loan.getLoanRepaymentDate() == null && loan.getUser().getId().equals(user.getId()));
    }

    /**
     * Busco un ejemplar disponible (que no tenga préstamos activos)
     */
    private BookCopy getAvailableCopy(Book book) {
        return book.getBookCopies().stream()
                .filter(bookCopy -> bookCopy.getLoans().stream().allMatch(loan -> loan.getLoanRepaymentDate() != null))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Loans getLoanById() {
        return null;
    }

    @Override
    public Set<LoanResponseDTO> getAllLoans() {
        return loansRepository
                .findAll()
                .stream()
                .map(LoanResponseDTO::new)
                .collect(Collectors.toSet());
    }

    @Override
    public LoanResponseDTO updateLoan(LoansRequestDTO loansRequestDTO) {
        Loans loanFounded = loansRepository.findById(loansRequestDTO.getId()).orElseThrow();
        loanFounded.copyWith(loansRequestDTO);
        return new LoanResponseDTO(loansRepository.save(loanFounded));
    }

    @Override
    public void deleteLoan() {

    }
}
