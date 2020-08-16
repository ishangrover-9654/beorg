package com.ishan.rd.beorg.domain.edges;

import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;
import com.ishan.rd.beorg.domain.entities.IssueTag;
import com.ishan.rd.beorg.domain.entities.SpecialityTag;
import lombok.Getter;
import lombok.Setter;

@Edge @Getter @Setter
public class BelongsToSpeciality {
    private String id;

    @From
    private IssueTag issueTag;

    @To
    private SpecialityTag specialityTag;
}