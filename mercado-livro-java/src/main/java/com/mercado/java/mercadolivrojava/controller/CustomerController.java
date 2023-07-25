package com.mercado.java.mercadolivrojava.controller;

import com.mercado.java.mercadolivrojava.controller.request.PostCustomerRequest;
import com.mercado.java.mercadolivrojava.controller.request.PutCustomerRequest;
import com.mercado.java.mercadolivrojava.controller.response.CustomerResponse;
import com.mercado.java.mercadolivrojava.controller.response.PageResponse;
import com.mercado.java.mercadolivrojava.model.CustomerModel;
import com.mercado.java.mercadolivrojava.security.UserCanOnlyAccessTheirOwnResource;
import com.mercado.java.mercadolivrojava.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public PageResponse<CustomerResponse> getAll(
            @PageableDefault(page = 0, size = 10)Pageable pageable,
            @RequestParam(required = false) String name
    ){
        Page<CustomerModel> customerModels = customerService.getAll(name, pageable);
        return CustomerModel.toPageResponse(customerModels.map(CustomerModelExtensionKt::toCustomerResponse));
    }

    @GetMapping("/{id}")
    @UserCanOnlyAccessTheirOwnResource
    public CustomerResponse getCustomer(@PathVariable int id){
        CustomerModel customerModel = customerService.findById(id);
        return CustomerModel.toCustomerResponse(customerModel);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid PostCustomerRequest customerRequest){
        CustomerModel customerModel = customerRequest.toCustomerModel();
        customerService.create(customerModel);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update (@PathVariable int id, @RequestBody @Valid PutCustomerRequest customerRequest){
        CustomerModel customerSaved = customerService.findById(id);
        CustomerModel updateCustomer = customerRequest.toCustomerModel(customerSaved);
        customerService.update(updateCustomer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        customerService.delete(id);
    }
}
