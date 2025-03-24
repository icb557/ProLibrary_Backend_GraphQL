package com.librarySpring.librarySpring.Entities.Person.Enums;

public enum PersonRoles {
    ADMIN("Admin"),
    EMPLOYEE("Employee");

    private final String role;

    PersonRoles(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public static boolean isValidRole(String value) {
        for (PersonRoles role : PersonRoles.values()) {
            if (role.getRole().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }

}
