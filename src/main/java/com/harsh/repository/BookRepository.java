package com.harsh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harsh.entity.Book;

public interface BookRepository extends JpaRepository<Book,Long> {

}
