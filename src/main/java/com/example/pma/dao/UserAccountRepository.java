package com.example.pma.dao;

import com.example.pma.entities.UserAccount;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by George Fouche on 11/30/19.
 */
public interface UserAccountRepository extends CrudRepository<UserAccount, Long>  {


}
