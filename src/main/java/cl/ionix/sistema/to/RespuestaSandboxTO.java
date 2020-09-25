
package cl.ionix.sistema.to;

import lombok.Builder;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Builder
public class RespuestaSandboxTO implements Serializable
{

    private Integer responseCode;
    private String description;
    private ResultSandboxTO result;
    private static final long serialVersionUID = 4104409181520784243L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public RespuestaSandboxTO() {
    }

    /**
     * 
     * @param result
     * @param description
     * @param responseCode
     */
    public RespuestaSandboxTO(Integer responseCode, String description, ResultSandboxTO result) {
        super();
        this.responseCode = responseCode;
        this.description = description;
        this.result = result;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ResultSandboxTO getResult() {
        return result;
    }

    public void setResult(ResultSandboxTO result) {
        this.result = result;
    }



}
