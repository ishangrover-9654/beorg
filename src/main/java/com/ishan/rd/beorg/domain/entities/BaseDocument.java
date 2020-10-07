package com.ishan.rd.beorg.domain.entities;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Ref;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.*;

import java.time.Instant;

@Getter @Setter
public class BaseDocument {

    @Id
    String _id;

    @CreatedDate
    private Instant created;

    @LastModifiedDate
    private Instant modified;

}
