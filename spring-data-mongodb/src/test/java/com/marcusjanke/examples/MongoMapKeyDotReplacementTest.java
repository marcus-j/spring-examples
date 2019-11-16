package com.marcusjanke.examples;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.test.context.junit4.SpringRunner;

import static java.time.Instant.now;
import static java.util.Collections.singletonMap;
import static org.junit.Assert.assertEquals;

@DataMongoTest
@RunWith(SpringRunner.class)
public class MongoMapKeyDotReplacementTest {

    @Autowired
    private MyDocumentRepository repository;

    @Test
    public void shouldConvertMapKeyContainingDots() {
        final MyDocument myDocument = new MyDocument().setMap(singletonMap(now(), "hello"));

        final MyDocument savedDoc = repository.save(myDocument);

        assertEquals(myDocument, savedDoc);
    }

    @TestConfiguration
    static class MongoMapKeyDotReplacementConfiguration {

        @Autowired
        public void setMapKeyDotReplacement(MappingMongoConverter mongoConverter) {
            mongoConverter.setMapKeyDotReplacement("#");
        }
    }
}