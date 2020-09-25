
package cl.ionix.sistema.to;

import lombok.ToString;

import java.io.Serializable;

@ToString
public class DetailTO implements Serializable
{

    private static final long serialVersionUID = 4269198723146223905L;

    private String email;
    private String phoneNumber;


    /**
     * No args constructor for use in serialization
     * 
     */
    public DetailTO() {
    }

    /**
     * 
     * @param phoneNumber
     * @param email
     */
    public DetailTO(String email, String phoneNumber) {
        super();
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }



}
