package com.theironyard.charlotte;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PurchasesRepository extends CrudRepository<Purchase, Integer> {

    List<Purchase> findByCategory(String category);

    List<Purchase> findByCreditCard(String cardNumber);

    List<Purchase> findByCvv(int cvv);

    List<Purchase> findByDate(String date);
}
