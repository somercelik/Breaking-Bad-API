package com.somercelik.apiforgeneralpurpose.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.UUID;

public class Person {
    private final UUID id;
    @NotBlank
    private final String name;
    @NotBlank
    private final String realName;
    @NotNull
    private final Date dateOfBirth;


    public Person(@JsonProperty("id") UUID id,
                  @JsonProperty("name") String name,
                  @JsonProperty("real_name") String realName,
                  @JsonProperty("date_of_birth") Date dateOfBirth) {
        this.id = id;
        this.name = name;
        this.realName = realName;
        this.dateOfBirth = dateOfBirth;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRealName() {
        return realName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }
}
