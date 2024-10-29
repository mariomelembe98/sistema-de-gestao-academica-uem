package com.uem.sgnfx.Services;


import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;
/**
 * Created by USER on 28/10/2024.
 */

public class EmailService {

    public void enviarEmailNovoUsuario(String destinatario, String nome, String apelido, String username, String email) {
        String remetente = "mariomelembe98@gmail.com";  // O seu endereço eletrónico do Gmail
        String senha = "melembe98";  // Senha de aplicativo

        String assunto = "Bem-vindo(a), " + nome;
        String mensagem = String.format(
                "Olá, %s %s!\n\n" +
                        "Seu usuário foi criado com sucesso.\n\n" +
                        "Nome de Usuário: %s\n" +
                        "Email: %s\n\n" +
                        "Acesse o sistema usando suas credenciais.\n\n" +
                        "Atenciosamente,\nEquipe de Suporte",
                nome, apelido, username, email
        );

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remetente, senha);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remetente));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject(assunto);
            message.setText(mensagem);

            Transport.send(message);
            System.out.println("E-mail enviado com sucesso!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }}
