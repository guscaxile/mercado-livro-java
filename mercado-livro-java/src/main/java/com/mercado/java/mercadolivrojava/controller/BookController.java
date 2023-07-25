package com.mercado.java.mercadolivrojava.controller;

import com.mercado.java.mercadolivrojava.controller.request.PostBookRequest;
import com.mercado.java.mercadolivrojava.service.BookService;
import com.mercado.java.mercadolivrojava.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("books")
public class BookController {

    private final BookService bookService;
    private final CustomerService customerService;

    public BookController(BookService bookService, CustomerService customerService) {
        this.bookService = bookService;
        this.customerService = customerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create (@RequestBody @Valid PostBookRequest request){
        var customer = customerService.findById(request.getCustomerId());
        bookService.create(request.toBookModel(customer));
    }

    @GetMapping
    public PageResponse<BookResponse> findAll(@PageableDefault(page = 0, size = 10)Pageable pageable){
        return bookService.findAll(pageable).map(BookExtensionKt::toResponse).toPageResponse();
    }

    @GetMapping("/active")
    public Page<BookResponse> findActives(@PageableDefault(page = 0, size = 10) Pageable pageable){
        return bookService.findActives(pageable).map(BookExtensionKt::toResponse);
    }

    @GetMapping("/{id}")
    public BookResponse findById(@PathVariable int id){
        return bookService.findById(id).toResponse();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        bookService.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable int id, @RequestBody PutBookRequest book){
        var bookSaved = bookService.findById(id);
        bookService.update(book.toBookModel(bookSaved));
    }
}
