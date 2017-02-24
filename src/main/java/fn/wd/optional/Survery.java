package fn.wd.optional;

/**
 * Created by fengweidong on 2016/12/26.
 */
public class Survery {

    private  Integer id;

    private Page page;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "Survery{" +
                "id=" + id +
                ", page=" + page +
                '}';
    }

    public Survery(Integer id, Page page) {
        this.id = id;
        this.page = page;
    }

    public Survery() {
    }
}
