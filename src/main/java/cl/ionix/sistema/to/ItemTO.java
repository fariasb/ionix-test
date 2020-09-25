
package cl.ionix.sistema.to;

import lombok.Builder;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Builder
public class ItemTO implements Serializable
{

    private String name;
    private DetailTO detail;
    private static final long serialVersionUID = 4828312265750089124L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ItemTO() {
    }

    /**
     * 
     * @param name
     * @param detail
     */
    public ItemTO(String name, DetailTO detail) {
        super();
        this.name = name;
        this.detail = detail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DetailTO getDetail() {
        return detail;
    }

    public void setDetail(DetailTO detail) {
        this.detail = detail;
    }



}
