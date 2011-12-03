package br.com.wordmapper.service.container;

import com.google.gson.Gson;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Felipe e Bruno
 */
public class UserContainer implements ContainerItf {

    private int SqlOperation;
    private Integer Id;
    private String FirstName;
    private String LastName;
    private String Email;
    private String City;
    private String Country;
    private String ZipCode;
    public static final int INSERT = 0;
    public static final int UPDATE = 1;
    public static final int DELETE = 2;

    public int getSqlOperation() {
        return SqlOperation;
    }

    public void setSqlOperation(int SqlOperation) {
        this.SqlOperation = SqlOperation;
    }

    public Integer getId() {
        return Id;
    }

    public String getId(Boolean SqlEncode, Boolean virg) {
        return Id.toString() + ((virg) ? "," : "");
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getCity() {
        return City;
    }

    public String getCity(Boolean SqlEncode, Boolean virg) {
        return "'" + this.City.replace("'", "''") + "'" + ((virg) ? "," : "");
    }

    public void setCity(String City) {
        this.City = City;
    }

    public String getCountry() {
        return Country;
    }

    public String getCountry(Boolean SqlEncode, Boolean virg) {
        return "'" + this.Country.replace("'", "''") + "'" + ((virg) ? "," : "");
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

    public String getEmail() {
        return Email;
    }

    public String getEmail(Boolean SqlEncode, Boolean virg) {
        return "'" + this.Email.replace("'", "''") + "'" + ((virg) ? "," : "");
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getFirstName(Boolean SqlEncode, Boolean virg) {
        return "'" + this.FirstName.replace("'", "''") + "'" + ((virg) ? "," : "");
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getLastName(Boolean SqlEncode, Boolean virg) {
        return "'" + this.LastName.replace("'", "''") + "'" + ((virg) ? "," : "");
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getZipCode() {
        return ZipCode;
    }

    public String getZipCode(Boolean SqlEncode, Boolean virg) {
        return "'" + this.ZipCode.replace("'", "''") + "'" + ((virg) ? "," : "");
    }

    public void setZipCode(String ZipCode) {
        this.ZipCode = ZipCode;
    }

    public String getLicense() {
        String dados = getFirstName() + getLastName() + getEmail() + getCity() + getCountry();

        MessageDigest md;
        BigInteger hash;
        try {
            md = MessageDigest.getInstance("MD5");
            hash = new BigInteger(1, md.digest(dados.getBytes("UTF-8")));

            return hash.toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getLicense(Boolean SqlEncode, Boolean virg) {
        String dados = getFirstName() + getLastName() + getEmail() + getCity() + getCountry();

        MessageDigest md;
        BigInteger hash;
        try {
            md = MessageDigest.getInstance("MD5");
            hash = new BigInteger(1, md.digest(dados.getBytes("UTF-8")));

            return "'" + hash.toString(16) + "'" + ((virg) ? "," : "");

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getJson() {
        return new Gson().toJson(this, this.getClass());
    }
}
