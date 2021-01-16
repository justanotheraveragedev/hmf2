package com.hmf.dao;

import com.hmf.service.Document;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentDao extends CrudRepository<Document, String>{
    
}
