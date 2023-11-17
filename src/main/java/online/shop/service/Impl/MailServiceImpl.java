package online.shop.service.Impl;

import lombok.RequiredArgsConstructor;
import online.shop.model.User;
import online.shop.service.MailService;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    @Override
    public void sendUserVerificationMail(User user, HttpServletRequest request) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        String content = MailUtils.ACCOUNT_VERIFY_TEMPLATE;
        String link = UrlPathUtility.getSiteUrl(request) + "/verify?email=@EMAIL@&token=@TOKEN@";
        link = link.replace(tokenReplaceString, user.getToken());
        link = link.replace(emailReplaceString, user.getEmail());
        content = content.replace(MailUtils.LINK, link);
        try {
            helper.setText(content, true);
            helper.setTo(user.getEmail());
            helper.setSubject(MailUtils.ACCOUNT_VERIFY_HEADER);
            helper.setFrom(fromMail);
            mailSender.send(mimeMessage);


        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendPasswordRestoreMail(User user, HttpServletRequest request) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        String content = MailUtils.CHANGE_PASSWORD_TEMPLATE;
        String link = UrlPathUtility.getSiteUrl(request) + "/recovery?email=@EMAIL@&token=@TOKEN@";
        link = link.replace(tokenReplaceString, user.getToken());
        link = link.replace(emailReplaceString, user.getEmail());
        content = content.replace(MailUtils.LINK, link);
        try {
            helper.setText(content, true);
            helper.setTo(user.getEmail());
            helper.setSubject(MailUtils.ACCOUNT_CHANGE_PASSWORD_HEADER);
            helper.setFrom(fromMail);
            mailSender.send(mimeMessage);

            logsUtils.log(loggerMail, "Send password restore mail (email " + user.getEmail() + ")");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
