package online.shop.service;

import online.shop.model.User;

import javax.servlet.http.HttpServletRequest;

public interface MailService {
    void sendUserVerificationMail(User user, HttpServletRequest request);

    //void sendPasswordRestoreMail(User user, HttpServletRequest request);
}
