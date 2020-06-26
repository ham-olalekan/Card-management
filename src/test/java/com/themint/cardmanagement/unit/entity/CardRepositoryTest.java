package com.themint.cardmanagement.unit.entity;

import com.themint.cardmanagement.entity.Card;
import com.themint.cardmanagement.model.CardStatus;
import com.themint.cardmanagement.repository.CardRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CardRepositoryTest {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private CardRepository cardRepository;


    @Test
    public void injectedComponentsAreNotNull() {
        assertNotNull(testEntityManager);
        assertNotNull(entityManager);
        assertNotNull(cardRepository);
    }

    @Test
    public void findByCardNumber() {
        // create card
        Card card = new Card();
        // set fields
        card.setCardNumber("12345");
        card.setBank("");
        card.setScheme("");
        card.setStatus(CardStatus.INVALID);

        entityManager.persist(card);

        Card actual = cardRepository.findByCardNumber("12345");
        assertNotNull(actual);
        assertEquals(card.getId(), actual.getId());
    }
}
