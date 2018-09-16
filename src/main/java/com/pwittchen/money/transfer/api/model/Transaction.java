package com.pwittchen.money.transfer.api.model;

import java.util.Objects;
import org.joda.money.Money;

public class Transaction {
  private final String id;
  private final Account from;
  private final Account to;
  private final Money money;

  private Transaction() {
    this(builder());
  }

  private Transaction(final Builder builder) {
    this(builder.id, builder.from, builder.to, builder.money);
  }

  private Transaction(final String id, final Account from, final Account to, final Money money) {
    this.id = id;
    this.from = from;
    this.to = to;
    this.money = money;
  }

  public static Builder builder() {
    return new Builder();
  }

  public String id() {
    return id;
  }

  public Account from() {
    return from;
  }

  public Account to() {
    return to;
  }

  public Money money() {
    return money;
  }

  @Override public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Transaction that = (Transaction) o;

    return Objects.equals(id, that.id)
        && Objects.equals(from, that.from)
        && Objects.equals(to, that.to)
        && Objects.equals(money, that.money);
  }

  @Override public int hashCode() {
    return Objects.hash(id, from, to, money);
  }

  public static class Builder {
    private String id;
    private Account from;
    private Account to;
    private Money money;

    private Builder() {
    }

    public Builder id(final String id) {
      this.id = id;
      return this;
    }

    public Builder from(final Account from) {
      this.from = from;
      return this;
    }

    public Builder to(final Account to) {
      this.to = to;
      return this;
    }

    public Builder money(final Money money) {
      this.money = money;
      return this;
    }

    public Transaction build() {
      return new Transaction(this);
    }
  }
}
