package com.york.oauth2.repositories;

import org.springframework.data.repository.Repository;

import com.york.oauth2.models.TokenBlackList;

import java.util.List;
import java.util.Optional;

public interface TokenBlackListRepo  extends Repository<TokenBlackList, Long> {
	
   Optional<TokenBlackList> findByJti(String jti);
   List<TokenBlackList> queryAllByUserIdAndIsBlackListedTrue(Long userId);
   void save(TokenBlackList tokenBlackList);
   List<TokenBlackList> deleteAllByUserIdAndExpiresBefore(Long userId, Long date);
   List<TokenBlackList> findAllByOrderByJtiAsc();
}
