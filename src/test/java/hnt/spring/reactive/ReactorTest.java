package hnt.spring.reactive;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ReactorTest {

    @Test
    public void testMono() {
        Mono<String> monoString = Mono.just("hnt").log();
        monoString.subscribe(System.out::println);
    }

    @Test
    public void testFlux() {
        Flux<String> fluxString = Flux.just("one", "two", "Three")
                .concatWithValues("HNT")
                .concatWith(Flux.error(new RuntimeException("exception")))
                .log();
        fluxString.subscribe(System.out::println);
    }
}
