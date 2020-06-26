package com.themint.cardmanagement.repository;

import com.themint.cardmanagement.entity.CardLookUpTracker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CardLookUpTrackerRepository extends PagingAndSortingRepository<CardLookUpTracker, String> {
    Page<CardLookUpTracker> findAll(Pageable pageable);
}
