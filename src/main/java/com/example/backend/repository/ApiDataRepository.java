package com.example.backend.repository;

import com.example.backend.entity.ApiData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiDataRepository extends JpaRepository<ApiData, Long> {

    ApiData findByEndpoint(String endpoint);
}
