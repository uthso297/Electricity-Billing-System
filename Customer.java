public class Customer extends User {
    private String customerId;
    private String name;
    private String address;

    public Customer(String username, String password, String customerId, String name, String address) {
        super(username, password, "customer");
        this.customerId = customerId;
        this.name = name;
        this.address = address;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

}
