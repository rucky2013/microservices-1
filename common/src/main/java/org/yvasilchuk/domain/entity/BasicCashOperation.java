package org.yvasilchuk.domain.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("basic")
public class BasicCashOperation extends CashOperation {
}
