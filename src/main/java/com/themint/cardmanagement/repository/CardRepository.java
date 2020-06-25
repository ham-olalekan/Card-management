package com.themint.cardmanagement.repository;

import com.themint.cardmanagement.entity.Card;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CardRepository extends PagingAndSortingRepository<Card, Long> {
    Card findByCardNumber(String cardNumber);
}
