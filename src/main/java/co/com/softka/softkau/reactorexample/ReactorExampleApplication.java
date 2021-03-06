package co.com.softka.softkau.reactorexample;

import co.com.softka.softkau.reactorexample.model.Person;
import co.com.softka.softkau.reactorexample.operators.*;
import co.com.softka.softkau.reactorexample.operators.Error;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Conditional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import io.reactivex.Observable;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class ReactorExampleApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(ReactorExampleApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ReactorExampleApplication.class, args);
    }

    public void reactor() {
        Mono.just(new Person(1, "David", 28))
                .doOnNext(p -> {
                    log.info("[Rezactor] person: " + p);
                    p.setName(p.getName().toLowerCase());
                })
                .subscribe(p -> log.info("[Rezactor] person: " + p));

    }

    public void rxJava2() {
        Observable.just(new Person(2, "Mariela", 73))

                .doOnNext(p -> {
                    log.info("[RxJava2] person: " + p);
                    p.setName(p.getName().toUpperCase());
                })
                .subscribe(p -> log.info("[RxJava2] person: " + p));
    }

    public void mono() {
        Mono.just(new Person(3, "Maria", 48))
                .subscribe(p -> log.info("Mono : " + p.toString()));
    }

    public void flux() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(4, "Ruben", 58));
        personList.add(new Person(5, "Robin", 38));
        personList.add(new Person(6, "David ", 28));

        Flux.fromIterable(personList)
                .subscribe(p -> log.info("FLux : "+ p.toString()));

    }

    public void fluxToMono(){
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(7, "Migel", 56));
        personList.add(new Person(8, "Camilo", 24));
        personList.add(new Person(9, "Pipe ", 22));

        Flux<Person> personFlux = Flux.fromIterable(personList);
        personFlux.collectList()
                .subscribe(list -> log.info(list.toString()));

    }



    @Override
    public void run(String... args) throws Exception {
        reactor();
        rxJava2();
        mono();
        flux();
        fluxToMono();
        Creation creation = new Creation();
        creation.range(5,3);
        creation.repeat(2 );
        Transformation transformation = new Transformation();
        transformation.map();
        transformation.mapRange(10,5  );
        transformation.flatMap();
        transformation.groupBy();
        Filtered filtered = new Filtered();
        filtered.filter();
        filtered.distinct();
        filtered.take(3);
        filtered.takeLast(2);
        filtered.skip(4);
        filtered.skipLast(3);
        Combination combination = new Combination();
        combination.merge();
        combination.zip();
        combination.zipWith();
        Error error = new Error();
        error.retry(2);
        error.errorReturn();
        error.errorResume();
        error.errorMap();
        Condition condition = new Condition();
        condition.defaultIfEmpty();
        condition.takeUntil("maria");
        condition.timeOut();
        Match match = new Match();
        match.average();
        match.count();
        match.min();
        match.sum();
        match.summarizing();
    }

}

