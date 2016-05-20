package org.yvasilchuk.domain.model.user;

import org.yvasilchuk.domain.entity.User;
import org.yvasilchuk.domain.model.cash.account.CashAccountModel;
import org.yvasilchuk.domain.model.cash.operation.CashOperationCategoryModel;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class UserAccount implements Serializable {
    private static final long serialVersionUID = -8843456140835441695L;

    private UserProfile profile;
    private List<CashOperationCategoryModel> categories;
    private List<CashAccountModel> cashAccounts;

    public UserAccount() {
    }

    public UserAccount(User u) {
        this.profile = new UserProfile(u);
        if (u.getCategories() != null) {
            this.categories = u.getCategories()
                    .stream()
                    .map(c -> new CashOperationCategoryModel(c, u.getId()))
                    .collect(Collectors.toList());
        }
        if (u.getAccounts() != null) {
            this.cashAccounts = u.getAccounts()
                    .stream()
                    .map(ca -> new CashAccountModel(ca, u.getId()))
                    .collect(Collectors.toList());
        }
    }

    public UserProfile getProfile() {
        return profile;
    }

    public void setProfile(UserProfile profile) {
        this.profile = profile;
    }

    public List<CashOperationCategoryModel> getCategories() {
        return categories;
    }

    public void setCategories(List<CashOperationCategoryModel> categories) {
        this.categories = categories;
    }

    public List<CashAccountModel> getCashAccounts() {
        return cashAccounts;
    }

    public void setCashAccounts(List<CashAccountModel> cashAccounts) {
        this.cashAccounts = cashAccounts;
    }
}
