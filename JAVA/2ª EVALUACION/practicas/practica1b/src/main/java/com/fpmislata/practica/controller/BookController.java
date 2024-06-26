package com.fpmislata.practica.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fpmislata.practica.domain.service.BookService;
import com.fpmislata.practica.domain.service.impl.BookServiceImpl;

@RequestMapping("/books")
@Controller
public class BookController {
    BookService bookService = new BookServiceImpl();

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "books";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookService.findById(id));
        return "bookDetails";
    }
}
