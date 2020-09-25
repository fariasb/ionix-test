
package cl.ionix.sistema.to;

import lombok.Builder;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ToString
@Builder
public class ResultSandboxTO implements Serializable
{

    private List<ItemTO> items = new ArrayList<>();
    private static final long serialVersionUID = 6860132522534095352L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ResultSandboxTO() {
    }

    /**
     * 
     * @param items
     */
    public ResultSandboxTO(List<ItemTO> items) {
        super();
        this.items = items;
    }

    public List<ItemTO> getItems() {
        return items;
    }

    public void setItems(List<ItemTO> items) {
        this.items = items;
    }



}
