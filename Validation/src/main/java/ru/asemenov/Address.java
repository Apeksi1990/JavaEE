package ru.asemenov;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Address {
    @NotNull
    private String street1;
    private String street2;
    @NotNull
    private String ciry;
    private String state;
    @NotNull
    @ZipCode
    private String zipcode;
    private String country;

    public Address(String street1, String ciry, String state, String zipcode, String country) {
        this.street1 = street1;
        this.ciry = ciry;
        this.state = state;
        this.zipcode = zipcode;
        this.country = country;
    }
}
