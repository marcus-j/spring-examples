package com.marcusjanke.examples;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MyDocumentRepository extends MongoRepository<MyDocument, String> {
}
