package com.harsh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harsh.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
		Author findByName(String name);
}
