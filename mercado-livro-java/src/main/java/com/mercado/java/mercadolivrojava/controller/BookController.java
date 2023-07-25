package com.mercado.java.mercadolivrojava.controller;

import com.mercado.java.mercadolivrojava.controller.request.PostBookRequest;
import com.mercado.java.mercadolivrojava.controller.request.PutBookRequest;
import com.mercado.java.mercadolivrojava.controller.response.BookResponse;
import com.mercado.java.mercadolivrojava.controller.response.PageResponse;
import com.mercado.java.mercadolivrojava.extension.ConverterExtensionFunction;
import com.mercado.java.mercadolivrojava.model.BookModel;
import com.mercado.java.mercadolivrojava.model.Util.BookModelUtil;
import com.mercado.java.mercadolivrojava.model.CustomerModel;
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
    public BookModel create (@RequestBody @Valid PostBookRequest request){
        CustomerModel customer = customerService.findById(request.getCustomerId());
        return bookService.create(request.toBookModel(customer));
    }

    @GetMapping
    public PageResponse<BookResponse> findAll(@PageableDefault(page = 0, size = 10)Pageable pageable){
        Page<BookModel> books = bookService.findAll(pageable);
        return ConverterExtensionFunction.toPageResponse(books.map(ConverterExtensionFunction::toBookResponse));
    }

    @GetMapping("/active")
    public PageResponse<BookResponse> findActives(@PageableDefault(page = 0, size = 10) Pageable pageable){
        Page<BookModel> activeBooks = bookService.findActives(pageable);
        return ConverterExtensionFunction.toBookPageResponse(activeBooks);
    }

    @GetMapping("/{id}")
    public BookResponse findById(@PathVariable int id){
        BookModel book = bookService.findById(id);
        return ConverterExtensionFunction.toBookResponse(book);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        bookService.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable int id, @RequestBody PutBookRequest book){
        BookModel bookSaved = bookService.findById(id);
        BookModel updatedBook = ConverterExtensionFunction.toBookModel(book, bookSaved);
        bookService.update(updatedBook);
    }
}
