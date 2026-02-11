package org.example.CarRentalSystem.user;

import java.util.List;
import java.util.Set;

public record User(String email, String name, Set<Contact> contactInfo, String licenseNumber) {
}
