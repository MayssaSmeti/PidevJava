/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class SmsController implements Initializable {

    @FXML
    private TextField txtapi;
    @FXML
    private TextField txtsender;
    @FXML
    private TextField txtnumber;
    @FXML
    private TextArea txtmss;
    @FXML
    private Button btnsend;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void send(ActionEvent event) {
        try {
			// Construct data
			String apiKey = "apikey=" + "NGY2ODVhMzM1OTcwMzI0NjMxNmU3NjU5Nzk3MzZkNjY=	";
			String message = "&message=" + txtmss.getText();
			String sender = "&sender=" + txtsender.getText();
			String numbers = "&numbers=" + txtnumber.getText();
			
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				//stringBuffer.append(line);
                                JOptionPane.showMessageDialog(null, "message"+line);
			}
			rd.close();
			
			//return stringBuffer.toString();
		} catch (Exception e) {
			//System.out.println("Error SMS "+e);
                        JOptionPane.showMessageDialog(null, e);
			//return "Error "+e;
		}
    }
    
}
