package hnt.spring.reactive.core.service;

import hnt.spring.reactive.core.dao.CustomerDao;
import hnt.spring.reactive.core.dto.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerDao customerDao;

    public List<Customer> loadAllCustomers() {
        long startTime = System.currentTimeMillis();
        List<Customer> customers = customerDao.getCustomers();
        long endTime = System.currentTimeMillis();
        System.out.println("Total time taken: " + (endTime - startTime) + "ms");
        return customers;
    }

    public Flux<Customer> loadAllCustomersStream() {
        long startTime = System.currentTimeMillis();
        Flux<Customer> customers = customerDao.getCustomersStream();
        long endTime = System.currentTimeMillis();
        System.out.println("Total time taken: " + (endTime - startTime) + "ms");
        return customers;
    }
}
