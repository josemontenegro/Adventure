package com.desafiolatam.adventure.data;

public class EmailSanitized {

    public String emailSanitized(String email){

        return email.replace("@", "AV").replace(".", "TUR");
    }
}
