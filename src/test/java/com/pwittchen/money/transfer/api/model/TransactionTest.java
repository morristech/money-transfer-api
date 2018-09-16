package com.pwittchen.money.transfer.api.model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class TransactionTest {

  @Test
  public void constructorShouldBePrivate() throws NoSuchMethodException, IllegalAccessException,
      InvocationTargetException, InstantiationException {

    // when
    Constructor<Transaction> constructor = Transaction.class.getDeclaredConstructor();

    // then
    assertThat(Modifier.isPrivate(constructor.getModifiers())).isTrue();

    constructor.setAccessible(true);
    constructor.newInstance();
  }

  @Test
  public void objectsShouldBeEqual() {
    // when
    Transaction transactionOne = createTransaction();
    Transaction transactionTwo = createTransaction();

    // then
    assertThat(transactionOne.equals(transactionTwo)).isTrue();
  }

  @Test
  public void objectsShouldBeEqualWhenTheyAreTheSameInstance() {
    // when
    Transaction transaction = createTransaction();

    // then
    assertThat(transaction.equals(transaction)).isTrue();
  }

  @Test
  public void objectsShouldNotBeEqualWhenOneIsNull() {
    // when
    Transaction transaction = createTransaction();

    // then
    assertThat(transaction.equals(null)).isFalse();
  }

  @Test
  public void objectsShouldBeInTheSameBucket() {
    // when
    Transaction transactionOne = createTransaction();
    Transaction transactionTwo = createTransaction();

    // then
    assertThat(transactionOne.hashCode() == transactionTwo.hashCode()).isTrue();
  }

  private Transaction createTransaction() {
    return Transaction.builder()
        .id("TR1")
        .from(createAccount())
        .to(createAccount())
        .money(Money.of(CurrencyUnit.EUR, 10))
        .build();
  }

  private Account createAccount() {
    User user = User.builder()
        .id("1")
        .name("John")
        .surname("Doe")
        .build();

    return Account.builder()
        .number("1")
        .user(user)
        .money(Money.of(CurrencyUnit.EUR, 10))
        .build();
  }
}