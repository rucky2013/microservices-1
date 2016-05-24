package org.yvasilchuk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;
import org.yvasilchuk.domain.entity.CashAccount;
import org.yvasilchuk.domain.entity.Category;
import org.yvasilchuk.domain.entity.User;
import org.yvasilchuk.domain.enums.CashOperationCategoryType;
import org.yvasilchuk.domain.enums.Currency;
import org.yvasilchuk.domain.messages.ErrorMessages;
import org.yvasilchuk.domain.model.security.FacebookCredentials;
import org.yvasilchuk.domain.model.security.TwitterCredentials;
import org.yvasilchuk.domain.model.security.WebCredentials;
import org.yvasilchuk.domain.model.security.WebRegistrationDetails;
import org.yvasilchuk.domain.model.user.UserProfile;
import org.yvasilchuk.domain.model.user.UserSearchModel;
import org.yvasilchuk.domain.requests.RegistrationRequest;
import org.yvasilchuk.domain.requests.SigninRequest;
import org.yvasilchuk.exceptions.AuthenticationException;
import org.yvasilchuk.exceptions.EntityNotFoundException;
import org.yvasilchuk.exceptions.InternalServerException;
import org.yvasilchuk.exceptions.InvalidRequestException;
import org.yvasilchuk.repository.CashAccountRepository;
import org.yvasilchuk.repository.CategoryRepository;
import org.yvasilchuk.repository.UserRepository;
import org.yvasilchuk.service.UserService;
import org.yvasilchuk.specification.UserSpecification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("DB_USER_SERVICE")
public class UserServiceImpl implements UserService {
    @Autowired
    ShaPasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;
    @Autowired
    CashAccountRepository cashAccountRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Value("${security.password.salt:f7f5e5f4sws5e4f3fHGhhH$h}")
    private String PASSWORD_SALT;

    @Override
    public User signup(RegistrationRequest request) {
        switch (request.getRegistrationType()) {
            case FACEBOOK:
                return FacebookSignup(request.getFbCredentials());
            case TWITTER:
                return TwitterSignup(request.getTwitterCredentials());
            case WEB:
                return WebSignup(request.getWebDetails());
        }
        throw new InternalServerException(ErrorMessages.SIGNUP_INVALID_REGISTRATION_TYPE);
    }

    @Override
    public User signin(SigninRequest request) {
        User user;
        switch (request.getPlatformInfo().getPlatformType()) {
            case WEB:
                user = webSignin(request);
                break;
            case ANDROID:
                user = androidSignin(request);
                break;
            case IOS:
                user = iosSignin(request);
                break;
            case ANOTHER:
                user = unknownPlatformSignin(request);
                break;
            default:
                throw new AuthenticationException(ErrorMessages.UNKNOWN_PLATFORM_TYPE);
        }

        if (user == null) {
            throw new AuthenticationException(ErrorMessages.AUTHENTICATION_EXCEPTION);
        }

        return user;
    }

    @Override
    public List<User> find(UserSearchModel request) {
        if (request != null && !request.isEmpty()) {
            UserSpecification specification = new UserSpecification(request);
            return userRepository.findAll(specification);
        } else {
            return userRepository.findAll();
        }
    }

    @Override
    public User updateUser(UserProfile request) {
        User user = userRepository.findOne(request.getId());
        if (user == null) {
            throw new EntityNotFoundException(ErrorMessages.USER_NOT_FOUND);
        }

        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setTwitterId(request.getTwitterId());
        user.setFacebookId(request.getFacebookId());

        user = userRepository.save(user);

        return user;
    }

    @Override
    public User patchUser(UserProfile request) {
        User user = userRepository.findOne(request.getId());
        if (user == null) {
            throw new EntityNotFoundException(ErrorMessages.USER_NOT_FOUND);
        }

        if (request.getUsername() != null) {
            user.setEmail(request.getEmail());
        }
        if (request.getUsername() != null) {
            user.setUsername(request.getUsername());
        }
        if (request.getTwitterId() != null) {
            user.setTwitterId(request.getTwitterId());
        }
        if (request.getFacebookId() != null) {
            user.setFacebookId(request.getFacebookId());
        }

        user = userRepository.save(user);
        return user;
    }

    @Override
    public User get(Integer id) {
        return userRepository.findOne(id);
    }

