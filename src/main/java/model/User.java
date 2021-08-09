package model;



import lombok.Getter;


@Getter
public class User {
    private int id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;

    public User(){}

    private User (UserBuilder userBuilder){
        this.id = userBuilder.id;
        this.name = userBuilder.name;
        this.username = userBuilder.username;
        this.email = userBuilder.email;
        this.address = userBuilder.address;
        this.phone = userBuilder.phone;
        this.website =  userBuilder.website;
        this.company = userBuilder.company;
    }


    public static  class  UserBuilder{

        private int id;
        private String name;
        private String username;
        private String email;
        private Address address;
        private String phone;
        private String website;
        private Company company;

        public UserBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public UserBuilder setName(String name) {
            this.name = name;
            return this;
        }


        public UserBuilder setUsername(String username) {
            this.username = username;
            return this;
        }
        public UserBuilder setEmail(String email){
            this.email = email;
            return this;
        }

        public UserBuilder setAddress(Address address) {
            this.address = address;
            return this;
        }

        public UserBuilder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public UserBuilder setWebsite(String website) {
            this.website = website;
            return this;
        }

        public UserBuilder setCompany(Company company) {
            this.company = company;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
