package com.pawandootshop.pawandootshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pawandootshop.pawandootshop.model.TokenLog;

@Repository
public interface TokenLogRepository extends JpaRepository<TokenLog,Integer>{


 TokenLog findBytoken(String token);
 
  }
