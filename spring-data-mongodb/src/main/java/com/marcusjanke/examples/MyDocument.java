package com.marcusjanke.examples;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Map;

@Data
@Accessors(chain = true)
@Document
public class MyDocument {

    @Id
    private String id;
    private Map<Instant, String> map;
}
