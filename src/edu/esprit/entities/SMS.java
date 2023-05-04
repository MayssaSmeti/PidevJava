/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

// Install the Java helper library from twilio.com/docs/java/install
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author msi
 */
public class SMS {
    // Find your Account SID and Auth Token at twilio.com/console
    // and set the environment variables. See http://twil.io/secure
    public static final String ACCOUNT_SID = "AC062a98dbd60baecf3aef6b189b39fc78";
    public static final String AUTH_TOKEN = "9c9ae92dc594428b53a42c42d252b8a4";

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+21629132895"),
                new com.twilio.type.PhoneNumber("+16814024311"),
                "Je suis un(e) client(e) fidèle de votre agence depuis 2ans, et j'espère que nous pourrons travailler ensemble pour résoudre ce problème et continuer à maintenir une relation positive à long terme.\n" +
"\n" +
"Je vous remercie de prendre le temps de lire cette lettre de réclamation et j'attends votre réponse avec impatience.")
            .create();

        System.out.println(message.getSid());
    }
}

