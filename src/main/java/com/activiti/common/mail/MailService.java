package com.activiti.common.mail;

/**发送邮件接口
 * Created by 12490 on 2017/8/2.
 */
public interface MailService {

    void sendSimpleMail(String desAddr, String subject, String content);

    void sendHtmlMail(String to, String subject, String content);

    void sendAttachmentsMail(String to, String subject, String content, String filePat);

    void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId);
}
