package com.helk.fraud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface FraudCheckHistoryRepository extends JpaRepository<FraudCheckHistory, Integer> {


}

