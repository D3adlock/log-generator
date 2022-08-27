package net.d3adlock;

import com.github.javafaker.Faker;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class LogGenerator {

    public static void main(String[] args) throws IOException {
        Faker faker = new Faker();
        Random rand = new Random();

        while(true) {
            BufferedWriter writer = new BufferedWriter(new FileWriter("mock.log"));
            String ipAddress = String.format("%s.%s.%s.%s",
                    faker.number().numberBetween(140,240),
                    faker.number().numberBetween(140,240),
                    faker.number().numberBetween(140,240),
                    faker.number().numberBetween(140,240));

            String name = faker.name().fullName();
            String city = faker.address().city();
            String country = faker.address().country();
            String latency = String.format("%sms",faker.number().numberBetween(5,600));

            writer.write(String.format("%s,%s,%s,%s\n",
                ipAddress,
                latency,
                name,
                city,
                country));

            writer.close();

            try {
                Thread.sleep(rand.nextInt(3) * 1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}