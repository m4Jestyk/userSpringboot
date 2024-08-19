package com.crud.practice.userRepository;


import com.crud.practice.userModel.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepository extends JpaRepository<UserModel, Long> {
}
