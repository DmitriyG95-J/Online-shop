package online.shop.service.Impl;

import lombok.RequiredArgsConstructor;
import online.shop.model.User;
import online.shop.service.MailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;

    public static final String ACCOUNT_VERIFY_TEMPLATE = "<div>\n" +
            "    <h3>Приветствуем!</h3>\n" +
            "    <p style=\"font-size: 20px;\">Пожалуйста, подтвердите свой аккаунт, перейдя по ссылке:</p>\n" +
            "    <p style=\"font-size: 20px;\">@LINK@</p>\n" +
            "</div>";

    public static final String ACCOUNT_VERIFY_HEADER = "Верификация аккаунта в Online-SHOP";

    @Value("${sender.mail}")
    private String fromMail;

    @Override
    public void sendUserVerificationMail(User user, HttpServletRequest request) {
        if (user == null || user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("User or user's email is null or empty.");
        }

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        String content = ACCOUNT_VERIFY_TEMPLATE;
        String link = generateVerificationLink(user, request);
        content = content.replace("@LINK@", link);

        try {
            helper.setText(content, true);
            helper.setTo(user.getEmail());
            helper.setSubject(ACCOUNT_VERIFY_HEADER);
            helper.setFrom(fromMail);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateVerificationLink(User user, HttpServletRequest request) {
        UrlPathUtility urlPathUtility = new UrlPathUtility();
        return urlPathUtility.getSiteUrl(request) + "/verify?email=" + user.getEmail();
    }

    public static class UrlPathUtility {
        public String getSiteUrl(HttpServletRequest request) {
            String siteUrl = request.getRequestURL().toString();
            return siteUrl.replace(request.getServletPath(), "");
        }
    }
}
