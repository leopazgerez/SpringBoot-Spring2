package com.example.library.repository;

import com.example.library.models.BookCopy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookCopiesRepository extends JpaRepository<BookCopy, Long> {
}
