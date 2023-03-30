package model;

public class Veterinarian extends Worker {
    private final String specialization;

    public Veterinarian(String w_id, String name, float pay_rate, String address, String email, String phone, String specialization) {
        super(w_id, name, pay_rate, address, email, phone);
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }
}
