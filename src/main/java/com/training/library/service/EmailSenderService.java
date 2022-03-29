package com.training.library.service;

import com.training.library.model.Image;
import com.training.library.model.Mail;

import javax.mail.MessagingException;

public interface EmailSenderService {

    void sendEmail(Mail mal, Image image) throws MessagingException;

}
