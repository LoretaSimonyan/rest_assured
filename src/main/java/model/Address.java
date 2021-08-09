package model;

import lombok.Data;

@Data
public class Address {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;

    public Address(){}
    private Address(AddressBuilder addressBuilder){
        this.street = addressBuilder.street;
        this.suite = addressBuilder.suite;
        this.city = addressBuilder.city;
        this.zipcode = addressBuilder.zipcode;
        this.geo = addressBuilder.geo;
    }

    public static class AddressBuilder {
        private String street;
        private String suite;
        private String city;
        private String zipcode;
        private Geo geo;

        public AddressBuilder setStreet(String street) {
            this.street = street;
            return this;
        }

        public AddressBuilder setSuite(String suite) {
            this.suite = suite;
            return this;
        }

        public AddressBuilder setCity(String city) {
            this.city = city;
            return this;
        }

        public AddressBuilder setZipcode(String zipcode){
            this.zipcode  = zipcode;
            return this;
        }

        public AddressBuilder setGeo(Geo geo){
            this.geo = geo;
            return this;
        }

        public Address build(){
            return new Address(this);
        }
    }
}
