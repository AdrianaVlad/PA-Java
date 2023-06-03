package com.mycompany.elevatorsimulator;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author Vlad Adriana
 */
@SpringBootApplication
@ComponentScan({"controllers","repositories","entities","services","com.mycompany.elevatorsimulator"})
public class ElevatorSimulator {

    public static void main(String[] args) {
        SpringApplication.run(ElevatorSimulator.class, args);
    }
}
