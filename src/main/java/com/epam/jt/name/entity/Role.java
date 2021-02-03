package com.epam.jt.name.entity;

public enum Role {
    ADMIN, LIBRARIAN, USER;

   /* public static Role getRole(User user) {
        //int roleId = user.getRoleId();
        //return Role.values()[roleId];
    }
*/
    public String getName() {
        return name().toLowerCase();
    }

}
