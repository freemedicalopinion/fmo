package com.healthcare.freemedicalopinion.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.healthcare.freemedicalopinion.entityobject.UserProfileEO;

@Repository
public interface UserProfileRepository extends
		BaseRepository<UserProfileEO, String> {

	List<UserProfileEO> findByUsername(String username);

}
