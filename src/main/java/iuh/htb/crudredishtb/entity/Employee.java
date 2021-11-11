package iuh.htb.crudredishtb.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Employee implements Serializable {
    private long id;
    private String name;
    private String address;
}
