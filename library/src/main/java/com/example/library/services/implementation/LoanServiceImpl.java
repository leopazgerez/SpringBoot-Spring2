package com.example.library.services.implementation;

import com.example.library.dtos.response.LoanResponseDTO;
import com.example.library.models.Book;
import com.example.library.models.BookCopy;
import com.example.library.models.Loans;
import com.example.library.models.User;
import com.example.library.repository.BookCopiesRepository;
import com.example.library.repository.BooksRepository;
import com.example.library.repository.LoansRepository;
import com.example.library.repository.UsersRepository;
import com.example.library.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LoanServiceImpl implements LoanService {
    @Autowired
    LoansRepository loansRepository;
    @Autowired
    BookCopiesRepository bookCopiesRepository;
    @Autowired
    BooksRepository booksRepository;
    @Autowired
    UsersRepository usersRepository;

    @Override
    public LoanResponseDTO createLoan(Long bookId) {
        // Busco el usuario
        User userResult = usersRepository.findById(2L).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Busco el libro
        Book bookResult = booksRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Libro no encontrado"));

        // Verifico si el usuario ya tiene un ejemplar activo de este libro
//        flatMap() se utiliza cuando hay colecciones dentro de los objetos. Es útil para "aplanar" es decir, tener un unico stream de las colleciones contenidas en los objetos
//        En este caso, dentro de cada Ejemplar, tengo una lista de prestamos. Entonces aplano a una sola lista, todos los prestamos de ese libro.
        boolean userHasThisCopy = bookResult.getBookCopies().stream()
                .flatMap(bookCopy -> bookCopy.getLoans().stream())
                .anyMatch(loan -> loan.getLoanRepaymentDate() == null && loan.getUser().getId().equals(userResult.getId()));

        if (userHasThisCopy) {
            throw new RuntimeException("El usuario ya tiene un préstamo activo de este libro.");
        }

        // Busco un ejemplar disponible (que no tenga préstamos activos)
        BookCopy availableCopy = bookResult.getBookCopies().stream()
                .filter(bookCopy -> bookCopy.getLoans().stream().allMatch(loan -> loan.getLoanRepaymentDate() != null))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No hay copias disponibles para préstamo."));

        // Creo el préstamo
        Loans resultLoan = new Loans(availableCopy, userResult);
        return new LoanResponseDTO(loansRepository.save(resultLoan));
    }


    @Override
    public Loans getLoan() {
        return null;
    }

    @Override
    public Set<LoanResponseDTO> getAllLoans() {
        return loansRepository.findAll().stream().map(LoanResponseDTO::new).collect(Collectors.toSet());
    }

    @Override
    public Loans updateLoan() {
        return null;
    }

    @Override
    public void deleteLoan() {

    }
}
