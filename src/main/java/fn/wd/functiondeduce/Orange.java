package fn.wd.functiondeduce;

/**
 * Created by fengweidong on 2016/12/24.
 */
public class Orange {

    private String name;
    private String type;
    private Long weight;

    public Orange(String name, String type, Long weight) {
        this.name = name;
        this.type = type;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }
}
