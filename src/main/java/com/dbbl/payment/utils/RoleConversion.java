package com.dbbl.payment.utils;

import com.dbbl.payment.constants.Role;
import com.dbbl.payment.model.UserAccount;
import com.dbbl.payment.model.UserRole;

import java.util.*;

public class RoleConversion {

    public static Set<UserRole> convertRole(String roleName, UserAccount user) {
        if (roleName.equals("Admin")) {
            return new HashSet<>(Arrays.asList(
                    new UserRole(Role.ADMIN.name(), user),
                    new UserRole(Role.USER.name(), user)
            ));
        } else {
            return new HashSet<>(Arrays.asList(
                    new UserRole(Role.USER.name(), user)
            ));

        }
    }
}
