package com.epam.services.userservices;

import java.util.Optional;

public class UserServicesFactory {
    public Optional<UserService> getUserservice(int choice) {
        UserService service = null;
        if (choice == 1) {
            service = new ValidateAdminCredentials();
        } else if (choice == 2) {
            service = new ValidatePlayerCredentials();
        } else if (choice == 3) {
            service = new RegisterAdmin();
        } else if (choice == 4) {
            service = new RegisterPlayer();
        }
        return Optional.ofNullable(service);
    }
}
