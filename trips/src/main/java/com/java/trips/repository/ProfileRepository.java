// ProfileRepository.java
package com.java.trips.repository;

import com.java.trips.Model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {
    Profile findByLoginId(int loginId);
}