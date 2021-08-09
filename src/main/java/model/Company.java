package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class Company {
    private String name;
    private String catchPhrase;
    private String bs;

    public Company(){}

    public Company(CompanyBuilder companyBuilder){
        this.name = companyBuilder.name;
        this.catchPhrase = companyBuilder.catchPhrase;
        this.bs = companyBuilder.bs;
    }

    public static class CompanyBuilder {
        private String name;
        private String catchPhrase;
        private String bs;

        public CompanyBuilder setName(String name){
            this.name = name;
            return this;
        }

        public CompanyBuilder setCatchPhrase(String catchPhrase){
            this.catchPhrase = catchPhrase;
            return this;
        }

        public CompanyBuilder setBs(String bs){
            this.bs = bs;
            return this;
        }
         public Company build(){
            return new Company(this);
         }
    }
}
