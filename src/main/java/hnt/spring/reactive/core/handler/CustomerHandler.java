package hnt.spring.reactive.core.handler;

import hnt.spring.reactive.core.dao.CustomerDao;
import hnt.spring.reactive.core.dto.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CustomerHandler {
    private final CustomerDao customerDao;

    public Mono<ServerResponse> loadCustomers(ServerRequest request) {
        Flux<Customer> customers = customerDao.getCustomersList();
        return ServerResponse.ok().body(customers, Customer.class);
    }

    public Mono<ServerResponse> getCustomer(ServerRequest request) {
        int id = Integer.parseInt(request.pathVariable("input"));
        Mono<Customer> customerMono = customerDao.getCustomersList()
                .filter(c -> c.getId().equals(id)).next();
        return ServerResponse.ok().body(customerMono, Customer.class);
    }

    public Mono<ServerResponse> saveCustomer(ServerRequest request) {
        Mono<Customer> customerMono = request.bodyToMono(Customer.class);
        Mono<String> saveOne = customerMono.map(dto -> dto.getId() + " : " + dto.getName());
        return ServerResponse.ok().body(saveOne, String.class);
    }


}
