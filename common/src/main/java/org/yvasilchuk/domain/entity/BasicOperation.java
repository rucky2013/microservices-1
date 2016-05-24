package org.yvasilchuk.domain.entity;

import org.yvasilchuk.domain.model.cash.operation.OperationModel;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("basic")
public class BasicOperation extends Operation {
    public BasicOperation() {
    }

    public BasicOperation(OperationModel request) {
        super(request);
    }
}