    @Override
    public User get(UserSearchModel request) {
        if (request == null || request.isEmpty()) {
            throw new InvalidRequestException(ErrorMessages.INVALID_REQUEST);
        }

        if (request.getUsername() != null && !request.getUsername().isEmpty()) {
            return userRepository.findByUsername(request.getUsername());
        } else if (request.getEmail() != null && !request.getEmail().isEmpty()) {
            return userRepository.findByEmail(request.getEmail());
        }
        throw new InvalidRequestException(ErrorMessages.INVALID_REQUEST);
    }

    private User unknownPlatformSignin(SigninRequest request) {
        UnsupportedOperationException unsupportedOperationException = new UnsupportedOperationException("Only web signin supported");
        throw new InternalServerException(unsupportedOperationException);
    }

    private User iosSignin(SigninRequest request) {
        UnsupportedOperationException unsupportedOperationException = new UnsupportedOperationException("Only web signin supported");
        throw new InternalServerException(unsupportedOperationException);
    }

    private User androidSignin(SigninRequest request) {
        UnsupportedOperationException unsupportedOperationException = new UnsupportedOperationException("Only web signin supported");
        throw new InternalServerException(unsupportedOperationException);
    }

    private User webSignin(SigninRequest request) {
        WebCredentials credentials = request.getWebCredentials();
        if (credentials == null) {
            throw new InvalidRequestException(ErrorMessages.INVALID_REQUEST);
        }
        User user = userRepository.findByUsername(credentials.getUsername());
        if (user == null) {
            user = userRepository.findByEmail(credentials.getUsername());
        }

        if (user == null) {
            throw new AuthenticationException(ErrorMessages.USERNAME_NOT_FOUND);
        }

        String password = credentials.getPassword();
        password = passwordEncoder.encodePassword(password, PASSWORD_SALT);

        if (!password.equals(user.getPassword())) {
            throw new AuthenticationException(ErrorMessages.INVALID_CREDENTIALS);
        }

        return user;
    }

    private User WebSignup(WebRegistrationDetails webDetails) {
        webDetails.setPassword(passwordEncoder.encodePassword(webDetails.getPassword(), PASSWORD_SALT));
        User user = new User(webDetails);
        user = userRepository.save(user);

        generateDefaultCategoriesForNewUser(user);
        generateDefaultAccountsForNewUser(user);
        return user;
    }

    private void generateDefaultAccountsForNewUser(User user) {//TODO: make default accounts configurable
        CashAccount cardAccountUsd = new CashAccount();
        cardAccountUsd.setName("USD card account");
        cardAccountUsd.setCurrency(Currency.USD);
        cardAccountUsd.setBalance(0L);
        cardAccountUsd.setOwner(user);

        CashAccount cardAccountUah = new CashAccount();
        cardAccountUah.setName("UAH card account");
        cardAccountUah.setCurrency(Currency.UAH);
        cardAccountUah.setBalance(0L);
        cardAccountUah.setOwner(user);

        CashAccount currentAccountUah = new CashAccount();
        currentAccountUah.setName("UAH cash");
        currentAccountUah.setCurrency(Currency.UAH);
        currentAccountUah.setBalance(0L);
        currentAccountUah.setOwner(user);

        List<CashAccount> cashAccounts = Arrays.asList(cardAccountUah, cardAccountUsd, currentAccountUah);
        cashAccountRepository.save(cashAccounts);
    }

    private void generateDefaultCategoriesForNewUser(User user) {//TODO: make default categories configurable
        List<Category> categories = new ArrayList<>();

        Category c = new Category();
        c.setOwner(user);
        c.setName("Salary");
        c.setType(CashOperationCategoryType.INCOME);
        categories.add(c);

        c = new Category();
        c.setOwner(user);
        c.setName("Cigarettes");
        c.setType(CashOperationCategoryType.EXPENSE);
        categories.add(c);

        c = new Category();
        c.setOwner(user);
        c.setName("Alcohol");
        c.setType(CashOperationCategoryType.EXPENSE);
        categories.add(c);

        categoryRepository.save(categories);
    }

    private User TwitterSignup(TwitterCredentials twitterCredentials) {
        UnsupportedOperationException unsupportedOperationException = new UnsupportedOperationException("Only web registration supported");
        throw new InternalServerException(unsupportedOperationException);
    }

    private User FacebookSignup(FacebookCredentials fbCredentials) {
        UnsupportedOperationException unsupportedOperationException = new UnsupportedOperationException("Only web registration supported");
        throw new InternalServerException(unsupportedOperationException);
    }
}
