package sample.Models.BasicModel;

import java.util.Objects;

public class Person {

    // prop
    private  long id;
    private String name;
    private String phone;

    // Constructor
    public Person(String name, String phone)
    {
        this.name=name;
        this.phone = phone;
    }

    public Person(long id, String name, String phone) {
        this.name = name;
        this.id = id;
        this.phone = phone;
    }

    // Getter
    public long getId()
    {
        return id;
    }
    public String getName()
    {
        return name;
    }
    public String getPhone() {
        return phone;
    }


    // Setter
    public void setId(long id) {
        this.id = id;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return getId() == person.getId() && getName().equals(person.getName()) && getPhone().equals(person.getPhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPhone());
    }
}