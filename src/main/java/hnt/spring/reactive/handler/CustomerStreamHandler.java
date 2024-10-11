package hnt.spring.reactive.handler;

import hnt.spring.reactive.dao.CustomerDao;
import hnt.spring.reactive.dto.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CustomerStreamHandler {

    private final CustomerDao customerDao;

    public Mono<ServerResponse> getCustomers(ServerRequest request) {
        Flux<Customer> customers = customerDao.getCustomersStream().log();
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(customers, Customer.class);
    }

}
