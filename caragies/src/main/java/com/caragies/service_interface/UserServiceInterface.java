package com.caragies.service_interface;

import com.caragies.entitymodel.Users;

public interface UserServiceInterface {

    String signup(Users user);

    String login(Users user);
}
