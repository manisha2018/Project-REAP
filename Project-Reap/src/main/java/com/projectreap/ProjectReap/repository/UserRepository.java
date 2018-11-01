package com.projectreap.ProjectReap.repository;

import com.projectreap.ProjectReap.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {
}
