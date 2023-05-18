package org.authentication.repository;


import org.authentication.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {


    @Query("from Token where tokenValue = :token")
    Token getTokenDetails(String token);
    @Query("from Token where username = :username")
    Optional<Token> getTokenByUsername(String username);


    @Query("select tokenValue from Token  where expired = false and revoked = false")
    Optional<String> getToken();

    @Modifying
    @Transactional
    @Query(value = "UPDATE user_schema.token " +
            "set expired = true, revoked = true, last_update = now() " +
            "WHERE username =  (select username from user_schema.token " +
            "where username = :username " +
            "and expired = false " +
            "and revoked = false)", nativeQuery = true)
    void logoutToken(String username);
}
