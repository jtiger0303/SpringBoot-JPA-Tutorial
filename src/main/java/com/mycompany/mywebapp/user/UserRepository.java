package com.mycompany.mywebapp.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    //id의 타입이 integer이므로//

}
