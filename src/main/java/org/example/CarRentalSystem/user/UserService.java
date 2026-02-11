package org.example.CarRentalSystem.user;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class UserService {
    private Map<String, User> users;

    public UserService(){
        this.users = new ConcurrentHashMap<>();
    }

    public User createUser(
            String email,
            String name,
            Set<Contact> contactList,
            String licenseNumber){
        if(Objects.isNull(contactList)) contactList = new HashSet<>(); // set should be used by contact type + data
        return users.putIfAbsent(email, new User(email, name, contactList, licenseNumber));
    }
}
