package com.healthcare.freemedicalopinion.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.healthcare.freemedicalopinion.entityobject.UserEO;


@Repository
public interface UserRepository extends BaseRepository<UserEO, String> {

	List<UserEO> findByUsername(String username);

	List<UserEO> findByRoles(List<String> roles);

}
