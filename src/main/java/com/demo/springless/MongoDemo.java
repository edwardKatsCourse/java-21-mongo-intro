package com.demo.springless;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import org.bson.Document;

public class MongoDemo {

    public static void main(String[] args) {
//        MongoClient client = new MongoClient("mongodb://root:r00t@ds239128.mlab.com:39128/demo-database");
        MongoClientURI mongoClientURI = new MongoClientURI("mongodb://root:r00t@ds239128.mlab.com:39128/demo-database");
        MongoClient client = new MongoClient(mongoClientURI);
        MongoDatabase mongoDatabase = client.getDatabase("demo-database");


        MongoIterable<String> collections = mongoDatabase.listCollectionNames();
        boolean collectionsExists = false;
        for (String s : collections) {
            if (s.equals("persons")) {
                collectionsExists = true;
                break;
            }
        }

        if (!collectionsExists) {
            mongoDatabase.createCollection("persons");
        }

        MongoCollection<Document> persons = mongoDatabase.getCollection("persons");
        persons.insertOne(new Document(getPerson().getAsMap()));

        MongoIterable<Person> personsResult = persons
                .find()
//                .map(x -> Person.builder()
//                        .id(x.getString("id"))
//                        .lastName(x.getString("lastName"))
//                        .firstName(x.getString("firstName"))
//                        .age(x.getInteger("age"))
//                        .build()
//                );
                .map(x -> Person.builder()
                        .id(x.getString("id"))
                        .lastName(x.getString("lastName"))
                        .firstName(x.getString("firstName"))
                        .age(x.getInteger("age"))
                        .build()
                );
        for (Person person : personsResult) {
            System.out.println(person.getId());
            System.out.println(person.getFirstName());
            System.out.println(person.getLastName());
            System.out.println(person.getAge());
        }
    }


    private static Person getPerson() {
        return Person.builder()
                .id("1")
                .firstName("Peter")
                .lastName("Dale")
                .age(37)
                .build();
    }
}
