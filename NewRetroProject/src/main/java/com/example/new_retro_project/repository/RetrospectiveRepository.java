package com.example.new_retro_project.repository;

import com.example.new_retro_project.models.Retrospective;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RetrospectiveRepository extends MongoRepository<Retrospective, String> {
}
