/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author Mayssa
 */
public class SMS {

    public SMS() {
    }
     
    public static final String ACCOUNT_SID = "ACc4a0f6c794f054db3ed5e0bc2a6c0d88";
    public static final String AUTH_TOKEN = "bce14234d9b6db83cc79989e0fecd615";

     public static void main(String[] args) {
    
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber("+21629402723"),
                new PhoneNumber("+13203628435"),
                "Votre nouveau Evenement est ajouté avec succée, ").create();

        System.out.println(message.getSid());

    }
}

