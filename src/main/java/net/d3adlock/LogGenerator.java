package net.d3adlock;

import com.github.javafaker.Faker;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class LogGenerator {

    private static BufferedWriter writer;

    private static String ipAddress;
    private static String name;
    private static String city;
    private static String country;
    private static String latency;

    public static void main(String[] args) throws IOException {
        Faker faker = new Faker();
        Random rand = new Random();

        while(true) {

            writer = new BufferedWriter(new FileWriter("mock.log"));
            ipAddress = String.format("%s.%s.%s.%s",
                    faker.number().numberBetween(140,240),
                    faker.number().numberBetween(140,240),
                    faker.number().numberBetween(140,240),
                    faker.number().numberBetween(140,240));
            name = faker.name().fullName();
            city = faker.address().city();
            country = faker.address().country();
            latency = String.format("%sms",faker.number().numberBetween(5,600));
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