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
import java.util.ArrayList;
import java.util.List;

import static com.mercado.java.mercadolivrojava.extension.ConverterExtensionFunction.toCustomerModel;
import static com.mercado.java.mercadolivrojava.extension.ConverterExtensionFunction.toCustomerResponse;

@RestController
@RequestMapping("customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public PageResponse<CustomerResponse> getAll(
            @PageableDefault(page = 0, size = 10) Pageable pageable,
            @RequestParam(required = false) String name
    ) {
        Page<CustomerModel> customerModels = customerService.getAll(name, pageable);
        List<CustomerResponse> customerResponses = new ArrayList<>();

        for (CustomerModel customerModel : customerModels.getContent()) {
            CustomerResponse customerResponse = new CustomerResponse(
                    customerModel.getId(),
                    customerModel.getName(),
                    customerModel.getEmail(),
                    customerModel.getStatus()
            );
            customerResponses.add(customerResponse);
        }

        return new PageResponse<>(customerResponses, customerModels.getNumber(), customerModels.getTotalElements(), customerModels.getTotalPages());
    }

    @GetMapping("/{id}")
    @UserCanOnlyAccessTheirOwnResource
    public CustomerResponse getCustomer(@PathVariable int id) {
        CustomerModel customerModel = customerService.findById(id);
        return toCustomerResponse(customerModel);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid PostCustomerRequest customerRequest) {
        CustomerModel customerModel = toCustomerModel(customerRequest);
        customerService.create(customerModel);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable int id, @RequestBody @Valid PutCustomerRequest customerRequest) {
        CustomerModel customerSaved = customerService.findById(id);
        CustomerModel updateCustomer = toCustomerModel(customerRequest, customerSaved);
        customerService.update(updateCustomer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        customerService.delete(id);
    }
}
