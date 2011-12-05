package br.com.wordmapper.service.container;

import com.google.gson.Gson;

/**
 *
 * @author Felipe e Bruno
 */
public class ErrorContainer implements ContainerItf {

    private Integer intCodeError;
    private String strDescError;

    public ErrorContainer() {
    }

    public Integer getCodeError() {
        return this.intCodeError;
    }

    public void setCodeError(Integer codeError) {
        this.intCodeError = codeError;
    }

    public String getDescError() {
        return this.strDescError;
    }

    public void setDescError(String descError) {
        this.strDescError = descError;
    }

    public String getJson() {
        return new Gson().toJson(this, this.getClass());
    }
}
