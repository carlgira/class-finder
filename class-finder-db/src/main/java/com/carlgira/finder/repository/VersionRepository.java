package com.carlgira.finder.repository;

import com.carlgira.finder.entity.Version;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VersionRepository  extends JpaRepository<Version, Long> {

    List<Version> findByNumber(String number);
}
